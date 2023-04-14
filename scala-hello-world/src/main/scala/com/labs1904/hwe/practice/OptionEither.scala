package com.labs1904.hwe.practice

import scala.Console.println

case class Item(description: String, price: Option[Int])

case class WeatherStation(name: String, temperature: Option[Int])

object OptionEither {
  /*
    Returns age of a dog when given a human age.
    Returns None if the input is None.
  */
  def dogAge(humanAge: Option[Int]): Option[Int] = {
    if (humanAge.isEmpty) None else {
      val dogAge = humanAge.get * 7
      Option(dogAge)
    }
  }

  /*
    Returns the total cost af any item.
    If that item has a price, then the price + 7% of the price should be returned.
  */
  def totalCost(item: Item): Option[Double] = {
    if (item.price.isEmpty) None else {
      val costPlusTax = item.price.get * 1.07
      Option(costPlusTax)
    }
  }

  /*
    Given a list of weather temperatures, calculates the average temperature across all weather stations.
    Some weather stations don't report temperature
    Returns None if the list is empty or no weather stations contain any temperature reading.
   */
  def averageTemperature(temperatures: List[WeatherStation]): Option[Int] = {
    if (temperatures.isEmpty) None else {
      val someTemps = Some(temperatures).get
      val recordedTemps : List[Int] = someTemps.flatMap(WeatherStation => WeatherStation.temperature)
      val sumOfTemperature = recordedTemps.reduce((a, b) => a+b)
      val averageTemperature = sumOfTemperature / recordedTemps.size
      Option(averageTemperature)
    }

  }
}
