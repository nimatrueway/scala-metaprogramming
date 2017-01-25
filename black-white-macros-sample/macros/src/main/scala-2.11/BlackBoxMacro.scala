package ir.taheri

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object BlackBoxMacro {

  def createJsonPrinter[T]: T => String = macro createJsonPrinterMacro[T]

  def createJsonPrinterMacro[T: c.WeakTypeTag](c: blackbox.Context): c.Tree = {
    import c.universe._

    // get all information about type T
    val tpe = weakTypeOf[T]
    // get all field-names of type T (we presume it's a case class) from its primary constructor
    val fields = tpe.typeSymbol.asClass.primaryConstructor.asMethod.paramLists.head.map(_.asTerm.name)
    val QUOTE = q""" "\"" """

    logBeforeReturn(c) {
      q"""
        (obj: $tpe) => { "{" + ${
          fields.map { field =>
            // create AST for expression: "\"" + "field" + "\"" + ": " + "\"" + obj.field.toString + "\""
            q""" $QUOTE + ${field.decodedName.toString} + $QUOTE + ": " + $QUOTE + obj.$field.toString + $QUOTE """
          } reduceLeft { (result, printedField) =>
            q""" $result + ", " + $printedField """
          }
        } + "}" }
      """
    }
  }

  /**
    * outputs a pretty-print version of tree plus its raw representation before returning it
    */
  def logBeforeReturn[T](c: blackbox.Context)(tree: T): T = {
    import c.universe._
    println("\n******************************** MACRO [Generated Source] ********************************\n")
    println(show(tree))
    println("\n******************************** MACRO [Generated AST] ********************************\n")
    println(showRaw(tree))
    println("\n******************************** MACRO ******************************** >>>\n")
    tree
  }

}