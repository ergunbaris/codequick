package multiples;

public class OfThreeAndFive
{
public static void main(String ... args)
  {
  int max = Integer.parseInt(args[0]);
  System.out.printf("sum is=%d%n", new OfThreeAndFive().findSum(max));
  System.out.printf("sum is=%d%n", new OfThreeAndFive().findSumWiser(max));
  
  }
public int findSum(int max)
  {
  int count = 0;
  for(int i=1;i<max;i++)
    {
    if(i%3==0 || i%5==0)
      {
        count += i;
      }
    }
  return count;
  }
public int findSumWiser(int max)
  {
    return sumOfDivisableBy(max,3) + sumOfDivisableBy(max,5) - sumOfDivisableBy(max,15);
  }
private int sumOfDivisableBy(int N, int dividend)
  {
    int times = (N-1)/dividend;
    int sum = dividend*times*(times+1)/2;
    return sum;
  }
  
}

