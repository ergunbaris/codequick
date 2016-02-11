package amicable;
import java.util.*;
import prime.Eratosthenes;
import prime.Divisors;

public class Numbers
  {
  private final Divisors divisors;
  public static void main (String ... args)
    {
    int limit = Integer.parseInt(args[0]);
    Divisors divisors = new Divisors(limit);
    System.out.println(new Numbers(divisors).sumOfAmicableNumbers(limit));
    }

  public Numbers(Divisors divisors)
    {
    this.divisors = divisors;
    }

  public int sumOfAmicableNumbers(int limit)
    {
    Map<Integer, Integer> sumOfMultiplesMap = new HashMap<>();
    List<Integer> primes = new Eratosthenes().getListOfPrimesUnder((int)Math.sqrt(limit)+100);
    int sum = 0;
    for (int i = 2;i < limit; i++)
      {
      int sumOfAllDivisors = findSumOfDivisors(i, sumOfMultiplesMap, primes);
      if(sumOfAllDivisors == 1 || sumOfAllDivisors == i)
        {
        continue;
        }
      if (sumOfAllDivisors <= limit)
        {
        int sumOfAllDivisorsNext = findSumOfDivisors(sumOfAllDivisors, sumOfMultiplesMap, primes);
        if (i == sumOfAllDivisorsNext)
          {
          sum += i;
          }
        }
      }
    return sum;
    }
  private int findSumOfDivisors(int number, Map<Integer, Integer> sumOfMultiplesMap, List<Integer> primes)
    {
    int sumOfAllDivisors = 0;
    if (sumOfMultiplesMap.containsKey(number))
      {
      sumOfAllDivisors = sumOfMultiplesMap.get(number);
      }
    else
      {
      Set<Integer> divs = divisors.findAllDivisors(number);
      sumOfAllDivisors = findSumOfAllDivisors(divs);
      sumOfMultiplesMap.put(number, sumOfAllDivisors);
      }
    return sumOfAllDivisors;
    }
  private int findSumOfAllDivisors(Set<Integer> divs)
    {
    int sum = 0;
    for(int divisor:divs)
      {
      sum += divisor;
      }
    return sum;  
    }
  }
