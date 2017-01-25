package ir.taheri

object BlackBoxMacroApp extends App {

  case class SampleCaseClass01(
    field01: Int,
    field02: String
  )

  val serializer = BlackBoxMacro.createJsonPrinter[SampleCaseClass01]
  val anObject = SampleCaseClass01(field01 = 20, field02 = "Nima Taheri")

  println(serializer(anObject))

}