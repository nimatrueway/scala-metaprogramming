import scala.meta._
import scala.meta.transversers._

object SimpleTransformer extends Transformer {

  override def apply(tree: Tree): Tree = {
    tree match {
      case q"val ${variable: Pat.Var.Term}: $tpe = $value" =>
        val varName = Lit(variable.name.toString)
        val replaceWith =
          q"""
              val $variable: $tpe = {
                val tmp = $value;
                println("Variable " + $varName + " is initialized with " + tmp);
                tmp
              }
            """
        replaceWith
      case _ =>
        super.apply(tree)
    }
  }

}