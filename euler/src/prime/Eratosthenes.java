package prime;

import java.util.List;
import java.util.ArrayList;

/*
Retrospective: WALK THROUGH YOUR CODE CAREFULLY
*/

public class Eratosthenes
{
  private final Integer [] primeNumbers;
  private final boolean [] nonprimes;
  public static void main (String ... args)
  {
    int n = Integer.parseInt(args[0]);
    Eratosthenes sieve = new Eratosthenes(n);
    Integer [] primes =  sieve.getPrimeNumbers();
    for(int prime:primes)
      {
      System.out.printf("%d%n",prime);
      }
    //System.out.printf("1st prime number=%d%n", sieve.getNthPrimeNumber(0));
  }
  public Eratosthenes(final int n)
    {
    List<Integer> primes = new ArrayList<>();
    nonprimes = new boolean[n+1];
    int prime = 2;
    primes.add(prime);
    int sqrtN = (int)Math.sqrt(n);
    while(prime<=sqrtN)
      {
      flagNonPrimes(prime);
      prime = getNextPrime(prime);
      primes.add(prime);
      }
    for(int i=prime+1;i<n;i++)
      {
      if(!nonprimes[i])
        {
        primes.add(i);
        }
      }
    primeNumbers = primes.toArray(new Integer[0]);
    }
  public Integer [] getPrimeNumbers()
    {
    return primeNumbers;
    }
  public int getNthPrimeNumber(final int n)
  {
  return primeNumbers[n];
  }
  public void flagNonPrimes(final int prime)
    {
    for(int i=prime*prime;i<nonprimes.length;i += prime)
      {
      nonprimes[i] = true;
      }
    }
  public int getNextPrime(final int number)
    {
    int next = number;
    for(int i = number + 1 ; i < nonprimes.length; i++)
     {
       if(!nonprimes[i])
       {
          next = i;
          break;
       }
     }
    return next;
    }
}
