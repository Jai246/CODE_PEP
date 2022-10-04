class daily
{
    // 557. Reverse Words in a String III

	public String reverseWords(String s) 
    {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        String[] arr = (sb.toString()).split(" ");
        int i = 0;
        int j = arr.length-1;
        
        while(i < j)
        {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        sb = new StringBuilder(arr[0]);
        for(int x = 1;x<arr.length;x++)
        {
            sb.append(" ");
            sb.append(arr[x]);
        }
        return sb.toString();
    }


    
}