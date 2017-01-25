package ir.taheri

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

object WhiteBoxMacro {

  import ReturnType._

  def generateCustomObject(returnTypeAST: ReturnType): Any = macro generateCustomClassObject

  def generateCustomClassObject(c: whitebox.Context)(returnTypeAST: c.Expr[ReturnType]) = {
    import c.universe._

    /**
      * we need the actual evaluated value of AST of returnType,
      * in order to evaluate we need to untype-check it before evaluation. (read context.eval documentation)
      */
    val returnType = c.eval(c.Expr[ReturnType.ReturnType](c.untypecheck(returnTypeAST.tree)))

    returnType match {
      case ABC =>
        q"""
           case class ttmp(a: String, b: String, c: String);
           ttmp("aaa", "bbb", "ccc")
         """
      case Z =>
        q"""
           case class tttmp(z: Int);
           tttmp(100)
           """
    }
  }

}

object ReturnType extends Enumeration {
  type ReturnType = Value
  val ABC, Z = Value
}