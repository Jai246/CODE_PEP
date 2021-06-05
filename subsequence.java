import java.util.Arrays;
class subsequence{
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
    public static void longestPalindromeSubseq(String s) 
    {
        int[][] dp = new int[s.length()][s.length()];
        // for(int[] d : dp)
        // {
        //     Arrays.fill(d,-1);
        // }
        //longestPalindromeSubseq(s,dp,0,s.length()-1);
        longestPalindromeSubseq_DP(s,dp);
        //print2D(dp);
        System.out.println(printSubseq(s, dp, 0, s.length()-1));

    }

    public static int longestPalindromeSubseq(String s , int[][] dp,int i , int j)
    {
        if(i >= j) return dp[i][j] = (i == j) ?  1 : 0; 
        if(dp[i][j] != 0) return dp[i][j];
        int length = 0;
        if(s.charAt(i) == s.charAt(j)) length = longestPalindromeSubseq(s,dp,i+1,j-1) + 2;
        else length = Math.max(longestPalindromeSubseq(s,dp,i,j-1),longestPalindromeSubseq(s,dp,i+1,j));
        return dp[i][j] = length;
    }
    public static void longestPalindromeSubseq_DP(String s , int[][] dp){
        int n = s.length();
        for(int gap = 0 ; gap < n ; gap++){
            for(int i = 0,j = gap ;j < n ; i++ , j++){   
                if(i == j){
                    dp[i][j]  = (i == j) ? 1 : 0;
                    continue;
                }
                
                if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i+1][j-1] + 2;
                else dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
    }
    public static String printSubseq(String s ,int[][] dp , int i , int j)
    {
        if(s.length() == 1) return s;
        if(dp[i][j] == 0) return "";
        if(i+1<s.length() && j-1>=0 && dp[i][j-1] == 0 || dp[i+1][j] == 0) return s.charAt(i) + "";
        String t = "";
        if(i+1<s.length() && j-1>=0 && s.charAt(i) == s.charAt(j))
        { 
            t = printSubseq(s,dp,i+1,j-1);
            return "" + s.charAt(i) + t + s.charAt(j);
        }
        else if(i+1 < s.length() && j-1>=0 && dp[i][j-1] >= dp[i+1][j]) return  printSubseq(s,dp,i,j-1);
        else if(i+1 < s.length() && j-1>=0 && dp[i][j-1] < dp[i+1][j]) return  printSubseq(s,dp,i+1,j);
        return t;
    }

    
    public static int numDistinct(String s, String t,int[][] dp , int i , int j) 
    {
        if(i == 0 || j == 0)
        {
            if(j == 0) return dp[i][j] = 1;
            else if(i == 0 && j!=0) return dp[i][j] = 0;
        }
        if(dp[i][j] !=-1) return dp[i][j];
        int count = 0;

        if(s.charAt(i-1) == t.charAt(j-1)) 
        {
            count = numDistinct(s, t, dp, i-1, j-1) + numDistinct(s, t, dp, i-1, j);
        }
        else
        {
            count = numDistinct(s, t, dp, i-1, j);
        }

        return dp[i][j] = count;
    }
    public static int numDistinct_DP(String s, String t,int[][] dp)
    {
        int n = s.length();
        int m = t.length();
        for(int i = 0;i<n+1;i++)
        {
            for(int j = 0;j<m+1;j++)
            {
                if(i == 0 || j == 0)
                {
                    if(j == 0) dp[i][j] = 1;
                    else if(i == 0 && j!=0) dp[i][j] = 0;
                    continue;
                }
                if(s.charAt(i-1) == t.charAt(j-1)) 
                {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }
                else
                {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][m];
    }
    public static void longestCommonSubsequence(String text1, String text2) 
    {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int[] d : dp)
        {
            Arrays.fill(d,-1);
        }
        //longestCommonSubsequence_DP(text1, text2, dp); 
        longestCommonSubsequence(text1, text2, dp, text1.length(), text2.length());
        print2D(dp);
    }
    public static int longestCommonSubsequence(String s, String t,int[][] dp , int i , int j) 
    {
        if(i == 0 || j == 0) return dp[i][j] = 0;
        if(dp[i][j] != -1) return dp[i][j];
        int temp = 0;
        if(s.charAt(i-1) == t.charAt(j-1))
        {
            temp = longestCommonSubsequence(s,t,dp,i-1,j-1) + 1;
        }
        else
        {
            temp  = Math.max(longestCommonSubsequence(s, t, dp,i-1,j),longestCommonSubsequence(s, t, dp,i,j-1));
        }
        return dp[i][j] = temp;
    }
    public static int longestCommonSubsequence_DP(String s, String t,int[][] dp) 
    {
        int n = s.length();
        int m = t.length();
        for(int i = 0;i<n+1;i++)
        {
            for(int j = 0;j<m+1;j++)
            {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if(s.charAt(i-1) == t.charAt(j-1))
                {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else
                {
                    dp[i][j]  = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    public static int maxUncrossedLines(int[] A, int[] B) 
    {
        int[][] dp = new int[A.length+1][B.length+1];
        return maxUncrossedLines_DP(A,B,dp);
    }
    public static int maxUncrossedLines_DP(int[]s , int[]t , int[][] dp)
    {
        int n = s.length;
        int m = t.length;
        for(int i = 0;i<n+1;i++)
        {
            for(int j = 0;j<m+1;j++)
            {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if(s[i-1] == t[j-1])
                {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else
                {
                    dp[i][j]  = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[s.length][t.length];
    }
    public static int maxDotProduct(int[] nums1, int[] nums2) 
    {
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for(int []d : dp)
        {
            Arrays.fill(d,-(int)1e8);
        }
        return maxDotProduct_memo(nums1,nums2 ,dp ,nums1.length ,nums2.length);
    }
    public static int maxDotProduct_memo(int[] nums1, int[] nums2 , int[][]dp , int i , int j){
        
        if(i == 0 || j == 0) dp[i][j] = -(int)1e7;        
        
        if(dp[i][j] != -(int)1e8) return dp[i][j];
        
        int val = nums1[i-1] * nums2[j-1];
        int acceptBoth = maxDotProduct_memo(nums1,nums2,dp,i-1,j-1) + val; // important
        int ans1 = maxDotProduct_memo(nums1,nums2,dp,i,j-1);
        int ans2 = maxDotProduct_memo(nums1,nums2,dp,i-1,j);
        int ans = Math.max(Math.max(ans1,ans2),(Math.max(acceptBoth,val)));
        
        return dp[i][j] = ans;
      
    }
    public static int maxDotProduct_DP(int[] nums1, int[] nums2 , int[][]dp){        
        for(int i = 0;i<nums1.length+1;i++)
        {
            for(int j = 0;j<nums2.length+1;j++)
            {
                if(i == 0 || j == 0) {
                    dp[i][j] = -(int)1e7;
                    continue;
                }
                int val = nums1[i-1] * nums2[j-1];
                int acceptBoth = dp[i-1][j-1] + val; // important
                int ans1 = dp[i][j-1];
                int ans2 = dp[i-1][j];
                dp[i][j] = Math.max(Math.max(ans1,ans2),(Math.max(acceptBoth,val)));
            }
        }
        return dp[nums1.length][nums2.length];
    }

    public static void minDistance(String word1, String word2) 
    {   
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int[] d : dp)
        {
            Arrays.fill(d,-1);
        }
        minDistance_memo(word1,word2,dp,word1.length(),word2.length());
        System.out.println(dp[word1.length()][word2.length()]);
        print2D(dp);
    }
    public static int minDistance_memo(String word1, String word2 , int[][] dp , int i , int j) 
    {   
        if(i == 0 || j == 0)
        {
            dp[i][j] = (i == 0) ? j : i;
        }
        if(dp[i][j] != -1) return dp[i][j];
        if(i-1>=0 && j-1>=0 && word1.charAt(i-1) == word2.charAt(j-1)) return dp[i][j] = minDistance_memo(word1,word2,dp,i-1,j-1);
        int insert = minDistance_memo(word1,word2,dp,i,j-1)+1;
        int delete = minDistance_memo(word1,word2,dp,i-1,j)+1;
        int replace = minDistance_memo(word1,word2,dp,i-1,j-1)+1;
        
        return dp[i][j] = Math.min(insert,Math.min(delete,replace));
    }

    public static int minDistance_DP(String word1, String word2 , int[][] dp) 
    {   
        for(int i = 0;i<word1.length()+1;i++)
        {
            for(int j = 0;j<word2.length()+1;j++)
            {
                if(i == 0 || j == 0)
                {
                    dp[i][j] = (i == 0) ? j : i;
                    continue;
                }
                int insert = dp[i][j-1];
                int delete = dp[i-1][j];
                int replace = dp[i-1][j-1];
                if(i-1>=0 && j-1>=0 && word1.charAt(i-1) == word2.charAt(j-1))
                {
                    dp[i][j] = replace;
                    continue;
                }
                dp[i][j] = Math.min(insert,Math.min(delete,replace))+1;
            }
        }
        
        return dp[word1.length()][word2.length()];
    }

    public static boolean isMatch(String s, String p) 
    {
        int[][] dp = new int[s.length()+1][p.length()+1];
        return dp[s.length()][p.length()] == 1 ? true : false;
    }

    public static int isMatch_memo(String s, String p,int[][] dp , int i , int j) 
    {
        if(i == 0 && j == 0) return dp[i][j] = 1;
        boolean res = false;
        if(s.charAt(i-1) == p.charAt(j-1) || s.charAt(j-1) == '?') res = res || isMatch_memo(s, p, dp, i-1, j-1) == 1;
        
        if(s.charAt(j-1) == '*')
        {
            for(int n = i;n>=0;n--) 
            {
                if(!res) res = res || isMatch_memo(s, p, dp, n-1, j-1) == 1;
            }
        }

        return dp[i][j] = res ? 1 : 0;
    }
    public static String convert(String s)
    {
        if(s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        int i = 1;
        while(i<s.length())
        {
            while(i<s.length() && sb.charAt(sb.length()-1) == '*' && s.charAt(i) == '*') i++;
            if(i<s.length()) sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }
    public static void isMatch_(String s, String p) 
    {
        String temp = convert(p);
        System.out.println(temp);
        int[][] dp = new int[s.length()+1][temp.length()+1];
        for(int[] d : dp) Arrays.fill(d,-1);
        isMatch_memo_(s,temp,dp,s.length(),temp.length());
        
        System.out.println(dp[s.length()][temp.length()] == 1 ? true : false);
        print2D(dp);
    }
    public static int isMatch_memo_(String s, String p,int[][] dp , int i , int j) 
    {
        if(i>=0 && j==1 && p.charAt(j-1) == '*') return dp[i][j] = 1;
        if(i == 0 && j == 0) return dp[i][j] = 1;
        if(dp[i][j] != -1) return dp[i][j];
        boolean res = false;
        if(i>=1 && j>=1 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?')) res = res || isMatch_memo_(s, p, dp, i-1, j-1) == 1;
        
        if(j>=1 && p.charAt(j-1) == '*' && !res)
        {

            for(int n = 0;n<s.length();n++) 
            {
                if(!res && i-n>=0 && j-1>=0) res = res || isMatch_memo_(s, p, dp, i-n, j-1) == 1;
            }
        }

        return dp[i][j] = res ? 1 : 0;
    }
    public int mod = (int)1e9+7;
    public long countPS(String str)
    {
        int n = str.length();
        long[][] dp = new long[n][n];
        for(long[] d : dp) Arrays.fill(d,-1);
        return countPS_memo(str,0,n-1,dp);

    }
    public long countPS_memo(String str,int i,int j,long[][] dp)
    {
        if(i>=j) return dp[i][j] = (i == j) ? 1:0;
        
        if(dp[i][j]!=-1) return dp[i][j];

        long a = countPS_memo(str,i+1,j,dp);
        long b = countPS_memo(str, i, j-1, dp);
        long c = countPS_memo(str, i+1, j-1, dp);

        return dp[i][j] = ((str.charAt(i) != str.charAt(j)) ? a+b-c + mod :a+b+1) % mod;

    }

    public String longestPalindrome(String s) 
    {
        int[][] dp = new int[s.length()][s.length()];
        return longestPalindromeSubseq_DP_(s,dp);
       
    }
    public static String longestPalindromeSubseq_DP_(String str , int[][] dp){
        int n = str.length();
        //int count = 0;
        int si = 0;
        int len = 0;
        for(int gap = 0 ; gap < n ; gap++){
            for(int i = 0,j = gap ;j < n ; i++ , j++){   
                if(gap == 0)
                {
                    dp[i][j] = 1;
                } 
                else if(gap == 1) dp[i][j] = str.charAt(i) == str.charAt(j) ? 2:0;
                else dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i+1][j-1] !=0 ? dp[i+1][j-1]+2:0;
                
                if(dp[i][j] > len)
                {
                    len = dp[i][j];
                    si = i;
                }
                //if(dp[i][j] != 0) count++;
            }
        }
        
        return str.substring(si,si+len);
    }
    public static int buildingBridges(int[][] arr)
    {
        Arrays.sort(arr,(a,b)->
        {
            return a[0] - b[0];
        });// by default (this - other) Ascending Order;

        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for(int i = 0;i<n;i++)
        {
            dp[i] = 1;
            for(int j = i-1;j>=0;j--)
            {
                if(arr[j][0]<arr[i][0] && arr[j][1] < arr[i][1])
                {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            len = Math.max(len,dp[i]);
        }
        return len;
    }
    public static void distinctSubseqII(String S) // Dp Solution
    {
        S = '$'+S;
        int[] dp = new int[S.length()];
        int[] lastOcc = new int[26];
        Arrays.fill(lastOcc,-1);
        int mod = (int)1e9 + 7;
        dp[0] = 1;
        for(int i = 1;i<S.length();i++)
        {
            char ch = S.charAt(i);
            dp[i] = (dp[i-1]*2)%mod;
            if(lastOcc[ch-'a']!=-1)
            {
                dp[i] = (dp[i] - dp[lastOcc[ch-'a']-1] + mod)%mod;
            }
            lastOcc[ch-'a'] = i;
        }

        System.out.println(dp[S.length()-1] - 1); // coz we dont want empty string
    }
    public static void main(String[] args)
    {
        // //longestPalindromeSubseq("a");
        // //numDistinct("geksfgeks", "gks");
        // //longestCommonSubsequence("abcde", "ace");
        // //minDistance("sunday", "saturday");
        // isMatch_("adceb","*a*b");
        distinctSubseqII("abc");
    }

}