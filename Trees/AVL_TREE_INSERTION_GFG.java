// NOTE THAT WE NEED NOT INSERT DUPLICATE DATA
class temp
{
    public Node llRotation(Node node)
    {
        Node one = node.left;
        Node two = one.right;

        node.left = two;
        one.right = node;
        
        int lh = (one.left!=null) ? one.left.height : 0;
        int rh = (node.right!=null) ? node.right.height : 0;
        int th = (two!=null) ? two.height : 0;
        
        node.height = Math.max(rh,th)+1;
        one.height = Math.max(lh,node.height)+1;
        return one;
    }
    public Node rrRotation(Node node)
    {
        Node one = node.right;
        Node two = one.left;

        node.right = one.left;
        one.left = node;
        
        int lh = (node.left!=null) ? node.left.height : 0;
        int rh = (one.right!=null) ? one.right.height : 0;
        int th = (two!=null) ? two.height : 0;
        
        node.height = Math.max(lh,th)+1;
        one.height = Math.max(rh,node.height)+1;

        return one;

    }

    public Node insert(Node node , int data)
    {
        if(node == null)
        {
            return new Node(data);
        }

        if(data < node.data) node.left = insert(node.left,data);
        else if(data > node.data) node.right = insert(node.right,data);
        else if(node.data == data) return node;

        int lh = (node.left!=null) ? node.left.height : 0;
        int rh = (node.right!=null) ? node.right.height : 0;

        if(lh-rh == -2)
        {
            int rrh = (node.right.right!=null) ? node.right.right.height : 0;
            int rlh = (node.right.left!=null) ? node.right.left.height : 0;

            if(rlh-rrh == -1)
            {
                return rrRotation(node);
            }
            else
            {
                node.right = llRotation(node.right);
                return rrRotation(node);
            }
        }
        else if(lh-rh == 2)
        {
            int llh = (node.left.left!=null) ? node.left.left.height : 0;
            int lrh = (node.left.right!=null) ? node.left.right.height : 0;

            if(llh-lrh == -1)
            {
                node.left = rrRotation(node.left);
                return llRotation(node);
            }
            else
            {
                return llRotation(node);
            }
        }
        else 
        {
            node.height = Math.max(lh,rh)+1;   
        }

        return node;
    }

    public  Node insertToAVL(Node node,int data)
    {
        
        return insert(node,data);
    }
}