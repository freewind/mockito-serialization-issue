package demo

import java.io.{ByteArrayOutputStream, ObjectOutputStream}

object Checker {
  def checkSerializable(obj: AnyRef, name: String) = {
    println("### checking " + name + ": " + obj.getClass)
    new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(obj)
    println(name + " ok")
  }
}
