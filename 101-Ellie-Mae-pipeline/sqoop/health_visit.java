// ORM class for table 'health_visit'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Thu Apr 02 14:53:02 PDT 2020
// For connector: org.apache.sqoop.manager.MySQLManager
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

public class health_visit extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("restaurant_name", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.restaurant_name = (String)value;
      }
    });
    setters.put("inspection_date", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.inspection_date = (String)value;
      }
    });
    setters.put("inspection_score", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.inspection_score = (String)value;
      }
    });
    setters.put("restaurant_address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.restaurant_address = (String)value;
      }
    });
    setters.put("restaurant_city", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.restaurant_city = (String)value;
      }
    });
    setters.put("restaurant_state", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.restaurant_state = (String)value;
      }
    });
    setters.put("restaurant_zip", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.restaurant_zip = (String)value;
      }
    });
    setters.put("inspection_description", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.inspection_description = (String)value;
      }
    });
    setters.put("inspection_type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.inspection_type = (String)value;
      }
    });
    setters.put("inspection_violation", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        health_visit.this.inspection_violation = (String)value;
      }
    });
  }
  public health_visit() {
    init0();
  }
  private String restaurant_name;
  public String get_restaurant_name() {
    return restaurant_name;
  }
  public void set_restaurant_name(String restaurant_name) {
    this.restaurant_name = restaurant_name;
  }
  public health_visit with_restaurant_name(String restaurant_name) {
    this.restaurant_name = restaurant_name;
    return this;
  }
  private String inspection_date;
  public String get_inspection_date() {
    return inspection_date;
  }
  public void set_inspection_date(String inspection_date) {
    this.inspection_date = inspection_date;
  }
  public health_visit with_inspection_date(String inspection_date) {
    this.inspection_date = inspection_date;
    return this;
  }
  private String inspection_score;
  public String get_inspection_score() {
    return inspection_score;
  }
  public void set_inspection_score(String inspection_score) {
    this.inspection_score = inspection_score;
  }
  public health_visit with_inspection_score(String inspection_score) {
    this.inspection_score = inspection_score;
    return this;
  }
  private String restaurant_address;
  public String get_restaurant_address() {
    return restaurant_address;
  }
  public void set_restaurant_address(String restaurant_address) {
    this.restaurant_address = restaurant_address;
  }
  public health_visit with_restaurant_address(String restaurant_address) {
    this.restaurant_address = restaurant_address;
    return this;
  }
  private String restaurant_city;
  public String get_restaurant_city() {
    return restaurant_city;
  }
  public void set_restaurant_city(String restaurant_city) {
    this.restaurant_city = restaurant_city;
  }
  public health_visit with_restaurant_city(String restaurant_city) {
    this.restaurant_city = restaurant_city;
    return this;
  }
  private String restaurant_state;
  public String get_restaurant_state() {
    return restaurant_state;
  }
  public void set_restaurant_state(String restaurant_state) {
    this.restaurant_state = restaurant_state;
  }
  public health_visit with_restaurant_state(String restaurant_state) {
    this.restaurant_state = restaurant_state;
    return this;
  }
  private String restaurant_zip;
  public String get_restaurant_zip() {
    return restaurant_zip;
  }
  public void set_restaurant_zip(String restaurant_zip) {
    this.restaurant_zip = restaurant_zip;
  }
  public health_visit with_restaurant_zip(String restaurant_zip) {
    this.restaurant_zip = restaurant_zip;
    return this;
  }
  private String inspection_description;
  public String get_inspection_description() {
    return inspection_description;
  }
  public void set_inspection_description(String inspection_description) {
    this.inspection_description = inspection_description;
  }
  public health_visit with_inspection_description(String inspection_description) {
    this.inspection_description = inspection_description;
    return this;
  }
  private String inspection_type;
  public String get_inspection_type() {
    return inspection_type;
  }
  public void set_inspection_type(String inspection_type) {
    this.inspection_type = inspection_type;
  }
  public health_visit with_inspection_type(String inspection_type) {
    this.inspection_type = inspection_type;
    return this;
  }
  private String inspection_violation;
  public String get_inspection_violation() {
    return inspection_violation;
  }
  public void set_inspection_violation(String inspection_violation) {
    this.inspection_violation = inspection_violation;
  }
  public health_visit with_inspection_violation(String inspection_violation) {
    this.inspection_violation = inspection_violation;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof health_visit)) {
      return false;
    }
    health_visit that = (health_visit) o;
    boolean equal = true;
    equal = equal && (this.restaurant_name == null ? that.restaurant_name == null : this.restaurant_name.equals(that.restaurant_name));
    equal = equal && (this.inspection_date == null ? that.inspection_date == null : this.inspection_date.equals(that.inspection_date));
    equal = equal && (this.inspection_score == null ? that.inspection_score == null : this.inspection_score.equals(that.inspection_score));
    equal = equal && (this.restaurant_address == null ? that.restaurant_address == null : this.restaurant_address.equals(that.restaurant_address));
    equal = equal && (this.restaurant_city == null ? that.restaurant_city == null : this.restaurant_city.equals(that.restaurant_city));
    equal = equal && (this.restaurant_state == null ? that.restaurant_state == null : this.restaurant_state.equals(that.restaurant_state));
    equal = equal && (this.restaurant_zip == null ? that.restaurant_zip == null : this.restaurant_zip.equals(that.restaurant_zip));
    equal = equal && (this.inspection_description == null ? that.inspection_description == null : this.inspection_description.equals(that.inspection_description));
    equal = equal && (this.inspection_type == null ? that.inspection_type == null : this.inspection_type.equals(that.inspection_type));
    equal = equal && (this.inspection_violation == null ? that.inspection_violation == null : this.inspection_violation.equals(that.inspection_violation));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof health_visit)) {
      return false;
    }
    health_visit that = (health_visit) o;
    boolean equal = true;
    equal = equal && (this.restaurant_name == null ? that.restaurant_name == null : this.restaurant_name.equals(that.restaurant_name));
    equal = equal && (this.inspection_date == null ? that.inspection_date == null : this.inspection_date.equals(that.inspection_date));
    equal = equal && (this.inspection_score == null ? that.inspection_score == null : this.inspection_score.equals(that.inspection_score));
    equal = equal && (this.restaurant_address == null ? that.restaurant_address == null : this.restaurant_address.equals(that.restaurant_address));
    equal = equal && (this.restaurant_city == null ? that.restaurant_city == null : this.restaurant_city.equals(that.restaurant_city));
    equal = equal && (this.restaurant_state == null ? that.restaurant_state == null : this.restaurant_state.equals(that.restaurant_state));
    equal = equal && (this.restaurant_zip == null ? that.restaurant_zip == null : this.restaurant_zip.equals(that.restaurant_zip));
    equal = equal && (this.inspection_description == null ? that.inspection_description == null : this.inspection_description.equals(that.inspection_description));
    equal = equal && (this.inspection_type == null ? that.inspection_type == null : this.inspection_type.equals(that.inspection_type));
    equal = equal && (this.inspection_violation == null ? that.inspection_violation == null : this.inspection_violation.equals(that.inspection_violation));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.restaurant_name = JdbcWritableBridge.readString(1, __dbResults);
    this.inspection_date = JdbcWritableBridge.readString(2, __dbResults);
    this.inspection_score = JdbcWritableBridge.readString(3, __dbResults);
    this.restaurant_address = JdbcWritableBridge.readString(4, __dbResults);
    this.restaurant_city = JdbcWritableBridge.readString(5, __dbResults);
    this.restaurant_state = JdbcWritableBridge.readString(6, __dbResults);
    this.restaurant_zip = JdbcWritableBridge.readString(7, __dbResults);
    this.inspection_description = JdbcWritableBridge.readString(8, __dbResults);
    this.inspection_type = JdbcWritableBridge.readString(9, __dbResults);
    this.inspection_violation = JdbcWritableBridge.readString(10, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.restaurant_name = JdbcWritableBridge.readString(1, __dbResults);
    this.inspection_date = JdbcWritableBridge.readString(2, __dbResults);
    this.inspection_score = JdbcWritableBridge.readString(3, __dbResults);
    this.restaurant_address = JdbcWritableBridge.readString(4, __dbResults);
    this.restaurant_city = JdbcWritableBridge.readString(5, __dbResults);
    this.restaurant_state = JdbcWritableBridge.readString(6, __dbResults);
    this.restaurant_zip = JdbcWritableBridge.readString(7, __dbResults);
    this.inspection_description = JdbcWritableBridge.readString(8, __dbResults);
    this.inspection_type = JdbcWritableBridge.readString(9, __dbResults);
    this.inspection_violation = JdbcWritableBridge.readString(10, __dbResults);
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
    JdbcWritableBridge.writeString(restaurant_name, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_date, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_score, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(restaurant_address, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(restaurant_city, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(restaurant_state, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(restaurant_zip, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_description, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_type, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_violation, 10 + __off, 12, __dbStmt);
    return 10;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(restaurant_name, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_date, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_score, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(restaurant_address, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(restaurant_city, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(restaurant_state, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(restaurant_zip, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_description, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_type, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(inspection_violation, 10 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.restaurant_name = null;
    } else {
    this.restaurant_name = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.inspection_date = null;
    } else {
    this.inspection_date = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.inspection_score = null;
    } else {
    this.inspection_score = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.restaurant_address = null;
    } else {
    this.restaurant_address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.restaurant_city = null;
    } else {
    this.restaurant_city = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.restaurant_state = null;
    } else {
    this.restaurant_state = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.restaurant_zip = null;
    } else {
    this.restaurant_zip = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.inspection_description = null;
    } else {
    this.inspection_description = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.inspection_type = null;
    } else {
    this.inspection_type = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.inspection_violation = null;
    } else {
    this.inspection_violation = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.restaurant_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_name);
    }
    if (null == this.inspection_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_date);
    }
    if (null == this.inspection_score) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_score);
    }
    if (null == this.restaurant_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_address);
    }
    if (null == this.restaurant_city) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_city);
    }
    if (null == this.restaurant_state) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_state);
    }
    if (null == this.restaurant_zip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_zip);
    }
    if (null == this.inspection_description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_description);
    }
    if (null == this.inspection_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_type);
    }
    if (null == this.inspection_violation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_violation);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.restaurant_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_name);
    }
    if (null == this.inspection_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_date);
    }
    if (null == this.inspection_score) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_score);
    }
    if (null == this.restaurant_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_address);
    }
    if (null == this.restaurant_city) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_city);
    }
    if (null == this.restaurant_state) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_state);
    }
    if (null == this.restaurant_zip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, restaurant_zip);
    }
    if (null == this.inspection_description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_description);
    }
    if (null == this.inspection_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_type);
    }
    if (null == this.inspection_violation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, inspection_violation);
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
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_name==null?"null":restaurant_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_date==null?"null":inspection_date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_score==null?"null":inspection_score, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_address==null?"null":restaurant_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_city==null?"null":restaurant_city, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_state==null?"null":restaurant_state, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_zip==null?"null":restaurant_zip, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_description==null?"null":inspection_description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_type==null?"null":inspection_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_violation==null?"null":inspection_violation, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_name==null?"null":restaurant_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_date==null?"null":inspection_date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_score==null?"null":inspection_score, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_address==null?"null":restaurant_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_city==null?"null":restaurant_city, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_state==null?"null":restaurant_state, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(restaurant_zip==null?"null":restaurant_zip, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_description==null?"null":inspection_description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_type==null?"null":inspection_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(inspection_violation==null?"null":inspection_violation, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
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
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_name = null; } else {
      this.restaurant_name = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_date = null; } else {
      this.inspection_date = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_score = null; } else {
      this.inspection_score = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_address = null; } else {
      this.restaurant_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_city = null; } else {
      this.restaurant_city = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_state = null; } else {
      this.restaurant_state = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_zip = null; } else {
      this.restaurant_zip = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_description = null; } else {
      this.inspection_description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_type = null; } else {
      this.inspection_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_violation = null; } else {
      this.inspection_violation = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_name = null; } else {
      this.restaurant_name = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_date = null; } else {
      this.inspection_date = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_score = null; } else {
      this.inspection_score = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_address = null; } else {
      this.restaurant_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_city = null; } else {
      this.restaurant_city = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_state = null; } else {
      this.restaurant_state = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.restaurant_zip = null; } else {
      this.restaurant_zip = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_description = null; } else {
      this.inspection_description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_type = null; } else {
      this.inspection_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.inspection_violation = null; } else {
      this.inspection_violation = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    health_visit o = (health_visit) super.clone();
    return o;
  }

  public void clone0(health_visit o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("restaurant_name", this.restaurant_name);
    __sqoop$field_map.put("inspection_date", this.inspection_date);
    __sqoop$field_map.put("inspection_score", this.inspection_score);
    __sqoop$field_map.put("restaurant_address", this.restaurant_address);
    __sqoop$field_map.put("restaurant_city", this.restaurant_city);
    __sqoop$field_map.put("restaurant_state", this.restaurant_state);
    __sqoop$field_map.put("restaurant_zip", this.restaurant_zip);
    __sqoop$field_map.put("inspection_description", this.inspection_description);
    __sqoop$field_map.put("inspection_type", this.inspection_type);
    __sqoop$field_map.put("inspection_violation", this.inspection_violation);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("restaurant_name", this.restaurant_name);
    __sqoop$field_map.put("inspection_date", this.inspection_date);
    __sqoop$field_map.put("inspection_score", this.inspection_score);
    __sqoop$field_map.put("restaurant_address", this.restaurant_address);
    __sqoop$field_map.put("restaurant_city", this.restaurant_city);
    __sqoop$field_map.put("restaurant_state", this.restaurant_state);
    __sqoop$field_map.put("restaurant_zip", this.restaurant_zip);
    __sqoop$field_map.put("inspection_description", this.inspection_description);
    __sqoop$field_map.put("inspection_type", this.inspection_type);
    __sqoop$field_map.put("inspection_violation", this.inspection_violation);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
