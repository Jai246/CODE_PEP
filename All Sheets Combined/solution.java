class solution
{
	// Minimize the Heights I 
	// Very Importnt Problem
	// Kind Off Ratne Wala Problem
	// Consider      ....a..........x (adjacent elements) y..........b....   this array
	// Adjacent Elements will have the minimum difference after sorting
	// min would be (y-k,a+k) && max would be (x+k,b-k); think over this
	// because they can cross over each other on the number line
	int getMinDiff(int[] arr, int n, int k) 
    {
       if(n==1)
       {
           return 0;
       }
       Arrays.sort(arr);
       int diff = arr[n-1] - arr[0];
       int minimum,maximum;
       for(int i=1;i<n;i++)
       {
           maximum = Math.max(arr[i-1]+k, arr[n-1]-k);
           minimum = Math.min(arr[0]+k, arr[i]-k);
           diff = Math.min(diff,maximum-minimum);
       }
       return diff;
   }


   // 55. Jump Game
   // Dp Solution

    public boolean canJump(int[] nums) 
    {
        boolean[] dp = new boolean[nums.length];
        dp[nums.length-1] = true;
        
        for(int i = nums.length-2;i>=0;i--)
        {
            int jump = nums[i];
            for(int j = 1;j<=jump && j+i<nums.length;j++)
            {
                if(dp[i+j])
                {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    // Linear Time solution

    public boolean canJump(int []A) 
    {
        int n = A.length;
        int last=n-1,i,j;
        for(i=n-2;i>=0;i--)
        {
            if(i+A[i]>=last) last=i;
        }
        return last<=0;
    }


    // 31. Next Permutation
    // Sorting Solution

    public void nextPermutation(int[] nums) 
    {
        int l = nums.length;
        if (l <= 1) return;
        
        int left = l-2, right = l-1;
        while (left >= 0) 
        {
            if (nums[left] >= nums[left+1]) left--; 
            else break; 
        }
        
        if (left < 0) Arrays.sort(nums); 
        else 
        {
            while(nums[right] <= nums[left]) right--; 
            swap(nums, left, right);
            Arrays.sort(nums, left+1, l);
        }
    }
    
    public void swap(int[] nums, int i, int j) 
    {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    // Linear Time Solution
    // Actually there is no need to sort
    // Here , We are actually swapping with the right most greater element

    // https://leetcode.com/problems/next-permutation/discuss/13994/Readable-code-without-confusing-ij-and-with-explanation
    // Check Picture Here

    public void nextPermutation(int[] nums) 
    {
       int pivot = indexOfLastPeak(nums) - 1;
       if (pivot != -1) 
       {
           int nextPrefix = lastIndexOfGreater(nums, nums[pivot]); 
           swap(nums, pivot, nextPrefix);
       }
       reverseSuffix(nums, pivot + 1);
    }
    public int indexOfLastPeak(int[] nums) 
    {
        for (int i = nums.length - 1; 0 < i; --i) 
        {
            if (nums[i - 1] < nums[i]) return i;
        }
        return 0;
    }

    public int lastIndexOfGreater(int[] nums, int threshold) 
    {
        for (int i = nums.length - 1; 0 <= i; --i) 
        {
            if (threshold < nums[i]) return i;
        }
        return -1;
    }

    public void reverseSuffix(int[] nums, int start) 
    {
        int end = nums.length - 1;
        while (start < end) 
        {
            swap(nums, start++, end--);
        }
    }
    
    public void swap(int[] nums, int i, int j) 
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    // Common elements in 3 Arrays'

    ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2, int n3) 
    {
        ArrayList<Integer> ans = new ArrayList<>();
        
        int i = 0;
        int j = 0;
        int k = 0;
        
        
        while(i < A.length && j < B.length && k < C.length)
        {
            int v1 = A[i];
            int v2 = B[j];
            int v3 = C[k];
            
            if(v1 == v2 && v2 == v3)
            {
                ans.add(v1);
                i++;
                while(i-1 >= 0 && i < A.length && A[i] == A[i-1])i++;
                j++;
                while(j-1 >= 0 && j < B.length && B[j] == B[j-1])j++;
                k++;
                while(k-1 >= 0 && k < C.length && C[k] == C[k-1])k++;
                
                if(i == A.length || j == B.length || k == C.length) break;
            }
            else if(v1<=v2 && v1<=v3)
            {
                i++;
            }
            else if(v2<=v1 && v2<=v3)
            {
                j++;
            }
            else if(v3<=v2 && v3<=v1)
            {
                k++;
            }
        }
        return ans;
    }


    // Array Subset of another array 
    public String isSubset( long a1[], long a2[], long n, long m) 
    {
        HashMap<Long,Long> map1 = new HashMap<>();
        HashMap<Long,Long> map2 = new HashMap<>();
        
        for(long ele : a1)
        {
            map1.put(ele,map1.getOrDefault(ele,0L)+1);
        }
        
        for(long ele : a2)
        {
            map2.put(ele,map2.getOrDefault(ele,0L)+1);
        }
        
        for(long ele : map2.keySet())
        {
            if(!map1.containsKey(ele)) return "No";
            else
            {
                if(map1.get(ele) < map2.get(ele)) return "No";
            }
        }
        return "Yes";
    }


    // Smallest subarray with sum greater than x 
    public static int smallestSubWithSum(int a[], int n, int x) 
    {
        int i = 0;
        int j = 0;
        int sum = 0;
        int len = (int)1e9;
        while(j < n)
        {
            int val = a[j];
            sum += val;
            
            while(sum > x)
            {
                len = Math.min(len,j-i+1);
                sum = sum - a[i];
                i++;
            }
            j++;
        }
        return len;
    }


    // Three way partitioning 
    public void threeWayPartition(int arr[], int a, int b)
    {
        int i = 0;
        int prev = -1;
        int j = arr.length;
        
        while(i < j)
        {
            int val = arr[i];
            
            if(val < a)
            {
                prev++;
                int temp = arr[prev];
                arr[prev] = arr[i];
                arr[i] = temp;
                i++;
            }
            else if(val > b)
            {
                j--;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            else if(val >=a && val <=b)
            {
                i++;
            }
        }
    }


    // Minimum swaps and K together 
    // Imp Solution
    // Same as Minimum Swaps required to group all 1’s together

    public static int minSwap (int arr[], int n, int k) 
    {
        int count = 0;
        for(int ele : arr) if(ele<=k) count++;
        int i = 0;
        int j = 0;
        int c = 0;
        int max = 0;
        
        while(j < n)
        {
            int ele = arr[j];
            if(ele <= k) c++;
            
            if(j-i+1 == count)
            {
                max = Math.max(max,c);
                if(arr[i]<=k) c--;
                i++;
            }
            j++;
        }
        return count-max;
    }


    // Median in a row-wise sorted Matrix
    // Important Question
    public int floor(int[][]arr , int val , int r)
    {
        int pos = -1;
        int i = 0;
        int j = arr[0].length-1;
        
        while(i<=j)
        {
            int m = (i+j)/2;
            if(arr[r][m]<=val)
            {
                pos = m;
                i = m+1;
            }
            else j = m-1;
        }
        if(pos == -1) return 0;
        return pos+1;
    }
    
    public int median(int matrix[][], int r, int c) 
    {
        int low = 0;
        int high = 2001;
        int desired = ((r*c)+1)/2;
        while(low < high)
        {
            int place = 0;
            int mid = (low+high)/2;
            for(int i = 0;i<r;i++) place += floor(matrix,mid,i);
            if(place >= desired) high = mid;
            else low = mid+1;
        }
        return low;
    }


    // Find A Specific Pair In Matrix

    public static int findMaxValue(int mat[][], int n)
    {
        int[][] max = new int[n][n];
        for(int i = n-1;i>=0;i--){
            for(int j = n-1;j>=0;j--){
                int right = ((j+1) < n) ? max[i][j+1] : -(int)1e9;
                int down = ((i+1) < n) ? max[i+1][j] : -(int)1e9;
                max[i][j] = Math.max(mat[i][j],Math.max(right,down));
            }
        }
        int ans = -(int)1e9;
        for(int i = n-2;i>=0;i--){
            for(int j = n-2;j>=0;j--) ans = Math.max(ans,max[i+1][j+1]-mat[i][j]);
        }
        return ans;
    }


    // 378. Kth Smallest Element in a Sorted Matrix
    // Adding the whole first column in the priotity queue
    public int kthSmallest(int[][] matrix, int k) 
    {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;

        int n = matrix.length;
        int m = matrix[0].length;
        int idx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return matrix[a/m][a%m] - matrix[b/m][b%m];
        });

        for(int i=0;i<n;i++) pq.add(i * m );

        while(k-- > 0)
        {
            idx = pq.remove();
            int r = idx / m;
            int c = idx % m;
            c++;
            if(c < m) pq.add( r * m + c);
        }

        int r = idx / m;
        int c = idx % m;

        return matrix[r][c];
    }


    // Write a Program to check whether a string is a valid shuffle of two strings or not
    // Using Sorting 

    static boolean isShuffledSubString(String A, String B)
    {
        int n = A.length();
        int m = B.length();

        if (n > m)
        {
            return false;
        }
        else
        {
            A = sort(A);
     
            for (int i = 0; i < m; i++)
            {
                if (i + n - 1 >= m) return false;
                String str = "";
                for (int j = 0; j < n; j++) str += B.charAt(i + j);
     
                str = sort(str);
                if (str.equals(A)) return true;
            }
        }
        return false;
    }

    // 38. Count and Say
    // Simple Solution 
    // Confusing Problem Statememt
    // Just Previous wali string koo bolna hei thats ittt


    public String countAndSay(int n) 
    {
        String s = "1";
        for(int i = 1; i < n; i++){
            s = countIdx(s);
        }
        return s;
    }
    
    public String countIdx(String s)
    {
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for(int i = 1; i < s.length(); i++)
        {
            if(s.charAt(i) == c)
            {
                count++;
            }
            else
            {
                sb.append(count);
                sb.append(c);
                c = s.charAt(i);
                count = 1;
            }
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }

    // 696. Count Binary Substrings

    // Simple Solution
    // It is simple because there is no need to have take to pointers
    // because we only need previous count 

    public int countBinarySubstrings(String s)
    {
        int ans = 0, prev = 0, cur = 1;
        for (int i = 1; i < s.length(); i++) 
        {
            if (s.charAt(i-1) != s.charAt(i)) 
            {
                ans += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } 
            else 
            {
                cur++;
            }
        }
        return ans + Math.min(prev, cur);
    }


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


    // Next greater number set digits 
    // Almost same as Next lexicographical string
    public int findNext (int n)
    {
        String str = n+"";
        int[] nums = new int[str.length()];
        for(int i = 0;i<nums.length;i++){
            nums[i] = Integer.parseInt(str.charAt(i)+"");
        }
        nextPermutation(nums);
        String ans = "";
        for(int ele : nums) ans += ele;
        int val = Integer.parseInt(ans);
        return (val <= n) ? -1 : val;
    }
    
    public void nextPermutation(int[] nums) 
    {
       int pivot = indexOfLastPeak(nums) - 1;
       if (pivot != -1) 
       {
           int nextPrefix = lastIndexOfGreater(nums, nums[pivot]); 
           swap(nums, pivot, nextPrefix);
       }
       reverseSuffix(nums, pivot + 1);
    }
    public int indexOfLastPeak(int[] nums) 
    {
        for (int i = nums.length - 1; 0 < i; --i) 
        {
            if (nums[i - 1] < nums[i]) return i;
        }
        return 0;
    }

    public int lastIndexOfGreater(int[] nums, int threshold) 
    {
        for (int i = nums.length - 1; 0 <= i; --i) 
        {
            if (threshold < nums[i]) return i;
        }
        return -1;
    }

    public void reverseSuffix(int[] nums, int start) 
    {
        int end = nums.length - 1;
        while (start < end) 
        {
            swap(nums, start++, end--);
        }
    }
    
    public void swap(int[] nums, int i, int j) 
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Convert a sentence into its equivalent mobile numeric keypad sequence 
    String printSequence(String S) 
    { 
       StringBuilder ans=new StringBuilder();
       HashMap<Character,Integer>hm=new HashMap<>();
       hm.put('A',2);
       hm.put('B',22);
       hm.put('C',222);
       hm.put('D',3);
       hm.put('E',33);
       hm.put('F',333);
       hm.put('G',4);
       hm.put('H',44);
       hm.put('I',444);
       hm.put('J',5);
       hm.put('K',55);
       hm.put('L',555);
       hm.put('M',6);
       hm.put('N',66);
       hm.put('O',666);
       hm.put('P',7);
       hm.put('Q',77);
       hm.put('R',777);
       hm.put('S',7777);
       hm.put('T',8);
       hm.put('U',88);
       hm.put('V',888);
       hm.put('W',9);
       hm.put('X',99);
       hm.put('Y',999);
       hm.put('Z',9999);
       hm.put(' ',0);
       for(char ch:S.toCharArray())
       {
           ans.append(hm.get(ch));
       }
       return ans.toString();
    }


    // Count the Reversals 
    int countRev (String s)
    {
        int len = s.length();
        if(len%2!=0) return -1;
        int open = 0;
        int close = 0;
        
        for(int i = 0;i<len;i++)
        {
            char ch = s.charAt(i);
            
            if(ch == '{') open++;
            else if(ch == '}')
            {
                if(open > 0) open--;
                else close++;
            }
        }
        int count = 0;
        count+=open/2;
        count+=close/2;
        count += (open%2) + (close%2);
        return count;
    }


    // Count occurences of a given word in a 2-d array 
    // Simple Recursive Solution

   public int findOccurrence(char mat[][], String target)
   {
       int count=0;
        for(int i=0;i<mat.length;i++)
        {
          for(int j=0;j<mat[0].length;j++)
          {
               count += helper(target,i,j,mat,mat.length-1,mat[0].length-1,0);
          }
        }
        return count;
   }
  public static int  helper(String target,int i,int j,char [][] mat,int row,int col,int idx)
  {
      int count = 0;
      if(i >= 0 && i <= row && j >= 0 && j <= col && target.charAt(idx) == mat[i][j])
      {
          char temp = target.charAt(idx) ;
          idx += 1;
          mat[i][j] = 0;
          if(idx==target.length()) count = 1;
          else
          {
              count += helper(target,i,j+1,mat,row,col,idx);
              count += helper(target,i,j-1,mat,row,col,idx);
              count += helper(target,i+1,j,mat,row,col,idx);
              count += helper(target,i-1,j,mat,row,col,idx);
          }
          
          mat[i][j] = temp;
      }
      return count;
    }


    // 14. Longest Common Prefix
    // Very Nice Solution
    // Sorting Made the things very easy

    public String longestCommonPrefix(String[] strs) 
     {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int c = 0;
        while(c < first.length())
        {
            if (first.charAt(c) == last.charAt(c)) c++;
            else break;
        }
        return c == 0 ? "" : first.substring(0, c);
    }

    // 1888. Minimum Number of Flips to Make the Binary String Alternating

    // Very important Approach Solution
    // if any problem check neetcode's video
    // so here  instead of taking each character manualy again ang again at the end and checking
    // We are pasting the whole string at the end and applying sliding window algorithm
    // Same Question on Geeks For Geeks
    public int minFlips(String s) 
    {
        int n = s.length();
        int mininumFlip = Integer.MAX_VALUE;
        int misMatchCount = 0;
        
        for(int i = 0; i < (2 * n); i++)
        {    
            int r = i % n;
            // aquiring the new character
            if((s.charAt(r) - '0') != (i % 2 == 0 ? 1 : 0)) misMatchCount++;
            // Releasing the previous charater 
            if(i >= n && (s.charAt(r) - '0') != (r % 2 == 0 ? 1 : 0)) misMatchCount--;
            // taking in account the minimum flip
            // 0101010101 if minFilp == k here
            // 1010101010 the here i will be n-k always
            if(i >= n - 1) mininumFlip = Math.min(mininumFlip, Math.min(misMatchCount, n - misMatchCount));
        }
        return mininumFlip;
    }

    // Second most repeated string in a sequence 
    String secFrequent(String arr[], int N)
    {
       Map<String, Integer> mp = new HashMap<>();
       for(int i=0;i<arr.length;i++)
       {
           if(mp.containsKey(arr[i])) mp.put(arr[i], mp.get(arr[i])+1);
           else mp.put(arr[i],1);
       }
       Map<Integer, String> mp1 = new HashMap<>();
       for(Map.Entry<String, Integer> e : mp.entrySet())
       {
           mp1.put(e.getValue(), e.getKey());
       }
       ArrayList<Integer> al = new ArrayList<>(mp1.keySet());
       Collections.sort(al);
       
       for(Map.Entry<Integer, String> e : mp1.entrySet())
       {
           if(e.getKey()==al.get(al.size()-2)) return e.getValue();
       }
       return "";
   }


   // 1963. Minimum Number of Swaps to Make the String Balanced
   // Note that open and close brackets are present in equal quantity
   // After removing the balanced out brackette string we will get equal no of ] and [ facing in opposit d*r^n
   public int minSwaps(String s) 
    {
        int len = s.length();
        if(len%2!=0) return -1;
        int open = 0;
        int close = 0;
        
        for(int i = 0;i<len;i++)
        {
            char ch = s.charAt(i);
            
            if(ch == '[') open++;
            else if(ch == ']')
            {
                if(open > 0) open--;
                else close++;
            }
        }
        return (int)(Math.ceil(open/2.0));
    }


    // 93. Restore IP Addresses

    public List<String> restoreIpAddresses(String s) 
    {
        List<String> addresses = new ArrayList<>();
        if (s.length() > 12 || s.length() == 0) return addresses;
        backtracking(addresses, new ArrayList<String>(), s, 0);
        return addresses;
    }

    private void backtracking(List<String> addresses, List<String> temp, String s, int start) 
    {
        if (start == s.length() && temp.size() == 4) 
        {
            addresses.add(String.join(".", temp));
            return;
        }

        // each number is between 0 and 255
        // which is 1 digit to 3 digit
        // so we have three different choices for each number
        // 1. substring from start to start+1
        // 2. substring from start to start+2
        // 3. substring from start to start+3
        for (int i = 1; i <= 3; i++) 
        {
            if (start + i > s.length()) return;
            String address = s.substring(start, start + i);
            // check the address validation
            if (validAddress(address)) 
            {
                // add valid num into List
                // start a new backtracking
                temp.add(address);
                backtracking(addresses, temp, s, start + i);
                temp.remove(temp.size() - 1);

            }
        }
    }

    // To valid the given address
    // each integer is between 0 and 255 and can not start with 0
    private boolean validAddress(String address) 
    {
        return !((address.charAt(0) == '0' && address.length() > 1) || Integer.parseInt(address) > 255);
    }


    // 1047. Remove All Adjacent Duplicates In String

    public String removeDuplicates(String s) 
    {
        StringBuilder sb = new StringBuilder();        
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(sb.length() == 0 || sb.charAt(sb.length()-1) != ch) sb.append(ch);
            else if(sb.charAt(sb.length()-1) == ch) sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    // Transform String 
    // Bhai Jai Please oolta chalna seekhle
    // Important Question
    // Since Peeche Wala oothke aage Jayenge
    // Matlab agar last ke do character match nahi kar rahe then 100% voh character aaga gaya hei A mei se
    int transform (String A, String B)
    {
        if (A.length() != B.length()) return -1;
 
        int i, j, res = 0;
        int count[] = new int[256];

        for (i = 0; i < A.length(); i++) 
        {
            count[A.charAt(i)]++;
            count[B.charAt(i)]--;
        }
        
        // Checking ki dono mei equal character and oonke count hei ki nahi
        for (i = 0; i < 256; i++)
        {
            if (count[i] != 0) return -1;
        }
            
 
        i = A.length() - 1;
        j = B.length() - 1;
 
        while (i >= 0) 
        {
            if (A.charAt(i) != B.charAt(j)) res++;
            else j--;
            i--;
        }
        return res;
    }


    // 69. Sqrt(x)
    // We can simply do this using binary search
    // root(n) == x  ==>>    n == square(x);

    public int mySqrt(int x) 
    {
        if (x == 0) return 0;
        int start = 1, end = x;
        int ans = 0;
        while (start <= end) 
        { 
            int mid = start + (end - start) / 2;
            if (mid > x / mid) end = mid-1;
            else 
            {
                start = mid + 1;
                ans = mid;
            }
        }
        return ans;
    }


    // Find Missing And Repeating 
    // We did kind of same question earlier in which we mark negative
    // Use inx zero to mark N **IMP**

    int[] findTwoElement(int arr[], int n) 
    {
        int[] ans = new int[2];
        
        for(int i = 0;i<n;i++)
        {
            int idx = (Math.abs(arr[i]) == n) ? 0 : Math.abs(arr[i]);
            if(arr[idx] < 0) ans[0] = Math.abs(arr[i]);
            else arr[idx] = -1*arr[idx];
        }
        
        for(int i = 0;i<n;i++)
        {
            if(arr[i] > 0)
            {
               ans[1] = (i == 0) ? n : i;
               break;
            }
        }
        return ans;
    }


    // Count triplets with sum smaller than X 

    long countTriplets(long arr[], int n,int sum)
    {
        long count = 0;
        Arrays.sort(arr);
        
        for(int i = n-1;i>=2;i--)
        {
            long v1 = arr[i];
            int x = 0;
            int y = i-1;
            
            while(x < y)
            {
                long v2 = arr[x];
                long v3 = arr[y];
                
                if(v1+v2+v3 < sum)
                {
                    count+=y-x;
                    x++;
                }
                else y--;
            }
        }
        return count;
    }


    // 1356. Sort Integers by The Number of 1 Bits
    public int getSetBits(int a)
    {
        int count = 0;
        while(a > 0)
        {
            count++;
            int v = a&(-a);
            a = a-v;
        }
        return count;
    }
    public int[] sortByBits(int[] arr) 
    {
        int[][] temp = new int[arr.length][2];
        
        for(int i = 0;i<arr.length;i++)
        {
            int val = arr[i];
            temp[i][0] = val;
            temp[i][1] = getSetBits(val);
        }
        
        Arrays.sort(temp,(a,b)->{
           if(a[1]!=b[1]) return a[1] - b[1];
            else return a[0]-b[0];
        });
        
        int[] ans = new int[arr.length];
        for(int i = 0;i<arr.length;i++)
        {
            int val = temp[i][0];
            ans[i] = val;
        }
        return ans;
    }


    // Kth smallest element 
    public static int kthSmallest(int[] arr, int l, int r, int k) 
    {
       Arrays.sort(arr);
    
       k = k - 1;
    
       return arr[k];
    }


    // K-th element of two sorted Arrays 
    // Important

    public static long kthElement( int arr1[], int arr2[], int n, int m, int k)
    {
        if(m < n) return kthElement(arr2,arr1,m,n,k);
        
        int low = Math.max(-1,k-m-1), high = Math.min(k-1,n-1);
        while(low <= high)
        {
            int mid1 = (low+high)/2;
            int mid2 = k-2-mid1;


            int x1 = -1;
            if(mid1 == -1) x1 = -(int)1e9;
            else x1 = arr1[mid1];
            int x2 = -1;
            if(mid1 == arr1.length-1) x2 = (int)1e9;
            else x2 = arr1[mid1+1];
            
            int y1 = -1;
            if(mid2 == -1) y1 = -(int)1e9;
            else  y1 = arr2[mid2];
            int y2 = -1;
            if(mid2 == arr2.length-1) y2 = (int)1e9;
            else y2 = arr2[mid2+1];
            
            if(y1<=x2 && x1<=y2) return Math.max(x1,y1);
            else if(y1 > x2) low = mid1+1;
            else high = mid1-1;
        }
        return -1;
    }


    // Aggressive Cows
    // 1552. Magnetic Force Between Two Balls

    // Available on SPOJ only

    // Question
    // Given an array of length ‘N’, where each element denotes the position of a stall. 
    // Now you have ‘N’ stalls and an integer ‘K’ which denotes the number of cows that are aggressive. 
    // To prevent the cows from hurting each other, you need to assign the cows to the stalls, 
    // such that the minimum distance between any two of them is as large as possible. 
    // Return the largest minimum distance.
    // Eg

    // array: 1,2,4,8,9  &  k=3
    // O/P: 3
    // Explaination: 1st cow at stall 1 , 2nd cow at stall 4 and 3rd cow at stall 8


    public static boolean isPossible(int a[], int n, int cows, int minDist) 
    {
        int cntCows = 1;
        int lastPlacedCow = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] - lastPlacedCow >= minDist) {
                cntCows++;
                lastPlacedCow = a[i];
            }
        }
        if (cntCows >= cows) return true;
        return false;
    }
    public static void main(String args[]) 
    {
        int n = 5, cows = 3;
        int a[]={1,2,8,4,9};
        Arrays.sort(a);

        int low = 1, high = a[n - 1] - a[0];

        while (low <= high) 
        {
            int mid = (low + high) >> 1;

            if (isPossible(a, n, cows, mid)) 
            {
                low = mid + 1;
            } 
            else 
            {
                high = mid - 1;
            }
        }
        System.out.println("The largest minimum distance is " + high);
    }


    // Job Sequencing Problem 
    // Can check abdul bari video

    public int[] JobScheduling(Job arr[], int n)
    {
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        Arrays.sort(arr,(a,b)->{
            return b.profit-a.profit;
        });
        int sum = 0;
        int count = 0;
        for(int i = 0;i<arr.length;i++)
        {
            for(int j = Math.min(arr[i].deadline-1,n-1);j>=0;j--)
            {
                if(ans[j] == -1)
                {
                    ans[j] = arr[i].profit;
                    count++;
                    sum+=ans[j];
                    break;
                }
            }
        }
        return new int[]{count,sum};
    }


    // Find the missing number in Arithmetic Progression
    // Important
    // Using Binary Search log(n)
    // Not Available to submit
    // Mujhe largest correct section mei jaana hei
    // jiske end tak saare elements present hooo starting from arr[0];
    public static int findMissingUtil(int arr[], int low,int high, int diff)
    {   
        while (low <= high)
        {   
            int mid = (low + high) / 2;
            if ((arr[mid] - arr[0]) / diff == mid) low = mid + 1;
            else high = mid - 1;
        }
        return arr[high] + diff;
    }

    public static int findMissing(int arr[], int n)
    {
        int diff = (arr[n - 1] - arr[0]) / n;
        return findMissingUtil(arr, 0, n - 1, diff);
    }


    // Smallest number with at least n trailing zeroes in factorial

    // Trailing 0s in x! = Count of 5s in prime factors of x!
    // = floor(x/5) + floor(x/25) + floor(x/125) + ....
    // We can simply use this formulae to compute number of 0's
    // If we observe, a trailing zero is produced by the multiplication of prime factors 2 and 5. Hence,
    // we need at least one occurrence of 2 and 5 in the prime factorization of factorial to get a trailing zero.


    // We can use this observation to find the pattern. Let’s see a few examples:
    // 0! (= 1) has no trailing zeros. Also, all the numbers from 0 to 4 have no trailing zeros, 
    // due to the absence of prime factors 2 and 5.
    // 5! (= 120) has one trailing zero. All the numbers from 5 to 9 have 1 trailing zero, 
    // due to the presence of prime factors 2 and 5, where 5 occurs only once.
    // Similarly, the factorial of numbers 10 to 14 have 2 trailing zeros.
    // Factorial of numbers 20 to 24 have 4 trailing zeros.
    // Factorial of numbers 25 to 29 have 6 trailing zeros 
    // (one extra because 25 has two fives in its prime factorization), and so on.
    // From this, we can observe that the minimum value containing at least N trailing zeros is always 
    // less than or equal to 5*N.
    // So we can apply a binary search in the range 0 to 5*N to find the smallest number.
    // For every iteration in binary search, we need to check if the current number has at least 
    // N trailing zeros in its factorial. This can be found using the formula:
    // Trailing 0s in X! = Number of 5s in the prime factorization of X!
    // i.e Trailing 0s in X! = floor(X / 5) + floor(X / 25) + floor(X / 125) + .......
    // The above formuala is to calculate directly the no of trailing zeros in a number
    // without calculating the factorial

    // Trailing 0s in X! = floor(X / 5) + floor(X / 25) + floor(X / 125) + .......

    // 5!  has 1 trailing zeroes 
    // [All numbers from 6 to 9
    //  have 1 trailing zero]

    // 10! has 2 trailing zeroes
    // [All numbers from 11 to 14 have 2 trailing zeroes]

    // 15! to 19! have 3 trailing zeroes

    // 20! to 24! have 4 trailing zeroes

    // 25! to 29! have 6 trailing zeroes

    public static int countTrailingZeros(int x)
    {
        int count = 0;
        int div = 5;

        while (div <= x) 
        {
            count += (x / div);
            div = div * 5;
        }
        return count;
    }

    public static int findNum(int n) 
    {
        int low = 0, high = 5 * n;
        while (low < high) 
        {
            int mid = low + (high - low) / 2;
            int count = countTrailingZeros(mid);
            if (count >= n) high = mid;
            else low = mid + 1;
        }
        return high;
    }


    // The painter’s partition problem
    // Submitted on codingNinjas
    public static int findLargestMinDistance(ArrayList<Integer> boards, int k)
    {
        return paint(k,1,boards);
    }
    public static boolean check(ArrayList<Integer>a , int A , int B , long time)
    {
        long t = 0;
        int c = 1;
        for(int i = 0;i<a.size();i++)
        {
            int ele = a.get(i);
            if(ele*B > time) return false;
            t += (ele*B);
            
            if(t > time)
            {
                c++;
                t = ele*B;
            }
        }
        if(c > A) return false;
        return true;
    }
    public static int paint(int A, int B, ArrayList<Integer> C) 
    {
        long low = 0;
        long high = (int)1e9;
        long ans = -1;
        while(low < high)
        {
            long mid = (low+high)/2;
            
            boolean c = check(C,A,B,mid);
            if(c) 
            {
                high = mid;
            }
            else low = mid+1;
        }
        return (int)(low);
    }



    // Partitioning and Sorting Arrays with Many Repeated Entries

    // A Basic Sorting algorithm like MergeSort, 
    // HeapSort would take O(nLogn) time where n is number of elements, can we do better?
    // A Better Solution is to use Self-Balancing Binary Search Tree like AVL or 
    // Red-Black to sort in O(n Log m) time where m is number of distinct elements.
    // The idea is to extend tree node to have count of keys also. 



    // Remove duplicates from an unsorted linked list 
    // Simple HashMap Approach
    public Node removeDuplicates(Node head) 
    {
        Node temp = head;
        Node prev = null;
        HashSet<Integer> hashSet = new HashSet<>();
        while (temp!=null)
        {
             if(hashSet.contains(temp.data))
             {
                prev.next = temp.next;
             }
             else 
             {
                hashSet.add(temp.data);
                prev = temp;
             }
            temp = temp.next;
        }
        return head;
    }


    // Intersection of two sorted Linked lists 
    public static Node findIntersection(Node head1, Node head2)
    {
        Node par = new Node(-1);
        Node last = par;
        
        Node t1 = head1;
        Node t2 = head2;
        
        while(t1!=null && t2!=null)
        {
            if(t1.data == t2.data)
            {
                last.next = new Node(t1.data);
                last = last.next;
                t1 = t1.next;
                t2 = t2.next;
            }
            else if(t1.data < t2.data) t1 = t1.next;
            else t2 = t2.next;
        }
        return par.next;
    }

    // Merge Sort for Linked List 

    public static int length(Node head)
    {
        int count = 0;
        while(head!=null)
        {
            count++;
            head = head.next;
        }
        return count;
    }
    
    public static Node mergeTwoLists(Node l1,Node l2)
    {
        Node dummy = new Node(-1);
        Node prev = dummy;
        
        while(l1 != null && l2 != null)
        {
            int v1 = l1.data;
            int v2 = l2.data;
            
            if(v1 <= v2)
            {
                prev.next = l1;
                prev = prev.next;
                l1 = l1.next;
            }
            else 
            {
                prev.next = l2;
                prev = prev.next;
                l2 = l2.next;
            }
        }
        
        if(l1 != null) prev.next = l1;
        else if(l2!=null) prev.next = l2;
        return dummy.next;
    }
    public static Node sort(Node head)
    {
        int len = length(head);
        if(len == 1)
        {
            return head;
        }
        int mid = (len+1)/2;
        
        Node head2 = head;
        Node prev = null;
        int count  = 0;
        while(count < mid)
        {
            prev = head2;
            head2 = head2.next;
            count++;
        }
        prev.next = null;
        
        return mergeTwoLists(sort(head),sort(head2));
        
    }
    public static Node mergeSort(Node head)
    {
        return sort(head);
    }


    // Quick Sort on Linked List 
    // In this QuickSort What we studied for Array QuickSort from pepcoding 
    // will Not work here because its a singly linked list and we can't move 
    // backwards so in order to acomplish quick sort here we have to make pivot
    // the starting element and not the ending


    public static Node partition(Node node)
    {
        Node t = node.next;
        Node pivot = node;
        Node start = node;
        
        while(t!=null)
        {
            if(t.data<pivot.data)
            {
                start = start.next;
                int tmp = t.data;
                t.data = start.data;
                start.data = tmp;
            }
            t=t.next;
        }
        int tmp = start.data;
        start.data = pivot.data;
        pivot.data = tmp;
        
        return start;
    }
    public static Node quickSort(Node node)
    {
        if(node==null || node.next==null) return node;
        
        Node part = partition(node);
        Node tmp = part.next;
        part.next = null;
        quickSort(node);
        quickSort(tmp);
        part.next = tmp;
        return node;
    }


    // Finding middle element in a linked list 
    // Hare And Tortoise Approach

    int getMiddle(Node head)
    {
         Node slow = head;
         Node fast = head;
         
         while(fast!=null && fast.next != null)
         {
             slow = slow.next;
             fast = fast.next.next;
         }
         return slow.data;
    }


    // Split a Circular Linked List into two halves 
    // Not able to submit on GFG due to some driver code error
    // When corrected do check this code

    void splitList(circular_LinkedList list)
    {
        circular_LinkedList temp = list;
        circular_LinkedList slow = list;
        circular_LinkedList fast = list;
        circular_LinkedList prev = list;
        boolean start = false;
        while(fast!=temp && fast.next!=temp)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(start == false)
            {
                start = true;
                prev = prev.next;
            }
            else prev = prev.next.next;
        }
        circular_LinkedList head2 = slow.next;
        prev.next = slow.next;
        slow.next = temp;
        
        boolean check = false;
        
        while(list!=list && !check)
        {
            if(!check) check = true;
            if(list.next == list) System.out.print(list.data);
            else System.out.print(list.data + " ");
            list = list.next;
        }
        
        System.out.println();
        
        check = false;
        
        while(head2!=head2 && !check)
        {
            if(!check) check = true;
            if(head2.next == head1) System.out.print(head2.data);
            else System.out.print(head2.data + " ");
            head2 = head2.next;
        }
        
    }

    // Given a linked list of 0s, 1s and 2s, sort it. 
    static Node segregate(Node head)
    {
        Node dummy1 = new Node(-1);
        Node l1 = dummy1;
        Node dummy2 = new Node(-1);
        Node l2 = dummy2;
        
        
        Node d = new Node(-1);
        d.next = head;
        
        while(head!=null)
        {
            int val = head.data;
            Node temp = head;
            head = head.next;
            temp.next = null;
            d.next = head;
            
            if(val == 0)
            {
                l1.next = temp;
                l1 = l1.next;
            }
            if(val == 1)
            {
                if(l2.data == -1)
                {
                    temp.next = dummy2.next;
                    dummy2.next = temp;
                    l2 = l2.next;
                }
                else 
                {
                    temp.next = dummy2.next;
                    dummy2.next = temp;
                }
            }
            if(val == 2)
            {
                l2.next = temp;
                l2 = l2.next;
            }
        }
        
        
        if(dummy1.next == null) return dummy2.next;
        l1.next = dummy2.next;
        return dummy1.next;
    }


    // Delete nodes having greater value on right 
    Node reverseList(Node head)
    {
        Node prev = null;
        while(head!=null)
        {
            Node forw = head.next;
            head.next = prev;
            prev = head;
            head = forw;
        }
        return prev;
    }
    Node compute(Node head)
    {
        if(head == null) return head;
        head = reverseList(head);
        int min = -1;
        Node temp = new Node(-1);
        Node a = temp;
        while(head!=null)
        {
            if(head.data >= min)
            {
                Node forw = head.next;
                min = head.data;
                a.next = head;
                head.next = null;
                head = forw;
                a = a.next;
            }
            else
            {
                Node forw = head.next;
                head.next = null;
                head = forw;
            }
        }
        return reverseList(temp.next);
    }


    // 1019. Next Greater Node In Linked List
    // Similar to next greater element 2

        public int[] nextLargerNodes(ListNode head) 
    {
       ArrayList<Integer> a = new ArrayList<>();
       ListNode current = head;
        while(current != null)
        {
            a.add(current.val);
            current = current.next;
        }
        int res[] = new int[a.size()];
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i<a.size(); i++)
        {
            while(!s.isEmpty() && a.get(i) > a.get(s.peek())) res[s.pop()] = a.get(i);
            s.push(i);
        }
        return res;
    }


    // Delete nodes having greater value on right 
    // Please OOLTA chalna seekh loo
    // Dimag Mei daal loo
    // Bhool Jate Ho Baar Baar
    Node reverseList(Node head)
    {
        Node prev = null;
        while(head!=null)
        {
            Node forw = head.next;
            head.next = prev;
            prev = head;
            head = forw;
        }
        return prev;
    }
    Node compute(Node head)
    {
        if(head == null) return head;
        head = reverseList(head);
        int min = -1;
        Node temp = head;
        Node prev = null;
        while(head!=null)
        {
            if(head.data >= min)
            {
                min = head.data;
                prev = head;
                head = head.next;
            }
            else
            {
                Node forw = head.next;
                head.next = null;
                head = forw;
                prev.next = forw;
            }
        }
        return reverseList(temp);
    }


    // Activity Selection 
    // This Will Give Maximum number because 
    // We are starting with the minimum ending point
     public static class pair
    {
        int s;
        int e;
        pair(int s , int e)
        {
            this.s=s;
            this.e=e;
        }
    }
    public static int activitySelection(int start[], int end[], int n)
    {
        pair[] arr = new pair[n];
        for(int i = 0;i<n;i++){
            arr[i] = new pair(start[i],end[i]);
        }
        Arrays.sort(arr,(a,b)->{
           return a.e-b.e; 
        });
        
        int ans = 1;
        pair curr = arr[0];
        
        for(int i = 1;i<n;i++)
        {
            if(arr[i].s > curr.e)
            {
                ans++;
                curr = arr[i];
            }
        }
        return ans;
    }

    // Water Connection Problem 
    public static class pair
    {
        int v;
        int w;
        pair(int v , int w)
        {
            this.v = v;
            this.w = w;
        }
    }
    ArrayList<ArrayList<Integer>> solve(int n, int p, ArrayList<Integer> a ,ArrayList<Integer> b ,ArrayList<Integer> d) 
    { 
        int[] in = new int[n+1];
        // int[] out = new int[n+1];
        pair[]graph = new pair[n+1];
        boolean[] vis = new boolean[n+1];
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i=0;i<p;i++)
        {
            // out[a.get(i)]++;
            in[b.get(i)]++;
            graph[a.get(i)] = new pair(b.get(i),d.get(i));
        }
        for(int i = 1;i<=n;i++)
        {
            int val = in[i];
            if(val == 0)
            {
                int[] min = new int[]{(int)1e9};
                dfs(graph,i,i,vis,ans,min);
            }
        }
        return ans;
    }
    
    public void dfs(pair[]graph , int start, int u , boolean[] vis , ArrayList<ArrayList<Integer>> ans , int[] min)
    {
        vis[u] = true;
        if(graph[u] == null && min[0] !=(int)1e9 )
        {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(start);
            temp.add(u);
            temp.add(min[0]);
            ans.add(temp);
            return;
        }
        if(graph[u]!=null && !vis[graph[u].v])
        {
            min[0] = Math.min(min[0],graph[u].w);
            dfs(graph,start,graph[u].v,vis,ans,min);
        }
        return;
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

    // Maximum trains for which stoppage can be provided
    // Similar to Activity Selection Problem


    // Shop in Candy Store 
    // Buggy Driver File
    // Try To Submit Afterwords

    static ArrayList<Integer> candyStore(int candies[],int N,int K)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        Arrays.sort(candies);
        int min_amount = 0;
        int max_amount = 0;
        int i = 0,end = N-1;
        while(i<=end)
        {
            min_amount+=candies[i++];
            end-=K;
        }
        i = 0;
        end = N-1;
        while(i<=end)
        {
            max_amount+=candies[end--];
            i+=K;
        }
        ans.add(min_amount);
        ans.add(max_amount);
        for(int ele : ans) System.out.println(ele);
        return ans;
    }


    // Check if it is possible to survive on Island 
    // My Solution
    // Important
    static int minimumDays(int S, int N, int M)
    {
        int sunday = S%7;
        int buy = S-sunday;
        int tillSundayReq = M*7;
        if(S >= 7 && N*6 < tillSundayReq) return -1;
        if(N < M) return -1;
        int totalReq = S*M;
        int days = (int)Math.ceil(totalReq/(N*1.0));
        return days;
    }

    // N meetings in one room 
    // Exactly Same as Activity Selection

    public static class pair
    {
        int s;
        int e;
        pair(int s , int e)
        {
            this.s=s;
            this.e=e;
        }
    }
    public static int maxMeetings(int start[], int end[], int n)
    {
        pair[] arr = new pair[n];
        for(int i = 0;i<n;i++){
            arr[i] = new pair(start[i],end[i]);
        }
        Arrays.sort(arr,(a,b)->{
           return a.e-b.e; 
        });
        
        int ans = 1;
        pair curr = arr[0];
        
        for(int i = 1;i<n;i++)
        {
            if(arr[i].s > curr.e)
            {
                ans++;
                curr = arr[i];
            }
        }
        return ans;
    }

    // Maximum product subset of an array 
    // Simple
    // Just have to take care of some cases
    // Can me done in O(n) as well

    public static int findMaxProduct(int n, int[] arr) 
    {
        if(n == 1) return arr[0];
        Arrays.sort(arr);
        int x = 0;
        int y = n-1;
        while(x < y) 
        {
            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
            x++;
            y--;
        }
        long mod = (int)1e9 + 7;
        
        long ans1 = 1;
        long ans2 = 1;
        int negCount = 0;
        boolean isOne = false;
        int i = 0;
        while(i < n)
        {
            if(arr[i] == 1) isOne = true;
            if(arr[i] > 0)
            {
                ans1 = (ans1*arr[i])%mod;
                i++;
            }
            else if(arr[i] == 0) i++;
            else if(arr[i] < 0) break;
        }
        if(ans1 == 1 && !isOne) ans1 = 0;
        int j = n-1;
        negCount = j-i+1;
        if(negCount%2!=0) negCount--;
        if(negCount == 0) ans2 = 0;
        while(negCount > 0)
        {
            ans2 = (ans2*arr[j])%mod;
            negCount--;
            j--;
        }
        if(ans1 == 0 && ans2 !=0) return (int)(ans2%mod);
        if(ans1 != 0 && ans2 ==0) return (int)(ans1%mod);
        return (int)((ans1*ans2)%mod);
    }


    // Maximize sum after K negations 
    // Note that we can edit a single element multiple times
    public static long maximizeSum(long a[], int n, int k)
    {
        long sum = 0;
        Arrays.sort(a);
        for(int i = 0;i<a.length;i++)
        {
            if(k > 0){
                if(a[i] < 0){
                    a[i] = -1*a[i];
                    k--;
                }
            }
        }
        Arrays.sort(a);
        for(long ele:a) sum+=ele;
        if(k == 0 || k%2 == 0) return sum;
        else return sum-(2*a[0]);
    }

    // Maximum sum difference 
    // For N == 8
    // 3 7 1 8 2 6 4
    // Jitna Maximum element beech mei hoga 
    // aur sabse chote element ooske aas paas toh sabse badi value aayegi
    // Isko Karne Kaa sabse chota tarika
    // Yaa fir jaise maine oopar bataya hei oosi tarah eak naya array banake bhi 
    // Kar sakte Hoooo

    static int maxSum(int n)
    {
        if(n==1)  return 1;
        return ((n*(n-1))/2 +n/2 -1);
    }


    // Smallest number 
    // Tedious Question
    // We are actually trying to append as many 9 as possible from 
    // The right hand Side
    static String smallestNumber(int S, int D)
    {
        String ans = "";
        if(S>9*D)
        {
            return "-1";
        }
        if(S == 0)
        {
            return (D == 1) ? "0" : "-1";
        }
        // Subtracting 1 will help in the case
        // S == 9 and D == 2
        // Because in that case answer would be 18
        S = S-1;
        
        //we will start filling from right to left
        for(int i = 0;i<D;i++)
        {
            if(i==D-1)
            {
                //if its most siginificant digit then add 1 to the sum which we earlier substracted and then add Sum to our answer
                S = S+1;
                ans = S + ans;
            }
            else if(S>9)
            {
                //if sum > 9 then add 9 to our answer and then update our sum by substracting 9 from it.
                ans = 9 + ans;
                S = S - 9;
            }
            else if(S<=9)
            {
                //if sum <= 9 then add sum itself to the answer and then update sum by substracting sum from sum. 
                ans = S + ans;
                S = S-S;
            }
        }
        return ans;        
    }

    // Find Maximum Equal sum of Three Stacks 

    public static int maxEqualSum(int N1,int N2,int N3, int[] S1, int[] S2, int[] S3) 
    {
        int i = 0;
        int j = 0;
        int k = 0;
        
        int s1 = 0;
        int s2 = 0;
        int s3 = 0;
        
        for(int ele : S1) s1+=ele;
        for(int ele : S2) s2+=ele;
        for(int ele : S3) s3+=ele;
        
        
        while(i < S1.length && j < S2.length && k < S3.length)
        {
            if(s1 == s2 && s1 == s3)
            {
                return s1;
            }
            else if(s1 >=s2 && s1 >= s3)
            {
                s1 -= S1[i++];
            }
            else if(s2 >=s1 && s2 >= s3)
            {
                s2 -= S2[j++];
            }
            else if(s3 >=s2 && s3 >= s1)
            {
                s3 -= S3[k++];
            }
        }
        
        return 0;
    }

    // Knight Tour

    public static ArrayList<ArrayList<Integer>> knightTour(int n, int m) 
    {                
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = 0;i<n;i++)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = 0;j<m;j++) temp.add(-1);
            ans.add(temp);
        }
        order(0,0,m,n,ans,new int[]{0},n*m);
//         for(ArrayList<Integer> ele : ans) System.out.println(ele.toString());
        return ans;
    }
    
    
    public static boolean order(int x,int y,int m,int n ,ArrayList<ArrayList<Integer>> check,int []c,int count)
    {
        // System.out.println(c[0]);
        if(x<0 || y<0 || x >= n || y >= m || check.get(x).get(y) != -1) return false;
        check.get(x).set(y,c[0]);
        if(c[0] == count-1) 
        {
            // System.out.println("hello");
            return true;
        }

        c[0]++;
        boolean bool = false;
        bool = order(x+2,y-1,m,n,check,c,count);
        if(bool) return true;
        bool = order(x+2,y+1,m,n,check,c,count);
        if(bool) return true;
        bool = order(x-2,y+1,m,n,check,c,count);
        if(bool) return true;
        bool = order(x-2,y-1,m,n,check,c,count);
        if(bool) return true;
        bool = order(x-1,y+2,m,n,check,c,count);
        if(bool) return true;
        bool = order(x-1,y-2,m,n,check,c,count);
        if(bool) return true;
        bool = order(x+1,y-2,m,n,check,c,count);
        if(bool) return true;
        bool = order(x+1,y+2,m,n,check,c,count);
        if(bool) return true;
        if(!bool)check.get(x).set(y,-1);
        if(!bool) c[0]--;
        return bool;
    }


    // Tug of War
    // Using Back Tracking

    public static int tugOfWar(ArrayList<Integer> arr, int n) 
    {
        boolean[] vis = new boolean[n];
        int[] team1 = new int[]{0};
        int[] team2 = new int[]{0};
        int[] min = new int[]{(int)1e9};
        int toggle = 0;
        minVal(arr,0,team1,team2,vis,n,min);
        return min[0];
    }
    
    public static void minVal(ArrayList<Integer> arr,int toggle , int[]team1 , int[]team2 , boolean[]vis , int n , int[]min)
    {
        if(n == 0)
        {
            min[0] = Math.min(min[0],Math.abs(team1[0]-team2[0]));
            return;
        }
        for(int i = 0;i<arr.size();i++)
        {
            if(!vis[i])
            {
                if(toggle == 0)
                {
                    vis[i] = true;
                    team1[0] += arr.get(i);
                    minVal(arr,1,team1,team2,vis,n-1,min);
                    team1[0] -= arr.get(i);
                    vis[i] = false;
                }
                else if(toggle == 1)
                {
                    vis[i] = true;
                    team2[0] += arr.get(i);
                    minVal(arr,0,team1,team2,vis,n-1,min);
                    team2[0] -= arr.get(i);
                    vis[i] = false;
                }
                
            }
        }
    }


    // Shortest Safe Route In A Field With Landmines
    // Yeh Dp se pass nahi hoga
    // kyoki neeche row wale cell koo uper row wale cell ki zaroorat hei
    // Naa ki sirf oopar wale ko neeche wale ki

    // TOh yeh backtracking se hi karna huoga

    // With Memoisation it is giving TLE on coding ninjas

    public static int shortestPath(ArrayList<ArrayList<Integer>> field)
    {
        int n = field.size();
        int m = field.get(0).size();
        int[][] memo = new int[n][m];
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                memo[i][j] = (int)1e9;
                if(field.get(i).get(j) == 0)
                {
                    if(i + 1 < n) field.get(i+1).set(j,-1);
                    if(i - 1 >= 0) field.get(i-1).set(j,-1);
                    
                    if(j + 1 < m) field.get(i).set(j+1,-1);
                    if(j - 1 >=0) field.get(i).set(j-1,-1);
                }
            }
        }
        int[] min = new int[]{(int)1e9};
        boolean[] vis = new boolean[n];        
        for(int i = 0;i<n;i++)
        {
            if(!vis[i]) getShortest(field,i,0,n,m,min,vis,memo);
        }
        if(min[0] == (int)1e9) return -1;
        return min[0];
    }
    
    public static int getShortest(ArrayList<ArrayList<Integer>> field , int i , int j , int n , int m , int[] min , boolean[]vis , int[][] memo)
    {
        if(i < 0 || j < 0 || i>= n || j >= m) return (int)1e9;
        if(field.get(i).get(j) == -1 || field.get(i).get(j) == 0) return memo[i][j] = (int)1e9;
        if(memo[i][j] != (int)1e9) return memo[i][j]+1;
        if(j == m-1) return memo[i][j] = 1;
        field.get(i).set(j,-1);
        int res = (int)1e9;
        res = Math.min(res,getShortest(field,i-1,j,n,m,min,vis,memo));
        res = Math.min(res,getShortest(field,i+1,j,n,m,min,vis,memo));
        res = Math.min(res,getShortest(field,i,j-1,n,m,min,vis,memo));
        res = Math.min(res,getShortest(field,i,j+1,n,m,min,vis,memo));
        field.get(i).set(j,1);
        if(res == (int)1e9) return memo[i][j] = res;
        if(j == 0) 
        {
            vis[i] = true;
            min[0] = Math.min(min[0],res);
        }
        memo[i][j] = res;
        return  res + 1;
    }


    // Largest number in K swaps 
    // Using Back Tracking
    // Always use char array no get riid of NumberFormat Exception
    // I tried this problem like the Leetcode Question Max Swap K times but was not getting
    // the correct answer\

    static String max;
    public static String findMaximumNum(String str, int k) {
        //code here.
        max = str;
        findMaximum(str.toCharArray(), k);
        return max;
    }
    public static void findMaximum(char[] strArr, int k) {
        if(k == 0) return;

        for(int i = 0; i < strArr.length - 1; i++) {
            for(int j = i + 1; j < strArr.length; j++) {
                if(strArr[j] > strArr[i]) {
                    strArr = swap(strArr, i, j);
                    String st = new String(strArr);
                    if(max.compareTo(st) < 0) max = st;
                    findMaximum(strArr, k - 1);
                    strArr = swap(strArr, i, j);
                }
            }
        }
    }
    public static char[] swap(char[] strArr, int i, int j) {
        char temp = strArr[i];
        strArr[i] = strArr[j];
        strArr[j] = temp;
        return strArr;
    }

    // Path of greater than equal to k length 
    // Simple Backtracking Solution

    boolean pathMoreThanK(int V , int E , int K , int [] A)
    {
        @SuppressWarnings("unchecked")
        ArrayList<int[]>[] graph = new ArrayList[V];
        for(int i = 0;i<graph.length;i++) graph[i] = new ArrayList<>();
        boolean[] vis = new boolean[V];
        int i = 0;
        while (i < A.length-3)
        {
            int u = A[i];
            int v = A[i+1];
            int w = A[i+2];
            graph[u].add(new int[]{v,w});
            graph[v].add(new int[]{u,w});
            i = i + 3;
        }
        
        return doesExist(graph,0,0,K,vis);
    }
    
    boolean doesExist(ArrayList<int[]>[] graph ,int src , int psf , int K, boolean[] vis)
    {
        if(vis[src]) return false;
        if(psf >= K) return true;
    
        vis[src] = true;
        
        boolean res = false;
        
        for(int[] ele : graph[src])
        {
            res = res || doesExist(graph,ele[0],psf+ele[1],K,vis);
        }
        vis[src] = false;
        return res;
    }


    // Longest Route
    public static int longestPath(int n, int m, int[][] mat, int sx, int sy, int dx, int dy) 
    {
        int[] max = new int[]{-(int)1e9};
        getRoot(mat,sx,sy,dx,dy,mat.length,mat[0].length,max,0);
        if(max[0] == -(int)1e9) return -1;
        return max[0];
    }
    
    public static void getRoot(int[][] mat , int i , int j , int x , int y , int m , int n , int[]max , int c)
    {
        if(i < 0 || j < 0 || i >= m || j >= n) return;
        if(mat[i][j] == 0 || mat[i][j] == -1) return;
        if(i == x && j == y)
        {
            max[0] = Math.max(max[0],c);
            return;
        }
        mat[i][j] = -1;
        
        getRoot(mat,i+1,j,x,y,m,n,max,c+1);
        getRoot(mat,i-1,j,x,y,m,n,max,c+1);
        getRoot(mat,i,j-1,x,y,m,n,max,c+1);
        getRoot(mat,i,j+1,x,y,m,n,max,c+1);
        
        mat[i][j] = 1;
    }


    // Count all possible paths from top left to bottom right 

    long numberOfPaths(int m, int n)
    {
        long mod = (long)1e9 + 7;
        long[][] dp = new long[m][n];
        dp[m-1][n-1] = 1;
        for(int i = m-1;i>=0;i--)
        {
            for(int j = n-1;j>=0;j--)
            {
                if(i+1 < m) dp[i][j] = (dp[i][j] + dp[i+1][j])%mod;
                if(j+1 < n) dp[i][j] = (dp[i][j] + dp[i][j+1])%mod;
            }
        }
        return dp[0][0]%mod;
    }


    // Partition array to K subsets 

    public boolean isKPartitionPossible(int a[], int n, int k)
    {
        int sum = 0;
        for(int ele : a) sum+=ele;
        if(sum%k!=0) return false;
        int val = sum/k;
        boolean[] vis = new boolean[n];
        return ispossible(a,k-1,n,0,val,vis);
    }
    
    
    public boolean ispossible(int[]a , int k , int n , int ssf , int sum , boolean[]vis)
    {
        if(sum == ssf && k == 0) return true;
        else if(sum == ssf && k > 0) return ispossible(a,k-1,n,0,sum,vis);
        boolean res = false;
        for(int i = 0;i<n;i++)
        {
            if(!vis[i])
            {
                int val = a[i];
                if(ssf + val > sum) continue;
                vis[i] = true;
                res = res || ispossible(a,k,n,ssf+val,sum,vis);
                vis[i] = false;
            }
        }
        return res;
    }


    // Permutations of a given string 
    // In Sorted Order and With No Duplicates

    public List<String> find_permutation(String S)
    {
        char[] s = S.toCharArray();
        Arrays.sort(s);
        String A = "";
        for(char ele : s) A+=ele; 
        
        List<String> ans = new ArrayList<>();    
        StringBuilder sb = new StringBuilder(A); 
        perm(sb,0,ans);
        return ans;
    }
    
    public void perm(StringBuilder sb , int i , List<String> ans)
    {
        if(i == sb.length()-1)
        {
            ans.add(sb.toString());
            return;
        }
        for(int j = i;j<sb.length();j++)
        {
            StringBuilder temp = new StringBuilder(sb);
            if(j-1 >=0 )
            {
                if(temp.charAt(j-1) == temp.charAt(j)) continue;
            }
            char del = temp.charAt(j);
            temp.deleteCharAt(j);
            temp.insert(i,del);
            
            perm(temp,i+1,ans);
            
        }
    }


    // 46. Permutations

    public List<List<Integer>> permute(int[] nums) 
    {
        List<List<Integer>> ans = new ArrayList<>();
        perm(nums,0,ans);
        return ans;
    }
    
     public void perm(int[] sb , int i , List<List<Integer>> ans)
    {
        if(i == sb.length-1)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            for(int ele : sb) temp.add(ele);
            ans.add(temp);
            return;
        }
        for(int j = i;j<sb.length;j++)
        {
            int[] temp = sb.clone();
            if(j-1 >=0)
            {
                if(temp[j-1] == temp[j]) continue;
            }
            int del = temp[j];
            temp[j] = temp[i];
            temp[i] = del;
            
            perm(temp,i+1,ans);
            
        }
    }

    

    // 60. Permutation Sequence
    // Brute Force Solution

        public String getPermutation(int n, int k)
    {
        String A = "";
        for(int i = 1;i<=n;i++){
            A += i;
        }
        String[] ans = new String[]{""};
        int[] c = new int[]{k};
        StringBuilder sb = new StringBuilder(A); 
        perm(sb,0,ans,c);
        return ans[0];
    }
    
    public void perm(StringBuilder sb , int i , String[] ans , int[] c)
    {
        if(i == sb.length()-1)
        {
            c[0]--;
            if(c[0] == 0)
            {
                ans[0] = sb.toString();
            }
            return;
        }
        for(int j = i;j<sb.length() && c[0] > 0;j++)
        {
            StringBuilder temp = new StringBuilder(sb);
            if(j-1 >=0 )
            {
                if(temp.charAt(j-1) == temp.charAt(j)) continue;
            }
            char del = temp.charAt(j);
            temp.deleteCharAt(j);
            temp.insert(i,del);
            
            perm(temp,i+1,ans,c);
            
        }
    }


    // Optimized Solution

    








    // Painting the Fence 
    // Pure Recursive Solution
    public long mod = (int)1e9+7;
    long countWays(int n,int k)
    {
        return count(n,k,1,1,-1);
    }
    
    public long count(int n , int k , int i , int c ,int prev)
    {
        if(i == n+1) return 1;
        long res = 0;
        
        for(int j = 1;j<=k;j++)
        {
            if(c < 2 && j == prev) res = (res + count(n,k,i+1,c+1,j))%mod;
            if(j!=prev) res = (res + count(n,k,i+1,1,j))%mod;
        }
        return res%mod;
    }


    // With Memoisation

    public long mod = (int)1e9+7;
    long countWays(int n,int k)
    {
        long[][][] memo = new long[n+2][3][k+2];
        for(long [][] ele : memo)
        {
            for(long[] e : ele) Arrays.fill(e,-1);
        }
        return count(n,k,1,1,k+1,memo);
    }
    
    public long count(int n , int k , int i , int c ,int prev,long[][][]memo)
    {
        if(i == n+1) return memo[i][c][prev] = 1;
        if(memo[i][c][prev]!=-1) return memo[i][c][prev];
        long res = 0;
        
        for(int j = 1;j<=k;j++)
        {
            if(c < 2 && j == prev) res = (res + count(n,k,i+1,c+1,j,memo))%mod;
            if(j!=prev) res = (res + count(n,k,i+1,1,j,memo))%mod;
        }
        return memo[i][c][prev] =  res%mod;
    }

    // Tabulation
    // Btw its a simple counting problem
    // Check Dryrun in folder

    long countWays(int n,int k)
    {
        if(n==1) return k;
        long M = (long)1e9 + 7;
        long pp = k;
        long p = k*k;
         
        for(int i = 3;i<=n;i++)
        {
            long c = ((pp*(k-1))%M +  (p*(k-1))%M)%M;
            pp = p;
            p = c;
        }
        return p;
    }


    // 713. Subarray Product Less Than K
    // Sliding Window Problem
    // Handle testcases like
    // [1 1 1] 1
    // [2 2 2] 1
    // [1 2 3 4 5 10 11 5 6 9] 10

    public int numSubarrayProductLessThanK(int[] nums, int k)
    {
        if(k == 0) return 0;
        int i = 0;
        int j = 0;
        int count = 0;
        int mul = 1;
        while(j < nums.length)
        {
            int val = nums[j];
            if(mul == 1 && k <= val)
            {
                i++;
                j++;
            }
            else if(val >= k)
            {
                j++;
                i = j;
                mul = 1;
            }
            else if(val*mul < k)
            {
                mul*=val;
                count += j-i+1;
                j++;
            }
            else 
            {
                while(mul*val >= k && i < j)
                {
                    int del = nums[i];
                    mul/=del;
                    i++;
                }
            }
        }
        return count;
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


    // Pairs with specific difference 

    // Since we need the maximum sum so we are iterating from backwards
    public static int maxSumPairWithDifferenceLessThanK(int arr[], int N, int K) 
    {
       Arrays.sort(arr);
       int sum = 0;
       for(int i = N-1;i>=1;i--)
       {
           if(Math.abs(arr[i]-arr[i-1])<K)
           {
               sum+=(arr[i]+arr[i-1]);
               i-=1;
           }
       }
       return sum;
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



    // Array Removals 
    int removals(int[] arr, int n, int k) 
    {
        if(n == 1 && arr[0] > k) return 0;
        Arrays.sort(arr);
        int max = 0;
        int i = 0;
        int j = i+1;
        while(j < n)
        {
            int v1 = arr[i];
            int v2 = arr[j];
            
            if(v2-v1 <= k)
            {
                max = Math.max(max,j-i+1);
                j++;
            }
            else if(v2-v1 > k) i++;
        }
        return n - max;
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


    // 209. Minimum Size Subarray Sum
    // Simple Sliding Window
    public int minSubArrayLen(int s, int[] a) 
    {
      if (a == null || a.length == 0)
        return 0;

      int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;

      while (j < a.length)
      {
        sum += a[j++];

        while (sum >= s) 
        {
          min = Math.min(min, j - i);
          sum -= a[i++];
        }
      }
      return min == Integer.MAX_VALUE ? 0 : min;
    }


    // Longest alternating subsequence 

    public int AlternatingaMaxLength(int[] nums)
    {
        int ele = nums[0];
        int minLen = 1;
        int maxLen = 1;
        
        for(int i = 1;i<nums.length;i++)
        {
            int val = nums[i];
            
            if(val > ele)
            {
                maxLen = Math.max(maxLen,minLen+1);
                ele = val;
            }
            else if(val < ele)
            {
                minLen = Math.max(minLen,maxLen+1);
                ele = val;
            }
        }
        return Math.max(minLen,maxLen);
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


    // Construct Binary Tree from String with bracket representation
    // Simple Recursive Solution
    // We have to use global pointer to get track for String IDX

    static class Node
    {
        int data;
        Node left,right;
         
        Node(int data)
        {
            this.data = data;
            this.left = this.right = null;
        }
    }
     
    // static variable to point to the
    // starting index of the string.
    static int start = 0;
     
    // Construct Tree Function which accepts
    // a string and return root of the tree;
    static Node constructTree(String s)
    {
         
        // Check for null or empty string
        // and return null;
        if (s.length() == 0 || s == null)
        {
            return null;
        }
         
        if (start >= s.length())
            return null;
         
        // Boolean variable to check
        // for negative numbers
        boolean neg = false;
         
        // Condition to check for negative number
        if (s.charAt(start) == '-')
        {
            neg = true;
            start++;
        }
         
        // This loop basically construct the
        // number from the continuous digits
        int num = 0;
        while (start < s.length() &&
               Character.isDigit(s.charAt(start)))
        {
            int digit = Character.getNumericValue(
                s.charAt(start));
            num = num * 10 + digit;
            start++;
        }
         
        // If string contains - minus sign
        // then append - to the number;
        if (neg)
            num = -num;
         
        // Create the node object i.e. root of
        // the tree with data = num;
        Node node = new Node(num);
         
        if (start >= s.length())
        {
            return node;
        }
         
        // Check for open bracket and add the
        // data to the left subtree recursively
        if (start < s.length() && s.charAt(start) == '(' )
        {
            start++;
            node.left = constructTree(s);
        }
         
        if (start < s.length() && s.charAt(start) == ')')
        {
            start++;
            return node;
        }
         
        // Check for open bracket and add the data
        // to the right subtree recursively
        if (start < s.length() && s.charAt(start) == '(')
        {
            start++;
            node.right = constructTree(s);
        }
         
        if (start < s.length() && s.charAt(start) == ')')
        {
            start++;
            return node;
        }
        return node;
    }
 
    // Print tree function
    public static void printTree(Node node)
    {
        if (node == null)
            return;
       
        System.out.println(node.data + " ");
        printTree(node.left);
        printTree(node.right);
    }


    // Transform to Sum Tree 

    public void toSumTree(Node root)
    {
        create(root);
    }
    
    public int create(Node root)
    {
        if(root == null) return 0;
        int left = create(root.left);
        int right = create(root.right);
        int sum = left + right;
        int rootVal = root.data;
        root.data = sum;
        return sum + rootVal;
    }


    // Minimum Swaps To Convert Binary Tree Into BST
    // Note that we can easily find Inorder , PreOrder , Postorder from the 
    // Array representation of tree itself without creating a tree

    public static int minSwaps(int nums[])
    {
        int count = 0;
        boolean[] vis = new boolean[nums.length];
        int[][] arr = new int[nums.length][];
        for(int i = 0;i<nums.length;i++)
        {
            arr[i] = new int[]{nums[i],i};
        }
        
        Arrays.sort(arr ,(a,b)->{
            return a[0] - b[0];
        } );
        
        for(int i = 0;i<arr.length;i++)
        {
            int temp = arr[i][0];
            int next = arr[i][1];
            int tCount = 0;
            if(!vis[i])
            {
                vis[i] = true;
                int j = next;
                while(arr[j][0]!=temp)
                {
                    vis[j] = true;
                    j = arr[j][1];
                    tCount++;
                }
                count += tCount;
            }
        }
        return count;
    }
    public static void getInOrder(ArrayList<Integer> arr , int[] inorder , int[] i , int idx)
    {
        if(idx >= inorder.length) return;
        getInOrder(arr,inorder,i,(2*idx)+1);
        inorder[i[0]++] = arr.get(idx);
        getInOrder(arr,inorder,i,(2*idx)+2);
    }
    public static int minimumSwaps(ArrayList<Integer> arr, int n) 
    {
        int[] inorder = new int[n];
        getInOrder(arr,inorder,new int[]{0},0);
//         System.out.println(Arrays.toString(inorder));
        return minSwaps(inorder);
    }



    // Sum Tree 

    boolean isSumTree(Node root)
    {
        boolean[] bool = new boolean[1];
        bool[0] = true;
        check(root,bool);
        return bool[0];
    }
    public int check(Node root , boolean[] bool)
    {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return root.data;
        int left = check(root.left,bool);
        if(!bool[0]) return -1;
        int right = check(root.right,bool);
        if(!bool[0]) return -1;
        
        if(root.data != left + right)
        {
            bool[0] = false;
            return -1;
        }
        
        return left + right + root.data;
    }


    //Leaf at same level 

    boolean check(Node root)
    {
        int[] lvl = new int[]{-1};
        return isTrue(root,lvl,0);
    }
    
    boolean isTrue(Node root , int[]lvl , int l)
    {
        if(root == null) return true;
        if(root.left == null && root.right == null)
        {
            if(l == lvl[0] || lvl[0] == -1)
            {
                lvl[0] = l;
                return true;
            }
            else return false;
        }
        
        boolean left = isTrue(root.left,lvl,l+1);
        boolean right = isTrue(root.right,lvl,l+1);
        
        return left && right;
    }

    // Sum of the Longest Bloodline of a Tree (Sum of nodes on the longest path from root to leaf node) 

    public int sumOfLongRootToLeafPath(Node root)
    {
        int[] lvl = new int[]{0};
        int[] sum = new int[]{0};
        getSum(root,sum,lvl,0,0);
        return sum[0];
    }
    
    public void getSum(Node root , int[]sum , int[] lvl ,  int l , int s)
    {
        if(root == null)
        {
            if(l > lvl[0])
            {
                lvl[0] = l;
                sum[0] = s;
            }
            else if(l == lvl[0])
            {
                sum[0] = Math.max(sum[0],s);
            }
            return;
        }
        
        getSum(root.left,sum,lvl,l+1,s+root.data);
        getSum(root.right,sum,lvl,l+1,s+root.data);
    }


    // Maximum sum of Non-adjacent nodes 
    // Important
    // Good Question
    // Bewakoofi kari meine iss question mei


    static int getMaxSum(Node root)
    {
        int[] answer = helper(root);
        return Math.max(answer[0], answer[1]);
    }
    static int[] helper(Node root)
    {
        if(root== null) 
        {
            return new int[] { 0, 0 };
        }

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int rob = root.data + left[1] + right[1];
        int notRob = Math.max(left[0],left[1])+Math.max(right[0],right[1]);

        return new int[] { rob, notRob };
    }


    // Populate Inorder Successor for all nodes 
    // Do Good Dryrun
    public void populateNext(Node root)
    {
        populate(root,new Node[]{null});
    }
    
    public void populate(Node root , Node[]temp)
    {
        if(root == null) return;
        // For Understanding But Not Required
        // if(root.left == null && root.right == null && temp[0] == null) 
        // {
        //     temp[0] = root;
        //     return;
        // }
        populate(root.left,temp);
        if(temp[0]!=null) temp[0].next = root;
        temp[0] = root;
        populate(root.right,temp);
    }
    

    // Merge two BST 's 
    // Using BST to SLL function

    public List<Integer> merge(Node root1,Node root2)
    {
        Node d1 = new Node(-1);
        Node d2 = new Node(-1);
        bstToSll(root1,new Node[]{d1});
        bstToSll(root2,new Node[]{d2});
        d1 = d1.right;
        d2 = d2.right;
        
        List<Integer> ans = new ArrayList<>();
        
        while(d1!=null && d2!=null)
        {
            int v1 = d1.data;
            int v2 = d2.data;
            
            if(v1 < v2)
            {
                ans.add(v1);
                d1 = d1.right;
            }
            else if(v1 > v2)
            {
                ans.add(v2);
                d2 = d2.right;
            }
            else
            {
                ans.add(v1);
                ans.add(v1);
                d1 = d1.right;
                d2 = d2.right;
            }
        }
        
        while(d1!=null)
        {
            ans.add(d1.data);
            d1 = d1.right;
        }
        
        while(d2!=null)
        {
            ans.add(d2.data);
            d2 = d2.right;
        }
        
        return ans;
    }
    public void bstToSll(Node root , Node[] dummy)
    {
        if(root == null) return;
        bstToSll(root.left,dummy);
        
        dummy[0].right = root;
        dummy[0] = root;
        
        bstToSll(root.right,dummy);
    }


    // Kth largest and smallest done Using morris traversal
    // Check In PRevious Folders



    // Median of BST 
    // We can further optimize this solution
    // y using morris traversal 2 times
    // 1 for size and 1 for median calculation
    public static Node rightMostNode(Node node,Node curr)
    {
        while(node.right != null && node.right != curr )
        {
            node = node.right;
        }
        return node;
    }
    public static ArrayList<Integer> morris(Node root) 
    {
        ArrayList<Integer> ans = new ArrayList<>();
        Node curr = root;
        while (curr != null) 
        {
            Node next = curr.left;

            if (next == null) 
            {
                ans.add(curr.data);
                curr = curr.right;

            } 
            else 
            {
                Node rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) 
                {
                    rightMost.right = curr;
                    curr = curr.left;
                }
                else 
                {
                    rightMost.right = null;
                    ans.add(curr.data);
                    curr = curr.right;

                }
            }
        }

        return ans;
    }

    public static float findMedian(Node root)
    {
        ArrayList<Integer> ans = morris(root);
        int s = ans.size()-1;
        if(s%2 == 0)
        {
            return (float)ans.get(s/2);
        }
        else
        {
            double v1 = (ans.get(s/2));
            double v2 = ans.get((s+1)/2);
            // System.out.println((v1 + v2)/2.0);
            Double v3 = new Double((v1 + v2)/2.0); 
            return v3.floatValue();
        }
    }


    // Count BST nodes that lie in a given range 

    int getCount(Node root,int l, int h)
    {
        int[] count = new int[]{0};
        inOrder(root,l,h,count);
        return count[0];
    }
    
    boolean inOrder(Node root , int l , int h , int[]count)
    {
        if(root == null) return true;
        boolean res1 = inOrder(root.left,l,h,count);
        if(!res1) return false;
        if(root.data >= l && root.data <= h) count[0]++;
        else if(root.data > h) return false;
        
        boolean res2 = inOrder(root.right,l,h,count);
        if(!res2) return false;
        
        return true;
    }


    // Check whether BST contains Dead End 
    // Good way to traverse

    public static boolean isDeadEnd(Node root)
   {
     return  find(root,1,Integer.MAX_VALUE);
   }
   public static boolean find(Node root,int min,int max)
   {
       if(root==null)
       {
           return false;
       }
       if(root.data==min && root.data==max)
       {
           return true;
       }
       return find(root.left,min,root.data-1) || find(root.right,root.data+1,max);
   }


   // 1373. Maximum Sum BST in Binary Tree
   // Almost same as check if BST
    private int maxSum = 0;
    public int maxSumBST(TreeNode root) 
    {
        postOrderTraverse(root);
        return maxSum;
    }
    private int[] postOrderTraverse(TreeNode root) 
    {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        int[] left = postOrderTraverse(root.left);
        int[] right = postOrderTraverse(root.right);
        if (!(left != null && right != null && root.val > left[1] && root.val < right[0])) return null;
        int sum = root.val + left[2] + right[2];
        maxSum = Math.max(maxSum, sum);
        int min = Math.min(root.val, left[0]);
        int max = Math.max(root.val, right[1]);
        return new int[]{min, max, sum};
    }


    // Rat in Maze

    public static ArrayList<String> findPath(int[][] m, int n)
    {
        int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        ArrayList<String> ans = new ArrayList<>();
        paths(m,0,0,dir,ans,"");
        return ans;
    }
    
    public static void paths(int[][]m , int i , int j , int[][] dir , ArrayList<String> ans , String a)
    {
        if(i < 0 || j < 0 || i >= m.length || j >= m[0].length) return ;
        if(m[i][j] == 0 || m[i][j] == -1) return;
        if(i == m.length-1 && j == m[0].length-1) 
        {
            ans.add(a);
            return;
        }
        m[i][j] = -1;
        for(int[] ele : dir)
        {
            int x = ele[0] + i;
            int y = ele[1] + j;
            
            
            if(ele[0] == 1 && ele[1] == 0) paths(m,x,y,dir,ans,a+"D");
            if(ele[0] == 0 && ele[1] == 1) paths(m,x,y,dir,ans,a+"R");
            if(ele[0] == -1 && ele[1] == 0) paths(m,x,y,dir,ans,a+"U");
            if(ele[0] == 0 && ele[1] == -1) paths(m,x,y,dir,ans,a+"L");
        }
        m[i][j] = 1;
    }


    // Steps by Knight 
    public int minStepToReachTarget(int kp[], int tp[], int N)
    {
        int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };
        
        boolean[][] vis = new boolean[N][N];
        
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(((kp[0]-1)*N)+kp[1]-1);
        vis[kp[0]-1][kp[1]-1] = true;
        int level = 0;

        while(queue.size() > 0)
        {
            int size = queue.size();
            while(size-- > 0)
            {
                int t = queue.removeFirst();
                int i = t/N;
                int j = t%N;
                // System.out.println(i + " + " + j);
                if(i == tp[0]-1 && j == tp[1]-1) return level;
                for(int k = 0 ;k<dx.length;k++)
                {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if(x<0||y<0||x>=N||y>=N) continue;
                    if(vis[x][y]) continue;
                    
                    vis[x][y] = true;
                    queue.addLast((x*N)+y);
                }
            }
            level++;
        }
        return -1;
    }


    // Prerequisite Tasks  

    public boolean isPossible(int N, int[][] prerequisites)
    {
        ArrayList<Integer>[] graph = new ArrayList[N];
        int[] indeg = new int[N];
        for(int i = 0;i<N;i++) graph[i] = new ArrayList<>();
        for(int[] ele : prerequisites)
        {
            int u = ele[0];
            int v = ele[1];
            indeg[u]++;
            graph[v].add(u);
        }
        
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0;i<N;i++){
            if(indeg[i] == 0) queue.add(i);
        }
        if(queue.size() == 0) return false;
        int n = 1;
        while(queue.size() > 0)
        {
            int u = queue.removeFirst();
            for(int ele : graph[u])
            {
                indeg[ele]--;
                if(indeg[ele] == 0) 
                {
                    queue.add(ele);
                    n++;
                }
            }
        }
        for(int ele : indeg) if(ele > 0) return false;
        return true;
    }


    // 200. Number of Islands

    public int findPar(int u , int[] par)
    {
        return par[u] = (par[u] == u) ? u : findPar(par[u],par);
    }
    public int numIslands(char[][] grid) 
    {
        int n = grid.length;
        int m = grid[0].length;
        int[]par = new int[n*m];
        for(int i = 0;i<(n*m);i++) par[i] = i;
        // Arrays.fill(par,-1);
        int[][] dir = new int[][]{{1,0},{0,-1}};
        int count = 0;
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                if(grid[i][j] == '1')
                { 
                    for(int[] d : dir)
                    {
                        int x = i + d[0];
                        int y = j + d[1];
                        if(x >=0 && y>=0 && x<n && y<m && grid[x][y] == '1')
                        {
                            int u = findPar(i*m+j,par);
                            int v = findPar(x*m+y,par);
                            if(u!=v)
                            {
                                par[v] = u;
                            }

                        }
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(par));
        for(int i = 0;i<(n*m);i++)
        {
            if(grid[i/m][i%m] == '1')
            {
                if(par[i] == i) count++;
            }
        }
        return count;
    }



    // Bridge edge in a graph 
    // No need to apply ATQPT algo 
    // Since c and d is given just using dfs make sure from c directly you don't to d and vice versa
    // and check if we make a call from c are we able to reach d

    static int isBridge(int V, ArrayList<ArrayList<Integer>> adj,int c,int d)
    {
        int[] vis = new int[V];
        int[] count =  new int[]{1};
        dfs(adj,c,c,d,vis);
        return (vis[d] == 1) ? 0 : 1;
    }
    
    public static void dfs(ArrayList<ArrayList<Integer>> adj , int u , int c , int d , int[] vis)
    {
        vis[u] = 1;
        for(int ele : adj.get(u))
        {
            if((u == c && ele == d) || (ele == c && u == d)) continue; 
            if(vis[ele] == 0) dfs(adj,ele,c,d,vis);
        }
    }


    // M-Coloring Problem
    // Important Problem
    // Important solution
    // Note that the graph is not connected
    public boolean graphColoring(boolean graph[][], int m, int n)
    {
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] g = new ArrayList[n];
        for(int i = 0;i<n;i++) g[i] = new ArrayList<>();
        for(int i = 0;i<graph.length;i++){
            for(int j = 0;j<graph.length;j++){
                if(graph[i][j]) g[i].add(j);
            }
        }
        boolean[] vis = new boolean[n];
        int[] color = new int[n];
        boolean res = true;
        for(int i = 0;i<n;i++)
        {
            if(!vis[i]) res = res && color(g,i,vis,color,m);
        }
        return res;
    }
    
    public boolean isValid(ArrayList<Integer>[]graph,int u,int[]color,int i)
    {
        for(int ele : graph[u])
        {
            if(color[ele] == i) return false;
        }
        return true;
    }
    
    public boolean color(ArrayList<Integer>[]graph,int u,boolean[]vis,int[]color,int m)
    {
        vis[u] = true;
        for(int i = 1;i<=m;i++)
        {
            if(isValid(graph,u,color,i))
            {
                color[u] = i;
                boolean r = true;
                for(int ele : graph[u])
                {
                    if(vis[ele]) continue;
                    
                    r = r && color(graph,ele,vis,color,m);
                    if(!r) break;
                }
                if(r) return r;
                color[u] = 0;
            }
        }
        vis[u] = false;
        return false;
    }


    // Paths to travel each nodes using each edge(Seven Bridges)
    // Eularian Path Problem


    // Number of Triangles in Directed and Undirected Graphs
    // Simple n^3 problem

    int V = 4;
    int countTriangle(int graph[][],boolean isDirected)
   {
       int count_Triangle = 0;
       for (int i = 0; i < V; i++)
       {
           for (int j = 0; j < V; j++)
           {
               for (int k=0; k<V; k++)
               {
                  if (graph[i][j] == 1 &&
                      graph[j][k] == 1 &&
                      graph[k][i] == 1)
                      count_Triangle++;
               }
           }
       }
       if(isDirected == true)
       {
           count_Triangle /= 3;
       }
       else
       {
           count_Triangle /= 6;
       }
       return count_Triangle;
   }


   // Two Clique Problem
   // Check If the graph is Bipartitr or not


   // Minimum cash flow
   // Just Calculate the setteled amount
   public static int minCashFlow(ArrayList<ArrayList<Integer>> money, int n)

   {

       int balance[] = new int[n];

       for(int i=0;i<n;i++)

       {

           for(int j=0;j<n;j++)

           {

               balance[j]+=money.get(i).get(j);

               balance[i]-=money.get(i).get(j);

           }

       }

       int trans = 0;

       for(int i=0;i<n;i++)

       {

           if(balance[i]>0)

               trans += balance[i];

       }
       return trans;
   }


   // 50. Pow(x, n)

   // We converted in tn to log n because
   // if n would be -2^31 and when we convert it to positive then it will overflow
   // because int range is 2^31-1 and we have 2^31 that's why
   // Just to a dryrun
    public double myPow(double x, int n) 
    {
        double ans = 1.0;
        long nn = n;
        if (nn < 0) nn = -1 * nn;
        while (nn > 0) 
        {
          if (nn % 2 == 1) 
          {
            ans = ans * x;
            nn = nn - 1;
          } 
          else 
          {
            x = x * x;
            nn = nn / 2;
          }
        }
        if (n < 0) ans = (double)(1.0) / (double)(ans);
        return ans;   
    }

    // 493. Reverse Pair
    // Important Question 
    // Use the Strategy that we use in inversion count

    public int reversePairs(int[] nums)
    {
        return (int)inversionCount(nums,nums.length);
    }
    public int InversionAcrossArray(int[] arr, int l, int r, int mid, int[] sortedArray) 
    {
        int lsi = l, lei = mid;
        int rsi = mid + 1, rei = r;

        int k = 0;
        int count = 0;
        int prevCount = 0;
        
        int i = lsi;
        int j = rsi;
        
        while(j <= rei && i <= lei)
        {
            long v1 = arr[i];
            long v2 = arr[j];
            
            
            if(2*v2 < v1)
            {
                j++;
                prevCount++;
            }
            else
            {
                i++;
                count += prevCount;
            }
            
        }
        
        while(i <= lei)
        {
            count += prevCount;
            i++;
        }
        
        
        while (lsi <= lei && rsi <= rei) 
        {
            if (arr[lsi] > arr[rsi]) sortedArray[k++] = arr[rsi++];
            else sortedArray[k++] = arr[lsi++];
        }

        while (lsi <= lei) sortedArray[k++] = arr[lsi++];
        while (rsi <= rei) sortedArray[k++] = arr[rsi++];

        k = 0;
        for (int m = l; m <= r; m++) arr[m] = sortedArray[k++];

        return count;
    }

    public int inversionCount(int[] arr, int si, int ei, int[] sortedArray) {
        if (si >= ei)
            return 0;

        int mid = (si + ei) / 2;
        int ICL = inversionCount(arr, si, mid, sortedArray); // IC : Inversion Count, L = left , R = Right
        int ICR = inversionCount(arr, mid + 1, ei, sortedArray);

        return (ICL + ICR + InversionAcrossArray(arr, si, ei, mid, sortedArray));
    }

    public int inversionCount(int arr[], int N) {
        if (N == 0) return 0;
        int n = (int)N;
        int[] sortedArray = new int[n];
        return inversionCount(arr, 0,n - 1, sortedArray);
    }


    // 128. Longest Consecutive Sequence
    // Very simple Solution from TUF
    // OTher solution wuld be to take an integer and go left and right untill u are able yo find n+1 and n-1 using an hashset
    // 

    public static int longestConsecutive(int[] nums)
    {
        Set<Integer> hashSet = new HashSet<Integer>();
        for (int num: nums) 
        {
            hashSet.add(num);
        }

        int longestStreak = 0;

        for (int num: nums) 
        {
            if (!hashSet.contains(num - 1)) 
            {
                int currentNum = num;
                int currentStreak = 1;

                while (hashSet.contains(currentNum + 1)) 
                {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }


    /// 61. Rotate List
    // Good Question

    public int len(ListNode head)
    {
        int count = 0;
        while(head!=null) 
        {
            head = head.next;
            count++;
        }
        return count;
    }
    public ListNode reverse(ListNode head)
    {
        ListNode prev = null;
        while(head!=null)
        {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    public ListNode rotateRight(ListNode head, int k) 
    {
        if(head == null) return null;
        int len = len(head);
        if(len == 1) return head;
        if(k > len )k = k%len;
        if(k == 0) return head;
        ListNode rev = reverse(head);
        ListNode d2 = new ListNode(-1);
        ListNode d = d2;
        while(k-- > 0)
        {
            ListNode next = rev.next;
            rev.next = null;
            d.next = rev;
            d = d.next;
            rev = next;
        }
        ListNode r1 = reverse(d2.next);
        rev = reverse(rev);
        if(d2.next!=null) d2.next.next = rev;
        
        return r1;
    }

    // 26. Remove Duplicates from Sorted Array
    // Good Question

    public int removeDuplicates(int[] nums)
    {
        int i = 0;
        int j = i+1;
        int count = 0;
        while(j < nums.length)
        {
            int v1 = nums[i];
            int v2 = nums[j];
            
            if(v1 == v2)
            {
                count++;
                j++;
            }
            else
            {
                i++;
                nums[i] = v2;
                j++;
            }
        }
        return nums.length-count;
    }


    // Find Nth root of M 
    // Important Solution
    // TUF solution
    // N*log(M*10^d)
    // where d is till what decimal place you need the answer


    private static double multiply(double number, int n) {
        double ans = 1.0;
        for(int i = 1;i<=n;i++) {
            ans = ans * number;
        }
        return ans; 
    }
    private static void getNthRoot(int n, int m) {
        double low = 1;
        double high = m;
        double eps = 1e-6; 
        
        while((high - low) > eps) {
            double mid = (low + high) / 2.0; 
            if(multiply(mid, n) < m) {
                low = mid; 
            }
            else {
                high = mid; 
            }
        }
        
        System.out.println(n+"th root of "+m+" is "+low);    
    } 


    // Sort a stack 
    // Recursive Solution


    public Stack<Integer> sort(Stack<Integer> s)
    {
        Stack<Integer> ans = new Stack<>();
        while(s.size() > 0) 
        {
            int min[] = new int[]{(int)1e9};
            getSorted(s,ans,min);
        }
        return ans;
    }
    
    public void getSorted(Stack<Integer> s , Stack<Integer> ans , int[] min)
    {
        if(s.size() == 0)
        {
            ans.push(min[0]);
            return ;
        }
        int val = s.pop();
        min[0] = Math.min(min[0],val);
        getSorted(s,ans,min);
        if(min[0] == val) min[0] = -1;
        else s.push(val);
    }


    // 155. Min Stack
    // This solution is mostly equivilant to using 2 stacks but still saves space because
    // we are adding prev min value in the stack only when the min values is changing
    // but in two stack we need to add minValue every time in other stack


    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) 
    {
        if(x <= min)
        {          
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() 
    {
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() 
    {
        return stack.peek();
    }

    public int getMin() 
    {
        return min;
    }



    // 901. Online Stock Span

    Stack<int[]> st = new Stack<>();
    int idx = 0;
    public StockSpanner() // constructor call
    {
        st.push(new int[]{-1, -1});
    }

    public int next(int price)
    {

        while (st.peek()[0] != -1 && st.peek()[1] <= price)
        {
            st.pop();
        }
        int span = idx - st.peek()[0];
        st.push(new int[]{idx++, price});
        return span;
    }


    // 215. Kth Largest Element in an Array

    public int findKthLargest(int[] nums, int k)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int ele : nums)
        {
            pq.add(ele);
            
            if(pq.size()>k)
            {
                pq.remove();
            }
        }
        
        return pq.peek();
        
        
    }

    // 703. Kth Largest Element in a Stream

    PriorityQueue<Integer> queue = new PriorityQueue<>();
    int K = 0;
    public KthLargest(int k, int[] nums) 
    {
        K = k;
        for(int ele : nums) {
            queue.add(ele);
            K--;
        }
        while(queue.size() > k) 
        {
            queue.poll();
            K++;
        }
    }
    
    public int add(int val) 
    {
        if(K > 0)
        {
            queue.add(val);
            K--;
        }
        else if(queue.size() > 0 && val > queue.peek())
        {
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }



    // Merge Two Balanced Binary Search Trees
    //     We can use a Doubly Linked List to merge trees in place. Following are the steps.
    // 1) Convert the given two Binary Search Trees into doubly linked list in place (Refer this post for this step). 
    // 2) Merge the two sorted Linked Lists (Refer this post for this step). 
    // 3) Build a Balanced Binary Search Tree from the merged list created in step 2. (Refer this post for this step)


    

}
