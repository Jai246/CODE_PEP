import java.util.*;

class treesLeftQues
{
    public class TreeNode 
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 1302. Deepest Leaves Sum

    public int height(TreeNode root)
    {
        if(root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left,right)+1;
    }
    public int deepestLeavesSum(TreeNode root) 
    {
        int height = height(root);
        int[] sum = new int[]{0};
        findSum(root,height,sum);
        return sum[0];
    }
    
    public void findSum(TreeNode root , int level , int[]sum)
    {
        if(root == null) return;
        if(level == 1) sum[0]+=root.val;
        findSum(root.left,level-1,sum);
        findSum(root.right,level-1,sum);
    }


    // 1315. Sum of Nodes with Even-Valued Grandparent

    public int sumEvenGrandparent(TreeNode root) 
    {
        int[] sum = new int[]{0};
        findSum(root,-1,-1,sum);
        return sum[0];
    }
    public void findSum(TreeNode root , int par , int gPar , int[] sum)
    {
        if(root == null) return;
        int val = root.val;
        if(gPar%2 == 0) sum[0]+=val;
        gPar = par;
        par = val;
        findSum(root.left,par,gPar,sum);
        findSum(root.right,par,gPar,sum);
    }


    // 654 Maximum Binary Tree
    // This is also called a Cartesian Tree. 
    // One interesting property is that if we do an in-order traversal, we get the array back which we used to create it.
    // O(n^2) 
    public TreeNode constructMaximumBinaryTree(int[] nums) 
    {
        return construct(nums, 0, nums.length);
    }
    
    public TreeNode construct(int[] nums, int l, int r) 
    {
        if (l == r) return null;
        int max_i = max(nums, l, r);
        TreeNode root = new TreeNode(nums[max_i]);
        root.left = construct(nums, l, max_i);
        root.right = construct(nums, max_i + 1, r);
        return root;
    }
    
    public int max(int[] nums, int l, int r) 
    {
        int max_i = l;
        for (int i = l; i < r; i++) 
        {
            if (nums[max_i] < nums[i]) max_i = i;
        }
        return max_i;
    }


    // 1305. All Elements in Two Binary Search Trees

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) 
    {
        List<Integer> one = new ArrayList<>();
        inorder(root1,one);
        List<Integer> two = new ArrayList<>();
        inorder(root2,two);
        
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < one.size() && j < two.size())
        {
            int v1 = one.get(i);
            int v2 = two.get(j);
            if(v1 == v2)
            {
                ans.add(v1);
                ans.add(v1);
                i++;
                j++;
            }
            else if(v1 < v2)
            {
                ans.add(v1);
                i++;
            }
            else 
            {
                ans.add(v2);
                j++;
            }
        }
        
        while(i < one.size()) ans.add(one.get(i++));
        while(j < two.size()) ans.add(two.get(j++));
        return ans;
    }
    
    public void inorder(TreeNode r,List<Integer>ans)
    {
        if(r == null) return ;
        inorder(r.left,ans);
        ans.add(r.val);
        inorder(r.right,ans);
    }

    // Simple O(N) approach Using Stack
    // Very Important Approach
    // Important Problem
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2)
    {
        Stack<TreeNode> left = new Stack<>();
        Stack<TreeNode> right = new Stack<>();
        
        List<Integer> ans = new ArrayList<>();
        
        TreeNode l = root1;
        TreeNode r = root2;
        
        while(l!=null || r!=null || left.size() > 0 || right.size() > 0)
        {
            while(l!=null)
            {
                left.add(l);
                l = l.left;
            }
            while(r!=null)
            {
                right.add(r);
                r = r.left;
            }
            
            if((right.size()==0)||(left.size() > 0 && left.peek().val <= right.peek().val))
            {
                TreeNode pop = left.pop();
                ans.add(pop.val);
                l = pop;
                l = l.right;
            }
            else if((left.size()==0)||(right.size() > 0 && right.peek().val < left.peek().val))
            {
                TreeNode pop = right.pop();
                ans.add(pop.val);
                r = pop;
                r = r.right;
            }
            
        }
        return ans;
    }

    // 1382. Balance a Binary Search Tree
    // Simple Solution No Need to do it like we do it as we do to create AVL trees
    // Note that we are storing Node itself in the list to avoide new initialization
    List<TreeNode> sortedArr = new ArrayList<>();
    
    public TreeNode balanceBST(TreeNode root) 
    {
        inorderTraverse(root);
        return sortedArrayToBST(0, sortedArr.size() - 1);
    }
    
    public void inorderTraverse(TreeNode root) 
    {
        if (root == null) return;
        inorderTraverse(root.left);
        sortedArr.add(root);
        inorderTraverse(root.right);
    }
    
    public TreeNode sortedArrayToBST(int start, int end) 
    {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = sortedArr.get(mid);
        root.left = sortedArrayToBST(start, mid - 1);
        root.right = sortedArrayToBST(mid + 1, end);
        return root;
    }


    // 1261. Find Elements in a Contaminated Binary Tree

    public TreeNode Root = null;

    public FindElements(TreeNode root) 
    {
        Root = root;
    }
    
    public boolean find(int target) 
    {
        if(target == 0) return true;
        return traverse(Root.left,target,0,0) || traverse(Root.right,target,1,0);
    }
    
    public boolean traverse(TreeNode root , int tar , int pos , int parVal)
    {
        if(root == null) return false;
        int val = -1;
        if(pos == 0) val = 2*parVal + 1;
        else if(pos == 1) val = 2*parVal + 2;
        if(val == tar) return true;
        return traverse(root.left,tar,0,val) || traverse(root.right,tar,1,val);
    }


    // 701. Insert into a Binary Search Tree

    public TreeNode insertIntoBST(TreeNode root, int val) 
    {
        return insert(root,val);
    }
    
    public TreeNode insert(TreeNode root , int val)
    {
        if(root == null) return new TreeNode(val);
        if(val > root.val) root.right =  insert(root.right,val);
        else root.left =  insert(root.left,val);
        return root;
    }


    // 814. Binary Tree Pruning

    public TreeNode pruneTree(TreeNode root) 
    {
        prune(root);
        if(root.left == null && root.right == null && root.val == 0) return null;
        return root;
    }
    
    public boolean prune(TreeNode root)
    {
        if(root == null) return false;
        boolean left = prune(root.left);
        boolean right = prune(root.right);
        
        if(!left) root.left = null;
        if(!right) root.right = null;
        
        if(root.val == 1 || left || right) return true;
        return false;
    }


    // 1123. Lowest Common Ancestor of Deepest Leaves
    // 865. Smallest Subtree with all the Deepest Nodes
    // Both Exactly Same Questions
    // Important Question But Very Easy
    public TreeNode lcaDeepestLeaves(TreeNode root) 
    {
        return helper(root, depth(root));
    }
    private int depth(TreeNode n) 
    {
        return n == null ? 0 : 1 + Math.max(depth(n.left), depth(n.right));
    }
    private TreeNode helper(TreeNode n, int d) 
    {
        if (n == null) return null;
        if (d == 1) return n;
        TreeNode L = helper(n.left, d - 1);
        TreeNode R = helper(n.right, d - 1);
        if (L != null && R != null) return n; // If Left And Right child both have Deepest Nodes
        return L == null ? R : L; // If Only one Child have Deepest Nodes
    }


    // 2196. Create Binary Tree From Descriptions

    public TreeNode createBinaryTree(int[][] des) 
    {
        HashMap<Integer, TreeNode> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        TreeNode root = null;
        for(int[] ele : des)
        {
            int p = ele[0];
            int c = ele[1];
            int pos = ele[2];
            set.add(p);
            
            if(!map.containsKey(p)) map.put(p,new TreeNode(p));
            if(!map.containsKey(c)) map.put(c,new TreeNode(c));
            
            TreeNode par = map.get(p);
            TreeNode ch = map.get(c);
            
            if(pos == 1) par.left = ch;        
            if(pos == 0) par.right = ch;
        }
        for(int[] ele : des) if(set.contains(ele[1])) set.remove(ele[1]);
        for(int ele : set) root = map.get(ele); 
        return root;
    }


    // 1457. Pseudo-Palindromic Paths in a Binary Tree

    public int pseudoPalindromicPaths (TreeNode root) 
    {
        int[]c = new int[10];
        int[] ans = new int[]{0};
        count(root,c,ans);
        return ans[0];
    }
    
    public void count(TreeNode root , int[]c , int[]ans)
    {
        if(root == null) return;
        if(root.left == null && root.right == null)
        {
            c[root.val]++;
            int modOneCount = 0;
            for(int ele : c)
            {
                if(ele%2 == 0) continue;
                if(ele%2 == 1) modOneCount++;
                else return;
            }
            if(modOneCount == 1 || modOneCount == 0) ans[0]++;
            c[root.val]--;
        }
        c[root.val]++;
        count(root.left,c,ans);
        count(root.right,c,ans);
        c[root.val]--;
    }

    // 429. N-ary Tree Level Order Traversal
    // Very Simple Solution  , Normal BFS

    public List<List<Integer>> levelOrder(Node root) 
    {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++){
                temp.add(queue.peek().val);
                queue.addAll(queue.poll().children);
            }
            res.add(temp);
        }
        return res;
    }

    // 951. Flip Equivalent Binary Trees
    // Also Called Tree Isomorphism Problem on GFG
    // Important Calls
    public boolean flipEquiv(TreeNode root1, TreeNode root2) 
    {
        return check(root1,root2);
    }
    
    public boolean check(TreeNode r1 , TreeNode r2)
    {
        if(r1 == null && r2 == null) return true;
        else if(r1 == null || r2 == null) return false;
        if(r1.val != r2.val) return false;
        boolean res1 = false;
        res1 = (check(r1.left,r2.left) && check(r1.right,r2.right)) || (check(r1.right,r2.left) && check(r1.left,r2.right));
        return res1;
    }   




    // 1161. Maximum Level Sum of a Binary Tree

    public int maxLevelSum(TreeNode root) 
    {
        int max = -(int)1e9;
        int maxLevel = -1;
        int level = 1;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(queue.size() > 0)
        {
            int size = queue.size();
            int sum = 0;
            while(size-- > 0)
            {
                TreeNode node = queue.removeFirst();
                sum+=node.val;
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            if(sum > max)
            {
                max = sum;
                maxLevel = level;
            }
            level++;
        }
        return maxLevel;
    }


    // 513. Find Bottom Left Tree Value
    // We Can Do this Using BFS as well
    public int findBottomLeftValue(TreeNode root) 
    {
        int level = 0;
        int[] val = new int[]{0};
        int[] lev = new int[]{(int)1e9};
        findLeftMost(root,level,val,lev);
        return val[0];
    }
    
    public void findLeftMost(TreeNode root , int level , int[]val , int[]lev)
    {
        if(root == null) return;
        findLeftMost(root.left,level-1,val,lev);
        if(root.left == null && root.right == null)
        {
            if(level < lev[0])
            {
                lev[0] = level;
                val[0] = root.val;
            }
        }
        findLeftMost(root.right,level-1,val,lev);
    }


    // 669. Trim a Binary Search Tree
    // Important Question
    // Agar left and right child dono include hei toh parent bhi hoga
    // Agar sirf left yaa fir right child include honge toh parent par check lagana padega kyoki 
    // zaroori nahi hei ki parent hamesha include hoo

    // Simple Solution

    public TreeNode trimBST(TreeNode root, int low, int high) 
    {
        return getTrimmed(root,low,high);
    }
    
    public TreeNode getTrimmed(TreeNode root , int low , int high)
    {
        if(root == null) return null;
        root.left = getTrimmed(root.left,low,high);
        root.right = getTrimmed(root.right,low,high);
        int val = root.val;
        
        if(root.val < low) return root.right;
        else if(root.val > high) return root.left;
        return root;
    }

    // hahahahahahahahahahahahahahaha

    public TreeNode trimBST(TreeNode root, int low, int high) 
    {
        return trim(root,low,high);
    }
    
    public TreeNode trim(TreeNode root , int lo , int hi)
    {
        if(root == null) return null;
        
        TreeNode left = null;
        TreeNode right = null;
        
        int val = root.val;
        
        if(val > lo) left = trim(root.left,lo,hi);
        
        if(val < hi) right = trim(root.right,lo,hi);
        
        if(left !=null && right != null)
        {
            root.left = left;
            root.right = right;
            return root;
        }
        else if(left == null && right !=null)
        {
            root.left = null;
            root.right = right;
            if(val >= lo && val <= hi) return root;
            else return right;
        }
        else if(right == null && left!=null)
        {
            root.right = null;
            root.left = left;
            if(val >= lo && val <= hi) return root;
            else return left;
        }
        else if(left == null && right == null)
        {
            root.left = null;
            root.right = null;
            if(val >= lo && val <= hi) return root;
            else return null;
        }
        return root;
    }



    // 515. Find Largest Value in Each Tree Row

    public List<Integer> largestValues(TreeNode root) 
    {
        if(root == null) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(queue.size() > 0)
        {
            int size = queue.size();
            long max = -(long)1e14;
            while(size-- > 0)
            {
                TreeNode node = queue.removeFirst();
                max = Math.max(max,node.val);
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            ans.add((int)max);
        }
        return ans;
    } 


    // 1530. Number of Good Leaf Nodes Pairs

    public int countPairs(TreeNode root, int distance) 
    {
        int[] sum=new int[1];
        countPairs(root,distance,sum);
        return sum[0];
    }
    
    public List<Integer> countPairs(TreeNode root, int distance,int[] sum) 
    {
        if(root==null)return new ArrayList<>();
        if(root.left==null&&root.right==null)
        {
            List list=new ArrayList<>();
            list.add(1);
            return list;
        }
        List<Integer> leftList=countPairs(root.left,distance,sum);
        List<Integer> rightList=countPairs(root.right,distance,sum);
        List<Integer> ret=new ArrayList<>();
        if(!leftList.isEmpty()&&!rightList.isEmpty())
        {
            for(int i=0;i<leftList.size();i++)
            {
                for(int j=0;j<rightList.size();j++)
                {
                    if(leftList.get(i)+rightList.get(j)<=distance)
                    {
                        sum[0]++;
                    }
                }
            }
        }
        for(int i=0;i<leftList.size();i++)
        {
            ret.add(leftList.get(i)+1);
        }
        for(int i=0;i<rightList.size();i++)
        {
            ret.add(rightList.get(i)+1);
        }
        return ret;
    }


    // 107. Binary Tree Level Order Traversal II

    public List<List<Integer>> levelOrderBottom(TreeNode root) 
    {
        if(root == null) return new LinkedList<>();
        List<List<Integer>> ans = new LinkedList<>();
        List<List<Integer>> fAns = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(queue.size() > 0)
        {
            int size = queue.size();
            List<Integer> temp = new LinkedList<>();
            while(size-- > 0)
            {
                TreeNode rem = queue.removeFirst();
                temp.add(rem.val);
                if(rem.left!=null) queue.add(rem.left);
                if(rem.right!=null) queue.add(rem.right);
            }
            ans.add(temp);
        }
        while(ans.size() > 0) fAns.add(ans.remove(ans.size()-1));
        return fAns;
    }


    // 1376. Time Needed to Inform All Employees
    // Simple Maximum Path Sum Problem
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) 
    {
        ArrayList<Integer>[]graph = new ArrayList[n];
        for(int i=0;i<n;i++)
        {
            if(manager[i] == -1) continue;
            if(graph[manager[i]] == null) graph[manager[i]] = new ArrayList<>();
            graph[manager[i]].add(i);
        }
        int[] ans = new int[]{-(int)1e9};
        dfs(graph,headID,ans,0,informTime);
        return ans[0];
    }
    
    public void dfs(ArrayList<Integer>[]graph , int par , int[]ans , int sum , int[]informTime)
    {
        if(graph[par] == null){
            ans[0] = Math.max(ans[0],sum);
            return;
        }
        
        for(int ele : graph[par])
        {
            dfs(graph,ele,ans,sum + informTime[par],informTime);
        }
    }

    // Most Optimized solution
    // Kind Of DP
    // Moving From bottom to root
    // Updating the inform time from node to root and using it further when required
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) 
    {
        int res = 0;
        for (int i = 0; i < n; ++i)
        {
            res = Math.max(res, dfs(i, manager, informTime));
        }
        return res;
    }
    
    public int dfs(int i, int[] manager, int[] informTime) 
    {
        if (manager[i] != -1) 
        {
            informTime[i] += dfs(manager[i], manager, informTime);
            manager[i] = -1;
        }
        return informTime[i];
    }

    // 652. Find Duplicate Subtrees
    // Very Important Problem
    // Do Remember This Approach

    HashMap<String, Integer> map= new HashMap<>();
    ArrayList<TreeNode> res= new ArrayList<>(); 

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) 
    {
        Mapper(root);
        return res;
    }

    public String Mapper(TreeNode root)
    {
        if(root == null) return "N";

        String left= Mapper(root.left);
        String right= Mapper(root.right);

        String curr= root.val +" "+left +" "+ right;

        map.put(curr, map.getOrDefault(curr, 0)+ 1);

        if(map.get(curr) == 2) res.add(root);

        return curr;
    }

    // 1443. Minimum Time to Collect All Apples in a Tree

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) 
    {
        ArrayList<Integer>[]tree = new ArrayList[n];
        for(int[] ele : edges)
        {
            int u = ele[0];
            int v = ele[1];
            if(tree[u] == null) tree[u] = new ArrayList<>();
            if(tree[v] == null) tree[v] = new ArrayList<>();
            tree[u].add(v);
            tree[v].add(u);
        }
        boolean[] vis = new boolean[n];
        int res = find(tree,0,hasApple,vis);
        if(res > 0) return res-2;
        return 0;
    }
    
    public int find(ArrayList<Integer>[]tree , int par , List<Boolean> hasApple , boolean[]vis)
    {
        vis[par] = true;
        if(tree[par].size() == 0)
        {
            if(hasApple.get(par)) return 2;
            return 0;
        }
        
        int res = 0;
        
        for(int ele : tree[par])
        {
            if(vis[ele]) continue;
            res+=find(tree,ele,hasApple,vis);
        }
        vis[par] = false;
        if(res == 0 && !hasApple.get(par)) return 0;
        return res + 2;
    }


    // 958. Check Completeness of a Binary Tree

        public boolean isCompleteTree(TreeNode root) 
    {
        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<Integer> q2 = new LinkedList<>();
        
        q1.add(root);
        q2.add(1);
        
        int prevVal = 0;
        
        while(q1.size() > 0)
        {
            int size = q1.size();
            while(size-- > 0)
            {
                TreeNode temp = q1.removeFirst();
                int val = q2.removeFirst();
                
                if(val != prevVal + 1) return false;
                prevVal = val;
                if(temp.left!=null)
                {
                    q1.add(temp.left);
                    q2.add(val*2);
                }
                
                if(temp.right!=null)
                {
                    q1.add(temp.right);
                    q2.add(val*2 + 1);
                }
            }
        }
        return true;
    }

    // Another Idea is just to make sure that there should not present any node after any null node

    public boolean isCompleteTree(TreeNode root) 
    {
        LinkedList<TreeNode> q1 = new LinkedList<>();
        q1.add(root);
        Boolean check = false;
        while(q1.size() > 0)
        {
            TreeNode temp = q1.removeFirst();
            if(temp == null) check = true;
            if(check && temp!=null) return false;
            if(temp!=null)
            {
                q1.add(temp.left);
                q1.add(temp.right);
            }
        }
        return true;
    }

    // Burning Tree
    // This can be done without calulating root to node path
    
    public static void getLongest(Node root , Node block , int count , int[]max)
    {
        if(root == null)
        {
            max[0] = Math.max(max[0],count-1);
            return;
        }
        if(root == block) return;
        
        getLongest(root.left,block,count+1,max);
        getLongest(root.right,block,count+1,max);
    }
    public static ArrayList<Node> rtnp(Node root , int tar)
    {
        if(root == null) return null;
        if(root.data == tar)
        {
            ArrayList<Node> list = new ArrayList<>();
            list.add(root);
            return list;
        }
        ArrayList<Node> left = rtnp(root.left,tar);
        if(left!=null)
        {
            left.add(root);
            return left;
        }
        ArrayList<Node> right = rtnp(root.right,tar);
        if(right!=null)
        {
            right.add(root);
            return right;
        }
        return null;
    }
    public static int minTime(Node root, int target) 
    {
        ArrayList<Node> list = rtnp(root,target);
        Node block = null;
        int maxTime = 0;
        
        int ctr = 0;
        
        for(Node ele : list)
        {
            int[] m = new int[]{0};
            getLongest(ele,block,0,m);
            block = ele;
            maxTime = Math.max(maxTime,m[0]+ctr++);
        }
        return maxTime;
    }

    // 971. Flip Binary Tree To Match Preorder Traversal
    // Important Question
    // Good and Simple Solution
    List<Integer> res = new ArrayList<>();
    int i = 0;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] v) 
    {
        return dfs(root, v) ? res : Arrays.asList(-1);
    }

    public Boolean dfs(TreeNode node, int[] v) 
    {
        if (node == null) return true;
        if (node.val != v[i++]) return false;
        if (node.left != null && node.left.val != v[i]) 
        {
            res.add(node.val);
            return dfs(node.right, v) && dfs(node.left, v);
        }
        return dfs(node.left, v) && dfs(node.right, v);
    }
}