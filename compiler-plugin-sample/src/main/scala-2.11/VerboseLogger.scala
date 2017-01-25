package ir.taheri

import scala.tools.nsc._
import scala.tools.nsc.plugins._

class VerboseLogger(val global: Global) extends Plugin {

  override val name: String = "verbose-logger"
  override val description: String = "logs every statement with in logger block"
  /** Each compiler plugin can have several components inside it */
  override val components = List[PluginComponent](MyPlugin)

  object MyPlugin extends PluginComponent {

    override val phaseName: String = VerboseLogger.this.name
    override val global: Global = VerboseLogger.this.global
    override def description: String = VerboseLogger.this.description
    /** Each plugin component can be set to run after a specific phase */
    override val runsAfter = List("parser")

    import global._

    def newPhase(prev: Phase): StdPhase = new StdPhase(prev) {
      def apply(unit: CompilationUnit) {
        new MyTransformer().transformUnit(unit)
      }
    }

    class MyTransformer extends Transformer {
      override def transform(tree: Tree): Tree = {
        val treeT = super.transform(tree)
        treeT match {
          // match tree nodes that represent val-definition (e.g. "private val myvar: Int = 10")
          case node @ q"$mod val $v: $tpe = $value" if !value.isEmpty =>
            // log the node
            log(s"Writing stdout logger for >> ${show(node)}")
            // change initial-value expression to a closure which prints the value before assigning it to the variable
            q"$mod val $v: $tpe = { val $$tmp = $value; println($$tmp); $$tmp };"
          case _ =>
            treeT
        }
      }
    }

  }

}