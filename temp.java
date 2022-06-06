import java.util.ArrayList;
import java.util.*;

public class temp
{
    public static void shortestPath(ArrayList<ArrayList<Integer>> field)
    {
        int n = field.size();
        int m = field.get(0).size();
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                if(field.get(i).get(j) == 0)
                {
                    if(i + 1 < n) field.get(i+1).set(j,-1);
                    if(i - 1 >= 0) field.get(i-1).set(j,-1);
                    
                    if(j + 1 < m) field.get(i).set(j+1,-1);
                    if(j - 1 >=0) field.get(i).set(j-1,-1);
                }
            }
        }
        int[] min = new int[]{(int)1e9};
        getShortest(field,0,0,n,m,min,0);
        if(min[0] == (int)1e9) System.out.println(-1);
        else System.out.println(min[0]);
    }
    
    public static int getShortest(ArrayList<ArrayList<Integer>> field , int i , int j , int n , int m , int[] min , int c)
    {
        if(i < 0 || j < 0 || i>= n || j >= m) return (int)1e9;
        if(field.get(i).get(j) == -1 || field.get(i).get(j) == 0) return (int)1e9;
        if(j == m-1) return 1;
        field.get(i).set(j,-1);
        int res = (int)1e9;
        res = Math.min(res,getShortest(field,i-1,j,n,m,min,c));
        res = Math.min(res,getShortest(field,i+1,j,n,m,min,c));
        res = Math.min(res,getShortest(field,i,j-1,n,m,min,c));
        res = Math.min(res,getShortest(field,i,j+1,n,m,min,c));
        field.get(i).set(j,1);
        if(res == (int)1e9) return res;
        if(j == 0) 
        {
            min[0] = Math.min(min[0],res);
            System.out.println("HELLO");
        }
        return res + 1;
    }

    public static void main(String[] args) 
    {
        // 1
        // 7 2
        // 1 0 
        // 1 1 
        // 1 1 
        // 1 0 
        // 1 1 
        // 1 1 
        // 1 1 

        ArrayList<ArrayList<Integer>> field = new ArrayList<>();
        ArrayList<Integer> t1 = new ArrayList<>();
        t1.add(1);
        t1.add(0);
        field.add(t1);
        ArrayList<Integer> t2 = new ArrayList<>();
        t2.add(1);
        t2.add(1);
        field.add(t2);
        ArrayList<Integer> t3 = new ArrayList<>();
        t3.add(1);
        t3.add(1);
        field.add(t3);
        ArrayList<Integer> t4 = new ArrayList<>();
        t4.add(1);
        t4.add(0);
        field.add(t4);
        ArrayList<Integer> t5 = new ArrayList<>();
        t5.add(1);
        t5.add(1);
        field.add(t5);
        ArrayList<Integer> t6 = new ArrayList<>();
        t6.add(1);
        t6.add(1);
        field.add(t6);
        ArrayList<Integer> t7 = new ArrayList<>();
        t7.add(1);
        t7.add(1);
        field.add(t7);

        shortestPath(field);
    }

    
}