 -- This is a sample control file
OPTIONS (SKIP=1) 
LOAD DATA
BADFILE     'Users.bad'
DISCARDFILE 'Users.dsc'
 APPEND
 INTO TABLE SDB_Users
 TRAILING NULLCOLS
(
	Name TERMINATED BY ",",
	Email TERMINATED BY WHITESPACE
)


