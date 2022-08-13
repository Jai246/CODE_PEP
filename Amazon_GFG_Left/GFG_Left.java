import java.util.*;
class GFG_Left
{
	// Circular Tour GFG
    // Important Problem
    // Check Tech Dose Video If Problem
    int tour(int petrol[], int distance[])
    {
        int deficit=0, start=0,cal=0;
        for(int i=0;i<petrol.length;i++)
        {
            cal+=petrol[i]-distance[i];
            if(cal<0)
            {
                start=i+1;
                deficit+=cal;
                cal=0;
            }
        }
        return (cal+deficit)>0 ? start :-1;
    }

    // 962. Maximum Width Ramp
    // updated Code

    public int maxWidthRamp(int[] nums) 
    {
        int N  = nums.length;
        int[] lrMin = new int[N];
        int[] rlMax = new int[N];
        
        lrMin[0] = nums[0];
        rlMax[N-1] = nums[N-1];
        
        for(int i = 1;i<N;i++)
        {
            lrMin[i] = Math.min(lrMin[i-1],nums[i]);
        }
        
        for(int i = N-2;i>=0;i--)
        {
            rlMax[i] = Math.max(rlMax[i+1],nums[i]);
        }
        
        int u = 0;
        int v = 0;
        
        int len = 0;
        while(u < N && v < N)
        {
            if(lrMin[u] <= rlMax[v])
            {
                v++;
            }
            else u++;
            len = Math.max(len,v-u-1);
        }
        return len;
    }


    // Nuts and Bolts Problem
    // Simple Sorting

    void matchPairs(char nuts[], char bolts[], int n) 
    {
        Arrays.sort(nuts);
        Arrays.sort(bolts);
    }

    // Count Occurences of Anagrams

    int search(String pat, String txt) 
    {
        int count = 0;
        HashMap<Character,Integer> pMap = new HashMap<>();
        
        for(int i = 0;i<pat.length();i++)
        {
            char ch = pat.charAt(i);
            pMap.put(ch,pMap.getOrDefault(ch,0)+1);
        }
        
        HashMap<Character,Integer> tMap = new HashMap<>();
        
        int len = pat.length();
        
        int i = 0;
        int j = 0;
        int k = 0;
        while(j < txt.length())
        {
            char ch = txt.charAt(j);
            
            tMap.put(ch,tMap.getOrDefault(ch,0)+1);
            k++;
            
            if(k == len)
            {
                if(tMap.equals(pMap)) count++;
                char ch1 = txt.charAt(i);
                k--;
                tMap.put(ch1,tMap.getOrDefault(ch1,0)-1);
                if(tMap.get(ch1) == 0) tMap.remove(ch1);
                i++;
            }
            j++;
        }
        return count;
    }

    // Restrictive Candy Crush	

    public static class pair
    {
        char ch;
        int c;
        
        pair(){
            
        }
        pair(char ch , int c)
        {
            this.c = c;
            this.ch = ch;
        }
    }
    public static String reduced_String(int k, String s)
    {
        if(k == 1) return "";
        StringBuilder sb = new StringBuilder();
        Stack<pair> st = new Stack<>();
        
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(st.size() == 0)
            {
                st.push(new pair(ch,1));
            }
            else if(st.peek().ch == ch)
            {
                st.peek().c++;
                if(st.peek().c == k) st.pop();
            }
            else
            {
                st.push(new pair(ch,1));
            }
        }
        
        while(st.size() > 0)
        {
            pair t = st.pop();
            char c = t.ch;
            int n = t.c;
            
            while(n-- > 0)
            {
                sb.append(c);
            }
        }
        
        sb.reverse();
        return sb.toString();
    }


    // Polynomial Addition
    // Simple Solution

    public static Node addPolynomial(Node p1,Node p2)
    {
         Node temp = new Node(-1,-1);
         Node head = temp;
         while(p1!=null && p2!=null)
         {
             if(p1.pow == p2.pow)
             {
                 temp.next = new Node((p1.coeff+p2.coeff),p1.pow);
                 p1 = p1.next;
                 p2 = p2.next;
                 temp = temp.next;
             }
             else if(p1.pow>p2.pow){
                 temp.next = new Node(p1.coeff,p1.pow);
                 p1 = p1.next;
                 temp = temp.next;
             }
             else{
                 temp.next = new Node(p2.coeff,p2.pow);
                 p2 = p2.next;
                 temp = temp.next;
             }
         }
         while(p1!=null){
             temp.next =  new Node(p1.coeff,p1.pow);
             temp = temp.next;
             p1 = p1.next;
         }
         while(p2!=null){
             temp.next =  new Node(p2.coeff,p2.pow);
             temp = temp.next;
             p2 = p2.next;
         }
         return head.next;
    }


    // Extreme nodes in alternate order
    // Simple Level Order Traversal Solution

    public ArrayList<Integer> ExtremeNode(Node root)
    {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) 
        {
            int size = q.size();
            if (level % 2 == 0) res.add(q.peekLast().data);
            else res.add(q.peekFirst().data);
            for (int i = 0; i < size; i++) 
            {
                Node p = q.poll();
                if (p.left != null) q.offer(p.left);
                if (p.right != null) q.offer(p.right);
            }
            level++;
        }
        return res;
    }

    // Jumping Numbers
    // Same As numbers with K consecutive Difference


    // Farthest number
    public static int findMin(int i , int[] arr , int val)
    {
        int j = arr.length-1;
        int res = -1;
        while(i<=j)
        {
            int mid = (i+j)/2;
            
            if(arr[mid] < val)
            {
                res = mid;
                i = mid+1;
            }
            else
            {
                j = mid-1;
            }
        }
        return res;
    }
    static int[] farNumber(int N, int Arr[])
	{    
        int[] ans = new int[N];
        int[]min = new int[N];
        min[N-1] = Arr[N-1];
        for(int i = N-2;i>=0;i--) min[i] = Math.min(Arr[i],min[i+1]);
        Arrays.fill(ans,-1);
        
        for(int i = 0;i<N;i++)
        {
            ans[i] = findMin(i+1,min,Arr[i]);
        }
        return ans;
	}


	// Let's Play!!!

	public static void rotateRight(int m , int[] arr1 , int[]arr2 , int x)
    {
        x = x%m;
        for(int i = 0;i<m;i++)
        {
            int val = arr1[i];
            int idx = (i+x)%m;
            arr2[idx] = val;
        }
    }
    
    public static void rotateLeft(int m , int[] arr1 , int[]arr2 , int x)
    {
        x = x%m;
        for(int i = 0;i<m;i++)
        {
            int val = arr1[i];
            int idx = ((i-x)%m + m)%m;
            arr2[idx] = val;
        }
    }
    static int isSuperSimilar(int n, int m, int mat[][], int x)
    {
        int[][] nMat = new int[n][m];
        
        for(int i = 0;i<n;i++)
        {
            if(i%2 == 0)
            {
                rotateRight(m,mat[i],nMat[i],x);
            }
            else
            {
                rotateLeft(m,mat[i],nMat[i],x);
            }
        }
        
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
            {
                if(mat[i][j]!=nMat[i][j]) return 0;
            }
        }
        return 1;
    }


    // Next Smallest Palindrome

    // 9
    // 999

    Vector<Integer> generateNextPalindrome(int arr[], int n) 
    {
        int[] copy = new int[n];
        for(int i = 0;i<n;i++) copy[i] = arr[i];
        int i = 0;
        int j = n-1;
        int carry = 0;
        while(i<j)
        {
            arr[j] = arr[i];
            i++;
            j--;
        }
        if(j<=i)
        {
            i--;
            j++;
        }
        
        boolean greater = false;
        
        int k = 0;
        while(k<n)
        {
            if(arr[k] == copy[k])
            {
                k++;
            }
            else if(arr[k] > copy[k])
            {
                greater = true;
                break;
            }
            else
            {
                break;
            }
        }
        
        if(greater) // This is important to check because there might be a case that already we got a larger number then original
        {
             Vector<Integer> ans = new Vector<>();
             for(int ele : arr) ans.add(ele);
             return ans;
        }
        
        if(j-i == 2 && arr[i+1] < 9) 
        {
            arr[i+1]++;
        }
        else if(j-i == 1 && arr[i] < 9)
        {
            arr[i]++;
            arr[j]++;
        }
        else
        {
            
            if(j-i == 2) arr[i+1] = 0;
            else if(j-i == 1)
            {
                arr[i] = 0;
                arr[j] = 0;
                i--;
                j++;
            }
            carry = 1;
            while(carry == 1 && i >=0)
            {
                int sum = arr[i] + 1;
                int val = sum%10;
                carry = sum/10;
                arr[i] = val;
                arr[j] = val;
                i--;
                j++;
            }
        }
        
        Vector<Integer> ans = new Vector<>();
        
        if(carry == 1)
        {
            ans.add(1);
            for(int b = 0;b<n-1;b++) ans.add(0);
            ans.add(1);
        }
        else
        {
            for(int ele : arr) ans.add(ele);
        }
        
        return ans;
    }


    // Minimum No Of jumps
    // jump Game 2
    public int jump(int[] A) 
    {
        int n = A.length;
        if (n <= 1) return 0;
        int currMaxReach = A[0];
        int stepsCount = A[0];
        int jump = 0;

        for (int start = 1; start < n - 1; start++)
        {
            currMaxReach = max(currMaxReach, start + A[start]);
            stepsCount = stepsCount - 1;

            if (stepsCount == 0)
            {
                jump = jump + 1;
                stepsCount = currMaxReach - start; //number of nodes in next level
            }
        }
        return jump + 1;
    }
}
