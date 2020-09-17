object Lcs {
  def main(args: Array[String]): Unit = {
    val t = Seq(("abcdef", "abc", "abc"), ("abcdef", "acf", "acf"), ("123", "abc", ""), ("132535365", "123456789", "12356"),
      ("abcdefghijklmnopq", "apcdefghijklmnobq", "acdefghijklmnoq")).zipWithIndex
    assert(t.forall { case ((s1, s2, e), idx) =>
      println(s"$idx step")
      val res = findLongestSubSequence(s1, s2)
      println(s"res=$res")
      res == e
    })
  }

  def findLongestSubSequence(s1: String, s2: String): String = {
    val s1Comb = allCombinations(s1)
    val s2Comb = allCombinations(s2)
    val x = s1Comb.intersect(s2Comb).maxByOption(_.length)
    x.getOrElse("")
  }

  def allCombinations(s: String): Set[String] = {
    (1 to s.length).flatMap { size =>
      (0 to s.length).flatMap { idx =>
        s.drop(idx).toSeq.combinations(size).map(_.toString())
      }
    }.toSet
  }

}

/**
 * lcs( "abcdef", "abc" ) => "abc"
 * lcs( "abcdef", "acf" ) => "acf"
 * lcs( "132535365", "123456789" ) => "12356"
 * lcs( "abcdefghijklmnopq", "apcdefghijklmnobq" ) => "acdefghijklmnoq"
 */