class tries
{
	// Tries Implementation 
	// Leetcode 208
	class TrieNode 
    {
        public boolean isWord; 
        public TrieNode[] children = new TrieNode[26];
        public TrieNode() {}
    }
    private TrieNode root;
    public Trie() 
    {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null){
                ws.children[c - 'a'] = new TrieNode();
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root; 
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root; 
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }

    // 211. Design Add And Search words Data Structure
    public class node
    {
        boolean isWord = false;
        node[] children = new node[26];
        node(){
        }
    }
    
    public node root;
    
    public WordDictionary() {
        root  = new node();
    }
    
    public void addWord(String word) 
    {
        node ws = root;
        for(int i = 0;i<word.length();i++)
        {
            int idx = word.charAt(i)-'a';
            if(ws.children[idx] == null) ws.children[idx] = new node();
            ws = ws.children[idx];
        }
        ws.isWord = true;
    }
    
    public boolean search(String word)
    {
        return match(word,0,root);
    }
    
    public boolean match(String word , int k , node ws) 
    {
        if(k == word.length()) return ws.isWord;
        int idx = word.charAt(k)-'a';
        if(word.charAt(k) == '.')
        {
            boolean check = false;
            for(int j = 0;j<26;j++)
            {
                if(ws.children[j]!=null) check = match(word,k+1,ws.children[j]);
                if(check) return true;
            }
            return false;
        }
        else
        {
            if(ws.children[idx] == null) return false;
            return match(word,k+1,ws.children[idx]);
        }
    }

    // 212. Word Search II

    public class node
    {
        node[] children = new node[26];
        String word;
        node(){
        }
    }
    public node root = new node();
    
    public List<String> findWords(char[][] board, String[] words) 
    {
        for(String word : words)
        {
            node ws = root;
            for(int i = 0;i<word.length();i++)
            {
                int idx = word.charAt(i)-'a';
                if(ws.children[idx] == null) ws.children[idx] = new node();
                ws = ws.children[idx];
            }
            ws.word = word;
        }
        
        List<String> ans = new ArrayList<>();
        for(int i = 0;i<board.length;i++)
        {
            for(int j = 0;j<board[0].length;j++)
            {
                dfs(board,i,j,root.children[board[i][j]-'a'],ans);
            }
        }
        return ans;
    }
    
    public void dfs(char[][] board ,int i , int j, node root , List<String> ans)
    {   if(root == null) return;
        if(root.word!=null)
        {
            ans.add(root.word);
            root.word = null;
        }
        char c = board[i][j];
        board[i][j] = '#';
        if(i-1 >=0 && board[i-1][j]!='#') dfs(board,i-1,j,root.children[board[i-1][j]-'a'],ans);
        if(i+1 < board.length && board[i+1][j]!='#') dfs(board,i+1,j,root.children[board[i+1][j]-'a'],ans);
        if(j+1 < board[0].length && board[i][j+1]!='#') dfs(board,i,j+1,root.children[board[i][j+1]-'a'],ans);
        if(j-1 >=0 && board[i][j-1]!='#') dfs(board,i,j-1,root.children[board[i][j-1]-'a'],ans);
        board[i][j] = c;
    }


    // Leetcode 648. Replace Words

	class TrieNode 
	{
	    TrieNode[] children;
	    String word;
	    TrieNode() 
	    {
	        children = new TrieNode[26];
	    }
	}

    public String replaceWords(List<String> roots, String sentence) 
    {
        TrieNode trie = new TrieNode();
        for (String root: roots) 
        {
            TrieNode cur = trie;
            for (char letter: root.toCharArray()) 
            {
                if (cur.children[letter - 'a'] == null) cur.children[letter - 'a'] = new TrieNode();
                cur = cur.children[letter - 'a'];
            }
            cur.word = root;
        }

        StringBuilder ans = new StringBuilder();

        for (String word: sentence.split("\\s+")) 
        {
            if (ans.length() > 0) ans.append(" ");
            
            TrieNode cur = trie;
            for (char letter: word.toCharArray()) 
            {
                if (cur.children[letter - 'a'] == null || cur.word != null) break; // because we can  find the shortest successor earlier as well cur.word != null
                cur = cur.children[letter - 'a'];
            }
            ans.append(cur.word != null ? cur.word : word);
        }
        return ans.toString();
    }

    // 421. Maximum XOR of Two Numbers in an Array

    public class node
    {
        node left;
        node right;
        boolean isDigit;
        node(){    
        }
    }
    
    public node root;
    
    public int findMaximumXOR(int[] nums) 
    {
        root = new node();
        
        for(int ele : nums)
        {
            node temp = root;
            for(int i = 31;i>=0;i--)
            {
                int bit = (ele>>i)&1;
                if(bit == 0){
                    if(temp.left == null) temp.left = new node();
                    temp = temp.left;
                }
                else{
                    if(temp.right == null) temp.right = new node();
                    temp = temp.right;
                }
            }
        }
        
        int max = -(int)1e9;
        int val = 0;
        for(int ele : nums)
        {
            node temp = root;
            for(int i = 31;i>=0;i--)
            {
                int bit = (ele>>i)&1;
                if(bit == 0)
                {
                    if(temp.right!=null){
                        temp = temp.right;
                        val += Math.pow(2,i);
                    }
                    else temp = temp.left;
                }
                else 
                {
                    if(temp.left!=null){
                        temp = temp.left;
                        val += Math.pow(2,i);
                    }
                    else temp = temp.right;
                }
            }
            max = Math.max(max,val);
            val = 0;
        }
        return max;
    }


    // 677. Map Sum Pairs

    class TrieNode 
    {
        TrieNode[] child = new TrieNode[26];
        int sum = 0; 
    }

    class MapSum 
    {
        HashMap<String, Integer> map = new HashMap<>();
        TrieNode trieRoot = new TrieNode();

        public void insert(String key, int val) 
        {
            int diff = val - map.getOrDefault(key, 0);
            TrieNode curr = trieRoot;
            for (char c : key.toCharArray()) 
            {
                c -= 'a';
                if (curr.child[c] == null) curr.child[c] = new TrieNode();
                curr = curr.child[c];
                curr.sum += diff;
            }
            map.put(key, val);
        }
        public int sum(String prefix) 
        {
            TrieNode curr = trieRoot;
            for (char c : prefix.toCharArray()) 
            {
                c -= 'a';
                if (curr.child[c] == null) return 0;
                curr = curr.child[c];
            }
            return curr.sum;
        }
    }

    // 720. Longest Word in Dictionary
    // Solution Using Sorting and HashMap

    public String longestWord(String[] words) 
    {
        Arrays.sort(words);
        Set<String> built = new HashSet<String>();
        String res = "";
        for (String w : words) 
        {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) 
            {
                res = w.length() > res.length() ? w : res;
                built.add(w);
            }
        }
        return res;
    }

    // Trie Solution
    class TreeNode 
    {
        TreeNode[] children = new TreeNode[26];
        String word;
        TreeNode () {}
    }
    private TreeNode root;
    private String result = "";

    public String longestWord(String[] words) {
        root = new TreeNode();

        for (String w : words)
            insert(w);

        dfs(root);

        return result;
    }

    private void dfs(TreeNode node) {
        if (node == null)
            return;

        if (node.word != null) {
            if (node.word.length() > result.length())
                result = node.word;
            else if (node.word.length() == result.length() && node.word.compareTo(result) < 0)
                result = node.word;
        }

        for (TreeNode child : node.children)
            if (child != null && child.word != null)
                dfs(child);
    }

    private void insert(String word) {
        TreeNode current = root;
        for (char c : word.toCharArray()) {
            if (current.children[c - 'a'] == null)
                current.children[c - 'a'] = new TreeNode();
            current = current.children[c - 'a'];
        }
        current.word = word;
    }


    // 1032. Stream of Characters

    class TrieNode{
        boolean isWord;
        TrieNode children[] = new TrieNode[26];
    }
    
    TrieNode root = new TrieNode();
    int maxSize;
    StringBuilder sb = new StringBuilder();
    
    public StreamChecker(String[] words) {
        insert(words);
    }
    
    public boolean query(char letter) {
        if(sb.length()>=maxSize){
            sb.deleteCharAt(0);
        }
        sb.append(letter);
        TrieNode curr = root;
        
        for(int i=sb.length()-1;i>=0;i--)
        {
            char ch = sb.charAt(i);
            if(curr!=null) curr = curr.children[ch-'a'];
            if(curr!=null && curr.isWord) return true;
        }
        return false;
    }
    
    public void insert(String[] words)
    {
        for(String s : words)
        {
            maxSize = Math.max(maxSize,s.length());
            TrieNode curr = root;
            for(int i = s.length()-1;i>=0;i--)
            {
                char ch = s.charAt(i);
                if(curr.children[ch-'a']==null) curr.children[ch-'a'] = new TrieNode();
                curr = curr.children[ch-'a'];
            }
            curr.isWord = true;
        }
    }

    // 1803. Count Pairs With XOR in a Range

    public class node{
        int count;
        node left;
        node right;
        node(){}
    }
    public node root;
    public int countPairs(int[] nums, int low, int high)
    {
        root = new node();
        int count = 0;
        for(int ele : nums)
        {
            count += (find(ele,high+1) - find(ele,low));
            insert(ele);
        }
        return count;
    }
    
    public int find(int n , int k)
    {
        int c = 0;
        node temp = root;
        for(int i = 14;i>=0&&temp!=null;i--)
        {
            int x = (n>>i)&1;
            int y = (k>>i)&1;
            
            if(y == 1)
            {
                if(temp.right!=null && x == 1)
                {
                    c+=temp.right.count;
                    temp = temp.left;
                }
                else if(temp.left!=null && x == 0)
                {
                    c+=temp.left.count;
                    temp = temp.right;
                }
                else if(x == 0 && temp.left == null) temp = temp.right;
                else if(x == 1 && temp.right == null) temp = temp.left;
 
            }
            else
            {
                if(x == 1) temp = temp.right;
                else if(x == 0) temp = temp.left;
            }
        }
        return c;
    }
    
    public void insert(int val)
    {
        node temp = root;
        for(int i = 14;i>=0;i--)
        {
            int bit = (val>>i)&1;
            if(bit == 1)
            {
                if(temp.right == null) temp.right = new node();
                temp.right.count++;
                temp = temp.right;
            }
            else {
                if(temp.left == null) temp.left = new node();
                temp.left.count++;
                temp = temp.left;
            }
        }
    }

                                

}