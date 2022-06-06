import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Bits
{
    // basics of bits
    public static void basicsOfBits(int i , int j , int k , int m , int n)
    {
        int setn = (1 << i);
        int unsetn = (1 << j);
        int toggle = (1 << k);
        int check = (1 << m);


        int setRes = (n & setn) == 0 ? (n | setn) : n;
        int unsetRes = (n & unsetn) > 0 ? (n ^ unsetn) : n;
        int toggleRes = (n ^ toggle);
        String checkRes = ((n & check)) > 0 ? "true" : "false";


        System.out.println(setRes);
        System.out.println(unsetRes);
        System.out.println(toggleRes);
        System.out.println(checkRes); 
    }

    // Right Most Set Bits
    public static int rmsb(int n) // getFirstSetBit Right Most Set Bit
    {
        if(n == 0) return 0;
        int val = (n & (-n)) ;
        return (int)(Math.log(val)/Math.log(2)) + 1;
    }

    // counting bits 2 approaches

    // approach 1
    // 
    public int countSetBits(int n) // This algo is also known as Kernighans Algorithm.. for counting the set bits in any number
    {
        int count = 0;
        while(n > 0)
        {
          count++;
          int rmsb = (n & (-n));
          n = n - rmsb;
        }
        return count;
    }
    
    
    public int[] countBits(int n) 
    {
        int[] ans = new int[n + 1];
        
        ans[0] = 0;
        
        for(int i = 1;i<ans.length;i++)
        {
            ans[i] = countSetBits(i);
        }
        return ans;
    }

    // approach 2
    // using DP
    // ratlo isko
    public int[] countBits(int num) 
    {
        int result[] = new int[num + 1];
        int offset = 1;
        for (int index = 1; index < num + 1; ++index){
            if (offset * 2 == index){
                offset *= 2;
            }
            result[index] = result[index - offset] + 1;
        }
        return result;
    }

    // josephus problem gfg two approaches iterative and recursive 
    // check video in dry running

    static int safePos(int n, int k)
    {
        return (josephus(n,k));
    }
    
    public static int josephus(int n , int k)
    {
      if(n == 1) return 0;
      return (josephus(n-1,k) + k)%n;
    }
    
    public static int josephus(int n , int k)
    {
        int ans = 0;
        for(int i = 2;i<=n;i++){
            ans=(ans+k)%i;
        }
        return ans+1;
    }

    // 89. Gray Code Leetcode
    // Iterative approach
    public List<Integer> grayCode(int n) 
    {
        ArrayList<String> ans = new ArrayList<>();
        ans.add("0");
        ans.add("1");
        
        if(n == 1) return new ArrayList<Integer>(Arrays.asList(0,1));
        
        for(int i = 2;i<=n;i++)
        {
            ArrayList<String> temp = new ArrayList<>();
            
            for(int j = 0;j<ans.size();j++)
            {
                String t = ans.get(j);
                temp.add("0" + t);
            }
            
            for(int j = ans.size()-1;j>=0;j--)
            {
                String t = ans.get(j);
                temp.add("1" + t);
            }
            
            ans = temp;
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        for(String ele : ans)
        {
            res.add(Integer.parseInt(ele,2)); // important function to remember
        }
        return res;
    }

    // Minimum Number Of Developers
    // giving tle on Leetcode
    HashMap<String , Integer> skillMap = new HashMap<>();
    ArrayList<Integer> ans = new ArrayList<>();
    boolean checkForFirstEntry = false;
    public int convert(List<String> list)
    {
        int mask = 0;
        for(String ele : list)
        {
            int idx = skillMap.get(ele);
            int toggleMask = (1 << idx);
            mask = (mask ^ toggleMask);
        }
        return mask;
    }
    
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) 
    {
        int i = 0;
        for(String ele : req_skills){
            skillMap.put(ele,i++);
        }
        
        int reqSkillMask = convert(new ArrayList<>(Arrays.asList(req_skills)));
        
        int[] pepSkill = new int[people.size()];
        for(int j = 0;j<people.size();j++)
        {
            List<String> pep = people.get(j);
            int msk = convert(pep);
            pepSkill[j] = msk;
        }
        
        ArrayList<Integer> min = new ArrayList<>();
        
        getMin(pepSkill,reqSkillMask,0,0,0,min);
        
        int[] arr = new int[ans.size()];
        int k = 0;
        for(int ele : ans) arr[k++] = ele;
        return arr;
    }
    
    
    public void getMin(int[]pepSkill , int skillMsk , int tempMsk , int n , int i , ArrayList<Integer>min)
    {
        if(i == pepSkill.length)
        {
            int temp = (skillMsk & tempMsk);
            if(temp == skillMsk)
            {
                if(min.size() < ans.size() || !checkForFirstEntry)
                {
                    ArrayList<Integer> g = new ArrayList<>();
                    for(int ele : min) g.add(ele);
                    ans = g;
                    checkForFirstEntry = true;
                }
            }
            return;
        }
        
        min.add(i);
        getMin(pepSkill ,skillMsk, (tempMsk | pepSkill[i]),n+1 , i+1 ,min);
        min.remove(min.size()-1);
        getMin(pepSkill ,skillMsk, tempMsk , n , i+1 , min);
    }


    // 1178. Number of Valid Words for Each Puzzle
    // Giving Tle on Leetcode 10/11 TestCases Passed
    // Try This With Tries After you learn them

    public int convert(String str)
    {
        int mask = 0;
        for(int i = 0;i<str.length();i++)
        {
            char ch = str.charAt(i);
            int idx = (int)ch - 'a';
            int toggleMask = (1 << idx);
            int checkMask = (1 << idx);
            if((mask & checkMask) == checkMask) continue;
            mask = (mask ^ toggleMask);
        }
        return mask;
    }
    
    
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) 
    {
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] map = new ArrayList[27];
        List<Integer> ans = new ArrayList<>();
        int[] word = new int[words.length];
        int[] puzzle = new int[puzzles.length];
        int l = 0;
        for(String ele : words){
            word[l++] = convert(ele);
        }
        l = 0;
        for(String ele : puzzles){
            puzzle[l++] = convert(ele);
        }
        for(int i = 0;i<puzzles.length;i++)
        {
            String str = puzzles[i];
            String first = str.charAt(0) + "";
            int converted = convert(first);
            if(map[str.charAt(0) - 'a' + 1] == null)
            {
                map[str.charAt(0) - 'a' + 1] = new ArrayList<>();
                for(int ele : word)
                {
                    if((converted & ele) == converted){
                        map[str.charAt(0) - 'a' + 1].add(ele);
                    }
                }
            }
        }
        
        for(int i = 0;i<puzzles.length;i++)
        {
            int p = puzzle[i];
            int count = 0;
            for(int w : map[puzzles[i].charAt(0) - 'a' + 1])
            {
                if((p & w) == w) count++;
            }
            ans.add(count);
            count = 0;
        }
        
        return ans;
    }

    // Find the element that appears once while other appears twice
    // Submitted On Geeks For Geeks
    // 136. Single Number
    public static int search(int A[], int N)
    {
        int ans = 0;
        for(int ele : A)
        {
            ans = (ans ^ ele);
        }
        return ans;
    }


    // Non Repeating Numbers
    // All Repeating Except Two | Two Unique Rest Twice 
    // 260. Single Number III

    public int[] singleNumber(int[] nums)
    {
        int[] res = new int[2];
        int ans = 0;
        
        for(int ele : nums){
            ans = (ans ^ ele);
        } 
        int x = 0;
        int y = 0;
        int rmsb = ((ans)&(-ans));
        
        for(int ele : nums)
        {
            if((ele & rmsb) == rmsb)
            {
                x = x^ele;
            }
            else y = y^ele;
        }
        
        if(x < y)
        {
            res[0] = x;
            res[1] = y;
        }
        else
        {
            res[0] = y;
            res[1] = x;
        }
        
        return res;
    }


    // Find Duplicate Number and Missing Number from 1 to N |
    // Submitted On Pepcoding

    public static void solution(int[] arr)
    {
        ArrayList<Integer> a = new ArrayList<>();
        for(int ele : arr) a.add(ele);
        for(int i = 1;i<= arr.length;i++) a.add(i);
        int ans =  0;
        for(int ele : a) ans = ans ^ ele;

        int x = 0;
        int y = 0;
        int rmsb = ((ans)&(-ans));

        for(int ele : a)
        {
            if((ele & rmsb) == rmsb)
            {
                x = x^ele;
            }
            else y = y^ele;
        }

        for(int ele : arr)
        {
            if(x == ele)
            {
                System.out.println("Missing Number -> " + y);
                System.out.println("Repeating Number -> " + x);
                break;
            }
            else if(y == ele)
            {
                System.out.println("Missing Number -> " + x);
                System.out.println("Repeating Number -> " + y);
                break;
            }
        }
    }

    // 137. Single Number II
    // Sumeet Sir's approach not working for -ve elements

    public int singleNumber(int[] nums) 
    {
        int threeKaMul = Integer.MAX_VALUE;
        int threeKaMulPOne = 0;
        int threeKaMulPTwo = 0;
        
        for(int ele : nums)
        {
            int a = threeKaMul & ele;
            int b = threeKaMulPOne & ele;
            int c = threeKaMulPTwo & ele;
            
            threeKaMul = threeKaMul & (~a);
            threeKaMulPOne = threeKaMulPOne & (~b);
            threeKaMulPTwo = threeKaMulPTwo & (~c);
            
            threeKaMulPOne = threeKaMulPOne | a;
            threeKaMulPTwo = threeKaMulPTwo | b;
            threeKaMul = threeKaMul | c;
        }
        
        return threeKaMulPOne;
    }

    // Another Approach nlogn
    // Intutive approach
    
    public int singleNumber(int[] nums) 
    {
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length)
        {
            if(i+1 < nums.length && nums[i] == nums[i+1]) i = i + 3;
            else return nums[i];
        }
        return -1;
    }

    // another approach would be count set bits which will take 32*n time and 
    // then take mod of each count by 3 by this we will get our final answer
    // for eg after count we get (3 4 4) then our answer would be (011)


    // Except this there is one jugadu approach also ratni hoo toh rat lena pep ki video hei 
    // and tech dose nee bhi video banayi hei hei
    public int singleNumber(int[] nums) 
    {
        int shift = 1;
        int ans = 0;
        for(int i = 0;i<32;i++)
        {
            int count = 0;
            for(int ele : nums) if((shift & ele) == shift) count++;
            if(count%3 != 0) ans+= shift;
            shift = shift * 2;
        }
        return ans;
    }

    // Triplets - 1

    public static void solution(int[] arr)
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
        System.out.println(ans);
    }

    // Reduce a Number to 1 in Minimum Steps 
    // Ratlo Is Approach Ko 
    // Every thing done by obsvation
    // insted of 2x and 2x+1;
    // we are taking 4x , 4x+2 as even  && 4x+1 , 4x+3 as odd
    // we can do this recursively as well 
    public static int solution(long n) {
        int res = 0;
        while(n!=1)
        {
            if(n%2 == 0) n = n/2;
            else if(n == 3){
                res+=2;
                break;
            } 
            else if(n%4 == 1) n = n-1;
            else if(n%4 == 3) n = n+1;
            res++;
        }
        return res;
    }


    // Pepcoder And Bits 

    public static long ncr(long n, long r){
        if(n < r){
            return 0L;    
        }
        long res = 1L;

        for(long i = 0L; i < r; i++){
            res = res * (n - i);
            res = res / (i + 1);
        }
        
        return res;
    }
    
    public static long solution(long n, int k, int i)
    {
      if(k == 0) return 0;  
      long count = 0;
      long mask = 1L << i;

      if((mask & n) == mask){
        count += ncr(i,k);
        count += solution(n,k-1,i-1);
      }
      else count += solution(n,k,i-1);
      return count;

    }
    
    public static int csb(long n){
        int res = 0;
        while(n > 0){
            long rsb = n & -n;
            n -= rsb;
            res++;
        }
        return res;
    }

    // Xor Of Sum Of All Pairs

    public static int solution(int[] arr)
    {
        int xor = 0;
        for(int ele : arr) xor ^= ele;
        return 2*xor;       
    }


    // Abbrevations 1 Pepcoding

    public static void solve(String str)
    {
        int len = (int)Math.pow(2,str.length());

        for(int i=0;i<len;i++)
        {
            int count = 0;
            String ans = "";
            
            for(int j = 0;j<str.length();j++)
            {
                int shift = 1 << j;
                if((i & shift) == 0)
                {
                    if(count > 0)
                    {
                        ans = count + ans;
                        count = 0;
                    } 
                    ans = str.charAt(str.length() - j - 1) + ans;
                }
                else count++;
            }
            if(count > 0) ans = count + ans;
            count = 0;
            System.out.println(ans);
        }
    }


    // UTF 8 IMPORTANT CODE STYLE
    // COPIED FORM PEPCODING PORTAL
    public static boolean solution(int[] arr) {
    int rbytes = 0; //rbytes=remaning bytes
    for (int val : arr) {
      if (rbytes == 0) {
        if ((val >> 7) == 0b0) {
          rbytes = 0;
        } else if ((val >> 5) == 0b110) {
          rbytes = 1;
        } else if ((val >> 4) == 0b1110) {
          rbytes = 2;
        } else if ((val >> 3) == 0b11110) {
          rbytes = 3;
        }
      } else {
        if ((val >> 6) == 0b10) {
          rbytes--;
        } else {
          return false;
        }
      }
    }

    if (rbytes == 0) {
      return true;
    } else {
      return false;
    }
  }


  // N queens Leetcode

  int col = 0;    
    int rDiag = 0;
    int lDiag = 0;
    public boolean isSafeToPLace(int c ,int r , int n)
    {
        int rMask = (1 << (r + c));
        int lMask = (1 << (r - c + n - 1));
        int cMask = (1 << c);
        if((col & cMask) != 0 || (lDiag & lMask) != 0 || (rDiag & rMask) != 0) return false;
        return true;
    }
    
    public void solve(int r ,int n, int[][] map , List<List<String>> ans)
    {
        if(r == n)
        {
            List<String> temp = new ArrayList<>();
            for(int i = 0;i<map.length;i++)
            {
                String a = "";
                for(int j = 0;j<map[0].length;j++)
                {
                    if(map[i][j] == 0) a+=".";
                    else a+="Q";
                }
                temp.add(a);
            }
            ans.add(temp);
            return;
        }
        
        for(int i = 0;i<n;i++)
        {
            if(isSafeToPLace(i,r,n))
            {
                int toggleMask = (1 << i);
                int toggleMaskDiag1 = (1 << (i+r));
                int toggleMaskDiag2 = (1 << (r-i+n-1));
                
                rDiag = (rDiag ^ toggleMaskDiag1);
                lDiag = (lDiag ^ toggleMaskDiag2);
                col = (col ^ toggleMask);
                
                map[r][i] = 1;
                solve(r+1,n,map,ans);
                map[r][i] = 0;
                
                col = (col ^ toggleMask);
                rDiag = (rDiag ^ toggleMaskDiag1);
                lDiag = (lDiag ^ toggleMaskDiag2);
            }
        }
    }
    public List<List<String>> solveNQueens(int n) 
    {
        List<List<String>> ans = new ArrayList<>();
        int[][] map = new int[n][n];
        solve(0,n,map,ans);
        return ans;
    }


    // N Queens 2 Leetcode

    HashSet<String> set = new HashSet<>();
    int col = 0;    
    int rDiag = 0;
    int lDiag = 0;
    public boolean isSafeToPLace(int c ,int r , int n)
    {
        int rMask = (1 << (r + c));
        int lMask = (1 << (r - c + n - 1));
        int cMask = (1 << c);
        if((col & cMask) != 0 || (lDiag & lMask) != 0 || (rDiag & rMask) != 0) return false;
        return true;
    }
    
    public void solve(int r ,int n, int[][] map)
    {
        if(r == n)
        {   
            String a = "";
            for(int i = 0;i<map.length;i++)
            {
                for(int j = 0;j<map[0].length;j++)
                {
                    a += map[i][j];
                }
            }
            set.add(a);
            return;
        }
        
        for(int i = 0;i<n;i++)
        {
            if(isSafeToPLace(i,r,n))
            {
                int toggleMask = (1 << i);
                int toggleMaskDiag1 = (1 << (i+r));
                int toggleMaskDiag2 = (1 << (r-i+n-1));
                
                rDiag = (rDiag ^ toggleMaskDiag1);
                lDiag = (lDiag ^ toggleMaskDiag2);
                col = (col ^ toggleMask);
                
                map[r][i] = 1;
                solve(r+1,n,map);
                map[r][i] = 0;
                
                col = (col ^ toggleMask);
                rDiag = (rDiag ^ toggleMaskDiag1);
                lDiag = (lDiag ^ toggleMaskDiag2);
            }
        }
    }
    public int totalNQueens(int n) 
    {
        int[][] map = new int[n][n];
        solve(0,n,map);
        return set.size();
    }



    // 37. Sudoku Solver

    int[][] check = new int[3][3];
    int[] row = new int[9];
    int[] col = new int[9];
    
    public boolean isSafeToPlace(int r , int c , int n)
    {
        int x = r/3;
        int y = c/3;
        int mask = (1 << n);
        
        if((row[r] & mask) !=0 ) return false;
        if((col[c] & mask) !=0) return false;
        if((check[x][y] & mask) !=0) return false;
        
        return true;
    }
    
    public void solveSudoku(char[][] board) 
    {
        // Pre-Calculations
        for(int i = 0;i<9;i++)
        {
            for(int j = 0;j<9;j++)
            {
                String str = board[i][j] + "";
                int val = (str.equals(".")) ? -1 : Integer.parseInt(str);
                if(val!=-1)
                {
                    int mask = (1 << val);
                    row[i] = row[i] ^ mask;
                    col[j] = col[j] ^ mask;
                    check[i/3][j/3] = check[i/3][j/3] ^ mask;
                }
            }
        }
        solve(board,0,0);
    }
    
    public boolean solve(char[][] board , int i , int j)
    {
        if(i == 9)
        {
            Arrays.toString(board);
            return true;
        }
        String str = board[i][j] + "";
        int val = (str.equals(".")) ? -1 : Integer.parseInt(str);
        if(val != -1)
        {
            boolean res = solve(board , (j == 8) ? i + 1 : i , (j == 8) ? 0 : j + 1);
            if(res) return true;
        }
        else
        {
            for(int n=1;n<=9;n++)
            {
                if(isSafeToPlace(i,j,n))
                {
                    int mask = (1 << n);
                    row[i] = row[i] ^ mask;
                    col[j] = col[j] ^ mask;
                    check[i/3][j/3] = check[i/3][j/3] ^ mask;
                    board[i][j] = (char)(n+'0'); // Integer To Character

                    boolean res = solve(board , (j == 8) ? i + 1 : i , (j == 8) ? 0 : j + 1);
                    if(res) return true;

                    row[i] = row[i] ^ mask;
                    col[j] = col[j] ^ mask;
                    check[i/3][j/3] = check[i/3][j/3] ^ mask;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }


    // 36. Valid Sudoku

    int[][] check = new int[3][3];
    int[] row = new int[9];
    int[] col = new int[9];
    
    public boolean isValidSudoku(char[][] board) 
    {
        for(int i = 0;i<9;i++)
        {
            for(int j = 0;j<9;j++)
            {
                String str = board[i][j] + "";
                int val = (str.equals(".")) ? -1 : Integer.parseInt(str);
                if(val!=-1)
                {
                    int mask = (1 << val);
                    if((row[i] & mask) == mask) return false;
                    row[i] = row[i] ^ mask;
                    if((col[j] & mask) == mask) return false;
                    col[j] = col[j] ^ mask;
                    if((check[i/3][j/3] & mask) == mask) return false;
                    check[i/3][j/3] = check[i/3][j/3] ^ mask;
                }
            }
        }
        return true;
    }

    // Flip Bits To Convert A To B

    public static int countBitsFlip(int a, int b){
        int val = (a ^ b);
        int count = 0;
        while(val!=0)
        {
          int t = (val & (-val));
          count++;
          val = val - t;
        }
        return count;
    }

    // Copy Set Bits In A Range
    public void copySet(int a , int b , int left , int right)
    {
        int m1 = 1;
        m1 = (m1 << (right - left + 1));
        m1 = m1 - 1;
        m1 = (m1 << (left-1));

        int res = a & m1;
        res = b | res;

        System.out.println(res);
    }

    // 231. Power of Two

    public boolean isPowerOfTwo(int n) 
    {
        long val = (long)n;
        if(val == 0) return false;
        return ((val & (val-1)) == 0) ? true : false;    
    }

    // Power of 4

    public boolean isPowerOfFour(int num) 
    {
        return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) > 0; 
    }

    // Swap all odd and even bits 

    public static int swapBits(int n) 
    {
        int m1 = 0b01010101010101010101010101010101;
        int m2 = 0b10101010101010101010101010101010;
        
        int r1 = n&m2;
        int r2 = n&m1;
        
        r1 = r1 >> 1;
        r2 = r2 << 1;
        
        return r1 | r2;
    }

    // Bit Difference GFG

    public static int countBits(int N, long A[])
    {
        int mod = (int)1e9 + 7;
        long ans = 0;
        long mask = 1;
        for(int i = 0;i<64;i++)
        {
            long count = 0;
            for(long ele : A)
            {
                if((ele & mask) == mask) count++;
            }
            mask = mask << 1;
            ans += ((count) * ((N - count)) * 2) % mod ;
            if(ans >= mod) ans -= mod ; // Discussion se dekha tha ye line 
            // pata nahi ku chal raha hei code isko likhkar
        }
        return (int)ans ;
    }

    // Count Total Set Bits GFG

    public static int countSetBits(int n)
    {
        if(n == 0) return 0;
        int x = largestPowerOfTwo(n);
        int totalCountBitsPower2 = x * ((1<<(x - 1))); // x * 2^(x-1)
        int nextComponent = n - (1<<x) + 1;
        int rest = n - (1<<x);
        int ans = totalCountBitsPower2 + nextComponent + countSetBits(rest);
        return ans;
    }

    public static int largestPowerOfTwo(int n)
    {
        int x = 0;
        while((1<<x) <= n) x++;
        return x - 1;
    }
    
    // Min Xor Pairs
    // Important Question nlogn Approach

    public static void solution(int[] arr) 
    {
        Arrays.sort(arr);
        ArrayList<String> list = new ArrayList<>();
        int min = (int)1e9;
        for(int i = 1;i<arr.length;i++)
        {
            int val = arr[i]^arr[i-1];
            if(val < min)
            {
                min = val;
                list = new ArrayList<>();
                list.add(arr[i-1] + ", " + arr[i]);
            }
            else if(min == val)
            {
                list.add(arr[i-1] + ", " + arr[i]);
            }
        }

        for(String ele : list)
        {
            System.out.println(ele);
        }        
    }

    // Nth Palindromic Binary

    public static int reverse(int n)
    {
        int res = 0;
        while(n>0)
        {
            res = res | (n & 1);
            n = n >> 1;
            res = res << 1;
        }
        // System.out.println(res >> 1);
        return res >> 1;
    }


    public static int NthPalindromicBinary(int n) 
    {
        int len = 0;
        int c = 1;

        while(len < n){
            len += (1 << (c-1)/2);
            c++;
        }
        c--;
        // len -= c; this will not work for odd length
        len -= (1 << (c-1)/2);
        // System.out.println(len);
        

        int set = n - len - 1;

        int ans = 1 << (c-1);
        ans |= (set << (c/2));
        // ans |= reverse(ans >> (c/2)); // both will work 
        ans |= reverse(ans);

        return ans;
    }

    // 1310. XOR Queries of a Subarray

    public int[] xorQueries(int[] A, int[][] queries) 
    {
        int[] res = new int[queries.length], q;
        for (int i = 1; i < A.length; ++i) A[i] ^= A[i - 1];
        for (int i = 0; i < queries.length; ++i) 
        {
            q = queries[i];
            res[i] = q[0] > 0 ? A[q[0] - 1] ^ A[q[1]] : A[q[1]];
        }
        return res;
    }

    // 1318. Minimum Flips to Make a OR b Equal to c

    public int minFlips(int a, int b, int c) 
    {
        int count = 0;
        int mask = 1;
        boolean x = false;
        boolean y = false;
        boolean z = false;
        for(int i = 0;i<32;i++)
        {
            x = ((mask & a) > 0) ? true : false;
            y = ((mask & b) > 0) ? true : false;
            z = ((mask & c) > 0) ? true : false;
            
            if(z)
            {
                if(x == false && y == false) count++;
            }
            else
            {
                if(x == true && y == true) count += 2;
                else if(x == true && y == false) count++;
                else if(x == false && y == true) count++;
            }
            mask = mask * 2;
        }
        return count;
    }

    // 1542. Find Longest Awesome Substring
    // code 1 not passing on leetcode giving tle

    public int longestAwesome(String s) 
    {
        int ans = 1;
        int[] dp = new int[s.length()+1];
        dp[0] = 0;
        for(int i = 1;i<=s.length();i++)
        {
            int val = Integer.parseInt((s.charAt(i-1)+""));
            dp[i] = dp[i-1]^(1 << val);
        }
        
        for(int len = 2;len <= s.length();len++)
        {
            for(int i = 1;i+len<=dp.length;i++)
            {
                int j = i+len-1;
                int res = dp[j]^dp[i-1];
                // checking is power of 2 as in this case only onw bit is onn
                if((res&(res-1)) == 0 || res == 0) ans = Math.max(len,ans);
            }
        }
        return ans;
    }

    // 2275. Largest Combination With Bitwise AND Greater Than Zero
    // Just returning Max Number of Set Bits in a column
    public int largestCombination(int[] candidates) 
    {
        int max = 0;
        for(int i=0;i<30;i++)
        {
            int c=0;
            for(int x : candidates) if((x>>i&1)==1) c++;
            max = Math.max(max,c);
        }
        return max;
    }

    
}