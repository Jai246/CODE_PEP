import java.util.Arrays;

class subsequenceXtra
{
    // wildcard matching dp solution
    public boolean isMatch(String str, String pattern) 
    {
        boolean[][] dp = new boolean[pattern.length() + 1][str.length() + 1];
		for(int i = dp.length - 1; i >= 0 ;i--) {
			for(int j = dp[0].length - 1; j >= 0; j--) {
				if(i == dp.length - 1 && j == dp[0].length - 1) {
					dp[i][j] = true;
				}else if(i == dp.length - 1) {
					dp[i][j] = false;
				}else if(j == dp[0].length - 1) {
					if(pattern.charAt(i) == '*') {
						dp[i][j] = dp[i + 1][j];
					}
				}else {
					if(pattern.charAt(i) == '?') {
						dp[i][j] = dp[i + 1][j + 1];
					}else if(pattern.charAt(i) == '*') {
						dp[i][j] = dp[i][j + 1] || dp[i + 1][j];
					}else if(pattern.charAt(i) == str.charAt(j)) {
						dp[i][j] = dp[i + 1][j + 1];
					}else {
						dp[i][j] = false;
					}
				}
			}    
        }
        
        for(int i = 0;i<dp.length;i++)
        {
            for(int j = 0;j<dp[0].length;j++)
            {
                if(dp[i][j]) System.out.print("1" + " ");
                else System.out.print("0" + " ");
            }
            System.out.println();
        }
            
        return dp[0][0];
    }

    // count palindromic subsequences dp solution

    long countPS(String str)
    {
        long mod = (int)1e9 + 7;
        long[][] dp = new long[str.length()][str.length()];
        for(int g = 0; g < dp.length; g++){
            for(int i = 0, j = g; j < dp[0].length; i++, j++){
                if(g == 0){
                    dp[i][j] = 1;
                } else if(g == 1){
                    dp[i][j] = str.charAt(i) == str.charAt(j)? 3: 2;
                } else {
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] + 1);
                    } else {
                        dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]);
                    }
                }
                
                dp[i][j]+=mod;
                dp[i][j]%=mod;
            }
        }
        return (dp[0][dp[0].length - 1]);
    }

    // count distinct palindromic subsequences
    public int countPalindromicSubsequences(String str) {
        int[] pre = new int[str.length()];
		HashMap<Character, Integer> map = new HashMap<>();
		int mod = 1000000007;
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(map.containsKey(ch))
				pre[i] = map.get(ch);
			else
				pre[i] = -1;
			map.put(ch, i);
		}
		
		int[] next = new int[str.length()];
		map = new HashMap<>();
		for(int i = str.length() - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			if(map.containsKey(ch))
				next[i] = map.get(ch);
			else
				next[i] = Integer.MAX_VALUE;
			map.put(ch, i);
		}
		
		int[][] dp = new int[str.length()][str.length()];
		
		for(int g = 0; g < dp.length; g++) {
			for(int i = 0, j = g; j < dp[0].length; i++, j++) {
				if(g == 0)
					dp[i][j] = 1;
				else if(g == 1)
					dp[i][j] = 2;
				else {
					if(str.charAt(i) == str.charAt(j)) {
						int n = next[i];
						int p = pre[j];
						
						if(n > p)
							dp[i][j] = ((2 * dp[i+1][j-1]) + 2) % mod;
						else if(n == p)
							dp[i][j] = ((2 * dp[i+1][j-1]) + 1) % mod;
						else
							dp[i][j] = ((2 * dp[i+1][j-1]) - dp[n+1][p-1]) % mod;
					}else
						dp[i][j] = (dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1]) % mod;
				}
                if(dp[i][j] < 0)
                    dp[i][j] += mod;
			}
		}
		return dp[0][dp[0].length - 1] % mod;
    }

    // count distinct subsequences
    public static int distinctSubseqII(String S)
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

        return dp[S.length()-1]-1;
    }

    // count palindromic substring
    public int countSubstrings(String str) 
    {
        boolean[][] dp = new boolean[str.length()][str.length()];
        int count = 0;
        for(int g = 0; g < dp.length; g++){
            for(int i = 0, j = g; j < dp[0].length; i++, j++){
                if(g == 0){
                    dp[i][j] = true;
                } else if(g == 1){
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }

                if(dp[i][j]){
                    count++;
                }
            }
        }
        return (count);
    }
    
    

}