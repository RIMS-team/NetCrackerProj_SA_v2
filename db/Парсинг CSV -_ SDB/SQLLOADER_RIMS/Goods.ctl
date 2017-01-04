 -- This is a sample control file
OPTIONS (SKIP=1) 
LOAD DATA
INFILE      'C:\SQLLOADER_RIMS\Goods.csv'
BADFILE     'Goods.bad'
DISCARDFILE 'Goods.dsc'
 APPEND
 INTO TABLE SDB_Orders
 TRAILING NULLCOLS
(
	Name TERMINATED BY ",",
	In_Use_by TERMINATED BY ",",
	Status TERMINATED BY ",",
	Location TERMINATED BY ",",
	Memory_Type TERMINATED BY ",",
	model.Model TERMINATED BY ",",
	Employee TERMINATED BY ",",
	Additional_Finance_Number TERMINATED BY ",",
	Last_inventory_date TERMINATED BY ",",
	Serial_Number TERMINATED BY ",",
	Description TERMINATED BY ","
)


