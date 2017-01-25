import scala.meta._

object Main extends App {

    val simpleSourceCode =
    """
    |  object SimpleSourceCode {
    |    def main(args: Array[String]) = {
    |      val x: Int = 100
    |      val y: Int = 200
    |      val z: Int = 300
    |      println(s"result: $x")
    |    }
    |  }
    """.stripMargin

    val file = Input.String(simpleSourceCode)
    val parsed = file.parse[Source].get
    val transformed = SimpleTransformer(parsed)
    println(transformed.syntax)

}