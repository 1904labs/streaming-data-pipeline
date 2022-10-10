import scala.collection.mutable.ListBuffer


val i = 12376
val iAsList = i.toString.map(_.asDigit).toList;
val iAsSet = iAsList.toSet[Int].subsets.map(_.toList);


val finalIndex = iAsList.last;
val nextFinalIndex = iAsList(iAsList.length - 2);
val iAppended = iAsList.slice(0, iAsList.length-2)

if (finalIndex <= nextFinalIndex){
  -1
} else {
  val nextBiggest = new ListBuffer[Int]();
  iAppended.foreach(num => {
    nextBiggest += num
  });
  nextBiggest += finalIndex;
  nextBiggest += nextFinalIndex;
  nextBiggest.mkString.toInt
}