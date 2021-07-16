import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;

class treeFromTraversal
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
    public static void inOrderTraversal(TreeNode root)
    {
        if(root == null) return ;
        inOrderTraversal(root.left);
        System.out.println(root.val);
        inOrderTraversal(root.right);
    }

    // LEETCODE 105 TREE FROM INORDER AND PREORDER
    public static TreeNode buildTree(int[] preorder, int[] inorder) 
    {
        TreeNode ans =  preInTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
        inOrderTraversal(ans);
        return ans;
    }
    public static TreeNode preInTree(int []pre, int psi, int pei, int[]in, int isi, int iei)
    {
        if(psi > pei) return null;
        TreeNode node = new TreeNode(pre[psi]);
        int idx = isi;
        while(in[idx] != pre[psi]) idx++;
        int tnel = idx - isi;
        node.left = preInTree(pre, psi+1, psi + tnel, in, isi, idx-1);
        node.right = preInTree(pre, psi + tnel +1, pei, in, idx+1, iei);
        return node;
    }

    // TREE FROM POSTORDER AND INORDER
    public static TreeNode buildTree_2(int[] inorder, int[] postorder) 
    {
        TreeNode ans =  postInTree(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
        //inOrderTraversal(ans);
        return ans;
    }
    public static TreeNode postInTree(int []in, int isi, int iei, int[]post, int psi, int pei)
    {
        if(psi > pei) return null;
        TreeNode node = new TreeNode(post[pei]);
        int idx = isi;
        while(in[idx] != post[pei]) idx++;
        int tnel = iei-idx;
        node.left = postInTree(in, isi, idx - 1, post, psi, pei-tnel-1);    
        node.right = postInTree(in, idx+1 , iei , post, pei-tnel, pei-1);     
        return node;
    }

    // FROM PREORDER AND POSTORDER
    public TreeNode postPreTree(int[] post, int ppsi, int ppei, int[] pre, int psi, int pei) {
        if (psi > pei)
            return null;

        TreeNode node = new TreeNode(pre[psi]);

        if (psi == pei)
            return node;

        int idx = ppsi;
        while (post[idx] != pre[psi + 1])
            idx++;

        int tnel = idx - ppsi;
        node.left = postPreTree(post, ppsi, idx, pre, psi + 1, psi + tnel + 1);
        node.right = postPreTree(post, idx + 1, ppei - 1, pre, psi + tnel + 2, pei);

        return node;
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int n = post.length;
        return postPreTree(post, 0, n - 1, pre, 0, n - 1);
    }

    // FLATTEN A BINARY TREE O(N^2) APPROACH
    public static TreeNode getTail(TreeNode root)
    {
        if(root == null) return null;
        while(root.right!=null) root = root.right;
        return root;
    }
    public static void flatten(TreeNode root)
    {
        if(root == null) return;

        flatten(root.left);
        flatten(root.right);
        TreeNode tail = getTail(root.left);
        if(tail!=null)
        {
            tail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }

    // FLATTEN O(N) APPROACH
    
    public static TreeNode flatten_2(TreeNode root)
    {
        if(root == null || root.left == null || root.right == null)
        {
            return root;
        }

        TreeNode l = flatten_2(root.left);
        TreeNode r = flatten_2(root.right);
        if(l!=null)
        {
            l.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return r;
    }


    // BINARY TREE TO DOUBLY LINKED LIST
    // IMPORTANT TESTCASE
            //         10
            //        /   \
            //       20   30
            //     /   \
            //     40   60
            //    /  \
            //   50  90
            //  /     \
            // 87     100
    public static TreeNode bToDLL(TreeNode root)
    {
        TreeNode D = new TreeNode(-1);
        createdoublyLL(root, D);
        TreeNode ans = D.right;
        D.right = null;
        ans.left = null;
        return ans;
    }
    public static TreeNode createdoublyLL(TreeNode root , TreeNode D)
    {
        if(root == null) return null;
        TreeNode left = createdoublyLL(root.left, D);
        if(left  == null)
        {
            D.right = new TreeNode(root.val);
            D.right.left = D;
            left = D.right;
        }
        else if(left != null)
        {
            left.right = new TreeNode(root.val);
            left.right.left = left;
            left = left.right;
        }
        TreeNode right = createdoublyLL(root.right, left);
        if(right != null) return right;
        return left;
    }
    
    //  TREE TO DOUBLY LINKED LIST MORE EASIER VERSION USING GLOBAL VARIABLES
    TreeNode dummy = new TreeNode(-1);
    TreeNode prev = dummy;

    public void treeToDoublyList_(TreeNode root) {
        if (root == null)
            return;

        treeToDoublyList_(root.left);

        prev.right = root;
        root.left = prev;

        prev = root;

        treeToDoublyList_(root.right);

    }

    public TreeNode treeToDoublyList(TreeNode root) {

        if (root == null)
            return root;
        treeToDoublyList_(root);

        TreeNode head = dummy.right;
        head.left = null;
        dummy.right = null;

        prev.right = head;
        head.left = prev;
        return head;
    }

    // Construct Binary Search Tree from Preorder Traversal
    // O(nLogn) approach
    public static TreeNode bstFromPreorder(int[] preorder) 
    {
        TreeNode root = null;
        for(int data : preorder) root = AddNode(root,data);
        return root;
    }
    public static TreeNode AddNode(TreeNode root , int data)
    {
        if(root == null) return new TreeNode(data);
        if(data < root.val) root.left = AddNode(root.left,data);   
        else root.right = AddNode(root.right,data);
        return root;
    }

    // construct a bst from preorder O(n) approach
    public static TreeNode bstFromPreorder_2(int[] preorder)
    {
        int[] idx = new int[1];
        return addNode(preorder, idx, -(int)1e9, (int)1e9);
    }
    public static TreeNode addNode(int[]pre , int[]idx  , int lr , int rr)
    {
        if(idx[0] >= pre.length || pre[idx[0]] < lr || pre[idx[0]] > rr) return null;
        TreeNode node = new TreeNode(pre[idx[0]]);
        idx[0] += 1;
        node.left = addNode(pre,idx,lr,node.val);
        node.right = addNode(pre,idx,node.val,rr);
        return node;
    }

    // construct a bst from postorder O(n) approach
    public static TreeNode bstFromPostorder(int[] Postorder)
    {
        int[] idx = new int[]{Postorder.length-1};
        return addNodePost(Postorder, idx, -(int)1e9, (int)1e9);
    }
    public static TreeNode addNodePost(int[]pre , int[]idx  , int lr , int rr)
    {
        if(idx[0] < 0 || pre[idx[0]] < lr || pre[idx[0]] > rr) return null;
        TreeNode node = new TreeNode(pre[idx[0]]);
        idx[0] -= 1;
        node.right = addNodePost(pre,idx,node.val,rr);
        node.left = addNodePost(pre,idx,lr,node.val);
        return node;
    }

    // construct a bst from levelorder traversal
    public static class LevelPair
    {
        TreeNode par = null;
        int lb = -(int)1e8;
        int rb = (int)1e8;
        LevelPair(){

        }
        LevelPair(TreeNode par , int lb ,  int rb)
        {
            this.par = par;
            this.lb = lb;
            this.rb = rb;
        }
    }
    public TreeNode constructBSTfromLevelOrder(int[] arr)
    {
        int idx = 0;
        LinkedList<LevelPair> queue = new LinkedList<>();
        queue.addLast(new LevelPair());
        TreeNode root = null;
        while(queue.size()!=0 && idx<arr.length)
        {
            LevelPair pair  = queue.removeFirst();
            if(arr[idx] <  pair.lb || arr[idx] > pair.rb) continue;

            TreeNode node = new TreeNode(arr[idx++]);
            if(pair.par == null) root = node;
            else{
                if(node.val < pair.par.val) pair.par.left = node;
                else pair.par.right = node;
            }
            queue.addFirst(new LevelPair(node,pair.lb,node.val));
            queue.addFirst(new LevelPair(node,node.val,pair.rb));
        } 
        return root;
    }
    public static void main(String[] args) 
    {
        int[] pre = new int[]{3,9,20,15,7};
        int[] in = new int []{9,3,15,20,7};

        buildTree_2(pre, in);
    }
}