case class BowlingScoreSheet(str: String) {
  case class State(total: Int, n: Int, nn: Int)

  def calcScore(): Int = {
    val bonusState = bonus match {
      case Some(StrikeBonusBall(one, two)) => State(0, one, two)
      case Some(SpareBonusBall(one))       => State(0, one, 0)
      case None                            => State(0, 0, 0)
    }

    frames
      .foldRight(bonusState) { (optionalFrame, state) =>
        optionalFrame match {
          case Some(Normal(one, two)) =>
            State(state.total + one + two, one, two)
          case Some(Spare(one)) =>
            State(state.total + 10 + state.n, one, 10 - one)
          case Some(Strike()) =>
            State(state.total + 10 + state.n + state.nn, 10, state.n)
          case None => state
        }
      }
      .total
  }

  val hoge = str.split("\\|\\|")
  val frames: Array[Option[Frame]] = hoge.head.split("\\|").map {
    case "X" => Some(Strike())
    case s if s.length >= 2 && s.charAt(1) == '/' =>
      val one = if (s.charAt(0) == '-') 0 else s.charAt(0).toString.toInt
      Some(Spare(one))
    case s =>
      val one = if (s.charAt(0) == '-') 0 else s.charAt(0).toString.toInt
      val two = if (s.charAt(1) == '-') 0 else s.charAt(1).toString.toInt
      Some(Normal(one, two))
    case _ => None
  }
  val bonus: Option[BonusBall] =
    if (hoge.length == 1) None
    else
      hoge(1) match {
        case s if s.length >= 2 =>
          val one =
            if (s.charAt(0) == '-') 0
            else if (s.charAt(0) == 'X') 10
            else s.charAt(0).toString.toInt
          val two =
            if (s.charAt(1) == '-') 0
            else if (s.charAt(1) == 'X') 10
            else if (s.charAt(1) == '/') 10 - one
            else s.charAt(1).toString.toInt
          Some(StrikeBonusBall(one, two))
        case s if s.length >= 1 =>
          val one =
            if (s.charAt(0) == '-') 0
            else if (s.charAt(0) == 'X') 10
            else s.charAt(0).toString.toInt
          Some(SpareBonusBall(one))
      }
}
