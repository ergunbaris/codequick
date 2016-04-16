package prime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class QuadraticPrimes
  {
  
  private static final int EXPECTED_ARG_SIZE = 3;
  private static final String INVALID_ARGUMENT_COUNT = "expected arg size=" + 
                                                        EXPECTED_ARG_SIZE +
                                                       " actual arg size=%d";
  private static final String NMAX_EXCEEDED_MSG = "Predefined nmax value must be higher " + 
                                                  "current nmax=%d";
  private static final String USAGE = "java prime.QuadraticPrimes <a_max> <b_max> <n_max>" +
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
      int a_max = Integer.parseInt(args[0]);
      int b_max = Integer.parseInt(args[1]);
      int n_max = Integer.parseInt(args[2]);
      CoefficientLimits limits = new CoefficientLimits(a_max,
                                     b_max
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
                                                                   limits.a_max,
                                                                   limits.b_max)
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
        tryQuadraticFunc(a,
                         b);
        tryQuadraticFunc(a_minus,
                         b);
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
  
  private void tryQuadraticFunc(int a,
                                int b)
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
                            int a,
                            int b)
    {
    return (n*n) + (a*n) + b;
    }
  
  private static class CoefficientLimits
    {
    // n^2 + an + b
    private final int a_max;
    private final int b_max;
    
    CoefficientLimits(int a_max,
                      int b_max)
      {
      this.a_max = a_max;
      this.b_max = b_max;
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
