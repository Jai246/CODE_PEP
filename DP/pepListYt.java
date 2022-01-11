import java.util.Arrays;
public class pepListYt 
{

    // unbounded knapsack

    // class itemComparator implements Comparable<Item>
    // {
    //     @Override
    //     public int compare(Item a , Item b)
    //     {
    //         double r1 = (double)a.value/(double)a.weight;
    //         double r2 = (double)b.value/(double)b.weight;
            
    //         if(r1 < r2) return 1;
    //         else if(r1 > r2) return -1;
            
    //         return 0;
    //     }
    // }

    // to implement the above comparator we should use Collections.sort(arr, new itemComparator());
    double fractionalKnapsack(int W, Item arr[], int n) 
    {
        // Arrays.sort(arr ,new itemComparator());
        
        Arrays.sort(arr ,(a,b)->{
            return (b.value * a.weight) - (a.value * b.weight);
        });
        
        double profit = 0;
        
        for(Item ele : arr)
        {
            int weight = ele.weight;
            int value = ele.value;
            
            if(W-weight>=0)
            {
                W = W - weight;
                profit+=value;
            }
            else
            {
                double newVal = (double) ((double)W/(double)weight) * (double)value;
                profit += newVal;
                break;
            }
        }
        return profit;
    }

    

    // REGULAR EXPRESSION MATCHING LEEETCODE IMPORTANT
    // CHECK DRY-RUN VIDEO
    public boolean isMatch(String s, String p) 
    {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for(int i = 1 ; i < dp.length; i++)
        {
            for(int j = 0 ; j < dp[0].length; j++)
            {
                if(j == 0 )
                {
                    if(p.charAt(i - 1) == '*')
                        dp[i][j] = dp[i - 2][j];
                }
                else
                {
                    if(p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j - 1))
                    {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    else if(p.charAt(i - 1) == '*')
                    {
                        dp[i][j] = dp[i - 2][j];
                        if(p.charAt(i - 2) == s.charAt(j - 1) || p.charAt(i - 2) == '.')
                        {
                            dp[i][j] = dp[i][j - 1] || dp[i - 2][j];
                        }
                    }
                }
                
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];    
    }

    // HIGHWAY BILLBOARD PROBLEM
    // O(M) TIME AND SPACE SOLUTION
    public static int solution(int m , int[] x, int[] rev, int t) {
		int[] dp = new int[m + 1];
		int j = 0;
		for(int i = 1; i <= m; i++) {
			if( j < x.length && x[j] == i) {
				dp[i] = Math.max(dp[i - 1],(i - t - 1 >= 0 ? dp[i - t - 1] : 0) + rev[j]);
				j++;
			}else {
				dp[i] = dp[ i - 1];
			}
        }
        return dp[m];
    }

    // maximal square Leetcode
    public int maximalSquare(char[][] matrix) 
    {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = 0;
        int[][] dp = new int[n][m];
        int[][] dir = new int[][]{{0,1},{1,1},{1,0}};
        for(int i = n-1;i>=0;i--)
        {
            for(int j = m-1;j>=0;j--)
            {
                if(i == n-1 && j == m-1)
                {
                    dp[i][j] = (matrix[i][j] == '1') ? 1 : 0;
                    max = Math.max(max , dp[i][j]);
                }
                else if(i == n-1)
                {
                    if(j<m-1) dp[i][j] = (matrix[i][j] == '1') ? 1 : 0;
                    max = Math.max(max , dp[i][j]);
                }
                else if(j == m-1)
                {
                    dp[i][j] = (matrix[i][j] == '1') ? 1 : 0;
                    max = Math.max(max , dp[i][j]);
                }
                else if(matrix[i][j] == '1')
                {
                    int min = (int)1e9;
                    for(int [] ele : dir)
                    {
                        int x = i+ele[0];
                        int y = j+ele[1];
                        if(x>=0 && y>=0 && x<n && y<m) min = Math.min(min,dp[x][y]);
                    }
                    dp[i][j] = 1 + ((min==(int)1e9) ? 0 : min);
                    max = Math.max(max , dp[i][j]);
                }
            }
        }
        return max*max;
    }

    // 650. 2 Keys Keyboard
    public int minSteps(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }


    // 354. Russian Doll Envelopes

    public int maxEnvelopes(int[][] env) 
    {
        Arrays.sort(env,(a,b)->{
            return a[0] - b[0];
        });
        int[] dp = new int[env.length];
        int oMax = 0;
        for(int i = 0;i<env.length;i++)
        {
            dp[i] = 1;
            int max = 0;
            for(int j = i;j>=0;j--)
            {
                if(env[i][1] > env[j][1] && env[i][0] > env[j][0])
                {
                    max = Math.max(dp[j],max);
                }
            }
            dp[i] = max + 1;
            oMax = Math.max(max+1,oMax);
        }
            return oMax;
    }

    // 279. Perfect Squares

    public int numSquares(int n) 
    {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i = 1;i<=n;i++)
        {
            int min = (int)1e9;
            for(int j = 1;j*j<=i;j++)
            {
                if(i-j*j >= 0) min = Math.min(min , dp[i-j*j]);
            }
            dp[i] = ((min == (int)1e9) ? 0 : min) + 1;
        }
        return dp[n];
    }

    // CATALAN
    public static int catalan(int n)
    {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        if(n<2) return 1;
        for(int i = 2;i<=n;i++)
        {
            for(int j = 0;j<i;j++)
            {
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }

    // NUMBER OF UNIQUE BSTS LEETCODE 96
    public int numTrees(int n) 
    {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        if(n<2) return 1;
        for(int i = 2;i<=n;i++)
        {
            for(int j = 0;j<i;j++)
            {
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }

    // 22. Generate Parentheses
    // Solved Using Catalan Number
    public List<String> generateParenthesis(int n) {
        ArrayList<String>[] dp = new ArrayList[n+1];
        for(int i = 0;i<=n;i++){
            dp[i] = new ArrayList();
        }
        dp[0].add("");
        dp[1].add("()");

        for(int i = 2;i<=n;i++){
            for(int j = 0;j<i;j++){
                ArrayList<String> l = dp[j];
                ArrayList<String> m = dp[i-j-1];
                for(String ele1 : l){
                    for(String ele2 : m){
                        String temp = "(" + ele1 +  ")" + ele2;
                        dp[i].add(temp);
                    }
                }
            }
        }
        return dp[n]; 
    }

    // OPTIMAL BST SUBMITTED ON PEPCODING PORTAL
    // CHECK NOTES 
    // IMPORTANT QUESTION
    private static void optimalbst(int[] keys, int[] freq, int n) {
    
        int[][] dp = new int[n][n];
        int[] pfs = new int[n];
        
        int sum = 0;
        for(int i = 0;i<n;i++)
        {
            sum += freq[i];
            pfs[i] = sum;
        }
        
        for(int gap = 0;gap<n;gap++)
        {
            for(int j = gap , i = 0;j<n;i++,j++)
            {
                if(gap == 0)
                {
                    dp[i][j] = freq[i];
                    continue;
                }
                
                int min = (int)1e9;
                
                for(int cut = i;cut<=j;cut++)
                {
                    int left = (cut-1<i) ? 0 : dp[i][cut-1];
                    int right = (cut+1 > j) ? 0 :  dp[cut+1][j];
                    min = Math.min(min , left+right + pfs[j] - ((i-1 >= 0) ? pfs[i-1] : 0));
                }
                dp[i][j] = min;
            }
        }
        
        System.out.println(dp[0][n-1]);
        
    }

    // PEPCODING ROD CUTTING MAXIMIZE COST
    // USING LEFT-HALVE RIGHT-HALVE STRATEGY 

    public static int solution(int[] p) 
    {
        int[] dp = new int[p.length+1];
        dp[0] = 0;

        for(int i = 1;i<dp.length;i++)
        {
            int max = p[i-1];
            for(int cut = 1;cut<=i/2;cut++)
            {
                max = Math.max(max , dp[cut] + dp[i-cut]);
            }
            dp[i] = max;
        }

        return dp[p.length];
    }

    // SCRAMBLE STRING LEETCODE MEMOIZED SOLUTION GIVING TLE 195/288 TESTCASES PASSED
    public boolean isScramble(String s1, String s2) 
    {
        boolean[][][] dp = new boolean[s1.length()][s1.length()][s1.length()+1];
        isIt(s1,s2,0,0,s1.length(),dp);
        return dp[0][0][s1.length()];
    }
    
    public boolean isIt(String s1 , String s2 , int si1 , int si2 , int len , boolean[][][] dp)
    {
        if(si1+len <= s1.length() && si2+len <= s2.length() && s1.substring(si1,si1+len).equals(s2.substring(si2,si2+len))) return dp[si1][si2][len] = true;
        if(si1+len <= s1.length() && si2+len <= s2.length() && dp[si1][si2][len]) return dp[si1][si2][len];
        boolean temp = false;
        for(int k = 1;k<len && !dp[si1][si2][len] ;k++)
        {
            if((isIt(s1,s2,si1,si2,k,dp) && isIt(s1,s2,si1+k,si2+k,len-k,dp)) || (isIt(s1,s2,si1,si2+len-k,k,dp) && isIt(s1,s2,si1+k,si2,len-k,dp)))
            {
                temp = true;
                break;
            }
        }
        return dp[si1][si2][len] = temp;
    }

    // SCRABLE STRING TABULATION SOLUTION

    public boolean isScramble(String s1, String s2) 
    {
        boolean[][][] dp = new boolean[s1.length()][s1.length()][s1.length()+1];
        
        for(int len = 1;len<=s1.length();len++)
        {
            for(int si1 = 0;si1<s1.length();si1++)
            {
                for(int si2 = 0;si2<s2.length();si2++)
                {
                    if(len == 1)
                    {
                        dp[si1][si2][len] = (s1.charAt(si1) == s2.charAt(si2));
                        continue;
                    }
                    for(int cut = 1;cut<len && !dp[si1][si2][len];cut++)
                    {
                        if(si1+len <= s1.length() && si2+len <= s2.length()){
                            dp[si1][si2][len] = (dp[si1][si2][cut] && dp[si1+cut][si2+cut][len-cut]) || (dp[si1][si2+len-cut][cut] && dp[si1+cut][si2][len-cut]);
                        }
                    }
                }
            }
        }
        return  dp[0][0][s1.length()];
    }

    // MINIMUM ASCII REMOVE TO MAKE STRING SIMILAR LEETCODE
    public int minimumDeleteSum(String s1, String s2) 
    {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        
        dp[s1.length()][s2.length()] = 0;
        
        for(int i = s1.length();i>=0;i--)
        {
            for(int j = s2.length();j>=0;j--)
            {
                if(i == s1.length() && j == s2.length()) continue;
                if(i == s1.length())
                {
                    dp[i][j] = dp[i][j+1] + (int)s2.charAt(j);
                }
                else if(j == s2.length())
                {
                    dp[i][j] = dp[i+1][j] + (int)(s1.charAt(i));
                }
                else if(s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = dp[i+1][j+1];
                }
                else{
                    dp[i][j] = Math.min((int)s1.charAt(i) + dp[i+1][j] , (int)s2.charAt(j) + dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }

    // LONGEST REPETING SUBSEQUENCE HARD
    public int LongestRepeatingSubsequence(String str)
    {
        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1 ; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }


    // 1031. Maximum Sum of Two Non-Overlapping Subarrays

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int lastLen) 
    {
        int ans = -(int)1e9;
        
        int psum[] = new int[nums.length];
        int dp1[] = new int[nums.length];
        int dp2[] = new int[nums.length];
        
        psum[0] = nums[0];
        
        for(int i = 1;i<nums.length;i++){
            psum[i] = psum[i-1] + nums[i];
        }
                
        for(int i = 0;i<nums.length;i++)
        {
            if(i+1<firstLen) continue;
            dp1[i] = Math.max(psum[i]-((i-firstLen>=0)?psum[i-firstLen] : 0) , (i-1>=firstLen-1) ? dp1[i-1] : 0);
        }
        
        psum = new int[nums.length];
        psum[nums.length-1] = nums[nums.length-1];
        
        for(int i = nums.length-2;i>=0;i--){
            psum[i] = psum[i+1] + nums[i];
        }
        
        for(int i = nums.length-1;i>=0;i--)
        {
            if(i+lastLen>nums.length) continue;
            dp2[i] = Math.max(psum[i]-((i+lastLen<nums.length)?psum[i+lastLen] : 0) , (i+1<=nums.length-lastLen) ? dp2[i+1] : 0);
        }
        
        for(int i = firstLen-1;i<=nums.length-lastLen-1;i++){
            ans = Math.max(ans,dp1[i]+dp2[i+1]);
        }
        
        psum = new int[nums.length];
        dp1 = new int[nums.length];
        dp2 = new int[nums.length];
        
        int temp = firstLen;
        firstLen = lastLen;
        lastLen = temp;
        
        psum[0] = nums[0];
        
        for(int i = 1;i<nums.length;i++){
            psum[i] = psum[i-1] + nums[i];
        }
                
        for(int i = 0;i<nums.length;i++)
        {
            if(i+1<firstLen) continue;
            dp1[i] = Math.max(psum[i]-((i-firstLen>=0)?psum[i-firstLen] : 0) , (i-1>=firstLen-1) ? dp1[i-1] : 0);
        }
        
        psum = new int[nums.length];
        psum[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length-2;i>=0;i--){
            psum[i] = psum[i+1] + nums[i];
        }
        for(int i = nums.length-1;i>=0;i--)
        {
            if(i+lastLen>nums.length) continue;
            dp2[i] = Math.max(psum[i]-((i+lastLen<nums.length)?psum[i+lastLen] : 0) , (i+1<=nums.length-lastLen) ? dp2[i+1] : 0);
        }
        for(int i = firstLen-1;i<=nums.length-lastLen-1;i++){
            ans = Math.max(ans,dp1[i]+dp2[i+1]);
        }
        return ans;
    }

    // 689. Maximum Sum of 3 Non-Overlapping Subarrays

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) 
    {
        int firstLen  = k;
        int lastLen = k;
        int ans = -(int)1e9;
        int psum[] = new int[nums.length];
        int psum2[] = new int[nums.length];
        int dp1[] = new int[nums.length];
        int dp2[] = new int[nums.length];
        
        psum[0] = nums[0];
        
        for(int i = 1;i<nums.length;i++){
            psum[i] = psum[i-1] + nums[i];
        }
                
        for(int i = 0;i<nums.length;i++)
        {
            if(i+1<firstLen) continue;
            dp1[i] = Math.max(psum[i]-((i-firstLen>=0)?psum[i-firstLen] : 0) , (i-1>=firstLen-1) ? dp1[i-1] : 0);
        }
        
        psum2[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length-2;i>=0;i--){
            psum2[i] = psum2[i+1] + nums[i];
        }
        
        for(int i = nums.length-1;i>=0;i--)
        {
            if(i+lastLen>nums.length) continue;
            dp2[i] = Math.max(psum2[i]-((i+lastLen<nums.length)?psum2[i+lastLen] : 0) , (i+1<=nums.length-lastLen) ? dp2[i+1] : 0);
        }
        
        int left = 0;
        int right = 0;
        int mid = 0;
        int midIdx = 0;
        for(int i = k;i<nums.length-k;i++)
        {
            int Left = dp1[i-k];
            int Right = dp2[i+1];
            int Mid = psum[i]-psum[i-k];
            if(ans < Left + Right + Mid)
            {
                ans = Left + Right + Mid;
                left = Left;
                right = Right;
                mid = Mid;
                midIdx = i-k+1;
            }
        }
        int leftIdx = 0;
        int rightIdx = 0;
        for(int i = 0;i<midIdx;i++)
        {
            if(dp1[i] == left){
                leftIdx = i;
                break;
            }
        }
        
        int temp  = 0;
        int sumTemp = 0;
        for(int i = midIdx+k;i<nums.length;i++)
        {
            int sum = (i+k<nums.length)?psum2[i] - psum2[i+k] : psum2[i];
            if(dp2[i] == right && sum  > sumTemp){
                sumTemp = sum;
                rightIdx = i;
            }
        }

        return new int[]{leftIdx-k+1 , midIdx , rightIdx};
    }

    // Maximum difference of zeros and ones in binary string 
    int maxSubstring(String S) 
    {
        int gSum = -(int)1e9;
        int cSum = 0;
        
        for(int i = 0;i<S.length();i++)
        {
            int val = (S.charAt(i) == '0') ? 1 : -1;
            
            if(cSum + val <= 0){
                cSum = 0;
                continue;
            }
            cSum += val;
            gSum = Math.max(cSum,gSum);
        }
        // 111111 since no 0 are there so return -1
        if(gSum < 0) return -1;
        return gSum;
    }

    // 17. Letter Combinations of a Phone Number

    public List<String> letterCombinations(String digits) 
    {
        if(digits.length() == 0) return new ArrayList<>();
        
        // instead of arraylist create a 2d array
        ArrayList<String>[] keys = new ArrayList[10];
        keys[2] = new ArrayList<>(Arrays.asList("a","b","c"));
        keys[3] = new ArrayList<>(Arrays.asList("d","e","f"));
        keys[4] = new ArrayList<>(Arrays.asList("g","h","i"));
        keys[5] = new ArrayList<>(Arrays.asList("j","k","l"));
        keys[6] = new ArrayList<>(Arrays.asList("m","n","o"));
        keys[7] = new ArrayList<>(Arrays.asList("p","q","r","s"));
        keys[8] = new ArrayList<>(Arrays.asList("t","u","v"));
        keys[9] = new ArrayList<>(Arrays.asList("w","x","y","z"));
        
        ArrayList<String> ans = new ArrayList<>(Arrays.asList(""));
        
        for(int i = 0;i<digits.length();i++)
        {
            ArrayList<String> temp = new ArrayList<>();
            int digit = Integer.parseInt(digits.charAt(i)+"");
            
            for(String ele1 : ans)
            {
                for(String ele2 : keys[digit])
                {
                    String t = ele1+ele2;
                    temp.add(t);
                }
            }
            ans = temp;
        }
        return ans;
    }

    // numeric keypad

    public static int solution(int n) {
        ArrayList<Integer>[] nextkeys = new ArrayList[10];
        for(int i = 0; i < 10; i++){
            nextkeys[i] = new ArrayList<>();
        }
        
        nextkeys[0].add(0);
        nextkeys[0].add(8);
        nextkeys[1].add(1);
        nextkeys[1].add(2);
        nextkeys[1].add(4);
        nextkeys[2].add(1);
        nextkeys[2].add(2);
        nextkeys[2].add(3);
        nextkeys[2].add(5);
        nextkeys[3].add(2);
        nextkeys[3].add(3);
        nextkeys[3].add(6);
        nextkeys[4].add(1);
        nextkeys[4].add(4);
        nextkeys[4].add(5);
        nextkeys[4].add(7);
        nextkeys[5].add(2);
        nextkeys[5].add(4);
        nextkeys[5].add(5);
        nextkeys[5].add(6);
        nextkeys[5].add(8);
        nextkeys[6].add(3);
        nextkeys[6].add(5);
        nextkeys[6].add(6);
        nextkeys[6].add(9);
        nextkeys[7].add(4);
        nextkeys[7].add(7);
        nextkeys[7].add(8);
        nextkeys[8].add(0);
        nextkeys[8].add(5);
        nextkeys[8].add(7);
        nextkeys[8].add(8);
        nextkeys[8].add(9);
        nextkeys[9].add(6);
        nextkeys[9].add(8);
        nextkeys[9].add(9);
        
        int[][] dp = new int[n + 1][10];
        for(int i = 0 ; i < 10; i++){
            dp[1][i] = 1;
        }
        
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < 10; j++){
                for(int key = 0 ; key < nextkeys[j].size();key++){
                    dp[i][j] += dp[i - 1][nextkeys[j].get(key)]; 
                }
            }
        }
        int ans = 0;
        for(int j = 0 ; j < 10; j++){
            ans += dp[n][j];
        }
        return ans;
    }


    // Distinct Transformations pepcoding

    public static int solution(String s, String t) 
    {
        int[][] dp = new int[s.length()+1][t.length()+1];
        
        for(int i = s.length();i>=0;i--)
        {
            for(int j = t.length();j>=0;j--)
            {
                if(j == t.length()){
                    dp[i][j] = 1;
                    continue;
                }
                if(i == s.length()){
                    dp[i][j] = 0;
                    continue;
                }
                
                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] = dp[i+1][j+1] + dp[i+1][j];
                }
                else dp[i][j] = dp[i+1][j];
            }
        }
        return dp[0][0];
    }

    // maximum sum of m non overlapping subarrays of size k

    public static int solution(int[] arr, int m, int k)
    {
        int[][] dp = new int[arr.length][m+1];
        for(int[] ele : dp) Arrays.fill(ele,-(int)1e9);
        int[] psum = new int[arr.length];
        psum[0] = arr[0];
        for(int i = 1;i<arr.length;i++){
            psum[i] = psum[i-1] + arr[i];
        }
        return maxSum(arr,psum,k-1,k,m,dp);
    }
    
    public static int maxSum(int[] arr , int[] psum , int idx , int k,int m,int[][] dp)
    {
        if(m == 0) return 0;
        if(idx < arr.length && dp[idx][m]!=-(int)1e9) return dp[idx][m];
        int max = -(int)1e9;
        for(int i = idx;i<arr.length-(m-1)*k;i++)
        {
            if(idx+k >= arr.length && m-1 > 0) break;
            int sum = psum[i] - ((i-k >=0) ? psum[i-k] : 0);
            int val = maxSum(arr,psum,i+k,k,m-1,dp);
            max = Math.max(max , val + sum);
        }
        return dp[idx][m] = max;
    }

    // with include exclude

    public static int solution(int[] arr, int m, int k)
    {
        int[][] dp = new int[arr.length][m+1];
        for(int[] ele : dp) Arrays.fill(ele,-(int)1e9);
        int[] psum = new int[arr.length];
        psum[0] = arr[0];
        for(int i = 1;i<arr.length;i++){
            psum[i] = psum[i-1] + arr[i];
        }
        return maxSum(arr,psum,k-1,k,m,dp);
    }
    
    public static int maxSum(int[] arr , int[] psum , int i , int k,int m,int[][] dp)
    {
        if(m == 0) return 0;
        if(dp[i][m]!=-(int)1e9) return dp[i][m];
        int val = psum[i]-((i-k >=0)?psum[i-k]:0);
        int include = (i+k >= arr.length && m-1 > 0) ? -(int)1e9  : maxSum(arr,psum,i+k,k,m-1,dp) + val;
        int exclude = (i+1 >= arr.length && m > 0) ? -(int)1e9  : maxSum(arr,psum,i+1,k,m,dp);
        return dp[i][m] = Math.max(include,exclude);
    }

    // pepcoding solution
    // simple 
    public static int solution(int[] arr, int m, int k){
        int n = arr.length;
        int[] prefixSum = new int[n];
        for(int i = 0 ; i < k; i++){
            prefixSum[0] += arr[i];
        }
        for(int i = 1; i <= n - k; i++){
            prefixSum[i] = prefixSum[i - 1] + arr[i + k - 1] - arr[i - 1];
        }
        return maxSum(prefixSum, arr, m, k, 0);
    }

    public static int maxSum(int[] prefixSum, int[] arr, int m , int k, int vidx){
        if(m == 0){
            return 0;
        }
        if(vidx >= arr.length){
            return 0;
        }
        //include-exclude call
        int include = prefixSum[vidx] + maxSum(prefixSum, arr, m - 1, k, vidx + k);
        int exclude = 0 + maxSum(prefixSum, arr, m, k, vidx + 1);
        return Math.max(include, exclude);
    }

    public static int maxSumTab(int[] arr, int m, int k){
        int[] ssum = new int[arr.length];
        for(int i = arr.length - 1; i >= arr.length - k; i--){
            ssum[arr.length - 1] += arr[i];
        }
        for(int i = arr.length - 2; i >= k - 1; i--){
            ssum[i] = ssum[i + 1] + arr[i - k + 1] - arr[i + 1];
        }

        int[][] dp = new int[arr.length + 1][m + 1];
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                dp[i][j] = Math.max(dp[i - 1][j],i - k >= 0 ? dp[i - k][j - 1] + ssum[i - 1] : 0);
            }
        }
        
        return dp[dp.length - 1][dp[0].length - 1];
    }

    // ARITHEMATIC SLICES LEETCODE 
    public int numberOfArithmeticSlices(int[] nums) 
    {
        int[] dp = new int[nums.length];
        int sum = 0;
        for(int i = 2;i<nums.length;i++)
        {
            if(nums[i]-nums[i-1] == nums[i-1]-nums[i-2])
            {
                dp[i] = dp[i-1] + 1;
                sum+= dp[i];
            }
        }
        return sum;
    }

    // LONGEST COMMON SUBSTRING

    int longestCommonSubstr(String s1, String s2, int n, int m)
    {
        int[][] dp = new int[n+1][m+1];
        int max = 0;
        for(int i = 1;i<=n;i++)
        {
            for(int j = 1;j<=m;j++)
            {
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }

    // 718. Maximum Length of Repeated Subarray
    // longest common substring
    public int findLength(int[] nums1, int[] nums2) 
    {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        int max = 0;
        for(int i = 1;i<=n;i++)
        {
            for(int j = 1;j<=m;j++)
            {
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }


    // arithematic slices 2 IMPORTANT
    public int numberOfArithmeticSlices(int[] nums) 
    {
        int ans = 0;
        HashMap<Integer,Integer>[] map = new HashMap[nums.length];
        for(int i = 0;i<nums.length;i++){
            map[i] = new HashMap<>();
        }
        for(int i = 1;i<nums.length;i++)
        {
            for(int j = 0;j<i;j++)
            {
                long cd = (long)nums[i] - (long)nums[j];
                if(cd<Integer.MIN_VALUE || cd > Integer.MAX_VALUE) continue;
                if(map[j].containsKey((int)cd))
                {
                    int count = map[j].get((int)cd);
                    if(!map[i].containsKey((int)cd)){
                        map[i].put((int)cd,count+1);
                    }
                    else{
                        map[i].put((int)cd,map[i].get((int)cd) +count+1);
                    }
                    if(count+1 >= 2 && map[i].containsKey((int)cd)) ans+=count;
                }
                else{
                    map[i].put((int)cd,map[i].getOrDefault((int)cd,0)+1);
                }
            }
        }
        return ans;
    }

    // temple offrings important question GEEKS FOR GEEKS

    int offerings(int N, int arr[])
    {
        int[] dp1 = new int[arr.length];
        int[] dp2 = new int[arr.length];
        
        dp1[0] = 1;
        
        for(int i = 1;i<N;i++)
        {
            if(arr[i] > arr[i-1])
            {
                dp1[i] = dp1[i-1] + 1;
            }
            else{
                dp1[i] = 1;
            }
        }
        dp2[N-1] = 1;
        for(int i = N-2;i>=0;i--)
        {
            if(arr[i] > arr[i+1])
            {
                dp2[i] = dp2[i+1] + 1;
            }
            else{
                dp2[i] = 1;
            }
        }
        int ans = 0;
        for(int i = 0;i<N;i++)
        {
            ans += Math.max(dp1[i],dp2[i]);
        }
        return ans;
    }

    // frog jump leetcode important 
    public boolean canCross(int[] stones) 
    {
        HashSet<Integer>[] dp = new HashSet[stones.length];
        HashMap<Integer,Integer> check = new HashMap<>();
        for(int i = 0;i<stones.length;i++){
            check.put(stones[i],i);
        }
        for(int i = 0;i<stones.length;i++){
            dp[i] = new HashSet<Integer>();
        }
        dp[0].add(1);
        for(int i = 0;i<stones.length;i++)
        {
            ArrayList<Integer> list = new ArrayList<>(); // we made a array List because it was giving us error that while iterating over hashset you cant alter the structure of hash set
            for(int ele1 : dp[i]) list.add(ele1);
            for(int ele : list)
            {
                if(check.containsKey(stones[i]+ele))
                {
                    if(stones[i]+ele == stones[stones.length-1]) return true;
                    int idx = check.get(stones[i]+ele);
                    dp[idx].add(ele);
                    dp[idx].add(ele+1);
                    if(ele-1 > 0) dp[idx].add(ele-1);
                }
            }
        }
        return false;
    }

    // Leetcode INTERLEVING STRINGS

    public boolean isInterleave(String s1, String s2, String s3) 
    {
        if(s1.length() + s2.length() != s3.length()) return false;
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        //return check(s1,s2,s3,0,0,dp);
        return check(s1,s2,s3,dp);
    }
    
    public boolean check2(String s1, String s2, String s3 , int i , int j , boolean[][]dp)
    {
        if(i == s1.length() && j == s2.length()) dp[i][j] = true;
        if(dp[i][j]) return true;
        
        boolean temp = false;
        
        if(i < s1.length() && s1.charAt(i) == s3.charAt(i+j)) temp = temp || check2(s1,s2,s3,i+1,j,dp);
        if(j < s2.length() && s2.charAt(j) == s3.charAt(i+j)) temp = temp || check2(s1,s2,s3,i,j+1,dp);
        
        return dp[i][j] = temp;
    }
    
    public boolean check(String s1, String s2, String s3 , boolean[][]dp)
    {
        dp[s1.length()][s2.length()] = true;        
        for(int i = s1.length(); i>=0;i--)
        {
            for(int j = s2.length(); j>=0;j--)
            {
                if(i+1 <= s1.length() && s1.charAt(i) == s3.charAt(i+j)) dp[i][j] = dp[i+1][j];
                if(j+1 <= s2.length() && s2.charAt(j) == s3.charAt(i+j)) dp[i][j] = dp[i][j] || dp[i][j+1];       
            }
        }
        return dp[0][0];
    }

    // min difference bw two subsets geeks for geeks
    // important
    public int minDifference(int elements[], int n) {
        int sum = 0;
        for(int ele : elements) sum+= ele;
        
        int dp[] = new int[sum + 1];
        dp[0] = 1;
        for (int i = 0; i < elements.length; i++) {
            for (int j = sum; j >= elements[i]; j--) {
                if (dp[j - elements[i]] == 1)
                    dp[j] = 1;
            }
        }
        int minDiff = (int)1e9;
        for(int i = sum/2 ;i>=0 ;i--){
            if(dp[i] == 1) minDiff = Math.min(minDiff , sum - 2*i);
        }
        return minDiff;
    } 


    // DISTINCT ECHO SUBSTRING LEEETCODE
    // SOLUTION 1
    // using substring function

    public int distinctEchoSubstrings(String text) 
    {
        HashSet<String> set = new HashSet<>();
        for(int len = 1;len <= text.length()/2;len++)
        {
            int count = 0;
            for(int l = 0,r=len;l<text.length()-len;l++,r++)
            {
                if(text.charAt(l) == text.charAt(r)) count++;
                else count = 0;
                
                if(count == len)
                {
                    set.add(text.substring(l-len+1,r+1));
                    count--;
                }
            }
        }
        return set.size();
    }

    // Solution 2 using Rabin Karp Algorithm

    long BASE = 27L, MOD = 1000000007L;
    public int distinctEchoSubstrings(String str) 
    {
        HashSet<Long> set = new HashSet<>();
        int n = str.length();
        long[] hash = new long[n + 1];
        long[] pow = new long[n + 1];
        pow[0] = 1;
        for (int i = 1; i <= n; i++) {
            hash[i] = (hash[i - 1] * BASE + str.charAt(i - 1)) % MOD;
            pow[i] = pow[i - 1] * BASE % MOD;
        }
        for (int i = 0; i < n; i++) {
            for (int len = 2; i + len <= n; len += 2) {
                int mid = i + len / 2;
                long hash1 = getHash(i, mid, hash, pow);
                long hash2 = getHash(mid, i + len, hash, pow);
                if (hash1 == hash2) set.add(hash1);
            }
        }
        return set.size();
    }

    public long getHash(int l, int r, long[] hash, long[] pow) 
    {
        return (hash[r] - hash[l] * pow[r - l] % MOD + MOD) % MOD;
    }



    // 1547. Minimum Cost to Cut a Stick
    // solution 1 using memoisation not passing on leetcode

    public int minCost(int n, int[] cuts) 
    {
        HashSet<Integer> cut = new HashSet<>();
        int[][] dp = new int[n+1][n+1];
        for(int[]ele:dp)
        {
            Arrays.fill(ele,(int)1e9);
        }
        for(int ele : cuts) cut.add(ele);
        return calculate(0,n,cut,dp);
    }
    
    public int calculate(int i , int n , HashSet<Integer> cut , int[][]dp)
    {
        if(dp[i][n]!=(int)1e9) return dp[i][n];
        int min = (int)1e9;
        for(int k=i+1;k<n;k++)
        {
            if(cut.contains(k))
            {
                int left = calculate(i,k,cut,dp);
                int right = calculate(k,n,cut,dp);
                min = Math.min(min,left+right+n-i);
            }
        }
        return dp[i][n] = (min == (int)1e9) ? 0 : min;
    }


    // solution 2 using dp tabulation on the basis of n giving tle

    public int minCost(int n, int[] cuts)
    {
        Arrays.sort(cuts);
        int[][] dp = new int[n+1][n+1];
        for(int[] ele : dp) Arrays.fill(ele,(int)1e9);
        return calculate(n,cuts,dp);
    }
    
    public int calculate(int n, int[]cuts,int[][]dp)
    {
        for(int gap = 0;gap<n+1;gap++)
        {
            for(int i = 0, j = gap;j<n+1;i++,j++)
            {
                if(gap < 2)
                {
                    dp[i][j] = 0;
                    continue;
                }
                for(int ele : cuts)
                {
                    if(ele > i && ele < j)
                    {
                        int left = dp[i][ele];
                        int right = dp[ele][j];
                        dp[i][j] = Math.min(dp[i][j],left+right+j-i);
                    }
                    else if(ele >= j) break;
                }
                if(dp[i][j] == (int)1e9) dp[i][j] = 0;
            }
        }
        return dp[0][n];
    }

    // solution 3 on the basis of cut with small modification to make it equal to mcm
    public int minCost(int n, int[] cuts)
    {
        Arrays.sort(cuts);
        int[] A = new int[cuts.length + 2];
        A[0] = 0;
        A[cuts.length + 1] = n;
        int start = 0;
        while (start < cuts.length) {
            A[start+1] = cuts[start++];
        }
        int len = A.length;
        
        int[][] dp = new int[len][len];
        
        for (int size = 2; size < len; size++) 
        {
            for (int i = 0,j = size;j<len; i++,j++) 
            {
                int min = (int)1e9;
                for (int k = i + 1; k < j; k++) 
                {
                    min = Math.min(min, dp[i][k] + dp[k][j] + A[j] - A[i]);
                }
                dp[i][j] = min;
            }
        }
        return dp[0][len - 1];
    }


    //  Maximum sum such that no two elements are adjacent // Stickler Thief gfg

    public int FindMaxSum(int nums[], int n)
    {
        int inc = 0;
        int exc = 0;
        
        for(int i = 0;i<nums.length;i++)
        {
            int in = Math.max(inc , exc + nums[i]);
            int ex = Math.max(exc , inc);
            inc = in;
            exc = ex;
        }
        return Math.max(inc,exc);
    }

    // Maximum sum such that no two elements are adjacent

    public int FindMaxSum(int nums[], int n)
    {
        int inc = 0;
        int exc = 0;
        
        for(int i = 0;i<nums.length;i++)
        {
            int in = Math.max(inc , exc + nums[i]);
            int ex = Math.max(exc , inc);
            inc = in;
            exc = ex;
        }
        return Math.max(inc,exc);
    }

    // delete and earn leetcode
    // mapped upon the question Maximum sum such that no two elements are adjacent
    public int deleteAndEarn(int[] nums) 
    {
        int inc = 0;
        int exc = 0;
        
        int[] dp = new int[10001];
        
        for(int ele : nums) dp[ele]++;
        
        for(int i = 0;i<10001;i++)
        {
            int in = Math.max(inc , exc + (i*dp[i]));
            int ex = Math.max(exc , inc);
            inc = in;
            exc = ex;
        }
        return Math.max(inc,exc);
    }

    // 1525. Number of Good Ways to Split a String

    public int numSplits(String s) 
    {
        int count = 0;
        int[] pfx = new int[s.length()];
        int[] sfx = new int[s.length()];
        HashSet<Character> set = new HashSet<>();
        for(int i = 0;i<pfx.length;i++)
        {
            char ch = s.charAt(i);
            set.add(ch);
            pfx[i] = set.size();
        }
        set = new HashSet<>();
        for(int i = pfx.length-1;i>=0;i--)
        {
            char ch = s.charAt(i);
            set.add(ch);
            sfx[i] = set.size();
        }
        
        for(int i = 0;i<pfx.length-1;i++)
        {
            if(pfx[i] == sfx[i+1]) count++;
        }
        return count;
    }

    // MIN COST FOR TICKETS

    public int mincostTickets(int[] days, int[] costs) {
        boolean[] isTravelDay = new boolean[366];
        for (int day : days) {
            isTravelDay[day] = true;
        }
        int[] dp = new int[366];
        for (int i = 1; i < 366; i++) {
            if (!isTravelDay[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = Integer.MAX_VALUE;
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 1)] + costs[0]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2]);
        }
        return dp[365];
    }

    // 1911. Maximum Alternating Subsequence SuM

    public long maxAlternatingSum(int[] nums) 
    {
        long evenEnd = 0;
        long oddEnd = nums[0];
        for(int i= 1; i < nums.length; i++)
        {
            long neven = Math.max(evenEnd, oddEnd - (long)(nums[i]));
            long nodd = Math.max(oddEnd, evenEnd + (long)(nums[i]));
            evenEnd = neven;
            oddEnd = nodd;
        }       
        return Math.max(evenEnd, oddEnd);
    }

    // 1690. Stone Game VII

    public int stoneGameVII(int[] stones) 
    {
        int sum = 0;
        for(int n: stones) sum += n;    
        int[][] memo = new int[stones.length][stones.length];
        for(int[] arr: memo) Arrays.fill(arr, Integer.MIN_VALUE);
        return helper(stones, 0, stones.length - 1, sum, true, memo);
    }
    
    private int helper(int[] stones, int i, int j, int sum, boolean turn, int[][] memo){
        if(i > j) return 0;
        if(memo[i][j] != Integer.MIN_VALUE) return memo[i][j];
        int x = 0;
        int y = 0;
        int res = 0;
        if(turn){
            x = helper(stones, i + 1, j, sum - stones[i], !turn, memo) + sum - stones[i];
            y = helper(stones, i, j - 1, sum - stones[j], !turn, memo) + sum - stones[j];
            res = Math.max(x, y);
        } else {
            x = helper(stones, i + 1, j, sum - stones[i], !turn, memo) - sum + stones[i];
            y = helper(stones, i, j - 1, sum - stones[j], !turn, memo) - sum + stones[j];
            res = Math.min(x, y);
        }
        memo[i][j] = res;
        return res;
    }

    // 1363. Largest Multiple of Three
    // Ratne Wala Question
    public String largestMultipleOfThree(int[] digits) 
    {
        int m1[] = new int[] {1, 4, 7, 2, 5, 8}, m2[] = new int[] {2, 5, 8, 1, 4, 7};
        int sum = 0, ds[] = new int[10];
        for (int d : digits) {
            ++ds[d];
            sum += d;
        }
        while (sum % 3 != 0) 
        {
            for (int i : sum % 3 == 1 ? m1 : m2) // sum%3 == 1 then take val from m1
            {                                    // sum%3 == 2 then take val from m2 
              if (ds[i] > 0) 
              {
                ds[i]--;
                sum = sum -  i;
                break;
              }
            }
         }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; --i) sb.append(Character.toString('0' + i).repeat(ds[i]));
        // here repeat() is basically appending the values in string without using a loop inside
        return sb.length() > 0 && sb.charAt(0) == '0' ? "0" : sb.toString();
    }    
}
