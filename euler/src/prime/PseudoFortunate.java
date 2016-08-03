package prime;
import java.util.List;
import java.util.ArrayList;

public class PseudoFortunate
  {
  private final int max;
  private final Eratosthenes sieve;
  private final Integer [] primes;
  private final int biggestPrimeIndex;  

  private long sum = 0;
  public static void main (String ... args)
    {
    int max = Integer.parseInt(args[0]);
    PseudoFortunate pf = new PseudoFortunate(max);
    System.out.println(pf.findSum());
    }
  public PseudoFortunate (int max)
    {
    this.max = max;
    this.sieve = new Eratosthenes(this.max + 2000);
    this.primes = sieve.getPrimeNumbers();
    this.biggestPrimeIndex = findBiggestConseqPrimeIndex(this.max);
    findAdmissibleNumbers(1,
                          0);
    }

  private long findSum()
    {
    return sum;
    }

  private int findBiggestConseqPrimeIndex(int max)
    {
    long result = 1;
    int index = 0;   
    do
      {
      result *= this.primes[index];
      if (result >= this.max)
        {
        break;
        }
      index++;
      }
    while (true);    
    return index--;
    }

  private void findAdmissibleNumbers(final long number,
                                     final int primeIndex
                                    )
    {
    if (number > this.max)
      {
      return;
      }
    if (number > 1)
      {
      System.err.println(number);
      sum += (sieve.getNextPrime((int)number+1) - number);
      }
    for (int i = primeIndex; i <= biggestPrimeIndex; i++)
        {
        if (number == 1 && i > 0 ||
            (i - 1) > primeIndex)
          {
          return;
          }
        long newNumber = number * this.primes[i];
        findAdmissibleNumbers(newNumber,
                              i);
        }
    }
  }
