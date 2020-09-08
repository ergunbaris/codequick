object Roman {
  def main(args: Array[String]): Unit = {
    val t = Seq(("MCMXC", 1990), ("MMVIII", 2008), ("MDCLXVI", 1666)).zipWithIndex
    assert(t.forall { case ((i, o), idx) =>
      println(s"running $idx step")
      val res = decode(i)
      println(s"res=$res")
      res == o
    })
  }

  val romanMap = Map('I' -> 1,
    'V' -> 5,
    'X' -> 10,
    'L' -> 50,
    'C' -> 100,
    'D' -> 500,
    'M' -> 1000)

  def decode(roman: String): Int = {
    val x = roman.foldLeft((0, 0, 'S')){case ((acc, midSum, prevChar), curChar) => if(prevChar == 'S') (acc , romanMap(curChar), curChar)
    else if(romanMap(curChar) < romanMap(prevChar)) (acc + midSum, romanMap(curChar), curChar)
    else if(romanMap(curChar) == romanMap(prevChar))(acc + romanMap(curChar) + midSum, 0, curChar)
    else (acc - midSum +  romanMap(curChar), 0,  curChar)}
    x._1 + x._2
  }

}
