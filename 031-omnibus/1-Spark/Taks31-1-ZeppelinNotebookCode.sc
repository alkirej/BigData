// IMPORTS
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.storage.StorageLevel
import spark.sql
import org.apache.spark.broadcast.Broadcast


// CREATE SPARK SESSION AND CONTEXT OBJECTS
val spark = SparkSession
      .builder()
      .appName("Task-31")
      .master("local[*]")
      .config("spark.sql.warehouse.dir", "/apps/hive/warehouse")
      .config("hive.metastore.uris", "thrift://localhost:9083")
      .enableHiveSupport()
      .getOrCreate()
val sc: SparkContext = spark.sparkContext


// SAMPLE SQL QUERY FOR TESTING
spark.sqlContext.sql( "USE movielens_db" )
sql("SHOW TABLES").show()


// CREATE RDD FROM SQL QUERY AND TRANSFORM IT A FEW TIMES

val test   =       sql( "SELECT * FROM genre" ).rdd
// return from query leaves us with unknown data types
val mTest  =  test.map( row => ( row.getAs[String](0), row.getAs[Int](1), 0, "notes") )
// return from map leavs us with known types
val m2Test = mTest.map( row => ( row._1, row._2, row._2*(row._2-2), row._4 +"-"+row._1 ))
val oTest  = m2Test.sortBy(_._1, ascending=true)

oTest.toDebugString



// CREATE A CHECKPOINT OF THE RDD

sc.setCheckpointDir("hdfs://sandbox-hdp.hortonworks.com:8020/RddCheckPoint")
oTest.checkpoint

// Checkpoint isn't created until an action occurs
val o2Test = oTest.sortBy( _._3, ascending=true)
o2Test.toDebugString



// CACHE / PERSIST
//o2Test.persist(StorageLevel.MEMORY_ONLY)
o2Test.cache
val o3Test = o2Test.sortBy( _._3 )
o3Test.toDebugString



// NOTE: CLASS THAT CREATES "UDFs" OR OTHERWISE WRITES VARIABLES INTO EXECUTOR CODE MUST BE SERIALIZABLE
class Test extends Serializable {
    def trying(): Unit = {   
        val intTable: List[Int] = List[Int]( 10, 8, 9, 4, 1, 7, 2, 3, 5, 6 )
        val btRdd = sc.parallelize( intTable )
        val bcValue:Broadcast[Int] = sc.broadcast( 5 )

        btRdd.map( x => x * bcValue.value ).take(10).foreach(println)
    } // def
} // class

val blue: Test = new Test
blue.trying




// USE EXPLICITS TO TURN SCALA SEQUENCES INTO DATAFRAMES
import spark.implicits._
val list: List[String] = List[String]("hello", "goodbye" )
list.toDF.take(2)




