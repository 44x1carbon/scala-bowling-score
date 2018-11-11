sealed trait BonusBall

case class StrikeBonusBall(one: Int, two: Int) extends BonusBall
case class SpareBonusBall(one: Int) extends BonusBall
