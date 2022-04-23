class pepYtList
{

    // NO OF EMPLOYEES UNDER EVERY MANAGER SUBMITTED ON PEPCODING'S PORTAL

    public static void NOoFEmployee(String[] args) 
    {
	    Scanner scn = new Scanner(System.in);
	    int n = Integer.parseInt(scn.nextLine());
	    String ceo = "";

	    HashMap<String, HashSet<String>> map = new HashMap<>();
	    for (int i = 0; i < n; i++) {
	      String[] parts = scn.nextLine().split(" ");
	      String emp = parts[0];
	      String man = parts[1];

	      if (man.equals(emp)) {
	        ceo = man;
	        continue;
	      }

	      if (map.containsKey(man)) {
	        HashSet<String> emps = map.get(man);
	        emps.add(emp);
	      } else {
	        HashSet<String> emps = new HashSet<>();
	        emps.add(emp);
	        map.put(man, emps);
	      }
	    }

	    HashMap<String, Integer> ans = new HashMap<>();
	    size(map, ceo, ans);

	    for (String emp : ans.keySet()) {
	      System.out.println(emp + " " + ans.get(emp));
	    }
    }

	public static int size(HashMap<String, HashSet<String>> map , String ceo , HashMap<String, Integer> ans)
	{
		if(!map.containsKey(ceo))
		{
			ans.put(ceo,0);
			return 1;
		}
		int size = 0;
		for(String ele : map.get(ceo))
		{
			size += size(map,ele,ans);
		}
		ans.put(ceo,size);
		return size+1;
	}


	// FIND ITINERARY FROM TICKET PEPCODING PORTAL

	public static void ITINERARY(String[] args) 
	{
		Scanner scn = new Scanner(System.in);
		int noofpairs_src_des = scn.nextInt();
		HashMap<String, String> map = new HashMap<>();
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < noofpairs_src_des; i++) {
			String s1 = scn.next();
			String s2 = scn.next();
			map.put(s1, s2);
			set.add(s1);
		}

		// first we have to find starting point
		ArrayList<String> al = new ArrayList<String>(map.keySet());
		for (String s : al) {
			if (set.contains(map.get(s))) {
				set.remove(map.get(s));
			}
		}
		String start = set.toString(); // because at the end only one string will be there
		// start will be of a form [Bombay] coz .toString on HashSet will return an Array Of String
		// this will work for hashSet,hashmap,priorityQueue and other objects
		// for array it works witha  different code
		// Arrays.toString(arr)
		StringBuilder sb = new StringBuilder(start);
		// [Bombay] because string is like this <-
		sb.deleteCharAt(0);
		sb.deleteCharAt(sb.length() - 1);
		start = sb.toString();

		while (map.containsKey(start)) {
			if(map.size() == 1){
				System.out.print(start + " -> " + map.get(start) + ".");
				break;
			}
			System.out.print(start + " -> ");
			String nstart = map.get(start);
			map.remove(start);
			start = nstart;
		}
	}

	// 1497. Check If Array Pairs Are Divisible by k

	public boolean canArrange(int[] arr, int k) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        
        for(int ele : arr)
        {
            int rem = ele%k;
            if(rem < 0) rem+=k;
            map.put(rem,map.getOrDefault(rem,0)+1);
        }
        
        ArrayList<Integer> al = new ArrayList<>(map.keySet());
        
        for(int ele : map.keySet())
        {
            if(ele == 0)
            {
                if(map.get(ele)%2!=0) return false;
            }
            else if(2*ele == k)
            {
                if(map.get(ele)%2!=0) return false;
            }
            else
            {
                int a = map.get(ele);
                int b = map.getOrDefault(k - ele,0);
                if(a!=b) return false;
            }
        }
        return true;
    }

    // Count distinct elements in every window 

    ArrayList<Integer> countDistinct(int A[], int n, int k)
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        
        k = k-1;
        int si = 0;
        int ei = 0;
        
        while(ei<A.length)
        {
            map.put(A[ei],map.getOrDefault(A[ei],0)+1);
            
            if(ei-si == k)
            {
                ans.add(map.size());
                map.put(A[si],map.get(A[si])-1);
                if(map.get(A[si]) == 0) map.remove(A[si]);
                si++;
            }
            ei++;
        }
        return ans;
    }

    // Largest subarray with 0 sum 

    int maxLen(int arr[], int n)
    {
        int maxLen = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        int pfs = 0;
        map.put(0,-1);
        for(int i = 0;i<n;i++)
        {
            pfs+=arr[i];
            
            if(!map.containsKey(pfs)) map.put(pfs,i);
            else
            {
                int temp = map.get(pfs);
                maxLen = Math.max(maxLen,i-temp);
            }
        }
        return maxLen;
    }

    // ZERO SUM SUBARRAYS

	public static long findSubarray(long[] arr ,int n) 
    {
        int maxCount = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        int pfs = 0;
        map.put(0,1);
        for(int i = 0;i<n;i++)
        {
            pfs+=arr[i];
            
            if(!map.containsKey(pfs)) map.put(pfs,1);
            else
            {
                int temp = map.get(pfs);
                maxCount += temp;
                map.put(pfs,map.get(pfs)+1);
            }
        }
        return maxCount;
    }

    // Largest Subarray With Contiguous Elements

    public static int solution(int[] arr) {
		int ans = 0;
		for (int i = 0; i < arr.length; i++) 
		{
			int min = arr[i];
			int max = arr[i];
			HashSet<Integer> set = new HashSet<Integer>();
			set.add(arr[i]);
			for (int j = i + 1; j < arr.length; j++) 
			{ 
				if (set.contains(arr[j])) break;
				set.add(arr[j]);
				min = Math.min(min, arr[j]);
				max = Math.max(max, arr[j]);
				if (max - min == j - i) ans = Math.max(ans, j - i + 1);
			}
		}
		return ans;
	}

	// MINIMUM WINDOW SUBSTRING

	public String minWindow(String s, String t) {

        int ns = s.length(), nt = t.length();
        if (ns < nt)
            return "";
        int[] freq = new int[128];
        for (int i = 0; i < nt; i++)
            freq[t.charAt(i)]++;

        int si = 0, ei = 0, count = nt, len = (int) 1e9, gsi = 0;

        while (ei < ns) {
            if (freq[s.charAt(ei++)]-- > 0)
                count--;

            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gsi = si;
                }

                if (freq[s.charAt(si++)]++ == 0)
                    count++;
            }
        }

        return len == (int) 1e9 ? "" : s.substring(gsi, gsi + len);
    }

    // SMALLEST DISTINCT WINDOW GEEKS FOR GEEKS

    public static String findSubString( String str) 
    {
        int []map = new int[128];
        int ei = 0;
        int si = 0;
        int DistCount = 0;
        int length = (int) 1e9;
        int start = 0;
        int end = 0;
        for(int i = 0;i<str.length();i++) if(map[str.charAt(i)]++ == 0) DistCount++;
        Arrays.fill(map,0);
        while(ei < str.length())
        {
            if(map[str.charAt(ei++)]++ == 0) DistCount--;

            while(DistCount == 0)
            {
                if(--map[str.charAt(si)] == 0) DistCount++;
                if(ei - si < length)
                {
                    length = ei - si;
                    start = si;
                    end = ei;
                }
                si++;
            }
        }
        return str.substring(start , end);
    }

    // 3. Longest Substring Without Repeating Characters

    public static int lengthOfLongestSubstring(String s) 
    {
        int[] map = new int[128];
        int si = 0;
        int ei = 0;
        int count = 0;
        int length = 0;
        while(ei < s.length())
        {
            if(map[s.charAt(ei++)]++ > 0) count++;
            while(count > 0)
            {
                if(map[s.charAt(si++)]-- > 1) count--;
            }
            length = Math.max(length , ei-si);
        }
        return length;
    }

 	// Count Of Substrings Having All Unique Characters

 	public static int solution(String str) 
	{
	    int si = 0;
	    int ei = 0;
	    int count = 0;
	    HashSet<Character> map = new HashSet<>();
	    
	    while(ei < str.length())
	    {
	        char ch = str.charAt(ei);
	        if(!map.contains(ch))
	        {
	             map.add(ch);
	             count+=ei-si+1;
	             ei++;
	        }
	        else{
	            while(si < ei){
	                char c = str.charAt(si);
	                if(map.contains(c)) map.remove(c);
	                si++;
	                if(c == ch) break;
	            }
	        }
	    }
	    return count;
	}

	// Longest K unique characters substring
	public static int longestkSubstr(String s, int k) 
    {
        int ei = 0;
        int si = 0;
        int gei = 0;
        int gsi = 0;
        int[] map = new int[128];
        int length = 0;
        while(ei < s.length())
        {
            if(map[s.charAt(ei++)]++ == 0) k--;
            while(k == -1)
            {
                if(--map[s.charAt(si++)] == 0) k++;
            }
            if(ei-si > length)
            {
                length = ei-si;
                if(k == 0)
                {
                    gei = ei;
                    gsi = si;
                }
            }
        }
        return (gei - gsi) == 0  ? -1 : gei - gsi;
    }   

    // Count Of Substrings With Exactly K Unique Characters
    // IMPORTANT QUESTION SOLVED ON PEEPCODING

    public static int solution(String str, int k)
	{
	    if(k == 1)
	    {
	        HashSet<Character> set = new HashSet<>();
	        int si = 0;
	        int ei = 0;
	        int count = 0;
	        
	        while(ei < str.length())
	        {
	            set.add(str.charAt(ei++));
	            if(set.size() == 1) count += ei-si;
	            else
	            {
	                while(si < ei-1)
	                {
	                    if(set.contains(str.charAt(si))) set.remove(str.charAt(si));
	                    si++;
	                }
	                count+= ei-si;
	            }
	        }
	        return count;
	    }
	    HashMap<Character,Integer> mapSmall = new HashMap<>();
	    HashMap<Character,Integer> mapBig = new HashMap<>();
	    int ssi = 0;
	    int sei = 0;
	    int bsi = 0;
	    int bei = 0;
	    int count = 0;
	    
	    while(bei <= str.length() && sei < str.length())
	    {
	        while(sei < str.length() && mapSmall.size()<=k-1)
	        {
	            if(!mapSmall.containsKey(str.charAt(sei)) && mapSmall.size() == k-1) break;
	            mapSmall.put(str.charAt(sei),mapSmall.getOrDefault(str.charAt(sei),0)+1);
	            sei++;
	        }
	        
	        while(bei < str.length() && mapBig.size()<=k)
	        {
	            if(!mapBig.containsKey(str.charAt(bei)) && mapBig.size() == k) break;
	            mapBig.put(str.charAt(bei),mapBig.getOrDefault(str.charAt(bei),0)+1);
	            bei++;
	        }
	        
	        if(mapSmall.size() == k-1 && mapBig.size() == k) count+= bei-sei;

	        mapBig.put(str.charAt(bsi),mapBig.get(str.charAt(bsi))-1);
	        if(mapBig.get(str.charAt(bsi)) == 0) mapBig.remove(str.charAt(bsi));
	        bsi++;
	        
	        mapSmall.put(str.charAt(ssi),mapSmall.get(str.charAt(ssi))-1);
	        if(mapSmall.get(str.charAt(ssi)) == 0) mapSmall.remove(str.charAt(ssi));
	        ssi++;
	    }
	    
	    return count;
	}

	
	// Equivalent Sub-Arrays 

	public static int countDistinctSubarray(int arr[], int n) 
    { 
        HashSet<Integer> set = new HashSet<>();
        for(int ele : arr)
        {
            set.add(ele);
        }
        return solution(arr,set.size());
    }
    public static int solution(int[] arr, int k)
	{
	    if(k == 1)
	    {
	        HashSet<Integer> set = new HashSet<>();
	        int si = 0;
	        int ei = 0;
	        int count = 0;
	        
	        while(ei < arr.length)
	        {
	            set.add(arr[ei++]);
	            if(set.size() == 1) count += ei-si;
	            else
	            {
	                while(si < ei-1)
	                {
	                    if(set.contains(arr[si])) set.remove(arr[si]);
	                    si++;
	                }
	                count+= ei-si;
	            }
	        }
	        return count;
	    }
	    HashMap<Integer,Integer> mapSmall = new HashMap<>();
	    HashMap<Integer,Integer> mapBig = new HashMap<>();
	    int ssi = 0;
	    int sei = 0;
	    int bsi = 0;
	    int bei = 0;
	    int count = 0;
	    
	    while(bei <= arr.length && sei < arr.length)
	    {
	        while(sei < arr.length && mapSmall.size()<=k-1)
	        {
	            if(!mapSmall.containsKey(arr[sei]) && mapSmall.size() == k-1) break;
	            mapSmall.put(arr[sei],mapSmall.getOrDefault(arr[sei],0)+1);
	            sei++;
	        }
	        
	        while(bei < arr.length && mapBig.size()<=k)
	        {
	            if(!mapBig.containsKey(arr[bei]) && mapBig.size() == k) break;
	            mapBig.put(arr[bei],mapBig.getOrDefault(arr[bei],0)+1);
	            bei++;
	        }
	        
	        if(mapSmall.size() == k-1 && mapBig.size() == k) count+= bei-sei;

	        mapBig.put(arr[bsi],mapBig.get(arr[bsi])-1);
	        if(mapBig.get(arr[bsi]) == 0) mapBig.remove(arr[bsi]);
	        bsi++;
	        
	        mapSmall.put(arr[ssi],mapSmall.get(arr[ssi])-1);
	        if(mapSmall.get(arr[ssi]) == 0) mapSmall.remove(arr[ssi]);
	        ssi++;
	    }
	    
	    return count;
	}


	// 485. Max Consecutive Ones

	public int findMaxConsecutiveOnes(int[] nums) 
    {
        int n = nums.length, si = 0, ei = 0, len = 0;
        while (ei < n) {
            if (nums[ei] == 0) {
                ei++;
                si = ei;
            } else {
                ei++;
            }

            len = Math.max(len, ei - si);
        }
        return len;
    }

    // Max Consecutive Ones 2
    // Submitted on Pepcodings Website
    public static int solution(int[] arr, int k){
        int zeroes = 0;
        int ans = 1;
        for(int i = 0 ,j = 0 ;i < arr.length; i++){
            if(arr[i] == 0)
            {
                zeroes++;
                while(zeroes > k){
                    if(arr[j] == 0){
                        zeroes--;
                    }
                    j++;
                }
            }
            ans = Math.max(ans,i - j + 1);
        }
        return ans;
    }

    // Longest Substring With At Most K Unique Characters
    // pepecoding's website code
    public static int solution(String str, int k) {
        HashMap<Character,Integer> map = new HashMap<>();
        int ans = 0;
        for(int i = 0 , j = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch, 0) + 1);
            while(map.size() > k){
                char chj = str.charAt(j);
                map.put(chj,map.get(chj) - 1);
                if(map.get(chj) == 0){
                    map.remove(chj);
                }
                j++;
            }
            ans = Math.max(ans,i - j + 1);
        }
        return ans;
    }

    // Count Of Substrings Having At Most K Unique Characters

    public static int solution(String str, int k)
	{
        int si = 0;
        int ei = 0;
        int count = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        while(ei<str.length() && si < str.length())
        {
            while(ei < str.length() && map.size() <= k)
            {
                if(map.size() == k && (!map.containsKey(str.charAt(ei)))) break;
                map.put(str.charAt(ei) , map.getOrDefault(str.charAt(ei++),0)+1);
                count+=ei-si;
            }
            while(map.size() == k)
            {
                map.put(str.charAt(si),map.get(str.charAt(si))-1);
                if(map.get(str.charAt(si)) == 0) map.remove(str.charAt(si));
                si++;
            }
        }
        return count;
	}

	// Find All Anagrams In A String

	public List<Integer> findAnagrams(String s, String p) 
    {
        HashMap<Character,Integer> smap = new HashMap<>();
        HashMap<Character,Integer> pmap = new HashMap<>();
        for(int i = 0;i<p.length();i++){
            pmap.put(p.charAt(i),pmap.getOrDefault(p.charAt(i),0)+1);
        }
        List<Integer> answer = new ArrayList<>();
        int si = 0;
        int ei = 0;
        while(ei < s.length())
        {
            char ch = s.charAt(ei);
            if(pmap.containsKey(ch) && smap.getOrDefault(ch,0)+1 <= pmap.get(ch))
            {
                smap.put(ch,smap.getOrDefault(ch,0)+1);
                ei++;
                if(smap.equals(pmap))
                {
                    answer.add(si);
                    smap.put(s.charAt(si),smap.getOrDefault(s.charAt(si),0)-1);
                    if(smap.get(s.charAt(si)) == 0) smap.remove(s.charAt(si));
                    si++;
                }
            }
            else if(pmap.containsKey(ch) && smap.getOrDefault(ch,0)+1 > pmap.get(ch))
            {
                smap.put(s.charAt(si),smap.getOrDefault(s.charAt(si),0)-1);
                if(smap.get(s.charAt(si)) == 0) smap.remove(s.charAt(si));
                si++;
            }
            else if(!pmap.containsKey(ch))
            {
                while(si < ei)
                {
                    smap.put(s.charAt(si),smap.getOrDefault(s.charAt(si),0)-1);
                    if(smap.get(s.charAt(si)) == 0) smap.remove(s.charAt(si));
                    si++;
                }
                si = ei+1;
                ei = si;
            }
        }
        return answer;
    }
    
    // Check if two strings are k-anagrams or not 
	
	boolean areKAnagrams(String s1, String s2, int k) 
    {
        if(s1.length()!=s2.length()) return false;
        
        HashMap<Character,Integer> map1 = new HashMap<>();
        for(int i = 0;i<s1.length();i++){
            map1.put(s1.charAt(i),map1.getOrDefault(s1.charAt(i),0)+1);
        }
        
        for(int i = 0;i<s2.length();i++)
        {
            char ch = s2.charAt(i);
            if(map1.containsKey(ch))
            {
                 map1.put(ch,map1.getOrDefault(ch,0)-1);
                 if(map1.get(ch) == 0) map1.remove(ch);
            }
            else k--;
        }
        return (k >= 0) ? true : false;
    }

    // Find Anagram Mappings

    public static int[] anagramMappings(int[] arr1, int[] arr2) 
	{
	    int[] ans = new int[arr1.length];
	    HashMap<Integer,LinkedList<Integer>> map = new HashMap<>();
	    for(int i = 0;i<arr2.length;i++)
	    {
	        int a = arr2[i];
	        if(!map.containsKey(a)) map.put(a,new LinkedList<>());
	        map.get(a).addLast(i);
	    }
	    for(int i = 0;i<arr1.length;i++)
	    {
	        int a = arr1[i];
	        ans[i] = map.get(a).removeFirst();
	    }
	    return ans;
	}

	// 242. Valid Anagram

	public boolean isAnagram(String s, String t) 
    {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            map.put(ch , map.getOrDefault(ch,0)+1);
        }
        for(int i = 0;i<t.length();i++)
        {
            char ch = t.charAt(i);
            if(map.containsKey(ch))
            {
                map.put(ch,map.getOrDefault(ch,0)-1);
                if(map.get(ch) == 0) map.remove(ch);
            }
            else return false;
        }
        return (map.size() == 0) ?  true : false; 
    }


    // 49. Group Anagrams

    public HashMap<Character,Integer> createMapping(String str)
    {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<str.length();i++)
        {
            char ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        return map;
    }
    public List<List<String>> groupAnagrams(String[] strs) 
    {
        HashMap<HashMap<Character,Integer>,List<String>> map1 = new HashMap<>();
        for(int i = 0;i<strs.length;i++)
        {
            HashMap<Character,Integer> map2 = createMapping(strs[i]);
            if(!map1.containsKey(map2))
            {
                map1.put(map2,new ArrayList<>());
                map1.get(map2).add(strs[i]);
            }
            else map1.get(map2).add(strs[i]);
        }
        List<List<String>> ans = new ArrayList<>();
        for(List<String> m : map1.values())
        {
            ans.add(m);
        }
        return ans;
    }

    // faster solution Group Anagrams

    public List<List<String>> groupAnagrams(String[] strs)
    {
	    HashMap<String,ArrayList<String>> map = new HashMap<>();
	    for(String str: strs){
	       
	       char[] arr = str.toCharArray();
	       Arrays.sort(arr);
	       String key = new String(arr);
	       map.putIfAbsent(key,new ArrayList<>());
	       map.get(key).add(str);
	    }
	    
	    List<List<String>> ans = new ArrayList<>();
	    for(String s: map.keySet()){
	        ans.add(map.get(s));
	    }
	    
	    return ans;   
    }

    // GROUP SHIFTED STRINGS

    public static String createCode(String str)
    {
        String code = "#"; // this will handle the case when string has only one characetr
        for(int i = 1;i<str.length();i++)
        {
            char first = str.charAt(i);
            char second = str.charAt(i-1);
            int val = ((int)first-(int)second);
            if(val<0) val+=26; // yab   97 - 121 + 26 coz y-a == 2;;;;
            code += val;
            code+="#";
        }
        return code;
    }
	public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs) 
	{
	    HashMap<String,ArrayList<String>> map = new HashMap<>();
	    
	    for(int i = 0;i<strs.length;i++)
	    {
	        String str = strs[i];
	        String code = createCode(str);
	        if(!map.containsKey(code)) map.put(code , new ArrayList<>());
	        map.get(code).add(str);
	    }
	    ArrayList<ArrayList<String>> ans = new ArrayList<>();
	    for(ArrayList<String> a : map.values())
	    {
	        ans.add(a);
	    }
	    return ans;
	}


	// 205. Isomorphic Strings

	public boolean isMatch(String two , int[]s1)
    {
        HashMap<Character , Integer> map = new HashMap<>();
        int[] s2 = new int[two.length()];

        for(int i = 0;i<two.length();i++)
        {
            if(!map.containsKey(two.charAt(i))){
                map.put(two.charAt(i),i);
            }
            s2[i] = map.get(two.charAt(i));
        }

        for(int i = 0;i<s1.length;i++)
        {
            if(s1[i]!=s2[i]){
                return false;
            }
        }
        return true;
    }
    public boolean isIsomorphic(String s, String pattern) 
    {
        int[] s1 = new int[pattern.length()];
        HashMap<Character , Integer> map = new HashMap<>();
        for(int i = 0;i<pattern.length();i++)
        {
            // PUTTING IN HASHMAP WITH FIRST OCCURANCE INDEX
            if(!map.containsKey(pattern.charAt(i))){
                map.put(pattern.charAt(i),i);
            }
            s1[i] = map.get(pattern.charAt(i)); // STORING IN AN ARRAY THAT AT WHAT INDEX EARLIER 
                                                // CHARATER WAS PRESENT 
        }
        if(s.length() == pattern.length()){
            if(isMatch(s,s1)) return true;
        }
        return false;
    }

    // 290. Word Pattern
    // USED SAME SRTATEGY AS WE DID IN ISOMORPHIC STRINGS
    public boolean wordPattern(String pattern, String s) 
    {
        String[] str = s.split(" ");
        int[] s1 = new int[str.length];
        HashMap<String , Integer> map = new HashMap<>();
        for(int i = 0;i<str.length;i++)
        {
            // PUTTING IN HASHMAP WITH FIRST OCCURANCE INDEX
            if(!map.containsKey(str[i])){
                map.put(str[i],i);
            }
            s1[i] = map.get(str[i]); // STORING IN AN ARRAY THAT AT WHAT INDEX EARLIER 
                                                // CHARATER WAS PRESENT 
        }
        if(s1.length == pattern.length()) if(isMatch(pattern,s1)) return true;
        return false;
    }
    public boolean isMatch(String two , int[]s1)
    {
        HashMap<Character , Integer> map = new HashMap<>();
        int[] s2 = new int[two.length()];
 
        for(int i = 0;i<two.length();i++)
        {
            if(!map.containsKey(two.charAt(i))){
                map.put(two.charAt(i),i);
            }
            s2[i] = map.get(two.charAt(i));
        }

        for(int i = 0;i<s1.length;i++)
        {
            if(s1[i]!=s2[i]){
                return false;
            }
        }
        return true;
    }

    // 560. Subarray Sum Equals K

    public int subarraySum(int[] nums, int k) 
    {
        int sum = 0;
        int count = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int ele : nums)
        {
            sum += ele;
            if(map.containsKey(sum - k)) count+=map.get(sum-k);
            if(!map.containsKey(sum)) map.put(sum,1);
            else if(map.containsKey(sum)) map.put(sum , map.get(sum)+1);
        }
        return count;
    }

    // LONGEST SUBARRAY WITH SUM DIVISIBLE BY K

    int longSubarrWthSumDivByK(int a[], int n, int k)
    {
        
       int[] map = new int[k];
       Arrays.fill(map,-2);
       int len = 0;
       int i = 0;
       int sum = 0;
       map[0] = -1;
       while(i<n)
       {
            sum += a[i];
            int div = ((sum % k) + k) % k;
            if(map[div] == -2) map[div] = i;
            else len = Math.max(len , i - map[div]);
            i++;
       }
       
       return len;
    }

    // Count Of Subarrays With Sum Divisible By K

    long subCount(long a[] ,int n,int k)
    {
       int[] map = new int[k];
       Arrays.fill(map,-2);
       int count = 0;
       int i = 0;
       int sum = 0;
       map[0] = 1;
       while(i<n)
       {
            sum += a[i];
            int div = ((sum % k) + k) % k;
            if(map[div] == -2) map[div] = 1;
            else{
                count+=map[div];
                map[div]++;
            }
            i++;
       }
       
       return count;   
    }

    // Longest Subarray With Equal Number Of Zeroes And Ones
    // 525. Contiguous Array

    public int findMaxLength(int[] arr) 
    {
        int ei = 0;
        int sum = 0;
        int length = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        while(ei < arr.length)
        {
            sum = sum + (arr[ei] == 1 ? 1 : -1);
            if(map.containsKey(sum))
            {
                length = Math.max(length , ei - map.get(sum));
            }
            else
            {
                map.put(sum,ei);
            }
            ei++;
        }
        return length;
    }

    // count of subarrays with equal no of zeros and ones

    static int countSubarrWithEqualZeroAndOne(int arr[], int n)
    {
        int ei = 0;
        int count = 0;
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        while(ei < arr.length)
        {
            sum = sum + (arr[ei++] == 1 ? 1 : -1);
            if(map.containsKey(sum))
            {
                count += map.get(sum);
                map.put(sum , map.get(sum)+1);
            }
            else
            {
                map.put(sum,1);
            }
        }
        return count;
    }

    // Count Subarray with Equal No Of 0, 1 and 2 

    String getCode(int zero , int one , int two)
    {
        int diff1 = one-zero;
        int diff2 = two-one;
        return diff1 + "#" + diff2;
    }
    long getSubstringWithEqual012(String str) 
    {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("0#0",1);
        int zero = 0;
        int one = 0;
        int two = 0;
        int count = 0;
        for(int i = 0;i<str.length();i++)
        {
            char ch = str.charAt(i);
            if(ch == '1') one++;
            if(ch == '2') two++;
            if(ch == '0') zero++;
            
            String code = getCode(zero,one,two);
            if(map.containsKey(code)) count+=map.get(code);
            map.put(code,map.getOrDefault(code,0)+1);
        }
        return count;
    }

    // Longest Subarray With Equal Number Of 0s 1s And 2s

    public static String getCode(int zero , int one , int two)
    {
        int diff1 = one-zero;
        int diff2 = two-one;
        return diff1 + "#" + diff2;
    }
    public static int solution(int[] arr) 
    {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("0#0",-1);
        int zero = 0;
        int one = 0;
        int two = 0;
        int length = 0;
        for(int i = 0;i<arr.length;i++)
        {
            int ch = arr[i];
            if(ch == 1) one++;
            if(ch == 2) two++;
            if(ch == 0) zero++;
            
            String code = getCode(zero,one,two);
            if(map.containsKey(code)) length = Math.max(i-map.get(code),length);
            if(!map.containsKey(code))map.put(code,i);
        }
        return length;
    }

    // A Simple Fraction 
    // do it on leetcode for -ve numerator and denominator as well
    // + +
    // + -
    // - +
    // - -
    public String fractionToDecimal(int num1, int den1) 
    {
        boolean isNegative = (num1 < 0 && den1> 0 || num1 > 0 && den1 < 0) ? true : false;
        StringBuilder sb = new StringBuilder();
        long num = Math.abs((long)num1);
        long den = Math.abs((long)den1);
        long quo = num / den;
        long rem = num % den;
        sb.append(Math.abs(quo));
        
        if(rem == 0){
            if (isNegative) {
            sb.insert(0, "-");
        }
            return sb.toString();
        } else {
            sb.append(".");
        }
        
        HashMap<Long, Integer> map = new HashMap<>();
        while(rem != 0){
            if(map.containsKey(rem) == false){
                map.put(rem, sb.length());
                rem *= 10;
                quo = rem / den;
                rem = rem % den;
                sb.append(Math.abs(quo));
            } else {
                int pos = map.get(rem);
                sb.insert(pos, "(");
                sb.append(")");
                break;
            }
        }
        
        if (isNegative) {
            sb.insert(0, "-");
        }
        return sb.toString();
    }

    // Pairs With Equal Sum

    public static boolean solution(int[] arr) 
    {
        HashSet<Integer> set = new HashSet<>();
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = i + 1; j < arr.length ; j++) {
				int sum = arr[i] + arr[j];
				if(set.contains(sum)){
				    return true;
				}else{
				    set.add(sum);
				}
			}
		}
		return false;
    }

    // Rabbits In The Forest

    public int numRabbits(int[] arr) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        for(int i = 0;i < arr.length;i++)
        {
           int ele = arr[i];
           if(!map.containsKey(ele))
           {
               if(ele!=0) map.put(ele,ele);
               sum += ele+1;
           }
           else
           {
               map.put(ele , map.get(ele) -1);
               if(map.get(ele) <= 0) map.remove(ele);
           }
        }
        return sum;
    }

    // Check Arithmetic Sequence
    // 1502. Can Make Arithmetic Progression From Sequence

    public static boolean solution(int[] arr) {
		if(arr.length == 1) {
			return true;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		int min =  Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0 ;i < arr.length; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
			set.add(arr[i]);
		}
		int diff = (max - min) / (arr.length - 1);
		for(int i = 0 ; i < arr.length; i++) {
			if(arr[i] == min || arr[i] == max) {
			}else {
				if(!set.contains(arr[i] - diff) || !set.contains(arr[i] + diff)) {
					return false;
				}
			}
		}
		
		return true;
	}

	// Smallest subarray with all occurrences of a most frequent element 

	ArrayList<Integer> smallestSubsegment(int a[], int n)
    {
        HashMap<Integer,int[]> map = new HashMap<>();
        int count = 0;
        int length = (int)1e9;
        int startIdx = -1;
        for(int i = 0;i<a.length;i++)
        {
            int val = a[i];
            if(!map.containsKey(val)) map.put(val,new int[]{0,i});
            map.get(val)[0]++;
            if(map.get(val)[0] > count)
            {
                count = map.get(val)[0];
                length = i-map.get(val)[1]+1;
                startIdx = map.get(val)[1];
            }
            else if(map.get(val)[0] == count)
            {
                if(i-map.get(val)[1] + 1 < length)
                {
                    length = i-map.get(val)[1] + 1;
                    startIdx = map.get(val)[1];
                }
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i=startIdx;i<length+startIdx;i++)
        {
            ans.add(a[i]);
        }
        return ans;
    }

	// TASK COMPLETION PEPCODING WEBSITE

	public static void completeTask(int n, int m, int[] arr) 
    {
        HashSet<Integer> set = new HashSet<>();
        for(int ele : arr) set.add(ele);
        
        ArrayList<Integer> s1 = new ArrayList<>();
        ArrayList<Integer> s2 = new ArrayList<>();
        int toggle = 0;
        for(int i = 1;i<=n;i++)
        {
            if(!set.contains(i))
            {
                if(toggle == 0)
                {
                    toggle = 1;
                    s1.add(i);
                }
                else {
                    toggle = 0;
                    s2.add(i);
                }
            }
        }
        for(int ele : s1) System.out.print(ele + " ");
        System.out.println();
        for(int ele : s2) System.out.print(ele + " ");
	}

	// Pairs With Given Sum In Two Sorted Matrices
	
	// USING HASHMAP

	public static int solve(int[][] num1, int[][] num2, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int ans = 0;
		for(int i = 0 ; i < num1.length ;i++) {
			for(int j = 0; j < num1[0].length ;j++) {
				map.put(num1[i][j], map.getOrDefault(num1[i][j], 0) + 1);
			}
		}
		
		for(int i = 0 ;i < num2.length ;i++) {
			for(int j = 0 ;j < num2[0].length ;j++) {
				if(map.containsKey(k - num2[i][j])) {
					ans += map.get(k - num2[i][j]);
				}
			}
		}
		return ans;
	}

	// Time O(n2) Space O(1)
	public static int solve(int[][] nums1, int[][] nums2, int k) 
    {
        int i1 = 0;
        int j1 = 0;
        
        int i2 = nums2.length-1;
        int j2 = nums2[0].length-1;
        int count = 0;
        
        while(i1 < nums1.length && i2 >= 0)
        {
            int sum = nums1[i1][j1] + nums2[i2][j2];
            if(sum == k)
            {
                int prevNum1 = nums1[i1][j1];
                int c1 = 1;
                j1++;
                while(i1 < nums1.length)
                {
                    if(j1 == nums1[0].length)
                    {
                        j1 = 0;
                        i1++; 
                    }
                    if(i1 < nums1.length && nums1[i1][j1] == prevNum1)
                    {
                        j1++;
                        c1++;
                    }
                    else break;
                    
                }
                int prevNum2 = nums2[i2][j2];
                int c2 = 1;
                j2--;
                while(i2 >= 0)
                {
                    if(j2 == -1)
                    {
                        j2 = nums2[0].length-1;
                        i2--;
                    }
                    if(i2 >= 0 && nums2[i2][j2] == prevNum2)
                    {
                        j2--;
                        c2++;
                    }
                    else break;
                }
                
                count+= c1*c2;
            }
            else if(sum < k)
            {
                j1++;
                if(j1 == nums1[0].length){
                    j1 = 0;
                    i1++; 
                }
            }
            else if(sum > k)
            {
                j2--;
                if(j2 == -1){
                    j2 = nums2[0].length-1;
                    i2--;
                }
            }
        }
        return count;
	}


	// 970. Powerful Integers
	// IMPORTANT 
	public List<Integer> powerfulIntegers(int x, int y, int bound) 
    {
        List<Integer> ans = new ArrayList<Integer>();
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 1; i < bound; i *= x) {
			for(int j = 1; j < bound; j *= y) {
				if(i + j <= bound) {
					set.add(i + j);
					if(y == 1) {
						break;
					}
				}
			}
			if(x == 1) {
				break;
			}
		}
		ans.addAll(set);
		return ans;
    }

    // solution 2

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<20;i++){
            if(Math.pow(x,i)>bound)
                break;
            for(int j=0;j<20;j++){
                double sum=Math.pow(x,i)+Math.pow(y,j);
                if(sum<=bound){
                    set.add((int)sum);
                }
                else{
                    break;
                }
            }
        }
        List<Integer> list=new ArrayList<>();
        for(int i:set){
            list.add(i);
        }
        return list;
    }


    // 811. Subdomain Visit Count

    public List<String> subdomainVisits(String[] cpdomains) 
    {
        HashMap<String,Integer> map = new HashMap<>();
        for(String ele : cpdomains)
        {
            String[] temp = ele.split(" ");
            int num = Integer.parseInt(temp[0]);
            String str = temp[1];
            String[] temp2 = str.split("\\."); // important 
            String add = "";
            
            for(int i = temp2.length-1;i>=0;i--)
            {
                if(i == temp2.length-1) add+=temp2[i];
                else{
                    add = temp2[i]+"."+ add;
                }
                map.put(add,map.getOrDefault(add,0)+num);
            }
        }
        
        List<String> ans = new ArrayList<>();
        for(String ele : map.keySet())
        {
            ans.add(map.get(ele) + " " + ele);
        }
        return ans;
    }

    // 387. First Unique Character in a String

    public int firstUniqChar(String s) 
    {
        HashMap<Character,int[]> map = new HashMap<>();
        for(int i = 0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(!map.containsKey(ch)) map.put(ch,new int[]{1,i});
            else{
                int[] temp = map.get(ch);
                temp[0]++;
                map.put(ch,temp);
            }
        }
        int idx = s.length();
        for(int[] ele : map.values())
        {
            if(ele[0] == 1)
            {
                idx = Math.min(idx,ele[1]);
            }
        }
        return (idx == s.length()) ? -1 : idx;
    }

    // OR ORIGINAL SOLUTION MUCH EASIER AND SPACE EFFICIENT

    public static int solution(String s) {
	  HashMap<Character, Integer> map = new HashMap<>();
	  for(int i = 0 ; i < s.length() ;i++) {
		  char ch = s.charAt(i);
		  map.put(ch, map.getOrDefault(ch, 0) + 1);
	  }
	  for(int i = 0 ; i< s.length() ;i++) {
		  char ch = s.charAt(i);
		  if(map.get(ch) == 1) {
			  return i;
		  }
	  }
	  return -1;
    }

    // 914. X of a Kind in a Deck of Cards

    public int gcd(int a , int b)
    {
        if(b == 0) return a;
        return gcd(b,a%b);
    }
    public boolean hasGroupsSizeX(int[] deck) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : deck){
            map.put(ele , map.getOrDefault(ele,0)+1);
        }
        int gcd = 0;
        for(int ele : map.values())
        {
            gcd = gcd(gcd,ele);
        }
        return (gcd < 2) ? false : true;
    }

    // 554. Brick Wall

    public int leastBricks(List<List<Integer>> wall) 
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(List<Integer> l1 : wall)
        {
            int sum = 0;
            for(int ele = 0 ; ele < l1.size()-1;ele++) // not putting the last element
            {
                sum+=l1.get(ele);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        int max = 0;
        for(int ele : map.values()){
            max = Math.max(ele,max);
        }
        return wall.size()-max;
    }

    // MAXIMUM FREQUENCY STACK VERY IMPORTANT PROBLEM

    HashMap<Integer,Stack<Integer>> map = new HashMap<>();
    HashMap<Integer,Integer> freq = new HashMap<>();
    int maxfreq = 0;
    public FreqStack()
    {
        
    }
    
    public void push(int x)
    {
        freq.put(x,freq.getOrDefault(x,0) + 1);
        maxfreq = Math.max(maxfreq,freq.get(x));
        if(!map.containsKey(freq.get(x)))
        {
            map.put(freq.get(x),new Stack<>());
            (map.get(freq.get(x))).push(x);
        }
        else
        {
            map.get(freq.get(x)).push(x);
        }
    }
    
    public int pop()
    {
        int val = (map.get(maxfreq).pop());
        if((map.get(maxfreq)).isEmpty())
           {
               maxfreq--;
           }
        freq.put(val,freq.get(val) - 1);
        return val;
                   
    }

    // ENCODE AND DECODE TINY URL

    static Integer count=0;
    Map<String,String>mp=new HashMap<>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key=count.toString();
            count++;
        mp.put(key,longUrl);
        return key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return mp.get(shortUrl);
    }

    // 218. The Skyline Problem

    public List<List<Integer>> getSkyline(int[][] buildings) 
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->{
            return b-a;
        });
        List<List<Integer>> ans = new ArrayList<>();
        queue.add(0);
        int n = buildings.length*2;
        int[][] arr = new int[n][2];
        int i = 0;
        for(int[] ele : buildings){
            arr[i][0] = ele[0];
            arr[i][1] = ele[2];
            i++;
            arr[i][0] = ele[1];
            arr[i][1] = -1*ele[2];
            i++;
        }
        Arrays.sort(arr,(int[]a , int[]b)->{
            if(a[0]!=b[0]) return a[0]-b[0];
            return b[1]-a[1];
        });
        
        for(int[] ele : arr)
        {
            if(ele[1] > queue.peek())
            {
                queue.add(ele[1]);
                List<Integer> temp = new ArrayList<>();
                temp.add(ele[0]);
                temp.add(ele[1]);
                ans.add(temp);
            }
            else if(ele[1] == queue.peek()) queue.add(ele[1]);
            else if(ele[1] > 0 && ele[1] < queue.peek()) queue.add(ele[1]);
            else if(ele[1] < 0)
            {
                if(queue.peek() == -1*ele[1])
                {
                    queue.poll();
                    if(queue.peek()!=-1*ele[1])
                    {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(ele[0]);
                        temp.add(queue.peek());
                        ans.add(temp);
                    }
                }
                else if(queue.peek() > -1*ele[1]) queue.remove(-1*ele[1]);
            }
        }
        return ans;
    }

    // 380. Insert Delete GetRandom O(1)
    // important
    HashMap<Integer,Integer> map = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    Random rand = new Random();
    public RandomizedSet() {
        
    }
    
    public boolean insert(int val) 
    {
        if(!map.containsKey(val))
        {
            map.put(val,list.size());
            list.add(val);
            return true;
        }
        return false;
    }
    
    public boolean remove(int val) 
    {
        if(!map.containsKey(val)) return false;
        
        int idx = map.get(val);
        // map.remove(val); // important case do dryrun for single element
        map.put(list.get(list.size()-1),idx);
        map.remove(val);
        list.set(idx,list.get(list.size()-1));
        list.remove(list.size()-1);
        
        return true;
    }
    
    public int getRandom() 
    {
        int idx = rand.nextInt(list.size());
        return list.get(idx);
    }

    // 381. Insert Delete GetRandom O(1) - Duplicates allowed
    // vvvvvvvvvvvvvvv important problem
    HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    Random rand = new Random();
    public RandomizedCollection() 
    {
        
    }
    public boolean insert(int val) 
    {
        if(!map.containsKey(val))
        {
            map.put(val,new HashSet<>());
        }
        if(map.get(val).size() > 0)
        {
            map.get(val).add(list.size());
            list.add(val);
            // System.out.println(map.toString());
            return false;
        }
        else
        {
            map.get(val).add(list.size());
            list.add(val);
            // System.out.println(map.toString());
            return true;
        }
    }
    
    public boolean remove(int val) 
    {
        if(!map.containsKey(val)) return false;
        int idx = 0;
        for(int ele : map.get(val))
        {
            idx = ele;
            break;
        }
        
        // System.out.println(idx);
        int lastIdx = list.size()-1;
        int lastVal = list.get(lastIdx);
        
        if(lastVal == val)
        {
            map.get(lastVal).remove(lastIdx);
            list.remove(lastIdx);
            if(map.get(lastVal).size() == 0) map.remove(lastVal);
            return true;
        }
        
        list.set(idx,lastVal);
        map.get(lastVal).add(idx);
        list.remove(lastIdx);
        
        map.get(val).remove(idx);
        map.get(lastVal).remove(lastIdx);

        if(map.get(lastVal).size() == 0) map.remove(lastVal);
        if(map.containsKey(val) && map.get(val).size() == 0) map.remove(val);
        return true;
    }
    
    public int getRandom() 
    {
        int idx = rand.nextInt(list.size());
        return list.get(idx);
    }


    // 710. Random Pick with Blacklist
    // 4(2,1)
    // 5(0,3,1)
	// vvvvvvvvvImp
    int M = 0;
    HashMap<Integer,Integer> map = new HashMap<>();
    HashSet<Integer> set = new HashSet<>();
    Random rand = new Random();
    public Solution(int n, int[] blacklist) 
    {
        int N = blacklist.length;
        M = n - N;
        n--;
        for(int ele : blacklist){
            map.put(ele,-1);
            set.add(ele);
        }
        int i = 0;
        while(i < blacklist.length && n >= M)
        {
            if(!map.containsKey(n) && blacklist[i]<M)
            {
                map.put(blacklist[i] , n);
                i++;
                n--;
            }
            else
            {
                if(map.containsKey(n)) n--;
                if(blacklist[i] >=M) i++;
            }
            
        }
    }
    public int pick() 
    {
        int val = rand.nextInt(M);
        if(!map.containsKey(val)) return val;
        else return map.get(val);
    }

    // 1488. Avoid Flood in The City
    // vvvvvvvvvvImp question
    // usage of tree set
    public int[] avoidFlood(int[] rains) 
    {
        HashMap<Integer, Integer> lastRain = new HashMap<>();
        TreeSet<Integer> dry = new TreeSet<>();

        int n = rains.length;
        int ans[] = new int[n];

        for (int i = 0; i < n; i++) 
        {
            int x = rains[i];
            if (x == 0) 
            {
                dry.add(i);
                ans[i] = 1;
            }
            else 
            {
            	// Integer can hold null values as well
                Integer id = lastRain.get(x);
                if (id == null) 
                {
                  lastRain.put(x, i);
                }
                else 
                {
                  Integer dryThisDay = dry.higher(id);

                  if (dryThisDay == null) 
                  {
                    return new int[0]; // when no zeros are available to dry
                  }
                  ans[dryThisDay] = x;
                  dry.remove(dryThisDay);

                  lastRain.put(x, i);
                }
                ans[i] = -1;
            }
        }
        return ans;
    }

    // Pairs of Non Coinciding Points
    // Important Qustion

    static int numOfPairs(int[] X, int[] Y, int N) 
    {
        HashMap<Integer, Integer> xmap = new HashMap<>();
        HashMap<Integer, Integer> ymap = new HashMap<>();
        HashMap<String, Integer> xymap = new HashMap<>();
    
        int sum = 0;
        for (int i = 0; i < X.length; i++) {
          int x = X[i];
          int y = Y[i];
          String xy = "" + x + "*" + y;
    
          int xfre = xmap.getOrDefault(x, 0);
          int yfre = ymap.getOrDefault(y, 0);
          int xyfre = xymap.getOrDefault(xy, 0);
    
          sum += xfre + yfre - 2 * xyfre;
    
          xmap.put(x, xfre + 1);
          ymap.put(y, yfre + 1);
          xymap.put(xy, xyfre + 1);
        }
        return sum;
    }

    // Line Reflection Important Question
    // important
    public static boolean isReflected(int[][] points)
  	{
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, HashSet<Integer>> ys = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            min = Math.min(min, x);
            max = Math.max(max, x);
            if (!ys.containsKey(x)) {
                ys.put(x, new HashSet<Integer>());
            }
            ys.get(x).add(y);
        }
        int doublebar = (min + max);
        for (int i = 0; i < points.length; i ++) 
        {
            int x = points[i][0];
            int y = points[i][1];
            int mx = doublebar - x;
            if (!ys.containsKey(mx)) {
                return false;
            }
            if (!ys.get(mx).contains(y)) {
                return false;
            }
        }

        return true;
    }

  	// Longest Substring With At Most Two Distinct Characters
  	// simple sliding window
  	// pep code
  	public static int lengthOfLongestSubstringTwoDistinct(String s) {
    HashMap<Character, Integer> count = new HashMap<>();
    int dis = 0;
    int start = 0;
    int ans = 0;
    for (int i = 0; i < s.length(); i++) {

      int fre = count.getOrDefault(s.charAt(i), 0);
      if (fre == 0)dis++;
      count.put(s.charAt(i), fre + 1);

      while (dis > 2) {
        fre = count.get(s.charAt(start));
        count.put(s.charAt(start), fre - 1);
        if (fre - 1 == 0) {
          dis--;
        }
        start++;
      }

      ans = Math.max(ans, i - start + 1);
    }
    return ans;
  }

  // MINIMUM COST TO ADD STICKS

  public static int connectSticks(int[] sticks) {

    PriorityQueue<Integer> q = new PriorityQueue<>();
    for (int stick : sticks) {
      q.add(stick);
    }

    int cost = 0;
    while (q.size() > 1) {
      int s1 = q.remove();
      int s2 = q.remove();

      cost += s1 + s2;
      q.add(s1 + s2);
    }

    return cost;

  }

  // Tricky Sorting Cost 

    static int sortingCost(int N, int arr[])
    {
        int max = -(int)1e9;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : arr)
        {
            if(map.containsKey(ele-1))
            {
                map.put(ele,map.get(ele-1)+1);
                max = Math.max(map.get(ele),max);
            }
            else map.put(ele,1);
            
        }
        return N-max;
    }

    // 767. Reorganize String

    // SOLUTION ONE

    public class pair
    {
        char ch = '#';
        int count = 0;
        pair(char ch , int count)
        {
            this.ch = ch;
            this.count = count;
        }
    }
    public String reorganizeString(String s) 
    {
        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return b.count-a.count;
        });
        HashMap<Character,Integer> map = new HashMap<>();
        int max = -(int)1e9;
        
        for(int i = 0;i<s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
            max = Math.max(map.get(s.charAt(i)),max);
        }
        
        for(Character ele : map.keySet()){
            pair t = new pair(ele,map.get(ele));
            pq.add(t);
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(pq.size() > 1)
        {
            pair p1 = pq.poll();
            char ch1 = p1.ch;
            
            sb.append(ch1);
            p1.count--;
            
            pair p2 = pq.poll();
            char ch2 = p2.ch;
            
            sb.append(ch2);
            p2.count--;
            
            if(p1.count > 0) pq.add(p1);
            if(p2.count > 0) pq.add(p2);
        }
        
        if(pq.size()>0 && pq.peek().count > 1) return "";
        else if(pq.size()>0 && pq.peek().count == 1) sb.append(pq.peek().ch);
        return sb.toString();
    }

    // SOLUTION 2
    // LEETCODE DISCUSSION
    public String reorganizeString(String S) {
        int[] hash = new int[26];
        for (int i = 0; i < S.length(); i++) {
            hash[S.charAt(i) - 'a']++;
        } 
        int max = 0, letter = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }
        if (max > (S.length() + 1) / 2) {
            return ""; 
        }
        char[] res = new char[S.length()];
        int idx = 0;
        while (hash[letter] > 0) {
            res[idx] = (char) (letter + 'a');
            idx += 2;
            hash[letter]--;
        }
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                hash[i]--;
            }
        }
        return String.valueOf(res);
    }

    
    // SNAPSHOT ARRAY
    // DOING WITH HASHMAP NOT ARRAY
    HashMap<String, Integer> snaps = new HashMap<String, Integer>();
    int currentSnapId = 0;
        
    public SnapshotArray(int length) {
    }
    
    public void set(int index, int val) {
        snaps.put("" + currentSnapId + "," + index, val);
    }
    
    
    public int snap(){
        return currentSnapId++;
    }
    
    public int get(int index, int snap_id) {
        if(snap_id > currentSnapId) return 0;
        
        for(int i=snap_id; i >=0; i--)
            if (snaps.containsKey("" + i + "," + index)) 
                return snaps.get("" + i + "," + index).intValue();       
        return 0;
    }


    // 567. Permutation in String

    public boolean checkInclusion(String s1, String s2) 
    {
        if(s1.length() > s2.length()) return false;
        HashMap<Character,Integer> st1 = new HashMap<>();
        HashMap<Character,Integer> st2 = new HashMap<>();
        
        for(int i = 0;i<s1.length();i++) st1.put(s1.charAt(i),st1.getOrDefault(s1.charAt(i),0)+1);
        
        int si = 0;
        int ei = 0;
        while(ei < s2.length())
        {
            while(ei-si < s1.length()) {
                st2.put(s2.charAt(ei),st2.getOrDefault(s2.charAt(ei),0)+1);
                ei++;
            }
            if(st1.equals(st2)) return true;
            st2.put(s2.charAt(si),st2.get(s2.charAt(si))-1);
            if(st2.get(s2.charAt(si)) == 0) st2.remove(s2.charAt(si));
            si++;
        }
        return false;
    }

    // 983. Minimum Cost For Tickets

    public int mincostTickets(int[] days, int[] costs) 
    {
        boolean[] isTravelDay = new boolean[366];
        for (int day : days) 
        {
            isTravelDay[day] = true;
        }
        int[] dp = new int[366];
        for (int i = 1; i < 366; i++) {
            if (!isTravelDay[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = Integer.MAX_VALUE;
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 1)] + costs[0]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2]);
        }
        return dp[365];
    }

    // 1911. Maximum Alternating Subsequence Sum

    public long maxAlternatingSum(int[] nums) 
    {
        long evenEnd = 0;
        long oddEnd = nums[0];
        for(int i= 1; i < nums.length; i++)
        {
            long neven = Math.max(evenEnd, oddEnd - (long)(nums[i]));
            long nodd = Math.max(oddEnd, evenEnd + (long)(nums[i]));
            evenEnd = neven;
            oddEnd = nodd;
        }       
        return Math.max(evenEnd, oddEnd);
    }
    

    // 
}
