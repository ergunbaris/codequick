object SmileyFaces {
  def main(args: Array[String]): Unit = {
    val t = Seq((Vector(":)", ";(", ";}", ":-D"), 2), (Vector(";D", ":-(", ":-)", ";~)"), 3), (Vector(";]", ":[", ";*", ":$", ";-D"), 1))
      .zipWithIndex
    assert(t.forall { case ((i, o), idx) =>
      println(s"$idx step")
      val res = countSmileys(i)
      println(s"res=$res")
      res == o
    })
  }

  def countSmileys(vec: Vector[String]): Int = {
    val validSmileys =
      Seq(":", ";").flatMap(z => Seq("", "-", "~").map(y => z + y)).flatMap(x => Seq("", ")", "D", "~").map(y => x + y)).toSet
    vec.foldLeft(0){case (acc, str) => if(validSmileys(str)) acc+ 1 else acc }
  }
}

/**
 * Given an array (arr) as an argument complete the function countSmileys that should return the total number of smiling faces.
 *
 * Rules for a smiling face:
 *
 * Each smiley face must contain a valid pair of eyes. Eyes can be marked as : or ;
 * A smiley face can have a nose but it does not have to. Valid characters for a nose are - or ~
 * Every smiling face must have a smiling mouth that should be marked with either ) or D
 * No additional characters are allowed except for those mentioned.
 *
 * Valid smiley face examples: :) :D ;-D :~)
 * Invalid smiley faces: ;( :> :} :]
 *
 * Example
 * countSmileys([':)', ';(', ';}', ':-D']);       // should return 2;
 * countSmileys([';D', ':-(', ':-)', ';~)']);     // should return 3;
 * countSmileys([';]', ':[', ';*', ':$', ';-D']); // should return 1;
 * Note
 * In case of an empty array return 0. You will not be tested with invalid input (input will always be an array). Order of the face (eyes, nose, mouth) elements will always be the same.
 */