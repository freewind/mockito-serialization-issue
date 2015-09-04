package demo

import org.mockito.Mockito

object MockitoDemo extends App {

  def ok(): Unit = {
    val task = new Task(new FileReader)
    Checker.checkSerializable(task, "no-mockito")
  }

  def failed1(): Unit = {
    val fileReader = Mockito.mock(classOf[FileReader])
    val config = Mockito.mock(classOf[Config])
    Mockito.when(fileReader.read(config)).thenReturn("mocked")
    val task = new Task(fileReader)
    Checker.checkSerializable(task, "with-mockito1")
  }

  def failed2(): Unit = {
    val fileReader = Mockito.mock(classOf[FileReader])
    val config = Mockito.mock(classOf[Config], Mockito.withSettings().serializable())
    Mockito.when(fileReader.read(config)).thenReturn("mocked")
    val task = new Task(fileReader)
    Checker.checkSerializable(task, "with-mockito2")
  }

  ok()

  try failed1() catch {
    case e: Throwable => e.printStackTrace()
  }

  try failed2() catch {
    case e: Throwable => e.printStackTrace()
  }

}
