import java.util.ArrayList;
import java.util.List;
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
    // Iterate over the matrix from top to bottom-left to right
    // do the back iteration from bottom to top-right to left

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

    public static void main(String args[] ) throws Exception 
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
}