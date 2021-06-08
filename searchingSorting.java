class searchingSorting
{
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
    public static void main(String[] args) 
    {
        int[]arr = new int[]{1,3,7,9,11,13,15,17,19,21,23,25};
        binarySearchInsert(arr, 11, 0, arr.length-1);
    }    
}