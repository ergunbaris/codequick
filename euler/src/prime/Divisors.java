package prime;
import java.util.*;

public class Divisors
  {
  private final List<Integer> primes;
  public static void main(String ... args)
    {
    int number = Integer.parseInt(args[0]);
    Divisors divisors = new Divisors(number);
    Set<Integer> allDivisors = divisors.findAllDivisors(number);
    for(int div:allDivisors)
      {
      System.out.printf("%d,", div);
      }
    System.out.println();
    }
  public Divisors(int maxLimit)
    {
    primes = new Eratosthenes().getListOfPrimesUnder((int)Math.sqrt(maxLimit)+100);
    }
  public int findSumOfAllDivisors(int number)
    {
    int sum = 0;
    Set<Integer> divs = findAllDivisors(number);
    for(int divisor:divs)
      {
      sum += divisor;
      }
    return sum;
    }
  public Set<Integer> findAllDivisors(int number)
    {
    Map<Integer, Integer> primeDivisorsUnderSqrt = getPrimeDivisorsUnderSqrt(number);
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
    return divisors;
    }
  private Map<Integer, Integer> getPrimeDivisorsUnderSqrt (int number)
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
  }
