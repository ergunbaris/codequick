package prime;
import java.util.List;
import java.util.ArrayList;

public class PseudoFortunate
  {
  private final int max;
  private final List<Integer> primes;
  public static void main (String ... args)
    {
    int max = Integer.parseInt(args[0]);
    PseudoFortunate pf = new PseudoFortunate(max);
    pf.findSumOfPFNumbers();
    }
  public PseudoFortunate (int max)
    {
    this.max = max;
    this.primes = new Eratosthenes().getListOfPrimesUnder((int)Math.sqrt(this.max));
    }
  public long findSumOfPFNumbers()
    {
    int biggestConseqPrimeIndex = findBiggestConseqPrimeIndex(this.max);
    System.out.println(biggestConseqPrimeIndex);
    return -1L;
    }
  private int findBiggestConseqPrimeIndex(int max)
    {
    long result = 1;
    int index = 0;   
    do
      {
      result *= this.primes.get(index);
      if (result >= this.max)
        {
        break;
        }
      index++;
      }
    while (true);    
    return index--;
    }
  private List<Integer> findAdmissibleNumbers(int biggestPrimeIndex)
    {
    List<Integer> admissibles = new ArrayList<>();
    for (int pIndex=0; pIndex <= biggestPrimeIndex; pIndex++)
      {
      for (int i = 0; i <= pIndex; i++)
        {
        for (int j = i; i <= j; j++)
          {
          }
        }
      }
    return null;
    }
  }
