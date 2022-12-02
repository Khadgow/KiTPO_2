package model.prototype

import model.comparator.Comparator

import java.io.InputStream

trait UserType {
  def typeName: String

  def create: AnyRef

  def clone(obj: AnyRef): AnyRef

  def readValue(inputStream: InputStream): AnyRef

  def parseValue(line: String): AnyRef

  def getTypeComparator: Comparator
}