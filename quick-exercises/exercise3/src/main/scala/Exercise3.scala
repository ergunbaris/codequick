object Exercise3 {
  def main(args: Array[String]): Unit = {
    val testTable = List((42145, 54421L), (145263, 654321L), (123456789, 987654321L), (0,0L), (Int.MaxValue, 8776444321L))
    assert(testTable.forall { case (input, output) =>
      descendingOrder(input) == output
    })
  }

  def descendingOrder(num: Int): Long = {
    val res = num.toString.toCharArray
    val str = new String(res.sorted.reverse)
    println(str)
    str.toLong
  }

}

/*
Your task is to make a function that can take any non-negative integer as an argument and
return it with its digits in descending order. Essentially, rearrange the digits to create the highest possible number.

nput: 42145 Output: 54421

Input: 145263 Output: 654321

Input: 123456789 Output: 987654321
 */