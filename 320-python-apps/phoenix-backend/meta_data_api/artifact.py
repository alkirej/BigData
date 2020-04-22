"""
This API is designed to allow end users to interact with the Ellie Mae
meta data storage for the Documentation Classification project.

Date: April 20, 2020

Commonly used methods:
    Artifact( doc_url, do_name, loan_id, mail_id, doc_type, file_size ) -> create
                                    a new artifact with the supplied info.  Only
                                    doc_url is required.  Other data is optional.
    write() -> save the current state of the Artifact to the data store

    load_artifacts_by_labels -> supply a label and value and get all matching artifacts
    load_artifacts_by_labels -> supply a list of labels and desired values and
                                    get a list of artifacts
"""
import uuid
import datetime
import copy
import phoenixdb
from phoenixdb.cursor import Cursor
from phoenixdb.connection import Connection

class Artifact:
    DATABASE_URL: str = "http://localhost:8765"
    INSERT_STMT: str = "UPSERT INTO artifact ( artifact_id, doc_name, doc_type, loan_id, mail_id, " \
                            + "doc_url, file_size, created ) VALUES ( ?,?,?,?, ?,?,?,? )"
    SELECT_STMT_BEGINNING: str = "SELECT artifact_id, doc_name, doc_type, loan_id, mail_id, " \
                            + "doc_url, file_size, created FROM artifact WHERE artifact_id="
    LABEL_INSERT: str = "UPSERT INTO label_indexable ( artifact_id, label_name, label_value ) VALUES ( ?, ?, ? )"
    FULL_LABEL_SELECT: str = "SELECT label_name, label_value FROM label_indexable WHERE "
    ART_ID_LABEL_SELECT: str = "SELECT artifact_id FROM label_indexable WHERE "
    LABEL_DELETE: str = "DELETE FROM label_indexable WHERE artifact_id=? AND label_name=?"

    ARTIFACT_BY_LABEL_QUERY_BEGINNING: str = "SELECT artifact_id FROM label WHERE "

    def __init__(self, \
                 doc_url: str,  \
                 doc_name: str = None, \
                 loan_id: str = None, \
                 mail_id: str = None, \
                 doc_type: str = None, \
                 file_size: int = None
                ):
        """
        Create a new Artifact:
            loan_id -
            mail_id -
            doc_url - S3 url of where document is stored.
            created - when the document was created. If it is not supplied,
                         it will be set to the date/time this method was
                        called
        """
        if type(doc_url) != str:
            raise Exception("url must be a string")
        self._doc_url: str  = doc_url
        self.doc_type: str  = doc_type
        self.doc_name: str  = doc_name
        self.loan_id: str   = loan_id
        self.mail_id: str   = mail_id
        self.file_size: int = file_size

        self._created: datetime = str( datetime.datetime.now() )
        my_id = uuid.uuid1()
        self._id: str           = str( salt(my_id.int) ) + my_id.hex
        self._labels:dict        = dict()

    def __str__(self):
        """
        Used to display the contents of this object.
        """
        main_str: str = "(Artifact: id=" \
                                + self._id \
                                + "; doc_url=" \
                                + self._doc_url \
                                + "; doc_name=" \
                                + ("" if None==self.doc_name else self.doc_name) \
                                + "; loan_id=" \
                                + ("" if None==self.loan_id else self.loan_id) \
                                + "; mail_id=" \
                                + ("" if None==self.mail_id else self.mail_id) \
                                + "; created=" \
                                + str(self._created) \
                                + "; doc_type=" \
                                + ("" if None==self.doc_type else self.doc_type) \
                                + "; file_size=" \
                                + str("" if None == self.file_size else str(self.file_size)) \
                         + ")"
        return main_str + "\n    Labels: " + str(self._labels)

    def __eq__(self, other):
        """
        Test for equality
        """
        if isinstance(other, self.__class__):
            return self.__dict__ == other.__dict__
        else:
            return False

    def __ne__(self, other):
        """
        Test for inequality
        """
        return not self.__eq__(other)

    def set_label(self, name: str, value: str) -> None:
        """
        Add a label to the label store
        :param name: label's name (or key)
        :param value: label's value
        """
        self._labels[name] = value

    def set_multiple_labels(self, labels: dict) -> None:
        """
        Associate a group of labels with this artifact
        :param labels: dictionary of labels to add
        """
        for key in labels.keys():
            self._labels[key] = labels[key]

    def get_label(self, name: str) -> str:
        """
        Get the value of the label with the given name (key).
        :param name: label to retreive.
        :return: label's value
        """
        return self._labels[name]

    def get_all_labels(self) -> dict:
        """
        :return: a copy of all labels associated with this artifact
        """
        return copy.deepcopy(self._labels)

    def remove_label(self, name:str):
        self.set_label( name, None )

        return ""

    def write_indexable_labels(self, conn: Connection) -> None:
        for key in self._labels.keys():
            value: str = self._labels[key]
            cur = conn.cursor()
            if ( None == value ):
                cur.execute(self.LABEL_DELETE, (self._id, key) )

            else:
                cur.execute(self.LABEL_INSERT, (self._id, key, value) )
            cur.close()

    def write_wide_labels(self, conn: Connection) -> None:
        # add labels for this artifact to the db.
        for key in self._labels.keys():
            value: str = self._labels[key]
            query: str = "UPSERT INTO label_wide ( artifact_id, " + key + " varchar ) VALUES ( ?, ? )"
            cur = conn.cursor()
            cur.execute(query, (self._id, value) )
            cur.close()

    def write_labels(self, conn: Connection) -> None:
        """
        Store current state of labels for this artifact.
        Note: the labels are stored in two places. One that is indexed on the label name
            and the other that is useful for joins.
        """
        self.write_wide_labels(conn)
        self.write_indexable_labels(conn)

        # remove any empty values now that they aren't in the db.
        mark_for_removal: list = []

        # Iterate through and collect empty values
        for key in self._labels.keys():
            value: str = self._labels[key]
            if ( None == value ):
                mark_for_removal.append(key)

        # Remove empty values
        for key in mark_for_removal:
            # remove empty items from label list
            try:
                del self._labels[key]
            finally:
                pass

    def write(self) -> None:
        """
        write the current object to the database
        """
        conn: Connection = phoenixdb.connect(self.DATABASE_URL, autocommit=True)
        cur: Cursor = conn.cursor()
        cur.execute(self.INSERT_STMT, (self._id,self.doc_name,self.doc_type,self.loan_id,\
                                       self.mail_id,self._doc_url,self.file_size,str(self._created)\
                                      )
                    )
        cur.close()
        self.write_labels(conn)
        conn.close()

def sql_quoted_string( text: str ) -> str:
    """
    Convert a string(hello) into a quoted string('hello').  If the value is null, the string null
    (without quotes) is returned.
    :param text: the text to quote
    :return: the correct value for sql
    """
    if ( None==text ):
        return "null"
    else:
        return "'" + text + "'"

def sql_number( num: int ) -> str:
    """
    Convert a number into a string. If the received value is None, return the text "null"
    :param text: the number to use in sql
    :return: the correct value for an sql query.
    """
    if ( None==num ):
        return "null"
    else:
        return str(num)

def salt(id: int) -> int:
    """
    Take a number and return its "salt". The goal of salt is to prevent "hotspots"
    :param id: the number (ususally an id) needing salt
    :return: the numeric salt (a value between 000 and 999)
    """
    return id % 1000

def load_artifact(id: str) -> Artifact:
    query: str = Artifact.SELECT_STMT_BEGINNING + sql_quoted_string(id)
    conn: Connection = phoenixdb.connect(Artifact.DATABASE_URL, autocommit=True)
    cur: Cursor = conn.cursor()
    cur.execute(query)
    data: tuple = cur.fetchone()
    cur.close()

    a: Artifact = Artifact( data[5], data[1], data[3], data[4], data[2], data[6] )
    a._id = id
    a._created = data[7]

    # read labels
    query = Artifact.FULL_LABEL_SELECT + " artifact_id="+ sql_quoted_string(id)
    cur = conn.cursor()
    cur.execute(query)
    results: list = cur.fetchall()
    cur.close()

    for l in results:
        a.set_label( l[0], l[1] )

    conn.close()
    return a

def load_artifacts_by_labels( labels: dict ) -> list:
    """
    Given a set of labels, return the elements that match the given criteria
    Note: the query match will match any label values that start with the given value
    Note: if a label has None for a label value, it will match all artifacts with
            any value for that.
    :param labels: pattern(s) to match
    :return: list of labels matching the criteria
    """
    conn: Connection = phoenixdb.connect(Artifact.DATABASE_URL, autocommit=True)

    first: bool = True
    matching_ids: set = set()
    for key in labels.keys():
        if ( None==labels[key]):
            query: str = Artifact.ART_ID_LABEL_SELECT + " label_name=" + sql_quoted_string(key)
        else:
            query: str = Artifact.ART_ID_LABEL_SELECT + " label_name=" + sql_quoted_string(key) \
                            + " AND label_value LIKE " + sql_quoted_string(labels[key]+"%")
        cur: Cursor = conn.cursor()
        cur.execute(query)
        current_id_list: list = cur.fetchall()
        current_id_set: set = set()
        for id_record in current_id_list:
            current_id_set.add(str(id_record[0]))
        cur.close()
        if (first):
            first = False
            matching_ids = copy.deepcopy(current_id_set)
        else:
            matching_ids = matching_ids.intersection(current_id_set)

    conn.close()

    return_list: list = []
    for id in matching_ids:
        return_list.append( load_artifact(id) )

    return return_list

def load_artifacts_by_label( name: str, value: str ) -> list:
    labels: dict = { name: value }
    return load_artifacts_by_labels( labels )

def double_quoted_string( value: str ) -> str:
    """
    add " ... " around a string
    :param value: string to quote
    """
    return '"' + value + '"'
