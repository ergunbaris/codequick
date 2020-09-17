object StringMixBetter {

  def main(args: Array[String]): Unit = {
    val t = Seq(("my&friend&Paul has heavy hats! &", "my friend John has many many friends &",
      "2:nnnnn/1:aaaa/1:hhh/2:mmm/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"),
      ("mmmmm m nnnnn y&friend&Paul has heavy hats! &", "my frie n d Joh n has ma n y ma n y frie n ds n&",
        "1:mmmmmm/=:nnnnnn/1:aaaa/1:hhh/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"),
      ("Are the kids at home? aaaaa fffff", "Yes they are here! aaaaa fffff", "=:aaaaaa/2:eeeee/=:fffff/1:tt/2:rr/=:hh"))
      .zipWithIndex

    assert(t.forall { case ((a, b, o), idx) =>
      println(s"$idx step")
      val res = mix(a, b)
      println(s"res=$res")
      res == o
    })
  }

  def mix(s1: String, s2: String): String = {
    val f1 = s1.filter(x => x >= 'a' && x <= 'z').groupMapReduce(identity)(_ => 1)(_ + _)
    val f2 = s2.filter(x => x >= 'a' && x <= 'z').groupMapReduce(identity)(_ => 1)(_ + _)
    for {
      k <- (f1.keys ++ f2.keys).toSet[Char]
      n1 = f1.getOrElse(k, 0)
      n2 = f2.getOrElse(k, 0)
      ma = math.max(n1, n2).toInt
      if ma > 1
    } yield {
      if (n1 == n2) ("=", ma, k.toString)
      else if (n1 == ma) ("1", ma, k.toString)
      else ("2", ma, k.toString)
    }
  }.toList.map(x => s"${x._1}:${x._3 * x._2}").sortBy(x => (-x.length, x)).mkString("/")
}
