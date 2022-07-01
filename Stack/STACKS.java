class stack
{

    // Celebrity Problem
    // O(n^2) Time  O(n) Space Approach

    int celebrity(int M[][], int n)
    {
        int[] outdeg = new int[n];
        
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<n;j++)
            {
                if(M[i][j] == 1 && i!=j) outdeg[i]++;
            }
        }
        
        int ans = -1;
        for(int i = 0;i<n;i++){
            if(outdeg[i] == 0){
                if(ans == -1) ans = i;
                else return -1;
            }
        }
        return ans;
    }



    // Leetcode 132 Pattern
    // Very Important Solution
    // Here, if we fix the peak, i.e. 3 in the 132 pattern, then we can determine if any numbers on its left and right satisfy the given pattern. We will do this with the help of a stack. 
    // Our stack implementation will take care of the 32 pattern and then we will iterate over the array to find if any number satisfies the 1 pattern
    // [3, 6, 4, 1, 2] // we are travsrsing from left to right
    public boolean find132pattern (int[] nums) 
    {
        Stack <Integer> stack = new Stack ();
        int second = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) 
        {
            if (nums [i] < second) return true;
            while (!stack.isEmpty() && nums [i] > stack.peek ()) second = stack.pop ();
            stack.push (nums [i]);
        }
        return false;
    }

    // Next Greater Element on Right
	// Similarly We Can Write Next Greater Element on the Left
	// By Using The Similar FAshion We can Write code for next Smaller element on left and right
    public static long[] nextLargerElement(long[] arr, int n){
        long[] ans = new long[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans,-1);
        
        st.push(0);
        
        for(int i=1;i<n;i++)
        {
            long val = arr[i];
            while(st.size()>0 && val>arr[st.peek()]){
                ans[st.peek()] = val;
                st.pop();
            }
            st.push(i);
        }
        return ans;
    }


    // Next Greater Element On the Left

    public static long[] nextLargerElementLeft(long[] arr, int n){
        long[] ans = new long[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans,-1);
        st.push(n-1);
        
        for(int i=n-2;i<=0;i--)
        {
            long val = arr[i];
            while(st.size()>0 && val>arr[st.peek()]){
                ans[st.peek()] = val;
                st.pop();
            }
            st.push(i);
        }
        return ans;
    }


    // 503. Next Greater Element II

    public int[] nextGreaterElements(int[] arr) 
    {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans,-1);
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{0,arr[0]});
        
        for(int i = 0;i<2*n;i++)
        {
            int idx = i;
            if(i >= n) idx = i - n;
            int[] val = new int[]{i,arr[idx]};
            
            while(st.size() > 0 && val[0] > st.peek()[0] && val[1] > st.peek()[1] ){
                ans[(st.peek()[0]>=n) ? st.peek()[0]-n : st.peek()[0]] = val[1];
                st.pop();
            }
            if(val[0] < n) st.push(val);
        }
        return ans;
    }



    // Largest Rectangle in Histogram 
    // Approach 1

    public int largestRectangleArea(int[] h)
    {
        Stack<int []> st = new Stack<>();
        st.push(new int[]{-1,-1});
        int area = 0;
        int val;
        int temp = 0;
        
        
        for(int i = 0;i<h.length;i++)
        {
            
            while(st.peek()[0] != -1 && h[i] <= st.peek()[1])
            {
                val = st.peek()[1];
                st.pop();
                temp = (i-st.peek()[0]-1) * val;
                area = Math.max(temp,area);
            }
            
            st.push(new int[]{i,h[i]});
        }
        
        while(st.peek()[0] != -1)
        {
            val = st.peek()[1];
            st.pop();
            temp = (h.length-st.peek()[0]-1)*val;
            area = Math.max(temp,area);
            
        }

        return area;
    }


    // Approach 2
    // Finding Next Smaller On Left 
    // Finding Next Smaller On Right
    

    public int largestRectangleArea(int[] h)
    {
        int[] rs = nsor(h);
        int[] ls = nsol(h);
        int area = 0;
        int temp;
        
        for(int i= 0 ;i<h.length;i++)
        {
            temp = (rs[i] - ls[i] - 1) * h[i];
            area = Math.max(area,temp);
        }
        
        return area;
    }
    
    public int[] nsor(int [] arr)
    {
        int[] res = new int[arr.length];
        Arrays.fill(res,arr.length);
        Stack <Integer> st = new Stack <>();
        st.push(-1);
        for(int i = 0;i<arr.length;i++)
        {
            while(st.peek()!=-1 && arr[i] < arr[st.peek()])
            {
                res[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
        
        return res;
    }
    
    public int[] nsol(int [] arr)
    {
        int[] res = new int[arr.length];
        Arrays.fill(res,-1);
        Stack <Integer> st = new Stack <>();
        st.push(-1);
        for(int i = arr.length-1;i>=0;i--)
        {
            while(st.peek()!=-1 && arr[i] < arr[st.peek()])
            {
                res[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
        
        return res;
    } 


    // 85. Maximal Rectangle

    // By Using The Concepts of Largest histogram Rectangle

    public int maximalRectangle(char[][] matrix)
    {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int area = 0;
        int [] arr = new int[matrix[0].length];
        Arrays.fill(arr,0);
        int n = matrix.length;
        int m = matrix[0].length;
         
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++) arr[j] =  matrix[i][j] == '1' ? arr[j] + 1 : 0;
             area = Math.max(largestRectangleArea(arr), area);
        }
        return area;
    }

    public int largestRectangleArea(int[] h){
        Stack<int []> st = new Stack<>();
        st.push(new int[]{-1,-1});
        int area = 0;
        int val;
        int temp = 0;
        
        for(int i = 0;i<h.length;i++){
            while(st.peek()[0] != -1 && h[i] <= st.peek()[1]){
                val = st.peek()[1];
                st.pop();
                temp = (i-st.peek()[0]-1) * val;
                area = Math.max(temp,area);
            }
            st.push(new int[]{i,h[i]});
        }
        
        while(st.peek()[0] != -1){
            val = st.peek()[1];
            st.pop();
            temp = (h.length-st.peek()[0]-1)*val;
            area = Math.max(temp,area);
            
        }
        return area;
    }

    // 946. Validate Stack Sequences

    public boolean validateStackSequences(int[] pushed, int[] popped)
    {
        Stack <Integer> st = new Stack <>();
        int j = 0;
        for(int i = 0;i<pushed.length;i++)
        {
            st.push(pushed[i]);
            while(st.size()!=0 && st.peek() == popped[j] && j<pushed.length){
                st.pop();
                j++;
            }
        }
        return st.size() == 0;
    }


    // 921. Minimum Add to Make Parentheses Valid

    public int minAddToMakeValid(String s) 
    {
        int open = 0;
        int close = 0;
        
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch == '(') open++;
            else if(ch == ')')
            {
                if(open > 0) open--;
                else close++;
            }
        }
        
        return open + close;
    }


    // 1021. Remove Outermost Parentheses

    public String removeOuterParentheses(String S){
        StringBuilder res = new StringBuilder();
        int open = 0;
        for(int i = 0;i<S.length();i++){
            char ch = S.charAt(i);
            if(ch == '(' && open++ > 0) res.append(ch);
            else if(ch == ')' && open-- > 1) res.append(ch);
        }
        return res.toString();
    }

    // 856. Score of Parentheses
    // Very Important Question

    // Approach 1 recursive

    public int scoreOfParentheses(String S) {
        return F(S, 0, S.length());
    }
    public int F(String S, int i, int j)
    {
        int ans = 0, bal = 0;
        for (int k = i; k < j; ++k){
            bal += S.charAt(k) == '(' ? 1 : -1;
            if (bal == 0) {
                if (k - i == 1) ans++;
                else ans += 2 * F(S, i+1, k);
                i = k+1;
            }
        }
        return ans;
    }

    // Approach 2 Iterative

    public int scoreOfParentheses(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) 
        {
            if (S.charAt(i) == '(') bal++;
            else 
            {
                bal--;
                if (S.charAt(i-1) == '(') ans += 1 << bal;
            }
        }
        return ans;
    }

    // 1190. Reverse Substrings Between Each Pair of Parentheses

    public String reverseParentheses(String s){
        LinkedList<Character> st = new LinkedList<>();

        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch == ')')
            {
                LinkedList<Character> q = new LinkedList<>();
                while(st.size()!=0 && st.get(st.size()-1)!='(') q.addLast(st.removeLast());
                if(st.size() > 0) st.removeLast();
                while(q.size() > 0) st.addLast(q.removeFirst());
            }
            else st.addLast(ch);
        }
        
        StringBuilder sb = new StringBuilder();
        while(st.size() > 0) sb.append(st.removeFirst());
        return sb.toString();
    }

    // 1249. Minimum Remove to Make Valid Parentheses

    // Approach 1
    public String minRemoveToMakeValid(String s) 
    {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> open = new Stack<>();
        Stack<Integer> close = new Stack<>();
        
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(open.size() > 0 && s.charAt(open.peek()) == '(' && ch == ')') open.pop();
            else if(ch == '(') open.push(i);
            else if(ch == ')') close.push(i);
        }
        
        while(open.size() > 0) sb.deleteCharAt(open.pop());
        while(close.size() > 0) sb.deleteCharAt(close.pop());
        
        return sb.toString();
    }

    // Approach 2 Important

    public String minRemoveToMakeValid(String s) 
    {
        int open = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch == ')')
            {
                if(open == 0) continue;
                else if(open > 0) open --;
            }
            else if(ch == '(') open++;
            sb.append(ch);
        }
        
        for(int i = sb.length()-1;i>=0;i--) /// Just removing the open brackets for whome we did'nt got closed brackette
        {
            char ch = sb.charAt(i);
            if(open > 0 && ch == '('){
                sb.deleteCharAt(i);
                open--;
            }
        }
        return sb.toString();
    }

    // 735. Asteroid Collision

    public int[] asteroidCollision(int[] asteroids) 
    {
        Stack<Integer> st = new Stack<>();
        for(int ele : asteroids){
            while(st.size() > 0 && st.peek() > 0 && ele < 0 && -1*ele > st.peek()) st.pop();
            if(st.size() > 0 && st.peek() > 0 &&  ele < 0 && -1*ele == st.peek()) st.pop();
            else if(st.size() > 0 && st.peek() > 0 && ele > 0) st.push(ele);
            else if(st.size() > 0 && st.peek() < 0 && ele < 0) st.push(ele);
            else if(st.size() > 0 && st.peek() < 0 && ele > 0) st.push(ele);
            else if(st.size() == 0) st.push(ele);
        }
                
        int[] ans = new int[st.size()];
        int j = st.size()-1;
        
        while(st.size() > 0 && j>=0) ans[j--] = st.pop();
        
        return ans;
    }

    // 402. Remove K Digits
    public String removeKdigits(String s, int k)
    {
        Stack <Integer> st = new Stack<>();
        st.push(-1);
        for(int i = 0;i<s.length();i++)
        {
            while(st.peek()!=-1 && s.charAt(i) < s.charAt(st.peek()) && k>0)
            {
                st.pop();
                k--;
            }
            if(st.peek() == -1 && s.charAt(i) == '0')
            {
                continue;
            }

            st.push(i);
        }
        
        while(k>0 && st.peek()!=-1)
        {
            st.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder(st.size()-1);
        
        if(st.peek() == -1)
        {
            return sb.append('0').toString();
        }
        while(st.peek()!= -1)
        {
            sb.append(s.charAt(st.pop()));
        }
        return sb.reverse().toString();
        
    }


    // 316. Remove Duplicate Letters
    // 1081. Smallest Subsequence of Distinct Characters
    // Both Has Same Solution
    // Important
    public String removeDuplicateLetters(String s)
    {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        StringBuilder sb = new StringBuilder();
        int[] freq = new int[26];
        for(int i = 0;i<s.length();i++)
        {
            freq[s.charAt(i)-'a']++;
        }
        boolean[]  vis = new boolean[26];
        
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            freq[ch - 'a']--;

            if(st.peek() == -1 || (ch > s.charAt(st.peek()) && vis[ch - 'a'] == false))
            {
                st.push(i);
                vis[ch - 'a'] = true;
            }
            else if(s.charAt(st.peek()) > ch && vis[ch - 'a'] == false)
            {
                
             while(st.peek() !=-1 &&freq[s.charAt(st.peek()) - 'a'] > 0 && s.charAt(st.peek()) > ch && vis[ch - 'a'] == false)
                {
                    char p = s.charAt(st.pop());
                    vis[p - 'a'] = false;
                }
                st.push(i);
                vis[ch - 'a'] = true;
            }
        }
        while(st.peek()!= -1) sb.append(s.charAt(st.pop()));
        return sb.reverse().toString();
    }


    // Traping Rain Water

    // Approach 1 Using Stack

    public int trap(int[] h)
    {
        int max = -(int)1e9;
        int ans = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        
        for(int ele : h)
        {
            if(ele >= max){
                while(st.peek()!=-1){
                    int pop = st.pop();
                    ans += max-pop;
                }
                max = ele;
            }
            else st.push(ele);
        }
        
        int nMax = -(int)1e9; // this logic is important
        if(st.size() > 1)
        {
            while(st.peek()!=-1)
            {
                nMax = Math.max(nMax,st.peek());
                ans += Math.min(max,nMax) - st.pop();
            }
        }
        return ans;
    }

    // Approach 2 Using 2 Pointers

    public int trap(int[] h)
    {
        if(h.length == 0) return 0;
        int rmax = h[h.length-1];
        int lmax = h[0];
        int water = 0;
        int i = 0;
        int j = h.length-1;
        while(i<j)
        {
            if(rmax<lmax){
                water += rmax - h[j];
                j--;
                rmax = Math.max(rmax,h[j]);
            }
            else{
                water +=lmax-h[i];
                i++;
                lmax = Math.max(lmax,h[i]);
            }
        }
        return water;
    }


    // Trapping Rain Water 2
    // Important Code 

    public int trapRainWater(int[][] heightMap)
    {
        if(heightMap.length == 0 || heightMap[0].length == 0) return 0;
        
        int n = heightMap.length; // Rows
        int m = heightMap[0].length; // Columns
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return heightMap[a/m][a%m] - heightMap[b/m][b%m];
        });
        
        boolean[][] vis  = new boolean[n][m];
        
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
                {
                    if(i == 0 || j == 0 || i == n-1 || j == m-1)
                    {
                        pq.add(i * m + j);
                        vis[i][j] = true;
                    }
                }
        }
        
        int water = 0;
        int ch = 0;
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        
        while(pq.size()!= 0)
        {
            int val = pq.remove();
            int r = val / m;
            int c = val % m;
            ch = Math.max(ch,heightMap[r][c]);
            water +=  ch - heightMap[r][c];
            
            for(int[] d : dir)
            {
                int x = r + d[0];
                int y = c + d[1];
                if(x>=0 && x<n && y>= 0 && y<m && !vis[x][y])
                { 
                    pq.add(x * m + y);
                    vis[x][y] = true;
                }
            }
        }
        
        return water;
    }


    // Number Of Valid Subarrays
    // Did by using the strategy Next Smaller Element
    // Submitted on pepcoding

    public static int validSubarrays(int[] nums) 
    {
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{-1,-1});
        int ans = 0;
        int i = 0;
        while(i<nums.length)
        {
           int val = nums[i];
           while(st.peek()[1] != -1 && st.peek()[0] > val)
           {
               int[] pop = st.pop();
               ans += i - pop[1];
           }
           st.push(new int[]{val,i});
           i++;
        }

        while(st.peek()[1]!=-1){
          ans+= i-st.pop()[1];
        }
        return ans;
    }

    // 224. Basic Calculator

    // Important Cases To Consider
    // "- (3 + (4 + 5))"
    // "(3-(5-(8)-(2+(9-(0-(8-(2))))-(4))-(4)))"
    // "64646464664"
    // do dryrun this code


    public static int calculate(String s)
    {
        int len = s.length(), sign = 1, result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; i++) 
        {
            if (Character.isDigit(s.charAt(i))) 
            {
                int sum = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) 
                {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sum * sign;
            } 
            else if (s.charAt(i) == '+') sign = 1;                
            else if (s.charAt(i) == '-') sign = -1;    
            else if (s.charAt(i) == '(') 
            {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } 
            else if (s.charAt(i) == ')') result = result * stack.pop() + stack.pop();
        }
        return result;
    }


    // Basic Calculator ||

    // With Stack Solution Copied From Leetcode
    // Dryrun On this
    //Previous operation kee basis par chalenge in
    // This doesnot have '(' and ')' 
    public int calculate(String s) 
    {
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) 
        {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) currentNumber = (currentNumber * 10) + (currentChar - '0'); 

            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) 
            {
                if (operation == '-') stack.push(-currentNumber);
                else if (operation == '+') stack.push(currentNumber);
                else if (operation == '*') stack.push(stack.pop() * currentNumber);
                else if (operation == '/') stack.push(stack.pop() / currentNumber);

                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) result += stack.pop();
        return result;
    }

    // Without Stack Solution
    // "0*1"
    // Very Important Code

    public int calculate(String s)
    {
        int ans = 0;
        int res = -(int)1e10; // to handle cases like "0*1"
        int pmSign = 1;
        int mdSign = 1;
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(Character.isDigit(ch))
            {
                int number = (ch-'0');
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i+1)))
                {
                    number = number*10+(s.charAt(i+1)-'0');
                    i++;
                }
                
                if(res == -(int)1e10) res = number;
                else
                {
                    if(mdSign == 1) res*=number;
                    else if(mdSign == -1) res/=number;
                }
            }
            else if(ch == '+'){
                ans += pmSign*res;
                pmSign = 1;
                res = -(int)1e10;
            }
            else if(ch == '-'){
                ans += pmSign*res;
                pmSign = -1;
                res = -(int)1e10;
            }
            else if(ch == '*'){
                mdSign = 1;
            }
            else if(ch == '/'){
                mdSign = -1;
            }
        }
        if(res > 0) return ans+= res*pmSign;
        return ans;
    }


    // Basic Calculator |||
    // Non Working Code

    public static int calculate(String s) 
    {
        Stack<Integer> st = new Stack<>();
        st.push(0);
        st.push(1);
        int pmd = 2;
        int pas = 1;

        int res = -(int)1e9;
        int ans = 0;

        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(Character.isDigit(ch+""))
            {
                int number = Integer.parseInt(ch+"");
                while(i+1 < s.length() && Character.isDigit(s.charAt(i+1)))
                {
                    number = number*10 + Integer.parseInt(s.charAt(i+1)+"");
                    i++;
                }

                if(res == -(int)1e9)
                {
                    res = number;
                }
                else
                {
                    if(pmd == 2)
                    {
                        res*= number;
                    }
                    else if(pmd == -2)
                    {
                        res/=number;
                    }
                }
            }
            else if(ch == '+')
            {
                ans += res*pas;
                res = -(int)1e9;
                pas = 1;
            }
            else if(ch == '-')
            {
                ans += res*pas;
                res = -(int)1e9;
                pas = -1;
            }
            else if(ch == '*')
            {
                if(i + 1 < s.length() && s.charAt(i+1) == '(') continue;
                else pmd = 2;
            }
            else if(ch == '/')
            {
                if(i + 1 < s.length() && s.charAt(i+1) == '(') continue;
                else pmd = -2;
            }
            else if(ch == '(')
            {
                if(res == -(int)1e9)
                {
                    st.push(ans);
                    st.push(pas);
                }
                else if(res!=-(int)1e9)
                {
                    st.push(ans);
                    st.push(pas);

                    st.push(res);
                    if(i-1 >= 0 && s.charAt(i-1) == '*') st.push(2);
                    if(i-1 >= 0 && s.charAt(i-1) == '/') st.push(-2);
                    else st.push(1);
                }
                
                pmd = 2;
                pas = 1;
                ans = 0;
                res = -(int)1e9;
            }
            else if(ch == ')')
            {
                if(i + 1 < s.length() && s.charAt(i+1) == '*' || s.charAt(i+1) == '/') continue; 
                if(res!=-(int)1e9)
                {
                    ans += pas*res;
                }
                int ope = st.pop();
                int val = st.pop();
                if(ope == 1)
                {
                    st.push(val + ans);
                }
                else if(ope == -1)
                {
                    st.push(val - ans);
                }
                else if(ope == 2)
                {
                    st.push(val * ans);
                }
                else if(ope == -2)
                {
                    st.push(val/ans);
                }
                ans = 0;
                res = -(int)1e9;
            }
        }

        if(ans != 0 || res!=-(int)1e9)
        {
            if(ans!=0 && res == -(int)1e9) st.push(ans);
            else if(ans!=0 && res != -(int)1e9)
            {
                if(pmd == 2)
                {
                    st.push(ans*res);
                }
                else if(pmd == -2)
                {
                    st.push(ans/res);
                }

            }
            else if(ans == 0 && res!=-(int)1e9)
            {
                st.push(res);
            }
        }


        st.push(1);

        int FinalResult = 0;

        while(st.size() > 0)
        {
            int op = st.pop();
            if(op == 1)
            {
                FinalResult = FinalResult + st.pop();
            }
            else if(op == -1)
            {
                FinalResult = st.pop() - FinalResult ;
            }
            else if(op == 2)
            {
                FinalResult = st.pop()*FinalResult;
            }
            else if(op == -2)
            {
                FinalResult = st.pop()/FinalResult;
            }
        }


        return FinalResult;

    }

































    // 1673. Find the Most Competitive Subsequence

    public int[] mostCompetitive(int[] nums, int k) 
    {
        int[] ans = new int[k];
        // Stack<Integer> st = new Stack<>(); // We can do this without stack as well
        int j = 0 ;// Stack Pointer pointing to array
        for(int i = 0;i<nums.length;i++)
        {
            int ele = nums[i];
            while(j > 0 && ele < ans[j-1] && nums.length - i + (j) > k) j--;
            if(j == k) continue;
            ans[j++] = ele;
        }
        
        // for(int i = k-1;i>=0;i--) ans[i] = st.pop();
        return ans;
    }


    // 1381. Design a Stack With Increment Operation
    // Important Solution
    // Range Query type operations
    int n;
    int[] inc;
    Stack<Integer> stack;
    public CustomStack(int maxSize) {
        n = maxSize;
        inc = new int[n];
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.size() < n)
            stack.push(x);
    }

    public int pop() {
        int i = stack.size() - 1;
        if (i < 0) return -1;
        if (i > 0) inc[i - 1] += inc[i];
        int res = stack.pop() + inc[i];
        inc[i] = 0;
        return res;
    }

    public void increment(int k, int val) { // Increment logic in important ie we 
        // are using an additional array to store the incremental value till that index
        int i = Math.min(k, stack.size()) - 1;
        if (i >= 0)
            inc[i] += val;
    }


    // 641. Design Circular Deque

    public class node
    {
        int data = 0;
        node next = null;
        node prev = null;
        
        node(){
        }
        node(int data){
            this.data = data;
        }
        node(int data , node prev , node next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
    
    public node front = null;
    public node rear = null;
    public int size = 0;
    public int maxSize = 0;
    
    public MyCircularDeque(int k)
    {
        maxSize = k;
    }
    
    public boolean insertFront(int value) 
    {
        if(size == 0)
        {
            node temp = new node(value);
            front = temp;
            rear = temp;
            size++;
            return true;
            
        }
        else if(size > 0 && size < maxSize)
        {
            node temp = new node(value);
            temp.next = front;
            front.prev = temp;
            front = temp;
            size++;
            return true;
        }
        return false;
    }
    
    public boolean insertLast(int value) 
    {
        if(size == 0)
        {
            node temp = new node(value);
            rear = temp;
            front = temp;
            size++;
            return true;
            
        }
        else if(size > 0 && size < maxSize)
        {
            node temp = new node(value);
            rear.next = temp;
            temp.prev = rear;
            rear = temp;
            size++;
            return true;
        }
        return false;
    }
    
    public boolean deleteFront() 
    {
        if(size == 1)
        {
            front = null;
            rear = null;
            size--;
            return true;
        }
        else if(size > 1)
        {
            node temp = front;
            front = front.next;
            temp.next = null;
            front.prev = null;
            size--;
            return true;
        }
        return false;
    }
    
    public boolean deleteLast() 
    {
        if(size == 1)
        {
            front = null;
            rear = null;
            size--;
            return true;
        }
        else if(size > 1)
        {
            node temp = rear;
            rear = rear.prev;
            temp.prev = null;
            rear.next = null;
            size--;
            return true;
        }
        return false;    
    }
    
    public int getFront() 
    {
        if(size > 0){
            return front.data;
        }
        return -1;
    }
    
    public int getRear() 
    {
        if(size > 0) return rear.data;
        return -1;
    }
    
    public boolean isEmpty() 
    {
        return size == 0;    
    }
    
    public boolean isFull() 
    {
        return size == maxSize;    
    }


    // Max Stack Most Optimized Solution

    public static class MaxStack 
  {
    public class node{
      int data = 0;
      node prev = null;
      node next = null;

      node(){
      }
      node(int data){
        this.data = data;
      }
    }
    public TreeMap<Integer,LinkedList<node>> map;
    public node dummy = new node(-1);
    public node front = dummy;
    public node rear = dummy;
    public int size = 0;

    public MaxStack() 
    { 
      map = new TreeMap<>();
    }

    public void push(int x) 
    {
      node temp = new node(x);
      rear.next = temp;
      temp.prev = rear;
      rear = rear.next;
      if(!map.containsKey(x)) map.put(x,new LinkedList<>());
      map.get(x).addLast(temp);
      size++;
    }

    public int pop() 
    {
      if(size == 0) return -1;
      node temp = rear;
      rear = rear.prev;
      rear.next = null;
      temp.prev = null;
      map.get(temp.data).removeLast();
      if(map.get(temp.data).size() == 0) map.remove(temp.data);
      size--;
      return temp.data;
    }

    public int top() {
      return (size > 0) ? rear.data : -1;
    }

    public int peekMax() {
      if(size == 0) return -1;
      int max = map.lastKey();
      return max;
    }

    public int popMax() 
    {
      if(size == 0) return -1;
      int max = map.lastKey();
      node temp = map.get(max).removeLast();
      if(rear == temp) rear = rear.prev;
      if(front == temp) front = front.next;  
      node p = temp.prev;
      node n = temp.next;
      temp.prev = null;
      temp.next = null;
      p.next = n;
      if(n!=null) n.prev = p; // imp
      size--;
      if(map.get(max).size() == 0) map.remove(max);
      return temp.data;
    }


    // 1003. Check If Word Is Valid After Substitutions


    public boolean isValid(String s) 
    {
        if(s.length() < 3) return false;
        Stack<String> st = new Stack<>();
        String res = "";
        
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch == 'a')
            {
                if(!res.equals("")){
                    st.push(res);
                    res = ch + "";
                }
                else{
                    res+=ch;
                }
            }
            else if(ch == 'b')
            {
                if(res.equals("ab")) return false;
                if(!res.equals("a") && st.size() > 0 && st.peek().equals("a")){
                    st.pop();
                    st.push("ab");
                    continue;
                }
                if(!res.equals("a")){
                    st.push(res);
                    res = ch + "";
                }
                else{
                    res+=ch;
                }
            }
            else if(ch == 'c')
            {
                if(res.equals("a") || res.equals("b")) return false;
                if(!res.equals("ab") && st.size() > 0 && st.peek().equals("ab")){
                    st.pop();
                    continue;
                }
                if(!res.equals("ab"))
                {
                    st.push(res);
                    res = ch + "";
                }
                else res = "";
            }
        }
        return st.size() == 0 && res == "";
    }

    // Most Optimized Solution 

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            if (c == 'c') {
                if (stack.isEmpty() || stack.pop() != 'b') return false;
                if (stack.isEmpty() || stack.pop() != 'a') return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }


    // Design Hit Counter Submitted On Pepcoding Portal

    public class HitCounter 
    {

        Queue<Integer> hits;
        public HitCounter() 
        {
            hits = new LinkedList<Integer>();
        }
        public void hit(int timestamp) {
            hits.add(timestamp);
        }
        public int getHits(int timestamp) 
        {
            while (hits.size() > 0 && hits.peek() <= timestamp - 300) hits.poll();
            return hits.size();
        }
    }


    // Using A Circular Array


    public class HitCounter 
    {

        int[] ts;
        int[] hits;

        public HitCounter() 
        {
            ts = new int[300];
            hits = new int[300];
        }

        public void hit(int timestamp) 
        {
            int i = timestamp % 300;
            if (ts[i] != timestamp) 
            {
                ts[i] = timestamp;
                hits[i] = 1;
            }
            else 
            {
                hits[i] ++;
            }
        }

        public int getHits(int timestamp) 
        {
            int result = 0;
            for (int i = 0; i < hits.length; i++) 
            {
                if (timestamp - ts[i] < 300)
                {
                    result += hits[i];
                }
            }
            return result;
        }
    }

    // 933. Number of Recent Calls

    LinkedList<Integer> queue;

    public RecentCounter() 
    {
        queue = new LinkedList<Integer>();
    }

    public int ping(int t) 
    {
        queue.addLast(t);
        while (queue.getFirst() < t - 3000) queue.removeFirst();
        return queue.size();
    }

    // Moving Average From Data Stream Submitted on Nados

    LinkedList<Integer> queue = new LinkedList<>();
    int size = 0;
    double sum = 0;
    public MovingAverage(int size) {
      this.size = size;
    }

    public double next(int val) 
    {
      if(queue.size()+1 <= size)
      {
        queue.addLast(val);
        sum+=val;
        return sum/queue.size(); 
      }

      int rem = queue.removeFirst();
      sum-=rem;
      queue.addLast(val);
      sum+=val;
      return sum/queue.size();
    }




}