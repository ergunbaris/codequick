package prime;

import java.util.Map;
import java.util.HashMap;

public class EnourmousFactorial
  {
  private static final long S0 = 290797L;  
  private static final long S_MOD = 50515093L;

  private final int p;
  private final int q;
  private final int mod;
  private final Divisors divisors;
  public static void main (String ... args)
    {
    int p = Integer.parseInt(args[0]);
    int q = Integer.parseInt(args[1]);
    int mod = Integer.parseInt(args[2]);
    EnourmousFactorial ef = new EnourmousFactorial(p,q,mod);
    long NF = ef.findNF();    
    System.out.println(NF);
    
    }
  public EnourmousFactorial(int p,
                            int q,
                            int mod)
    {
    this.p = p;
    this.q = q;
    this.mod = mod;
    this.divisors = new Divisors(100_000_000);
    
    }
  public long findN()
    {
    long result = 0;
    long sn = S0;
    for (int i = 0; i < this.mod; i++)
      {
      result += (sn % p) * (long) Math.pow(this.p, i);
      sn = (sn * sn) % 50515093L;
      }
    return result;
    }

   public long findNF()
      {
      long N = findN();
      Map<Long, Integer> primeDivs = this.divisors.getPrimeDivisors(N);
      Map<Long, Integer> primeDivsOfFactorial = new HashMap<>();
      for (Map.Entry<Long,Integer> entry:primeDivs.entrySet())
        {
        int factorialPrimeExp = 0;
        final long prime = entry.getKey();
        final int primeExp = entry.getValue();
        for (int i = 1; i <= primeExp; i++)
          {
          factorialPrimeExp += N / (long) Math.pow(prime, i);
          }
        primeDivsOfFactorial.put(prime, factorialPrimeExp);
        }
      displayPrimeMap(primeDivsOfFactorial);
      return this.divisors.findTheNumberOfFactors(primeDivsOfFactorial);
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
