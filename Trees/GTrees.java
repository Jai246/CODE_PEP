import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GTrees
{
    public static class TreeNode
    {
        int val = 0;
        ArrayList<TreeNode> child;
        TreeNode(int val)
        {
            this.val = val;
        }
    }

    public static int size(TreeNode root)
    {
        int size = 0;
        for(TreeNode node : root.child)
        {
            size += size(node);
        }
        return size+1;
    }

    public static int height(TreeNode root)
    {
        int height = -1;
        for(TreeNode node : root.child)
        {
            height = Math.max(height , height(node));
        }
        return height + 1;
    }

    public static int maximumEle(TreeNode root)
    {
        int ele = root.val;
        for(TreeNode node : root.child)
        {
            ele = Math.max(ele , maximumEle(node));
        }
        return ele;
    }

    public static boolean find(TreeNode root , int data)
    {
        if(root.val == data) return true;
        boolean res = false;
        for(TreeNode node : root.child)
        {
            res = res || find(node,data);
        }
        return res;

    }

    public static boolean rootToNodePath(TreeNode root , int data , ArrayList<Integer> ans)
    {
        if(root.val == data)
        {
            ans.add(root.val);
            return true;
        }
        boolean res = false;
        for(TreeNode node : root.child)
        {
            res = res || rootToNodePath(node, data, ans);
        }
        if(res) ans.add(root.val);
        return res;
    }

    public static ArrayList<Integer> rootToNodePath_2(TreeNode root , int data)
    {
        if(root.val == data)
        {
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(root.val);
            return ans;
        }
        for(TreeNode node : root.child)
        {
            ArrayList<Integer> res = rootToNodePath_2(node, data);
            if(res.size() > 0)
            {
                res.add(root.val);
                return res;
            } 
        }
        return new ArrayList<>();
    }

    public static ArrayList<TreeNode> rootToNodePath_2_listOfNode(TreeNode root , TreeNode target)
    {
        if(root == target)
        {
            ArrayList<TreeNode> ans = new ArrayList<>();
            ans.add(root);
            return ans;
        }
        for(TreeNode node : root.child)
        {
            ArrayList<TreeNode> res = rootToNodePath_2_listOfNode(node, target);
            if(res.size() > 0)
            {
                res.add(root);
                return res;
            } 
        }
        return new ArrayList<>();
    }

    public static int LCA(TreeNode root , int data1 , int data2)
    {
        ArrayList<Integer> d1 = rootToNodePath_2(root, data1);
        ArrayList<Integer> d2 = rootToNodePath_2(root, data2);

        int i = d1.size()-1;
        int j = d2.size()-1;

        while(j >= 0 && i>=0)
        {
            if(d1.get(i) == d1.get(j))
            {
                i--;
                j--;
            }
            else break;
        }
        return d1.get(i+1);
    }

    public static void Kdown(TreeNode root , int k ,TreeNode blockNode ,  ArrayList<ArrayList<Integer>> ans)
    {
        if(root == blockNode) return;
        if(k == ans.size())
        {
            ans.add(new ArrayList<>());
        }
        ans.get(k).add(root.val);
        for(TreeNode node : root.child)
        {
            Kdown(node,k+1,blockNode ,ans);
        }
        return;
    }

    public static void burnigTreeGeneric_1(TreeNode root , TreeNode target)
    {
        ArrayList<TreeNode> rtnp = rootToNodePath_2_listOfNode(root, target);
        TreeNode blockNode = null;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = 0;i<rtnp.size();i++)
        {
            Kdown(rtnp.get(i), 0, blockNode, ans);
            blockNode = rtnp.get(i);
        }
    }
    // In this the time would be ans.size()
    // But for more clearity regarding time check next solution
    public static TreeNode burnigTreeGeneric_2(TreeNode root , TreeNode target , ArrayList<ArrayList<Integer>> ans)
    {   if(root == target)
        {
            Kdown(root, 0, null, ans);
            return root;
        }
        TreeNode temp = null;
        for(TreeNode node : root.child)
        {
            temp = burnigTreeGeneric_2(node, target , ans);
            if(temp!=null) break;
        }
        if(temp!=null) 
        {
            Kdown(root, 0, temp, ans);
            temp = root;
        }
        return temp;
    }

    public static int burningTree_02(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans) {
        if (root.val == target) {
            Kdown(root, 0, null, ans);
            return 1;
        }

        int time = -1;
        TreeNode blockNode = null;
        for (TreeNode child : root.child) {
            time = burningTree_02(child, target, ans);
            if (time != -1) {
                blockNode = child;
                break;
            }
        }

        if (time != -1) {
            Kdown(root,time , blockNode, ans);
            time++;
        }
        return time;
    }

    public static ArrayList<Integer> lineWiseLevelOrderTraversal(TreeNode root)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size()!=0)
        {
            TreeNode temp = queue.removeFirst();
            ans.add(temp.val);
            for(TreeNode node : temp.child)
            {
                queue.addLast(node);
            }
        }
        return ans;
    }

    public static boolean isMirror(TreeNode root1 , TreeNode root2)
    {
        if(root1.child.size()!=root2.child.size()) return false;
        if(root1.val!=root2.val) return false;

        for(int i = 0,j = root2.child.size()-1;i<root1.child.size() && j>=0;i++,j--)
        {
            TreeNode node1 = root1.child.get(i);
            TreeNode node2 = root2.child.get(j);

            if(!isMirror(node1, node2)) return false;
        }
        return true;
    }

    // flatten in preorder
    public static TreeNode flatten(TreeNode root)
    {
        if(root.child.size()==0) return root;

        int n = root.child.size();
        TreeNode tail =  flatten(root.child.get(n-1));

        for(int i = n-2;i>=0;i--)
        {
            TreeNode temp2 =  flatten(root.child.get(i));
            temp2.child.add(root.child.get(i+1));
            root.child.remove(i+1);
        }
        return tail;
    }

    

}