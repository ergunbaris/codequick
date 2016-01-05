package powerdigit;
import java.util.*;

// Retrospective when using a collection realise it and dont treat as an array
// Realise the difference between .set and .add functions of LinkedList

public class Sum
  {
  public static void main(String...args)
    {
    int pow = Integer.parseInt(args[0]);
    System.out.printf("Sum of digits for 2^%d is %d%n", pow, new Sum().findSumOfDigits(pow));
    }
  public int findSumOfDigits(int power)
    {
    List<Integer> digits = new LinkedList<>();
    int initial = 1;
    digits.add(initial);
    for (int pow=0; pow<power; pow++)
      {
      int overflow = 0;
      for (int i=0; i<digits.size(); i++)
        {
        int dSum = (digits.get(i)*2)+overflow;
        digits.set(i, dSum%10);
        overflow = dSum/10;
        }
      if (overflow>0)
        {
        digits.add(overflow);
        }
      }
    int sum = 0;
    for (int number:digits)
      {
      sum += number;
      }
    return sum;
    }
  }
