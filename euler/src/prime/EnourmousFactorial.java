package prime;

import java.util.Map;
import java.util.HashMap;

/* Retrospective
- Learn math trick behind number of p prime factors of two numbers or factorial of number? can be added up
- Never trust Math.pow because of rounding errors.
*/

public class EnourmousFactorial
  {
  private static final long S0 = 290797L;  
  private static final long S_MOD = 50515093L;

  private final int p;
  private final int q;
  private final int m;
  private final long modN;
  private final Map<Integer, Long> powerCache = new HashMap<>();
  public static void main (String ... args)
    {
    int p = Integer.parseInt(args[0]);
    int q = Integer.parseInt(args[1]);
    int m = Integer.parseInt(args[2]);
    EnourmousFactorial ef = new EnourmousFactorial(p,q,m);
    long NF = ef.findNF();    
    System.out.println(NF);
    
    }
  public EnourmousFactorial(int p,
                            int q,
                            int m)
    {
    this.p = p;
    this.q = q;
    this.m = m;
    modN = findPow(p, m);
    }

  public long findNF()
    {
    long numOfFactors = 0;
    long sn = S0;
    for (int i = 0; i <= this.q; i++)
      {
      int pow = i;
      if (i > this.m)
        {
        pow = this.m;
        }
      long Ti = sn % p;
      sn = (sn * sn) % S_MOD;      

      if (Ti == 0)
        {
        continue;
        }
      numOfFactors += findNumOfFactors(Ti, pow);
      if (numOfFactors >= modN)
        {
        numOfFactors %= modN;
        }
      }
    return numOfFactors % modN;
    }

   public long findNumOfFactors(long Ti,
                                int pPow)
      {
      long numFactors = 0;
      for (int i = pPow - 1; i >= 0; i--)
        {
        numFactors += (Ti * findPow(p, i));
        if (numFactors >= modN)
          {
          numFactors %= modN;
          }
        }
      return numFactors % modN;
      }
    private long findPow(long number,
                         int pow)
      {
      if (powerCache.containsKey(pow))
        {
        return powerCache.get(pow);
        }
      long res = 1;
      for (int i = 0; i < pow; i++)
        {
        res *= number;
        }
      powerCache.put(pow, res);
      return res;
      }

  }
