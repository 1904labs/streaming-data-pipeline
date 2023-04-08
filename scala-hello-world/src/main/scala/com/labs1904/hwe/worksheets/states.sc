val destinations: Map[String, String] = Map(
  "DC" -> "Washington",
  "MO" -> "Springfield",
  "CA" -> "Orange County",
  "FL" -> "Disney World"
)

val visits = List("MO", "CA", "FL", "DC")

visits.flatMap((v) => destinations.get(v))