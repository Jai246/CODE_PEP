import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

class arrayString
{
    public static void Print1D(int[] arr)
    {
        for(int ele : arr)
        {
            System.out.print(ele + " ");
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i++, j--);
        }
    }

    public static void rotateByK(int[] arr, int r) {
        int n = arr.length;
        // r %= n;
        // if (r < 0)
        // r += n;
        r = (r % n + n) % n;

        reverse(arr, 0, n - 1);
        reverse(arr, n - r, n - 1);
        reverse(arr, 0, n - r - 1);

    }

    public static void segregatePositiveAndNegative(int[] arr) {
        int n = arr.length, pt = -1, itr = 0;
        while (itr < n) {
            if (arr[itr] < 0)
                swap(arr, ++pt, itr);
            itr++;
        }
    }
    // public static void segregateZerosOnesTwos(int[] arr)
    // {
    //     int pt = -1;
    //     int itr = 0;
    //     int last = arr.length;

    //     while(itr < last)
    //     {
    //         if(arr[itr] == 1)
    //         {
    //             itr++;
    //         }
    //         else if(arr[itr] == 0)
    //         {
    //             pt++;
    //             swap(arr, itr, pt);
    //             itr++;
    //         }
    //         else if(arr[itr] == 2)
    //         {
    //             last--;
    //             swap(arr, itr, last);
    //         }
    //     }
    //     Print1D(arr);
    // }

    // this will work for if one 2 is on idx 0 and other on idx arr.length-1;
    // dry run video mei update karna ki last arr.length-1 se start karna hei
    public static void segregateZerosOnesTwos(int[] arr)
    {
        int pt = -1;
        int itr = 0;
        int last = arr.length;
        while(itr < last)
        {
            if(arr[itr] == 1) itr++;
            else if(arr[itr] == 0) swap(arr, ++pt, itr++);
            else if(arr[itr] == 2) swap(arr, itr, --last);
        }
        Print1D(arr);
    }
    public static int max_sum(int[] arr, int n) {
        int sum = 0, sumWithIndex = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            sumWithIndex += arr[i] * i;
        }

        int maxAns = sumWithIndex;
        for (int i = 1; i < n; i++) {
            sumWithIndex = sumWithIndex - sum + arr[i - 1] * n;
            maxAns = Math.max(maxAns, sumWithIndex);
        }

        return maxAns;
    }

    // 11
    public int maxArea(int[] arr) {
        int n = arr.length, maxArea = 0, i = 0, j = n - 1;
        while (i < j) {
            int w = j - i;
            maxArea = arr[i] < arr[j] ? Math.max(maxArea, arr[i++] * w) : Math.max(maxArea, arr[j--] * w);
        }

        return maxArea;
    }
    // Leetcode 003
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
    // leetcode 76
    public static String minWindow(String s, String t) {

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

            while (count == 0)  // comparing and updating length
            {
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
    // leetcode 159 lintcode instead of 2 we have k
    public static int lengthOfLongestSubstringKDistinct(String s, int k) 
    {
        if(k == 0) return 0;
        int ei = 0;
        int si = 0;
        int[] map = new int[128];
        int count = k;
        int length = 0;
        while(ei < s.length())
        {
            if(map[s.charAt(ei++)]++ == 0) count--;
            
            while(count < 0)
            {
                if(map[s.charAt(si++)]-- == 1) count++;// important 
            }
            length = Math.max(length , ei - si);
        }
        return length;
    }
    // Geeks For Geeks Smallest distinct window
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
        //System.out.println(str.substring(start , end));
        return str.substring(start , end);
    }
    public static boolean isVowel(Character ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static int maxVowels(String s, int k) {

        // HashSet<Character> set = new HashSet<>();
        // set.add('a');
        // set.add('e');
        // set.add('i');
        // set.add('o');
        // set.add('u');

        int n = s.length(), si = 0, ei = 0, vowelCount = 0, maxVowelCount = 0;
        while (ei < n) {
            if (isVowel(s.charAt(ei++)))
                vowelCount++;

            if (ei - si == k) {
                maxVowelCount = Math.max(maxVowelCount, vowelCount);
                if (isVowel(s.charAt(si++)))
                    vowelCount--;
            }
        }

        return maxVowelCount;
    }
    //leetcode 992
    public static int AtmostkDistinctCount(int[] arr , int k)
    {
        int ei = 0;
        int si = 0;
        int count = 0;
        HashMap<Integer,Integer> set = new HashMap<>();
        while(ei < arr.length)
        {
            if(!set.containsKey(arr[ei]))
            {
                set.put(arr[ei] , 1);
                k--;
            }
            else set.put(arr[ei],set.get(arr[ei])+1);
            ei++;
            while(k == -1)
            {
                if(set.containsKey(arr[si]))
                {
                    if(set.get(arr[si])-1 == 0)
                    {
                        set.put(arr[si] , set.get(arr[si])-1);
                        set.remove(arr[si]);
                        k++;
                    }
                    else set.put(arr[si] , set.get(arr[si])-1);
                }
                si++;
            }
            count  = count +  ei-si;
        }
        return count;
    }
    public static int CountSubArraysExactlyK(int[]arr , int k)
    {
        return AtmostkDistinctCount(arr, k) - AtmostkDistinctCount(arr, k-1);
    }
    // Leetcode 1248
    public static int numberOfSubarrays(int[] nums, int k) 
    {
        return AtmostkDistinctCount2(nums,k) - AtmostkDistinctCount2(nums ,k-1);
    }
    public static int AtmostkDistinctCount2(int[] arr , int k)
    {
        int ei = 0;
        int si = 0;
        int count = 0;
        
        while(ei < arr.length)
        {
            //if(arr[ei++]%2!=0) k--;
            if((arr[ei++]&1)!=0) k--;
            while(k == -1)
            {
                if(arr[si++]%2 != 0) k++;
            }
            count += ei - si;
        }
        return count;
    }
    // gfg Longest K unique characters substring
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
                if(k == 0){
                gei = ei;
                gsi = si;
                }
            }
        }
        //System.out.println((gei - gsi) == 0  ? -1 : gei - gsi);
        return (gei - gsi) == 0  ? -1 : gei - gsi;
    }
    // leetcode fruites into basket
    public int totalFruit(int[] arr) {
        int n = arr.length, si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[40000 + 1];

        while (ei < n) {
            if (freq[arr[ei++]]++ == 0)
                count++;

            while (count > 2)
                if (freq[arr[si++]]-- == 1)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }
    // leetcode 930 array approach Atmost Subarrays
    public int atMostSum(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = 0, sum = 0, count = 0;
        while (ei < n) {
            sum += arr[ei++];

            while (sum > tar)
                sum -= arr[si++];

            count += ei - si;
        }

        return count;
    }
    
    public int numSubarraysWithSum(int[] arr, int tar) 
    {
        return atMostSum(arr, tar) - (tar != 0 ? atMostSum(arr, tar - 1) : 0);
    }
    // 485
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length, si = 0, ei = 0, len = 0, count = 0;
        while (ei < n) {
            if (nums[ei++] == 0)
                count++;

            while (count == 1) {
                if (nums[si++] == 0)
                    count--;
            }

            len = Math.max(len, ei - si);
        }
        return len;
    }

    public int findMaxConsecutiveOnes_01(int[] nums) {
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
    // 487
    public int findMaxConsecutiveOnes2(int[] arr) {
        int n = arr.length, si = 0, ei = 0, count = 0, len = 0;

        while (ei < n) {
            if (arr[ei++] == 0)
                count++;

            while (count == 2)
                if (arr[si++] == 0)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }

    public int findMaxConsecutiveOnes_02(int[] arr) {
        int n = arr.length, si = 0, ei = 0, firstZeroIndex = -1, len = 0;

        while (ei < n) {
            if (arr[ei++] == 0) {
                si = firstZeroIndex + 1;
                firstZeroIndex = ei - 1;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }
    // 1004
    public int longestOnes(int[] arr, int k) {
        int n = arr.length, si = 0, ei = 0, count = 0, len = 0;

        while (ei < n) {
            if (arr[ei++] == 0)
                count++;

            while (count > k)
                if (arr[si++] == 0)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }
    // 974
    public int subarraysDivByK(int[] arr, int k) {
        int[] rem = new int[k];
        int n = arr.length, ei = 0, sum = 0, ans = 0;
        rem[0] = 1;
        while (ei < n) {
            sum += arr[ei++];
            int r = (sum % k + k) % k;

            ans += rem[r];
            rem[r]++;
        }

        return ans;
    }

    public int longestSubarraysDivByK(int[] arr, int k) {
        int[] rem = new int[k];
        int n = arr.length, ei = 0, sum = 0, len = 0;
        Arrays.fill(rem, -2);
        rem[0] = -1;
        while (ei < n) {
            sum += arr[ei];
            int r = (sum % k + k) % k;

            if (rem[r] == -2)
                rem[r] = ei;
            else
                len = Math.max(len, ei - rem[r]);
            ei++;
        }

        return len;
    }

    public int subarraysDivByK_map(int[] arr, int k) {
        HashMap<Integer, Integer> rem = new HashMap<>();
        int n = arr.length, ei = 0, sum = 0, ans = 0;
        rem.put(0, 1);
        while (ei < n) {
            sum += arr[ei++];
            int r = (sum % k + k) % k;

            ans += rem.getOrDefault(r, 0);
            rem.put(r, rem.getOrDefault(r, 0) + 1);
        }

        return ans;
    }

    public int longestSubarraysDivByK_map(int[] arr, int k) {
        HashMap<Integer, Integer> rem = new HashMap<>();
        int n = arr.length, ei = 0, sum = 0, len = 0;
        rem.put(0, -1);
        while (ei < n) {
            sum += arr[ei];
            int r = (sum % k + k) % k;

            rem.putIfAbsent(r, ei);
            len = Math.max(len, ei - rem.get(r));
            ei++;
        }

        return len;
    }
    // gfg subarrays with equal ones and zeros
    public static int countSubarrWithEqualZeroAndOne(int arr[], int n)
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
    // leetcode 525 contigus array
    public static int findMaxLength(int[] arr) 
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
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return nums[b] - nums[a]; // other - this, reverse of default behaviour.
        });

        int n = nums.length, idx = 0;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (pq.size() != 0 && pq.peek() <= i - k)
                pq.remove();

            pq.add(i);

            if (i >= k - 1)
                ans[idx++] = nums[pq.peek()];
        }
        return ans;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        int n = nums.length, idx = 0;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (deque.size() != 0 && deque.getFirst() <= i - k)
                deque.removeFirst();

            while (deque.size() != 0 && nums[deque.getLast()] <= nums[i])
                deque.removeLast();

            deque.addLast(i);

            if (i >= k - 1)
                ans[idx++] = nums[deque.getFirst()];
        }
        return ans;
    }
    // kadanes 
    public static void kadanes(int[] arr)
    {
        int gsum = 0;
        int csum = 0;
        for(int ele : arr)
        {
            csum += ele;
            if(csum <= 0) csum = 0;
            if(csum > gsum) gsum = csum;
        }
        System.out.println(gsum);
    }
    public static void kadanesGeneric(int[] arr)
    {
        int gsum = -(int)1e9;
        int csum = 0;

        for(int ele : arr)
        {
            
            csum = Math.max(ele , csum + ele);
            gsum = Math.max(gsum , csum);
            
        }
        System.out.println(gsum);
    }

    public static int kadanesAlgo_SubArray(int[] arr) 
    {
        int gSum = 0, cSum = 0, gsi = 0, gei = 0, csi = 0;
        for (int i = 0; i < arr.length; i++) {
            int ele = arr[i];
            cSum += ele;
            if (cSum > gSum) {
                gSum = cSum;

                gsi = csi;
                gei = i;
            }
            if (cSum <= 0) {
                cSum = 0;
                csi = i + 1;
            }
        }

        return gSum;
    }
    public static int[] kadanesAlgoGenericSubarray(int[] arr) {
        int gSum = -(int) 1e9, cSum = 0, gsi = 0, gei = 0, csi = 0;
        for (int i = 0; i < arr.length; i++) {
            int ele = arr[i];
            cSum += ele;
            if (ele >= cSum) {
                cSum = ele;
                csi = i;
            }

            if (cSum > gSum) {
                gSum = cSum;
                gsi = csi;
                gei = i;
            }
        }

        return new int[] { gSum, gsi, gei };
    }
    // 
    public static int kadanesForMatrix(int []arr)
    {
        int gsum = -(int)1e9;
        int csum = 0;

        for(int ele : arr)
        {
            
            csum = Math.max(ele , csum + ele);
            gsum = Math.max(gsum , csum);
            
        }
        //System.out.println(gsum);
        return gsum;
    }
    public static int maximumSumRectangle(int R, int C, int M[][]) 
    {
        int []arr = new int[C];
        int MaxSum = -(int)1e9;
        for(int k = 0;k<R;k++)
        {
            for(int i = k;i<R;i++)
            {
                for(int j = 0;j<C;j++)
                {
                    arr[j] += M[i][j];
                }
                MaxSum = Math.max(MaxSum,kadanesForMatrix(arr));
            }
            Arrays.fill(arr,0);
        }
        System.out.println(MaxSum);
        return MaxSum;

    }
    // if we want to print matrix
    int maximumSumRectangle_02(int R, int C, int arr[][]) {
        int n = R, m = C, maxSum = -(int) 1e9;
        int[] colPrefixSum = new int[m];

        int r1 = 0, c1 = 0, r2 = 0, c2 = 0;

        for (int fixRow = 0; fixRow < n; fixRow++) {

            Arrays.fill(colPrefixSum, 0);

            for (int row = fixRow; row < n; row++) {
                for (int col = 0; col < m; col++)
                    colPrefixSum[col] += arr[row][col];

                int[] res = kadanesAlgoGenericSubarray(colPrefixSum);
                if (res[0] >= maxSum) {
                    maxSum = res[0];
                    r1 = fixRow;
                    c1 = res[1];
                    r2 = row;
                    c2 = res[2];
                }
            }
        }

        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        return maxSum;
    }
    // rabbits in forest leetcode 781
    public static int numRabbits(int[] arr) 
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
    // leetcode 560 SubArray sum ( Prefix Sum ) Approach using HashMap
    public static int subarraySum(int[] nums, int k) 
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
    // leetcode 1074
    public static int numSubmatrixSumTarget(int M[][] , int tar) 
    {
        int []arr = new int[M[0].length];
        int count = 0;
        for(int k = 0;k<M.length;k++)
        {
            for(int i = k;i<M.length;i++)
            {
                for(int j = 0;j<M[0].length;j++)
                {
                    arr[j] += M[i][j];
                }
                count += subarraySum(arr,tar);
            }
            Arrays.fill(arr,0);
        }
        System.out.println(count);
        return count;

    }
    public static void main(String[] args) 
    {
        // int[] arr = new int[]{2,0,0,1,0,2,0,0,1,1,0};
        // segregateZerosOnesTwos(arr);
        //System.out.println(lengthOfLongestSubstring("abcabcbb"));
        //findSubString("AABBBCBBAC");
        //findSubString("CCCbAbbBbbC");
        //maxVowels("aeiou", 5);
        //AtmostkCount(new int[]{2, 1, 2, 1, 6}, 2);
        //System.out.println(CountSubArraysExactlyK(new int[]{1,2,1,2,3}, 2));
        //longestkSubstr("aabacbebebe", 3);
        //kadanes(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
        //kadanesGeneric(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
        //int[][]matrix = new int[][]{{1,2,-1,-4,-20},{-8,-3,4,2,1},{3,8,10,1,3},{-4,-1,1,7,-6}};
        // maximumSumRectangle(4, 5, matrix);
    }
}