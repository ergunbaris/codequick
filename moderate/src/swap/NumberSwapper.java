package swap;

public class NumberSwapper
  {
  public static void main(String...args)
    {
      Pair pair = new Pair(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
      System.out.println(pair);
      new NumberSwapper().swap(pair);
      System.out.println(pair);
    }
  public void swap(Pair pair)
    {
    pair.a += pair.b;
    pair.b = pair.a-pair.b;
    pair.a = pair.a-pair.b;
    }
  }
class Pair
  {
  int a;
  int b;
  Pair(int a, int b)
    {
    this.a = a;
    this.b = b;
    }
  public String toString()
    {
    return String.format("%d %d",a,b);
    }
  }
