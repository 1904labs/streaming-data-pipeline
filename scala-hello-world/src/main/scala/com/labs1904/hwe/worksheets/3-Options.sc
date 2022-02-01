val states = Map(
  "AL" -> "Alabama",
  "AK" -> "Alaska",
  "IA" -> "Iowa",
  "IL" -> "Illinois",
  "LA" -> "Louisiana",
  "MO" -> "Missouri",
  "NE" -> "Nebraska"
)

val capitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska" -> "Juneau",
  "Arizona" -> "Phoenix",
  "Arkansas" -> "Little Rock",
  "California" -> "Sacramento",
  "Colorado" -> "Denver",
  "Connecticut" -> "Hartford"
)


states.get("AL").flatMap(capitals.get).map(_.toUpperCase)


// Option(databaseCall)



