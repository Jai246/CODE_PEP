public class LinkedList 
{
    // Intersection of two sorted Linked lists 
    public static Node findIntersection(Node head1, Node head2)
    {
        Node par = new Node(-1);
        Node last = par;
        
        Node t1 = head1;
        Node t2 = head2;
        
        while(t1!=null && t2!=null)
        {
            if(t1.data == t2.data)
            {
                last.next = new Node(t1.data);
                last = last.next;
                t1 = t1.next;
                t2 = t2.next;
            }
            else if(t1.data < t2.data) t1 = t1.next;
            else t2 = t2.next;
        }
        return par.next;
    }

    // Merge Sort for Linked List 

    public static int length(Node head)
    {
        int count = 0;
        while(head!=null)
        {
            count++;
            head = head.next;
        }
        return count;
    }
    
    public static Node mergeTwoLists(Node l1,Node l2)
    {
        Node dummy = new Node(-1);
        Node prev = dummy;
        
        while(l1 != null && l2 != null)
        {
            int v1 = l1.data;
            int v2 = l2.data;
            
            if(v1 <= v2)
            {
                prev.next = l1;
                prev = prev.next;
                l1 = l1.next;
            }
            else 
            {
                prev.next = l2;
                prev = prev.next;
                l2 = l2.next;
            }
        }
        
        if(l1 != null) prev.next = l1;
        else if(l2!=null) prev.next = l2;
        return dummy.next;
    }
    public static Node sort(Node head)
    {
        int len = length(head);
        if(len == 1)
        {
            return head;
        }
        int mid = (len+1)/2;
        
        Node head2 = head;
        Node prev = null;
        int count  = 0;
        while(count < mid)
        {
            prev = head2;
            head2 = head2.next;
            count++;
        }
        prev.next = null;
        
        return mergeTwoLists(sort(head),sort(head2));
        
    }
    public static Node mergeSort(Node head)
    {
        return sort(head);
    }


    // Quick Sort on Linked List 
    // In this QuickSort What we studied for Array QuickSort from pepcoding 
    // will Not work here because its a singly linked list and we can't move 
    // backwards so in order to acomplish quick sort here we have to make pivot
    // the starting element and not the ending


    public static Node partition(Node node)
    {
        Node t = node.next;
        Node pivot = node;
        Node start = node;
        
        while(t!=null)
        {
            if(t.data<pivot.data)
            {
                start = start.next;
                int tmp = t.data;
                t.data = start.data;
                start.data = tmp;
            }
            t=t.next;
        }
        int tmp = start.data;
        start.data = pivot.data;
        pivot.data = tmp;
        
        return start;
    }
    public static Node quickSort(Node node)
    {
        if(node==null || node.next==null) return node;
        
        Node part = partition(node);
        Node tmp = part.next;
        part.next = null;
        quickSort(node);
        quickSort(tmp);
        part.next = tmp;
        return node;
    }


    // Finding middle element in a linked list 
    // Hare And Tortoise Approach

    int getMiddle(Node head)
    {
         Node slow = head;
         Node fast = head;
         
         while(fast!=null && fast.next != null)
         {
             slow = slow.next;
             fast = fast.next.next;
         }
         return slow.data;
    }


    // Split a Circular Linked List into two halves 
    // Not able to submit on GFG due to some driver code error
    // When corrected do check this code

    void splitList(circular_LinkedList list)
    {
        circular_LinkedList temp = list;
        circular_LinkedList slow = list;
        circular_LinkedList fast = list;
        circular_LinkedList prev = list;
        boolean start = false;
        while(fast!=temp && fast.next!=temp)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(start == false)
            {
                start = true;
                prev = prev.next;
            }
            else prev = prev.next.next;
        }
        circular_LinkedList head2 = slow.next;
        prev.next = slow.next;
        slow.next = temp;
        
        boolean check = false;
        
        while(list!=list && !check)
        {
            if(!check) check = true;
            if(list.next == list) System.out.print(list.data);
            else System.out.print(list.data + " ");
            list = list.next;
        }
        
        System.out.println();
        
        check = false;
        
        while(head2!=head2 && !check)
        {
            if(!check) check = true;
            if(head2.next == head1) System.out.print(head2.data);
            else System.out.print(head2.data + " ");
            head2 = head2.next;
        }
        
    }

    // Given a linked list of 0s, 1s and 2s, sort it. 
    static Node segregate(Node head)
    {
        Node dummy1 = new Node(-1);
        Node l1 = dummy1;
        Node dummy2 = new Node(-1);
        Node l2 = dummy2;
        
        
        Node d = new Node(-1);
        d.next = head;
        
        while(head!=null)
        {
            int val = head.data;
            Node temp = head;
            head = head.next;
            temp.next = null;
            d.next = head;
            
            if(val == 0)
            {
                l1.next = temp;
                l1 = l1.next;
            }
            if(val == 1)
            {
                if(l2.data == -1)
                {
                    temp.next = dummy2.next;
                    dummy2.next = temp;
                    l2 = l2.next;
                }
                else 
                {
                    temp.next = dummy2.next;
                    dummy2.next = temp;
                }
            }
            if(val == 2)
            {
                l2.next = temp;
                l2 = l2.next;
            }
        }
        
        
        if(dummy1.next == null) return dummy2.next;
        l1.next = dummy2.next;
        return dummy1.next;
    }


    // Delete nodes having greater value on right 
    Node reverseList(Node head)
    {
        Node prev = null;
        while(head!=null)
        {
            Node forw = head.next;
            head.next = prev;
            prev = head;
            head = forw;
        }
        return prev;
    }
    Node compute(Node head)
    {
        if(head == null) return head;
        head = reverseList(head);
        int min = -1;
        Node temp = new Node(-1);
        Node a = temp;
        while(head!=null)
        {
            if(head.data >= min)
            {
                Node forw = head.next;
                min = head.data;
                a.next = head;
                head.next = null;
                head = forw;
                a = a.next;
            }
            else
            {
                Node forw = head.next;
                head.next = null;
                head = forw;
            }
        }
        return reverseList(temp.next);
    }


    // 1019. Next Greater Node In Linked List
    // Similar to next greater element 2

    public int[] nextLargerNodes(ListNode head) 
    {
       ArrayList<Integer> a = new ArrayList<>();
       ListNode current = head;
        while(current != null)
        {
            a.add(current.val);
            current = current.next;
        }
        int res[] = new int[a.size()];
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i<a.size(); i++)
        {
            while(!s.isEmpty() && a.get(i) > a.get(s.peek())) res[s.pop()] = a.get(i);
            s.push(i);
        }
        return res;
    }


    // Delete nodes having greater value on right 
    // Please OOLTA chalna seekh loo
    // Dimag Mei daal loo
    // Bhool Jate Ho Baar Baar
    Node reverseList(Node head)
    {
        Node prev = null;
        while(head!=null)
        {
            Node forw = head.next;
            head.next = prev;
            prev = head;
            head = forw;
        }
        return prev;
    }
    Node compute(Node head)
    {
        if(head == null) return head;
        head = reverseList(head);
        int min = -1;
        Node temp = head;
        Node prev = null;
        while(head!=null)
        {
            if(head.data >= min)
            {
                min = head.data;
                prev = head;
                head = head.next;
            }
            else
            {
                Node forw = head.next;
                head.next = null;
                head = forw;
                prev.next = forw;
            }
        }
        return reverseList(temp);
    }
    
    
    /// 61. Rotate List
    // Good Question

    public int len(ListNode head)
    {
        int count = 0;
        while(head!=null) 
        {
            head = head.next;
            count++;
        }
        return count;
    }
    public ListNode reverse(ListNode head)
    {
        ListNode prev = null;
        while(head!=null)
        {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    public ListNode rotateRight(ListNode head, int k) 
    {
        if(head == null) return null;
        int len = len(head);
        if(len == 1) return head;
        if(k > len )k = k%len;
        if(k == 0) return head;
        ListNode rev = reverse(head);
        ListNode d2 = new ListNode(-1);
        ListNode d = d2;
        while(k-- > 0)
        {
            ListNode next = rev.next;
            rev.next = null;
            d.next = rev;
            d = d.next;
            rev = next;
        }
        ListNode r1 = reverse(d2.next);
        rev = reverse(rev);
        if(d2.next!=null) d2.next.next = rev;
        
        return r1;
    }

    
}
