CREATE OR REPLACE package body dm_test_data is

  
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
       where object_id = l_tab_objects_id(i).id;
     
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
      raise_application_error(-20001, 'Ошибка в колчестве создаваемых объектов.');
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
      raise_application_error(-20001, 'Ошибка в колчестве создаваемых объектов.');
    end if;
    
    for i in 1..p_count loop
      l_cur_id := null;
      dm_access_card.access_card_insert(
        l_cur_id,  -- p_object_id
        lpad(i, 5, '0') || lpad(round(DBMS_RANDOM.VALUE(1, 99999)), 5, '0'),  -- p_inventory_num
        round(DBMS_RANDOM.VALUE(1, 2))  -- p_inv_status_id
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
      raise_application_error(-20001, 'Ошибка в колчестве создаваемых объектов.');
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
        round(DBMS_RANDOM.VALUE(1, 3))   -- p_inv_status_id
      );
    end loop;
  
    --dm_notebook.notebook_select(null, p_out_cursor);   -- for debug
  end;
  
  ----
  
  procedure add_orders(
        p_count     in number,
        p_date_from in date,
        p_date_to   in date
        --,p_out_cursor out sys_refcursor   -- for debug
        )
  is
    l_cur_id  number;
    
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
    l_date_to date := p_date_to;
    l_cur_date date;
    l_date_coeff number;
    l_cur_order_status number;
  begin
    if p_count is null or p_count < 1 then
      raise_application_error(-20001, 'Ошибка в колчестве создаваемых объектов.');
    end if;
  
    if l_date_from is null then
      l_date_from := sysdate - 300;
    end if;
    if l_date_to is null then
      l_date_to := sysdate + 30;
    end if;
    l_date_coeff := (l_date_to - l_date_from) / p_count;
    
    select o.object_id
      bulk collect into l_tab_inventory_id
      from objects o
     where o.object_type_id in (4 /*INVENTORY*/, 6 /*CARD*/, 7 /*NOTEPAD*/);
    
    select o.object_id
      bulk collect into l_tab_employee_id
      from objects o
     where o.object_type_id = 1 /*EMPLOYEE*/;
     
    select o.object_id
      bulk collect into l_tab_user_id
      from objects o
     where o.object_type_id = 2 /*User*/;
    
    for i in 1..p_count loop
      l_cur_id := null;
      l_cur_date := l_date_from + i * l_date_coeff;
      if l_cur_date < sysdate - 10 then
        l_cur_order_status := 8;  -- CLOSED
      elsif l_cur_date > sysdate then
        l_cur_order_status := round(dbms_random.value(5, 6));  -- Открыт \ Продлен 
      else
        l_cur_order_status := round(dbms_random.value(6, 7));  -- Продлен \ Просрочен
      end if;
      dm_order.order_insert(
        l_cur_id,  -- p_object_id
        l_tab_inventory_id(round(dbms_random.value(l_tab_inventory_id.first, l_tab_inventory_id.last))).id,  -- p_inventory_id
        l_tab_employee_id(round(dbms_random.value(l_tab_employee_id.first, l_tab_employee_id.last))).id,  -- p_employee_id
        l_tab_user_id(round(dbms_random.value(l_tab_user_id.first, l_tab_user_id.last))).id,  -- p_user_id
        l_cur_order_status,  -- p_ord_status_id
        l_cur_date  -- p_date
      );
    end loop;
  
    --dm_order.order_select(null, p_out_cursor);   -- for debug
  
  end;
  
  ----

begin
  -- Initialization
  null;
end dm_test_data;


/
