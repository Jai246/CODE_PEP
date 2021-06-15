import java.util.ArrayList;
import java.util.Arrays;

public class LIS
{
    public static int LIS_memo(int []arr , int i , int[] dp)
    {
        if(dp[i]!=0) return dp[i] ;
        int length = 1;
        dp[i] = 1;
        for(int j = i-1;j>=0;j--)
        {
            if(arr[j]<arr[i])
            {
                length = Math.max(length,LIS_memo(arr,j,dp)+1);
            }
        }

        return dp[i] = length;
    }
    public static void LIS_DP(int[] arr)
    {
        int[] dp = new int[arr.length];
        int length = 1;
        for(int i = 0;i<arr.length;i++)
        {
            dp[i] = 1;
            for(int j = i-1;j>=0;j--)
            {
                if(arr[j]<arr[i])
                {
                    dp[i] = Math.max(dp[i] , dp[j]+1);
                }
            }
            length = Math.max(dp[i],length);
        }

        System.out.println(length);
    }
    public static void LISumSub_DP(int[] arr)
    {
        int[] dp = new int[arr.length];
        int sum = 0;
        for(int i = 0;i<arr.length;i++)
        {
            dp[i] = arr[i];
            for(int j = i-1;j>=0;j--)
            {
                if(arr[j]<arr[i])
                {
                    dp[i] = Math.max(dp[i] , dp[j]+arr[i]);
                }
            }
            sum = Math.max(dp[i],sum);
        }

        System.out.println(sum);
    }
    public static void LDS_DP(int[] arr)
    {
        int[] dp = new int[arr.length];
        int length = 1;
        for(int i = arr.length-1;i>=0;i--)
        {
            dp[i] = 1;
            for(int j = i+1;j<arr.length;j++)
            {
                if(arr[j]<arr[i])
                {
                    dp[i] = Math.max(dp[i] , dp[j]+1);
                }
            }
            length = Math.max(dp[i],length);
        }

        System.out.println(length);
    }
    public static void LIS_main(int[] arr)
    {
        int[] dp = new int[arr.length];
        int maxLen = 1;
        for(int j = 0;j<arr.length;j++)
        {
            maxLen = Math.max(maxLen,LIS_memo(arr,j,dp));
        }

        System.out.println(maxLen);
    }
    public static int[] LIS_DP_(int[] arr)
    {
        int[] dp = new int[arr.length];
        int length = 1;
        for(int i = 0;i<arr.length;i++)
        {
            dp[i] = 1;
            for(int j = i-1;j>=0;j--)
            {
                if(arr[j]<arr[i])
                {
                    dp[i] = Math.max(dp[i] , dp[j]+1);
                }
            }
            length = Math.max(dp[i],length);
        }

        System.out.println(length);
        return dp;
    }
    public static int[] LDS_DP_(int[] arr)
    {
        int[] dp = new int[arr.length];
        int length = 1;
        for(int i = arr.length-1;i>=0;i--)
        {
            dp[i] = 1;
            for(int j = i+1;j<arr.length;j++)
            {
                if(arr[j]<arr[i])
                {
                    dp[i] = Math.max(dp[i] , dp[j]+1);
                }
            }
            length = Math.max(dp[i],length);
        }

        System.out.println(length);
        return dp;
    }
    public static int[] LDS_DP_LR(int[] arr)
    {
        int[] dp = new int[arr.length];
        int length = 1;
        for(int i = 0;i<arr.length;i++)
        {
            dp[i] = 1;
            for(int j = i-1;j>=0;j--)
            {
                if(arr[j]>arr[i])
                {
                    dp[i] = Math.max(dp[i] , dp[j]+1);
                }
            }
            length = Math.max(dp[i],length);
        }

        System.out.println(length);
        return dp;
    }
    public static int[] LIS_DP_RL(int[] arr)
    {
        int[] dp = new int[arr.length];
        int length = 1;
        for(int i = arr.length-1;i>=0;i--)
        {
            dp[i] = 1;
            for(int j = i+1;j<arr.length;j++)
            {
                if(arr[j]>arr[i])
                {
                    dp[i] = Math.max(dp[i] , dp[j]+1);
                }
            }
            length = Math.max(dp[i],length);
        }

        System.out.println(length);
        return dp;
    }
    public static void LongestBitonicSequence(int[] nums)
    {
        int [] dp1 = LIS_DP_(nums);
        int [] dp2 = LDS_DP_(nums);
        int length = 1;
        for(int i = 0;i<nums.length;i++)
        {
            length = Math.max(length,dp1[i]+dp2[i]-1);
        }
        System.out.println(length);
    }

    public static void LongestBitonicSequenceFollowUP(int[] nums)
    {
        int [] dp1 = LIS_DP_RL(nums);
        int [] dp2 = LDS_DP_LR(nums);
        int length = 1;
        for(int i = 0;i<nums.length;i++)
        {
            length = Math.max(length,dp1[i]+dp2[i]-1);
        }
        System.out.println(length);
    }
    public static void LIS_DP_For_Printing(int[] arr)
    {
        ArrayList<ArrayList<Integer>> mapping = new ArrayList<>();
        int[] dp = new int[arr.length];
        int length = 1;
        for(int i = 0;i<arr.length;i++)
        {
            dp[i] = 1;
            for(int j = i-1;j>=0;j--)
            {
                if(arr[j]<arr[i])
                {
                    dp[i] = Math.max(dp[i] , dp[j]+1);
                }
            }
            length = Math.max(dp[i],length);
        }
        for(int i = 0;i<=length;i++) mapping.add(i,new ArrayList<>());
        for(int i = 0;i<arr.length;i++) mapping.get(dp[i]).add(i);
        for(Integer i : mapping.get(length))
        {
            printAllLIS(mapping, arr, i, length,"");
        }
    }
    public static void printAllLIS(ArrayList<ArrayList<Integer>> mapping,int[] arr,int idx,int len,String ans)
    {
        if(len == 1){
            System.out.println(ans+arr[idx]);
            return;
        }
        for(Integer i :mapping.get(len-1))
        {
            if(i<idx && arr[i]<arr[idx])
            {
                printAllLIS(mapping, arr, i, len-1, ans+arr[idx]+",");
            }
        }
    }
    public int findNumberOfLIS(int[] arr) 
    {
        int n = arr.length;
        int[] dp = new int[n];
        int[] count = new int[n];

        int maxLen = 0;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            } else if (maxLen == dp[i]) {
                maxCount += count[i];
            }
        }

        return maxCount;
    }
    
    public static void main(String[] args)
    {
    //     int[] arr = new int[]{0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14};
    //     int[] arr1 = new int[]{10,20,100,1,2,3,4,5};
    //     int[] arr2 = new int[]{9,29,6};
    //     //LIS_DP(arr);
    //     //LDS_DP(arr);
    //     //LISumSub_DP(arr);
    //     //LongestBitonicSequence(arr2);
    //     //LongestBitonicSequenceFollowUP(arr);
    //     LIS_DP_For_Printing(arr);
    }
}