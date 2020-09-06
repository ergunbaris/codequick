import scala.annotation.tailrec

object Exercise1 {
  def main(args: Array[String]): Unit = {
    val fibonacciTest = List((2,1), (3, 2),(22, 17711), (28,	317811))
    assert(fibonacciTest.forall{case (num, fib) =>
      val res = fibonacci(num)
      println(s"$res $fib")
      fibonacci(num) == fib
    })

  }
  def fibonacci(max: Int):Int = {
    @tailrec
    def fibonacci(n: Int, prev: Int, prevPrev: Int): Int = {
      if (n == max) prev + prevPrev
      else fibonacci(n + 1,prev + prevPrev, prev)
    }
    max match {
      case 0 => 0
      case 1 => 1
      case _ => fibonacci(2, 1 , 0)
    }
  }
}

/*
5 5
22	17711
28	317811
* */
