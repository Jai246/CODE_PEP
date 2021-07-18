import java.util.LinkedList;

class BinarySearchTrees
{
    public static class TreeNode
    {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode(int data){
            this.val = data;
        }
        TreeNode(int data , TreeNode left , TreeNode right)
        {
            this.val = data;
            this.left = left;
            this.right = right;
        }
    }
    
    public static int size(TreeNode root)
    {
        return (root == null) ? 0 : size(root.left) + size(root.right);
    }

    public static int height(TreeNode root)
    {
        return (root == null) ? 0 : Math.max(height(root.left),height(root.right)) + 1; 
    }

    public static int maximumEle(TreeNode root)
    {
        while(root.right!=null) root = root.right;
        return root.val;
    }

    public static int minimumEle(TreeNode root)
    {
        while(root.left!=null) root = root.left;
        return root.val;
    }

    public boolean findData(TreeNode root , int data)
    {
        while(root!=null)
        {
            if(root.val == data) return true;
            if(data > root.val) root = root.right;
            else root = root.left; 
        }
        return false;
    }

    public boolean findData2(TreeNode root , int data)
    {
        if(root == null) return false;
        if(root.val == data) return true;
        if(data > root.val) return (findData2(root.right,data));
        else return findData2(root.left,data);
    }

    public TreeNode insertIntoBST(TreeNode root,int data)
    {
        if(root == null) return new TreeNode(data,null,null);
        if(data > root.val) root.right = insertIntoBST(root.right,data);
        else root.left = insertIntoBST(root.left,data);
        return root;
    }

    public TreeNode insertIntoBST_2(TreeNode root , int data)
    {
        TreeNode prev = null;

        while(root!=null)
        {
            if(data > root.val){
                prev = root;
                root = root.right;
            }
            else{
                prev = root;
                root = root.left;
            }
        }
        TreeNode ans = null;
        if(data > prev.val) {
            prev.right = new TreeNode(data,null,null);
            ans = prev.right;
        }
        else {
            prev.left = new TreeNode(data,null,null);
            ans = prev.left;
        }
        return ans;
    }

    public static TreeNode deleteNode(TreeNode root , int key)
    {
        if(root == null) return null;
        if(key > root.val) root.right =  deleteNode(root.right,key);
        else if(key < root.val) root.left =  deleteNode(root.left,key);
        else
        {
            if(root.left == null && root.right == null) return null;
            if(root.left!=null)
            {
                int max = maximumEle(root.left);
                root.val = max;
                root.left = deleteNode(root.left,max);
            }
            else if(root.right!=null)
            {
                int min = minimumEle(root.right);
                root.val = min;
                root.right = deleteNode(root.right,min);
            }
        }
        return root;
    }

    // LCA of a binary search tree
    public static TreeNode LCA(TreeNode root,int n1 , int n2)
    {
        while(root!=null){
        if(root.val > n1 && root.val>n2) root = root.left;
        else if(root.val < n1 && root.val<n2) root = root.right;
        else return root;
        }
        return null;
    }

    // creating BST from a sorted array
    public static void traverse(TreeNode root)
    {
        if(root == null) return;
        System.out.print(root.val + " ");
        traverse(root.left);
        traverse(root.right);
        return;
    }
    public static TreeNode createBST(int[]arr , int si , int ei)
    {
        if(si > ei) return null;
        int mid = (si+ei)/2;
        TreeNode node = new TreeNode(arr[mid],null,null);
        node.left = createBST(arr,si,mid-1);
        node.right = createBST(arr,mid+1,ei);
        return node;
    }

    // INORDER PRED SUCC CEIL FLOOR
    // FOR BINARY TREE
    public static class allPair
    {
        TreeNode prev = null;
        TreeNode pred = null;
        TreeNode succ = null;
        int ceil = (int)1e9;
        int floor = -(int)1e9;
    }

    public static void allSolution(TreeNode node , allPair pair , int data)
    {
        if(node == null) return;
        if(node.val < data)
        {
            pair.floor = Math.max(pair.floor,node.val);
        }
        if(node.val > data) 
        {
            pair.ceil = Math.min(pair.ceil,node.val);
        }
        allSolution(node.left, pair, data);
        if(node.val == data && pair.pred == null)
        {
            pair.pred = pair.prev;
        }
        if(pair.prev!=null && data == pair.prev.val && pair.succ == null)
        {
            pair.succ = node;
        }
        pair.prev = node;
        allSolution(node.right, pair, data);
    }

    // FOR BST PRED == FLOOR && SUCC = CEIL
    public static void predSucc(TreeNode root , int data)
    {
        TreeNode pred = null;
        TreeNode succ = null;
        boolean ifDataPresent = false; 
        
        while(root!=null)
        {
            if(root.val == data)
            {
                ifDataPresent = true;
                if(root.left != null)
                {
                    pred = root.left;
                    while(pred.right!=null) pred = pred.right;
                }
                if(root.right != null)
                {
                    succ = root.right;
                    while(succ.left!=null) succ = succ.right;
                }
                break;
            }
            else if(data > root.val)
            {
                pred = root;
                root = root.right;
            }
            else{
                succ = root;
                root = root.left;
            }
        }
        if(ifDataPresent) System.out.println("Pred ->" + pred.val + "Succ ->" + succ.val);
    }

    // BST ITERATOR LEETCODE 173
    public LinkedList<TreeNode> stack = new LinkedList<>();

    public void BSTIterator(TreeNode root) 
    {
        addAllLeft(root);
    }
    
    public int next() 
    {
        TreeNode temp = stack.removeLast();
        if(temp.right != null)
        {
            addAllLeft(temp.right);
        }
        return temp.val;
    }
    
    public boolean hasNext() 
    {
        return stack.size()!=0;
    }
    public void addAllLeft(TreeNode root)
    {
        while(root!=null)
        {
            stack.add(root);
            root = root.left;
        }
    }

    
    public static void main(String[] args) 
    {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,22,33,44,55,66,77,88,99};
        traverse(createBST(arr,0,arr.length-1));    
    }
}