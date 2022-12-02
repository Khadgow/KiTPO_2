package model.cycleList

trait Iterator[T] {
  def toDo(data: T): Unit
}