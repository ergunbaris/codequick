import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in))
            {
            byte T = in.nextByte();
            for (byte tc = 0; tc < T; tc++)
              {
              int N = in.nextInt();
              int [] arr = new int[N];
              for (int i = 0; i < N; i++)
                {
                arr[i] = in.nextInt();
                }
              System.out.println(xorArray(arr));
              }
            }
    }
    public static int xorArray(int [] arr)
        {
        int result = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++)
          {
          if (isIndexEven(N,
                          i)
              )
            {
            result ^= arr[i];
            }
          }
        return result;
        }
            
    private static boolean isIndexEven(int N,
                                       int i)
      {
      int mid = N / 2;
      int dist = 0;
      if (i <= mid)
        {
        dist = i;
        }
      else
        {
        dist = N - i - 1;
        }
      int res = (dist + 1) * (N - dist);
      return (res & 1) == 1;
      }
}
