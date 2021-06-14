import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class searchingSorting
{
    public static void print1D(int[]arr)
    {
        for(int ele : arr)
        {
            System.out.print(ele + " ");
        }
    }
    public static int binarySearch(int[] arr , int tar , int si , int ei)
    {
        while(si <= ei)
        {
            int mid = (ei+si)/2;
            if(arr[mid] == tar) return mid;
            else if(arr[mid] < tar) si = mid+1;
            else ei = mid-1;
        }
        return -1;
    }
    // leetcode 34 Find First and Last Position of Element in Sorted Array
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
    public static int firstIndex(int[]arr, int data)
    {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei)
        {
            int mid = (si + ei) / 2;
            if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return si < n && arr[si] == data ? si : -1;
    }

    public static int lastIndex(int[]arr, int data)
    {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei)
        {
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        si--;
        return si >= 0 && arr[si] == data ? si : -1;
    }

    //34
    public static int[] searchRange1(int[]arr, int data)
    {
        return new int[]{firstIndex(arr, data), lastIndex(arr, data)};
    }

    // important
    public static int insertLocation(int[]arr, int data)
    {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei)
        {
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return si;
    }

    public static int perfectPosOfElement(int[]arr, int data)
    {
        int insertPos = insertLocation(arr, data);
        int lastIndex = insertPos - 1;

        return (lastIndex >= 0 && arr[lastIndex] == data) ? lastIndex : insertPos;
    }

    public static int nearestElement(int[]arr, int data)
    {
        if (arr.length == 0)
            return 0;

        int n = arr.length;
        if (data <= arr[0] || data >= arr[n - 1])
            return data <= arr[0] ? arr[0] : arr[n - 1];

        int si = 0, ei = n - 1;
        while (si <= ei)
        {
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return data - arr[ei] <= arr[si] - data ? arr[ei] : arr[si];
    }
    public static int binarySearchInsert(int[] arr , int tar , int si , int ei)
    {
        int mid = 0;
        while(si <= ei)
        {
            mid = (ei+si)/2;
            if(arr[mid] <= tar) si = mid+1;
            else ei = mid-1;
        }
        int insertPos = si;
        int lastIndex = si - 1;
        System.out.println(insertPos  + " , " + lastIndex);
        return (lastIndex >= 0 && arr[lastIndex] == tar) ? lastIndex : insertPos;
    }
    // Leetcode 74
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
    // GFG INVERSION COUNT
    static int count = 0;
    public static void countInversion(int [] arr ,int a , int b , int i , int j)
    {
        while(a <= b && i <= j)
        {
            if(arr[a] > arr[i])
            {
                count += (b - a + 1);
                i++;
            }
            while(a <= b && i <= j && arr[a] <= arr[i]) a++;
        }
    }
    public static void merge(int [] arr ,int a , int b , int i , int j)
    {
        int t = a;
        int k = 0 , x = 0 , y = 0;
        int []temp = new int[b-a+1+j-i+1];
        while(a <= b && i <= j)
        {
            if(arr[a] < arr[i]) temp[k++] = arr[a++];
            else if(arr[i]<=arr[a]) temp[k++] = arr[i++];
        }
        for(x = a;x<=b;x++) temp[k++] = arr[x]; // REMAINING ELEMENTS FROM A -> B
        for(y = i;y<=j;y++) temp[k++] = arr[y]; // REMAINING ELEMENTS FROM I -> J
        for(int m = 0;m<temp.length;m++) arr[t++] = temp[m]; // COPYING BACK IN ARR FORM TEMP
    }
    public static void mergeSort(int[] arr , int i , int j)
    {
        if(i == j) return;
        int mid = (i+j)/2;
        mergeSort(arr,i,mid);
        mergeSort(arr,mid+1,j);
        countInversion(arr,i,mid,mid+1,j);
        merge(arr,i,mid,mid+1,j);
    }
    public static void inversionCount()
    {
        //int[] arr = new int[]{20 , -2 , 5 , 6 , 4 , -5 , 7 , 10 , 14 , -3 , 7 , 11 , 19 , -5 , 4 , 2 , 9 , -20 , 7 , 35 , -40};
        int[] arr = new int[]{174 ,165 ,142 ,212 ,254 ,369 ,48 ,145 ,163, 258, 38 ,360 ,224 ,242 ,30 ,279 ,317 ,36 ,191 ,343, 289 ,107 ,41 ,443 ,265 ,149 ,447 ,306 ,391 ,230 ,371 ,351 ,7 ,102};
        mergeSort(arr, 0, arr.length-1);
        System.out.println(count);
        print1D(arr);
    }
    // Leetcode 33 search in rotated sorted array
    // hum sorted region ke basis par decision le sakte hei
    public static int search(int[] arr, int data) 
    {
        int lo = 0;
        int hi = arr.length-1;
        
        while(lo<=hi)
        {
            int mid = (lo + hi)/2;
            if(data == arr[mid]) return mid;
            else if(arr[mid]>=arr[lo])
            {
                if(data < arr[mid] && data >= arr[lo]) hi = mid - 1; //data < arr[mid] ismei <= par check karna zaroori nahihei  
                else lo = mid + 1;                                   //kyoki data = mid wali condition oopar hi check hori hei                           
            }
            else //if(arr[hi] >= arr[mid])
            {
                if(data <= arr[hi] && data > arr[mid]) lo = mid+1;
                else hi = mid-1;
            }
        }
        
        return -1;
    }
    // leetcode 81
    public static boolean search1(int[] arr, int data) 
    {
        int lo = 0;
        int hi = arr.length-1;
        
        while(lo<=hi)
        {
            int mid = (lo + hi)/2;
            if(data == arr[mid] || data == arr[hi]) return true;
            else if(arr[mid] > arr[lo])
            {
                if(data < arr[mid] && data >= arr[lo]) hi = mid - 1;//data < arr[mid] ismei <= par check karna zaroori nahihei  
                else lo = mid + 1;                                  //kyoki data = mid wali condition oopar hi check hori hei 
            }
            else if(arr[hi] > arr[mid])
            {
                if(data <= arr[hi] && data > arr[mid]) lo = mid+1;
                else hi = mid-1;
            }
            else hi--;
        }
        
        return false;
    }
    // leetcode 153
    public static  int findMin(int[] arr) 
    {
        int lo = 0, hi = arr.length - 1;
        if(arr[lo] <= arr[hi]) return arr[lo];

        while(lo < hi)
        {
            int mid = (lo + hi) / 2;

            if(arr[mid] < arr[hi]) hi = mid;
            else if(arr[lo] <= arr[mid]) lo = mid + 1;
        }

        return arr[lo];
    }
    // leetcode 154 Important
    public static int findMin1(int[] arr) 
    {
        int n = arr.length, si = 0, ei = n - 1;
        if (arr[si] < arr[ei])
            return arr[si];

        while (si < ei)
        {
            int mid = (si + ei) / 2;
            if (arr[mid] < arr[ei])
                ei = mid;
            else if (arr[mid] > arr[ei])
                si = mid + 1;
            else
                ei--;
        }

        return arr[si];
    }
    // leetcode 167
    public static int[] twoSum(int[] arr,int data) 
    {
        int ei = 0;
        int si = arr.length-1;
        while(ei<si)
        {
            int sum = arr[ei] + arr[si];
            if(sum == data) return new int[]{ei+1,si+1};//because indexes are not zero based
            else if(sum>data) si--;
            else ei++;
        }
        return new int[]{-1,-1};
    }
    // Printing Target sum No Duplicates
    public static void twoSumPrint(int[] arr , int data , int si , int ei) // withen given range
    {
        while(si < ei)
        {
            // while(si > 0 && arr[si] == arr[si-1]) si++;
            // while(ei < arr.length-1 && arr[ei] == arr[ei+1]) ei--;
            int sum = arr[si] + arr[ei];
            if(si < ei && sum == data)
            {
                System.out.println("{" + arr[si] + "," + arr[ei] + "}");
                si++;
                ei--;
                while(si < ei && si > 0 && arr[si] == arr[si-1]) si++;
                while(si < ei && ei < arr.length-1 && arr[ei] == arr[ei+1]) ei--;
            }
            else if(sum > data) ei--;
            else si++;
        }
    }
    // leetcode 15
    public List<List<Integer>> twoSumPrint(int[] arr , int data , int si , int ei , int i) // withen given range
    {
        List<List<Integer>> list = new ArrayList<>();
        while(si < ei)
        {
            int sum = arr[si] + arr[ei];
            if(si < ei && sum == data)
            {
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[i]);
                temp.add(arr[si]);
                temp.add(arr[ei]);
                list.add(temp);
                si++;
                ei--;
                while(si < ei && si > 0 && arr[si] == arr[si-1]) si++;
                while(si < ei && ei < arr.length-1 && arr[ei] == arr[ei+1]) ei--;
            }
            else if(sum > data) ei--;
            else si++;
        }
        return list;
    }
    public List<List<Integer>> threeSum(int[] arr) 
    {
        Arrays.sort(arr);
        int target = 0;
        int i = 0;
        List<List<Integer>> list = new ArrayList<>();
        while(i < arr.length && arr.length >= 3)
        {
            while(i >=1 && i<arr.length && arr[i] == arr[i-1]) i++;
            int j = i+1; int k = arr.length-1;
            if(j<arr.length) {
                List<List<Integer>> temp = twoSumPrint(arr,target-arr[i],j,k,i);
                for(List<Integer> ele : temp) list.add(ele);
            }
            i++;
        }
     return list;
    }
    // leetcode 4 sum
    public static List<List<Integer>> fourSum(int[] arr, int target) 
    {
        Arrays.sort(arr);
        List<List<Integer>> list = new ArrayList<>();
        int i = 0;
        while(i < arr.length)
        {
            while(i > 0 && i < arr.length && arr[i] == arr[i-1]) i++;
            if(i < arr.length){
                List<List<Integer>> temp = threeSum(arr , i, target-arr[i]);
                for(List<Integer> ele : temp) list.add(ele);
                i++;
            }
        }
        return list;
    }
    public static List<List<Integer>> threeSum(int[] arr ,int n, int target) 
    {
        int i = n+1;
        List<List<Integer>> list = new ArrayList<>();
        while(i < arr.length && arr.length >= 3)
        {
            while(i > n+1 && i<arr.length && arr[i] == arr[i-1]) i++;
            int j = i+1; int k = arr.length-1;
            if(j<arr.length) {
                List<List<Integer>> temp = twoSumPrint(arr,target-arr[i],j,k,i,n);
                for(List<Integer> ele : temp) list.add(ele);
            }
            i++;
        }
     return list;
    }
    public static List<List<Integer>> twoSumPrint(int[] arr , int data , int si , int ei , int i , int h) // withen given range
    {
        List<List<Integer>> list = new ArrayList<>();
        while(si < ei)
        {
            int sum = arr[si] + arr[ei];
            if(si < ei && sum == data)
            {
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[h]);
                temp.add(arr[i]);
                temp.add(arr[si]);
                temp.add(arr[ei]);
                list.add(temp);
                si++;
                ei--;
                while(si < ei && si > 0 && arr[si] == arr[si-1]) si++;
                while(si < ei && ei < arr.length-1 && arr[ei] == arr[ei+1]) ei--;
            }
            else if(sum > data) ei--;
            else si++;
        }
        return list;
    }
    // More generic way using recursion
    public List<List<Integer>> twoSumR(int[] arr, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == target) {
                ArrayList<Integer> smallAns = new ArrayList<>();
                smallAns.add(arr[si]);
                smallAns.add(arr[ei]);
                ans.add(smallAns);
                si++;
                ei--;
                while (si < ei && arr[si] == arr[si - 1])
                    si++;
                while (si < ei && arr[ei] == arr[ei + 1])
                    ei--;
            } else if (sum < target)
                si++;
            else
                ei--;
        }

        return ans;
    }
    public void prepareAns(List<List<Integer>> ans, List<List<Integer>> smallAns, int fixEle) {

        for (List<Integer> arr : smallAns) {
            List<Integer> ar = new ArrayList<>();
            ar.add(fixEle);
            for (int ele : arr)
                ar.add(ele);
            ans.add(ar);
        }
    }
    public List<List<Integer>> kSum(int[] arr, int target, int k, int si, int ei) {
        if (k == 2)
            return twoSumR(arr, target, si, ei);

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = si; i < ei;) {
            List<List<Integer>> smallAns = kSum(arr, target - arr[i], k - 1, i + 1, ei);
            prepareAns(ans, smallAns, arr[i]);
            i++;
            while (i < ei && arr[i] == arr[i - 1])
                i++;
        }

        return ans;
    }
    // leetcode 454
    public static int twoSumCount(int[] nums1, int[] nums2, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums1)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        int count = 0;
        for (int ele : nums2)
            if (map.containsKey(target - ele))
                count += map.get(target - ele);

        return count;
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int e1 : nums1)
            for (int e2 : nums2)
                map.put(e1 + e2, map.getOrDefault(e1 + e2, 0) + 1); // putting all possible combinations

        int count = 0, target = 0;
        for (int e1 : nums3)
            for (int e2 : nums4)
                if (map.containsKey(target - e1 - e2)) // Testing for all possible combinations from two arrays 
                    count += map.get(target - e1 - e2);// without actually making an array

        return count;
    }
    public static int fourSumCountType2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) 
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums1.length, idx = 0;
        int[] A = new int[n * n];
        int[] B = new int[n * n];

        for (int e1 : nums1)
            for (int e2 : nums2)
                A[idx++] = e1 + e2;

        idx = 0;
        for (int e1 : nums3)
            for (int e2 : nums4)
                B[idx++] = e1 + e2;

        return twoSumCount(A, B, 0);
    }
    // leetcode 658
    public static int insertPosition(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return si;
    }
    // if the difference comes out to be same then we will drop the greater element
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int ele : arr)
            ans.add(ele);

        int n = arr.length;
        if (x <= arr[0])
            return ans.subList(0, k);
        else if (x >= arr[n - 1])
            return ans.subList(n - k, n);
        else {
            int idx = insertPosition(arr, x);
            int lr = Math.max(0, idx - k);
            int rr = Math.min(n - 1, idx + k);

            while ((rr - lr + 1) > k) {
                if (x - arr[lr] > arr[rr] - x)
                    lr++;
                else
                    rr--; // if the difference comes out to be same then we will drop the greater element
            }
            return ans.subList(lr, rr + 1);
        }
    }

    // O(log(n) + k) ratlo
    public static List<Integer> findClosestElements_02(int[] arr, int k, int x) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int ele : arr)
            ans.add(ele);

        int n = arr.length;
        if (x <= arr[0])
            return ans.subList(0, k); // {arr.begin(), arr.begin() + k}
        else if (x >= arr[n - 1])
            return ans.subList(n - k, n); // {arr.end() - k, arr.end()}
        else {
            int lr = 0, rr = n - k;
            while (lr < rr) {
                int mid = (lr + rr) / 2;
                if (x - arr[mid] > arr[mid + k] - x)
                    lr = mid + 1;
                else
                    rr = mid;
            }

            return ans.subList(lr, lr + k);
        }
    }
    // leetcode 300
    public static  int insertPosition(ArrayList<Integer> list, int data) {
        int n = list.size(), si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (list.get(mid) <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        int insertPos = si;
        int lastIndex = si - 1;
        return lastIndex >= 0 && list.get(lastIndex) == data ? lastIndex : insertPos;
    }

    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return n;

        ArrayList<Integer> list = new ArrayList<>();
        for (int ele : nums) {
            int loc = insertPosition(list, ele);
            if (loc == list.size())
                list.add(ele);
            else
                list.set(loc, ele);
        }
        return list.size();
    }
    public static void main(String[] args) 
    {
        // int[]arr = new int[]{1,3,7,9,11,13,15,17,19,21,23,25};
        // binarySearchInsert(arr, 11, 0, arr.length-1);
        //inversionCount();
        // int[] arr = new int[]{-1,-1,-1,-1,-1,-1,1,1,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,6,6,6,6};
        // twoSumPrint(arr, 5,0,arr.length-1);
    }    
}