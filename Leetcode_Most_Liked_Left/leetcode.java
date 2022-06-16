class leetcode
{
    // 101. Symmetric Tree

	public boolean isSymmetric(TreeNode root) 
    {
        return isSymm(root.left,root.right);
    }
    
    public boolean isSymm(TreeNode r1 , TreeNode r2)
    {
        if(r1 == null && r2 == null) return true;
        if(r1 == null || r2 == null) return false;
        if(r1.val != r2.val) return false;
        return isSymm(r1.left,r2.right) && isSymm(r1.right,r2.left);
    }

    // 7. Reverse Integer
    public int reverse(int x) 
    {
        long xx = x;
        boolean negg = false;
        if(xx < 0){
            negg = true;
            xx*=-1;
        }
        StringBuilder sb = new StringBuilder(xx+"");
        sb.reverse();
        String a = sb.toString();
        long val = Long.parseLong(a);
        if(val < Integer.MIN_VALUE || val > Integer.MAX_VALUE) return 0;
        return (!negg) ? (int)val : -1*(int)val;
    }

    // / 739. Daily Temperatures
    // Next Greater Element
    public int[] dailyTemperatures(int[] temp) 
    {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[temp.length];
        for(int i = 0;i<temp.length;i++)
        {
            if(st.size() == 0) st.push(i);
            else 
            {
                while(st.size() > 0 && temp[st.peek()] < temp[i]) ans[st.peek()] = i-st.pop();
                st.push(i);
            }
        }
        return ans;
    }


    //24. Swap Nodes in Pairs

    public ListNode swapPairs(ListNode head) 
    {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        
        while(head!=null)
        {
            ListNode next = head.next;
            if(next == null) break;
            ListNode nextNext = next.next;
            
            head.next = nextNext;
            next.next = head;
            prev.next = next;
            
            prev = head;
            head = nextNext;
            
        }
        return dummy.next;
    }


    // 621. Task Scheduler

    // Important Question
    // Important Solution
    // We Know that in these kind of questions we first handle the greater count value
    // My Solution

    public int leastInterval(char[] tasks, int n)
    {
        if (n == 0) return tasks.length;
        n++;
        int ans = 0;
        Map<Character, Integer> taskToCount = new HashMap<>();
        for (char c : tasks) {
            taskToCount.put(c, taskToCount.getOrDefault(c, 0) + 1);
        }
        
        Queue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (char c : taskToCount.keySet()) pq.offer(taskToCount.get(c));
        
        LinkedList<Integer> rem = new LinkedList<>();
        
        while(pq.size()!=0)
        {
            int s = pq.size();
            ans += n;
            int p = n;
            while(pq.size() > 0 && p-- > 0) rem.addLast(pq.remove());

            while(rem.size() > 0)
            {
                int val = rem.removeLast()-1; 
                if(val > 0) pq.add(val);
            }

            if(pq.size() == 0) ans -= (n-s);
        }
        return ans;
    }


    // 337. House Robber III
    // Maximum sum of Non-adjacent nodes Same Questions
    // Did This Question on Geeks For Geeks

    public int rob(TreeNode root) 
    {
        int[] ans = rob_(root);
        return Math.max(ans[0] , ans[1]);
    }
    public int[] rob_(TreeNode root) 
    {
        if(root == null) return new int[2];
        int[] myAns = new int[2];
        int[] lans = rob_(root.left);
        int[] rans = rob_(root.right);
        myAns[0] = root.val + lans[1] + rans[1];
        myAns[1] = Math.max(lans[0],lans[1])+Math.max(rans[0],rans[1]);
        return myAns;
    }


    // 16. 3Sum Closest
    // Almost Same As Three Sum
    public int threeSumClosest(int[] num, int target) 
    {
        int result = (int)1e9;
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) 
        {
            int start = i + 1, end = num.length - 1;
            while (start < end) 
            {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) end--;  
                else start++; 
                if (Math.abs(sum - target) < Math.abs(result - target)) result = sum;
            }
        }
        return result;
    }


}