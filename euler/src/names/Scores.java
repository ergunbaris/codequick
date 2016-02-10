package names;
import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Scores
  {
  private static Pattern namePattern = Pattern.compile("^\\\"([A-Z]+)\\\"$");
  public static void main (String...args) throws FileNotFoundException
    {
    Scores scores = new Scores();
    String [] names = scores.loadAllNames(args[0]);
    Arrays.sort(names);
    System.out.println(scores.findScoreSum(names));
    }
  public String [] loadAllNames(String uri) throws FileNotFoundException
    {
    List<String> names = new ArrayList<>();
    File file = new File(uri);
    Scanner scanner = new Scanner(file);
    scanner.useDelimiter(",");
    while (scanner.hasNext())
      {
      Matcher m = namePattern.matcher(scanner.next());
      if (m.matches())
        {
        names.add(m.group(1));
        }
      }
    return names.toArray(new String[0]);
    }
  public long findScoreSum(String [] names)
    {
    long sum = 0L;
    for (int i = 0; i < names.length; i++)
      {
      long sumInt = 0L;
      for (int j = 0; j < names[i].length();j++)
        {
        sumInt += charVal(names[i].charAt(j));
        }
      sum += (sumInt * (i + 1));
      }
    return sum;
    }
  private int charVal(char a)
    {
    return a - 'A' + 1;
    }
  }
