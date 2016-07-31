package totient;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import prime.Divisors;
import math.CompareNumbers;

public class TotientPermutation
  {
  private int minRatioTotientPermutation = -1;
  public static void main (String ... args)
    {
    final int limit = Integer.parseInt(args[0]);
    Divisors primeDivisors = new Divisors(limit);
    TotientPermutation tp = new TotientPermutation(primeDivisors,
                                                   limit);
    System.out.printf("min ratio totient permutation=%d, max limit=%d%n",
                       tp.getMinRatioTotientPermutaion(),
                       limit);
    }
  public TotientPermutation(Divisors primeDivisors,
                            int limit)
    {
    double minRatio = Double.POSITIVE_INFINITY;
    for (int i = 2; i < limit; i++)
      {
      Set<Integer> pDivisors = primeDivisors.getPrimeDivisors(i).keySet();
      if (pDivisors.size() == 0)
          {
          pDivisors = new HashSet<>();
          pDivisors.add(i);
          }
      int phiRes = phi(i,
                       pDivisors);
      if (CompareNumbers.isPermutation2(i, phiRes))
        {
        double ratio = (double) i/ (double)phiRes;
        if (ratio < minRatio)
          {
          minRatioTotientPermutation = i;
          minRatio = ratio;
          }
        }
      }
    }
  public int getMinRatioTotientPermutaion()
    {
    return minRatioTotientPermutation;
    }
  private int phi(int number,
                  Set<Integer> primes)
    {
    int result = number;
    for(Integer prime: primes)
      {
      result /= prime;
      result *= (prime - 1);
      }
    return result;
    }
  }
