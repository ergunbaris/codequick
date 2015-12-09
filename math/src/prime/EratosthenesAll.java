package prime;

import java.util.List;
import java.util.LinkedList;

public class EratosthenesAll
{
  public static void main (String ... args)
  {
    int n = Integer.parseInt(args[0]);
    System.out.printf("%d th prime is %d%n", n, new EratosthenesAll().getNthPrimeNumber(n));
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
    if(prime < 45000)
    {
      for(int i=prime*prime;i<nonprimes.length;i += prime)
        {
        nonprimes[i] = true;
        }
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
