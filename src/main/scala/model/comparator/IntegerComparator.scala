package model.comparator

import model.usertype.IntegerClass

import java.io.Serializable

class IntegerComparator extends Comparator with Serializable {
  override def compare(o1: Any, o2: Any) = o1.asInstanceOf[IntegerClass].getValue - o2.asInstanceOf[IntegerClass].getValue
}