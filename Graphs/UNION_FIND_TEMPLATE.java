import java.util.ArrayList;
class unionFind
{
    static int N = 8;
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
    static int[]par;
    static int[]size;
    @SuppressWarnings("unchecked")
    public static ArrayList<edge>[] graph = new ArrayList[N];
    static{
        for(int i = 0;i<N;i++) graph[i] = new ArrayList<>();
    }
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
    public static int findPar(int u)
    {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }
    public static void merge(int p1 , int p2)
    {
        if(size[p1] > size[p2])
        {
            par[p2] = p1;
            size[p1] += size[p2];
        }
        else
        {
            par[p1] = p2;
            size[p2] += size[p1];
        }
    }
    public static void unionfind(int[][]edges)
    {
        par = new int[N];
        size = new int[N];
        for(int i = 0;i<N;i++)
        {
            size[i] = 1;
            par[i] = i;
        }
        boolean cycle = false;
        for(int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1!=p2)
            {
                merge(p1, p2);
                addEdge(u, v, w);
            }
            else cycle = true;
        }
        display();
        System.out.println(cycle);
    }

    public static void main(String[] args) 
    {
        int[][] edges= new int[][]{{1,0,4},{0,7,8},{1,2,10},{2,3,7},{3,4,9},{4,5,10},{5,6,2}};    
        unionfind(edges);
        display();
    }
}