class quickSort
{
    public static void swap(int []arr , int i , int j)
    {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
    public static int segregateData(int[]arr, int si, int ei, int pivot)
    {
        swap(arr , pivot, ei);
        int p = si - 1, itr = si;
        while (itr <= ei)
        {
            if (arr[itr] <= arr[ei])
                swap(arr , ++p, itr);
            itr++;
        }

        return p;
    }

    public static void QuickSort(int[]arr, int si, int ei)
    {
        if (si > ei)
            return;

        // int len = (ei - si + 1);
        // int pivot = rand() % len + si;

        int pivot = ei;
        int pIdx = segregateData(arr, si, ei, pivot);

        //Sorted Array Check
        // if it is sorted then return

        QuickSort(arr, si, pIdx - 1);
        QuickSort(arr, pIdx + 1, ei);
    }



    public static void main(String[] args)
    {
        int[] arr = {-12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3};
        QuickSort(arr, 0, arr.length - 1);

        for (int ele : arr)
            System.out.println(ele + " ");
    }
}