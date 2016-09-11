package probability;

import java.util.Set;
import java.util.LinkedHashSet;

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
      Set<Integer> remainingDists = new LinkedHashSet<>();
      Set<Integer> usedDists = new LinkedHashSet<>();
      Set<Integer> remForAddDists = new LinkedHashSet<>();
      for (int j = this.shootCount ; j > 0; j--)
        {
        if (j > i)
          {
          remForAddDists.add(j);
          }
        else if (i != j)
          {
          remainingDists.add(j);
          }
        }
      usedDists.add(i);
      calculateCoeffficients(this.points,
                             i,
                             i,
                             remainingDists,
                             usedDists,
                             remForAddDists);
      }
    }
  
  private void calculateCoeffficients (int coefficientPow,
                                       int curDist,
                                       int curVal,
                                       Set<Integer> remainingDists,
                                       Set<Integer> usedDists,
                                       Set<Integer> remForAddDists)
    {
    if (coefficientPow < 0)
      {
      return;
      }
    if (coefficientPow != this.points &&
        curDist < this.shootCount)
        {
        for (int i = this.shootCount; i > curDist; i--)
          {
          if (!usedDists.contains(i))
            {
            remainingDists.add(i);
            }
          }
        }
    int sum = 0;
    for (int distance:remainingDists)
      {         
      sum += distance;
      }
    for (int distance:remForAddDists)
      {         
      sum += distance;
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
    for (int i = 1; i <= this.shootCount; i++)
      {
      if (!remainingDists.contains(i))
        {
        continue;
        }
      remainingDists.remove(i);
      usedDists.add(i);
      int nextVal = curVal * i;
      calculateCoeffficients(coefficientPow,
                             i,
                             nextVal,
                             remainingDists,
                             usedDists,
                             remForAddDists);
      remainingDists.add(i);
      usedDists.remove(i);
      }
    
    }

  private void displayRemainingDists(Set<Integer> remainingDists)
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
