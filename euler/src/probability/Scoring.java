package probability;

import java.util.Set;
import java.util.LinkedHashSet;

public class Scoring
  {

  private static final double EXPECTED_RATIO = 0.02;

  private final int points;
  private final int shootCount;
  
  public static void main(String ... args)
    {
    
    int shootCount = Integer.parseInt(args[0]);
    int points = Integer.parseInt(args[1]);
    Scoring prob = new Scoring(shootCount, points);
    //double q = 52.6494571953;
    //System.out.println(prob.findProbabiltyOfScoring(q));
    System.out.printf("%12.10f%n",prob.findQ());
    }

  public double findQ()
    {
    double high = 100.0;
    double low = 50.0;
    double mid = (high + low)/2;
    double res;
    while((res = findProbabiltyOfScoring(mid)) != EXPECTED_RATIO)
      {
      if (res > EXPECTED_RATIO)
        {
        if (mid - low < 0.0000000001)
          {
          break;
          }
        low = mid;        
        }
      else
        {
        if (high - mid < 0.0000000001)
          {
          break;
          }
        high = mid;
        }
      mid = (high + low)/2;
      }
    return mid;
    }

  public Scoring (int shootCount,
                  int points)
    {
    this.shootCount = shootCount;
    this.points = points;
    }  
  

  public double findProbabiltyOfScoring(double q)
    {
    double [][] probTable = new double[this.shootCount+1][this.shootCount+1];
    probTable[0][0] = 1;
    Outer:for (int i = 1; i <= this.shootCount; i++)
      {
      for (int j = 0; j <= i; j++)
        {       
        probTable[i][j] = probTable[i-1][j] * ((double)i/q);
        if (j > 0)
          {
          probTable[i][j] += probTable[i-1][j-1] * (1- ((double)i/q));
          }
        if (i == this.shootCount && 
            j == this.points)
          {
          break Outer;
          }
        }
      }
    return probTable[this.shootCount][this.points];
    }
  }
