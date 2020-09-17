object DuplicateEncoder {
  def main(args: Array[String]): Unit = {
    val t = Seq(("din", "((("), ("recede", "()()()"), ("Success", ")())())"), ("(( @", "))(("))
      .zipWithIndex
    assert(t.forall { case ((i, o), idx) =>
      println(s"$idx step")
      val res = encode(i)
      println(s"res=$res")
      res == o
    })
  }

  def encode(str: String): String = {
    val lowered = str.toLowerCase
    val charMap = lowered.toSeq.groupBy(identity).view.mapValues(_.length > 1)
    lowered.map(ch => if(charMap(ch)) ')' else '(')
  }
}


/**
 * "din"      =>  "((("
 * "recede"   =>  "()()()"
 * "Success"  =>  ")())())"
 * "(( @"     =>  "))(("
 */