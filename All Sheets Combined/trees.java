class Trees
{
    // Construct Binary Tree from String with bracket representation
    // Simple Recursive Solution
    // We have to use global pointer to get track for String IDX

    static class Node
    {
        int data;
        Node left,right;
         
        Node(int data)
        {
            this.data = data;
            this.left = this.right = null;
        }
    }
     
    // static variable to point to the
    // starting index of the string.
    static int start = 0;
     
    // Construct Tree Function which accepts
    // a string and return root of the tree;
    static Node constructTree(String s)
    {
         
        // Check for null or empty string
        // and return null;
        if (s.length() == 0 || s == null)
        {
            return null;
        }
         
        if (start >= s.length())
            return null;
         
        // Boolean variable to check
        // for negative numbers
        boolean neg = false;
         
        // Condition to check for negative number
        if (s.charAt(start) == '-')
        {
            neg = true;
            start++;
        }
         
        // This loop basically construct the
        // number from the continuous digits
        int num = 0;
        while (start < s.length() &&
               Character.isDigit(s.charAt(start)))
        {
            int digit = Character.getNumericValue(
                s.charAt(start));
            num = num * 10 + digit;
            start++;
        }
         
        // If string contains - minus sign
        // then append - to the number;
        if (neg)
            num = -num;
         
        // Create the node object i.e. root of
        // the tree with data = num;
        Node node = new Node(num);
         
        if (start >= s.length())
        {
            return node;
        }
         
        // Check for open bracket and add the
        // data to the left subtree recursively
        if (start < s.length() && s.charAt(start) == '(' )
        {
            start++;
            node.left = constructTree(s);
        }
         
        if (start < s.length() && s.charAt(start) == ')')
        {
            start++;
            return node;
        }
         
        // Check for open bracket and add the data
        // to the right subtree recursively
        if (start < s.length() && s.charAt(start) == '(')
        {
            start++;
            node.right = constructTree(s);
        }
         
        if (start < s.length() && s.charAt(start) == ')')
        {
            start++;
            return node;
        }
        return node;
    }
 
    // Print tree function
    public static void printTree(Node node)
    {
        if (node == null)
            return;
       
        System.out.println(node.data + " ");
        printTree(node.left);
        printTree(node.right);
    }


    // Transform to Sum Tree 

    public void toSumTree(Node root)
    {
        create(root);
    }
    
    public int create(Node root)
    {
        if(root == null) return 0;
        int left = create(root.left);
        int right = create(root.right);
        int sum = left + right;
        int rootVal = root.data;
        root.data = sum;
        return sum + rootVal;
    }


    // Minimum Swaps To Convert Binary Tree Into BST
    // Note that we can easily find Inorder , PreOrder , Postorder from the 
    // Array representation of tree itself without creating a tree

    public static int minSwaps(int nums[])
    {
        int count = 0;
        boolean[] vis = new boolean[nums.length];
        int[][] arr = new int[nums.length][];
        for(int i = 0;i<nums.length;i++)
        {
            arr[i] = new int[]{nums[i],i};
        }
        
        Arrays.sort(arr ,(a,b)->{
            return a[0] - b[0];
        } );
        
        for(int i = 0;i<arr.length;i++)
        {
            int temp = arr[i][0];
            int next = arr[i][1];
            int tCount = 0;
            if(!vis[i])
            {
                vis[i] = true;
                int j = next;
                while(arr[j][0]!=temp)
                {
                    vis[j] = true;
                    j = arr[j][1];
                    tCount++;
                }
                count += tCount;
            }
        }
        return count;
    }
    public static void getInOrder(ArrayList<Integer> arr , int[] inorder , int[] i , int idx)
    {
        if(idx >= inorder.length) return;
        getInOrder(arr,inorder,i,(2*idx)+1);
        inorder[i[0]++] = arr.get(idx);
        getInOrder(arr,inorder,i,(2*idx)+2);
    }
    public static int minimumSwaps(ArrayList<Integer> arr, int n) 
    {
        int[] inorder = new int[n];
        getInOrder(arr,inorder,new int[]{0},0);
//         System.out.println(Arrays.toString(inorder));
        return minSwaps(inorder);
    }



    // Sum Tree 

    boolean isSumTree(Node root)
    {
        boolean[] bool = new boolean[1];
        bool[0] = true;
        check(root,bool);
        return bool[0];
    }
    public int check(Node root , boolean[] bool)
    {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return root.data;
        int left = check(root.left,bool);
        if(!bool[0]) return -1;
        int right = check(root.right,bool);
        if(!bool[0]) return -1;
        
        if(root.data != left + right)
        {
            bool[0] = false;
            return -1;
        }
        
        return left + right + root.data;
    }


    //Leaf at same level 

    boolean check(Node root)
    {
        int[] lvl = new int[]{-1};
        return isTrue(root,lvl,0);
    }
    
    boolean isTrue(Node root , int[]lvl , int l)
    {
        if(root == null) return true;
        if(root.left == null && root.right == null)
        {
            if(l == lvl[0] || lvl[0] == -1)
            {
                lvl[0] = l;
                return true;
            }
            else return false;
        }
        
        boolean left = isTrue(root.left,lvl,l+1);
        boolean right = isTrue(root.right,lvl,l+1);
        
        return left && right;
    }

    // Sum of the Longest Bloodline of a Tree (Sum of nodes on the longest path from root to leaf node) 

    public int sumOfLongRootToLeafPath(Node root)
    {
        int[] lvl = new int[]{0};
        int[] sum = new int[]{0};
        getSum(root,sum,lvl,0,0);
        return sum[0];
    }
    
    public void getSum(Node root , int[]sum , int[] lvl ,  int l , int s)
    {
        if(root == null)
        {
            if(l > lvl[0])
            {
                lvl[0] = l;
                sum[0] = s;
            }
            else if(l == lvl[0])
            {
                sum[0] = Math.max(sum[0],s);
            }
            return;
        }
        
        getSum(root.left,sum,lvl,l+1,s+root.data);
        getSum(root.right,sum,lvl,l+1,s+root.data);
    }


    // Maximum sum of Non-adjacent nodes 
    // Important
    // Good Question
    // Bewakoofi kari meine iss question mei


    static int getMaxSum(Node root)
    {
        int[] answer = helper(root);
        return Math.max(answer[0], answer[1]);
    }
    static int[] helper(Node root)
    {
        if(root== null) 
        {
            return new int[] { 0, 0 };
        }

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int rob = root.data + left[1] + right[1];
        int notRob = Math.max(left[0],left[1])+Math.max(right[0],right[1]);

        return new int[] { rob, notRob };
    }


    // Populate Inorder Successor for all nodes 
    // Do Good Dryrun
    public void populateNext(Node root)
    {
        populate(root,new Node[]{null});
    }
    
    public void populate(Node root , Node[]temp)
    {
        if(root == null) return;
        // For Understanding But Not Required
        // if(root.left == null && root.right == null && temp[0] == null) 
        // {
        //     temp[0] = root;
        //     return;
        // }
        populate(root.left,temp);
        if(temp[0]!=null) temp[0].next = root;
        temp[0] = root;
        populate(root.right,temp);
    }
    

    // Merge two BST 's 
    // Using BST to SLL function

    public List<Integer> merge(Node root1,Node root2)
    {
        Node d1 = new Node(-1);
        Node d2 = new Node(-1);
        bstToSll(root1,new Node[]{d1});
        bstToSll(root2,new Node[]{d2});
        d1 = d1.right;
        d2 = d2.right;
        
        List<Integer> ans = new ArrayList<>();
        
        while(d1!=null && d2!=null)
        {
            int v1 = d1.data;
            int v2 = d2.data;
            
            if(v1 < v2)
            {
                ans.add(v1);
                d1 = d1.right;
            }
            else if(v1 > v2)
            {
                ans.add(v2);
                d2 = d2.right;
            }
            else
            {
                ans.add(v1);
                ans.add(v1);
                d1 = d1.right;
                d2 = d2.right;
            }
        }
        
        while(d1!=null)
        {
            ans.add(d1.data);
            d1 = d1.right;
        }
        
        while(d2!=null)
        {
            ans.add(d2.data);
            d2 = d2.right;
        }
        
        return ans;
    }
    public void bstToSll(Node root , Node[] dummy)
    {
        if(root == null) return;
        bstToSll(root.left,dummy);
        
        dummy[0].right = root;
        dummy[0] = root;
        
        bstToSll(root.right,dummy);
    }


    // Kth largest and smallest done Using morris traversal
    // Check In PRevious Folders



    // Median of BST 
    // We can further optimize this solution
    // y using morris traversal 2 times
    // 1 for size and 1 for median calculation
    public static Node rightMostNode(Node node,Node curr)
    {
        while(node.right != null && node.right != curr )
        {
            node = node.right;
        }
        return node;
    }
    public static ArrayList<Integer> morris(Node root) 
    {
        ArrayList<Integer> ans = new ArrayList<>();
        Node curr = root;
        while (curr != null) 
        {
            Node next = curr.left;

            if (next == null) 
            {
                ans.add(curr.data);
                curr = curr.right;

            } 
            else 
            {
                Node rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) 
                {
                    rightMost.right = curr;
                    curr = curr.left;
                }
                else 
                {
                    rightMost.right = null;
                    ans.add(curr.data);
                    curr = curr.right;

                }
            }
        }

        return ans;
    }

    public static float findMedian(Node root)
    {
        ArrayList<Integer> ans = morris(root);
        int s = ans.size()-1;
        if(s%2 == 0)
        {
            return (float)ans.get(s/2);
        }
        else
        {
            double v1 = (ans.get(s/2));
            double v2 = ans.get((s+1)/2);
            // System.out.println((v1 + v2)/2.0);
            Double v3 = new Double((v1 + v2)/2.0); 
            return v3.floatValue();
        }
    }


    // Count BST nodes that lie in a given range 

    int getCount(Node root,int l, int h)
    {
        int[] count = new int[]{0};
        inOrder(root,l,h,count);
        return count[0];
    }
    
    boolean inOrder(Node root , int l , int h , int[]count)
    {
        if(root == null) return true;
        boolean res1 = inOrder(root.left,l,h,count);
        if(!res1) return false;
        if(root.data >= l && root.data <= h) count[0]++;
        else if(root.data > h) return false;
        
        boolean res2 = inOrder(root.right,l,h,count);
        if(!res2) return false;
        
        return true;
    }


    // Check whether BST contains Dead End 
    // Good way to traverse

    public static boolean isDeadEnd(Node root)
   {
     return  find(root,1,Integer.MAX_VALUE);
   }
   public static boolean find(Node root,int min,int max)
   {
       if(root==null)
       {
           return false;
       }
       if(root.data==min && root.data==max)
       {
           return true;
       }
       return find(root.left,min,root.data-1) || find(root.right,root.data+1,max);
   }


   // 1373. Maximum Sum BST in Binary Tree
   // Almost same as check if BST
    private int maxSum = 0;
    public int maxSumBST(TreeNode root) 
    {
        postOrderTraverse(root);
        return maxSum;
    }
    private int[] postOrderTraverse(TreeNode root) 
    {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        int[] left = postOrderTraverse(root.left);
        int[] right = postOrderTraverse(root.right);
        if (!(left != null && right != null && root.val > left[1] && root.val < right[0])) return null;
        int sum = root.val + left[2] + right[2];
        maxSum = Math.max(maxSum, sum);
        int min = Math.min(root.val, left[0]);
        int max = Math.max(root.val, right[1]);
        return new int[]{min, max, sum};
    }

    // Merge Two Balanced Binary Search Trees
    //     We can use a Doubly Linked List to merge trees in place. Following are the steps.
    // 1) Convert the given two Binary Search Trees into doubly linked list in place (Refer this post for this step). 
    // 2) Merge the two sorted Linked Lists (Refer this post for this step). 
    // 3) Build a Balanced Binary Search Tree from the merged list created in step 2. (Refer this post for this step)
}