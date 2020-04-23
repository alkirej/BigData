# CREATE 1 MANAGED TABLE
sqoop --options-file sqoop-data.opt

# CREATE 4 MANAGED TABLES
sqoop --options-file sqoop-user.opt
sqoop --options-file sqoop-genre.opt
sqoop --options-file sqoop-occupation.opt
sqoop --options-file sqoop-movie.opt

