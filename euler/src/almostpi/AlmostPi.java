package almostpi;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class AlmostPi
{
  private final List<Pair> sumOfPairs =new ArrayList<Pair>();

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
    double [] fnArray = constructFnArray(N);
    constructSumOfPairs(fnArray);
    Collections.sort(sumOfPairs);
    }

  private double [] constructFnArray(int N)
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

  private void constructSumOfPairs(double [] fnArray)
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
    double smallestDiff = Double.POSITIVE_INFINITY;
    Pair pair1 = null;
    Pair pair2 = null;
    int numberOfPairs = sumOfPairs.size();
    for(int i=0; i < numberOfPairs; i++)
      {
      Pair pair = sumOfPairs.get(i);
      Pair otherPair = findComplementaryPair(pair);
      double sum = pair.getSum() + otherPair.getSum();
      double diff = piDiff(sum);
      if (diff < smallestDiff)
        {
        smallestDiff = diff;
        System.err.printf("smallestDiff=%1.15f%n", smallestDiff);
        pair1 = pair;
        pair2 = otherPair;
        System.err.printf("a=%d,b=%d,c=%d,d=%d%n",
                          pair.getA(), 
                          pair.getB(), 
                          otherPair.getA(),
                          otherPair.getB()
                         );
        }
      }
    return g(pair1.getA(),
             pair1.getB(),
             pair2.getA(),
             pair2.getB());
    }

  private Pair findComplementaryPair (final Pair pair)
    {
    double search = Math.PI - pair.getSum();
    Pair virtualSearchPair = new Pair(-1,-1, search);
    int index = Collections.binarySearch(sumOfPairs, virtualSearchPair);
    if(index < 0)
      {
      int insertpoint = 0-(index+1);
      int biggerPairIndex = insertpoint;
      int smallerPairIndex = insertpoint - 1;
      double sumWithBiggerPair = Double.POSITIVE_INFINITY;
      double sumWithSmallerPair = Double.POSITIVE_INFINITY;
      if (biggerPairIndex < sumOfPairs.size())
        {
        sumWithBiggerPair = pair.getSum() + sumOfPairs.get(biggerPairIndex).getSum();
        }
      if (smallerPairIndex >= 0)
        {
        sumWithSmallerPair = pair.getSum() + sumOfPairs.get(smallerPairIndex).getSum();
        }
      double diffWithBiggerPair = Math.abs(sumWithBiggerPair - Math.PI);
      double diffWithSmallerPair = Math.abs(sumWithSmallerPair - Math.PI);
      if (diffWithBiggerPair <= diffWithSmallerPair)
        {
        return sumOfPairs.get(biggerPairIndex);
        }
      else
        {
        return sumOfPairs.get(smallerPairIndex);
        }
      }
     else
      {
      return sumOfPairs.get(index);
      }
    }

  private double piDiff (double sum)
    {
     return Math.abs(sum - Math.PI);
    }
  private double f(double a, double base)
    {
    double expA = a / base;
    return Math.pow(Math.E, expA)-1;
    }
  private long g (int a, 
                  int b,
                  int c,
                  int d)
    {
    return (long)(Math.pow(a,2) +
                  Math.pow(b,2) +
                  Math.pow(c,2) +
                  Math.pow(d,2)
                 );
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
    if (null == o)
      {
      return false;
      }
    if (!(o instanceof Pair))
      {
      return false;
      }
    Pair obj = (Pair)o;
    if (obj.sum != this.sum ||
        obj.a != this.a ||
        obj.b != this.b)
      {
      return false;
      }
    return true;
    }
  public int hashCode()
    {
      final int prime = 31;
      int result = 1;
      result = prime * result + a;
      result = prime * result + b;
      result = prime * result + Double.valueOf(sum).hashCode();
      return result;
    }
  }
