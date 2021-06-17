import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
class dp_faang
{
    public static void print1D(int[] arr)
    {
        for(int ele : arr) System.out.print(ele +  " ");
    }
    public static void print2d(int[][] arr)
    {
        for(int[]a : arr) {
            print1D(a);
            System.out.println();
        }
    }
    // leetcode 264 ugly numbers
    public static int nthUglyNumber(int n) 
    {
        int[] dp = new int[n+1];
        dp[1] = 1;
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        for(int i = 2; i < n+1 ;i++)
        {
            dp[i] = Math.min(2 * dp[p2] , Math.min(3 * dp[p3] , 5 * dp[p5]));
            if(dp[p2] * 2 == dp[i]) p2++;
            if(dp[p3] * 3 == dp[i]) p3++;
            if(dp[p5] * 5 == dp[i]) p5++;
        }
        return dp[n];
    }
    // leetcode 313
    public static int nthSuperUglyNumber(int n, int[] primes) 
    {
        int [] dp = new int[n+1];
        int [] ptrs  = new int[primes.length];
        dp[1] = 1;
        PriorityQueue<Integer> val = new PriorityQueue<>();
        HashSet<Integer> dup = new HashSet<>();
        Arrays.fill(ptrs , 1);
        for(int ele : primes)
        {
            val.add(ele);
            dup.add(ele);
        }
        for(int i=2;i<n+1;i++)
        {
            dp[i] = val.remove();
            for(int j = 0;j<ptrs.length;j++)
            {
                if(dp[i] == primes[j] * dp[ptrs[j]])
                {
                    ptrs[j]++;
                    if(!dup.contains(primes[j] * dp[ptrs[j]]))
                    {
                        dup.add(primes[j] * dp[ptrs[j]]);
                        val.add(primes[j] * dp[ptrs[j]]);
                    }
                }
            }
        }
        return dp[n];
    }
    // gfg max sum bitonic subsequence
    public static int maxSumBS(int arr[], int n)
    {
        int[] lisLr = new int[n];
        int[] lisRl = new int[n];
        lisRl[n-1] = arr[n-1];
        lisLr[0] = arr[0];
        int max = -(int)1e9;
        for(int i = 1;i<n;i++)
        {
            lisLr[i] = arr[i];
            for(int j = i-1;j>=0;j--)
            {
                if(arr[j] < arr[i]) lisLr[i] = Math.max(lisLr[i] , arr[i] + lisLr[j]);
            }
        }
        for(int i = n-2;i>=0;i--)
        {
            lisRl[i] = arr[i];
            for(int j = i+1;j<n;j++)
            {
                if(arr[j] < arr[i]) lisRl[i] = Math.max(lisRl[i] , arr[i] + lisRl[j]);
            }
        }
        print1D(lisLr);
        System.out.println();
        print1D(lisRl);
        for(int i = 0;i<n;i++) max = Math.max(max , lisLr[i] + lisRl[i] - arr[i]);
        return max;
    }
    // LCS OF 3 STRINGS
    public static int lcs(String A , String B , String C)
    {
        int count = 0;
        int[][][] dp = new int[A.length()+1][B.length()+1][C.length()+1];
        for(int [][]ele  : dp)
        {
            for(int []ele1 : ele) Arrays.fill(ele1,-1);
        }
        return count = lcs_memo(A, B , C , A.length(), B.length(),C.length(), dp);
        
    }
    public static int lcs_memo(String A , String B , String C , int n , int m , int o , int[][][]dp)
    {
        if(n == 0||m == 0 || o == 0) return dp[n][m][o] = 0;
        if(dp[n][m][o]!=-1) return dp[n][m][o];
        int count = 0;
        if(A.charAt(n-1) == B.charAt(m-1) && B.charAt(m-1) == C.charAt(o-1)) count += lcs_memo(A,B,C,n-1,m-1,o-1,dp) + 1;
        else count = Math.max(count,Math.max(lcs_memo(A, B,C, n-1, m,o,dp) , Math.max(lcs_memo(A, B, C,n, m-1,o, dp) , lcs_memo(A, B,C, n, m,o-1 ,dp))));
        return dp[n][m][o] = count;
    }
    public static int LCSof3(String A, String B, String C, int n1, int n2, int n3) 
    { 
        return lcs(A,B,C);
    }
    public static void main(String[] args) {
        // int[] arr1 = new int[]{1, 15, 51, 45, 33,100, 12, 18, 9};
        // maxSumBS(arr1, 9);
        //LCSof3("geeks","geeksfor", "geeksforgeeks", 5, 8, 13);
        LCSof3("abcd","efgh","ijkl", 4, 4, 4);

    }
}