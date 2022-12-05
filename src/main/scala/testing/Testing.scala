package testing

import model.cycleList.CycleList
import model.factory.FactoryType

class Testing {

  private val INT_FILE_SAVE = "integer.dat"
  private val TIME_FILE_SAVE = "time.dat"

  def testIntegerType(): Unit = {
    val factoryType = new FactoryType
    System.out.println("\n--------------TEST FOR Integer-------------")
    var userType = factoryType.getBuilderByName("Integer")
    var cycleList = new CycleList(userType.getTypeComparator)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    System.out.println("---------PRINT CYCLE LIST---------")
    cycleList.printList()
    System.out.println("-----SAVE TO FILE .DAT----")
    try {
      cycleList.save(userType, INT_FILE_SAVE)
      System.out.println("Saving is successful!")
    } catch {
      case e: Exception =>
        throw new RuntimeException(e)
    }
    System.out.println("\n----GET NODE BY INDEX 3, 4, 5, 6----")
    System.out.println("3) = " + cycleList.getByIndex(3).toString)
    System.out.println("4) = " + cycleList.getByIndex(4).toString)
    System.out.println("5) = " + cycleList.getByIndex(5).toString)
    System.out.println("6) = " + cycleList.getByIndex(6).toString)
    System.out.println("\n---DELETE NODE BY INDEX 3, 4, 5, 6--")
    cycleList.remove(3)
    cycleList.remove(3)
    cycleList.remove(3)
    cycleList.remove(3)
    cycleList.printList()
    System.out.println("-----------SORTING----------")
    cycleList.sort()
    cycleList.printList()
    System.out.println("---LOAD FROM FILE----")
    cycleList.load(userType, INT_FILE_SAVE)
    cycleList.printList()
    System.out.println("-----------SORTING BY FUNCTIONAL STYLE----------")
    cycleList = cycleList.mergeSortFuncStyle()
    cycleList.printList()
    System.out.println("---------ITERATOR-----------")
    cycleList.forEach(System.out.println)
    System.out.println("---------ITERATOR REVERSE-----------")
    cycleList.forEachReverse(System.out.println)
  }

  def testTimeType(): Unit = {
    val factoryType = new FactoryType
    System.out.println("\n--------------TEST FOR Time-------------")
    var userType = factoryType.getBuilderByName("Time")
    var cycleList = new CycleList(userType.getTypeComparator)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    System.out.println("---------PRINT CYCLE LIST---------")
    cycleList.printList()
    System.out.println("-----SAVE TO FILE .DAT----")
    try {
      cycleList.save(userType, TIME_FILE_SAVE)
      System.out.println("Saving is successful!")
    } catch {
      case e: Exception =>
        throw new RuntimeException(e)
    }
    System.out.println("\n----GET NODE BY INDEX 3, 4, 5, 6----")
    System.out.println("3) = " + cycleList.getByIndex(3).toString)
    System.out.println("4) = " + cycleList.getByIndex(4).toString)
    System.out.println("5) = " + cycleList.getByIndex(5).toString)
    System.out.println("6) = " + cycleList.getByIndex(6).toString)
    System.out.println("\n---DELETE NODE BY INDEX 3, 4, 5, 6--")
    cycleList.remove(3)
    cycleList.remove(4)
    cycleList.remove(5)
    cycleList.remove(6)
    cycleList.printList()
    System.out.println("-----------SORTING----------")
    cycleList.sort()
    cycleList.printList()
    System.out.println("---LOAD FROM FILE----")
    cycleList.load(userType, TIME_FILE_SAVE)
    cycleList.printList()
    System.out.println("-----------SORTING BY FUNCTIONAL STYLE----------")
    cycleList = cycleList.mergeSortFuncStyle()
    cycleList.printList()
    System.out.println("---------ITERATOR-----------")
    cycleList.forEach(System.out.println)
    System.out.println("---------ITERATOR REVERSE-----------")
    cycleList.forEachReverse(System.out.println)
  }
}