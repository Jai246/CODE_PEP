class ll
{
      public static class ListNode
      {
          int val = 0;
          ListNode next = null;
          ListNode(int val)
          {
            val = this.val;
          }
      }

    // WHEN WE WANT TO CHOOSE THE SECOND MIDDLE NODE OF THE TWO MIDDLE NODES
    public ListNode middleNode(ListNode head) 
    {
        if(head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // WHEN WE WANT TO CHOOSE THE FIRST MIDDLE NODE OF THE TWO MIDDLE NODES
    public ListNode middleNode_(ListNode head) 
    {
        if(head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // REVERSE A LINKED LIST
    public ListNode reverseList(ListNode head) 
    {
        if(head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr!=null)
        {
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }

    // CHECK IF PALINDROME
    // WITH SAME STRATEGY WE CAN DO THE DATA REVERSE CODE
    public boolean isPalindrome(ListNode head) 
    {
        if(head == null && head.next == null) return true;
        ListNode mid = middleNode_(head);
        ListNode head2 = reverseList(mid.next);
        mid.next = null;
        while(head2!=null)
        {
            if(head.val!=head2.val) return false;
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    // REORDER LINKEDLIST
    public void reorderList(ListNode head) 
    {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode c1 = head;
        ListNode temp = middleNode_(head);
        ListNode c2 = reverseList(temp.next);
        temp.next = null;
        ListNode f1 = null;
        ListNode f2 = null;

        while(c2!=null && c1!=null)
        {
            f1 = c1.next;
            f2 = c2.next;
            c1.next = c2;
            c1 = c1.next;
            c2 = f1;
        }
    }

    // MERGE K SORTED LIST
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) 
    {
        if(l1 == null && l2 == null) return null;
        if(l1 == null || l2 == null)
        {
            return (l1 == null) ? l2 : l1;
        }
        ListNode ans = null;
        ListNode c1 = l1;
        ListNode c2 = l2;
        
        while(c1!=null && c2!= null)
        {
            ListNode f1 = c1.next;
            ListNode f2 = c2.next;
            if(c1.val <= c2.val)
            {
                if(ans == null) ans = c1;
                while(f1!=null && f1.val < c2.val)
                {
                    c1 = f1;
                    f1 = c1.next;
                }
                c1.next = c2;
                c1 = c1.next;
                c2 = f1;
            }
            else if(c2.val < c1.val)
            {
                ListNode temp = c1;
                c1 = c2;
                c2 = temp;
            }
        }
        return ans;
    }

    // MERGE K SORTED LIST WITH DUMMYNODE
    public ListNode mergeTwoLists_(ListNode l1, ListNode l2) 
    {
        if(l1 == null && l2 == null) return null;
        if(l1 == null || l2 == null)
        {
            return (l1 == null) ? l2 : l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode c1 = l1;
        ListNode c2 = l2;
        while(c1!=null && c2!=null)
        {
            if(c1.val <= c2.val)
            {
                prev.next = c1;
                c1 = c1.next;
            }
            else
            {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }
        
        if(c1 == null) prev.next = c2;
        else prev.next = c1;
        return dummy.next;
    }

    // SORT LIST USING MERGESORT
    public ListNode sortList(ListNode head) 
    {
        if(head == null || head.next == null) return head;
        ListNode mid = middleNode(head);
        ListNode nHead = mid.next;
        return mergeTwoLists(sortList(head),sortList(nHead));               
    }

    // MERGE K LISTS
    public ListNode mergeKLists(ListNode[] lists) 
    {
        if(lists.length == 0) return null;
        
        ListNode refList = null;
        for(int i = 0;i<lists.length;i++)
        {
            refList = mergeTwoLists_(refList,lists[i]);
        }
        
        return refList;
    }

    // DETECT CYCLE IN JAVA
    public boolean hasCycle(ListNode head) 
    {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        
        return false;
    }

    // LINKEDLIST CYCLE 2 FIND THE REFRENCE NODE
    public ListNode detectCycle(ListNode head) 
    {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        boolean res = false;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
            {
                res = true;
                break;
            }
        }
        if(res == false) return null;
        slow = head;
        while(slow!=fast)
        {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }

    // LEETCODE GET INTERSECTION NODE
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) 
    {
        if(headA==null || headB == null) return null;
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        int l1 = length(headA);
        int l2 = length(headB);
        
        int l = l1>=l2?l1-l2 : l2-l1;
        while(l>0)
        {
            if(l1>l2) temp1 = temp1.next;
            else temp2 = temp2.next;
            l--;
        }
        while(temp1!=temp2)
        {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        
        return temp1;
        
    }
    public int length(ListNode head)
    {
        int len = 0;
        ListNode temp = head;
        while(temp!=null)
        {
            len++;
            temp = temp.next;
        }
        return len;
    }

    // GET INTERSECTION NODE METHODE 2 USING FLOYDS CYCLE FINDING ALGORITHM (FINDING THE REFRENCE POINT)
    public ListNode getIntersectionNode_(ListNode headA, ListNode headB) 
    {
        ListNode temp = headA;
        while(temp.next!=null)
        {
            temp = temp.next;
        }
        temp.next = headB;
        ListNode ans = detectCycle(headA);
        temp.next = null;
        return ans;
    }

    // REVERSE IN GROUP OF K IMPORTANT QUESTION
    public ListNode[] reverse(ListNode node , int k)
    {
        ListNode curr = node;
        ListNode prev = null;
        if(length(node)<k) return new ListNode[]{node,node,node};
        ListNode temp = null;
        while(k-- > 0)
        {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            
        }
        return new ListNode[]{node,temp,prev};
    }
    public ListNode reverseKGroup(ListNode head, int k) 
    {
        if(k == 1 || head == null) return head;
        ListNode ret = null;
        ListNode c1 = new ListNode(-1);
        c1.next = head;
        while(c1.next!=null)
        {
            ListNode[] ans = reverse(c1.next,k);
            if(ret == null) ret = ans[2];
            if(ans[0] == ans[1] && ans[1] == ans[2])
            {
                c1.next = ans[1];
                break;
            }
            c1.next = ans[2];
            c1 = ans[0];
            c1.next = ans[1];
        }
        return ret;
    }

    public static ListNode tHead = null;
    public static ListNode tTail = null;
    
    //REVERSE IN GROUP OF K APPROACH 2 REVOLUTIONARY APPROACH CHECK ITS VIDEO
    
    public static void addFirst(ListNode head)
    {
        if(tHead == null){
            tHead = head;
            tTail = head;
        }
        else{
            head.next = tHead;
            tHead = head;
        }
    }
    public ListNode reverseKGroup2(ListNode head, int k)
    {
        ListNode oHead = new ListNode(-1);
        ListNode oTail = oHead;
        int tempk = k;
        ListNode curr = head;
        int length = length(head);
        while(length>0)
        {
            if(length < k)
            {
                oTail.next = curr;
                length = 0;
                break;
            }
            while(length>0 && tempk-- > 0)
            {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
                length--;
            }
            tempk = k;
            oTail.next = tHead;
            oTail = tTail;
            tTail = null;
            tHead = null;
        }
        return oHead.next;
    }

    // REVERSE LINKEDLIST 2
    public ListNode reverseBetween(ListNode head, int left, int right) 
    {
        if(head == null || left == right || head.next == null) return head;
        ListNode curr = head;
        ListNode prev = new ListNode(-1);
        prev.next = head;
        head = prev;
        ListNode forw = null;
        int k = 1;
        while(curr!=null)
        {
            while(k>= left && k<= right)
            {
                if(prev.next!=null) prev.next = null;
                forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
                k++;
            }
            if(k > right)
            {
                prev.next = tHead;
                tTail.next = forw;
                break;
            }
            prev = prev.next;
            curr = curr.next;
            k++;
        }
        return head.next;
    }

}
