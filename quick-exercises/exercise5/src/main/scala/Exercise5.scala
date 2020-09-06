object Exercise5 {
  def main(args: Array[String]): Unit = {
    val t = List((3.0, 0.66, 1.5, 3), (3.0, 1.0, 1.5, -1))
    assert(t.zipWithIndex.forall{case ((h, b, w, r), i) =>
      println(s"running $i")
      bouncingBall(h, b, w) == r
    })
  }

  def bouncingBall(h: Double, bounce: Double, window: Double): Int = {
    def bounceBall(h: Double, count: Int): Int = {
      val nextH = h * bounce

      if(nextH <= window) count
      else bounceBall(nextH, count + 2)
    }
    if((checkB(bounce) && checkH(h) && checkW(window, h)) == false) -1
    else{
      bounceBall(h, 1 )
    }
  }

  def checkH(h: Double) = if(h <= 0.0 ) false else true
  def checkB(b: Double) = if(b > 0.0 && b < 1.0 ) true else false
  def checkW(w: Double, h: Double) = if(w < h) true else false

}

/*
A child is playing with a ball on the nth floor of a tall building. The height of this floor, h, is known.

He drops the ball out of the window. The ball bounces (for example), to two-thirds of its height (a bounce of 0.66).

His mother looks out of a window 1.5 meters from the ground.

How many times will the mother see the ball pass in front of her window (including when it's falling and bouncing?
 */
