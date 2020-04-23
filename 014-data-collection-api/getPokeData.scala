import java.net.{URL, HttpURLConnection, URLConnection}
import java.io.{PrintWriter,File}
import scala.language.postfixOps
import sys.process._

object PokeData {

    def test() : Unit = {
        val url:URL = new URL("https://pokeapi.co/api/v2/evolution-chain")

        val conn:URLConnection = url.openConnection
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.74 Safari/537.36 Edg/79.0.309.43");
        print ( "conn: " + conn.getClass + "\n")

        val input_stream = conn.getInputStream
        val content = io.Source.fromInputStream(input_stream).mkString
        input_stream.close

        val local_path  = "/home/jeff/output/pokemon-site.scala"

        val local_writer = new PrintWriter(new File( local_path ))
        local_writer.write(content)
        local_writer.close
        
        val hdfs_path = "/data/pokemon-site.scala"
        "hdfs dfs -put " + local_path + " " + hdfs_path !
    }    
    
    def main( args: Array[String] ): Unit = {
        test
    }
}
