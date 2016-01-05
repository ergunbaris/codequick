package numberletter;
import java.util.*;

// Retrospcetive should have done 1-100 and than multiply by 10 for upto 1000 where as
// I did group the numbers 1-9 10-19 twenties hundreds and try to calculate all the way upto thousand.
// Misspeled forty as fourty

public class Count
  {
  public static void main(String...args) 
    {
    System.out.printf("%d%n", new Count().countLetters());
    }
  public int countLetters()
    {
    String ones [] = {"one","two","three","four","five","six","seven","eight","nine"};
    String elevens [] = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    String ten = "ten";
    String twenties [] = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    String hundred = "hundred";
    String and = "and";
    String thousand = "thousand";
    int sum = 0;
    for(String num:ones)
      {
      sum += num.length()*9;
      }
    for(String num:elevens)
      {
      sum += num.length();
      }
    for(String num:twenties)
      {
      sum += num.length()*10;
      }
    sum *= 10;
    sum += hundred.length()*9;
    sum += (hundred.length() + and.length())*891;
    for(String num:ones)
      {
      sum += num.length()*100;
      }
    sum += thousand.length();
    sum += "one".length();
    return sum;
    }
  }
