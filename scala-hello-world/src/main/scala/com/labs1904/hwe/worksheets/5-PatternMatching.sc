val states = Map(
  "AL" -> "Alabama",
  "AK" -> "Alaska",
  "IA" -> "Iowa",
  "IL" -> "Illinois",
  "LA" -> "Louisiana",
  "MO" -> "Missouri",
  "NE" -> "Nebraska"
)

val state = states.get("AL")

state match {
  case Some(s) => {
    println("Got a state")
  }
  case None => {
    println("Got nothing")
  }
}

//case class Candy(name: String, sweet: Boolean, salty: Boolean, chocolatey: Boolean)
//
//case class Person(name: String, height: Int, favoriteCandy: Candy)
//
//val c = Candy("Skittles", sweet = true, salty = false, chocolatey = false)
//
//val p = Person("Will", 6, c)