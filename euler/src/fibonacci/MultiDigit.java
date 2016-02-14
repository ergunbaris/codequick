package fibonacci;

public class MultiDigit
  {
  private final int [] digits;
  public static void main (String ... args)
    {
    int dSize = Integer.parseInt(args[0]);
    MultiDigit mDigit = new MultiDigit(dSize);
    mDigit.displayNumber();
    System.out.println(mDigit.findFirstMaxDigitNumberIndex());
    mDigit.displayNumber();
    }
  public MultiDigit(int digitSize)    
    {
    if (digitSize < 1)
      {
      throw new IllegalArgumentException("digitsize must be at least one");
      }
    digits = new int[digitSize];
    digits[0] = 1;
    }
  public int findFirstMaxDigitNumberIndex()
    {
    int [] prevNumber = new int[digits.length];
    prevNumber[0] = 1;
    int index = 2;
    while (digits[digits.length-1] == 0)
      {
      addPreviousNumber(prevNumber);
      index++;
      }
    return index;
    }
  private void addPreviousNumber (int [] prevDigits)
    {
    int [] curDigits = new int [digits.length];
    copyNumber(digits, curDigits);
    for (int i = 0; i < digits.length; i++)
      {
      digits[i] += prevDigits[i];
      }
    rectifyDigits(digits);
    copyNumber(curDigits, prevDigits);
    }
  private void rectifyDigits(int [] digits)
    {
    for (int i = 0; i < digits.length; i++)
      {
      if(digits[i] > 9)
        {
        if (i+1 >= digits.length)
          {
          throw new IllegalArgumentException("not enough digits to represent the number");
          }
        digits[i+1] += digits[i] / 10;
        digits[i] = digits[i] % 10;
        }
      }
    }
  private void copyNumber (int [] number, int [] copy)
    {
    if (number.length != copy.length)
      {
      throw new IllegalArgumentException("two numbers must have same number of digits");
      }
    for (int i = 0; i < number.length; i++)
      {
      copy[i] = number[i];
      }
    }
  public void displayNumber()
    {
    boolean start = false;
    for (int i = digits.length-1; i >= 0;i--)
      {
      if(digits[i] != 0)
        {
        start = true;
        }
      if(start)
        {
        System.out.print(digits[i]);
        }
      }
    System.out.println();
    }
  }
