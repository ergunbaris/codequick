package prime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class QuadraticPrimes
  {
  
  private static final int EXPECTED_ARG_SIZE = 5;
  private static final String INVALID_ARGUMENT_COUNT = "expected arg size=" + 
                                                        EXPECTED_ARG_SIZE +
                                                       " actual arg size=%d";
  private static final String NMAX_EXCEEDED_MSG = "Predefined nmax value must be higher " + 
                                                  "current nmax=%d";
  private static final String USAGE = "java prime.QuadraticPrimes <a_max> <a_min> <b_max> <b_min> <n_max>" +
  "where n^2 + a*n +b";

  private int solution = -1;
  private int maxConsequentPrimes = 0;

  private final List<Integer> primes;
  private final Set<Integer> primeSet;
  private final int n_max;
  public static void main(String ... args)
    {
    if (args.length != EXPECTED_ARG_SIZE)
      {
      throw new IllegalArgumentException(String.format(INVALID_ARGUMENT_COUNT, 
                                                       args.length));
      }
    try
      {
      short a_max = Short.parseShort(args[0]);
      short a_min = Short.parseShort(args[1]);
      short b_max = Short.parseShort(args[2]);
      short b_min = Short.parseShort(args[3]);
      short n_max = Short.parseShort(args[4]);
      CoefficientLimits limits = new CoefficientLimits(a_max,
                                     a_min,
                                     b_max,
                                     b_min
                                    );
      QuadraticPrimes qPrimes = new QuadraticPrimes(limits,n_max);
      System.out.printf("solution=%d%n", qPrimes.solution());
      
      }
    catch (NumberFormatException e)
      {
      throw new IllegalArgumentException(USAGE);
      }
    }
  public QuadraticPrimes(CoefficientLimits limits,
                        int n_max)
    {
    primes = new Eratosthenes().getListOfPrimesUnder(quadraticFunc(n_max,
                                                                   (short)limits.a_max,
                                                                   (short)limits.b_max)
                                                                  );
    primes.add(0,1);
    primeSet = new HashSet<>(primes);
    this.n_max = n_max;
    for (int i = 0 ; i < primes.size() ; i++)
      {
      int a = primes.get(i);
      if (a >= limits.a_max)
        {
        break;
        }
      int a_minus = 0 - a;
      for (int j = 0 ; j < primes.size(); j++)
        {
        int b = primes.get(j);
        if (b >= limits.b_max)
          {
          break;
          }
        tryQuadraticFunc((short)a,
                         (short)b);
        tryQuadraticFunc((short)a_minus,
                         (short)b);
        }
      }
    }
  public int solution()
    {
    if (solution == -1)
      {
      throw new NoSolutionException("not solved!");
      }
    return solution;
    }
  
  private void tryQuadraticFunc(short a,
                                short b)
    {
    for (int n = 0; n < this.n_max+1 ; n++)
      {
      if (n == this.n_max)
        {
        throw new NMaxExceededException(String.format(NMAX_EXCEEDED_MSG,
                                                      n));
        }
      int result = quadraticFunc(n,
                                 a,
                                 b
                                );
      if (!primeSet.contains(result))
        {
        if (n > maxConsequentPrimes)
          {
          maxConsequentPrimes = n;
          solution = a*b;
          System.out.printf("a=%d b=%d n=%d solution=%d%n",
                            a,
                            b,
                            n,
                            solution);
          }
        break;
        }
      }
    }

  private int quadraticFunc(int n,
                            short a,
                            short b)
    {
    return (n*n) + (a*n) + b;
    }
  
  private static class CoefficientLimits
    {
    // n^2 + an + b
    private final short a_max;
    private final short a_min;
    private final short b_max;
    private final short b_min;
    
    CoefficientLimits(short a_max,
                      short a_min,
                      short b_max,
                      short b_min)
      {
      this.a_max = a_max;
      this.a_min = a_min;
      this.b_max = b_max;
      this.b_min = b_min;
      }
    }

  private static class NoSolutionException extends RuntimeException
    {
    private static final long serialVersionUID = 100123L;
    NoSolutionException(String message)
      {
      super(message);
      }
    }
  private static class NMaxExceededException extends RuntimeException
    {
    private static final long serialVersionUID = 100124L;
    NMaxExceededException(String message)
      {
      super(message);
      }
    }
  }
