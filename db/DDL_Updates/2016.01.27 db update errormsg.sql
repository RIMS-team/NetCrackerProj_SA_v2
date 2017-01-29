--2016.01.27 db update errormsg.sql



create or replace package dm_access_card is

  --===========================================
  
  procedure access_card_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_inv_status_id in number default null,   -- filter by status
        p_rownum_first  in number default null,
        p_rownum_last   in number default null
        );
   procedure access_card_select_ext(
        p_object_id     in number,
        p_out_cursor   out sys_refcursor,
        p_inv_status_id in number default null,   -- filter by status
        p_rownum_first  in number default null,   -- pagination
        p_rownum_last   in number default null    -- pagination
        );
   ------
   procedure access_card_insert(
        p_object_id     in out  number,
        p_inventory_num in      varchar2,
        p_inv_status_id in      varchar2);
  --
  procedure access_card_insert_ext(
        p_object_id     in out  number,
        p_inventory_num in      varchar2,
        p_inv_status_id in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2);
  ------
  procedure access_card_update(
        p_object_id     in number,
        p_inventory_num in varchar2,
        p_inv_status_id in varchar2);
  --
  procedure access_card_update_ext(
          p_object_id in number,
          p_inventory_num in varchar2,
          p_inv_status_id in varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2);
  ------
  procedure access_card_delete(p_object_id in number);
  --
  procedure access_card_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2);
  
  --===========================================

end dm_access_card;


/


create or replace package dm_employee is

  --==========================================
  
  procedure employee_select(
        p_object_id      in number,
        p_out_cursor     out sys_refcursor,
        p_full_name_mask in varchar2 default null,
        p_rownum_first   in number default null,
        p_rownum_last    in number default null);
  ----
  procedure employee_insert(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2);
  --
  procedure employee_insert_ext(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2);
  ----
  procedure employee_update(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2);
  --
  procedure employee_update_ext(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2,
        p_err_code  out number,
        p_err_msg   out varchar2);
  ----
  procedure employee_delete(p_object_id in number);
  --
  procedure employee_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2);
  
  --===========================================


end dm_employee;


/


create or replace package dm_inv_status is

  --======================================
  
  procedure inv_status_select(
        p_id          in number,
        p_out_cursor  out sys_refcursor);  
  ------
  procedure inv_status_insert(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2);
  --
  procedure inv_status_insert_ext(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2);
  ------
  procedure inv_status_update(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2);
  --
  procedure inv_status_update_ext(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2);
  ------
  procedure inv_status_delete(p_id in number);
  --
  procedure inv_status_delete_ext(
          p_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2);
  
  --==========================================

end dm_inv_status;


/


create or replace package dm_notebook is

  --===========================================
  
  procedure notebook_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_inv_status_id in number default null,    -- filter by status
        p_name_mask     in varchar2 default null,  -- filter by name
        p_location_mask in varchar2 default null,   -- filter by location
        p_rownum_first  in number default null,
        p_rownum_last   in number default null
        );
  procedure notebook_select_ext(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_inv_status_id in number default null,    -- filter by status
        p_name_mask     in varchar2 default null,  -- filter by name
        p_location_mask in varchar2 default null,  -- filter by location
        p_rownum_first  in number default null,    -- pagination 
        p_rownum_last   in number default null     -- pagination 
        );
  ----
  procedure notebook_insert(
        p_object_id     in out  number,
        p_name          in      varchar2,
        p_location      in      varchar2,
        p_memory_type   in      varchar2,
        p_model         in      varchar2,
        p_inventory_num in      varchar2,
        p_serial_number in      varchar2,
        p_inv_status_id in      varchar2);
  --
  procedure notebook_insert_ext(
        p_object_id     in out  number,
        p_name          in      varchar2,
        p_location      in      varchar2,
        p_memory_type   in      varchar2,
        p_model         in      varchar2,
        p_inventory_num in      varchar2,
        p_serial_number in      varchar2,
        p_inv_status_id in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2);
  ----
  procedure notebook_update(
        p_object_id     in number,
        p_name          in varchar2,
        p_location      in  varchar2,
        p_memory_type   in varchar2,
        p_model         in varchar2,
        p_inventory_num in varchar2,
        p_serial_number in varchar2,
        p_inv_status_id in varchar2);
  --
  procedure notebook_update_ext(
        p_object_id     in number,
        p_name          in varchar2,
        p_location      in  varchar2,
        p_memory_type   in varchar2,
        p_model         in varchar2,
        p_inventory_num in varchar2,
        p_serial_number in varchar2,
        p_inv_status_id in varchar2,
        p_err_code  out number,
        p_err_msg   out varchar2);
  -----
  procedure notebook_delete(p_object_id in number);
  --
  procedure notebook_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2);
  --===========================================

end dm_notebook;


/


create or replace package dm_notification is

  --===========================================
  
  procedure notification_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_rownum_first  in number default null,  -- pagination
        p_rownum_last   in number default null   -- pagination
        );
  ----
  procedure notification_insert(
        p_object_id     in out  number,
        p_order_id      in      number,
        p_first_date    in      date,
        p_second_date   in      date,
        p_third_date    in      date);
  --
  procedure notification_insert_ext(
        p_object_id     in out  number,
        p_order_id      in      number,
        p_first_date    in      date,
        p_second_date   in      date,
        p_third_date    in      date,
          p_err_code    out number,
          p_err_msg     out varchar2);
  ----
  procedure notification_update(
        p_object_id     in number,
        p_first_date    in date,
        p_second_date   in date,
        p_third_date    in date);
  --
  procedure notification_update_ext(
        p_object_id     in number,
        p_first_date    in date,
        p_second_date   in date,
        p_third_date    in date,
          p_err_code  out number,
          p_err_msg   out varchar2);
  ----
  procedure notification_delete(p_object_id in number);
  --
  procedure notification_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2);
  
  
  --=======================================
  
  function get_notification_num(
        p_ord_date             in date,
        p_notif_1_shift_days   in number,
        p_notif_2_shift_days   in number,
        p_notif_3_shift_days   in number) return number;
  
  --return debtors info on the date of sysdate into cursor:
  --  ORDER_ID - type number
  --  ORD_DATE - type date  - deadline date
  --  INVENTORY_ID - type number
  --  INVENTORY_NAME - type varchar2
  --  INVENTORY_NUM - type varchar2 - universal financial inventory number
  --  INVENTORY_EXTRA_PARAM - type varchar2
  --  EMPLOYEE_ID - type number
  --  EMPLOYEE_FUll_NAME - type varchar2
  --  EMPLOYEE_EMAIL - type varchar2
  --  USER_ID - type number
  --  USER_FUll_NAME - type varchar2
  --  USER_EMAIL- type varchar2
  --  USER_PHONE - type varchar2
  --  NOTIFICATION_ID - type number
  --  NOTIFICATION_NUM - type number  - number of notification (1 or 2 or 3)
  --  NOTIFICATION_TEMPLATE - type varchar2
  
  procedure debtors_info_select(
        p_notif_1_shift_days    in number,
        p_notif_2_shift_days    in number,
        p_notif_3_shift_days    in number,
        p_out_cursor            out sys_refcursor );
        
  procedure recalc_orders_statuses;
  
  procedure register_notifications(
        p_orders_id_comma_delimited  varchar2,
        p_notif_1_shift_days  in  number,
        p_notif_2_shift_days  in  number,
        p_notif_3_shift_days  in  number);
        

        

end dm_notification;


/


create or replace package dm_notif_templ is

  function notif_templ_get(
        p_notif_num   in number default 1,
        p_user_id     in number) return varchar2;  --clob;
  ------
  procedure get_notif_templates_by_user(
        p_user_id         in number,
        p_notif_templ_1   out varchar2,
        p_notif_templ_2   out varchar2,
        p_notif_templ_3   out varchar2 );
  ------
  procedure notif_templ_set(
        p_notif_num   in number,
        p_user_id     in number,
        p_template    in varchar2  --clob
        --,p_out_cursor out sys_refcursor   -- for debug
        );
  --
  procedure notif_templ_set_ext(
        p_notif_num   in number,
        p_user_id     in number,
        p_template    in varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2);    
  ------
  procedure notif_templ_delete(
        p_notif_num   in number,
        p_user_id     in number);

end dm_notif_templ;


/


create or replace package dm_order is

  --===========================================
  
  procedure order_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_ord_status_id in number default null,   -- filter by status
        p_rownum_first  in number default null,
        p_rownum_last   in number default null
        );
  ------
  procedure order_insert(
        p_object_id      in out  number,
        p_inventory_id   in      number,
        p_employee_id    in      number,
        p_user_id        in      number,
        p_ord_status_id  in      number,
        p_date           in      date);
  --
  procedure order_insert_ext(
        p_object_id     in out  number,
        p_inventory_id  in      number,
        p_employee_id   in      number,
        p_user_id       in      number,
        p_ord_status_id in      number,
        p_date          in      date,
          p_err_code    out number,
          p_err_msg     out varchar2);
  ------
  procedure order_update(
        p_object_id      in number,
        p_inventory_id   in number,
        p_employee_id    in number,
        p_user_id        in number,
        p_ord_status_id  in number,
        p_date           in date);
  --
  procedure order_update_ext(
        p_object_id     in number,
        p_inventory_id  in number,
        p_employee_id   in number,
        p_user_id       in number,  --p_editor_user_id
        p_ord_status_id in number,
        p_date          in date,
        p_err_code  out number,
        p_err_msg   out varchar2);
  ------
  procedure order_delete(p_object_id in number);
  --
  procedure order_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2);
  
  --===========================================

end dm_order;


/


create or replace package dm_ord_status is

  --=====================================
  procedure ord_status_select(
        p_id          in number,
        p_out_cursor out sys_refcursor);
        
  procedure ord_status_insert(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2);
  --
  procedure ord_status_insert_ext(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2);
  ------
  procedure ord_status_update(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2);
  --
  procedure ord_status_update_ext(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2);
  ------      
  procedure ord_status_delete(p_id in number);
  --
  procedure ord_status_delete_ext(
          p_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2);
  --======================================

end dm_ord_status;


/


create or replace package dm_user is

  --===========================================
  
  procedure user_select(
        p_object_id   in number,
        p_out_cursor out sys_refcursor);
  ------
  procedure user_insert(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2,
        p_password      in      varchar2);
  --
  procedure user_insert_ext(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2,
        p_password      in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2);
  ------
  procedure user_update(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2,
        p_password      in varchar2);
  --
  procedure user_update_ext(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2,
        p_password      in varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2);
  ------
  procedure user_delete(p_object_id in number);
  --
  procedure user_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2);
  
  --===========================================

end dm_user;


/


create or replace package body dm_user is

--==========================================================
  
  procedure user_select(
        p_object_id   in number,
        p_out_cursor out sys_refcursor) as
  begin
    open p_out_cursor for
      SELECT EMP.OBJECT_ID AS EMPLOYEE_ID, 
             PHONE_ATTR.VALUE AS PHONE_NUMBER, 
             FNAME_ATTR.VALUE AS FULL_NAME, 
             EMAIL_ATTR.VALUE AS EMAIL,
             PASS_ATTR.VALUE AS PASSWORD
        FROM OBJECTS EMP, ATTRIBUTES FNAME_ATTR, ATTRIBUTES EMAIL_ATTR, ATTRIBUTES PHONE_ATTR, ATTRIBUTES PASS_ATTR
       WHERE EMP.OBJECT_TYPE_ID = 2 /* USER */
         AND EMP.OBJECT_ID = FNAME_ATTR.OBJECT_ID
         AND EMP.OBJECT_ID = EMAIL_ATTR.OBJECT_ID
         AND EMP.OBJECT_ID = PHONE_ATTR.OBJECT_ID
         AND EMP.OBJECT_ID = PASS_ATTR.OBJECT_ID
         AND FNAME_ATTR.ATTR_ID = 1 /* FULL_NAME */
         AND EMAIL_ATTR.ATTR_ID = 2 /* EMAIL */
         AND PHONE_ATTR.ATTR_ID = 3 /* PHONE_NUMBER */
         AND PASS_ATTR.ATTR_ID  = 4 /* PASSWORD */
         AND (p_object_id is null or EMP.OBJECT_ID = p_object_id);
  end;
  ----
  procedure user_insert(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2,
        p_password      in      varchar2) as
  begin
    if p_object_id is null then
      p_object_id := seq_objects.nextval;
    end if;
  
    INSERT ALL
      INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (p_object_id, NULL, 2, p_full_name, NULL)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (1, p_object_id, p_full_name, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (2, p_object_id, p_email, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (3, p_object_id, p_phone_number, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (4, p_object_id, p_password, null)
    SELECT * FROM dual;  
  end;
  ----
  procedure user_insert_ext(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2,
        p_password      in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2) as
  begin
    SAVEPOINT user_insert_ext;
    user_insert(p_object_id, p_full_name, p_phone_number, p_email, p_password);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO user_insert_ext;
      return;
  end;
  ----
  procedure user_update(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2,
        p_password      in varchar2) as
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
    end if;
    
    merge into attributes a
    USING(
      select p_object_id as object_id, 1 attr_id /* FULL_NAME */, p_full_name as value from dual union all
      select p_object_id as object_id, 2 attr_id /* EMAIL */, p_email as value from dual union all
      select p_object_id as object_id, 3 attr_id /* PHONE_NUMBER */, p_phone_number as value from dual union all
      select p_object_id as object_id, 4 attr_id /* PASSWORD */, p_password as value from dual
    ) t on (a.object_id = t.object_id and a.attr_id = t.attr_id)
    when matched then update set a.value = t.value;

  end;
  ----
  procedure user_update_ext(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2,
        p_password      in varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT user_update_ext;
    user_update(p_object_id, p_full_name, p_phone_number, p_email, p_password);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO user_update_ext;
      return;
  end;
  ----
  procedure user_delete(p_object_id in number) as
    l_OBJECT_TYPE_ID  number;
    l_is_referenced_by_others number := 0;
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для удаления.');
    end if;
    
    begin
      select o.object_type_id
        into l_OBJECT_TYPE_ID
        from objects o
       where o.object_id = p_object_id;
    exception
      when no_data_found then
        raise_application_error(-20001, 'Запись с ID=' || p_object_id || ' не найдена.');
    end; 
    if l_OBJECT_TYPE_ID <> 2 then
      raise_application_error(-20001, 'Запись не соответствувет типу USER.');
    end if;
        
    begin
      select 1
        into l_is_referenced_by_others
        from dual
       where exists ( select 1 from objreference o where o.reference = p_object_id );
    exception 
      when no_data_found then NULL;
    end;
    if l_is_referenced_by_others = 1 then
      raise_application_error(-20001, 'Удаление невозможно. На order ссылаются записи в других документах.');
    end if;
     

    delete from attributes
     where object_id = p_object_id;
     
    -- +++
    delete from OBJREFERENCE
     where object_id = p_object_id  --or reference = p_object_id
     ;
    delete from objects
     where object_id = p_object_id;
  end;
  ----
  procedure user_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT user_delete_ext;
    user_delete(p_object_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO user_delete_ext;
      return;
  end;
  
--==========================================================

begin
  -- Initialization
  null;
end dm_user;


/


create or replace package body dm_ord_status is

--=========================================

  procedure ord_status_select(
        p_id          in number,
        p_out_cursor out sys_refcursor) as
  begin
    open p_out_cursor for 
      select t.id, t.code, t.name
        from listtype t
       where t.attrtype_code = 'ORD_STATUS'
         and (p_id is null  or  p_id = t.id);
  end;
  ----
  procedure ord_status_insert(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2) as
  begin
    insert into listtype
      (id, attrtype_code, code, name, comments)
      values (p_id, 'ORD_STATUS', p_code, p_name, p_comments)
      returning id, attrtype_code
      into p_id, p_attrtype_code;
  end;
  ----
  procedure ord_status_insert_ext(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2) as
  begin
    SAVEPOINT ord_status_insert_ext;
    ord_status_insert(p_id, p_attrtype_code, p_code, p_name, p_comments);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO ord_status_insert_ext;
      return;
  end;
  ----
  procedure ord_status_update(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2) as
  begin
    if p_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
    end if;
    
    update listtype
      set code = p_code,
          name = p_name,
          comments = p_comments
      where id = p_id
        and attrtype_code = 'ORD_STATUS'
      returning code, name, comments
      into p_code, p_name, p_comments;
      
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Не найдена запись для редактирования.');
    end if;
  end;
  ----
  procedure ord_status_update_ext(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT ord_status_update_ext;
    ord_status_update(p_id, p_code, p_name, p_comments);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO ord_status_update_ext;
      return;
  end;
  ----
  procedure ord_status_delete(p_id in number) as
  begin
    delete from listtype
     where id = p_id
       and attrtype_code = 'ORD_STATUS';
     
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Не найдена запись для удаления.');
    end if;
  end;
  ----
  procedure ord_status_delete_ext(
          p_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT ord_status_delete_ext;
    ord_status_delete(p_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO ord_status_delete_ext;
      return;
  end;
  
--=========================================


begin
  -- Initialization
  null;
end dm_ord_status;


/


create or replace package body dm_order is

--==========================================================
  function is_new_ord_status_available(p_new_ord_status_id number, p_old_ord_status_id number) return boolean as
    l_res boolean :=false;
  begin
    if p_new_ord_status_id >= p_old_ord_status_id then
      l_res := true;
    elsif p_new_ord_status_id = 6 /*EXTENDED Продлен*/ and p_old_ord_status_id = 7 /*OVERDUE Просрочен*/ then 
      l_res := true;
    end if;
    return l_res;
  end;
  ----

  -- TODO
  -- add filters by - inventory_type_id, user_id, employee_id, date
  procedure order_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_ord_status_id in number default null,  -- filter by status
        p_rownum_first  in number default null,  -- pagination
        p_rownum_last   in number default null   -- pagination
        ) as
    l_rownum_first number := p_rownum_first;
  begin
    if l_rownum_first is null then
      l_rownum_first := 1;
    end if;
  
    open p_out_cursor for
      select * from (
        SELECT /*+ first_rows(30) */
              row_number() over (order by ORD.OBJECT_ID) rn
             ,ORD.OBJECT_ID AS ORDER_ID
             ,ATTR_DATE.DATE_VALUE
             ,ATTR_ORD_STATUS.VALUE  AS ORD_STATUS_ID
             ,LIST_ORD_STATUS.NAME   AS ORD_STATUS_NAME
             
             ,INVENTORY.OBJECT_ID AS INVENTORY_ID
             ,INVENTORY_TYPE.NAME AS INVENTORY_TYPE
             ,ATTR_INV_NUM.VALUE AS INVENTORY_NUM
             ,NOTE_ATTR_NAME.VALUE AS NOTE_NAME
             ,NOTE_ATTR_MODEL.VALUE AS NOTE_MODEL
             ,NOTE_ATTR_MEMORY_TYPE.VALUE AS NOTE_MEMORY_TYPE
             ,NOTE_ATTR_SERIAL_NUMBER.VALUE AS NOTE_SERIAL_NUMBER
             
             ,EMP_REF.REFERENCE AS EMPLOYEE_ID
             ,EMP_ATTR_FUll_NAME.VALUE AS EMPLOYEE_FUll_NAME
             ,EMP_ATTR_EMAIL.VALUE AS EMPLOYEE_EMAIL
             
             ,USR_REF.REFERENCE AS USER_ID
             ,USR_EDITOR_ATTR_FUll_NAME.VALUE AS USER_FUll_NAME
             --,USR_EDITOR_ATTR_EMAIL.VALUE AS USER_ATTR_EMAIL
             
             ,ATTR_CREATE_DATE.DATE_VALUE AS CREATE_DATE
             ,USR_EDITOR_REF.REFERENCE AS EDITOR_ID
             ,USR_EDITOR_ATTR_FUll_NAME.VALUE AS EDITOR_FUll_NAME
             
          FROM OBJECTS ORD
      LEFT     JOIN ATTRIBUTES ATTR_DATE ON ORD.OBJECT_ID = ATTR_DATE.OBJECT_ID AND ATTR_DATE.ATTR_ID = 15 /* DATE */
      LEFT     JOIN ATTRIBUTES ATTR_CREATE_DATE ON ORD.OBJECT_ID = ATTR_CREATE_DATE.OBJECT_ID AND ATTR_CREATE_DATE.ATTR_ID = 26 /* CREATION_DATE */
      LEFT     JOIN ATTRIBUTES ATTR_ORD_STATUS ON ORD.OBJECT_ID = ATTR_ORD_STATUS.OBJECT_ID AND ATTR_ORD_STATUS.ATTR_ID = 19 /* ORDER_STATUS */
      LEFT     JOIN LISTTYPE LIST_ORD_STATUS ON ATTR_ORD_STATUS.VALUE = LIST_ORD_STATUS.ID
             
      LEFT     JOIN OBJREFERENCE INVENTORY_REF ON INVENTORY_REF.OBJECT_ID = ORD.OBJECT_ID AND INVENTORY_REF.ATTR_ID = 8 /*INVENTORY*/
      LEFT     JOIN OBJECTS INVENTORY ON INVENTORY.OBJECT_ID = INVENTORY_REF.REFERENCE 
      LEFT     JOIN OBJTYPE INVENTORY_TYPE ON INVENTORY_TYPE.OBJECT_TYPE_ID = INVENTORY.OBJECT_TYPE_ID
      LEFT     JOIN ATTRIBUTES ATTR_INV_NUM ON ATTR_INV_NUM.OBJECT_ID = INVENTORY.OBJECT_ID AND ATTR_INV_NUM.ATTR_ID = 13 /*INVENTORY_NUM*/
        
           LEFT JOIN ATTRIBUTES NOTE_ATTR_NAME ON NOTE_ATTR_NAME.OBJECT_ID = INVENTORY.OBJECT_ID AND NOTE_ATTR_NAME.ATTR_ID = 9/*NAME*/
           LEFT JOIN ATTRIBUTES NOTE_ATTR_MODEL ON NOTE_ATTR_MODEL.OBJECT_ID = INVENTORY.OBJECT_ID AND NOTE_ATTR_MODEL.ATTR_ID = 12/*MODEL*/
           LEFT JOIN ATTRIBUTES NOTE_ATTR_MEMORY_TYPE ON NOTE_ATTR_MEMORY_TYPE.OBJECT_ID = INVENTORY.OBJECT_ID AND NOTE_ATTR_MEMORY_TYPE.ATTR_ID = 11/*MEMORY_TYPE*/
           LEFT JOIN ATTRIBUTES NOTE_ATTR_SERIAL_NUMBER ON NOTE_ATTR_SERIAL_NUMBER.OBJECT_ID = INVENTORY.OBJECT_ID AND NOTE_ATTR_SERIAL_NUMBER.ATTR_ID = 14/*SERIAL_NUMBER*/
        
      LEFT     JOIN OBJREFERENCE USR_REF ON USR_REF.OBJECT_ID = ORD.OBJECT_ID AND USR_REF.ATTR_ID = 6 /*USER_FROM*/
      LEFT     JOIN OBJECTS USR ON USR.OBJECT_ID = USR_REF.REFERENCE
          LEFT JOIN ATTRIBUTES USR_ATTR_FUll_NAME ON USR_ATTR_FUll_NAME.OBJECT_ID = USR.OBJECT_ID AND USR_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
          LEFT JOIN ATTRIBUTES USR_ATTR_EMAIL ON USR_ATTR_EMAIL.OBJECT_ID = USR.OBJECT_ID AND USR_ATTR_EMAIL.ATTR_ID = 2 /*EMAIL*/
          
      LEFT     JOIN OBJREFERENCE USR_EDITOR_REF ON USR_EDITOR_REF.OBJECT_ID = ORD.OBJECT_ID AND USR_EDITOR_REF.ATTR_ID = 27 /*EDITOR_USER*/
      LEFT     JOIN OBJECTS USR_EDITOR ON USR.OBJECT_ID = USR_EDITOR_REF.REFERENCE
          LEFT JOIN ATTRIBUTES USR_EDITOR_ATTR_FUll_NAME ON USR_EDITOR_ATTR_FUll_NAME.OBJECT_ID = USR.OBJECT_ID AND USR_EDITOR_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
        
      LEFT     JOIN OBJREFERENCE EMP_REF ON EMP_REF.OBJECT_ID = ORD.OBJECT_ID AND EMP_REF.ATTR_ID = 7 /*EMPLOYEE_TO*/
      LEFT     JOIN OBJECTS EMP ON EMP.OBJECT_ID = EMP_REF.REFERENCE
          LEFT JOIN ATTRIBUTES EMP_ATTR_FUll_NAME ON EMP_ATTR_FUll_NAME.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
          LEFT JOIN ATTRIBUTES EMP_ATTR_EMAIL ON EMP_ATTR_EMAIL.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_EMAIL.ATTR_ID = 2 /*EMAIL*/
         
         WHERE ORD.OBJECT_TYPE_ID = 5 /* ORDER */
           AND (p_object_id is null or ORD.OBJECT_ID = p_object_id)
           AND (p_ord_status_id is null or ATTR_ORD_STATUS.VALUE = p_ord_status_id)
      )
      where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last);
  end;
  ----
  procedure order_insert(
        p_object_id     in out  number,
        p_inventory_id  in      number,
        p_employee_id   in      number,
        p_user_id       in      number,
        p_ord_status_id in      number,
        p_date          in      date
        --,p_create_date    in      date
        --,p_editor_user_id in      number
        ) as
    l_date date := p_date;
    l_ord_status_id number := p_ord_status_id;
  begin
    if p_inventory_id is null then
      raise_application_error(-20001, 'Не задан ИД инвентаря для ордера.');
    end if;
    if p_employee_id is null then
      raise_application_error(-20001, 'Не задан ИД сотрудника для ордера.');
    end if;
    if p_user_id is null then
      raise_application_error(-20001, 'Не задан ИД пользователя для ордера.');
    end if;
    
    if p_object_id is null then
      p_object_id := seq_objects.nextval;
    end if;
    
    if l_date is null then
      l_date := sysdate;
    end if;
    
    if l_ord_status_id is null then
      l_ord_status_id := 5;  /*Открыт*/
    end if;
    
    INSERT ALL
      INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (p_object_id, NULL, 5, 'ORD-' || p_object_id, NULL)
      INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID) VALUES (8, p_inventory_id, p_object_id)
      INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID) VALUES (7, p_employee_id, p_object_id)
      INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID) VALUES (6, p_user_id, p_object_id)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (19, p_object_id, l_ord_status_id, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (15, p_object_id, null, l_date)
      
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (26, p_object_id, null, sysdate)  -- p_create_date
    SELECT * FROM dual;
  end;
  ----
  procedure order_insert_ext(
        p_object_id     in out  number,
        p_inventory_id  in      number,
        p_employee_id   in      number,
        p_user_id       in      number,
        p_ord_status_id in      number,
        p_date          in      date,
          p_err_code    out number,
          p_err_msg     out varchar2) as
  begin
    SAVEPOINT order_insert_ext;
    order_insert(p_object_id, p_inventory_id, p_employee_id, p_user_id, p_ord_status_id, p_date);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO order_insert_ext;
      return;
  end;
  ----
  procedure order_update(
        p_object_id     in number,
        p_inventory_id  in number,
        p_employee_id   in number,
        p_user_id       in number,  --p_editor_user_id
        p_ord_status_id in number,
        p_date          in date) as
        
    l_old_ord_status_id number;
    l_old_ord_status_name varchar2(50);
    l_new_ord_status_name varchar2(50);
    
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
    end if;
    
    if p_inventory_id is null then
      raise_application_error(-20001, 'Не задан ИД инвентаря для ордера.');
    end if;
    if p_employee_id is null then
      raise_application_error(-20001, 'Не задан ИД сотрудника для ордера.');
    end if;
    if p_user_id is null then
      raise_application_error(-20001, 'Не задан ИД пользователя для ордера.');
    end if;
    if p_ord_status_id is null then
      raise_application_error(-20001, 'Не задан статус для ордера.');
    end if;
    if p_date is null then
      raise_application_error(-20001, 'Не задана дата для ордера.');
    end if;
    
    --validation of p_ord_status_id new value 
    begin
      select a.value, l.name
        into l_old_ord_status_id, l_old_ord_status_name
        from objects o
        join attributes a on a.object_id = p_object_id and a.attr_id = 19 /* ORDER_STATUS */
        join listtype l on l.id = a.value
       where o.object_id = p_object_id;
    exception
      when no_data_found then
        raise_application_error(-20001, 'Ордер не найден.');
    end;
    
    if l_old_ord_status_id = 8 then  -- order closed => edition impossible
      raise_application_error(-20001, 'Editing of order is impossible');
    end if;
    
    if not is_new_ord_status_available(p_ord_status_id, l_old_ord_status_id) then
      select l.name
        into l_new_ord_status_name
        from listtype l
       where l.id = p_ord_status_id;
      raise_application_error(-20001, 'Нельзя зменить статус ордера с "' || l_old_ord_status_name || '" на "' || l_new_ord_status_name || '".');
    end if;
    
    merge into attributes a
    USING(
      select p_object_id as object_id, 15 attr_id /* DATE */, null as value, p_date as date_value from dual union all
      select p_object_id as object_id, 19 attr_id /* ORDER_STATUS */, p_ord_status_id as value, null as date_value from dual
    ) t on (a.object_id = t.object_id and a.attr_id = t.attr_id)
    when matched then update set a.value = t.value, a.date_value = t.date_value;
    
    merge into OBJREFERENCE a
    USING(
      select p_object_id as object_id, 27 as attr_id /* EDITOR_USER */, p_user_id as reference from dual union all
      select p_object_id as object_id, 7 as attr_id /* EMPLOYEE */, p_employee_id as reference from dual union all
      select p_object_id as object_id, 8 as attr_id /* INVENTORY */, p_inventory_id as reference from dual
    ) t on (a.object_id = t.object_id and a.attr_id = t.attr_id )
    when matched then update set a.reference = t.reference;
  end;
  ----
  procedure order_update_ext(
        p_object_id     in number,
        p_inventory_id  in number,
        p_employee_id   in number,
        p_user_id       in number,  --p_editor_user_id
        p_ord_status_id in number,
        p_date          in date,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT order_update_ext;
    order_update(p_object_id, p_inventory_id, p_employee_id, p_user_id, p_ord_status_id, p_date);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO order_update_ext;
      return;
  end;
  
  ----
  
  procedure order_delete(p_object_id in number) as
    l_OBJECT_TYPE_ID  number;
    l_is_referenced_by_others number := 0;
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для удаления.');
    end if;
    
    begin
      select o.object_type_id
        into l_OBJECT_TYPE_ID
        from objects o
       where o.object_id = p_object_id;
    exception
      when no_data_found then
        raise_application_error(-20001, 'Запись с ID=' || p_object_id || ' не найдена.');
    end; 
    if l_OBJECT_TYPE_ID <> 5 then  /* ORDER */
      raise_application_error(-20001, 'Запись не соответствувет типу ORDER.');
    end if;
      
    begin
      select 1
        into l_is_referenced_by_others
        from dual
       where exists ( select 1 from objreference o where o.reference = p_object_id );
    exception 
      when no_data_found then NULL;
    end;
    if l_is_referenced_by_others = 1 then
      raise_application_error(-20001, 'Удаление невозможно. На order ссылаются записи в других документах.');
    end if;
     

    delete from attributes
     where object_id = p_object_id;
     
    -- +++
    delete from OBJREFERENCE
     where object_id = p_object_id  --or reference = p_object_id
     ;
    delete from objects
     where object_id = p_object_id;
  end;
  ----
  
  procedure order_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT order_delete_ext;
    order_delete(p_object_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO order_delete_ext;
      return;
  end;
  ----
  


  
--==========================================================

begin
  -- Initialization
  null;
end dm_order;


/


create or replace package body dm_notif_templ is

  ----
  
  procedure notif_templ_select(
        p_out_cursor out sys_refcursor) as
  begin
    open p_out_cursor for 
      select nt.*
        from notif_templ nt
       ;
  end;

  ----
  
  function notif_templ_get(
        p_notif_num   in number default 1,
        p_user_id     in number) return varchar2  
  is
    l_template  varchar2(4000);
  begin
    begin
      select template
        into l_template
        from (
               select nt.template
                 from notif_templ nt
                 left join objects o on o.object_id = nt.user_id
                where nt.notif_num = p_notif_num
                  and (nt.user_id = p_user_id  or  nt.user_id is null)
                order by nt.user_id nulls last )
         where rownum = 1;
    exception
      when no_data_found then
        l_template := null;
    end;
    
    return l_template;
  end;
        
  ----
  
    
  procedure get_notif_templates_by_user(
        p_user_id         in number,
        p_notif_templ_1   out varchar2,
        p_notif_templ_2   out varchar2,
        p_notif_templ_3   out varchar2 ) as
  begin
    begin
      select 
             (select template
                 from (
                       select nt.template
                         from notif_templ nt
                         left join objects o on o.object_id = nt.user_id
                        where nt.notif_num = 1
                          and (nt.user_id = p_user_id  or  nt.user_id is null)
                        order by nt.user_id nulls last )
                 where rownum = 1
              ), 
              (select template
                 from (
                       select nt.template
                         from notif_templ nt
                         left join objects o on o.object_id = nt.user_id
                        where nt.notif_num = 2
                          and (nt.user_id = p_user_id  or  nt.user_id is null)
                        order by nt.user_id nulls last )
                 where rownum = 1
              ), 
              (select template
                 from (
                       select nt.template
                         from notif_templ nt
                         left join objects o on o.object_id = nt.user_id
                        where nt.notif_num = 3
                          and (nt.user_id = p_user_id  or  nt.user_id is null)
                        order by nt.user_id nulls last )
                 where rownum = 1
              )
        into p_notif_templ_1, p_notif_templ_2, p_notif_templ_3
        from dual;
    exception
      when no_data_found then
        p_notif_templ_1 := null;
        p_notif_templ_2 := null;
        p_notif_templ_3 := null;
    end;  
  end;
        
  ----
  
  procedure notif_templ_set(
        p_notif_num   in number,
        p_user_id     in number,
        p_template    in varchar2  --clob
        --,p_out_cursor out sys_refcursor   -- for debug
        )
  is
    l_obj_type_id number;
  begin
    if p_user_id is null then
      l_obj_type_id := 2;  /*USER*/
    else 
      begin
        select o.object_type_id
          into l_obj_type_id
          from objects o
         where o.object_id = p_user_id;
      exception 
        when no_data_found then
          l_obj_type_id := -1;
      end;
    end if;
    
    if l_obj_type_id <> 2 /*USER*/ then
      raise_application_error(-20001, 'Параметр p_user_id не ссылается на объект типа USER.');
    end if;
    
    merge into notif_templ nt using dual 
      on (nt.notif_num = p_notif_num and (p_user_id is null and user_id is null or nt.user_id = p_user_id))
      when not matched then insert (notif_num, user_id, template) values (p_notif_num, p_user_id, p_template)
      when matched then update set template = p_template;
      
    -- dm_notif_templ.notif_templ_select(p_out_cursor);   -- for debug
  end;
  ----
  procedure notif_templ_set_ext(
        p_notif_num   in number,
        p_user_id     in number,
        p_template    in varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT notif_templ_set_ext;
    notif_templ_set(p_notif_num, p_user_id, p_template);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO notif_templ_set_ext;
      return;
  end;
        
  ----
        
  procedure notif_templ_delete(
        p_notif_num   in number,
        p_user_id     in number)
  is
  begin
    delete from notif_templ
     where notif_num = p_notif_num
       and (p_user_id is null and user_id is null or user_id = p_user_id);
  end;
  
  ----

begin
  -- Initialization
  null;
end dm_notif_templ;


/


create or replace package body dm_notification is

  --===========================================
  
  procedure notification_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_rownum_first  in number default null,  -- pagination
        p_rownum_last   in number default null   -- pagination
        )
  is
    l_rownum_first number := p_rownum_first;
  begin
    if l_rownum_first is null then
      l_rownum_first := 1;
    end if;
    
    open p_out_cursor for
      select * from (
        SELECT /*+ first_rows(30) */
              row_number() over (order by NOTIF.OBJECT_ID) rn
             ,NOTIF.OBJECT_ID AS NOTIFICATION_ID
             ,ORD_REF.REFERENCE AS ORDER_ID
             ,ATTR_DATE_1.DATE_VALUE AS FIRST_DATE
             ,ATTR_DATE_2.DATE_VALUE AS SECOND_DATE
             ,ATTR_DATE_3.DATE_VALUE AS THIRD_DATE
             
             
             ,ATTR_DATE.DATE_VALUE   DUE_DATE
             ,ATTR_ORD_STATUS.VALUE  AS ORD_STATUS_ID
             ,LIST_ORD_STATUS.NAME   AS ORD_STATUS_NAME
             
             ,INVENTORY.OBJECT_ID AS INVENTORY_ID
             ,INVENTORY_TYPE.NAME AS INVENTORY_TYPE
             ,ATTR_INV_NUM.VALUE AS INVENTORY_NUM
             --,NOTE_ATTR_NAME.VALUE AS NOTE_NAME
             --,NOTE_ATTR_MODEL.VALUE AS NOTE_MODEL
             --,NOTE_ATTR_MEMORY_TYPE.VALUE AS NOTE_MEMORY_TYPE
             --,NOTE_ATTR_SERIAL_NUMBER.VALUE AS NOTE_SERIAL_NUMBER
             
             ,EMP_REF.REFERENCE AS EMPLOYEE_ID
             ,EMP_ATTR_FUll_NAME.VALUE AS EMPLOYEE_FUll_NAME
             ,EMP_ATTR_EMAIL.VALUE AS EMPLOYEE_EMAIL
             
          FROM OBJECTS NOTIF
          JOIN OBJREFERENCE ORD_REF ON ORD_REF.OBJECT_ID = NOTIF.OBJECT_ID AND ORD_REF.ATTR_ID = 25 /*ORDER*/
          
          LEFT JOIN ATTRIBUTES ATTR_DATE_1 ON NOTIF.OBJECT_ID = ATTR_DATE_1.OBJECT_ID AND ATTR_DATE_1.ATTR_ID = 20 /* FIRST_DATE */
          LEFT JOIN ATTRIBUTES ATTR_DATE_2 ON NOTIF.OBJECT_ID = ATTR_DATE_2.OBJECT_ID AND ATTR_DATE_2.ATTR_ID = 21 /* SECOND_DATE */
          LEFT JOIN ATTRIBUTES ATTR_DATE_3 ON NOTIF.OBJECT_ID = ATTR_DATE_3.OBJECT_ID AND ATTR_DATE_3.ATTR_ID = 22 /* THIRD_DATE */
          
          LEFT JOIN OBJECTS ORD ON ORD.OBJECT_ID = ORD_REF.REFERENCE
          --LEFT JOIN ATTRTYPE ORD_INVENT ON ORD_INVENT.ATTR_ID = 8 /*INVENTORY*/
          
          LEFT JOIN ATTRIBUTES ATTR_DATE ON ORD.OBJECT_ID = ATTR_DATE.OBJECT_ID AND ATTR_DATE.ATTR_ID = 15 /* DATE */
          LEFT JOIN ATTRIBUTES ATTR_ORD_STATUS ON ORD.OBJECT_ID = ATTR_ORD_STATUS.OBJECT_ID AND ATTR_ORD_STATUS.ATTR_ID = 19 /* ORDER_STATUS */
          LEFT JOIN LISTTYPE LIST_ORD_STATUS ON ATTR_ORD_STATUS.VALUE = LIST_ORD_STATUS.ID
          
          LEFT JOIN OBJREFERENCE INVENTORY_REF ON INVENTORY_REF.OBJECT_ID = ORD.OBJECT_ID AND INVENTORY_REF.ATTR_ID = 8 /*INVENTORY*/
          LEFT JOIN OBJECTS INVENTORY ON INVENTORY.OBJECT_ID = INVENTORY_REF.REFERENCE 
          LEFT JOIN OBJTYPE INVENTORY_TYPE ON INVENTORY_TYPE.OBJECT_TYPE_ID = INVENTORY.OBJECT_TYPE_ID
          LEFT JOIN ATTRIBUTES ATTR_INV_NUM ON ATTR_INV_NUM.OBJECT_ID = INVENTORY.OBJECT_ID AND ATTR_INV_NUM.ATTR_ID = 13 /*INVENTORY_NUM*/
          
          LEFT JOIN OBJREFERENCE EMP_REF ON EMP_REF.OBJECT_ID = ORD.OBJECT_ID AND EMP_REF.ATTR_ID = 7 /*EMPLOYEE_TO*/
          LEFT JOIN OBJECTS EMP ON EMP.OBJECT_ID = EMP_REF.REFERENCE
          LEFT JOIN ATTRIBUTES EMP_ATTR_FUll_NAME ON EMP_ATTR_FUll_NAME.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
          LEFT JOIN ATTRIBUTES EMP_ATTR_EMAIL ON EMP_ATTR_EMAIL.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_EMAIL.ATTR_ID = 2 /*EMAIL*/
          
          
         WHERE NOTIF.OBJECT_TYPE_ID = 8 /* NOTIFICATION */
           AND (p_object_id is null or NOTIF.OBJECT_ID = p_object_id)
      )
      where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last);        
  
  end;
  
  -------------
  
  procedure notification_insert(
        p_object_id     in out  number,
        p_order_id      in      number,
        p_first_date    in      date,
        p_second_date   in      date,
        p_third_date    in      date)
  is
  begin
    if p_object_id is null then
      p_object_id := seq_objects.nextval;
    end if;
    
    INSERT ALL
      INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (p_object_id, NULL, 8, 'NOTIF-' || p_object_id, NULL)
      INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID) VALUES (25, p_order_id, p_object_id)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (20, p_object_id, null, p_first_date)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (21, p_object_id, null, p_second_date)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (22, p_object_id, null, p_third_date)
    SELECT * FROM dual;
  end;
  ----
  procedure notification_insert_ext(
        p_object_id     in out  number,
        p_order_id      in      number,
        p_first_date    in      date,
        p_second_date   in      date,
        p_third_date    in      date,
          p_err_code    out number,
          p_err_msg     out varchar2) as
  begin
    SAVEPOINT notification_insert_ext;
    notification_insert(p_object_id, p_order_id, p_first_date, p_second_date, p_third_date);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO notification_insert_ext;
      return;
  end;
  
  ----
  
  procedure notification_update(
        p_object_id     in number,
        --p_order_id      in number,
        p_first_date    in date,
        p_second_date   in date,
        p_third_date    in date)
  is
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
    end if;
    
    merge into attributes a
    USING(
      select p_object_id as object_id, 20 attr_id /* FIRST_NOTIFICATION */, null as value, p_first_date as date_value from dual union all
      select p_object_id as object_id, 21 attr_id /* SECOND_NOTIFICATION */, null as value, p_second_date as date_value from dual union all
      select p_object_id as object_id, 22 attr_id /* THIRD_NOTIFICATION */, null as value, p_third_date as date_value from dual
    ) t on (a.object_id = t.object_id and a.attr_id = t.attr_id)
    when matched then update set a.value = t.value, a.date_value = t.date_value;
      
    -- ссылку на ордер редактировать нельзя

  end;
  ------
  procedure notification_update_ext(
        p_object_id     in number,
        p_first_date    in date,
        p_second_date   in date,
        p_third_date    in date,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT notification_update_ext;
    notification_update(p_object_id, p_first_date, p_second_date, p_third_date);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO notification_update_ext;
      return;
  end;
  
  ------
   
  procedure notification_delete(p_object_id in number)
  is
    l_OBJECT_TYPE_ID  number;
    l_is_referenced_by_others number := 0;
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для удаления.');
    end if;
    
    begin
      select o.object_type_id
        into l_OBJECT_TYPE_ID
        from objects o
       where o.object_id = p_object_id;
    exception
      when no_data_found then
        raise_application_error(-20001, 'Запись с ID=' || p_object_id || ' не найдена.');
    end; 
    if l_OBJECT_TYPE_ID <> 8 then  /* NOTIFICATION */
      raise_application_error(-20001, 'Запись не соответствувет типу NOTIFICATION.');
    end if;
      
    begin
      select 1
        into l_is_referenced_by_others
        from dual
       where exists ( select 1 from objreference o where o.reference = p_object_id );
    exception 
      when no_data_found then NULL;
    end;
    if l_is_referenced_by_others = 1 then
      raise_application_error(-20001, 'Удаление невозможно. На order ссылаются записи в других документах.');
    end if;
     

    delete from attributes
     where object_id = p_object_id;
     
    -- +++
    delete from OBJREFERENCE
     where object_id = p_object_id  --or reference = p_object_id
     ;
    delete from objects
     where object_id = p_object_id;
  end;
  ----
  procedure notification_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT notification_delete_ext;
    notification_delete(p_object_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO notification_delete_ext;
      return;
  end;
  
  --=======================================
  
  function get_notification_num(
        p_ord_date             in date,
        p_notif_1_shift_days   in number,
        p_notif_2_shift_days   in number,
        p_notif_3_shift_days   in number) return number
  is
    l_trunc_sysdate date := trunc(sysdate);
  begin
    if l_trunc_sysdate between (p_ord_date + p_notif_1_shift_days) and (p_ord_date + p_notif_2_shift_days)  then 
      return 1;
    elsif l_trunc_sysdate between (p_ord_date + p_notif_2_shift_days) and (p_ord_date + p_notif_3_shift_days)  then 
      return 2;
    elsif l_trunc_sysdate >= (p_ord_date + p_notif_3_shift_days)  then
      return 3;
    else
      return null;
    end if;
  end;
  
  procedure adjust_shift_days(
        p_notif_1_shift_days   in out number,
        p_notif_2_shift_days   in out number,
        p_notif_3_shift_days   in out number) is
  begin
    if p_notif_1_shift_days is null then
      p_notif_1_shift_days := 0;
    end if;
    
    if p_notif_2_shift_days is null then
      p_notif_2_shift_days := 3;
    end if;
    if p_notif_2_shift_days < p_notif_1_shift_days then
      p_notif_2_shift_days := p_notif_1_shift_days + 1;
    end if;
    
    if p_notif_3_shift_days is null then
      p_notif_3_shift_days := 7;
    end if;
    if p_notif_3_shift_days < p_notif_2_shift_days then
      p_notif_3_shift_days := p_notif_2_shift_days + 1;
    end if;    
  end;
  
  --return debtors info on the date of sysdate into cursor:
  --  ORDER_ID - type number
  --  ORD_DATE - type date  - deadline date
  --  INVENTORY_ID - type number
  --  INVENTORY_NAME - type varchar2
  --  INVENTORY_NUM - type varchar2 - universal financial inventory number
  --  INVENTORY_EXTRA_PARAM - type varchar2
  --  EMPLOYEE_ID - type number
  --  EMPLOYEE_FUll_NAME - type varchar2
  --  EMPLOYEE_EMAIL - type varchar2
  --  USER_ID - type number
  --  USER_FUll_NAME - type varchar2
  --  USER_EMAIL- type varchar2
  --  USER_PHONE - type varchar2
  --  NOTIFICATION_ID - type number
  --  NOTIFICATION_NUM - type number  - number of notification (1 or 2 or 3)
  --  NOTIFICATION_TEMPLATE - type varchar2
  procedure debtors_info_select(
        p_notif_1_shift_days  in  number,
        p_notif_2_shift_days  in  number,
        p_notif_3_shift_days  in  number,
        p_out_cursor    out sys_refcursor )
  is
    l_notif_1_shift_days number := p_notif_1_shift_days;
    l_notif_2_shift_days number := p_notif_2_shift_days;
    l_notif_3_shift_days number := p_notif_3_shift_days;
  begin
    adjust_shift_days(l_notif_1_shift_days, l_notif_2_shift_days, l_notif_3_shift_days);
  
    open p_out_cursor for
              select
                     a.ORDER_ID,
                     --a.ORD_STATUS_ID,
                     a.ORD_DATE,
                     a.ORD_STATUS_NAME,
                     
                     a.INVENTORY_ID,
                     case when a.INVENTORY_CODE = 'NOTEPAD' then a.INVENTORY_TYPE || ' ' || a.NOTE_NAME
                          else a.INVENTORY_TYPE
                     end INVENTORY_NAME,
                     --a.INVENTORY_TYPE,
                     a.INVENTORY_NUM,
                     case when a.INVENTORY_CODE = 'NOTEPAD' then 'модель ' || a.NOTE_MODEL || ', s/n ' || a.NOTE_SERIAL_NUMBER
                          else null
                     end INVENTORY_EXTRA_PARAM,
                     
                     a.EMPLOYEE_ID,
                     a.EMPLOYEE_FUll_NAME,
                     a.EMPLOYEE_EMAIL,
                     
                     a.USER_ID,
                     a.USER_FUll_NAME,
                     a.USER_EMAIL,
                     a.USER_PHONE,
                     
                     a.NOTIFICATION_ID,
                     a.NOTIFICATION_NUM,
                     case when a.NOTIFICATION_NUM = 1 then dm_notif_templ.notif_templ_get(1, a.USER_ID)
                          when a.NOTIFICATION_NUM = 2 then dm_notif_templ.notif_templ_get(2, a.USER_ID)
                          when a.NOTIFICATION_NUM = 3 then dm_notif_templ.notif_templ_get(3, a.USER_ID)
                     end NOTIFICATION_TEMPLATE
                from (
                       SELECT 
                              ORD.OBJECT_ID AS ORDER_ID
                              ,ATTR_ORD_DATE.DATE_VALUE  as ORD_DATE
                             --,ATTR_ORD_STATUS.VALUE  AS ORD_STATUS_ID
                              ,LIST_ORD_STATUS.NAME   AS ORD_STATUS_NAME
                     
                              ,INVENTORY.OBJECT_ID AS INVENTORY_ID
                              ,INVENTORY_TYPE.CODE AS INVENTORY_CODE
                              ,INVENTORY_TYPE.NAME AS INVENTORY_TYPE
                              ,ATTR_INV_NUM.VALUE AS INVENTORY_NUM
                              ,NOTE_ATTR_NAME.VALUE AS NOTE_NAME
                              ,NOTE_ATTR_MODEL.VALUE AS NOTE_MODEL
                             --,NOTE_ATTR_MEMORY_TYPE.VALUE AS NOTE_MEMORY_TYPE
                              ,NOTE_ATTR_SERIAL_NUMBER.VALUE AS NOTE_SERIAL_NUMBER
                     
                              ,EMP_REF.REFERENCE AS EMPLOYEE_ID
                              ,EMP_ATTR_FUll_NAME.VALUE AS EMPLOYEE_FUll_NAME
                              ,EMP_ATTR_EMAIL.VALUE AS EMPLOYEE_EMAIL
                     
                              ,USR_REF.REFERENCE AS USER_ID
                              ,USR_ATTR_FUll_NAME.VALUE AS USER_FUll_NAME
                              ,USR_ATTR_EMAIL.VALUE AS USER_EMAIL
                              ,USR_ATTR_PHONE.VALUE AS USER_PHONE
                     
                              ,NOTIF.OBJECT_ID AS NOTIFICATION_ID
                              --,ATTR_DATE_1.DATE_VALUE AS NOTIF_DATE_1
                              --,ATTR_DATE_2.DATE_VALUE AS NOTIF_DATE_2
                              --,ATTR_DATE_3.DATE_VALUE AS NOTIF_DATE_3
                               ,case when ATTR_DATE_1.DATE_VALUE is null 
                                         and dm_notification.get_notification_num(ATTR_ORD_DATE.DATE_VALUE, 
                                               l_notif_1_shift_days, l_notif_2_shift_days, l_notif_3_shift_days) = 1  then 1
                                    when ATTR_DATE_2.DATE_VALUE is null
                                         and dm_notification.get_notification_num(ATTR_ORD_DATE.DATE_VALUE, 
                                               l_notif_1_shift_days, l_notif_2_shift_days, l_notif_3_shift_days) = 2  then 2
                                    when ATTR_DATE_3.DATE_VALUE is null
                                         and dm_notification.get_notification_num(ATTR_ORD_DATE.DATE_VALUE, 
                                               l_notif_1_shift_days, l_notif_2_shift_days, l_notif_3_shift_days) = 3  then 3
                                    else null   -- all 3 notifications sent
                               end NOTIFICATION_NUM
                     
                          FROM OBJECTS ORD
                          LEFT JOIN ATTRIBUTES ATTR_ORD_DATE ON ORD.OBJECT_ID = ATTR_ORD_DATE.OBJECT_ID AND ATTR_ORD_DATE.ATTR_ID = 15 /* DATE */
                          LEFT JOIN ATTRIBUTES ATTR_ORD_STATUS ON ORD.OBJECT_ID = ATTR_ORD_STATUS.OBJECT_ID AND ATTR_ORD_STATUS.ATTR_ID = 19 /* ORDER_STATUS */
                          LEFT JOIN LISTTYPE LIST_ORD_STATUS ON ATTR_ORD_STATUS.VALUE = LIST_ORD_STATUS.ID
                     
                          LEFT JOIN OBJREFERENCE INVENTORY_REF ON INVENTORY_REF.OBJECT_ID = ORD.OBJECT_ID AND INVENTORY_REF.ATTR_ID = 8 /*INVENTORY*/
                          LEFT JOIN OBJECTS INVENTORY ON INVENTORY.OBJECT_ID = INVENTORY_REF.REFERENCE 
                          LEFT JOIN OBJTYPE INVENTORY_TYPE ON INVENTORY_TYPE.OBJECT_TYPE_ID = INVENTORY.OBJECT_TYPE_ID
                          LEFT JOIN ATTRIBUTES ATTR_INV_NUM ON ATTR_INV_NUM.OBJECT_ID = INVENTORY.OBJECT_ID AND ATTR_INV_NUM.ATTR_ID = 13 /*INVENTORY_NUM*/
                
                          LEFT JOIN ATTRIBUTES NOTE_ATTR_NAME ON NOTE_ATTR_NAME.OBJECT_ID = INVENTORY.OBJECT_ID AND NOTE_ATTR_NAME.ATTR_ID = 9/*NAME*/
                          LEFT JOIN ATTRIBUTES NOTE_ATTR_MODEL ON NOTE_ATTR_MODEL.OBJECT_ID = INVENTORY.OBJECT_ID AND NOTE_ATTR_MODEL.ATTR_ID = 12/*MODEL*/
                          --LEFT JOIN ATTRIBUTES NOTE_ATTR_MEMORY_TYPE ON NOTE_ATTR_MEMORY_TYPE.OBJECT_ID = INVENTORY.OBJECT_ID AND NOTE_ATTR_MEMORY_TYPE.ATTR_ID = 11/*MEMORY_TYPE*/
                          LEFT JOIN ATTRIBUTES NOTE_ATTR_SERIAL_NUMBER ON NOTE_ATTR_SERIAL_NUMBER.OBJECT_ID = INVENTORY.OBJECT_ID AND NOTE_ATTR_SERIAL_NUMBER.ATTR_ID = 14/*SERIAL_NUMBER*/
                
                          LEFT JOIN OBJREFERENCE USR_REF ON USR_REF.OBJECT_ID = ORD.OBJECT_ID AND USR_REF.ATTR_ID = 6 /*USER_FROM*/
                          LEFT  JOIN OBJECTS USR ON USR.OBJECT_ID = USR_REF.REFERENCE
                          LEFT JOIN ATTRIBUTES USR_ATTR_FUll_NAME ON USR_ATTR_FUll_NAME.OBJECT_ID = USR.OBJECT_ID AND USR_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
                          LEFT JOIN ATTRIBUTES USR_ATTR_EMAIL ON USR_ATTR_EMAIL.OBJECT_ID = USR.OBJECT_ID AND USR_ATTR_EMAIL.ATTR_ID = 2 /*EMAIL*/
                          LEFT JOIN ATTRIBUTES USR_ATTR_PHONE ON USR_ATTR_PHONE.OBJECT_ID = USR.OBJECT_ID AND USR_ATTR_PHONE.ATTR_ID = 3 /*PHONE_NUMBER*/
                
                          LEFT JOIN OBJREFERENCE EMP_REF ON EMP_REF.OBJECT_ID = ORD.OBJECT_ID AND EMP_REF.ATTR_ID = 7 /*EMPLOYEE_TO*/
                          LEFT JOIN OBJECTS EMP ON EMP.OBJECT_ID = EMP_REF.REFERENCE
                          LEFT JOIN ATTRIBUTES EMP_ATTR_FUll_NAME ON EMP_ATTR_FUll_NAME.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
                          LEFT JOIN ATTRIBUTES EMP_ATTR_EMAIL ON EMP_ATTR_EMAIL.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_EMAIL.ATTR_ID = 2 /*EMAIL*/
                 
                          LEFT JOIN OBJREFERENCE NOTIF_ORD_REF ON NOTIF_ORD_REF.REFERENCE = ord.OBJECT_ID AND NOTIF_ORD_REF.ATTR_ID = 25 /*ORDER*/
                          left join objects notif on notif.object_id = NOTIF_ORD_REF.OBJECT_ID
          
                          LEFT JOIN ATTRIBUTES ATTR_DATE_1 ON NOTIF.OBJECT_ID = ATTR_DATE_1.OBJECT_ID AND ATTR_DATE_1.ATTR_ID = 20 /* FIRST_DATE */
                          LEFT JOIN ATTRIBUTES ATTR_DATE_2 ON NOTIF.OBJECT_ID = ATTR_DATE_2.OBJECT_ID AND ATTR_DATE_2.ATTR_ID = 21 /* SECOND_DATE */
                          LEFT JOIN ATTRIBUTES ATTR_DATE_3 ON NOTIF.OBJECT_ID = ATTR_DATE_3.OBJECT_ID AND ATTR_DATE_3.ATTR_ID = 22 /* THIRD_DATE */
              
                         WHERE ORD.OBJECT_TYPE_ID = 5 /* ORDER */
                           AND (ATTR_ORD_STATUS.VALUE IN (5 /*ISSUED*/, 6 /*EXTENDED*/, 7 /*OVERDUE*/))
                           AND trunc(sysdate) >= ATTR_ORD_DATE.DATE_VALUE + l_notif_1_shift_days
                      ) A
               where a.NOTIFICATION_NUM is not null        
               ;
        
  end;
  
  ----
  
  procedure recalc_orders_statuses is
  begin
    update ATTRIBUTES ATTR_ORD_STATUS
       set ATTR_ORD_STATUS.VALUE = 7 /*OVERDUE*/
     where ATTR_ORD_STATUS.ATTR_ID = 19 /*ORDER_STATUS*/
       and exists (
           select 1
             from objects ord
             LEFT JOIN ATTRIBUTES ATTR_ORD_DATE ON ORD.OBJECT_ID = ATTR_ORD_DATE.OBJECT_ID AND ATTR_ORD_DATE.ATTR_ID = 15 /* DATE */
            where ord.object_id = ATTR_ORD_STATUS.OBJECT_ID
              and ord.object_type_id = 5 /*ORDER*/
              and trunc(sysdate) >= ATTR_ORD_DATE.DATE_VALUE
           )
       ;
  end;
  
  ----
  
  -- если у ордера нет нотификации, создать и записать в date1 sysdate.
  -- если у ордера есть нотификация, отредактировать ее даты
  procedure notification_sent(
        p_order_id  in number,
        p_notif_1_shift_days  in  number,
        p_notif_2_shift_days  in  number,
        p_notif_3_shift_days  in  number)
  is
    l_notif_1_shift_days number := p_notif_1_shift_days;
    l_notif_2_shift_days number := p_notif_2_shift_days;
    l_notif_3_shift_days number := p_notif_3_shift_days;
    
    l_ord_date date;
    l_notif_id number;
    l_notif_date1 date;
    l_notif_date2 date;
    l_notif_date3 date;
    l_notification_num number;
  begin
    if p_order_id is null then
      return;
    end if;
    
    begin
      select ATTR_ORD_DATE.DATE_VALUE,
             NOTIF.OBJECT_ID AS NOTIFICATION_ID,
             ATTR_DATE_1.DATE_VALUE AS NOTIF_DATE_1,
             ATTR_DATE_2.DATE_VALUE AS NOTIF_DATE_2,
             ATTR_DATE_3.DATE_VALUE AS NOTIF_DATE_3
        into l_ord_date, l_notif_id, l_notif_date1, l_notif_date2, l_notif_date3
        from objects ord
        LEFT JOIN ATTRIBUTES ATTR_ORD_DATE ON ORD.OBJECT_ID = ATTR_ORD_DATE.OBJECT_ID AND ATTR_ORD_DATE.ATTR_ID = 15 /* DATE */
         
        LEFT JOIN OBJREFERENCE NOTIF_ORD_REF ON NOTIF_ORD_REF.REFERENCE = ord.OBJECT_ID AND NOTIF_ORD_REF.ATTR_ID = 25 /*ORDER*/
        left join objects notif on notif.object_id = NOTIF_ORD_REF.OBJECT_ID
          
        LEFT JOIN ATTRIBUTES ATTR_DATE_1 ON NOTIF.OBJECT_ID = ATTR_DATE_1.OBJECT_ID AND ATTR_DATE_1.ATTR_ID = 20 /* FIRST_DATE */
        LEFT JOIN ATTRIBUTES ATTR_DATE_2 ON NOTIF.OBJECT_ID = ATTR_DATE_2.OBJECT_ID AND ATTR_DATE_2.ATTR_ID = 21 /* SECOND_DATE */
        LEFT JOIN ATTRIBUTES ATTR_DATE_3 ON NOTIF.OBJECT_ID = ATTR_DATE_3.OBJECT_ID AND ATTR_DATE_3.ATTR_ID = 22 /* THIRD_DATE */
       where ord.object_id = p_order_id;
    exception
      when no_data_found then
        raise_application_error(-20001, 'Order (id=' || p_order_id || ' not found');
    end;
    
    adjust_shift_days(l_notif_1_shift_days, l_notif_2_shift_days, l_notif_3_shift_days);
    
    l_notification_num := dm_notification.get_notification_num(l_ord_date, 
          l_notif_1_shift_days, l_notif_2_shift_days, l_notif_3_shift_days);
          
    if l_notification_num = 1 then
      l_notif_date1 := sysdate;
    elsif l_notification_num = 2 then
      l_notif_date2 := sysdate;
    elsif l_notification_num = 3 then
      l_notif_date3 := sysdate;
    end if;          
    
    if l_notif_id is null then
      notification_insert(
        l_notif_id,
        p_order_id,
        l_notif_date1,
        l_notif_date2,
        l_notif_date3);
    else
      notification_update(
        l_notif_id,
        --p_order_id,
        l_notif_date1,
        l_notif_date2,
        l_notif_date3);
    end if;
        
  end;
  
  ----
  
  procedure register_notifications(
        p_orders_id_comma_delimited  in varchar2,
        p_notif_1_shift_days  in  number,
        p_notif_2_shift_days  in  number,
        p_notif_3_shift_days  in  number
        )
  is
    type r_ord_id is record (id number);
    type t_tab_ord_id is table of r_ord_id index by pls_integer;
    l_tab_ord_id  t_tab_ord_id;
  begin
    select trim(regexp_substr(p_orders_id_comma_delimited, '[^,]+', 1, level)) 
      bulk collect into l_tab_ord_id
      from dual
      connect by regexp_substr(p_orders_id_comma_delimited, '[^,]+', 1, level) is not null;
  
    for i in l_tab_ord_id.first..l_tab_ord_id.last loop
      begin
        dm_notification.notification_sent(l_tab_ord_id(i).id, p_notif_1_shift_days, p_notif_2_shift_days, p_notif_3_shift_days);
      exception
        when others then null;
      end;
    end loop;
  end;
  
  ----
  

begin
  -- Initialization
  null;
end dm_notification;


/


create or replace package body dm_notebook is

--==========================================================

  procedure notebook_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_inv_status_id in number default null,    -- filter by status
        p_name_mask     in varchar2 default null,  -- filter by name
        p_location_mask in varchar2 default null,  -- filter by location
        p_rownum_first  in number default null,    -- pagination 
        p_rownum_last   in number default null     -- pagination 
        ) as
    l_rownum_first number := p_rownum_first;
  begin
    if l_rownum_first is null then
      l_rownum_first := 1;
    end if;
  
    open p_out_cursor for
      select * from (
        SELECT /*+ first_rows(30) */
               row_number() over (order by NOTE.OBJECT_ID) rn,
               NOTE.OBJECT_ID NOTE_ID,
               NAME_ATTR.VALUE NAME,
               LOC_ATTR.VALUE LOCATION,
               MEM_ATTR.VALUE MEM_TYPE,
               MODEL_ATTR.VALUE MODEL,
               INVENTORY_NUM_ATTR.VALUE INVENTORY_NUM,
               SER_ATTR.VALUE SERIAL_NUMBER
               ,STATUS_ATTR.VALUE  AS INV_STATUS_ID
               ,LIST_STATUS.NAME   AS INV_STATUS_NAME
          FROM OBJECTS NOTE
          LEFT JOIN ATTRIBUTES NAME_ATTR ON NAME_ATTR.OBJECT_ID = NOTE.OBJECT_ID AND NAME_ATTR.ATTR_ID = 9/*NAME*/
          LEFT JOIN ATTRIBUTES LOC_ATTR ON LOC_ATTR.OBJECT_ID = NOTE.OBJECT_ID AND LOC_ATTR.ATTR_ID = 10/*LOCATION*/
          LEFT JOIN ATTRIBUTES MEM_ATTR ON MEM_ATTR.OBJECT_ID = NOTE.OBJECT_ID AND MEM_ATTR.ATTR_ID = 11/*MEMORY_TYPE*/
          LEFT JOIN ATTRIBUTES MODEL_ATTR ON MODEL_ATTR.OBJECT_ID = NOTE.OBJECT_ID AND MODEL_ATTR.ATTR_ID = 12/*MODEL*/
          LEFT JOIN ATTRIBUTES INVENTORY_NUM_ATTR ON INVENTORY_NUM_ATTR.OBJECT_ID = NOTE.OBJECT_ID  AND INVENTORY_NUM_ATTR.ATTR_ID = 13/*INVENTORY_NUM*/
          LEFT JOIN ATTRIBUTES SER_ATTR ON SER_ATTR.OBJECT_ID = NOTE.OBJECT_ID AND SER_ATTR.ATTR_ID = 14/*SERIAL_NUMBER*/
          LEFT JOIN ATTRIBUTES STATUS_ATTR ON STATUS_ATTR.OBJECT_ID = NOTE.OBJECT_ID  AND STATUS_ATTR.ATTR_ID = 16/*STATUS*/
          LEFT JOIN LISTTYPE LIST_STATUS ON LIST_STATUS.ID = STATUS_ATTR.VALUE
         WHERE NOTE.OBJECT_TYPE_ID = 7 /*NOTEPAD*/
           AND (p_object_id is null or NOTE.OBJECT_ID = p_object_id)
           AND (p_inv_status_id is null or STATUS_ATTR.VALUE = p_inv_status_id)
           AND (p_name_mask is null or (length(p_name_mask) > 2 and NAME_ATTR.VALUE like '%' || p_name_mask || '%'))
           AND (p_location_mask is null or (length(p_name_mask) > 2 and LOC_ATTR.VALUE like '%' || p_location_mask || '%'))
      )
      where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last);
  end;

  ----

  procedure notebook_select_ext(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_inv_status_id in number default null,    -- filter by status
        p_name_mask     in varchar2 default null,  -- filter by name
        p_location_mask in varchar2 default null,  -- filter by location
        p_rownum_first  in number default null,    -- pagination 
        p_rownum_last   in number default null     -- pagination 
        ) as
    l_rownum_first number := p_rownum_first;
  begin
    if l_rownum_first is null then
      l_rownum_first := 1;
    end if;
  
    open p_out_cursor for
      with ref_ord_invent as
      (  
         select
                invent.object_id       invent_id
                ,ORD_REF.object_id     order_id
                ,ATTR_DATE.DATE_VALUE  due_date
                
                ,ATTR_ORD_STATUS.VALUE  AS ORD_STATUS_ID
                ,LIST_ORD_STATUS.NAME   AS ORD_STATUS_NAME
           from objects invent

           LEFT JOIN OBJREFERENCE ORD_REF ON ORD_REF.REFERENCE = invent.OBJECT_ID
           LEFT JOIN OBJECTS ORD ON ORD.OBJECT_ID = ORD_REF.OBJECT_ID
           LEFT JOIN ATTRTYPE ORD_INVENT ON ORD_INVENT.ATTR_ID = 8 /*INVENTORY*/

           LEFT JOIN ATTRIBUTES ATTR_DATE ON ORD.OBJECT_ID = ATTR_DATE.OBJECT_ID AND ATTR_DATE.ATTR_ID = 15 /* DATE */
           LEFT JOIN ATTRIBUTES ATTR_ORD_STATUS ON ORD.OBJECT_ID = ATTR_ORD_STATUS.OBJECT_ID AND ATTR_ORD_STATUS.ATTR_ID = 19 /* ORDER_STATUS */
           LEFT JOIN LISTTYPE LIST_ORD_STATUS ON ATTR_ORD_STATUS.VALUE = LIST_ORD_STATUS.ID

          where invent.object_type_id = 7 /*NOTEPAD*/
            and ATTR_ORD_STATUS.VALUE <> 8
       ) 
               
       select * from (
         SELECT /*+ first_rows(30) */ 
                row_number() over (order by invent.OBJECT_ID) rn
              
               ,invent.OBJECT_ID              NOTE_ID
               ,ATTR_INVENTORY_NUM.VALUE      INVENTORY_NUM
               ,ATTR_INV_STATUS.VALUE         INV_STATUS_ID
               ,LIST_INV_STATUS.NAME          INV_STATUS_NAME
               --
               ,ATTR_INV_NAME.VALUE           NAME
               ,ATTR_INV_LOC.VALUE            LOCATION
               ,ATTR_INV_MEM.VALUE            MEM_TYPE
               ,ATTR_INV_MODEL.VALUE          MODEL
               ,ATTR_INV_SER.VALUE            SERIAL_NUMBER
                
               -- LINK TO ORDER DATA >> --
               ,ref_ord_invent.order_id         ORDER_ID
               ,invent_date_max.max_due_date    DUE_DATE
               ,ATTR_CREATE_DATE.DATE_VALUE     OPEN_DATE
               ,ref_ord_invent.ORD_STATUS_ID    ORD_STATUS_ID           
               ,ref_ord_invent.ORD_STATUS_NAME  ORD_STATUS_NAME
               ,EMP_REF.REFERENCE               EMPLOYEE_ID
               ,EMP_ATTR_FUll_NAME.VALUE        EMPLOYEE_FUll_NAME
               -- << LINK TO ORDER DATA --
                
          from objects invent
          left join (
                     select invent_id        max_invent_id
                            ,max(due_date) max_due_date
                       from ref_ord_invent
                      group by invent_id
                    ) invent_date_max 
               on invent_date_max.max_invent_id = invent.object_id
          left join ref_ord_invent on ref_ord_invent.invent_id = invent.object_id and ref_ord_invent.due_date = invent_date_max.max_due_date
          
          
          LEFT JOIN ATTRIBUTES ATTR_INVENTORY_NUM ON ATTR_INVENTORY_NUM.OBJECT_ID = invent.OBJECT_ID AND ATTR_INVENTORY_NUM.ATTR_ID = 13 /* INVENTORY_NUM */
        LEFT JOIN ATTRIBUTES ATTR_INV_STATUS ON ATTR_INV_STATUS.OBJECT_ID = invent.OBJECT_ID AND ATTR_INV_STATUS.ATTR_ID = 16 /* STATUS */
        LEFT JOIN LISTTYPE LIST_INV_STATUS ON LIST_INV_STATUS.ID =  ATTR_INV_STATUS.VALUE
          ----
          LEFT JOIN ATTRIBUTES ATTR_INV_NAME ON ATTR_INV_NAME.OBJECT_ID = invent.OBJECT_ID AND ATTR_INV_NAME.ATTR_ID = 9/*NAME*/
          LEFT JOIN ATTRIBUTES ATTR_INV_LOC ON ATTR_INV_LOC.OBJECT_ID = invent.OBJECT_ID AND ATTR_INV_LOC.ATTR_ID = 10/*LOCATION*/
          LEFT JOIN ATTRIBUTES ATTR_INV_MEM ON ATTR_INV_MEM.OBJECT_ID = invent.OBJECT_ID AND ATTR_INV_MEM.ATTR_ID = 11/*MEMORY_TYPE*/
          LEFT JOIN ATTRIBUTES ATTR_INV_MODEL ON ATTR_INV_MODEL.OBJECT_ID = invent.OBJECT_ID AND ATTR_INV_MODEL.ATTR_ID = 12/*MODEL*/
          LEFT JOIN ATTRIBUTES ATTR_INV_SER ON ATTR_INV_SER.OBJECT_ID = invent.OBJECT_ID AND ATTR_INV_SER.ATTR_ID = 14/*SERIAL_NUMBER*/
          
          
          -- LINK TO ORDER DATA >> --
          LEFT JOIN ATTRIBUTES ATTR_CREATE_DATE ON ref_ord_invent.order_id/*ORD.OBJECT_ID*/ = ATTR_CREATE_DATE.OBJECT_ID AND ATTR_CREATE_DATE.ATTR_ID = 26 /* CREATION_DATE */
          LEFT JOIN OBJREFERENCE EMP_REF ON EMP_REF.OBJECT_ID = ref_ord_invent.order_id/*ORD.OBJECT_ID*/ AND EMP_REF.ATTR_ID = 7 /*EMPLOYEE_TO*/
          LEFT JOIN OBJECTS EMP ON EMP.OBJECT_ID = EMP_REF.REFERENCE
          LEFT JOIN ATTRIBUTES EMP_ATTR_FUll_NAME ON EMP_ATTR_FUll_NAME.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
          -- << LINK TO ORDER DATA  --

          where invent.object_type_id = 7 /*NOTEPAD*/
            AND (p_object_id is null or invent.OBJECT_ID = p_object_id)
            AND (p_inv_status_id is null or ATTR_INV_STATUS.VALUE = p_inv_status_id)
            AND (p_name_mask is null or (length(p_name_mask) > 2 and ATTR_INV_NAME.VALUE like '%' || p_name_mask || '%'))
            AND (p_location_mask is null or (length(p_name_mask) > 2 and ATTR_INV_LOC.VALUE like '%' || p_location_mask || '%'))
        )
        where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last)
      ;
  end;
  ----
  procedure notebook_insert(
        p_object_id     in out  number,
        p_name          in      varchar2,
        p_location      in      varchar2,
        p_memory_type   in      varchar2,
        p_model         in      varchar2,
        p_inventory_num in      varchar2,
        p_serial_number in      varchar2,
        p_inv_status_id in      varchar2) as
  begin
    if p_object_id is null then
      p_object_id := seq_objects.nextval;
    end if;
  
    INSERT ALL
      INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (p_object_id, NULL, 7, p_serial_number, NULL)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES ( 9, p_object_id, p_name, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (10, p_object_id, p_location, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (11, p_object_id, p_memory_type, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (12, p_object_id, p_model, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (13, p_object_id, p_inventory_num, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (14, p_object_id, p_serial_number, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (16, p_object_id, p_inv_status_id, null)
    SELECT * FROM dual;
  end;
  ----
  procedure notebook_insert_ext(
        p_object_id     in out  number,
        p_name          in      varchar2,
        p_location      in      varchar2,
        p_memory_type   in      varchar2,
        p_model         in      varchar2,
        p_inventory_num in      varchar2,
        p_serial_number in      varchar2,
        p_inv_status_id in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2) as
  begin
    SAVEPOINT notebook_insert_ext;
    notebook_insert(p_object_id, p_name, p_location, p_memory_type, p_model, p_inventory_num, p_serial_number, p_inv_status_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO notebook_insert_ext;
      return;
  end;      
  ----
  procedure notebook_update(
        p_object_id     in number,
        p_name          in varchar2,
        p_location      in  varchar2,
        p_memory_type   in varchar2,
        p_model         in varchar2,
        p_inventory_num in varchar2,
        p_serial_number in varchar2,
        p_inv_status_id in varchar2) 
  as
    l_OBJECT_TYPE_ID  number;
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
    end if;
    
    select o.object_type_id
      into l_OBJECT_TYPE_ID
      from objects o
     where o.object_id = p_object_id;
     
    if l_OBJECT_TYPE_ID <> 7 then
      raise_application_error(-20001, 'Запись не соответствувет типу NOTEBOOK.');
    end if;
    
    merge into attributes a
    USING(
      select p_object_id as object_id,  9 attr_id, p_name as value from dual union all
      select p_object_id as object_id, 10 attr_id, p_location as value from dual union all
      select p_object_id as object_id, 11 attr_id, p_memory_type as value from dual union all
      select p_object_id as object_id, 12 attr_id, p_model as value from dual union all
      select p_object_id as object_id, 13 attr_id, p_inventory_num as value from dual union all
      select p_object_id as object_id, 14 attr_id, p_serial_number as value from dual union all
      select p_object_id as object_id, 16 attr_id, p_inv_status_id as value from dual
    ) t on (a.object_id = t.object_id and a.attr_id = t.attr_id)
    when matched then update set a.value = t.value;
  end;
  --
  procedure notebook_update_ext(
        p_object_id     in number,
        p_name          in varchar2,
        p_location      in  varchar2,
        p_memory_type   in varchar2,
        p_model         in varchar2,
        p_inventory_num in varchar2,
        p_serial_number in varchar2,
        p_inv_status_id in varchar2,
        p_err_code  out number,
        p_err_msg   out varchar2) 
  as
  begin
    SAVEPOINT notebook_update_ext;
    notebook_update(p_object_id,
        p_name,
        p_location,
        p_memory_type,
        p_model,
        p_inventory_num,
        p_serial_number,
        p_inv_status_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO notebook_update_ext;
      return;
  end;
  
  ------
  procedure notebook_delete(p_object_id in number) as
    l_OBJECT_TYPE_ID  number;
    l_is_referenced_by_others number := 0;
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для удаления.');
    end if;
    
    begin
      select o.object_type_id
        into l_OBJECT_TYPE_ID
        from objects o
       where o.object_id = p_object_id;
    exception
      when no_data_found then
        raise_application_error(-20001, 'Запись с ID=' || p_object_id || ' не найдена.');
    end;
    if l_OBJECT_TYPE_ID <> 7 then
      raise_application_error(-20001, 'Запись не соответствувет типу NOTEBOOK.');
    end if;
       
    begin
      select 1
        into l_is_referenced_by_others
        from dual
       where exists ( select 1 from objreference o where o.reference = p_object_id );
    exception 
      when no_data_found then NULL;
    end;
    if l_is_referenced_by_others = 1 then
      raise_application_error(-20001, 'Удаление невозможно. На ноутбук ссылаются записи в других документах.');
    end if;
     

    delete from attributes
     where object_id = p_object_id;
     
    -- +++
    delete from OBJREFERENCE
     where object_id = p_object_id  --or reference = p_object_id
     ;
    delete from objects
     where object_id = p_object_id;
  end;
  
  --
  
  procedure notebook_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT notebook_delete_ext;
    notebook_delete(p_object_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO notebook_delete_ext;
      return;
  end;
  
--==========================================================

begin
  -- Initialization
  null;
end dm_notebook;


/


create or replace package body dm_inv_status is

  --======================================
  
  procedure inv_status_select(
        p_id          in number,
        p_out_cursor out sys_refcursor) as
  begin
    open p_out_cursor for 
      select t.id, t.code, t.name
        from listtype t
       where t.attrtype_code = 'INV_STATUS'
         and (p_id is null  or  p_id = t.id);
  end;
  ----
  procedure inv_status_insert(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2) as
  begin
    insert into listtype
      (id, attrtype_code, code, name, comments)
      values (p_id, 'INV_STATUS', p_code, p_name, p_comments)
      returning id, attrtype_code
      into p_id, p_attrtype_code;
  end;
  ----
  procedure inv_status_insert_ext(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2) as
  begin
    SAVEPOINT inv_status_insert_ext;
    inv_status_insert(p_id, p_attrtype_code, p_code, p_name, p_comments);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO inv_status_insert_ext;
      return;
  end;
  ----
  procedure inv_status_update(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2) as
  begin
    if p_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
    end if;
    
    update listtype
      set code = p_code,
          name = p_name,
          comments = p_comments
      where id = p_id
        and attrtype_code = 'INV_STATUS'
      returning code, name, comments
      into p_code, p_name, p_comments;
      
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Не найдена запись для редактирования.');
    end if;
  end;
  ----
  procedure inv_status_update_ext(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT inv_status_update_ext;
    inv_status_update(p_id, p_code, p_name, p_comments);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO inv_status_update_ext;
      return;
  end;
  ----
  procedure inv_status_delete(p_id in number) as
  begin
    delete from listtype
     where id = p_id
       and attrtype_code = 'INV_STATUS';
     
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Не найдена запись для удаления.');
    end if;
  end;
  ----
  procedure inv_status_delete_ext(
          p_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT inv_status_delete_ext;
    inv_status_delete(p_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO inv_status_delete_ext;
      return;
  end;
  
  --======================================

begin
  -- Initialization
  null;
end dm_inv_status;


/


create or replace package body dm_employee is

--=========================================

  procedure employee_select(
        p_object_id      in number,
        p_out_cursor     out sys_refcursor,
        p_full_name_mask in varchar2 default null,
        p_rownum_first   in number default null,   -- pagination
        p_rownum_last    in number default null    -- pagination
  ) as
    l_rownum_first number := p_rownum_first;
  begin
    if l_rownum_first is null then
      l_rownum_first := 1;
    end if;
  
    open p_out_cursor for
      select * from (
        SELECT /*+ first_rows(30) */
               row_number() over (order by EMP.OBJECT_ID) rn,
               EMP.OBJECT_ID AS EMPLOYEE_ID, 
               PHONE_ATTR.VALUE AS PHONE_NUMBER, 
               FNAME_ATTR.VALUE AS FULL_NAME, 
               EMAIL_ATTR.VALUE AS EMAIL
          FROM OBJECTS EMP, ATTRIBUTES FNAME_ATTR, ATTRIBUTES EMAIL_ATTR, ATTRIBUTES PHONE_ATTR
         WHERE EMP.OBJECT_TYPE_ID = 1 /* EMPLOYEE */
           AND EMP.OBJECT_ID = FNAME_ATTR.OBJECT_ID
           AND EMP.OBJECT_ID = EMAIL_ATTR.OBJECT_ID
           AND EMP.OBJECT_ID = PHONE_ATTR.OBJECT_ID
           AND FNAME_ATTR.ATTR_ID = 1 /* FULL_NAME */
           AND EMAIL_ATTR.ATTR_ID = 2 /* EMAIL */
           AND PHONE_ATTR.ATTR_ID = 3 /* PHONE_NUMBER */
           AND (p_object_id is null or EMP.OBJECT_ID = p_object_id)
           AND (p_full_name_mask is null or (length(p_full_name_mask) > 2 and FNAME_ATTR.VALUE like '%' || p_full_name_mask || '%'))
      )
      where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last);
  end;
  ----
  procedure employee_insert(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2) as
  begin
    if p_object_id is null then
      p_object_id := seq_objects.nextval;
    end if;
  
    INSERT ALL
      INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (p_object_id, NULL, 1, p_full_name, NULL)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (1, p_object_id, p_full_name, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (2, p_object_id, p_email, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (3, p_object_id, p_phone_number, null)
    SELECT * FROM dual;  
  end;
  ----
  procedure employee_insert_ext(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2) as
  begin
    SAVEPOINT employee_insert_ext;
    employee_insert(p_object_id, p_full_name, p_phone_number, p_email);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO employee_insert_ext;
      return;
  end;
  
  ----
  procedure employee_update(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2) 
  as
    l_OBJECT_TYPE_ID  number;
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
    end if;
    
    select o.object_type_id
      into l_OBJECT_TYPE_ID
      from objects o
     where o.object_id = p_object_id;
     
    if l_OBJECT_TYPE_ID <> 1 then
      raise_application_error(-20001, 'Запись не соответствувет типу EMPLOYEE.');
    end if;
    
    merge into attributes a
    USING(
      select p_object_id as object_id, 1 attr_id /* FULL_NAME */, p_full_name as value from dual union all
      select p_object_id as object_id, 2 attr_id /* EMAIL */, p_email as value from dual union all
      select p_object_id as object_id, 3 attr_id /* PHONE_NUMBER */, p_phone_number as value from dual
    ) t on (a.object_id = t.object_id and a.attr_id = t.attr_id)
    when matched then update set a.value = t.value;
  end;
  ----
  
  procedure employee_update_ext(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2,
        p_err_code  out number,
        p_err_msg   out varchar2) as
  begin
    SAVEPOINT employee_update_ext;
    employee_update(p_object_id, p_full_name, p_phone_number, p_email);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO employee_update_ext;
      return;
  end;
        
        
  ----
  
  
  procedure employee_delete(p_object_id in number) as
    l_OBJECT_TYPE_ID  number;
    l_is_referenced_by_others number := 0;
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для удаления.');
    end if;
    
    begin
      select o.object_type_id
        into l_OBJECT_TYPE_ID
        from objects o
       where o.object_id = p_object_id;
    exception
      when no_data_found then
        raise_application_error(-20001, 'Запись с ID=' || p_object_id || ' не найдена.');
    end; 
    if l_OBJECT_TYPE_ID <> 1 then
      raise_application_error(-20001, 'Запись не соответствувет типу EMPLOYEE.');
    end if;
    
    begin
      select 1
        into l_is_referenced_by_others
        from dual
       where exists ( select 1 from objreference o where o.reference = p_object_id );
    exception 
      when no_data_found then NULL;
    end;
    if l_is_referenced_by_others = 1 then
      raise_application_error(-20001, 'Удаление невозможно. На сотрудника ссылаются записи в других документах.');
    end if;
  
    delete from attributes
     where object_id = p_object_id;
     
    -- +++
    delete from OBJREFERENCE
     where object_id = p_object_id  --or reference = p_object_id
     ;
    delete from objects
     where object_id = p_object_id;
  end;
  
  ----
  
  procedure employee_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT employee_delete_ext;
    employee_delete(p_object_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO employee_delete_ext;
      return;
  end;
  
  
--==========================================================

begin
  -- Initialization
  null;
end dm_employee;


/


create or replace package body dm_access_card is

--==========================================================
  procedure access_card_select(
        p_object_id     in number,
        p_out_cursor   out sys_refcursor,
        p_inv_status_id in number default null,   -- filter by status
        p_rownum_first  in number default null,   -- pagination
        p_rownum_last   in number default null    -- pagination
        ) as
    l_rownum_first number := p_rownum_first;
  begin
    if l_rownum_first is null then
      l_rownum_first := 1;
    end if;
  
    open p_out_cursor for
      select * from (
        SELECT /*+ first_rows(30) */ 
                row_number() over (order by CARD.OBJECT_ID) rn
               ,CARD.OBJECT_ID AS OBJECT_ID
               ,ATTR_INVENTORY_NUM.VALUE AS INVENTORY_NUM
               ,ATTR_INV_STATUS.VALUE  AS INV_STATUS_ID
               ,LIST_INV_STATUS.NAME   AS INV_STATUS_NAME
               
          FROM OBJECTS CARD
          LEFT JOIN ATTRIBUTES ATTR_INVENTORY_NUM ON ATTR_INVENTORY_NUM.OBJECT_ID = CARD.OBJECT_ID AND ATTR_INVENTORY_NUM.ATTR_ID = 13 /* INVENTORY_NUM */
          LEFT JOIN ATTRIBUTES ATTR_INV_STATUS ON ATTR_INV_STATUS.OBJECT_ID = CARD.OBJECT_ID AND ATTR_INV_STATUS.ATTR_ID = 16 /* STATUS */
          LEFT JOIN LISTTYPE LIST_INV_STATUS ON LIST_INV_STATUS.ID =  ATTR_INV_STATUS.VALUE

         WHERE CARD.OBJECT_TYPE_ID = 6 /* CARD */
           AND (p_object_id is null or CARD.OBJECT_ID = p_object_id)
           AND (p_inv_status_id is null or ATTR_INV_STATUS.VALUE = p_inv_status_id)
      )
      where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last);
  end;
  
  ----

  procedure access_card_select_ext(
        p_object_id     in number,
        p_out_cursor   out sys_refcursor,
        p_inv_status_id in number default null,   -- filter by status
        p_rownum_first  in number default null,   -- pagination
        p_rownum_last   in number default null    -- pagination
        ) as
    l_rownum_first number := p_rownum_first;
  begin
    if l_rownum_first is null then
      l_rownum_first := 1;
    end if;
  
    open p_out_cursor for
      with ref_ord_invent as
      (  
         select
                invent.object_id       invent_id
                ,ORD_REF.object_id     order_id
                ,ATTR_DATE.DATE_VALUE  due_date
                ,ATTR_ORD_STATUS.VALUE ORD_STATUS_ID
                ,LIST_ORD_STATUS.NAME  ORD_STATUS_NAME
           from objects invent
           LEFT JOIN OBJREFERENCE ORD_REF ON ORD_REF.REFERENCE = invent.OBJECT_ID
           LEFT JOIN OBJECTS ORD ON ORD.OBJECT_ID = ORD_REF.OBJECT_ID
           LEFT JOIN ATTRTYPE ORD_INVENT ON ORD_INVENT.ATTR_ID = 8 /*INVENTORY*/

           LEFT JOIN ATTRIBUTES ATTR_DATE ON ORD.OBJECT_ID = ATTR_DATE.OBJECT_ID AND ATTR_DATE.ATTR_ID = 15 /* DATE */
           LEFT JOIN ATTRIBUTES ATTR_ORD_STATUS ON ORD.OBJECT_ID = ATTR_ORD_STATUS.OBJECT_ID AND ATTR_ORD_STATUS.ATTR_ID = 19 /* ORDER_STATUS */
           LEFT JOIN LISTTYPE LIST_ORD_STATUS ON ATTR_ORD_STATUS.VALUE = LIST_ORD_STATUS.ID
          where invent.object_type_id = 6 /* CARD */
            and ATTR_ORD_STATUS.VALUE <> 8
       ) 
       select * from (
         SELECT /*+ first_rows(30) */ 
                row_number() over (order by invent.OBJECT_ID) rn
              
               ,invent.OBJECT_ID              OBJECT_ID
               ,ATTR_INVENTORY_NUM.VALUE      INVENTORY_NUM
               ,ATTR_INV_STATUS.VALUE         INV_STATUS_ID
               ,LIST_INV_STATUS.NAME          INV_STATUS_NAME
               -- LINK TO ORDER DATA >> --
               ,ref_ord_invent.order_id         ORDER_ID
               ,invent_date_max.max_due_date    DUE_DATE
               ,ATTR_CREATE_DATE.DATE_VALUE     OPEN_DATE
               ,ref_ord_invent.ORD_STATUS_ID    ORD_STATUS_ID           
               ,ref_ord_invent.ORD_STATUS_NAME  ORD_STATUS_NAME             
               ,EMP_REF.REFERENCE               EMPLOYEE_ID
               ,EMP_ATTR_FUll_NAME.VALUE        EMPLOYEE_FUll_NAME
               -- << LINK TO ORDER DATA --
          from objects invent
          left join (
                     select invent_id        max_invent_id
                            ,max(due_date) max_due_date
                       from ref_ord_invent
                      group by invent_id
                    ) invent_date_max 
               on invent_date_max.max_invent_id = invent.object_id
          left join ref_ord_invent on ref_ord_invent.invent_id = invent.object_id and ref_ord_invent.due_date = invent_date_max.max_due_date
          
          LEFT JOIN ATTRIBUTES ATTR_INVENTORY_NUM ON ATTR_INVENTORY_NUM.OBJECT_ID = invent.OBJECT_ID AND ATTR_INVENTORY_NUM.ATTR_ID = 13 /* INVENTORY_NUM */
          LEFT JOIN ATTRIBUTES ATTR_INV_STATUS ON ATTR_INV_STATUS.OBJECT_ID = invent.OBJECT_ID AND ATTR_INV_STATUS.ATTR_ID = 16 /* STATUS */
          LEFT JOIN LISTTYPE LIST_INV_STATUS ON LIST_INV_STATUS.ID =  ATTR_INV_STATUS.VALUE
          -- LINK TO ORDER DATA >> --
          LEFT JOIN ATTRIBUTES ATTR_CREATE_DATE ON ref_ord_invent.order_id/*ORD.OBJECT_ID*/ = ATTR_CREATE_DATE.OBJECT_ID AND ATTR_CREATE_DATE.ATTR_ID = 26 /* CREATION_DATE */
          LEFT JOIN OBJREFERENCE EMP_REF ON EMP_REF.OBJECT_ID = ref_ord_invent.order_id/*ORD.OBJECT_ID*/ AND EMP_REF.ATTR_ID = 7 /*EMPLOYEE_TO*/
          LEFT JOIN OBJECTS EMP ON EMP.OBJECT_ID = EMP_REF.REFERENCE
          LEFT JOIN ATTRIBUTES EMP_ATTR_FUll_NAME ON EMP_ATTR_FUll_NAME.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
          -- << LINK TO ORDER DATA  --
           
          where invent.object_type_id = 6 /* CARD */
            AND (p_object_id is null or invent.OBJECT_ID = p_object_id)
            AND (p_inv_status_id is null or ATTR_INV_STATUS.VALUE = p_inv_status_id)
        )
        where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last)
      ;
  end;
  
  ----
  procedure access_card_insert(
        p_object_id     in out  number,
        p_inventory_num in      varchar2,
        p_inv_status_id in      varchar2) as
  begin
    if p_object_id is null then
      p_object_id := seq_objects.nextval;
    end if;
  
    INSERT ALL
      INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (p_object_id, NULL, 6, p_inventory_num, NULL)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (13, p_object_id, p_inventory_num, null)
      INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE) VALUES (16, p_object_id, p_inv_status_id, null)
    SELECT * FROM dual;  
  end;
  ----
  procedure access_card_insert_ext(
        p_object_id     in out  number,
        p_inventory_num in      varchar2,
        p_inv_status_id in      varchar2,
          p_err_code    out number,
          p_err_msg     out varchar2) as
  begin
    SAVEPOINT access_card_insert_ext;
    access_card_insert(p_object_id, p_inventory_num, p_inv_status_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO access_card_insert_ext;
      return;
  end;
  ----
  procedure access_card_update(
        p_object_id     in number,
        p_inventory_num in varchar2,
        p_inv_status_id in varchar2) 
  as
    l_OBJECT_TYPE_ID  number;
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
    end if;
    
    begin
      select o.object_type_id
        into l_OBJECT_TYPE_ID
        from objects o
       where o.object_id = p_object_id;
    exception
      when no_data_found then
        raise_application_error(-20001, 'Запись с ID=' || p_object_id || ' не найдена.');
    end;
     
    if l_OBJECT_TYPE_ID <> 6 then
      raise_application_error(-20001, 'Запись не соответствувет типу ACCESS_CARD.');
    end if;
    
    merge into attributes a
    USING(
      select p_object_id as object_id, 13 attr_id /* INVENTORY_NUM */, p_inventory_num as value from dual union all
      select p_object_id as object_id, 16 attr_id /* STATUS */, p_inv_status_id as value from dual
    ) t on (a.object_id = t.object_id and a.attr_id = t.attr_id)
    when matched then update set a.value = t.value;
  end;
  
  ----
  procedure access_card_update_ext(
          p_object_id in number,
          p_inventory_num in varchar2,
          p_inv_status_id in varchar2,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT access_card_update_ext;
    access_card_update(p_object_id, p_inventory_num, p_inv_status_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO access_card_update_ext;
      return;
  end;
  
  ----
  procedure access_card_delete(p_object_id in number) as
    l_OBJECT_TYPE_ID  number;
    l_is_referenced_by_others number := 0;
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для удаления.');
    end if;
    
    begin
      select o.object_type_id
        into l_OBJECT_TYPE_ID
        from objects o
       where o.object_id = p_object_id;
    exception
      when no_data_found then
        raise_application_error(-20001, 'Запись с ID=' || p_object_id || ' не найдена.');
    end;
    if l_OBJECT_TYPE_ID <> 6 then
      raise_application_error(-20001, 'Запись не соответствувет типу ACCESS_CARD.');
    end if;
      
    begin
      select 1
        into l_is_referenced_by_others
        from dual
       where exists ( select 1 from objreference o where o.reference = p_object_id );
    exception 
      when no_data_found then NULL;
    end;
    if l_is_referenced_by_others = 1 then
      raise_application_error(-20001, 'Удаление невозможно. На карту ссылаются записи в других документах.');
    end if;
     

    delete from attributes
     where object_id = p_object_id;
          
    -- +++
    delete from OBJREFERENCE
     where object_id = p_object_id  --or reference = p_object_id    
    ;
    delete from objects
     where object_id = p_object_id;
  end;
  
  ----
  
  procedure access_card_delete_ext(
          p_object_id in number,
          p_err_code  out number,
          p_err_msg   out varchar2) as
  begin
    SAVEPOINT access_card_delete_ext;
    access_card_delete(p_object_id);
    p_err_code := 0;
    p_err_msg  := '';
  exception
    when others then
      p_err_code := SQLCODE;
      p_err_msg  := SQLERRM;
      ROLLBACK TO access_card_delete_ext;
      return;
  end;
  
--==========================================================

begin
  -- Initialization
  null;
end dm_access_card;


/


