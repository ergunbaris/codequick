package prime;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Divisors
  {
  private final Integer [] primes;
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
    Map<Long, Integer> primeDivisors = divisors.getPrimeDivisors(number);
    System.out.printf("number=%d factor_count=%d%n",
                      number,
                      divisors.findTheNumberOfFactors(primeDivisors));
    }
  public Divisors(int maxLimit)
    {
    primes = new Eratosthenes(maxLimit).getPrimeNumbers();
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
    Map<Long, Integer> primeDivisorsUnderSqrt = getPrimeDivisors(number);
    int sqrtNumber = (int)Math.sqrt(number);
    Set<Integer> divisors = new TreeSet<>();
    divisors.add(1);
    for (Map.Entry<Long, Integer> entry: primeDivisorsUnderSqrt.entrySet())
      {
      Set<Integer> newDivisors = new HashSet<>();
      long prime = entry.getKey();
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
  public Map<Long, Integer> getPrimeDivisors (long number)
    {
    Map<Long, Integer> primeDivisors= new LinkedHashMap<>();
    int primeIndex = 0;
    long result = number;
    while (result != 1)
      {
      int prime = primes[primeIndex];
      if (prime > (int)Math.sqrt(number))
        {
        primeDivisors.put(result, 1);
        break;
        }
      if (result % prime == 0)
        {
        if(primeDivisors.containsKey(prime))
          {
          primeDivisors.put((long)prime, primeDivisors.get(prime) + 1);
          }
        else
          {
          primeDivisors.put((long)prime, 1);
          }
        result /= prime;
        }
      else
        {
        primeIndex++;
        }
      }
    return primeDivisors;
    }
  public int findTheNumberOfFactors(Map<Long, Integer> primeDivisors)
    {
    int result = 1;
    for (int exponent:primeDivisors.values())
      {
      result *= (exponent+1);
      }
    return result;
    }
  }
