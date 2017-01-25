package ir.taheri

object WhiteBoxMacroApp extends App {
  import ReturnType._

  // type of the object ClassGenerator returns can differ and IT IS EXPOSED TO OUTSIDE THE MACRO

  val objectified1 = WhiteBoxMacro.generateCustomObject(ABC)
  println(objectified1.a)
  println(objectified1.b)
  println(objectified1.c)

  val objectified2 = WhiteBoxMacro.generateCustomObject(Z)
  println(objectified2.z)

}