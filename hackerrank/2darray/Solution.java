import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        byte arr[][] = new byte[6][6];
        for(int arr_i=0; arr_i < 6; arr_i++){
            for(int arr_j=0; arr_j < 6; arr_j++){
                arr[arr_i][arr_j] = in.nextByte();
            }
        }
        System.out.println(findMaxHourGlassSum(arr));
    }
    // TODO flatten the array into 1D 
    public static byte findMaxHourGlassSum(byte [][] arr)
      {
      byte maxSum = Byte.MIN_VALUE;
      for(int row=1; row < 5; row++){
            for(int col=1; col < 5; col++){
            byte sum = findHourGlassSum(arr, row, col);
            if (sum > maxSum)
              {
              maxSum = sum;
              }
            }
        }
      return maxSum;
      }
    // Rabin Karp approach just saves 1 step.
    private static byte findHourGlassSum(byte [][] arr,
                                         int row,
                                          int col)
      {
      byte sum = 0;
      sum += arr[row-1][col-1];
      sum += arr[row-1][col];
      sum += arr[row-1][col+1];
      sum += arr[row][col];
      sum += arr[row+1][col-1];
      sum += arr[row+1][col];
      sum += arr[row+1][col+1];
      return sum;
      }      
}

