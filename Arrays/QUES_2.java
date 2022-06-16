class pepYtList
{
    // FAULTY KEYBOARD LEETCODE
    // IMPORTANT QUESTION
    public boolean isLongPressedName(String name, String typed) 
    {
        int i = 0;
        int j = 0;
        while(i < name.length())
        {
            if(j >= typed.length()) return false;
            if(name.charAt(i)!=typed.charAt(j)) return false;
            char prev = name.charAt(i++);
            int count1 = 1;
            while(i < name.length() && name.charAt(i) == prev)
            {
                i++;
                count1++;
            }
            char prev2 = typed.charAt(j);
            while(j < typed.length() && typed.charAt(j) == prev2)
            {
                j++;
                count1--;
            }
            if(count1 > 0) return false;
        }
        return (j == typed.length()) ? true : false;
    }


    // range query leetcode locked submitted on pepcoding
    public static int[] getModifiedArray(int length, int[][] queries) 
    {
        int[] arr = new int[length];
        for(int[] query : queries)
        {
            int start = query[0];
            int endPlusOne = query[1]+1;
            int val = query[2];
            arr[start]+=val;
            if(endPlusOne < length) arr[endPlusOne] +=(-val);
        }
        int pSum = 0;
        for(int i = 0;i<length;i++){
            pSum += arr[i];
            arr[i] = pSum;
        }
        return arr;
    }

    // container with most water leetcode
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length-1;
        int maxArea = 0;
        while(j > i)
        {
            maxArea = Math.max(maxArea ,Math.min(height[i],height[j])*(j-i));
            if(height[i] >= height[j]) j--;
            else i++;
        }
        return maxArea;
    }

    // Squares of a sorted Array Leetcode 
    public int[] sortedSquares(int[] nums) 
    {
        int[] ans = new int[nums.length];
        int i = 0;
        int j = nums.length-1;
        int idx = nums.length-1;
        while(j >= i)
        {
            int sqStart = nums[i]*nums[i];
            int sqEnd = nums[j]*nums[j];
            ans[idx] = Math.max(sqStart,sqEnd);
            if(sqStart >= sqEnd) i++;
            else j--;
            idx--;
        }
        return ans;
    }


    // MAJORITY ELEMENT 1 LEETCODE MOORE'S VOOTING ALGORITHM

    public int majorityElement(int[] nums) 
    {
        int count = 1;
        int value = nums[0];
        
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]!=value)
            {
                if(count == 0)
                {
                    value = nums[i];
                    count = 1;
                }
                else
                {
                    count--;
                }
            }
            else count++;
        }
        // count = 0;
        // for(int i = 0;i<nums.length;i++)
        // {
        //     if(nums[i] == value) count++;
        // }
        // if count of val > n/2 return val : -1;
        // but in this case majority element will definately exist so we are not checking
        return value;
    }

    // MAJORITY ELEMENT 2 LEETCODE
    public List<Integer> majorityElement(int[] nums) 
    {
        int val1 = nums[0];
        int count1 = 1;
        
        int val2 = nums[0];
        int count2 = 0;
        
        for(int i = 1;i<nums.length;i++)
        {
            int val = nums[i];
            if(val!=val1 && val!=val2 && count1!=0 && count2!=0){ // both non 0
                count1--;
                count2--;
            }
            else if(val!=val1 && val!=val2 && count1==0 && count2==0) // both 0
            {
                count1 = 1;
                val1 = val;
            }
            else if(val!=val1 && val!=val2) // either one of them 0;
            {
                if(count1 == 0){
                    val1 = val;
                    count1 = 1;
                }
                if(count2 == 0){
                    val2 = val;
                    count2 = 1;
                }
            }
            else if(val == val1) count1++;
            else if(val == val2) count2++;
        }
        
        int c1 = 0;
        int c2 = 0;
        
        for(int i = 0;i<nums.length;i++)
        {
            if(nums[i] == val1) c1++;
            if(nums[i] == val2) c2++;
        }
        //System.out.println(val1 + " " + c1 + "     " + val2 + " " + c2);
        ArrayList<Integer> list = new ArrayList<>();
        if(c1 > nums.length/3) list.add(val1);
        if(c2 > nums.length/3 && val1!=val2) list.add(val2);
        
        return list;
    }

    // MAJORITY ELEMENT GENERAL GEEKS FOR GEEKS N/K

    public int countOccurence(int[] arr, int n, int k) 
    {
        HashMap<Integer , Integer> map = new HashMap<>();
        
        for(int i = 0;i<n;i++)
        {
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }
            else
            {
                map.put(arr[i] , map.get(arr[i]) + 1);
            }
        }
        int count = 0;
        int threshold = n/k;
        for(int ele : map.values()){
            if(ele > threshold) count++;
        }
        
        return count;
    }

    // 238. Product of Array Except Self 

    public int[] productExceptSelf(int[] nums) 
    {
        int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];
        int[] ans = new int[nums.length];
        dp1[0] = nums[0];
        dp2[nums.length-1] = nums[nums.length-1];
        
        for(int i = 1;i<nums.length;i++){
            dp1[i] = dp1[i-1]*nums[i];
        }
        
        for(int i = nums.length-2;i>=0;i--){
            dp2[i] = nums[i]*dp2[i+1];
        }
        
        for(int i = 0;i<nums.length;i++){
            int Val1 = (i-1>=0) ?  dp1[i-1] : 1;
            int Val2 = (i+1 < nums.length) ? dp2[i+1] : 1;
            ans[i] = Val1*Val2;
        }
        
        return ans;
    }

    // 763. Partition Labels

    // important
    public List<Integer> partitionLabels(String s) 
    {
        HashMap<Character,Integer> map = new HashMap<>();
        
        for(int i = 0;i<s.length();i++){
            char t = s.charAt(i);
            map.put(t,i);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int prevLen = 0;
        int maxIdx = map.get(s.charAt(0));
        int i = 0;
        for( ;i<s.length();i++)
        {
            maxIdx = Math.max(maxIdx , map.get(s.charAt(i)));
            if(i == maxIdx)
            {
                ans.add(i+1-prevLen);
                prevLen += i+1-prevLen;
            }
        }
        if(i-prevLen > 0) ans.add(i-prevLen);
        return ans;
    }

    // 769. Max Chunks To Make Sorted

    public int maxChunksToSorted(int[] arr) 
    {
        int chunks = 0;
        int maxIdx = arr[0];
        for(int i = 0;i<arr.length;i++)
        {
            maxIdx = Math.max(maxIdx , arr[i]);
            if(i == maxIdx){
                chunks++;
            }
        }
        return chunks;
    }

    // 768. Max Chunks To Make Sorted II Important

    public int maxChunksToSorted(int[] arr) 
    {
        // left right max
        int[] dp1 = new int[arr.length];
        // right left min
        int[] dp2 = new int[arr.length];
        
        dp1[0] = arr[0];
        dp2[arr.length-1] = arr[arr.length-1];
        
        for(int i = 1;i<arr.length;i++){
            dp1[i] = Math.max(dp1[i-1] , arr[i]);
        }
        for(int i = arr.length-2;i>=0;i--){
            dp2[i] = Math.min(dp2[i+1] , arr[i]);
        }
        
        int chunks  = 0;
        for(int i = 0;i<arr.length-1;i++)
        {
            if(dp1[i] <= dp2[i+1]) chunks++;
        }
        return chunks+1;
    }

    // 915. Partition Array into Disjoint Intervals
    // approach 1 
    // check approach 2 in notes

    public int partitionDisjoint(int[] arr) {
        // left right max
        int[] dp1 = new int[arr.length];
        // right left min
        int[] dp2 = new int[arr.length];
        
        dp1[0] = arr[0];
        dp2[arr.length-1] = arr[arr.length-1];
        
        for(int i = 1;i<arr.length;i++){
            dp1[i] = Math.max(dp1[i-1] , arr[i]);
        }
        for(int i = arr.length-2;i>=0;i--){
            dp2[i] = Math.min(dp2[i+1] , arr[i]);
        }
        int idx = 0;
        for(int i = 0;i<arr.length-1;i++){
            // <= because we want the smallest possible left side partition
            if(dp1[i] <= dp2[i+1]) {
                idx = i;
                break;
            }
        }
        return idx+1;
    }

    // Min Jumps With +i -i Moves pepcoding portal
    public static int minJumps(int x) {
        int sum = 0;
        int i = 1;
        while(sum < x) {
            sum += i;
            i++;
        }
        if(sum == x) {
            return i - 1;
        } else if((sum - x) % 2 == 0) {
            return i - 1;
        } else if((sum + i - x) % 2 == 0) {
                return i;
        } else {
            return i + 1;
        }
    }

    // Maximum Product of 3 Numbers

    public int maximumProduct(int[] nums) 
    {
        int min1 = (int)1e9;
        int min2 = (int)1e9;
        int max1 = -(int)1e9;
        int max2 = -(int)1e9;
        int max3 = -(int)1e9;
        
        for(int i = 0;i<nums.length;i++)
        {
            int val = nums[i];
            
            if(val < min1){
                min2 = min1;
                min1 = val; 
            }else if(val < min2){
                min2 = val;
            }
            
            if(val > max1){
                max3 = max2;
                max2 = max1;
                max1 = val;
            }else if(val > max2){
                max3 = max2;
                max2 = val;
            }
            else if( val > max3){
                max3 = val;
            }
        }
        return Math.max(min1*min2*max1 , max1*max2*max3);
    }

    // 905. Sort Array By Parity

    public int[] sortArrayByParity(int[] nums)
    {
        int i = 0;
        int j = nums.length-1;
        while(i < j)
        {
            while(i < nums.length && nums[i]%2 == 0) i++;
            while(j >= 0 && nums[j]%2 != 0) j--;
            if(i < j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            i++;
            j--;
        }
        return nums;
    }

    // best meeting point leetcode locked

    //1. A group of two or more people wants to meet and minimize the total travel distance.
    //2. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
    //3. Return min distance where distance is calculated using 'Manhattan Distance',
    // where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

    public static int minTotalDistance(int[][] grid) {
        // Write your code here
        ArrayList<Integer> xcord = new ArrayList<>();
        ArrayList<Integer> ycord = new ArrayList<>();

        // We Are Doing it this way i.e seperately because
        // This way we will get values in sortd order in the ArrayList

        // for row coordinates
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1) {
                    xcord.add(r);
                }
            }
        }

        // for col coordinates
        for(int c = 0; c < grid[0].length; c++) {
            for(int r = 0; r < grid.length; r++) {
                if(grid[r][c] == 1) {
                    ycord.add(c);
                }
            }
        }

        int x = xcord.get(xcord.size() / 2);
        int y = ycord.get(ycord.size() / 2);
        
        // calculate distance
        // Manhatten Distance
        int dist = 0;
        for(int i = 0; i < xcord.size(); i++) {
            dist += Math.abs(xcord.get(i) - x) + Math.abs(ycord.get(i) - y);
        }
        return dist;
    }

    // sieve of eratosthenese leetcode 204
    // here it is mentioned less than n so we have created the array of size n;
    // but generally we create array of n-1;
    public int countPrimes(int n)
    {
        if(n == 0 || n == 1) return 0;
        Boolean[] isPrime = new Boolean[n];
        Arrays.fill(isPrime,true);

        for(int i=2;i*i<isPrime.length;i++)
        {   
            if(isPrime[i])
            {
                for(int j = i+i;j<isPrime.length;j = j + i)
                {
                    isPrime[j] = false;
                }
            }
        }
        int count = 0;
        for(int i = 2;i<isPrime.length;i++) if(isPrime[i]) count++;
        return count;
    }

    // segmented sieve pepcoding solution

    public static ArrayList<Integer> sieveOfEratosthenes(int N) 
    {
        Boolean[] isPrime = new Boolean[n];
        Arrays.fill(isPrime,true);

        for(int i=2;i*i<isPrime.length;i++)
        {   
            if(isPrime[i])
            {
                for(int j = i+i;j<isPrime.length;j = j + i)
                {
                    isPrime[j] = false;
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 2; i <= N; i++) 
        {
          if (isPrime[i]) 
          {
            ans.add(i);
          }
        }
        return ans;
    }

    public static void segmentedSieveAlgo(int a, int b) 
    {
        StringBuilder sb = new StringBuilder();
        int rootb = (int) Math.sqrt(b);
        ArrayList<Integer> primes = sieveOfEratosthenes(rootb);
        boolean[] arr = new boolean[b - a + 1];

        for (int i : primes) 
        {
          int multiple = (int) Math.ceil(a * 1.0 / i);
          if (multiple == 1) 
          {
            multiple++;
          }
          int firstidx = (multiple * i) - a;

          while (firstidx < arr.length) 
          {
            arr[firstidx] = true;
            firstidx += i;
          }
        }
        for (int i = 0; i < arr.length; i++) 
        {
          if (arr[i] == false && (a + i) != 1) 
          {
            sb.append(a + i + "\n");
          }
        }
        sb.append("\n");
        System.out.println(sb);
    }

    // transpose of a matrix leetcode

    public int[][] transpose(int[][] matrix) 
    {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] nMat = new int[m][n];
        
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                nMat[j][i] = matrix[i][j];
            }
        }
        return nMat;
    }

    // inplace transpose of n*n matrix
    public static void transpose(int[][] matrix) {
    // write your code here
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j <= i; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
  }

  // ROTATE IMAGE LEETCODE

    public void reverse(int[][] mat){
        for(int i = 0;i<mat.length;i++)
        {
            int j = 0;
            int k = mat.length-1;
            
            while(j<k)
            {
                int temp = mat[i][k];
                mat[i][k] = mat[i][j];
                mat[i][j] = temp;
                j++;
                k--;
            }
        }
    }
    public void rotate(int[][] matrix) 
    {
        for(int gap = 1;gap<matrix.length;gap++)
        {
            for(int i = 0,j = gap;j<matrix.length;j++,i++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        reverse(matrix);
    }

    // REVERSE VOVELS IN A STRING LEETCODE 345

    public boolean isVowel(char[] arr, int indx) {
    char ch = arr[indx];
    if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
        ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
      return true;
    }
    return false;
  }

  public void swap(char[] arr, int a, int b) {
    char temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  public String reverseVowels(String s) {
    char[] arr = s.toCharArray();
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
      while (left < right && isVowel(arr, left) == false) {
        left++;
      }
      while (left < right && isVowel(arr, right) == false) {
        right--;
      }
      swap(arr, left, right);
      left++;
      right--;
    }

    String str = "";
    for (char ch : arr) {
      str += ch;
    }
    return str;
  }

  // WIGGLE SORT 1 // WAVE ARRAY SOLVED ON GEEKS FOR GEEKS

    public static void convertToWave(int arr[], int n)
    {
        for(int i = 0;i<n;i++)
        {
            if(i%2 == 0){
                if(i+1 < n){
                    if(arr[i] < arr[i+1]){
                        int temp = arr[i+1];
                        arr[i+1] = arr[i];
                        arr[i] = temp;
                    }
                }
            }
            else{
                if(i+1 < n){
                    if(arr[i] > arr[i+1]){
                        int temp = arr[i+1];
                        arr[i+1] = arr[i];
                        arr[i] = temp;
                    }
                }
            }
        }
    }

    // LEETCODE 324 WIGGLE SORT 2

    public void wiggleSort(int[] nums) 
    {
        Arrays.sort(nums);
        int[] res = new int[nums.length];        
        int j = 1;
        for(int i = nums.length-1;i>=0;i--)
        {
            if(j >= nums.length) j = 0;
            res[j] = nums[i];
            j+=2;
        }
        for(int i = 0;i<nums.length;i++){
            nums[i] = res[i];
        }
    }

    // Add Strings leetcode 415
    // We can do this question without creating a string builder and reversing it
    public String addStrings(String num1, String num2) 
    {
        String ans = "";
        int carry = 0;
        StringBuilder s1 = new StringBuilder();
        s1.append(num1);
        s1.reverse();
        StringBuilder s2 = new StringBuilder();
        s2.append(num2);
        s2.reverse();
        int maxLen = Math.max(s1.length(),s2.length());
        
        for(int i=0;i<maxLen;i++)
        {
            int m = (i < s1.length()) ? s1.charAt(i) - '0' : 0; 
            int n = (i < s2.length()) ? s2.charAt(i) - '0' : 0;
            int sum = m + n + carry;
            ans = (sum%10) + ans;
            carry = sum/10;
        }
        if(carry == 1) ans = 1 + ans;
        return ans;
    }

    // leetcode 43. Multiply Strings

    public String multiply(String num1, String num2) 
    {
        if(num1.charAt(0) == '0' || num2.charAt(0) == '0') return "0";
        String prev = "";
        String curr = "";
        int maxLen = Math.max(num1.length() , num2.length());
        for(int i = num2.length()-1;i>=0;i--)
        {
            int n = num2.charAt(i) - '0';
            int carry = 0;
            for(int j = num1.length()-1;j>=0;j--)
            {
                int m = num1.charAt(j) - '0'; 
                int r = (m*n) + carry;
                curr = (r%10) + curr;
                carry = r/10;
            }
            if(carry >= 1) curr = carry + curr;
            for(int k = 0;k<num2.length()-i-1;k++) curr += 0; // adding trailing zeros
            prev = addStrings(prev , curr);
            curr = "";
        }
        return prev;
    }

    // 537. Complex Number Multiplication

    public String complexNumberMultiply(String a, String b) {
        String x[] = a.split("\\+|i");
        String y[] = b.split("\\+|i");
        int a_real = Integer.parseInt(x[0]);
        int a_img = Integer.parseInt(x[1]);
        int b_real = Integer.parseInt(y[0]);
        int b_img = Integer.parseInt(y[1]);
        return (a_real * b_real - a_img * b_img) + "+" + (a_real * b_img + a_img * b_real) + "i";

    }

    // valid palindrome 2 leetcode 

    // "lcupuuffuupucul" important test case
    public boolean validAfterSkipping(String s , int i , int j)
    {
        while(i<=j)
        {
            if(s.charAt(i) == s.charAt(j))
            {
                i++;
                j--;
            }
            else return false;
        }
        return true;
    }
    public boolean validPalindrome(String s) 
    {
        int i = 0;
        int j = s.length()-1;
        while(i <= j)
        {
            if(s.charAt(i) == s.charAt(j))
            {
                i++;
                j--;
            }
            else if(s.charAt(i) != s.charAt(j))
            {
                if(validAfterSkipping(s ,i+1,j) || validAfterSkipping(s,i,j-1)) return true;
                return false;
            }
        }
        return true;
    }

    // 795. Number of Subarrays with Bounded Maximum
    public int numSubarrayBoundedMax(int[] arr, int left, int right) 
    {
        int si = 0;
        int ei = 0;
        int count = 0;
        int prevc = 0;

        while (ei < arr.length) 
        {
          if (arr[ei] >= left && arr[ei] <= right) 
          {
            count += ei - si + 1;
            prevc = ei - si + 1;
          } 
          else if (arr[ei] < left) 
          {
            count += prevc;
          } 
          else 
          {
            prevc = 0;
            si = ei + 1;
          }
          ei++;
        }
        return count;
    }

    // LEETCODE 670 MAXIMUM SWAP

    public int maximumSwap(int num) 
    {
        LinkedList<Integer> nums = new LinkedList<>();
        while(num>0){
            nums.addFirst(num%10);
            num = num/10;
        }
        int[] check = new int[10];
        for(int i = 0;i<nums.size();i++){
            check[nums.get(i)] = i;
        }
        boolean ter = false;
        for(int i = 0;i<nums.size();i++){
            for(int j = 9;j>=0;j--)
            {
                if(nums.get(i) < j && check[j] > i)
                {
                    int temp = nums.get(check[j]);
                    nums.set(check[j],nums.get(i));
                    nums.set(i,temp);
                    ter = true;
                    break;
                }
            }
            if(ter) break;
        }
        int ans = 0;
        for(int ele : nums){
            ans = ans*10+ele;
        }
        return ans;
    }

    // MINIMUM DOMINOES SWAP LEETCODE 1007

    public int minDominoRotations(int[] tops, int[] bottoms) 
    {
        int count1 = 0, count2 = 0, count3 = 0, count4 = 0;

        int num1 = tops[0];
        int num2 = bottoms[0];

        for (int i = 0; i < tops.length; i++) {
          // count 1 is count of rotation if top array have num1
          if (count1 != Integer.MAX_VALUE) {
            if (tops[i] == num1) {
              // nothing to do for count
            } else if (bottoms[i] == num1) {
              count1++;
            } else {
              count1 = Integer.MAX_VALUE;
            }
          }

          // count 2 is count of rotation if bottom array have num1
          if (count2 != Integer.MAX_VALUE) {
            if (bottoms[i] == num1) {
              // nothing to do
            } else if (tops[i] == num1) {
              count2++;
            } else {
              count2 = Integer.MAX_VALUE;
            }
          }

          // count 3 is count of rotation if top array have num2
          if (count3 != Integer.MAX_VALUE) {
            if (tops[i] == num2) {
              // nothing to do for count
            } else if (bottoms[i] == num2) {
              count3++;
            } else {
              count3 = Integer.MAX_VALUE;
            }
          }

          // count 4 is count of rotation if bottom array have num2
          if (count4 != Integer.MAX_VALUE) {
            if (bottoms[i] == num2) {
              // nothing to do
            } else if (tops[i] == num2) {
              count4++;
            } else {
              count4 = Integer.MAX_VALUE;
            }
          }
        }

        int ans = Math.min(Math.min(count1, count2), Math.min(count3, count4));

        return ans == Integer.MAX_VALUE ? -1 : ans;
  }

    // LEETCODE 881 BOATS TO SAVE PEOPLE

    public int numRescueBoats(int[] people, int limit) 
    {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int ans = 0;
        while (i <= j) 
        {
            ans++;
            if (people[i] + people[j] <= limit) i++;
            j--;
        }
        return ans;
    }

    // 2SUM , 3SUM , 4 SUM ALREADY SUBMITTED AND STORED IN OTHER FILES AND NOTES

    // KSUM PEPCODING GENERIC

    public static List<List<Integer>> kSum(int[] arr, int target , int k) 
    {
        Arrays.sort(arr);
        // int k = 4;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        sum(arr,0,k,target,temp,ans);
        return ans;
    }
    
    public static ArrayList<int[]> sum_2(int[] arr , int target , int i)
    {
        int j = arr.length-1;
        ArrayList<int[]> ans = new ArrayList<>();
        while(i<j)
        {
            if(arr[i] + arr[j] == target){
                int[] a = new int[2];
                a[0] = arr[i];
                a[1] = arr[j];
                ans.add(a);
                i++;
                while(i < arr.length && arr[i] == arr[i-1]) i++;
                j--;
                while(j >= 0 && arr[j] == arr[j+1]) j--;
            }
            else if(arr[i] + arr[j] > target) j--;
            else i++;
        }
        return ans;
    }
    
    public static void sum(int[] arr , int i , int k , int target , List<Integer> temp , List<List<Integer>> ans)
    {
        if(k == 2)
        {
            ArrayList<int[]> t = sum_2(arr , target , i);
            for(int j = 0;j<t.size();j++)
            {
                int[] h = t.get(j);
                temp.add(h[0]);
                temp.add(h[1]);
                ArrayList<Integer> temp2 = new ArrayList<>();
                for(int ele : temp) temp2.add(ele);
                ans.add(temp2);
                temp.remove(temp.size()-1);
                temp.remove(temp.size()-1);
            }
            return;
        }
        int j = i;
        while(j < arr.length)
        {
            temp.add(arr[j]);
            sum(arr,j+1,k-1,target-arr[j],temp,ans);    
            temp.remove(temp.size()-1);
            j++;
            while(j < arr.length && arr[j] == arr[j-1]) j++;
        }
    }

    // 119. Pascal's Triangle II

    public List<Integer> getRow(int rowIndex) 
    {
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();
        a1.add(1);
        
        while(rowIndex > 0)
        {
            a2.add(1);
            for(int i = 0;i<a1.size()-1;i++){
                a2.add(a1.get(i) + a1.get(i+1));
            }
            a2.add(1);
            a1 = a2;
            a2 = null;
            a2 = new ArrayList<>();
            rowIndex--;
        }
        return a1;
    }


    // LEETCODE 41 FIRST MISSING POSITIVE

    public int firstMissingPositive(int[] nums) 
    {
        boolean one = false;
        
        for(int i = 0;i<nums.length;i++)
        {   if(nums[i] == 1) one = true;
            if(nums[i]<=0 || nums[i]>nums.length){
                nums[i] = 1;
            }
        }

        if(!one) return 1;
        
        for(int i = 0;i<nums.length;i++)
        {
            int idx = Math.abs(nums[i]);
            nums[idx-1] = -Math.abs(nums[idx - 1]);
        }

        for(int i = 0;i<nums.length;i++)
        {
            if(nums[i]>0){
                return i+1;
            }
        }
        return nums.length+1;
    }


    // LEETCODE FIND ALL DUPLICATES

    public List<Integer> findDuplicates(int[] nums) 
    {
        List<Integer> ans = new ArrayList<>();
        
        for(int i= 0;i<nums.length;i++)
        {
            int ele = Math.abs(nums[i]);
            if(nums[ele-1] < 0) ans.add(ele);
            nums[ele-1] = -1 * Math.abs(nums[ele-1]);
        }
        
        return ans;
    }

    // 890. Find and Replace Pattern

    public boolean isMatch(String two , int[]s1)
    {
        HashMap<Character , Integer> map = new HashMap<>();
        int[] s2 = new int[two.length()];
        
        for(int i = 0;i<two.length();i++)
        {
            if(!map.containsKey(two.charAt(i))){
                map.put(two.charAt(i),i);
            }
            s2[i] = map.get(two.charAt(i));
        }
        
        for(int i = 0;i<s1.length;i++)
        {
            if(s1[i]!=s2[i]){
                return false;
            }
        }
        return true;
    }
    public List<String> findAndReplacePattern(String[] words, String pattern) 
    {
        List<String> ans = new ArrayList<>();
        int[] s1 = new int[pattern.length()];
        HashMap<Character , Integer> map = new HashMap<>();
        for(int i = 0;i<pattern.length();i++)
        {
            if(!map.containsKey(pattern.charAt(i))){
                map.put(pattern.charAt(i),i);
            }
            s1[i] = map.get(pattern.charAt(i));
        }
        
        for(String ele : words){
            if(ele.length() == pattern.length()){
                if(isMatch(ele,s1)) ans.add(ele);
            }
        }
        return ans;
    }
    
    // // LEETCODE 829. Consecutive Numbers Sum

    // PFX APPROACH
    // public int consecutiveNumbersSum(int n) 
    // {
    //     HashSet<Integer> map = new HashSet<>();
    //     map.add(0);
    //     int pfx = 0;
    //     for(int i = 1;i<=n;i++){
    //         pfx += i;
    //         map.add(pfx);
    //     }
    //     int count = 0;
    //     for(int ele : map){
    //         if(map.contains(ele-n)) count++;
    //     }
    //     return count;
    // }

    public int consecutiveNumbersSum(int n) 
    {
        int ans = 0;
        for (int k = 1; k * (k - 1) < 2 * n; k++) 
        {
          int numerator = n - (k * (k - 1) / 2);
          if (numerator % k == 0)  ans++;
        }
        return ans;
    }


    // push dominoes leetcode
    
    public void rearrange(StringBuilder sb , int i , int j)
    {
        char one = sb.charAt(i);
        char two = sb.charAt(j);
        
        if(one == 'L' && two == 'L'){
            i++;
            while(i < j){
                sb.setCharAt(i++,'L');
            }
        }
        else if(one == 'R' && two == 'R'){
            i++;
            while(i < j){
                sb.setCharAt(i++,'R');
            }
        }
        else if(one == 'L' && two == 'R'){
            // do nothing;
        }
        else if(one == 'R' && two == 'L')
        {
            if((j-i-1)%2 == 0)
            {
                int last = i + (j-i-1)/2;
                i++;
                while(i<=last)
                {
                    sb.setCharAt(i++,'R');
                }
                while(i < j)
                {
                    sb.setCharAt(i++,'L');
                }
            }
            else
            {
                int idx = i+(j-i)/2;
                i++;
                while(i < idx){
                    sb.setCharAt(i++,'R');
                }
                i++;
                while(i < j)
                {
                    sb.setCharAt(i++,'L');
                }
            }
        }
    }
    
    public String pushDominoes(String dominoes) 
    {
        dominoes = 'L' + dominoes + 'R';
        StringBuilder sb = new StringBuilder(dominoes);
        int i = 0;
        int j = 1;
        while(j < sb.length())
        {
            if(sb.charAt(j) == 'L' ||sb.charAt(j) == 'R')
            {
                rearrange(sb,i,j);
                i = j;
            }
            j++;
        }
        
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }


    // MAXIMUM PRODUCT SUM SUBARRAY LEETCODE

    public int maxProduct(int[] arr) 
    {
        if(arr.length == 1) return arr[0];
        long min = 1;
        long max = 1;
        long ans = 0;
        for(int i = 0;i<arr.length;i++)
        {
            if(arr[i] == 0)
            {
                min = 1;
                max = 1;
                continue;
            }
            long temp1 = Math.min(arr[i] ,Math.min(arr[i]*min , arr[i]*max));
            long temp2 = Math.max(arr[i] ,Math.max(arr[i]*min , arr[i]*max));
            min = temp1;
            max = temp2;
            ans = Math.max(ans,max);
        }
        return (int)ans;
    }

    // 891. Sum of Subsequence Widths
    public int sumSubseqWidths(int[] A) {
        int MOD = 1_000_000_007;
        int N = A.length;
        Arrays.sort(A);

        long[] pow2 = new long[N];
        pow2[0] = 1;
        for (int i = 1; i < N; ++i)
            pow2[i] = pow2[i-1] * 2 % MOD;

        long ans = 0;
        for (int i = 0; i < N; ++i)
            ans = (ans + (pow2[i] - pow2[N-1-i]) * A[i]) % MOD;

        return (int) ans;
    }

    // 1415. The k-th Lexicographical String of All Happy Strings of Length n 

    // RECURSIVE SOLUTION

    public void generateStrings(int n, int m, String ans , List<String> allStrs)
    {
        if(n == 0){
            allStrs.add(ans);
            return;
        }
        int start = (int)('a');
        for(int i=0;i<m;i++)
        {
            if(ans.length() == 0) generateStrings(n-1,m,ans + (char)(start+i),allStrs);
            else if(ans.charAt(ans.length()-1)!= (char)(start+i)) generateStrings(n-1,m,ans + (char)(start+i),allStrs);
        }
    }
    public String getHappyString(int n, int k) 
    {
        List<String> allStrs = new ArrayList<>();
        generateStrings(n,3,"",allStrs);
        if(k > allStrs.size()) return "";
        return allStrs.get(k-1);
    }

    // ITERATIVE SOLUTION

    public String getHappyString(int n, int k) {
        k--;
        int permutations = (int)Math.pow(2, n - 1);
        if(k >= 3 * permutations)//k is too large
        {
            return "";
        }
        int last = -1; //in order to know what last digit i used (could just check in the string)
        StringBuilder sb = new StringBuilder();
        if(k < permutations){
            sb.append('a');
            last = 1;
        }
        else if(k < permutations * 2)
        {
            sb.append('b');
            last = 2;
        }
        else{
            sb.append('c');
            last = 3;
        }
        for(int i = 1; i < n; i++){
            k %= permutations;
            permutations /= 2;
            if(last == 1){
                if(k < permutations){
                    sb.append('b');
                    last = 2;
                }
                else{
                    sb.append('c');
                    last = 3;
                }
            }
            else if(last == 2){
                if(k < permutations){
                    sb.append('a');
                    last = 1;
                }
                else{
                    sb.append('c');
                    last = 3;
                }
            }
            else{
                if(k < permutations){
                    sb.append('a');
                    last = 1;
                }
                else{
                    sb.append('b');
                    last = 2;
                }
            }
        }
        return sb.toString();
    }

    // LEETCODE 1354 IMPORTANT WATCH VIDEO IN DRY RUN

    public boolean isPossible(int[] target) 
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return b-a;
        });
        
        int sum = 0;
        for(int ele : target){
            sum+=ele;
            pq.add(ele);
        }
        
        while(pq.size()!=0)
        {
            int ele = pq.poll();
            if(ele == 0) return false;
            if(ele == 1) return true;
            int remain = sum-ele;
            if(remain == 1) return true;
            if(remain >= ele || remain <= 0) return false;
            int updateVal = ele%remain;
            if(updateVal == 0) return false;
            sum = remain + (ele % remain);
            pq.add(ele % remain);
        }
        return true;
    }
    
    // 1642. Furthest Building You Can Reach

    public int furthestBuilding(int[] heights, int bricks, int ladders) 
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        while(i < heights.length-1)
        {
            int diff = heights[i+1] - heights[i];
            if(diff<=0){
                i++;
                continue;
            }
            pq.add(diff);
            if(pq.size() > ladders){
                int ele = pq.poll();
                if(bricks-ele >= 0){
                    bricks = bricks - ele;
                }
                else return i;
            }
            i++;
        }
        return (i>=heights.length) ? i-1 : i;
    }


    // WAYS TO MAKE A FAIR ARRAY

    public int waysToMakeFair(int[] nums) 
    {
        int n = nums.length;
        if(n == 1) return 1;
        int l1 = n/2;
        int sum = 0;
        for(int ele : nums) sum+=ele;
        
        int[] odd = new int[l1];
        int[] even = new int[n-l1];
        
        int ans = 0;
        
        int j = 0;
        int k = 0;
        for(int i = 0;i<nums.length;i++)
        {
            if(i%2==0)
            {
                even[j] = nums[i] + ((j-1 >= 0) ? even[j-1] : 0);
                j++;
            }
            else if(i%2!=0)
            {
                odd[k] = nums[i] + ((k-1 >= 0) ?  odd[k-1] : 0);
                k++;
            }
        }
    
        for(int i = 0;i<n;i++)
        {
            if(i%2==0)
            {
                if(i == 0)
                {
                    int evenSum = odd[l1-1];
                    int oddSum = even[n-l1-1] - even[0];
                    if(evenSum == oddSum) ans++;
                }
                else
                {
                    int evenSum = even[(i/2) - 1] + odd[l1-1] - odd[(i/2)-1];
                    int oddSum = odd[(i/2) - 1] + even[n-l1-1] - even[(i/2)-1] - nums[i];
                    if(evenSum == oddSum) ans++;
                }
            }
            else
            {
                if(i == 1)
                {
                    int evenSum = even[(i-1)/2] + odd[l1-1] - nums[i];
                    int oddSum = even[n-l1-1] - even[0];
                    if(evenSum == oddSum) ans++;
                }
                else
                {
                    int evenSum = even[(i-1)/2] + odd[l1-1] - odd[((i-1)/2)-1] - nums[i];
                    int oddSum = odd[((i-1)/2) -1] + even[n-l1-1] - even[(i-1)/2];
                    if(evenSum == oddSum) ans++;
                }
            }
        }
        return ans;
    }

    // 1329. Sort the Matrix Diagonally

    // USING COUNT SORT O(N) TIME AND O(101) SPACE

    public int[] count = new int[101];
    public void countSort(int[][] mat , int i , int j)
    {
        int k = j;
        int l = i;
        for(;k<mat[0].length && l<mat.length;l++,k++)
        {
            int val = mat[l][k];
            count[val]++;
        }
        for(int g = 0;g<101;g++)
        {
            while( count[g] > 0 && i < mat.length && j < mat[0].length){
                mat[i++][j++] = g;
                count[g]--;
            }
        }
        count = new int[101];
    }
    
    public int[][] diagonalSort(int[][] mat) 
    {
        for(int j = 0;j<mat[0].length;j++){
            countSort(mat,0,j);       
        }
        for(int i = 1;i<mat.length;i++){
            countSort(mat,i,0);
        }
        return mat;
    }

    // 1529. Bulb Switcher IV

    public int minFlips(String target) 
    {
        char toggle = '0';
        int ans = 0;
        for(int i = 0;i<target.length();i++)
        {
            if(target.charAt(i) == toggle) continue;
            else
            {
                ans++;
                toggle = (toggle == '0') ? '1' : '0';
            }
        }
        return ans;
    }

    // ISLAND PERIMETER LEETCODE 463

    public int islandPerimeter(int[][] grid) 
    {
        int para = 0;
        for(int i = 0;i<grid.length;i++)
        {
            for(int j = 0;j<grid[0].length;j++)
            {
                if(grid[i][j] == 1) para += 4 - findPara(grid,i,j);
            }
        }
        
        return para;
    }
    public int findPara(int[][] grid , int i, int j)
    {
        int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
        int n = 0;
        
        for(int[] d : dir)
        {
            int r = i+d[0];
            int c = j+d[1];
            
            if(r>=0 && c>=0 && r < grid.length && c < grid[0].length)
            {
                if(grid[r][c] == 1)
                {
                    n++;
                }
            }
        }
        
        return n;
    }

    // 419. Battleships in a Board

    public int countBattleships(char[][] board) 
    {
        int ans = 0;
        for(int i = 0;i<board.length;i++)
        {
            for(int j = 0;j<board[0].length;j++)
            {
                if(board[i][j] == 'X')
                {
                    boolean left = false;
                    boolean up = false;
                
                    if(j-1 >= 0){
                        if(board[i][j-1] != 'X'){
                            left = false;
                        }
                        else left = true;
                    }

                    if(i-1 >= 0){
                        if(board[i-1][j] != 'X'){
                            up = false;
                        }
                        else up = true;
                    }

                    if(!left && !up) ans++;
                }
            }
        }
        return ans;
    }

    // 56. Merge Intervals


    public int[][] merge(int[][] intervals) 
    {
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        ArrayList<int[]> ans = new ArrayList<>();
        ans.add(new int[]{intervals[0][0],intervals[0][1]});
        
        for(int i = 1;i<intervals.length;i++)
        {
            int[] test = ans.get(ans.size()-1); 
            int a = test[0];
            int b = test[1];
            int c = intervals[i][0];
            int d = intervals[i][1];
            
            if(c<=b){
                ans.remove(ans.size()-1);
                ans.add(new int[]{Math.min(a,c),Math.max(b,d)});
            }
            else{
                ans.add(new int[]{c,d});
            }
        }
        int[][] ans2 = new int[ans.size()][2];
        for(int i = 0;i<ans.size();i++)
        {
            int[] temp = ans.get(i);
            ans2[i][0] = temp[0];
            ans2[i][1] = temp[1];
        }
        
        return ans2;
    }

    // Minimum Platforms Geeks for Geeks

    static int findPlatform(int arr[], int dep[], int n)
    {
        Arrays.sort(arr);
        Arrays.sort(dep);
        
        int count = 0;
        int stations = 0;
        
        int i = 0;
        int j = 0;
        
        while(i<arr.length)
        {
            int a = arr[i];
            int d = dep[j];
            
            if(a<=d)
            {
                i++;
                count++;
                stations = Math.max(stations,count);
            }
            else if(a>=d)
            {
                j++;
                count--;
            }
            
        }
        return stations;
    }

    // Meeting Rooms I Leetcode Locked Submitted on Pepcoding Portal
    public static boolean meetingRooms(int intervals[][])
    {
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        for(int i = 1;i<intervals.length;i++)
        {
            int a = intervals[i-1][0];
            int b = intervals[i-1][1];
            int c = intervals[i][0];
            int d = intervals[i][1];
            
            if((c>a&&c<b)||(d>a&&d<b)||(a>c&&b<d)) return false;
        }
        return true;    
    }


    // MEETING ROOMS 2 LEETCODE LOCKED SUBMITTED ON PEPCODING
    public static int meetingRooms(int intervals[][]) 
    {
      if(intervals.length == 0) return 0;
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
      int count = 1;
      pq.add(intervals[0][1]);
      for(int i = 1;i < intervals.length; i++)
      {
          int start = intervals[i][0];
          int peek = (pq.size() > 0) ? pq.peek() : 0;
          int end = intervals[i][1];
          if(start < peek)
          {
            pq.add(end);
            count = Math.max(count,pq.size());
          }
          else{
              while(pq.size() > 0 && start >= pq.peek()) pq.remove();
              pq.add(end);
              count = Math.max(count,pq.size());
          }
          
      }
      return count;
    }

    // 986. Interval List Intersections

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList)
    {
        ArrayList<int[]> ans = new ArrayList<>();
        int i = 0;
        int j = 0;
        
        while(i<firstList.length && j<secondList.length)
        {
            int a = firstList[i][0];
            int b = firstList[i][1];
            int c = secondList[j][0];
            int d = secondList[j][1];
            
            if(c>=a && b<=d && c<=b)
            {
                ans.add(new int[]{c,b});
                i++;
            }
            else if(a>=c && d<=b && a<=d)
            {
                ans.add(new int[]{a,d});
                j++;
            }
            else if(c >= a && d <= b)
            {
                ans.add(new int[]{c,d});
                j++;
            }
            else if(a >= c && b <= d)
            {
                ans.add(new int[]{a,b});
                i++;
            }
            else if(b == c)
            {
                ans.add(new int[]{b,b});
                i++;
            }
            else if(a == d)
            {
                ans.add(new int[]{a,a});
                j++;
            }
            else if(a > d)
            {
                j++;
            }
            else if(c > b)
            {
                i++;
            }
        }
        
        int[][] ans2 = new int[ans.size()][2];
        for(int k = 0;k<ans.size();k++)
        {
            int[] temp = ans.get(k);
            ans2[k][0] = temp[0];
            ans2[k][1] = temp[1];
        }
        
        return ans2;
    }
    // SIMPLE SOLUTION OF THE ABOVE PROBLEM
    public int[][] intervalIntersection(int[][] A, int[][] B)
    {
        List<int[]> ans = new ArrayList();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) 
        {
          int lo = Math.max(A[i][0], B[j][0]);
          int hi = Math.min(A[i][1], B[j][1]);
          if (lo <= hi)
            ans.add(new int[]{lo, hi});

          if (A[i][1] < B[j][1])
            i++;
          else
            j++;
        }

        return ans.toArray(new int[ans.size()][]);
    }

    // INSERT INTERVALS LEETCODE 57
    // INSERT INTERVALS SIMPLE CODE

    public int[][] insert(int intervalList[][], int newInterval[])
    {
        ArrayList<int[]> ans = new ArrayList<>();
        int idx = 0;
        while (idx < intervalList.length) 
        {
          if (intervalList[idx][0] < newInterval[0]) 
          {
            ans.add(intervalList[idx]);
            idx++;
          } 
          else 
          {
            break;
          }
        }

        if (ans.size() == 0 || newInterval[0] > ans.get(ans.size() - 1)[1]) 
        {
          ans.add(newInterval);
        } 
        else 
        {
          ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], newInterval[1]);
        }

        while (idx < intervalList.length) 
        {
          int lastInterval[] = ans.get(ans.size() - 1);
          if (intervalList[idx][0] <= lastInterval[1]) 
          {
            lastInterval[1] = Math.max(lastInterval[1], intervalList[idx][1]);
          } 
          else 
          {
            ans.add(intervalList[idx]);
          }
          idx++;
        }
        return ans.toArray(new int[ans.size()][]);
  }

  // 452. Minimum Number of Arrows to Burst Balloons

    public int findMinArrowShots(int[][] points) 
    {
        Arrays.sort(points,(a,b)->Integer.compare(a[0],b[0]));
        int[] lastMerge = points[0];
        int count = 1;
        for(int i = 1;i<points.length;i++)
        {
            int lo = Math.max(lastMerge[0],points[i][0]);
            int hi = Math.min(lastMerge[1],points[i][1]);
            if(lo <= hi)
            {
                lastMerge[0] = lo;
                lastMerge[1] = hi;
            }
            else
            {
                lastMerge = points[i];
                count++;
            }
        }
        return count;
    }

    // 1094. Car Pooling

    public boolean carPooling(int[][] trips, int capacity) 
    {
        int[] arr = new int[1001];
        for(int i = 0;i<trips.length;i++)
        {
            int num = trips[i][0];
            int start = trips[i][1];
            int end = trips[i][2];
            
            arr[start] += num;
            arr[end] += -1*num;
        }
        int sum = 0;
        for(int i = 0;i<1001;i++)
        {
            sum+=arr[i];
            if(sum>capacity) return false;
        }
        return true;
    }

    // 643. Maximum Average Subarray I

    public double findMaxAverage(int[] nums, int k) 
    {
        int i = 0;
        int j = 0;
        double ans = 0;
        double sum = 0;
        while(j<k) sum+=nums[j++];
        ans = sum/k;
        while(j < nums.length)
        {
            sum = sum - nums[i] + nums[j];
            ans = Math.max(ans,sum/k);
            i++;
            j++;
        }
        return ans;
    }

    // 1750. Minimum Length of String After Deleting Similar Ends

    public int minimumLength(String s) 
    {
        int i = 0;
        int j = s.length()-1;
        
        while(i<j)
        {
            if(s.charAt(i) == s.charAt(j))
            {
                i++;
                j--;
                while(i < s.length() && s.charAt(i) == s.charAt(i-1)) i++;
                while(j>=0 && s.charAt(j) == s.charAt(j+1)) j--;
            }
            else break;
        }
        
        return (j-i+1 > 0) ? j-i+1 : 0;
    }

    // SET MATRIX ZERO MOST OPTIMIZED SOLUTION LEETCODE
    
    public void setZeroes(int[][] matrix) {

    int n = matrix.length;
    int m = matrix[0].length;

    boolean row = false, col = false;

    for (int i = 0; i < n; i++)if (matrix[i][0] == 0)col = true;
    for (int j = 0; j < m; j++)if (matrix[0][j] == 0)row = true;

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (matrix[i][j] == 0) {
          matrix[0][j] = 0;
          matrix[i][0] = 0;
        }
      }
    }

    for (int i = 1; i < n; i++) {
      if (matrix[i][0] == 0) {
        for (int j = 0; j < m; j++) {
          matrix[i][j] = 0;
        }
      }
    }

    for (int j = 1; j < m; j++) {
      if (matrix[0][j] == 0) {
        for (int i = 0; i < n; i++) {
          matrix[i][j] = 0;
        }
      }
    }

    if (row) {
      for (int j = 0; j < m; j++) {
        matrix[0][j] = 0;
      }
    }
    if (col) {
      for (int i = 0; i < n; i++) {
        matrix[i][0] = 0;
      }
    }
  }

  // 347. Top K Frequent Elements

  public int[] topKFrequent(int[] nums, int k)
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        
        for(int i = 0;i<nums.length;i++)
        {
            map.put(nums[i],map.getOrDefault(nums[i],0) + 1);
        }
        int j = 0;
        while(j < nums.length)
        {
            if(map.containsKey(nums[j]))
            {
                pq.add(new int[]{nums[j],map.get(nums[j])});
                map.remove(nums[j]);
            }
            
            if(pq.size()>k)
            {
                pq.remove();
            }
            j++;
        }
        
        int[] arr = new int[pq.size()];
        int l = 0;
        while(pq.size()!=0)
        {
            arr[l++] = (pq.remove())[0];
        }
        return arr;
    }

    // WORD SEARCH LEETCODE

    public boolean explore(char[][] board , int k , String word , int i , int j, int[][]dir)
    {
        char ch = board[i][j];
        board[i][j] = '@';
        if(k == word.length()) return true;
        for(int[] ele : dir)
        {
            int x = i + ele[0];
            int y = j + ele[1];
            
            if(x < board.length && x >=0 && y>=0 && y < board[0].length && board[x][y] == word.charAt(k))
            {
                boolean res = explore(board,k+1,word,x,y,dir);
                if(res) return true;
            }
        }
        board[i][j] = ch;
        return false;
    }
    public boolean exist(char[][] board, String word) 
    {
        int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        boolean res = false;
        for(int i = 0;i<board.length;i++)
        {
            for(int j = 0;j<board[0].length;j++)
            {
                if(board[i][j] == word.charAt(0) && !res) res =  explore(board,1,word,i,j,dir);
                if(res) return true;
            }
        }
        return false;
    }
}