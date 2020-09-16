import scala.collection.mutable

object CleanString {
  def main(args: Array[String]): Unit = {
    val t = Seq(("abc#d##c","ac"),("abc##d######",""),("#######",""),("","")).zipWithIndex
    assert(t.forall{case ((i, o), idx) =>
    println(s"$idx step")
      val res = erase(i)
      println(s"res=$res")
      res == o
    })
  }

  def erase(str : String): String = {
    val stack = new mutable.Stack[Char]
    str.foreach(elem => {
      elem match {
        case '#' => if (!stack.isEmpty) stack.pop
        case x => stack push x
      }
    })
    stack.popAll.mkString
  }
}
