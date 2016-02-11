package nonabundant;

import prime.Divisors;
import java.util.*;

public class Numbers
  {
  private final Divisors divisors;
  public static void main(String...args)
    {
    int limit = 28_111;
    int smallestAbundant = 12;
    Numbers nums = new Numbers(new Divisors(limit));
    System.out.println( nums.findSumOfNumbersCantBeExpressedAsSumOfAbundant(limit + smallestAbundant));
    }
  public Numbers(Divisors divisors)
    {
    this.divisors = divisors;
    }
  public int findSumOfNumbersCantBeExpressedAsSumOfAbundant(int limit)
    {
    int sum = 0;
    boolean [] markArray = markNumbersCantBeExpressedAsSumOfAbundant(limit);
    for (int i = 0; i < markArray.length; i++)
      {
      if(!markArray[i])
        {
        sum += i;
        }
      }
    return sum;
    }
  public List<Integer> findNumbersCantBeExpressedAsSumOfAbundant(int limit)
    {
    List<Integer> numbersCannotBeExprAsSumOfAbundant = new ArrayList<>();
    boolean [] markArray = markNumbersCantBeExpressedAsSumOfAbundant(limit);
    for (int i = 0; i < markArray.length; i++)
      {
      if(!markArray[i])
        {
        numbersCannotBeExprAsSumOfAbundant.add(i);
        }
      }
    return numbersCannotBeExprAsSumOfAbundant;
    }
  private boolean [] markNumbersCantBeExpressedAsSumOfAbundant(int limit)
    {
    boolean [] isExpressedAsSumOfTwoAbundant = new boolean[limit+1];
    List<Integer> abundantNums = find(limit);
    for (int i = 0; i < abundantNums.size(); i++)
      {
      for (int j = 0; j < abundantNums.size(); j++)
        {
        int sum = abundantNums.get(i) + abundantNums.get(j);
        if (sum > limit)
          {
          break;
          }
        isExpressedAsSumOfTwoAbundant[sum] = true;
        }
      }
    return isExpressedAsSumOfTwoAbundant;
    }
  public List<Integer> find(int limit)
    {
    List<Integer> abundantNums = new ArrayList<Integer>();
    for (int i = 12; i <= limit ; i++)
      {
      int sum = divisors.findSumOfAllDivisors(i);
      if(sum > i)
        {
        abundantNums.add(i);
        }
      }
    return abundantNums;
    }
  }
