object ReverseLetter {
  def main(args: Array[String]): Unit = {
    val t = Seq(("krishan","nahsirk"),("ultr53o?n","nortlu"), ("", "")).zipWithIndex
    assert(t.forall{case ((i, o), idx) =>
    println(s"step $idx")
      val res = reverse(i)
      println(res)
      res == o
    })
  }

  def reverse(s: String): String = s.filter(_.isLetter).reverse
}
//("krishan","nahsirk"),("ultr53o?n","nortlu"),