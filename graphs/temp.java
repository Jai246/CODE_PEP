import java.util.*;
class temp 
{
   public static class edge
   {
       int v = 0;
       int w = 0;
       edge(){

       }
       edge(int v , int w)
       {
           this.v = v;
           this.w = w;
       }
   }
   public static class dij
   {
       int v = 0;
       int wsf = 0;
       dij(){

       }
       dij(int v , int wsf)
       {
           this.v = v;
           this.wsf = wsf;
       }
   } 
   public static void main(String args[])
    { 
        Scanner sc = new Scanner(System.in);
        String[] first = sc.nextLine().split(" ");
        int N = Integer.parseInt(first[0]);
        int E = Integer.parseInt(first[1]);
        @SuppressWarnings("unchecked")
        ArrayList<edge>[] graph = new ArrayList[N+1];
        for(int i=0;i<N+1;i++) graph[i] = new ArrayList<>();
        for(int i = 0;i<E;i++)
        {
            String s = sc.nextLine();
            String[] st = s.split(" ");
            //for(String ele : st) System.out.println(ele);
            graph[Integer.parseInt(st[0])].add(new edge(Integer.parseInt(st[1]) , Integer.parseInt(st[2])));
            graph[Integer.parseInt(st[1])].add(new edge(Integer.parseInt(st[0]) , Integer.parseInt(st[2])));
        }
        String[] r = sc.nextLine().split(" ");
        int R = Integer.parseInt(r[0]);
        //System.out.println(R);
        int[] pos = new int[R];

        String[] third = sc.nextLine().split(" ");
        int[] min = new int[N+1];
        int[] ans = new int[N+1];
        //for(String ele : third) System.out.println(ele);
        for(int i = 0;i<R;i++)
        {
            pos[i] = Integer.parseInt(third[i]);
        }

        int MSize = (int)1e9;

        
        Arrays.fill(min,(int)1e9);
        //System.out.println(ans.length);

        for(int ele : pos)
        {
            PriorityQueue<dij> queue = new PriorityQueue<>((a,b)->
            {
                return a.wsf - b.wsf;
            });

            queue.add(new dij(ele,0));

            while(queue.size()!=0)
            {
                dij temp = queue.remove();
                //System.out.println(temp.v +  "," +  temp.wsf);
                min[temp.v] = Math.min(min[temp.v] , temp.wsf);

                for(edge e : graph[temp.v])
                {
                    if((e.w + temp.wsf) < min[e.v]) queue.add(new dij(e.v , e.w + temp.wsf));
                }
            }
            System.out.println();
            System.out.println();
            // for(int le =1;le<min.length;le++) System.out.println(min[le]);
            System.out.println();
            int temp = 0;
            for(int k = 1;k<min.length;k++)
            {
                temp += min[k];
            }
            if(temp < MSize){
                ans = min;
                min = new int[N+1];
                MSize = temp;
            }
            Arrays.fill(min,(int)1e9);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        for(int i = 1;i<ans.length;i++)
        {
            System.out.println(ans[i]);
        }
    }
}