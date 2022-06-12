class temp
{
    public void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
    
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
    public static int binarySearch2(int[] arr , int ele , int i , int j)
    {
        int si = i;
        int ei = j;
        while(si <= ei)
        {
            int mid = (si+ei)/2;
            if(ele >= arr[mid]) si = mid+1;
            else ei = mid-1; 
        }
        return si;
    }
    public static void binaryInsertionSort(int[]arr)
    {
        int n = arr.length;
        for (int j = 0; j < n; j++) 
        {  
            int key = arr[j];  
            int pos = binarySearch2(arr, key, 0, j-1);
            int k = j;
            int val = arr[j];
            if(k == pos) continue;  
            while(k>pos)
            {
                arr[k] = arr[k-1];
                k--;
            }
            arr[pos] = val;
        }  
    }

    public int partition(int arr[], int low, int high)
    {
        int pivot = arr[high]; 
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (arr[j] <= pivot)
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
  
        return i+1;
    }
  
    public void sort(int arr[], int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
  
    public static void main(String[] args)
    {
        //int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        //System.out.println(binarySearch(arr, 10));
        //System.out.println(linearSearch(arr, 20));
        int[]arr1 = new int[]{9,8,7,6,5,4,3,2,1,0};
        binaryInsertionSort(arr1);
        for(int ele : arr1) System.out.print(ele + " ");
    }
}