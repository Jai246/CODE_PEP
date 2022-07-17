import java.util.*;
public class HEAP_SORT 
{
    public static void heapSort(int[] arr)
    {
        ArrayList<Integer> heap = new ArrayList<>();
        for(int ele : arr)
        {
            heap.add(ele);
            upHeapify(heap,heap.size()-1);
        }

        System.out.println(heap.toString());

        while(heap.size() > 0)
        {
            int peek = heap.get(0);
            System.out.println(peek);
            int last = heap.get(heap.size()-1);

            heap.set(0,last);
            heap.remove(heap.size()-1);            

            if(heap.size() > 0) downHeapify(heap,0);
        }
    }

    public static void upHeapify(ArrayList<Integer> arr , int idx)
    {
        if(idx == 0) return;

        int par = (idx-1)/2;

        if(arr.get(par) > arr.get(idx))
        {
            int p = arr.get(par);
            int c = arr.get(idx);
            arr.set(par,c);
            arr.set(idx,p);
            upHeapify(arr,par);
        }
    }

    public static void downHeapify(ArrayList<Integer> arr , int idx)
    {
        int par = arr.get(idx);
        int left = 2*idx+1;
        int right = 2*idx+2;

        int leftVal = -1;
        int rightVal = -1;

        leftVal = (left > arr.size()-1) ? (int)1e10 : arr.get(left);
        rightVal = (right > arr.size()-1) ? (int)1e10 : arr.get(right);
        
        if(leftVal < par &&  rightVal < par)
        {
            if(leftVal < rightVal)
            {
                arr.set(idx,leftVal);
                arr.set(left,par);
                downHeapify(arr,left);
            }
            else
            {
                arr.set(idx,rightVal);
                arr.set(right,par);
                downHeapify(arr,right);
            }
        }
        else if(leftVal < par)
        {
            arr.set(idx,leftVal);
            arr.set(left,par);
            downHeapify(arr,left);
        }
        else if(rightVal < par)
        {
            arr.set(idx,rightVal);
            arr.set(right,par);
            downHeapify(arr,right);
        }  
    }  
}
