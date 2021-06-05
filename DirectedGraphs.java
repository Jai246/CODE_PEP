import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
class DirectedGraphs
{
    static int N = 15;
    public static class edge
    {
        int v = 0;
        int w = 0;
        edge(int v , int w)
        {
            this.v = v;
            this.w = w;
        }
    }
    @SuppressWarnings("unchecked")
    public static ArrayList<edge>[] graph = new ArrayList[N];
    public static void addEdge(int u , int v , int w)
    {
        graph[u].add(new edge(v,w));
    }
    public static void display()
    {
        for(int i = 0;i<N;i++)
        {
            System.out.print(i + "->");
            for(edge ele : graph[i])
            {
                System.out.print("(" + ele.v + "," + ele.w + ")");
            }
            System.out.println();
        }
    }
    public static int findEdge(int u , int v)
    {
        int idx = -1;
        for(int i = 0;i<graph[u].size();i++)
        {
            if(graph[u].get(i).v == v)
            {
                idx = i;
                break;
            }
        }
        return idx;
    }
    public static void removeEdge(int u , int v)
    {
        int idx1 = findEdge(u,v);
        graph[u].remove(idx1);
    }

    public static void removeVtx(int u)
    {
        for(int i = graph[u].size()-1;i>=0;i--)
        {
            int v = graph[u].get(i).v;
            removeEdge(u, v);
        }
    }
    //https://www.cs.usfca.edu/~galles/visualization/TopoSortDFS.html
    public static void dfs_topo(int src , boolean[]vis , ArrayList<Integer> ans) 
    {
        vis[src] = true;
        for(edge ele : graph[src])
        {
            if(!vis[ele.v]) dfs_topo(ele.v, vis, ans); 
        }
        ans.add(src);
    }
    public static void topologicalOrder_Dfs() // for multiple components
    {
        boolean[]vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0;i<N;i++)
        {
            if(!vis[i]) dfs_topo(i, vis, ans);
        }

        for(Integer ele : ans)
        {
            System.out.print(" -> " + ele);
        }
    }
    public static void kahnsAlgo()
    {
        int[] inDegree = new int[N];
        for(int i = 0;i<N;i++)
        {
            for(edge ele : graph[0]) inDegree[ele.v]++;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0;i<N;i++) 
        {
            if(inDegree[i] == 0) queue.addLast(i);
        }
        int level = 0;
        while(queue.size()!=0)
        {
            int size = queue.size();
            while(size-- > 0)
            {
                int rvtx = queue.removeFirst();
                ans.add(rvtx);
                for(edge ele : graph[rvtx])
                {
                    if(--inDegree[ele.v] == 0) queue.addLast(ele.v);
                }
            }
            level++;
        }
        boolean test = false;
        for(int i = 0;i<N;i++) 
        {
            if(inDegree[i]!= 0)
            {
                System.out.println("Topological Order Not Possible");
                test = true;
                break;
            }
        }
        if(!test)
        {
            for(int ele : ans)
            {
                System.out.print("->" + ele);
            }
        }
    }
    
    public static boolean dfs_Topo(int src , int[] vis , ArrayList<Integer> ans)
    {
        boolean isCycle = false;
        vis[src] = 0;
        for(edge ele : graph[src])
        {
            if(vis[ele.v] == -1) 
            {
                isCycle = isCycle || dfs_Topo(ele.v, vis,ans);
            }
            else if(vis[ele.v] == 0) 
            {
                isCycle = true;
                System.out.println(src + "->" + ele.v);
            }
        }
        ans.add(src);
        vis[src] = 1;
        return isCycle;
    }
    public static void isCycleTopo()
    {
        int[] vis = new int[N];
        Arrays.fill(vis,-1);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        boolean res = false;
        for(int i = 0;i<N;i++)
        {
            if(vis[i] == -1) res = res || dfs_Topo(i, vis,ans);
        }
        System.out.println((res)?"Cycle Present !! Topological Order Not possible":"Topological Order Possible");
        if(!res)
        {
            for(int ele : ans)
            {
                System.out.print(ele + " ");
            }
        }
    }
    public static void constructGraph() {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

            addEdge(5, 0, 10);
            addEdge(4, 0, 10);
            addEdge(5, 1, 10);
            addEdge(1, 2, 10);
            addEdge(2, 3, 10);
            addEdge(0, 6, 10);
            addEdge(6, 7, 10);
            addEdge(7, 3, 10);
            addEdge(4, 8, 10);
            addEdge(8, 9, 10);
            addEdge(9, 3, 10);
            addEdge(3, 14, 10);
            addEdge(9, 10, 10);
            addEdge(10, 11, 100);
            addEdge(11, 12, 10);
            addEdge(12, 13, 10);
            addEdge(13, 4, 10);

            // addEdge(0, 1, 10);
            // addEdge(1, 2, 10);
            // addEdge(6, 1, 10);
            // addEdge(2, 3, 10);
            // addEdge(3, 4, 10);
            // addEdge(4, 5, 10);
            // addEdge(5, 6, 10);

            // addEdge(0, 1, 10);
            // addEdge(1, 2, 10);
            // addEdge(2, 3, 10);
            // addEdge(3, 4, 10);
            // addEdge(4, 1, 10);
            
    }
    public static void main(String[] args) 
    {
        constructGraph();
        //topologicalOrder_Dfs();
        //display();
        //kahnsAlgo();    
        isCycleTopo();
    }

}