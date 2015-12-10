package phytagorean;

public class Triplet
  {
  public static void main(String...args)
    {
    System.out.println(new Triplet().productOfTriple());
    }
  public int productOfTriple()
    {
    // from m^2+n^2+2*m*n+m^2-n^2 = 1000;
    // m(m+n) = 500, m>n
    for(int n=1;n<498;n++)
      {
      for(int m=n+1;m<499;m++)
        {
        if(validNumbers(m,n))
          {
          int a = (int)(Math.pow(m,2) + Math.pow(n,2));
          int b = (int)(Math.pow(m,2) - Math.pow(n,2));
          int c = 2*m*n;
          return a*b*c;
          }
        }
      }
    return -1;
    }
  private boolean validNumbers(int m, int n)
    {
    return m*(m+n) == 500;
    }
  }
