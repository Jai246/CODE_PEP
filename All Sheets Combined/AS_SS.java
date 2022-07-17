public class AS_SS
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
    // Dutch National flag algorithm
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
    // Important Problem
    // Given an n x n matrix mat[n][n] of integers, 
    // Find the maximum value of mat(c, d) – mat(a, b) over all choices of indexes such that both c > a and d > b.

    public static int findMaxValue(int mat[][], int n)
    {
        int[][] max = new int[n][n];
        for(int i = n-1;i>=0;i--)
        {
            for(int j = n-1;j>=0;j--)
            {
                int right = ((j+1) < n) ? max[i][j+1] : -(int)1e9;
                int down = ((i+1) < n) ? max[i+1][j] : -(int)1e9;
                max[i][j] = Math.max(mat[i][j],Math.max(right,down));
            }
        }
        int ans = -(int)1e9;
        for(int i = n-2;i>=0;i--)
        {
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

    // Simple but important Solution
    // It is simple because there is no need to take to pointers
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
        count+=open/2; // Khud hi khud mei adjust
        count+=close/2; // Khud hi khud mei adjust
        count += (open%2) + (close%2); // Aaapas mei adjust
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

   // Simple Solution Using Sorting

   String secFrequent(String arr[], int N)
    {
        Arrays.sort(arr,(a,b)->{
           return a.compareTo(b); 
        });
        
        int j = 0;
        String most1 = "";
        int m1 = 0;
        String most2 = "";
        int m2 = 0;
        for(int i = 0;i<N;i++)
        {
            String str1 = arr[i];
            String str2 = ((i+1 == N) ? "" : arr[i+1]); 
            if(!str1.equals(str2))
            {
                int count = i-j+1;
                if(count > m1)
                {
                    m2 = m1;
                    most2 = most1;
                    m1 = count;
                    most1 = arr[i];
                }
                else if(count < m1 && count > m2)
                {
                    m2 = count;
                    most2 = arr[i];
                }
                j = i+1;
            }
        }
        return most2;
    }

   // 1963. Minimum Number of Swaps to Make the String Balanced
   // Note that open and close brackets are present in equal quantity
   // Important 
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
    // Simple Stack Solution
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
    // Matlab agar last ke do character match nahi kar rahe then 100% voh character aage gaya hei A mei se
    // ABC
    // CBA
    // BAS UNMATCHED CHARACTER NIKALNE HEI THATS IT , AUR KUSH NAHI KARNA
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
            // not doing mid*mid to handle overflow
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
    // Use index zero to mark N **IMP**

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
    // Note that according to our algorithm the kth element is left oriented
    public long kthElement( int arr1[], int arr2[], int m, int n, int k) 
    {
        if(n < m) return kthElement(arr2,arr1,n,m,k);
        int start = Math.max(k-n-1,-1);
        int end = Math.min(k-1,m-1);
        
        while(start <= end)
        {
            int mid1 = (start+end)/2; // Calculate Middle && Calculate Elements you are covering in first Array
            int left = k-(mid1+1); // Calculate Left Out Elements to be takken from second array
            int mid2 = -1+left; // Take those Elements
            
            
            int l1 = -1;
            int l2 = -1;
            
            if(mid1 == -1)
            {
                l1 = -(int)1e9;
                l2 = arr1[mid1+1];
            }
            else if(mid1 == m-1)
            {
                l1 = arr1[mid1];
                l2 = (int)1e9;
            }
            else
            {
                l1 = arr1[mid1];
                l2 = arr1[mid1+1];
            }
            int r1 = -1;
            int r2 = -1;
            
            if(mid2 == -1)
            {
                r1 = -(int)1e9;
                r2 = arr2[mid2+1];
            }
            else if(mid2 == n-1)
            {
                r1 = arr2[mid2];
                r2 = (int)1e9;
            }
            else
            {
                r1 = arr2[mid2];
                r2 = arr2[mid2+1];
            }
            
            if(r1<=l2 && l1<=r2) return Math.max(l1,r1);
            else if(r1 > l2) start = mid1+1;
            else end = mid1-1;
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
        for (int i = 1; i < n; i++) 
        {
            if (a[i] - lastPlacedCow >= minDist) 
            {
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
            if (isPossible(a, n, cows, mid)) low = mid + 1;
            else high = mid - 1;
        }
        System.out.println("The largest minimum distance is " + high);
    }


    // Job Sequencing Problem 
    // Can check abdul bari video

    public int[] JobScheduling(Job arr[], int n)
    {
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        Arrays.sort(arr,(a,b)->{return b.profit-a.profit;});
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
    // Mujhe largest correct section ko dhoondna hei
    // jiske end tak saare elements present hooo starting from arr[0];
    // Agar mujhe voh mil jata hei toh mei right side mei chalajaunga i.e low = mid+1;
    // Note that First yaa last element se pehle yaa baad mei koi element missing nahi ho sakta
    // think over this carefully
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
        // An = A0 + (n-1)*D
        // D = An-A0/n;
        // Because 1 element is missing
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
            if(k > 0)
            {
                if(a[i] < 0)
                {
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

    // Array Removals 
    // Important Solution 
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

    // 50. Pow(x, n)
   // We converted in tn to log n because
   // if n would be -2^31 and when we convert it to positive then it will overflow
   // because int range is 2^31-1 and we have 2^31 that's why
   // Just to a dryrun on 
    // pow(2,5)
    // pow(2,4)
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


    private static double multiply(double number, int n)
    {
        double ans = 1.0;
        for(int i = 1;i<=n;i++) {
            ans = ans * number;
        }
        return ans; 
    }
    private static void getNthRoot(int n, int m) 
    {
        double low = 1;
        double high = m;
        double eps = 1e-6; 
        
        while((high - low) > eps) {
            double mid = (low + high) / 2.0; 
            if(multiply(mid, n) < m) 
            {
                low = mid; 
            }
            else 
            {
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

}
