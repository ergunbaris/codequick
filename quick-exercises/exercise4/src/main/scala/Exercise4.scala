object Exercise4 {
  def main(args: Array[String]): Unit = {
    val t = List((Seq(121, 144, 19, 161, 19, 144, 19, 11), Seq(121, 14641, 20736, 361, 25921, 361, 20736, 361), true),
      (Seq(121, 144, 19, 161, 19, 144, 19, 11), Seq(132, 14641, 20736, 361, 25921, 361, 20736, 361), false),
      (Seq(121, 144, 19, 161, 19, 144, 19, 11), Seq(121, 14641, 20736, 36100, 25921, 361, 20736, 361), false),
      (Seq.empty[Int], Seq.empty[Int], true))
    assert(t.forall { case (i, o, e) =>
      println(e)
      comp(i, o) == e
    })
  }

  def comp(seq1: Seq[Int], seq2: Seq[Int]): Boolean = {
    if (seq1.size == 0 && seq2.size == 0) true
    else if(seq1 == null || seq2 == null) false
    else if (seq1.size == 0 || seq2.size == 0) false
    else {
      val roots = seq2.map(Math.sqrt(_))
      val seq2Root = roots.filter(_.isValidInt)
      if (seq2Root.size != seq2.size) false
      else {
        val rootArr = seq2Root.map(_.toInt).sorted.toArray
        val seq1Arr = seq1.sorted.toArray
        (0 until rootArr.size).forall(idx => rootArr(idx) == seq1Arr(idx))
      }
    }
  }
}

/*
Given two arrays a and b write a function comp(a, b) (compSame(a, b) in Clojure) that checks whether the two arrays
have the "same" elements, with the same multiplicities. "Same" means, here, that the elements in b are the elements in a squared, regardless of the order.
 */

/*
Valid

a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [121, 14641, 20736, 361, 25921, 361, 20736, 361]

a = [121, 144, 19, 161, 19, 144, 19, 11]
b = [11*11, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19]

 */