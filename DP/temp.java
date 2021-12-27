public static void mcm(int[] arr , int si , int ei , int[][]dp)
{
	if(dp[si][ei] != -1 ) return dp[si][ei];
	if(si+1 == ei) return dp[si][ei] = 0;
	int minCost = (int)1e9;
	for(int cut = si+1 ; cut<ei ; cut++)
	{
		int leftAns = mcm(arr,si,cut,dp);
		int rightAns = mcm(arr,cut,ei,dp);
		minCost = Math.min(minCost , leftAns + arr[cut]*arr[si]*arr[ei] +rightAns);
	}
	return dp[si][ei] = minCost;
}

class pair{
	int min = 0;
	int max = 0;
	String minExp = "";
	String maxExp = "";
	pair(){

	}
	pair(int min , int max , String minExp , String maxExp){
		this.min = min;
		this.max = max;
		this.minExp = minExp;
		this.maxExp = maxExp;
	}
}

public static pair minMax(String exp)
{
	pair dp[][] = new pair[exp.length()][exp.length()];
	return calculateMinMax(exp,dp,0,exp.length()-1);
}

public static pair evaluate(pair leftAns , pair rightAns , pair temp , int cut , String s)
{
	if(s.charAt(cut) == "*")
	{
		int newMin = Math.min(temp.min , leftAns.min * rightAns.min);
		int newMax = Math.max(temp.max , leftAns.max * rightAns.max);

		String newMinExp = "(" + leftAns.minExp + "*" + rightAns.minExp + ")";
		String newMaxExp = "(" + leftAns.maxExp + "*" + rightAns.maxExp + ")";

		if(temp.min > newMin)
		{
			temp.minExp = newMinExp;
		}
		if(temp.max < newMax)
		{
			temp.maxExp = newMaxExp;
		}
 	}
 	else if(s.charAt(cut) == "+")
	{
		int newMin = Math.min(temp.min , leftAns.min * rightAns.min);
		int newMax = Math.max(temp.max , leftAns.max * rightAns.max);

		String newMinExp = "(" + leftAns.minExp + "+" + rightAns.minExp + ")";
		String newMaxExp = "(" + leftAns.maxExp + "+" + rightAns.maxExp + ")";

		if(temp.min > newMin)
		{
			temp.minExp = newMinExp;
		}
		if(temp.max < newMax)
		{
			temp.maxExp = newMaxExp;
		}
 	}
}
public static pair calculateMinMax(String s , pair[][]dp , int si , int ei)
{
	if(dp[si][ei]!=null) return dp[si][ei];
	if(si == ei)
	{
		int val = Integer.parseInt(s.charAt(si));
		return new pair(val,val,""+val,""+val);
	}
	pair temp = new pair();
	for(int cut = si+1;cut<ei;cut+=2)
	{
		pair leftAns = calculateMinMax(s,dp,si,cut-1);
		pair rightAns = calculateMinMax(s,dp,cut+1,ei);
		evaluate(leftAns,rightAns,temp,cut,s);
	}
	return dp[si][ei] = temp;
}


public static boolean[][]dp PalindromicSubstrings(String str)
{

	boolean[][] dp = new boolean[str.length()][str.length()];
	for(int gap = 0;gap<str.length();gap++)
	{
		for(int j = gap,i = 0;j<str.length;i++,j++)
		{
			if(gap == 0){
				dp[i][j] = true;
				continue;
			}
			if(gap == 1)
			{
				dp[i][j] = (str.charAt(i) == str.charAt(j)) ? true : false;
			}

			if(s.charAt(i) == s.charAt(j))
			{
				if(dp[i+1][j-1])
				{
					dp[i][j] = true;
				}
			}
		}
	}
	return dp;
}

public static void PalindromePart2_calling(String str)
{
	boolean[][]check = PalindromicSubstrings(str);
	int[] dp = new int[str.length()][str.length()];
	Arrays.fill(dp,-1);
	PalindromePart2(str,check,dp,0,str.length);
}
public static int PalindromePart2(String str , boolean[][]check , int[]dp , int si , int ei)
{
	if(check[si][ei-1]) return dp[si] = 0;
	//if(dp[si]!=-1) return dp[si];
	if(si == ei) return 0;
	int count = (int)1e9;
	for(int cut = si;cut<=ei;cut++)
	{
		if(check[si][cut])
		{
			count = Math.min(count , PalindromePart2(str,check,dp,cut+1,ei)+1);
		}
	}
	dp[si] = count;
}


public int maxCoins(int[] nums)
{
	int[][] dp = new int[nums.length][nums.length];
	for(int[]ele : dp) Arrays.fill(dp,-1);
	return calculateMax(nnums,0,nums.length-1,dp);
}
public int calculateMax(int[] nums , int si , int ei , int[][]dp)
{
	if(si>ei) return 0;
	if(dp[si][ei]!=-1) return dp[si][ei];
	int lval = (si-1 >= 0) ? nums[si-1] : 1;
	int rval = (ei+1 < nums.length) ? nums[ei+1] : 1;
	int maxVal = -(int)1e9;
	for(int cut = si;cut<=ei;cut++)
	{
		int leftAns = calculateMax(nums,si,cut-1,dp);
		int rightAns = calculateMax(nums,cut+1,ei,dp);
		maxVal = Math.max(maxVal , leftAns +lval*nums[cut]*rval+ rightAns);
	}
	return dp[si][ei] = maxVal;
}


public static class pair{
	int trueWays = 0;
	int falseWays = 0;
	pair(){

	}
	pair(int trueWays , int falseWays){
		this.trueWays = trueWays;
		this.falseWays = falseWays;
	}
}

static int countWays(int N, String S)
{
   pair[][] dp = new pair[N][N];
   pair temp = count(N,s,dp,0,N-1);
   return temp.trueWays;
}

public static pair evaluateExp(pair left , pair right , String s , int idx)
{
	char op = s.charAt(idx);
	int totalWays = (left.trueWays + left.falseWays)*(right.trueWays + right.falseWays);
	if(op == '&')
	{
		int tWays = left.trueWays*right.trueWays;
		int fWays = total - tWays;
		return new pair(tWays,fWays);
	}
	if(op == '|')
	{
		int fWays = left.falseWays*right.falseWays;
		int tWays = total - tWays;
		return new pair(tWays,fWays);
	}
	if(op == '^')
	{
		int tWays = (left.trueWays*right.falseWays) + (left.falseWays*right.trueWays);
		int fWays = total - tWays;
		return new pair(tWays,fWays);
	}
}
public static count(int n , String s , pair[][]dp , int si , int ei)
{
	if(si == ei)
	{
		char val = s.charAt(si);
		return dp[si][ei] (val == 'T') ? pair(1,0) : pair(0,1);
	}
	if(dp[si][ei]!=null) return dp[si][ei];
	pair ans = new pair(0,0);
	for(int cut = si+1;cut<ei;cut+=2)
	{
		pair lans = count(n,s,dp,si,cut-1);
		pair rans = count(n,s,dp,cut+1,ei);
		pair temp = evaluateExp(lans,rans,s,cut);
		ans.trueWays += temp.trueWays;
		ans.falseWays += temp.falseWays;
	}
	return dp[si][ei] = ans;
}