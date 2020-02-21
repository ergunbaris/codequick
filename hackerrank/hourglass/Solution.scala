import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._

object Solution {

    // Complete the hourglassSum function below.
    def hourglassSum(arr: Array[Array[Int]]): Int = {
    (1 until 5).flatMap(i => (1 until 5).map(j => (i,j)))
      .foldLeft(Int.MinValue){case (max, (i, j)) => Math.max(max, hourglassSum(arr, i, j))}

  }

  def hourglassSum(arr: Array[Array[Int]], iIdx: Int, jIdx: Int): Int = {
    (iIdx - 1 to iIdx + 1).flatMap(i => (jIdx - 1 to jIdx + 1).flatMap(j =>
    if(j == jIdx -1 && i == iIdx) None else if(j == jIdx +1 && i == iIdx) None else Some(i, j)))
      .foldLeft(0){case (sum , (i, j)) => sum + arr(i)(j)}
  }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val arr = Array.ofDim[Int](6, 6)

        for (i <- 0 until 6) {
            arr(i) = stdin.readLine.split(" ").map(_.trim.toInt)
        }

        val result = hourglassSum(arr)

        printWriter.println(result)

        printWriter.close()
    }
}

