class backtracking
{
    public static ArrayList<ArrayList<Integer>> knightTour(int n, int m) 
    {                
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = 0;i<n;i++)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = 0;j<m;j++) temp.add(-1);
            ans.add(temp);
        }
        order(0,0,m,n,ans,new int[]{0},n*m);
//         for(ArrayList<Integer> ele : ans) System.out.println(ele.toString());
        return ans;
    }
    
    
    public static boolean order(int x,int y,int m,int n ,ArrayList<ArrayList<Integer>> check,int []c,int count)
    {
        // System.out.println(c[0]);
        if(x<0 || y<0 || x >= n || y >= m || check.get(x).get(y) != -1) return false;
        check.get(x).set(y,c[0]);
        if(c[0] == count-1) 
        {
            // System.out.println("hello");
            return true;
        }

        c[0]++;
        boolean bool = false;
        bool = order(x+2,y-1,m,n,check,c,count);
        if(bool) return true;
        bool = order(x+2,y+1,m,n,check,c,count);
        if(bool) return true;
        bool = order(x-2,y+1,m,n,check,c,count);
        if(bool) return true;
        bool = order(x-2,y-1,m,n,check,c,count);
        if(bool) return true;
        bool = order(x-1,y+2,m,n,check,c,count);
        if(bool) return true;
        bool = order(x-1,y-2,m,n,check,c,count);
        if(bool) return true;
        bool = order(x+1,y-2,m,n,check,c,count);
        if(bool) return true;
        bool = order(x+1,y+2,m,n,check,c,count);
        if(bool) return true;
        if(!bool)check.get(x).set(y,-1);
        if(!bool) c[0]--;
        return bool;
    }


    // Tug of War
    // Using Back Tracking

    public static int tugOfWar(ArrayList<Integer> arr, int n) 
    {
        boolean[] vis = new boolean[n];
        int[] team1 = new int[]{0};
        int[] team2 = new int[]{0};
        int[] min = new int[]{(int)1e9};
        int toggle = 0;
        minVal(arr,0,team1,team2,vis,n,min);
        return min[0];
    }
    
    public static void minVal(ArrayList<Integer> arr,int toggle , int[]team1 , int[]team2 , boolean[]vis , int n , int[]min)
    {
        if(n == 0)
        {
            min[0] = Math.min(min[0],Math.abs(team1[0]-team2[0]));
            return;
        }
        for(int i = 0;i<arr.size();i++)
        {
            if(!vis[i])
            {
                if(toggle == 0)
                {
                    vis[i] = true;
                    team1[0] += arr.get(i);
                    minVal(arr,1,team1,team2,vis,n-1,min);
                    team1[0] -= arr.get(i);
                    vis[i] = false;
                }
                else if(toggle == 1)
                {
                    vis[i] = true;
                    team2[0] += arr.get(i);
                    minVal(arr,0,team1,team2,vis,n-1,min);
                    team2[0] -= arr.get(i);
                    vis[i] = false;
                }
                
            }
        }
    }


    // Shortest Safe Route In A Field With Landmines
    // Yeh Dp se pass nahi hoga
    // kyoki neeche row wale cell koo uper row wale cell ki zaroorat hei
    // Naa ki sirf oopar wale ko neeche wale ki

    // TOh yeh backtracking se hi karna huoga

    // With Memoisation it is giving TLE on coding ninjas

    public static int shortestPath(ArrayList<ArrayList<Integer>> field)
    {
        int n = field.size();
        int m = field.get(0).size();
        int[][] memo = new int[n][m];
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                memo[i][j] = (int)1e9;
                if(field.get(i).get(j) == 0)
                {
                    if(i + 1 < n) field.get(i+1).set(j,-1);
                    if(i - 1 >= 0) field.get(i-1).set(j,-1);
                    
                    if(j + 1 < m) field.get(i).set(j+1,-1);
                    if(j - 1 >=0) field.get(i).set(j-1,-1);
                }
            }
        }
        int[] min = new int[]{(int)1e9};
        boolean[] vis = new boolean[n];        
        for(int i = 0;i<n;i++)
        {
            if(!vis[i]) getShortest(field,i,0,n,m,min,vis,memo);
        }
        if(min[0] == (int)1e9) return -1;
        return min[0];
    }
    
    public static int getShortest(ArrayList<ArrayList<Integer>> field , int i , int j , int n , int m , int[] min , boolean[]vis , int[][] memo)
    {
        if(i < 0 || j < 0 || i>= n || j >= m) return (int)1e9;
        if(field.get(i).get(j) == -1 || field.get(i).get(j) == 0) return memo[i][j] = (int)1e9;
        if(memo[i][j] != (int)1e9) return memo[i][j]+1;
        if(j == m-1) return memo[i][j] = 1;
        field.get(i).set(j,-1);
        int res = (int)1e9;
        res = Math.min(res,getShortest(field,i-1,j,n,m,min,vis,memo));
        res = Math.min(res,getShortest(field,i+1,j,n,m,min,vis,memo));
        res = Math.min(res,getShortest(field,i,j-1,n,m,min,vis,memo));
        res = Math.min(res,getShortest(field,i,j+1,n,m,min,vis,memo));
        field.get(i).set(j,1);
        if(res == (int)1e9) return memo[i][j] = res;
        if(j == 0) 
        {
            vis[i] = true;
            min[0] = Math.min(min[0],res);
        }
        memo[i][j] = res;
        return  res + 1;
    }


    // Largest number in K swaps 
    // Using Back Tracking
    // Always use char array no get riid of NumberFormat Exception
    // I tried this problem like the Leetcode Question Max Swap K times but was not getting
    // the correct answer\

    static String max;
    public static String findMaximumNum(String str, int k) {
        //code here.
        max = str;
        findMaximum(str.toCharArray(), k);
        return max;
    }
    public static void findMaximum(char[] strArr, int k) {
        if(k == 0) return;

        for(int i = 0; i < strArr.length - 1; i++) {
            for(int j = i + 1; j < strArr.length; j++) {
                if(strArr[j] > strArr[i]) {
                    strArr = swap(strArr, i, j);
                    String st = new String(strArr);
                    if(max.compareTo(st) < 0) max = st;
                    findMaximum(strArr, k - 1);
                    strArr = swap(strArr, i, j);
                }
            }
        }
    }
    public static char[] swap(char[] strArr, int i, int j) {
        char temp = strArr[i];
        strArr[i] = strArr[j];
        strArr[j] = temp;
        return strArr;
    }

    // Path of greater than equal to k length 
    // Simple Backtracking Solution

    boolean pathMoreThanK(int V , int E , int K , int [] A)
    {
        @SuppressWarnings("unchecked")
        ArrayList<int[]>[] graph = new ArrayList[V];
        for(int i = 0;i<graph.length;i++) graph[i] = new ArrayList<>();
        boolean[] vis = new boolean[V];
        int i = 0;
        while (i < A.length-3)
        {
            int u = A[i];
            int v = A[i+1];
            int w = A[i+2];
            graph[u].add(new int[]{v,w});
            graph[v].add(new int[]{u,w});
            i = i + 3;
        }
        
        return doesExist(graph,0,0,K,vis);
    }
    
    boolean doesExist(ArrayList<int[]>[] graph ,int src , int psf , int K, boolean[] vis)
    {
        if(vis[src]) return false;
        if(psf >= K) return true;
    
        vis[src] = true;
        
        boolean res = false;
        
        for(int[] ele : graph[src])
        {
            res = res || doesExist(graph,ele[0],psf+ele[1],K,vis);
        }
        vis[src] = false;
        return res;
    }


    // Longest Route
    public static int longestPath(int n, int m, int[][] mat, int sx, int sy, int dx, int dy) 
    {
        int[] max = new int[]{-(int)1e9};
        getRoot(mat,sx,sy,dx,dy,mat.length,mat[0].length,max,0);
        if(max[0] == -(int)1e9) return -1;
        return max[0];
    }
    
    public static void getRoot(int[][] mat , int i , int j , int x , int y , int m , int n , int[]max , int c)
    {
        if(i < 0 || j < 0 || i >= m || j >= n) return;
        if(mat[i][j] == 0 || mat[i][j] == -1) return;
        if(i == x && j == y)
        {
            max[0] = Math.max(max[0],c);
            return;
        }
        mat[i][j] = -1;
        
        getRoot(mat,i+1,j,x,y,m,n,max,c+1);
        getRoot(mat,i-1,j,x,y,m,n,max,c+1);
        getRoot(mat,i,j-1,x,y,m,n,max,c+1);
        getRoot(mat,i,j+1,x,y,m,n,max,c+1);
        
        mat[i][j] = 1;
    }


    // Count all possible paths from top left to bottom right 

    long numberOfPaths(int m, int n)
    {
        long mod = (long)1e9 + 7;
        long[][] dp = new long[m][n];
        dp[m-1][n-1] = 1;
        for(int i = m-1;i>=0;i--)
        {
            for(int j = n-1;j>=0;j--)
            {
                if(i+1 < m) dp[i][j] = (dp[i][j] + dp[i+1][j])%mod;
                if(j+1 < n) dp[i][j] = (dp[i][j] + dp[i][j+1])%mod;
            }
        }
        return dp[0][0]%mod;
    }


    // Partition array to K subsets 

    public boolean isKPartitionPossible(int a[], int n, int k)
    {
        int sum = 0;
        for(int ele : a) sum+=ele;
        if(sum%k!=0) return false;
        int val = sum/k;
        boolean[] vis = new boolean[n];
        return ispossible(a,k-1,n,0,val,vis);
    }
    
    
    public boolean ispossible(int[]a , int k , int n , int ssf , int sum , boolean[]vis)
    {
        if(sum == ssf && k == 0) return true;
        else if(sum == ssf && k > 0) return ispossible(a,k-1,n,0,sum,vis);
        boolean res = false;
        for(int i = 0;i<n;i++)
        {
            if(!vis[i])
            {
                int val = a[i];
                if(ssf + val > sum) continue;
                vis[i] = true;
                res = res || ispossible(a,k,n,ssf+val,sum,vis);
                vis[i] = false;
            }
        }
        return res;
    }


    // Permutations of a given string 
    // In Sorted Order and With No Duplicates

    public List<String> find_permutation(String S)
    {
        char[] s = S.toCharArray();
        Arrays.sort(s);
        String A = "";
        for(char ele : s) A+=ele; 
        
        List<String> ans = new ArrayList<>();    
        StringBuilder sb = new StringBuilder(A); 
        perm(sb,0,ans);
        return ans;
    }
    
    public void perm(StringBuilder sb , int i , List<String> ans)
    {
        if(i == sb.length()-1)
        {
            ans.add(sb.toString());
            return;
        }
        for(int j = i;j<sb.length();j++)
        {
            StringBuilder temp = new StringBuilder(sb);
            if(j-1 >=0 )
            {
                if(temp.charAt(j-1) == temp.charAt(j)) continue;
            }
            char del = temp.charAt(j);
            temp.deleteCharAt(j);
            temp.insert(i,del);
            
            perm(temp,i+1,ans);
            
        }
    }


    // 46. Permutations

    public List<List<Integer>> permute(int[] nums) 
    {
        List<List<Integer>> ans = new ArrayList<>();
        perm(nums,0,ans);
        return ans;
    }
    
     public void perm(int[] sb , int i , List<List<Integer>> ans)
    {
        if(i == sb.length-1)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            for(int ele : sb) temp.add(ele);
            ans.add(temp);
            return;
        }
        for(int j = i;j<sb.length;j++)
        {
            int[] temp = sb.clone();
            if(j-1 >=0)
            {
                if(temp[j-1] == temp[j]) continue;
            }
            int del = temp[j];
            temp[j] = temp[i];
            temp[i] = del;
            
            perm(temp,i+1,ans);
            
        }
    }

    

    // 60. Permutation Sequence
    // Brute Force Solution

        public String getPermutation(int n, int k)
    {
        String A = "";
        for(int i = 1;i<=n;i++){
            A += i;
        }
        String[] ans = new String[]{""};
        int[] c = new int[]{k};
        StringBuilder sb = new StringBuilder(A); 
        perm(sb,0,ans,c);
        return ans[0];
    }
    
    public void perm(StringBuilder sb , int i , String[] ans , int[] c)
    {
        if(i == sb.length()-1)
        {
            c[0]--;
            if(c[0] == 0)
            {
                ans[0] = sb.toString();
            }
            return;
        }
        for(int j = i;j<sb.length() && c[0] > 0;j++)
        {
            StringBuilder temp = new StringBuilder(sb);
            if(j-1 >=0 )
            {
                if(temp.charAt(j-1) == temp.charAt(j)) continue;
            }
            char del = temp.charAt(j);
            temp.deleteCharAt(j);
            temp.insert(i,del);
            
            perm(temp,i+1,ans,c);
            
        }
    }


    // Optimized Solution

    








    // Painting the Fence 
    // Pure Recursive Solution
    public long mod = (int)1e9+7;
    long countWays(int n,int k)
    {
        return count(n,k,1,1,-1);
    }
    
    public long count(int n , int k , int i , int c ,int prev)
    {
        if(i == n+1) return 1;
        long res = 0;
        
        for(int j = 1;j<=k;j++)
        {
            if(c < 2 && j == prev) res = (res + count(n,k,i+1,c+1,j))%mod;
            if(j!=prev) res = (res + count(n,k,i+1,1,j))%mod;
        }
        return res%mod;
    }


    // With Memoisation

    public long mod = (int)1e9+7;
    long countWays(int n,int k)
    {
        long[][][] memo = new long[n+2][3][k+2];
        for(long [][] ele : memo)
        {
            for(long[] e : ele) Arrays.fill(e,-1);
        }
        return count(n,k,1,1,k+1,memo);
    }
    
    public long count(int n , int k , int i , int c ,int prev,long[][][]memo)
    {
        if(i == n+1) return memo[i][c][prev] = 1;
        if(memo[i][c][prev]!=-1) return memo[i][c][prev];
        long res = 0;
        
        for(int j = 1;j<=k;j++)
        {
            if(c < 2 && j == prev) res = (res + count(n,k,i+1,c+1,j,memo))%mod;
            if(j!=prev) res = (res + count(n,k,i+1,1,j,memo))%mod;
        }
        return memo[i][c][prev] =  res%mod;
    }

    // Tabulation
    // Btw its a simple counting problem
    // Check Dryrun in folder

    long countWays(int n,int k)
    {
        if(n==1) return k;
        long M = (long)1e9 + 7;
        long pp = k;
        long p = k*k;
         
        for(int i = 3;i<=n;i++)
        {
            long c = ((pp*(k-1))%M +  (p*(k-1))%M)%M;
            pp = p;
            p = c;
        }
        return p;
    }

}