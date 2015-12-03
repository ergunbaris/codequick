package almostpi;

//import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class AlmostPi
{
  public static final long PI_UNFLOAT = (long)(Math.PI * Math.pow(10,15));
  public static final int BASE_N = 200;
  public static void main(String...args)
  {
     int N = Integer.parseInt(args[0]);
     long startConstruct = System.nanoTime();
     List<Pair> sumOfPairs = new AlmostPi().constructSums(N);
     long endConstruct = System.nanoTime();
     double time = (endConstruct-startConstruct)/1_000_000_000;
     System.out.printf("construct time=%5.10f%n", time);
     long startAlgo = System.nanoTime();
     System.out.printf("%d %n", new AlmostPi().getAlmostPi(sumOfPairs));
     long endAlgo = System.nanoTime();
     time = (endAlgo-startAlgo)/1_000_000_000;
     System.out.printf("algo time=%5.10f%n", time);

  }
  public List<Pair> constructSums(int N)
  {
  int len = (int)(1.5*BASE_N*N/BASE_N) +1;
  List<Pair> sumOfPairs = new ArrayList<Pair>();
  int ind=0;
  for(int i=0;i<len;i++)
    {
    for(int j=i+1;j<len;j++)
      {
      double sum = f(i,N)+f(j,N);
      if(sum < Math.PI)
        {
        sumOfPairs.add(new Pair(i,j,sum));
        ind++;
        //System.err.printf("ind=%d%n",ind);
        }
      }
    }
  return sumOfPairs;
  }
  public long getAlmostPi(List<Pair> sumOfPairs)
  {
    Collections.sort(sumOfPairs);
    double smallestDiff = -1.0;
    Pair pair1 = null;
    Pair pair2 = null;
    for(int i=0;i<sumOfPairs.size();i++)
      {
      double search = Math.PI - sumOfPairs.get(i).getSum();

      if(search <= 0.0)
        {
          break;
        }
      Pair searchPair = new Pair(-1,-1, search);
      int index = Collections.binarySearch(sumOfPairs, searchPair);
      if(index < 0)
        {
        int insertpoint = 0-(index+1);
        double sumAtInsertionPoint = sumOfPairs.get(i).getSum() + sumOfPairs.get(insertpoint).getSum();
        double diffAtInsertionPoint = Math.abs(sumAtInsertionPoint-Math.PI);
        if(smallestDiff == -1.0 || diffAtInsertionPoint<smallestDiff)
          {
          smallestDiff = diffAtInsertionPoint;
          System.err.printf("smallestDiff=%1.15f%n", smallestDiff);
          pair1 = sumOfPairs.get(i);
          pair2 = sumOfPairs.get(insertpoint);
          System.err.printf("a=%d,b=%d,c=%d,d=%d%n",pair1.getA(), pair1.getB(), pair2.getA(),pair2.getB());
          }
        if(insertpoint != 0)
          {
          double sumAfterInsertionPoint = sumOfPairs.get(i).getSum() + sumOfPairs.get(insertpoint-1).getSum();
          double diffAfterInsertionPoint = Math.abs(sumAfterInsertionPoint-Math.PI);
          if(smallestDiff == -1.0 || diffAfterInsertionPoint<smallestDiff)
            {
            smallestDiff = diffAfterInsertionPoint;
            System.err.printf("smallestDiff=%1.15f%n", smallestDiff);
            pair1 = sumOfPairs.get(i);
            pair2 = sumOfPairs.get(insertpoint-1);
          System.err.printf("a=%d,b=%d,c=%d,d=%d%n",pair1.getA(), pair1.getB(), pair2.getA(),pair2.getB());
            }
          }
        }
       else
        {
        double sumIndex = sumOfPairs.get(i).getSum() + sumOfPairs.get(index).getSum();
        double diffIndex = Math.abs(sumIndex-Math.PI);
        if(smallestDiff == -1.0 || diffIndex<smallestDiff)
          {
          smallestDiff = diffIndex;
          System.err.printf("smallestDiff=%1.15f%n", smallestDiff);
          pair1 = sumOfPairs.get(i);
          pair2 = sumOfPairs.get(index);
          System.err.printf("a=%d,b=%d,c=%d,d=%d%n",pair1.getA(), pair1.getB(), pair2.getA(),pair2.getB());
          }
        }
      }
    System.err.printf("a=%d,b=%d,c=%d,d=%d%n",pair1.getA(), pair1.getB(), pair2.getA(),pair2.getB());
    double result = Math.pow(pair1.getA(),2) + Math.pow(pair1.getB(),2) + 
                    Math.pow(pair2.getA(),2) + Math.pow(pair2.getB(),2);
    return (long)result;
  }

  private double f(double a, double base)
    {
      double expA = a/base;
      return Math.pow(Math.E,expA)-1;
    }
}

class Pair implements Comparable<Pair>
  {
  private final int a;
  private final int b;
  private final double sum;
  Pair(int a, int b, double sum)
    {
      this.a = a; 
      this.b = b; 
      this.sum = sum;
    }
  public int getA()
    {
      return a;
    }
  public int getB()
    {
      return b;
    }
  public double getSum()
    {
      return sum;
    }
  public int compareTo(Pair obj)
    {
    return (new Double(sum)).compareTo(new Double(obj.sum));
    }
  public boolean equals(Object o)
    {
    if(null == o)
      {
      return false;
      }
    if(!(o instanceof Pair))
      {
      return false;
      }
    Pair obj = (Pair)o;
    if(obj.sum != this.sum)
      {
        return false;
      }
    return true;
    }
  public int hashCode()
    {
      return (int)sum;
    }
  }
