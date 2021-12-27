class buyAndSell
{
    // 121. Best Time to Buy and Sell Stock

    public int maxProfit(int[] prices) 
    {
        int min = (int)1e9;
        int maxProfit = 0;
        for(int i=0;i<prices.length;i++)
        {
            if(min == (int)1e9 || prices[i]<min){
                min = prices[i];
                continue;
            }
            maxProfit = Math.max(maxProfit,prices[i]-min);
        }
        return maxProfit;
    }

    // 122. Best Time to Buy and Sell Stock II

    // This code clearly handels [0,5,5] & [0,5,7]

    public int maxProfit(int[] prices) 
    {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) 
        {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
            return maxprofit;
    }
        


    // 714. Best Time to Buy and Sell Stock with Transaction Fee

    public int maxProfit(int[] prices, int fee) 
    {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }

    // 123. Best Time to Buy and Sell Stock III

    public int maxProfit(int[] prices) 
    {
        int[]dp1 = new int[prices.length];
        int low = prices[0];
        int max1 = 0;
        for(int i=1;i<prices.length;i++)
        {
            int temp = prices[i] - low;
            max1 = Math.max(max1,temp);
            low = Math.min(low,prices[i]);
            dp1[i] = max1;
        }
        
        int[]dp2 = new int[prices.length];
        int high = prices[prices.length-1];
        int max2 = 0;
        for(int i=prices.length-2;i>=0;i--)
        {
            int temp = high - prices[i];
            max2 = Math.max(max2,temp);
            high = Math.max(high,prices[i]);
            dp2[i] = max2;
        }
        
        int ans = 0;
        for(int i = 0;i<prices.length;i++)
        {
            ans = Math.max(ans , dp1[i] + dp2[i]);
        }
        return ans;
    }

    // 188. Best Time to Buy and Sell Stock IV

    public int maxProfit(int k, int[] arr) 
    {   
        int n = arr.length;
        if(n == 0) return 0; 
        int[][] dp = new int[k + 1][n];

          for(int i = 1; i <= k; i++)
          {
             int fadd = Integer.MIN_VALUE;

             for(int j = 1; j < n; j++){
                if(dp[i - 1][j - 1] - arr[j - 1] > fadd)
                {
                   fadd = dp[i - 1][j - 1] - arr[j - 1];
                }

                if(fadd + arr[j] > dp[i][j - 1])
                {
                   dp[i][j] = fadd + arr[j];
                } else 
                {
                   dp[i][j] = dp[i][j - 1];
                }
             }
          }

      return (dp[k][n - 1]);
    }

    // 309. Best Time to Buy and Sell Stock with Cooldown

    public int maxProfit(int[] arr) 
    {
        int bstp = -arr[0];
      int sstp = 0;
      int cstp = 0;
      for(int i = 1; i < arr.length; i++){
         int nbstp = 0;
         int nsstp = 0;
         int ncstp = 0;

         if(cstp - arr[i] > bstp){
            nbstp = cstp - arr[i];
         } else {
            nbstp = bstp;
         }

         if(bstp + arr[i] > sstp){
            nsstp = bstp + arr[i];
         } else {
            nsstp = sstp;
         }

         if(sstp > cstp){
            ncstp = sstp;
         } else {
            ncstp = cstp;
         }

         bstp = nbstp;
         sstp = nsstp;
         cstp = ncstp;
      }

      return Math.max(sstp, cstp);
    }



}