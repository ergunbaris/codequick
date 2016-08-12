package prime;

import java.util.Map;
import java.util.HashMap;

public class EnourmousFactorial
  {
  private static final long S0 = 290797L;  
  private static final long S_MOD = 50515093L;

  private final int p;
  private final int q;
  private final long N;
  private final Eratosthenes sieve;
  private final Divisors divisors;
  public static void main (String ... args)
    {
    int p = Integer.parseInt(args[0]);
    int q = Integer.parseInt(args[1]);
    EnourmousFactorial ef = new EnourmousFactorial(p,q);
    long NF = ef.findNF();    
    System.out.println(NF);
    
    }
  public EnourmousFactorial(int p,
                            int q)
    {
    this.p = p;
    this.q = q;
    this.N = findN();
    this.sieve = new Eratosthenes((int)N);
    this.divisors = new Divisors(this.sieve);
    }

  public long findN()
    {
    long result = 0;
    long sn = S0;
    for (int i = 0; i < this.q; i++)
      {
      result += (sn % p) * (long) Math.pow(this.p, i);
      sn = (sn * sn) % 50515093L;
      }
    return result;
    }

   public long findNF()
      {
      long N = findN();
      System.out.println(N);
      Map<Long, Integer> primeDivs = this.divisors.getFactorialsPrimeDivisors(N);
      displayPrimeMap(primeDivs);
      return this.divisors.findTheNumberOfFactors(primeDivs);
      }

    private void displayPrimeMap(Map<Long, Integer> primeMap)
      {
      for(Map.Entry<Long, Integer> entry: primeMap.entrySet())
	{
	System.out.printf("primeDiv=%d, exp=%d%n",
			  entry.getKey(),
			  entry.getValue());
	}

      }
  }
