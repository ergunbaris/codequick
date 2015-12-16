package math;
import java.util.*;
import java.io.*;

public class FirstTenDigits
  {
  public static void main(String...args)
    {
    System.out.println(new FirstTenDigits().findFirstTenDigits(args[0]));
    }

  public long findFirstTenDigits(String filename)
    {
    String [] numbers = readNumbers(filename);
    long currentSum = 0;
    for (int i=50; i>0; i-=10)
      {
      currentSum /= (long) Math.pow(10,10);
      for(String number:numbers)
        {
        long n = Long.parseLong(number.substring(i-10,i));
        currentSum +=n;
        }
      }
    return currentSum;
    }
  public String [] readNumbers(String filename)
    {
    List<String> numbers = new LinkedList<String>();
    File file = new File(filename);
    try {
      Scanner sc = new Scanner(file, "UTF-8");

      while (sc.hasNextLine()) {
        numbers.add(sc.nextLine());
      }
      sc.close();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return numbers.toArray(new String[0]); 
    }
  }

