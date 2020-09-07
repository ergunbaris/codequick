object PrinterErrors {
  def main(args: Array[String]): Unit = {
    val t = Seq(("aaabbbbhaijjjm", "0/14"), ("aaaxbbbbyyhwawiwjjjwwm", "8/22")).zipWithIndex
    assert(t.forall{case ((i, o), idx) =>
      println(s"$idx step")
      val res = printerError(i)
      println(s"res=$res")
      printerError(i) == o
    })
  }

  def printerError(str: String): String = {
    val eCnt = str.filterNot(c => c >= 'a' && c <= 'm').size
    val strCnt = str.size
    s"$eCnt/$strCnt"
  }
}
