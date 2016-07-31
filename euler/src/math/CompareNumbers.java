package math;
import java.util.Arrays;

public class CompareNumbers
  {
  public static void main (String ... args)
    {
    int first = Integer.parseInt(args[0]);
    int second = Integer.parseInt(args[1]);
    System.out.println(isPermutation(first,
                                        second));
    }
  public static boolean isPermutation(int number,
                               int otherNumber)
    {
    int [] decimalCountArray = getDecimalCountArray(number);
    int [] otherDecimalCountArray = getDecimalCountArray(otherNumber);
    return Arrays.equals(decimalCountArray,
                         otherDecimalCountArray);
    }
  private static int [] getDecimalCountArray (int number)
    {
    int [] decimalCountArray = new int[10];
    int result = number;
    while (result != 0)
      {
      int remainder = result % 10;
      decimalCountArray[remainder]++;
      result /= 10;
      }
    return decimalCountArray;
    }
  }

