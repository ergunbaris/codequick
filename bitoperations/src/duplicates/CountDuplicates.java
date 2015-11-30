package duplicates;

public class CountDuplicates
{
  public static void main (String ... args)
    {
      int array [] = {1,32000,3000,3000,19,300,400,19,31999, 50, 20, 3001, 300};
      new CountDuplicates().printDups(array);
    }
  public void printDups (int [] array)
    {
    BitSet bits = new BitSet(32000); // 0 to 32000-1
    for (int i=0;i<array.length;i++)
      {
      // TODO defence against N > 32000?
      int num = array[i];
      int num1 = num -1; // BitSet starts with 0 our 1 <= N <= 32000
      if(bits.get(num1))
        {
        System.out.printf("duplicate value=%d%n" , num);
        }
      else
        {
        bits.set(num1);
        }
      }
    }
}

class BitSet
{
  final int [] words;
  public BitSet(int size)
    {
    words = new int[(size>>5)];
    }
  public void set(int num)
    {
    int wordSet = num>>5;
    int bitCount = num&0x1F;
    words[wordSet] |= (1<<bitCount);
    }
  public boolean get(int num)
    {
    int wordSet = num>>5;
    int bitCount = num&0x1F;
    return (words[wordSet] & (1<<bitCount)) != 0;
    }
}
