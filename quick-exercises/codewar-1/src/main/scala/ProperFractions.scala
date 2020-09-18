object ProperFractions {
  def main(args: Array[String]): Unit = {
    val t = Seq((1L, 0L), (2L, 1L), (5L, 4L), (15L, 8L), (25L, 20L), (35L, 24L), (Long.MaxValue, 4L)).zipWithIndex
    assert(t.forall { case ((i, o), idx) =>
      println(s"$idx step")
      val res = proper_fractions(i)
      println(s"res = $res")
      res == o
    })
  }

  def proper_fractions(n: Long): Long = {
    if (n == 1) 0
    else if (n == 2) 1
    else {
      val primes = getPrimes(n)
      val primeFactors = primes.scanLeft((List.empty[(Long, Int)], n)) { case ((acc, div), prime) =>
        val times =
          LazyList.continually(prime).scanLeft((div, 1)) { case ((d, cnt), y) => if (d % y == 0) (d / y, cnt + 1) else (d, cnt) }.takeWhile(_._1 % prime == 0)
            .lastOption.map(_._2).getOrElse(0)
        if (times == 0) (acc, div)
        else ((prime, times) :: acc, div / (prime * times))
      }.last._1.map(_._1)
        .toSet[Long].flatMap(x => Set(x, n / x)) ++ (if(BigInt(n).isProbablePrime(100)) Set(n) else Set.empty[Long])

      n - primeFactors.foldLeft(0L) { case (acc, x) => acc + n / x } +
        (2 to primeFactors.size).flatMap(members =>
          primeFactors.toSeq.combinations(members).map(_.reduce(_ * _))).toSet
          .foldLeft(0L) { case (acc, x) => acc + n / x }
    }

  }


  def getPrimes(n: Long): LazyList[Long] = {
    2L #:: LazyList.continually(1L).scanLeft(3L) { case (num, y) => num + y }.takeWhile(_ <= math.sqrt(n)).filter(BigInt(_).isProbablePrime(100))
  }


}

/**
 * proper_fractions(1)==0
 * proper_fractions(2)==1
 * proper_fractions(5)==4
 * proper_fractions(15)==8
 * proper_fractions(25)==20
 */