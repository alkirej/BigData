#!/bin/bash

echo ... GENERATE A LIST WITH 30 NUMBERS ...
echo
scala /home/jeff/BigData/009-SkillsTestInScala/q1-random.scala

echo
echo ... FIND THE LOWEST POSITIVE MISSING INTEGER FROM A LIST ...
scala /home/jeff/BigData/009-SkillsTestInScala/q2-missingNum.scala
echo

echo
echo "... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ..."
echo "... IN SCALA YIELD ADDS A RESULT TO A FOR CONSTRUCT         ..."
echo "... WHILE RETURN RETURNS (POSSIBLY) A VALUE FROM A FUNCTION ..."
echo "... OR METHOD                                               ..."
echo "... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ..."
echo "... SHOW THE DIFFERENCE BETWEEN YIELD AND RETURN            ..."
echo "... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ..."
scala /home/jeff/BigData/009-SkillsTestInScala/q3-yieldReturn.scala
echo

echo
echo ... FIND THE LOWEST POSITIVE MISSING INTEGER FROM A LIST ...
scala /home/jeff/BigData/009-SkillsTestInScala/q4-missingNum.scala
echo

echo
echo ... SQL ASSIGNMENT - NO DIFFERENCE BETWEEN THIS AND PREVIOUS ...
echo ... EXAMPLE BECAUSE IT SQL AND NOT PYTHON                    ...
/home/jeff/BigData/009-SkillsTestInScala/q5-sql.sh
echo
