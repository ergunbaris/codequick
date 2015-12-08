package palindrome;

public class LargestPalindrome
  {
  public static void main(String...args)
    {
      System.out.println(new LargestPalindrome().findLargestPalindrome((int)Math.pow(999,2),((int)Math.pow(100,2))));
      System.out.println(new LargestPalindrome().findLargestPalindromeMath());
    }
  private boolean isPalindrome(int number)
    {
    int reverse = 0;
    int num = number;
    while(num/10!=0 || num%10!=0)
      {
      reverse = reverse*10+(num%10);
      num /= 10;
      }
    return reverse==number;
    }

  public int findLargestPalindrome(int max, int min)
    {
    if(max <= min)
      return -1;
    int minRoot= (int)Math.sqrt(min);
    int maxRoot= (int)Math.sqrt(max);
    for(int i=max;i>=min;i--)
      {
      if(isPalindrome(i))
        {
        int root = (int)Math.sqrt(i);        
        for(int j=root; j>=minRoot;j--)
          {
          if(i/j > maxRoot)
            {
            break;
            }
          if(i%j==0)
            {
            return i;
            }
          }
        }
      }
      return -1;
    }
  public int findLargestPalindromeMath()
    {
    for(int a=9;a>0;a--)
      {
      for(int b=9;b>=0;b--)
        {
        for(int c=9;c>=0;c--)
          {
          int palindrome = 100001*a+10010*b+1100*c;
          if(isMultipleOf3Digit(palindrome))
            {
            return palindrome;
            }
          }
        }
      }
      return -1;
    }
  private boolean isMultipleOf3Digit(int palindrome)
    {
    int root = (int)Math.sqrt(palindrome);
    int minRoot = 100;
    int maxRoot = 999;
    for(int j=root; j>=minRoot;j--)
      {
      if(palindrome/j > maxRoot)
        {
        break;
        }
      if(palindrome%j==0)
        {
        return true;
        }
      }
      return false;
    }
}
