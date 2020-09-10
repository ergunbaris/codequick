object FactorialZeros {

  def main(args: Array[String]): Unit = {
    val t = Seq((6, 1), (12, 2), (18, 3)).zipWithIndex
    assert(t.forall { case ((i, o), idx) =>
      println(s"$idx step")
      val res = zeros(i)
      println(s"res=$res")
      res == o
    })
  }

  def zeros(n: Int): Int = findFives(n, 0)

  def  findFives(n: Int, count: Int): Int = {
    if(n/5 < 1) count
    else findFives(n/5, count+n/5)
  }
}
