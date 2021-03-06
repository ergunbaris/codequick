package prime;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Divisors
  {
  private final Integer [] primes;
  private final Eratosthenes sieve;
  public static void main(String ... args)
    {
    int number = Integer.parseInt(args[0]);
    Eratosthenes sieve = new Eratosthenes(number);
    Divisors divisors = new Divisors(sieve);
    Set<Long> allDivisors = divisors.findAllDivisors(number);
    for(long div:allDivisors)
      {
      System.out.printf("%d,", div);
      }
    System.out.println();
    Map<Long, Integer> primeDivisors = divisors.getPrimeDivisors(number);
    for(Map.Entry<Long, Integer> entry: primeDivisors.entrySet())
      {
      System.out.printf("primeDiv=%d, exp=%d%n",
                        entry.getKey(),
                        entry.getValue());
      }
    System.out.printf("number=%d factor_count=%d%n",
                      number,
                      divisors.findTheNumberOfFactors(primeDivisors));
    Map<Long, Integer> factorialsPrimeDivisors = divisors.getFactorialsPrimeDivisors(number);
    for(Map.Entry<Long, Integer> entry: factorialsPrimeDivisors.entrySet())
      {
      System.out.printf("primeDiv=%d, exp=%d%n",
                        entry.getKey(),
                        entry.getValue());
      }
    }
  public Divisors(Eratosthenes sieve)
    {
    this.sieve = sieve;
    this.primes = this.sieve.getPrimeNumbers();
    }
  public long findSumOfAllDivisors(int number)
    {
    long sum = 0;
    Set<Long> divs = findAllDivisors(number);
    for(long divisor:divs)
      {
      sum += divisor;
      }
    return sum;
    }
  public Set<Long> findAllDivisors(int number)
    {
    Map<Long, Integer> primeDivisorsUnderSqrt = getPrimeDivisors(number);
    int sqrtNumber = (int)Math.sqrt(number);
    Set<Long> divisors = new TreeSet<>();
    divisors.add(1L);
    for (Map.Entry<Long, Integer> entry: primeDivisorsUnderSqrt.entrySet())
      {
      Set<Long> newDivisors = new HashSet<>();
      long prime = entry.getKey();
      int pow = entry.getValue();
      for (long divisor:divisors)
        {
        for (int i = 0;i < pow; i++)
          {
          long newDivisor = divisor*(int)Math.pow(prime, i+1);
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
    Set<Long> biggerDivisors = new HashSet<>();
    for(long divisor:divisors)
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
        if(primeDivisors.containsKey((long)prime))
          {
          primeDivisors.put((long)prime, primeDivisors.get((long)prime) + 1);
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
  public Map<Long, Integer> getFactorialsPrimeDivisors(long number)
    {
    Integer [] primes = new Eratosthenes((int)number + 1).getPrimeNumbers();
    Map<Long, Integer> factorialPrimes = new HashMap<>();
		for (int prime:primes)
        {
        int factorialPrimeExp = 0;
        for (int i = 1; i <= 32; i++)
          {
          long result = number / (long) Math.pow(prime, i);
          if (result == 0)
            {
            break;
            }
          factorialPrimeExp += result;
          }
        if (factorialPrimeExp > 0)
          {
          factorialPrimes.put((long)prime, factorialPrimeExp);
          }
        }
    return factorialPrimes;
    }
  public long findTheNumberOfFactors(Map<Long, Integer> primeDivisors)
    {
    long result = 1;
    for (int exponent:primeDivisors.values())
      {
      result *= (exponent+1);
      }
    return result;
    }
  }
