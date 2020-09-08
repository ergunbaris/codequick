object BitCounting {
  def main(args: Array[String]): Unit = {
    val t = Seq((1234, 5), (0, 0), (4, 1), (7, 3), (9, 2), (10, 2)).zipWithIndex
    assert(t.forall { case ((i, o), idx) =>
      println(s"$idx step")
      bitCount(i) == o
    })
  }

  def bitCount(n: Int): Int = n.toBinaryString.filter(_ == '1').size
}

/*
Write a function that takes an integer as input, and returns the number of bits that are equal to one in the
binary representation of that number. You can guarantee that input is non-negative.

Example: The binary representation of 1234 is 10011010010, so the function should return 5 in this case*/
