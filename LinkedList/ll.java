import java.util.HashMap;
import java.util.List;

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
    public static ListNode reverseList(ListNode head) 
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
    public static int length(ListNode head)
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

    // LEETCODE 138 HASHMAP APPROACH FOR COPY LIST WITH RANDOM POINTER
    public static class Node{
        int val = 0;
        Node next = null;
        Node random = null;
        Node(int val)
        {
            this.val = val;
        }
    }
    public Node copyRandomList(Node head) 
    {
        if(head == null) return null;
        Node curr = head;
        HashMap<Node,Node> map = new HashMap<>();
        while(curr!=null)
        {
            if(!map.containsKey(curr))
            {
                map.put(curr,new Node(curr.val));
            }
            if(curr.next!=null)
            {
                if(!map.containsKey(curr.next))
                {
                    map.put(curr.next , new Node(curr.next.val));
                }
                map.get(curr).next = map.get(curr.next);
            }
            if(curr.random!=null)
            {
                if(!map.containsKey(curr.random))
                {
                    map.put(curr.random , new Node(curr.random.val));
                }
                map.get(curr).random = map.get(curr.random);
            }
            curr = curr.next;
        }
        return map.get(head);
    }

    
    // IMPLEMENTATION OF LRU CACHE
    // MAKE VIDEO FOR REMEMBERING THE IMPORTANT POINTS FOR LRU CACHE
    public class LRUNode
    {
        int val = 0;
        int key = 0;
        LRUNode next = null;
        LRUNode prev = null;
        LRUNode(int key , int val)
        {
            this.val = val;
            this.key = key;
        }
    }
    public HashMap<Integer , LRUNode> map = null;
    public LRUNode head = null;
    public LRUNode tail = null;
    public int Maxcapacity = 0;
    public void removeFirst()
    {
        if(head == null) return;
        else if(map.size() == 1){
            map.remove(head.key);
            head = null;
            tail = null;
        }
        else{
            LRUNode temp = head.next;
            map.remove(head.key);
            head.next = null;
            temp.prev = null;
            head = temp;
        }
    }
    public void addLast(LRUNode node)
    {
        if(head == null && tail == null)
        {
            head = node;
            tail = node;
            map.put(node.key,node);
        }
        else
        {
            map.put(node.key,node);
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        
    }
    public void reArrange(int key)
    {
        if(map.size() == 1) return;
        LRUNode node = map.get(key);
        LRUNode prev = node.prev;
        LRUNode forw = node.next;
        if(forw == null)
        {
            return;    
        }
        else if(prev == null)
        {
            node.next = null;
            forw.prev = null;
            head = forw;
            addLast(node);
        }
        else
        {
            prev.next = null;
            node.prev = null;
            forw.prev = null;
            node.next = null;
            prev.next = forw;
            forw.prev = prev;
            addLast(node);
        }
    }
    // LRUCache(int capacity) // this is the constructor for the class lru cache
    // {
    //     Maxcapacity = capacity;
    //     map = new HashMap<>(); 
    // }
    
    public int get(int key) 
    {
        if(map.containsKey(key))
        {
            reArrange(key);
            return map.get(key).val;
        }
        return -1;
    }
    
    public void put(int key, int val) 
    {
        LRUNode node = new LRUNode(key, val);
        if(map.containsKey(key))
        {
            LRUNode temp = map.get(key);
            temp.val = val;
            reArrange(key);
        }
        else
        {
            addLast(node);
            Maxcapacity--;
            if(Maxcapacity < 0)
            {
                removeFirst();
                Maxcapacity++;
            }
        }
    }

    // COPY LIST WITH RANDOM POINTER APPROACH 2 WITHOUT HASHMAP
    public static Node headP1 = null;
    public static Node headP2 = null;
    public static void  createAttachedCopy(Node head)
    {
        while(head!=null)
        {
            Node forw = head.next;
            head.next = new Node(head.val);
            head.next.next = forw;
            head = forw;
        }
    }
    public static void extractCopyNode()
    {
        Node curr1 = headP1;
        Node curr2 = headP2;
        while(curr1!=null && curr2!=null)
        {
            curr1.next = curr2.next;
            curr1 = curr1.next;
            if(curr1!=null && curr2!=null) curr2.next = curr1.next;
            curr2 = curr2.next;
        }
    }
    public Node copyRandomList_2(Node head)
    {
        if(head == null) return head;
        headP2 = head;
        headP1 = head;
        createAttachedCopy(head);
        while(head!=null)
        {
            Node random = head.random;
            if(random!=null) head.next.random = random.next;
            head = head.next.next; 
        }
        headP2 = headP2.next;
        extractCopyNode();
        Node e = headP1;
        while(e!=null)
        {
            System.out.print(e.val + " ");
            e = e.next;
        }
        return headP2;
    }
    // SEGREGATE ODD AND EVEN
    public static ListNode divide(int N,ListNode head)
    {
        ListNode dummyOdd = new ListNode(-1);
        ListNode dummyEven = new ListNode(-1);
        ListNode odd = dummyOdd;
        ListNode even = dummyEven;
        ListNode curr = head;
        while (curr != null) 
        {
            if (curr.val % 2 != 0) 
            {
                odd.next = curr;
                odd = odd.next;
            } 
            else 
            {
                even.next = curr;
                even = even.next;
            }
            curr = curr.next;
        }
        even.next = dummyOdd.next;
        odd.next = null;
        return dummyEven.next;
    }

    // UNFOLD A LINKEDLIST
    public static ListNode UnfoldLL(ListNode head)
    {
        ListNode nHead = new ListNode(-1);
        ListNode temp = head.next;
        ListNode prev = head;
        ListNode Nprev = nHead;
        while(temp!=null)
        {
            Nprev.next = temp;
            Nprev = Nprev.next;
            prev.next = temp.next;
            if(temp.next!=null) prev = temp.next;
            temp.next = null;
            temp = prev.next;
        }
        nHead = nHead.next;
        nHead = reverseList(nHead);
        prev.next = nHead;
        return head;
    }

    // SEGREGATE ODD EVEN NODES LEETCODE 328
    public ListNode oddEvenList(ListNode head) 
    {
        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);

        ListNode e = even;
        ListNode o = odd;

        while(head != null)
        {
            if(head.val%2 == 0)
            {
                ListNode forw = head.next;
                e.next = head;
                head.next = null;
                e = e.next;
                head = forw;
            }
            else
            {
                ListNode forw = head.next;
                o.next = head;
                head.next = null;
                o = o.next;
                head = forw;
            }
        }

        odd = odd.next;
        even = even.next;
        o.next = even;
        return odd;
    }

    // ADDITION OF TWO LINKEDLISTS WITHOUT EXTRA SPACE LEETCODE 
    public static ListNode  addTwoNumbers(ListNode l1 , ListNode l2)
    {
        ListNode ans = new ListNode(-1);
        ListNode a = ans;
        ListNode head1 = reverseList(l1);
        ListNode head2 = reverseList(l2);
        ListNode c1 = head1;
        ListNode c2 = head2;
        int carry = 0;
        while(c1!=null||c2!=null)
        {
            if(c1!=null && c2!=null)
            {
                int sum = carry + c1.val + c2.val;
                int val = sum % 10;
                carry = sum / 10;
                a.next = new ListNode(val);
                a = a.next;
                c1 = c1.next;
                c2 = c2.next;
            }
            else if(c1 != null && c2 == null)
            {
                int sum = c1.val + carry;
                int val = sum % 10;
                carry = sum / 10;
                a.next = new ListNode(val);
                a = a.next;
                c1 = c1.next;
            }
            else if(c1 == null && c2!=null)
            {
                int sum = c2.val + carry;
                int val = sum % 10;
                carry = sum / 10;
                a.next = new ListNode(val);
                a = a.next;
                c2 = c2.next;
            }
        }
        if(carry == 1) a.next = new ListNode(carry);
        return reverseList(ans.next);
    }

    // ADDITION OF TWO LINKED LISTS WITH EXTRA SPACE (RECURSION STACK) WITHOUT REVERSING APPROACH
    public ListNode ans = new ListNode(-1);
    public ListNode  addTwoNumbers_2(ListNode l1 , ListNode l2)
    {
        int carry = addRec(l1,length(l1),l2,length(l2));
        if(carry == 1)
        {
            ListNode temp = new ListNode(1);
            temp.next = ans;
            ans = temp;
        }
        return ans;
    }
    
    
    public int addRec(ListNode l1 ,int pos1 ,  ListNode l2 ,int pos2)
    {
        if(l1 == null && l2 == null) return 0;
        if(pos1 > pos2)
        {
            int carry = addRec(l1.next, pos1-1, l2, pos2);
            int sum = carry + l1.val;
            int val = sum%10;
            System.out.println(val);
            if(ans.val == -1)
            {
                ans = new ListNode(val);
                return sum/10;
            }
            else
            {
                ListNode temp = new ListNode(val);
                temp.next = ans;
                ans = temp;
                return sum/10;
            }
        }
        else if(pos2 > pos1)
        {
            int carry = addRec(l1, pos1, l2.next, pos2-1);
            int sum = carry + l2.val;
            int val = sum%10;
            System.out.println(val);
            if(ans.val == -1)
            {
                ans = new ListNode(val);
                return sum/10;
            }
            else
            {
                ListNode temp = new ListNode(val);
                temp.next = ans;
                ans = temp;
                return sum/10;
            }
        }
        else if(pos1 == pos2)
        {
            int carry = addRec(l1.next, pos1-1, l2.next, pos2-1);
            int sum = carry + l1.val + l2.val;
            int val = sum%10;
            System.out.println(val);
            if(ans.val == -1)
            {
                ans = new ListNode(val);
                return sum/10;
            }
            else
            {
                ListNode temp = new ListNode(val);
                temp.next = ans;
                ans = temp;
                return sum/10;
            }
        }
        return 0;
    }

    // SUBTRACT TWO LINKED LISTS NO EXTRA SPACE SUBMITTED ON PEPCODING PORTAL
    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2)
    {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode ans = new ListNode(-1);
        ListNode itr = ans;
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode c1 = l1 , c2 = l2;
        int borrow = 0;
        while(c1!=null)
        {
            int diff = borrow + c1.val - (c2!=null ? c2.val : 0);
            if(diff < 0){
                borrow = -1;
                diff+=10;
            }
            else borrow = 0;
            itr.next = new ListNode(diff);
            itr = itr.next;
            c1 = c1.next;
            if(c2!=null) c2 = c2.next;
        }
        ans =  reverseList(ans.next);
        while(ans!=null && ans.val == 0) ans = ans.next;
        return (ans == null) ? new ListNode(0) : ans;
    }
    
    public static void main(String[] args)
    {
        
    }
}