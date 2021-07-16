import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
class traversals
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
    public static void levelOrderTraversalSimple(TreeNode root)
    {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(queue.size()!=0)
        {
            TreeNode node = queue.removeFirst();
            if(node.left!= null) queue.addLast(node.left);
            if(node.right!= null) queue.addLast(node.right);
            System.out.print(node.val + " ");
        }
    }
    // With Two Queues
    public static void levelOrderTraversal_2(TreeNode root)
    {
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();
        queue1.addLast(root);
        int level = 0;
        System.out.println("Level" + level +  "->");
        while(queue1.size()!=0)
        {
            TreeNode node = queue1.removeFirst();
            System.out.println(node.val);
            if(node.left!= null) queue2.addLast(node.left);
            if(node.right!= null) queue2.addLast(node.right);
            
            if(queue1.size()==0)
            {
                System.out.println();
                System.out.println("Level" + (++level) +  "->");
                LinkedList<TreeNode> temp = queue1;
                queue1 = queue2;
                queue2 = temp;
            }
        }
    }

    public static void levelOrderTraversal_3(TreeNode root)
    {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        queue.addLast(null);

        int level = 0;
        System.out.println("Level" + level +  "->");

        while(queue.size()!=1) // because of last NULL
        {
            TreeNode node = queue.removeFirst();
            System.out.print(node.val + " ");
            if(node.left!= null) queue.addLast(node.left);
            if(node.right!= null) queue.addLast(node.right);
            if(queue.getFirst() == null)
            {
                System.out.println();
                System.out.println("Level" + (++level) +  "->");
                queue.addLast(queue.removeFirst());
            }
        }
    }

    public static void levelOrderTraversal_4(TreeNode root)
    {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int level = 0;
        while(queue.size()!=0)
        {
            int size = queue.size();
            System.out.println("Level" + (++level) +  "->");
            while(size-- > 0)
            {
                TreeNode node = queue.removeFirst();
                System.out.println(node.val);
                if(node.left!= null) queue.addLast(node.left);
                if(node.right!= null) queue.addLast(node.right);

            }
            level++;
            System.out.println();
        }
    }
    // LEFT VIEW
    public static ArrayList<Integer> leftView(TreeNode root)
    {
        if(root == null) return new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        queue.addLast(root);
        while(queue.size()!=0)
        {
            int size = queue.size();
            int count = 0;
            while(size-- > 0)
            {
                TreeNode node = queue.removeFirst();
                if(count++ == 0) ans.add(node.val);
                if(node.left!= null) queue.addLast(node.left);
                if(node.right!= null) queue.addLast(node.right);

            }
        }
        return ans;
    }
    // RIGHT VIEW
    public static ArrayList<Integer> rightView(TreeNode root) // just invert the child insertion order
    {
        if(root == null) return new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        queue.addLast(root);
        while(queue.size()!=0)
        {
            int size = queue.size();
            int count = 0;
            while(size-- > 0)
            {
                TreeNode node = queue.removeFirst();
                if(count++ == 0) ans.add(node.val);
                if(node.right!= null) queue.addLast(node.right);
                if(node.left!= null) queue.addLast(node.left);

            }
        }
        return ans;
    }
    // WE CAN EASILY DO THIS USING HASHMAP CHECK NOTES
    // VERTICAL ORDER TRAVERSAL 2 USING WIDTH FUNCTION
    public static class verticalPair
    {
        TreeNode node = null;
        int Hidx = 0;
        verticalPair(){
        }
        verticalPair(TreeNode node , int Hidx , int Vidx)
        {
            this.node = node;
            this.Hidx = Hidx;
        }
        public verticalPair(traversals.TreeNode left, int i) {
        }
    }
    public static void width(TreeNode root , int hl , int[] ans)
    {
        if(root == null) return;
        ans[0] = Math.min(ans[0],hl);
        ans[1] = Math.max(ans[1],hl);
        width(root.left,hl-1,ans);
        width(root.right,hl+1,ans);
    }
    public static List<List<Integer>> verticalTraversal(TreeNode root) 
    {
        LinkedList<verticalPair> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int[] a = new int[2];
        width(root,0,a);
        int width = (a[1] - a[0]) + 1;

        queue.addLast(new verticalPair(root,-a[0],1));
        @SuppressWarnings("unchecked")
        ArrayList<int[]>[] map = new ArrayList[width+1];
        while(queue.size()!=0)
        {
            verticalPair pair = queue.removeFirst();
            if(map[pair.Hidx] == null)
            {
                map[pair.Hidx] = new ArrayList<>();
            }
            else map[pair.Hidx].add(new int[]{pair.node.val});
            if(pair.node.left != null) {
                queue.add(new verticalPair(pair.node.left,(pair.Hidx-1)));
            }
            if(pair.node.right != null) {
                queue.add(new verticalPair(pair.node.right,(pair.Hidx+1)));
            }
        }
        int i = 0;
        while(i < width)
        {
            List<Integer> temp = new ArrayList<>();
            for(int[]ele : map[i++]) temp.add(ele[0]);
            ans.add(temp);
        }
        return ans;
    }

    // GEEKS FOR GEEKS VERTICAL SUM
    public class verticalPair2
    {
        TreeNode node = null;
        int idx = 0;
        verticalPair2(){
        }
        verticalPair2(TreeNode node , int idx)
        {
            this.node = node;
            this.idx = idx;
        }
    }
    public ArrayList <Integer> verticalSum(TreeNode root) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        LinkedList<verticalPair2> queue = new LinkedList<>();
        queue.add(new verticalPair2(root,0));
        int min = 0;
        int max = 0;
        while(queue.size()!=0)
        {
            verticalPair2 temp = queue.removeFirst();
            if(!map.containsKey(temp.idx))
            {
                map.put(temp.idx,temp.node.val);
            }
            else
            {
                map.put(temp.idx,map.get(temp.idx)+temp.node.val);
            }
            if(temp.node.left != null) queue.addLast(new verticalPair2(temp.node.left,temp.idx-1));
            if(temp.node.right != null) queue.addLast(new verticalPair2(temp.node.right,temp.idx+1));
            if(temp.node.left!=null) min = Math.min(min,temp.idx-1);
            if(temp.node.right!=null) max = Math.max(max,temp.idx+1);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while(min<=max)
        {
            ans.add(map.get(min++));
        }
        return ans;
    }

    // BOTTOM VIEW OF A BONARY TREE GFG
    // last element of the vertical traversal
    public ArrayList <Integer> bottomView(TreeNode root) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        LinkedList<verticalPair2> queue = new LinkedList<>();
        queue.add(new verticalPair2(root,0));
        int min = 0;
        int max = 0;
        while(queue.size()!=0)
        {
            verticalPair2 temp = queue.removeFirst();
            // if(!map.containsKey(temp.idx))
            // {
            map.put(temp.idx,temp.node.val);
            // }
            // else
            // {
            //     map.put(temp.idx,map.get(temp.idx)+temp.node.val);
            // }
            if(temp.node.left != null) queue.addLast(new verticalPair2(temp.node.left,temp.idx-1));
            if(temp.node.right != null) queue.addLast(new verticalPair2(temp.node.right,temp.idx+1));
            if(temp.node.left!=null) min = Math.min(min,temp.idx-1);
            if(temp.node.right!=null) max = Math.max(max,temp.idx+1);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while(min<=max)
        {
            ans.add(map.get(min++));
        }
        return ans;
    }

    // bottom view show only the first element that occures at an level
    // using visited Array
    // using with tech would be easier in this
    public class verticalPair3
    {
        TreeNode node = null;
        int idx = 0;
        int h = 0;
        verticalPair3(){
        }
        verticalPair3(TreeNode node , int idx,int h)
        {
            this.node = node;
            this.idx = idx;
            this.h = h;
        }
    }
    public static int height(TreeNode root)
    {
        return (root == null) ? 0 : Math.max(height(root.left),height(root.right)) + 1; 
    }
    public ArrayList <Integer> bottomView2(TreeNode root) 
    {
        int height = height(root);
        int[] a = new int[2];
        width(root, 0, a);
        int w = a[1]-a[0]+1;
        int[] map = new int[w];
        boolean[]vis = new boolean[w];
        LinkedList<verticalPair3> queue = new LinkedList<>();
        queue.add(new verticalPair3(root,-a[0],1));
        int min = 0;
        int max = 0;
        while(queue.size()!=0)
        {
            verticalPair3 temp = queue.removeFirst();
            if(temp.h == height && !vis[temp.idx]){
                map[temp.idx] = temp.node.val;
                vis[temp.idx] = true;
            }
            if(temp.node.left != null) queue.addLast(new verticalPair3(temp.node.left,temp.idx-1,temp.h+1));
            if(temp.node.right != null) queue.addLast(new verticalPair3(temp.node.right,temp.idx+1,temp.h+1));
            if(temp.node.left!=null) min = Math.min(min,temp.idx-1);
            if(temp.node.right!=null) max = Math.max(max,temp.idx+1);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while(min<=max)
        {
            ans.add(map[min++]);
        }
        return ans;
    }

    // DIAGONAL ORDER TRAVERSAL OF A BINARY TREES
    public static class diagonalPair{
        TreeNode node = null;
        int idx = 0;
        diagonalPair(){
        }
        diagonalPair(TreeNode node , int idx)
        {
            this.node = node;
            this.idx = idx;
        }
    }

    // diagonal traversal in -> direction
    // diagonal traversal in -> direction
    // On GFG it is giving error but it seems to be correct
    public static ArrayList<Integer> diagonal(TreeNode root)
    {
        LinkedList<diagonalPair> que = new LinkedList<>();
        que.addLast(new diagonalPair(root, 0));
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        int minHL = 0;
        int maxHL = 0;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                diagonalPair rp = que.removeFirst();

                map.putIfAbsent(rp.idx, new ArrayList<>());
                // if (!map.containsKey(rp.hl))
                // map.put(rp.hl, new ArrayList<>());

                map.get(rp.idx).add(rp.node.val);

                minHL = Math.min(minHL, rp.idx);
                maxHL = Math.max(maxHL, rp.idx);

                if (rp.node.left != null)
                    que.addLast(new diagonalPair(rp.node.left, rp.idx - 1));

                if (rp.node.right != null)
                    que.addLast(new diagonalPair(rp.node.right, rp.idx));
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (maxHL >= minHL) {
            for(int ele : map.get(maxHL)) ans.add(ele);
            maxHL--;
        }

        return ans;
    }

    // leetcode 987 Approach 1 using two priority queue
    // passed on LEETCODE NOT PASSING ON GFG
    public static class verticalPair4
    {
        TreeNode node = null;
        int idx = 0;
        verticalPair4(){
        }
        verticalPair4(TreeNode node , int idx)
        {
            this.node = node;
            this.idx = idx;
        }
    }
    public static List<List<Integer>> verticalTraversal_1(TreeNode root)
    {
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        PriorityQueue<verticalPair4> queue1 = new PriorityQueue<>((a,b)->{
            return a.node.val-b.node.val;
        });
        PriorityQueue<verticalPair4> queue2 = new PriorityQueue<>((a,b)->{
            return a.node.val-b.node.val;
        });
        int min = 0;
        int max = 0;
        queue1.add(new verticalPair4(root,0));
        while(queue1.size()!=0)
        {
            verticalPair4 temp = queue1.remove();
            if(!map.containsKey(temp.idx))
            {
                map.put(temp.idx,new ArrayList<>());
            }
            map.get(temp.idx).add(temp.node.val);
            
            if(temp.node.left != null){
                queue2.add(new verticalPair4(temp.node.left,temp.idx-1));
                min = Math.min(min,temp.idx-1);
            }
            if(temp.node.right != null){
                queue2.add(new verticalPair4(temp.node.right,temp.idx+1));
                max = Math.max(max,temp.idx+1);
            }
            if(queue1.size() == 0)
            {
                PriorityQueue<verticalPair4> queue = queue1;
                queue1 = queue2;
                queue2 = queue;
            }
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        while(min<=max)
        {
            ans.add(map.get(min++));
        }

        return ans;
    }

    //LEETCODE 987 APPROACH 2 USING ONE PRIORITYQUEUE 
    // PASSED ON LEETCODE NOT PASSING ON GFG
    public static class verticalPair5
    {
        TreeNode node = null;
        int x = 0;
        int y = 0;
        verticalPair5(){  
        }
        verticalPair5(TreeNode node , int x , int y)
        {
            this.node = node;
            this.x = x; //width
            this.y = y; //height
        }
    }

    public static List<List<Integer>> verticalTraversal_2(TreeNode root)
    {
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        PriorityQueue<verticalPair5> queue = new PriorityQueue<>((a,b)->{
            if(a.y!=b.y) return a.y-b.y;// IF HEIGHT IS NOT SAME THEN RETURN THE SAMLLER HEIGHT ELEMENT COZ WE ARE USING SINGLE PRIORITY QUEUE SO ELEMENTS PRESENT AT DIFFERENT HEIGHT CAN BE THERE IN THE QUEUE
            else return a.node.val - b.node.val;// IF HEIGHT IS SAME RETURN THE SMALLER ONE
        });
        int min = 0;
        int max = 0;
        queue.add(new verticalPair5(root,0,0));
        
        while(queue.size()!=0)
        {
            verticalPair5 temp = queue.remove();
            if(!map.containsKey(temp.x))
            {
                map.put(temp.x,new ArrayList<>());
            }
            map.get(temp.x).add(temp.node.val);

            if(temp.node.left != null)
            {
                queue.add(new verticalPair5(temp.node.left,temp.x-1,temp.y+1));
                min = Math.min(min,temp.x-1);
            }
            if(temp.node.right != null)
            {
                queue.add(new verticalPair5(temp.node.right,temp.x+1,temp.y+1));
                max = Math.max(max,temp.x+1);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();

        while(min <= max)
        {
            ans.add(map.get(min++));
        }
        return ans;
    }
    public static TreeNode findRightMost(TreeNode curr , TreeNode root)
    {
        if(curr == null) return null;
        while(curr.right!=null && curr.right != root)
        {
            curr = curr.right;
        }
        return curr;
    }
    // Morris Inorder Traversal
    // Important TestCase -> [2,3,null,1]
    public static List<Integer> MorrisInorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        while(root!=null)
        {
            TreeNode check = findRightMost(root.left,root);
            if(root.left == null)
            {
                list.add(root.val);
                root = root.right;
            }
            else if(check.right == root)
            { 
                list.add(root.val);
                root = root.right;
                check.right = null;
            }
            else
            {
                TreeNode rightMost = findRightMost(root.left,root);
                if(rightMost!=null) rightMost.right = root;
                root = root.left;
            }
        }
        return list;
    }

    // Morris Preorder Traversal
    public static List<Integer> MorrisPreorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        while(root!=null)
        {
            TreeNode check = findRightMost(root.left,root);
            if(root.left == null)
            {
                list.add(root.val);
                root = root.right;
            }
            else if(check.right == root)
            { 
                //list.add(root.val);
                root = root.right;
                check.right = null;
            }
            else
            {
                list.add(root.val);
                TreeNode rightMost = findRightMost(root.left,root);
                if(rightMost!=null) rightMost.right = root;
                root = root.left;
            }
        }
        return list;
    }

    // PRE POST IN ORDER TRAVERSALS USING ONE STACK (NEW TECHNIQUE)
    public static class pair
    {
        TreeNode node = null;
        boolean left = false;
        boolean right = false;
        boolean self = false;
        pair(){

        }
        pair(TreeNode node)
        {
            this.node = node;
        }
    }

    // Inorder Traversal
    // by changing the positions of if else we can do this for pre and post order
    public static List<Integer> inorderTraversal(TreeNode root)
    {
        List<Integer> ans = new ArrayList<>();
        LinkedList<pair> stack = new LinkedList<>();
        stack.addLast(new pair(root));
        while(stack.size()!=0 && root!=null)
        {
            pair temp = stack.getLast();
            if(temp.left == false)
            {
                temp.left = true;
                if(temp.node.left != null) stack.addLast(new pair(temp.node.left));
            }
            else if(temp.self == false)
            {
                temp.self = true;
                ans.add(temp.node.val);
            }
            else if(temp.right == false)
            {
                temp.right = true;
                if(temp.node.right != null) stack.addLast(new pair(temp.node.right));
            }
            else stack.removeLast();
        }
        return ans;
    }

    
}
