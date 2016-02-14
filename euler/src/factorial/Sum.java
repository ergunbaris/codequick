package factorial;

public class Sum
  {
  private final int [] digits;
  public static void main(String... args)
    {
    int number = Integer.parseInt(args[0]);
    int dSize = Integer.parseInt(args[1]);
    Sum sum = new Sum(dSize);
    sum.displayDigits();    
    System.out.println(sum.findSumOfDigitsOfFactorial(number));
    sum.displayDigits();    
    }

  public Sum (int digitSize)
    {
    digits = new int[digitSize];
    }

  public int findSumOfDigitsOfFactorial (int number)
    {
    processDigits(number);    
    return findSumOfDigits();
    }

  private void processDigits(int number)
    {
    digits[0] = 1;
    for (int i = 2; i <= number;i++)
      {
      for (int j = 0; j < digits.length; j++)
        {
        digits[j] = digits[j]*i;
        }
      reorganizeDigits();
      }
    }

  private void reorganizeDigits()
    {
    for (int j = 0; j < digits.length; j++)
      {
      if(digits[j] > 9)
        {
        if(j+1 > digits.length-1)
          {
          throw new IllegalArgumentException(
          String.format("Need more than %d digits to represent the operation%n", 
                        digits.length));
          }
        digits[j+1] += digits[j]/10;
        digits[j] = digits[j]%10;
        }
      }
    }

  private int findSumOfDigits()
    {
    int sum = 0;
    for (int j = 0; j < digits.length; j++)
      {
      sum += digits[j];
      }
    return sum;
    }  

  public void displayDigits()
    {
    for (int j = digits.length - 1; j >= 0; j--)
      {
      System.out.print(digits[j]);
      }
    System.out.println();
    }  
  }
