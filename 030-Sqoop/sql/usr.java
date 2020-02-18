// ORM class for table 'usr'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Feb 17 19:02:04 UTC 2020
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

public class usr extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("user_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        user_id = (Integer)value;
      }
    });
    setters.put("age", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        age = (Integer)value;
      }
    });
    setters.put("sex", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        sex = (String)value;
      }
    });
    setters.put("career", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        career = (String)value;
      }
    });
    setters.put("zipcode", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        zipcode = (String)value;
      }
    });
  }
  public usr() {
    init0();
  }
  private Integer user_id;
  public Integer get_user_id() {
    return user_id;
  }
  public void set_user_id(Integer user_id) {
    this.user_id = user_id;
  }
  public usr with_user_id(Integer user_id) {
    this.user_id = user_id;
    return this;
  }
  private Integer age;
  public Integer get_age() {
    return age;
  }
  public void set_age(Integer age) {
    this.age = age;
  }
  public usr with_age(Integer age) {
    this.age = age;
    return this;
  }
  private String sex;
  public String get_sex() {
    return sex;
  }
  public void set_sex(String sex) {
    this.sex = sex;
  }
  public usr with_sex(String sex) {
    this.sex = sex;
    return this;
  }
  private String career;
  public String get_career() {
    return career;
  }
  public void set_career(String career) {
    this.career = career;
  }
  public usr with_career(String career) {
    this.career = career;
    return this;
  }
  private String zipcode;
  public String get_zipcode() {
    return zipcode;
  }
  public void set_zipcode(String zipcode) {
    this.zipcode = zipcode;
  }
  public usr with_zipcode(String zipcode) {
    this.zipcode = zipcode;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof usr)) {
      return false;
    }
    usr that = (usr) o;
    boolean equal = true;
    equal = equal && (this.user_id == null ? that.user_id == null : this.user_id.equals(that.user_id));
    equal = equal && (this.age == null ? that.age == null : this.age.equals(that.age));
    equal = equal && (this.sex == null ? that.sex == null : this.sex.equals(that.sex));
    equal = equal && (this.career == null ? that.career == null : this.career.equals(that.career));
    equal = equal && (this.zipcode == null ? that.zipcode == null : this.zipcode.equals(that.zipcode));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof usr)) {
      return false;
    }
    usr that = (usr) o;
    boolean equal = true;
    equal = equal && (this.user_id == null ? that.user_id == null : this.user_id.equals(that.user_id));
    equal = equal && (this.age == null ? that.age == null : this.age.equals(that.age));
    equal = equal && (this.sex == null ? that.sex == null : this.sex.equals(that.sex));
    equal = equal && (this.career == null ? that.career == null : this.career.equals(that.career));
    equal = equal && (this.zipcode == null ? that.zipcode == null : this.zipcode.equals(that.zipcode));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.user_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.age = JdbcWritableBridge.readInteger(2, __dbResults);
    this.sex = JdbcWritableBridge.readString(3, __dbResults);
    this.career = JdbcWritableBridge.readString(4, __dbResults);
    this.zipcode = JdbcWritableBridge.readString(5, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.user_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.age = JdbcWritableBridge.readInteger(2, __dbResults);
    this.sex = JdbcWritableBridge.readString(3, __dbResults);
    this.career = JdbcWritableBridge.readString(4, __dbResults);
    this.zipcode = JdbcWritableBridge.readString(5, __dbResults);
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
    JdbcWritableBridge.writeInteger(user_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(age, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(sex, 3 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(career, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(zipcode, 5 + __off, 12, __dbStmt);
    return 5;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(user_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(age, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(sex, 3 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(career, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(zipcode, 5 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.user_id = null;
    } else {
    this.user_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.age = null;
    } else {
    this.age = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.sex = null;
    } else {
    this.sex = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.career = null;
    } else {
    this.career = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.zipcode = null;
    } else {
    this.zipcode = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.user_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_id);
    }
    if (null == this.age) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.age);
    }
    if (null == this.sex) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sex);
    }
    if (null == this.career) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, career);
    }
    if (null == this.zipcode) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, zipcode);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.user_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_id);
    }
    if (null == this.age) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.age);
    }
    if (null == this.sex) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sex);
    }
    if (null == this.career) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, career);
    }
    if (null == this.zipcode) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, zipcode);
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
    __sb.append(FieldFormatter.escapeAndEnclose(user_id==null?"null":"" + user_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(age==null?"null":"" + age, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sex==null?"null":sex, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(career==null?"null":career, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(zipcode==null?"null":zipcode, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(user_id==null?"null":"" + user_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(age==null?"null":"" + age, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sex==null?"null":sex, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(career==null?"null":career, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(zipcode==null?"null":zipcode, delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_id = null; } else {
      this.user_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.age = null; } else {
      this.age = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.sex = null; } else {
      this.sex = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.career = null; } else {
      this.career = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.zipcode = null; } else {
      this.zipcode = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_id = null; } else {
      this.user_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.age = null; } else {
      this.age = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.sex = null; } else {
      this.sex = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.career = null; } else {
      this.career = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.zipcode = null; } else {
      this.zipcode = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    usr o = (usr) super.clone();
    return o;
  }

  public void clone0(usr o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("user_id", this.user_id);
    __sqoop$field_map.put("age", this.age);
    __sqoop$field_map.put("sex", this.sex);
    __sqoop$field_map.put("career", this.career);
    __sqoop$field_map.put("zipcode", this.zipcode);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("user_id", this.user_id);
    __sqoop$field_map.put("age", this.age);
    __sqoop$field_map.put("sex", this.sex);
    __sqoop$field_map.put("career", this.career);
    __sqoop$field_map.put("zipcode", this.zipcode);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
