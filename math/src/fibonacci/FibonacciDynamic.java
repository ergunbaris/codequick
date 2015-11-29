package fibonacci;

public class FibonacciDynamic
  {
  public static void main(String...args)
    {
      int max = Integer.parseInt(args[0]);
      long [] fibSerie = new FibonacciDynamic().getFibonacciSeriesUpto(max);
      printFib(fibSerie);
      long [] fibSerie2 = new FibonacciDynamic().getBottomUpFibonacciSeriesUpto(max);
      printFib(fibSerie2);
      long fibSerieRes = new FibonacciDynamic().getBottomUpFibonacciResult(max);
      System.out.printf("fibonacci for %d is=%d%n",max, fibSerieRes);
    
    }
  public static void printFib(long [] fibSerie)
    {
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
  public long [] getBottomUpFibonacciSeriesUpto(int max)
    {
    long [] fibonaccis = new long[max+1];
    fibonaccis[0] = 0;
    fibonaccis[1] = 1;
    for(int i=2;i<fibonaccis.length;i++)
      {
      fibonaccis[i] = fibonaccis[i-1] + fibonaccis[i-2];
      }
    return fibonaccis;
    }
  public long getBottomUpFibonacciResult(int max)
    {
    if(max == 0 || max == 1)
      {
        return max;
      }
    long a = 0;
    long b = 1;
    for(int i=2;i<max;i++)
      {
      long c = a+b;
      a = b;
      b = c;  
      }
    return a+b;
    }
  }
