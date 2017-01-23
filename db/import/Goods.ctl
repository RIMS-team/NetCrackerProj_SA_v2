 -- This is a sample control file
OPTIONS (SKIP=1) 
LOAD DATA
BADFILE     'Goods.bad'
DISCARDFILE 'Goods.dsc'
 APPEND
 INTO TABLE SDB_Orders
 TRAILING NULLCOLS
(
	Name TERMINATED BY ",",
	In_Use_by TERMINATED BY ",",
	Inv_Status TERMINATED BY ",",
	Location TERMINATED BY ",",
	Memory_Type TERMINATED BY ",",
	Model TERMINATED BY ",",
	Employee TERMINATED BY ",",
	Inventory_Num TERMINATED BY ",",
	Last_Inventory_Date TERMINATED BY ",",
	Serial_Number TERMINATED BY ",",
	Description TERMINATED BY ","
)


