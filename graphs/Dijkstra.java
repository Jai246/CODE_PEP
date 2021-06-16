import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Dijkstra
{
    static int N = 9;
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
    @SuppressWarnings("unchecked")
    public static ArrayList<edge>[] MST = new ArrayList[N];
    static
    {
        for(int i = 0;i<N;i++)
        {
            graph[i] = new ArrayList<>();
            MST[i] = new ArrayList<>();
        }
    }

    public static void addEdge(int u , int v , int w , ArrayList<edge>[] graph)
    {
        graph[u].add(new edge(v,w));
        graph[v].add(new edge(u,w));
    }
    public static void display(ArrayList<edge>[] graph)
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
    public static int findEdge(int u , int v,ArrayList<edge>[] graph)
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
    public static void removeEdge(int u , int v , ArrayList<edge>[] graph)
    {
        int idx1 = findEdge(u,v,graph);
        int idx2= findEdge(v, u,graph);
        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int u,ArrayList<edge>[] graph)
    {
        for(int i = graph[u].size()-1;i>=0;i--)
        {
            int v = graph[u].get(i).v;
            removeEdge(u, v,graph);
        }
    }
    public static class DijkstraPair
    {
        int v = 0;
        int par = -1;
        int wsf = 0;
        DijkstraPair()
        {
            
        }
        DijkstraPair(int v , int par , int w)
        {
            this.v = v;
            this.par = par;
            this.wsf = w;
        }
    }

    public static void DijkstraAlgo1(ArrayList<edge>[] graph)
    {
        PriorityQueue<DijkstraPair> queue = new PriorityQueue<>((a,b)->{
            return a.wsf - b.wsf;
        });
        boolean[]vis = new boolean[N];
        queue.add(new DijkstraPair(0,-1,0));
        while(queue.size()!=0)
        {
            DijkstraPair temp = queue.remove();
            if(vis[temp.v]) continue;
            if(temp.par!=-1) addEdge(temp.par, temp.v, temp.wsf, MST);
            vis[temp.v] = true;
            for(edge e : graph[temp.v])
            {
                if(!vis[e.v]) queue.add(new DijkstraPair(e.v,temp.v,e.w + temp.wsf));
            }
        }
        display(MST);
    }
    public static void DijkstraAlgo2(ArrayList<edge>[] graph)
    {
        PriorityQueue<DijkstraPair> queue = new PriorityQueue<>((a,b)->{
            return a.wsf - b.wsf;
        });
        boolean[]vis = new boolean[N];
        int[]dis = new int[N];
        Arrays.fill(dis,(int)1e9);
        int[]par = new int[N];
        Arrays.fill(par,-1);
        queue.add(new DijkstraPair(0,-1,0));
        while(queue.size()!=0)
        {
            DijkstraPair temp = queue.remove();
            if(vis[temp.v]) continue;
            if(temp.par!=-1) addEdge(temp.par, temp.v, temp.wsf, MST);
            vis[temp.v] = true;
            for(edge e : graph[temp.v])
            {
                if(!vis[e.v] && dis[e.v] > e.w + temp.wsf) 
                {
                    queue.add(new DijkstraPair(e.v,temp.v,e.w + temp.wsf));
                    dis[e.v] = temp.wsf + e.w;
                    par[e.v] = temp.v;
                }
            }
        }
        display(MST);
    }
    // Bellman Ford.
    // {{u,v,w}.....}
    public static void bellmanFord(int src, int[][] edges, int N) {
        int[] dis = new int[N];
        Arrays.fill(dis, (int) 1e9);

        dis[src] = 0;
        boolean isNegativeCycle = false;

        for (int EdgeCount = 1; EdgeCount <= N; EdgeCount++) 
        {
            int[] ndis = new int[N];
            for (int i = 0; i < N; i++) // copying
                ndis[i] = dis[i];

            for (int[] e : edges) 
            {
                int u = e[0], v = e[1], w = e[2];
                if (dis[u] != (int) 1e9 && dis[u] + w < ndis[v]) 
                {
                    if (EdgeCount == N) 
                    {
                        isNegativeCycle = true;
                        break;
                    }

                    ndis[v] = dis[u] + w;
                }
            }
            dis = ndis;
        }

        
    }
    public static void constructGraph(ArrayList<edge>[] graph) {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

            addEdge(0, 1, 4,graph);
            addEdge(0, 7, 8,graph);
            addEdge(1, 2, 8,graph);
            addEdge(2, 3, 7,graph);
            addEdge(3, 4, 9,graph);
            addEdge(4, 5, 10,graph);
            addEdge(5, 6, 2,graph);
            addEdge(6, 7, 1,graph);
            addEdge(1, 7, 11,graph);
            addEdge(2, 8, 2, graph);
            addEdge(8, 7, 7, graph);
            addEdge(8, 6, 6, graph);
            addEdge(2, 5, 4, graph);
            addEdge(3, 5, 14,graph);
            
    }

    public static void main(String[] args) 
    {
        constructGraph(graph);
        //DijkstraAlgo1(graph);
        DijkstraAlgo2(graph);
    }
}