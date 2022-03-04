public class contests
{
	// BIWEEKLY CONTEST 68

	// 2114. Maximum Number of Words Found in Sentences

	public int mostWordsFound(String[] sentences) 
    {
        int ans = -(int)1e9;
        for(String ele : sentences){
            String[] temp = ele.split(" "); 
            ans = Math.max(temp.length,ans);
        }
        return ans;
    }

    // 2115. Find All Possible Recipes from Given Supplies
    // Important Question Using Kahns Algo
    // Calculate Indegree After Creating the Graph

    HashMap<String,ArrayList<String>> map;
    HashSet<String> supp;
    HashSet<String> recep;
    HashSet<String> made;
    List<String> ans;
    
    public boolean find(String ing)
    {
        boolean res = true;
        if(map.containsKey(ing))
        {
            for(String ele : map.get(ing))
            {
                res = find(ele);
            }    
        }
        else if(supp.contains(ing) || recep.contains(ing)) return true;
        
        return res;
    }
    
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) 
    {
        supp = new HashSet<>();
        for(String ele : suplies) set1.add(ele);
        
        recep = new HashSet<>();
        for(String ele : recepies) set2.add(ele);
        
        made = new HashSet<>();
        
        ans = new ArrayList<>();
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        for(int i = 0;i<recepies.length;i++)
        {
            map.put(recepies[i],new ArrayList<>());
            for(List<String> ele : ingredients)
            {
                for(String t : ele) map.get(recepies[i]).add(t);
            }
        }
    }


    // 678. Valid Parenthesis String
    // Important Question

    public boolean checkValidString(String s) 
    {
        Stack<Integer> s1 = new Stack<>(); // for opening brackets
        Stack<Integer> s2 = new Stack<>(); // for stars
        
        
        for(int i = 0;i<s.length();i++)
        {
            char c = s.charAt(i);
            
            if(c == '(') s1.push(i);
            else if(c == ')')
            {
                if(s1.size() > 0) s1.pop();
                else if(s2.size() > 0) s2.pop();
                else return false;
            }
            else s2.push(i);
        }
        
        while(s1.size()!=0) // checking the left over opening bracketsss
        {
            if(s2.size() == 0) return false;
            int pos = s1.pop();
            if(s2.size() > 0 && s2.peek() > pos) s2.pop();
            else return false;
        }
        return true;
    }

    // 2116. Check if a Parentheses String Can Be Valid
    // Important
    public boolean canBeValid(String s, String locked) 
    {
        int n = s.length();
        if (n % 2 == 1) 
        {
          return false;
        }

        int remain = 0;
        for (int i = 0; i < n; i++) 
        {
          if (locked.charAt(i) == '1' && s.charAt(i) == ')') {
            if (remain == 0) return false;
            else remain--;
          } 
          else remain++;
        }

        remain = 0;
        for (int i = n - 1; i >= 0; i--) 
        {
          if (locked.charAt(i) == '1' && s.charAt(i) == '(')
          {
            if(remain == 0) return false;
            else remain--; 
          } 
          else remain++;
        }
        return true;
    }


    // Weekly Contest 274

    // 2124. Check if All A's Appears Before All B's

    public boolean checkString(String s) 
    {
        boolean bAppear = false;
        boolean aAppear = false;
        
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch == 'a'){
                aAppear = true;
                if(bAppear) return false;
            }
            else if(ch == 'b') bAppear = true;
        }
        
        return true;
    }


    // 2125. Number of Laser Beams in a Bank

    public int countDevices(String row){
        int count = 0;
        for(int i = 0;i<row.length();i++){
            char ch = row.charAt(i);
            if(ch == '1') count++;
        }
        return count;
    }
    
    public int numberOfBeams(String[] bank) {
        int ans = 0;
        int prev = 0;
        int curr = 0;
        prev = countDevices(bank[0]);
        for(int i = 1;i<bank.length;i++){
            curr = countDevices(bank[i]);
            if(curr == 0) continue;
            ans += prev * curr;
            prev = curr;
            curr = 0;
        }
        return ans;       
    }

    // 2126. Destroying Asteroids
    // Simple Sorting Mei Confuse Ho gaya tha Meine Binary Search Laga Diya Tha lower Bound Nikalne Ke Liye

    public boolean asteroidsDestroyed(int mass, int[] asteroids) 
    {
        Arrays.sort(asteroids);
        long masss = mass;
        for (int asteroid : asteroids) 
        {
            if ((long) asteroid > masss) return false;
            masss += asteroid;
        }
        return true;
    }



    // BI WEEKLY 69 CONTEST

    // 2129. Capitalize the Title

    public String capitalizeTitle(String title) 
    {
        String[] arr = title.split(" ");
        String ans = "";
        
        for(String ele : arr)
        {
            if(ele.length() == 1 || ele.length() == 2) ans+= " " + ele.toLowerCase();
            else
            {
                String temp = (" " + ele.charAt(0)).toUpperCase();
                for(int i = 1;i<ele.length();i++)
                {
                    temp+= (ele.charAt(i) + "").toLowerCase();
                }
                ans +=temp;
            }
        }
        return ans.trim();   
    }

    // 2130. Maximum Twin Sum of a Linked List

    public ListNode reverse(ListNode head)
    {
        ListNode prev = null;
        ListNode next = null;
        while(head!=null)
        {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    
    public int pairSum(ListNode head) 
    {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while(fast!=null && fast.next!=null)
        {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        ListNode revList = reverse(slow);
        
        
        int sum = -(int)1e9;
        
        while(head!=null && revList!=null)
        {
            sum = Math.max(sum , head.val + revList.val);
            head = head.next;
            revList = revList.next;
        }
        
        return sum;
    }


    // 2131. Longest Palindrome by Concatenating Two Letter Words
    // IMPORTANT
    public int longestPalindrome(String[] words) 
    {
        HashMap<String,Integer> map = new HashMap<>();
        HashMap<String,Integer> ansMap = new HashMap<>();
        boolean sameChar = false;
        int ans = 0;
        for(int i = 0;i<words.length;i++)
        {
            StringBuilder sb=new StringBuilder(words[i]);  
            sb.reverse();  
            String temp = sb.toString();
            
            if(map.containsKey(temp))
            {
                map.put(temp,map.get(temp)-1);
                if(map.get(temp) == 0) map.remove(temp);
                ans+=4;
            }
            else map.put(words[i],map.getOrDefault(words[i],0)+1);
        }
        
        
        for(String ele : words)
        {
            if(map.containsKey(ele) && ele.charAt(0) == ele.charAt(1) && map.get(ele)==1){
                sameChar = true;
                break;
            }
        }
        if(sameChar) return ans + 2;
        return ans;
    }


    // 2132. Stamping The Grid Important Question

    public int[][] NumMatrix(int[][] matrix) 
    {
        int[][]ans = new int[matrix.length+1][matrix[0].length+1];
        for(int i = 1;i<=matrix.length;i++)
        {
            for(int j = 1;j<=matrix[0].length;j++)
            {
                ans[i][j] = ans[i-1][j] + ans[i][j-1] + matrix[i-1][j-1] - ans[i-1][j-1];
            }
        }
        return ans;
    }
    
    public int sumRegion(int[][] ans , int row1, int col1, int row2, int col2) 
    {
        return ans[row2 + 1][col2 + 1] - ans[row1][col2 + 1] - ans[row2 + 1][col1] + ans[row1][col1];
    }
    
    public boolean possibleToStamp(int[][] grid, int h, int w) 
    {
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] stamp = new int[n][m];
        
        int[][] pfx1 = NumMatrix(grid);
        
        
        for(int i = h-1;i<n;i++)
        {
            for(int j = w-1;j<m;j++)
            {
                stamp[i][j] = (sumRegion(pfx1,i-h+1,j-w+1,i,j) == 0) ? 1 : 0;
            }
        }
        
        int[][] pfx2 = NumMatrix(stamp);
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j] == 0 && sumRegion(pfx2,i,j,Math.min(n-1,i+h-1),Math.min(m-1,j+w-1)) == 0) return false;    
            }
        }
        return true;
    }

    // Minimum Swaps required to group all 1s together Geeks For Geeks Important Question Using Sliding Window

    public static int minSwaps (int arr[], int n) 
    {
        int sum = 0;
        for(int ele : arr) sum+=ele;
        int i = 0;
        int j = 0;
        int one = 0;
        int min = (int)1e9;
        if(sum == 0) return -1;
        while(j < n)
        {
            if(arr[j] == 1) one++;
            
            if(j > i+sum-1){
                if(arr[i] == 1) one--;
                i++;
            }
            
            if(j == i+sum-1){
                min =  Math.min(min , sum-one);
            }
            
            j++;
        }
        return min;
    }


    /// 2134. Minimum Swaps to Group All 1's Together II Leetcode

    public int minSwaps(int[] arr) 
    {
        int n = arr.length;
        int sum = 0;
        for(int ele : arr) sum+=ele;
        int i = 0;
        int j = 0;
        int one = 0;
        int min = (int)1e9;
        if(sum == 0) return 0;
        while(j < 2*n)
        {
            int x = i%n;
            int y = j%n;
            if(arr[y] == 1) one++;
            
            if(j > i+sum-1){
                if(arr[x] == 1) one--;
                i++;
            }
            
            if(j == i+sum-1){
                min =  Math.min(min , sum-one);
            }
            
            j++;
        }
        return min;
    }


    // 2133. Check if Every Row and Column Contains All Numbers

    public boolean checkValid(int[][] matrix) 
    {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 1;i<=matrix.length;i++) set.add(i);

        for(int i = 0;i<matrix.length;i++){
            HashSet<Integer> row = new HashSet<>();
            for(int j = 0;j<matrix[0].length;j++) row.add(matrix[i][j]);
            if(!set.equals(row)) return false;
            row = new HashSet<>();
        }
        
        for(int i = 0;i<matrix[0].length;i++){
            HashSet<Integer> col = new HashSet<>();
            for(int j = 0;j<matrix.length;j++) col.add(matrix[j][i]);
            if(!set.equals(col)) return false;
            col = new HashSet<>();
        }
        return true;
    }

    // Count Words Obtained After Adding a letter

    // 2 test gave TLE
    public int wordCount(String[] startWords, String[] targetWords)
    {
        int count = 0;
        HashMap<Integer,ArrayList<HashSet<Character>>> sizeMap = new HashMap<>();
        for(String ele : startWords){
            int len = ele.length();
            if(!sizeMap.containsKey(len)) sizeMap.put(len,new ArrayList<>());
            HashSet<Character> set = new HashSet<>();
            for(int i = 0;i<ele.length();i++) set.add(ele.charAt(i));
            sizeMap.get(len).add(set);
        }
        
        for(String ele : targetWords)
        {
            int len = ele.length();
            if(!sizeMap.containsKey(len-1)) continue;
            else 
            {
                if(len == 1) {
                    count++;
                    break;
                }
                ArrayList<HashSet<Character>> rem = sizeMap.get(len-1);
                for(HashSet<Character> str : rem)
                {
                    int matchCount = 0;
                    for(int i = 0;i<ele.length();i++)
                    {
                        if(str.contains(ele.charAt(i))) matchCount++;
                    }
                    if(matchCount == len-1){
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
        
    // 2 test gave TLE
        
    public int convertToMask(String str)
    {
        int a = 0;
        for(int i = 0;i<str.length();i++)
        {
            char ch = str.charAt(i);
            int togglePos = ch - 'a';
            a = a^(1 << togglePos);
        }
        return a;
    }
    public boolean isSingleNumber(int n){
        return (n & (n-1)) == 0;
    }
    
    public int wordCount(String[] startWords, String[] targetWords) 
    {
        int count = 0;
        HashMap<Integer,ArrayList<Integer>> map = new HashMap();
        for(String ele : startWords)
        {
            int length = ele.length();
            if(!map.containsKey(length)) map.put(length,new ArrayList<>());
            map.get(length).add(convertToMask(ele));
        }
        
        for(String ele : targetWords)
        {
            int len = ele.length();
            if(map.containsKey(len-1))
            {
                ArrayList<Integer> list = map.get(len-1);
                for(int ele2 : list)
                {
                    if(isSingleNumber(convertToMask(ele)^ele2))
                    {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
        

    // All Passed

    public int convertToMask(String str)
    {
        int a = 0;
        for(int i = 0;i<str.length();i++)
        {
            char ch = str.charAt(i);
            int togglePos = ch - 'a';
            a = a^(1 << togglePos);
        }
        return a;
    }
    
    public int wordCount(String[] startWords, String[] targetWords) 
    {
        int count = 0;
        
        HashSet<Integer> set = new HashSet<>();
        for(String ele : startWords){
            set.add(convertToMask(ele));
        }
        
        for(String ele : targetWords)
        {
            int mask = convertToMask(ele);
            for(int i = 0;i<ele.length();i++)
            {
                int toggle = ele.charAt(i)-'a';
                if(set.contains(mask ^ (1<<toggle)))
                {
                    count++;
                    break;
                }
            }
            
        }
        return count;
    }


    // 2138. Divide a String Into Groups of Size k

    public String[] divideString(String s, int k, char fill) 
    {        
        StringBuilder sb = new StringBuilder(s);
        int len = s.length();
        int remLen = k-(len%k);
        
        int temp = remLen;
        while(remLen!=k && temp-- > 0){
            sb.append(fill);
        }
        
        int x = 0;
        int y = x+k;
        String[] ans = new String[sb.length()/k];
        int z = 0;
        while(y <= (sb.length())){
            ans[z++] = (sb.substring(x,y));
            x = x + k;
            y = y + k;
        }
        return ans;
    }

    // 2139. Minimum Moves to Reach Target Score

    public int minMoves(int target, int maxDoubles) 
    {        
        int count = 0;
        while(maxDoubles > 0 && target > 1)
        {
            if(maxDoubles > 0)
            {
                if(target%2!=0)
                {
                    target = target/2;
                    count+=2;
                    maxDoubles--;
                }
                else 
                {
                    target = target/2;
                    count++;
                    maxDoubles--;
                }
            }
            else
            {
                count+= target-1;
                break;
            }
        }
        
        if(target!=1) return count + target-1;
        return count;
    }


    // 2124. Check if All A's Appears Before All B's

    public boolean checkString(String s) 
    {
        boolean bAppear = false;
        boolean aAppear = false;
        
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch == 'a'){
                aAppear = true;
                if(bAppear) return false;
            }
            else if(ch == 'b') bAppear = true;
        }
        
        return true;
    }

    // 2125. Number of Laser Beams in a Bank

    public int countDevices(String row)
    {
        int count = 0;
        for(int i = 0;i<row.length();i++){
            char ch = row.charAt(i);
            if(ch == '1') count++;
        }
        return count;
    }
    
    public int numberOfBeams(String[] bank) 
    {
        int ans = 0;
        int prev = 0;
        int curr = 0;
        
        prev = countDevices(bank[0]);
        
        for(int i = 1;i<bank.length;i++)
        {
            curr = countDevices(bank[i]);
            if(curr == 0) continue;
            ans += prev * curr;
            prev = curr;
            curr = 0;
        }
        
        return ans;
        
    }

    // Destroying Asteroids
    // Imp

    public boolean asteroidsDestroyed(int mass, int[] asteroids) 
    {
        Arrays.sort(asteroids);
        long masss = mass;
        for (int asteroid : asteroids) 
        {
            if ((long) asteroid > masss) return false;
            masss += asteroid;
        }
        return true;
    }
    

    // 2140. Solving Questions With Brainpower
    // Important DP Problem

    public long mostPoints(int[][] q)
    {
        long[] dp = new long[200001];
        
        for(int i = q.length-1;i>=0;i--)
        {
            dp[i] = Math.max(q[i][0]+dp[i+1+q[i][1]],dp[i+1]);
        }
        return dp[0];
    }

    // 2144. Minimum Cost of Buying Candies With Discount

    public int minimumCost(int[] cost) 
    {
        Arrays.sort(cost);
        
        int minCost = 0;
        int c = 0;
        for(int i = cost.length-1;i>=0;i--)
        {
            c++;
            if(c == 3){
                c = 0;
                continue;
            }
            minCost+=cost[i];
        }
        return minCost;
    }

    // 2146. K Highest Ranked Items Within a Price Range
    class pair
    {
        int price;
        int row;
        int col;
        int dist;
        
        pair(){
        }
        pair(int price , int row , int col , int dist){
            this.row = row;
            this.col = col;
            this.price = price;
            this.dist = dist;
        }
    }
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] price, int[] start, int k) 
    {
        
        List<List<Integer>> ans = new ArrayList<>();
        int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        
        PriorityQueue<pair> queue = new PriorityQueue<>((a,b)->{
            if(a.dist != b.dist) return a.dist-b.dist;
            else if(a.price!=b.price) return a.price-b.price;
            else if(a.row != b.row) return a.row-b.row;
            else return a.col-b.col;
        });
        
            
        LinkedList<pair> bfs = new LinkedList<>();
        grid[start[0]][start[1]] = -1;
        bfs.add(new pair(0,start[0],start[1],0));
        
        while(bfs.size() > 0)
        {
            pair temp = bfs.removeFirst();
            if(grid[temp.row][temp.col] > 1 && temp.price >= price[0] && temp.price <= price[1]){
                queue.add(temp);
            }
            
            for(int[] ele : dir)
            {
                int x = temp.row + ele[0];
                int y = temp.col + ele[1];
                
                if(x >=0 && y >=0 && x < grid.length && y < grid[0].length && grid[x][y]>0)
                {
                    grid[x][y] = -1;
                    bfs.add(new pair(grid[x][y],x,y,temp.dist+1));
                }
            }
        }
        
        while(queue.size() > 0 && k-- > 0)
        {
            pair temp = queue.remove();
            ArrayList<Integer> coord = new ArrayList<>();
            coord.add(temp.row);
            coord.add(temp.col);
            ans.add(coord);
        }
        return ans;
    }


    // 2145. Count the Hidden Sequences
    // BinarySearch Solution

    // Not an optimal solution

    public int[] isPossibleStart(int[]diff,int val ,int lower , int upper)
    {
        for(int i = 0;i<diff.length;i++)
        {
            int num = diff[i];
            if(val+num >= lower && val+num <=upper){
                val = val+num;
            }
            else return new int[]{-1,num+val};
        }
        
        return new int[]{1,1};
    }
    public int numberOfArrays(int[] diff, int lower, int upper) 
    {
        int i = lower;
        int j = upper;
        int poss = 0;
        while(i <= j)
        {
            int mid = (i+j)/2;
            int[] ans = isPossibleStart(diff,mid,lower,upper);
            if(ans[0] == 1) 
            {
                j = mid-1;
                poss = mid;
            }
            else 
            {
                if(ans[1] < lower) i = mid+1;
                else if(ans[1] > upper) j = mid-1;
            }
        }
        
        int[] check = new int[diff.length+1];
        check[0] = poss;
        int maxValInCheck = poss;
        int minValInCheck = poss;
        for(int k = 1;k<=diff.length;k++)
        {
            check[k] = check[k-1] + diff[k-1];
            maxValInCheck = Math.max(maxValInCheck,check[k]);
            minValInCheck = Math.min(minValInCheck,check[k]);
        }
        
        if(maxValInCheck > upper  || minValInCheck < lower ) return 0;
    
        return upper - maxValInCheck + 1;
        
    }

    //2178. Maximum Split of Positive Even Integers

    // for 28 this code is producing [2,4,6,16] 
    // the idea is to start with minimum even number values....
    // So we can have multiple answers and not only many permutations of answers
      public List<Long> maximumEvenSplit(long f) 
      {
        LinkedList<Long> ans = new LinkedList<>();
        if (f % 2 == 0) 
        {
            long i = 2;
            while (i <= f) 
            {
                ans.offer(i);
                f -= i;
                i += 2;
            } 
            ans.offer(f + ans.pollLast());
        }
        return ans;
     }


    // 2177. Find Three Consecutive Integers That Sum to a Given Number

     // O(1) solution is also there check that as well in discuss
    // Simply Divide by three
    
    // public long[] sumOfThree(long num) {
    //     if (num % 3 != 0) {
    //         return new long[0];
    //     }
    //     num /= 3;
    //     return new long[]{num - 1, num, num + 1};
    // }
    
    public long[] sumOfThree(long n) 
    {
        long i = 0;
        long j = n+1; // to handle n == 0
        while(i<=j)
        {
            long mid = i + ((j - i) / 2);
            long sum = (3*mid)-3;
            if(sum == n){
                return new long[]{mid-2,mid-1,mid};
            }
            else if(sum > n) j = mid-1;
            else i = mid + 1;
        }
        return new long[]{};
    }

    
    
}