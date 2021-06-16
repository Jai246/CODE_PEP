import java.util.Arrays;
public class coinSet
{
    public static void print1D(int dp[])
    {
        for(int ele:dp)
        {
            System.out.print(ele + " ");
        }
    }
    public static void print2D(int dp[][])
    {
        for(int[] d : dp)
        {
            print1D(d);
            System.out.println();
        }
    }
    public static void print1D_boolean(boolean dp[])
    {
        for(boolean ele:dp)
        {
            if(ele) System.out.print("T" + " ");
            else System.out.print("F" + " ");
        }
    }
    public static void print2D_boolean(boolean dp[][])
    {
        for(boolean[] d : dp)
        {
            print1D_boolean(d);
            System.out.println();
        }
    }
    public static int coinChange_memo(int[] arr,int[] dp,int tar)
    {
        if(tar == 0) return dp[tar] =  1;
        if(dp[tar]!= -1) return dp[tar];
        int count = 0;
        for(int i = 0;i<arr.length;i++)
        {
            if(tar-arr[i]>=0) count += coinChange_memo(arr,dp,tar-arr[i]);
        }

        return dp[tar] = count;
    }
    public static void coinChange_perm(int[] arr,int tar)
    {
        int sum = 0;
        for(int ele : arr) sum += ele;
        int[] dp = new int[tar+1];
        Arrays.fill(dp,-1);
        if(sum!=0) coinChange_memo(arr, dp, tar);
        System.out.println(dp[tar]);
        print1D(dp);
    }
    public static void coinChange_Dp(int[] arr , int tar)
    {
        int[] dp = new int[tar+1];
        dp[0] = 1;
        //int count = 0;
        for(int i = 1;i<=tar;i++)
        {
            for(int j = 0;j<arr.length;j++)
            {
                if(i-arr[j]>=0) dp[i]+=dp[i-arr[j]]; 
            }
        }
        System.out.println(dp[tar]);
        print1D(dp);
    }
    public static int coinChange_Combi_memo(int[] arr, int[][] dp , int i , int tar)
    {
        if(tar == 0) return dp[i][tar] = 1;
        if(dp[i][tar]!=-1) return dp[i][tar];
        int count = 0;
        for(int j = i;j<arr.length;j++)
        {
            if(tar-arr[j]>=0) count += coinChange_Combi_memo(arr, dp, j, tar-arr[j]);
        }
        return dp[i][tar] = count;
    }
    public static void coinChange_combi(int[] arr , int tar)
    {
        int[][] dp = new int[arr.length][tar+1];
        for(int [] d : dp) Arrays.fill(d,-1);
        coinChange_Combi_memo(arr, dp, 0 , tar);
        System.out.println(dp[0][tar]);
        print2D(dp);
    }
    public static void coinChange_combi_2DP(int[] arr , int Tar , int LI , int[][]dp)
    {
        for(int tar = 0;tar<=Tar;tar++)
        {
            for(int li = 0;li<=LI;li++)
            {
                if(tar == 0){
                    dp[li][tar] = 1;
                    continue;
                }
                for(int i = li;i<arr.length;i++)
                {
                    if(tar - arr[i]>=0)
                    {
                        dp[li][tar]+=dp[i][tar - arr[i]];
                    }
                }
            }
        }

        System.out.println(dp[0][Tar]);
    }
    public static void coinChange_combi_2DP(int [] arr , int tar)
    {
        int[][] dp = new int[arr.length][tar+1];
        coinChange_combi_2DP(arr, tar, arr.length-1, dp);
        print2D(dp);
    }

    public static void coinChange_Combi_1DP(int[] arr , int tar)
    {
        int[] dp = new int[tar+1];
        dp[0] = 1;
        //int count = 0;
        for(int j = 0;j<arr.length;j++)
        {
            for(int i = 1;i<=tar;i++)
            {
                if(i-arr[j]>=0) dp[i]+=dp[i-arr[j]]; 
            }
        }
        System.out.println(dp[tar]);
        print1D(dp);
    }
    // Leetcode 518
    public int change(int tar, int[] arr) 
    {
        int[] dp = new int[tar+1];
        dp[0] = 1;
        for(int j = 0;j<arr.length;j++)
        {
            for(int i = 1;i<=tar;i++)
            {
                if(i-arr[j]>=0) dp[i]+=dp[i-arr[j]]; 
            }
        }
        System.out.println(dp[tar]);
        return dp[tar];
    }
    //Leetcode 377
    public int combinationSum4(int[] arr, int tar) 
    {
        int[] dp = new int[tar+1];
        dp[0] = 1;
        for(int i = 1;i<=tar;i++)
        {
            for(int j = 0;j<arr.length;j++)
            {
                if(i-arr[j]>=0) dp[i]+=dp[i-arr[j]]; 
            }
        }
        
        return dp[tar];
    }
    //Leetcode 322
    public int coinChange(int[] coins, int amount) 
    {
        int[]dp = new int[amount+1];
        Arrays.fill(dp,-1);
        coinChange(coins,amount,dp);
        int amt =  dp[amount];
        return amt == (int)1e8 ? -1 : amt;
    }
    
    public int coinChange(int[] arr , int tar,int[] dp)
    {
        if(tar == 0) return dp[tar] = 0;
        if(dp[tar]!=-1) return dp[tar];
        int minCoins = (int)1e8;
        for(int i = 0;i<arr.length;i++)
        {
            if(tar-arr[i]>=0) minCoins = Math.min(minCoins , coinChange(arr,tar-arr[i],dp)+1);
        }
        return dp[tar] = minCoins;
    }
    public static int Solution(int[] arr , int tar , int[][] dp , int i)// we can solve this using combination 1D
    {
        if(tar == 0) return dp[i][tar] = 1;
        if(dp[i][tar] != -1) return dp[i][tar];
        int count = 0;
        for(int j = i;j<arr.length;j++)
        {
            if(tar-arr[j]>=0) count += Solution(arr,tar-arr[i],dp,j);
        }
        return dp[i][tar] = count;

    }
    public static void Solution(int[] arr ,int tar)
    {
        int[][] dp = new int[arr.length][tar+1];
        for(int [] d:dp) Arrays.fill(d,-1);
        Solution(arr,tar,dp,0);
        System.out.println(dp[0][tar]);
    }
    // Printing The Solutions
    public static void printSol(int[] arr , int[] sol , int tar)
    {
        int ch = 'a';
        for(int m = 0;m < arr.length;m++)
        {
            System.out.println(((char)(ch+m)) + "->" + sol[m]);
        }
    }
    public static void Solution_Print(int[] arr , int tar, int i,int[] sol)// we can solve this using combination 1D
    {
        if(tar == 0)
        {
            printSol(arr,sol,tar);
            System.out.println();
            System.out.println();
            return;
        }
        for(int j = i;j<arr.length;j++)
        {
            if(tar-arr[j]>=0)
            {
                sol[j]++;
                Solution_Print(arr,tar-arr[i],j,sol);
                sol[j]--;
            }
        }
        return;
    }
    public static void Solution_Print(int[] arr , int tar)
    {
        int[] sol = new int[arr.length];
        Solution_Print(arr,tar,0,sol);
    }
    //Peeche Se
    public static int SubsetSum_Rear(int[] arr , int tar ,int i , int[][] dp)
    {
        if(tar == 0) return dp[i-1][tar] = 1;
        if(dp[i-1][tar]!=-1) return dp[i-1][tar];
        boolean res = false;
        // for(int j = i;j<arr.length;j++)
        // {
        //     if(tar - arr[j]>=0 && j+1<arr.length) res = res ||  SubsetSum(arr,tar-arr[j],j+1,dp) == 1;
        // }
        if(tar - arr[i-1]>=0 && i-1>0) res = res || SubsetSum_Rear(arr,tar-arr[i-1],i-1,dp) == 1;
        if(i-1>0)res = res || SubsetSum_Rear(arr,tar,i-1,dp) == 1;
        return dp[i-1][tar] = res ? 1:0; 
    }
    public static void SubsetSum_Rear(int[] arr , int tar)
    {
        int[][] dp = new int[arr.length][tar+1];
        for(int[] d:dp) Arrays.fill(d,-1);
        SubsetSum_Rear(arr,tar,arr.length,dp);
        print2D(dp);
        System.out.println(dp[arr.length-1][tar]);
    }
    public static void SubsetSum_DP_Type1(int[] arr , int tar)
    {
        boolean[][] dp = new boolean[arr.length+1][tar+1];
        for(int i = 0;i<=arr.length;i++)
        {
            for(int j = 0;j<=tar;j++)
            {
                if(j == 0 || i == 0) 
                {
                    dp[i][j] = (j == 0);
                    continue;
                }
                if(j-arr[i-1]>=0) dp[i][j] =  dp[i-1][j-arr[i-1]];
                dp[i][j] = dp[i][j] || dp[i-1][j];

            }
        } 

        System.out.println(dp[arr.length][tar]);
        print2D_boolean(dp);
        System.out.println();
        System.out.println();
        printingSubsets(dp, arr, arr.length, tar, "");
    }
    public static void SubsetSum_DP_Type1_TotalWays(int[] arr , int tar)
    {
        int[][] dp = new int[arr.length+1][tar+1];
        for(int i = 0;i<=arr.length;i++)
        {
            for(int j = 0;j<=tar;j++)
            {
                if(j == 0 || i == 0) 
                {
                    dp[i][j] = (j == 0 ? 1: 0);
                    continue;
                }
                if(j-arr[i-1]>=0) dp[i][j] +=  dp[i-1][j-arr[i-1]];
                dp[i][j] +=  dp[i-1][j];
            }
        } 
        System.out.println(dp[arr.length][tar]);
        print2D(dp);
    }
    public static void printingSubsets(boolean[][] dp,int[] arr,int i,int tar,String str)
    {
        if(!dp[i][tar]) return;
        if(tar == 0)
        {
            System.out.println(str);
            return;
        }
        if(tar - arr[i-1]>=0 && dp[i][tar]) printingSubsets(dp,arr,i-1,tar-arr[i-1],str +" "+ arr[i-1]);
        if(i-1>=0) printingSubsets(dp,arr,i-1,tar,str);
    }
    // 416
    public static boolean canPartition(int[] arr) 
    {
        int sum = 0;
        for(int ele : arr) sum += ele;
        if(sum % 2 !=0) return false;
        int tar = sum/2;

        boolean[][] dp = new boolean[arr.length+1][tar+1];
        for(int i = 0;i<=arr.length;i++)
        {
            for(int j = 0;j<=tar;j++)
            {
                if(j == 0 || i == 0) 
                {
                    dp[i][j] = (j == 0);
                    continue;
                }
                if(j-arr[i-1]>=0) dp[i][j] =  dp[i-1][j-arr[i-1]];
                dp[i][j] = dp[i][j] || dp[i-1][j];

            }
        }
        return dp[arr.length][tar];
    }
    public static void findTargetSumWays(int[] nums, int target) 
    {
        int sum = 0;
        for(int ele:nums) sum+=ele;
        if(sum<target||target<-sum) System.out.println(0);;
        int[][] dp = new int[nums.length+1][2*sum + 1];
        for(int[] d:dp) Arrays.fill(d,-1);
        System.out.println(findTargetSumWays(nums,sum + target,sum,nums.length,dp));;
        print2D(dp);
    }
    public static int findTargetSumWays(int[] nums,int target,int sum,int i,int[][] dp) 
    {
        if(i == 0)
        {
            return dp[i][sum] =  (target == sum) ?  1 : 0;
        }
        if(dp[i][sum] != -1) return dp[i][sum];
        int count = 0;
        if(sum+nums[i-1] < dp[0].length) count += findTargetSumWays(nums,target,sum + nums[i-1],i-1,dp);
        if(sum-nums[i-1] >=0) count += findTargetSumWays(nums,target,sum - nums[i-1],i-1,dp);
        return dp[i][sum] = count;
    }
    public static void knapSack() 
    { 
        int W = 50;
        int[] val = new int[]{60,100,120,150,170};
        int[] wt = new int[]{0,1,100,2,3};
        int i = val.length;
        System.out.println(knapSack(W, wt, val,i, 0, 0));;
    }
    public static int knapSack(int W, int wt[], int val[], int i , int wtSum , int valSum) 
    { 
        if(i == 0 || wtSum == W)
        {
            return wtSum<=W ? valSum : 0;
        }
        int sum = 0;
        if(wtSum+wt[i-1]<=W) sum = Math.max(sum,knapSack(W,wt,val,i-1,wtSum + wt[i-1],valSum + val[i-1]));
        sum = Math.max(sum,knapSack(W,wt,val,i-1,wtSum,valSum));
        return sum;
    }

    public static void knapSack_DP() 
    { 
        int W = 50;
        int[] val = new int[]{60,100,120,150,170};
        int[] wt = new int[]{0,1,100,2,3};
        int[][] dp = new int[val.length+1][W+1];
        for(int[]d:dp) Arrays.fill(d,-1);
        knapSack_DP(wt, val,W,val.length);
        //print2D(dp);
    }
    public static int knapSack_memo(int wt[], int val[],int wtSum, int i,int[][]dp) 
    { 
        if(i == 0 || wtSum == 0)
        {
            return dp[i][wtSum] = 0;
        }
        if(dp[i][wtSum]!=-1) return dp[i][wtSum];
        int sum = 0;
        if(wtSum-wt[i-1]>=0) sum = Math.max(sum,knapSack_memo(wt,val,wtSum-wt[i-1],i-1,dp) + val[i-1]);
        sum = Math.max(sum,knapSack_memo(wt,val,wtSum,i-1,dp));
        return dp[i][wtSum] = sum;
    }
    public static void knapSack_DP(int wt[], int val[],int WtSum, int i) 
    { 
        int N = val.length;
        int[][] dp = new int[val.length+1][WtSum+1];
        for(int n = 0;n<=N;n++)
        {
            for(int wtSum=0;wtSum<=WtSum;wtSum++)
            {
                if(n == 0 || wtSum == 0)
                {
                    dp[n][wtSum] = 0;
                    continue;
                }
                if(wtSum-wt[n-1]>=0) dp[n][wtSum] = dp[n-1][wtSum - wt[n-1]] + val[n-1];
                dp[n][wtSum] = Math.max(dp[n][wtSum] , dp[n-1][wtSum]);
            }
        }

        System.out.println(dp[N][WtSum]);
        print2D(dp);
    }
    public static void knapSack_Unbounded(int W, int val[], int wt[]) // can use both perm and combi
    {// Combination
        int n = wt.length;
        int[] dp = new int[W+1];
        for(int i = 0;i<n;i++)
        {
            for(int bagWt = wt[i];bagWt<=W;bagWt++)
            {
                dp[bagWt] = Math.max(dp[bagWt],dp[bagWt -  wt[i]] + val[i]) ;
            }
            print1D(dp);
            System.out.println();
        }

        System.out.println(dp[W]);
    }
    public static void KnapSack_Unbounded()
    {
        int W = 8;
        int[] val = new int[]{10,40,50,70};
        int[] wt = new int[]{1,3,4,5};
        knapSack_Unbounded(W,val,wt);
    }
    public static boolean canPartitionKSubsets(int[] nums, int k) 
    {
        int sum = 0;
        int maxEle = 0;
        for(int ele : nums)
        {
            sum+=ele;
            maxEle = Math.max(maxEle,ele);
        }
        if(sum %k!=0 || maxEle > sum/k) return false;
        boolean[] vis = new boolean[nums.length];
        return canPartitionKSubsets(nums,k,0,0,sum/k,vis);
    }
    public static boolean canPartitionKSubsets(int[] nums, int k,int idx , int ssf , int tar , boolean[] vis) 
    {
        if(k == 0) return true;
        if(ssf > tar) return false;
        if(ssf == tar) return canPartitionKSubsets(nums,k-1,0,0,tar,vis);
        boolean res = false;
        for(int i = idx ;i<nums.length;i++)
        {
            if(vis[i]) continue;
            vis[i] = true;
            res = res || canPartitionKSubsets(nums,k,i+1,ssf+nums[i],tar,vis);
            vis[i] = false;
        }
        return res;
    }
    //This is 
    public static int temp(int[] arr , int tar , int i , int [][]dp)// Can't be converted into DP 
    {
        if(i == arr.length && tar!=0) return 0;
        if(i == arr.length && tar == 0) return 1;
        if(tar == 0) return dp[i][tar] = 1;
        if(dp[i][tar]!=-1) return dp[i][tar];
        int count = 0;
        if(tar-arr[i] >=0) count+= temp(arr,tar-arr[i],i+1,dp);
        count+= temp(arr,tar,i+1,dp);
        return dp[i][tar] = count;
    }
    public static void temp()
    {
        int [] arr = new int[]{2,3,5,7};
        int tar = 10;
        int[][] dp =new int[arr.length][tar+1];
        for(int[] d : dp) Arrays.fill(d,-1);
        temp(arr,tar,0,dp);
        print2D(dp);
    }
    public static void main(String[] args)
    {
        int[] arr = new int[]{2,3,5,7};
        // int tar = 10;
        // int[] arr2 = new int[]{3,4,5,2};
        // int tar2 = 9;
        // int[] arr3 = new int[]{1,1,1,1,1};
        // int tar3 = 3;
        //coinChange_perm(arr,tar);
        //coinChange_Dp(arr, tar);
        //coinChange_combi(arr, tar);
        //coinChange_combi(arr,tar);
        //coinChange_combi_2DP(arr, tar);
        //coinChange_Combi_1DP(arr ,tar);
        //Solution(arr2,tar2);
        //Solution_Print(arr2, tar2);
        //SubsetSum_Rear(arr,tar);
        //SubsetSum_DP_Type1(arr,2);
        SubsetSum_DP_Type1_TotalWays(arr, 10);
        //findTargetSumWays(arr3, tar3);
        //KnapSack_Unbounded();
        //temp();
        
    }
}
