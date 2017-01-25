## What this sample does ?

**VerboseLogger**  is a simple compiler plugin which adds a phase `verbose-logger` to *scalac*. This phase applies a simple transformation to the code: It modifies every `val` statement in the scala source file such that the value will be printed before assignment to the immutable-variable.

File | Explanation 
-------------------------------------|-------------------------------------------------
src/main/resources/scalac-plugin.xml | plugin descriptor which will be read by `scalac`
src/main/scala-2.11/VerboseLogger.scala | the compiler plugin
test/main/scala-2.11/samplescalafile.scala | sample source file the plugin can be tested on
run.sh | runs `scalac` with **VerboseLogger** plugin on samplescalafile.scala, and prints transformed AST after **verbose-logger** phase  


## How to run ?

Goto the project root directory then run:
`sbt test:compile && ./run.sh`