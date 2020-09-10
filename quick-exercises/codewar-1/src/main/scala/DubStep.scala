object DubStep {
  def main(args: Array[String]): Unit = {
    val t = Seq(("AWUBBWUBC", "A B C"), ("AWUBWUBWUBBWUBWUBWUBC", "A B C"),
      ("WUBWUBIWUBAMWUBWUBX", "I AM X"),
      ("WUBWEWUBAREWUBWUBTHEWUBCHAMPIONSWUBMYWUBFRIENDWUB", "WE ARE THE CHAMPIONS MY FRIEND")).zipWithIndex
    assert(t.forall { case ((i, o), idx) =>
      println(s"$idx step")
      val res = songDecoder(i)
      println(s"res=$res")
      res == o
    })
  }

  def songDecoder(song: String): String = song.replaceAll("^(WUB)+|(WUB)+$", "")
    .replaceAll("(WUB)+", " ")
}
