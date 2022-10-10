//create a map of a few states I've visited
//key is state code... value is state
//create a list of state codes in the order you visited them
//convert the list from codes to list of places i've visited in order

val stateCodes = Map(
  "FL" -> "Florida",
  "OR" -> "Oregon",
  "ID" -> "Idaho",
  "MO" -> "Missouri",
  "NM" -> "New Mexico"
)

val brandoJourney = List(
  "MO",
  "FL",
  "OR",
  "ID",
  "NM"
)

val states = brandoJourney.map(state => {
  stateCodes.getOrElse(state, "Unknown")
})

println(states)