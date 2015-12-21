package collatz;
import java.util.*;
public class Sequence
  {
  public static void main (String...args)
    {
    int n = Integer.parseInt(args[0]);
    long start = System.nanoTime();
    System.out.println(new Sequence().findLongestChainInteger(n));
    long end = System.nanoTime();
    System.out.println( (end - start)/1_000_000);
    }
  public int findLongestChainInteger(int n)
    {
    int maxCount = 1;
    int longestChainInt = 2;
    Map<Long,Integer> sequenceCount = new HashMap<>();
    for (int i = 3; i < n; i++)
      {
      long test = i;
      int count = 1;
      while (test!=1)
        {
        if (sequenceCount.get(test) != null)
          {
          count += sequenceCount.get(test);
          break;
          }
        if ((test&1)==1)
          {
          test = test*3 +1;
          }
        else
          {
          double log2base = Math.log(test)/Math.log(2);
          double roundedResult = (int)log2base;
          if ((log2base - roundedResult) == 0.0)
            {
            count += (int)log2base;
            break;
            }
          test /= 2;
          }
        count++;
        }
      sequenceCount.put((long)i,count);
      if (count > maxCount)
        {
        longestChainInt=i;
        maxCount = count;
        }
      }
    return longestChainInt;
    }
  }
