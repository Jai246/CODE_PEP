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
    public static void main(String[] args) 
    {
        // int[]arr = new int[]{1,3,7,9,11,13,15,17,19,21,23,25};
        // binarySearchInsert(arr, 11, 0, arr.length-1);
        inversionCount();
    }    
}