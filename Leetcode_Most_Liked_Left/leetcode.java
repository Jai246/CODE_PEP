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

    // 100. Same Tree

    public boolean isSameTree(TreeNode p, TreeNode q) 
    {
        if (p == null && q == null) return true;
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
    }

    // 973. K Closest Points to Origin

    public double calc(int x1 , int y1 , int x2 , int y2)
    {
        double v1 = (x2-x1)*(x2-x1);
        double v2 = (y2-y1)*(y2-y1);
        
        return Math.sqrt(v1+v2);
    }
    public int[][] kClosest(int[][] points, int k) 
    {
        Arrays.sort(points,(a,b)->{
            double v1 = calc(0,0,a[0],a[1]);
            double v2 = calc(0,0,b[0],b[1]);
            return Double.compare(v1,v2);
        });
        
        int[][] ans = new int[k][2];
        
        for(int i = 0;i<k;i++)
        {
            ans[i][0] = points[i][0];
            ans[i][1] = points[i][1];
        }
        
        return ans;
    }


    // 90. Subsets II
    // We also Need Not maintain order of elements inside the subset
    // Thats why here sorting works

    public List<List<Integer>> subsetsWithDup(int[] nums) 
    {
        // sorting our array will allow us to skip repetitions easily
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }
    
    public void backtrack(int[] a, int idx, List<Integer> curr, List<List<Integer>> res) 
    {
        res.add(List.copyOf(curr));
        if(idx == a.length) return;
        
        for(int i=idx; i<a.length; i++) 
        {
            curr.add(a[i]);
            backtrack(a, i+1, curr,res);
            curr.remove(curr.size()-1);
            
            
            while(i+1 < a.length && a[i] == a[i+1]) 
            {
                i += 1;
            }
        }
    }

    // 67. Add Binary
    // A Very Simple Solution
    // we know that 1 + 1 in binary produce carry = 1 and in re we write 0

    public String addBinary(String a, String b) 
    {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) 
        {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }


    // 203. Remove Linked List Elements
    // Its a very simple probleme
    // But I have found a very nice solution

    public ListNode removeElements(ListNode head, int val) 
    {
        if (head == null) return null;
        ListNode pointer = head;
        while (pointer.next != null)
        {
            if (pointer.next.val == val) pointer.next = pointer.next.next;
            else pointer = pointer.next;
        }
        return head.val == val ? head.next : head;
    }


    // 222. Count Complete Tree Nodes

    // Very Important Approach 
    // Very Important Approach
    // Very Important Approach

    // Har Node par jakar height nikalni hei left and right both sides
    // Agar left and right barabar hei(matlab jab left null hogaya hei) toh return kardo (1<<height)-1;
    // Kyoki voh sub tree eak complete binary tree hei
    // Kyoki No of nodes in a complete binary tree kaa formulae(i << height)-1 hota hei;
    // arag aaisa nahi hei toh left sub tree and right sub tree par call lagado aur current node ka +1 karke return kardo

    public int countNodes(TreeNode root) 
    {
        if (root == null) return 0;
        TreeNode left = root, right = root;
        int height = 0;
        while (right != null) 
        {
            left = left.left;
            right = right.right;
            height++;
        }
        if (left == null) return (1 << height) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }


    // 406. Queue Reconstruction by Height
    // Good Problem
    // Main thing is sorting and after that inserting people at the people[1] position
    // O(n2) due to insertion
    // Here Front means left to the current person

    public int[][] reconstructQueue(int[][] people) 
    {
        Arrays.sort(people,new peopleComparator());        
        List<int[]> result=new ArrayList<int[]>();
        for(int[] p:people) result.add(p[1],p);
        return result.toArray(new int[people.length][2]);
    }
    
    public class peopleComparator implements Comparator<int[]>
    {
        public int compare(int[] one,int[] two)
        {
            if(one[0]==two[0]) return one[1]-two[1];
            return two[0]-one[0];
        }
    }

    ///289. Game of Life
    // No Extra Spac Just Modifying the values
    // This is someone  else's solution and not my silution
    // There are tons of ways to solve this problem
    // You can do this by using your own convention
    // Only thing to note here is that to update a cell only previous state values are going to be considered
    // New values will not be considered for updation in new state 
    public void gameOfLife(int[][] board) 
    {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                checkAndUpdate(board, i, j);
            }
        }
        for (int i = 0; i < m; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                board[i][j] = board[i][j] >= 1 ? 1 : 0;
            }
        }
    }

    private void checkAndUpdate(int[][] board, int i, int j) 
    {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        int[][] indexes = { { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        for (int[] index : indexes) 
        {
            if (i + index[0] < 0 || i + index[0] >= m || j + index[1] < 0 || j + index[1] >= n) 
            {
                continue;
            }
            if (Math.abs(board[i + index[0]][j + index[1]]) == 1) 
            {
                count++;
            }
        }
        if (board[i][j] == 0 && count == 3) 
        {
            board[i][j] = 2;
        } 
        else if (board[i][j] == 1 && (count < 2 || count > 3)) 
        {
            board[i][j] = -1;
        }
    }



    // 424. Longest Repeating Character Replacement
    // Imp BAAA and k == 2
    // O(26.n) solution
    // O(26) solution nahi samajh aaraha
    // "longest substring where (length - max occurrence) <= k"
    // Very Important Line to solve this problem

    public int characterReplacement(String s, int k)
    {
        int i = 0;
        int j = 0;
        int length = 0;
        
        int[] map = new int[26];
        
        int freq = 0;
        char freqEle = '#';
        
        while(j < s.length())
        {
            char ch = s.charAt(j);
            ++map[ch-'A'];
            if(map[ch-'A'] > freq)
            {
                freqEle = ch;
                freq = map[ch-'A'];
            }
            int l = j-i+1;
            if(l-freq <= k) length = Math.max(length,l);
            else
            {
                while((j-i+1)-freq > k && i <= j)
                {
                    char f = s.charAt(i);
                    if(f!=freqEle)
                    {
                        map[f-'A']--;
                        i++;
                    }
                    else
                    {
                        while(f == freqEle) 
                        {
                            map[f-'A']--;
                            i++;
                            f = s.charAt(i);
                        }
                        
                        for(int b = 0;b<26;b++)
                        {
                            if(map[b] > freq)
                            {
                                freq = map[b];
                                freqEle = (char)((int)'A' + b);
                            }
                        }
                    }
                    
                }
            }
            j++;
        }
        return length;
    }

    // 77. Combinations

    public List<List<Integer>> combine(int n, int k) 
    {
        List<List<Integer>> ans = new ArrayList<>();
        combi(1,k,n,ans,new ArrayList<>());
        return ans;
    }
    
    public void combi(int i , int k , int n ,List<List<Integer>> ans , List<Integer> temp)
    {
        if(k == 0)
        {
            List<Integer> t = new  ArrayList<>();
            for(int ele : temp) t.add(ele);
            ans.add(t);
            return;
        }
        
        for(int j = i;j<=n;j++)
        {
            temp.add(j);
            combi(j+1,k-1,n,ans,temp);
            temp.remove(temp.size()-1);
        }
    }


    // 451. Sort Characters By Frequency
    // Simple Bucket Sort Solution

    // Simple Solution Using Bucket Sort
    public String frequencySort(String s) 
    {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
                        
        List<Character> [] bucket = new List[s.length() + 1];
        for (char key : map.keySet()) 
        {
            int frequency = map.get(key);
            if (bucket[frequency] == null) bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(key);
        }
                
        StringBuilder sb = new StringBuilder();
        for (int pos = bucket.length - 1; pos >= 0; pos--)
        {
            if (bucket[pos] != null)
            {
                for (char c : bucket[pos])
                {
                    for (int i = 0; i < pos; i++) sb.append(c);
                }
            }
        }

        return sb.toString();
    }


    // 137. Single Number II

    public int singleNumber(int[] nums) 
    {
        int shift = 1;
        int ans = 0;
        for(int i = 0;i<32;i++)
        {
            int count = 0;
            for(int ele : nums) 
            {
                if((shift & ele) == shift) 
                {
                    count++;
                }
            }
            if(count%3 != 0) 
            {
                // Here We Are Actually Creating the answer by collecting differrnt bits
                ans+= shift;
            }
            shift = shift * 2;
        }
        return ans;
    }


    // 334. Increasing Triplet Subsequence
    // Kaafi Dimag Wala Solution
    // Mera Nahi hei
    // Its understanding is kind of Patience sort we did for russian dolls
    public boolean increasingTriplet(int[] nums) 
    {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) 
        {
            if (n <= small) 
            { 
                small = n; 
            } // update small if n is smaller than both
            else if (n <= big) 
            { 
                big = n; 
            } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }


    //461. Hamming Distance
    public int hammingDistance(int x, int y) 
    {
        int z = 0;
        z = z^x;
        z = z^y;
        int count = 0;
        while(z > 0)
        {
            count++;
            int t = (z)&(-z);
            z-=t;
        }
        return count;
    }

    // 745. Prefix and Suffix Search
    // Using HashMap
    // for apple we are storing
    // a|apple  a|pple  a|ple  a|le  a|e
    // ap|apple  ap|pple  ap|ple  ap|le  ap|e
    // app|apple  app|pple  app|ple  app|le  app|e
    // appl|apple  appl|pple  appl|ple  appl|le  appl|e
    // apple|apple  apple|pple  apple|ple  apple|le  apple|e

    public HashMap<String,Integer> map;
    public WordFilter(String[] words) 
    {
        map = new HashMap<>();
        for(int i = 0;i<words.length;i++)
        {
            String word = words[i];
            
            for(int j = 1;j<=word.length();j++)
            {
                String p = word.substring(0,j);
                
                for(int k = 0;k<word.length();k++)
                {
                    String s = word.substring(k,word.length());
                    
                    map.put(p+"|"+s,i);
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) 
    {
        String t = prefix+"|"+suffix;
        if(!map.containsKey(t)) return -1;
        return map.get(t);
    }

    // We can do this problem using trie as well
    // That would optimize the space a lot
    // in trie instead of using | as the seperator we will use {  becfause this is closest to z ans 27 size childrean
    // array would be sufficient


    public class trieNode
    {
        int weight = 0;
        trieNode[] child; 
        boolean isEnd = false;
        trieNode(int w)
        {
            this.weight = w;
            this.child = new trieNode[27];
        }
    }
    public trieNode parent = new trieNode(-1);
    
    public WordFilter(String[] words) 
    {
        
        for(int w = 0;w<words.length;w++)
        {
            String word = words[w];
            for(int i = 1;i<=word.length();i++)
            {
                for(int j = 0;j<word.length();j++)
                {
                    String temp = word.substring(0,i) + "{" + word.substring(j,word.length());
                    trieNode root = parent;
                    
                    for(int c = 0;c<temp.length();c++)
                    {
                        char ch = temp.charAt(c);
                        if(root.child[ch-'a']==null) root.child[ch-'a'] = new trieNode(w);
                        root = root.child[ch-'a'];
                    }
                    root.isEnd = true;
                    root.weight = w;
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) 
    {
        String temp = prefix + "{" + suffix;
        trieNode root = parent;
        for(int c = 0;c<temp.length();c++)
        {
            char ch = temp.charAt(c);
            if(root.child[ch-'a'] == null) return -1;
            root = root.child[ch-'a'];
        }
        if(root.isEnd) return root.weight;
        return -1;
    }


    // 417. Pacific Atlantic Water Flow
    // Very Important Problem And Visualization
    // Check Picture in DryRun
    // Check Algorithms Made Easy video If Needed;
    // Consider there is flood from ocean
    // And find out that till what cells the water can flow
    // from both the oceans and from all the border cells
    // Very Tedious Question to think

    public void fill(int i , int j , int[][] mat , int[][] dir , int[][] fill)
    {
        fill[i][j] = 1;
        for(int[] ele : dir)
        {
            int x = i + ele[0];
            int y = j + ele[1];
            
            if(x < 0 || y < 0 || x >= mat.length || y >= mat[0].length || mat[x][y] < mat[i][j] || fill[x][y] == 1) continue;
            fill(x,y,mat,dir,fill);
        }
    }
    public List<List<Integer>> pacificAtlantic(int[][] mat) 
    {
        List<List<Integer>> ans = new ArrayList<>();
        int[][] atlantic = new int [mat.length][mat[0].length];
        int[][] pacific = new int [mat.length][mat[0].length];
        
        int[][] aDir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        
        for(int i = 0;i<mat.length;i++)
        {
            fill(i,0,mat,aDir,atlantic);
            fill(i,mat[0].length-1,mat,aDir,pacific);
        }
        for(int j = 0;j<mat[0].length;j++)
        {
            fill(0,j,mat,aDir,atlantic);
            fill(mat.length-1,j,mat,aDir,pacific);
        }
                
        for(int i = 0;i<mat.length;i++)
        {
            for(int j = 0;j<mat[0].length;j++)
            {
                if(atlantic[i][j] == 1 && pacific[i][j] == 1)
                {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    ans.add(temp);
                }
            }
        }
        return ans;
    }


    // Two City Scheduling Leetcode
    // First Approach

    class cityComp implements Comparator<int[]>
    {
        @Override
        public int compare(int[]a,int[]b)
        {
            int v1 = (a[0]-a[1]);
            int v2 = (b[0]-b[1]);
            
            return Integer.compare(v1,v2);
        }
    }
    public int twoCitySchedCost(int[][] costs) 
    {
        Arrays.sort(costs,new cityComp());
        for(int[] ele : costs) System.out.println(Arrays.toString(ele));
        int cost = 0;
        
        for(int i = 0;i<costs.length;i++)
        {
            if(i < (costs.length)/2)
            {
                cost+=costs[i][0];
            }
            else cost+=costs[i][1];
        }
        return cost;
    }


    // Second Approach

    // The problem is to send n persons to city A 
    // and n persons to city B with minimum cost.

    // The idea is to send each person to city A.
    // costs = [[10,20],[30,200],[400,50],[30,20]]

    // So, totalCost = 10 + 30 + 400 + 30 = 470

    // Now, we need to send n persons to city B.
    // Which persons do we need to send city B?

    // Here, we need to minimize the cost.
    // We have already paid money to go to city A.
    // So, Send the persons to city B who get more refund
    // so that our cost will be minimized.

    // So, maintain refunds of each person
    // refund[i] = cost[i][1] - cost[i][0]

    // So, refunds of each person
    //     refund = [10, 170, -350, -10]

    // Here, refund +ve means we need to pay
    //              -ve means we will get refund.

    // So, sort the refund array.

    // refund = [-350, -10, 10, 170]

    // Now, get refund for N persons,
    // totalCost += 470 + -350 + -10 = 110

    // So, minimum cost is 110

    public int twoCitySchedCost(int[][] costs) {
        int N = costs.length/2;
        int[] refund = new int[N * 2];
        int minCost = 0, index = 0;
        for(int[] cost : costs){
            refund[index++] = cost[1] - cost[0];
            minCost += cost[0];
        }
        Arrays.sort(refund);
        for(int i = 0; i < N; i++){
            minCost += refund[i];
        }
        return minCost;
    }

    // 907. Sum of Subarray Minimums
    // Very Important Solution
    // Stack is used to get the Minimum value index then current value
    // Check Picture In Dryrun


    public int sumSubarrayMins(int[] arr) 
    {
        int mod = (int)1e9+7;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        
        int[] dp = new int[arr.length];
        int sum = 0;
        for(int i = 0;i<arr.length;i++)
        {
            int val = arr[i];
            while(st.peek()!=-1 && arr[st.peek()] >= val) st.pop();
            
            if(st.peek() == -1) dp[i] = (val*(i-st.peek()))%mod;
            else dp[i] = (((i-st.peek())*val)%mod + dp[st.peek()])%mod;
            
            sum = (sum + dp[i])%mod;
            st.push(i);
        }
        return sum%mod;
    }

    // 665. Non-decreasing Array
    // [3,4,2,3]
    // [5,7,1,8]
    // Very Important Problem , Seems simple but tricky

    public boolean checkPossibility(int[] nums) 
    {
        int count = 0;     
        int prev = -(int)1e9;
        for(int i = 0;i<nums.length-1;i++)
        {
            int next = nums[i+1];
            int curr = nums[i];
            
            if(curr > next)
            {
                if(prev <= next) nums[i] = next;
                else nums[i+1] = nums[i];
                count++;
            }
            prev = nums[i];
            if(count > 1) return false;
        }
        return true;        
    }

    /// 784. Letter Case Permutation

    public List<String> letterCasePermutation(String s) 
    {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder(s);
        getCombi(sb,0,ans);
        return ans;
    }
    
    public void getCombi(StringBuilder s , int i , List<String> ans)
    {
        if(i == s.length()) 
        {
            ans.add(s.toString());
            return;
        }
        
        char ch = s.charAt(i);
        getCombi(s,i+1,ans);
        if(ch>=65&&ch<=90)
        {
            s.setCharAt(i,(char)(ch+32));
            getCombi(s,i+1,ans);
            s.setCharAt(i,ch);
        }
        else if(ch>=97&&ch<=122)
        {
            s.setCharAt(i,(char)(ch-32));
            getCombi(s,i+1,ans);
            s.setCharAt(i,ch);
        }
    }


    // 918. Maximum Sum Circular Subarray
    // Very Important Problem
    // USing The Idea of Finding minSum , maxSum SubArray
    // if maxSum < 0 means that all elements in the array are negative
    // so we will return negative
    // else we will return totalSum + minSum
    // Please note that minSum That we have calculated here is actually the max sum of array when all
    // elements of the array is inverted ie 1 -> -1 || -1 -> 1
    // That's why we are adding it to total sum ans not subtracting it

    public int maxSubarraySumCircular(int[] A) 
    {
        int total = 0, maxSum = -(int)1e9, curMax = 0, minSum = -(int)1e9, curMin = 0;
        for (int a : A) 
        {
            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.max(curMin + (-1*a),-1*a);
            minSum = Math.max(minSum, curMin);
            total += a;
        }
        // System.out.println(maxSum + " + " + minSum);
        return maxSum > 0 ? Math.max(maxSum, total - (-1*minSum)) : maxSum;
    }



    //150. Evaluate Reverse Polish Notation
    // Simple Stack Problem

    public int evalRPN(String[] tokens) 
    {
        int a,b;
        Stack<Integer> S = new Stack<Integer>();
        for (String s : tokens) 
        {
            if(s.equals("+")) 
            {
                S.add(S.pop()+S.pop());
            }
            else if(s.equals("/")) 
            {
                b = S.pop();
                a = S.pop();
                S.add(a / b);
            }
            else if(s.equals("*")) 
            {
                S.add(S.pop() * S.pop());
            }
            else if(s.equals("-")) 
            {
                b = S.pop();
                a = S.pop();
                S.add(a - b);
            }
            else 
            {
                S.add(Integer.parseInt(s));
            }
        }   
        return S.pop();
    }



    // 1130. Minimum Cost Tree From Leaf Values
    // DP Solution
    // There Are Other Solutions as well but we will stick at Dp for now
    // Check Stack Solution as well

    public int mctFromLeafValues(int[] arr) 
    {
        int n = arr.length;
        int[][]dp = new int[n][n];
        int[][] ans = new int[n][n];
        for(int i = 0;i<n;i++) dp[i][i] = arr[i];
        
        for(int gap = 1;gap<n;gap++)
        {
            for(int i = 0,j=gap;j<n;i++,j++)
            {
                dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        
        for(int gap = 1;gap<n;gap++)
        {
            for(int i = 0,j=gap;j<n;i++,j++)
            {
                ans[i][j] = (int)1e9;
                if(gap == 1)
                {
                    ans[i][j] = (dp[i][i]*dp[j][j]);
                    continue;
                }
                for(int k = i;k<j;k++)
                {
                    ans[i][j] = Math.min(ans[i][j],(dp[i][k]*dp[k+1][j]) + ans[i][k] + ans[k+1][j]);
                }
            }
        }
        for(int[]ele : dp) System.out.println(Arrays.toString(ele));
        for(int[]ele : ans) System.out.println(Arrays.toString(ele));
        
        return ans[0][n-1];
        
    }

    // The problem can be translated into removing all local minium values while finding the first greater
    // element on the left and on the right.
    // A stack based solution from previous problems can be applied as lee215 mentioned in his post.


    public int mctFromLeafValues(int[] arr) 
     {
          int res = 0;
          Stack<Integer> stack = new Stack<>();          
          for(int num : arr)
          {
              
             while(!stack.isEmpty() && stack.peek() <= num)
             {
                 int mid = stack.pop();
                 if(stack.isEmpty())  res += mid * num;
                 else res += mid * Math.min(stack.peek(), num); // This line is important, this will fetch us the min sum node
                 // We will mul with min of(ngol,ngor) to fetch the minimun sum;
             }
             
             stack.push(num);         
          } 
         while(stack.size() > 1)
         {
             res += stack.pop() * stack.peek();
         }
         
         return res;
    }


    // 1631. Path With Minimum Effort
    // Very Important Problem
    // We Did This Problem using Dijikstra
    // Here our cost function is the Maximum Absolute Difference and not the path of absolute difference
    // I did with distance but it was giving TLE
    // Our Task is to minimize the maximum absolute difference
    // This will help us find the maximim absolute difference on the mimimum path

    public class pair{
        int x;
        int y;
        int dis;
        pair(int x , int y, int dis){
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
    
    public int minimumEffortPath(int[][] heights) 
    {
        int n = heights.length;
        int m = heights[0].length;
        PriorityQueue<pair> q = new PriorityQueue<>((a,b)->{return a.dis-b.dis;});
        int[] distance = new int[n*m];
        Arrays.fill(distance,(int)1e9);
        int[][] dir = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
        q.add(new pair(0,0,0));
        distance[0] = 0;
        while(q.size() > 0)
        {
            pair rem = q.remove();
            int x = rem.x;
            int y = rem.y;
            int dis = rem.dis;
            
            if(dis > distance[x*m+y]) continue;
            
            if(x == n-1 && y == m-1) return distance[x*m+y];
            
            for(int[] ele : dir)
            {
                int r = x + ele[0];
                int s = y + ele[1];
                
                if(r < 0 || s < 0 || r >= n || s >= m) continue;
                
                int v = heights[r][s];
                int d = Math.max(dis,Math.abs(heights[x][y] - v));
                if(d < distance[r*m+s])
                {
                    distance[r*m+s] = d;
                    q.add(new pair(r,s,d));
                }
            }
        }
        return distance[(n-1)*m+(m-1)];
    }


    // 373. Find K Pairs with Smallest Sums
    // Important Question
    // Used the Almost same approach we learnt in Leetcode 786
    // Kth smallest prime fraction
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) 
    {
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->
        {
            int v1 = nums1[a[0]] + nums2[a[1]];
            int v2 = nums1[b[0]] + nums2[b[1]];
            return Integer.compare(v1,v2);
        });

        for(int i = 0 ;i<nums2.length;i++) q.add(new int[]{0,i});
        
        List<List<Integer>> ans = new ArrayList<>();
        
        while(q.size() > 0 && k-- > 0)
        {
            int[] pop = q.remove();
            List<Integer> temp = new ArrayList<>();
            temp.add(nums1[pop[0]]);
            temp.add(nums2[pop[1]]);
            ans.add(temp);
            if(pop[0]+1 < nums1.length) 
            {
                q.add(new int[]{pop[0]+1,pop[1]});
            }
        }
        return ans;
    }

    // No Of Matching Subsequences
    // Again a very good question
    // Very Important Visualization in DryRun
    // Mind Blowing Solution


    class Node 
    {
        String word;
        int index;
        Node(String word, int index) 
        {
            this.word = word;
            this.index = index;
        }
    }
    public int numMatchingSubseq(String s, String[] words)
    {
        ArrayList<Node>[] buckets = new ArrayList[26];
        for (int i = 0; i < 26; ++i) buckets[i] = new ArrayList<>();
        for (String word : words) 
        {
            char startingChar = word.charAt(0);
            buckets[startingChar-'a'].add(new Node(word, 0));
        }
        int ans = 0;
        for (char c : s.toCharArray()) 
        {
            ArrayList<Node> currBucket = buckets[c-'a'];
            buckets[c-'a'] = new ArrayList<>();
            for (Node node : currBucket) 
            {
                node.index += 1; // Point to next character of node.word
                if (node.index == node.word.length()) 
                {
                    ans += 1;
                } 
                else 
                {
                    char startingChar = node.word.charAt(node.index);
                    buckets[startingChar - 'a'].add(node);
                }
            }
        }
        return ans;
    }

    // 486. Predict the Winner
    // Exactly Same As Optimal Game Strategy
    // MINI-MAX algorithm
    // Here we have to follow Maximize the Minimums
    public boolean PredictTheWinner(int[] nums) 
    {
        if(nums.length == 1)
        {
            return true;
        }
        int total = 0;
        for(int i : nums)
        {
            total+=i;
        }
        
        int[][] dp = new int[nums.length][nums.length];
        for(int g = 0; g < nums.length; g++)
        {
            for(int i = 0,j = g; j < dp.length; i++,j++)
            {
                if(g == 0)
                {
                    dp[i][j] = nums[i];
                }
                else if(g == 1)
                {
                    dp[i][j] = Math.max(nums[i],nums[j]);
                }
                else
                {
                    int val1 = nums[i] + Math.min(dp[i+2][j],dp[i+1][j-1]);
                    int val2 = nums[j] + Math.min(dp[i+1][j-1],dp[i][j-2]);
                    dp[i][j] = Math.max(val1,val2);
                }
            }
        }
        for(int[] ele : dp) 
        {
            System.out.println(Arrays.toString(ele));
        }
        
        return (dp[0][nums.length-1] >= (total - dp[0][nums.length-1]));
    }


    // 752. Open the Lock
    // One Thing to Note is that We USE BFS when we need the shortest path and when there is no need to update values
    // in Level Order Fashion
    // We Use Dijikstra's Algorithm when the edge weights are not 1 ie they are not same for each edge
    // Dijikstra is also used when we need to update the minimimin values because there could be multiple
    // ways to reach a particular node and we need to reach with the minimum value
    // Matlab Jab alag alag values hoti hei edges ki toh agar hei eak state par pehle pahunch gaya
    // toh aaisa bhi possible hei ki eak lambe raste se oosi state par kam value ke saath pahunch sakta hu
    private static final String START = "0000";
    
    public int openLock(String[] deadends, String target) 
    {
        if (target == null || target.length() == 0) return -1;
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        Queue<String> queue = new LinkedList<>();
        int level = 0;
        queue.offer(START);
        
        while (!queue.isEmpty()) 
        {
            int size = queue.size();
            for (int i = 0; i < size; i++) 
            {
                String currentLock = queue.poll();
                if(visited.contains(currentLock)) continue;
                visited.add(currentLock);
                if (currentLock.equals(target)) return level;
                
                for (String nextLock : getNextStates(currentLock)) 
                {
                    if (!visited.contains(nextLock)) queue.offer(nextLock);
                }
            }
            level++;
        }
        
        return -1;
    }
    
    private List<String> getNextStates(String lock) 
    {
        List<String> locks = new LinkedList<>();
        char[] arr = lock.toCharArray();
        for (int i = 0; i < arr.length; i++) 
        {
            char c = arr[i];
            arr[i] = c == '9' ? '0' : (char) (c + ((char) 1));
            locks.add(String.valueOf(arr));
            arr[i] = c == '0' ? '9' : (char) (c - ((char) 1));
            locks.add(String.valueOf(arr));
            arr[i] = c;
        }
        return locks;
    }

    // 1306. Jump Game III
    // Simple O(n) approach

    public boolean canReach(int[] arr, int start) 
    {
        return can(arr,start);
    }
    
    public boolean can(int[]arr,int start)
    {
        if(start >= arr.length || start < 0) return false;
        if(arr[start] == 0) return true;
        boolean res = false;
        int val = arr[start];
        arr[start]+=arr.length; // Marking Visited by adding length of array
        res = res || can(arr,start + val) || can(arr,start - val);
        return res;
    }

    // 611. Valid Triangle Number
    // Simple Sorting Problem

    public int triangleNumber(int[] nums) 
    {
        int count = 0;
        Arrays.sort(nums);
        
        for(int k = nums.length-1;k>=2;k--)
        {
            int v1 = nums[k];
            int i = 0;
            int j = k-1;
            
            while(i < j)
            {
                int v2 = nums[i];
                int v3 = nums[j];
                
                if(v3+v2>v1)
                {
                    count += j-i;
                    j--;
                }
                else i++;
            }
        }
        return count;
    }

    // 849. Maximize Distance to Closest Person
    // This problem is just what it is and nothing fancy
    // [0,0,0,0,0,0,1]
    // [1,0,0,0,0,0,0]
    // Consider these cases as well
    public int maxDistToClosest(int[] seats)
    {
        int dist = Integer.MIN_VALUE;
        int j = -1;
        if(seats[0] == 1) j = 0;
        for (int i = 1; i < seats.length; i++) 
        {
            if(seats[i] == 1)
            {
                if(j == -1) dist = Math.max(dist , i);
                else dist = Math.max(dist , (i - j)/2);
                j = i;
            }
        }
        if(seats[seats.length - 1] == 0) dist = Math.max(dist , seats.length - 1 - j);
        return dist;
    }


    // 71. Simplify Path
    // Simple Stack Problem
    public String simplifyPath(String path) 
    {
        Stack<String> s = new Stack<>();
        StringBuilder res = new StringBuilder();
        String[] p =path.split("/");
        // this will remove even continous"////";
        
        for(int i=0;i<p.length;i++)
        {
            if(!s.isEmpty() && p[i].equals("..")) s.pop();
            else if(!p[i].equals("") && !p[i].equals(".") && !p[i].equals("..")) s.push(p[i]);
        }
        
        
        if(s.isEmpty()) return "/";
        while(!s.isEmpty())
        {
            res.insert(0,s.pop()).insert(0,"/");
        }
        
        return res.toString();
    }


    // 1584. Min Cost to Connect All Points
    // Solved This Using Kruskals

    // Can be further optimized if we check for size of component and then combine

    public int dist(int[][] points , int i , int j)
    {
        int x1 = points[i][0];
        int y1 = points[i][1];
        int x2 = points[j][0];
        int y2 = points[j][1];
        
        return Math.abs(x2-x1) + Math.abs(y2-y1);
    }
    public int findPar(int[]par,int u)
    {
        return (par[u] == -1) ? u : findPar(par,par[u]); 
    }
    public int minCostConnectPoints(int[][] points) 
    {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{
            return a[0] - b[0];
        });
        int[] par = new int[points.length];
        Arrays.fill(par,-1);
        for(int i= 0;i<points.length;i++)
        {
            for(int j=i+1;j<points.length;j++)
            {
                queue.add(new int[]{dist(points,i,j),i,j});
            }
        }
        int ans = 0;
        while(queue.size() > 0)
        {
            int[]pop = queue.remove();
            
            int u = findPar(par,pop[1]);
            int v = findPar(par,pop[2]);
            
            if(u == v) continue;
            
            par[u] = v;
            
            ans+=pop[0];
        }
        return ans;
    }


    //820. Short Encoding of Words

    // Given string list L1, construct a another string list L2, 
    // making every word in L1 be a suffix of a word in L2.
    // Return the minimum possible total length of words in L2
    // Input L1: [“time”,“me”,“bell”]
    // L2: [“time”,“bell”]
    
    // Could Be Done Using Trie As well but this solution is much much simpler
    public int minimumLengthEncoding(String[] words) 
    {
        Set<String> s = new HashSet<>(Arrays.asList(words));
        for (String w : words)
            for (int i = 1; i < w.length(); ++i)
                s.remove(w.substring(i));
        int res = 0;
        for (String w : s) res += w.length() + 1;
        return res;
    }


    // 991. Broken Calculator
    // This approach Will Always Work In these kind of questions
    // Obviously if a number is odd it will always be benificial to make it even
    // bby adding(1) accordig to this ques and divide by 2
    // we are moving from target to start
    public int brokenCalc(int startValue, int target) 
    {
        int res = 0;
        while(target > startValue)
        {
            // if target is odd we will make it even
            if(target % 2 == 1) ++target;
            // if target is even divide by 2
            else target /= 2;
            ++res;
        }
        return res  + startValue - target;  
    }

    // 498. Diagonal Traverse
    // Here We Will Use that fact that Sum of Coordinates on the diagonal are same
    // Copied Code
    // You can easily write it according to you only the above fact is important
    public int[] findDiagonalOrder(int[][] matrix) 
    {
        if(matrix==null|| matrix.length==0) return new int[0];
        int N=matrix.length;
        int M=matrix[0].length;
        int[] res=new int[N*M];
        Map<Integer,List<Integer>>dict=new HashMap<>();
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                int sum=i+j;
                if(!dict.containsKey(sum))
                {
                    dict.put(sum, new ArrayList<>());
                }
                dict.get(sum).add(matrix[i][j]);
            }
        }
        int ctr=0;
        for(Map.Entry<Integer,List<Integer>> entry:dict.entrySet())
        {
            List<Integer> temp_list=new ArrayList<>();
            if(entry.getKey()%2==0)
            {
                temp_list=entry.getValue();
                Collections.reverse(temp_list);
            }
            else
            {
                 temp_list=entry.getValue(); 
            }
            for(int i=0;i<temp_list.size();i++)
            {
                res[ctr++]=temp_list.get(i);
            }
        }
        return res;
    }
    

    //923. 3Sum With Multiplicity
    public int threeSumMulti(int[] arr, int target) 
    {
        int mod = (int)1e9+7;
        int count = 0;
        Arrays.sort(arr);
        for(int k = arr.length-1;k>=0;k--)
        {
            int v1 = arr[k];
            int i = 0;
            int j = k-1;
            
            while(i < j)
            {
                int v2 = arr[i];
                int v3 = arr[j];
                
                if(v1+v2+v3 == target)
                {
                    if(v2 == v3) // Important Case
                    {
                        while(j > i)
                        {
                            count = (count + (j-i)%mod)%mod;
                            j--;
                        }
                        continue;
                    }
                    else
                    {
                        int c1 = 1;
                        int c2 = 1;
                        while(arr[j-1] == arr[j])
                        {
                            j--;
                            c1++;
                        }
                        while(arr[i+1] == arr[i])
                        {
                            i++;
                            c2++;
                        }
                        
                        count = (count +  ((c1*c2))%mod)%mod;
                    }
                    i++;
                    j--;
                }
                else if(v1+v2+v3 > target)
                {
                    j--;
                }
                else
                {
                    i++;
                }
            }
        }
        return count%mod;
    }


    //1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
    // Simple Solution Using TreeMap to get the minimum and maximum elements 

    // We can Simple Do this question using two PriorityQueues
    // or using a single TreeMap to get Maximum and minimum
    public int longestSubarray(int[] nums, int limit) 
    {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int len = 0;
        int i = 0;
        int j = 0;
        while(j<nums.length)
        {
            int val = nums[j]; 
            map.put(val,map.getOrDefault(val,0)+1);
            int min = map.firstKey();
            int max = map.lastKey();
            
            if(max-min <= limit)
            {
                len = Math.max(len,j-i+1);
            }
            else
            {
                while(max-min > limit)
                {
                    map.put(nums[i],map.getOrDefault(nums[i],0)-1);
                    if(map.get(nums[i]) == 0) map.remove(nums[i]);
                    min = map.firstKey();
                    max = map.lastKey();
                    i++;
                }
            }
            j++;
        }
        return len;
    }


    // 239. Sliding Window Maximum
    // Important Strategy to use dequeue

    public int[] maxSlidingWindow(int[] nums, int k) 
    {
        LinkedList<Integer> deque = new LinkedList<>();
        int n = nums.length, idx = 0;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < nums.length; i++) 
        {
            while (deque.size() != 0 && deque.getFirst() <= i - k) deque.removeFirst();
            while (deque.size() != 0 && nums[deque.getLast()] <= nums[i]) deque.removeast();
            deque.addLast(i);
            if (i >= k - 1) ans[idx++] = nums[deque.getFirst()];
        }
        return ans;
    }

    // 659. Split Array into Consecutive Subsequences
    // Important
    // My approach

    // ele.count > (ele+1).count break;
    // beacuse agar aaisa kuch hei 1 2 3 3 4 5 aur mei pehla subsequence 1 2 3 4 5 bana leta hu toh
    // joh eak extra 3 hei voh akale reh jayega 
    // but agar mei 1 2 3 , 3 4 5 banata hu toh 3 bhe subsequence bana sakta hei of length >=3
    // isliye since humne aaisa kiya
    // think over this , Important Solution

    public boolean isPossible(int[] nums) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : nums) map.put(ele,map.getOrDefault(ele,0)+1);
        
        for(int i = 0;i<nums.length;i++)
        {
            int ele = nums[i];
            if(!map.containsKey(ele)) continue;
            
            int len = 0;
            
            while(map.containsKey(ele))
            {
                if(map.containsKey(ele+1) && map.get(ele) > map.get(ele+1))
                {
                    map.put(ele,map.get(ele)-1);
                    if(map.get(ele) == 0) map.remove(ele);
                    len++;
                    break;
                }
                map.put(ele,map.get(ele)-1);
                if(map.get(ele) == 0) map.remove(ele);
                len++;
                ele++;   
            }
            if(len < 3) return false;
        }
        return map.size()==0;
    }



    // 1049. Last Stone Weight II
    // This Problem is equivilant to partitian into subsets such that the diffenence is minimum
    // Greedy Was Not working For this problem

    public int lastStoneWeightII(int[] elements)
    {
        int n = elements.length;
        int sum = 0;
        for(int ele : elements) sum+= ele;
        
        int dp[] = new int[sum + 1];
        dp[0] = 1;
        for (int i = 0; i < elements.length; i++) 
        {
            for (int j = sum; j >= elements[i]; j--) 
            {
                if (dp[j - elements[i]] == 1) dp[j] = 1;
            }
        }
        int minDiff = (int)1e9;
        for(int i = sum/2 ;i>=0 ;i--){
            if(dp[i] == 1) minDiff = Math.min(minDiff , sum - 2*i);
        }
        return minDiff;
    }



    //187. Repeated DNA Subsequences
    // Just Generate all continous subsequences of length 10
    // OverLapping of sunsequences is also permissible
    // We Can Use Rolling Hash as well
    public List<String> findRepeatedDnaSequences(String s) 
    {
        HashMap<String, Integer> cnt = new HashMap<>();
        for (int i = 0; i < s.length() - 9; i++) 
        {
            String subStr = s.substring(i, i + 10);
            cnt.put(subStr, cnt.getOrDefault(subStr, 0) + 1);
        }
        List<String> ans = new ArrayList<>();
        for (String key : cnt.keySet())
        {
            if (cnt.get(key) >= 2) ans.add(key);
        }
        return ans;
    }


    // 791. Custom Sort String

    public String customSortString(String order, String s) 
    {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0;i<order.length();i++)
        {
            char ch = order.charAt(i);
            if(!map.containsKey(ch)) continue;
            
            for(int j = 0;j<map.get(ch);j++)
            {
                sb.append(ch);
            }
            map.remove(ch);
        }
        
        
        for(char ele : map.keySet())
        {
            for(int i = 0;i<map.get(ele);i++)
            {
                sb.append(ele);
            }
        }
        return sb.toString();
    }


    // 926. Flip String to Monotone Increasing
    // Important Solution
    // Easy Solution
    public int minFlipsMonoIncr(String S) 
    {
        if(S == null || S.length() <= 0 ) return 0;
        char[] sChars = S.toCharArray();
        int flipCount = 0;
        int onesCount = 0;

        for(int i=0; i<sChars.length; i++)
        {
            if(sChars[i] == '0')
            {
                if(onesCount == 0) continue;
                else flipCount++;
            }
            else
            {
                onesCount++;
            }
            if(flipCount > onesCount)// if this case then instead of toggling 0->1 we toggle 1->0 coz that's more benificial
            {
                flipCount = onesCount;
            }
        }
        return flipCount;
    }


    // 1254. Number of Closed Islands
    // 2 represents that the cell is visited but not valid as it may lead to no boundary so no need to apply further DFS
    // -1 represents that the cell is visited and valid as it is leading to boundary
    public int closedIsland(int[][] grid) 
    {
        int count = 0;
        int[][] dir = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
        for(int i = 0;i<grid.length;i++)
        {
            for(int j = 0;j<grid[0].length;j++)
            {
                if(grid[i][j] == 0)
                {
                    if(getIsland(i,j,grid,dir)) count++;;
                }
            }
        }
        return count;
    }
    
    public boolean getIsland(int i , int j , int[][] grid , int[][]dir)
    {
        if(i < 0||j < 0||i >= grid.length || j >= grid[0].length) return false;
        if(grid[i][j] == 2) return false;
        if(grid[i][j] == -1) return true;
        if(grid[i][j] == 1) return true;
        
        boolean res = true;
        grid[i][j] = -1;
        for(int[]ele : dir)
        {
            int x = i+ele[0];
            int y = j+ele[1];
            res = res && getIsland(x,y,grid,dir);
        }
        if(!res) grid[i][j] = 2;
        return res;
    }

    // 845. Longest Mountain in Array
    // Note That we are talking about mountain continous array and not any subsequence
    // So it is straightforward to come up with this solution
    // So find continous increasing count and continous decreasing count

    public int longestMountain(int[] A) 
    {
        int maxMnt = 0;
        int i = 1;
        while (i < A.length) 
        {
            while(i < A.length && A[i-1] == A[i]) ++i;
            int up = 0;
            while(i < A.length && A[i-1] < A[i]) 
            {
                ++up;
                ++i;
            }
            int down = 0;
            while(i < A.length && A[i-1] > A[i]) 
            {
                ++down;
                ++i;
            }
            if (up > 0 && down > 0) maxMnt = Math.max(maxMnt, up+down+1);            
        }
        return maxMnt;    
    }


    // 1395. Count Number of Teams
    // First Solution
    // Check DryRun
    public int numTeams(int[] rating) 
    {
        HashMap<Integer,Integer>[] dp1 = new HashMap[rating.length];
        HashMap<Integer,Integer>[] dp2 = new HashMap[rating.length];
        
        for(int i = 0;i<dp1.length;i++)
        {
            dp1[i] = new HashMap<>();
            dp2[i] = new HashMap<>();
        }
        
        int count = 0;
        for(int i=0;i<rating.length;i++)
        {
            int v1 = rating[i];
            for(int j = 0;j<i;j++)
            {
                 int v2 = rating[j];
                
                if(v1 > v2)
                {
                    if(dp1[j].containsKey(2)) count+=dp1[j].get(2);
                    if(dp1[j].containsKey(1)) dp1[i].put(2,dp1[i].getOrDefault(2,0)+dp1[j].get(1));
                }
                else if(v1 < v2)
                {
                    if(dp2[j].containsKey(2)) count+=dp2[j].get(2);
                    if(dp2[j].containsKey(1)) dp2[i].put(2,dp2[i].getOrDefault(2,0)+dp2[j].get(1));
                }
            }
            dp1[i].put(1,dp1[i].getOrDefault(1,0)+1);
            dp2[i].put(1,dp2[i].getOrDefault(1,0)+1);
        }
        return count;
    }


    // Other Solution
    // After Observing Further the above solution 
    // There is no point of storing one in the map/dp
    // because anyway we will increase the 2's count in dp[i] when dp[j] < dp[i];

    public int numTeams(int[] rating) 
    {
        int[] up = new int[rating.length];
        int[] down = new int[rating.length];
        
        int count = 0;
        
        for(int i = 0;i<rating.length;i++)
        {
            int v1 = rating[i];
            for(int j = 0;j<i;j++)
            {
                int v2 = rating[j];
                if(v1 > v2)
                {
                    up[i]+=1;
                    count+=up[j];
                }
                else if(v1 < v2)
                {
                    down[i]+=1;
                    count+=down[j];
                }
            }
        }
        return count;
    }

    // 1219. Path with Maximum Gold
    // Simple DFS Solution

        int r = 0;
    int c = 0;
    int max = 0;
    public int getMaximumGold(int[][] grid) 
    {
        r = grid.length;
        c = grid[0].length;
        for(int i = 0; i < r; i++) 
        {
            for(int j = 0; j < c; j++) 
            {
                if(grid[i][j] != 0) 
                {
                    dfs(grid, i, j, 0);
                }
            }
        }
        return max;
    }
    
    public void dfs(int[][] grid, int i, int j, int cur) 
    {        
        if(i < 0 || i >= r || j < 0 || j >= c || grid[i][j] == 0) 
        {
            max = Math.max(max, cur);
            return;
        }
        int val = grid[i][j];
        grid[i][j] = 0;
        dfs(grid, i + 1, j, cur + val);
        dfs(grid, i - 1, j, cur + val);
        dfs(grid, i, j + 1, cur + val);
        dfs(grid, i, j - 1, cur + val);
        grid[i][j] = val;
    }

    // 1288. Remove Covered Intervals
    // [[1,2],[1,4],[3,4]]
    // Important Test Case

    public int removeCoveredIntervals(int[][] intervals)
    {
        Arrays.sort(intervals,(a,b)->{
            if(a[0]!=b[0]) return a[0]-b[0];
            return b[1]-a[1];
        });
        // for(int[]ele : intervals) System.out.println(Arrays.toString(ele));
        int count = 1;
        int[] prev = intervals[0];
        for(int i = 1;i<intervals.length;i++)
        {
            int a = intervals[i][0];
            int b = intervals[i][1];
            if(a>=prev[0] && b<=prev[1]) continue;
            count++;
            prev = intervals[i];
        }
        return count;
    }


    // 565. Array Nesting
    // This Solution giving TLE
    // O(n^2)
    public int arrayNesting(int[] nums) 
    {
        int ans = 0;
        for(int i = 0;i<nums.length;i++)
        {
            int count = 1;
            int idx = nums[i];
            while(idx!=i)
            {
                count++;
                idx = nums[idx];
            }
            ans = Math.max(ans,count);
        }
        return ans;
    }

    // Just find the longest cycle

    // Just find the longest cycle
    public int arrayNesting(int[] nums) 
    {
        int ans = 1;
        for(int i = 0;i<nums.length;i++)
        {
            if(nums[i] < 0) continue;
            int count = 0;
            int idx = i;
            do
            {
                int prev = idx;
                idx = nums[idx];
                nums[prev] = -1*nums[prev]; // marking Visited
                count++;
                if(nums[idx] < 0) break;
                
            }while(idx!=i);
            ans = Math.max(ans,count);
        }
        return ans;
    }

    // 950. Reveal Cards In Increasing Order
    // Simulate In Reverse order as said in question
    // Lee's Solution

    public int[] deckRevealedIncreasing(int[] deck) 
    {
        int n = deck.length;
        Arrays.sort(deck);
        Queue<Integer> q = new LinkedList<>();
        for (int i = n - 1; i >= 0; --i) 
        {
            if (q.size() > 0) q.add(q.poll());
            q.add(deck[i]);
        }
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; --i) 
        {
            res[i] = q.poll();
        }
        return res;
    }


    // 1353. Maximum Number of Events That Can Be Attended
    // First Simple Solution
    // Sorting On the basis of end time in increasing order
    // filling up the spaces on the array 
    // The idea is to complete that job first which is going to end first
    // 42/44 test cases passed
    public int maxEvents(int[][] events) 
    {
        Arrays.sort(events,(a,b)->{
            return a[1]-b[1];
        });
        
        int[] arr = new int[100001];
        int count = 0;
        for(int[] ele : events)
        {
            for(int i = ele[0];i<=ele[1];i++)
            {
                if(arr[i] == 0)
                {
                    arr[i]=1;
                    count++;
                    break;
                }
            }
        }
        return count;
    }


    // Simple TreeSet Solution

    public int maxEvents(int[][] events) 
    {
        Arrays.sort(events,(a,b)->{
            return a[1]-b[1];
        });
        int min = (int)1e9;
        int max = -(int)1e9;
        int count = 0;
        for(int[]ele :events)
        {
            min = Math.min(min,ele[0]);
            max = Math.max(max,ele[1]);
        }
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = min;i<=max;i++) set.add(i);
        for(int[] ele : events)
        {
            Integer idx = set.ceiling(ele[0]);
            if(idx!=null && idx <= ele[1]) 
            {
                count++;
                set.remove(idx);// removing the used index
            }
        }
        
        return count;
    }

    // 515 · Paint House
    // Submitted On Lintcode

    public int minCost(int[][] costs) 
    {
        int rP = 0;
        int gP = 0;
        int bP = 0;


        for(int[] ele : costs)
        {
            int rC = 0;
            int gC = 0;
            int bC = 0;

            rC = Math.min(gP,bP)+ele[0];
            gC = Math.min(rP,bP)+ele[2];
            bC = Math.min(gP,rP)+ele[1];

            rP = rC;
            gP = gC;
            bP = bC;
        }

        return Math.min(rP,Math.min(gP,bP));
    }


    // 1239. Maximum Length of a Concatenated String with Unique Characters
    // Note That The time complexity for this solution is still O(2^N) ans Not n^2
    // Because anyway we are going to generate all the subsequences

    private boolean isUnique(String str) 
    {
        if (str.length() > 26) return false;
        boolean[] used = new  boolean [26];
        char[] arr = str.toCharArray();
        for (char ch : arr) 
        {
            if (used[ch - 'a']) return false;
            else used[ch -'a'] = true;
        }
        return true;
    }
    public int maxLength(List<String> arr) 
    {
        List<String> res = new ArrayList<>();
        res.add("");
        for (String str : arr) 
        {
            if (!isUnique(str)) continue;// Without Contatinating Checking
            List<String> resList = new ArrayList<>();
            for (String candidate : res) 
            {
                String temp = candidate + str;
                if (isUnique(temp)) resList.add(temp);// After Concatinating Checking
            }
            res.addAll(resList);
        }
        int ans = 0;
        for (String str : res) ans = Math.max(ans, str.length());
        return ans;
    }


    // 807. Max Increase to Keep City Skyline

    public int maxIncreaseKeepingSkyline(int[][] grid) 
    {
        int n = grid.length;
        int[] row = new int[n];
        int[] col = new int[n];
        
        
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<n;j++)
            {
                int val = grid[i][j];
                row[i] = Math.max(row[i],val);
                col[j] = Math.max(col[j],val);
            }
        }
        
        int ans = 0;
        
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<n;j++)
            {
                int min = Math.min(row[i],col[j]);
                if(min-grid[i][j] > 0) ans += min-grid[i][j];
            }
        }
        return ans;
    }


    // 622. Design Circular Queue
    // Very Important And Concise Solution
    // Initializing rear to -1 is important

    final int[] a;
    int front = 0, rear = -1, len = 0;

    public MyCircularQueue(int k) 
    { 
        a = new int[k];
    }

    public boolean enQueue(int val) 
    {
        if (!isFull()) 
        {
            rear = (rear + 1) % a.length;
            a[rear] = val;
            len++;
            return true;
        } 
        else return false;
    }

    public boolean deQueue() 
    {
        if (!isEmpty()) 
        {
            front = (front + 1) % a.length;
            len--;
            return true;
        } 
        else return false;
    }

    public int Front() { return isEmpty() ? -1 : a[front];}

    public int Rear() {return isEmpty() ? -1 : a[rear];}

    public boolean isEmpty() { return len == 0;}

    public boolean isFull() { return len == a.length;}


    // 331. Verify Preorder Serialization of a Binary Tree
    // Using Stack

    public boolean isValidSerialization(String pre)
    {
        Stack<String> st = new Stack<>();
        String[] preorder = pre.split(",");
        for(int i = 0;i<preorder.length;i++)
        {
            String ch = preorder[i];
            while(st.size()>1 && st.peek().equals("#") && ch.equals("#"))
            {
                st.pop();
                if(st.pop().equals("#")) return false;
            }
            st.push(ch);
        }
        // System.out.println(st.toString());
        return (st.size() == 1) && (st.peek().equals("#"));
    }


    // 1079. Letter Tile Possibilities
    // BAcktrackig Solution

    Set<String> set = new HashSet();
    boolean[] vis;
    public int numTilePossibilities(String tiles) 
    {
        int n = tiles.length();
        vis = new boolean[n];
        dfs(new StringBuilder(), tiles);
        return set.size();
    }
    public void dfs(StringBuilder sb, String tiles)
    {
        if(sb.length()>0) set.add(sb.toString());
        if(sb.length()>=tiles.length()) return;
        for(int i=0;i<tiles.length();i++)
        {
            if(vis[i]) continue;
            vis[i] = true;
            int len = sb.length();
            dfs(sb.append(tiles.charAt(i)), tiles);
            sb.setLength(len);
            vis[i] = false;
        }
    }


    // 1014. Best Sightseeing Pair
    // Simple Dp problem
    // Can be done in O(n) time and O(1) space
    public int maxScoreSightseeingPair(int[] values) 
    {
        int[] dp = new int[values.length];
        dp[0] = values[0];
        for(int i = 1;i<values.length;i++)
        {
            dp[i] = Math.max(dp[i-1],values[i]+i);
        }
        int score = 0;
        for(int i = values.length-1;i>0;i--)
        {
            score = Math.max(score,dp[i-1] + values[i]-i);
        }
        return score;
    }


    // 477. Total Hamming Distance
    // Simple bit masking

    public int totalHammingDistance(int[] nums) 
    {
        int ans = 0;
        for(int i = 0;i<32;i++)
        {
            int count = 0;
            int mask = 1<<i;
            for(int ele : nums)
            {
                if((ele&mask) == mask) count++;
            }
            ans += (count*(nums.length-count));
        }
        return ans;
    }


    // 473. Matchsticks to Square
    // This Question is nothing but partitian set into k equal subset sums
    // Very Important Observation
    public boolean makesquare(int[] matchsticks) 
    {
        return canPartitionKSubsets(matchsticks,4);
    }
    public boolean canPartitionKSubsets(int[] nums, int k) 
    {
        int n = nums.length;
        int sum = 0;
        int maxEle = 0;
        for(int ele : nums)
        {
            sum+=ele;
            maxEle = Math.max(maxEle,ele);
        }
        if(sum %k!=0 || maxEle > sum/k) return false;
        boolean[] vis = new boolean[nums.length];
        return canPartitionKSubsets(nums,k,0,0,sum/k,vis);
    }
    public boolean canPartitionKSubsets(int[] nums, int k,int idx , int ssf , int tar , boolean[] vis) 
    {
        if(k == 0) return true;
        if(ssf > tar) return false;
        if(ssf == tar) return canPartitionKSubsets(nums,k-1,0,0,tar,vis);
        boolean res = false;
        for(int i = idx ;i<nums.length;i++)
        {
            if(vis[i]) continue;
            vis[i] = true;
            res = res || canPartitionKSubsets(nums,k,i+1,ssf+nums[i],tar,vis);
            vis[i] = false;
        }
        return res;
    }

    // 1937. Maximum Number of Points with Cost
    // Similar approach to min falling path sum giving tle
    // 152/157 TC passed

    public long maxPoints(int[][] points) 
    {
        long[] dp = new long[points[0].length];
        long max = 0;
        for(int i = 0;i<points[0].length;i++)
        {
            dp[i] = points[0][i];
            max = Math.max(max,dp[i]);
        }
        
        
        for(int i = 1;i<points.length;i++)
        {
            long[] temp = new long[points[0].length];
            for(int j = 0;j<points[0].length;j++)
            {
                for(int k = 0;k<points[0].length;k++)
                {
                    temp[j] = Math.max(temp[j],dp[k]+points[i][j]-Math.abs(k-j));
                    if(i == points.length-1)
                    {
                        max = Math.max(max,temp[j]);
                    }
                }
            }
            dp = temp;
        }
        return max;
    }


    

}