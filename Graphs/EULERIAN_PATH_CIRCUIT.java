import java.util.ArrayList;
class eulerianPath
{
    static int n = 0;
    @SuppressWarnings("unchecked")
    static ArrayList<Integer>[] graph = new ArrayList[n+1];

    public static void dfs(int src , boolean[]vis)
    {
        vis[src] = true;
        for(int ele : graph[src])
        {
            if(!vis[ele])
            {
                dfs(ele, vis);
            }
        }
    }

    public static boolean Connected_Graph()
    {
        boolean[] vis = new boolean[n+1];
        int node = -1;	//Node to start DFS
        for(int i=0;i<n;++i)
            if(graph[i].size()>0)
            {
                node = i;	//Found a node to start DFS
                break;
            }
        if(node == -1)	//No start node was found-->No edges are present in graph
            return true; //It's always Eulerian

        dfs(node,vis);
        //Check if all the non-zero vertices are visited
        for(int i=0;i<n;++i)
            if(vis[i]==false && graph[i].size()>0)
                return false;	//We have edges in multi-component
        return true;
    }

    public static int find_Euler()
    {
        if(!Connected_Graph())	//multi-component edged graph
            return 0;		//All non-zero degree vertices should be connected

        //Count odd-degree vertices
        int odd = 0;
        for(int i=0;i<n;++i)
            if(graph[i].size()%2 != 0)
                odd += 1;

        if(odd > 2)	//Only start and end node can have odd degree
            return 0;

        return (odd==0)?2:1;	//1->Semi-Eulerian...2->Eulerian
    }

    public static void findEuler_Path_Cycle()
    {
        int ret = find_Euler();
        if(ret==0)
            System.out.println("Graph is NOT a Euler graph");
        else if(ret==1)
            System.out.println("Graph is Semi-Eulerian");
        else
            System.out.println("Graph is Eulerian (Euler circuit)");
    }
}