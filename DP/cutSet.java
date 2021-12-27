import java.util.Arrays;

class cutSet
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
    
    public static int mcm(int [] arr , int i , int j,int[][]dp)
    {
        if(i+1 == j) return dp[i][j] = 0;
        int minVal = (int)1e10;
        for(int cut = i+1;cut<j;cut++)
        {
            minVal = Math.min(minVal , mcm(arr,i,cut,dp) + arr[i]*arr[cut]*arr[j] + mcm(arr,cut,j,dp));
        }
        return dp[i][j] = minVal;
    }
    public static void mcm_dp(int [] arr , int I , int J,int[][]dp)// do this with gap strategy
    {
        for(int i = J ; i >=0 ;i-- )
        {
            for(int j = 0;j<=J;j++)
            {
                if(i+1 == j)
                {
                    dp[i][j] = 0;
                    continue;
                }
                int minVal = (int)1e10;
                for(int cut = i+1;cut<j;cut++)
                {
                    minVal = Math.min(minVal , dp[i][cut] + arr[i]*arr[cut]*arr[j] + dp[cut][j]);
                }
                dp[i][j] = (minVal == (int)1e10) ? 0 : minVal;
            }
        }
        System.out.println(dp[0][arr.length-1]);
    }
    public static void mcm ()
    {
        int[] arr = new int[]{40,20,30,10,30};
        int[][] dp = new int[arr.length][arr.length];
        //System.out.println(mcm(arr,0,arr.length-1,dp));
        mcm_dp(arr,0,arr.length-1,dp);
        print2D(dp);
    }
    public static class pair
    {
        int minVal = (int)1e9;
        int maxVal = -(int)1e9;
        String min = "";
        String max = "";

        pair(){}
        pair(int minVal , int maxVal , String min , String max)
        {
            this.minVal = minVal;
            this.maxVal = maxVal; 
            this.min = min;
            this.max = max;

        }
    }
    public static int evaluate(int i , int j ,  char exp)
    {
        if(exp == '+') return i+j;
        else return i*j;
    }
    public static pair MaxMin(String exp , int i , int j , pair[][] dp)
    {
        if(i == j)
        {
            return dp[i][j] = new pair((exp.charAt(i)-'0'),(exp.charAt(i)-'0'),exp.charAt(i) + "",exp.charAt(i) + "");
        }
        if(dp[i][j]!=null) return dp[i][j]; 
        pair Ans = new pair();
        for(int cut = i;cut<j;cut+=2)
        {
            pair leftAns = MaxMin(exp,i,cut,dp);
            pair rightAns = MaxMin(exp,cut+2,j,dp);
            int min = evaluate(leftAns.minVal,rightAns.minVal , exp.charAt(cut+1));
            int max = evaluate(leftAns.maxVal,rightAns.maxVal , exp.charAt(cut+1));
            if(min < Ans.minVal)
            {
                Ans.minVal = min;
                Ans.min = "(" + leftAns.min + exp.charAt(cut+1) + rightAns.min + ")"; 
            }
            if(max > Ans.maxVal)
            {
                Ans.maxVal = max;
                Ans.max = "(" + leftAns.max + exp.charAt(cut+1) + rightAns.max + ")"; 
            }

        }
        return dp[i][j] = Ans;
    }
    public static void MaxMin()
    {
        String exp = "1+2*3+4*5";
        pair[][] dp = new pair[exp.length()][exp.length()];
        pair Final = MaxMin(exp,0,exp.length()-1,dp);
        System.out.println(Final.maxVal);
        System.out.println(Final.minVal);
        System.out.println(Final.min);
        System.out.println(Final.max);
    }
    public static int[][] longestPalindrome_DP(String s) 
    {
        int n = s.length();
        int[][] dp = new int[n][n];

        int len = 0;
        int si = 0;

        for(int gap = 0;gap<n;gap++)
        {
            for(int i = 0,j = gap;j<n;i++,j++)
            {
                if(gap == 0) dp[i][j] = 1;
                else if(gap == 1) dp[i][j] = (s.charAt(i) == s.charAt(j)) ? 2 : 0;
                else dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1]!=0 ? dp[i+1][j-1]+2 : 0;
                if(dp[i][j] > len)
                {
                    len = dp[i][j];
                    si = i;
                }
            }
        }
        return dp;
    }
    public static void minCut(String s) 
    {
        int[][] subStr = longestPalindrome_DP(s);
        // int[] dp = new int[s.length()];
        // Arrays.fill(dp,-1);
        //System.out.println(minCut(s, 0, subStr, dp));
        //print1D(dp);
        minCut_dp(s, subStr);
    }
    public static int minCut(String s,int i, int[][]subStr , int []dp)
    {
        if(subStr[i][s.length()-1] > 0) return dp[i] = 0;
        if(dp[i]!= -1) return dp[i];
        int minAns = (int)1e9;
        for(int cut = i;cut<s.length();cut++)
        {
            if(subStr[i][cut] > 0)
            {
                minAns = Math.min(minAns , minCut(s,cut+1,subStr,dp)+1);
            }
        } 
        return dp[i] = minAns;
    }
    public static int minCut_dp(String s,int[][]subStr) // only 1d dp is required 
    {
        int[] dp = new int[s.length()];
        for(int i = s.length()-1;i>=0;i--)
        {
            if(subStr[i][s.length()-1] > 0)
            {
                dp[i] = 0;
                continue;
            }
            int minAns = (int)1e9;
            for(int cut = i;cut<s.length();cut++)
            {
                if(subStr[i][cut] > 0)
                {
                    minAns = Math.min(minAns , dp[cut+1]+1);
                }
            } 
            dp[i] = minAns;
        }
        print1D(dp);
        return dp[0];
    }
    public static void burstBaloon()
    {
        int[] arr = new int[]{3,1,5,8};
        int[][] dp = new int[arr.length][arr.length];
        // for(int[] d : dp) Arrays.fill(d,-1);
        // System.out.println(burstBaloon(arr, 0, arr.length-1, dp));
        // print2D(dp);
        burstBaloon_DP(arr, 0, arr.length-1, dp);
    }
    public static int burstBaloon(int[] arr , int i , int j,int[][] dp)
    {
        if(dp[i][j]!=-1) return dp[i][j];
        int max = -(int)1e8;
        int l = (i == 0) ? 1 : arr[i-1];
        int r = (j == arr.length-1) ? 1 : arr[j+1];
        for(int cut = i;cut<=j;cut++)
        {
            int lval =(cut == i)?0:burstBaloon(arr,i,cut-1,dp);
            int rval =(cut == j)?0:burstBaloon(arr,cut+1,j,dp);
            max = Math.max(max , lval + r*arr[cut]*l + rval);
        }
        return dp[i][j] = max;
    }
    public static void burstBaloon_DP(int[] arr , int I , int J,int[][] dp)
    {
        for(int gap = 0;gap<arr.length;gap++)
        {
            for(int i = 0,j = gap;j<=J;i++,j++)
            {
                int l = (i == 0) ? 1 : arr[i-1];
                int r = (j == arr.length-1) ? 1 : arr[j+1];
                for(int cut = i;cut<=j;cut++)
                {
                    int lval =(cut == i)?0:dp[i][cut-1];
                    int rval =(cut == j)?0:dp[cut+1][j];
                    dp[i][j] = Math.max(dp[i][j] , lval + r*arr[cut]*l + rval);
                }
            }
        }

        print2D(dp);
    }
    public static int minScoreTriangulation_memo(int[] value , int i , int j , int[][]dp) 
    {
        if(j-i<=1) return dp[i][j] = 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int minAns = (int)1e8;
        for(int cut = i+1 ; cut<j ; cut++)
        {
            int lans = minScoreTriangulation_memo(value, i, cut, dp);
            int rans = minScoreTriangulation_memo(value, cut, j, dp);
            minAns = Math.min(minAns , lans + value[i]*value[cut]*value[j] + rans);
        }
        return dp[i][j] = minAns;
    }
    public static void minScoreTriangulation_Dp(int[] values)
    {
        int[][] dp = new int[values.length][values.length];
        for(int[]d : dp) Arrays.fill(d,(int)1e8); 
        for(int gap = 0;gap<values.length;gap++)
        {
            for(int i = 0,j = gap;j<values.length;j++,i++)
            {
                if(j-i<=1)
                {
                    dp[i][j] = 0;
                    continue;
                }
                for(int cut = i+1 ; cut<j ; cut++)
                {
                    int lans = dp[i][cut];
                    int rans = dp[cut][j];
                    dp[i][j] = Math.min(dp[i][j] , lans + values[i]*values[cut]*values[j] + rans);
                }
            }
        }
        print2D(dp);
    }
    public static void minScoreTriangulation() 
    {
        int[] arr = new int[]{1,3,1,4,1,5};
        int[][]dp = new int[arr.length][arr.length];
        for(int[] d : dp) Arrays.fill(d,-1);
        System.out.println(minScoreTriangulation_memo(arr, 0, arr.length-1, dp));
        print2D(dp);

    }


    public static int mod = 1003;

    public static class bool
    {
        int tc = 0;
        int fc = 0;
        bool()
        {

        }
        bool(int tc , int fc)
        {
            this.tc = tc;
            this.fc = fc;
        }
    }
    public static bool countWays(int N, String S,int i , int j , bool[][]dp)
    {
        if(i == j)
        {
            char ch = S.charAt(i);
            return new bool(ch == 'T' ? 1:0 , ch == 'F' ? 1 : 0);
        }
        if(dp[i][j]!=null) return dp[i][j];
        bool Final = new bool(0,0);
        for(int cut = i+1;cut<j;cut+=2)
        {
            bool lans = countWays(N, S, i, cut-1, dp);
            bool rans = countWays(N, S, cut+1, j, dp);
            bool temp = evaluate(lans, rans, S.charAt(cut));
            Final.tc = (Final.tc + temp.tc)%mod;
            Final.fc = (Final.fc + temp.fc)%mod;
        }

        return dp[i][j] = Final;
    }
    public static bool evaluate(bool left , bool right , char ope)
    {
        int totalWays = ((right.tc + right.fc)%mod)*((left.tc + left.fc)%mod)%mod;
        bool ans = new bool(0,0);
        if(ope == '&')
        {
            int t = (right.tc * left.tc)%mod;
            int f = (totalWays - t + mod)%mod;
            ans.tc = t;
            ans.fc = f;
        }
        else if(ope == '|')
        {
            int f = (right.fc*left.fc)%mod;
            int t = (totalWays - f + mod)%mod;
            ans.tc = t;
            ans.fc = f;
        }
        else if(ope == '^')
        {
            int t = ((right.tc * left.fc)%mod + (right.fc*left.tc)%mod)%mod;
            int f = (totalWays - t + mod)%mod;
            ans.tc = t;
            ans.fc = f;
        }

    return ans;
    }
    public static void countWays()
    {
        String S = "T^F|F";
        bool[][] dp = new bool[S.length()][S.length()];
        countWays(S.length(), S, 0, S.length()-1, dp);
        System.out.println(dp[0][S.length()-1].tc);
    }
    public static int[][] minChanges(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j) ? 0 : 1;
                else
                    dp[i][j] = str.charAt(i) != str.charAt(j) ? dp[i + 1][j - 1] + 1 : dp[i + 1][j - 1];
            }
        }
        return dp;
    }

    public static int palindromePartition_(String str, int k, int si, int[][] dp, int[][] minChange) {
        if (str.length() - si <= k) {
            return dp[si][k] = (str.length() - si == k) ? 0 : (int) 1e9;
        }

        if (k == 1)
            return dp[si][k] = minChange[si][str.length() - 1];
        if (dp[si][k] != -1)
            return dp[si][k];

        int minAns = (int) 1e9;
        for (int i = si; i < str.length() - 1; i++) {
            int minChangesInMySet = minChange[si][i];
            int minChangesInRecSet = palindromePartition_(str, k - 1, i + 1, dp, minChange);
            if (minChangesInRecSet != (int) 1e9)
                minAns = Math.min(minAns, minChangesInRecSet + minChangesInMySet);
        }

        return dp[si][k] = minAns;
    }

    public static int palindromePartition(String str, int k) {
        int[][] minChangeDP = minChanges(str);
        int[][] dp = new int[str.length()][k + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return palindromePartition_(str, k, 0, dp, minChangeDP);
    }

    // 241. Different Ways to Add Parentheses

    public List<Integer> diffWaysToCompute(String expression) 
    {
        return calculate(expression,0,expression.length()-1);
    }
    
    public List<Integer> calculate(String exp , int si , int ei)
    {
        if((ei == si) || (ei == si+1))
        {
            List<Integer> temp = new ArrayList<>();
            String p = exp.substring(si,ei+1);
            int val = Integer.parseInt(p);
            temp.add(val);
            return temp;
        }
        
        List<Integer> ret = new ArrayList<>();
        
        for(int cut = si+1;cut<ei;cut++)
        {
            char op = exp.charAt(cut);
            if(op == '*' || op == '+' || op == '-')
            {
                List<Integer> left = calculate(exp,si,cut-1);
                List<Integer> right = calculate(exp,cut+1,ei);
                                
                if(op == '*')
                {
                    for(int ele : left)
                    {
                        for(int ele2 : right)
                        {
                            ret.add(ele * ele2);
                        }
                    }
                }
                else if(op == '+')
                {
                    for(int ele : left)
                    {
                        for(int ele2 : right)
                        {
                            ret.add(ele + ele2);
                        }
                    }
                }
                else if(op == '-')
                {
                    for(int ele : left)
                    {
                        for(int ele2 : right)
                        {
                            ret.add(ele - ele2);
                        }
                    }
                }
            }
        }
        
        return ret;
    }

    public static void main(String[] args)
    {
        //mcm();
        //MaxMin();
        //System.out.println(longestPalindrome_DP("jai"));
        //minCut("ppabccbadd");
        //burstBaloon();
        //minScoreTriangulation();
        //minScoreTriangulation_Dp(new int[]{1,3,1,4,1,5});
        //countWays();
    }
}