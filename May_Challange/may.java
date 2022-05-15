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

}