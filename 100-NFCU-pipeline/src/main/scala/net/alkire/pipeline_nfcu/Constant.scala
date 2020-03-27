package net.alkire.pipeline_nfcu

object Constant {
    val Master:  String = "local[*]"
    val AppName: String = "nfcu-pipeline"

    val DataDir:    String = "/home/jeff/git/BigData/100-NFCU-pipeline/data"
    val FilePrefix: String = "health-violations-"

    val FileSuffix: Array[String] = Array( ""
                                         )
}
