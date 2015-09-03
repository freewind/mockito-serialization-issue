import java.io.{ByteArrayOutputStream, ObjectOutputStream}

import org.mockito.Mockito

object Demo extends App {

  def checkSerializable(obj: AnyRef, name: String) = {
    println("### checking " + name + ": " + obj.getClass)
    new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(obj)
    println(name + " ok")
  }

  def ok(): Unit = {
    val task = new Task(new FileReader)
    checkSerializable(task, "no-mockito")
  }

  def failed1(): Unit = {
    val fileReader = Mockito.mock(classOf[FileReader])
    val config = Mockito.mock(classOf[Config])
    Mockito.when(fileReader.read(config)).thenReturn("mocked")
    val task = new Task(fileReader)
    checkSerializable(task, "with-mockito1")
  }

  def failed2(): Unit = {
    val fileReader = Mockito.mock(classOf[FileReader])
    val config = Mockito.mock(classOf[Config], Mockito.withSettings().serializable())
    Mockito.when(fileReader.read(config)).thenReturn("mocked")
    val task = new Task(fileReader)
    checkSerializable(task, "with-mockito2")
  }

  ok()

  try failed1() catch {
    case e: Throwable => e.printStackTrace()
  }

  try failed2() catch {
    case e: Throwable => e.printStackTrace()
  }

}

// Not Serializable
class Config

object FileReader extends FileReader
class FileReader extends Serializable {
  def read(config: Config): String = config.getClass.toString
}

object Task extends Task(FileReader)
class Task(fileReader: FileReader) extends Serializable {
  def execute(config: Config): Unit = {
    fileReader.read(config)
  }
}
