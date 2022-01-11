public class pepYtListSearchingSorting 
{
    // Marks of PCM

    public class Marks implements Comparable<Marks>
    {
        int phy;
        int chem;
        int math;
        Marks(){
        }
        
        Marks(int phy , int chem , int math){
            this.phy = phy;
            this.chem = chem;
            this.math = math;
        }
        @Override
        public int compareTo(Marks o)
        {
            if(this.phy != o.phy) return this.phy-o.phy;
            else if(this.chem!=o.chem) return o.chem-this.chem;
            else return this.math-o.math;
        }
    }
    public void customSort (int phy[], int chem[], int math[], int N)
    {
        Marks[] arr = new Marks[phy.length];
        
        for(int i = 0;i<phy.length;i++)
        {
            arr[i] = new Marks(phy[i],chem[i],math[i]);
        }
        
        Arrays.sort(arr);
        
        for(int i = 0;i< phy.length;i++)
        {
            Marks temp = arr[i];
            phy[i] = temp.phy;
            chem[i] = temp.chem;
            math[i] = temp.math;
        }
    }

    // Union of Two Sorted Arrays 

    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0;
        int j = 0;
        
        while(i < n && j < m)
        {
            if(arr1[i] == arr2[j])
            {
                if(ans.size() > 0 && ans.get(ans.size()-1) != arr1[i]) ans.add(arr1[i]);
                else if(ans.size() == 0) ans.add(arr1[i]);
                i++;
                j++;
            }
            else if(arr1[i] < arr2[j])
            {
                if(ans.size() > 0 && ans.get(ans.size()-1) != arr1[i]) ans.add(arr1[i]);
                else if(ans.size() == 0) ans.add(arr1[i]);
                i++;
            }
            else if(arr2[j] < arr1[i])
            {
                if(ans.size() > 0 && ans.get(ans.size()-1) != arr2[j]) ans.add(arr2[j]);
                else if(ans.size() == 0) ans.add(arr2[j]);
                j++;
            }
        }
        
        while(i < n)
        {
            if(ans.size() > 0 && ans.get(ans.size()-1) != arr1[i]) ans.add(arr1[i]);
            else if(ans.size() == 0) ans.add(arr1[i]);
            i++;    
        }
        
        while(j < m)
        {
            if(ans.size() > 0 && ans.get(ans.size()-1) != arr2[j]) ans.add(arr2[j]);
            else if(ans.size() == 0) ans.add(arr2[j]);
            j++;
        }
        
        return ans;
    }

    // Search in 2D Matrix 
    // Using Binary Search

    public boolean searchMatrix(int[][] matrix, int target) 
    {
        if(matrix.length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = m*n;
        
        while(i<j)
        {
            int mid = (i+j)/2;
            if(matrix[mid/m][mid%m] == target) return true;
            else if(matrix[mid/m][mid%m] > target) j = mid;
            else i = mid + 1;
        }
        
        return false;
    }

    // 74. Search a 2D Matrix

    public boolean searchMatrix(int[][] matrix, int target)
    {
        if(matrix.length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = m*n-1;
        
        while(i<=j)
        {
            int mid = (i+j)/2;
            if(matrix[mid/m][mid%m] == target) return true;
            else if(matrix[mid/m][mid%m] > target) j = mid-1;
            else i = mid + 1;
        }
        return false;
    }


    // 240. Search a 2D Matrix II
    // Important Sawal
    public boolean searchMatrix(int[][] matrix, int target) 
    {
        if (matrix == null || matrix.length == 0) return false;
        int i = 0;
        int j = matrix[i].length - 1;
        while (i < matrix.length && j >= 0) 
        {
            int currVal = matrix[i][j];
            if (currVal == target) return true; 
            else if (target < currVal) j--;
            else i++;
        }
        return false;
    }


    // 724. Find Pivot Index

    public int pivotIndex(int[] nums) 
    {
        int sum = 0;
        int rsum = 0;
        for(int ele : nums) rsum+= ele;
        
        for(int i = 0;i<nums.length;i++)
        {
            int val = nums[i];
            rsum-=val;
            if(sum == rsum) return i;
            sum+= val;
        }
        return -1;
    }

    // 658. Find K Closest Elements

    public int searchIdx(int[] arr , int x)
    {
        if(x <= arr[0]) return 0;
        if(x >= arr[arr.length-1]) return arr.length-1;
        int possibleMid = 0;
        int possibleIdx = 0;
        int i = 0;
        int j = arr.length-1;
        
        while(i < j)
        {
            int mid = (i + j)/2;
            if(arr[mid] == possibleMid && possibleIdx == mid)
            {
                break;
            }
            if(arr[mid] <= x)
            {
                i = mid;
                possibleMid = arr[mid];
                possibleIdx = mid;
            }
            else j = mid-1;
        }
        return i;
    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) 
    {
        List<Integer> ans = new ArrayList<>();
        int idx = searchIdx(arr,x);
        int i = idx;
        int j = idx + 1;
        while(i >= 0 && j < arr.length && k > 0){
            int diff1 = x-arr[i];
            int diff2 = arr[j]-x;
            
            if(diff1 <= diff2)
            {
                ans.add(0,arr[i--]);
                k--;
            }
            else{
                ans.add(arr[j++]);
                k--;
            }
        }
        
        if(k > 0)
        {
            while(i >= 0 && k > 0)
            {
                ans.add(0,arr[i--]);
                k--;
            }
            while(j < arr.length && k > 0)
            {
                ans.add(arr[j++]);
                k--;
            }
        }
        return ans;
    }

    // Find Pair Given Difference 
    // Always Remember That In Case Of Difference we Always move both I and J pointers from Left to Right
    public boolean findPair(int arr[], int size, int N)
    {
        Arrays.sort(arr);
        int i = 0;
        int j = 1;
        while(i < arr.length && j < arr.length)
        {
            if(arr[j] - arr[i] == N){
                return true;
            }
            else if(arr[j] - arr[i] > N) i++;
            else j++;
        }
        
        return false;
    }

    // Roof Top 

    static int maxStep(int arr[], int n)
    {
       int step_count=0;
       int max_count=0;
       for(int i=0; i<n-1; i++)
       {
           if(arr[i]<arr[i+1]) step_count++;
           else{
               max_count=(step_count>max_count)?step_count:max_count;
               step_count=0;
           }
       }
       return Math.max(step_count,max_count);
   }


   // Maximize sum(arr[i]*i) of an Array 
   // the idea is to multiply minimum element with min idx and max element with max idx

   int Maximize(int arr[], int n) 
   {
       Arrays.sort(arr);
       long sum = 0;
       int mod = (int)1e9+7;
       for (int i = 0; i < n; i++) {
           sum = (sum + (long)arr[i] * i) % mod;

       }
       return (int) sum;
   }

   // Inversion Count

   public static long InversionAcrossArray(long[] arr, int l, int r, int mid, long[] sortedArray)
    {
        int lsi = l, lei = mid;
        int rsi = mid + 1, rei = r;

        int k = 0;
        long count = 0;
        while (lsi <= lei && rsi <= rei) {
            if (arr[lsi] > arr[rsi]) {
                count += (lei - lsi + 1);
                sortedArray[k++] = arr[rsi++];
            } else
                sortedArray[k++] = arr[lsi++];
        }

        while (lsi <= lei)
            sortedArray[k++] = arr[lsi++];
        while (rsi <= rei)
            sortedArray[k++] = arr[rsi++];

        // for (k = 0; k < sortedArray.length; k++)
        // arr[k + l] = sortedArray[k];

        k = 0;
        for (int i = l; i <= r; i++)
            arr[i] = sortedArray[k++];

        return count;
    }

    public static long inversionCount(long[] arr, int si, int ei, long[] sortedArray) {
        if (si >= ei)
            return 0;

        int mid = (si + ei) / 2;
        long ICL = inversionCount(arr, si, mid, sortedArray); // IC : Inversion Count, L = left , R = Right
        long ICR = inversionCount(arr, mid + 1, ei, sortedArray);

        return (ICL + ICR + InversionAcrossArray(arr, si, ei, mid, sortedArray));
    }

    public static long inversionCount(long arr[], long N) {
        if (N == 0)
            return 0;
        int n = (int)N;
        long[] sortedArray = new long[n];
        return inversionCount(arr, 0,n - 1, sortedArray);
    }

    // 34. Find First and Last Position of Element in Sorted Array

    public int[] searchRange(int[] arr, int target) 
    {
        if(arr.length == 0) return new int[]{-1,-1};
        int[] res = new int[2];
        res[0] = first(arr,target,0,arr.length-1);
        res[1] = last(arr,target,0,arr.length-1);
        return res;
    }
    public static int first(int[] arr , int tar , int si , int ei)
    {
        while(si >= 0 && ei < arr.length && si <= ei)
        {
            int mid = (ei+si)/2;
            if(arr[mid] == tar)
            {
                if(mid-1 >= 0 && arr[mid-1] == tar) ei = mid-1;
                else return mid;
            }
            else if(arr[mid] < tar) si = mid+1;
            else ei = mid-1;
        }
        
        return -1;
    }
    public static int last(int[] arr , int tar , int si , int ei)
    {
        while(si >= 0 && ei < arr.length && si <= ei)
        {
            int mid = (ei+si)/2;
            if(arr[mid] == tar)
            {
                if(mid + 1 <= arr.length-1 && arr[mid+1] == tar) si = mid+1;
                else return mid;                
            }
            else if(arr[mid] < tar) si = mid+1;
            else ei = mid-1;
        }
        
        return -1;
    }

    // Max sum in the configuration 

    int max_sum(int A[], int n)
    {
        int sum = 0;
        int s = 0;
        int ans = -(int)1e9;
        
        for(int i = 0;i<n;i++){
            sum += i*A[i];
            s += A[i];
        }
        ans = sum;
        
        for(int i = 1;i<=n;i++)
        {
            int temp = sum + s - n*A[n-i];
            sum = temp;
            // System.out.println(sum);
            ans = Math.max(ans,sum);
        }
        return ans;
    }

    // 33. Search in Rotated Sorted Array

    public int search(int[] nums, int target)
    {    
        int i = 0;
        int j = nums.length-1;
        while(i<=j)
        {
            int mid = (i+j)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] <= nums[j])
            {
                if(target > nums[mid] && target <= nums[j]) i = mid + 1;
                else j = mid - 1;
            }
            else if(nums[mid] >= nums[i])
            {
                if(target >= nums[i] && target < nums[mid]) j = mid-1;
                else i = mid + 1;
            }
        }
        return -1;
    }

    // 153. Find Minimum in Rotated Sorted Array

    public int findMin(int[] nums) 
    {
        int i = 0;
        int j = nums.length-1;
        int min = (int)1e9;
        while(i <= j)
        {
            int mid = (i+j)/2;
            if(nums[mid] <= nums[j])
            {
                min = Math.min(min,nums[mid]);
                j = mid-1;
            }
            else if(nums[mid] >= nums[i])
            {
                min = Math.min(min,nums[mid]);
                i = mid + 1;
            }
        }
        return min;
    }

    // Rotation Geeks For Geeks

    int findKRotation(int nums[], int n) 
    {
        int i = 0;
        int j = nums.length-1;
        int min = (int)1e9;
        int idx = 0;
        while(i <= j)
        {
            int mid = (i+j)/2;
            if(nums[mid] <= nums[j])
            {
                if(nums[mid] < min)
                {
                    min = nums[mid];
                    idx = mid;
                }
                j = mid-1;
            }
            else if(nums[mid] >= nums[i])
            {
                if(nums[mid] < min)
                {
                    min = nums[mid];
                    idx = mid;
                }
                i = mid + 1;
            }
        }
        return idx;   
    }


    // 4. Median of Two Sorted Arrays

    public double findMedianSortedArrays(int[] nums1, int[] nums2) 
    {
        if(nums1.length > nums2.length) return findMedianSortedArrays(nums2,nums1);
        int x = nums1.length;
        int y = nums2.length;
        int low = 0;
        int high = x;
        while(low<=high)
        {
            int partX =  (low+high)/2;
            int partY =  (x+y+1)/2 - partX;
            int xLeft = partX == 0 ? Integer.MIN_VALUE : nums1[partX-1];
            int xRight = partX == x ? Integer.MAX_VALUE : nums1[partX];
            int yLeft = partY == 0 ? Integer.MIN_VALUE : nums2[partY-1];
            int yRight = partY == y ? Integer.MAX_VALUE : nums2[partY];
            if(xLeft<=yRight && yLeft<=xRight)
            {
               if((x+y)%2==0)
               {
                   return ((double)Math.max(xLeft,yLeft) + Math.min(xRight,yRight))/2;
               }
               else
               {
                   return Math.max(xLeft,yLeft);
               } 
            }
            else if(xLeft>yRight)
            {
                high = partX -1;
            }
            else
            {
                low = partX+1;
            }
        }
        return 0.0;
    }

    // 875. Koko Eating Bananas

    public int minEatingSpeed(int[] piles, int h) 
    {
        return solve(piles,h);
    }
    
    public boolean possibleValue(int[]piles , int k , int h)
    {
        int c = 0;
        for(int ele : piles){
            if(ele <= k) c++;
            else{
                c += ele/k;
                if(ele%k != 0) c++;
            }
        }
        return (c <= h) ? true : false;
    }
    
    public int solve(int[]piles , int h){
        int i = 1;
        int j = (int)1e9;
        
        while(i < j){
            int mid = (i + j)/2;
            if(possibleValue(piles,mid,h)) j = mid;
            else i = mid + 1;
        }
        return i;
    }

    // 1283. Find the Smallest Divisor Given a Threshold

    public boolean possibleValue(int []nums , int threshold , int val)
    {
        int count = 0;
        for(int ele : nums)
        {
            if(ele <= val) count++;
            else
            {
                count += ele/val + ((ele%val == 0) ? 0 : 1);
            }
        }
        
        return (count <= threshold) ? true : false;
    }
    
    public int solve(int[] nums , int threshold)
    {
        int i = 1;
        int j = (int)1e9;
        
        while(i < j)
        {
            int mid = (i + j)/2;
            if(possibleValue(nums,threshold,mid)) j = mid;
            else i = mid + 1;
        }
        return j;
    }
    
    public int smallestDivisor(int[] nums, int threshold) 
    {
        return solve(nums , threshold);
    }


    // Chocolate Distribution Problem 

    // Note that Long value can be added,subtracted to integer but remember to typecast
    // Long  value can't be used as indexes
    public long findMinDiff (ArrayList<Long> a, long n, long m)
    {
       Collections.sort(a);
       int i=0;
       long ans=Long.MAX_VALUE;
       while(i<=n-m)
       {
           int j=(int)(i+m-1);
           ans=Math.min(ans,a.get(j)-a.get(i));
           i++;
       }
       return ans;
    }


    // Same type Problems Important

    // Allocate minimum number of pages 

    public static boolean check(int val , int[] a , int stu)
    {
        int sum = 0;
        int c = 1;
        for(int ele : a)
        {
            if(ele > val) return false;
            sum += ele;
            if(sum > val)
            {
                c++;
                sum = ele;
            }
            if(c > stu) return false;
        }
        return true;
    }
    public static int findPages(int[]a,int n,int m)
    {
        int ei = (int)1e9;
        int si = 1;
        
        while(si < ei)
        {
            int mid = (si+ei)/2;
            if(check(mid , a , m)) ei = mid;
            else si = mid + 1;
        }
        return si;
    }

    // 410. Split Array Largest Sum

    public static boolean check(int val , int[] a , int stu)
    {
        int sum = 0;
        int c = 1;
        for(int ele : a)
        {
            if(ele > val) return false;
            sum += ele;
            if(sum > val)
            {
                c++;
                sum = ele;
            }
            if(c > stu) return false;
        }
        return true;
    }
    public static int splitArray(int[]a,int m)
    {
        if(a.length == 1 && a[0] == 0) return 0;
        int ei = (int)1e9;
        int si = 1;
        
        while(si < ei)
        {
            int mid = (si+ei)/2;
            if(check(mid , a , m)) ei = mid;
            else si = mid + 1;
        }
        return ei;
    }



    // 1011. Capacity To Ship Packages Within D Days

    public static boolean check(int val , int[] weights , int days)
    {
        int sum = 0;
        int count = 1;
        for(int ele : weights)
        {
            if(ele > val) return false; //means val is small
            sum += ele;
            if(sum > val)
            {
                count++;
                sum = ele;
            }
            if(count > days) return false;// means val is small
        }
        return true;
        
    }
    public int shipWithinDays(int[] weights, int days) 
    {
        int si = 1;
        int ei = (int)1e9;
        while(si < ei)
        {
            int mid = (si + ei)/2;
            if(!check(mid , weights , days)) si = mid+1;
            else ei = mid;
        }
        return si;
    }


    // Two Pointer Style Problems

    // Count the triplets 

    public int countTriplet(int arr[], int n){
        Arrays.sort(arr);
        int count = 0;
        for(int i = n-1;i>=2;i--){
            int x = 0;
            int y = i-1;
            int val = arr[i];
            while(x < y)
            {
                if(arr[x] + arr[y] == val){
                    count++;
                    x++;
                    y--;
                }
                else if(arr[x] + arr[y] > val) y--;
                else x++;
            }
        }
        return count;
    }

    //Count the number of possible triangles 

    // A Triangle Is Valid When Sum Of
    // Two Sides Is Greater Than Third
    // Side
    static int findNumberOfTriangles(int arr[], int n)
    {
        Arrays.sort(arr);
        int count = 0;
        for(int i = n-1;i>=2;i--){
            int x = 0;
            int y = i-1;
            int val = arr[i];
            while(x < y)
            {
                if(arr[x] + arr[y] > val)
                {
                    count += y-x;
                    y--;
                }
                else x++;
            }
        }
        return count;
    }

    // Count zeros in a sorted matrix 
    // Used the property that we used in Search in Sorted Matrix 2

    int countZeros(int A[][], int N)
    {
        int i = 0;
        int j = N-1;
        
        int count = 0;
        
        while(i < N && j >= 0)
        {
            if(A[i][0] == 0 && A[i][j] == 0){
                count+= j+1;
                i++;
            }
            else if(A[i][0] == 0 && A[i][j]!=0) j--;
            else break;
        }
        
        return count;
    }

    //Counting elements in two arrays 

    public static int findLastIdx(int[]arr , int tar)
    {
        int i = 0;
        int j = arr.length;
        while(i < j)
        {
            int mid = (i+j)/2;
            if(arr[mid] <= tar)
            {
                i = mid + 1;
            }
            else j = mid;
        }
        return (i < arr.length && arr[i] == tar) ? i+1 : i;
    }
    public static ArrayList<Integer> countEleLessThanOrEqual(int arr1[], int arr2[], int m, int n)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        
        Arrays.sort(arr2);
        
        for(int ele : arr1)
        {
            int val = findLastIdx(arr2,ele);
            ans.add(val);
        }
        
        return ans;
    }

    
    // Count Zeros Xor Pairs

    static int nCr(int n, int r){
        return fact(n) / (fact(r) * fact(n - r));
    }
    static int fact(int n)
    {
        int res = 1;
        for (int i = 2; i <= n; i++) res = res * i;
        return res;
    }
    public static int countPairs(int[]arr) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        int count = 0;
        for(int ele : arr){
            map.put(ele,map.getOrDefault(ele,0)+1);    
        }

        for(int ele : map.values()) count+= nCr(ele,2);

        return count;
    }


    // Facing the sun 

    int countBuildings(int h[], int n) 
    {
        int max = -(int)1e9;
        int count = 0;
        for(int ele : h)
        {
            if(ele > max)
            {
                max = ele;
                count++;
            }
        }
        return count;
    }


    // Distinct absolute array elements 

    int distinctCount(int[] arr, int n) 
    {
        int i=0;
        int j=arr.length-1;
        int count=0;
        while(i<=j)
        {
            while(i+1 < arr.length && arr[i] == arr[i+1]) i++;
            while(j-1 >=0 && arr[j] == arr[j-1]) j--;
            if(Math.abs(arr[i])==Math.abs(arr[j])){
                i++;
                j--;
            }
            else if(Math.abs(arr[i])>Math.abs(arr[j])) i++;
            else if(Math.abs(arr[i])<Math.abs(arr[j])) j--;
            count++;
        }
        return count;
    }


    // Find Transition Point 

    int transitionPoint(int arr[], int n) 
    {
        int tar = 0;
        int i = 0;
        int j = arr.length;
        while(i < j)
        {
            int mid = (i+j)/2;
            if(arr[mid] <= tar)
            {
                i = mid + 1;
            }
            else j = mid;
        }
        return (i == n) ? -1 : i;
    }

    // 278. First Bad Version
    // IMPORTANT PROBLEM
    // IS BAD VERSION IS THE FUNCTION PROVIDED BY THE API
    public int firstBadVersion(int n) 
    {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 374. Guess Number Higher or Lower
    // ALMOST SAME AS ABOVE

     public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            if (res == 0)
                return mid;
            else if (res < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }


    // Find the element that appears once in sorted array 
    // O(log N) Time Complexity

    public int findOnce(int arr[], int n){
        int i = 0;
        int j = n-1;
        while(i < j){
            int mid = (i+j)/2;
            if(mid > 0 && arr[mid] == arr[mid-1] && (mid-i+1)%2 == 0) i = mid + 1;
            else if(mid + 1 < arr.length && arr[mid] == arr[mid + 1] && (mid+2)%2==0) i = mid + 2;
            else j = mid;
        }
        return arr[i];
    }


    // Row with max 1s 

    int rowWithMax1s(int arr[][], int n, int m) 
    {
        int count = -(int)1e9;
        int row = -1;
        for(int i = 0;i<n;i++)
        {
            int x = 0;
            int y = m-1;
            
            while(x <= y)
            {
                if(arr[i][x] == 1 && arr[i][y] == 1)
                {
                    int val = y-x+1;
                    if(val > count){
                        count = val;
                        row = i;
                    }
                    break;
                }
                x++;
            }
        }
        return row;
    }


    // 475. Heaters

    public long ceil(int[]arr , int tar)
    {   
        int i = 0;
        int j = arr.length-1;
        while(i<j)
        {
            int mid = (i+j)/2;
            if(arr[mid] >= tar) j = mid;
            else i = mid+1;
        }
        return (arr[i]>=tar) ? arr[i] : (long)1e11;
    }
    
    public long floor(int[]arr , int tar)
    {
        int i = 0;
        int j = arr.length-1;
        while(j-i >=2)
        {
            int mid = (i+j)/2;
            if(arr[mid] <= tar) i = mid;
            else j = mid-1;
        }
        if(arr[i] == tar || arr[j] == tar) return tar;
        
        if(arr[j] < tar) return arr[j];
        
        if(arr[i] < tar) return arr[i];
        
        return -(long)1e11;
        
    }
    
    public int findRadius(int[] houses, int[] heaters) 
    {
        Arrays.sort(heaters);
        Arrays.sort(houses);
        long max = -(long)1e11;
        for(int ele : houses){
            long rightVal = ceil(heaters,ele);
            long leftVal = floor(heaters,ele);
            // System.out.println(leftVal+" + "+ rightVal);
            
            long left = ele - leftVal;
            long right = rightVal - ele;
            
            max = Math.max(max,Math.min(left,right));
            // System.out.println(max);
        }
        return (int)max;
    }

    // Punish the Students 

    int shouldPunish (int roll[], int marks[], int n, double avg)
    {
        int count=0;
        float ans=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(j+1 < n && roll[j]>roll[j+1])
                {
                    int temp = roll[j];
                    roll[j]=roll[j+1];
                    roll[j+1] = temp;
                    count++;
                }
            }
        }
        
        for(int i=0;i<n;i++){
            ans+=marks[i];
        }
        
        double fin=(ans-count)/(n*1.0);
        
        if(fin<avg){
            return 0;
        }else{
            return 1;
        }
    }


    // 179. Largest Number
    // Important Question
    // Ratlo

    private class LargerNumberComparator implements Comparator<String> 
    {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
           return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) 
    {
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(asStrs, new LargerNumberComparator());

        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        String largestNumberStr = new String();
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }

        return largestNumberStr;
    }

    // 976. Largest Perimeter Triangle
    // For a triangle to be valid the sum of two sides should be greater tha the third side
    public int largestPerimeter(int[] nums)
    {
        Arrays.sort(nums);
        int peri = -(int)1e9;
        for(int i = nums.length-1;i>=2;i--)
        {
            if(nums[i-2] + nums[i-1] > nums[i]){
                peri = Math.max(peri,nums[i]+nums[i-1]+nums[i-2]);
            }
        }
        return (peri == -(int)1e9) ? 0 : peri;\
    }

    // Leaders in an array 

    static ArrayList<Integer> leaders(int arr[], int n)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        int max = -(int)1e9;
        for(int i = n-1;i>=0;i--)
        {
            int val = arr[i];
            if(val >= max){
                ans.add(0,val);
                max = val;
            }
        }
        
        return ans;
    }

    // Ishaan and Sticks 

    public static ArrayList<Integer> square (int arr[], int n) 
    {
        ArrayList<Integer> a = new ArrayList<>();
        int maxArea = -(int)1e9;
        Arrays.sort(arr);
        // System.out.println(Arrays.toString(arr));
        int count = 1;
        int ans = 0;
        for(int i = 1;i<n;i++)
        {
            if(arr[i] == arr[i-1]){
                count++;
            }
            else
            {
                if(count/4 > 0)
                {
                    if(arr[i-1]*arr[i-1] > maxArea)
                    {
                        ans = count/4;
                        maxArea = arr[i-1]*arr[i-1];
                    }
                }
                count = 1;
            }
        }
        // consider this test case {1,2,3,4,6,6,6,6,6,6,6,6,6,6,6}
        // so below lines are required
        if(count/4 > 0)
        {
            if(arr[n-1]*arr[n-1] > maxArea)
            {
                ans = count/4;
                maxArea = arr[n-1]*arr[n-1];
            }
        }
        
        if(maxArea == -(int)1e9){
            a.add(-1);
            return a;
        }
        a.add(maxArea);
        a.add(ans);
        
        return a;
    }


    // Toppers of Class 
    // Code copies from peepcoding Portal

    public static class Pair implements Comparable<Pair> {
    int marks;
    int idx;

    Pair(int marks, int idx) {
      this.marks = marks;
      this.idx = idx;
    }

    public int compareTo(Pair o) {
      if (this.marks != o.marks) {
        return this.marks - o.marks;
      }
      else {
        return o.idx - this.idx;
      }
    }
  }
  // We could easily do it by sorting as well
  public static int[] kToppers(int[]marks, int k) 
  {
    PriorityQueue<Pair>pq = new PriorityQueue<>();
    for (int i = 0; i < marks.length; i++) {
      if (pq.size() < k) {
        pq.add(new Pair(marks[i], i));
      }
      else if (pq.peek().marks < marks[i]) {
        pq.remove();
        pq.add(new Pair(marks[i], i));
      }
    }

    int[]ans = new int[k];
    int idx = k - 1;

    while (idx >= 0) {
      ans[idx] = pq.remove().idx;
      idx--;
    }

    return ans;
  }


  // 1030. Matrix Cells in Distance Order
  // Solution Using BFS

  static class Pair {
        int i;
        int j;
        int lev;
        
        Pair() {
            
        }
        
        Pair(int i,int j,int lev) {
            this.i = i;
            this.j = j;
            this.lev = lev;
        }
    }
    
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) 
    {
        int[][]dist = new int[rows][cols];
        ArrayList<ArrayList<Integer>>ans = new ArrayList<>();
        
        
        //initially all cells are unvisited so mark them as -1
        for(int i=0; i < dist.length;i++) {
            for(int j=0; j < dist[0].length;j++) {
                dist[i][j] = -1;
            }
        }
        
        
        int[][]dir = {{-1,0},{0,-1},{1,0},{0,1}};
        
        ArrayDeque<Pair>q = new ArrayDeque<>();
        
        q.add(new Pair(rCenter,cCenter,0));
        dist[rCenter][cCenter] = 0;
        
        while(q.size() > 0) {
            //remove
            Pair rem = q.remove();
            
            //work
            ArrayList<Integer>list = new ArrayList<>();
            list.add(rem.i); list.add(rem.j);
            ans.add(list);
            
            //add unvisited nbrs
            for(int i=0; i < 4;i++) {
                int ni = rem.i + dir[i][0];
                int nj = rem.j + dir[i][1];
                
                if(ni >= 0 && ni < dist.length && nj >= 0 && nj < dist[0].length && dist[ni][nj] == -1) {
                    q.add(new Pair(ni,nj,rem.lev + 1)); 
                    dist[ni][nj] = rem.lev + 1;
                }
            }
        }
        
        
        int[][]temp = new int[rows*cols][2];
        
        for(int i=0; i < ans.size();i++) {
            temp[i][0] = ans.get(i).get(0);
            temp[i][1] = ans.get(i).get(1);
        }
        
        return temp;
    }

    // Solution Using Sort Function And Comparator
    // important code
    public static int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) 
    {
        int[][]ans = new int[rows * cols][2];

        for (int i = 0; i < rows; i++) {
          for (int j = 0; j < cols; j++) {
            int bno = i * cols + j;
            ans[bno][0] = i;
            ans[bno][1] = j;
          }
        }

        Arrays.sort(ans, (a, b) -> {
          int d1 = Math.abs(rCenter - a[0]) + Math.abs(cCenter - a[1]);
          int d2 = Math.abs(rCenter - b[0]) + Math.abs(cCenter - b[1]);

          return d1 - d2; // this - other
        });

        return ans;
    }


    // 1291. Sequential Digits

    public List<Integer> sequentialDigits(int low, int high) 
    {
        String num = "123456789";
        List<Integer> ans = new ArrayList<>();
        
        int n1 = (low+"").length();
        int n2 = (high+"").length();
        
        for(int i=n1;i<=n2;i++)
        {
            for(int j = 0;j<=num.length()-i;j++)
            {
                int n = Integer.parseInt(num.substring(j,j+i));
                if(n <= high && n >= low) ans.add(n);
            }
        }
        
        return ans;
    }


    // 969. Pancake Sorting
    // Important Ratne wala Question
    // Check Leetcode's Solution Tab
    // This Is Leetcode's Solution
    public List<Integer> pancakeSort(int[] A) 
    {
        List<Integer> ans = new ArrayList<>();

        for (int valueToSort = A.length; valueToSort > 0; valueToSort--) 
        {
            
            int index = find(A, valueToSort);
            if (index == valueToSort - 1) continue; // Means Item already at sorted position
            if (index != 0) // works only when element is not at head 
            {
                ans.add(index + 1);
                flip(A, index + 1);
            }
            ans.add(valueToSort);
            flip(A, valueToSort);
        }
        return ans;
    }

    public void flip(int[] sublist, int k) 
    {
        int i = 0;
        while (i < k / 2) 
        {
            int temp = sublist[i];
            sublist[i] = sublist[k - i - 1];
            sublist[k - i - 1] = temp;
            i += 1;
        }
    }

    public int find(int[] a, int target) 
    {
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] == target) return i;
        }
        return -1;
    }


    // 786. K-th Smallest Prime Fraction
    // Priority Queue Approach
    // Important Approach

    public int[] kthSmallestPrimeFraction(int[] a, int k) 
    {
        int n = a.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int s1 = a[o1[0]] * a[o2[1]];
                int s2 = a[o2[0]] * a[o1[1]];
                return s1 - s2;
            }
        });
        
        for (int i = 0; i < n-1; i++) 
        {
            pq.add(new int[]{i, n-1});
        }
        
        for (int i = 0; i < k-1; i++) 
        {
            int[] pop = pq.remove();
            int ni = pop[0];
            int di = pop[1];
            if (pop[1] - 1 > pop[0]) 
            {
                pop[1]--;
                pq.add(pop);
            }
        }

        int[] peek = pq.peek();
        return new int[]{a[peek[0]], a[peek[1]]};
    }

    
}
