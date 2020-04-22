"""
This API is designed to allow end users to interact with the Ellie Mae
meta data storage for the Documentation Classification project.

Date: April 20, 2020

Commonly Used Methods:
    Collection(name) -> get the data from the db about this Collection (based on the name) if there is any.
                                        Create a new one if
    update_parent(parent) -> set the parent collection of this collection
    add_artifact(artifact or artifact_id) -> add a new artifact to the collection.
    remove_artifact(artifact or artifact_id) -> remove an artifact to the collection.
    get_member_ids() -> get a list of the artifacts in this collection.
                            The member artifacts can be loaded with the load_artifact(id) method
                            from artifact.py
"""
import json
import copy
import phoenixdb
from phoenixdb.cursor import Cursor
from phoenixdb.connection import Connection
from artifact import *

class Collection:
    LOAD_MEMBERS_QUERY: str = "SELECT artifact_list, parent FROM collection WHERE collection_name="
    COLLECTION_INSERT: str = "UPSERT INTO collection ( collection_name, artifact_list, parent ) VALUES ( ?, ?, ?)"
    JSON_ELEMENT_NAME: str = "artifact-list"

    def __init__(self, name: str):
        """
        Create a new collection of artifacts
        :param name: The name for the collection
        """
        self._name: str = name
        self._artifact_list: set = set()
        self._parent: str = None
        self.load_from_database()

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

    def __str__(self):
        return_val: str = "(Collection: name=" + self._name + "; parent=" + ("" if None==self._parent else self._parent) + ";"
        for i in self._artifact_list:
            return_val += "\n    " + i
        return_val += ")"
        return return_val

    def update_parent(self, parent: str) -> None:
        """
        Define the parent collection of this collection
        :param parent: new parent of collection
        """
        self._parent = parent

    def get_parent_name(self) -> str:
        return self._parent

    def add_artifact(self, a: Artifact) -> None:
        """
        add an artifact to the collection
        :param a: artifact to add
        """
        self.add_artifact_by_id(a._id)

    def add_artifact_by_id(self, artifact_id: str) -> None:
        """
        Add an artifact to this collection
        :param artifact_id: id of collection to add
        """
        if ( type(artifact_id)!=str ):
            raise TypeError("artifact id must be a str")
        self._artifact_list.add(str(artifact_id))

    def remove_artifact(self, a: Artifact) -> None:
        """
        remove an artifact from the collection
        :param a: artifact to remove
        """
        self._artifact_list.remove(a._id)

    def remove_artifact(self, artifact_id: str) -> None:
        """
        remove an artifact from the collection
        :param artifact_id: id of the item to remove
        """
        self._artifact_list.remove(artifact_id)

    def get_member_ids(self) -> list:
        """
        getter for the id list
        :return: artifact id list
        """
        return copy.deepcopy(self._artifact_list)

    def write(self) -> None:
        """
        write the current collection to the database
        """
        # make list of artifacts into json array
        art_list: str = '{"' + self.JSON_ELEMENT_NAME + '": ['
        first: bool = True
        for a in self._artifact_list:
            if (first):
                first=False
            else:
                art_list += ","

            art_list += double_quoted_string(a)
        art_list += ']}'

        conn: Connection = phoenixdb.connect(Artifact.DATABASE_URL, autocommit=True)
        cur: Cursor = conn.cursor()
        cur.execute(self.COLLECTION_INSERT, (self._name, art_list, self._parent) )
        cur.close()
        conn.close()

    def load_from_database(self) -> None:
        """
        Load the data for this collection from the database
        :return:
        """
        conn: Connection = phoenixdb.connect(Artifact.DATABASE_URL, autocommit=True)
        cur: Cursor = conn.cursor()
        cur.execute(self.LOAD_MEMBERS_QUERY + sql_quoted_string(self._name) )
        coll_record = cur.fetchone()
        cur.close()
        conn.close()

        if (coll_record != None):
            self._artifact_list = json.loads(coll_record[0])[self.JSON_ELEMENT_NAME]
            self._parent = coll_record[1]
            pass
        return None

