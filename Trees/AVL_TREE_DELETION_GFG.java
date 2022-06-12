class AVL_TREE_DELETION
{
	public static void levelOrderTraversalSimple(Node root)
    {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while(queue.size()!=0)
        {
            Node node = queue.removeFirst();
            if(node == null) 
            {
                System.out.print(null + " ");
                continue;
            }
            
            queue.addLast(node.left);
            queue.addLast(node.right);
            
            System.out.print(node.data + " ");
        }
    }
    public static Node deleteNode(Node root, int key)
    {
        Node t =  delete(root,key);
        // levelOrderTraversalSimple(t);
        // System.out.println();
        return t;
        
    }
    public static Node llRotation(Node node)
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
    public static Node rrRotation(Node node)
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

    public static Node inorderPred(Node node)
    {
        if(node == null) return null;
    	while(node.right!=null) node = node.right;
    	return node;
    }

    public static Node balance(Node node)
    {
    	if(node == null) return null;

        node.left = balance(node.left);
    	node.right = balance(node.right);


    	int lh = (node.left!=null) ? node.left.height : 0;
        int rh = (node.right!=null) ? node.right.height : 0;

        if(lh-rh == -2)
        {
            int rrh = (node.right.right!=null) ? node.right.right.height : 0;
            int rlh = (node.right.left!=null) ? node.right.left.height : 0;

            if(rlh-rrh == -1)
            {
                node =  rrRotation(node);
            }
            else
            {
                node.right = llRotation(node.right);
                node =  rrRotation(node);
            }
        }
        else if(lh-rh == 2)
        {
            int llh = (node.left.left!=null) ? node.left.left.height : 0;
            int lrh = (node.left.right!=null) ? node.left.right.height : 0;

            if(llh-lrh == -1)
            {
                node.left = rrRotation(node.left);
                node =  llRotation(node);
            }
            else
            {
                node =  llRotation(node);
            }
        }
        else 
        {
            node.height = Math.max(lh,rh)+1;
            return node;
        }
        
        node.left = balance(node.left);
        node.right = balance(node.right);
        
        return node;
    }

    public static Node delete(Node node, int key)
    {
    	if(node == null) return null;
        int val = node.data;
        if(key > val) node.right = delete(node.right,key);
        else if(key < val) node.left = delete(node.left,key);
        else
        {
        	Node inPred = inorderPred(node.left);
        	if(inPred == null) return node.right;
        	else
        	{
        		node.data = inPred.data;
        		node.left = delete(node.left,inPred.data);
        	}	
        }

        return balance(node);
    }

}