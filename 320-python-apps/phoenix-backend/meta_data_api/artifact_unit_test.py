import uuid
from artifact import *
from collection import *

"""
create some data for our tests
the tuples below include:
    1) document name
    2) document url
    3) load id
    4) mail id
    5) file type
    6) file size
"""
""" TEST METHOD SKELETON:

    errs: int = 0
    printmsg("--- xxx ---", 0)

    try:
        printmsg("xxx")
        if ( TEST ):
            raise AssertionError("xxx")
        passed()
    except Exception as e:
        failed(e)
        errs += 1

    return errs
"""
data        = [ ("artifact for b1-f1", "s3://bucket-1/file-1", "Z3844773", "mailid:38473", "W-2", 75526),
                (None, "s3://bucket-1/file-4", "Z8367349", "mailid:38473", "Credit Report", 9234),
                ("artifact for b1-f2", "s3://bucket-1/file-2", "Z3844884", "mailid:38473", "Utility Bill", 92827),
                ("artifact for b1-f3", "s3://bucket-1/file-3", "Z4873829", "mailid:38473", "Utility Bill", None),
                ("artifact for b2-f1", "s3://bucket-2/file-1", "1234s837", "mailid:38473", "Bank Statement", 76265),
                ("artifact for b2-f2", "s3://bucket-2/file-2", "1234s883", "mailid:38473", "W-2", 5632),
                ("artifact for b2-f3", "s3://bucket-2/file-3", "1245s383", None, "Paycheck", None),
                ("artifact for b3-f1", "s3://bucket-3/file-1", None, "mailid:38473", "Utility Bill", 37212),
                ("artifact for b3-f2", "s3://bucket-3/file-2", "298-387-3843", "mailid:38473", "Bank Statement", 39837),
                ("artifact for b3-f3", "s3://bucket-3/file-3", "633-598-3836", "mailid:38473", None, 62524),
                ("artifact for b4-f1", "s3://bucket-4/file-1", "uidDE383EjF", "mailid:special", "UNKNOWN", 1000000)
              ]

label_list_1: dict = { "one": "apple", "two": "banana", "three": "cucumber", \
                       "four": "down", "five": "elephant", "six": "fifty", \
                       "seven": "grasshopper", "eight": "hadoop", "nine": "igloo" \
                     }

def printmsg( message: str, indent: int = 1) -> None:
    print("    "*indent + message)

def passed( exc: Exception = None, msg: str = None) -> None:
    display: str = ""
    if ( None != exc ):
        display += "(" + str(exc) + ")"

    if ( msg != None ):
        display += " ==> " + msg

    printmsg("PASSED" + display, 2)

def failed( exc: Exception = None, msg: str = None) -> None:
    display: str = ""
    if ( None != exc ):
        display += "(" + str(exc) + ")"

    if ( msg != None ):
        display += " ==> " + msg

    printmsg("FAILED" + display, 2)

def create_artifact( index: int ) -> Artifact:
    vals = data[index]
    return Artifact(vals[1],vals[0],vals[2],vals[3],vals[4],vals[5])

def display_results( error_counts: dict ) -> None:
    total_err = 0
    printmsg( "Artifact Unit Test Results:",0)
    for test in error_counts.keys():
        crt_err: int = error_counts[test]
        printmsg( test + ": " + str(crt_err) + " errors")
        total_err += crt_err

    print()
    print("Total errors: " + str(total_err))
    print()

def test_create_artifact() -> int:
    errs: int = 0
    printmsg("--- create artifact tests ---", 0)
    try:
        printmsg("artifact test 1 - no info")
        a = Artifact()
        errs += 1
        failed()
    except Exception as e:
        passed(e)

    try:
        printmsg("artifact test 2 - bad info")
        b = Artifact(3)
        failed()
        errs += 1
    except Exception as e:
        passed(e)

    printmsg("artifact test 3 - create 10 artifacts")
    for x in range(10):
        try:
            a = create_artifact(x)
            passed(msg=str(x))
        except:
            failed(msg=str(x))

    try:
        printmsg("artifact test 4 - check for correct data")
        ARR_IDX = 10
        c = create_artifact(ARR_IDX)
        if (c.doc_name != data[ARR_IDX][0]):
            raise AssertionError("wrong document name")
        if (c._doc_url != data[ARR_IDX][1]):
            raise AssertionError("wrong document url")
        if (c.loan_id != data[ARR_IDX][2]):
            raise AssertionError("wrong loan id")
        if (c.mail_id != data[ARR_IDX][3]):
            raise AssertionError("wrong mail id")
        if (c.doc_type != data[ARR_IDX][4]):
            raise AssertionError("wrong file type")
        if (c.file_size != data[ARR_IDX][5]):
            raise AssertionError("wrong file size")

        passed()

    except Exception as e:
        failed(e)
        errs += 1

    return errs

def test_set_label() -> int:
    errs: int = 0
    printmsg("--- set label tests ---", 0)
    name: str = "test-name"
    value: str = "test-value"
    value_2: str = "new-test-value"

    a: Artifact = create_artifact(4)
    ("artifact for b2-f2", "s3://bucket-2/file-2", "1234s883", "mailid:38473", "W-2", 5632),

    try:
        printmsg("add simple label")
        a.set_label(name, value)
        if ( value != a.get_label(name) ):
            raise AssertionError("Did not receive correct data back from newly created label")
        passed()
    except Exception as e:
        printmsg("FAILED (" + str(e) + ")",2)
        errs += 1

    try:
        printmsg("replace label with a new value")
        a = create_artifact(9)
        a.set_label(name, value_2)
        if ( value_2 != a.get_label(name) ):
            raise AssertionError("Did not receive correct data back from updated label")
        passed()
    except Exception as e:
        failed(e)
        errs += 1

    return errs

def test_set_multiple_labels() -> int:
    errs: int = 0
    printmsg("--- set multiple labels tests ---", 0)

    try:
        printmsg("add a dictionary to artifact and check how it comes back")
        ar = create_artifact(7)
        ar.set_multiple_labels(label_list_1)
        if (label_list_1!=ar.get_all_labels()):
            raise AssertionError("labels went in, but came out incorrectly")
        passed()

    except Exception as e:
        failed(e)
        errs += 1

    return errs

def test_db_write_and_read() -> int:
    errs: int = 0
    printmsg("--- test database read and write ---", 0)

    id_list: list = []

    printmsg("write 10 records to db")
    for i in range(10):
        try:
            a: Artifact = create_artifact(i)
            a.write()
            passed(msg=str(i))
            id_list.append( a )
        except Exception as e:
            failed(e,msg=str(i))
            errs += 1
            id_list.append( None )
    printmsg("read 10 records from db")
    idx = -1
    for art in id_list:
        try:
            idx += 1
            a: Artifact = load_artifact( art._id )
            if ( art != a ):
                raise AssertionError( "Data read from db does not match data inserted." )
            passed(msg=str(idx))
        except Exception as e:
            failed(e,str(idx))
            errs += 1

    printmsg("write artifact with a list of labels")

    try:
        a: Artifact = create_artifact(10)
        a.set_multiple_labels(label_list_1)
        a.remove_label("four")
        a.write()

        b: Artifact = load_artifact(a._id)

        if (b != a):
            raise AssertionError("Data read from db does not match data inserted.")

        passed()

    except Exception as e:
        failed(e)
        errs += 1

    return errs

def test_load_by_label() -> int:
    errs: int = 0
    printmsg("--- Load artifacts by label tests ---", 0)

    unique: str = uuid.uuid1().hex
    try:
        printmsg("load to check for results by single label")
        a: Artifact = Artifact("s3://test-bkt/test","artifact for this test", "s837R4", "mailid:bob", "Bank Statement", 6265)
        a.set_multiple_labels( label_list_1 )
        a.set_label("label_test_1", unique )

        a.write()
        b: Artifact = Artifact("s3://test-bkt/test-1","artifact for this test-2", "s837R4", "mailid:bob", "Bank Statement", 34565)
        b.set_multiple_labels( label_list_1 )
        b.set_label("label_test_1", unique )
        b.set_label("label_test_2", unique )
        b.write()

        art_list: list = load_artifacts_by_label("label_test_1", unique)

        if ( len(art_list) != 2 ):
            raise AssertionError("wrong number of artifacts returned")
        passed()

    except Exception as e:
        failed(e)
        errs += 1

    try:
        printmsg("load to check for results by multiple label")
        l: dict = { "label_test_1": unique, "label_test_2": unique }

        art_list: list = load_artifacts_by_labels( l )

        if ( len(art_list) != 1 ):
            raise AssertionError("wrong number of artifacts returned")
        passed()

    except Exception as e:
        failed(e)
        errs += 1

    return errs

def test_collection() -> int:
    errs: int = 0
    printmsg("--- Collection Tests ---", 0)

    printmsg("create a collection")

    try:
        # start indent
        # create some artifacts and add to our collection
        c_name: str = uuid.uuid1().hex
        c: Collection = Collection(c_name)
        for i in range(6):
            a: Artifact = create_artifact(i)
            a.write()
            c.add_artifact(a)
        c.write()

        d: Collection = Collection(c_name)
        if len(c.get_member_ids()) != len(d.get_member_ids()):
            print(c)
            print(d)
            raise AssertionError("number of items in collection from read db doesn't match those going in")
        passed()

    except Exception as e:
        failed(e)
        errs += 1

    return errs

if __name__ == '__main__':
    errors = dict()
    errors["Create Artifact Tests"] = test_create_artifact()
    print()
    errors["Add Label Tests"] = test_set_label()
    print()
    errors["Add Label Group Tests"] = test_set_multiple_labels()
    print()
    errors["Read and Write to Db Tests"] = test_db_write_and_read()
    print()
    errors["Read by Label Tests"] = test_load_by_label()
    print()
    errors["Collections Tests"] = test_collection()
    print()

    display_results(errors)

    q: Artifact = load_artifacts_by_labels( {'label_test_2': None } )
    print( len(q) )