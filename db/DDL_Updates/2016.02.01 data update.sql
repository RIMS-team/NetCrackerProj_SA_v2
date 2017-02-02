

insert into NOTIF_TEMPL (
"NOTIF_NUM", "USER_ID", "TEMPLATE" )
values (
1, NULL, 'Hello Dear |NAME| , your |INVENTORY_TYPE| with number |INVENTORY_NUM| is already overdue, please return it as soon as possible. Date:  |DUE_DATE| . First Send.' );


insert into NOTIF_TEMPL (
"NOTIF_NUM", "USER_ID", "TEMPLATE" )
values (
2, NULL, 'Hello Dear |NAME| , your |INVENTORY_TYPE| with number |INVENTORY_NUM| is already overdue, please return it as soon as possible. Date:  |DUE_DATE| . Second Send.' );


insert into NOTIF_TEMPL (
"NOTIF_NUM", "USER_ID", "TEMPLATE" )
values (
3, NULL, 'Hello Dear |NAME| , your |INVENTORY_TYPE| with number |INVENTORY_NUM| is already overdue, please return it as soon as possible. Date:  |DUE_DATE| . Third Send.' );


commit;

