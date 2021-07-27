import java.util.HashMap;
import java.util.LinkedList;
class extraFaang
{
    public static class Node
    {
        int data = 0;
        Node left = null;
        Node right = null;
        Node(int val)
        {
            this.data = val;
        }
    }
    public static class TreeNode
    {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode(int val)
        {
            this.val = val;
        }
    }
    public static class Tree
    {
        int d = 0;
        Tree left , right , random;
        Tree(int d)
        {
            this.d = d;
        }
    }
    public static void preorder(Tree root)
    {
        System.out.print(root.d + " ");

    }
    // GEEKS FOR GEEKS IMAGE MULTIPLICATION
    public static void addAllRight(Node root , LinkedList<Node> stack)
    {
        while(root!=null)
        {
            stack.addLast(root);
            root = root.right;
        }
    }
    public static void addAllLeft(Node root , LinkedList<Node> stack)
    {
        while(root!=null)
        {
            stack.addLast(root);
            root = root.left;
        }
    }
    public static long imgMultiply(Node root)
    {
        long sum = 0;
        LinkedList<Node> left = new LinkedList<>();
        LinkedList<Node> right = new LinkedList<>();

        addAllLeft(root, left);
        addAllRight(root, right);

        while(left.size()!=0 && right.size()!=0)
        {
            if(left.size() == right.size())
            {
                sum  = (sum + (left.get(left.size()-1).data * right.get(right.size()-1).data))%((int)1e9+7);
                if(left.size() == 1 && right.size() == 1)
                {
                    break;
                }
                Node Right = right.removeLast();
                Node Left = left.removeLast();
                if(Right.left!=null) addAllLeft(Right.left, left);
                if(Left.right!=null) addAllRight(Left.right, right);
            }
            else if(left.size() > right.size())
            {
                int temp = left.size() - right.size();
                while(temp > 0)
                {
                    left.removeLast();
                    temp--;
                }
            }
            else if(right.size() > left.size())
            {
                int temp = right.size() - left.size();
                while(temp > 0)
                {
                    right.removeLast();
                    temp--;
                }
            }
        }
        return sum;
    }

    public static Tree cloneTree(Tree root)
    {
        HashMap<Tree,Tree> map = new HashMap<>();  
        clone(root, map); 
        return map.get(root); 
    }

    public static void clone(Tree root , HashMap<Tree,Tree> map)
    {
        if(root == null) return;
        if(!map.containsKey(root))
        {
            Tree t = new Tree(root.d);
            map.put(root, t);
        }

        if(root.left!=null && !map.containsKey(root.left))
        {
            Tree t = new Tree(root.left.d);
            map.put(root.left, t);
            map.get(root).left = map.get(root.left);
        }
        else map.get(root).left = map.get(root.left);

        if(root.right!=null && !map.containsKey(root.right))
        {
            Tree t = new Tree(root.right.d);
            map.put(root.right,t);
            map.get(root).right = map.get(root.right);
        }
        else map.get(root).right = map.get(root.right);

        if(root.random!=null && !map.containsKey(root.random))
        {
            Tree t = new Tree(root.random.d);
            map.put(root.random,t);
            map.get(root).random = map.get(root.random);
        }
        else map.get(root).random = map.get(root.random);

        clone(root.left,map);
        clone(root.right,map);

        return;
    }
    
    // CONVERT TREE TO A DOUBLY LINKED LIST
    public Node bTreeToClist(Node root)
    {
        if(root == null) return null;
        Node dummy = new Node(-1);
        Node tail = convert(root,dummy);
        dummy = dummy.right;
        dummy.left = tail;
        tail.right = dummy;
        return dummy;
    }
    public Node convert(Node root , Node dummy)
    {
        if(root == null) return null;
        Node left = convert(root.left , dummy);
        if(left == null)
        {
            dummy.right = root;
            root.left = dummy;
        }
        if(left!=null)
        {
            left.right = root;
            root.left = left;
        }
        Node right = convert(root.right,root);
        if(right!=null) return right;
        return root;
    }

    // DOUBLY LINKED LIST TO BINARY SEARCH TREE (INORDER TRAVERSAL TO A BINARY SEARCH TREE)
    // VERY NICE SOLUTION READ FROM GFG 
    // WATCH VIDEO FOR MORE CLEARITY
    // O(n) SOLUTION
    
    public static int length(TreeNode root)
    {
        int count = 0;
        while(root!=null)
        {
            count++;
            root = root.right;
        }
        return count;
    }
    public static TreeNode head;
    public static TreeNode DLLtoBST()
    {
        int len = length(head);
        return convert(len);
    }
    public static TreeNode convert(int n)
    {
        if(n<=0) return null;
        TreeNode left = convert(n/2);
        TreeNode root = head;
        root.left = left;
        head = head.right;
        root.right = convert(n - (n/2) - 1);
        return root;
    }
}