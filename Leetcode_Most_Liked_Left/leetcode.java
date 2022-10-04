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

    // Iterative Way to generate all combinations

    set<string>GenaretallComb(string s,int len)
    {
        int mask = 1<<s.length();
        set<string>hold;
        string comString = "";
        for(int no=1;no<mask;no++){
            int num = no,i = 0;
            while(num){
                if(num&1)comString = comString + s[i];
                i++,num>>=1;
            }
            if(comString.length()==len)hold.insert(comString);
            comString = "";
        }
        return hold;
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
        for (int i = 1; i < nums.length; i++) 
        {
            if (nums[i] <= prevNum) 
            {
                prevNum++;
                count += (prevNum - nums[i]);
            } 
            else 
            {
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
    
    // 2. Add Two Numbers

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        int carry = 0;
        
        while(l1!=null || l2!=null)
        {
            int a = (l1!=null) ? l1.val : 0;
            int b = (l2!=null) ? l2.val : 0;
            
            int val = a + b + carry;
            
            int nVal = val % 10;
            carry = val / 10;
            
            prev.next = new ListNode(nVal);
            prev = prev.next;
            
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;
        }
        
        if(carry > 0) prev.next = new ListNode(carry);
        
        return dummy.next;
    }


    // 6. Zigzag Conversion

    public String convert(String s, int numRows) 
    {
        if(numRows == 1) return s;
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        for(int i = 0;i<numRows;i++) map.add(new ArrayList<>());
        
        int i = 0;
        int idx = 0;
        boolean toggle = false;
        while(i < s.length())
        {
            char ch = s.charAt(i);
            if(!toggle)
            {
                map.get(idx).add(ch);
                idx++;
            }
            else
            {
                map.get(idx).add(ch);
                idx--;
            }
            
            if(idx == numRows)
            {
                toggle = true;
                idx-=2;
            }
            else if(idx == -1)
            {
                toggle = false;
                idx+=2;
            }
            i++;
        }
        
        
        
        StringBuilder ans = new StringBuilder();
        
        for(ArrayList<Character> list : map)
        {
            for(char ele : list)
            {
                ans.append(ele);
            }
        }
            
        return ans.toString();
    }

    // 59. Spiral Matrix II

    public int[][] generateMatrix(int n) 
    {
        int[][] matrix = new int[n][n];
        int m = n;
        int x = 0;
        int y = 0;
        int dx = n-1;
        int dy = m-1;
        int k = 1;
        
        while(k <= n*n)
        {
            for(int i = y;i<=dy;i++) matrix[x][i] = k++;
            for(int i = x+1;i<=dx;i++) matrix[i][dy] = k++;
            for(int i = dy-1;i>=y;i--) matrix[dx][i] = k++;
            for(int i = dx-1;i>x;i--) matrix[i][y] = k++;
            
            y++;
            x++;
            dy--;
            dx--;
        }
        return matrix;
    }

    // 78. Subsets

    public List<List<Integer>> subsets(int[] nums) 
    {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start)
    {
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++)
        {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }


    // 235. Lowest Common Ancestor of a Binary Search Tree

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) 
    {        
        while((root.val > p.val && root.val > q.val) ||(root.val < p.val && root.val < q.val))
        {
            if(root.val > p.val && root.val > q.val) root = root.left;
            else root = root.right;
        }
        return root;
    }


    // 396. Rotate Function

    public int maxRotateFunction(int[] nums) 
    {
        int bSum = 0;
        int sum = 0;
        int max = -(int)1e11;
        for(int i = 0;i<nums.length;i++)
        {
            sum += nums[i];
            bSum += (i*nums[i]);
        }
        
        for(int i = nums.length-1;i>=0;i--)
        {
            bSum = bSum+sum-(nums[i]*nums.length);
            max = Math.max(max,bSum);
        }
        return max;
    }


    // 414. Third Maximum Number

    public int thirdMax(int[] nums) 
    {
        long f = Long.MIN_VALUE;
        long s = Long.MIN_VALUE;
        long t = Long.MIN_VALUE;
        for (int n: nums) 
        {
            if (n == f || n == s || n == t) continue;
            if (n >= f) 
            {
                t = s;
                s = f;
                f = n;
            } 
            else if (n >= s) 
            {
                t = s;
                s = n;
            } 
            else if (n >= t) 
            {
                t = n;
            }
        }
        if (t != Long.MIN_VALUE) return (int)t;
        else if (f != Long.MIN_VALUE) return (int)f;
        else return (int)s;    
    }


    // 384. Shuffle an Array

    private int[] array;
    private int[] original;

    Random rand = new Random();

    private int randRange(int min, int max) 
    {
        return rand.nextInt(max - min) + min;
    }

    private void swapAt(int i, int j) 
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Solution(int[] nums) 
    {
        array = nums;
        original = nums.clone();
    }
    
    public int[] reset() 
    {
        array = original;
        original = original.clone();
        return original;
    }
    
    public int[] shuffle() 
    {
        for (int i = 0; i < array.length; i++) 
        {
            swapAt(i, randRange(i, array.length));
            // Apne Aage wale indexes se swap kare hei
            // jisse Ki yeh Ensure Ho jaye ki hum jisse swap huie hei oose wapis naa hojaye
        }
        return array;
    }

    // 443. String Compression


    public int compress(char[] chars) 
    {
        int indexAns = 0, index = 0;
        while(index < chars.length)
        {
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar)
            {
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if(count != 1)
            {
                for(char c : Integer.toString(count).toCharArray()) 
                {
                    chars[indexAns++] = c;
                }
            }
        }
        return indexAns;
    }


    // 449. Serialize and Deserialize BST
    // Nice Tarika

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) 
    {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }
    private void dfs(TreeNode root, StringBuilder sb) 
    {
        if (root == null) 
        {
            return;
        }
        sb.append(root.val + ",");
        dfs(root.left, sb);
        dfs(root.right, sb);
        return;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) 
    {
        String[] arr = data.split(",");
        TreeNode root = null;
        for (String s : arr) 
        {
            if (s.length() > 0) 
            {
                root = buildBST(root, Integer.parseInt(s));
            }
        }
        return root;
    }

    public TreeNode buildBST(TreeNode root, int v) 
    {
        if (root == null) return new TreeNode(v);
        if (v < root.val) 
        {
            root.left = buildBST(root.left, v);
        } 
        else 
        {
            root.right = buildBST(root.right, v);
        }
        return root;
    }



    // 459. Repeated Substring Pattern
    // Bahut Zyada BruteForce
    // Creating String with all substrings
    public boolean repeatedSubstringPattern(String str) 
    {
        int l = str.length();
        for(int i=l/2;i>=1;i--) 
        {
            if(l%i==0) 
            {
                int m = l/i;
                String subS = str.substring(0,i);
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<m;j++) 
                {
                    sb.append(subS);
                }
                if(sb.toString().equals(str)) return true;
            }
        }
        return false;
    }

    // 1092. Shortest Common Supersequence

    public void dfs(int[][] dp ,int i ,int j,String str1,String str2,StringBuilder sb)
    {
        if(i >= dp.length || j >= dp[0].length || dp[i][j] == 0) return;
        if( i!=dp.length-1 && j!=dp[0].length-1 && str1.charAt(i) == str2.charAt(j))
        {
            sb.append(str1.charAt(i));
            dfs(dp,i+1,j+1,str1,str2,sb);
        }
        else if((i+1 < dp.length && j+1 < dp[0].length && dp[i+1][j] < dp[i][j+1]) || j == dp[0].length-1)
        {
            sb.append(str1.charAt(i));
            dfs(dp,i+1,j,str1,str2,sb);
        }
        else
        {
            sb.append(str2.charAt(j));
            dfs(dp,i,j+1,str1,str2,sb);
        }
    }
    public String shortestCommonSupersequence(String str1, String str2) 
    {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m+1][n+1];
        
        for(int i = m;i>=0;i--)
        {
            for(int j = n;j>=0;j--)
            {
                if(i == m && j == n) continue;
                else if(i == m) dp[i][j] = dp[i][j+1] + 1;
                else if(j == n) dp[i][j] = dp[i+1][j] + 1;
                else
                {
                    char ch1 = str1.charAt(i);
                    char ch2 = str2.charAt(j);
                    if(ch1 == ch2) dp[i][j] = dp[i+1][j+1]+1;
                    else dp[i][j] = Math.min(dp[i+1][j],dp[i][j+1]) + 1;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        dfs(dp,0,0,str1,str2,sb);
        return sb.toString();
    }

    // 131. Palindrome Partitioning

    public List<List<String>> partition(String s) 
    {
        int n = s.length();
        boolean[][]dp = new boolean[n][n];
        
        for(int gap = 0;gap<n;gap++)
        {
            for(int i = 0,j=gap;j<n;j++,i++)
            {
                if(gap == 0) 
                {
                    dp[i][j] = true;
                    continue;
                }
                if(gap == 1) 
                {
                    if(s.charAt(i) == s.charAt(j))  dp[i][j] = true;
                    continue;
                }
                
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) 
                {
                    dp[i][j] = true;    
                }
            }
        }
                
        List<List<String>> ans = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        
        getPalindromes(s,0,dp,ans,temp);
        
        return ans;
        
    }
    
    public void getPalindromes(String s , int start , boolean[][] dp,List<List<String>> ans , List<String> temp)
    {
        if(start == s.length())
        {
            List<String> t = new ArrayList<>(temp);
            ans.add(t);
            return;
        }
        for(int i = start;i<s.length();i++)
        {
            if(dp[start][i])
            {
                temp.add(s.substring(start,i+1));
                getPalindromes(s,i+1,dp,ans,temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    // 132. Palindrome Partitioning II

    public int minCut(String s) 
    {
        int n = s.length();
        boolean[][]dp = new boolean[n][n];
        
        for(int gap = 0;gap<n;gap++)
        {
            for(int i = 0,j=gap;j<n;j++,i++)
            {
                if(gap == 0) 
                {
                    dp[i][j] = true;
                    continue;
                }
                if(gap == 1) 
                {
                    if(s.charAt(i) == s.charAt(j))  dp[i][j] = true;
                    continue;
                }
                
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) 
                {
                    dp[i][j] = true;    
                }
            }
        }
        
        int[] dp2 = new int[n];
        Arrays.fill(dp2,(int)1e9);
        dp2[n-1] = 0;
        
        for(int i = n-1;i>=0;i--)
        {
            for(int j = i;j<n;j++)
            {
                if(dp[i][j])
                {
                    int val = (j+1 < n) ? dp2[j+1] : -1;
                    dp2[i] = Math.min(dp2[i] , val+1);
                }
            }
        }
        
        return dp2[0];
    }


    // Egg Dropping Problem
    // General Solution
    // Submmitted On Geek For Geeks
    // TLE on leetcode
    public static int eggDrop(int n, int k)
    {
        int[][] dp = new int[n + 1][k + 1];
        //if number of floors == 1 ans number of eggs >= 1, then we need only one attempt
        for(int i = 1; i <= n; i++)
        {
            dp[i][1] = 1;
        }
        //if number of eggs == 1
        for(int i = 1 ; i <= k; i++)
        {
            dp[1][i] = i;
        }

        for(int i = 2; i <= n; i++)
        {
            for(int j = 2; j <= k; j++)
            {
                dp[i][j] = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for(int f = 1; f <= j; f++)
                {
                    max = 1 + Math.max(dp[i - 1][f - 1], dp[i][j - f]);
                    if(max < dp[i][j])
                    {
                        dp[i][j] = max;
                    }
                }
            }
        }
        return dp[n][k];
    } 


    // 225. Implement Stack using Queues

    Queue<Integer> queue;
    
    public MyStack()
    {
        this.queue=new LinkedList<Integer>();
    }
    
    // Push element x onto stack.
    public void push(int x) 
    {
       queue.add(x);
       for(int i=0;i<queue.size()-1;i++)
       {
           queue.add(queue.poll());
       }
    }

    // Removes the element on top of the stack.
    public int pop() 
    {
        return queue.poll();
    }

    // Get the top element.
    public int top() 
    {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() 
    {
        return queue.isEmpty();
    }

    // GFG
    // Maximum of minimum for every window size

    //     Result for length i, i.e. ans[i] would always be greater or same as result for length i+1, 
//     i.e., ans[i+1]. 
//     If ans[i] is not filled it means there is no direct element which is minimum of length i
//     and therefore either the element of length ans[i+1], or ans[i+2], and so on is same as ans[i] 
//     So we fill rest of the entries using below loop. 
    static void nsor(int[]arr , int[]ans)
    {
        int i = 0;
        Stack<Integer> st = new Stack<>();
        while(i < arr.length)
        {
            if(st.size() == 0) st.push(i);
            else
            {
                while(st.size() > 0 && arr[st.peek()] > arr[i]) ans[st.pop()] = i;
                st.push(i);
            }
            i++;
        }
    }
    
    static void nsol(int[]arr , int[]ans)
    {
        int i = arr.length-1;
        Stack<Integer> st = new Stack<>();
        while(i >= 0)
        {
            if(st.size() == 0) st.push(i);
            else
            {
                while(st.size() > 0 && arr[st.peek()] > arr[i]) ans[st.pop()] = i;
                st.push(i);
            }
            i--;
        }
    }
    static int[] maxOfMin(int[] arr, int n) 
    {
        int[]left = new int[n];
        int[]right = new int[n];
        
        Arrays.fill(left,-1);
        Arrays.fill(right,n);
        
        nsor(arr,right);
        nsol(arr,left);
        
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        
        int[] ans = new int[n];
        
        for(int i = 0;i<n;i++)
        {
            int l = left[i];
            int r = right[i];
            
            int length = (r-l)-1; // Exclude the boundries
            ans[length-1] = Math.max(ans[length-1],arr[i]);
        }
        
        for(int i = n-2;i>=0;i--)
        {
            ans[i] = Math.max(ans[i],ans[i+1]);
        }
        return ans;
    }


    // 8. String to Integer (atoi)

    public int myAtoi(String str) 
    {
        int index = 0, sign = 1, total = 0;
        if(str.length() == 0) return 0;

        while(index < str.length() && str.charAt(index) == ' ') index ++;

        if(index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-'))
        {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index ++;
        }

        while(index < str.length())
        {
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;

            //check if total will be overflow after 10 times and add digit
            // Integer max value is 2147483647
            // max/10 = 214748364
            // max%10 == 7
            // so at the end we can atmax add 7
            // because when we are at 2147483647
            // then we will do 2147483647*10 -> 21474836470
            // and then we can add atmax 7
            if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
            {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = 10 * total + digit;
            index ++;
        }
        return total * sign;
    }

    // 165. Compare Version Numbers

    // . is a special pattern token in a regular expression. It matches any one character. When you split on every possible                     // character you get an empty array (because there is nothing left).
    // In contrast, when you escape the . with \\. the token is rendered as a literal (and only matches a literal .).
    public int compareVersion(String version1, String version2)
    {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");
        // System.out.println(Arrays.toString(levels1));
        // System.out.println(Arrays.toString(levels2));
        int length = Math.max(levels1.length, levels2.length);
        for (int i=0; i<length; i++) 
        {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            // System.out.println(compare);
            if (compare != 0) 
            {
                return compare;
            }
        }

        return 0;
    }

    // Minimum characters to be added at front to make string palindrome
    // Important , Solven Using KMP
    // Reverse and Append String
    public static int minChar(String str) 
    {
        return getMinCharToAddedToMakeStringPalin(str);
    }
    public static int[] computeLPSArray(String str)
    {
        int n = str.length();
        int lps[] = new int[n];
        int i = 1, len = 0;
        lps[0] = 0; // lps[0] is always 0
         
        while (i < n)
        {
            if (str.charAt(i) == str.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            else
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0)
                {
                    len = lps[len - 1];
                     
                    // Also, note that we do not increment
                    // i here
                }
                else
                {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
     
    // Method returns minimum character to be added at
    // front to make string palindrome
    static int getMinCharToAddedToMakeStringPalin(String str)
    {
        StringBuilder s = new StringBuilder();
        s.append(str);
         
        // Get concatenation of string, special character
        // and reverse string
        String rev = s.reverse().toString();
        s.reverse().append("$").append(rev);
         
        // Get LPS array of this concatenated string
        int lps[] = computeLPSArray(s.toString());
        return str.length() - lps[s.length() - 1];
    }

    // 151. Reverse Words in a String

    public String reverseWords(String s) 
    {
        String [] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        int end = words.length - 1;
        for(int i = 0; i<= end; i++)
        {
            if(!words[i].isEmpty()) 
            {
                sb.insert(0, words[i]);
                if(i < end) sb.insert(0, " ");
            }
        }
        return sb.toString();
    }

    // 13. Roman to Integer
    // Simplest and the easiest solution

    public int getValue(char c)
    {
        if(c=='I') return 1;
        else if(c=='V') return 5;
        else if(c=='X') return 10;
        else if(c=='L') return 50;
        else if(c=='C') return 100;
        else if(c=='D') return 500;
        else return 1000;
    }
    public int romanToInt(String s) 
    {
        int n = s.length();
        int res = 0;
        for(int i=0;i<n-1;i++)
        {
            int a = getValue(s.charAt(i));
            int b = getValue(s.charAt(i+1));
            if(a<b)
            {
                res-=a;
            }
            else
            {
                res+=a;
            }           
        }
        res += getValue(s.charAt(n-1));     
        return res;
    }


    // 315. Count of Smaller Numbers After Self

    public List<Integer> countSmaller(int[] nums) 
    {
        int n = nums.length;
        int[][] arr = new int[n][2];
        int[][] temp = new int[n][2];
        int[] ans = new int[n];
        for(int i = 0;i<n;i++) 
        {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        
        mergeSort(arr,0,n-1,ans,temp);
        
        List<Integer> finalAns = new ArrayList<>();
        for(int ele : ans) finalAns.add(ele); 

        return finalAns;
    }
    
    public void mergeSort(int[][] nums , int i , int j ,int[] ans,int[][]temp)
    {
        if(i == j) return;
        int mid = (i+j)/2;
        mergeSort(nums,i,mid,ans,temp);
        mergeSort(nums,mid+1,j,ans,temp);
        getInversions(nums,ans,i,mid+1,mid,j,temp);
    }
    
    public void getInversions(int[][] nums ,int[]ans, int s1 , int s2 , int e1 , int e2,int[][]temp)
    {
        int len = e2-s1+1;
        int u = s1;
        int v = s2;
        
        while(u <= e1)
        {
            while(v <= e2 && nums[v][0] < nums[u][0]) v++;
            ans[nums[u][1]] += (v-s2);
            u++;
        }
        u = s1;
        int k = u;

        while(s1<=e1 && s2<=e2)
        {
            int v1 = nums[s1][0];
            int v2 = nums[s2][0];
            
            if(v1<=v2)
            {
                temp[k][0] = v1;
                temp[k][1] = nums[s1][1];
                s1++;
            }
            else
            {
                temp[k][0] = v2;
                temp[k][1] = nums[s2][1];
                s2++;
            }
            k++;
        }
        
        while(s1<=e1)
        {
            temp[k][0] = nums[s1][0];
            temp[k][1] = nums[s1][1];
            s1++;
            k++;
        }
        while(s2<=e2)
        {
            temp[k][0] = nums[s2][0];
            temp[k][1] = nums[s2][1];
            s2++;
            k++;
        }
        
                
        while(u<=e2)
        {
            nums[u][0] = temp[u][0];
            nums[u][1] = temp[u][1];
            u++;
        }
    }


    // 126. Word Ladder II
    // TLE Approach
    // 32/35 passed

    private Set<String> set;
    private Queue<Node> q;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) 
    {
        List<List<String>> result = new ArrayList<>();
        
        set = new HashSet<>();
        q = new LinkedList<>();
        
        for(String s : wordList) set.add(s);

        if(!set.contains(endWord)) return result; 
        
        q.add(new Node(beginWord));
        
        while(!q.isEmpty())
        {
            int size = q.size(); 
            Set<String> removeSet = new HashSet<>(); 
            for(int i=0; i < size; i++)
            {
                Node cur = q.poll();
                if(cur.name.equals(endWord)) 
                {
                    result.add(cur.path);
                }
                else
                {
                    List<String> neighbours = getNeighbours(cur.name);
                    for(String n : neighbours)
                    {
                        q.add(new Node(n, cur.path));
                        removeSet.add(n);
                    }
                }
            }
            set.removeAll(removeSet);
        }
        
        return result;
    }
    
    private List<String> getNeighbours(String word)
    {
        char[] ch = word.toCharArray();
        List<String> words = new ArrayList<>();
        for(int i=0; i < ch.length; i++)
        {
            char temp = ch[i];
            for(char j = 'a'; j <= 'z'; j++)
            {
                ch[i] = j;
                String newWord = new String(ch);
                if(set.contains(newWord)) words.add(newWord);
            }
            ch[i] = temp;
        }
        return words;
    }

    class Node
    {
        String name;
        LinkedList<String> path;
        
        public Node(String name)
        {
            this.name = name;
            this.path = new LinkedList<>();
            this.path.add(name);
        }
        public Node(String name, LinkedList<String> path)
        {
            this.name = name;
            this.path = new LinkedList<>();
            this.path.addAll(path);
            this.path.add(name);
        }
       
    }

    // BFS + DAG also giving TLE

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        LinkedList<String> queue = new LinkedList<>();
        HashSet<String> words = new HashSet<>();
        for(String ele : wordList) words.add(ele);
        HashMap<String ,Integer> size = new HashMap<>();
        size.put(beginWord,0);
        HashMap<String,LinkedList<String>> map = new HashMap<>();
        queue.add(beginWord);
        map.put(beginWord,new LinkedList<>());
        while(queue.size() > 0)
        {
            int s = queue.size();
            while(s-- > 0)
            {
                String pop = queue.removeFirst();
                for(int i = 0;i<pop.length();i++)
                {
                    StringBuilder sb = new StringBuilder(pop);
                    for(int j = 0;j<26;j++)
                    {
                        char ch = (char)('a'+j);
                        sb.setCharAt(i,ch);
                        String temp = sb.toString();
                        if(words.contains(temp))
                        {
                            if(!size.containsKey(temp))
                            {
                                map.put(temp,new LinkedList());
                                map.get(pop).addLast(temp);
                                queue.addLast(temp);
                                size.put(temp,size.get(pop)+1);
                            }
                            else if(size.get(pop)+1 == size.get(temp))
                            {
                                map.get(pop).addLast(temp);
                            }
                        }
                    }
                }
            }
        }
        
        List<List<String>> ans = new ArrayList<>();
        getPaths(map,beginWord,endWord,ans,new ArrayList<>());
        return ans;
    }
    
    public void getPaths(HashMap<String,LinkedList<String>> map , String beginWord , String endWord,List<List<String>> ans,List<String> temp)
    {
        if(beginWord.equals(endWord))
        {
            temp.add(beginWord);
            List<String> l = new ArrayList<>();
            for(String ele : temp) l.add(ele);
            ans.add(l);
            temp.remove(temp.size()-1);
            return;
        }
        temp.add(beginWord);
        for(String ele : map.get(beginWord))
        {
            getPaths(map,ele,endWord,ans,temp);
        }
        temp.remove(temp.size()-1);
    }


    // Accepted Solution 
    // BFS + reverse DAG Traversal
    // This will remove redundancy
    // endWord to beginWord
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        LinkedList<String> queue = new LinkedList<>();
        HashSet<String> words = new HashSet<>();
        for(String ele : wordList) words.add(ele);
        HashMap<String ,Integer> size = new HashMap<>();
        size.put(beginWord,0);
        HashMap<String,LinkedList<String>> map = new HashMap<>();
        HashMap<String,LinkedList<String>> newMap = new HashMap<>();
        queue.add(beginWord);
        map.put(beginWord,new LinkedList<>());
        while(queue.size() > 0)
        {
            int s = queue.size();
            while(s-- > 0)
            {
                String pop = queue.removeFirst();
                for(int i = 0;i<pop.length();i++)
                {
                    StringBuilder sb = new StringBuilder(pop);
                    for(int j = 0;j<26;j++)
                    {
                        char ch = (char)('a'+j);
                        sb.setCharAt(i,ch);
                        String temp = sb.toString();
                        if(words.contains(temp))
                        {
                            if(!size.containsKey(temp))
                            {
                                map.put(temp,new LinkedList());
                                map.get(pop).addLast(temp);
                                queue.addLast(temp);
                                size.put(temp,size.get(pop)+1);
                            }
                            else if(size.get(pop)+1 == size.get(temp))
                            {
                                map.get(pop).addLast(temp);
                            }
                        }
                    }
                }
            }
        }
        
        for(String ele : map.keySet())
        {
            for(String e : map.get(ele))
            {
                if(!newMap.containsKey(e)) newMap.put(e,new LinkedList<>());
                newMap.get(e).addLast(ele);
            }
        }
        
        List<List<String>> ans = new ArrayList<>();
        getPaths(newMap,endWord,beginWord,ans,new ArrayList<>());
        return ans;
    }
    
    public void getPaths(HashMap<String,LinkedList<String>> map , String beginWord , String endWord,List<List<String>> ans,List<String> temp)
    {
        if(beginWord.equals(endWord))
        {
            temp.add(beginWord);
            List<String> l = new LinkedList<>();
            for(String ele : temp) l.add(0,ele);
            ans.add(l);
            temp.remove(temp.size()-1);
            return;
        }
        temp.add(beginWord);
        if(!map.containsKey(beginWord)) return;
        for(String ele : map.get(beginWord))
        {
            getPaths(map,ele,endWord,ans,temp);
        }
        temp.remove(temp.size()-1);
    }


    // 980. Unique Paths III
    // Simple Backtracking brute force

    int res = 0, empty = 1, sx, sy, ex, ey;
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        dfs(grid, sx, sy);
        return res;
    }

    public void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] < 0)
            return;
        if (grid[x][y] == 2) {
            if (empty == 0) res++;
            return;
        }
        grid[x][y] = -2;
        empty--;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        grid[x][y] = 0;
        empty++;
    }


    // 135. Candy
    // Simple Solution like temple offerings 

    public int candy(int[] ratings) 
    {
        int n = ratings.length;
        int[] dp = new int[n];
        int prev = 0;
        
        int val = -(int)1e9;
        
        for(int i = 0;i<n;i++)
        {
            int cVal = ratings[i];
            
            if(cVal > val) dp[i] = ++prev;
            else if(cVal == val) 
            {
                dp[i] = 1;
                prev = 1;
            }
            else {
                dp[i] = 1;
                prev = 1;
            }
            val = cVal;
        }
        
        
        prev = 0;
        val = -(int)1e9;
        
        for(int i = n-1;i>=0;i--)
        {
            int cVal = ratings[i];
            if(cVal > val)
            {
                dp[i] = Math.max(dp[i],++prev);
                prev = Math.max(prev,dp[i]);
            }
            else if(cVal == val)
            {
                dp[i] = Math.max(dp[i],1);
                prev = Math.max(dp[i],1);
            }
            else if(cVal < val)
            {
                dp[i] = Math.max(dp[i],1);
                prev = Math.max(1,dp[i]);
            }
            val = cVal;
        }
        
        int ans = 0;
        for(int ele : dp) ans+=ele;
        return ans;
        
    }



    // 336. Palindromic Pairs

    // Once all the words are added to HashMap,
    // there are 4 cases to be considered for each word:

    // Check if reverse of word is present.
    // Check if there is empty string. 
    // In that case if word is palindrome, 
    // empty string can be added before and after the word.
    // Check if some other word can be added as prefix.
    // Check if some other word can be added as suffix.

    // Code Not Mine , Logic is Mine
    // Not passing 135/136 passed

    // Every Solution is giving tle now
    // check for new Solutions Afterwords

    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> palindromePairs(String[] words) 
    {
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        for (String s: words) map.put(s, i++);
        
        // if empty string exists then check if any other word is palindrome
        if(map.containsKey(""))
        {
            int id = map.get("");
            for(i=0;i<words.length;i++)
            {
                if(i != id && isPalindrome(words[i]))
                {
                    addList(i, id);
                    addList(id, i);
                }
            }
        }
        
        // if reverse exist for a word then add it
        for(i=0;i<words.length;i++)
        {
            String word = words[i];
            String reverse = new StringBuilder(word).reverse().toString();
            mapAndAdd(map, reverse, i, false);
        }
        
        // if first portion of word is palindrome and reverse of second section then add
        // if second portion is palindrome and reverse of first section then add


        // This is also Giving TLE
        for(i=0;i<words.length;i++)
        {
            String word = words[i];
            StringBuilder left = new StringBuilder();
            StringBuilder right = new StringBuilder(word);

            for (int j=1;j<word.length();j++)
            {
                left.append(right.charAt(0));
                right.deleteCharAt(0);
                if(isPalindrome(left.toString()))
                {
                    String reverse = new StringBuilder(right.toString()).reverse().toString();
                    mapAndAdd(map, reverse, i, false);
                }
                if(isPalindrome(right.toString()))
                {
                    String reverse = new StringBuilder(left.toString()).reverse().toString();
                    mapAndAdd(map, reverse, i, true);
                }
            }
        }

        // for(i=0;i<words.length;i++)
        // {
        //     String word = words[i];
        //     for (int j=1;j<word.length();j++)
        //     {
        //         String left = word.substring(0,j);
        //         String right = word.substring(j);
        //         if(isPalindrome(left))
        //         {
        //             String reverse = new StringBuilder(right).reverse().toString();
        //             mapAndAdd(map, reverse, i, false);
        //         }
        //         if(isPalindrome(right))
        //         {
        //             String reverse = new StringBuilder(left).reverse().toString();
        //             mapAndAdd(map, reverse, i, true);
        //         }
        //     }
        // }
        
        return result;
    }
    
    public void mapAndAdd(Map<String, Integer> map, String word, int i, boolean flag)
    {
        if(map.containsKey(word) && i != map.get(word)) 
        {
            if (flag) addList(i, map.get(word));
            else addList(map.get(word), i);
        }
    }
    
    public void addList(int i, int j)
    {
        List<Integer> temp = new ArrayList<>();
        temp.add(i);
        temp.add(j);
        result.add(temp);
    }
    
    public boolean isPalindrome(String s)
    {
        int left = 0;
        int right = s.length()-1;
        while (left < right) if (s.charAt(left++) != s.charAt(right--)) return false;
        return true;
    }

    // 847. Shortest Path Visiting All Nodes
    // o(n*2^n)
    // Mera Dimag Kharab Hogaya Tha
    // Itna Mushkil bhi sawaal nahi hei
    class Node
    {
        int visited;
        int dist;
        int node;
        Node(int node, int dist, int visited) 
        {
            this.node = node;
            this.dist = dist;
            this.visited = visited;
        }
    }
    public int shortestPathLength(int[][] graph) 
    {
        int n = graph.length;
        Set<Integer>[] visited = new HashSet[n];
        Queue<Node> queue = new LinkedList<>();

        for(int i = 0; i < n; i++) 
        {
            visited[i] = new HashSet<>();
            queue.add(new Node(i, 0, 1 << i));
            visited[i].add(1 << i);
        }
        while(!queue.isEmpty()) 
        {
            Node node = queue.poll();
            if(node.visited == (1 << n) - 1)  return node.dist;
            for(int nei : graph[node.node]) // kahi Oos element ke Visited mei hum same Mask ke saath toh nahi aa rahe
            {
                int mask = node.visited | (1 << nei);
                if(visited[nei].add(mask)) queue.add(new Node(nei, 1 + node.dist, mask));
            }
        }
        return -1;
    }


    // 827. Making A Large Island

    public int findPar(int[] par , int[]size , int u)
    {
        if(par[u] == u) return u;
        int p = findPar(par,size,par[u]);
        return par[u] = p;
    }
    
    public void findAndSetPar(int i , int j , int m , int n , int up , int left , int[]par, int[]size)
    {
        int parUp =  (up == -1) ? -1 : findPar(par,size,up);
        int parLeft = (left == -1) ? -1 : findPar(par,size,left);
        if(parUp != parLeft)
        {
            if(up !=-1 && left!=-1)
            {
                if(size[parUp] < size[parLeft])
                {
                    par[parUp] = parLeft;
                    par[i*m+j] = parLeft;
                    size[parLeft] += size[parUp]+1;
                }
                else
                {
                    par[parLeft] = parUp;
                    par[i*m+j] = parUp;
                    size[parUp] += size[parLeft]+1;
                }
            }
            else
            {
                if(parUp == -1 && parLeft != -1)
                {
                    par[i*m+j] = parLeft;
                    size[parLeft] += 1;
                }
                else if(parUp != -1 && parLeft == -1)
                {
                    par[i*m+j] = parUp;
                    size[parUp] += 1;
                }
            }
        }
        else if(parUp == parLeft && parUp!=-1 && parLeft!=-1)
        {
            par[i*m+j] = parUp;
            size[parUp] += 1;
        }
    }
    
    public int largestIsland(int[][] grid) 
    {
        int n = grid[0].length;
        int m = grid[0].length;
        int[] par = new int[n*n];
        int[] size = new int[n*n];
        Arrays.fill(size,1);
        
        for(int i=0;i<n*n;i++) par[i]=i;
        
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<n;j++)
            {
                if(grid[i][j] == 0) continue;
                int up = (i == 0 || grid[i-1][j] == 0) ? -1 : n*(i-1)+j ;
                int left = (j == 0 || grid[i][j-1] == 0) ? -1 : n*(i)+(j-1);
                findAndSetPar(i,j,m,m,up,left,par,size);
            }
        }
        
        int max = 0;
        int max2 = 0;
        boolean isZero = false;
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<n;j++)
            {
                if(grid[i][j] == 1) {
                    continue;
                }
                isZero = true;
                HashSet<Integer> set = new HashSet<>();
                
                int left = (j == 0 || grid[i][j-1] == 0) ? -1 : findPar(par,size,(i*m)+j-1);
                int right = (j == n-1 || grid[i][j+1] == 0) ? -1 : findPar(par,size,(i*m)+j+1);
                int up = (i == 0 || grid[i-1][j] == 0) ? -1 : findPar(par,size,((i-1)*m)+j);
                int down = (i == n-1 || grid[i+1][j] == 0) ? -1 : findPar(par,size,((i+1)*m)+j);
                
                if(left!=-1) set.add(left);
                if(right!=-1) set.add(right);
                if(up!=-1) set.add(up);
                if(down!=-1) set.add(down);
                
                int sum = 1;
                for(int ele : set) sum+=size[ele];
                max = Math.max(max,sum);
            }
        }
        if(!isZero) return n*n;
        return max;
    }

    // 1293. Shortest Path in a Grid with Obstacles Elimination

    // All the cells would be visited more than once as we could reach same cell 
    // with more distance but less obstacles which could be helpful later in
    // traversal so we need to consider all the paths passing through
    // same cell even with more distance.


    public int shortestPath(int[][] grid, int k) 
    {
        int minDistance = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[grid.length][grid[0].length];
        for(int[] row: visited) Arrays.fill(row, -1);
        
        queue.add(new int[] {0,0,k});
        
        while(!queue.isEmpty()) 
        {
            int size = queue.size();
            while(size-- > 0) 
            {
                int[] currentPos = queue.poll();
                int i = currentPos[0], j = currentPos[1], obs = currentPos[2];
                
                if (i<0 || j<0 || i>=grid.length || j>=grid[i].length) continue;
                if (obs <= visited[i][j]) continue;
                visited[i][j] = obs;
                if (i == grid.length-1 && j == grid[i].length-1) return minDistance;
                
                if (grid[i][j] == 0 || --obs >= 0) 
                {
                    queue.add(new int[] {i+1, j, obs});
                    queue.add(new int[] {i-1, j, obs});
                    queue.add(new int[] {i, j+1, obs});
                    queue.add(new int[] {i, j-1, obs});
                }
            }
            ++minDistance;
        }
        return -1;
    }


    // 480. Sliding Window Median
    // Using the Idea of Median In a data stream

    public double[] medianSlidingWindow(int[] nums, int k) 
    {
        PriorityQueue<Integer> max = new PriorityQueue<>((a,b)->{
            return (b.compareTo(a));
        });
        PriorityQueue<Integer> min = new PriorityQueue<>();
        int i = 0;
        int j = 0;
        int n = nums.length;
        int l = 0;
        double[] ans = new double[n-k+1];
        while(j < n)
        {
            while(min.size()+max.size() < k && j < n)
            {
                int val = nums[j];
                if(min.size() == 0 || val < min.peek()) max.add(val);
                else min.add(val);

                int diff = Math.abs(min.size()-max.size());
                
                if(diff == 2)
                {
                    if(min.size() > max.size()) max.add(min.poll());
                    else min.add(max.poll());
                }
                else if(min.size() > max.size()) max.add(min.poll());
                j++;
            }
            
            if(k%2 == 1) ans[l++] = (double)max.peek();
            else ans[l++] = (long)((long)max.peek()+(long)min.peek())/2.0;
            int rem = nums[i];
            if(min.contains(rem)) min.remove(rem); 
            else max.remove(rem);
            int diff = Math.abs(min.size()-max.size());                
            if(diff == 2)
            {
                if(min.size() > max.size()) max.add(min.poll());
                else min.add(max.poll());
            }
            else if(min.size() > max.size()) max.add(min.poll());
            i++;
        }
        return ans;
    }


    // 164. Maximum Gap
    // Solve This Using  Bucket Sort


    // 778. Swim in Rising Water
    // Simple Dijikstra Problem

        public int swimInWater(int[][] grid)
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {return a[2] - b[2];});
        
        // It is not possible to come at same point with Less Time
        // So Boolean Matrix Is suffic 
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];
        
        pq.add(new int[] {0, 0, grid[0][0]});
        while(!pq.isEmpty()) 
        {
            int[] arr = pq.poll();
            int row = arr[0];
            int col = arr[1];
            int time = arr[2];

            if(isVisited[row][col]) continue;
            isVisited[row][col] = true;
            
            if(row == grid.length - 1 && col == grid[0].length - 1) return Math.max(time, grid[row][col]);
            
            if(isValid(grid, row - 1, col, isVisited)) 
            {
                pq.add(new int[] {row - 1, col, Math.max(time, grid[row - 1][col])});
            }
            
            if(isValid(grid, row, col + 1, isVisited)) 
            {
                if(row == grid.length - 1 && col + 1 == grid[0].length - 1) return Math.max(time, grid[row][col + 1]);
                pq.add(new int[] {row, col + 1, Math.max(time, grid[row][col + 1])});
            }
            
            if(isValid(grid, row + 1, col, isVisited)) 
            {
                if(row + 1 == grid.length - 1 && col == grid[0].length - 1) return Math.max(time, grid[row + 1][col]);
                pq.add(new int[] {row + 1, col, Math.max(time, grid[row + 1][col])});
            }
            
            if(isValid(grid, row, col - 1, isVisited)) 
            {
                pq.add(new int[] {row, col - 1, Math.max(time, grid[row][col - 1])});
            }
        }
        return -1;
    }
    
    public boolean isValid(int[][] arr, int row, int col, boolean[][] isVisited) 
    {
        return row >= 0 && col >= 0 && row < arr.length && col < arr[0].length && !isVisited[row][col];
    }


    

    // 1387. Sort Integers by The Power Value

    // Simple Memoized Solution

        public int setUpDp(HashMap<Integer,Integer> map , int x)
    {
        if(map.containsKey(x)) return map.get(x);
        if(x%2 == 0) map.put(x,setUpDp(map,x/2)+1);
        else map.put(x,setUpDp(map,(3*x) + 1)+1);
        return map.get(x);
    }
    public int getKth(int lo, int hi, int k)
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,0);
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{
            if(a[1]!=b[1]) return a[1]-b[1];
            return a[0]-b[0];
        });
        
        for(int i = lo;i<=hi;i++) queue.add(new int[]{i,setUpDp(map,i)});        
        while(k-- > 1) queue.remove();
        return queue.peek()[0];
    }


    // 1054. Distant Barcodes
    // Similar Questions we did earlier

    public int[] rearrangeBarcodes(int[] barcodes) 
    {
        int n = barcodes.length;
        if(n == 1) return barcodes;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : barcodes) map.put(ele,map.getOrDefault(ele,0)+1);
        
        int[] ans = new int[n];
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{return b[1]-a[1];});
        for(int ele : map.keySet()) queue.add(new int[]{ele,map.get(ele)});
        int ptr = 0;
        
        while(queue.size() > 1)
        {
            int[] first = queue.remove();
            int[] second = queue.remove();
            
            ans[ptr++] = first[0];
            first[1]--;
            ans[ptr++] = second[0];
            second[1]--;
            
            if(first[1] > 0)queue.add(first);
            if(second[1] > 0)queue.add(second);
        }
        
        if(queue.size() > 0) ans[ptr++] = queue.peek()[0];
        return ans;
    }


    // 1654. Minimum Jumps to Reach Home
    // BFS Solution

        public int minimumJumps(int[] forbidden, int a, int b, int x)
    {
        HashSet<Integer> set = new HashSet<>();
        HashSet<String> vis = new HashSet<>();
        int maxLimit = x+a+b;
        for(int ele : forbidden) {
            set.add(ele);
            maxLimit = Math.max(maxLimit, ele + (2 * b));
        }
        
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        int level = 0;
        
        while(queue.size() > 0)
        {
            int size = queue.size();
            while(size-- > 0)
            {
                int[] pop = queue.removeFirst();
                if(vis.contains(pop[0]+","+pop[1])) continue;
                vis.add(pop[0]+","+pop[1]);
                if(pop[0] == x) return level;
                if(!set.contains(pop[0]+a)&&!vis.contains((pop[0]+a)+","+0)&&(pop[0]+a<=maxLimit)) queue.addLast(new int[]{pop[0]+a,0});
                if(pop[0]-b>=0&&!set.contains(pop[0]-b)&&!vis.contains((pop[0]-b)+","+(pop[1]-1))&&pop[1]-1>-2) queue.addLast(new int[]{pop[0]-b,pop[1]-1});
            }
            level++;
        }
        return -1;
    }



    // 823. Binary Trees With Factors

    public int numFactoredBinaryTrees(int[] arr)
    {
        int mod = (int)1e9 + 7;
        HashMap<Integer,Long> map = new HashMap<>();
        map.put(1,1l);
        for(int ele : arr) map.put(ele,1l);
        Arrays.sort(arr);
        
        for(int ele : arr)
        {
            for(int div : arr)
            {
                if(div < ele)
                {
                    if(ele%div == 0)
                    {
                        int v1 = ele/div;
                        int v2 = div;
                        if(!map.containsKey(v1) || !map.containsKey(v2)) continue;
                        map.put(ele,(long)(map.get(ele)+((long)map.get(v1)*(long)map.get(v2))%mod)%mod);
                    }
                }
                else break;
            }
        }
        long sum = 0;
        for(long ele : map.values()) sum=(sum+ele)%mod;
        return (int)(sum-1)%mod;
    }


    // 1424. Diagonal Traverse II

    public int[] findDiagonalOrder(List<List<Integer>> nums) 
    {
        HashMap<Integer,LinkedList<Integer>> map = new HashMap<>();
        int size = 0;
        int maxCoord = nums.size();
        for(int i = 0;i<nums.size();i++)
        {
            for(int j = 0;j<nums.get(i).size();j++)
            {
                maxCoord = Math.max(nums.get(i).size(),maxCoord);
                int idx = i+j;
                if(!map.containsKey(idx)) map.put(idx,new LinkedList<>());
                int val = nums.get(i).get(j);
                map.get(idx).addFirst(val);
                size++;
            }
        }
        int[] ans = new int[size];
        int k = 0;
        for(int i = 0;i<2*maxCoord;i++)
        {
            if(map.containsKey(i)) for(int ele : map.get(i)) ans[k++] = ele;
        }
        return ans;
    }



    // 1856. Maximum Subarray Min-Product
    // Simple Approach
    public int maxSumMinProduct(int[] nums) 
    {
        int mod = (int)1e9 + 7;
        int n = nums.length;
        int[] left = new int[n];
        Arrays.fill(left,-1);
        int[] right = new int[n];
        Arrays.fill(right,-1);
        long[] pfx = new long[n];
        
        pfx[0] = (long)nums[0];
        for(int i=1;i<n;i++) pfx[i] = pfx[i-1]+nums[i];
        Stack<Integer> st = new Stack<>();
        for(int i = 0;i<n;i++)
        {
            int val = nums[i];
            while(st.size() > 0 && nums[st.peek()] > val) right[st.pop()] = i; 
            st.push(i);
        }
        st = new Stack<>();
        for(int i = n-1;i>=0;i--)
        {
            int val = nums[i];
            while(st.size() > 0 && nums[st.peek()] > val) left[st.pop()] = i; 
            st.push(i);
        }
        
        long maxAns = 0;
        
        for(int i = 0;i<n;i++)
        {
            int l = left[i];
            int r = right[i];
            
            long sum = ((r!=-1) ? pfx[r-1] : pfx[n-1])-((l!=-1) ? pfx[l] : 0);
            
            maxAns = Math.max(maxAns , (sum*nums[i]));
        }
        return (int)(maxAns%mod);
    }

    // 1712. Ways to Split Array Into Three Subarrays
    // Important Problem
    // Check O(n) solution if you want
    // this is BS solution
    // We Need to find the range of valid points
    // Article Link -> https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/discuss/999113/JavaScala-Detailed-Explanation-Prefix-Sum-Binary-Search

    // [0,0,0,0]
    // [1,1,1,1]
    // [2,3,5,10]
    // [2,3,5,2,1,10]

    // [2,3,5,10]
    // first cut [1,2]
    // second cut [2,2]

    public int getLeftCut(int[] pfx , int x)
    {
        int n = pfx.length;
        int ans = -1;
        int j = n-2; // Imp
        int i = x;
        while(i <= j)
        {
            int mid = (i+j)/2;
            
            if(pfx[x-1] <= pfx[mid]-pfx[x-1] && pfx[mid]-pfx[x-1] <= pfx[n-1]-pfx[mid])
            {
                ans = mid;
                j = mid-1;
            }
            else if(pfx[x-1] > pfx[mid]-pfx[x-1]) i = mid+1;
            else j = mid-1;
        }
        return ans;
    }
    public int getRightCut(int[] pfx , int x)
    {
        int n = pfx.length;
        int ans = -1;
        int j = n-2; // Imp
        int i = x;
        while(i <= j)
        {
            int mid = (i+j)/2;
            if(pfx[x-1] <= pfx[mid]-pfx[x-1] && pfx[mid]-pfx[x-1] <= pfx[n-1]-pfx[mid])
            {
                ans = mid;
                i = mid+1;
            }
            else if(pfx[x-1] > pfx[mid]-pfx[x-1]) i = mid+1;
            else j = mid-1;
        }
        return ans;
    }
    public int waysToSplit(int[] nums)
    {
        long mod = (int)1e9+7;
        int n = nums.length;
        int[] pfx = new int[n];
        pfx[0] = nums[0];
        
        for(int i=1;i<n;i++) pfx[i] = pfx[i-1] + nums[i];
        long ans = 0;
        for(int i = 0;i<n-2;i++)
        {
            int left = getLeftCut(pfx,i+1);
            int right = getRightCut(pfx,i+1);
            if(right == -1 || left == -1) continue; 
            ans = (ans + (right-left+1))%mod;
        }
        return (int)(ans);
    }

    // 355. Design Twitter
    // Simple Solution
    // Check for other solution as well
    HashMap<Integer,HashSet<Integer>> map;
    LinkedList<int[]> posts;
    public Twitter() 
    {
        map = new HashMap<>();
        posts = new LinkedList<>();
    }
    
    public void postTweet(int userId, int tweetId) 
    {
        if(!map.containsKey(userId)) map.put(userId,new HashSet<>());
        posts.addFirst(new int[]{userId,tweetId});
    }
    
    public List<Integer> getNewsFeed(int userId)
    {
        List<Integer> ans = new ArrayList<>();
        
        for(int[] ele : posts)
        {
            if(map.get(userId).contains(ele[0]) || ele[0] == userId) ans.add(ele[1]);
            if(ans.size() == 10) break;
        }
        return ans;
    }
    
    public void follow(int followerId, int followeeId) 
    {
        if(!map.containsKey(followerId)) map.put(followerId,new HashSet<>());
        if(!map.containsKey(followeeId)) map.put(followeeId,new HashSet<>());
        map.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) 
    {
        map.get(followerId).remove(followeeId);
    }

    // 842. Split Array into Fibonacci Sequence
    // Recursive solution

    public List<Integer> splitIntoFibonacci(String num) 
    {
        List<Integer> ans = new ArrayList<>();
        getFib(num,0,0,0,false,false,ans);
        return ans;
    }
    
    public boolean getFib(String num , int i , int pp , int p , boolean isFirst , boolean isSecond ,  List<Integer> ans)
    {
        if(i == num.length() && ans.size()>2) return true;
        if(i == num.length() && ans.size()<=2) return false;
        int n = 0;
        boolean res = false;
        boolean isPrevZero = false;
        for(int j = i;j<num.length();j++)
        {
            int v = Integer.parseInt(""+num.charAt(j));
            if(n*10 < 0) return false;
            n*=10;
            if(n+10 < 0) return false; 
            n+=v;
            if(n == p + pp || !isFirst || !isSecond) 
            {
                if(n == 0) isPrevZero = true;
                ans.add(n);
                if(!isFirst) res = getFib(num,j+1,p,n,true,isSecond,ans);
                else if(!isSecond) res = getFib(num,j+1,p,n,isFirst,true,ans);
                else res = getFib(num,j+1,p,n,isFirst,isSecond,ans);
                if(!res) ans.remove(ans.size()-1);
                if(res) return true;
            }
            if(isPrevZero) break;
        }
        return res;
    }

    // 1551. Minimum Operations to Make Array Equal

    public int minOperations(int n) 
    {
        if(n%2==1)
        {
            n/=2;
            return (n*(n+1));
        }        
        n/=2;
        return n*n;
    }

    // 2419. Longest Subarray With Maximum Bitwise AND
    public int longestSubarray(int[] nums) 
    {
        List<int[]> ans = new ArrayList<>();
        int n = nums.length;
        int i = 0;
        int j = 0;
        while(j < n)
        {
            while(j < n && nums[j] == nums[i])
            {
                j++;
            }
            ans.add(new int[]{nums[i],j-i});
            i = j;
        }
        
        // for(int[] ele : ans) System.out.println(Arrays.toString(ele));
        
        int res = 0;
        int a = 0;
        
        for(int[]ele : ans)
        {
            if(ele[0] >= a)
            {
                if(a == ele[0]) res = Math.max(res,ele[1]);
                else res = ele[1];
                a = ele[0];
                
            }
        }
        
        return res;
    }


    // 2420. Find All Good Indices
    public List<Integer> goodIndices(int[] arr, int k) 
    {
        
        if(k == 1)
        {
            List<Integer> ans = new ArrayList<>();
            for(int i = 1;i<arr.length-1;i++) ans.add(i);
            return ans;
        }
        
        
        int n = arr.length;
        boolean[] left = new boolean[n];
        boolean[] right = new boolean[n];
        
        int j = 1;
        
        
        int count = k-1;
        while(j < n)
        {
            
            while(count > 0 && j < n)
            {
                if(arr[j] <= arr[j-1])
                {
                    count--;
                    j++;
                }
                else break;
            }
            if(count == 0 && j<n) 
            {
                left[j] = true;
                count++;
            }
            else 
            {
                count = k-1;
                j++;
            }
        }
        
        int i = n-2;
        count = k-1;
        while(i >= 0)
        {
            while(count > 0 && i>=0)
            {
                if(arr[i] <= arr[i+1])
                {
                    count--;
                    i--;
                }
                else break;
            }
            if(count == 0 && i>=0) 
            {
                right[i] = true;
                count++;
            }
            else 
            {
                count = k-1;
                i--;
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for(int h = 0;h<n;h++)
        {
            if(left[h] && right[h]) ans.add(h);
        }
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        return ans;
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        // return null;
    }


    // 926. Flip String to Monotone Increasing
    // Prefix Sum Solution

    // Basically we go through string and found out how much 1 before index much be 
    // flipped to 0 and how much 0 after index need to be flipped to 1.
    // adds them up and get min for result
    // We Are going to find that perfect splitting point

    public int minFlipsMonoIncr(String S) {
        int N = S.length();
        int[] P = new int[N + 1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + (S.charAt(i) == '1' ? 1 : 0);

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= N; ++j) {
            ans = Math.min(ans, P[j] + N-j-(P[N]-P[j]));
        }

        return ans;
    }




    // 2104. Sum of Subarray Ranges
    // Tedious Problem

    
}