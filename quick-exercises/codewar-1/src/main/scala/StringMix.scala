object StringMix {
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

  def mix(a: String, b: String): String = {
    val aMap = getLetterMap(a, 1)
    val bMap = getLetterMap(b, 2)
    (aMap ++ bMap).groupBy(_._1).view.mapValues(value => {
      val maxOccur = value.map(_._2).max
      val matching = value.filter(_._2 == maxOccur)
      val newStr = (0 until maxOccur).map(_ => value.head._1).mkString
      (matching.size match{
        case 2 => (s"=:${newStr}",3)
        case _ => (s"${matching.head._3}:$newStr", matching.head._3)
      }, maxOccur)

    }).toSeq.sortBy(x => (- x._2._2, x._2._1._2, x._1)).map(_._2._1._1).mkString("/")
  }

  def getLetterMap(a: String, seqId: Int): Seq[(Char, Int, Int)] = {
    a.filter(x => x.isLetter && x.isLower).foldLeft(Map.empty[Char, Int]) {
      (acc, l) => {
        val lCount = acc.getOrElse(l, 0)
        acc.updated(l, lCount + 1)
      }
    }.filter(_._2  >= 2).toSeq.sortBy(- _._2).map(x => (x._1, x._2, seqId))
  }
}


/**
 * s1 = "my&friend&Paul has heavy hats! &"
 * s2 = "my friend John has many many friends &"
 * mix(s1, s2) --> "2:nnnnn/1:aaaa/1:hhh/2:mmm/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"
 * *
 * s1 = "mmmmm m nnnnn y&friend&Paul has heavy hats! &"
 * s2 = "my frie n d Joh n has ma n y ma n y frie n ds n&"
 * mix(s1, s2) --> "1:mmmmmm/=:nnnnnn/1:aaaa/1:hhh/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"
 * *
 * s1="Are the kids at home? aaaaa fffff"
 * s2="Yes they are here! aaaaa fffff"
 * mix(s1, s2) --> "=:aaaaaa/2:eeeee/=:fffff/1:tt/2:rr/=:hh"
 */