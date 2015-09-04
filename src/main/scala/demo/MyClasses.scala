package demo

// Not Serializable
class Config

object FileReader extends FileReader
class FileReader extends Serializable {
  def read(config: Config): String = config.getClass.toString
}

object Task extends Task(FileReader)
class Task(fileReader: FileReader) extends Serializable {
  def execute(config: Config): String = {
    fileReader.read(config)
  }
}
