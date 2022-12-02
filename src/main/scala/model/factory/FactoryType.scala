package model.factory

import model.prototype.{IntegerType, TimeType, UserType}

class FactoryType {
  val typeList : List[UserType] = List(new TimeType, new IntegerType)
  def getTypeNameList: List[String] = {
    var list: List[String] = List()
    for (t <- typeList) {
      list = list ++: List(t.typeName)
    }
    list
  }
  def getBuilderByName(name: String): UserType = {
    if (name == null) throw new NullPointerException
    for (userType <- typeList) {
      if (name == userType.typeName) return userType
    }
    throw new IllegalArgumentException
  }
}