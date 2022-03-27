class leftGraphsQues
{
    // 797. All Paths From Source to Target
	// You need not have a boolean array because the graph is DAG
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) 
    {
        List<List<Integer>> ans = new ArrayList<>();   
        List<Integer> temp = new ArrayList<>();
        printAns(graph,ans,temp,0);
        return ans;
    }
    
    public void printAns(int[][] graph , List<List<Integer>> ans , List<Integer> temp , int par)
    {
        temp.add(par);
        if(par == graph.length-1)
        {
            List<Integer> t = new ArrayList<>();
            for(int ele : temp) t.add(ele);
            ans.add(t);
        }
        for(int ele : graph[par]) printAns(graph,ans,temp,ele);
        temp.remove(temp.size()-1);
    }

    // 1557. Minimum Number of Vertices to Reach All Nodes

    // MOTHER VERTEX CONCEPT NOT PASSING ON LEETCODE

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) 
    {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0;i<n;i++) graph[i] = new ArrayList<>();
        for(List<Integer> ele : edges){
            graph[ele.get(0)].add(ele.get(1));
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        HashSet<Integer> check = new HashSet<>();
        
        boolean[] vis = new boolean[n];
        ArrayList<Integer> topoOrder = new ArrayList<>();
        for(int i = 0;i<n;i++){
            if(!vis[i]){
                topo(graph,i,topoOrder,vis);
            }
        }
        
        while(topoOrder.size() > 0)
        {
            int vertex = topoOrder.remove(topoOrder.size()-1);
            if(check.contains(vertex)) continue;
            dfs(graph,vertex,check);
            ans.add(vertex);
            if(check.size() == n) break;
        }
        return ans;
    }
    
    public void dfs(ArrayList<Integer>[]graph , int par, HashSet<Integer> check)
    {
        check.add(par);
        for(int ele : graph[par]) dfs(graph,ele,check);
    }
    
    public void topo(ArrayList<Integer>[]graph , int par , ArrayList<Integer> topoOrder , boolean[] vis)
    {
        vis[par] = true;
        for(int ele : graph[par]){
            if(!vis[ele]){
                topo(graph,ele,topoOrder,vis);
            }
        }
        topoOrder.add(par);
    }


    // Another Idea
    // We only have to count the number of nodes with zero incoming edges.

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) 
    {
        int[] indegree = new int[n];
        for(List<Integer> ele : edges) indegree[ele.get(1)]++;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0;i<n;i++) if(indegree[i] == 0) ans.add(i);
        return ans;
    }

    // 841. Keys and Rooms

    public boolean canVisitAllRooms(List<List<Integer>> rooms) 
    {
        Set<Integer> visited=new HashSet<>();
        addKey(0, rooms,visited);
        return visited.size() == rooms.size();
    }

    public void addKey(int room, List<List<Integer>> rooms,Set<Integer> visited) 
    {
        visited.add(room);
        for (int key: rooms.get(room)) if (!visited.contains(key)) addKey(key, rooms,visited);
    }

    // 1615. Maximal Network Rank

    public int maximalNetworkRank(int n, int[][] roads) 
    {
        HashSet<Integer>[] graph = new HashSet[n];
        for(int i = 0;i<n;i++) graph[i] = new HashSet<>();
        for(int[] ele : roads)
        {
            graph[ele[0]].add(ele[1]);
            graph[ele[1]].add(ele[0]);
        }
        
        int max = 0;
        
        for(int i = 0;i<n;i++)
        {
            for(int j = i+1;j<n;j++)
            {
                int v1 = graph[i].size();
                int v2 = graph[j].size();
                
                if(graph[i].contains(j) || graph[j].contains(i)) max = Math.max(max,v1+v2-1);
                else max = Math.max(max,v1+v2);
            }
        }
        return max;
    }


    // 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance

    public int findTheCity(int n, int[][] edges, int th) 
    {
        int[][] dp = new int[n][n];
        for(int[] e : dp){
            Arrays.fill(e,(int)1e9);
        }
        
        for(int []ele : edges){
            int u = ele[0];
            int v = ele[1];
            int w = ele[2];
            dp[u][v] = w;
            dp[v][u] = w;
        }
        
        for(int x = 0;x<n;x++)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(i == j)
                    {
                        dp[i][j] = 0;
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j] , dp[i][x] + dp[x][j]);
                }
            }
        }
        
        int fCount = (int)1e9;
        int city = -1;
        for(int i = 0;i<dp.length;i++)
        {
            int count = 0;
            for(int e : dp[i])
            {
                if(e <= th) count++;
            }
            if(count <= fCount)
            {
                fCount = count;
                city = i;
            }
        }
        return city;
    }

    // 1042. Flower Planting With No Adjacent

    public int[] gardenNoAdj(int n, int[][] paths)
    {
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=0;i<=n;i++) graph[i] = new ArrayList<>();
        
        int[] flow = new int[n+1];
        Arrays.fill(flow,5);
        for(int[]ele : paths)
        {
            graph[ele[0]].add(ele[1]);
            graph[ele[1]].add(ele[0]);
        }
        
        for(int i = 1;i<=n;i++)
        {
            boolean[] p = new boolean[6];
            for(int ele : graph[i]) p[flow[ele]] = true;
            for(int j=1;j<5;j++)
            {
                if(!p[j]){
                    flow[i] = j;
                    break;
                }
            }
        }
        int[] ans = new int[n];
        for(int i = 1;i<=n;i++) ans[i-1] = flow[i];
        return ans;
    }

    // 1462. Course Schedule IV

    // Solution One Giving TLE
    // Because of copy function of hashmap

    public List<Boolean> checkIfPrerequisite(int n, int[][] pre, int[][] q)
    {
        HashSet<Integer>[] map = new HashSet[n];
        for(int i = 0;i<n;i++) map[i] = new HashSet<>();
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0;i<n;i++) graph[i] = new ArrayList<>();
        int[] indeg = new int[n];
        for(int[] ele : pre)
        {
            int u = ele[0];
            int v = ele[1];
            graph[u].add(v);
            indeg[v]++;
        }
        
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0;i<indeg.length;i++) if(indeg[i] == 0) queue.add(i);
        
        while(queue.size() > 0)
        {
            int pop = queue.removeFirst();
            for(int ele : graph[pop])
            {
                map[ele].add(pop);
                map[ele].addAll(map[pop]);
                queue.add(ele);
            }
        }
        
        List<Boolean> ans = new ArrayList<>();
        
        for(int[] ele : q)
        {
            int u = ele[0];
            int v = ele[1];
            
            if(map[v].contains(u)) ans.add(true);
            else ans.add(false);
        }
        return ans;
    }

    // Solution 2
    // Note That with the help of Flowd Warshal we can find the reachibility as well

    public List<Boolean> checkIfPrerequisite(int n, int[][] pre, int[][] q)
    {
        int[][] dp = new int[n][n];
        for(int[]ele:dp) Arrays.fill(ele,-1);
        
        for(int[] ele : pre)
        {
            int u = ele[0];
            int v = ele[1];
            dp[u][v] = 0;
        }
        
        for(int x = 0;x<n;x++)
        {
            for(int i = 0;i<n;i++)
            {
                for(int j = 0;j<n;j++)
                {
                    if(dp[i][x] != -1 && dp[x][j] != -1)
                    {
                        dp[i][j] = 0;
                    }
                }
            }
        }
        
        List<Boolean> ans = new ArrayList<>();
        
        for(int[] ele : q)
        {
            if(dp[ele[0]][ele[1]] == 0) ans.add(true);
            else ans.add(false);
        }
        
        // for(int[]ele : dp) System.out.println(Arrays.toString(ele));
        return ans;
        
    }


    // 133. Clone Graph
    // With HashMap
    public Node cloneGraph(Node node) 
    {
        if(node == null) return null;
        boolean[] vis = new boolean[101];
        HashMap<Integer,Node> map = new HashMap<>();
        Node nGraph = new Node();
        createGraph(node,nGraph,vis,map);
        return nGraph;
    }
    
    public void createGraph(Node node , Node nGraph , boolean[]vis , HashMap<Integer,Node> map)
    {
        nGraph.val = node.val;
        map.put(nGraph.val,nGraph);
        vis[node.val] = true;
        for(Node ele : node.neighbors)
        {
            if(vis[ele.val])
            {
                nGraph.neighbors.add(map.get(ele.val));
                continue;
            }
            nGraph.neighbors.add(new Node());
            createGraph(ele,nGraph.neighbors.get(nGraph.neighbors.size()-1),vis,map);
        }
    }


    // Without HashMap Solution

    public Node cloneGraph(Node node) 
    {
        if(node == null) return null;
        Node[] vis = new Node[101];
        Node nGraph = new Node();
        createGraph(node,nGraph,vis);
        return nGraph;
    }
    
    public void createGraph(Node node , Node nGraph , Node[]vis)
    {
        nGraph.val = node.val;
        vis[node.val] = nGraph;
        for(Node ele : node.neighbors)
        {
            if(vis[ele.val]!=null)
            {
                nGraph.neighbors.add(vis[ele.val]);
                continue;
            }
            nGraph.neighbors.add(new Node());
            createGraph(ele,nGraph.neighbors.get(nGraph.neighbors.size()-1),vis);
        }
    }


    // 886. Possible Bipartition
    // Checking For Bipartite Graph simple Code
    public boolean possibleBipartition(int n, int[][] dislikes)
    {
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=0;i<n+1;i++) graph[i] = new ArrayList<>();
        
        for(int[] ele : dislikes)
        {
            int u = ele[0];
            int v = ele[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        int[]vis = new int[n+1];
        Arrays.fill(vis,-1);
        for(int i=1;i<=n;i++)
        {
            if(vis[i] != -1) continue;
            int level = 1;
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(i);
            vis[i] = 0;
            
            while(queue.size() > 0)
            {
                int size = queue.size();
                while(size-- > 0)
                {
                    int pop = queue.removeFirst();
                    
                    for(int ele : graph[pop]) 
                    {    
                        if(vis[ele] == -1) 
                        {
                            queue.add(ele);
                            vis[ele] = level;
                        }
                        else if(vis[ele] != level) return false;
                        else if(vis[ele] == level) continue;
                        
                    }
                }
                level = (level+1)%2;
            }
        }
        return true;
    }


    /// 1514. Path with Maximum Probability

    public class pair{
        int v;
        double w;    
        pair(){
        }
        pair(int data,double psf){
            this.v = data;
            this.w = psf;
        }
    }
    
    public class pair2{
        int v;
        double d;
        pair2(){
        }
        pair2(int v , double d){
            this.v = v;
            this.d = d;
        }
    }
    
    public class psf implements Comparator<pair>
    {
        public int compare(pair s1, pair s2) 
        {
            if (s1.w < s2.w) return 1;
            else if (s1.w > s2.w) return -1;
            return 0;
        }
    }
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end)
    {
        ArrayList<pair2>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++) graph[i] = new ArrayList<>();
        
        for(int i = 0;i<edges.length;i++)
        {
            int[] ele = edges[i];
            double w = succProb[i];
            int u = ele[0];
            int v = ele[1];
            graph[u].add(new pair2(v,w));
            graph[v].add(new pair2(u,w));
        }
        
        
        PriorityQueue<pair> queue = new PriorityQueue<>(new psf());
        
        // We could have used this as well
        // PriorityQueue<double[]> pq = new PriorityQueue<double[]>((a,b)->{
        //     return Double.compare(b[1],a[1]);
        // });
        
        
        double[] max = new double[n];
        queue.add(new pair(start,1.0));
        
        while(queue.size() > 0)
        {
            pair pop = queue.remove();
            if(pop.v == end) return pop.w;
            
            for(pair2 e : graph[pop.v]) 
            {
                if(max[e.v] < e.d*pop.w) // We HAve to check this to get rid of tle as th graph is not connected always
                {
                    queue.add(new pair(e.v,e.d*pop.w));
                    max[e.v] = e.d*pop.w;
                }
            }
        }
        return 0.0;
    }


    // 2192. All Ancestors of a Node in a Directed Acyclic Graph


    // Floyds Warshal Not Passing TLE

    public List<List<Integer>> getAncestors(int n, int[][] edges) 
    {
        int[][] dp = new int[n][n];
        for(int[]ele:dp) Arrays.fill(ele,-1);
        
        for(int[] ele : edges)
        {
            int u = ele[0];
            int v = ele[1];
            dp[v][u] = 0;
        }
        
        for(int x = 0;x<n;x++)
        {
            for(int i = 0;i<n;i++)
            {
                for(int j = 0;j<n;j++)
                {
                    if(dp[i][x] != -1 && dp[x][j] != -1)
                    {
                        dp[i][j] = 0;
                    }
                }
            }
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        
        int i = 0;
        for(int []ele : dp)
        {
            List<Integer> a = new ArrayList<>();
            for(int j=0;j<n;j++)
            {
                if(dp[i][j] == 0) a.add(j);
            }
            ans.add(a);
            i++;
        }
        return ans;
    }

    // 2192. All Ancestors of a Node in a Directed Acyclic Graph

     public List<List<Integer>> getAncestors(int n, int[][] edges) 
     {
        List<TreeSet<Integer>> ancestorList = new ArrayList();
        List<List<Integer>> list = new ArrayList();

        Queue<Integer> queue = new LinkedList();
        
        int degree[] = new int[n];
        ArrayList<Integer>[] connections = new ArrayList[n];
        
        for(int i = 0;i<n;i++){
            connections[i] = new ArrayList<Integer>();
            ancestorList.add(new TreeSet<Integer>());
        }
        
        for(int edge[]: edges){
           connections[edge[0]].add(edge[1]);
           degree[edge[1]]++;
        }
    
        for(int i = 0;i<n;i++){
            if(degree[i] == 0){
                queue.add(i);
            }
        }
        
     
        while(!queue.isEmpty()){
            
            int currNode = queue.poll();
            for(int node : connections[currNode])
            {
                ancestorList.get(node).addAll(new TreeSet(ancestorList.get(currNode)));
                ancestorList.get(node).add(currNode);
                
                degree[node]--;
                if(degree[node] == 0){
                    queue.add(node);
                }
            }
            
        }

        for(TreeSet<Integer> set : ancestorList) list.add(new ArrayList<Integer>(set));
        return list;
    }



    // 1129. Shortest Path with Alternating Colors

    // Important

    public class pair{
        int val;
        int c;
        pair(){
        }
        pair(int val , int c){
            this.val = val;
            this.c = c;
        }
    }
    
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) 
    {
        ArrayList<pair>[] graph = new ArrayList[n];
        for(int i =0;i<n;i++) graph[i] = new ArrayList<>();
        
        for(int[] ele : redEdges){
            int u = ele[0];
            int v = ele[1];
            graph[u].add(new pair(v,0));
        }
        
        for(int[] ele : blueEdges){
            int u = ele[0];
            int v = ele[1];
            graph[u].add(new pair(v,1));
        }
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{return a[2]-b[2];});
        
        int[] zero = new int[n];
        Arrays.fill(zero,(int)1e9);
        
        int[] one = new int[n];
        Arrays.fill(one,(int)1e9);
        
        int[] ans = new int[n];
        Arrays.fill(ans,-1);
        
        queue.add(new int[]{0,-1,0});
        
        while(queue.size() > 0)
        {
            int[] pop = queue.remove();
            if(pop[0] == 0) ans[0] = 0;
            else ans[pop[0]] = (ans[pop[0]] == -1) ? pop[2]  : Math.min(ans[pop[0]],pop[2]);
            
            for(pair ele : graph[pop[0]])
            {
                if(ele.c!=pop[1])
                {
                    if(ele.c == 1)
                    {
                        if(one[ele.val] > pop[2]+1)
                        {
                            one[ele.val] = pop[2]+1;
                            queue.add(new int[]{ele.val,ele.c,pop[2]+1});
                        }
                    }
                    else if(ele.c == 0)
                    {
                        if(zero[ele.val] > pop[2]+1)
                        {
                            zero[ele.val] = pop[2]+1;
                            queue.add(new int[]{ele.val,ele.c,pop[2]+1});
                        }
                    }
                }
            }
        }
        return ans;
    }


    
}