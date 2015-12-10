package multiples;

public class SmallestCommonMultiple
{
  public static void main(String...args)
  {
    System.out.println(new SmallestCommonMultiple().findCommonMultipleOfFirst(20));
  }
  public int findCommonMultipleOfFirst(int number)
  {
  int result = 1;
  int [] primes = getPrimesUnder(number);
  for(int prime:primes)
    {
    result *= (int) Math.pow(prime,(int)(Math.log(number)/Math.log(prime)));
    }
  return result;
  }

  private int [] getPrimesUnder(int number)
  {
  // TODO reimplement Erastothones
  int [] sample = {2,3,5,7,11,13,17,19};
  return sample;
  }
}
