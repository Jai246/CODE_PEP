import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Arrays;
class extraFaang
{
    // leetcode 399 EVALUATE DIVISION
    class edge
    {
        String v = "";
        double w = 0;
        edge(String v , double w)
        {
            this.v = v;
            this.w = w;
        }
    }
    public boolean dfsGetVal(HashMap<String,ArrayList<edge>> graph , String src , String dest , double[] ans , HashSet<String> vis)
    {
        if(src.equals(dest)) return true; // not working with ==
        vis.add(src);
        boolean res = false;
        for(edge E : graph.get(src))
        {
            if(!vis.contains(E.v)) res = res || dfsGetVal(graph,E.v,dest,ans,vis);
            if(res)
            {
                ans[0] = ans[0] * E.w;
                return true;
            }
        }
        vis.remove(src);
        return res;
    }
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) 
    {   
        HashMap<String,ArrayList<edge>> graph = new HashMap<>();
        double[] ans = new double[queries.size()];
        Arrays.fill(ans,-1.0);
        
        for(int i = 0;i<equations.size();i++)
        {
            List<String> arr = equations.get(i);
            String a = arr.get(0);
            String b = arr.get(1);
            
            double val1 = values[i];
            double val2 = 1.0/val1;
            
            if(!graph.containsKey(a)) graph.put(a,new ArrayList<>());
            if(!graph.containsKey(b)) graph.put(b,new ArrayList<>());
            
            graph.get(a).add(new edge(b,val1));
            graph.get(b).add(new edge(a,val2));
        }
        
        
        for(int i = 0;i<queries.size();i++)
        {
            List<String> arr = queries.get(i);
            String a = arr.get(0);
            String b = arr.get(1);
                        
            if(graph.containsKey(a) && graph.containsKey(b))
            {
                HashSet<String> vis = new HashSet<>();
                double[] temp = new double[]{1.0};
                boolean res = dfsGetVal(graph,a,b,temp,vis);
                if(res) ans[i] = temp[0];
            }
        }
        return ans;
    }

    // NUBER OF ENCLAVES LEETCODE 1020
    // FIRST MAKE ALL ONES 2 WHICH ARE REACHABLE FROM THE BOUNDARY
    // THEN CALCULATE THE LEFT ONES

    public void make2(int[][] grid , int r , int c)
    {
        int l = grid.length;
        int m = grid[0].length;
        int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        grid[r][c] = 2;
        for(int[] a : dir)
        {
            int x = r + a[0];
            int y = c + a[1];

            if(x >= 0 && y >= 0 && x < l && y < m && grid[x][y] == 1)
            {
                make2(grid,x,y);
            }
        }
    }
    public int numEnclaves(int[][] grid) 
    {
        int count = 0;
        int l = grid.length;
        int m = grid[0].length;
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j] == 1 && (i == 0 || j == 0 || i == l-1 || j == m-1))
                {
                    make2(grid,i,j);
                }
            }
        }
        for(int i = 0;i<grid.length;i++)
        {
            for(int j = 0;j<grid[0].length;j++)
            {
                if(grid[i][j] == 1) count++;
            }
        }
        return count;
    }

    // LEETCODE 542 01 MATRIX DP SOLUTION ITERATING TWO TIMES
    // Iterate over the matrix from top to bottom AND left to right
    // do the back iteration from bottom to top-right to left
    // WE CAN DO THIS USING MULTI POINT BFS APPROACH AS DISCUSSED IN AS FAR FROM LAND ANS POSSIBLE
    // GO THROUGH THAT CODE AS WELL

    class Solution 
    {
        public int[][] updateMatrix(int[][] mat) 
        {
            int l = mat.length;
            int m = mat[0].length;
            int[][] ans = new int[l][m];
            
            for(int[] ele : ans) Arrays.fill(ele,(int)1e9);
            
            int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
            
            for(int i = 0;i<l;i++){
                for(int j = 0;j<m;j++){
                    if(mat[i][j] == 1){
                        for(int[] ele : dir){
                            int x = i + ele[0];
                            int y = j + ele[1];
                            if(x>=0 && y>=0 && x<l && y<m){
                                if(mat[x][y] == 0){
                                    ans[i][j] = 1;
                                    break;
                                }
                                else{
                                    ans[i][j] = Math.min(ans[x][y]+1,ans[i][j]);
                                }
                            }
                        }
                    }
                    else ans[i][j] = 0;
                }
            }
            for(int i = l-1;i>=0;i--){
                for(int j = m-1;j>=0;j--){
                    if(mat[i][j] == 1){
                        for(int[] ele : dir){
                            int x = i + ele[0];
                            int y = j + ele[1];
                            if(x>=0 && y>=0 && x<l && y<m){
                                if(mat[x][y] == 0){
                                    ans[i][j] = 1;
                                    break;
                                }
                                else{
                                    ans[i][j] = Math.min(ans[x][y]+1,ans[i][j]);
                                }
                            }
                        }
                    }
                    else ans[i][j] = 0;
                }
            }
            return ans;
        }
    }

    // MOTHER VERTEX GEEKS FOR GEEKS
    // KOUSA RAJU ALGORITHM APPLICATION
    // FILL THE STACK AS WE FILL IN KOSA RAJU ALGO AND THE TOP ELEMENT WILL BE THE MOTHER VETREX 

    // 0 -> part of my own path
    // 1 -> visited
    // -1 -> not visited
    public int findMotherVertex(int V, ArrayList<ArrayList<Integer>>adj)
    {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] vis = new int[adj.size()];
        int[] count = new int[]{0};
        Arrays.fill(vis,-1);
        int possibleMother = 0;
        for(int i = 0;i<adj.size();i++)
        {
            dfs(i,adj,stack,vis,count);
            possibleMother = stack.removeLast();
            if(count[0] + 1 == adj.size()) break;
            stack = new LinkedList<>();
            count = new int[]{0};
            vis = new int[adj.size()];
            Arrays.fill(vis,-1);
        }
        count[0]++;
        return (count[0]<adj.size()) ? -1 : possibleMother;
    }
    public void dfs(int src ,ArrayList<ArrayList<Integer>>adj, LinkedList<Integer> stack,int[] vis,int[] count)
    {
        vis[src] = 0;
        for(int ele : adj.get(src))
        {
            if(vis[ele] == -1)
            {
                count[0] += 1;
                dfs(ele,adj,stack,vis,count);
            }
        }
        vis[src] = 1;
        stack.addLast(src);
    }

   // A Walk to Remember HAKER HakerEarth

    public static void mainWalk(String args[] ) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        int m = Integer.parseInt(arr[0]);
        int n = Integer.parseInt(arr[1]);
        @SuppressWarnings("unChecked")

        int[] ans = new int[n+1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1;i<=n;i++) graph[i] = new ArrayList<>();
        for(int i = 1;i<=n;i++)
        {
            String[] a = sc.nextLine().split(" ");
            int u = Integer.parseInt(a[0]);
            int v = Integer.parseInt(a[1]);
            graph[u].add(v);
        }
        int[] vis = new int[n+1];
        Arrays.fill(vis,-1);
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = 1;i<=n;i++)
        {
            if(vis[i] == -1)
            {
                dfsTopological(graph,i,vis,stack);
            }
        }
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] ngraph = new ArrayList[n+1];
        for(int i = 1;i<=n;i++) ngraph[i] = new ArrayList<>();
        for(int i = 1;i<=n;i++) // reversing the graph
        {
            for(int ele : graph[i])
            {
                ngraph[ele].add(i);
            }
        }
        boolean[] vis2 = new boolean[n+1]; 

        while(stack.size()!=0)
        {
            int i = stack.removeLast();
            ArrayList<Integer> temp = new ArrayList<>();
            if(!vis2[i])
            {
                dfs_SCC(ngraph,i,vis2,temp);
            }
            if(temp.size() > 1)
            {
                for(int ele : temp)
                {
                    ans[ele] = 1;
                }
            }
        }
        for(int i = 1;i<=n;i++)
        {
            System.out.print(ans[i] + " ");
        }
    }
    public static void dfsTopological(ArrayList<Integer>[] graph ,int src , int[] vis , LinkedList<Integer> stack)
    {
        vis[src] = 0;
        for(int ele : graph[src])
        {
            if(vis[ele] == -1)
            {
                dfsTopological(graph,ele,vis,stack);
            }
        }
        vis[src] = 1;
        stack.addLast(src);
    }
    public static void dfs_SCC(ArrayList<Integer>[] graph ,int src , boolean[]vis ,ArrayList<Integer> scc)
    {
        vis[src] = true;
        scc.add(src);
        for(int ele : graph[src])
        {
            if(!vis[ele])
            {
                dfs_SCC(graph,ele,vis,scc);
            }
        }
    }

    // LEETCODE 773 SLIDING PUZZLE IMPORTANT BFS
    // CHECK DRYRUN
    public int slidingPuzzle(int[][] board){
        int level = 0;
        String finalStr = "123450";
        int[][] swapMap = {{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{4,2}};
        LinkedList<String> queue = new LinkedList<>();
        HashSet<String> vis = new HashSet<>();
        String start = "";
        for(int i=0;i<2;i++){
            for(int j = 0;j<3;j++){
                start += board[i][j];
            }
        }
        queue.addLast(start);
        
        while(queue.size()!=0){
            int size = queue.size();
            while(size-->0){
                String temp = queue.removeFirst();
                if(temp.equals(finalStr)){
                    System.out.println(temp);
                    return level;
                }
                vis.add(temp);
                int idx = 0;
                
                for(int i = 0;i<6;i++){
                    if(temp.charAt(i) == '0'){
                        idx = i;
                        break;
                    }
                }
                for(int ele : swapMap[idx]){
                    int idx2 = ele;
                    StringBuilder sb = new StringBuilder();
                    sb.append(temp);
                    char t = sb.charAt(idx2);
                    sb.setCharAt(idx2,'0');
                    sb.setCharAt(idx,t);
                    String check = sb.toString();
                    if(!vis.contains(check)) queue.addLast(check);
                }
            }
            level+=1;
        }
        return -1;
    }
    // LEETCODE 127. Word Ladder IMPORTANT
    // BFS SOLUTION 
    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        HashSet<String> vis = new HashSet<>();
        HashSet<String> check = new HashSet<>();
        for(String str : wordList) check.add(str);
        LinkedList<String> queue = new LinkedList<>();
        int level = 0;
        queue.add(beginWord);
        while(queue.size()!=0){
            int size = queue.size();
            while(size-- > 0){
                String temp = queue.removeFirst();
                if(temp.equals(endWord)){
                    return level + 1;
                }
                vis.add(temp);
                for(int i = 0;i<beginWord.length();i++){
                    StringBuilder sb = new StringBuilder();
                    sb.append(temp);
                    for(int j = 0;j<26;j++){
                        char newChar = (char)(j+(int)'a');
                        if(newChar!=sb.charAt(i)){
                            sb.setCharAt(i,newChar);
                            String newString = sb.toString();
                            if(check.contains(newString)){
                                if(!vis.contains(newString)){
                                    queue.addLast(newString);
                                    vis.add(newString);
                                }
                            }
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
    
    // SATISFIBILITY OF EQUATIONS LEETCODE 990
    // A==B , B==C , C!=D , D!=F , A==F
    // FIRST TRAVERSE AND SET ALL == WALE IN PARENT ARRAY
    // A,B,C,F SUPPOSE THERE VALUE TO BE ONE
    // NOW TRAVERSE ON !=
    // SINCE C!=D D IS NOT IN THE GROUP OF ONES SO IT IS POSSIBLE THAT D CAN HAVE 0 AS THE VALUE
    // SINCE D!=F D IS NOT IN THE GROUP OF ONES SO IT IS POSSIBLE THAT D CAN HAVE 0 AS THE VALUE

    // LET A CASE WHERE A==B && A!=B THEN 
    // FIRST ITR A,B GROUP 1
    // NOW IN SECOND ITR A!=B BUT BOTH HAVE THE SAME PARENT ACCORDING TO FIRST ITR SO IT IS CONTRADICTING

    public int findPar(int u)
    {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }
    int[] par;
    public boolean equationsPossible(String[] equations) 
    {
        par = new int[26];
        
        for(int i = 0 ; i < 26 ; i++){
            par[i] = i;
        }
        
        for(String str : equations){
            int p1 = findPar(str.charAt(0) - 'a');
            int p2 = findPar(str.charAt(3) - 'a');
            if(p1 != p2 && str.charAt(1) == '='){
                par[p1] = p2;
            }   
        }
        for(String str : equations){
            int p1 = findPar(str.charAt(0) - 'a');
            int p2 = findPar(str.charAt(3) - 'a');
            if(p1 == p2 && str.charAt(1) == '!'){
                return false;
            }   
        }
        return true;
    }

    // JOB SEQUENCING PROBLEM O(n2) APPROACH GREEDY
    // DO WITH UNIONFIND ALGO AS WELL
    public int[] JobScheduling(Job arr[], int n)
    {
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        Arrays.sort(arr,(a,b)->{
            return b.profit-a.profit;
        });
        int sum = 0;
        int count = 0;
        for(int i = 0;i<arr.length;i++)
        {
            for(int j = Math.min(arr[i].deadline-1,n-1);j>=0;j--)
            {
                if(ans[j] == -1)
                {
                    ans[j] = arr[i].profit;
                    count++;
                    sum+=ans[j];
                    break;
                }
            }
        }
        return new int[]{count,sum};
    }

    // JOBSEQUENCING UNIONFIND SOLUTION O(NLOGN);;

    // public int findPar(int u , int[]par)
    // {
    //     return par[u] = (par[u] == u) ? u : findPar(par[u],par);
    // }
    int[] JobScheduling(Job arr[], int n)
    {
        Arrays.sort(arr,(a,b)->{
            return b.deadline-a.deadline;
        });
        int maxDeadLine = arr[0].deadline;
        int[] par = new int[maxDeadLine+1];
        boolean[] ans = new boolean[maxDeadLine+1];
        Arrays.sort(arr,(a,b)->{
            return b.profit-a.profit;
        });

        for(int i = 0;i<=maxDeadLine;i++) par[i] = i;
        int sum = 0; 
        int count = 0;
        for(int i = 0;i<arr.length;i++)
        {
            int deadline = arr[i].deadline;
            int profit = arr[i].profit;
            
            int parCurr = findPar(deadline,par);
            
            if(!ans[parCurr])
            {
                sum+=profit;
                count++;
                ans[parCurr] = true;
                if(parCurr>1)
                {
                    int prevPar = findPar(parCurr-1,par);
                    par[parCurr] = prevPar;
                }
            }
        }
        return new int[]{count,sum};
    }

    // DR STRANGE NOT SUBMITTING ON GEEKS FOR GEEKS
    static int[] dis;
    static int[] low;
    static boolean[] vis;
    static int count = 0;
    static int atqpt = 0;
    static int noOfCallsFromRoot = 0;
    
    public static int doctorStrange (int n, int k, int g[][]) 
    {
        dis = new int[n+1];
        low = new int[n+1];
        vis = new boolean[n+1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1;i<graph.length;i++) graph[i] = new ArrayList<>();
        for(int i = 0;i<g.length;i++)
        {
            int u = g[i][0];
            int v = g[i][1];
            graph[u].add(v);
        }
        articulationPoint(1,-1,graph);
        return atqpt - ((noOfCallsFromRoot > 1) ? 1 : 0);
    }
    
    public static void articulationPoint(int src,int par,ArrayList<Integer>[] graph)
    {
        low[src] = dis[src] = ++count;
        vis[src] = true;
        for(int nbr : graph[src])
        {
            if(!vis[nbr])
            {
                if(par == -1) noOfCallsFromRoot++;
                articulationPoint(nbr,src,graph);
                if(dis[src] <= low[nbr]) atqpt++;
                low[src] = Math.min(low[src],low[nbr]);
            }
            else if(nbr!=par)
            {
                low[src] = Math.min(dis[nbr],low[src]);
            }
        }
    }

    // GEEKS FOR GEEKS POSSIBLE PATH (EULERIAN CIRCUIT)

    static int n = 0;
    static @SuppressWarnings("unchecked")
    static ArrayList<Integer>[] graph;
    
    public static int isPossible(int[][] paths)
    {
        graph = new ArrayList[paths.length];
        n = paths.length;
        for(int i = 0;i<n;i++) graph[i] = new ArrayList<>();
        for(int i = 0;i<paths.length;i++)
        {
            for(int j = 0;j<paths[0].length;j++)
            {
                if(paths[i][j] == 1)
                {
                    int u = i;
                    int v = j;
                    graph[u].add(v);
                }
            }
        }
        
        return findEuler_Path_Cycle();
    }
    
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
                node = i;	//Found a node to start DFS WHO SE DEGREE > 0
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

    public static int findEuler_Path_Cycle()
    {
        int ret = find_Euler();
        if(ret==0)
            return 0;
        else if(ret==1)
            return 0;
        else
            return 1;
    }
    
    // AS FAR FROM LAND AS POSSIBLE USING MULTIPOINT BFS SOLUTION
    public int maxDistance(int[][] grid) 
    {
        int n = grid.length;
        int m = grid[0].length;
        LinkedList<int[]> queue = new LinkedList<>();
        boolean containsZero = false;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1){
                    queue.addLast(new int[]{i,j});
                }
                else if(grid[i][j] == 0) containsZero = true;
            }
        }
        
        if(!containsZero) return -1;
        
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int level = 0;
        while(queue.size()!=0){
            int size = queue.size();
            while(size-- > 0){
                int[] temp = queue.removeFirst();
                for(int[] ele : dir){
                    int x = temp[0] + ele[0];
                    int y = temp[1] + ele[1];
                    if(x>=0&&y>=0&&x<n&&y<m){
                        if(grid[x][y] == 0){
                            queue.addLast(new int[]{x,y});
                            grid[x][y] = 1;
                        }
                    }
                }
            }
            level++;
        }
        return level-1;
    }

    // Leetcode 934. Shortest Bridge

    public void dfs(int[][]grid,int[][]dir,int i , int j , LinkedList<int[]>queue)
    {
        grid[i][j] = -1;
        queue.addLast(new int[]{i,j});
        for(int[] ele : dir)
        {
            int x = i + ele[0];
            int y = j + ele[1];
            if(x>=0 && y>=0 && x<grid.length && y<grid[0].length)
            {
                if(grid[x][y] == 1)
                {
                    dfs(grid,dir,x,y,queue);
                }
            }
        }
    }
    public int shortestBridge(int[][] grid) 
    {
        int n = grid.length;
        int m = grid[0].length;
        LinkedList<int[]> queue = new LinkedList<>();
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        boolean stop = false;
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                if(grid[i][j] == 1)
                {
                    dfs(grid,dir,i,j,queue);
                    stop = true;
                    break;
                }
            }
            if(stop) break;
        }
        int level = 0;
        int ans = (int)1e9;
        while(queue.size()!=0)
        {
            int size = queue.size();
            while(size-->0)
            {
                int[] temp = queue.removeFirst();
                
                for(int[] ele : dir)
                {
                    int x = ele[0] + temp[0];
                    int y = ele[1] + temp[1];
                    
                    if(x>=0&&y>=0&&x<n&&y<m){
                        if(grid[x][y] == 0)
                        {
                            queue.addLast(new int[]{x,y});
                            grid[x][y] = -1;
                        }
                        else if(grid[x][y] == 1) return level;
                    }
                }
            }
            level++;
        }
        return 0;
    }

    // 947. Most Stones Removed with Same Row or Column
    // basic union find problem 

    int[] unions;
    public int removeStones(int[][] stones) {
        int len = stones.length;
        unions = new int[len];
        for (int i = 0; i < len; i++){
            unions[i] = i;
        }
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    union(i, j);
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < len; i++){
            if (unions[i] == i) count++;
        }
        
        return len - count;
    }
    
    private void union(int x, int y){
        x = find(x);
        y = find(y);
        if (x == y) return;
        unions[y] = x;
    }
    private int find(int x){
        if (unions[x] == x) return x;
        return find(unions[x]);
    }

    // FLOYDS WARSHALL ALGORITHM
    public void shortest_distance(int[][] graph)
    {
        int V = graph.length;
        int dist[][] = new int[V][V];
        
        for(int i = 0;i<V;i++)
        {
            for(int j = 0;j<V;j++)
            {
                if(graph[i][j] == -1) graph[i][j] = (int)1e9;
            }
        }

        for(int i=0;i<V;++i)
            for(int j=0;j<V;++j)
                dist[i][j] = graph[i][j];
    
        for(int k=0;k<V;++k)
            for(int i=0;i<V;++i)
                for(int j=0;j<V;++j)
                {
                    if(dist[i][k]==(int)1e9 || dist[k][j]==(int)1e9)
                        continue;
                    else if(dist[i][k]+dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
    
    //  for(int i=0;i<V;++i)
    //      if(dist[i][i] < 0)
    //      {
    //          cout<<"Negative edge weight cycle is present\n";
    //          return;
    //      }
    
        
    //  for(int i=1;i<V;++i)
    //  {
    //      for(int j=0;j<V;++j)
    //          cout<<i<<" to "<<j<<" distance is "<<dist[i][j]<<"\n";
    //      cout<<"=================================\n";
    //  }
        for(int i = 0;i<V;i++)
        {
            for(int j = 0;j<V;j++)
            {
                if(dist[i][j] == (int)1e9) dist[i][j] = -1;
            }
        }
            
        for(int i = 0;i<V;i++)
        {
            for(int j = 0;j<V;j++)
            {
                graph[i][j] = dist[i][j];
            }
        }    
    }

    // 332. Reconstruct Itinerary
    // This question we have done using Heirholzer's Algorithm
    // This Algorithm is used to print the Eularian Path in O(E + V) time complexity

    public List<String> findItinerary(List<List<String>> tickets) 
    {
        HashMap<String,PriorityQueue<String>> graph = new HashMap<>();
        for(List<String> ele : tickets){
            String u = ele.get(0);
            String v = ele.get(1);
            if(!graph.containsKey(u)) graph.put(u,new PriorityQueue<>());
            if(!graph.containsKey(v)) graph.put(v,new PriorityQueue<>());
            graph.get(u).add(v);
        }

        LinkedList<String> stack = new LinkedList<>();
        List<String> ans = new ArrayList<>();
        dfsItinerary(graph,ans,stack,"JFK");
        while(stack.size()!=0){
            ans.add(stack.removeLast());
        }
        return ans;
    }
    
    public void dfsItinerary(HashMap<String,PriorityQueue<String>> graph,List<String> ans,LinkedList<String> stack , String src)
    {
        ans.add(src);
        while(graph.get(src).size()!=0){
            dfsItinerary(graph,ans,stack,graph.get(src).remove());
        }
        stack.addLast(ans.remove(ans.size()-1));
    }
    
    // COLORING A BORDER LEETCODE 1034
    // Use a DFS to find every square in the component. Then for each square,
    // color it if it has a neighbor that is outside the grid or a different color.

    int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    boolean[][] visited;
    
    private void dfs(int[][] grid, int r0, int c0, int origColor, int newColor, int n, int m) {       

        visited[r0][c0] = true;
        for(int[] d: dir) 
        {
            int x = r0 + d[0];
            int y = c0 + d[1];
            
            if(x>=n || x<0 || y>=m || y<0 || (!visited[x][y] && grid[x][y]!=origColor))
            {
                grid[r0][c0] = newColor;
                continue;
            }
            if(visited[x][y]) continue;
            
            dfs(grid, x, y, origColor, newColor, n, m);
        }
    }
    
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        if(grid[r0][c0] == color)
            return grid;
        int n = grid.length;
        int m = grid[0].length;
        visited = new boolean[n][m];
        dfs(grid, r0, c0, grid[r0][c0], color, n, m);
        return grid;
    }

    // FIND THE MAXIMUM FLOW GEEKS FOR GEEKS FORD FULKERSON ALGORITHM
    
    // NOTE THAT THE GRAPH WE NEED TO MAKE IS UNDIRECTED

    // Note: Multiple edges can exist between two nodes.
    // Eg: (1 2 3) (1 2 4).
    int solve(int N, int M, ArrayList<ArrayList<Integer>> Edges) 
    {
        int[][] graph = new int[N+1][N+1];
        for(ArrayList<Integer>ele : Edges)
        {
            int u = ele.get(0);
            int v = ele.get(1);
            int w = ele.get(2);
            graph[u][v] += w;
            graph[v][u] += w;
        }
        int max = findMaxFlow(1,N,graph);
        return max;
    }
    public int findMaxFlow(int source, int sink , int[][] graph) 
    {
        int[][] residualGraph = new int[graph.length][graph.length];

        for (int i = 0; i <graph.length ; i++) {
            for (int j = 0; j <graph.length ; j++) {
                residualGraph[i][j] = graph[i][j];
            }
        }
        int [] parent = new int[graph.length];
        int max_flow = 0;
        while(isPathExist_BFS(residualGraph, source, sink, parent)){
            int flow_capacity = (int)1e9;
            int t = sink;
            while(t!=source){
                int s = parent[t];
                flow_capacity = Math.min(flow_capacity, residualGraph[s][t]);
                t = s;
            }
            t = sink;
            while(t!=source){
                int s = parent[t];
                residualGraph[s][t]-=flow_capacity;
                residualGraph[t][s]+=flow_capacity;
                t = s;
            }

            max_flow+=flow_capacity;
        }
        return max_flow;
    }
    public boolean isPathExist_BFS(int [][] residualGraph, int src, int dest, int [] parent){
            boolean pathFound = false;
            boolean [] visited = new boolean[parent.length];
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(src);
            parent[src] = -1;
            visited[src] = true;
            while(queue.isEmpty()==false){
                int u = queue.removeFirst();
                for (int v = 0; v <parent.length ; v++) {

                    if(visited[v]==false && residualGraph[u][v]>0) {
                        queue.addLast(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }
            pathFound = visited[dest];
            return pathFound;
        }

    // NUMBER OF DISTINCT ISLANDS LEETCODE LOCKED SUBMITTED ON PEPCODING PORTAL
    
    public static int numDistinctIslands(int[][] arr) 
    {
        int n = arr.length;
        int m = arr[0].length;
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        HashMap<String,Integer> map = new HashMap<>();
        int count = 0;
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                if(arr[i][j] == 1)
                {
                    String[] ans = new String[]{"x"};
                    dfsIsland(arr, i, j, ans, dir);
                    ans[0] += "z";
                    map.put(ans[0],map.getOrDefault(ans[0],0)+1);
                }
            }
        }
        return map.size();
    }
    public static void dfsIsland(int[][] arr , int i , int j , String[] ans , int[][] dir)
    {
        arr[i][j] = 0;
        for(int[] ele : dir)
        {
            int x = i + ele[0];
            int y = j + ele[1];

            if(x >=0 && y >=0 && x < arr.length && y < arr[0].length && arr[x][y] == 1)
            {
                if(ele[0] == 0 && ele[1] == 1)
                {
                    ans[0] += "u";
                    dfsIsland(arr, x, y, ans, dir);
                    ans[0] += "z";
                }
                else if(ele[0] == 0 && ele[1] == -1)
                {
                    ans[0] += "d";
                    dfsIsland(arr, x, y, ans, dir);
                    ans[0] += "z";
                }
                else if(ele[0] == -1 && ele[1] == 0)
                {
                    ans[0] += "l";
                    dfsIsland(arr, x, y, ans, dir);
                    ans[0] += "z";
                }
                else if(ele[0] == 1 && ele[1] == 0)
                {
                    ans[0] += "r";
                    dfsIsland(arr, x, y, ans, dir);
                    ans[0] += "z";
                }
            }
        }
    }  

    // SENTENCE SIMILARITY 2 LEETCODE LOCKED SUBMITTED ON PEPCODING PORTAL

    public static String findParStr(String u , HashMap<String,String> map)
    {
        if(map.get(u) == u) return u;
        String temp = findParStr(map.get(u),map);
        map.put(u,temp);
        return temp;
    }
    public static boolean areSentencesSimilarTwo(String[] Sentence1, String[] Sentence2, String[][] pairs) 
    {
        HashMap<String,String> map = new HashMap<>();
        boolean res = true;
        for(String[] ele : pairs)
        {
            String u = ele[0];
            String v = ele[1];

            if(!map.containsKey(u)) map.put(u,u);
            if(!map.containsKey(v)) map.put(v,v);

            String par1 = findParStr(u,map);
            String par2 = findParStr(v,map);

            if(par1 != par2) map.put(par1,par2);
        }
        for(int i = 0;i<Sentence1.length;i++)
        {
            if(findParStr(Sentence1[i],map) != findParStr(Sentence2[i],map))
            {
                res = false;
                break;
            }
        }
        return res;
    }

    // Leetcode  Remove Max Number Of Edges To Keep Graph Fully Traversable

    // The idea here is to think that initially the graph is empty and now we want to add the edges into the graph such that graph is connected.
    // Union-Find is an easiest way to solve such problem where we start with all nodes in separate components and merge the nodes as we add edges into the graph.
    // As some edges are available to only Bob while some are available only to Alice, we will have two different union find objects to take care of their own traversability.
    // Key thing to remember is that we should prioritize type 3 edges over type 1 and 2 because they help both of them at the same time.
    // We are applying union find for both alice and bob seperately because we want that after removing the edges both of them should have same no of vertices in their graph

    public int findPar(int u , int[] par)
    {
        return par[u] = (par[u] == u) ? u : (findPar(par[u],par));
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) 
    {
        Arrays.sort(edges,(a,b)->(b[0]-a[0]));
        int removeCount = 0;
        int c1 = 0;
        int c2 = 0;

        int[]par1 = new int[n+1];
        int[]par2 = new int[n+1];

        for(int i = 0;i<=n;i++)
        {
            par1[i] = i;
            par2[i] = i;
        }

        for(int[] ele : edges)
        {
            int type = ele[0];
            int u = ele[1];
            int v = ele[2];

            if(type == 1)
            {
                int pu = findPar(u,par1);
                int pv = findPar(v,par1);

                if(pu !=pv) {
                    par1[pv] = pu;
                    c1++;
                }
                else removeCount++;
            }
            else if(type == 2)
            {
                int pu = findPar(u,par2);
                int pv = findPar(v,par2);

                if(pu!=pv) {
                    par2[pv] = pu;
                    c2++;
                }
                else removeCount++;
            }
            else
            {
                int pu1 = findPar(u,par1);
                int pv1 = findPar(v,par1);
                int pu2 = findPar(u,par2);
                int pv2 = findPar(v,par2);

                if(pu1!=pv1) {
                    par1[pv1] = pu1;
                    c1++;
                }
                if(pu2!=pv2) {
                    par2[pv2] = pu2;
                    c2++;
                }
                if(pu1 == pv1 && pu2 == pv2) removeCount++;
            }
        }
        return (c1 > 0 && c2 > 0 && c1 == n-1 && c2 == n-1) ? removeCount : -1;
    }

    // MINIMUM SWAPS REQUIRED TO SORT AN ARRAY GEEKS FOR GEEKS
    // VERY IMPORTANT
    // MAKE NOTES

    public int minSwaps(int nums[])
    {
        int count = 0;
        boolean[] vis = new boolean[nums.length];
        int[][] arr = new int[nums.length][];
        for(int i = 0;i<nums.length;i++) arr[i] = new int[]{nums[i],i};

        Arrays.sort(arr ,(a,b)->{
            return a[0] - b[0];
        });

        for(int i = 0;i<arr.length;i++)
        {
            int temp = arr[i][0];
            int next = arr[i][1];
            int tCount = 0;
            if(!vis[i])
            {
                vis[i] = true;
                int j = next;
                while(arr[j][0]!=temp)
                {
                    vis[j] = true;
                    j = arr[j][1];
                    tCount++;
                }
                count += tCount;
            }
        }
        return count;
    }

    // NOT MUCH IMPORTANT
    // // MAXIMUM BIPARTITE MATCHING GEEKS FOR GEEKS
    // // FOR DETAILED EXPLAINATION CHECK MAX BIPARTITE MATCHING FILE
    // public boolean dfs(int[]assigned, boolean[]seen, int candidate, int jobs, int applicants, int[][]G){

    //     for(int i=0; i<jobs;i++){
            
    //         if(G[i][candidate]==1  && seen[i]==false ){
    //             seen[i] = true;
    //             if(assigned[i]==-1 || dfs(assigned, seen, assigned[i], jobs, applicants, G)){
    //                 assigned[i] = candidate;
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    // }

    // public int maximumMatch(int[][] G)
    // {
    //     int jobs= G.length;
    //     int applicant= G[0].length;
    //     int[]assigned  = new int[jobs];
    //     Arrays.fill(assigned, -1);
    //     int count = 0;
    //     for(int i=0; i<applicant;i++)
    //     {
    //         boolean[]seen = new boolean[jobs];
    //         if(dfs(assigned, seen, i, jobs, applicant, G)) count++;
    //     }
    //     return count;
    // }

    // ALIEN DICTONARY
    // TOPOSORT USING KHANS ALGO
    // USING HASHSET TO REMOVE DUPLICACY
    public static String alienOrder(String[] words) {
        HashMap<Character, HashSet<Character>> map = new HashMap<Character, HashSet<Character>>();
        HashMap<Character, Integer> degree = new HashMap<Character, Integer>();
        String result = "";
        if (words == null || words.length == 0)
          return result;
        for (String s : words) {
          for (char c : s.toCharArray()) {
            degree.put(c, 0);
          }
        }
        for (int i = 0; i < words.length - 1; i++) {
          boolean flag = false;
          String cur = words[i];
          String next = words[i + 1];
          int length = Math.min(cur.length(), next.length());
          for (int j = 0; j < length; j++) {
            char c1 = cur.charAt(j);
            char c2 = next.charAt(j);
            if (c1 != c2) {
              HashSet<Character> set = new HashSet<Character>();
              if (map.containsKey(c1))
                set = map.get(c1);
              if (!set.contains(c2)) {
                set.add(c2);
                map.put(c1, set);
                degree.put(c2, degree.get(c2) + 1);
              }
              flag = true;
              break;
            }
          }
    
          if (flag == false && next.length() < cur.length()) {
            return "";
          }
        }
        LinkedList<Character> q = new LinkedList<Character>();
        for (char c : degree.keySet()) {
          if (degree.get(c) == 0)
            q.addLast(c);
        }
        while (!q.isEmpty()) {
          char c = q.removeFirst();
          result += c;
          if (map.containsKey(c)) {
            for (char c2 : map.get(c)) {
              degree.put(c2, degree.get(c2) - 1);
              if (degree.get(c2) == 0)
                q.addLast(c2);
            }
          }
        }
    
        if (result.length() != degree.size()) {
          return "";
        }
        return result;
      }

    // REDUNDANT CONNETCIONS 2

    public int[] parent;
    public int[] rank;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] indegree = new int[n + 1];
        Arrays.fill(indegree, -1);
        int bl1 = -1;
        int bl2 = -1;
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];

            if (indegree[v] == -1) {
                indegree[v] = i;
            } else {
                bl1 = i;
                bl2 = indegree[v];
            }
        }
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        for (int i = 0; i < edges.length; i++) {
            if (bl1 == i) {
                continue; // ignoring that edge which we have removed
            }
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];
            boolean flag = union(u, v); // return true only when parents are same
            if (flag == true) {
                if (bl1 == -1) {
                return edge;
                } else {
                return edges[bl2];
                }
            }
        }
        return edges[bl1];
    }

    public int find(int x) {
        if (parent[x] == x) {
          return x;
        }
        int temp = find(parent[x]);
        parent[x] = temp;
        return temp;
      }
    
      public boolean union(int x, int y) {
        int lx = find(x);
        int ly = find(y);
    
        if (lx == ly) {
          return true;
        }
    
        if (rank[lx] > rank[ly]) {
          parent[ly] = lx;
        } else if (rank[lx] < rank[ly]) {
          parent[lx] = ly;
        } else {
          parent[lx] = ly;
          rank[ly]++;
        }
    
        return false;
      }


    // 2115. Find All Possible Recipes from Given Supplies
    // Indegree Should Be calculated Afterwards Take Care of this
    // kahns algo solution 
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies)
    {
        HashSet<String> supp = new HashSet<>();
        for(String ele : supplies) supp.add(ele);
        
        HashSet<String> reci = new HashSet<>();
        for(String ele : recipes) reci.add(ele);
        
        ArrayList<String> ans = new ArrayList<>();
        HashMap<String , ArrayList<String>> map = new HashMap<>();
        HashMap<String,Integer> indegree = new HashMap<>();
        LinkedList<String> queue = new LinkedList<>();
        for(int i = 0;i<recipes.length;i++)
        {
            String rec = recipes[i];
            indegree.put(rec,0);
            List<String> ing = ingredients.get(i);
            for(String ele : ing)
            {
                indegree.put(ele,0);
                if(!map.containsKey(ele)) map.put(ele , new ArrayList<>());
                map.get(ele).add(rec);
            }
        }
        
        
        for(String ele : map.keySet())
        {
            ArrayList<String> neigh = map.get(ele);
            
            for(String e : neigh)
            {
                indegree.put(e , indegree.get(e) + 1);   
            }
        }
        
        for(String ele : indegree.keySet())
        {
            if(indegree.get(ele) == 0) queue.add(ele);
        }
        
        
        // System.out.println(indegree.toString());

        
        while(queue.size() > 0)
        {
            int size = queue.size();
            while(size-- > 0)
            {
                String ele = queue.removeFirst();
                ArrayList<String> neighbours = map.get(ele);
                
                if(neighbours!=null && supp.contains(ele))
                {
                    for(String e : neighbours)
                    {
                        indegree.put(e,indegree.get(e)-1);
                        if(indegree.get(e) == 0)
                        {
                            queue.addLast(e);
                            if(reci.contains(e)) ans.add(e);
                            supp.add(e);
                        }
                    }
                }
            }
        }

        return ans;
    }
}