object Solution {
  def solution(a: Array[Int]): Int = {
    val aSorted = a.filter(_ > 0).sorted
    val s = aSorted.scanLeft(0, 0){case ((prev, prevDiff), x) => (x, x - prev)}.takeWhile{case (_, diff) => diff < 2}
    if (s.length == 1) 1 else s.last._1 +1
  }
}

