class BinarySearchTrees
{
    public static class TreeNode
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

    public static void main(String[] args) 
    {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,22,33,44,55,66,77,88,99};
        traverse(createBST(arr,0,arr.length-1));    
    }
}