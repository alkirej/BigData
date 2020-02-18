// ORM class for table 'movie'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Feb 17 21:21:53 UTC 2020
// For connector: org.apache.sqoop.manager.DirectMySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class movie extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("movie_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        movie_id = (Integer)value;
      }
    });
    setters.put("title", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        title = (String)value;
      }
    });
    setters.put("release_dt", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        release_dt = (java.sql.Date)value;
      }
    });
    setters.put("video_release_dt", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        video_release_dt = (java.sql.Date)value;
      }
    });
    setters.put("imdb_url", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        imdb_url = (String)value;
      }
    });
    setters.put("g_unknown", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_unknown = (Integer)value;
      }
    });
    setters.put("g_action", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_action = (Integer)value;
      }
    });
    setters.put("g_adventure", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_adventure = (Integer)value;
      }
    });
    setters.put("g_animation", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_animation = (Integer)value;
      }
    });
    setters.put("g_children", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_children = (Integer)value;
      }
    });
    setters.put("g_comedy", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_comedy = (Integer)value;
      }
    });
    setters.put("g_crime", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_crime = (Integer)value;
      }
    });
    setters.put("g_documentary", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_documentary = (Integer)value;
      }
    });
    setters.put("g_drama", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_drama = (Integer)value;
      }
    });
    setters.put("g_fantasy", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_fantasy = (Integer)value;
      }
    });
    setters.put("g_filemoir", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_filemoir = (Integer)value;
      }
    });
    setters.put("g_horror", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_horror = (Integer)value;
      }
    });
    setters.put("g_musical", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_musical = (Integer)value;
      }
    });
    setters.put("g_mystery", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_mystery = (Integer)value;
      }
    });
    setters.put("g_romance", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_romance = (Integer)value;
      }
    });
    setters.put("g_scifi", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_scifi = (Integer)value;
      }
    });
    setters.put("g_thriller", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_thriller = (Integer)value;
      }
    });
    setters.put("g_war", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_war = (Integer)value;
      }
    });
    setters.put("g_western", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        g_western = (Integer)value;
      }
    });
  }
  public movie() {
    init0();
  }
  private Integer movie_id;
  public Integer get_movie_id() {
    return movie_id;
  }
  public void set_movie_id(Integer movie_id) {
    this.movie_id = movie_id;
  }
  public movie with_movie_id(Integer movie_id) {
    this.movie_id = movie_id;
    return this;
  }
  private String title;
  public String get_title() {
    return title;
  }
  public void set_title(String title) {
    this.title = title;
  }
  public movie with_title(String title) {
    this.title = title;
    return this;
  }
  private java.sql.Date release_dt;
  public java.sql.Date get_release_dt() {
    return release_dt;
  }
  public void set_release_dt(java.sql.Date release_dt) {
    this.release_dt = release_dt;
  }
  public movie with_release_dt(java.sql.Date release_dt) {
    this.release_dt = release_dt;
    return this;
  }
  private java.sql.Date video_release_dt;
  public java.sql.Date get_video_release_dt() {
    return video_release_dt;
  }
  public void set_video_release_dt(java.sql.Date video_release_dt) {
    this.video_release_dt = video_release_dt;
  }
  public movie with_video_release_dt(java.sql.Date video_release_dt) {
    this.video_release_dt = video_release_dt;
    return this;
  }
  private String imdb_url;
  public String get_imdb_url() {
    return imdb_url;
  }
  public void set_imdb_url(String imdb_url) {
    this.imdb_url = imdb_url;
  }
  public movie with_imdb_url(String imdb_url) {
    this.imdb_url = imdb_url;
    return this;
  }
  private Integer g_unknown;
  public Integer get_g_unknown() {
    return g_unknown;
  }
  public void set_g_unknown(Integer g_unknown) {
    this.g_unknown = g_unknown;
  }
  public movie with_g_unknown(Integer g_unknown) {
    this.g_unknown = g_unknown;
    return this;
  }
  private Integer g_action;
  public Integer get_g_action() {
    return g_action;
  }
  public void set_g_action(Integer g_action) {
    this.g_action = g_action;
  }
  public movie with_g_action(Integer g_action) {
    this.g_action = g_action;
    return this;
  }
  private Integer g_adventure;
  public Integer get_g_adventure() {
    return g_adventure;
  }
  public void set_g_adventure(Integer g_adventure) {
    this.g_adventure = g_adventure;
  }
  public movie with_g_adventure(Integer g_adventure) {
    this.g_adventure = g_adventure;
    return this;
  }
  private Integer g_animation;
  public Integer get_g_animation() {
    return g_animation;
  }
  public void set_g_animation(Integer g_animation) {
    this.g_animation = g_animation;
  }
  public movie with_g_animation(Integer g_animation) {
    this.g_animation = g_animation;
    return this;
  }
  private Integer g_children;
  public Integer get_g_children() {
    return g_children;
  }
  public void set_g_children(Integer g_children) {
    this.g_children = g_children;
  }
  public movie with_g_children(Integer g_children) {
    this.g_children = g_children;
    return this;
  }
  private Integer g_comedy;
  public Integer get_g_comedy() {
    return g_comedy;
  }
  public void set_g_comedy(Integer g_comedy) {
    this.g_comedy = g_comedy;
  }
  public movie with_g_comedy(Integer g_comedy) {
    this.g_comedy = g_comedy;
    return this;
  }
  private Integer g_crime;
  public Integer get_g_crime() {
    return g_crime;
  }
  public void set_g_crime(Integer g_crime) {
    this.g_crime = g_crime;
  }
  public movie with_g_crime(Integer g_crime) {
    this.g_crime = g_crime;
    return this;
  }
  private Integer g_documentary;
  public Integer get_g_documentary() {
    return g_documentary;
  }
  public void set_g_documentary(Integer g_documentary) {
    this.g_documentary = g_documentary;
  }
  public movie with_g_documentary(Integer g_documentary) {
    this.g_documentary = g_documentary;
    return this;
  }
  private Integer g_drama;
  public Integer get_g_drama() {
    return g_drama;
  }
  public void set_g_drama(Integer g_drama) {
    this.g_drama = g_drama;
  }
  public movie with_g_drama(Integer g_drama) {
    this.g_drama = g_drama;
    return this;
  }
  private Integer g_fantasy;
  public Integer get_g_fantasy() {
    return g_fantasy;
  }
  public void set_g_fantasy(Integer g_fantasy) {
    this.g_fantasy = g_fantasy;
  }
  public movie with_g_fantasy(Integer g_fantasy) {
    this.g_fantasy = g_fantasy;
    return this;
  }
  private Integer g_filemoir;
  public Integer get_g_filemoir() {
    return g_filemoir;
  }
  public void set_g_filemoir(Integer g_filemoir) {
    this.g_filemoir = g_filemoir;
  }
  public movie with_g_filemoir(Integer g_filemoir) {
    this.g_filemoir = g_filemoir;
    return this;
  }
  private Integer g_horror;
  public Integer get_g_horror() {
    return g_horror;
  }
  public void set_g_horror(Integer g_horror) {
    this.g_horror = g_horror;
  }
  public movie with_g_horror(Integer g_horror) {
    this.g_horror = g_horror;
    return this;
  }
  private Integer g_musical;
  public Integer get_g_musical() {
    return g_musical;
  }
  public void set_g_musical(Integer g_musical) {
    this.g_musical = g_musical;
  }
  public movie with_g_musical(Integer g_musical) {
    this.g_musical = g_musical;
    return this;
  }
  private Integer g_mystery;
  public Integer get_g_mystery() {
    return g_mystery;
  }
  public void set_g_mystery(Integer g_mystery) {
    this.g_mystery = g_mystery;
  }
  public movie with_g_mystery(Integer g_mystery) {
    this.g_mystery = g_mystery;
    return this;
  }
  private Integer g_romance;
  public Integer get_g_romance() {
    return g_romance;
  }
  public void set_g_romance(Integer g_romance) {
    this.g_romance = g_romance;
  }
  public movie with_g_romance(Integer g_romance) {
    this.g_romance = g_romance;
    return this;
  }
  private Integer g_scifi;
  public Integer get_g_scifi() {
    return g_scifi;
  }
  public void set_g_scifi(Integer g_scifi) {
    this.g_scifi = g_scifi;
  }
  public movie with_g_scifi(Integer g_scifi) {
    this.g_scifi = g_scifi;
    return this;
  }
  private Integer g_thriller;
  public Integer get_g_thriller() {
    return g_thriller;
  }
  public void set_g_thriller(Integer g_thriller) {
    this.g_thriller = g_thriller;
  }
  public movie with_g_thriller(Integer g_thriller) {
    this.g_thriller = g_thriller;
    return this;
  }
  private Integer g_war;
  public Integer get_g_war() {
    return g_war;
  }
  public void set_g_war(Integer g_war) {
    this.g_war = g_war;
  }
  public movie with_g_war(Integer g_war) {
    this.g_war = g_war;
    return this;
  }
  private Integer g_western;
  public Integer get_g_western() {
    return g_western;
  }
  public void set_g_western(Integer g_western) {
    this.g_western = g_western;
  }
  public movie with_g_western(Integer g_western) {
    this.g_western = g_western;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof movie)) {
      return false;
    }
    movie that = (movie) o;
    boolean equal = true;
    equal = equal && (this.movie_id == null ? that.movie_id == null : this.movie_id.equals(that.movie_id));
    equal = equal && (this.title == null ? that.title == null : this.title.equals(that.title));
    equal = equal && (this.release_dt == null ? that.release_dt == null : this.release_dt.equals(that.release_dt));
    equal = equal && (this.video_release_dt == null ? that.video_release_dt == null : this.video_release_dt.equals(that.video_release_dt));
    equal = equal && (this.imdb_url == null ? that.imdb_url == null : this.imdb_url.equals(that.imdb_url));
    equal = equal && (this.g_unknown == null ? that.g_unknown == null : this.g_unknown.equals(that.g_unknown));
    equal = equal && (this.g_action == null ? that.g_action == null : this.g_action.equals(that.g_action));
    equal = equal && (this.g_adventure == null ? that.g_adventure == null : this.g_adventure.equals(that.g_adventure));
    equal = equal && (this.g_animation == null ? that.g_animation == null : this.g_animation.equals(that.g_animation));
    equal = equal && (this.g_children == null ? that.g_children == null : this.g_children.equals(that.g_children));
    equal = equal && (this.g_comedy == null ? that.g_comedy == null : this.g_comedy.equals(that.g_comedy));
    equal = equal && (this.g_crime == null ? that.g_crime == null : this.g_crime.equals(that.g_crime));
    equal = equal && (this.g_documentary == null ? that.g_documentary == null : this.g_documentary.equals(that.g_documentary));
    equal = equal && (this.g_drama == null ? that.g_drama == null : this.g_drama.equals(that.g_drama));
    equal = equal && (this.g_fantasy == null ? that.g_fantasy == null : this.g_fantasy.equals(that.g_fantasy));
    equal = equal && (this.g_filemoir == null ? that.g_filemoir == null : this.g_filemoir.equals(that.g_filemoir));
    equal = equal && (this.g_horror == null ? that.g_horror == null : this.g_horror.equals(that.g_horror));
    equal = equal && (this.g_musical == null ? that.g_musical == null : this.g_musical.equals(that.g_musical));
    equal = equal && (this.g_mystery == null ? that.g_mystery == null : this.g_mystery.equals(that.g_mystery));
    equal = equal && (this.g_romance == null ? that.g_romance == null : this.g_romance.equals(that.g_romance));
    equal = equal && (this.g_scifi == null ? that.g_scifi == null : this.g_scifi.equals(that.g_scifi));
    equal = equal && (this.g_thriller == null ? that.g_thriller == null : this.g_thriller.equals(that.g_thriller));
    equal = equal && (this.g_war == null ? that.g_war == null : this.g_war.equals(that.g_war));
    equal = equal && (this.g_western == null ? that.g_western == null : this.g_western.equals(that.g_western));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof movie)) {
      return false;
    }
    movie that = (movie) o;
    boolean equal = true;
    equal = equal && (this.movie_id == null ? that.movie_id == null : this.movie_id.equals(that.movie_id));
    equal = equal && (this.title == null ? that.title == null : this.title.equals(that.title));
    equal = equal && (this.release_dt == null ? that.release_dt == null : this.release_dt.equals(that.release_dt));
    equal = equal && (this.video_release_dt == null ? that.video_release_dt == null : this.video_release_dt.equals(that.video_release_dt));
    equal = equal && (this.imdb_url == null ? that.imdb_url == null : this.imdb_url.equals(that.imdb_url));
    equal = equal && (this.g_unknown == null ? that.g_unknown == null : this.g_unknown.equals(that.g_unknown));
    equal = equal && (this.g_action == null ? that.g_action == null : this.g_action.equals(that.g_action));
    equal = equal && (this.g_adventure == null ? that.g_adventure == null : this.g_adventure.equals(that.g_adventure));
    equal = equal && (this.g_animation == null ? that.g_animation == null : this.g_animation.equals(that.g_animation));
    equal = equal && (this.g_children == null ? that.g_children == null : this.g_children.equals(that.g_children));
    equal = equal && (this.g_comedy == null ? that.g_comedy == null : this.g_comedy.equals(that.g_comedy));
    equal = equal && (this.g_crime == null ? that.g_crime == null : this.g_crime.equals(that.g_crime));
    equal = equal && (this.g_documentary == null ? that.g_documentary == null : this.g_documentary.equals(that.g_documentary));
    equal = equal && (this.g_drama == null ? that.g_drama == null : this.g_drama.equals(that.g_drama));
    equal = equal && (this.g_fantasy == null ? that.g_fantasy == null : this.g_fantasy.equals(that.g_fantasy));
    equal = equal && (this.g_filemoir == null ? that.g_filemoir == null : this.g_filemoir.equals(that.g_filemoir));
    equal = equal && (this.g_horror == null ? that.g_horror == null : this.g_horror.equals(that.g_horror));
    equal = equal && (this.g_musical == null ? that.g_musical == null : this.g_musical.equals(that.g_musical));
    equal = equal && (this.g_mystery == null ? that.g_mystery == null : this.g_mystery.equals(that.g_mystery));
    equal = equal && (this.g_romance == null ? that.g_romance == null : this.g_romance.equals(that.g_romance));
    equal = equal && (this.g_scifi == null ? that.g_scifi == null : this.g_scifi.equals(that.g_scifi));
    equal = equal && (this.g_thriller == null ? that.g_thriller == null : this.g_thriller.equals(that.g_thriller));
    equal = equal && (this.g_war == null ? that.g_war == null : this.g_war.equals(that.g_war));
    equal = equal && (this.g_western == null ? that.g_western == null : this.g_western.equals(that.g_western));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.movie_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.title = JdbcWritableBridge.readString(2, __dbResults);
    this.release_dt = JdbcWritableBridge.readDate(3, __dbResults);
    this.video_release_dt = JdbcWritableBridge.readDate(4, __dbResults);
    this.imdb_url = JdbcWritableBridge.readString(5, __dbResults);
    this.g_unknown = JdbcWritableBridge.readInteger(6, __dbResults);
    this.g_action = JdbcWritableBridge.readInteger(7, __dbResults);
    this.g_adventure = JdbcWritableBridge.readInteger(8, __dbResults);
    this.g_animation = JdbcWritableBridge.readInteger(9, __dbResults);
    this.g_children = JdbcWritableBridge.readInteger(10, __dbResults);
    this.g_comedy = JdbcWritableBridge.readInteger(11, __dbResults);
    this.g_crime = JdbcWritableBridge.readInteger(12, __dbResults);
    this.g_documentary = JdbcWritableBridge.readInteger(13, __dbResults);
    this.g_drama = JdbcWritableBridge.readInteger(14, __dbResults);
    this.g_fantasy = JdbcWritableBridge.readInteger(15, __dbResults);
    this.g_filemoir = JdbcWritableBridge.readInteger(16, __dbResults);
    this.g_horror = JdbcWritableBridge.readInteger(17, __dbResults);
    this.g_musical = JdbcWritableBridge.readInteger(18, __dbResults);
    this.g_mystery = JdbcWritableBridge.readInteger(19, __dbResults);
    this.g_romance = JdbcWritableBridge.readInteger(20, __dbResults);
    this.g_scifi = JdbcWritableBridge.readInteger(21, __dbResults);
    this.g_thriller = JdbcWritableBridge.readInteger(22, __dbResults);
    this.g_war = JdbcWritableBridge.readInteger(23, __dbResults);
    this.g_western = JdbcWritableBridge.readInteger(24, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.movie_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.title = JdbcWritableBridge.readString(2, __dbResults);
    this.release_dt = JdbcWritableBridge.readDate(3, __dbResults);
    this.video_release_dt = JdbcWritableBridge.readDate(4, __dbResults);
    this.imdb_url = JdbcWritableBridge.readString(5, __dbResults);
    this.g_unknown = JdbcWritableBridge.readInteger(6, __dbResults);
    this.g_action = JdbcWritableBridge.readInteger(7, __dbResults);
    this.g_adventure = JdbcWritableBridge.readInteger(8, __dbResults);
    this.g_animation = JdbcWritableBridge.readInteger(9, __dbResults);
    this.g_children = JdbcWritableBridge.readInteger(10, __dbResults);
    this.g_comedy = JdbcWritableBridge.readInteger(11, __dbResults);
    this.g_crime = JdbcWritableBridge.readInteger(12, __dbResults);
    this.g_documentary = JdbcWritableBridge.readInteger(13, __dbResults);
    this.g_drama = JdbcWritableBridge.readInteger(14, __dbResults);
    this.g_fantasy = JdbcWritableBridge.readInteger(15, __dbResults);
    this.g_filemoir = JdbcWritableBridge.readInteger(16, __dbResults);
    this.g_horror = JdbcWritableBridge.readInteger(17, __dbResults);
    this.g_musical = JdbcWritableBridge.readInteger(18, __dbResults);
    this.g_mystery = JdbcWritableBridge.readInteger(19, __dbResults);
    this.g_romance = JdbcWritableBridge.readInteger(20, __dbResults);
    this.g_scifi = JdbcWritableBridge.readInteger(21, __dbResults);
    this.g_thriller = JdbcWritableBridge.readInteger(22, __dbResults);
    this.g_war = JdbcWritableBridge.readInteger(23, __dbResults);
    this.g_western = JdbcWritableBridge.readInteger(24, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(movie_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(title, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDate(release_dt, 3 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeDate(video_release_dt, 4 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeString(imdb_url, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(g_unknown, 6 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_action, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_adventure, 8 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_animation, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_children, 10 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_comedy, 11 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_crime, 12 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_documentary, 13 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_drama, 14 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_fantasy, 15 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_filemoir, 16 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_horror, 17 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_musical, 18 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_mystery, 19 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_romance, 20 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_scifi, 21 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_thriller, 22 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_war, 23 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_western, 24 + __off, 4, __dbStmt);
    return 24;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(movie_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(title, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDate(release_dt, 3 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeDate(video_release_dt, 4 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeString(imdb_url, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(g_unknown, 6 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_action, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_adventure, 8 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_animation, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_children, 10 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_comedy, 11 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_crime, 12 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_documentary, 13 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_drama, 14 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_fantasy, 15 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_filemoir, 16 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_horror, 17 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_musical, 18 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_mystery, 19 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_romance, 20 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_scifi, 21 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_thriller, 22 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_war, 23 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(g_western, 24 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.movie_id = null;
    } else {
    this.movie_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.title = null;
    } else {
    this.title = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.release_dt = null;
    } else {
    this.release_dt = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.video_release_dt = null;
    } else {
    this.video_release_dt = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.imdb_url = null;
    } else {
    this.imdb_url = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.g_unknown = null;
    } else {
    this.g_unknown = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_action = null;
    } else {
    this.g_action = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_adventure = null;
    } else {
    this.g_adventure = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_animation = null;
    } else {
    this.g_animation = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_children = null;
    } else {
    this.g_children = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_comedy = null;
    } else {
    this.g_comedy = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_crime = null;
    } else {
    this.g_crime = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_documentary = null;
    } else {
    this.g_documentary = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_drama = null;
    } else {
    this.g_drama = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_fantasy = null;
    } else {
    this.g_fantasy = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_filemoir = null;
    } else {
    this.g_filemoir = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_horror = null;
    } else {
    this.g_horror = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_musical = null;
    } else {
    this.g_musical = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_mystery = null;
    } else {
    this.g_mystery = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_romance = null;
    } else {
    this.g_romance = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_scifi = null;
    } else {
    this.g_scifi = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_thriller = null;
    } else {
    this.g_thriller = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_war = null;
    } else {
    this.g_war = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.g_western = null;
    } else {
    this.g_western = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.movie_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.movie_id);
    }
    if (null == this.title) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, title);
    }
    if (null == this.release_dt) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.release_dt.getTime());
    }
    if (null == this.video_release_dt) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.video_release_dt.getTime());
    }
    if (null == this.imdb_url) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, imdb_url);
    }
    if (null == this.g_unknown) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_unknown);
    }
    if (null == this.g_action) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_action);
    }
    if (null == this.g_adventure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_adventure);
    }
    if (null == this.g_animation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_animation);
    }
    if (null == this.g_children) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_children);
    }
    if (null == this.g_comedy) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_comedy);
    }
    if (null == this.g_crime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_crime);
    }
    if (null == this.g_documentary) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_documentary);
    }
    if (null == this.g_drama) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_drama);
    }
    if (null == this.g_fantasy) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_fantasy);
    }
    if (null == this.g_filemoir) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_filemoir);
    }
    if (null == this.g_horror) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_horror);
    }
    if (null == this.g_musical) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_musical);
    }
    if (null == this.g_mystery) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_mystery);
    }
    if (null == this.g_romance) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_romance);
    }
    if (null == this.g_scifi) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_scifi);
    }
    if (null == this.g_thriller) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_thriller);
    }
    if (null == this.g_war) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_war);
    }
    if (null == this.g_western) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_western);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.movie_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.movie_id);
    }
    if (null == this.title) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, title);
    }
    if (null == this.release_dt) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.release_dt.getTime());
    }
    if (null == this.video_release_dt) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.video_release_dt.getTime());
    }
    if (null == this.imdb_url) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, imdb_url);
    }
    if (null == this.g_unknown) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_unknown);
    }
    if (null == this.g_action) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_action);
    }
    if (null == this.g_adventure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_adventure);
    }
    if (null == this.g_animation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_animation);
    }
    if (null == this.g_children) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_children);
    }
    if (null == this.g_comedy) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_comedy);
    }
    if (null == this.g_crime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_crime);
    }
    if (null == this.g_documentary) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_documentary);
    }
    if (null == this.g_drama) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_drama);
    }
    if (null == this.g_fantasy) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_fantasy);
    }
    if (null == this.g_filemoir) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_filemoir);
    }
    if (null == this.g_horror) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_horror);
    }
    if (null == this.g_musical) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_musical);
    }
    if (null == this.g_mystery) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_mystery);
    }
    if (null == this.g_romance) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_romance);
    }
    if (null == this.g_scifi) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_scifi);
    }
    if (null == this.g_thriller) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_thriller);
    }
    if (null == this.g_war) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_war);
    }
    if (null == this.g_western) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.g_western);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(movie_id==null?"null":"" + movie_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(title==null?"null":title, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(release_dt==null?"null":"" + release_dt, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(video_release_dt==null?"null":"" + video_release_dt, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(imdb_url==null?"null":imdb_url, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_unknown==null?"null":"" + g_unknown, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_action==null?"null":"" + g_action, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_adventure==null?"null":"" + g_adventure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_animation==null?"null":"" + g_animation, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_children==null?"null":"" + g_children, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_comedy==null?"null":"" + g_comedy, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_crime==null?"null":"" + g_crime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_documentary==null?"null":"" + g_documentary, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_drama==null?"null":"" + g_drama, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_fantasy==null?"null":"" + g_fantasy, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_filemoir==null?"null":"" + g_filemoir, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_horror==null?"null":"" + g_horror, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_musical==null?"null":"" + g_musical, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_mystery==null?"null":"" + g_mystery, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_romance==null?"null":"" + g_romance, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_scifi==null?"null":"" + g_scifi, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_thriller==null?"null":"" + g_thriller, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_war==null?"null":"" + g_war, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_western==null?"null":"" + g_western, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(movie_id==null?"null":"" + movie_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(title==null?"null":title, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(release_dt==null?"null":"" + release_dt, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(video_release_dt==null?"null":"" + video_release_dt, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(imdb_url==null?"null":imdb_url, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_unknown==null?"null":"" + g_unknown, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_action==null?"null":"" + g_action, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_adventure==null?"null":"" + g_adventure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_animation==null?"null":"" + g_animation, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_children==null?"null":"" + g_children, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_comedy==null?"null":"" + g_comedy, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_crime==null?"null":"" + g_crime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_documentary==null?"null":"" + g_documentary, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_drama==null?"null":"" + g_drama, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_fantasy==null?"null":"" + g_fantasy, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_filemoir==null?"null":"" + g_filemoir, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_horror==null?"null":"" + g_horror, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_musical==null?"null":"" + g_musical, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_mystery==null?"null":"" + g_mystery, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_romance==null?"null":"" + g_romance, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_scifi==null?"null":"" + g_scifi, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_thriller==null?"null":"" + g_thriller, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_war==null?"null":"" + g_war, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(g_western==null?"null":"" + g_western, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.movie_id = null; } else {
      this.movie_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.title = null; } else {
      this.title = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.release_dt = null; } else {
      this.release_dt = java.sql.Date.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.video_release_dt = null; } else {
      this.video_release_dt = java.sql.Date.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.imdb_url = null; } else {
      this.imdb_url = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_unknown = null; } else {
      this.g_unknown = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_action = null; } else {
      this.g_action = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_adventure = null; } else {
      this.g_adventure = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_animation = null; } else {
      this.g_animation = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_children = null; } else {
      this.g_children = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_comedy = null; } else {
      this.g_comedy = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_crime = null; } else {
      this.g_crime = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_documentary = null; } else {
      this.g_documentary = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_drama = null; } else {
      this.g_drama = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_fantasy = null; } else {
      this.g_fantasy = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_filemoir = null; } else {
      this.g_filemoir = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_horror = null; } else {
      this.g_horror = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_musical = null; } else {
      this.g_musical = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_mystery = null; } else {
      this.g_mystery = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_romance = null; } else {
      this.g_romance = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_scifi = null; } else {
      this.g_scifi = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_thriller = null; } else {
      this.g_thriller = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_war = null; } else {
      this.g_war = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_western = null; } else {
      this.g_western = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.movie_id = null; } else {
      this.movie_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.title = null; } else {
      this.title = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.release_dt = null; } else {
      this.release_dt = java.sql.Date.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.video_release_dt = null; } else {
      this.video_release_dt = java.sql.Date.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.imdb_url = null; } else {
      this.imdb_url = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_unknown = null; } else {
      this.g_unknown = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_action = null; } else {
      this.g_action = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_adventure = null; } else {
      this.g_adventure = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_animation = null; } else {
      this.g_animation = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_children = null; } else {
      this.g_children = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_comedy = null; } else {
      this.g_comedy = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_crime = null; } else {
      this.g_crime = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_documentary = null; } else {
      this.g_documentary = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_drama = null; } else {
      this.g_drama = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_fantasy = null; } else {
      this.g_fantasy = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_filemoir = null; } else {
      this.g_filemoir = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_horror = null; } else {
      this.g_horror = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_musical = null; } else {
      this.g_musical = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_mystery = null; } else {
      this.g_mystery = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_romance = null; } else {
      this.g_romance = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_scifi = null; } else {
      this.g_scifi = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_thriller = null; } else {
      this.g_thriller = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_war = null; } else {
      this.g_war = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.g_western = null; } else {
      this.g_western = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    movie o = (movie) super.clone();
    o.release_dt = (o.release_dt != null) ? (java.sql.Date) o.release_dt.clone() : null;
    o.video_release_dt = (o.video_release_dt != null) ? (java.sql.Date) o.video_release_dt.clone() : null;
    return o;
  }

  public void clone0(movie o) throws CloneNotSupportedException {
    o.release_dt = (o.release_dt != null) ? (java.sql.Date) o.release_dt.clone() : null;
    o.video_release_dt = (o.video_release_dt != null) ? (java.sql.Date) o.video_release_dt.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("movie_id", this.movie_id);
    __sqoop$field_map.put("title", this.title);
    __sqoop$field_map.put("release_dt", this.release_dt);
    __sqoop$field_map.put("video_release_dt", this.video_release_dt);
    __sqoop$field_map.put("imdb_url", this.imdb_url);
    __sqoop$field_map.put("g_unknown", this.g_unknown);
    __sqoop$field_map.put("g_action", this.g_action);
    __sqoop$field_map.put("g_adventure", this.g_adventure);
    __sqoop$field_map.put("g_animation", this.g_animation);
    __sqoop$field_map.put("g_children", this.g_children);
    __sqoop$field_map.put("g_comedy", this.g_comedy);
    __sqoop$field_map.put("g_crime", this.g_crime);
    __sqoop$field_map.put("g_documentary", this.g_documentary);
    __sqoop$field_map.put("g_drama", this.g_drama);
    __sqoop$field_map.put("g_fantasy", this.g_fantasy);
    __sqoop$field_map.put("g_filemoir", this.g_filemoir);
    __sqoop$field_map.put("g_horror", this.g_horror);
    __sqoop$field_map.put("g_musical", this.g_musical);
    __sqoop$field_map.put("g_mystery", this.g_mystery);
    __sqoop$field_map.put("g_romance", this.g_romance);
    __sqoop$field_map.put("g_scifi", this.g_scifi);
    __sqoop$field_map.put("g_thriller", this.g_thriller);
    __sqoop$field_map.put("g_war", this.g_war);
    __sqoop$field_map.put("g_western", this.g_western);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("movie_id", this.movie_id);
    __sqoop$field_map.put("title", this.title);
    __sqoop$field_map.put("release_dt", this.release_dt);
    __sqoop$field_map.put("video_release_dt", this.video_release_dt);
    __sqoop$field_map.put("imdb_url", this.imdb_url);
    __sqoop$field_map.put("g_unknown", this.g_unknown);
    __sqoop$field_map.put("g_action", this.g_action);
    __sqoop$field_map.put("g_adventure", this.g_adventure);
    __sqoop$field_map.put("g_animation", this.g_animation);
    __sqoop$field_map.put("g_children", this.g_children);
    __sqoop$field_map.put("g_comedy", this.g_comedy);
    __sqoop$field_map.put("g_crime", this.g_crime);
    __sqoop$field_map.put("g_documentary", this.g_documentary);
    __sqoop$field_map.put("g_drama", this.g_drama);
    __sqoop$field_map.put("g_fantasy", this.g_fantasy);
    __sqoop$field_map.put("g_filemoir", this.g_filemoir);
    __sqoop$field_map.put("g_horror", this.g_horror);
    __sqoop$field_map.put("g_musical", this.g_musical);
    __sqoop$field_map.put("g_mystery", this.g_mystery);
    __sqoop$field_map.put("g_romance", this.g_romance);
    __sqoop$field_map.put("g_scifi", this.g_scifi);
    __sqoop$field_map.put("g_thriller", this.g_thriller);
    __sqoop$field_map.put("g_war", this.g_war);
    __sqoop$field_map.put("g_western", this.g_western);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
