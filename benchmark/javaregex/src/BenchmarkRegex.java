import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BenchmarkRegex
  {
  private static final Pattern PATTERN = Pattern.compile("(a|aa)*b");
  public static void main(String... args) 
    {    
    StringBuilder input = new StringBuilder("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaac");
    for (int i = 0 ;i < 6 ; i++)
      {
      Matcher matcher = PATTERN.matcher(input.toString());
      long start = System.nanoTime();
      matcher.find();
      long end = System.nanoTime();
      System.out.printf("input=%s ", input.toString());
      System.out.printf("time=%dms%n", (end - start) / 1000_000);
      input.insert(0, "a");
      
      }
    
    
    }
  }
