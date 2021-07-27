class temp
{
    public static void insertionSort(int arr[]) 
    {  
        int n = arr.length;  
        for (int j = 1; j < n; j++) {  
            int key = arr[j];  
            int i = j-1;  
            while ( (i > -1) && (arr [i] > key))
            {  
                arr[i+1] = arr[i];  
                i--;  
            }  
            arr[i+1] = key;  
        }  
    }

    public static boolean linearSearch(int[] arr , int ele)
    {
        for(int element : arr)
        {
            if(element == ele) return true;
        }
        return false;
    }
    
    public static boolean binarySearch(int[] arr , int ele)
    {
        int si = 0;
        int ei = arr.length-1;
        while(si <= ei)
        {
            int mid = (si+ei)/2;
            if(ele == arr[mid]) return true;
            else if(ele > arr[mid]) si = mid+1;
            else ei = mid-1; 
        }
        return false;
    }
    public static void main(String[] args) 
    {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(binarySearch(arr, 10));
        //System.out.println(linearSearch(arr, 20));
    }
}