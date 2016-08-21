package probability;

import java.util.List;
import java.util.LinkedList;

public class Scoring
  {

  private static final double EXCPECTED_RATIO = 0.02;

  private final double q;
  private final int points;
  private final int shootCount;
  private final int [] probFuncCoefficients;
  
  public static void main(String ... args)
    {
    
    double q = Double.parseDouble(args[0]);
    int shootCount = Integer.parseInt(args[1]);
    int points = Integer.parseInt(args[2]);
    Scoring prob = new Scoring(q, shootCount, points);
    
    }

  public Scoring (double q,
                  int shootCount,
                  int points)
    {
    this.q = q;
    this.shootCount = shootCount;
    this.points = points;
    probFuncCoefficients = new int[this.points+1];
    findProbabiltyFunctionCoefficients();
    displayCoefficients();
    }
  
  public void findProbabiltyFunctionCoefficients()
    {
    for (int i = this.shootCount; i > 1; i--)
      {
      List<Integer> remainingDists = new LinkedList<>();
      for (int j = i ; j > i - this.shootCount; j--)
        {        
        if (i != j)
          {
          int dist = j > 0 ? j : this.shootCount + j;
          remainingDists.add(dist);
          }
        }
      calculateCoeffficients(this.points,
                             i,
                             i,
                             remainingDists);
      }
    }
  
  private void calculateCoeffficients (int coefficientPow,
                                       int curDist,
                                       int curVal,
                                       List<Integer> remainingDists)
    {
    if (coefficientPow < 0)
      {
      return;
      }
    int sum = 0;
    for (int distance:remainingDists)
      {
      if (coefficientPow == this.points)
        {
        if (curDist > distance)
          {
          sum += distance;
          }
        }
        else
        {
        sum += distance;
        }
      }
    displayRemainingDists(remainingDists);
    System.out.printf("pow=%d sum=%d curVal=%d curDist=%d%n", 
                      coefficientPow,
                      sum,
                      curVal,
                      curDist);
    System.out.println();
    probFuncCoefficients[coefficientPow] += curVal * sum;
    coefficientPow--;
    for (int i = 0; i < remainingDists.size(); i++)
      {      
      int nextDistance = remainingDists.remove(i);
      int nextVal = curVal * nextDistance;
      calculateCoeffficients(coefficientPow,
                             nextDistance,
                             nextVal,
                             remainingDists);
      remainingDists.add(i, nextDistance);      
      }
    
    }

  private void displayRemainingDists(List<Integer> remainingDists)
    {
    for (int dist:remainingDists)
      {
      System.out.printf("%d,", dist);
      }
    System.out.println();
    }

  private void displayCoefficients()
    {
    for (int i = probFuncCoefficients.length-1; i >=0; i--)
      {
      System.out.printf("%d * q^%d +", 
                        probFuncCoefficients[i],
                        i);
      }
    System.out.println();
    }

  public double findProbabiltyOfScoring()
    {
    return 0.0;
    }
  }
