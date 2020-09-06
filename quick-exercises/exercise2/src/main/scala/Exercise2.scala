object Exercise2 {
  def main(args: Array[String]): Unit = {
    val testTable = List((Array(2, 3, 4), Array(1, 2, 3, 4, 5), true),
      (Array(5, 5, 5), Array(4, 5, 5, 5, 3, 2), true),
      (Array(1, 1), Array(2, 3, 1, 0), false),
      (Array(2, 1), Array(2, 1, 1, 0), true),
      (Array.emptyIntArray, Array(2, 1, 1, 0), true),
      (Array(5, 5, 5), Array.emptyIntArray, false))
    assert(testTable.forall { case (arrA, arrB, expected) =>
      println(expected)
      isArraySub(arrA, arrB) == expected
    })
  }

  def isArraySub(a: Array[Int], b: Array[Int]): Boolean = {
    if (a.size == 0) true
    else if (b.size == 0) false
    else {
      val res = b.foldLeft(0) { case (bIndex, elem) =>
        if (bIndex == a.size) bIndex else if (a(bIndex) == elem) bIndex + 1 else 0
      }
      res == a.size
    }
  }
}

/*
(2,3,4) (1,2,3,4,5) true
(5,5,5) (4,5,5,5,3,2) true
(1, 1) (2, 3, 1,0) false
  */