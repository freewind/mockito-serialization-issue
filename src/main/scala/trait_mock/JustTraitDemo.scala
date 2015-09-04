package trait_mock

import demo.Checker

object JustTraitDemo extends App {

  def ok(): Unit = {
    val task = new Task
    Checker.checkSerializable(task, "no-mockito")
  }

  def failed1(): Unit = {
    val task = new Task {
      override def read(config: Config): String = {
        "mocked"
      }
    }
    Checker.checkSerializable(task, "just-use-trait")
  }


  ok()

  try failed1() catch {
    case e: Throwable => e.printStackTrace()
  }

}


// Not Serializable
class Config

trait FileReader {
  def read(config: Config): String = config.getClass.toString
}

class Task extends Serializable with FileReader {
  def execute(config: Config): String = {
    read(config)
  }
}
