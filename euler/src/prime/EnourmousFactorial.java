package prime;

import java.util.Map;

public class EnourmousFactorial
  {
  private static final int S0 = 290797;  
  private static final long S_MOD = 50515093L;

  private final int [] S_CACHE;
  private final int p;
  private final int q;
  private final Divisors divisors;
  private int sCurMaxIndex = 0;
  public static void main (String ... args)
    {
    int p = Integer.parseInt(args[0]);
    int q = Integer.parseInt(args[1]);
    EnourmousFactorial ef = new EnourmousFactorial(p,q);
    long NF = ef.findN();    
    System.out.println(NF);
    
    }
  public EnourmousFactorial(int p,
                            int q)
    {
    this.p = p;
    this.q = q;
    this.divisors = new Divisors((int)Math.pow(p, q/2));
    S_CACHE = new int[q+1];
    S_CACHE[0] = S0;
    
    }
  public long findN()
    {
    long result = 0;
    for (int i = 0; i < this.q; i++)
      {
      result += findTNPow(i) * (long) Math.pow(this.p, i);
      }
    return result;
    }
  private int findTNPow(int n)
    {
    if (S_CACHE[n] != 0)
      {
      return S_CACHE[n] % p;
      }
    for (int i = sCurMaxIndex + 1; i <= n; i++)
      {
      long snPow = (long) Math.pow(S_CACHE[i-1],2);
      S_CACHE[i] = (int) (snPow % S_MOD);
      sCurMaxIndex = i;
      }
    return S_CACHE[sCurMaxIndex] % p;
    }

   public long findNF()
      {
      Map<Long, Integer> primeDivs = this.divisors.getPrimeDivisors(findN());
      
      return this.divisors.findTheNumberOfFactors(primeDivs);
      }
  }
