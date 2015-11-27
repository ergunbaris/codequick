package insertbits;

public class BitInserter
{
  public static void main (String ... args)
  {
      int sample1 = 0b1010_0000_0000_1111;
      int insert = 0b1101;
      int result = insertBits(sample1, insert, 5, 8);
      System.out.printf("result is %s%n", Integer.toBinaryString(result));
  }
  public static int insertBits(int n, int m, int i, int j)
  {
    int leftMask = (1 << (j+1)) -1; // ie 00001111
    int rightMask = ~((1 << i) -1); //  ie 1111100
    int mask = ~(leftMask & rightMask); // ie 11110011
    int nReseted = n & mask;
    int mShifted = m << i;
    return nReseted | mShifted;
  }
}
