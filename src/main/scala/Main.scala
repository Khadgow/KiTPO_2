import testing.Testing

object Main {
  def main(args: Array[String]): Unit = {
    val test = new Testing
    test.testIntegerType()
    test.testTimeType()
  }
}