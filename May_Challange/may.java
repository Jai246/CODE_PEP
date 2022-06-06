class may
{
    // 1679. Max Number of K-Sum Pairs

    // Same As Two Sum Problem
    public int maxOperations(int[] nums, int k)
    {
        int count = 0;
        Arrays.sort(nums);        
        int i = 0;
        int j = nums.length-1;
        
        while(i < j)
        {
            if(nums[i] + nums[j] == k) {
                count ++;
                i++;
                j--;
            }
            else if(nums[i] + nums[j] < k) i++;
            else j--;
        }
        return count;
    }

    //844. Backspace String Compare
    // A simple Approach would we to use a stack and generate two seperate strings and compare them
    
    // Very Important Approach
    // Traversing from the back
    // Do remember this tech

    public boolean backspaceCompare(String s, String t)
    {
        int i = s.length()-1;
        int j = t.length()-1;
        
        int skips = 0;
        int skipt = 0;
        
        while(i >=0 || j >= 0)
        {
            while(i>=0)
            {
                if(s.charAt(i) == '#') skips++;  // b#c# will we counted in one go
                else if(skips > 0) skips--;
                else break;
                i--;
            }
            
            while(j>=0)
            {
                if(t.charAt(j) == '#') skipt++;
                else if(skipt > 0) skipt--;
                else break;
                j--;
            }
            
            if(i >=0  && j >=0 && s.charAt(i)!=t.charAt(j)) return false;
            if ((i >= 0) != (j >= 0)) return false; 
            // chaecking that aren't we comparing with nothing
            
            i--;
            j--;
        }
        return true;
    }

    // 1209. Remove All Adjacent Duplicates in String II
    // Instead of String use string builder 
    // Convert s to char array
    public String removeDuplicates(String s, int k) 
    {
        Stack<int[]> st = new Stack<>();
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(st.size() == 0)
            {
                st.push(new int[]{(int)ch,1});
                if(st.peek()[1] == k) st.pop();
            }
            else
            {
                if(st.peek()[0] == (int)ch) 
                {
                    st.peek()[1]++;
                    if(st.peek()[1] == k) st.pop();
                }
                else st.push(new int[]{(int)ch,1});
            }
        }
        String ans = "";
        while(st.size() > 0)
        {
            int ch = st.peek()[0];
            int count = st.pop()[1];
            
            while(count > 0)
            {
                ans = (char)ch + ans;
                count--;
            }
        }
        return ans;
    }

    //341. Flatten Nested List Iterator
    // Very Easy Solution
    // just Create a queue of values first using recursion
    public class NestedIterator implements Iterator<Integer> 
    {

        private Queue<Integer> queue = new LinkedList();
        
        public NestedIterator(List<NestedInteger> nestedList) 
        {
            helper(nestedList);
        }
        
        public void helper(List<NestedInteger> list)
        {
            if (list == null) return;
            
            for(NestedInteger in: list)
            {
                if (in.isInteger()) queue.offer(in.getInteger());
                else helper(in.getList());
            }
        }

        @Override
        public Integer next() 
        {
            if(hasNext()) return queue.poll();
            else return -1;
        }

        @Override
        public boolean hasNext() 
        {
            if (!queue.isEmpty()) return true;
            else return false;
        }
    }

    // 1641. Count Sorted Vowel Strings

    public int countVowelStrings(int n) 
    {
        int[] dp = new int[5];
        Arrays.fill(dp,1);
        
        for(int x = 0;x<n-1;x++)
        {
            int[] dp2 = new int[5];
            
            for(int i = 0;i<5;i++)
            {
                for(int j = i;j<5;j++)
                {
                    dp2[j] += dp[i];
                }
            }
            dp = dp2;
        }
        
        int count = 0;
        for(int ele : dp) count += ele;
        return count;
    }

    // 47. Permutations II
    // Important Question
    // Sorting is Important
    public List<List<Integer>> permuteUnique(int[] nums) 
    {
        List<List<Integer>> permutations = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(permutations, new ArrayList<>(), nums, new boolean[nums.length]);
        return permutations;
    }

    public void backtracking(List<List<Integer>> permutations, List<Integer> current, int[] nums, boolean[] used) 
    {
        if (current.size() == nums.length) permutations.add(new ArrayList<>(current));
        else 
        {
            for (int i = 0; i < nums.length; i++) 
            {
                if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
                current.add(nums[i]);
                used[i] = true;
                backtracking(permutations, current, nums, used);
                used[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }

    // 29. Divide Two Integers
    // IMportant Lee125 Solution

    public static int divide(int dividend, int divisor) 
    {
        if (Integer.MIN_VALUE == dividend && divisor == -1) 
        {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        while (a - b >= 0) 
        {
            int tmp = b;
            int count = 1;
            while (a - (tmp << 1) >= 0) 
            {
                tmp <<= 1;
                count <<= 1;
            }
            a -= tmp;
            res += count;
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }


    
}