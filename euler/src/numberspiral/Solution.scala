object Solution {
  def solution(n: Int): Long = {
    (2 to 1001).foldLeft(1L){case (acc, x) =>
        if(x % 2 == 0) acc + 3 * (Math.pow(x, 2).toLong + 1L)
        else acc + Math.pow(x, 2).toLong
    }
  }
}
