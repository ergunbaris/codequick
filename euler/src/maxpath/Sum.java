package maxpath;

public class Sum
  {
  public static void main(String...args) 
    {
    int [][] triangle = new int[15][];
    triangle[0] = new int[]{75};
    triangle[1] = new int[]{95,64};
    triangle[2] = new int[]{17,47,82};
    triangle[3] = new int[]{18, 35, 87, 10};
    triangle[4] = new int[]{20, 4, 82, 47, 65};
    triangle[5] = new int[]{19, 1, 23, 75, 3, 34};
    triangle[6] = new int[]{88, 2, 77, 73, 7, 63, 67};
    triangle[7] = new int[]{99, 65, 4, 28, 6, 16, 70, 92};
    triangle[8] = new int[]{41, 41, 26, 56, 83, 40, 80, 70, 33};
    triangle[9] = new int[]{41, 48, 72, 33, 47, 32, 37, 16, 94, 29};
    triangle[10] = new int[]{53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14};
    triangle[11] = new int[]{70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57};
    triangle[12] = new int[]{91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48};
    triangle[13] = new int[]{63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31};
    triangle[14] = new int[]{4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23};

    display(triangle);
    System.out.println(maxSum(triangle));
    }
  public static void display(int [][] array)
    {
    for (int row=0; row<array.length; row++)
      {
      for (int col=0; col<array[row].length; col++)
        {
        System.out.printf("%d,", array[row][col]);
        }
        System.out.println();
      }
    }
  public static int maxSum(int [][] array)
    {
    int [][] sumArray = new int[array.length][];
    for (int row=0; row<array.length; row++)
      {
      sumArray[row] = new int[array[row].length];
      }

    sumArray[0][0] = array[0][0];
    
    for (int row=0; row<array.length -1; row++)
      {
      for (int col=0; col<array[row].length; col++)
        {
        traverse(array,sumArray,row, col);
        }
      }
    int max = 0;
    for (int col=0; col<sumArray[sumArray.length-1].length; col++)
      {
      if(sumArray[sumArray.length-1][col] > max)
        {
        max = sumArray[sumArray.length-1][col];
        }
      }
    return max;
    }
  private static void traverse(int [][] array, int [][] sumArray, int row, int col)
    {
    int downRow = row +1;
    int leftCol = col;
    int rightCol = col+1;
    if ((sumArray[row][col] + array[downRow][leftCol]) > sumArray[downRow][leftCol])
       {
       sumArray[downRow][leftCol] = sumArray[row][col] + array[downRow][leftCol];
       }
    if ((sumArray[row][col] + array[downRow][rightCol]) > sumArray[downRow][rightCol])
       {
       sumArray[downRow][rightCol] = sumArray[row][col] + array[downRow][rightCol];
       }
    }
  }
