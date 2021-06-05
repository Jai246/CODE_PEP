import java.util.ArrayList;
import java.util.List; // important
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

class graphQues
{
    //684 redundant connections
    public static int findPar(int u , int[] par)
    {
        return (par[u] == u) ? u : (par[u] = findPar(par[u] , par));
    }
    public static int[] findRedundantConnection(int[][] edges) 
    {
        int[] par = new int[edges.length + 1];
        for(int i = 1;i<=edges.length;i++) par[i] = i;
        
        for(int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            int par_u = findPar(u,par);
            int par_v = findPar(v,par);
            if(par_u != par_v) par[par_u] = par_v;
            else return edge;
        }
        return new int[]{};
    }
    // Leetcode Lexicographically samllest string
	public static String smallestString(String s, String t, String str) 
    {
		int[]par = new int[26];
        for(int i = 0;i<par.length;i++) par[i] = i;
        for(int i = 0;i<s.length();i++)
        {
            int u = findPar(s.charAt(i)-'a' , par);
            int v = findPar(t.charAt(i)-'a' , par);
            par[u] = Math.min(u,v);
            par[v] = Math.min(u,v);
        }
        String res = "";
        for(int i = 0;i<str.length();i++){
            res += (char)(findPar(str.charAt(i) - 'a' , par) + 'a');
        }
       return res;
	}

    //leetcode 839
    public static int findPar1(int u , int[]par)
    {
        return (par[u] == -1) ? u : (par[u] = findPar1(par[u] , par));
    }
    public static boolean isSimilar(String a , String b)
    {
        int count = 0;
        for(int i = 0;i<a.length();i++)
        {
            if(a.charAt(i) != b.charAt(i)) count++;
        }
        return (count <= 2) ? true : false; // since two strings can be similar as well
    }
    public static int numSimilarGroups(String[] strs) 
    {
        int[] par = new int[strs.length];
        Arrays.fill(par,-1);
        for(int i = 0;i<strs.length;i++)
        {
            for(int j = i-1;j>=0;j--)
            {
                if(isSimilar(strs[i] , strs[j]))
                {
                    int u = findPar1(i,par);
                    int v = findPar1(j,par);
                    if(u!=v) par[v] = u;
                }
            }
        }
        int count = 0;
        for(int i = 0;i<par.length;i++)
        {
            if(par[i] == -1) count++;
        }
        
        return count;
    }
    // Lintcode Number Island 2
    class Point 
    {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
    public static List<Integer> numIslands2(int n, int m, Point[] operators) 
    {
        if(operators == null) return Collections.emptyList();
        List<Integer> ans = new ArrayList<Integer>();
        int[] par = new int[m*n];
        Arrays.fill(par,-1);
        int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        int[][] mat = new int[n][m];
        int C = 0;
        for(int i = 0;i<operators.length;i++)
        {
            int count = 0;
            if(mat[operators[i].x][operators[i].y] != 1)
            {
                mat[operators[i].x][operators[i].y] = 1;
                count++;
            }
            for(int[] ele : dir)
            {
                int x = ele[0] + operators[i].x;
                int y = ele[1] + operators[i].y;
                if(x >=0 && y>=0 && x<n && y<m && mat[x][y] == 1)
                {
                    int u = findPar1(x*m+y , par);
                    int v = findPar1(operators[i].x*m+operators[i].y,par);
                    if(u!=v) 
                    {
                        par[u] = v;
                        count--;
                    }
                }
            }
            ans.add(C + count);
            C = C + count;
        }

        return ans;
    }
    // optimize water distribution
    public static int minCostToSupplyWater(int N, ArrayList<int[]> Edges) {
        int[]par = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }

        int cost = 0;
        for (int[] edge : Edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            int p1 = findPar(u,par);
            int p2 = findPar(v,par);

            if (p1 != p2) {
                par[p1] = p2;
                cost += w;
            }
        }
        return cost;
    }

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> PIPES = new ArrayList<>();
        for (int i = 0; i < n; i++) 
        {
            PIPES.add(new int[] { 0, i + 1, wells[i] }); // creating dummy edge and connecting with all present vertex with weight as cost for well
        }

        for (int[] p : pipes)
            PIPES.add(p);

        Collections.sort(PIPES, (a, b) -> {
            return a[2] - b[2];
        });

        return minCostToSupplyWater(n, PIPES);
    }
    // see the way how it is done 
    // first when we see ch == 1 the we increment and while combining with neighbours we decrement
    // and the when we go to the neighbours we increment
    public static int numIslands(char[][] grid) // remember this solution !!!!!!!!!!!!!!!! Imp Imp Imp Imp
    {
        int n = grid.length;
        int m = grid[0].length;
        int[]par = new int[n*m];
        for(int i = 0;i<n*m;i++) par[i] = i;
        int[][] dir = new int[][]{{1,0},{0,-1}};
        int count = 0;
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                if(grid[i][j] == '1')
                { 
                    count++;
                    for(int[] d : dir)
                    {
                        int x = i + d[0];
                        int y = j + d[1];
                        if(x >=0 && y>=0 && x<n && y<m && grid[x][y] == '1')
                        {
                            int u = findPar1(i*m+j,par);
                            int v = findPar1(x*m+y,par);
                            if(u!=v)
                            {
                                par[v] = u;
                                count--;
                            }

                        }
                    }
                }
            }
        }
        return count;
    }

    // Leetcode 695 Max Area Of Island
    public static int maxAreaOfIsland(int[][] grid) 
    {
        int n = grid.length;
        int m = grid[0].length;
        int[] par = new int[n*m];
        int[] size = new int[n*m];
        int[][] dir = new int[][]{{1,0},{0,-1}};
        for(int i = 0;i<n*m;i++) par[i] = i;
        Arrays.fill(size,1);
        int maxArea = 0;
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                if(grid[i][j] == 1)
                {
                    int u = findPar(i*m+j,par);
                    for(int[] d : dir)
                    {
                        int x = d[0] + i;
                        int y = d[1] + j;

                        if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1)
                        {
                            int v = findPar(x*m+y,par);

                            if(u!=v)
                            {
                                par[v] = u;
                                size[u] += size[v];


                            }
                        }
                    }
                    maxArea = Math.max(maxArea,size[u]);
                }
                //else size[i*m+j] = 0;////VVVVVVImp but not compulsary for the given test cases
                
            }
        }
        return maxArea;
    }

    public static void journeyToMoon(int n , int[][]astronauts)// dry run on desktop or in a folder
    {
        int[]par = new int[n];
        int[]size = new int[n];

        for(int i = 0;i<n;i++)
        {
            par[i] = i;
            size[i] = 1;
        }
        for(int [] ast : astronauts)
        {
            int u = findPar(ast[0], par);
            int v = findPar(ast[1], par);

            if(u!=v)
            {
                par[v] = u;
                size[u] += size[v];
            }
        }
        long sum = 0;
        long pairs = 0;
        for(int i = 0;i<n;i++)
        {
            if(par[i] == i)
            {
                pairs += sum*size[i];
                sum += size[i];
            }
        }

        System.out.println(pairs);
    }

    // Mr.President HakerEarth
    public static void KruskalsForMrPresident(int n , int cities ,int[][]roads,int cost)
    {
        Arrays.sort(roads , (a,b)->{
            return a[2] - b[2];
        });
        int[] par = new int[n];
        int totalCost = 0;
        int change = 0;
        for(int i = 0;i<n;i++) par[i] = i;
        ArrayList<int[]> min = new ArrayList<>();
        for(int i = 0;i<roads.length;i++)
        {
            int u = roads[i][0];
            int v = roads[i][1];
            int w = roads[i][2];

            int p1 = findPar(u, par);
            int p2 = findPar(v, par);

            if(p1!=p2)
            {
                par[p1] = p2;
                totalCost += w;
                min.add(new int[]{u,v,w});
            }
        }
        System.out.println(totalCost);
        for(int [] ele : min)
        {
            System.out.println(ele[2]);
        }
        System.out.println();
        System.out.println();
        //for(int i = 0;i<min.size();i++)
        for(int i = min.size()-1;i>=0;i--)
        {
            if(totalCost > cost)
            {
                int w = min.get(i)[2];
                totalCost = totalCost -  w + 1;
                System.out.println(totalCost);
                change++;
            }
            
        }

        System.out.println(totalCost);
        System.out.println(change);

    }
    // Leetcode Bus Routes 815
    public static int numBusesToDestination(int[][] routes, int source, int target) 
    {
        if(source == target) return 0;
        HashMap<Integer , ArrayList<Integer>> mapping = new HashMap<>();
        int busNumber = 0;
        for(int[]route : routes)
        {
            for(int ele : route)
            {
                if(mapping.containsKey(ele))
                {
                    mapping.get(ele).add(busNumber);
                }
                else
                {
                    mapping.put(ele,new ArrayList<Integer>());
                    mapping.get(ele).add(busNumber);
                }
            }
            busNumber++;
        }
        boolean[] bus = new boolean[routes.length];
        HashSet<Integer> busStand = new HashSet<>();// Coz we dont know no of bus stands so instead of iterating over , we created a hash set
        LinkedList<Integer> queue = new LinkedList<>();
        int level = 0;
        queue.addFirst(source);
        while(queue.size()!=0)
        {
            int size = queue.size();
            while(size-- >0)
            {
                int stand = queue.removeFirst();
                ArrayList<Integer> buses = mapping.get(stand);
                for(int ele : buses)
                {
                    if(bus[ele]) continue;
                    for(int r : routes[ele]) // Important we can Iterate over each element of each column in a 2D Array Like This
                    {
                        if(!busStand.contains(r))
                        {
                            queue.addLast(r);
                            busStand.add(r);
                            if(r == target) return level + 1 ;
                        }
                    }
                    bus[ele] = true;
                }

            }
            level++;
        }
        return -1;
    }
    // Leetcode Network Delay
    public static int networkDelayTime(int[][] times, int n, int k) // Another Approach In Notes
    {
        int MinTime = 0;
        int N = 1;
        boolean[] added = new boolean[n+1];
        boolean[] vis = new boolean[n+1];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        queue.add(new int[]{k,0});
        added[k] = true;
        while(queue.size()!=0)
        {
            int[] temp = queue.remove();
            if(vis[temp[0]]) continue;
            vis[temp[0]] = true;
            if(N == n) MinTime = Math.max(MinTime,temp[1]);
            for(int[]d : times)
            {
                if(d[0] == temp[0] && !vis[d[1]])
                {
                    queue.add(new int[]{d[1],d[2] + temp[1]});
                    if(!added[d[1]]) N++;
                    added[d[1]] = true;

                }
            }
        }
        
        System.out.println(N);
        return (N == n) ? MinTime : -1; 
    }
    // Critical Connections
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) 
    {
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n];
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0;i<n;i++) graph[i] = new ArrayList<>();
        for(List<Integer> ele : connections)
        {
            graph[ele.get(0)].add(ele.get(1));
            graph[ele.get(1)].add(ele.get(0));
        }

        int[] dis = new int[n];
        boolean[] vis = new boolean[n];
        int[] low = new int[n];
        dfsATPAB(dis, low, vis, 0, 0,-1, graph, ans);
        return ans;
    }
    public static void dfsATPAB(int[] dis , int[] low , boolean[] vis , int i, int src,  int par ,ArrayList<Integer>[] graph ,List<List<Integer>> ans )
    {
        low[src] = dis[src] = i;
        vis[src] = true;

        for(int e : graph[src])
        {
            if(!vis[e])
            {
                dfsATPAB(dis, low, vis, i+1, e,src,graph, ans);
                if(dis[src] < low[e]) 
                {
                    ans.add(new ArrayList<Integer>(Arrays.asList(src,e)));
                }
                low[src] = Math.min(low[src],low[e]);
            }
            else if(e!=par)
            {
                low[src] = Math.min(dis[e] , low[src]);
            }
        }
    }
    // MiniMize Malware Spread

    public static int minMalwareSpread(int[][] graph, int[] initial) 
    {
        int n = graph.length;
        int[] size = new int[n];
        int[] par = new int[n];

        for (int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        Arrays.sort(initial);// because we need the minimum index
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && graph[i][j] == 1) {
                    int p1 = findPar(i,par);
                    int p2 = findPar(j,par);

                    if (p1 != p2) {
                        par[p1] = p2;
                        size[p2] += size[p1];
                    }
                }
            }
        }

        int[] InfectedNodesInCity = new int[n];
        for (int i : initial) 
        {
            int leader = findPar(i,par);
            InfectedNodesInCity[leader]++;
        }

        int ans = initial[0];
        int maxPopulatedCity = 0;
        for (int i : initial) {
            int NoOfNodesInfected = InfectedNodesInCity[findPar(i,par)];
            if (NoOfNodesInfected == 1 && size[findPar(i,par)] > maxPopulatedCity) {
                maxPopulatedCity = size[findPar(i,par)];
                ans = i;
            }
        }

        return ans;
    }
    // Find Cheapest Flight USING BELLMAN FORD BFS SOLUTION IS GIVING TLE
    // BFS SOLUTION IS IN LEETCODE NOTES
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) 
    {
        int[] dis = new int[n];
        Arrays.fill(dis, (int) 1e9);
        dis[src] = 0;

        for (int EdgeCount = 1; EdgeCount <= K + 1; EdgeCount++) 
        {
            int[] ndis = new int[n];
            for (int i = 0; i < n; i++)
                ndis[i] = dis[i];

            for (int[] e : flights) 
            {
                int u = e[0], v = e[1], w = e[2];
                if (dis[u] != (int) 1e9 && dis[u] + w < ndis[v])
                    ndis[v] = dis[u] + w;
            }

            dis = ndis;
        }

        return dis[dst] != (int) 1e9 ? dis[dst] : -1;
    }
    // leetcode 959 region cut by slashes
    public static int regionsBySlashes(String[] grid) 
    {
        int n = grid.length;
        int m = grid[0].length();
        int count = 0;
        int[] par = new int[(n+1)*(m+1)];
        for(int i = 0;i<n+1;i++)
        {
            for(int j = 0;j<m+1;j++)
            {
                if(i == 0 || j == 0 || i == n || j == m) par[i*(m+1)+j] = 0;
                else par[i*(m+1)+j] = i*(m+1)+j;
            }
        }
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                if(grid[i].charAt(j) != ' ')
                {
                    int x = 0;
                    int y = 0;
                    if(grid[i].charAt(j) == '/')
                    {
                        x = findPar(i*(m+1)+(j+1) , par);
                        y = findPar((i+1)*(m+1)+j , par);
                    }
                    else if(grid[i].charAt(j) == '\\')
                    {
                        x = findPar(i*(m+1)+j,par);
                        y = findPar((i+1)*(m+1)+(j+1),par);
                    }
                    if(x!=y)
                    {
                        if(x == 0 && y != 0) par[y] = x;
                        else par[x] = y;
                    }
                    else if(x == y) count++;
                }
                
            }
        }
        return count + 1;
    }
    
    public static void main(String[] args) 
    {
        //smallestString("parker","morris","parser");
        //journeyToMoon(20, new int[][]{{0,1},{1,2},{3,4},{5,6},{2,5},{6,10}});
        //int[][] roads = new int[][]{{1,2,10},{1,4,10},{4,5,20},{5,6,10},{6,7,10},{7,3,40},{3,2,20},{1,3,30},{1,6,8}};
        //KruskalsForMrPresident(9,7, roads, 25);
        //numBusesToDestination(roads, 0, 5);
        // int[][] time = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        // System.out.println(networkDelayTime(time, 4, 2));
    }
}