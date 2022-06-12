import java.util.ArrayList;
class ArticulationPoint
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
        int idx2= findEdge(v, u);
        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int u)
    {
        for(int i = graph[u].size()-1;i>=0;i--)
        {
            int v = graph[u].get(i).v;
            removeEdge(u, v);
        }
    }
    static int[] dis , low , AP;
    static boolean[] vis , isAP;
    static int time = 0 , noOfCallsFromRoot = 0;
    
    public static void DFS(int src , int par)
    {
        dis[src] = low[src] = time++;
        vis[src] = true;
        for(edge nbr : graph[src])
        {
            if(!vis[nbr.v])
            {
                if(par == -1) noOfCallsFromRoot++;
                DFS(nbr.v , src);
                if(dis[src] <= low[nbr.v])
                {
                    AP[src]++;
                    isAP[src] = true;
                }
                if(dis[src] < low[nbr.v])
                {
                    System.out.println("APEdge : " + src + "->" + nbr.v);
                }
                low[src] = Math.min(low[src],low[nbr.v]);
            }
            else if(nbr.v!=par) // important
            {
                low[src] = Math.min(low[src] , dis[nbr.v]);
            }

        }
    }
    public static void ArticulationPointAndBridges()
    {
        low = new int[N];
        dis = new int[N];
        vis = new boolean[N];
        AP = new int[N];
        isAP = new boolean[N];
        int components = 0;

        for(int i = 0;i<N;i++)
        {
            if(!vis[i])
            {
                DFS(i, -1);
                if(noOfCallsFromRoot == 1)
                {
                    isAP[i] = false;
                    AP[i] = 0;
                }
                noOfCallsFromRoot = 0;
                components++;
            }
        }

        int countOfAP = 0;
        for (int i = 0; i < N; i++) {
            if (isAP[i]) {
                countOfAP++;
                System.out.println("AP: " + i + " @ " + "Increase in No Of components: " + (AP[i]+1));
            }
        }
    }
    
    public static void main(String[] args) 
    {
            
    }
}