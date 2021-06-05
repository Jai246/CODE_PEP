class bit
{
    public static void solve(int A) 
    {
        if(A == 1 || A == 3)  System.out.println(1);
        if(A == 2) System.out.println(2);
        int[] count = new int[2];
        count[1] = (int)1e9;
        boolean[] vis = new boolean[A];
        Solve(A,vis,0,count);
        System.out.println(count[1]);
    }
    public static void Solve(int A , boolean[] vis ,int n, int[] count)
    {
        if(n == A-1)
        {
            count[1] = Math.min(count[1] , count[0]);
            return;
        }
        for(int i = 0;i<A;i++)
        {
            if(!vis[i])
            {
                int temp = 0;
                count[0]++;
                if(i >= 2) 
                {
                    vis[i-2] = true;
                    temp++;
                }
                if(i <= A - 3) {
                    vis[i+2] = true;
                    temp++;
                }
                vis[i] = true;
                Solve(A,vis,n+temp+1,count);
                if(i >= 2) 
                {
                    vis[i-2] = false;
                    temp--;
                }
                if(i <= A - 3) {
                    vis[i+2] = false;
                    temp--;
                }
                vis[i] = false;
                count[0]--;
            }
        }
    }
    public static void main(String[] args) 
    {
        solve(4);
    }
}