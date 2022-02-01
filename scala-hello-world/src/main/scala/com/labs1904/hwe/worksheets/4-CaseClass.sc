import net.liftweb.json._
import net.liftweb.json.Serialization.{read, write}

case class Candy(name: String, sweet: Boolean, salty: Boolean)

case class Person(name: String, height: Int, favoriteCandy: Candy)

val c = Candy("Skittles", sweet = true, salty = false)

val p = Person("Will", 6, c)












//JSON STUFF
//
////// create a JSON string from the Person, then print it
//implicit val formats = DefaultFormats
////
////val jsonString = write(p)
////println(jsonString)
//
//val jsonInMessageBody = "{\"name\":\"Skittles\",\"sweet\":true,\"salty\":false}"
//val jsonInMessageBodyTwo = ""
//parse(jsonInMessageBody)
//parse(jsonInMessageBodyTwo).extractOpt[Candy]
