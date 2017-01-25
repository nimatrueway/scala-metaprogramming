package ir.taheri

import scala.language.experimental.macros
import scala.reflect.macros.blackbox._

class addHello extends scala.annotation.StaticAnnotation {
  /**
    * <p> Why annottees are a list of expressions ? Here's why : </p>
    * <ul>
    * <li>If a class is annotated and it has a companion, then both are passed into the macro. (But not vice versa - if an object is annotated and it has a companion class, only the object itself is expanded).</li>
    * <li>If a parameter of a class, method or type member is annotated, then it expands its owner. First comes the annottee, then the owner and then its companion as specified by the previous rule.</li>
    *
    * [[http://docs.scala-lang.org/overviews/macros/annotations Read More]]
    */
  def macroTransform(annottees: Any*): Any = macro addHello.impl
}

object addHello {

  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    val result = {
      annottees.map(_.tree).toList match {
        // match annottees with definition of a single object
        case List(q"object $tname extends { ..$earlydefns } with ..$parents { $self => ..$body }") =>
          // create AST of a method named 'sayHello' which prints something
          val helloMethod = q"""def sayHello = println("Hello Scala !") """
          // create a new object with our method added to body of the matched object
          val result = q"object $tname extends { ..$earlydefns } with ..$parents { $self => ..${helloMethod +: body} }"
          result
      }
    }
    c.Expr[Any](result)
  }

}