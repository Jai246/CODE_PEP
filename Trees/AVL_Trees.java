class AVL_Trees
{
    public static class TreeNode
    {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        int height = 0;
        int bal = 0;
        TreeNode(){
        }
        TreeNode(int val){
            this.val = val;
        }
    }

    public static void updateHeightAndBalance(TreeNode root)
    {
        int lh = -1;
        int rh = -1;

        if(root.left!=null) updateHeightAndBalance(root.left);
        if(root.right!=null) updateHeightAndBalance(root.right);

        root.height = Math.max(lh,rh)+1;
        root.bal = lh-rh;
    }

    public static TreeNode rightRotation(TreeNode root) // LL ROTATION
    {
        TreeNode a = root.left;
        TreeNode b = a.right;

        a.right = root;
        root.left = b;


        updateHeightAndBalance(root);
        updateHeightAndBalance(a);
        return a;
    }

    public static TreeNode leftRotation(TreeNode root) // RR ROTATION
    {
        TreeNode a = root.right;
        TreeNode b = a.left;

        a.left = root;
        root.right = b;

        updateHeightAndBalance(root);
        updateHeightAndBalance(a);
        return a;
    }

    public static TreeNode getRotation(TreeNode root)
    {
        updateHeightAndBalance(root);
        if(root.bal == 2)
        {   
            if(root.left.bal == 1)
            {
                rightRotation(root);
            }
            else
            {
                root.left = leftRotation(root.left);
                rightRotation(root);
            }
            
        }
        else if(root.bal == -2)
        {
            if(root.right.bal == -1)
            {
                leftRotation(root);
            }
            else
            {
                root.right = rightRotation(root.right);
                leftRotation(root);
            }
        }
        return root;
    }

    public static TreeNode insertIntoBST(TreeNode root , int val)
    {
        if(root == null) root = new TreeNode(val);
        if(val > root.val) insertIntoBST(root.right,val);
        else insertIntoBST(root.left,val);
        root = getRotation(root);
        return root;
    }

    public static int maximumEle(TreeNode root)
    {
        while(root.right!=null)
        {
            root = root.right;
        }
        return root.val;
    }

    public static int minimumEle(TreeNode root)
    {
        while(root.left!=null)
        {
            root = root.left;
        }
        return root.val;
    }
    public static TreeNode deleteNode(TreeNode root , int val)
    {
        if(root == null) return null;
        if(val > root.val) root.right =  deleteNode(root.right,val);
        else if(val < root.val) root.left =  deleteNode(root.left,val);
        else
        {
            if(root.left == null && root.right == null) return null;
            else if(root.left!=null)
            {
                int max = maximumEle(root.left);
                root.val = max;
                root.left = deleteNode(root.left,max);  
            }
            else if(root.right!=null)
            {
                int min = minimumEle(root.left);
                root.val = min;
                root.right = deleteNode(root.right,min);  
            }
        }
        getRotation(root);
        return root;
    }
    // BST to AVL
    public static TreeNode getRotation2(TreeNode root)
    {
        updateHeightAndBalance(root);
        if(root.bal >= 2)
        {
            if(root.left.bal <= -1)
            {
                root.left = leftRotation(root.left);
                rightRotation(root);
            }
            else
            {
                rightRotation(root);
            }
        }
        else if(root.bal <= -2)
        {
            if(root.right.bal <= -1)
            {
                leftRotation(root);
            }
            else
            {
                root.right = rightRotation(root.right);
                leftRotation(root);
            }
        }
        return root;
    }

    public static TreeNode postOrder(TreeNode root)
    {
        if(root == null) return null;
        root.left = postOrder(root.left);
        root.right = postOrder(root.right);

        return getRotation(root);
    }
}