package prime;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class PseudoFortunate
  {
  private final int max;
  private final Eratosthenes sieve;
  private final Integer [] primes;
  private final int biggestPrimeIndex;
  private final Set<Long> fortunateNumbers = new HashSet<>();

  private long sum = 0L;
  public static void main (String ... args)
    {
    int max = Integer.parseInt(args[0]);
    PseudoFortunate pf = new PseudoFortunate(max);
    System.out.println(pf.findSum());
    }
  public PseudoFortunate (int max)
    {
    this.max = max;
    this.sieve = new Eratosthenes(this.max + 100);
    this.primes = sieve.getPrimeNumbers();
    this.biggestPrimeIndex = findBiggestConseqPrimeIndex(this.max);
    findDistinctPseudoFortunateNumbers(1,
                          0);    
    }
  private long findSum()
    {
    if (sum != 0L)
      {
      return sum;
      }
    for(long found:fortunateNumbers)
      {
      sum += found;
      }
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
  private void findDistinctPseudoFortunateNumbers(final long number,
                                                  final int primeIndex)
    {
    if (number > this.max)
      {
      return;
      }
    if (number > 1)
      {
      fortunateNumbers.add(findPseudoFortunateNumber(number));
      }
    for (int i = primeIndex; i <= biggestPrimeIndex; i++)
        {
        if (number == 1 && i > 0 ||
            (i - 1) > primeIndex)
          {
          return;
          }
        long newNumber = number * this.primes[i];
        findDistinctPseudoFortunateNumbers(newNumber,
                                           i);
        }
    }
  private long findPseudoFortunateNumber (final long admissibleNumber)
    {
    return sieve.getNextPrime((int)admissibleNumber+1) - admissibleNumber;
    }
  }
