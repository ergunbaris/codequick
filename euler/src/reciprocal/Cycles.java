package reciprocal;
import java.util.*;

public class Cycles
  {
  private final ReciprocalNumber [] reciprocalNumbers;
  private int maxCycleIndex = 1;
  public static void main (String ... args)
    {
    int max = Integer.parseInt(args[0]);
    Cycles cycles = new Cycles(max);
    System.out.println(cycles.getMaximumReciprocalNumber());
    cycles.displayAllReciprocals();
    }
  public Cycles(int max)
    {
    reciprocalNumbers = new ReciprocalNumber[max];
    findCycles(reciprocalNumbers);
    }
  public void displayAllReciprocals()
    {
    for (int i = 0; i < reciprocalNumbers.length; i++)
      {
      if (null == reciprocalNumbers[i])
        {
        continue;
        }
      System.out.printf("%d -> %s", i, "0.");
      for (int j = 0; j < reciprocalNumbers[i].fractionals.size(); j++)
        {
        if (reciprocalNumbers[i].reciprocalIndex -1  == j)
          {
          System.out.printf("%s","(");
          }
        System.out.printf("%d", reciprocalNumbers[i].fractionals.get(j));
        }
      System.out.printf("%s%n", ")");
      }
    }
  public int getMaximumReciprocalNumber()
    {
    return maxCycleIndex;
    }
  private void findCycles(ReciprocalNumber [] reciprocalNumbers)
    {
    int maxCycleCnt = 0;
    for (int i = 1; i < reciprocalNumbers.length; i++)
      {
      Map<Integer,Integer> remaindersMap = new HashMap<>();
      Map<Integer,Integer> resultsMap = new HashMap<>();
      int numerator = 1;
      int remainder = 0;
      int result = 0;
      int index = 1;
      LinkedList<Integer> fractionals = null;
      while ((remainder = ((numerator*10)%i)) != 0)
        {
        result = ((numerator*10)/i);
        if (remaindersMap.containsKey(remainder) && 
            fractionals.get(remaindersMap.get(remainder)-1) == result)
          {
          reciprocalNumbers[i] = new ReciprocalNumber(fractionals, 
                                                      remaindersMap.get(remainder));
          int cycleCount = reciprocalNumbers[i].fractionals.size() 
                           - reciprocalNumbers[i].reciprocalIndex + 1;
          if (maxCycleCnt < cycleCount)
            {
            maxCycleCnt = cycleCount;
            this.maxCycleIndex = i;
            }
          break;
          }
        else
          {
          remaindersMap.put(remainder, index);
          index++;
          }
        if (fractionals == null)
          {
          fractionals = new LinkedList<>();
          }
        fractionals.addLast(result);
        numerator = remainder;
        }
      }
    }
  private static class ReciprocalNumber
    {
    private final List<Integer> fractionals;
    private final int reciprocalIndex;
    
    ReciprocalNumber(LinkedList<Integer> fractionals, 
                     int reciprocalIndex)
      {
      this.fractionals = fractionals;
      this.reciprocalIndex = reciprocalIndex;
      }
    }
  }
