package net.alkire.pipeline_nfcu

object Constant {
    // Spark Session instantiation constants
    val Master:  String = "local[*]"
    val AppName: String = "nfcu-pipeline"

    // .csv file name constnats
    val DataDir:    String = "/home/jeff/git/BigData/100-NFCU-pipeline/data"
    val FilePrefix: String = "health-violations-"
    val FileSuffix: String = ".csv"

    // List of files (one for each city)
    val FileMainName: Array[String] = Array( "louisville-kt",
                                             "pittsburgh-pa",
                                             "montgomery-county-md",
                                             "hartford-ct",
                                             "new-orleans-la",
                                             "new-york-ny",
                                             "austin-tx",
                                             "anchorage-ak",
                                             "los-angeles-ca",
                                             "chicago-il"
                                           )

    // Indexes into array for each city
    val FnIdxLouisville = 0
    val FnIdxPittsburg  = 1
    val FnIdxMongtomery = 2
    val FnIdxHartford   = 3
    val FnIdxNewOrleans = 4
    val FnIdxNewYork    = 5
    val FnIdxAustin     = 6
    val FnIdxAnchorage  = 7
    val FnIdxLosAngeles = 8
    val FnIdxChicago    = 9
}
