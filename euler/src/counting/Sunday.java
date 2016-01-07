package counting;

public class Sunday
  {
  public static void main(String...args)
    {
    System.out.println(new Sunday().countSundays());
    }
  public int countSundays()
    {
    byte [] firstDayOfMonths = {1,4,4,0,2,5,0,3,6,1,4,6}; // based on 1901
    int sundayCnt = countSundays(firstDayOfMonths);
    for (int y=1902;y<2001;y++)
      {
      for (int i=0;i<firstDayOfMonths.length;i++)
        {
        byte leap = 1;
        if(isLeap(y,i))
          {
          leap =2;
          }
        firstDayOfMonths[i] = (byte)((firstDayOfMonths[i]+leap)%7);
        }
      sundayCnt += countSundays(firstDayOfMonths);
      }
    return sundayCnt;
    }
  private boolean isLeap(int year, int month)
    {
    if (((year%4==0 && year%100 !=0) || year%400==0) && month >1)
      {
      return true;
      }
    if ((((year-1)%4==0 && (year-1)%100 !=0) || (year-1)%400==0) && month < 2)
      {
      return true;
      }
    return false;
    }
  private byte countSundays(byte [] firstDayOfMonths)
    {
    byte sundayCnt = 0;
    for (int i=0;i<firstDayOfMonths.length;i++)
      {
      if (firstDayOfMonths[i] == 6)
        {
        sundayCnt++;
        }
      }
    return sundayCnt;
    }
  }
