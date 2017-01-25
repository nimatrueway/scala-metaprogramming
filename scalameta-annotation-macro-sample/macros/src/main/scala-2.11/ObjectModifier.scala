package ir.taheri

import scala.meta._

class addHello extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"object $name extends { ..$parents } with ..$traits { $param => ..$stats }" = defn
    val helloMethod = q"""def sayHello = println("Hello Scala !") """
    q"object $name extends { ..$parents } with ..$traits { $param => ..${helloMethod +: stats} }"
  }
}