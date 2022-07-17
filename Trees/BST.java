import java.util.ArrayList;
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

    // LEETCODE 653
    // TWO SUM 4
    // SOLUTION USING TWO STACKS
    public void incMeLeft(TreeNode root , LinkedList<TreeNode> lst)
    {
        while(root!=null)
        {
            lst.addLast(root);
            root = root.left;
        }
    }
    public void incMeRight(TreeNode root , LinkedList<TreeNode> rst)
    {
        while(root!=null){
            rst.addLast(root);
            root = root.right;
        }
    }
    public int top(LinkedList<TreeNode> list){
        return (list.get(list.size()-1)).val;
    }
    public boolean findTarget(TreeNode root, int k) 
    {
        LinkedList<TreeNode> lst = new LinkedList<>();
        LinkedList<TreeNode> rst = new LinkedList<>();
        incMeLeft(root,lst);
        incMeRight(root,rst);
        while(top(rst) > top(lst))
        {
            if(top(lst) + top(rst) == k) return true;
            else if(top(lst) + top(rst) < k)
            {
                TreeNode temp = lst.removeLast();
                incMeLeft(temp.right,lst);
            }
            else
            {
                TreeNode temp = rst.removeLast();
                incMeRight(temp.left,rst);
            }
        }
        return false;
    }

    // LEETCODE 230
    // Kth SMALLEST ELEMENT USING MORRIS TRAVERSAL
    public TreeNode rightMostNode(TreeNode node,TreeNode curr){
        while(node.right != null && node.right != curr ){
            node = node.right;
        }
        return node;
    }
     public int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode next = curr.left;
            if (next == null) {
                if (k == 1)
                    return curr.val;
                k--;
                curr = curr.right;

            } else {
                TreeNode rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    curr = curr.left;
                } else { // thread break
                    rightMost.right = null;
                    if (k == 1)
                        return curr.val;
                    k--;
                    curr = curr.right;
                }
            }
        }
        return -1;
    }

    // LEETCODE 1372 LONGEST ZIGZAG PATH IN A BINARY TREE

    public static class zigZagPair
    {
        int leftMax = -1;
        int rightMax = -1;
        zigZagPair(int leftMax , int rightMax){
            this.leftMax = leftMax;
            this.rightMax = rightMax;
        }
    }
    public int longestZigZag(TreeNode root) 
    {
        int[] ans = new int[]{-1};
        calculatePath(root, ans);
        return ans[0];
    }
    public static zigZagPair calculatePath(TreeNode root, int[] ans)
    {
        if(root == null) return new zigZagPair(-1,-1);
        zigZagPair left = calculatePath(root.left,ans);
        zigZagPair right = calculatePath(root.right,ans);
        zigZagPair temp = new zigZagPair(left.rightMax+1,right.leftMax+1);
        ans[0] = Math.max(ans[0] , Math.max(temp.rightMax,temp.leftMax));
        return temp;
    }

    // LEETCODE 99 RECOVER A BINARY TREE

    public static TreeNode a = null;
    public static TreeNode b = null;
    public static TreeNode prev = null;
    
    public void recoverTree(TreeNode root) 
    {
        recoverTree_(root);
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
        return;
    }
    
    public static void recoverTree_(TreeNode root)
    {
        if(root == null) return;
        recoverTree_(root.left);
        if(prev!=null && prev.val > root.val)
        {
            b = root;
            if(a == null) a = prev;
        }
        prev = root;
        recoverTree_(root.right);
        return;
    }

    // public TreeNode a = null;
    // public TreeNode b = null;
    // public TreeNode prev = null;
    
    // public void recoverTree(TreeNode root) 
    // {
    //     recoverTree_(root);
    //     int temp = a.val;
    //     a.val = b.val;
    //     b.val = temp;
    //     return;
    // }
    
    // public boolean recoverTree_(TreeNode root)
    // {
    //     if(root == null) return true;
    //     if(!recoverTree_(root.left)) return false;
    //     if(prev!=null && prev.val > root.val)
    //     {
    //         b = root;
    //         if(a == null) a = prev;
    //         else return false;
    //     }
    //     prev = root;
    //     if(!recoverTree_(root.right)) return false;
    //     return true;
    // }

    // SERIALIZE AND DESERIALIZE A BINARY SEARCH TREE
    // ITS SAME AS TREE FROM PREORDER
    // LEETCODE 297
    int idx = 0;
    public TreeNode createTree(int[] arr) {
        if (idx == arr.length || arr[idx] == -1001) {
            idx++;
            return null;
        }
        TreeNode node = new TreeNode(arr[idx++]);
        node.left = createTree(arr);
        node.right = createTree(arr);

        return node;
    }
    public void serializeTree(TreeNode node, ArrayList<Integer> arr) 
    {
        if (node == null) {
            arr.add(-1001);
            return;
        }
        arr.add(node.val);
        serializeTree(node.left, arr);
        serializeTree(node.right, arr);
    }
    public String serialize(TreeNode root) 
    {
        ArrayList<Integer> ans = new ArrayList<>();
        serializeTree(root,ans);
        String res = "";
        for(int i = 0;i<ans.size();i++)
        {
            if(i > 0) res = res + "," + ans.get(i);
            else res +=  ans.get(i);
        }
        System.out.println(res);
        return res;
    }
    public TreeNode deserialize(String data) 
    {
        String[] arr = data.split(",");
        int[] finalArr = new int[arr.length];
        int i = 0;
        for(String ele : arr)
        {
            finalArr[i++] = Integer.parseInt(ele);
        }
        return createTree(finalArr);
    }

    public static void main(String[] args) 
    {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,22,33,44,55,66,77,88,99};
        traverse(createBST(arr,0,arr.length-1));    
    }
}