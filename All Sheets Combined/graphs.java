Matrix Graphs
{
    // Water Connection Problem 
    public static class pair
    {
        int v;
        int w;
        pair(int v , int w)
        {
            this.v = v;
            this.w = w;
        }
    }
    ArrayList<ArrayList<Integer>> solve(int n, int p, ArrayList<Integer> a ,ArrayList<Integer> b ,ArrayList<Integer> d) 
    { 
        int[] in = new int[n+1];
        // int[] out = new int[n+1];
        pair[]graph = new pair[n+1];
        boolean[] vis = new boolean[n+1];
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i=0;i<p;i++)
        {
            // out[a.get(i)]++;
            in[b.get(i)]++;
            graph[a.get(i)] = new pair(b.get(i),d.get(i));
        }
        for(int i = 1;i<=n;i++)
        {
            int val = in[i];
            if(val == 0)
            {
                int[] min = new int[]{(int)1e9};
                dfs(graph,i,i,vis,ans,min);
            }
        }
        return ans;
    }
    
    public void dfs(pair[]graph , int start, int u , boolean[] vis , ArrayList<ArrayList<Integer>> ans , int[] min)
    {
        vis[u] = true;
        if(graph[u] == null && min[0] !=(int)1e9 )
        {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(start);
            temp.add(u);
            temp.add(min[0]);
            ans.add(temp);
            return;
        }
        if(graph[u]!=null && !vis[graph[u].v])
        {
            min[0] = Math.min(min[0],graph[u].w);
            dfs(graph,start,graph[u].v,vis,ans,min);
        }
        return;
    }


    // Rat in Maze

    public static ArrayList<String> findPath(int[][] m, int n)
    {
        int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        ArrayList<String> ans = new ArrayList<>();
        paths(m,0,0,dir,ans,"");
        return ans;
    }
    
    public static void paths(int[][]m , int i , int j , int[][] dir , ArrayList<String> ans , String a)
    {
        if(i < 0 || j < 0 || i >= m.length || j >= m[0].length) return ;
        if(m[i][j] == 0 || m[i][j] == -1) return;
        if(i == m.length-1 && j == m[0].length-1) 
        {
            ans.add(a);
            return;
        }
        m[i][j] = -1;
        for(int[] ele : dir)
        {
            int x = ele[0] + i;
            int y = ele[1] + j;
            
            
            if(ele[0] == 1 && ele[1] == 0) paths(m,x,y,dir,ans,a+"D");
            if(ele[0] == 0 && ele[1] == 1) paths(m,x,y,dir,ans,a+"R");
            if(ele[0] == -1 && ele[1] == 0) paths(m,x,y,dir,ans,a+"U");
            if(ele[0] == 0 && ele[1] == -1) paths(m,x,y,dir,ans,a+"L");
        }
        m[i][j] = 1;
    }


    // Steps by Knight 
    public int minStepToReachTarget(int kp[], int tp[], int N)
    {
        int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };
        
        boolean[][] vis = new boolean[N][N];
        
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(((kp[0]-1)*N)+kp[1]-1);
        vis[kp[0]-1][kp[1]-1] = true;
        int level = 0;

        while(queue.size() > 0)
        {
            int size = queue.size();
            while(size-- > 0)
            {
                int t = queue.removeFirst();
                int i = t/N;
                int j = t%N;
                // System.out.println(i + " + " + j);
                if(i == tp[0]-1 && j == tp[1]-1) return level;
                for(int k = 0 ;k<dx.length;k++)
                {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if(x<0||y<0||x>=N||y>=N) continue;
                    if(vis[x][y]) continue;
                    
                    vis[x][y] = true;
                    queue.addLast((x*N)+y);
                }
            }
            level++;
        }
        return -1;
    }


    // Prerequisite Tasks  

    public boolean isPossible(int N, int[][] prerequisites)
    {
        ArrayList<Integer>[] graph = new ArrayList[N];
        int[] indeg = new int[N];
        for(int i = 0;i<N;i++) graph[i] = new ArrayList<>();
        for(int[] ele : prerequisites)
        {
            int u = ele[0];
            int v = ele[1];
            indeg[u]++;
            graph[v].add(u);
        }
        
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0;i<N;i++){
            if(indeg[i] == 0) queue.add(i);
        }
        if(queue.size() == 0) return false;
        int n = 1;
        while(queue.size() > 0)
        {
            int u = queue.removeFirst();
            for(int ele : graph[u])
            {
                indeg[ele]--;
                if(indeg[ele] == 0) 
                {
                    queue.add(ele);
                    n++;
                }
            }
        }
        for(int ele : indeg) if(ele > 0) return false;
        return true;
    }


    // 200. Number of Islands

    public int findPar(int u , int[] par)
    {
        return par[u] = (par[u] == u) ? u : findPar(par[u],par);
    }
    public int numIslands(char[][] grid) 
    {
        int n = grid.length;
        int m = grid[0].length;
        int[]par = new int[n*m];
        for(int i = 0;i<(n*m);i++) par[i] = i;
        // Arrays.fill(par,-1);
        int[][] dir = new int[][]{{1,0},{0,-1}};
        int count = 0;
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                if(grid[i][j] == '1')
                { 
                    for(int[] d : dir)
                    {
                        int x = i + d[0];
                        int y = j + d[1];
                        if(x >=0 && y>=0 && x<n && y<m && grid[x][y] == '1')
                        {
                            int u = findPar(i*m+j,par);
                            int v = findPar(x*m+y,par);
                            if(u!=v)
                            {
                                par[v] = u;
                            }

                        }
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(par));
        for(int i = 0;i<(n*m);i++)
        {
            if(grid[i/m][i%m] == '1')
            {
                if(par[i] == i) count++;
            }
        }
        return count;
    }



    // Bridge edge in a graph 
    // No need to apply ATQPT algo 
    // Since c and d is given just using dfs make sure from c directly you don't to d and vice versa
    // and check if we make a call from c are we able to reach d

    static int isBridge(int V, ArrayList<ArrayList<Integer>> adj,int c,int d)
    {
        int[] vis = new int[V];
        int[] count =  new int[]{1};
        dfs(adj,c,c,d,vis);
        return (vis[d] == 1) ? 0 : 1;
    }
    
    public static void dfs(ArrayList<ArrayList<Integer>> adj , int u , int c , int d , int[] vis)
    {
        vis[u] = 1;
        for(int ele : adj.get(u))
        {
            if((u == c && ele == d) || (ele == c && u == d)) continue; 
            if(vis[ele] == 0) dfs(adj,ele,c,d,vis);
        }
    }


    // M-Coloring Problem
    // Important Problem
    // Important solution
    // Note that the graph is not connected
    public boolean graphColoring(boolean graph[][], int m, int n)
    {
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] g = new ArrayList[n];
        for(int i = 0;i<n;i++) g[i] = new ArrayList<>();
        for(int i = 0;i<graph.length;i++){
            for(int j = 0;j<graph.length;j++){
                if(graph[i][j]) g[i].add(j);
            }
        }
        boolean[] vis = new boolean[n];
        int[] color = new int[n];
        boolean res = true;
        for(int i = 0;i<n;i++)
        {
            if(!vis[i]) res = res && color(g,i,vis,color,m);
        }
        return res;
    }
    
    public boolean isValid(ArrayList<Integer>[]graph,int u,int[]color,int i)
    {
        for(int ele : graph[u])
        {
            if(color[ele] == i) return false;
        }
        return true;
    }
    
    public boolean color(ArrayList<Integer>[]graph,int u,boolean[]vis,int[]color,int m)
    {
        vis[u] = true;
        for(int i = 1;i<=m;i++)
        {
            if(isValid(graph,u,color,i))
            {
                color[u] = i;
                boolean r = true;
                for(int ele : graph[u])
                {
                    if(vis[ele]) continue;
                    
                    r = r && color(graph,ele,vis,color,m);
                    if(!r) break;
                }
                if(r) return r;
                color[u] = 0;
            }
        }
        vis[u] = false;
        return false;
    }


    // Paths to travel each nodes using each edge(Seven Bridges)
    // Eularian Path Problem


    // Number of Triangles in Directed and Undirected Graphs
    // Simple n^3 problem

    int V = 4;
    int countTriangle(int graph[][],boolean isDirected)
   {
       int count_Triangle = 0;
       for (int i = 0; i < V; i++)
       {
           for (int j = 0; j < V; j++)
           {
               for (int k=0; k<V; k++)
               {
                  if (graph[i][j] == 1 &&
                      graph[j][k] == 1 &&
                      graph[k][i] == 1)
                      count_Triangle++;
               }
           }
       }
       if(isDirected == true)
       {
           count_Triangle /= 3;
       }
       else
       {
           count_Triangle /= 6;
       }
       return count_Triangle;
   }


   // Two Clique Problem
   // Check If the graph is Bipartitr or not


   // Minimum cash flow
   // Just Calculate the setteled amount
   public static int minCashFlow(ArrayList<ArrayList<Integer>> money, int n)

   {

       int balance[] = new int[n];

       for(int i=0;i<n;i++)

       {

           for(int j=0;j<n;j++)

           {

               balance[j]+=money.get(i).get(j);

               balance[i]-=money.get(i).get(j);

           }

       }

       int trans = 0;

       for(int i=0;i<n;i++)

       {

           if(balance[i]>0)

               trans += balance[i];

       }
       return trans;
   }


   

}