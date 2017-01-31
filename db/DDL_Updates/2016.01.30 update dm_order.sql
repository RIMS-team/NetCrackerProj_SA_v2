create or replace package dm_order is


update LISTTYPE set
"ID" = 5, "ATTRTYPE_CODE" = 'ORD_STATUS', "CODE" = 'CLOSED', "NAME" = 'Closed', "COMMENTS" = 'Order status'
where ID = 5;

commit;


  --===========================================
  
  procedure order_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_ord_status_id in number default null,   -- filter by status
        p_rownum_first  in number default null,
        p_rownum_last   in number default null
        );
  ------
  procedure order_select_by_employee(
          p_employee_id  in number,
          p_out_cursor   out sys_refcursor);
  ----
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


create or replace package body dm_order is

--==========================================================
  function is_new_ord_status_available(p_new_ord_status_id number, p_old_ord_status_id number) return boolean as
    l_res boolean :=false;
  begin
    if p_new_ord_status_id >= p_old_ord_status_id then
      l_res := true;
    elsif p_new_ord_status_id = 6 /*EXTENDED �������*/ and p_old_ord_status_id = 7 /*OVERDUE ���������*/ then 
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
      LEFT     JOIN OBJECTS USR ON USR.OBJECT_ID = USR_REF.REFERENCE  -- user id
          LEFT JOIN ATTRIBUTES USR_ATTR_FUll_NAME ON USR_ATTR_FUll_NAME.OBJECT_ID = USR.OBJECT_ID AND USR_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
          LEFT JOIN ATTRIBUTES USR_ATTR_EMAIL ON USR_ATTR_EMAIL.OBJECT_ID = USR.OBJECT_ID AND USR_ATTR_EMAIL.ATTR_ID = 2 /*EMAIL*/
          
      LEFT     JOIN OBJREFERENCE USR_EDITOR_REF ON USR_EDITOR_REF.OBJECT_ID = ORD.OBJECT_ID AND USR_EDITOR_REF.ATTR_ID = 27 /*EDITOR_USER*/
      LEFT     JOIN OBJECTS USR_EDITOR ON USR.OBJECT_ID = USR_EDITOR_REF.REFERENCE  -- user-editor id
          LEFT JOIN ATTRIBUTES USR_EDITOR_ATTR_FUll_NAME ON USR_EDITOR_ATTR_FUll_NAME.OBJECT_ID = USR.OBJECT_ID AND USR_EDITOR_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
        
      LEFT     JOIN OBJREFERENCE EMP_REF ON EMP_REF.OBJECT_ID = ORD.OBJECT_ID AND EMP_REF.ATTR_ID = 7 /*EMPLOYEE_TO*/
      LEFT     JOIN OBJECTS EMP ON EMP.OBJECT_ID = EMP_REF.REFERENCE  -- employee id
          LEFT JOIN ATTRIBUTES EMP_ATTR_FUll_NAME ON EMP_ATTR_FUll_NAME.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_FUll_NAME.ATTR_ID = 1 /*FUll_NAME*/
          LEFT JOIN ATTRIBUTES EMP_ATTR_EMAIL ON EMP_ATTR_EMAIL.OBJECT_ID = EMP.OBJECT_ID AND EMP_ATTR_EMAIL.ATTR_ID = 2 /*EMAIL*/
         
         WHERE ORD.OBJECT_TYPE_ID = 5 /* ORDER */
           AND (p_object_id is null or ORD.OBJECT_ID = p_object_id)
           AND (p_ord_status_id is null or ATTR_ORD_STATUS.VALUE = p_ord_status_id)
      )
      where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last);
  end;
  ----
  
  procedure order_select_by_employee(
          p_employee_id  in number,
          p_out_cursor   out sys_refcursor)
  is
  begin
    open p_out_cursor for
      select
              ORD.OBJECT_ID        ORDER_ID
             ,ATTR_DATE.DATE_VALUE DUE_DATE
             --,INVENTORY.OBJECT_ID  INVENTORY_ID
             ,INVENTORY_TYPE.NAME  INVENTORY_TYPE
             ,ATTR_INV_NUM.VALUE   INVENTORY_NUM
             --,EMP_REF.REFERENCE    EMPLOYEE_ID
        FROM OBJECTS ORD
        LEFT JOIN ATTRIBUTES ATTR_DATE ON ORD.OBJECT_ID = ATTR_DATE.OBJECT_ID AND ATTR_DATE.ATTR_ID = 15 /* DATE */
        LEFT JOIN OBJREFERENCE INVENTORY_REF ON INVENTORY_REF.OBJECT_ID = ORD.OBJECT_ID AND INVENTORY_REF.ATTR_ID = 8 /*INVENTORY*/
        LEFT JOIN OBJECTS INVENTORY ON INVENTORY.OBJECT_ID = INVENTORY_REF.REFERENCE 
        LEFT JOIN OBJTYPE INVENTORY_TYPE ON INVENTORY_TYPE.OBJECT_TYPE_ID = INVENTORY.OBJECT_TYPE_ID  -- inv
        LEFT JOIN ATTRIBUTES ATTR_INV_NUM ON ATTR_INV_NUM.OBJECT_ID = INVENTORY.OBJECT_ID AND ATTR_INV_NUM.ATTR_ID = 13 /*INVENTORY_NUM*/
        LEFT JOIN OBJREFERENCE EMP_REF ON EMP_REF.OBJECT_ID = ORD.OBJECT_ID AND EMP_REF.ATTR_ID = 7 /*EMPLOYEE_TO*/
        LEFT JOIN ATTRIBUTES ATTR_ORD_STATUS ON ORD.OBJECT_ID = ATTR_ORD_STATUS.OBJECT_ID AND ATTR_ORD_STATUS.ATTR_ID = 19 /* ORDER_STATUS */
       WHERE ORD.OBJECT_TYPE_ID = 5 /* ORDER */
         AND EMP_REF.REFERENCE = p_employee_id
         AND ATTR_ORD_STATUS.VALUE IN (5,6,7);
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
      raise_application_error(-20016, dm_util.get_err_msg(-20016, 'p_inventory_id'));
    end if;
    if p_employee_id is null then
      raise_application_error(-20016, dm_util.get_err_msg(-20016, 'p_employee_id'));
    end if;
    if p_user_id is null then
      raise_application_error(-20016, dm_util.get_err_msg(-20016, 'p_user_id'));
    end if;
    
    if p_object_id is null then
      p_object_id := seq_objects.nextval;
    end if;
    
    if l_date is null then
      l_date := sysdate;
    end if;
    
    if l_ord_status_id is null then
      l_ord_status_id := 5;  /*������*/
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
      raise_application_error(-20010, dm_util.get_err_msg(-20010));
    end if;
    
    if p_inventory_id is null then
      raise_application_error(-20016, dm_util.get_err_msg(-20016, 'p_inventory_id'));
    end if;
    if p_employee_id is null then
      raise_application_error(-20016, dm_util.get_err_msg(-20016, 'p_employee_id'));
    end if;
    if p_user_id is null then
      raise_application_error(-20016, dm_util.get_err_msg(-20016, 'p_user_id'));
    end if;
    if p_ord_status_id is null then
      raise_application_error(-20016, dm_util.get_err_msg(-20016, 'p_ord_status_id'));
    end if;
    if p_date is null then
      raise_application_error(-20016, dm_util.get_err_msg(-20016, 'p_date'));
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
        raise_application_error(-20011, dm_util.get_err_msg(-20011, p_object_id));
    end;
    
    if l_old_ord_status_id = 8 then  -- order closed => edition impossible
      raise_application_error(-20017, dm_util.get_err_msg(-20017, l_old_ord_status_name));
    end if;
    
    if not is_new_ord_status_available(p_ord_status_id, l_old_ord_status_id) then
      select l.name
        into l_new_ord_status_name
        from listtype l
       where l.id = p_ord_status_id;
      raise_application_error(-20018, dm_util.get_err_msg(-20018, l_old_ord_status_name, l_new_ord_status_name));
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
      raise_application_error(-20013, dm_util.get_err_msg(-20013));
    end if;
    
    begin
      select o.object_type_id
        into l_OBJECT_TYPE_ID
        from objects o
       where o.object_id = p_object_id;
    exception
      when no_data_found then
        raise_application_error(-20011, dm_util.get_err_msg(-20011, p_object_id));
    end; 
    if l_OBJECT_TYPE_ID <> 5 then  /* ORDER */
      raise_application_error(-20012, dm_util.get_err_msg(-20012, 'ORDER'));
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
      raise_application_error(-20014, dm_util.get_err_msg(-20014));
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


