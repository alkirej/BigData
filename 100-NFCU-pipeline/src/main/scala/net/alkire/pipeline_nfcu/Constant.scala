package net.alkire.pipeline_nfcu

import java.util.Properties

object Constant {
    // Spark Session instantiation constants
    val Master:  String = "local[*]"
    val AppName: String = "pipeline"

    // .csv file name constants
    val DataDir:    String = "/home/jeff/git/BigData/100-NFCU-pipeline/data"
    val FilePrefix: String = "health-violations-"
    val FileSuffix: String = ".csv"

    // List of files (one for each city)
    val FileMainName: Array[String] = Array( "louisville-kt",
                                             "pittsburgh-pa",
                                             "montgomery-county-md",
                                             "new-orleans-la",
                                             "austin-tx",
                                             "anchorage-ak",
                                             "los-angeles-ca",
                                             "chicago-il"
                                           )

    // Use this name when creating temporary tables
    val TempViewName    = "original_data"

    val ColumnNames:Array[String] = Array(  "restaurant_name",
                                            "inspection_date",
                                            "inspection_score",
                                            "restaurant_address",
                                            "restaurant_city",
                                            "restaurant_state",
                                            "restaurant_zip",
                                            "inspection_description",
                                            "inspection_type",
                                            "inspection_violation"
                                         )
    // Sql to build similar data for all cities despite different starting schemas.
    val SqlByCity: Array[String] = Array(
                                        // LOUISVILLE, KENTUCKY
                                            s"""SELECT eSTABLISHMENTnAME as ${ColumnNames(0)},
                                            |          InspectionDate as ${ColumnNames(1)},
                                            |          score as ${ColumnNames(2)},
                                            |          Address as ${ColumnNames(3)},
                                            |          City as ${ColumnNames(4)},
                                            |          State as ${ColumnNames(5)},
                                            |          Zip as ${ColumnNames(6)},
                                            |          '' as ${ColumnNames(7)},
                                            |          TypeDescription as ${ColumnNames(8)},
                                            |          '' as ${ColumnNames(9)}
                                            |  FROM original_data""".stripMargin,
                                        // PITTSBURGH, PENNSYLVANIA
                                            s"""SELECT facility_name as ${ColumnNames(0)},
                                            |          inspect_dt as ${ColumnNames(1)},
                                            |          IF( 'T'=low, 'low', IF( 'T'=medium, 'medium', IF( 'T'=high, 'high', 'other' ))) as ${ColumnNames(2)},
                                            |          CONCAT(num, ' ' , street) as ${ColumnNames(3)},
                                            |          city as ${ColumnNames(4)},
                                            |          state as ${ColumnNames(5)},
                                            |          zip as ${ColumnNames(6)},
                                            |          description_new as ${ColumnNames(7)},
                                            |          description as ${ColumnNames(8)},
                                            |          description_new as ${ColumnNames(9)}
                                            |  FROM original_data""".stripMargin,
                                        // MONTGOMERY COUNTY, MARYLAND
                                            s"""SELECT business_name as ${ColumnNames(0)},
                                            |          inspection_date as ${ColumnNames(1)},
                                            |          inspection_result as ${ColumnNames(2)},
                                            |          business_address as ${ColumnNames(3)},
                                            |          business_city as ${ColumnNames(4)},
                                            |          business_state as ${ColumnNames(5)},
                                            |          business_postal_code as ${ColumnNames(6)},
                                            |          inspection_type as ${ColumnNames(7)},
                                            |          inspection_type as ${ColumnNames(8)},
                                            |          violation_description as ${ColumnNames(9)}
                                            |  FROM original_data""".stripMargin,
                                        // NEW ORLEANS, LOUISIANA
                                            s"""SELECT ViolatorName as ${ColumnNames(0)},
                                            |          CreateDateTime as ${ColumnNames(1)},
                                            |          ResolutionType as ${ColumnNames(2)},
                                            |          ViolationAddress as ${ColumnNames(3)},
                                            |          '' as ${ColumnNames(4)},
                                            |          '' as ${ColumnNames(5)},
                                            |          '' as ${ColumnNames(6)},
                                            |          ComplaintType as ${ColumnNames(7)},
                                            |          Category as ${ColumnNames(8)},
                                            |          Description as ${ColumnNames(9)}
                                            |  FROM original_data""".stripMargin,
                                        // AUSTIN, TEXAS
                                            s"""SELECT `Restaurant Name` as ${ColumnNames(0)},
                                            |          `Inspection Date` as ${ColumnNames(1)},
                                            |          Score as ${ColumnNames(2)},
                                            |          Address as ${ColumnNames(3)},
                                            |          '' as ${ColumnNames(4)},
                                            |          '' as ${ColumnNames(5)},
                                            |          `Zip Code` as ${ColumnNames(6)},
                                            |          `Process Description` as ${ColumnNames(7)},
                                            |          'N/A' as ${ColumnNames(8)},
                                            |          'N/A' as ${ColumnNames(9)}
                                            |  FROM original_data""".stripMargin,
                                        // ANCHORAGE, ALASKA
                                            s"""SELECT business_name as ${ColumnNames(0)},
                                            |          inspection_date as ${ColumnNames(1)},
                                            |          inspection_score as ${ColumnNames(2)},
                                            |          business_address as ${ColumnNames(3)},
                                            |          business_city as ${ColumnNames(4)},
                                            |          business_state as ${ColumnNames(5)},
                                            |          business_postal_code as ${ColumnNames(6)},
                                            |          inspection_description as ${ColumnNames(7)},
                                            |          inspection_type as ${ColumnNames(8)},
                                            |          violation_description as ${ColumnNames(9)}
                                            |  FROM original_data""".stripMargin,
                                        // LOS ANGELES, CALIFORNIA
                                            s"""SELECT facility_name as ${ColumnNames(0)},
                                            |          activity_date as ${ColumnNames(1)},
                                            |          score as ${ColumnNames(2)},
                                            |          facility_address as ${ColumnNames(3)},
                                            |          facility_city as ${ColumnNames(4)},
                                            |          facility_state as ${ColumnNames(5)},
                                            |          facility_zip as ${ColumnNames(6)},
                                            |          service_description as ${ColumnNames(7)},
                                            |          pe_description as ${ColumnNames(8)},
                                            |          violation_description as ${ColumnNames(9)}
                                            |  FROM original_data""".stripMargin,
                                        // CHICAGO, ILLINOIS
                                            s"""SELECT `DBA Name` as ${ColumnNames(0)},
                                            |          `Inspection Date` as ${ColumnNames(1)},
                                            |          Results as ${ColumnNames(2)},
                                            |          Address as ${ColumnNames(3)},
                                            |          City as ${ColumnNames(4)},
                                            |          State as ${ColumnNames(5)},
                                            |          Zip as ${ColumnNames(6)},
                                            |          `Inspection Type` as ${ColumnNames(7)},
                                            |          Risk as ${ColumnNames(8)},
                                            |          Violations as ${ColumnNames(9)}
                                            |  FROM original_data""".stripMargin
                                      )

    // Indexes into array for each city
    val FnIdxLouisville = 0
    val FnIdxPittsburg  = 1
    val FnIdxMongtomery = 2
    val FnIdxNewOrleans = 3
    val FnIdxAustin     = 4
    val FnIdxAnchorage  = 5
    val FnIdxLosAngeles = 6
    val FnIdxChicago    = 7


    // JDBC Connection properties
    val JdbcDriverClass = "com.teradata.jdbc.TeraDriver"
    val JdbcHost  = "192.168.0.121"
    val JdbcDb    = "task100"
    val JdbcUser  = "dbc"
    val JdbcPw    = "dbc"
    val JdbcDbTbl = "task100.health_visit"

    val JdbcUrl = s"jdbc:teradata://${JdbcHost}/${JdbcDb}"

    val ConnProps = new Properties
    ConnProps.put("user", s"${JdbcUser}")
    ConnProps.put("password", s"${JdbcPw}")

}
