package probability;

public class Scoring
  {

  private static final double EXCPECTED_RATIO = 0.02;

  private final double q;
  private final int points;
  private final int shootCount;
  private double probability;
  
  public static void main(String ... args)
    {
    
    double q = Double.parseDouble(args[0]);
    int shootCount = Integer.parseInt(args[1]);
    int points = Integer.parseInt(args[2]);
    Scoring prob = new Scoring(q, shootCount, points);
    double probability = prob.findProbabiltyOfScoring();
    System.out.printf("%13.10f%n", probability);
    
    }

  public Scoring (double q,
                  int shootCount,
                  int points)
    {
    this.q = q;
    this.shootCount = shootCount;
    this.points = points;
    }

  public double findProbabiltyOfScoring()
    {
    probability = 0.0;
    for (int j = 0; j <= this.shootCount - this.points; j++)
      {
      System.out.println(j);
      boolean [] probabilities = new boolean[this.shootCount];
      calculateProbability(probabilities, 0, j);
      if (probability > EXCPECTED_RATIO)
        {
        break;
        }
      }
    return probability;
    }
  private double findScoringProbability (double distance)
    {   
    return 1.0 - (distance / this.q);
    }
  private double findNotScoringProbability (double distance)
    {
    return 1.0 - findScoringProbability(distance);
    }

  private void calculateProbability (boolean [] probabilities,
                                     int totalPoints,
                                     int index)
    {
    probabilities[index] = true;
    totalPoints++;
    if (totalPoints == this.points)
      {
      probability += calculateIncidentProbability(probabilities);
      System.out.printf("%.10f%n", probability);
      return;
      }
    for (int i = index + 1; i < this.shootCount; i++)
      {
      calculateProbability (probabilities,
                            totalPoints,
                            i);
      if (probability > EXCPECTED_RATIO)
        {
        break;
        }
      probabilities[i] = false;
      }
    probabilities[index] = false;

    }

  private double calculateIncidentProbability(boolean [] probabilities)
    {
    double prob = 1.0;
    for (int i = 0; i < this.shootCount; i++)
      {
      int distance = i + 1;
      if (probabilities[i])
        {
        prob *= findScoringProbability(distance);
        }
      else
        {
        prob *= findNotScoringProbability(distance);
        }
      }
    System.err.printf("%5.3f%n", prob);
    return prob;
    }

  }
