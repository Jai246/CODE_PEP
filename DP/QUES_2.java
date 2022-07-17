class leftDpQues
{
	// Divisor Game Leetcode

	public boolean divisorGame(int n) 
    {
        boolean[] dp = new boolean[n+1];
        dp[0] = false;
        dp[1] = false;
        
        for(int i = 2;i<=n;i++)
        {
            for(int j = i-1;j>0;j--)
            {
                if(i%j == 0) dp[i] = dp[i] || !dp[i-j];
            }
        }
        return dp[n];
    }

    // Most Optimized Solution

    public boolean divisorGame(int n)
    {
    	return n%2 == 0;
    }


    // STUDENT RECORD IMPORTANT QUESTION 
    // RECURSIVE

    public int checkRecord(int n) {
        return dfs(0, 0, 0, n);   
    }
    private int dfs(int i, int A, int L, int n) {
        if(i==n) return 1;
        int res = dfs(i+1, A, 0, n);  //P
        if(A==0) res += dfs(i+1, 1, 0, n); //A
        if(L<2) res += dfs(i+1, A, L+1, n); //L
        return res;
    }

    // MEMOISED 

    public int checkRecord(int n) {
        int[][][] mem = new int[n][2][3];
        return dfs(0, 0, 0, n, mem);   
    }
    private int dfs(int i, int A, int L, int n, int[][][] mem) {
        if(i==n) return 1;
        if(mem[i][A][L]!=0) return mem[i][A][L];
        long res = dfs(i+1, A, 0, n, mem);  //P
        if(A==0) res += dfs(i+1, 1, 0, n, mem); //A
        if(L<2) res += dfs(i+1, A, L+1, n, mem); //L
        return mem[i][A][L] = (int)(res%1000000007);
    }

    // TABULATION

    public int checkRecord(int n) 
    {
        int[][][] dp = new int[n+1][2][3];

        dp[n]=new int[][]{{1,1,1},{1,1,1}};

        int mod = 1000000007;

        for(int i=n-1;i>=0;i--)
        {
            for(int A=0; A<2;A++)
            {
                for(int L=0;L<3;L++) 
                {
                    dp[i][A][L] = dp[i+1][A][0];

                    if(A==0) dp[i][0][L] = (dp[i][0][L]+dp[i+1][1][0])%mod;

                    if(L<2) dp[i][A][L] = (dp[i][A][L]+dp[i+1][A][L+1])%mod;
                }
            }
        }           
        return dp[0][0][0];   
    }


    // Decode Ways 2 Awesome Solution

    private static final int M = 1000000007;
    
    private long ways(char a) 
    {
        return (a == '*') ? 9 : (a != '0') ? 1 : 0; 
    }
    
    private long ways(char a, char b) 
    {
        if (a == '*' && b == '*') return 15; 
        else if (a == '*') return (b > '6') ? 1 : 2;
        else if (b == '*') return (a == '1') ? 9 : (a == '2') ? 6 : 0; 
        else 
        {
            int val = Integer.valueOf("" + a + b);
            return (10 <= val && val <= 26) ? 1 : 0;
        }
    } 
    
    public int numDecodings(String s) 
    {
        long x = 1; // Previous to Previous Count
        long y = ways(s.charAt(0)); // Previous Count
        
        for (int i = 1; i < s.length(); i++) 
        {
            long oneCharWays = ways(s.charAt(i)) * y;
            long twoCharWays = ways(s.charAt(i - 1), s.charAt(i)) * x;
            x = y;
            y = (oneCharWays % M + twoCharWays % M) % M;
        }
        return (int)y;
    }


    // 982. Triples with Bitwise AND Equal To Zero

    public int countTriplets(int[] A) 
    {
        int[] count = new int[1 << 16];
        for(int a:A) for(int b:A) count[a & b]++;
        int res = 0;
        for(int a: A) for(int i = 0; i < count.length; i++) if((a & i) == 0) res += count[i];
        return res;
    }

    // In naive approach 1, we're iterating over all the values from 0 to 216-1 for each A[i]. 
    // But we can make this loop faster by skipping over numbers that we can be sure wouldn't give 0 when taken AND with A[i]. 
    // Let's say, a (from A) = xxxx1000 and i (from loop) = 1000 so that a & i = 00001000 which is != 0. 
    // At this point, we can be sure that subsequent values after i i.e. 1001, 1010, 1011, 1100, ..., 1111 
    // when taken AND with a won't give 0 either because all of these have a 1 at bit position 3 so we can directly skip ahead to i = 10000. 

    public int countTriplets(int[] A) 
    {
        int[] count = new int[1 << 16];
        for(int a: A) for(int b: A) count[a & b]++;
        int res = 0;
        for(int a: A) for(int i = 0; i < count.length; i++) {
            if((a & i) == 0) res += count[i];
            else i += (a & i) - 1; // -1 because one ++ will be done by the loop as well
            // Remember that if we add the same number it will get multiplied by 2
        }
        return res;
    }

    // MAXIMUM PROFIT IN JOB SCHEDULING

    class pair implements Comparable<pair>
    {
        int start;
        int end;
        int profit;
        pair(){
        }
        pair(int start , int end , int profit){
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
        
        @Override
        public int compareTo(pair a){
            return this.end-a.end;
        }
    }
    public int jobScheduling(int[] s, int[] e, int[] profit)
    {
        pair[] arr = new pair[profit.length];
        for(int i = 0;i<profit.length;i++)
        {
            arr[i] = new pair(s[i],e[i],profit[i]);
        }
        Arrays.sort(arr);
        
        int[] dpEnd = new int[e.length+1];
        int[] dpPro = new int[e.length+1];
        
        dpEnd[0] = 0;
        dpPro[0] = 0;
        
        for(int i = 1;i<dpEnd.length;i++)
        {
            int max = arr[i-1].profit;
            int startOfI = arr[i-1].start;
            for(int j = i-1;j>=0;j--)
            {
                if(dpEnd[j] <= startOfI)
                {
                    max = Math.max(max , arr[i-1].profit + dpPro[j]);
                }
            }
            if(max > dpPro[i-1])
            {
                dpEnd[i] = arr[i-1].end;
                dpPro[i] = max;
            }
            else
            {
                dpEnd[i] = dpEnd[i-1];
                dpPro[i] = dpPro[i-1];
            }
        }
        return dpPro[e.length];
    }

    // BinarySearch Approach

    class pair implements Comparable<pair>
    {
        int start;
        int end;
        int profit;
        pair(){
            
        }
        pair(int start , int end , int profit){
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
        
        @Override
        public int compareTo(pair a){
            return this.end-a.end;
        }
    }
    public int floor(int[] end , int e, int val)
    {
        int i = 0;
        int j = e;
        int ans = -1;
        while(i <= j)
        {
            int mid = (i+j)/2;
            if(end[mid] <= val)
            {
                ans = mid;
                i = mid+1;
            }
            else j = mid-1;
        }
        return ans;
    }
        
    public int jobScheduling(int[] s, int[] e, int[] profit)
    {
        pair[] arr = new pair[profit.length];
        for(int i = 0;i<profit.length;i++)
        {
            arr[i] = new pair(s[i],e[i],profit[i]);
        }
        Arrays.sort(arr);
        
        int[] dpEnd = new int[e.length+1];
        int[] dpPro = new int[e.length+1];
        
        dpEnd[0] = 0;
        dpPro[0] = 0;
        
        for(int i = 0;i<arr.length;i++)
        {
            int start = arr[i].start;
            int end = arr[i].end;
            int p = arr[i].profit;
            int idx = floor(dpEnd,i,start);
            int pro = dpPro[idx] + p;
            
            if(pro > dpPro[i])
            {
                dpEnd[i+1] = end;
                dpPro[i+1] = pro;
            }
            else
            {
                dpEnd[i+1] = dpEnd[i];
                dpPro[i+1] = dpPro[i];
            }
        }
        return dpPro[profit.length];
    }

    // Min Taps Leetcode
    // Greedy Approach

    public int minTaps(int n ,int[] ranges) 
    {
        ArrayList<int[]> r = new ArrayList<>();
        
        for(int i = 0;i<ranges.length;i++)
        {
            if(ranges[i] == 0) continue;
            int[] temp = new int[2];    
            temp[0] = Math.max(0,i-ranges[i]);
            temp[1] = Math.min(n,i+ranges[i]);
            r.add(temp);
        }
        int T = n;
        int start = 0;
        int count = 0;
        int max = 0;
        while(start < T) 
        {
            for(int[] clip : r)
            {
                if(start >= clip[0]) max = Math.max(max, clip[1]);
            }
            if(start == max) return -1; 
            start = max;
            count++;
        }
        return count;
    }


    // 45. Jump Game II

    // Dp Solution

    public int jump(int[] nums) 
    {
        int len = ranges.length;
        int[] dp = new int[len]; 
        Arrays.fill(dp, len + 1); 
        dp[0] = 0;

        for (int i = 0; i < len; i++) 
        {
            int start = Math.max(i - ranges[i], 0);
            int end = Math.min(i + ranges[i], len - 1);
                        
            for (int j = start; j <= end; j++) 
            {
                dp[j] = Math.min(dp[j], dp[start] + 1);       
            }
        }
        return dp[len - 1] == len + 1 ? -1 : dp[len - 1];
    }

    // Greedy Approach

    public int jump(int[] ranges)
    {
        ArrayList<int[]> r = new ArrayList<>();
        for(int i = 0;i<ranges.length;i++)
        {
            if(ranges[i] == 0) continue;
            int[] temp = new int[2];    
            temp[0] = i;
            temp[1] = Math.min(ranges.length-1,i+ranges[i]);
            r.add(temp);
        }
        int T = ranges.length-1;
        int start = 0;
        int count = 0;
        int max = 0;
        while(start < T) 
        {
            for(int[] clip : r)
            {
                if(start >= clip[0]) max = Math.max(max, clip[1]);
            }
            if(start == max) return -1; 
            start = max;
            count++;
        }
        return count;
    }

    // 1024. Video Stitching Dp Solotion

    public int videoStitching(int[][] clips, int T) 
    {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, T + 1);
        dp[0] = 0;

        for (int i = 0; i <= T; i++) 
        {
            for (int[] clip : clips) 
            {
                int start = clip[0];
                int end = clip[1];
                if (i >= start && i <= end) 
                {
                    dp[i] = Math.min(dp[i], dp[start] + 1);
                }
            }
        }
        return dp[T] == T + 1 ? -1 : dp[T];
    }

    // Greedy Approach

    public int videoStitching(int[][] clips, int T) 
    {
        int count = 0;
        int start = 0;
        int max = 0;
        while(start < T) 
        {
            for(int[] clip : clips)
            {
                if(start >= clip[0]) max = Math.max(max, clip[1]);
            }
            if(start == max) return -1; 
            start = max;
            count++;
        }
        return count;
    }


    // 198. House Robber

    public int rob(int[] nums) 
    {
        int prev = 0;
        int prevPrev = 0;
        int val = 0;
        for(int ele : nums)
        {
            val = prevPrev + ele;
            prevPrev = Math.max(prevPrev,prev);
            prev = val;
        }
        return Math.max(prevPrev,Math.max(prev,val));
    }

    // 338. Counting Bits

    public int[] countBits(int num) {

        if(num == 0) return new int[1];
        
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i < num + 1; i++)
        {
            if(i % 2 == 0) dp[i] = dp[i/2];
            else dp[i] = dp[i/2] + 1;
        }
        return dp;
    }

    // 343. Integer Break
    // Important
    // DoglaPan

    public int integerBreak(int n) 
    {
        int[]dp = new int[n+1];
        if(n == 2 || n == 3) return n-1;
        if(n == 4) return 4;
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2;i<=n;i++){
            dp[i] = i;
            for(int j = i-1;j>=i/2;j--){
                dp[i] = Math.max(dp[i] , dp[i-j]*dp[j]);
            }
        }
        return dp[n];
    }

    // 357 Count Numbers With Unique Digits

    // This is a digit combination problem. Can be solved in at most 10 loops.

    // When n == 0, return 1. I got this answer from the test case.

    // When n == 1, _ can put 10 digit in the only position. [0, ... , 10]. Answer is 10.

    // When n == 2, _ _ first digit has 9 choices [1, ..., 9], second one has 9 choices excluding the already chosen one. 

    //So totally 9 * 9 = 81. answer should be 10 + 81 = 91

    // When n == 3, _ _ _ total choice is 9 * 9 * 8 = 684. answer is 10 + 81 + 648 = 739

    // When n == 4, _ _ _ _ total choice is 9 * 9 * 8 * 7.

    // ...

    // When n == 10, _ _ _ _ _ _ _ _ _ _ total choice is 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1

    // When n == 11, _ _ _ _ _ _ _ _ _ _ _ total choice is 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 * 0 = 0


    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 10, base = 9;
        for (int i = 2; i <= n; i++) 
        {
            base = base * (9 - i + 2);
            ans += base;
        }
        return ans;
    }


    // 376. Wiggle Subsequence
    // Important

    public int wiggleMaxLength(int[] nums) 
    {
        int up = 1;
        int down = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i] > nums[i-1]) up = down+1;
            else if(nums[i] < nums[i-1]) down = up+1;
        }
        return Math.max(up,down);
    }


    // 646. Maximum Length of Pair Chain

    public int findLongestChain(int[][] pairs) 
    {
        Arrays.sort(pairs,(a,b)->{return a[1] - b[1];});
        int ans = 0;
        int min = -(int)1e9;
        for(int[]ele : pairs)
        {
            if(ele[0] > min){
                ans++;
                min = ele[1];
            }
        }
        return ans;
    }

    // 790. Domino and Tromino Tiling

    public int numTilings(int n) 
    {
        if(n <= 2) return n;
        int mod = (int)1e9 + 7;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        
        for(int i = 4;i<=n;i++)
        {
            dp[i] = ((dp[i-1]*2)%mod + dp[i-3]%mod)%mod;
        }
        return dp[n];
    }

    // 935. Knight Dialer

    public int knightDialer(int n) {

        int mod = 1000000007;
        
        long[] a1 = new long[10];
        Arrays.fill(a1, 1);
        while (n>1) 
        {
            long[] a2 = new long[10];
            a2[0] = (a1[4]+a1[6])%mod;
            a2[1] = (a1[6]+a1[8])%mod;
            a2[2] = (a1[7]+a1[9])%mod;
            a2[3] = (a1[4]+a1[8])%mod;
            a2[4] = (a1[0]+a1[3]+a1[9])%mod;        
            a2[6] = (a1[0]+a1[1]+a1[7])%mod;                
            a2[7] = (a1[2]+a1[6])%mod;
            a2[8] = (a1[1]+a1[3])%mod;        
            a2[9] = (a1[2]+a1[4])%mod;
            a1 = a2;
            n--;
        }
        long a = 0;
        for (long a22:a1) {
            a = (a + a22)%mod;
        }
        return (int) a;    
    }

    // 1043. Partition Array for Maximum Sum
    // Important Dp Question
    // k = 2 [10,9,3,2] 30 max
    // k = 4 [1,4,1,5,7,3,6,1,9,9,3] 83 max
    // Ratlo/SamajhLo is code koo Aache See
    public int maxSumAfterPartitioning(int[] A, int K) 
    {
        int N = A.length, dp[] = new int[N + 1];
        
        for (int i = 1; i <= N; ++i) 
        {
            int curMax = 0, best = 0;
            
            for (int k = 1; k <= K && i - k >= 0; ++k) 
            {
                curMax = Math.max(curMax, A[i - k]);

                best = Math.max(best, dp[i - k] + curMax * k);
            }
            dp[i] = best;
        }
        return dp[N];
    }


    // 1105. Filling Bookcase Shelves
    // Important Question
    public int minHeightShelves(int[][] books, int shelfWidth) 
    {
        int n = books.length;
        int[] dp = new int[n+1];
        
        for(int i = 1;i<dp.length;i++)
        {
            dp[i] = dp[i-1] + books[i-1][1];
            int height = books[i-1][1];
            int width = books[i-1][0];
            for(int j=i-1;j>0;j--)
            {
                height = Math.max(height,books[j-1][1]);
                width += books[j-1][0];
                if(width <= shelfWidth) dp[i] = Math.min(dp[i],height + dp[j-1]);   
            }   
        }
        return dp[n];
    }

    // 1218. Longest Arithmetic Subsequence of Given Difference

    public int longestSubsequence(int[] arr, int d) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 1;
        for(int ele : arr)
        {
            if(map.containsKey(ele-d))
            {
                map.put(ele,map.get(ele-d)+1);
                max = Math.max(max,map.get(ele));
                if(ele != ele-d) map.remove(ele-d);
                //[7,7,7,7,7,7,7,7] d = 0;
            }
            else if(!map.containsKey(ele)) map.put(ele,1);
        }
        return max;
    }


    // 1262. Greatest Sum Divisible by Three

    // TLE using target sum approach

    public int maxSumDivThree(int[] nums) 
    {
        int sum = 0;
        int max = 0;
        for(int ele : nums) sum+=ele;
        
        boolean[]dp = new boolean[sum+1];
        dp[0] = true;
        
        for(int ele : nums)
        {
            for(int i = dp.length-1;i>=ele;i--)
            {
                dp[i] = dp[i] || dp[i-ele];
            }    
        }
        
        int i = 3;
        
        while(i < dp.length){
            if(dp[i]) max = i;
            i+=3;
        }
        return max;
    }

    // VVVVVVVVVVV Nice approach to solve these kind of problems

    public int maxSumDivThree(int[] nums) 
    {
        int[] dp = new int[3];
        
        for(int num:nums)
        {
            int[] next = dp.clone();
            for(int sum:dp)
            {
                int nsum = sum+num;
                int index = nsum%3;
                next[index] = Math.max(nsum, next[index]);
            }
            dp = next;
        }
        return dp[0];
    }


    // 213. House Robber II

    public int rob(int[] nums) 
    {
        if(nums.length == 1) return nums[0];
        int max1 = 0;
        int max2 = 0;
        int prev = 0;
        int prevPrev = 0;
        int val = 0;
        for(int i = 0;i<nums.length-1;i++)
        {
            int ele = nums[i];
            val = prevPrev + ele;
            prevPrev = Math.max(prevPrev,prev);
            prev = val;
        }
        max1 = Math.max(prevPrev,Math.max(prev,val));
        
        prev = 0;
        prevPrev = 0;
        val = 0;
        for(int i = 1;i<nums.length;i++)
        {
            int ele = nums[i];
            val = prevPrev + ele;
            prevPrev = Math.max(prevPrev,prev);
            prev = val;
        }
        max2 = Math.max(prevPrev,Math.max(prev,val));
        
        return Math.max(max1,max2);
    }


    // 474. Ones and Zeroes

    public int[] conv(String str){
        int m = 0;
        int n = 0;
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch == '1') n++;
            else m++;
        }
        return new int[]{m,n};
    }
    
    
    public int findMaxForm(String[] strs, int m, int n)
    {
        int[][] dp = new int[m+1][n+1];
        
        for(String ele : strs)
        {
            int[] mn = conv(ele);
            int[][] temp = new int[m+1][n+1];
            
            for(int i=0;i<=m;i++)
            {
                for(int j=0;j<=n;j++)
                {
                    temp[i][j] = dp[i][j];
                    if(i-mn[0] >=0 && j-mn[1] >=0){
                        temp[i][j] = Math.max(temp[i][j],1+dp[i-mn[0]][j-mn[1]]);
                    }
                }
            }
            dp = temp;
        }
        return dp[m][n];
    }


    // 638. Shopping Offers
    // UNBOUNDED KNAPSACK VERSION
    public int shoppingOffers(List<Integer> prices, List<List<Integer>> special, List<Integer> need)
    {
        int[]price = new int[6];
        int[]needs = new int[6];
        int[][] arrs = new int[special.size()][7];
        
        int x = 0;
        
        for(List<Integer>ele : special)
        {
            for(int k = 0;k<ele.size()-1;k++)
            {
                arrs[x][k] = ele.get(k);
            }
            arrs[x++][6] = ele.get(ele.size()-1);
        }
        
        for(int i = 0;i<prices.size();i++)
        {
            price[i] = prices.get(i);
            needs[i] = need.get(i);
        }
        
        
        int[][][][][][]dp = new int[11][11][11][11][11][11];
        
        for(int i = 0;i<=needs[0];i++)
        {
            for(int j = 0;j<=needs[1];j++)
            {
                for(int k = 0;k<=needs[2];k++)
                {
                    for(int l = 0;l<=needs[3];l++)
                    {
                        for(int m = 0;m<=needs[4];m++)
                        {
                            for(int n = 0;n<=needs[5];n++)
                            {
                                dp[i][j][k][l][m][n] = (i*price[0]) +(j*price[1]) +(k*price[2]) +(l*price[3]) +(m*price[4]) +(n*price[5]); 
                                // if we do this as well in the next loop then we might end up getting answer as 0 becauese we have to take min
                                // of dp[i][j][k][l][m][n] and (i*price[0]) +(j*price[1]) +(k*price[2]) +(l*price[3]) +(m*price[4]) +(n*price[5])
                                // But the min of them will always be 0 as int [] is initialize with 0 , the another idea is to first initialize dp with
                                // (int)1e9 but then it is better to initialize it with (i*price[0]) +(j*price[1]) +(k*price[2]) +(l*price[3]) +(m*price[4]) +(n*price[5])

                             }
                        }
                    }
                }
            }
        }
        
        
        for(int off=0;off<special.size();off++)
        {
            for(int i = 0;i<=needs[0];i++)
            {
                for(int j = 0;j<=needs[1];j++)
                {
                    for(int k = 0;k<=needs[2];k++)
                    {
                        for(int l = 0;l<=needs[3];l++)
                        {
                            for(int m = 0;m<=needs[4];m++)
                            {
                                for(int n = 0;n<=needs[5];n++)
                                {                                    
                                    if(i - arrs[off][0] < 0) continue;
                                    if(j - arrs[off][1] < 0) continue;
                                    if(k - arrs[off][2] < 0) continue;
                                    if(l - arrs[off][3] < 0) continue;
                                    if(m - arrs[off][4] < 0) continue;
                                    if(n - arrs[off][5] < 0) continue;
                                    
                                    int val = dp[i-arrs[off][0]][j-arrs[off][1]][k-arrs[off][2]][l-arrs[off][3]][m-arrs[off][4]][n-arrs[off][5]] + arrs[off][6];
                                    dp[i][j][k][l][m][n] = Math.min(dp[i][j][k][l][m][n],val);
                                }
                            }
                        }
                    }
                }
            }
        }
        return dp[needs[0]][needs[1]][needs[2]][needs[3]][needs[4]][needs[5]];
    }


    // 1626. Best Team With No Conflicts

    public class details
    {
        int score;
        int age;
        
        details(int score , int age)
        {
            this.score = score;
            this.age = age;
        }
    }
    
    public class comp implements Comparator<details>
    {
        @Override
        public int compare(details a, details b) 
        {
            if(a.age != b.age) return a.age-b.age;
            else return a.score-b.score;
        }
    }
    
    public int bestTeamScore(int[] scores, int[] ages)
    {
        int max = -(int)1e9;
        details[] det = new details[scores.length];
        for(int i = 0;i<scores.length;i++) det[i] = new details(scores[i],ages[i]);
        
        Arrays.sort(det,new comp());
        
        int[]dp = new int[scores.length];
        for(int i=0;i<scores.length;i++)
        {
            dp[i] = det[i].score;
            for(int j = i-1;j>=0;j--)
            {

                if(det[j].score<=det[i].score || det[j].age==det[i].age)
                {
                    dp[i] = Math.max(dp[i],det[i].score + dp[j]);
                }
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }


    // 576. Out of Boundary Paths

    // Memoization Solution
    // Time complexity : O(mnN)O(mnN). 
    // We need to fill the memomemo array once with dimensions m \times n \times Nm×n×N.
    // Here, mm, nn refer to the number of rows and columns of the given grid respectively. 
    // NN refers to the total number of allowed moves.

    public int mod = (int)1e9+7;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) 
    {
        int[][][]dp = new int[m][n][maxMove+1];
        for(int[][] ele : dp){
            for(int[]e : ele) Arrays.fill(e,-1);
        }
        return paths(m,n,maxMove,startRow,startColumn,0,dp);
    }
    
    public int paths(int m , int n , int N , int i , int j , int k, int[][][]dp)
    {
        if(k > N) return 0;
        if(i < 0 || j < 0 || i > m-1 || j > n-1) return 1;
        
        if(dp[i][j][k] != -1) return dp[i][j][k];

        int ways = 0;
        
        ways = (ways + paths(m,n,N,i,j-1,k+1,dp)%mod)%mod;
        ways = (ways + paths(m,n,N,i-1,j,k+1,dp)%mod)%mod;
        ways = (ways + paths(m,n,N,i+1,j,k+1,dp)%mod)%mod;
        ways = (ways + paths(m,n,N,i,j+1,k+1,dp)%mod)%mod;
        
        return dp[i][j][k] = ways;
    }


    // 799. Champagne Tower
    // This Solution Not Passing on leetcode

    public double champagneTower(int poured, int query_row, int query_glass)
    {
        if(poured == 0) return 0.0;
        double[][]dp = new double[101][101];
        
        dp[1][1] = 1.0;
        
        for(int p=2;p<=poured;p++)
        {
            dp[1][1] += 1.0;
            boolean isExcess = false;
            for(int i = 2;i<=100;i++)
            {
                for(int j = 1;j<=i;j++)
                {
                    double wineP1 = dp[i-1][j];
                    
                    if(wineP1 > 1.0)
                    {
                        double toBePoured = (wineP1-1.0)/2.0;
                        dp[i][j] += toBePoured;
                    }
                    
                    double wineP2 = dp[i-1][j-1];
                    
                    if(wineP2 > 1.0)
                    {
                        double toBePoured = (wineP2-1.0)/2.0;
                        dp[i][j] += toBePoured;
                    }
                }
                
                for(int j = 1;j<=i;j++) 
                {
                    if(dp[i-1][j] > 1.0) dp[i-1][j] = 1.0;
                    if(dp[i][j] > 1.0) isExcess = true;
                }
                if(!isExcess) break;
            }
        }
        return dp[query_row+1][query_glass+1];
    }

    // Passed On Leetcode 
    public double champagneTower(int poured, int query_row, int query_glass)
    {
        if(poured == 0) return 0.0;
        double[][]dp = new double[101][101];
        
        dp[1][1] = poured;
        
        boolean isExcess = false;
        for(int i = 2;i<=100;i++)
        {
            for(int j = 1;j<=i;j++)
            {
                double wineP1 = dp[i-1][j];

                if(wineP1 > 1.0)
                {
                    double toBePoured = (wineP1-1.0)/2.0;
                    dp[i][j] += toBePoured;
                }

                double wineP2 = dp[i-1][j-1];

                if(wineP2 > 1.0)
                {
                    double toBePoured = (wineP2-1.0)/2.0;
                    dp[i][j] += toBePoured;
                }
            }

            for(int j = 1;j<=i;j++) 
            {
                if(dp[i-1][j] > 1.0) dp[i-1][j] = 1.0;
                if(dp[i][j] > 1.0) isExcess = true;
            }
            if(!isExcess) break;
        }
        return dp[query_row+1][query_glass+1];
    }


    // 813. Largest Sum of Averages
    // Very Important Problem 
    // This Problem Actually Taught us How to partitian an array

    public double largestSumOfAverages(int[] A, int K) 
    {
        int N = A.length;
        double[] P = new double[N+1];
        for (int i = 0; i < N; ++i) P[i+1] = P[i] + A[i];

        double[] dp = new double[N];
        
        for (int i = 0; i < N; ++i) dp[i] = (P[N] - P[i]) / (N - i);

        for (int k = 0; k < K-1; ++k)
        {
            for (int i = 0; i < N; ++i)
            {
                for (int j = i+1; j < N; ++j)
                {
                    dp[i] = Math.max(dp[i], (P[j]-P[i]) / (j-i) + dp[j]);
                }
            }
        }
        return dp[0];
    }

    // 931. Minimum Falling Path Sum

    public int minFallingPathSum(int[][] matrix) 
    {
        int[]dp = new int[matrix[0].length];
        int minAns = (int)1e9;
        for(int i = 0;i<matrix.length;i++)
        {
            int[]temp = new int[matrix[0].length];
            
            for(int j = 0;j<matrix[0].length;j++)
            {
                int v1 = (j-1 >=0 ) ? dp[j-1] : (int)1e9;
                int v2 = dp[j];
                int v3 = (j+1 <matrix[0].length ) ? dp[j+1] : (int)1e9;
                temp[j] = matrix[i][j] + Math.min(v1,Math.min(v2,v3));
                if(i == matrix.length-1) minAns = Math.min(minAns,temp[j]);
            }
            dp = temp;
        }
        return minAns;
    }



    // 1027. Longest Arithmetic Subsequence
    // Very Important Question With Very Important Algorithm
    // Do Remember This Algorithm
    public int longestArithSeqLength(int[] A)
    {
        if (A.length <= 1) return A.length;
       
        int longest = 0;
        
        // Declare a dp array that is an array of hashmaps.
        // The map for each index maintains an element of the form-
        // (difference, length of max chain ending at that index with that difference).        
        HashMap<Integer, Integer>[] dp = new HashMap[A.length];
        
        for (int i = 0; i < A.length; ++i) {
            // Initialize the map.
            dp[i] = new HashMap<Integer, Integer>();
        }
        
        for (int i = 1; i < A.length; ++i) {
            int x = A[i];
            // Iterate over values to the left of i.
            for (int j = 0; j < i; ++j) {
                int y = A[j];
                int d = x - y;
                
                // We at least have a minimum chain length of 2 now,
                // given that (A[j], A[i]) with the difference d, 
                // by default forms a chain of length 2.
                int len = 2;  
                
                if (dp[j].containsKey(d)) {
                    // At index j, if we had already seen a difference d,
                    // then potentially, we can add A[i] to the same chain
                    // and extend it by length 1.
                    len = dp[j].get(d) + 1;
                }
                
                // Obtain the maximum chain length already seen so far at index i 
                // for the given differene d;
                int curr = dp[i].getOrDefault(d, 0);
                
                // Update the max chain length for difference d at index i.
                dp[i].put(d, Math.max(curr, len));
                
                // Update the global max.
                longest = Math.max(longest, dp[i].get(d));
            }
        }
        
        return longest;
    }

    // 1155. Number of Dice Rolls With Target Sum

    // Memoised 

    public int numRollsToTarget(int n, int k, int target) 
    {
        int[][] dp = new int[n+2][target+1];
        for(int[]ele : dp) Arrays.fill(ele,-1);
        return calculate(1,n,k,0,target,dp);
    }
    
    public int calculate(int curr , int n , int k , int sum , int target,int[][]dp)
    {
        if(sum > target) return 0;
        if(curr <= n && dp[curr][sum]!=-1) return dp[curr][sum];
        
        if(curr == n+1 && sum == target) return dp[curr][sum] = 1;
        else if(curr > n) return 0;
        
        int count = 0;
        for(int i = 1;i<=k;i++){
            count = (count + (calculate(curr+1,n,k,sum+i,target,dp))%((int)1e9+7))%((int)1e9+7);
        }
        return dp[curr][sum] = count;
    }



    // Tabulation

    public int numRollsToTarget(int n, int k, int target) 
    {
        int[]dp = new int[target+1];

        for(int i = 1;i<=n;i++)
        {
            int[]temp = new int[target+1];
            for(int j = 1;j<=target;j++)
            {
                if(i == 1 && j <=k)
                {
                     temp[j] = 1;
                    continue;
                }
                for(int l = 1;l<=k;l++)
                {
                    if(j - l >= 0) temp[j] = (temp[j] + (dp[j-l]))%((int)1e9+7);
                }
            }
            dp = temp;
        }
        return dp[target];
    }



    /// 1621. Number of Sets of K Non-Overlapping Line Segments

    // memoization
    public int mod = (int)1e9+7;
    public int numberOfSets(int n, int k) 
    {
        int[][][]memo = new int[n+1][k+1][2];
        for(int[][] ele : memo){
            for(int[] e : ele) Arrays.fill(e,-1);
        }
        
        return count(n,k,0,0,0,memo);
    }
    
    public int count(int n , int k , int x , int y , int bool , int[][][]memo)
    {
        if(x >= n) return 0;
        if(memo[x][y][bool]!=-1) return memo[x][y][bool];
        if(y == k) return memo[x][y][bool] = 1;
        int cnt = 0;
        if(bool == 0)
        {
            cnt = (cnt + count(n,k,x+1,y,1,memo)%mod)%mod;
            cnt = (cnt + count(n,k,x+1,y,0,memo)%mod)%mod;
        }
        
        if(bool == 1)
        {
            cnt = (cnt + count(n,k,x,y+1,0,memo)%mod)%mod;
            cnt = (cnt + count(n,k,x+1,y,1,memo)%mod)%mod;
        }
        return memo[x][y][bool] = cnt%mod;
    }


    // 53. Maximum Subarray

    public int maxSubArray(int[] nums) 
    {
        int gMax = -(int)1e9;
        int lSum = 0;
        for(int ele : nums)
        {
            lSum = lSum + ele;
            gMax = Math.max(lSum,gMax);
            if(lSum < 0) lSum = 0;
        }
        return gMax;
    }

    // 898. Bitwise ORs of Subarrays

    // This Solution Not Passing
    // TLE
    public int subarrayBitwiseORs(int[] arr) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0;i<arr.length;i++){
            map.put(i,arr[i]);
            set.add(arr[i]);
        }
        
        for(int i = 1;i<arr.length;i++)
        {
            HashMap<Integer,Integer> temp = new HashMap<>();
            for(int j = i;j<arr.length;j++)
            {
                int ele = arr[j];
                temp.put(j,map.get(j-1)|arr[j]);
                set.add(temp.get(j));
            }
            map = temp;
        }
        return set.size();
    }


    // Important Solution

    // [1]
    // [1,2][2]
    // [1,2,3][2,3][3]
    // [1,2,3,4][2,3,4][3,4][4]

    public int subarrayBitwiseORs(int[] arr) 
    {
        HashSet<Integer>set = new HashSet<>();
        HashSet<Integer>ans = new HashSet<>();
        set.add(0);
        for(int ele : arr)
        {
            HashSet<Integer> temp = new HashSet<>();
            temp.add(ele);
            ans.add(ele);
            for(int e : set)
            {
                int val = ele|e;
                ans.add(val);
                temp.add(val);
            }
            set = temp;
        }
        return ans.size();
    }


    // 978. Longest Turbulent Subarray
    // Handeling for the continous duplicates is important
    // [9,9,9,9,8,7,6,7,8,9,10]
    // [9,10,9,8,7,64]
    public int maxTurbulenceSize(int[] A) 
    {
        int inc = 1, dec = 1, result = 1;
        for (int i = 1; i < A.length; i++) 
        {
            if (A[i] < A[i - 1]) 
            {
                dec = inc + 1;
                inc = 1;
            } 
            else if (A[i] > A[i - 1]) 
            {
                inc = dec + 1;
                dec = 1;
            } 
            else 
            {
                inc = 1;
                dec = 1;
            }
            result = Math.max(result, Math.max(dec, inc));
        }
        return result;
    }


    // 368. Largest Divisible Subset

    // Sort And Apply LIS

    public List<Integer> largestDivisibleSubset(int[] nums) 
    {
        int n = nums.length;
        int[] count = new int[n];
        int[] pre = new int[n]; // Parent Array
        Arrays.sort(nums);
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + count[j] > count[i]) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }


    // 873. Length of Longest Fibonacci Subsequence

    public int lenLongestFibSubseq(int[] arr)
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<arr.length;i++) map.put(arr[i],i);
        int[]max = new int[]{2};
        
        for(int i = 0;i<arr.length;i++){
            for(int j = i+1;j<arr.length-1;j++){
                int sum = arr[i] + arr[j];
                if(map.containsKey(sum)) if(map.get(sum)>j) findLen(map,arr[j],max,sum,3);
            }
        }
        return (max[0] > 2) ? max[0] : 0;
    }
    
    public void findLen(HashMap<Integer,Integer> map,int prev, int[]max , int sum , int len)
    {
        if(map.containsKey(sum + prev)) findLen(map,sum,max,sum+prev,len+1);
        else max[0] = Math.max(max[0],len);
    }

    // 960. Delete Columns to Make Sorted III
    // Simple LIS Solution

    public int minDeletionSize(String[] A) 
    {
        int m = A.length, n = A[0].length(), res = n - 1, k;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int j = 0; j < n; ++j) 
        {
            for (int i = 0; i < j; ++i) 
            {
                for (k = 0; k < m; ++k) 
                {
                    if (A[k].charAt(i) > A[k].charAt(j)) break;
                }
                if (k == m && dp[i] + 1 > dp[j]) dp[j] = dp[i] + 1;
            }
            res = Math.min(res, n - dp[j]);
        }
        return res;
    }
    
    // 1671. Minimum Number of Removals to Make Mountain Array
    // Simple LIS Solution

    public int minimumMountainRemovals(int[] nums) 
    {
        int n=nums.length;
        int []left=new int [n]; 
        int []right=new int [n];
        Arrays.fill(left,1);
        Arrays.fill(right,1);

        for(int i=1;i<n;i++)
        {
            for(int j=0;j<i;j++)
            {
                if(nums[j]<nums[i]&&left[i]<left[j]+1) left[i]=left[j]+1;
            }
        }
        
        for(int i=n-2;i>=0;i--)
        {
            for(int j=n-1;j>i;j--)
            {
                if(nums[j]<nums[i]&&right[i]<right[j]+1) right[i]=right[j]+1;
            }
        }
        
        int max=0;
        for(int i=1;i<n-1;i++) 
        {
            if(right[i]>1&&left[i]>1) max=Math.max(max,left[i]+right[i]-1);
        }
        
        return n-max;
    }


    // 174. Dungeon Game
    // Important Problem
    public int calculateMinimumHP(int[][] dungeon) 
    {
        int n=dungeon.length;
        int m=dungeon[0].length;
        
        int [][] dp=new int[n][m];
        dp[n-1][m-1]=dungeon[n-1][m-1]>0?0:dungeon[n-1][m-1];
        
        for(int i=m-2;i>=0;i--) dp[n-1][i]=dungeon[n-1][i]+dp[n-1][i+1]>0?0:dungeon[n-1][i]+dp[n-1][i+1];
        for(int i=n-2;i>=0;i--) dp[i][m-1]=dungeon[i][m-1]+dp[i+1][m-1]>0?0:dungeon[i][m-1]+dp[i+1][m-1];
        
        for(int i=n-2;i>=0;i--)
        {
            for(int j=m-2;j>=0;j--)
            {
              int temp=dungeon[i][j];
              if(Math.abs(dp[i+1][j])<Math.abs(dp[i][j+1])) temp+=dp[i+1][j];
              else temp+=dp[i][j+1];
              dp[i][j]=temp>0?0:temp;
            }
        }
        
        for(int[] ele : dp) System.out.println(Arrays.toString(ele));
        
       return Math.abs(dp[0][0])+1;
    }

    // 62. Unique Paths

    public int uniquePaths(int m, int n) 
    {
        int[][] dp = new int[m][n];
        for(int i = 0;i<m;i++) dp[i][n-1] = 1;
        for(int j = 0;j<n;j++) dp[m-1][j] = 1;
        for(int i = m-2;i>=0;i--) for(int j = n-2;j>=0;j--) dp[i][j] = dp[i+1][j] + dp[i][j+1];
        return dp[0][0];
    }


    // 63. Unique Paths II

    public int uniquePathsWithObstacles(int[][] arr) 
    {
        int m = arr.length;
        int n = arr[0].length;
        int[][] dp = new int[arr.length][arr[0].length];
        if(arr[m-1][n-1] == 1 || arr[0][0] == 1) return 0;
        dp[m-1][n-1] = 1;
        for(int i = m-2;i>=0;i--) {
            if(arr[i][n-1] == 1) continue;
            dp[i][n-1] = dp[i+1][n-1];
        }
        for(int j = n-2;j>=0;j--) {
            if(arr[m-1][j] == 1) continue;
            dp[m-1][j] = dp[m-1][j+1];
        }
        for(int i = m-2;i>=0;i--) 
        {
            for(int j = n-2;j>=0;j--) 
            {
                if(arr[i][j] == 1) continue;
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }
        
        // for(int[] ele : dp) System.out.println(Arrays.toString(ele));
        return dp[0][0];
    }


    // 64. Minimum Path Sum

    public int minPathSum(int[][] grid) 
    {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = grid[m-1][n-1];
        for(int i = m-2;i>=0;i--) dp[i][n-1] = dp[i+1][n-1] + grid[i][n-1];
        for(int j = n-2;j>=0;j--) dp[m-1][j] = dp[m-1][j+1] + grid[m-1][j];
        for(int i = m-2;i>=0;i--) {
            for(int j = n-2;j>=0;j--) {
                dp[i][j] = Math.min(dp[i+1][j] , dp[i][j+1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }


    // 1594. Maximum Non Negative Product in a Matrix
    // Store MinMax pair at every location
    public class Pair{
        long min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
        Pair(){
            
        }
        Pair(long min,long max){
            this.min=min;
            this.max=max;
        }
    }
    public int maxProductPath(int[][] grid) 
    {
        Pair[][] dp=new Pair[grid.length][grid[0].length];
        for(int r=grid.length-1;r>=0;r--)
        {
            for(int c=grid[0].length-1;c>=0;c--)
            {
                if(r==grid.length-1 && c==grid[0].length-1)
                {
                    dp[r][c]=new Pair(grid[r][c],grid[r][c]);
                }
                else
                {
                    Pair hor=(c==grid[0].length-1)?new Pair():dp[r][c+1];
                    Pair ver=(r==grid.length-1)?new Pair():dp[r+1][c];
                    long min,max;
                    if(grid[r][c]>=0)
                    {
                         max=Math.max(hor.max,ver.max);
                         min=Math.min(hor.min,ver.min);
                    }
                    else
                    {
                         min=Math.max(hor.max,ver.max);
                         max=Math.min(hor.min,ver.min);
                    }
                    dp[r][c]=new Pair(min*grid[r][c],max*grid[r][c]);
                }
            }
        }
        int mod=(int)1e9 +7;
        return dp[0][0].max<0?-1:(int)(dp[0][0].max%mod);
    }

    // 303. Range Sum Query - Immutable

    public int[]dp;
    public NumArray(int[] nums) 
    {
        dp = new int[nums.length+1];
        for(int i = 1;i<dp.length;i++)
        {
            dp[i] = dp[i-1] + nums[i-1];
        }
    }
    
    public int sumRange(int left, int right) 
    {
        return dp[right+1] - dp[left];
    }


    // 862. Shortest Subarray with Sum at Least K

    // [84,-37,32,40,95]
    // 167
    // Important problem and intuition
    public int shortestSubarray(int[] A, int K)
    {
        int N = A.length;
        long[] P = new long[N+1];
        for (int i = 0; i < N; ++i) P[i+1] = P[i] + (long) A[i];
        int ans = N+1;
        
        Deque<Integer> monoq = new LinkedList();

        for (int y = 0; y < P.length; ++y) 
        {
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()]) monoq.removeLast();
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K) ans = Math.min(ans, y - monoq.removeFirst());
            monoq.addLast(y);
        }
        return ans < N+1 ? ans : -1;
    }


    // Maximum Sum Submatrix Kadanes 2d

    public int maxSubArray(int[] nums) 
    {
        int gMax = -(int)1e9;
        int lSum = 0;
        for(int ele : nums)
        {
            lSum = lSum + ele;
            gMax = Math.max(lSum,gMax);
            if(lSum < 0) lSum = 0;
        }
        return gMax;
    }
    
    public int maxSumSubmatrix(int[][] matrix) 
    {
        int max = -(int)1e9;
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[] dp = new int[n];
        
        for(int i = 0;i<m;i++)
        {
            for(int y = i;y<m;y++)
            {
                for(int x = 0;x<n;x++)
                {
                    dp[x]+=matrix[x][y];
                    int res = maxSubArray(dp);
                    max = Math.max(max,res);
                }
            }
        }
        return (max!=-(int)1e9) ? max : -1;
    }


    // 363. Max Sum of Rectangle No Larger Than K
    // O(n^4) Solution

    public int maxSumSubmatrix(int[][] matrix, int k) 
    {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] areas = new int[rows][cols];
        
        for (int r = 0; r < rows; r++) 
        {
            for (int c = 0; c < cols; c++) 
            {
                int area = matrix[r][c];
                if (r-1 >= 0) area += areas[r-1][c];
                if (c-1 >= 0) area += areas[r][c-1];
                if (r-1 >= 0 && c-1 >= 0) area -= areas[r-1][c-1];
                areas[r][c] = area;
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < rows; r1++) 
        {
            for (int c1 = 0; c1 < cols; c1++) 
            {
                for (int r2 = r1; r2 < rows; r2++) 
                {
                    for (int c2 = c1; c2 < cols; c2++) 
                    {
                        int area = areas[r2][c2];
                        if (r1-1 >= 0) area -= areas[r1-1][c2];
                        if (c1-1 >= 0) area -= areas[r2][c1-1];
                        if (r1-1 >= 0 && c1 -1 >= 0) area += areas[r1-1][c1-1];
                        if (area <= k) max = Math.max(max, area);
                    }
                }
            }
        }
        return max;
    }

    // Optimized Version

    // Firstly, let solve this sub problem Max Sum of Subarray No Larger Than K, which is "Given an array of N integers, find the maximum sum of subarray which is no larger than K".
    // Iterating index i from left to right.
    // Calculate prefixSum so far, let name it right
    // Try to find the left prefixSum so that right - left <= k --->>> left >= right - k.
    // We can use TreeSet (implemented as BST), and use ceiling(x) to find the least key greater than or equal to the given x. So left = bst.ceiling(right-k).
    // If we found a valid left, then we update the answer by ans = max(ans, right - left).
    // Then we try all possible pairs of (r1, r2) of rows in the matrix, where 0 <= r1 <= r2 < m. Make an array of n integer, where arr[c] = sum(matrix[r1][c]...matrix[r2][c]), then solve that sub problem.

    public int maxSumSubmatrix(int[][] matrix, int k) { // Like Kadens 2d
        int m = matrix.length, n = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < m; ++r1) {
            int[] arr = new int[n]; // arr[i] is sum(matrix[r1][c]...matrix[r2][c])
            for (int r2 = r1; r2 < m; ++r2) 
            {
                for (int c = 0; c < n; ++c)
                {
                    arr[c] += matrix[r2][c];
                } 
                ans = Math.max(ans, maxSumSubArray(arr, n, k));
            }
        }
        return ans;
    }
    
    int maxSumSubArray(int[] arr, int n, int k) { // O(N * logN)
        TreeSet<Integer> bst = new TreeSet<>();
        bst.add(0);
        int ans = Integer.MIN_VALUE;
        for (int i = 0, right = 0; i < n; ++i) 
        {
            right += arr[i];
            Integer left = bst.ceiling(right - k); // right - left <= k -> left >= right - k
            if (left != null) 
            {
                ans = Math.max(ans, right - left);
            }
            bst.add(right);
        }
        return ans;
    }


    // 1074. Number of Submatrices That Sum to Target

    public static int subarraySum(int[] nums, int k) 
    {
        int sum = 0;
        int count = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int ele : nums)
        {
            sum += ele;
            if(map.containsKey(sum - k)) count+=map.get(sum-k);
            if(!map.containsKey(sum)) map.put(sum,1);
            else if(map.containsKey(sum)) map.put(sum , map.get(sum)+1);
        }
        return count;
    }
    
    public static int numSubmatrixSumTarget(int M[][] , int tar) 
    {
        int []arr = new int[M[0].length];
        int count = 0;
        for(int k = 0;k<M.length;k++)
        {
            for(int i = k;i<M.length;i++)
            {
                for(int j = 0;j<M[0].length;j++)
                {
                    arr[j] += M[i][j];
                }
                count += subarraySum(arr,tar);
            }
            Arrays.fill(arr,0);
        }
        return count;
    }


    // 764. Largest Plus Sign

    public class coord{
        int u;
        int d;
        int l;
        int r;
        coord(){
            
        }
        coord(int u , int d , int l , int r){
            this.u = u;
            this.d = d;
            this.l = l;
            this.r = r;
        }
    }
    public int orderOfLargestPlusSign(int n, int[][] mines) 
    {
        HashSet<Integer> set = new HashSet<>();
        int max = 0;
        boolean isOrderOne = false;
        for(int[] ele:mines){
            int a = ele[0];
            int b = ele[1];
            set.add(a*n + b);
        }
        coord[][]dp = new coord[n][n];
        
        for(int i = 0;i<=n-1;i++)
        {
            for(int j = 0;j<=n-1;j++)
            {
                dp[i][j] = new coord();
                if(i == 0 || j == 0 || i == n-1 || j == n-1) continue;
                
                if(!set.contains(i*n + j))
                {
                    int u = 0;
                    int l = 0;
                    if(!set.contains((i-1)*n+ j))
                    {
                        coord up = dp[i-1][j];
                        u = up.u+1;
                    }
                    if(!set.contains(i*n+ (j-1)))
                    {
                        coord left = dp[i][j-1];
                        l = left.l + 1;
                    }
                    
                    dp[i][j] = new coord(u,0,l,0);
                }
            }
        }
        
        for(int i = n-1;i>=0;i--)
        {
            for(int j = n-1;j>=0;j--)
            {
                if(!set.contains(i*n +j)) isOrderOne = true;
                
                if(i == 0 || j == 0 || i == n-1 || j == n-1) continue;
                
                if(!set.contains(i*n +j))
                {
                    int d = 0;
                    int r = 0;
                    if(!set.contains((i+1)*n+j))
                    {
                        coord down = dp[i+1][j];
                        d = down.d+1;
                    }
                    if(!set.contains(i*n+ (j+1)))
                    {
                        coord right = dp[i][j+1];
                        r = right.r + 1;
                    }
                    
                    dp[i][j].d = d;
                    dp[i][j].r = r;
                    
                    coord t = dp[i][j];
                    max = Math.max(max , Math.min(t.u,Math.min(t.d,Math.min(t.l,t.r))));
                }
            }
        }
        if(max == 0 && !isOrderOne) return 0;
        if(max == 0 && isOrderOne) return 1;
        return max+1;
    }

    // 1277. Count Square Submatrices with All Ones
    // Not the most optimal one

    public int countSquares(int[][] matrix) 
    {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = matrix;
        int count = 0;
        
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(matrix[i][j] == 1) count += 1;
            }
        }
        
        for(int k=1;k<m;k++)
        {
            int[][] temp = new int[m][n];
            for(int i=k;i<m;i++)
            {
                for(int j=k;j<n;j++)
                {
                    if(dp[i-1][j-1] == 1 && dp[i-1][j] == 1 && dp[i][j-1] == 1 && matrix[i][j] == 1)
                    {
                        temp[i][j] = 1;
                        count+=1;
                    }
                }
            }
            dp = temp;
        }
        return count;
    }


    // Most Optimal Solution

    public int countSquares(int[][] matrix) 
    {
        int mat[][] = new int[matrix.length + 1][matrix[0].length + 1];
        int sum = 0;
        
        for(int i = 1; i <= matrix.length; i++)
            for(int j = 1; j <= matrix[0].length; j++)
                if(matrix[i - 1][j - 1] != 0)
                    sum += (mat[i][j] = Math.min(Math.min(mat[i - 1][j], mat[i][j - 1]), mat[i - 1][j - 1]) + 1);
           
        return sum;
    }

    /// 1139. Largest 1-Bordered Square

    public class pair
    {
        int d;
        int r;
        pair(){
        }
        pair(int d , int r){
            this.d = d;
            this.r = r;
        }
    }
    
    public int largest1BorderedSquare(int[][] matrix) 
    {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        boolean isOne = false;
        pair[][] dp = new pair[m][n];
        
        for(int i = m-1;i>=0;i--)
        {
            for(int j = n-1;j>=0;j--)
            {
                dp[i][j] = new pair();
                if(matrix[i][j] == 0) continue;
                if(j+1 < n) dp[i][j].r = dp[i][j+1].r+1;
                if(i+1 < m) dp[i][j].d = dp[i+1][j].d+1;
            }
        }
        
        for(int i = 0;i<m;i++)
        {
            for(int j = 0;j<n;j++)
            {
                if(matrix[i][j] == 0) continue;
                isOne = true;
                pair t = dp[i][j];
                for(int k = Math.min(t.d,t.r);k>0;k--)
                {
                    pair t1 = dp[i][j+k];
                    pair t2 = dp[i+k][j];                    
                    if(matrix[i][j+k]!=0 && matrix[i+k][j]!=0 && matrix[i+k][j+k] !=0)
                    {
                        if(t1.d>=k && t2.r>=k) max = Math.max(max,k);
                    }
                }
            }
        }
        
        if(max == 0 && isOne) return 1;
        if(max == 0 && !isOne) return 0;
        
        return (max+1)*(max+1);
    }


    // 1314. Matrix Block Sum

    public int[][]ans;
    
    public void NumMatrix(int[][] matrix) 
    {
        ans = new int[matrix.length+1][matrix[0].length+1];
        for(int i = 1;i<=matrix.length;i++){
            for(int j = 1;j<=matrix[0].length;j++){
                ans[i][j] = ans[i-1][j] + ans[i][j-1] + matrix[i-1][j-1] - ans[i-1][j-1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) 
    {
        return ans[row2 + 1][col2 + 1] - ans[row1][col2 + 1] - ans[row2 + 1][col1] + ans[row1][col1];
    }
    
    public int[][] matrixBlockSum(int[][] mat, int k)
    {
        NumMatrix(mat);
        int[][] dp = new int[mat.length][mat[0].length];
        
        for(int i=0;i<mat.length;i++)
        {
            for(int j = 0;j<mat[0].length;j++)
            {
                int r1 = Math.max(0,i-k);
                int r2 = Math.min(mat.length-1,i+k);
                
                int c1 = Math.max(0,j-k);
                int c2 = Math.min(mat[0].length-1,j+k);
                
                dp[i][j] = sumRegion(r1,c1,r2,c2);
            }
        }
        return dp;
    }


    // 1423. Maximum Points You Can Obtain from Cards

    public int maxScore(int[] arr, int k) 
    {
        int i = 0;
        int j = arr.length-1;
        int sum = 0;
        int max = 0;
        while(i<k)
        {
            sum+=arr[i];
            i++;
        }
        i--;
        max = sum;
        while(j >= arr.length-k)
        {
            sum  = sum - arr[i] + arr[j];
            max = Math.max(max,sum);
            i--;
            j--;
        }
        return max;
    }

    // 1504. Count Submatrices With All Ones
    // Important Approach

    public int numSubmat(int[][] mat) 
    {
        int[][] dp = new int[mat.length][mat[0].length];
        
        for(int i = 0;i<mat.length;i++)
        {
            int count = 0;
            for(int j = mat[0].length-1;j>=0;j--)
            {
                if(mat[i][j] == 1)
                {
                    count++;
                    dp[i][j] = count;
                }
                else
                {
                    count = 0;
                }
            }
        }
                
        int ans = 0;
        
        for(int i = 0;i<mat.length;i++)
        {
            for(int j = 0;j<mat[0].length;j++)
            {
                int min = (int)1e9;
                for(int k = i;k<mat.length;k++)
                {
                    ans += Math.min(min , dp[k][j]);
                    min = Math.min(min , dp[k][j]);
                }
            }
        }
        return ans;
    }


    // 1664. Ways to Make a Fair Array

    // Important And Simple Solution Copied From Discusion
    // Much Much Better than the previous one I wrote

    public int waysToMakeFair(int[] nums) 
    {
        int res = 0;
        int n =nums.length;
        int righteven = 0, rightodd = 0;
        
        for(int i=0; i<n; ++i) 
        {
            if(i%2 == 0) righteven += nums[i];
            else rightodd += nums[i];
        }
        
        int lefteven = 0, leftodd = 0;
        for(int i=0; i<n; ++i) 
        {
            
            if(i%2 == 0) righteven -= nums[i];
            else rightodd -= nums[i];
            
            if(lefteven + rightodd == leftodd + righteven) res++;
            
            if(i%2== 0) lefteven += nums[i];
            else leftodd += nums[i];
        }
        
        return res;
    }


    // 523. Continuous Subarray Sum
    // Very Important Ratne Wala Solution
    // Remainder wali kahani
    // https://www.youtube.com/watch?v=OKcrLfR-8mE
    public boolean checkSubarraySum(int[] nums, int k) 
    {
        int psum=0;
        HashMap<Integer,Integer> hm=new HashMap<>();
        hm.put(0,-1);
        for(int i=0;i<nums.length;i++)
        {
            psum+=nums[i];
            int rem=psum%k;
            if(hm.containsKey(rem))
            {
                if(i-hm.get(rem)>=2)
                return true;
            }
            else
            {
                hm.put(rem,i);
            }
        }
        return false;
    }

    //1477. Find Two Non-overlapping Sub-arrays Each With Target Sum

    public int minSumOfLengths(int[] arr, int target) 
    {
        int[] dp1 = new int[arr.length];
        int[] dp2 = new int[arr.length];
        Arrays.fill(dp1,(int)1e9);
        Arrays.fill(dp2,(int)1e9);
        
        int sum1 = 0;
        int i1 = 0;
        int j1 = 0;
        int min1 = (int)1e9;
        while(j1<arr.length)
        {
            sum1+=arr[j1];
            if(sum1 == target) 
            {
                dp1[j1] = Math.min(min1,j1-i1+1);
                min1 = Math.min(min1,j1-i1+1);
            }
            else dp1[j1] = min1;
            
            while(sum1 >= target && i1 < j1)
            {
                sum1 -= arr[i1++];
                if(sum1 == target)
                {
                    dp1[j1] = Math.min(min1,j1-i1+1);
                    min1 = Math.min(min1,j1-i1+1);
                }
            }
            j1++;
        }
        
        
        int sum2 = 0;
        int i2 = arr.length-1;
        int j2 = arr.length-1;
        int min2 = (int)1e9;
        while(j2>=0)
        {
            sum2+=arr[j2];
            if(sum2 == target) 
            {
                dp2[j2] = Math.min(min2,i2-j2+1);
                min2 = Math.min(min2,i2-j2+1);
            }
            else dp2[j2] = min2;
            
            while(sum2 >= target && i2 > j2)
            {
                sum2 -= arr[i2--];
                if(sum2 == target)
                {
                    dp2[j2] = Math.min(min2,i2-j2+1);
                    min2 = Math.min(min2,i2-j2+1);
                }
            }
            j2--;
        }
        
        int ans = (int)1e9;
        
        for(int i = 0;i<dp1.length;i++)
        {
            int val1 = dp1[i];
            int val2 = (i+1 < dp1.length) ? dp2[i+1] : (int)1e9;
            
            if(val1 == (int)1e9 || val2 == (int)1e9) continue;
            ans = Math.min(ans,val1+val2);
        }
        
        
        if(ans == (int)1e9) return -1;
        return ans;
    }



    // 1546. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target

    public int maxNonOverlapping(int[] nums, int target) 
    {
        int[] dp = new int[nums.length];
        HashMap<Integer,Integer> map = new HashMap<>();
        
        map.put(0,-1);
        
        int sum = 0;
        
        for(int i =0;i<nums.length;i++)
        {
            sum += nums[i];
            if(map.containsKey(sum-target))
            {
                if(map.get(sum-target) == -1) dp[i] = Math.max((i-1 >= 0) ? dp[i-1] : -(int)1e9 , 1);
                else dp[i] = Math.max((i-1 >= 0) ? dp[i-1] : -(int)1e9 ,dp[map.get(sum-target)] + 1);
            }
            else 
            {
                if(i-1 >=0) dp[i] = dp[i-1];
            }
            
            map.put(sum,i);
        }
        // System.out.println(Arrays.toString(dp));
        return dp[nums.length-1];
    }

    // 2266. Count Number of Texts

    public int countTexts(String str) 
    {
        LinkedList<Long> val = new LinkedList<>();
        val.addLast(1l);
        val.addLast(1l);
        val.addLast(2l);
        val.addLast(4l);        
        
        long max = 1;
        int mod = (int)1e9 + 7;
        
        
        int prev = -1;
        
        int i = 0;
        int j = 0;
        
        int count = 0;
        
        while(j < str.length())
        {
            char ch = str.charAt(j);
            int v = Integer.parseInt(ch+"");
            
            if(v == prev)
            {
                count++;
                
                if(count > 3)
                {
                    int size = val.size();
                    long nVal = 0;
                    if(prev == 7 || prev == 9) nVal = val.get(size-1) + val.get(size-2) + val.get(size-3) + val.get(size-4);
                    else nVal = val.get(size-1) + val.get(size-2) + val.get(size-3);
                    val.add((nVal%mod));
                }
                j++;
            }
            else if(v!=prev)
            {
                max = (max * val.get(count))%mod;
                i = j;
                count = 0;
                prev = v;
                val = new LinkedList<>();
                val.addLast(1l);
                val.addLast(1l);
                val.addLast(2l);
                val.addLast(4l);
            }
        }
        max = (max * val.get(count))%mod;
        return (int)max;
    }

    // 1048. Longest String Chain

    public boolean compare(String o , String t)
    {
        int i = 0;
        int j = 0;
        
        boolean temp = false;
        
        while(j < o.length())
        {
            char c1 = t.charAt(i);
            char c2 = o.charAt(j);
            
            if(c1 == c2)
            {
                j++;
                i++;
            }
            else if(c1!=c2 && temp == false)
            {
                i++;
                temp = true;
            }
            else if(c1!=c2 && temp) return false;
        }
        return true;
    }
    
    public int longestStrChain(String[] words) 
    {
        int ans = 1;
        Arrays.sort(words,(a,b)->{
           if(a.length()!=b.length()) return a.length()-b.length(); 
           else return a.compareTo(b);
        });
        
        int[] dp = new int[words.length];
        Arrays.fill(dp,1);
        
        
        for(int i = 1;i<words.length;i++)
        {
            for(int j = 0;j<i;j++)
            {
                if(words[j].length() != words[i].length()-1) continue;
                boolean res = compare(words[j],words[i]);
                if(res)
                {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    ans = Math.max(ans,dp[i]);
                }
            }
        }
        
        return ans;
    }
    
}