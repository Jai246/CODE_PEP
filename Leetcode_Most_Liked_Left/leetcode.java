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
        int ans = 0;
        n = n + 1;
        Map<Character, Integer> taskToCount = new HashMap<>();
        for (char c : tasks) {
            taskToCount.put(c, taskToCount.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (char c : taskToCount.keySet()) pq.offer(taskToCount.get(c));
        
        LinkedList<Integer> rem = new LinkedList<>();
        
        while(pq.size()!=0)
        {
            int s = pq.size();
            ans += n; // We just Need to Figure Out This Only
            int p = n;
            while(pq.size() > 0 && p-- > 0) rem.addLast(pq.remove());

            while(rem.size() > 0)
            {
                int val = rem.removeLast()-1; 
                if(val > 0) pq.add(val);
            }

            if(pq.size() == 0) ans -= (n-s); // Handling the Ending Part
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
    // This Will Work Both from Left to Right and Right To Left
    public int threeSumClosest(int[] nums, int target) 
    {
        int result = (int)1e9;
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = n-1;i>=0;i--)
        {
            int v1 = nums[i];
            int j = 0;
            int k = i-1;
            
            while(j < k)
            {
                int v2 = nums[j];
                int v3 = nums[k];
                int sum = v1+v2+v3;
                if(Math.abs(result-target) > Math.abs(sum-target)) result = sum;
                if(sum > target) k--;
                else j++;
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
            while(i+1 < a.length && a[i] == a[i+1])  i += 1;
            // Skipping the Duplicates
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
    // Agar left and right barabar hei(matlab jab left null hogaya hei) 
    // toh return kardo (1<<height)-1;
    // Kyoki voh sub tree eak complete binary tree hei
    // Kyoki No of nodes in a complete binary tree kaa 
    // formulae(i << height)-1 hota hei;
    // arag aaisa nahi hei toh left sub tree and right sub tree par
    // call lagado aur current node ka +1 karke return kardo

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
    // Main thing is sorting and after that inserting people 
    // at the people[1] position
    // O(n2) due to insertion
    // Here Front means left to the current person


    // When Height is equal sort in increasing order off people
    // Else sort in decreasing order of height
    
    // [(7,0),(4,4),(7,1),(5,0),(6,1),(5,2)]
    // [(7,0),(7,1),(6,1),(5,0),(5,2),(4,4)]

    // [(5,0),(7,0),(5,2),(6,1),(4,4),(7,1)]

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
    // No Extra Space Just Modifying the values
    // This is someone  else's solution and not my solution
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

    public void checkAndUpdate(int[][] board, int i, int j)
    {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        int[][] indexes = {{0,-1},{0,1},{1,-1},{1,0},{1,1},{-1,-1},{-1,0}, {-1,1}};
        for (int[] index : indexes) 
        {
            if (i + index[0] < 0 || i + index[0] >= m || j + index[1] < 0 || j + index[1] >= n) continue;
            if (Math.abs(board[i + index[0]][j + index[1]]) == 1) count++;
            // Took the absolute value to cover both 1 and -1
            // 1 has not died
            // Whereas -1 represents , Initially I was but now Died
        }
        if (board[i][j] == 0 && count == 3) board[i][j] = 2; // Will Live Long
        else if (board[i][j] == 1 && (count < 2 || count > 3)) board[i][j] = -1; // Will Die
    }



    // 424. Longest Repeating Character Replacement
    // Imp BAAA and k == 2
    // O(26.n) solution
    // O(n) solution nahi samajh aaraha
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
            // Agar count 4 ya ooose zyada hei toh bhi voh
            // Bit add hogi answer mei
            // Agar 3 se kam hei toh bhi add hogi
            if(count%3 != 0) 
            {
                // Here We Are Actually Creating the answer by collecting different bits
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
        long[] dp = new long[3];
        Arrays.fill(dp,(long)1e10);
        for(int ele : nums)
        {
            if(ele <= dp[0]) dp[0] = ele;
            else if(ele <= dp[1]) dp[1] = ele;
            else if(ele <= dp[2]) dp[2] = ele;
        }
        return dp[2]!=(long)1e10;
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
    // for apple we are storing every combination of prefix and suffix
    // a|apple      a|pple      a|ple      a|le      a|e
    // ap|apple     ap|pple     ap|ple     ap|le     ap|e
    // app|apple    app|pple    app|ple    app|le    app|e
    // appl|apple   appl|pple   appl|ple   appl|le   appl|e
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
                if(prev <= next) nums[i] = nums[i+1]; // 1 5 7  -->   1 5 5 //  Hamesha Choti Value Lagane Ki Koshish
                else nums[i+1] = nums[i]; // 5 7 1 -->  5 7 7
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
    // Check Dryrun Folder
    // Using The Idea of Finding minSum , maxSum SubArray
    // if maxSum < 0 means that all elements in the array are negative
    // so we will return negative
    // else we will return totalSum + minSum
    // Please note that minSum That we have calculated
    // here is actually the max sum of array when all
    // elements of the array is inverted ie 1 -> -1 || -1 -> 1
    // That's why we are adding it to total sum ans not subtracting it

    // SUMMARY
    // TOTAL-MIN SUBARRAY SUM IS GONNA GIVE YOU MAXIMUM CIRCULAR SUM
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

    // The problem can be translated into removing all local minium 
    // values while finding the first greater
    // element on the left and on the right.
    // A stack based solution from previous 
    // problems can be applied as lee215 mentioned in his post.
    // Yeh Mujhe Samajh Nahi Aaya

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
    // Here our cost function is the Maximum 
    // Absolute Difference and not the path of absolute 
    // difference
    // I did with distance but it was giving TLE
    // Our Task is to minimize the maximum absolute difference
    // This will help us find the maximim absolute 
    // difference on the mimimum path

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
        {
            for (int i = 1; i < w.length(); ++i)
            {
                s.remove(w.substring(i));
            }
        }           
        int res = 0;
        for (String w : s) res += w.length() + 1;
        return res;
    }


    // 991. Broken Calculator
    // This approach Will Always Work 
    // In these kind of questions
    // Obviously if a number is odd it 
    // will always be benificial to make it even
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
    // Here We Will Use that fact that Sum of 
    // Coordinates on the diagonal are same
    // Copied Code
    // You can easily write it according to 
    // you only the above fact is important
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
                    if(v2 == v3) // Important Case [2 2 2 2 2 2]
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
                else if(v1+v2+v3 > target) j--;
                else i++;
            }
        }
        return count%mod;
    }


    // 1438. Longest Continuous Subarray With
    // Absolute Diff Less Than or Equal to Limit
    // Simple Solution Using TreeMap to get the 
    // minimum and maximum elements

    // We can Simple Do this question using two PriorityQueues
    // or using a single TreeMap to get Maximum and minimum

    // TreeSet + Sliding Window;
    // Important Problem

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
    // but agar mei 1 2 3 , 3 4 5 banata hu toh 3 bhi subsequence bana sakta hei of length >=3
    // isliye humne aaisa kiya
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
    // Important Observation
    // This Problem is equivilant to partitian 
    // into subsets such that the diffenence is minimum
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
    // Put Dfs Call On Zero
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
    // Note That we are talking about mountain continous 
    // array and not any subsequence
    // So it is straightforward to come up with this solution
    // So find continous increasing count and continous 
    /// decreasing count

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
    // Here We Have created A HashMap which is actually storing
    // Valid count of Subarrays of length 1 and length 2;
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
            return b[1]-a[1]; // [[1,2],[1,4],[3,4]] because of this TC
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
    // The idea is to complete that job first 
    // which is going to end first
    // i.e assigning the first free slot in the range
    // 42/44 test cases passed

    // Here we Are Assgning the first Empty Index to the Job

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
    // Similar Work using TreeSet we did above
    // In the array we are marking 1 but here in treeset solution 
    // I am removig the used index
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
    // Row and Column kaa max nikal lo
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
    // Initializing rear to -1 is Important

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
    // Backtrackig Solution

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
        for(int i = 1;i<values.length;i++) dp[i] = Math.max(dp[i-1],values[i]+i);

        int score = 0;
        for(int i = values.length-1;i>0;i--) score = Math.max(score,dp[i-1] + values[i]-i);

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

    // 630. Course Schedule III
    // Very Important Problem

    // Suppose we have Selected
    // courses of Durations [10,20,40,30]
    // and 70 came so do we swap any selected course
    // with 70 , so the answer is no , we don't
    // beacuse that will increase the time
    // But Suppose now 5 came and we would definately 
    // like to update it with the max duration selected
    // course (40 -> 5) so that it will increase the chances to encorporate
    // the chances of selecting new courses becuase the time is less

    // while updating 40 -> 5 it is 100% sure that encorporating
    // it won't exceed the deadline of the selected course
    // because we are 100% sure that the course we swapped out have
    // had a deadline loweer than the current course (because of sorting it in asc ord of deadlines)


    public int scheduleCourse(int[][] courses) 
    {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue < Integer > queue = new PriorityQueue < > ((a, b) -> b - a);
        int time = 0;
        for (int[] c: courses) 
        {
            if (time + c[0] <= c[1]) 
            {
                queue.offer(c[0]);
                time += c[0];
            } 
            else if (!queue.isEmpty() && queue.peek() > c[0]) 
            {
                time += c[0] - queue.poll();
                queue.offer(c[0]);
            }
        }
        return queue.size();
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


    // Optimized Approach
    // Check Dryrun

    public long maxPoints(int[][] points)
    {
        long[] dp = new long[points[0].length];
        long max = 0;
        for(int i = 0;i<points[0].length;i++)
        {
            dp[i] = points[0][i];
            max = Math.max(max,dp[i]);
        }
        
        int n = points[0].length;
        for(int i = 1;i<points.length;i++)
        {
            
            // Using The Idea of best sight seeing pair question
            long[] lr = new long[n];
            long[] rl = new long[n];
            long[] temp = new long[n];
            
            lr[0] = dp[0];
            rl[n-1] = dp[n-1] - (n-1);
            
            
            for(int j = 1;j<n;j++)
            {
                lr[j] = Math.max(lr[j-1],dp[j]+j);
            }
            
            for(int j = n-2;j>=0;j--)
            {
                rl[j] = Math.max(rl[j+1],dp[j]-j);
            }
            
            for(int j = 0;j<n;j++)
            {
                temp[j] = Math.max(rl[j]+points[i][j]+j , lr[j]+points[i][j]-j);
            }
            dp = temp;
        }
        
        for(long ele : dp) max = Math.max(max,ele);
        return max;
    }


    // 1663. Smallest String With A Given Numeric Value
    // Important Solution
    // Kind Off Ratne Wala
    // Think Greedly
    // Store Largest At the end
    public String getSmallestString(int n, int k) 
    {
        char res[] = new char[n];
        // By this we are able to handle a lot of edge cases
        Arrays.fill(res, 'a');
        k -= n;
        
        while(k > 0)
        {
            res[--n] += Math.min(25, k);
            k -= Math.min(25, k);
        }
        return String.valueOf(res);
    }


    // 491. Increasing Subsequences
    // We just have to handel that we put call
    // on the duplicate values in the same context frame

    public List<List<Integer>> findSubsequences(int[] nums) 
    {
        List<List<Integer>> ans = new ArrayList<>();
        getAns(nums,0,ans,new ArrayList<>());
        
        return ans;
    }
    
    public void getAns(int[] nums , int start , List<List<Integer>> ans , List<Integer> temp)
    {
        if(temp.size() > 1)
        {
            List<Integer> t = new ArrayList<>();
            for(int ele : temp) t.add(ele);
            ans.add(t);
        }
        
        HashSet<Integer> set = new HashSet<>();
        for(int i = start ; i < nums.length;i++)
        {
            int val = nums[i];
            boolean added = false;
            if(temp.size() == 0 || val >= temp.get(temp.size()-1)) 
            {
                temp.add(val);
                added = true;
            }
            else continue;
            
            if(!set.contains(val)) 
            {
                set.add(val);
                getAns(nums,i+1,ans,temp);
            }
            
            if(added) temp.remove(temp.size()-1);
            added = false;
        }
    }


    // 853. Car Fleet
    // Simple Sorting + Stack

    public int carFleet(int target, int[] position, int[] speed) 
    {
        int[][] cars = new int[position.length][2];
        for(int i = 0;i<position.length;i++)
        {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        
        Arrays.sort(cars,(a,b)->{
           return a[0]-b[0]; 
        });
        Stack<Double> st = new Stack<>();
        for(int[] ele : cars)
        {
            double time = (target-ele[0])/(ele[1]*1.0); 
            while(st.size() > 0 && time >= st.peek())
            {
                st.pop();
            }
            st.push(time);
        }
        return st.size();
    }

    // 1567. Maximum Length of Subarray With Positive Product
    // Simple Solution
    // Get Rid Of OverFlow
    // Maintain First Negative and First positive

    public int getMaxLen(int[] arr)
    {
        int length = 0;
        
        int firstPos = -1;
        int firstNeg = -1;
        
        int pfm = 1;
        
        for(int i = 0;i<arr.length;i++)
        {
            int val = arr[i];
            if(val == 0)
            {
                firstPos = i;
                firstNeg = -1;
                pfm = 1;
                continue;
            }
            if(val < 0 && firstNeg == -1) firstNeg = i; 
            pfm *= ((val < 0) ? -1 : 1);
            
            if(pfm > 0) length = Math.max(length,i-firstPos);
            else if(pfm < 0) if(firstNeg!=-1) length = Math.max(length,i-firstNeg);
        }
        return length;
    }
    // 1367. Linked List in Binary Tree

    // Ajib Sawal And Code
    // [1,10]
    // [1,null,1,10,1,9]

    // [2,2,1]
    // [2,null,2,null,2,null,1]


    //TLE Solution
    public boolean isSubPath(ListNode head, TreeNode root) 
    {
        return checkPath(head,root,head);
    }
    
    public boolean checkPath(ListNode head , TreeNode root ,ListNode orig)
    {
        if(root == null && head!=null) return false;
        if(head == null) return true;
        
        boolean data = (root.val == head.val);
        boolean left = false;
        boolean right = false;
        
        if(data)
        {
            left = checkPath(head.next,root.left,orig);
            right = left || checkPath(head.next,root.right,orig);  
        }

        left = left || right || checkPath(orig,root.left,orig);
        right = right || left || checkPath(orig,root.right,orig);
        
        return right || left;
    }

    // Passed Lee's Code
    // This is almost impossible to come up in an interview
    public boolean isSubPath(ListNode head, TreeNode root) 
    {
        if (head == null) return true;
        if (root == null) return false;
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode root) 
    {
        if (head == null) return true;
        if (root == null) return false;
        return head.val == root.val && (dfs(head.next, root.left) || dfs(head.next, root.right));
    }

    // 1358. Number of Substrings Containing All Three Characters
    // Important Sliding Window

    public int numberOfSubstrings(String s) 
    {
        int i = 0;
        int j = 0;
        
        int a = 0;
        int b = 0;
        int c = 0;
        
        int count = 0;
        
        while(j < s.length())
        {
            char ch = s.charAt(j);
            if(ch == 'a') a++;
            else if(ch == 'b') b++;
            else if(ch == 'c') c++;
            
            while(a > 0 && b > 0 && c > 0)
            {
                count += s.length()-j; // Important , End Se Calculate Karre Hei Substrings
                char rem = s.charAt(i);
                if(rem == 'a') a--;
                else if(rem == 'b') b--;
                else if(rem == 'c') c--;
                i++;
            }
            j++;
        }
        return count;
    }

    
    // 462. Minimum Moves to Equal Array Elements II
    // My solution 

    public int minMoves2(int[] nums) 
    {
        Arrays.sort(nums);
        long[] pfx = new long[nums.length];
        pfx[0] = nums[0];
        for(int i=1;i<nums.length;i++)
        {
            pfx[i] = pfx[i-1] + nums[i];
        }
        
        int ans = (int)1e10;
        
        for(int i = 0;i<nums.length;i++)
        {
            long left = (i == 0) ? 0 : pfx[i-1];
            long right = (i == nums.length-1) ? 0 : pfx[nums.length-1]-pfx[i];
            
            
            long val =  nums[i];
            
            
            long a = (i == nums.length-1) ? 0 : right-(long)(nums.length-i-1)*val;
            long b = (i == 0) ? 0 : ((long)i*val) - left;
            
            ans = (int)Math.min(ans,a+b);
        }
        return ans;
    }


    // Discuss Approach
    // Not Sure How This works
    public int minMoves2(int[] nums) 
    {
        Arrays.sort(nums);
        int i = 0, j = nums.length-1;
        int count = 0;
        while(i < j){
            count += nums[j]-nums[i];
            i++;
            j--;
        }
        return count;
    }

    // 1769. Minimum Number of Operations to Move All Balls to Each Box
    // Simple Solution

    public int[] minOperations(String boxes) 
    {
        int[] res = new int[boxes.length()];
        for (int i = 0, ops = 0, cnt = 0; i < boxes.length(); ++i) 
        {
           res[i] += ops;
           cnt += boxes.charAt(i) == '1' ? 1 : 0;
           ops += cnt;
        }    
        for (int i = boxes.length() - 1, ops = 0, cnt = 0; i >= 0; --i) 
        {
            res[i] += ops;
            cnt += boxes.charAt(i) == '1' ? 1 : 0;
            ops += cnt;
        }
        return res;
    }



    // 1647. Minimum Deletions to Make Character Frequencies Unique
    // Simple Greedy

    public int minDeletions(String s) 
    {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->{
            return b-a;
        });

        for(int ele : map.values()) queue.add(ele);
        
        int ans = 0;
        int n = queue.remove();
        n--;
        while(queue.size() > 0)
        {
            int val = queue.remove();
            if(val < n)
            {
                n = val-1;;
                continue;
            }
            ans += val-n;
            if(n>0)n--;
        }
        return ans;
    }


    // 846. Hand of Straights
    // nlogn + n*gs solution

    public boolean isNStraightHand(int[] hand, int gs) 
    {
        int n = hand.length;
        if(n%gs!=0) return false;
        
        int[][] arr = new int[n/gs][2];
        for(int[]ele:arr) ele[0]=-1;
        
        Arrays.sort(hand);
        int i = 0;
        
        while(i < n)
        {
            int val = hand[i];
            for(int j = 0;j<arr.length;j++)
            {
                if(arr[j][1] < gs)
                {
                    if(val == arr[j][0]+1 || arr[j][0] == -1)
                    {
                        arr[j][0] = val;
                        arr[j][1]++;
                        break;
                    }
                }
            }
            i++;
        }
        
        for(int[]ele:arr) if(ele[1] != gs) return false;
        return true;
    }

    // Good Solution
    public boolean isNStraightHand(int[] hand, int W)
    {
        if(hand.length % W != 0) return false;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int elem: hand) minHeap.add(elem);

        while(!minHeap.isEmpty())
        {
            int head = minHeap.poll();
            for(int i=1; i<W; i++)
            {
                if(!minHeap.remove(head+i)) return false;
            }
        }
        return true;
    }

    // 524. Longest Word in Dictionary through Deleting
    // Simple Problem

    public String findLongestWord(String s, List<String> d) 
    {
        String res = "";
        if (s.length() == 0 || d.size() == 0) 
        {
            return res;
        }
        for (String str : d) 
        {
            int resLen = res.length();
            int strLen = str.length();
            if (isSequence(s, str) && (strLen > resLen || (strLen == resLen && str.compareTo(res) < 0))) 
            {
                res = str;
            }
        }
        return res;
    }
    
    private boolean isSequence(String s, String d) 
    {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < d.length()) 
        {
            while (i < s.length() && s.charAt(i) != d.charAt(j)) 
            {
                i++;
            }
            if (i < s.length()) 
            {
                i ++;
                j ++;
            }
        }
        return j == d.length();
    }

    // 1696. Jump Game VI
    // Very Important Solution and Approach
    // It is kind of a sliding window problem
    // with max window maintained upto i-k
    // Heap Solution
    public int maxResult(int[] nums, int k) 
    {
        // a[0] = score , a[1] = idx 
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{
           return b[0]-a[0]; 
        });
        queue.add(new int[]{nums[0],0});
        int res = nums[0];
        for(int i = 1;i<nums.length;i++)
        {
            while(queue.peek()[1] < i-k) queue.remove();
            res = nums[i]+queue.peek()[0];
            queue.add(new int[]{res,i});
        }
        // System.out.println(queue.peek()[1]);
        // We have to create res variable explicitely because 
        // It will Not be always true that the element which is on the top
        // will always be ending on the last index
        return res;
    }


    // 1838. Frequency of the Most Frequent Element
    // Very Important Sliding Window Problem

    public int maxFrequency(int[] nums, int s) 
    {
        long k = s;
        Arrays.sort(nums);
        int i = 0;
        int j = 1;
        long sum = nums[0];
        int max = 1;
        while(j < nums.length)
        {
            int val = nums[j];
            
            if(sum+k >= val*(j-i))
            {
                max = Math.max(max,j-i+1);
                sum+=val;
                j++;
            }
            else
            {
                while(sum+k < val*(j-i))
                {
                    sum-=nums[i];
                    i++;
                }
            }
        }
        return max;
    }


    // 1823. Find the Winner of the Circular Game
    // This is the Josephus Problem
    // Check Bits Notes there we have linear time solution
    // But we are doing it in O(k*n) time and O(n) space

    public int findTheWinner(int n, int k) 
    {
        // Initialisation of the LinkedList
        LinkedList<Integer> participants = new LinkedList<>();
        for (int i = 1; i <= n; i++) 
        {
            participants.add(i);
        }
        
        int lastKilled = 0;
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < k-1; j++) 
            {
                participants.add(participants.poll());
            }
            lastKilled = participants.poll();
        }
        return lastKilled;
    }


    // 1338. Reduce Array Size to The Half
    // Simple Solution

    public int minSetSize(int[] arr) 
    {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int x : arr) cnt.put(x, cnt.getOrDefault(x, 0) + 1);

        int[] frequencies = new int[cnt.values().size()];
        int i = 0;
        for (int freq : cnt.values()) frequencies[i++] = freq;
        Arrays.sort(frequencies);

        int ans = 0, removed = 0, half = arr.length / 2;
        i = frequencies.length - 1;
        while (removed < half) 
        {
            ans += 1;
            removed += frequencies[i--];
        }
        return ans;
    }



    // 386. Lexicographical Numbers
    // Approach 1
    // O(nlogn) time // O(1) space
    public List<Integer> lexicalOrder(int n) 
    {
        List<Integer> temp = new ArrayList<>();
        for(int i = 1;i<=n;i++) temp.add(i);
        Collections.sort(temp,(a,b)->{return (a+"").compareTo((b+""));});
        return temp;
    }

    // Approach 2
    // DFS Solution

    // The idea is pretty simple. 
    // If we look at the order we can find out we just keep adding 
    // digit from 0 to 9 to every digit and make it a tree.
    // Then we visit every node in pre-order. 
    //        1        2        3    ...
    //       /\        /\       /\
    //    10 ...19  20...29  30...39   ....

    public List<Integer> lexicalOrder(int n) 
    {
        List<Integer> res = new ArrayList<>();
        for(int i=1;i<10;++i)
        {
          dfs(i, n, res); 
        }
        return res;
    }
    
    public void dfs(int cur, int n, List<Integer> res)
    {
        if(cur>n) return;
        res.add(cur);
        for(int i=0;i<10;++i)
        {
            if(10*cur+i>n) return;
            dfs(10*cur+i, n, res);
        }
    }


    // 1347. Minimum Number of Steps to Make Two Strings Anagram
    // Simple Solution

    // Could be optimized using character array
    public int minSteps(String s, String t) 
    {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        int count = 0;
        for(int i = 0;i<t.length();i++)
        {
            char ch = t.charAt(i);
            if(map.containsKey(ch))
            {
                map.put(ch,map.get(ch)-1);
                if(map.get(ch)== 0) map.remove(ch);
                count++;
            }
        }
        return t.length()-count;
    }


    // 870. Advantage Shuffle
    // Simple Solution Using TreeMap

    public int[] advantageCount(int[] nums1, int[] nums2) 
    {
        int[] ans = new int[nums1.length];
        Arrays.fill(ans,-1);
        TreeMap<Integer,Integer> map = new TreeMap<>();
        
        for(int ele : nums1) map.put(ele,map.getOrDefault(ele,0)+1);
        
        for(int i = 0;i<nums2.length;i++)
        {
            int val = nums2[i];
            Integer res = map.higherKey(val);
            if(res == null) continue;
            ans[i] = res;
            map.put(res,map.getOrDefault(res,0)-1);
            if(map.get(res) == 0) map.remove(res);
        }
        
        for(int i = 0;i<ans.length;i++)
        {
            if(ans[i] == -1)
            {
                int val = map.firstKey();
                ans[i] = val;
                map.put(val,map.getOrDefault(val,0)-1);
                if(map.get(val) == 0) map.remove(val);
            }
        }
        return ans;
    }


    // 436. Find Right Interval
    // Simple TreeMap solution

    public int[] findRightInterval(int[][] intervals) 
    {
        TreeMap<Integer, Integer> startPoints = new TreeMap<>();
        for(int i=0; i<intervals.length; i++)
        {
            startPoints.put(intervals[i][0], i);
        }
        
        int[] ans = new int[intervals.length];
        for(int i=0; i<intervals.length; i++)
        {
            Integer nextClosestStartPoint = startPoints.ceilingKey(intervals[i][1]);
            ans[i]= nextClosestStartPoint==null ? -1 : startPoints.get(nextClosestStartPoint);
        }
        return ans;
    }


    // 1405. Longest Happy String

    public class pair
    {
        char ch;
        int c;
        pair(char ch , int c)
        {
            this.ch=ch;
            this.c=c;
        }
    }
    public String longestDiverseString(int a, int b, int c) 
    {
        PriorityQueue<pair> queue = new PriorityQueue<>((d,e)->{
            return e.c-d.c;
        });
        if(a>0) queue.add(new pair('a',a));
        if(b>0) queue.add(new pair('b',b));
        if(c>0) queue.add(new pair('c',c));
        
        StringBuilder sb = new StringBuilder();
        
        while(queue.size() > 1)
        {
            pair f = queue.remove();
            pair s = queue.remove();
            
            if(f.c == 1) 
            {
                sb.append(f.ch);
                f.c--;
            }
            else if(f.c >= 2)
            {
                sb.append(f.ch);
                sb.append(f.ch);
                f.c-=2;
            }
            
            if(s.c == 1) 
            {
                sb.append(s.ch);
                s.c--;
            }
            else if(s.c >= 2)
            {
                sb.append(s.ch);
                sb.append(s.ch);
                s.c-=2;
            }

            
            if(f.c > 0) queue.add(f);
            if(s.c > 0) queue.add(s);
        }
                
        if(queue.size() == 1)
        {
            pair r = queue.remove();
            char l = r.ch;
            int n = r.c;
            
            int i = 0;
            
            while(i <= sb.length() && n > 0)
            {
                char prev = (i-1>=0) ? sb.charAt(i-1) : '#';
                char prevPrev = (i-2 < 0) ? '#' : sb.charAt(i-2);
                char curr = (i<sb.length()) ? sb.charAt(i) : '#';
                char next = (i+1>sb.length()-1) ? '#' : sb.charAt(i+1);
                
                if(prev == prevPrev && l == prev) i++;
                else if(curr==next && l == curr) i++;
                else if(prev == curr && l == curr) i++;
                else
                {
                    sb.insert(i,l);
                    n--;
                }
            }
        }
        return sb.toString();
    }


    // 1498. Number of Subsequences That Satisfy the Given Sum Condition
    // Simple Two Sum Problem

    // Intentionally Pre Computed The Power
    // Due to some conversion Issues
    public int numSubseq(int[] A, int target)
    {
        Arrays.sort(A);
        int res = 0, n = A.length, l = 0, r = n - 1, mod = (int)1e9 + 7;
        int[] pows = new int[n];
        pows[0] = 1;
        for (int i = 1 ; i < n ; ++i) pows[i] = pows[i - 1] * 2 % mod;
        while (l <= r) 
        {
            if (A[l] + A[r] > target) 
            {
                r--;
            } 
            else 
            {
                res = (res + pows[r - l++]) % mod;
            }
        }
        return res;
    }


    // 962. Maximum Width Ramp
    // Here Sorting Is Important as well
    // Can't be done without sorting
    // Simple Sorting + Stack
    // There is an O(N) solution check that as well
     public int maxWidthRamp(int[] nums) 
    {
        int[][] arr = new int[nums.length][2];
        for(int i = 0;i<nums.length;i++)
        {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        
        Arrays.sort(arr,(a,b)->{
            return a[0]-b[0];
        });
        Stack<Integer> st = new Stack<>();
        
        int max = 0;
        int minIdx = -1;
        
        for(int[] ele : arr)
        {
            while(st.size() > 0 && st.peek() > ele[1]) st.pop();
            if(st.size() > 0) max = Math.max(max,ele[1]-minIdx);
            if(st.size() == 0) minIdx = ele[1];
            st.push(ele[1]);
        }
        
        if(st.size() > 1)
        {
            int rem = st.pop();
            while(st.size() > 1) st.pop();
            max = Math.max(rem-st.pop(),max);
        }
        return max;
    }

    // 1052. Grumpy Bookstore Owner
    // Simple sliding Window

    public int maxSatisfied(int[] cust, int[] gr, int min)
    {
        int n = cust.length;
        int[] lr = new int[n];
        int[] rl = new int[n];
        
        lr[0] = (gr[0] == 1) ? 0 : cust[0];
        rl[n-1] = (gr[n-1] == 1) ? 0 : cust[n-1];
        
        for(int i = 1;i<n;i++)
        {
            if(gr[i] == 0) lr[i] = cust[i] + lr[i-1];
            else lr[i] = lr[i-1];
        }
        
        for(int i = n-2;i>=0;i--)
        {
            if(gr[i] == 0) rl[i] = cust[i] + rl[i+1];
            else rl[i] = rl[i+1];
        }
        
        int i = 0;
        int j = 0;
        
        int sum = 0;
        int max = 0;
        while(j < n)
        {
            if((j-i) <= min-1) sum+=cust[j];
            if((j-i) == min-1)
            {
                int left = (i == 0) ? 0 : lr[i-1];
                int right = (j == n-1) ? 0 : rl[j+1];
                max = Math.max(max,sum+left+right);
                sum -= cust[i];
                i++;
            }
            j++;
        }
        return max;
    }


    // 1286. Iterator for Combination
    // A simple solution would be to simply backtrack and
    // generate all the Combinations
    // Brute Force Solution

    ArrayList<String> comb;
    int pointer, cLen;
    public CombinationIterator(String characters, int combinationLength) 
    {
        this.comb = new ArrayList<>();
        this.pointer = 0;
        this.cLen = combinationLength;
        this.combinations(characters, 0, "");
    }
    public void combinations(String s, int index, String com)
    {
        if(com.length() == this.cLen)
        {
            this.comb.add(com);
            return ;
        }
        if(index >= s.length()) return ;
        for(int i = index; i < s.length(); i++) combinations(s, i + 1, com + String.valueOf(s.charAt(i)));
    }
    public String next() 
    {
        return this.comb.get(this.pointer++);
    }
    
    public boolean hasNext() 
    {
        return this.pointer < this.comb.size();
    }

    // 1371. Find the Longest Substring Containing Vowels in Even Counts

    // Its a HashMap Counting Problem
    // 0 1 0 
    // 1 0 1
    public int findTheLongestSubstring(String s) 
    {
        HashSet<Character> set= new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        
        HashMap<Integer, Integer> map= new HashMap<>();
        map.put(0, -1);
        int xor=0; // good Choice to Use XOR
        int maxLen=0;
        
        for(int i=0; i<s.length(); i++)
        {
            if(set.contains(s.charAt(i))) xor^=(s.charAt(i)-'a'+1);
            if(!map.containsKey(xor)) map.put(xor, i);
            maxLen=Math.max(maxLen, i-map.get(xor));
        }
        return maxLen;
    }


    // 910. Smallest Range II
    // Same Problem as we did Minimum Heights I gfg ADOBE kaa khatarnaak wala question
    public int smallestRangeII(int[] arr, int k) 
    {
       int n = arr.length;
       if(n==1) return 0;
       Arrays.sort(arr);
       int diff = arr[n-1] - arr[0];
       int minimum,maximum;
       for(int i=1;i<n;i++)
       {
           maximum = Math.max(arr[i-1]+k, arr[n-1]-k);
           minimum = Math.min(arr[0]+k, arr[i]-k);
           diff = Math.min(diff,maximum-minimum);
       }
       return diff;
    }

    /// 904. Fruit Into Baskets

    // sliding Window
    public int totalFruit(int[] arr) 
    {
        int n = arr.length, si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[40000 + 1];

        while (ei < n) 
        {
            if (freq[arr[ei++]]++ == 0) count++;

            while (count > 2)
            {
                if (freq[arr[si++]]-- == 1) count--;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }


    // 954. Array of Doubled Pairs
    // Sorting On the basis of Absolute values is an Important Step

    public boolean canReorderDoubled(int[] A)
    {
        Map<Integer, Integer> count = new HashMap();
        for (int x: A) count.put(x, count.getOrDefault(x,0)+1);
        
        Integer[] B = new Integer[A.length];
        for (int i = 0; i < A.length; ++i) B[i] = A[i];
        Arrays.sort(B, Comparator.comparingInt(Math::abs));

        for (int x: B) 
        {
            if (count.get(x) == 0) continue;
            if (count.getOrDefault(2*x, 0) <= 0) return false;

            count.put(x, count.get(x) - 1);
            count.put(2*x, count.get(2*x) - 1);
        }
        return true;
    }



    // 2096. Step-By-Step Directions From a Binary Tree Node to Another
    // Good Problem Based On LCA

    public String getDirections(TreeNode root, int startValue, int destValue) 
    {
        if(root == null) return "";
        StringBuilder direction = new StringBuilder();
        TreeNode lca= findLCA(root, startValue, destValue); // Common parent        
        List<String> paths = new ArrayList<>();
        find(lca, startValue, new StringBuilder(), paths);
        find(lca, destValue, new StringBuilder(), paths);
        for(int i=0;i<paths.get(0).length();i++) direction.append("U");
        direction.append(paths.get(1));
        return direction.toString();
    }
    
    public TreeNode findLCA(TreeNode root, int start, int end) // Nice way to find LCA without post processing
    {
        if(root==null) return null;
        if(root.val == start || end == root.val) return root;
        TreeNode left = findLCA(root.left, start, end);
        TreeNode right = findLCA(root.right, start, end);
        if(left!=null && right!=null) return root;
        return left!=null? left: right;
    }
    
    public void find(TreeNode root, int value, StringBuilder path, List<String> paths)
    {    
        if(root.val == value)
        {
            paths.add(new String(path));
            return;
        }
        if(root.left!=null) find(root.left, value, path.append("L"), paths);
        if(root.right!=null) find(root.right, value, path.append("R"), paths);        
        path.deleteCharAt(path.length()-1);
        return;
    }


    // 1282. Group the People Given the Group Size They Belong To
    // Simple HashMap Problem

    public List<List<Integer>> groupThePeople(int[] groupSizes) 
    {
        Map<Integer, List<Integer>> map=new HashMap<>();
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<groupSizes.length;i++) 
        {
            if(!map.containsKey(groupSizes[i])) map.put(groupSizes[i], new ArrayList<>());
            List<Integer> cur=map.get(groupSizes[i]);
            cur.add(i);
            if(cur.size()==groupSizes[i]) 
            {
                res.add(cur);
                map.remove(groupSizes[i]);
            }
        }
        return res;
    }

    // 1109. Corporate Flight Bookings
    // Similar to range sum problem

    public int[] corpFlightBookings(int[][] bookings, int n) 
    {
        int[] ans = new int[n];
        for (int[] booking : bookings) 
        {
            int i = booking[0]-1;
            int j = booking[1];
            int seats = booking[2];
            ans[i] += seats;
            if (j != n)
                ans[j] -= seats;
        }
        
        int count = 0;
        for (int i = 0; i < ans.length; i++) 
        {
            ans[i] += count;
            count = ans[i];
        }
        return ans;
    }

    // 539. Minimum Time Difference

    public int findMinDifference(List<String> timePoints) 
     {
        int res = Integer.MAX_VALUE;
        int N = timePoints.size();
        int[] c = new int[N];
        
        for (int i = 0; i < N; i++) 
        {
            String s = timePoints.get(i);
            c[i] = Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3, 5));
        }
        Arrays.sort(c);
        for (int i = 1; i < N; i++) 
        {
            res = Math.min(res, c[i] - c[i - 1]);
        }
        // Thee last can be closest to the first one
        // That's why
        res = Math.min(res, c[0] + (24*60 - c[N - 1]));
        return res;
    }



    // 988. Smallest String Starting From Leaf
    // Simple Solution
    // Here ye are not sorting all the strings after traversing
    // We are simply comparing the yay we find min or max in the array

    String ans = "~";
    public String smallestFromLeaf(TreeNode root)
    {
        dfs(root, new StringBuilder());
        return ans;
    }

    public void dfs(TreeNode node, StringBuilder sb) 
    {
        if (node == null) return;
        sb.append((char)('a' + node.val));

        if (node.left == null && node.right == null) 
        {
            sb.reverse();
            String S = sb.toString();
            sb.reverse();
            if (S.compareTo(ans) < 0) ans = S;
        }

        dfs(node.left, sb);
        dfs(node.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
    // 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
    // Good Question
    public int minDifference(int[] A) 
    {
        int n = A.length;
        if (n < 5) return 0;
        Arrays.sort(A);
        int res = (int)1e11;
        
        for(int i = 1;i<=4;i++)
        {
            res = Math.min(res,A[n-i]-A[4-i]);
        }
        return res;
    }


    // 1760. Minimum Limit of Balls in a Bag
    // Important Binary Search Problem
    // With heap it will give TLE

    public int minimumSize(int[] nums, int maxOperations) 
    {
        int lo = 1, hi = 1_000_000_001;
        while (lo < hi) 
        {
            int size = lo + hi >> 1;
            if(canDivide(nums, size, maxOperations)) 
            {
                hi = size;
            }
            else 
            {
                lo = size + 1;
            }
        }
        return lo;
    }
    private boolean canDivide(int[] nums, int size, int maxOperations)
    {
        int cnt = 0;
        for (int balls : nums) 
        {
            cnt += balls / size - (balls % size == 0 ? 1 : 0);
            if (cnt > maxOperations)
            {
                return false;
            }
        }
        return true;
    }


    // 1578. Minimum Time to Make Rope Colorful
    // simple Problem
    // We have to put 1 ballon in a group so we will burst all the ballons with min values except 1 with
    // the highest value add sum-max t answer

    public int minCost(String s, int[] t) 
    {
        int ans = 0;
        int i = 1;
        int max = t[0];
        int sum = t[0];
        while(i < s.length())
        {
            if(s.charAt(i) == s.charAt(i-1))
            {
                sum += t[i];
                max = Math.max(max,t[i]);
                i++;
            }
            else
            {
                ans += sum-max;
                max = t[i];
                sum = t[i];
                i++;
            }
        }
        ans += sum-max;
        return ans;
    }



    // 945. Minimum Increment to Make Array Unique
    // Simple Counting Problem

    public int minIncrementForUnique(int[] nums) 
    {
        int count = 0;
        Arrays.sort(nums);
        int prevNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= prevNum) {
                prevNum++;
                count += (prevNum - nums[i]);
            } else {
                prevNum = nums[i];
            }
        }
        return count;
    }


    // 1905. Count Sub Islands
    // DFS

    public int countSubIslands(int[][] grid1, int[][] grid2)
    {
        int count = 0;
        int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        for(int i = 0;i<grid1.length;i++)
        {
            for(int j = 0;j<grid1[0].length;j++)
            {
                if(grid2[i][j] == 1)
                {
                    grid2[i][j] = 0;
                    boolean res = (grid1[i][j] == 1) ? true : false;
                    res = dfs(i,j,grid1,grid2,dir) && res;
                    // for(int[] ele : grid2) System.out.println(Arrays.toString(ele));
                    // System.out.println();
                    if(res) count++;
                }
            }
        }
        return count;
    }
    
    public boolean dfs(int i,int j,int[][]g1,int[][]g2,int[][]dir)
    {
        boolean res = true;
        for(int[] ele : dir)
        {
            int x = i+ele[0];
            int y = j+ele[1];
            
            if(x<0||y<0||x>=g1.length||y>=g1[0].length||g2[x][y]==0) continue;
            g2[x][y] = 0;
            if(g1[x][y] == 0) res = false;
            res = dfs(x,y,g1,g2,dir) && res;
        }
        return res;
    }



    // 1574. Shortest Subarray to be Removed to Make Array Sorted
    // The Solution I wrote was passing 112/117 cases
    // So this is th copied solution form discussion 
    // Please do dryrun
    // 2 2 2 1 1 1
    // [16,10,0,3,22,1,14,7,1,12,15]
    // [8,0,92,75,54,1,45,62,38,71,65,28,89,85,78,84,41,11,94,35,49,12,9,87,74,53,48,43,92,77,66,49,27,11,8,81,60,66,6,63,63,37,27,62,82,60,42,64]
    // Dryrun karke samajh lena iise
    public int findLengthOfShortestSubarray(int[] arr)
    {
        int left = 0;
        while(left + 1 < arr.length && arr[left] <= arr[left+1]) left++;
        if(left == arr.length - 1) return 0;
        
        int right = arr.length - 1;
        while(right > left && arr[right-1] <= arr[right]) right--;
        int result = Math.min(arr.length - left - 1, right);
        
        int i = 0;
        int j = right;
        while(i <= left && j < arr.length) 
        {
            if(arr[j] >= arr[i]) 
            {
                result = Math.min(result, j - i - 1);
                i++;
            }
            else j++;
        }
        return result;
    }


    // 877. Stone Game
    // Exactly Same as Optimal Game Strategy

    public boolean stoneGame(int[] piles) 
    {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for(int gap = 0;gap<n;gap++)
        {
            for(int i = 0,j = gap;j<n;i++,j++)
            {
                if(gap == 0)
                {
                    dp[i][j] = piles[i];
                    continue;
                }
                if(gap == 1)
                {
                    dp[i][j] = Math.max(piles[i],piles[j]);
                    continue;
                }
                int left = Math.min(dp[i+2][j],dp[i+1][j-1]);
                int right = Math.min(dp[i+1][j-1],dp[i][j-2]);
                dp[i][j] = Math.max(piles[i]+left,piles[j]+right);
            }
        }
        int sum = 0;
        for(int ele : piles) sum+=ele;
        int v1 = dp[0][n-1];
        int v2 = sum-v1;
        
        if(v1 > v2) return true;
        return false;
    }


    // 1406. Stone Game III
    // Memoized Solution

    public String stoneGameIII(int[] piles)
    {
        int[] presum =  Arrays.copyOf(piles, piles.length);
        for (int i = presum.length - 2; i >= 0; i--) presum[i] += presum[i + 1];
        int[] dp = new int[piles.length];
        Arrays.fill(dp,-(int)1e9);
        int r1 =  dfs(presum, 0, dp);
        int r2 = presum[0] - r1;
        if(r1 > r2) return "Alice";
        else if(r2 > r1) return "Bob";
        return "Tie";
        
    }
    public int dfs(int[] presum, int p, int[]memo)
    {
        if (p  >= presum.length) return 0; 
        if (memo[p] != -(int)1e9) return memo[p];
        int res = -(int)1e9, take = 0;
        for (int i = 1; i <= 3 && p+i <= memo.length; i++) 
        {
            int p_i = (p+i >= presum.length) ? 0 : presum[p+i];
            take = presum[p] - p_i;
            res = Math.max(res, take + p_i - dfs(presum, p + i, memo));
        }
        memo[p] = res;
        return res;
    }


    // Tabulation

    public String stoneGameIII(int[] piles)
    {
        int[] presum =  Arrays.copyOf(piles, piles.length);
        for (int i = presum.length - 2; i >= 0; i--) presum[i] += presum[i + 1];
        int[] dp = new int[piles.length];
        Arrays.fill(dp,-(int)1e9);
        tab(presum,dp);
        int r1 =  dp[0];
        int r2 = presum[0] - r1;
        if(r1 > r2) return "Alice";
        else if(r2 > r1) return "Bob";
        return "Tie";
    }
    public void tab(int[] presum,int[]memo)
    {
        for(int p = presum.length-1;p>=0;p--)
        {
            int res = -(int)1e9, take = 0;
            
            for (int i = 1; i <= 3 && p+i <= memo.length; i++) 
            {
                int p_i = (p+i >= presum.length) ? 0 : presum[p+i];
                take = presum[p] - p_i;
                res = Math.max(res, take + p_i - ((p+i>=presum.length) ? 0 : memo[p + i]));
            }
            memo[p] = res;
        }
    }

    // 1510. Stone Game IV
    // Already Did Similar Problem

    public boolean winnerSquareGame(int n) 
    {
        boolean[] dp = new boolean[n+1];
        dp[0] = false;
        for(int i = 1;i<=n;i++)
        {
            for(int k =1;k<=Math.sqrt(i);k++)
            {
                dp[i] = dp[i] || !dp[i-(k*k)];
            }
        }
        return dp[n];
    }


    // 1685. Sum of Absolute Differences in a Sorted Array

    public int[] getSumAbsoluteDifferences(int[] nums) 
    {
        int[] pfx = new int[nums.length];
        pfx[0] = nums[0];
        for(int i=1;i<nums.length;i++) pfx[i] = pfx[i-1] + nums[i];
        
        int[] ans = new int[nums.length];
        
        for(int i = 0;i<nums.length;i++)
        {
            int left = (i == 0) ? 0 : pfx[i-1];
            int right = (i == nums.length-1) ? 0 : pfx[nums.length-1]-pfx[i];
            ans[i] = (i*nums[i])-left + right - (nums.length-i-1)*nums[i];
        }
        return ans;
    }

    // 467. Unique Substrings in Wraparound String
    // Simple Solution

    public int findSubstringInWraproundString(String p)
    {
        int[] arr = new int[26];
        int i = 0;
        int j = 1;
        arr[p.charAt(0)-'a'] = 1;
        while(j < p.length())
        {
            int ch = p.charAt(j)-'a';
            int prev = p.charAt(j-1)-'a';
            // Max Used To Remove Duplicacy
            if((prev == 25 && ch == 0) || (prev+1==ch)) arr[ch] = Math.max(arr[ch],j-i+1);
            else
            {
                i = j;
                arr[ch] = Math.max(arr[ch],1);
            }
            j++;
        }
        int sum = 0;
        for(int ele : arr) sum+=ele;
        return sum;
    }


    // 676. Implement Magic Dictionary
    // Using Trie

    class Node
    {
        Node[] next= new Node[26];
        boolean isEnd;
    }
    Node root= new Node();

    public MagicDictionary() 
    {
        
    }
    
    public void buildDict(String[] dict) 
    {
        for (String s: dict)
        {
            Node cur= root;
            for (char c: s.toCharArray())
            {
                if (cur.next[c-'a']==null) cur.next[c-'a']=new Node();
                cur=cur.next[c-'a'];
            }
            cur.isEnd=true;
        }
    }

    public boolean search(String word) 
    {
        return dfs(root, word, 0, false);
    }
    public boolean dfs(Node cur, String word, int idx, boolean used)
    {
        if (cur==null) return false;
        if (idx==word.length()) return cur.isEnd && used;
        char c= word.charAt(idx);
        for (int i=0; i<26; i++)
        {
            if (used && (c-'a')!=i) continue; // Means that we have used a chnace of changing
            // but we are still un un matched character
            if (dfs(cur.next[i], word, idx+1, used || (c-'a')!=i)) return true;
            // if (c-'a')!=i set used to true else be it false
        }
        return false;
    }


    // 861. Score After Flipping Matrix
    // First make first column 1 by toggling on the rows
    // Then traverse on every column check no of 1
    // 1 > 0 continue;
    // 0 > 1 toggle whole columns


    public int matrixScore(int[][] grid) 
    {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0;i<m;i++)
        {
            if(grid[i][0] == 0)
            {
                for(int j = 0;j<n;j++)
                {
                    grid[i][j] = (grid[i][j] == 0) ? 1 : 0;
                }
            }
        }
        
        for(int i = 1;i<n;i++)
        {
            int c = 0;
            for(int k = 0;k<m;k++)
            {
                if(grid[k][i] == 1)  c++;
            }
            
            if(m-c > c)
            {
                for(int j = 0;j<m;j++)
                {
                    grid[j][i] = (grid[j][i] == 0) ? 1 : 0;
                }
            }
        }
        
        int sum = 0;
        
        for(int i = 0;i<m;i++)
        {
            StringBuilder t = new StringBuilder();
            for(int j = 0;j<n;j++) t.append(grid[i][j]);
            sum+=Integer.parseInt(t.toString(),2);
        }
        
        return sum;
    }

    // 54. Spiral Matrix

    public List<Integer> spiralOrder(int[][] matrix) 
    {
        int n = matrix.length;
        int m = matrix[0].length;
        int x = 0;
        int y = 0;
        int dx = n-1;
        int dy = m-1;
        
        List<Integer> ans = new ArrayList<>();
        
        while(ans.size() < m*n)
        {
            for(int i = y;i<=dy;i++) ans.add(matrix[x][i]);
            
            if(ans.size() == m*n) break;
            
            for(int i = x+1;i<=dx;i++) ans.add(matrix[i][dy]);
                
            if(ans.size() == m*n) break;
            
            for(int i = dy-1;i>=y;i--) ans.add(matrix[dx][i]);
            
            if(ans.size() == m*n) break;
            
            for(int i = dx-1;i>x;i--) ans.add(matrix[i][y]);
            y++;
            x++;
            dy--;
            dx--;
        }
        return ans;
    }

    // 2326. Spiral Matrix IV
    // Concept is same as above
    public int[][] spiralMatrix(int m, int n, ListNode head) 
    {
        int[][] matrix = new int[m][n];
        for(int[]ele : matrix) Arrays.fill(ele,-1);
        
        int temp = n;
        n = m;
        m = temp;
        int x = 0;
        int y = 0;
        int dx = n-1;
        int dy = m-1;
        
        while(head!=null)
        {
            for(int i = y;i<=dy;i++) 
            {
                matrix[x][i] = head.val;
                head = head.next;
                if(head == null) return matrix;
            }
            
            if(head == null) return matrix;
            
            for(int i = x+1;i<=dx;i++) 
            {
                matrix[i][dy] = head.val;
                head = head.next;
                if(head == null) return matrix;
            }
                
            if(head == null) return matrix;
            
            for(int i = dy-1;i>=y;i--) 
            {
                matrix[dx][i] = head.val;
                head = head.next;
                if(head == null) return matrix;
            }
            
            if(head == null) return matrix;
            
            for(int i = dx-1;i>x;i--) 
            {
                matrix[i][y] = head.val;
                head = head.next;
                if(head == null) return matrix;
            }
            y++;
            x++;
            dy--;
            dx--;
        }
        return matrix;
    }


    // 2327. Number of People Aware of a Secret
    // Good Problem

    public int peopleAwareOfSecret(int n, int delay, int forget)
    {
        int mod = (int)1e9+7;
        
        int[] dp = new int[n];
        
        dp[0] = 1;
        
        for(int i=1;i<n;i++)
        {
            for(int j = Math.max(i-forget+1,0);j<i&&i-j>=delay;j++)
            {
                dp[i] = (dp[i] + dp[j])%mod;
            }
        }
        int sum = 0;
        for(int i = n-1;i>=n-forget;i--) sum = (sum + dp[i])%mod;
        return sum;
    }


    // 1124. Longest Well-Performing Interval
    // JAB pfx greater than 0 hei toh map.get(pfx-1) se length nikalenge
    // Agar pfx >=1 hei toh length is the answer
    public int longestWPI(int[] hours)
    {
        int len = 0;
        int pfx = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        
        for(int i = 0;i<hours.length;i++)
        {
            pfx += (hours[i] > 8) ? 1 : -1;
            if(!map.containsKey(pfx)) map.put(pfx,i);
            if(map.containsKey(pfx-1) && pfx <= 0) len = Math.max(i-map.get(pfx-1),len);
            else if(pfx > 0) len = Math.max(len,i+1);
        }
        return len;
    }


    // 916. Word Subsets
    // Simple Solution

    public List<String> wordSubsets(String[] A, String[] B) 
  {
        int[] count = new int[26], tmp;
        int i;
        for (String b : B) 
        {
            tmp = counter(b);
            for (i = 0; i < 26; ++i) count[i] = Math.max(count[i], tmp[i]);
        }
        List<String> res = new ArrayList<>();
        for (String a : A) 
        {
            tmp = counter(a);
            for (i = 0; i < 26; ++i) if (tmp[i] < count[i]) break;
            if (i == 26) res.add(a);
        }
        return res;
    }

    public int[] counter(String word) 
    {
        int[] count = new int[26];
        for (char c : word.toCharArray()) count[c - 'a']++;
        return count;
    }


    // 1344. Angle Between Hands of a Clock
    // Its A Math Question
    // The minute hand moves 360 degrees in 60 minute(or 6 degrees in one minute)
    // Hour hand moves 360 degrees in 12 hours(or 0.5 degrees in 1 minute).
    public double angleClock(int hour, int minutes) 
    {
         // Degree covered by hour hand (hour area + minutes area)
        double h = (hour%12 * 30) + ((double)minutes/60 * 30); // This Is Tricky to find
        
         // Degree covered by minute hand (Each minute = 6 degree)
        double m = minutes * 6;
        
         // Absolute angle between them
        double angle = Math.abs(m - h);
        
         // If the angle is obtuse (>180), convert it to acute (0<=x<=180)
        if (angle > 180) angle = 360.0 - angle;
        
        return angle;
    }


    // 738. Monotone Increasing Digits
    // Simple Problem

    public int monotoneIncreasingDigits(int N) 
    {
        char[] ch = String.valueOf(N).toCharArray();
        int mark = ch.length;
        for(int i = ch.length-1;i>0;i--)
        {
            if(ch[i]<ch[i-1])
            {
                mark = i-1;
                ch[i-1]--;
            }
        }
        for(int i = mark+1;i<ch.length;i++) ch[i] = '9';
        return Integer.parseInt(new String(ch));
    }


    // 433. Minimum Genetic Mutation
    // Simple BFS

    public int minMutation(String s, String end, String[] bank)
    {
        HashSet<String> set = new HashSet<>();
        for(String ele : bank) set.add(ele);
        char[] arr = new char[]{'A','C','G','T'};
        StringBuilder start = new StringBuilder(s);
        HashSet<String> visited = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        
        queue.add(start.toString());
        visited.add(start.toString());
        int level = 0;
        
        while(queue.size() > 0)
        {
            int size = queue.size();
            
            while(size-- > 0)
            {
                StringBuilder temp = new StringBuilder(queue.removeFirst());
                if(temp.toString().equals(end)) return level;
                
                for(int i = 0;i<temp.length();i++)
                {
                    char original = temp.charAt(i);
                    for(char ele : arr)
                    {
                        temp.setCharAt(i,ele);
                        if(set.contains(temp.toString()) && !visited.contains(temp.toString())) 
                        {
                            visited.add(temp.toString());
                            queue.addLast(temp.toString());
                        }
                    }
                    temp.setCharAt(i,original);
                }
            }
            level++;
        }
        return -1;
    }


    // 1352. Product of the Last K Numbers
    // Just Handle For ele 0;
    // Prefix Product

    static List<Integer> prod;
    static int p;
    public ProductOfNumbers() 
    {
        prod = new ArrayList<>();
        p = 1;
    }
    public void add(int num) 
    {
        if(num == 0) 
        {
            prod = new ArrayList<>();
            p = 1;
            return;
        }
        p *= num;
        prod.add(p);
    }
    public int getProduct(int k) 
    {
        if(prod.size() < k) return 0;
        int ans = prod.get(prod.size() - 1);
        if(prod.size() == k) return ans;
        return (ans / prod.get(prod.size() - 1 - k));
    }

    // 826. Most Profit Assigning Work
    // Combine difficulty and profit and sort on the basis of profit
    // in decreasing onder
    // Sort Workers also in decreasing order

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) 
    {
        List<Pair<Integer, Integer>> jobs = new ArrayList<>();
        int N = profit.length, res = 0, i = 0, best = 0;
        for (int j = 0; j < N; ++j) jobs.add(new Pair<Integer, Integer>(difficulty[j], profit[j]));
        Collections.sort(jobs, Comparator.comparing(Pair::getKey));
        Arrays.sort(worker);
        for (int ability : worker) 
        {
            while (i < N && ability >= jobs.get(i).getKey()) best = Math.max(jobs.get(i++).getValue(), best);
            res += best;
        }
        return res;
    }
    
    // 1493. Longest Subarray of 1's After Deleting One Element
    // Simple Sliding Window Problem

    public int longestSubarray(int[] nums) 
    {
        int i = 0;
        int j = 0;
        boolean zero = false;
        int len = 0;
        while(j < nums.length)
        {
            int val = nums[j];
            if(val == 1)
            {
                len = Math.max(len,j-i);
            }
            else if(val == 0)
            {
                if(!zero) 
                {
                    len = Math.max(len,j-i);
                    zero = true;
                }
                else
                {
                    while(nums[i] == 1) i++;
                    i++;
                }
            }
            j++;
        }
        return len;
    }


    // 1605. Find Valid Matrix Given Row and Column Sums
    // Important Problem And Solution

        public int[][] restoreMatrix(int[] rowSum, int[] colSum) 
    {
        int p, q, answer[][], i = 0, j = 0;
        p = rowSum.length;
        q = colSum.length;
        answer = new int[p][q];
        while(i<p && j<q) 
        {
            answer[i][j] = Math.min(rowSum[i], colSum[j]);
            rowSum[i] -= answer[i][j];
            colSum[j] -= answer[i][j];
            if(rowSum[i] == 0) i++;
            if(colSum[j] == 0) j++;
        }
        return answer;
    }


    // 1834. Single-Threaded CPU

    public class pair{
        int s;
        int t;
        int i;
        pair(int s , int t , int i)
        {
            this.s = s;
            this.t = t;
            this.i = i;
        }
    }
    public int[] getOrder(int[][] tasks) 
    {
        pair[] arr = new pair[tasks.length];
        for(int i = 0;i<tasks.length;i++) arr[i] = new pair(tasks[i][0],tasks[i][1],i);
        Arrays.sort(arr,(a,b)->{return a.s-b.s;});
        PriorityQueue<pair> queue = new PriorityQueue<>((a,b)->{
            if(a.t!=b.t) return a.t-b.t;
            return a.i-b.i;
        });
        int[] ans = new int[tasks.length];
        
        int i = 0;
        int j = 0;
        int time = arr[0].s;
        
        while(i < tasks.length)
        {
            while(i < tasks.length && arr[i].s <= time) queue.add(arr[i++]);
            
            if(queue.size() > 0)
            {
                pair temp = queue.remove();
                ans[j++] = temp.i;
                time+=temp.t;
            }
            else time = arr[i].s; // [[1,2],[40,4],[3,2],[4,1]]
        }
        while(queue.size() > 0) ans[j++] = queue.remove().i;
        return ans;
    }


    // 1442. Count Triplets That Can Form Two Arrays of Equal XOR
    // Also Did Earlier in Bit Manipulation
    // Ratne Wala Question 
    // Check Dryrun In Bits Notes
    public int countTriplets(int[] arr) 
    {
        int ans = 0;
        for(int i = 0;i<arr.length;i++)
        {
            int xor = 0;
            for(int j = i;j < arr.length;j++)
            {
                xor = xor ^ arr[j];
                if(xor == 0) ans+= j-i;
            }
        }
        return ans;
    }

    // 1590. Make Sum Divisible by P
    // Simple Math Problem 
    // Use The Understanding of the problem subarray sum divisible by k

    public int minSubarray(int[] nums, int p) 
    {
        long sum = 0;
        for(int ele : nums) sum+=ele;
        long rem = sum%p;
        if(rem == 0) return 0;
        
        HashMap<Long,Long> map = new HashMap<>();
        map.put(0l,-1l);
        
        long pfx = 0;
        int len = nums.length;
        for(int i = 0;i<nums.length;i++)
        {
            pfx+=nums[i];
            long val = pfx%p;
            long find = val-rem;
            if(find < 0) find+=p;
            if(map.containsKey(find)) len = Math.min(len,(int)(i-map.get(find)));
            map.put((long)val,(long)i);
        }
        if(len == nums.length) return -1;
        return len;
    }


    // 1481. Least Number of Unique Integers after K Removals
    // Simple map + pq

    public int findLeastNumOfUniqueInts(int[] arr, int k) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : arr) map.put(ele,map.getOrDefault(ele,0)+1);
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->{
            return a-b;
        });
        for(int ele:map.keySet()) queue.add(map.get(ele));
        
        while(k-- > 0)
        {
            int rem = queue.remove();
            if(rem-1 > 0) queue.add(rem-1);
        }
        return queue.size();
    }


    // 1267. Count Servers that Communicate
    // Simple Solution

    public int countServers(int[][] grid) 
    {
        if(grid==null||grid.length==0||grid[0].length==0)return 0;
        int[] rowSum=new int[grid.length];
        int[] colSum=new int[grid[0].length];
        int sum=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]!=1) continue;
                rowSum[i]++;
                colSum[j]++;
            }
        }
        
        int onlyOne=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1&&(rowSum[i]>1||colSum[j]>1))onlyOne++;
            }
        }
        return onlyOne;
    }


    // 1669. Merge In Between Linked Lists
    // Very Simple Problem

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) 
    {
        ListNode end = list1, start = null;
        for (int i = 0; i < b; ++i, end = end.next) if (i == a - 1) start = end;
        start.next = list2;
        while (list2.next != null) list2 = list2.next;
        list2.next = end.next;
        end.next = null;
        return list1;
    }

    // 1871. Jump Game VII
    // O(n^2) won't pass

    public boolean canReach(String s, int minJump, int maxJump)
    {
        int n = s.length();
        if(s.charAt(n-1) == '1') return false;
        boolean[] dp = new boolean[n];
        dp[n-1] = true;
        for(int i = n-1;i>=0;i--)
        {
            for(int j = i+minJump;j<=Math.min(i+maxJump,n-1);j++)
            {
                if(s.charAt(i) == '0')
                {
                    dp[i] = dp[i] || dp[j];
                    if(dp[i]) break;
                }
            }
        }
        return dp[0];
    }

    // Important Case
    // "0000000000"
    // 2
    // 5 
    // Important Solution
    // Lee's Solution
    // Check Dryrun 
    // Kind Of Same As Sliding window maximum
    // We can do this using queue as well
    public boolean canReach(String s, int minJ, int maxJ) 
    {
        int n = s.length(), pre = 0;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        
        for (int i = 1; i < n; ++i) 
        {
            if (i >= minJ && dp[i - minJ]) pre++;
            // yaha par mein 100% sure hu ki current wale ke liye i-minJ(Agar true hei) wala 
            // ooski window mei pehle se nahi hoga
            if (i > maxJ && dp[i - maxJ - 1]) pre--;
            // Yaha par mei sure hu ki i-maxJ-1(Agar true hei) wala 100% window mein include
            // hoga
            dp[i] = pre > 0 && s.charAt(i) == '0';
        }
        return dp[n - 1];
    }

    // 967. Numbers With Same Consecutive Differences
    // Simple Problem using BFS
    // Just Generate Level By Level

    public int[] numsSameConsecDiff(int N, int K)
    {
        if (N == 1) return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<Integer> queue = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        
        for(int level = 1; level < N; ++ level) 
        {
            ArrayList<Integer> nextQueue = new ArrayList<>();
            // iterate through each number within the level
            
            for (Integer num : queue) 
            {
                Integer tailDigit = num % 10;

                ArrayList<Integer> nextDigits = new ArrayList<>();
                nextDigits.add(tailDigit + K);
                
                if (K != 0) nextDigits.add(tailDigit - K);
                
                for (Integer nextDigit : nextDigits) 
                {
                    if (0 <= nextDigit && nextDigit < 10) 
                    {
                        Integer newNum = num * 10 + nextDigit;
                        nextQueue.add(newNum);
                    }
                }
            }
            // prepare for the next level
            queue = nextQueue;
        }
        return queue.stream().mapToInt(i->i).toArray();
    }

    // 1247. Minimum Swaps to Make Strings Equal
    // Ratne Wala Sawal

    /**
        Observation from given example
        case 1:
        xx
        yy => minimum swap is 1

        case 2:
        xy
        yx => minimum swap is 2

        case 3:
        xx
        xy => not possible [If we have odd no of sum of x/y in both strings then it is impossible to make them equal ]
        
        Steps:
        1. Find sum of x and y in both string, return -1 if case 3 [We can ignore count of x and y which are equal on same index in both string]
        2. Reduce the problem to case 1 and case 2.
        
        Eg:
        s1= xxyyxyxyxx
        s2= xyyxyxxxyx
        
        step 1:
        xs1 =3, ys1=3
        xs2 =3, ys2=3
        
        step 2:
        
        > now we can try to reduce problem to case1 and case 2, 
            as there sum is even we can run loop by subtracting 2 with each [adding 1 to answer , see code below]
        
        > we will left with  xs1 =1, ys1=1, xs2 =1, ys2=1 => case 2 [add two to answer]
        
    **/


    public int minimumSwap(String s1, String s2) 
     {
        int x1 = 0; // number of 'x' in s1 (skip equal chars at same index)
        int y1 = 0; // number of 'y' in s1 (skip equal chars at same index)
        int x2 = 0; // number of 'x' in s2 (skip equal chars at same index)
        int y2 = 0; // number of 'y' in s2 (skip equal chars at same index)

        for(int i = 0; i < s1.length(); i ++)
        {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(c1 == c2){ // skip chars that are equal at the same index in s1 and s2
                continue;
            }
            if(c1 == 'x')
            {
                x1 ++;
            }
            else
            {
                y1 ++;
            }
            if(c2 == 'x')
            {
                x2 ++;
            }
            else
            {
                y2 ++;
            }
        } // end for
        
        // After skip "c1 == c2", check the number of  'x' and 'y' left in s1 and s2.
        if((x1 + x2) % 2 != 0 || (y1 + y2) % 2 != 0){
            return -1; // if number of 'x' or 'y' is odd, we can not make s1 equals to s2
        }
        
        int swaps = x1 / 2 + y1 / 2 + (x1 % 2) * 2;
        // Cases to do 1 swap:
        // "xx" => x1 / 2 => how many pairs of 'x' we have ?
        // "yy" => y1 / 2 => how many pairs of 'y' we have ?
        // 
        // Cases to do 2 swaps:
        // "xy" or "yx" =>  x1 % 2
                 
        return swaps;        
    } 


    // 848. Shifting Letters

    public String shiftingLetters(String s, int[] shift)
    {
        long[]dp = new long[s.length()];
        
        for(int i = 0;i<shift.length;i++)
        {
            int val = shift[i];
            dp[0]+=(long)val;
            if(i+1<dp.length) dp[i+1]-=(long)val;
        }
        
        for(int i = 1;i<dp.length;i++) dp[i] += dp[i-1];
        
        for(int i = 0;i<dp.length;i++) dp[i] += (long)s.charAt(i);
        
        StringBuilder sb = new StringBuilder();
        
        for(long ele : dp)
        {
            long val = ((ele-97)%26) + 97;
            sb.append((char)val);
        }
        return sb.toString();
    }


    // 2095. Delete the Middle Node of a Linked List

    public ListNode deleteMiddle(ListNode head) 
    {
       if(null == head || null == head.next) return null; 
       ListNode  slow = head , fast = head.next.next;
        while( fast != null && fast.next != null )
        {
            slow =  slow.next; 
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    // Celebrity Problem O(n) approach using stack

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then print "none"
        Stack < Integer > st = new Stack < > ();
        for (int i = 0; i < arr.length; i++) 
        {
            st.push(i);
        }

        while (st.size() > 1)
        {
            int i = st.pop();
            int j = st.pop();

            if (arr[i][j] == 1) 
            {
                st.push(j);
            } 
            else 
            {
                st.push(i);
            }
        }

        int pot = st.pop(); // Potential Celebrity
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) 
        {
            if (i != pot) 
            {
                if (arr[i][pot] == 0 || arr[pot][i] == 1) 
                {
                    flag = false;
                    break;
                }
            }
        }

        if (flag) 
        {
            System.out.println(pot);
        } 
        else 
        {
            System.out.println("none");
        }
    }
    
    
}
