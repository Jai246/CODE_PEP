public class contests
{
	// BIWEEKLY CONTEST 68

	// 2114. Maximum Number of Words Found in Sentences

	public int mostWordsFound(String[] sentences) 
    {
        int ans = -(int)1e9;
        for(String ele : sentences){
            String[] temp = ele.split(" "); 
            ans = Math.max(temp.length,ans);
        }
        return ans;
    }

    // 2115. Find All Possible Recipes from Given Supplies
    // Important Question Using Kahns Algo
    // Calculate Indegree After Creating the Graph

    HashMap<String,ArrayList<String>> map;
    HashSet<String> supp;
    HashSet<String> recep;
    HashSet<String> made;
    List<String> ans;
    
    public boolean find(String ing)
    {
        boolean res = true;
        if(map.containsKey(ing))
        {
            for(String ele : map.get(ing))
            {
                res = find(ele);
            }    
        }
        else if(supp.contains(ing) || recep.contains(ing)) return true;
        
        return res;
    }
    
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) 
    {
        supp = new HashSet<>();
        for(String ele : suplies) set1.add(ele);
        
        recep = new HashSet<>();
        for(String ele : recepies) set2.add(ele);
        
        made = new HashSet<>();
        
        ans = new ArrayList<>();
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        for(int i = 0;i<recepies.length;i++)
        {
            map.put(recepies[i],new ArrayList<>());
            for(List<String> ele : ingredients)
            {
                for(String t : ele) map.get(recepies[i]).add(t);
            }
        }
    }


    // 678. Valid Parenthesis String
    // Important Question

    public boolean checkValidString(String s) 
    {
        Stack<Integer> s1 = new Stack<>(); // for opening brackets
        Stack<Integer> s2 = new Stack<>(); // for stars
        
        
        for(int i = 0;i<s.length();i++)
        {
            char c = s.charAt(i);
            
            if(c == '(') s1.push(i);
            else if(c == ')')
            {
                if(s1.size() > 0) s1.pop();
                else if(s2.size() > 0) s2.pop();
                else return false;
            }
            else s2.push(i);
        }
        
        while(s1.size()!=0) // checking the left over opening bracketsss
        {
            if(s2.size() == 0) return false;
            int pos = s1.pop();
            if(s2.size() > 0 && s2.peek() > pos) s2.pop();
            else return false;
        }
        return true;
    }

    // 2116. Check if a Parentheses String Can Be Valid
    // Important
    public boolean canBeValid(String s, String locked) 
    {
        int n = s.length();
        if (n % 2 == 1) 
        {
          return false;
        }

        int remain = 0;
        for (int i = 0; i < n; i++) 
        {
          if (locked.charAt(i) == '1' && s.charAt(i) == ')') {
            if (remain == 0) return false;
            else remain--;
          } 
          else remain++;
        }

        remain = 0;
        for (int i = n - 1; i >= 0; i--) 
        {
          if (locked.charAt(i) == '1' && s.charAt(i) == '(')
          {
            if(remain == 0) return false;
            else remain--; 
          } 
          else remain++;
        }
        return true;
    }


    // Weekly Contest 274

    // 2124. Check if All A's Appears Before All B's

    public boolean checkString(String s) 
    {
        boolean bAppear = false;
        boolean aAppear = false;
        
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch == 'a'){
                aAppear = true;
                if(bAppear) return false;
            }
            else if(ch == 'b') bAppear = true;
        }
        
        return true;
    }


    // 2125. Number of Laser Beams in a Bank

    public int countDevices(String row){
        int count = 0;
        for(int i = 0;i<row.length();i++){
            char ch = row.charAt(i);
            if(ch == '1') count++;
        }
        return count;
    }
    
    public int numberOfBeams(String[] bank) {
        int ans = 0;
        int prev = 0;
        int curr = 0;
        prev = countDevices(bank[0]);
        for(int i = 1;i<bank.length;i++){
            curr = countDevices(bank[i]);
            if(curr == 0) continue;
            ans += prev * curr;
            prev = curr;
            curr = 0;
        }
        return ans;       
    }

    // 2126. Destroying Asteroids
    // Simple Sorting Mei Confuse Ho gaya tha Meine Binary Search LAga Diya Tha lower Bound Nikalne Ke Liye

    public boolean asteroidsDestroyed(int mass, int[] asteroids) 
    {
        Arrays.sort(asteroids);
        long masss = mass;
        for (int asteroid : asteroids) 
        {
            if ((long) asteroid > masss) return false;
            masss += asteroid;
        }
        return true;
    }

    





}