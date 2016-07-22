package almostpi;

//import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class AlmostPi
{
  private List<Pair> sumOfPairs =new ArrayList<Pair>();
  public static void main(String...args)
    {
     int N = Integer.parseInt(args[0]);
     long startConstruct = System.nanoTime();
     AlmostPi pi = new AlmostPi(N);
     long endConstruct = System.nanoTime();
     double time = (endConstruct - startConstruct)/1_000_000;
     System.out.printf("construct time=%5.10f%n", time);
     long startAlgo = System.nanoTime();
     System.out.printf("%d %n", pi.getAlmostPi());
     long endAlgo = System.nanoTime();
     time = (endAlgo-startAlgo)/1_000_000;
     System.out.printf("algo time=%5.10f%n", time);
    }

  public AlmostPi(int N)
    {
    double [] fnArray = calculateFnArray(N);
    calculateSumOfPairs(fnArray);
    }
  private double [] calculateFnArray(int N)
    {
    int margin = ((int)(1.425 * N)) + 1;
    double [] fnArray = new double[margin];
    for (int i = 0; i < margin; i++)
      {
      double result = f(i,N);
      if (result >= Math.PI)
        {
        break;
        }
      fnArray[i] = result;
      }
    return fnArray;
    }
  public void calculateSumOfPairs(double [] fnArray)
    {
    for(int i=0; i < fnArray.length; i++)
      {
      for(int j=i+1; j < fnArray.length; j++)
        {
        double sum = fnArray[i] + fnArray[j];
        if(sum < Math.PI)
          {
          sumOfPairs.add(new Pair(i,j,sum));
          }
        else
          {
          break;
          }
        }
      }
    }
  public long getAlmostPi()
    {
    Collections.sort(sumOfPairs);
    double smallestDiff = -1.0;
    Pair pair1 = null;
    Pair pair2 = null;
    int numberOfPairs = sumOfPairs.size();
    for(int i=0; i < numberOfPairs; i++)
      {
      double search = Math.PI - sumOfPairs.get(i).getSum();
      Pair searchPair = new Pair(-1,-1, search);
      int index = Collections.binarySearch(sumOfPairs, searchPair);
      if(index < 0)
        {
        int insertpoint = 0-(index+1);
        int biggerPairIndex = insertpoint;
        int smallerPairIndex = insertpoint - 1;
        double sumWithBiggerPair = Double.POSITIVE_INFINITY;
        double sumWithSmallerPair = Double.POSITIVE_INFINITY;
        if (biggerPairIndex < numberOfPairs)
          {
          sumWithBiggerPair = sumOfPairs.get(i).getSum() + sumOfPairs.get(biggerPairIndex).getSum();
          }
        if (smallerPairIndex >= 0)
          {
          sumWithSmallerPair = sumOfPairs.get(i).getSum() + sumOfPairs.get(smallerPairIndex).getSum();
          }
        double diffWithBiggerPair = Math.abs(sumWithBiggerPair - Math.PI);
        double diffWithSmallerPair = Math.abs(sumWithSmallerPair - Math.PI);
        double diff = Double.POSITIVE_INFINITY;
        int pairIndex = -1;
        if (diffWithBiggerPair <= diffWithSmallerPair)
          {
          diff = diffWithBiggerPair;
          pairIndex = biggerPairIndex;
          }
        else
          {
          diff = diffWithSmallerPair;
          pairIndex = smallerPairIndex;
          }
        if(smallestDiff == -1.0 || diff < smallestDiff)
          {
          smallestDiff = diff;
          System.err.printf("smallestDiff=%1.15f%n", smallestDiff);
          pair1 = sumOfPairs.get(i);
          pair2 = sumOfPairs.get(pairIndex);
          System.err.printf("a=%d,b=%d,c=%d,d=%d%n",pair1.getA(), pair1.getB(), pair2.getA(),pair2.getB());
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
    double expA = a / base;
    return Math.pow(Math.E, expA)-1;
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
