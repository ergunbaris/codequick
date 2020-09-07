object RevRot {
  def main(args: Array[String]): Unit = {
    val t = Seq(("123456987654", 6, "234561876549"),
      ("123456987653", 6, "234561356789"),
      ("66443875", 4, "44668753"),
      ("66443875", 8, "64438756"),
      ("664438769", 8, "67834466"),
      ("123456779", 8, "23456771"),
      ("", 8, ""),
      ("123456779", 0, ""),
      ("563000655734469485", 4, "0365065073456944")).zipWithIndex
    assert(t.forall { case ((str, sz, exp), idx) =>
      println(s"$idx step")
      val res = revrot(str, sz)
      println(s"res=$res")
      res == exp
    })
  }

  def revrot(strng: String, sz: Int): String = {
    if (strng.isEmpty || sz <= 0 || sz > strng.size) ""
    else {
      strng.dropRight(strng.size % sz).grouped(sz)
        .map{str => {
          val cubeSum = str.map(x => Math.pow(x.asDigit , 3).toInt).sum
          if(cubeSum % 2 == 0) str.reverse
          else str.tail + str.head
        }}.mkString
    }
  }
}


/*
  The input is a string str of digits. Cut the string into chunks (a chunk here is a substring of the initial string)
  of size sz (ignore the last chunk if its size is less than sz). If a chunk represents an integer such as the sum of
  the cubes of its digits is divisible by 2, reverse that chunk; otherwise rotate it to the left by one position.
  Put together these modified chunks and return the result as a string.

  revrot("123456987654", 6) --> "234561876549"
revrot("123456987653", 6) --> "234561356789"
revrot("66443875", 4) --> "44668753"
revrot("66443875", 8) --> "64438756"
revrot("664438769", 8) --> "67834466"
revrot("123456779", 8) --> "23456771"
revrot("", 8) --> ""
revrot("123456779", 0) --> ""
revrot("563000655734469485", 4) --> "0365065073456944"
  */
