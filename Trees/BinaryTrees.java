import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
class BinaryTrees
{
    public class TreeNode
    {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode(int data){

        }
        TreeNode(int data , TreeNode left , TreeNode right)
        {
            this.val = data;
            this.left = left;
            this.right = right;
        }
    }
    
    // public static int size(node root)
    // {
    //     int count = 0;
    //     if(root.left != null) count += size(root.left);
    //     if(root.right != null) count += size(root.right);
    //     return count+1;
    // }

    public static int size(TreeNode root)
    {
        return (root == null) ? 0 : size(root.left) + size(root.right);
    }

    public static int height(TreeNode root)
    {
        return (root == null) ? 0 : Math.max(height(root.left),height(root.right)) + 1; 
    }
    
    public int maximum(TreeNode root)
    {
        return (root == null) ? (-(int)1e9) :  Math.max(root.val , Math.max(maximum(root.left) , maximum(root.right)));
    }

    public boolean find(TreeNode node , int data)
    {
        if(node == null) return false;
        if(node.val == data) return true;
        return find(node.left,data) || find(node.right,data);
    }

    public static boolean rootToNodePath(TreeNode root , int data , ArrayList<Integer> list)
    {
        if(root == null) return false;
        if(root.val == data)
        {
            list.add(data);
            return true;
        }
        boolean res = rootToNodePath(root.left,data,list) || rootToNodePath(root.right,data,list);
        if(res) list.add(root.val);
        return res; 
    }

    public static ArrayList<Integer> rootToNodePath2(TreeNode root , int data)
    {
        if(root == null) return new ArrayList<>();
        if(root.val == data)
        {
            ArrayList<Integer> ans = new ArrayList<>();;
            ans.add(data);
        }
        ArrayList<Integer> left = rootToNodePath2(root.left,data);
        if(left.size() > 0){
            left.add(root.val);
            return left;
        }
        ArrayList<Integer> right = rootToNodePath2(root.right,data);
        if(right.size() > 0){
            right.add(root.val);
            return right;
        }
        return new ArrayList<>();
    }

    // 236. Lowest Common Ancestor of a Binary Tree
    public static ArrayList<TreeNode> rootToNodePath2(TreeNode root , TreeNode p)
    {
        if(root == null) return new ArrayList<>();
        if(root == p)
        {
            ArrayList<TreeNode> ans = new ArrayList<>();;
            ans.add(p);
            return ans;
        }
        ArrayList<TreeNode> left = rootToNodePath2(root.left,p);
        if(left.size() > 0){
            left.add(root);
            return left;
        }
        ArrayList<TreeNode> right = rootToNodePath2(root.right,p);
        if(right.size() > 0){
            right.add(root);
            return right;
        }
        return new ArrayList<>();
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) 
    {
        ArrayList<TreeNode> list1 =  rootToNodePath2(root , p);
        ArrayList<TreeNode> list2 =  rootToNodePath2(root , q);
        int i = list1.size()-1;
        int j = list2.size()-1;
        
        while(i >= 0 && j >=0)
        {
            if(i >= 0 && j>=0 && list1.get(i).val == list2.get(j).val)
            {
                i--;
                j--;
            }
            else break;
        }
        return list2.get(j+1);
    }

    public static void KDown(TreeNode node , TreeNode block , int depth , ArrayList<Integer> list)
    {
        if(node == null || depth<0||node == block) return;
        if(depth == 0)
        {
            list.add(node.val);
            return;
        }
        KDown(node.left,block,depth-1,list);
        KDown(node.right,block,depth-1,list);
    }
    // 2n Space
    public List<Integer> distanceK(TreeNode root , TreeNode target , int k)
    {
        ArrayList<TreeNode> list = rootToNodePath2(root,target);

        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode block = null;

        for(int i = 0;i<list.size();i++)
        {
            KDown(list.get(i),block,k-i,ans);
            block = list.get(i);
        }
        Collections.sort(ans);
        return ans;
    }
    // Reduced Space
    public static int kDistance2(TreeNode node , TreeNode target , int k , ArrayList<Integer> list)
    {
        if(node == null) return -1;
        if(node == target)
        {
            KDown(node,null,k,list);
            return 1;
        }
        int lans = kDistance2(node.left,target,k,list);
        if(lans != -1)
        {
            KDown(node,node.left,k-lans,list);
            return lans + 1;
        }
        int rans = kDistance2(node.right,target,k,list);
        if(rans!=-1)
        {
            KDown(node,node.right,k-rans,list);
            return rans+1;
        }
        return -1;
    }
    // Diameter of A Binary Tree Optimized Approach
    public static int callDiameter(TreeNode node)
    {
        int[] res = new int[1];
        diameter(node, res);
        return res[0] + 1; // In terms of No of Nodes 
    }
    public static int diameter(TreeNode node , int[]res)
    {
        if(node == null) return -1;
        int lh = diameter(node.left,res);
        int rh = diameter(node.right,res);
        res[0] = lh+rh+2;
        return Math.max(lh,rh) + 1;
    }

    // Leetcode 112 PathSum   Root to Leaf Path
    public boolean hasPathSum(TreeNode root, int targetSum) 
    {
       if (root == null) return false;
       if (root.left == null && root.right == null) return (targetSum - root.val == 0);
       return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    //Leetcode 113 PathSum 2
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) 
    {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        pathSumCalc(root,targetSum,smallAns,ans);
        return ans;
    }
    public static void pathSumCalc(TreeNode root, int targetSum , List<Integer> smallAns , List<List<Integer>> ans) 
    {
        if(root == null) return;
        if(root.left == null && root.right == null && targetSum - root.val == 0)
        {
            smallAns.add(root.val);
            ans.add(smallAns);
            List<Integer> temp = new ArrayList<>(smallAns);
            ans.add(temp);
            return;
        }
        smallAns.add(root.val);
        pathSumCalc(root.left,targetSum-root.val,smallAns,ans);
        pathSumCalc(root.right,targetSum-root.val,smallAns,ans);
        smallAns.remove(smallAns.size()-1);
        return;
    }

    // leetcode 437 Path Sum III
    // Important TestCases [0,1,1] and Target = 1 Output is 4 
    public int pathSum3(TreeNode root, int sum) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int[] ans = new int[1];
        pathSum_(root,sum,0,map,ans);
        return ans[0];
    }
    public void pathSum_(TreeNode root,int sum,int pfs,HashMap<Integer,Integer> map,int[] count) 
    {
        if(root == null) return;
        pfs+=root.val;
        if(map.containsKey(pfs-sum))
        {
            count[0] += map.get(pfs-sum);
        }
        map.put(pfs,map.getOrDefault(pfs,0)+1);
        
        pathSum_(root.left,sum,pfs,map,count);
        pathSum_(root.right,sum,pfs,map,count);
        
        map.put(pfs,map.get(pfs)-1);
        
        if(map.get(pfs) == 0) map.remove(pfs);
    }
    // MAX Path sum from AnyNode to AnyNode
    // leetcode 124
    public static int maxPathSum(TreeNode root) 
    {
        int[] res = new int[1];
        res[0] = -(int)1e9;
        maxPathSum(root,res);
        return res[0];
    }
    public static int maxPathSum(TreeNode root , int[]max)
    {
        if(root == null) return -(int)1e9;
        int lh = maxPathSum(root.left,max);
        int rh = maxPathSum(root.right,max);
        max[0] = Math.max(max[0],Math.max(root.val+lh+rh,Math.max(root.val+(Math.max(lh,rh)),Math.max(root.val,Math.max(lh,rh)))));
        int temp = Math.max(root.val+Math.max(lh,rh),root.val);
        return temp;
    }

    // Leetcode 98 Validate BST
    public static class minMax
    {
        long min = (long)1e11;
        long max = -(long)1e11;
        minMax(){
        }
        minMax(long min , long max){
            this.min = min;
            this.max = max;
        }
    }
    public static boolean isValidBST(TreeNode root)
    {
        minMax ans = isValidBST_(root);
        if(ans.min != -(long)1e11 && ans.max!= (long)1e11) return true;
        return false;
    }
    public static minMax isValidBST_(TreeNode root) 
    {
        if(root == null) return new minMax();
        if(root.left == null && root.right == null) return new minMax(root.val,root.val);
        minMax lh = isValidBST_(root.left);
        minMax rh = isValidBST_(root.right);
        if(root.val > lh.max && root.val < rh.min)  return new minMax(Math.min(lh.min,Math.max(rh.min,root.val)) ,Math.max(lh.max,Math.max(rh.max,root.val)));
        return new minMax(-(long)1e11 , (long)1e11);
    }

    // Another Methode Is by Doing In Order Traversal Coz it gives the Sorted Order Of A BST
    public static int prev = -(int)1e9;
    public static boolean isValidBST2(TreeNode root)
    {
        if(root == null) return true;
        if(!isValidBST2(root.left)) return false;
        if(root.val <= prev) return false;
        prev = root.val;
        if(!isValidBST2(root.right)) return false;
        return true;
    }

    // BINARY TREES CAMERAS
    // 0 -> CAMERA NAHI HEI ZAROORAT BHI NAHI HEI
    // 1 -> CAMERA HEI ZAROORAT NAHI HEI
    // -1 -> CAMERA NAHI HEI ZAROORAT HEI
    public int minCameraCover(TreeNode root) 
    {
        int [] count = new int[1];
        int ans  = minCameraCover_(root,count);
        return ans == -1 ? count[0] + 1 : count[0]; // IMPORTANT FOR ANY SWEWED TREE 
        // OF EVEN SIZE SINCE IT WILL RETURN -1 BUT STILL WE NEED A CAMERA FOR THE ROOT
        // NODE
    }
    public int minCameraCover_(TreeNode root , int[] count) 
    {
        if(root == null) return 0;
        int lans = minCameraCover_(root.left,count);
        int rans = minCameraCover_(root.right,count);
        if(lans == -1 || rans == -1)
        {
            count[0]++;
            return 1;
        }
        if(lans == 1 || rans ==1 )
        {
            return 0;
        }
        return -1;
    }

    // LEETCODE 337 HOUSE ROBBER 3
    // JUST RETURN THE VALUE OF (MY) AND (MY CHILD) VIDEO IN DRU RUN
    public int rob(TreeNode root) 
    {
        int[] ans = rob_(root);
        return Math.max(ans[0] , ans[1]);
    }
    public int[] rob_(TreeNode root) 
    {
        if(root == null) return new int[2];
        int[] myAns = new int[2];
        int[] lans = rob_(root.left);
        int[] rans = rob_(root.right);
        myAns[0] = root.val + lans[1] + rans[1];
        myAns[1] = Math.max(lans[0],lans[1]) + Math.max(rans[0],rans[1]);
        return myAns;
    }

    // LEETCODE 662 
    public class pair {
        TreeNode node = null;
        long w = 0;
        pair(TreeNode node, long w) {
            this.node = node;
            this.w = w;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root, 1));
        int ans = 0;
        while (que.size() != 0) {
            int size = que.size();
            long fi = que.getFirst().w;
            long li = que.getFirst().w;
            while (size-- > 0) {
                pair p = que.removeFirst();
                TreeNode node = p.node;
                long w = p.w;
                li = w;
                if (node.left != null)
                    que.addLast(new pair(node.left, 2 * w));
                if (node.right != null)
                    que.addLast(new pair(node.right, 2 * w + 1));
            }
            ans = Math.max(ans, (int) (li - fi + 1));
        }
        return ans;
    }


}