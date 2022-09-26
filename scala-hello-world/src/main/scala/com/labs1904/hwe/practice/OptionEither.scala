package com.labs1904.hwe.practice

case class Item(description: String, price: Option[Int])

case class WeatherStation(name: String, temperature: Option[Int])

object OptionEither {
  /*
    Returns age of a dog when given a human age.
    Returns None if the input is None.
  */
  def dogAge(humanAge: Option[Int]): Option[Int] = {
    if (humanAge.isDefined) {
      val v1 = humanAge.getOrElse(0) * 7;
      Some(v1)
    } else None
  }

  /*
    Returns the total cost af any item.
    If that item has a price, then the price + 7% of the price should be returned.
  */
  def totalCost(item: Item): Option[Double] = {
    if (item.price.isDefined) {
      val v1 = item.price.getOrElse(0);
      Some(v1+v1*.07)
    } else None
  }

  /*
    Given a list of weather temperatures, calculates the average temperature across all weather stations.
    Some weather stations don't report temperature
    Returns None if the list is empty or no weather stations contain any temperature reading.
   */
  def averageTemperature(temperatures: List[WeatherStation]): Option[Int] = {
    var sum = 0;
    var divider = 0;
    temperatures.map(x => if (x.temperature.isDefined){
      sum = sum + x.temperature.getOrElse(0);
      divider = divider + 1});
    if (sum == 0){None}
    else Some(sum/divider)
  }
}
