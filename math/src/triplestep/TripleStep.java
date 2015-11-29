package triplestep;

public class TripleStep
  {
  public static void main(String ... args)
    {
      int steps = Integer.parseInt(args[0]);
      System.out.printf("Number of ways to climb the %d stairs with 1-2-3 steps=%d%n", steps,
                        new TripleStep().countways(steps));
    }
  public long countways(int stairs)
    {
      long [] memo = new long [stairs +1];
      return countways(stairs, memo);
      
    }
  private long countways(int n, long [] memo)
    {
    if(n < 0)
      {
        return 0;
      }
    else if (n == 0)
      {
        memo[n] = 1;
        return 1;
      }
    else if(memo[n] > 0)
      {
        return memo[n];
      }
    else
      {
        memo[n] = countways(n-1, memo) + countways(n-2, memo) + countways(n-3, memo);
      }
    return memo[n];
    }
  }
