class temp
{
    public static void print1D(int[] arr)
    {
        for(int ele : arr) System.out.print((ele == 1005) ? "M" + " " : ele + " ");
    }
    public static void print2d(int[][] arr)
    {
        for(int[]a : arr) {
            print1D(a);
            System.out.println();
        }
    }
    static final int MAX = 1005;
 
    // Returns length of shortest common subsequence
    static int shortestSeq(char[] S, char[] T)
    {
        int m = S.length, n = T.length;
 
        // declaring 2D array of m + 1 rows and
        // n + 1 columns dynamically
        int dp[][] = new int[m + 1][n + 1];
 
        // T string is empty
        for (int i = 0; i <= m; i++)
        {
            dp[i][0] = 1;
        }
 
        // S string is empty
        for (int i = 0; i <= n; i++)
        {
            dp[0][i] = MAX;
        }
 
        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                char ch = S[i - 1];
                int k;
                for (k = j - 1; k >= 0; k--)
                {
                    if (T[k] == ch)
                    {
                        break;
                    }
                }
 
                // char not present in T
                if (k == -1)
                {
                    dp[i][j] = 1;
                }
                else
                {
                    dp[i][j] = Math.min(dp[i - 1][j],
                                    dp[i - 1][k] + 1);
                    System.out.println(dp[i][j]);
                }
            }
        }

        print2d(dp);
 
        int ans = dp[m][n];
        if (ans >= MAX)
        {
            ans = -1;
        }
        return ans;
    }
 
    // Driver code
    public static void main(String[] args)
    {
        char S[] = "babab".toCharArray();
        char T[] = "babba".toCharArray();
        int m = S.length, n = T.length;
        System.out.println("Length of shortest" +
                            "subsequence is : " +
                            shortestSeq(S, T));
        
        
    }
}
 