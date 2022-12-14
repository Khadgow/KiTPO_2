package model.cycleList

import model.comparator.Comparator
import model.prototype.UserType

import java.io.{BufferedReader, BufferedWriter, FileReader, FileWriter, IOException}
import scala.language.postfixOps

class CycleList {

  private var head: Node = _
  private var length: Int = 0
  private var comparator: Comparator = null


  private class Node(var data: Any) extends Serializable {
    var next: Node = _
    var prev: Node = _

    override def toString: String = data.toString
  }

  def this(comparator: Comparator) = {
    this()
    this.comparator = comparator
  }

  def add(data: Any): Unit = {
    if (head == null) {
      var node: Node = new Node(data)
      node.next = node
      node.prev = node
      head = node
      length += 1
    } else {
      var tail: Node = head.prev
      var node: Node = new Node(data)
      node.next = head
      head.prev = node
      node.prev = tail
      tail.next = node
      length += 1
    }
  }

  def add(data: Any, index: Int): Unit = {
    var tmp = getNode(index)
    var newNode = new Node(data)
    var tail = head.prev
    if (tmp ne head) {
      tmp.prev.next = newNode
      newNode.prev = tmp.prev
    }
    else head = newNode
    newNode.next = tmp
    tmp.prev = newNode
    tail.next = head
    head.prev = tail
    length += 1
  }

  def sort(): Unit = {
    if (head != null && (head.next ne head) && (head.prev ne head)) {
      var tail = head.prev
      tail.next = null
      head.prev = null
      head = mergeSort(head)
      tail = getNode(length - 1)
      tail.next = head
      head.prev = tail
    }
  }

  def sortFuncStyle(): CycleList = {
    if (length <= 1)
      this
    else {
      val sortedList = new CycleList(comparator)
      var leftList = new CycleList(comparator)
      var rightList = new CycleList(comparator)
      val middle = length / 2;
      forEachInRange(
        x => {
          leftList.add(x)
        },
        x => {
          rightList.add(x)
        },
        0, middle, length
      )


      leftList = leftList.sortFuncStyle()
      rightList = rightList.sortFuncStyle()

      var left = leftList.head
      var right = rightList.head
      var leftHead = 0
      var rightHead = 0

      while (leftHead < leftList.length && rightHead < rightList.length) {
        if (comparator.compare(left.data, right.data) > 0) {
          sortedList.add(right.data)
          right = right.next
          rightHead = rightHead + 1
        }
        else if (comparator.compare(left.data, right.data) <= 0) {
          sortedList.add(left.data)
          left = left.next
          leftHead = leftHead + 1
        }
      }

        while (leftHead < leftList.length) {
          sortedList.add(left.data)
          left = left.next
          leftHead = leftHead + 1
        }

        while (rightHead < rightList.length) {
          sortedList.add(right.data)
          right = right.next
          rightHead = rightHead + 1
        }

      sortedList
    }
  }

  private def mergeSort(headNode: Node): Node = {
    if (headNode == null || headNode.next == null) {
      return headNode
    }
    val middle = getMiddle(headNode)
    val middleNext = middle.next
    middle.next = null
    val left = mergeSort(headNode)
    val right = mergeSort(middleNext)
    merge(left, right)
  }

  private def merge(first: Node, second: Node) = {
    var firstNode: Node = first
    var secondNode: Node = second
    val merged = new Node(null)
    var temp = merged
    var tail = head.prev
    while ( {
      firstNode != null && secondNode != null
    }) {
      if (comparator.compare(firstNode.data, secondNode.data) < 0) {
        temp.next = firstNode
        firstNode.prev = temp
        firstNode = firstNode.next
      }
      else {
        temp.next = secondNode
        secondNode.prev = temp
        secondNode = secondNode.next
      }
      temp = temp.next
    }
    while ( {
      firstNode != null
    }) {
      temp.next = firstNode
      firstNode.prev = temp
      firstNode = firstNode.next
      temp = temp.next
    }
    while ( {
      secondNode != null
    }) {
      temp.next = secondNode
      secondNode.prev = temp
      secondNode = secondNode.next
      temp = temp.next
      tail = temp
    }
    merged.next
  }

  private def getMiddle(h: Node) = {
    var fast = h.next
    var slow = h
    while (fast != null) {
      fast = fast.next
      if (fast != null) {
        slow = slow.next
        fast = fast.next
      }
    }
    slow
  }

  def remove(index: Int): Unit = {
    var tmp = getNode(index)
    var tail: Node = head.prev
    if (tmp != head) {
      tmp.prev.next = tmp.next
    }
    else {
      head = tmp.next
    }
    if (tmp != tail) {
      tmp.next.prev = tmp.prev
    }
    else {
      tail = tmp.prev
    }
    tmp.next = null
    tmp.prev = null
    tail.next = head
    head.prev = tail
    length -= 1
  }

  def getByIndex(index: Int): Any = getNode(index).data

  def getLength: Int = length

  private def getNode(index: Int): Node = {
    if (index < 0 || index >= length) throw new IndexOutOfBoundsException()
    var tmp = head
    for (_ <- 0 until index) {
      tmp = tmp.next
    }
    tmp
  }

  def forEach(iterator: Any => Unit): Unit = {
    var tmp = head
    for (i <- 0 until length) {
      iterator(tmp.data)
      tmp = tmp.next
    }
  }

  def forEachInRange(iteratorBefore: Any => Unit, iteratorAfter: Any => Unit, start: Int, middle: Int, end: Int): Unit = {
    var tmp = head
    for (i <- start until middle) {
      iteratorBefore(tmp.data)
      tmp = tmp.next
    }
    for (i <- middle until end) {
      iteratorAfter(tmp.data)
      tmp = tmp.next
    }
  }

  def forEachReverse(iterator: Any => Unit): Unit = {
    var tmp = head
    for (i <- 0 until length) {
      iterator(tmp.data)
      tmp = tmp.prev
    }
  }

  def printList(): Unit = {
    var tmp = head
    for (i <- 0 until length) {
      System.out.print(i + ") ")
      System.out.println(tmp.data)
      tmp = tmp.next
    }
  }

  override def toString: String = {
    var str = ""
    var tmp: Node = head
    for (i <- 0 until length) {
      str = str + tmp.data + "\n"
      tmp = tmp.next
    }
    str
  }

  def clearList(): Unit = {
    head = null
    length = 0
  }


  def save(userType: UserType, fileName: String): Unit = {
    try {
      val writer = new BufferedWriter(new FileWriter(fileName))
      try {
        writer.write(userType.typeName + "\n")
        this.forEach((el: Any) => {
          def foo(el: Any) = {
            try writer.write(el.toString() + "\n")
            catch {
              case e: IOException =>
                e.printStackTrace()
            }
          }

          foo(el)
        })
      } catch {
        case e: IOException =>
          e.printStackTrace()
      } finally if (writer != null) writer.close()
    }
  }


  def load(userType: UserType, fileName: String): Unit = {
    clearList()
    val br = new BufferedReader(new FileReader(fileName))
    var line: String = null
    line = br.readLine
    if (!(userType.typeName == line)) throw new Exception("Wrong file structure")
    line = br.readLine
    while (line != null) {
      add(userType.parseValue(line))
      line = br.readLine
    }
  }

}