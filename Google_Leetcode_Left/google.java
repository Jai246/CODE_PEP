class google
{
    // 66. Plus One
    // Simple Solution'

    public int[] plusOne(int[] digits) 
    {
        int n = digits.length;
        digits[n-1]++;
        int i = n-2;
        int carry = 0;        
        if(digits[n-1] == 10)
        {
            digits[n-1] = 0;
            carry = 1;
        }
        
        while(i>=0 && carry == 1)
        {
            if(digits[i] == 9)
            {
                digits[i] = 0;
            }
            else if(digits[i] < 9)
            {
                digits[i]++;
                carry = 0;
            }
            i--;
        }
        
        if(carry == 1)
        {
            int[] ans = new int[n+1];
            ans[0] = 1;
            return ans;
        }
        return digits;
    }

    // 140. Word Break II

    // Simple Backtracking Solution
    // putting cuts on coorect positions only

    public List<String> wordBreak(String s, List<String> wordDict) 
    {
        HashSet<String> set = new HashSet<>();
        for(String ele : wordDict) set.add(ele);
        List<String> ans = new ArrayList();
        getList(s,set,0,new ArrayList<>(),ans);
        return ans;
    }
    
    public void getList(String s , HashSet<String> set , int i , List<String> temp , List<String> ans)
    {
        if(i == s.length())
        {
            StringBuilder st = new StringBuilder();
            for(String ele : temp) st.append(ele);
            ans.add(st.toString());
            return;
        }
        for(int j = i+1;j<=s.length();j++)
        {
            if(set.contains(s.substring(i,j)))
            {
                temp.add(s.substring(i,j));
                if(j < s.length()) temp.add(" ");
                getList(s,set,j,temp,ans);
                temp.remove(temp.size()-1);
                if(j < s.length()) temp.remove(temp.size()-1);
            }
        }
    }


    // 162. Find Peak Element
    // Important Solution
    // Visualize it using Graph

    public int findPeakElement(int[] nums) 
    {
        int l = 0, r = nums.length - 1;
        while (l < r) 
        {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) r = mid;
            else l = mid + 1;
        }
        return l;
    }


    // 228. Summary Ranges

    public List<String> summaryRanges(int[] nums) 
    {
       ArrayList<String> al=new ArrayList<>();
        
        for(int i=0;i<nums.length;i++)
        {
            int start=nums[i];
            while(i+1<nums.length && nums[i]+1==nums[i+1]) i++;
            
            if(start!=nums[i])
            {
                al.add(""+start+"->"+nums[i]);
            }
            else
            {
                al.add(""+start);
            }
        }
        return al;
    }



    // 257. Binary Tree Paths

    public void addPath(TreeNode root, List<String> ans, String s)
    {    
        if(root.left== null && root.right == null) ans.add(s + root.val);
        if(root.left != null) addPath(root.left, ans, s+root.val + "->"); 
        if(root.right !=null) addPath(root.right, ans, s+root.val + "->");
    }
    public List<String> binaryTreePaths(TreeNode root) 
    {
        ArrayList<String> A = new ArrayList<>(); 
        addPath(root,A,"");     
        return A;                              
    }


    // 284. Peeking Iterator

    Queue<Integer> q;
    public PeekingIterator(Iterator<Integer> iterator) 
    {
        q = new LinkedList<>();
        while(iterator.hasNext()) q.add(iterator.next());  
    }
    
    public Integer peek() 
    {
        return q.peek();
    }
    @Override
    public Integer next() 
    {
        return q.remove();
    }
    @Override
    public boolean hasNext() 
    {
        return q.size()!=0;
    }


    // 326. Power of Three
    // Simple Recursive Solution
    // Math solution is horrible

    public boolean isPowerOfThree(int n) 
    {
        if(n==0) return false;
        if(n==1) return true;
        
        if(n%3==0)
        {
            return isPowerOfThree(n/3);
        }
        else
        {
            return false;
        }
    }


    
}