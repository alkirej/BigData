package net.alkire.task35
import org.apache.spark.sql.ForeachWriter

abstract class MongodbForeachWriter[RECORD] extends ForeachWriter[RECORD]
{
    var _open:Boolean  = false

    override def open(partitionId: Long, version: Long): Boolean = {
        _open = true
        true
    }
    
    override def process(record: RECORD): Unit = {
        if ( !_open ) {
            throw new IllegalStateException( "Cannot process using a closed writer." )
        }
        write( record )
    }
    
    override def close(errorOrNull: Throwable): Unit = {
        _open = false
    }
    
    def write( item: RECORD ): Unit
}


