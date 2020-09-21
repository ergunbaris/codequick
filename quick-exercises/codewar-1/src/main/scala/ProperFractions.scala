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
    else if (BigInt(n).isProbablePrime(100)) n - 1
    else if(math.sqrt(n).isValidInt) n - math.sqrt(n).toLong
    else {
      val primes = getPrimes(n)
      val primeFactors = primes.filter(n % _ == 0)
        .flatMap(prime => LazyList.from(1).scanLeft(n) { case (div, _) => div / prime }.takeWhile(_ % prime == 0).map(_ => prime))
      val multipy = n / primeFactors.reduce(_ * _)
      val primeFactors2 = if (multipy == 1) primeFactors else multipy #:: primeFactors
      n - primeFactors.toSet.foldLeft(0L) { case (acc, x) =>
        val times = n / x
        if (times == x) times + acc
        else acc + times + x
      } + (2 to primeFactors2.size).flatMap(members =>
        primeFactors2.combinations(members).map(_.reduce(_ * _))).toSet
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