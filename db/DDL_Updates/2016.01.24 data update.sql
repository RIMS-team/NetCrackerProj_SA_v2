insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
1, 1, NULL, 'FUll_NAME', 'Full name' );

update ATTRTYPE set
"ATTR_ID" = 1, "OBJECT_TYPE_ID" = 1, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'FUll_NAME', "NAME" = 'Full name'
where ATTR_ID = 1;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
2, 1, NULL, 'EMAIL', 'E-Mail' );

update ATTRTYPE set
"ATTR_ID" = 2, "OBJECT_TYPE_ID" = 1, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'EMAIL', "NAME" = 'E-Mail'
where ATTR_ID = 2;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
3, 1, NULL, 'PHONE_NUMBER', 'Phone number' );

update ATTRTYPE set
"ATTR_ID" = 3, "OBJECT_TYPE_ID" = 1, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'PHONE_NUMBER', "NAME" = 'Phone number'
where ATTR_ID = 3;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
4, 2, NULL, 'PASSWORD', 'Password' );

update ATTRTYPE set
"ATTR_ID" = 4, "OBJECT_TYPE_ID" = 2, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'PASSWORD', "NAME" = 'Password'
where ATTR_ID = 4;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
6, 5, 2, 'USER_FROM', 'User' );

update ATTRTYPE set
"ATTR_ID" = 6, "OBJECT_TYPE_ID" = 5, "OBJECT_TYPE_ID_REF" = 2, "CODE" = 'USER_FROM', "NAME" = 'User'
where ATTR_ID = 6;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
7, 5, 1, 'EMPLOYEE_TO', 'Employee' );

update ATTRTYPE set
"ATTR_ID" = 7, "OBJECT_TYPE_ID" = 5, "OBJECT_TYPE_ID_REF" = 1, "CODE" = 'EMPLOYEE_TO', "NAME" = 'Employee'
where ATTR_ID = 7;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
8, 5, 4, 'INVENTORY', 'Inventory' );

update ATTRTYPE set
"ATTR_ID" = 8, "OBJECT_TYPE_ID" = 5, "OBJECT_TYPE_ID_REF" = 4, "CODE" = 'INVENTORY', "NAME" = 'Inventory'
where ATTR_ID = 8;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
9, 7, NULL, 'NAME', 'Name' );

update ATTRTYPE set
"ATTR_ID" = 9, "OBJECT_TYPE_ID" = 7, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'NAME', "NAME" = 'Name'
where ATTR_ID = 9;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
10, 7, NULL, 'LOCATION', 'Location' );

update ATTRTYPE set
"ATTR_ID" = 10, "OBJECT_TYPE_ID" = 7, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'LOCATION', "NAME" = 'Location'
where ATTR_ID = 10;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
11, 7, NULL, 'MEMORY_TYPE', 'Memory type' );

update ATTRTYPE set
"ATTR_ID" = 11, "OBJECT_TYPE_ID" = 7, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'MEMORY_TYPE', "NAME" = 'Memory type'
where ATTR_ID = 11;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
12, 7, NULL, 'MODEL', 'Model' );

update ATTRTYPE set
"ATTR_ID" = 12, "OBJECT_TYPE_ID" = 7, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'MODEL', "NAME" = 'Model'
where ATTR_ID = 12;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
13, 4, NULL, 'INVENTORY_NUM', 'Inventory num' );

update ATTRTYPE set
"ATTR_ID" = 13, "OBJECT_TYPE_ID" = 4, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'INVENTORY_NUM', "NAME" = 'Inventory num'
where ATTR_ID = 13;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
14, 7, NULL, 'SERIAL_NUMBER', 'Serial num' );

update ATTRTYPE set
"ATTR_ID" = 14, "OBJECT_TYPE_ID" = 7, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'SERIAL_NUMBER', "NAME" = 'Serial num'
where ATTR_ID = 14;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
15, 5, NULL, 'DATE', 'Date' );

update ATTRTYPE set
"ATTR_ID" = 15, "OBJECT_TYPE_ID" = 5, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'DATE', "NAME" = 'Date'
where ATTR_ID = 15;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
16, 4, 9, 'STATUS', 'Inventory status' );

update ATTRTYPE set
"ATTR_ID" = 16, "OBJECT_TYPE_ID" = 4, "OBJECT_TYPE_ID_REF" = 9, "CODE" = 'STATUS', "NAME" = 'Inventory status'
where ATTR_ID = 16;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
25, 8, NULL, 'ORDER', 'Order' );

update ATTRTYPE set
"ATTR_ID" = 25, "OBJECT_TYPE_ID" = 8, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'ORDER', "NAME" = 'Order'
where ATTR_ID = 25;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
19, 5, 10, 'ORDER_STATUS', 'Order status' );

update ATTRTYPE set
"ATTR_ID" = 19, "OBJECT_TYPE_ID" = 5, "OBJECT_TYPE_ID_REF" = 10, "CODE" = 'ORDER_STATUS', "NAME" = 'Order status'
where ATTR_ID = 19;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
20, 8, NULL, 'FIRST_NOTIFICATION', 'First notification' );

update ATTRTYPE set
"ATTR_ID" = 20, "OBJECT_TYPE_ID" = 8, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'FIRST_NOTIFICATION', "NAME" = 'First notification'
where ATTR_ID = 20;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
21, 8, NULL, 'SECOND_NOTIFICATION', 'Second notification' );

update ATTRTYPE set
"ATTR_ID" = 21, "OBJECT_TYPE_ID" = 8, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'SECOND_NOTIFICATION', "NAME" = 'Second notification'
where ATTR_ID = 21;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
22, 8, NULL, 'THIRD_NOTIFICATION', 'Third notification' );

update ATTRTYPE set
"ATTR_ID" = 22, "OBJECT_TYPE_ID" = 8, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'THIRD_NOTIFICATION', "NAME" = 'Third notification'
where ATTR_ID = 22;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
23, 9, NULL, 'INV_STATUS', 'Inventory status' );

update ATTRTYPE set
"ATTR_ID" = 23, "OBJECT_TYPE_ID" = 9, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'INV_STATUS', "NAME" = 'Inventory status'
where ATTR_ID = 23;

insert into ATTRTYPE (
"ATTR_ID", "OBJECT_TYPE_ID", "OBJECT_TYPE_ID_REF", "CODE", "NAME" )
values (
24, 10, NULL, 'ORD_STATUS', 'Order status' );

update ATTRTYPE set
"ATTR_ID" = 24, "OBJECT_TYPE_ID" = 10, "OBJECT_TYPE_ID_REF" = NULL, "CODE" = 'ORD_STATUS', "NAME" = 'Order status'
where ATTR_ID = 24;

--====================================================

insert into LISTTYPE (
"ID", "ATTRTYPE_CODE", "CODE", "NAME", "COMMENTS" )
values (
1, 'INV_STATUS', 'IN_USE', 'In use', 'Inventory status' );

update LISTTYPE set
"ID" = 1, "ATTRTYPE_CODE" = 'INV_STATUS', "CODE" = 'IN_USE', "NAME" = 'In use', "COMMENTS" = 'Inventory status'
where ID = 1;

insert into LISTTYPE (
"ID", "ATTRTYPE_CODE", "CODE", "NAME", "COMMENTS" )
values (
2, 'INV_STATUS', 'IN_STOCK', 'In stock', 'Inventory status' );

update LISTTYPE set
"ID" = 2, "ATTRTYPE_CODE" = 'INV_STATUS', "CODE" = 'IN_STOCK', "NAME" = 'In stock', "COMMENTS" = 'Inventory status'
where ID = 2;

insert into LISTTYPE (
"ID", "ATTRTYPE_CODE", "CODE", "NAME", "COMMENTS" )
values (
3, 'INV_STATUS', 'ON_DIAGNOSTICS', 'On diagnostics', 'Inventory status' );

update LISTTYPE set
"ID" = 3, "ATTRTYPE_CODE" = 'INV_STATUS', "CODE" = 'ON_DIAGNOSTICS', "NAME" = 'On diagnostics', "COMMENTS" = 'Inventory status'
where ID = 3;

insert into LISTTYPE (
"ID", "ATTRTYPE_CODE", "CODE", "NAME", "COMMENTS" )
values (
4, 'ORD_STATUS', 'REQUEST', 'Request', 'Order status' );

update LISTTYPE set
"ID" = 4, "ATTRTYPE_CODE" = 'ORD_STATUS', "CODE" = 'REQUEST', "NAME" = 'Request', "COMMENTS" = 'Order status'
where ID = 4;

insert into LISTTYPE (
"ID", "ATTRTYPE_CODE", "CODE", "NAME", "COMMENTS" )
values (
5, 'ORD_STATUS', 'ISSUED', 'Issued', 'Order status' );

update LISTTYPE set
"ID" = 5, "ATTRTYPE_CODE" = 'ORD_STATUS', "CODE" = 'ISSUED', "NAME" = 'Issued', "COMMENTS" = 'Order status'
where ID = 5;

insert into LISTTYPE (
"ID", "ATTRTYPE_CODE", "CODE", "NAME", "COMMENTS" )
values (
6, 'ORD_STATUS', 'EXTENDED', 'Extended', 'Order status' );

update LISTTYPE set
"ID" = 6, "ATTRTYPE_CODE" = 'ORD_STATUS', "CODE" = 'EXTENDED', "NAME" = 'Extended', "COMMENTS" = 'Order status'
where ID = 6;

insert into LISTTYPE (
"ID", "ATTRTYPE_CODE", "CODE", "NAME", "COMMENTS" )
values (
7, 'ORD_STATUS', 'OVERDUE', 'Overdue', 'Order status' );

update LISTTYPE set
"ID" = 7, "ATTRTYPE_CODE" = 'ORD_STATUS', "CODE" = 'OVERDUE', "NAME" = 'Overdue', "COMMENTS" = 'Order status'
where ID = 7;

insert into LISTTYPE (
"ID", "ATTRTYPE_CODE", "CODE", "NAME", "COMMENTS" )
values (
8, 'ORD_STATUS', 'CLOSED', 'Closed', 'Order status' );

update LISTTYPE set
"ID" = 8, "ATTRTYPE_CODE" = 'ORD_STATUS', "CODE" = 'CLOSED', "NAME" = 'Closed', "COMMENTS" = 'Order status'
where ID = 8;

--====================================

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
1, NULL, 'EMPLOYEE', 'Employee', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 1, "PARENT_ID" = NULL, "CODE" = 'EMPLOYEE', "NAME" = 'Employee', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 1;

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
2, 1, 'User', 'User', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 2, "PARENT_ID" = 1, "CODE" = 'User', "NAME" = 'User', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 2;

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
3, 2, 'ADMIN', 'Admin', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 3, "PARENT_ID" = 2, "CODE" = 'ADMIN', "NAME" = 'Admin', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 3;

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
4, NULL, 'INVENTORY', 'Inventory', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 4, "PARENT_ID" = NULL, "CODE" = 'INVENTORY', "NAME" = 'Inventory', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 4;

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
5, NULL, 'ORDER', 'Order', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 5, "PARENT_ID" = NULL, "CODE" = 'ORDER', "NAME" = 'Order', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 5;

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
6, 4, 'CARD', 'Card', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 6, "PARENT_ID" = 4, "CODE" = 'CARD', "NAME" = 'Card', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 6;

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
7, 4, 'NOTEPAD', 'Notebook', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 7, "PARENT_ID" = 4, "CODE" = 'NOTEPAD', "NAME" = 'Notebook', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 7;

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
8, 5, 'NOTIFICATION', 'Notification', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 8, "PARENT_ID" = 5, "CODE" = 'NOTIFICATION', "NAME" = 'Notification', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 8;

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
9, NULL, 'INV_STATUS', 'Inventory status', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 9, "PARENT_ID" = NULL, "CODE" = 'INV_STATUS', "NAME" = 'Inventory status', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 9;

insert into OBJTYPE (
"OBJECT_TYPE_ID", "PARENT_ID", "CODE", "NAME", "DESCRIPTION" )
values (
10, NULL, 'ORD_STATUS', 'Order satus', NULL );

update OBJTYPE set
"OBJECT_TYPE_ID" = 10, "PARENT_ID" = NULL, "CODE" = 'ORD_STATUS', "NAME" = 'Order satus', "DESCRIPTION" = NULL
where OBJECT_TYPE_ID = 10;




