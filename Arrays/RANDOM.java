import java.io.*;
import java.util.Random;
import java.util.*;
 
class RANDOM
{
    // the number of runs
    // for the test data generated
    static int requiredNumbers = 30;
 
    // miminum range of random numbers
    static int lowerBound = 1;
 
    // maximum range of random numbers
    static int upperBound = 10;
 
    // Driver Code
    public static void main (String[] args) throws IOException
    {
        Random random = new Random();
        int[]arr = new int[requiredNumbers];
        for(int i = 0; i < requiredNumbers; i++)
        {
            int a = random.nextInt(upperBound - lowerBound) + lowerBound;
            arr[i] = a;
        }
        // Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}