-- db update 2016.01.21  notification.sql

--========================


create or replace package dm_notification is

  --===========================================
  
  procedure notification_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_rownum_first  in number default null,  -- pagination
        p_rownum_last   in number default null   -- pagination
        );
        
  procedure notification_insert(
        p_object_id     in out  number,
        p_order_id      in      number,
        p_first_date    in      date,
        p_second_date   in      date,
        p_third_date    in      date);
      
  procedure notification_update(
        p_object_id     in number,
        --p_order_id      in number,
        p_first_date    in date,
        p_second_date   in date,
        p_third_date    in date);
  
  procedure notification_delete(p_object_id in number);
  
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

--=====================================


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
             row_number() over (order by NOTIF.OBJECT_ID) rn,
             NOTIF.OBJECT_ID AS NOTIFICATION_ID,
             ORD_REF.REFERENCE AS ORDER_ID,
             ATTR_DATE_1.DATE_VALUE AS FIRST_DATE,
             ATTR_DATE_2.DATE_VALUE AS SECOND_DATE,
             ATTR_DATE_3.DATE_VALUE AS THIRD_DATE
          FROM OBJECTS NOTIF
          JOIN OBJREFERENCE ORD_REF ON ORD_REF.OBJECT_ID = NOTIF.OBJECT_ID AND ORD_REF.ATTR_ID = 25 /*ORDER*/
          
          LEFT JOIN ATTRIBUTES ATTR_DATE_1 ON NOTIF.OBJECT_ID = ATTR_DATE_1.OBJECT_ID AND ATTR_DATE_1.ATTR_ID = 20 /* FIRST_DATE */
          LEFT JOIN ATTRIBUTES ATTR_DATE_2 ON NOTIF.OBJECT_ID = ATTR_DATE_2.OBJECT_ID AND ATTR_DATE_2.ATTR_ID = 21 /* SECOND_DATE */
          LEFT JOIN ATTRIBUTES ATTR_DATE_3 ON NOTIF.OBJECT_ID = ATTR_DATE_3.OBJECT_ID AND ATTR_DATE_3.ATTR_ID = 22 /* THIRD_DATE */
          
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
  
  procedure notification_update(
        p_object_id     in number,
        --p_order_id      in number,
        p_first_date    in date,
        p_second_date   in date,
        p_third_date    in date)
  is
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Ќе указан id записи дл€ редактировани€.');
    end if;
    
    merge into attributes a
    USING(
      select p_object_id as object_id, 20 attr_id /* FIRST_NOTIFICATION */, null as value, p_first_date as date_value from dual union all
      select p_object_id as object_id, 21 attr_id /* SECOND_NOTIFICATION */, null as value, p_second_date as date_value from dual union all
      select p_object_id as object_id, 22 attr_id /* THIRD_NOTIFICATION */, null as value, p_third_date as date_value from dual
    ) t on (a.object_id = t.object_id and a.attr_id = t.attr_id)
    when matched then update set a.value = t.value, a.date_value = t.date_value;
      
    -- ссылку на ордер редактировать нельз€
    
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Ќе найдена запись дл€ редактировани€.');
    end if;
  end;
  
  
  ------
   
  procedure notification_delete(p_object_id in number)
  is
    l_OBJECT_TYPE_ID  number;
  begin
    select o.object_type_id
      into l_OBJECT_TYPE_ID
      from objects o
     where o.object_id = p_object_id;
     
    if l_OBJECT_TYPE_ID <> 8 then  /* NOTIFICATION */
      raise_application_error(-20001, '«апись не соответствувет типу NOTIFICATION.');
    end if;
  
    delete from attributes
     where object_id = p_object_id;
     
    delete from objects
     where object_id = p_object_id;
     
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Ќе найдена запись дл€ удалени€.');
    end if;
    
    -- +++
    delete from OBJREFERENCE
     where object_id = p_object_id or reference = p_object_id;    
  
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
                             --,LIST_ORD_STATUS.NAME   AS ORD_STATUS_NAME
                     
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
                          --LEFT JOIN LISTTYPE LIST_ORD_STATUS ON ATTR_ORD_STATUS.VALUE = LIST_ORD_STATUS.ID
                     
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
  -- если у ордера есть нотификаци€, отредактировать ее даты
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

