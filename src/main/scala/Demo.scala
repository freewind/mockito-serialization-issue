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

  def failed(): Unit = {
    val fileReader = Mockito.mock(classOf[FileReader])
    val config = Mockito.mock(classOf[Config])
    Mockito.when(fileReader.read(config)).thenReturn("mocked")
    val task = new Task(fileReader)
    checkSerializable(task, "with-mockito")
  }

  ok()
  failed()

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
