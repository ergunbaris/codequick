import scala.collection.mutable.Set

object Solution {

  def main(args: Array[String]): Unit = {
    val xs = Array(2, 2,2)
    println(solution(xs))
  }

  def solution(a: Array[Int]): Int = {
    // write your code in Scala 2.12
    val distinctCount = a.distinct.size
    val distinctSet = Set.empty[Int]
    a.scanLeft((0,0)) { case ((setSize, idx), x) =>
      distinctSet.add(x)
      (distinctSet.size, idx + 1)
    }.takeWhile(x => {
      x._1 != distinctCount
    })
      .last._2
  }
}

