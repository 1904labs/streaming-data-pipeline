import scala.collection.immutable.HashMap

val places = Map( // val places: Map[String, String] = Map(
  "MO" -> "Columbia",
  "MI" -> "Ludington",
  "NY" -> "NYC")

val stateCodes = List("MI", "MO", "NY", "Test")

stateCodes.flatMap(x => places.get(x))




