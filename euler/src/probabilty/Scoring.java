package probabilty;

public class Scoring
  {
  
  public static void main(String ... args)
    {
    
    double prob = 125.0;
    double q = 281.45117;
      do
        {
        prob = 125.0;
        for (int i = 1; i <= 50; i++)
          {
          prob *= (1 - ( (double)i / q));
          }
        q += 0.00000000001;
        }
      while (prob < 1.0);
    
    System.out.printf("%13.10f%n", q);
    }
  
  }
