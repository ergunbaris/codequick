package bitcounter;

public class BitCounter
{
  public static void main(String ... args)
  {
    long number = Long.parseLong(args[0],2);
    System.out.printf("%s has %d one bits%n",args[0],countBits(number));
  }
  public static byte countBits(long number)
  {
    byte count = 0;
    for(;number!=0;number &= (number-1))
    {
      count++;
    }
    return count;
  }
  
}
