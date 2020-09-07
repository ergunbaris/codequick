object DigPow {

  def main(args: Array[String]): Unit = {
    val t = Seq((89, 1, 1), (92, 1, -1), (695, 2, 2), (46288, 3, 51)).zipWithIndex
    assert(t.forall{case ((n, h, r), idx) =>
      println(s"$idx step n= $n, h=$h")
      dig_pow(n,h) == r
    })

  }
  def dig_pow(n: Int, p: Int): Int = {
    val digitSum = n.toString.zipWithIndex
      .map{case (ch, idx) => Math.pow(ch.asDigit.toDouble, (p + idx).toDouble)}.sum / n
    digitSum match {
      case r if r.isWhole => r.toInt
      case _ => -1
    }
  }
}


/*
dig_pow(89, 1) should return 1 since 8¹ + 9² = 89 = 89 * 1
dig_pow(92, 1) should return -1 since there is no k such as 9¹ + 2² equals 92 * k
dig_pow(695, 2) should return 2 since 6² + 9³ + 5⁴= 1390 = 695 * 2
dig_pow(46288, 3) should return 51 since 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51
*/
