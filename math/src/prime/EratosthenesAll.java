package prime;

import java.util.List;
import java.util.LinkedList;

/*
Retrospective: WALK THROUGH YOUR CODE CAREFULLY
*/

public class EratosthenesAll
{
  public static void main (String ... args)
  {
    int n = Integer.parseInt(args[0]);
    System.out.printf("sum of prime under %d is %d%n", n, new EratosthenesAll().findSumOfPrimesUnder(n));
    //System.out.printf("%d th prime is %d%n", n, new EratosthenesAll().getNthPrimeNumber(n));
  }
  public long findSumOfPrimesUnder(int n)
  {
  long sum = 0L;
  boolean [] nonprimes = new boolean[n+1];
  int prime = 2;
  sum += prime;
  int sqrtN = (int)Math.sqrt(n);
  while(prime<=sqrtN)
    {
    flagNonPrimes(nonprimes, prime);
    prime = getNextPrime(nonprimes,prime);
    sum += prime;
    }
  for(int i=prime+1;i<n;i++)
    {
    if(!nonprimes[i])
      {
      sum+=i;
      }
    }
    return sum;
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
