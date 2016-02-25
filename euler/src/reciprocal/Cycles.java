package reciprocal;
import java.util.*;

public class Cycles
  {
  private final LinkedList<Integer> [] reciprocalLists;
  private int maxCycleIndex = 1;
  public static void main (String ... args)
    {
    int max = Integer.parseInt(args[0]);
    System.out.println(new Cycles(max).getMaximumReciprocalNumber());
    }
  public Cycles(int max)
    {
    reciprocalLists = new LinkedList[max];
    findCycles(reciprocalLists);
    }
  public int getMaximumReciprocalNumber()
    {
    return maxCycleIndex;
    }
  private void findCycles(LinkedList<Integer> [] reciprocalLists)
    {
    int maxCycleCnt = 0;
    for (int i = 1; i < reciprocalLists.length; i++)
      {
      int [] remainders = new int[reciprocalLists.length];
      int numerator = 1;
      int remainder = 0;
      int result = 0;
      int index = 1;
      while ((remainder = ((numerator*10)%i)) != 0)
        {
        if (remainders[remainder] != 0)
          {
          int cycleCount = reciprocalLists[i].size() - remainders[remainder] + 1;
          if (maxCycleCnt < cycleCount)
            {
            maxCycleCnt = cycleCount;
            this.maxCycleIndex = i;
            }
          break;
          }
        else
          {
          remainders[remainder] = index;
          index++;
          }
        result = ((numerator*10)/i);
        if (reciprocalLists[i] == null)
          {
          reciprocalLists[i] = new LinkedList<>();
          }
        reciprocalLists[i].addLast(result);
        numerator = remainder;
        }
      if (remainder == 0)
        {
        reciprocalLists[i] = null;
        }
      }
    }
  }
