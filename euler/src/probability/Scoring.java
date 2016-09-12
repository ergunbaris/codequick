package probability;

import java.util.Set;
import java.util.LinkedHashSet;

public class Scoring
  {

  private static final double EXCPECTED_RATIO = 0.02;

  private final double q;
  private final int points;
  private final int shootCount;
  private final long [] probFuncCoefficients;
  
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
    probFuncCoefficients = new long[this.points+1];
    findProbabiltyFunctionCoefficients();
    processCoefficients();
    displayCoefficients();
    }
  
  public void findProbabiltyFunctionCoefficients()
    {
    for (int i = this.shootCount; i > 1; i--)
      {
      Set<Integer> usedDists = new LinkedHashSet<>();
      usedDists.add(i);
      calculateCoeffficients(this.points,
                             i,
                             usedDists);
      }
    }
  
  private void calculateCoeffficients (int coefficientPow,
                                       int curDist,
                                       Set<Integer> usedDists)
    {
    if (coefficientPow < 0)
      {
      return;
      }
    System.out.printf("pow=%d curDist=%d", 
                      coefficientPow,
                      curDist);
    System.out.printf(" multiples=(");
    int multiples = 1;
    for (int multiple:usedDists)
      {
      System.out.printf("%d,",multiple);
      multiples *= multiple;
      }
    System.out.printf(") sums=(");
    int sum = 0;
    for (int i = 1; i <= this.shootCount; i++)
      {
      if (coefficientPow == this.points &&
          i > curDist)
        {
        continue;
        }
      if (usedDists.contains(i))
        {
        continue;
        }
      System.out.printf("%d,",i);
      sum += i;
      }
    System.out.printf(")%n");
    System.out.println();
    probFuncCoefficients[coefficientPow] +=  multiples * sum;
    coefficientPow--;
    for (int i = 1; i <= this.shootCount; i++)
      {
      if (usedDists.contains(i))
        {
        continue;
        }
      if (coefficientPow == this.points - 1 &&
          i > curDist)
        {
        continue;
        }
      usedDists.add(i);
      calculateCoeffficients(coefficientPow,
                             i,
                             usedDists);
      usedDists.remove(i);
      }
    
    }

  private void processCoefficients()
    {
    for (int i = probFuncCoefficients.length-1; i >=0; i--)
      {
      probFuncCoefficients[i] = probFuncCoefficients[i] / findFactorial(this.points - i);
      }
    }

  private long findFactorial(int number)
    {
    long factorial = 1;
    for (int i = 1; i <= number; i++)
      {
      factorial *= i;
      }
    return factorial;
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
