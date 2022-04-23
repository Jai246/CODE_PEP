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

    // 1361. Validate Binary Tree Nodes

    // Very Important Solution

    // BY UNION FIND WE WILL TAKE CARE OF BOTH CYCLES AND MORE THAN ONE INDEGREE
    // BY UNION FIND WE CAN ALSO TAKE CARE ABOUT WHETHER THERE ARE MORE THAN ONE COMPONENTS OR NOT
    public boolean validateBinaryTreeNodes(int n, int[] left, int[] right)
    {
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i; //every node is parent of itselt

        for(int i = 0; i < n; i++) 
        {
            int l = left[i];
            int r = right[i];
            int x = find(parent, i);
            if(l != -1) 
            {
                int root = find(parent, l);
                if(root == x) return false; // tells boyh cycle ans more than 1 indegree
                union(parent, i, l);
            }
            if(r != -1) 
            {
                int root = find(parent, r); 
                if(root == x) return false; // tells boyh cycle ans more than 1 indegree
                union(parent, i, r);
            }
        }
        int root = find(parent, 0);
        for(int i = 1; i < n; i++) 
        {
            int rootI = find(parent, i);
            if(root != rootI) return false; //Root of all node should be same - note the path compression
        }
        return true;
    }
    
    public void union(int[] parent, int x, int y) {
        parent[y] = x;
    }
    
    int find(int[] parent, int x) 
    {
        if(parent[x] != x) 
        {
            //Path compression for faster parent search - this makes Tree root as parent of all nodes
            parent[x] = find(parent, parent[x]); 
            return parent[x];
        }
        return x;
    }

    // 2101. Detonate the Maximum Bombs

    // bomb A can trigger bomb B
    // bomb C can trigger bomb B
    // bomb B cannot trigger bomb A and C
    // bomb A, C cannot trigger each other
    // but in Union Find A B C will be in same group. (cause A->B, C->B
    // it is not possible that each of them triggers other two bombs.

    // IN THIS QUESTION TYPECASTING TO LONG IS VERY IMPORTANT
    public long findDistance(int x1 , int y1 , int x2 , int y2)
    {
        long val = (long)Math.abs(x2-x1)*(long)Math.abs(x2-x1) + (long)Math.abs(y2-y1)*(long)Math.abs(y2-y1);
        // System.out.println(val);
        return val;
    }
    
    public void dfs(HashSet<Integer>[] graph , int p , boolean[] vis , int[]count)
    {
        vis[p] = true;
        count[0]++;
        for(int ele : graph[p])
        {
            if(!vis[ele]) dfs(graph,ele,vis,count);
        }
    }
    
    public int maximumDetonation(int[][] bombs) 
    {
        int n = bombs.length;
        HashSet<Integer>[] graph = new HashSet[n];
        for(int i = 0;i<n;i++) graph[i] = new HashSet<>();
        
        
        for(int i = 0;i<n;i++)
        {
            for(int j = i+1;j<n;j++)
            {
                int[] b1 = bombs[i];
                int[] b2 = bombs[j];
                
                long dist = findDistance(b1[0],b1[1],b2[0],b2[1]);
                
                int r1 = b1[2];
                int r2 = b2[2];
                
                if((long)r1*(long)r1 >= dist) graph[i].add(j);
                if((long)r2*(long)r2 >= dist) graph[j].add(i);
            }
        }
        int maxCount = -(int)1e9;
        
        for(int i = 0;i<n;i++)
        {
            int[] count = new int[]{0};
            boolean[] vis = new boolean[n];
            dfs(graph,i,vis,count);
            maxCount = Math.max(maxCount , count[0]);
        }
        
        return maxCount;
    }


    // 1786. Number of Restricted Paths From First to Last Node

    public int calcResPath(ArrayList<int[]>[] graph , int[]dist , int p , boolean[]vis , int[]dp)
    {
        if(dp[p]!=-1) return dp[p];
        if(p == graph.length-1) return dp[p] = 1;
        vis[p] = true;
        int c = 0;
        for(int[] ele : graph[p])
        {
            if(dist[ele[0]] < dist[p] && !vis[ele[0]]) c = (c +  calcResPath(graph,dist,ele[0],vis,dp))%((int)1e9 + 7);
        }
        vis[p] = false;
        dp[p] = c%((int)1e9 + 7);
        return dp[p];
    }
    public int countRestrictedPaths(int n, int[][] edges) 
    {
        ArrayList<int[]>[] graph = new ArrayList[n+1];
        for(int i =0;i<n+1;i++) graph[i] = new ArrayList<>();
        
        for(int[] ele : edges){
            int u = ele[0];
            int v = ele[1];
            int w = ele[2];
            graph[u].add(new int[]{v,w});
            graph[v].add(new int[]{u,w});
        }
        
        int[] minDist = new int[n+1];
        Arrays.fill(minDist,(int)1e11);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        queue.add(new int[]{n,0});
        minDist[n] = 0;
        while(queue.size() > 0)
        {
            int[] t = queue.remove();
            
            for(int []node : graph[t[0]])
            {
                if(node[1] + t[1] < minDist[node[0]]) 
                {
                    minDist[node[0]] = node[1] + t[1]; // To stop extra edges entry in PQ and getting rid of TLE
                    queue.add(new int[]{node[0],node[1] + t[1]});
                }
            }
        }
        
        // System.out.println(Arrays.toString(minDist));
        
        int[] count = new int[]{0};
        boolean[] vis = new boolean[n+1];
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        calcResPath(graph,minDist,1,vis,dp);
        return dp[1];
    }


    // 1976. Number of Ways to Arrive at Destination

    // Very Important Dijikstra Style to find no 
    // of minimum  Paths from source to any node 
    // We have also used a DP(paths) in this quesstion

    // One thing I Noted about dijikstra Algorithm that it
    // Kind of Behaves Like TopoSort because first all 
    // minimum weights are processed.

    public int countPaths(int n, int[][] edges)
    {
        ArrayList<int[]>[] graph = new ArrayList[n];
        for(int i =0;i<n;i++) graph[i] = new ArrayList<>();

        for(int[] ele : edges)
        {
            int u = ele[0];
            int v = ele[1];
            int w = ele[2];
            graph[u].add(new int[]{v,w});
            graph[v].add(new int[]{u,w});
        }
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{return a[1] - b[1];});
        
        int[] min = new int[n];
        Arrays.fill(min,(int)1e11);
        int[] path = new int[n];
        path[0] = 1;
        min[0] = 0;
        int mod = (int)1e9+7;
        queue.add(new int[]{0,0});
        
        while(queue.size() > 0)
        {
            int[] t = queue.remove();
            int u = t[0];
            int w1 = t[1];
            if(u == n-1) return path[n-1]; // Will always give total no of paths because at the time of popping out
            // there are No more Minimum Distance ways to reach destination node because all minimum ways are already
            // resolved and all other psf present in PQ is greater than this one
            for(int[] ele : graph[u])
            {
                int v = ele[0];
                int w2 = ele[1];
                if(w1 + w2 == min[v]) path[v] = (path[v] + path[u])%mod;
                else if(w1 + w2 < min[v])
                {
                    path[v] = path[u]%mod;
                    min[v] = w1+w2;
                    queue.add(new int[]{v,w1+w2});
                }
            }
        }
        return -1;
    }
    

    // 1466. Reorder Routes to Make All Paths Lead to the City Zero
    // Very Simple Solution
    // reverse the current edges with weight 0;
    // add new edges in the orignal direction with weight 1

    public int minReorder(int n, int[][] edges) 
    {
        ArrayList<int[]>[] graph = new ArrayList[n];
        for(int i =0;i<n;i++) graph[i] = new ArrayList<>();

        for(int[] ele : edges)
        {
            int u = ele[0];
            int v = ele[1];
            graph[u].add(new int[]{v,1});
            graph[v].add(new int[]{u,0});
        }
        
        LinkedList<Integer> queue = new LinkedList<>();
        
        boolean[] vis = new boolean[n];
        
        queue.addLast(0);
        int count = 0;
        while(queue.size() > 0)
        {
            int v = queue.removeFirst();
            vis[v] = true;
            
            for(int[] ele : graph[v])
            {
                if(vis[ele[0]]) continue;
                count+=ele[1];
                queue.add(ele[0]);
            }
        }
        
        return count;
    }


    // 2039. The Time When the Network Becomes Idle

    public int networkBecomesIdle(int[][] edges, int[] patience) 
    {
        // We Can Apply Simple BFS as Well
        int n = patience.length;
        ArrayList<int[]>[] graph = new ArrayList[n];
        for(int i =0;i<n;i++) graph[i] = new ArrayList<>();
        
        for(int[] ele : edges){
            int u = ele[0];
            int v = ele[1];
            int w = 1;
            graph[u].add(new int[]{v,w});
            graph[v].add(new int[]{u,w});
        }
        
        int[] minDist = new int[n];
        Arrays.fill(minDist,(int)1e11);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        queue.add(new int[]{0,0});
        minDist[0] = 0;
        while(queue.size() > 0)
        {
            int[] t = queue.remove();
            
            for(int []node : graph[t[0]])
            {
                if(node[1] + t[1] < minDist[node[0]]) // To stop extra edges entry in PQ and getting rid of TLE
                {
                    minDist[node[0]] = node[1] + t[1]; 
                    queue.add(new int[]{node[0],node[1] + t[1]});
                }
            }
        }
        
        
        int max = 0;
        for(int i = 1;i<n;i++)
        {
            int dis = minDist[i]*2;
            int pat = patience[i];
            int val = dis + dis - (dis % pat);
            if(dis % pat == 0) val -= pat;
            // Yeh VAli Line Isliye likhi kyoki agar dis pat kaa multiple hoga toh 
            // last wala message generate nahi ho payega kyoki First Message phle he vaha pahunch jayega
            // DryRun On This
            max = Math.max(max,val);
        }
        return max+1;
    }


    // 310. Minimum Height Trees
    // Very Important Approach Saw From Leetcode's Original Solution
    // The Idea Is to trim the leave nodes and do this until the size of 
    // the graph is greater than 2
    // Note That A Tree Can HAve Atmost two centroids
    // If we Consider Odd Length then centriod will we 1;
    // In Case Of Even Centriod Will be 2;
    public List<Integer> findMinHeightTrees(int n, int[][] edges) 
    {
        if (n < 2) {
            ArrayList<Integer> centroids = new ArrayList<>();
            for (int i = 0; i < n; i++) centroids.add(i);
            return centroids;
        }
        
        HashSet<Integer>[] graph = new HashSet[n];
        for(int i = 0;i<n;i++) graph[i] = new HashSet<>();
        for(int[] ele : edges)
        {
            int u = ele[0];
            int v = ele[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        ArrayList<Integer> leaves = new ArrayList<>();
        for(int i = 0;i<n;i++) 
        {
            if(graph[i].size() == 1) 
            {
                leaves.add(i);
            }
        }
        
        int nodes = n;
        // System.out.println(leaves.toString());
        while(nodes > 2)
        {
            nodes -= leaves.size();
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i : leaves) 
            {
                int val = -1;
                for(int ele : graph[i]) val = ele;
                graph[i].remove(val);
                graph[val].remove(i);
                if(graph[val].size() == 1) temp.add(val);
            }
            // System.out.println(leaves.toString());
            leaves = temp;
        }
        return leaves;
    }
}