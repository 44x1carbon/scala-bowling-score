//case class Frame(str: String) {
//  val one: Char = str.charAt(0)
//  val two: Char = if(str.length >= 2) str.charAt(1) else
//}

sealed trait Frame

case class Strike() extends Frame
case class Spare(one: Int) extends Frame
case class Normal(one: Int, two: Int) extends Frame
