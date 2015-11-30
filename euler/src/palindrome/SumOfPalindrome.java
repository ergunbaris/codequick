package palindrome;
import java.math.BigInteger;
/*
   Retrospective:
      - pay attention on all possible cases on different inputs you may encounter
      - Consider if current math operations fit in current data type.
      - try to find most simple and least costly solution for an algo
*/

public class SumOfPalindrome
{
  public static void main(String ... args)
    {
    int number = Integer.parseInt(args[0]);
    SumOfPalindrome pal = new SumOfPalindrome();
    long sumPalindromes = pal.getSumOfPalindromes(number);
    System.out.printf("sum of palindromes is=%d%n", sumPalindromes);

    }

  public int getDigitSize(int number)
    {
    return (int)Math.log10(number) +1;
    }

  public boolean isPalindrome(int number)
    {
    int digitSize = getDigitSize(number);  
    if(digitSize == 1)
      {
      return true;
      }
    for(int i=1,j=digitSize; i<j; i++,j--)
      {
      if((number%(int)Math.pow(10,i))/(int)Math.pow(10,i-1) 
          != (number%(int)Math.pow(10,j))/(int)Math.pow(10,j-1))
        {
        return false;
        }
      }
    return true;
    }

   public long getSumOfPalindromes(int max)
    {
    long result = 0;
    for (int i=1; i<max; i++)
      {
      if(isPalindrome2(i) && isSumOfSquares(i))
        {
          result +=i;
        }
      }
    return result;
    }
   public boolean isPalindrome2(int number)
    {
    int normal = number;
    int reverse = 0;
    while(normal != 0)
      {
        int remainder = normal % 10;
        reverse = reverse*10 + remainder;
        normal = normal/10;
      }
      if(number == reverse)
        {
          return true;
        }
      return false;
    }

    public boolean isSumOfSquares(long number)
      {
        int closestRoot = (int)Math.sqrt(number/2)+1;
        int nextClosestRoot = closestRoot -2;
        while (closestRoot > 1 && nextClosestRoot >-1)
          {
          BigInteger currentSumClosest = getSumOfSquares(closestRoot);
          if(currentSumClosest.compareTo(BigInteger.valueOf(number))<0)
            {
              break;
            }
          BigInteger currentSumNextClosest = getSumOfSquares(nextClosestRoot);
          BigInteger currentSum = currentSumClosest.subtract(currentSumNextClosest);
          if(currentSum.equals(BigInteger.valueOf(number)))
            {
            System.err.printf("number=%d, closestRoot=%d, nextClosestRoot=%d%n",number,closestRoot,nextClosestRoot);
            return true;
            }
          else if(currentSum.compareTo(BigInteger.valueOf(number)) < 0) 
            {
            nextClosestRoot -= 1;
            }
          else
            {
            closestRoot -= 1;
            }
          }
      return false;
      }

    private BigInteger getSumOfSquares(int number)
      {
        BigInteger first = BigInteger.valueOf(number);
        BigInteger second = BigInteger.valueOf(number+1);
        BigInteger third = BigInteger.valueOf(2*number+1);
        BigInteger result = first.multiply(second).multiply(third);
        result = result.divide(BigInteger.valueOf(6));
        return result;
      }
}
