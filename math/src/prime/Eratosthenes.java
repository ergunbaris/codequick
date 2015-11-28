package prime;

import java.util.List;
import java.util.LinkedList;

public class Eratosthenes
{
  public static void main (String ... args)
  {
    int max = Integer.parseInt(args[0]);
    List<Integer> primes = new Eratosthenes().getPrimeNumbers(max);
    for(int prime:primes)
    {
      System.out.printf("%d,", prime);
    }
  }

  public List<Integer> getPrimeNumbers(int max)
  {
    List<Integer> primes = new LinkedList<Integer>();
    boolean [] nonprimes = new boolean[max/2];
    double sqrtMax = Math.sqrt(max);
    int prime = 2;
    primes.add(2);
    while(prime <= sqrtMax)
      {
        flagNonPrimes(nonprimes, prime);
        prime = getNextPrime(nonprimes, prime);
        
      }
    for(int i=0; i<nonprimes.length;i++)
    {
      if(!nonprimes[i])
      {
        int primeFound = i*2+3;
        if(primeFound <= max)
        {
          primes.add(primeFound);
        }
      }
    }
    return primes;    
  }
  public void flagNonPrimes(boolean [] nonprimes, int prime)
  {
    if((prime&1) == 1){
      for(int i=prime*prime;i<nonprimes.length*2;i += 2*prime)
      {
        nonprimes[(i-3)/2] = true;
      }
    }
  }
  public int getNextPrime(boolean [] nonprimes, int curPrime)
  {
     int next = curPrime;
     for(int i=(curPrime-3)/2 +1 ;i<nonprimes.length/2 +1; i++)
     {
       if(!nonprimes[i])
       {
          next = i*2+3;
          break;
       }
     }
     return next;
  }
}
