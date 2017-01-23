drop table SDB_Orders;
drop table SDB_Users;

create table SDB_Orders
(
	Name varchar2(2000),
	In_Use_by varchar2(2000),
	Inv_Status varchar2(2000),
	Location varchar2(2000),
	Memory_Type varchar2(2000),
	Model varchar2(2000),
	Employee varchar2(2000),
	Inventory_Num varchar2(2000),
	Last_inventory_date varchar2(2000),
	Serial_Number varchar2(2000),
	Description varchar2(2000),
	Is_Valid number(1) default 1
);

create table SDB_Users
(
	Name varchar2(2000),
	Email varchar2(2000),
	Is_Valid number(1) default 1
);