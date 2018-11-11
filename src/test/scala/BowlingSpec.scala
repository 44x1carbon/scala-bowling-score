import org.scalatest.FlatSpec

class BowlingSpec extends FlatSpec {

  "Bowling Score Sheet" should "全部のフレームがストライクだと300点" in {
    val input = "X|X|X|X|X|X|X|X|X|X||XX"
    val output = 300

    val bowlingScoreShet = BowlingScoreSheet(input)
    val score: Int = bowlingScoreShet.calcScore()
    assert(score === output)
  }

  it should "全部のフレームが9本だと90点" in {
    val input = "9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||"
    val output = 90

    val bowlingScoreShet = BowlingScoreSheet(input)
    val score: Int = bowlingScoreShet.calcScore()
    assert(score === output)
  }

  it should "全部のフレームがストライクだけど、ボーナスボールは全てガーター" in {
    val input = "X|X|X|X|X|X|X|X|X|--||"
    val output = 240

    val bowlingScoreShet = BowlingScoreSheet(input)
    val score: Int = bowlingScoreShet.calcScore()
    assert(score === output)
  }
}
