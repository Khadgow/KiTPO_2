package model.usertype

class TimeClass() extends Serializable {
  try {
    setHour(11)
    setMinute(11)
    setSecond(11)
  } catch {
    case ex: Exception =>
      System.out.println("Bad time")
  }
  private var hour = 0
  private var minute = 0
  private var second = 0

  def this(hour: Int, minute: Int, second: Int) = {
    this()
    this.hour = hour
    this.minute = minute
    this.second = second
  }

  def getHour: Int = hour

  @throws[Exception]
  def setHour(hour: Int): Unit = {
    if (hour < 0 || hour > 23) throw new Exception("Bad hour")
    this.hour = hour
  }

  def getMinute: Int = minute

  @throws[Exception]
  def setMinute(minute: Int): Unit = {
    if (minute < 0 || minute > 59) throw new Exception("Bad minute")
    this.minute = minute
  }

  def getSecond: Int = second

  @throws[Exception]
  def setSecond(second: Int): Unit = {
    if (second < 0 || second > 59) throw new Exception("Bad second")
    this.second = second
  }

  override def toString: String = {
    var total = ""
    if (hour < 10) total += "0"
    total += String.valueOf(hour)
    total += ":"
    if (minute < 10) total += "0"
    total += String.valueOf(minute)
    total += ":"
    if (second < 9) total += "0"
    total += String.valueOf(second)
    total
  }
}