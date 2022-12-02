package model.prototype

import model.comparator.{Comparator, IntegerComparator}
import model.usertype.IntegerClass

import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.util.Random
import java.util.stream.Collectors

class IntegerType extends UserType {
  override def typeName = "Integer"

  override def create: AnyRef = {
    val min = -1000
    val max = 1000
    val rand = new Random
    val intValue = new IntegerClass(rand.nextInt(max - min) + min)
    intValue
  }

  override def clone(obj: AnyRef): AnyRef = {
    val copyInt = new IntegerClass(obj.asInstanceOf[IntegerClass].getValue)
    copyInt
  }

  override def readValue(inputStream: InputStream): AnyRef = {
    val integerClass = new IntegerClass(new BufferedReader(new InputStreamReader(inputStream)).lines.collect(Collectors.joining("\n")).toInt)
    integerClass
  }

  override def parseValue(line: String) = new IntegerClass(line.toInt)

  override def getTypeComparator: Comparator = {
    val comparator = new IntegerComparator
    comparator
  }
}