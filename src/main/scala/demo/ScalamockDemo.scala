package demo

import java.io.{ByteArrayOutputStream, ObjectOutputStream}

import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class ScalamockDemo extends FunSuite with MockFactory {

  def checkSerializable(obj: AnyRef, name: String) = {
    println("### checking " + name + ": " + obj.getClass)
    new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(obj)
    println(name + " ok")
  }

  test("should serialized") {
    val fileReader = stub[FileReader]
    val config = mock[Config]
    (fileReader.read _).when(config).returns("mocked")
    val task = new Task(fileReader)

    checkSerializable(task, "with-mockito1")
  }

}
