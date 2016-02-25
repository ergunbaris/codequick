package reciprocal;
import java.util.*;

public class Cycles
  {
  private final List<Integer> [] reciprocalLists;
  private int maxCycleIndex = 0;
  public static void main (String ... args)
    {
    
    }
  public Cycles(int max)
    {
    reciprocalLists = new List[max];
    for (int i = 0; i < max; i++)
      {
      reciprocalLists[i] = new LinkedList<>();
      }
    }
  private void findCycles(List<Integer> [] reciprocalLists)
    {
    int maxCycleCnt = 0;
    for (int i = 2; i < reciprocalLists.length; i++)
      {
      int [] remainders = {-1,0,-1,-1,-1,-1,-1,-1,-1,-1};
      int numerator = 1;
      int d = i;
      int remainder = 0;
      while ((remainder = ((numerator*10)%d)) != 0)
        {
        remainder = ((numerator*10)/d);
        
        }
      }
    }
  }
