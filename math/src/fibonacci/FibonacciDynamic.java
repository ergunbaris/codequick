package fibonacci;

public class FibonacciDynamic
  {
  public static void main(String...args)
    {
      int max = Integer.parseInt(args[0]);
      long [] fibSerie = new FibonacciDynamic().getFibonacciSeriesUpto(max);
      for(int i=0; i<fibSerie.length; i++ )
        {
        if(i == 0)
          {
          System.out.printf("%d", fibSerie[i]);
          }
        else
          {
          System.out.printf(",%d", fibSerie[i]);
          }
        }
        System.out.printf("%n");
    }
  public long [] getFibonacciSeriesUpto(int max)
    {
      long [] fibonaccis = new long[max+1];
      generateFibonacci(fibonaccis, max);
      return fibonaccis;
    }
  private long generateFibonacci(long [] fibonaccis, int nth)
    {
    if(nth == 0 || nth == 1)
      {
        fibonaccis[nth] = nth;
        return nth;
      }
    if(fibonaccis[nth] == 0)
      {
        fibonaccis[nth] = generateFibonacci(fibonaccis,nth-1) + generateFibonacci(fibonaccis,nth-2);
      }
    return fibonaccis[nth];
    }
  }
