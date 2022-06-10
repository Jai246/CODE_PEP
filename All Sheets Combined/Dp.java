public class Dp 
{
    // Word Wrap    
    // Same as we did the Filling Book Shelves DP problem
    public int solveWordWrap (int[] nums, int k)
    {
        int[] dp = new int[nums.length];
        
        for(int i = 0;i<nums.length;i++)
        {
            if(i == nums.length-1) dp[i] = ((i-1) >=0 ? dp[i-1] : 0);
            else dp[i] = ((i-1) >=0 ? dp[i-1] : 0) + (k-nums[i])*(k-nums[i]); 
            int sum = nums[i];
            int j = i-1;
            for(;j>=0;j--)
            {
                int val = nums[j];
                if(val + sum + 1 <= k)
                {
                    if(i == nums.length-1) dp[i] = Math.min(dp[i],((j-1) >=0 ? dp[j-1] : 0));
                    else dp[i] = Math.min(dp[i] , ((j-1) >= 0 ? dp[j-1] : 0) + (k-(val + sum + 1))*(k-(val + sum + 1)));
                    sum+=val+1;
                }
                else break;
            }
            if(i == nums.length-1 && j==-1) dp[i] = 0;
        }
        return dp[nums.length-1];
    }
    
    // Number of Coins 
    // Simple Dp Solution Combinations

    public int minCoins(int coins[], int M, int V) 
    { 
        int[] dp = new int[V+1];
        Arrays.fill(dp,(int)1e9);
        dp[0] = 0;
        
        for(int i = 1;i<dp.length;i++)
        {
            for(int ele : coins)
            {
                if(i-ele >= 0 && dp[i-ele]!=(int)1e9)
                {
                    dp[i] = Math.min(dp[i],dp[i-ele] + 1);
                }
            }
        }
        if(dp[V] == (int)1e9) return -1;
        return dp[V];
    }

    // Count all subsequences having product less than K
    // This Can Easily be done using subset sum 1d Approack

    public static int prodCount(int[] arr , int k)
    {
        int[]dp = new int[k+1];

        for(int ele : arr)
        {
            for(int i = k;i>0;i--)
            {
                if(ele == i) dp[i]++;
                dp[i] += dp[i/ele];
            }
        }
        int sum = 0;
        for(int ele : dp) sum += ele;
        return sum;
    }


    // Maximum subsequence sum such that no three are consecutive
    // Important
    public static int maxSum(ArrayList<Integer> a, int n)
    {
        int[] dp = new int[n];
        dp[0] = a.get(0);
        if(n == 1) return dp[0];
        dp[1] = dp[0] + a.get(1);
        if(n == 2) return dp[1];
        dp[2] = Math.max(a.get(2) + a.get(1) ,Math.max(a.get(2) + a.get(0) , a.get(0) + a.get(1)));
        
        if(n == 3) return dp[2];
        for(int i = 3;i<n;i++)
        {
            dp[i] = Math.max(a.get(i)+a.get(i-1) + dp[i-3] , a.get(i) + dp[i-2]);
            dp[i] = Math.max(dp[i] , dp[i-1]);
        }
        return dp[n-1];
    }

    // Maximum path sum in matrix 

    static int maximumPath(int N, int grid[][])
    {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int maxAns = 0;
        for(int i = m-1;i>=0;i--) 
        {
            for(int j = n-1;j>=0;j--) 
            {
                int d1 = (i+1<m && j+1 < n) ? dp[i+1][j+1] : -(int)1e9;
                int d2 = (i+1<m && j-1 >=0) ? dp[i+1][j-1] : -(int)1e9;
                int d3 = (i+1<m) ? dp[i+1][j] : -(int)1e9;
                int max = Math.max(d1,Math.max(d2,d3));
                if(max == -(int)1e9) dp[i][j] = grid[i][j];
                else dp[i][j] = grid[i][j]+max;
                
                if(i == 0) maxAns = Math.max(maxAns,dp[i][j]); 
            }
        }
        return maxAns;
    }


    // Minimum cost to fill given weight in a bag 
    // Same ans combination/kanpsack unbounded

    public int minimumCost(int cost[], int N,int W)
    {
        int[] dp = new int[W+1];
        Arrays.fill(dp,(int)1e9);
        dp[0] = 0;
        for(int i = 0;i<cost.length;i++)
        {
            int w = i+1;
            int ele = cost[i];
            if(ele == -1) continue;
            for(int j = w;j<=W;j++)
            {
                dp[j] = Math.min(dp[j],ele+dp[j-w]);
            }
        }
        return dp[W];
    }

    // Reach a given score 
    // Just use combination code

    public long count(int n) 
    {
        long[] dp = new long[(int)n+1];
        Arrays.fill(dp, 0);
        dp[0] = 1;
        int[] sc = new int[]{3,5,10};
        for(int ele : sc)
        {
            for(int i = ele;i<=n;i++) dp[i] += dp[i-ele];
        }
        return dp[n];
    }


    // Geek and its Game of Coins 
    public int findWinner(int N,int X,int Y)
    {
        boolean[] dp = new boolean[N+1];
        dp[0] = false;
        dp[1] = true;
        
        for(int i = 2;i<=N;i++)
        {
            if(i-1 >= 0) dp[i] = dp[i] || !dp[i-1];
            if(i-X >= 0) dp[i] = dp[i] || !dp[i-X];
            if(i-Y >= 0) dp[i] = dp[i] || !dp[i-Y];
        }
        
        return (dp[N]) ? 1 : 0;
    }

    // Count Dearrangements
    // Ratne Wala Question
    // Recurrence Relation countDer(n) = (n - 1) * [countDer(n - 1) + countDer(n - 2)]
    // Ratlo Isko
    static int countDer(int n)
    {
        // Create an array to store
        // counts for subproblems
        int der[] = new int[n + 1];
     
        // Base cases
        der[1] = 0;
        der[2] = 1;
     
        // Fill der[0..n] in bottom up
        // manner using above recursive
        // formula
        for (int i = 3; i <= n; ++i) der[i] = (i - 1) * (der[i - 1] + der[i - 2]);
     
        // Return result for n
        return der[n];
    }

    // Largest rectangular sub-matrix whose sum is 0 
    // GFG mei dikkat hei
    // Mera answer sahi aaraha hei
    // O(n^4) solution
    // We  can do this in N*N*M or M*M*N using hashing

    //  The solution is based on Maximum sum rectangle in a 2D matrix.
    //  The idea is to reduce the problem to 1 D array. 
    //  We can use Hashing to find maximum length of sub-array in 1-D array in O(n) time. 
    //  We fix the left and right columns one by one and find the largest sub-array with 0 sum 
    //  contiguous rows for every left and right column pair. 
    //  We basically find top and bottom row numbers (which have sum is zero) 
    //  for every fixed left and right column pair.

    public static  int[][]ans;
    public static void NumMatrix(int[][] matrix) 
    {
        ans = new int[matrix.length+1][matrix[0].length+1];
        for(int i = 1;i<=matrix.length;i++)
        {
            for(int j = 1;j<=matrix[0].length;j++)
            {
                ans[i][j] = ans[i-1][j] + ans[i][j-1] + matrix[i-1][j-1] - ans[i-1][j-1];
            }
        }
    }
    public static int sumRegion(int row1, int col1, int row2, int col2) 
    {
        return ans[row2 + 1][col2 + 1] - ans[row1][col2 + 1] - ans[row2 + 1][col1] + ans[row1][col1];
    }
    
    
    public static ArrayList<ArrayList<Integer>> sumZeroMatrix(int[][] a) 
    {
        NumMatrix(a);
        
        int maxSize = 0;
        int m = a.length;
        int n = a[0].length;
        int start = -1;
        int end = -1;
        
        for(int i = 0;i<m;i++)
        {
            for(int j = 0;j<n;j++)
            {
                for(int x = i;x<m;x++)
                {
                    for(int y = j;y<n;y++)
                    {
                        int sum = sumRegion(i,j,x,y);
                        int size = (x-i+1)*(y-j+1);
                        
                        if(sum == 0 && size >= maxSize)
                        {
                            maxSize = size;
                            start = (i*n)+j;
                            end = (x*n)+y;
                        }
                    }
                }
            }
        }
        
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        int x1 = start/n;
        int x2 = start%n;
        
        
        int y1 = end/n;
        int y2 = end%n;
        
        
        if(start == -1 && end == -1)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(-1);
            ans.add(temp);
            return ans;
        }
        
        for(int i = x1;i<=y1;i++)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = x2;j<=y2;j++)
            {
                temp.add(a[i][j]);
            }
            ans.add(temp);
        }
        return ans;
    }

    
}
