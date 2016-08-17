package probability;

public class Scoring
  {

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
    for (int j = 0; j < this.shootCount - this.points; j++)
      {
      boolean [] probabilities = new boolean[this.shootCount];
      for (int i = j; i < this.shootCount; i++)
        {
        calculateProbability(probabilities, 0, i);
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
    if (totalPoints == this.points)
      {
      probability += calculateIncidentProbability(probabilities);
      return;
      }
    probabilities[index] = true;
    totalPoints++;
    for (int i = index + 1; i < this.shootCount; i++)
      {
      calculateProbability (probabilities,
                            totalPoints,
                            i);
      totalPoints--;
      }
    }

  private double calculateIncidentProbability(boolean [] probabilities)
    {
    double prob = 0.0;
    for (int i = 0; i < this.shootCount; i++)
      {
      int distance = i + 1;
      if (probabilities[i])
        {
        prob += findScoringProbability(distance);
        }
      else
        {
        prob += findNotScoringProbability(distance);
        }
      }
    return prob;
    }

  }
