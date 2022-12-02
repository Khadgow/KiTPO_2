package model.prototype

import model.comparator.{Comparator, TimeComparator}
import model.usertype.TimeClass

import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.util.Random
import java.util.stream.Collectors

class TimeType extends UserType {
  override def typeName = "Time"

  override def create: AnyRef = {
    val minHour = 0
    val maxHour = 23
    val minTime = 0
    val maxTime = 59
    val rand = new Random
    val hour = rand.nextInt(maxHour - minHour)
    val minute = rand.nextInt(maxTime - minTime)
    val second = rand.nextInt(maxTime - minTime)
    var dateTimeValue = new TimeClass()
    try dateTimeValue = new TimeClass(hour, minute, second)
    catch {
      case ex: Exception =>
        System.out.println("Bad time, generating using a static values")
        dateTimeValue = new TimeClass
    }
    dateTimeValue
  }

  override def clone(obj: AnyRef): AnyRef = {
    var copyDateTime = new TimeClass()
    try copyDateTime = new TimeClass(obj.asInstanceOf[TimeClass].getHour, obj.asInstanceOf[TimeClass].getMinute, obj.asInstanceOf[TimeClass].getSecond)
    catch {
      case ex: Exception =>
        copyDateTime = new TimeClass
    }
    copyDateTime
  }

  override def readValue(inputStream: InputStream): AnyRef = parseValue(new BufferedReader(new InputStreamReader(inputStream)).lines.collect(Collectors.joining("\n")))

  override def parseValue(line: String): AnyRef = {
    val timeStr = line.split(":")
    val timeInt = new Array[Integer](3)
    for (i <- 0 until 3) {
      timeInt(i) = timeStr(i).toInt
    }
    var dateTimeValue = new TimeClass
    try dateTimeValue = new TimeClass(timeInt(0), timeInt(1), timeInt(2))
    catch {
      case ex: Exception =>
        System.out.println("Bad time, generating using a static values")
        dateTimeValue = new TimeClass()
    }
    dateTimeValue
  }

  override def getTypeComparator: Comparator = {
    val comparator = new TimeComparator
    comparator
  }
}