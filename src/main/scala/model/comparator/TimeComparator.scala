package model.comparator

import model.usertype.TimeClass

import java.io.Serializable

class TimeComparator extends Comparator with Serializable {
  override def compare(o1: Any, o2: Any) = {
    val hourL = o1.asInstanceOf[TimeClass].getHour
    val hourR = o2.asInstanceOf[TimeClass].getHour
    val minuteL = o1.asInstanceOf[TimeClass].getMinute
    val minuteR = o2.asInstanceOf[TimeClass].getMinute
    val secondL = o1.asInstanceOf[TimeClass].getSecond
    val secondR = o2.asInstanceOf[TimeClass].getSecond
    val timeL = hourL * 60 * 60 + minuteL * 60 + secondL
    val timeR = hourR * 60 * 60 + minuteR * 60 + secondR
    timeL - timeR
  }
}