package amicable;
import java.util.*;
import prime.Eratosthenes;

public class Numbers
  {
  public static void main (String ... args)
    {
    System.out.println(new Numbers().sumOfAmicableNumbers(10_000));
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
      Map<Integer, Integer> primeDivisorsUnderSqrt = getPrimeDivisorsUnderSqrt(number, primes);
      sumOfAllDivisors = findSumOfAllDivisors(number, primeDivisorsUnderSqrt);
      sumOfMultiplesMap.put(number, sumOfAllDivisors);
      }
    return sumOfAllDivisors;
    }
  private Map<Integer, Integer> getPrimeDivisorsUnderSqrt (int number, List<Integer> primes)
    {
    Map<Integer, Integer> primeDivisorsUnderSqrt = new LinkedHashMap<>();
    int primeIndex = 0;
    int result = number;
    while (result != 1 && primes.get(primeIndex) <= (int)Math.sqrt(number))
      {
      int prime = primes.get(primeIndex);
      if (result % prime == 0)
        {
        if(primeDivisorsUnderSqrt.containsKey(prime))
          {
          primeDivisorsUnderSqrt.put(prime, primeDivisorsUnderSqrt.get(prime) + 1);
          }
        else
          {
          primeDivisorsUnderSqrt.put(prime, 1);
          }
        result /= prime; 
        }
      else
        {
        primeIndex++;
        }
      }
    return primeDivisorsUnderSqrt;
    }
  private int findSumOfAllDivisors(int number, Map<Integer, Integer> primeDivisorsUnderSqrt)
    {
    int sqrtNumber = (int)Math.sqrt(number);
    Set<Integer> divisors = new TreeSet<>();
    divisors.add(1);
    for (Map.Entry<Integer, Integer> entry: primeDivisorsUnderSqrt.entrySet())
      {
      Set<Integer> newDivisors = new HashSet<>();
      int prime = entry.getKey();
      int pow = entry.getValue();
      for (int divisor:divisors)
        {
        for (int i = 0;i < pow; i++)
          {
          int newDivisor = divisor*(int)Math.pow(prime, i+1);
          if (newDivisor <= sqrtNumber)
            {
            newDivisors.add(newDivisor);
            }
          else
            {
            break;
            }
          }
        }
      divisors.addAll(newDivisors);
      }
    Set<Integer> biggerDivisors = new HashSet<>();
    for(int divisor:divisors)
      {
      if (divisor != 1)
        {
        biggerDivisors.add(number/divisor);
        }
      }
    divisors.addAll(biggerDivisors);
    int sum = 0;
    for(int divisor:divisors)
      {
      sum += divisor;
      }
    return sum;  
    }
  }
