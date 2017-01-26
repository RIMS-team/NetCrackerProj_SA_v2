create or replace package dm_test_data is

  procedure drop_all_objects_by_type(p_obj_type_id in number);
  
  procedure drop_all_objects;
  
  ----
  
  procedure add_objects(
        p_count_employees           in number default 100,
        p_count_access_cards        in number default 200,
        p_count_notebooks           in number default 50,
        p_count_orders_access_cards in number default 500,
        p_count_orders_notebooks    in number default 150,
        p_count_notifications       in number default 0    -- 0 = на каждый ордер
        );
  
  procedure add_employees(
        p_count in number
        --,p_out_cursor out sys_refcursor   -- for debug
        );
  ----
  procedure add_access_cards(
        p_count in number
        --,p_out_cursor out sys_refcursor   -- for debug
        );
  ----
  procedure add_notebooks(
        p_count in number
        --,p_out_cursor out sys_refcursor   -- for debug
        );
  ----
  procedure add_card_orders(
        p_count      in number
        ,p_date_from in date
        --,p_out_cursor out sys_refcursor   -- for debug
        );
  ----
  procedure add_notebook_orders(
        p_count      in number
        ,p_date_from in date
        --,p_out_cursor out sys_refcursor   -- for debug
        );
  ----
  procedure add_notifications(
        p_count     in number
        --,p_out_cursor out sys_refcursor   -- for debug
        );
        

end dm_test_data;


/


create or replace package body dm_test_data is

  
  procedure drop_all_objects_by_type(p_obj_type_id in number) as
    type t_rec_id is record (id number);
    type t_tab_id is table of t_rec_id index by pls_integer;
    l_tab_objects_id t_tab_id;
  begin
    select o.object_id
      bulk collect into  l_tab_objects_id
      from objects o, objtype ot
     where o.object_type_id = ot.object_type_id and ot.object_type_id = p_obj_type_id;
     
    forall i in l_tab_objects_id.first..l_tab_objects_id.last
      delete from attributes
       where object_id = l_tab_objects_id(i).id;
     
    forall i in l_tab_objects_id.first..l_tab_objects_id.last
      delete from OBJREFERENCE
       where object_id = l_tab_objects_id(i).id or reference = l_tab_objects_id(i).id;
     
    forall i in l_tab_objects_id.first..l_tab_objects_id.last
      delete from objects
       where object_id = l_tab_objects_id(i).id;
      
  end;
  
  ----
  
  procedure drop_all_objects as
  begin
    drop_all_objects_by_type(8);  -- NOTIFICATION
    drop_all_objects_by_type(5);  -- ORDER
    drop_all_objects_by_type(6);  -- CARD
    drop_all_objects_by_type(7);  -- NOTEPAD
    drop_all_objects_by_type(4);  -- INVENTORY
    --drop_all_objects_by_type(3);  -- ADMIN
    --drop_all_objects_by_type(2);  -- User
    drop_all_objects_by_type(1);  -- EMPLOYEE
  end;
  
  ----
  
  procedure add_employees(
        p_count in number
        --,p_out_cursor out sys_refcursor   -- for debug
        ) 
  is
    l_last_name varchar2(15);
    l_cur_id  number;
  begin
    if p_count is null or p_count < 1 then
      raise_application_error(-20001, 'Ошибка в количестве создаваемых объектов.');
    end if;
    
    for i in 1..p_count loop
      l_cur_id := null;
      l_last_name := dbms_random.string('U', 1) || dbms_random.string('L', round(DBMS_RANDOM.VALUE(2, 8)));
      dm_employee.employee_insert(
        l_cur_id,  -- p_object_id
        l_last_name || ' ' || dbms_random.string('U', 1) || '. ' || dbms_random.string('U', 1) || '. ',  -- p_full_name
        '(093) ' || lpad(round(DBMS_RANDOM.VALUE(1, 999)), 3, '0') || ' ' ||
          lpad(round(DBMS_RANDOM.VALUE(1, 9999)), 4, '0'),  -- p_phone_number
        l_last_name || '_' || i ||  '@gmail.com'  -- p_email
      );
    end loop;
  
    --dm_employee.employee_select(null, p_out_cursor);   -- for debug
  end;
  
  ----
  
  procedure add_access_cards(
        p_count in number
        --,p_out_cursor out sys_refcursor   -- for debug
        ) 
  is
    l_cur_id  number;
  begin
    if p_count is null or p_count < 1 then
      raise_application_error(-20001, 'Ошибка в количестве создаваемых объектов.');
    end if;
    
    for i in 1..p_count loop
      l_cur_id := null;
      dm_access_card.access_card_insert(
        l_cur_id,  -- p_object_id
        lpad(i, 5, '0') || lpad(round(DBMS_RANDOM.VALUE(1, 99999)), 5, '0'),  -- p_inventory_num
        2 /*IN_STOCK*/   --round(DBMS_RANDOM.VALUE(1, 2))  -- p_inv_status_id
      );
    end loop;
  
    --dm_access_card.access_card_select(null, p_out_cursor);   -- for debug
  end;
  
  ----
  
  procedure add_notebooks(
        p_count in number
        --,p_out_cursor out sys_refcursor   -- for debug
        ) 
  is
    l_cur_id  number;
    
    type t_rec_data is record (note_name varchar2(10), model_prefix varchar2(10), model_suffix varchar2(10));
    type t_tab_data is table of t_rec_data index by pls_integer;
    l_tab_data t_tab_data;
    
    type t_rec_loc is record (location varchar2(20));
    type t_tab_loc is table of t_rec_loc index by pls_integer;
    l_tab_loc t_tab_loc;
    
    l_cur_rec_data t_rec_data;
    l_cur_rec_loc t_rec_loc;
    l_cur_rec_data_num  number;
  begin
    if p_count is null or p_count < 1 then
      raise_application_error(-20001, 'Ошибка в количестве создаваемых объектов.');
    end if;
    
    select * bulk collect into l_tab_data from (
           select 'ASUS' as note_name, 'AS' as model_prefix, 'XXL' as model_suffix from dual union all
           select 'ASUS' as note_name, 'AS' as model_prefix, 'EEE' as model_suffix from dual union all
           select 'ASUS' as note_name, 'AS' as model_prefix, 'Gold' as model_suffix from dual union all
           select 'ASUS' as note_name, 'AS' as model_prefix, 'Drisch' as model_suffix from dual union all
           select 'DELL' as note_name, 'DL' as model_prefix, 'PinkBaby' as model_suffix from dual union all
           select 'DELL' as note_name, 'DL' as model_prefix, 'Tractor' as model_suffix from dual union all
           select 'DELL' as note_name, 'DL' as model_prefix, 'Lattitude' as model_suffix from dual union all
           select 'LENOVO' as note_name, 'LE' as model_prefix, 'MoonNinja' as model_suffix from dual union all
           select 'LENOVO' as note_name, 'LE' as model_prefix, 'Knife' as model_suffix from dual union all
           select 'ACER' as note_name, 'AC' as model_prefix, 'Ferrary' as model_suffix from dual union all
           select 'ACER' as note_name, 'AC' as model_prefix, 'Cosmo' as model_suffix from dual union all
           select 'ACER' as note_name, 'AC' as model_prefix, 'Patriot' as model_suffix from dual 
    );
      
    select * bulk collect into l_tab_loc from (
           select 'Odessa' as location from dual union all
           select 'Kiev' as location from dual union all
           select 'Lviv' as location from dual
    );
  
    for i in 1..p_count loop
      l_cur_id := null;
      l_cur_rec_data_num := round(dbms_random.value(1, 12));
      l_cur_rec_data := l_tab_data(l_cur_rec_data_num);
      l_cur_rec_data_num := round(dbms_random.value(1, 3));
      l_cur_rec_loc := l_tab_loc(l_cur_rec_data_num);
      dm_notebook.notebook_insert(
        l_cur_id,  -- p_object_id
        l_cur_rec_data.note_name,  -- p_name
        l_cur_rec_loc.location,  -- p_location
        power(2, round(dbms_random.value(1, 5))),  -- p_memory_type
        l_cur_rec_data.model_suffix || ' ' || lpad(round(DBMS_RANDOM.VALUE(1, 999)), 3, '0'),  -- p_model
        rpad(i, 7, '0') || lpad(round(DBMS_RANDOM.VALUE(1, 99999)), 5, '0'),  -- p_inventory_num
        l_cur_rec_data.model_prefix || '-' || lpad(round(DBMS_RANDOM.VALUE(1, 999)), 3, '0') ||
          '-' || lpad(round(DBMS_RANDOM.VALUE(1, 9999999)), 7, '0'),  -- p_serial_number
        2 /*IN_STOCK*/   --round(DBMS_RANDOM.VALUE(1, 3))   -- p_inv_status_id
      );
    end loop;
  
    --dm_notebook.notebook_select(null, p_out_cursor);   -- for debug
  end;
  
  ----
  
  procedure add_card_orders(
        p_count     in number
        ,p_date_from in date
        --,p_out_cursor out sys_refcursor   -- for debug
        )
  is
    l_cur_ord_id  number;
    
    type t_rec_inventory_id is record (id number);
    type t_tab_inventory_id is table of t_rec_inventory_id index by pls_integer;
    l_tab_inventory_id t_tab_inventory_id;
    
    type t_rec_employee_id is record (id number);
    type t_tab_employee_id is table of t_rec_employee_id index by pls_integer;
    l_tab_employee_id t_tab_employee_id;
    
    type t_rec_user_id is record (id number);
    type t_tab_user_id is table of t_rec_user_id index by pls_integer;
    l_tab_user_id t_tab_user_id;
    
    l_date_from date := p_date_from;
    l_date_to date := sysdate + 30;
    l_cur_due_date date;
    l_date_coeff number;
    l_cur_order_status number;
    
    l_breaked_i  number;
    l_tab_inventory_cur_i  number;
    l_inventory_cur_id  number;
    l_inventory_status_in_use  number := 1;  -- in_use statuse id
    l_inventory_status_in_stock  number := 2;  -- IN_STOCK statuse id
  begin
    if p_count is null or p_count < 1 then
      raise_application_error(-20001, 'Ошибка в количестве создаваемых объектов.');
    end if;
  
    if l_date_from is null then
      l_date_from := sysdate - 300;
    end if;
    l_date_coeff := (l_date_to - l_date_from) / p_count;
    

    select o.object_id
      bulk collect into l_tab_inventory_id
      from objects o
      LEFT JOIN ATTRIBUTES ATTR_INV_STATUS ON ATTR_INV_STATUS.OBJECT_ID = o.OBJECT_ID AND ATTR_INV_STATUS.ATTR_ID = 16 /* STATUS */
     --where o.object_type_id in (4 /*INVENTORY*/, 6 /*CARD*/, 7 /*NOTEPAD*/);
     where o.object_type_id = 6 /*CARD*/
       and ATTR_INV_STATUS.VALUE = l_inventory_status_in_stock;    -- select only in stock inventary
    
    select o.object_id
      bulk collect into l_tab_employee_id
      from objects o
     where o.object_type_id = 1 /*EMPLOYEE*/;
     
    select o.object_id
      bulk collect into l_tab_user_id
      from objects o
     where o.object_type_id = 2 /*User*/;
    
    for i in 1..p_count loop
      l_cur_ord_id := null;
      l_cur_due_date := l_date_from + i * l_date_coeff;
      if l_cur_due_date < sysdate - 10 then
        l_cur_order_status := 8;  -- CLOSED
        dm_order.order_insert(
          l_cur_ord_id,
          l_tab_inventory_id(round(dbms_random.value(l_tab_inventory_id.first, l_tab_inventory_id.last))).id,  
          l_tab_employee_id(round(dbms_random.value(l_tab_employee_id.first, l_tab_employee_id.last))).id,  
          l_tab_user_id(round(dbms_random.value(l_tab_user_id.first, l_tab_user_id.last))).id,  
          l_cur_order_status,
          l_cur_due_date
        );        
      else
        l_breaked_i := i;
        exit;
      end if;
    end loop;
    
    l_tab_inventory_cur_i := 0;
    for i in l_breaked_i..p_count loop
      l_cur_ord_id := null;
      l_tab_inventory_cur_i :=l_tab_inventory_cur_i + 1;
      if l_tab_inventory_cur_i > l_tab_inventory_id.last then
        exit;
      end if;
      l_inventory_cur_id := l_tab_inventory_id(l_tab_inventory_cur_i).id;
      l_cur_due_date := l_date_from + i * l_date_coeff;
      
      if l_cur_due_date < sysdate then
        l_cur_order_status := 7;  -- Overdue
        
        dm_order.order_insert(
          l_cur_ord_id,
          l_inventory_cur_id,  
          l_tab_employee_id(round(dbms_random.value(l_tab_employee_id.first, l_tab_employee_id.last))).id,  
          l_tab_user_id(round(dbms_random.value(l_tab_user_id.first, l_tab_user_id.last))).id,  
          l_cur_order_status,
          l_cur_due_date
        ); 
        -- update oder creation date:
        update attributes set date_value = l_cur_due_date - 3
         where object_id = l_cur_ord_id and attr_id = 26;  /*CREATION_DATE*/
        
        -- set card status to  IN_USE
        update attributes set value = l_inventory_status_in_use
         where object_id = l_inventory_cur_id and attr_id = 16;  /*STATUS*/
      else
        l_breaked_i := i;
        exit;
      end if;
    end loop;
    
    for i in l_breaked_i..p_count loop
      l_cur_ord_id := null;
      l_tab_inventory_cur_i := l_tab_inventory_cur_i + 1;
      if l_tab_inventory_cur_i > l_tab_inventory_id.last then
        exit;
      end if;
      l_inventory_cur_id := l_tab_inventory_id(l_tab_inventory_cur_i).id;
      l_cur_due_date := l_date_from + i * l_date_coeff;

      l_cur_order_status := round(dbms_random.value(5, 6));  -- Issued \ Extended
            
      dm_order.order_insert(
          l_cur_ord_id,
          l_inventory_cur_id,  
          l_tab_employee_id(round(dbms_random.value(l_tab_employee_id.first, l_tab_employee_id.last))).id,  
          l_tab_user_id(round(dbms_random.value(l_tab_user_id.first, l_tab_user_id.last))).id,  
          l_cur_order_status,
          l_cur_due_date
        ); 
      -- update oder creation date:
      update attributes set date_value = sysdate - trunc(dbms_random.value(0, 4))
       where object_id = l_cur_ord_id and attr_id = 26;  /*CREATION_DATE*/
      
      -- set card status to  IN_USE
      update attributes set value = l_inventory_status_in_use
       where object_id = l_inventory_cur_id and attr_id = 16;  /*STATUS*/
    end loop;
  
    --dm_order.order_select(null, p_out_cursor);   -- for debug
  
  end;
  
  ----
  
  procedure add_notebook_orders(
        p_count      in number
        ,p_date_from in date
        --,p_out_cursor out sys_refcursor   -- for debug
        )
  is
    l_cur_ord_id  number;
    
    type t_rec_inventory_id is record (id number);
    type t_tab_inventory_id is table of t_rec_inventory_id index by pls_integer;
    l_tab_inventory_id t_tab_inventory_id;
    
    type t_rec_employee_id is record (id number);
    type t_tab_employee_id is table of t_rec_employee_id index by pls_integer;
    l_tab_employee_id t_tab_employee_id;
    
    type t_rec_user_id is record (id number);
    type t_tab_user_id is table of t_rec_user_id index by pls_integer;
    l_tab_user_id t_tab_user_id;
    
    l_date_from date := p_date_from;
    l_date_to date := sysdate + 30;
    l_cur_due_date date;
    l_date_coeff number;
    l_cur_order_status number;
    
    l_breaked_i  number;
    l_tab_inventory_cur_i  number;
    l_inventory_cur_id  number;
    l_inventory_status_in_use  number := 1;  -- in_use statuse id
    l_inventory_status_in_stock  number := 2;  -- IN_STOCK statuse id
  begin
    if p_count is null or p_count < 1 then
      raise_application_error(-20001, 'Ошибка в количестве создаваемых объектов.');
    end if;
  
    if l_date_from is null then
      l_date_from := sysdate - 300;
    end if;
    l_date_coeff := (l_date_to - l_date_from) / p_count;
    

    select o.object_id
      bulk collect into l_tab_inventory_id
      from objects o
      LEFT JOIN ATTRIBUTES ATTR_INV_STATUS ON ATTR_INV_STATUS.OBJECT_ID = o.OBJECT_ID AND ATTR_INV_STATUS.ATTR_ID = 16 /* STATUS */
     where o.object_type_id = 7 /*NOTEPAD*/
       and ATTR_INV_STATUS.VALUE = l_inventory_status_in_stock;    -- select only in stock inventary
    
    select o.object_id
      bulk collect into l_tab_employee_id
      from objects o
     where o.object_type_id = 1 /*EMPLOYEE*/;
     
    select o.object_id
      bulk collect into l_tab_user_id
      from objects o
     where o.object_type_id = 2 /*User*/;
    
    for i in 1..p_count loop
      l_cur_ord_id := null;
      l_cur_due_date := l_date_from + i * l_date_coeff;
      if l_cur_due_date < sysdate - 10 then
        l_cur_order_status := 8;  -- CLOSED
        dm_order.order_insert(
          l_cur_ord_id,
          l_tab_inventory_id(round(dbms_random.value(l_tab_inventory_id.first, l_tab_inventory_id.last))).id,  
          l_tab_employee_id(round(dbms_random.value(l_tab_employee_id.first, l_tab_employee_id.last))).id,  
          l_tab_user_id(round(dbms_random.value(l_tab_user_id.first, l_tab_user_id.last))).id,  
          l_cur_order_status,
          l_cur_due_date
        );        
      else
        l_breaked_i := i;
        exit;
      end if;
    end loop;
    
    l_tab_inventory_cur_i := 0;
    for i in l_breaked_i..p_count loop
      l_cur_ord_id := null;
      l_tab_inventory_cur_i :=l_tab_inventory_cur_i + 1;
      if l_tab_inventory_cur_i > l_tab_inventory_id.last then
        exit;
      end if;
      l_inventory_cur_id := l_tab_inventory_id(l_tab_inventory_cur_i).id;
      l_cur_due_date := l_date_from + i * l_date_coeff;
      
      if l_cur_due_date < sysdate then
        l_cur_order_status := 7;  -- Overdue
        
        dm_order.order_insert(
          l_cur_ord_id,
          l_inventory_cur_id,  
          l_tab_employee_id(round(dbms_random.value(l_tab_employee_id.first, l_tab_employee_id.last))).id,  
          l_tab_user_id(round(dbms_random.value(l_tab_user_id.first, l_tab_user_id.last))).id,  
          l_cur_order_status,
          l_cur_due_date
        ); 
        -- update oder creation date:
        update attributes set date_value = l_cur_due_date - 30
         where object_id = l_cur_ord_id and attr_id = 26;  /*CREATION_DATE*/
        
        -- set card status to  IN_USE
        update attributes set value = l_inventory_status_in_use
         where object_id = l_inventory_cur_id and attr_id = 16;  /*STATUS*/
      else
        l_breaked_i := i;
        exit;
      end if;
    end loop;
    
    for i in l_breaked_i..p_count loop
      l_cur_ord_id := null;
      l_tab_inventory_cur_i := l_tab_inventory_cur_i + 1;
      if l_tab_inventory_cur_i > l_tab_inventory_id.last then
        exit;
      end if;
      l_inventory_cur_id := l_tab_inventory_id(l_tab_inventory_cur_i).id;
      l_cur_due_date := l_date_from + i * l_date_coeff;

      l_cur_order_status := round(dbms_random.value(5, 6));  -- Issued \ Extended
            
      dm_order.order_insert(
          l_cur_ord_id,
          l_inventory_cur_id,  
          l_tab_employee_id(round(dbms_random.value(l_tab_employee_id.first, l_tab_employee_id.last))).id,  
          l_tab_user_id(round(dbms_random.value(l_tab_user_id.first, l_tab_user_id.last))).id,  
          l_cur_order_status,
          l_cur_due_date
        ); 
      -- update oder creation date:
      update attributes set date_value = sysdate - trunc(dbms_random.value(0, 30))
       where object_id = l_cur_ord_id and attr_id = 26;  /*CREATION_DATE*/
      
      -- set card status to  IN_USE
      update attributes set value = l_inventory_status_in_use
       where object_id = l_inventory_cur_id and attr_id = 16;  /*STATUS*/
    end loop;
  
    --dm_order.order_select(null, p_out_cursor);   -- for debug
  end;
  
  ----
  
  procedure add_objects(
        p_count_employees           in number default 100,
        p_count_access_cards        in number default 200,
        p_count_notebooks           in number default 50,
        p_count_orders_access_cards in number default 500,
        p_count_orders_notebooks    in number default 150,
        p_count_notifications       in number default 0
        ) 
  as
    l_count_employees number := p_count_employees;  
    l_count_access_cards number := p_count_access_cards;
    l_count_notebooks number := p_count_notebooks;
    l_count_orders_access_cards number := p_count_orders_access_cards;
    l_count_orders_notebooks number := p_count_orders_notebooks;
  begin
    if l_count_employees is null then
      l_count_employees := 100;
    end if;
    if l_count_access_cards is null then
      l_count_access_cards := 200;
    end if;
    if l_count_notebooks is null then
      l_count_notebooks := 50;
    end if;
    if p_count_orders_access_cards is null then
      l_count_orders_access_cards := 500;
    end if;
    if p_count_orders_notebooks is null then
      l_count_orders_notebooks := 150;
    end if;
  
    add_employees(l_count_employees);
    add_access_cards(l_count_access_cards);
    add_notebooks(l_count_notebooks);
    add_card_orders(l_count_orders_access_cards, null);
    add_notebook_orders(l_count_orders_notebooks, null);
    add_notifications(p_count_notifications);
  end;
  
  ----
  
  procedure add_notifications(
        p_count     in number
        --,p_out_cursor out sys_refcursor   -- for debug
        )
  is
    l_cur_id  number;
    
    type t_rec_order is record (ord_id number, d date, notif_id number);
    type t_tab_order is table of t_rec_order index by pls_integer;
    l_tab_order t_tab_order;
    
    l_date_1  date;
    l_date_2  date;
    l_date_3  date;
    l_count_notif number;
    l_count  number := p_count;
  begin
    select ORD.OBJECT_ID AS ID,
           ATTR_DATE.DATE_VALUE  AS D,
           oref.OBJECT_ID as notif_id
      bulk collect into l_tab_order
      FROM OBJECTS ORD
      JOIN ATTRIBUTES ATTR_DATE ON ORD.OBJECT_ID = ATTR_DATE.OBJECT_ID AND ATTR_DATE.ATTR_ID = 15 /* DATE */
      left join ObjReference oref on oref.reference = ord.object_id and oref.attr_id = 25
     WHERE ORD.OBJECT_TYPE_ID = 5 /* ORDER */
       and oref.OBJECT_ID is null;
       
     if l_count > l_tab_order.last then
       l_count := l_tab_order.last;
     elsif p_count is null or p_count = 0 then
       l_count := l_tab_order.last;
     end if;
  
    for i in 1..l_count loop
      
      l_date_1 := trunc(l_tab_order(i).d + 1);
      if sysdate - l_tab_order(i).d > 7 then
        l_count_notif := trunc(dbms_random.value(0, 4));  
      elsif sysdate - l_tab_order(i).d > -1 then
        l_count_notif := trunc(dbms_random.value(1, 3));  
      else 
        l_count_notif := 0;  
      end if;
      
      if l_count_notif = 0 then
        l_date_1 := null;
      end if;
      l_date_2 := null;
      l_date_3 := null;
        
      if l_count_notif > 1 then
        l_date_2 := trunc(l_tab_order(i).d + 4);
      end if;
      if l_count_notif > 2 then
        l_date_3 := trunc(l_tab_order(i).d + 7);
      end if;
    
      l_cur_id := null;
      dm_notification.notification_insert(l_cur_id, l_tab_order(i).ord_id, l_date_1, l_date_2, l_date_3);
    end loop;
    
    --dm_notification.notification_select(null, p_out_cursor);   -- for debug
  
  end;

begin
  -- Initialization
  null;
end dm_test_data;


/





