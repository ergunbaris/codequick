package palindrome;

public class SumOfPalindrome
{
  public static void main(String ... args)
    {
    int number = Integer.parseInt(args[0]);
    SumOfPalindrome pal = new SumOfPalindrome();
    System.out.printf("number=%d digitSize=%d%n", number, pal.getDigitSize(number));
    System.out.printf("number=%d palindrom=%b%n", number, pal.isPalindrome(number));
    System.out.printf("number=%d palindrom=%b%n", number, pal.isPalindrome2(number));
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
}
