FN=allData.hql
DB_NAME=movies_db

echo "DROP DATABASE ${DB_NAME} CASCADE;

CREATE DATABASE ${DB_NAME};
USE ${DB_NAME};
" > $FN

echo "
" >> $FN
cat data.hql       >> $FN
echo "
" >> $FN
cat movie.hql      >> $FN
echo "
" >> $FN
cat genre.hql      >> $FN
echo "
" >> $FN
cat occupation.hql >> $FN
echo "
" >> $FN
cat user.hql       >> $FN
echo "
" >> $FN


