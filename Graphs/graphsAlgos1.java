import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
class graphsAlgos1
{
    static int N = 7;
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
    // static 
    // {
    //     for(int a = 0;a<N;a++) graph[a] = new ArrayList<>();
    // }
    public static void addEdge(int u , int v , int w)
    {
        // if(graph[u] == null) graph[u] = new ArrayList<>();
        // if(graph[v] == null) graph[v] = new ArrayList<>();
        graph[u].add(new edge(v,w));
        graph[v].add(new edge(u,w));
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
        int idx2 = findEdge(v,u);
        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int u)
    {
        for(int i = graph[u].size()-1;i>=0;i--) // to save the shifting time
        {
            int v = graph[u].get(i).v;
            removeEdge(u, v);
        }
    }
    public static boolean hasPath(int start , int dest , boolean[]vis)
    { // taking visited array so that it dosent make an infinite loop
        if(start == dest)
        {
            return true;
        }
        boolean res = false;
        vis[start] = true;
        for(edge ele : graph[start])
        {
            
            if(!vis[ele.v]) res = res || hasPath(ele.v , dest , vis);
            
        }
        vis[start] = false;
        return res;
    }
    public static int PrintAllPath(int start , int dest , boolean[]vis ,String ans)
    {
        if(start == dest)
        {
            System.out.println(ans + dest);
            return 1;
        }
        int count = 0;
        vis[start] = true;
        for(edge ele : graph[start])
        {
            
            if(!vis[ele.v]) count += PrintAllPath(ele.v , dest , vis , ans + start + " ");
            
        }
        vis[start] = false;
        return count;
    }
    public static class heavyPair
    {
        int w = 0;
        String s = "";
        heavyPair()
        {

        }
        heavyPair(int w , String s)
        {
            this.w = w;
            this.s = s;
        }
    }
    public static heavyPair printHeavyPath(int src , int dest , boolean[] vis)
    {
        if(src == dest)
        {
            return new heavyPair(0,dest + " ");
        }
        heavyPair temp = new heavyPair(-(int)1e8,"");
        heavyPair ans = new heavyPair(0,"");
        vis[src] = true;
        for(edge ele : graph[src])
        {
            if(!vis[ele.v])
            {
                ans = printHeavyPath(ele.v, dest, vis);
                if(ans.w + ele.w > temp.w)
                {
                    temp.w = ans.w + ele.w;
                    temp.s = src + ans.s;
                }
            }
        }
        vis[src] = false;
        return temp;
    }
    public static void hamiltonian(int src, int orSrc, int count , boolean[] vis, String ans)
    {
        if(count + 1  == N)
        {
            if(findEdge(src, orSrc) != -1)
            {
                System.out.println("Cycle : " + ans + src + orSrc);
            }
            else System.out.println("Path : " + ans + src);
        }
        for(edge ele : graph[src])
        {
            vis[src] = true;
            if(!vis[ele.v]) hamiltonian(ele.v, orSrc,count+1,vis,ans + src);
            vis[src] = false;
        }
    }
    public static void dfs(int src , boolean[]vis , ArrayList<Integer> ans)
    {
        vis[src] = true;
        for(edge ele : graph[src])
        {
            if(!vis[ele.v]) dfs(ele.v, vis, ans);
        }
        ans.add(src);
    }
    public static ArrayList<ArrayList<Integer>> getConnectedComponents()
    {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        boolean[] vis = new boolean[N];
        int components = 0;
        for(int i = 0;i<N;i++)
        {
            if(!vis[i])
            {
                ArrayList<Integer> temp = new ArrayList<>();
                dfs(i,vis,temp);
                res.add(temp);
                components++;
            }
        }
        System.out.println(components);
        return res;
    }
    

    public static void BFS_Cycle(int src , boolean[]vis)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(src);
        int dest = 6;
        int atLevel = -1;
        boolean isCycle = false;
        int level = 0;
        while(queue.size()!=0)
        {
            int size = queue.size();
            System.out.print(" " + level + "->");
            while(size-- > 0)
            {
                int rvtx = queue.removeFirst();
                System.out.print(rvtx + ",");
                if(vis[rvtx])
                {
                    isCycle = true;
                    continue;
                }
                if(rvtx == dest) atLevel = level;
                vis[rvtx] = true;
                for(edge e : graph[rvtx])
                {
                    if(!vis[e.v]) queue.addLast(e.v);
                }
            }
            level++;
            System.out.println();
        }
        System.out.println(" Destination -> " + dest + " Present At Level " + atLevel);
        System.out.println(("IsCycle :" + (isCycle ? "True" : "False")));
    }
    public static void BFS_ShortestPath(int src , boolean[]vis)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(src);
        vis[src] = true;
        int dest = 6;
        int level = 0;
        int atLevel = -1;
        while(queue.size()!=0)
        {
            int size = queue.size();
            while(size-- > 0)
            {
                int rvtx = queue.removeFirst();
                for(edge e : graph[rvtx])
                {
                    if(!vis[e.v]) 
                    {
                        queue.addLast(e.v);
                        vis[e.v] = true;
                        if(atLevel == -1 && e.v == dest) atLevel = level+1;
                     }
                }
            }
            level++;
        }
        System.out.println();
        System.out.println(atLevel);
        
    }
    public static void BFS_PrintShortestPath(int src , boolean[]vis)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(src);
        vis[src] = true;
        int dest = 6;
        int level = 0;
        int atLevel = -1;
        int[] parent = new int[N];
        parent[src] = -1;
        while(queue.size()!=0)
        {
            int size = queue.size();
            while(size-- > 0)
            {
                int rvtx = queue.removeFirst();
                for(edge e : graph[rvtx])
                {
                    if(!vis[e.v]) 
                    {
                        queue.addLast(e.v);
                        vis[e.v] = true;
                        parent[e.v] = rvtx;
                        if(atLevel == -1 && e.v == dest) atLevel = level+1;
                     }
                }
            }
            level++;
        }
        System.out.println();
        System.out.println("Level -> " + atLevel);
        int val = dest;
        String ans = "";
        while(val!=-1)
        {
            ans = val + " " + ans;
            val = parent[val];
        }
        System.out.println(ans);
        
    }
    public static void BFS_GCC()
    {
        boolean[] vis = new boolean[N];
        int components = 0;
        for(int i = 0;i<N;i++)
        {
            if(!vis[i])
            {
                BFS_PrintShortestPath(i, vis);
                components++;
            }
        }
        System.out.println("Components -> " + components);
    }
    public static boolean Bipartite(int[]vis)
    {
        int src = 0;
        int dest = 6;
        Boolean Bipartite = true;
        Arrays.fill(vis,-1);
        int level = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(src);
        while(queue.size()!=0)
        {
            int size = queue.size();
            while(size-- > 0)
            {
                int rvtx = queue.removeFirst();
                if(vis[rvtx]==-1)
                {
                    vis[rvtx] = level;
                }
                else if(vis[rvtx] != -1 && vis[rvtx]!=level)
                {
                    Bipartite = false;
                }
                for(edge ele : graph[rvtx])
                {
                    if(vis[ele.v] == -1) queue.addLast(ele.v);
                }

            }
            level = (level+1)%2;
        }
        return Bipartite;
    }
    // We are doing this because there there can be more than one components and all of these components should
    // be checked before making the final decision
    public static void isBipartite()
    {
        int[] vis = new int[N];
        Arrays.fill(vis,-1);
        boolean res = true;
        for(int i = 0;i<N;i++)
        {
            if(vis[i]==-1)
            {
                res = res && Bipartite(vis);
            }
        }
        System.out.println((res) ? "True" : "False");
    }
    public static void constructGraph() {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

            addEdge(0, 1, 10);
            addEdge(0, 3, 10);
            addEdge(1, 2, 10);
            addEdge(2, 3, 40);
            addEdge(3, 4, 2);
            addEdge(4, 5, 2);
            addEdge(4, 6, 8);
            addEdge(5, 6, 3);
            
    }

    public static void main(String[] args) 
    {
        
        boolean[] vis = new boolean[N];
        //System.out.println(hasPath(0,6,vis));
        //System.out.println(PrintAllPath(0, 6, vis, ""));// prints 120 paths because the graph is unidirectional 
        // heavyPair ans = printHeavyPath(0, 6, vis);
        // System.out.println(ans.w);
        // System.out.println(ans.s);
        //hamiltonian(0, 0, 0, vis, " ");
        constructGraph();
        // ArrayList<ArrayList<Integer>> ans = getConnectedComponents();
        // for(int i = 0;i<ans.size();i++)
        // {
        //     for(int j = 0;j<ans.get(i).size();j++)
        //     {
        //         System.out.print(ans.get(i).get(j) + " ");
        //     }
        //     System.out.println();
        // }
        // display();
        //BFS_Cycle(0, vis);
        //BFS_ShortestPath(0, vis);
        //BFS_PrintShortestPath(0, vis);
        //BFS_GCC();
        isBipartite();
    }
}