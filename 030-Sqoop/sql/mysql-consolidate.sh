FN=allData.sql
DB_NAME=movies_db

echo "DROP DATABASE ${DB_NAME};

CREATE DATABASE ${DB_NAME};
USE ${DB_NAME};
" > $FN

echo "
" >> $FN
cat data.sql       >> $FN
echo "
" >> $FN
cat movie.sql      >> $FN
echo "
" >> $FN
cat genre.sql      >> $FN
echo "
" >> $FN
cat occupation.sql >> $FN
echo "
" >> $FN
cat user.sql       >> $FN
echo "
" >> $FN

