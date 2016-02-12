package lexicographic;
import java.util.*;

public class Permutations
  {
  public static void main(String...args)    
    {
    if (args.length != 1)
      {
      throw new IllegalArgumentException("java Permutations <permutation order as integer>");
      }
    int nth = Integer.parseInt(args[0]) - 1;
    // 1 based input to 0 based algorithm
    if (nth < 0)
      {
      throw new IllegalArgumentException("permutation order cannot be smalle than 1");
      }
    Character [] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    long perm = new Permutations().findNthLexicographicPermutation(nth, digits);
    System.out.println(perm);
    }
  public long findNthLexicographicPermutation(long n, Character [] digits)
    {
    long retval = 0L;
    Arrays.sort(digits);
    List<Character> charList = new ArrayList<Character>(Arrays.asList(digits));
    while (n != 0L)
      {
      int digitSize = charList.size()-1;
      long fact = findFactorial(digitSize);
      int charIndex = (int) (n / fact);
      if (charIndex > charList.size()-1)
        {
        throw new IllegalArgumentException(String.format("%d permutation does not exist %n",n+1));
        // n+1 because problem ordering is 1 based but algorithm is 0 based.
        }
      int digitValue = getDigitIntValue(charList.remove(charIndex));
      retval += digitValue * (long)Math.pow(10,digitSize);
      n = n % fact;
      }
    while (charList.size() > 0)
      {
      int digitSize = charList.size()-1;
      int digitValue = getDigitIntValue(charList.remove(0));
      retval += digitValue * (int)Math.pow(10,digitSize);
      }
    return retval;
    }
  private int getDigitIntValue(char digit)
    {
    if (digit < '0' || digit > '9')
      {
      throw new IllegalArgumentException("Unknown numeric digit value");
      }
    return digit - '0';
    }
  private int findFactorial(int number)
    {
    int fact = 1;
    while (number > 0)
      {
      fact *= number--;
      }
    return fact;
    }
  }
