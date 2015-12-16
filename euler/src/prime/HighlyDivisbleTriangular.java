package prime;
import java.util.*;

public class HighlyDivisbleTriangular
  {
  public static void main (String...args)
    {
    int nDivisor = Integer.parseInt(args[0]);
    int startWith = Integer.parseInt(args[1]);
    System.out.println(new HighlyDivisbleTriangular().findFirstTriangular(nDivisor,startWith));
    }
  public int findFirstTriangular(int nDivisor, int startWith)
    {
    if(startWith<1)
      {
      throw new IllegalArgumentException("startWith must be big or equal to1");
      }
    int triNumber = -1;
    List<Integer> primes = new Eratosthenes().getListOfPrimesUnder(10000000);
    int maxIntegerRoot = (int)Math.sqrt(Integer.MAX_VALUE);
    for(int i=startWith;i<maxIntegerRoot;i++)
      {
      int number = i*(i+1)/2;
      int numberOfDivisors = 1;
      while(number != 1)
        {
        for(int prime:primes)
          {
          int count = 1;
          while(number%prime == 0)
            {
            count++;
            number/=prime;
            }
           numberOfDivisors *= count;
          }
        if(number == 1)
          {
          break;
          }
        }
      System.err.printf("numberOfDivisors=%d number=%d%n", numberOfDivisors, i*(i+1)/2);
      if(numberOfDivisors > nDivisor)
        {
        triNumber = i*(i+1)/2;
        break;
        }        
      }
    return triNumber;
    }
  }
