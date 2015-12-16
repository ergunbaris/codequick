package prime;

import java.util.List;
import java.util.LinkedList;

/*
Retrospective: WALK THROUGH YOUR CODE CAREFULLY
*/

public class Eratosthenes
{
  public static void main (String ... args)
  {
    int n = Integer.parseInt(args[0]);
    List<Integer> primes =  new Eratosthenes().getListOfPrimesUnder(n);
    for(int prime:primes)
      {
      System.out.printf("%d,",prime);
      }
    System.out.println();
    //System.out.printf("%d th prime is %d%n", n, new EratosthenesAll().getNthPrimeNumber(n));
  }
  public List<Integer> getListOfPrimesUnder(int n)
    {
    List<Integer> primes = new LinkedList<>();
    boolean [] nonprimes = new boolean[n+1];
    int prime = 2;
    primes.add(prime);
    int sqrtN = (int)Math.sqrt(n);
    while(prime<=sqrtN)
      {
      flagNonPrimes(nonprimes, prime);
      prime = getNextPrime(nonprimes,prime);
      primes.add(prime);
      }
    for(int i=prime+1;i<n;i++)
      {
      if(!nonprimes[i])
        {
        primes.add(i);
        }
      }
    return primes;
    }
  public int getNthPrimeNumber(int n)
  {
  boolean [] nonprimes = new boolean[n*100];
  int prime = 2;
  int count =1;
  while(prime < n*100 && count !=n )
    {
      flagNonPrimes(nonprimes, prime);
      prime = getNextPrime(nonprimes, prime);
      count++;
    }
  return prime;
  }
  public void flagNonPrimes(boolean [] nonprimes, int prime)
    {
    for(int i=prime*prime;i<nonprimes.length;i += prime)
      {
      nonprimes[i] = true;
      }
    }
  public int getNextPrime(boolean [] nonprimes, int curPrime)
    {
    int next = curPrime;
    for(int i=curPrime+1 ;i<nonprimes.length; i++)
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
