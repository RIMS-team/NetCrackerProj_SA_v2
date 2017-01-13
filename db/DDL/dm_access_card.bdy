CREATE OR REPLACE package body dm_access_card is

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
                row_number() over (order by CARD.OBJECT_ID) rn,
                CARD.OBJECT_ID AS OBJECT_ID
               ,ATTR_INVENTORY_NUM.VALUE AS INVENTORY_NUM
               ,ATTR_STATUS.VALUE  AS INV_STATUS_ID
               ,LIST_STATUS.NAME   AS INV_STATUS_NAME
          FROM OBJECTS CARD, ATTRIBUTES ATTR_INVENTORY_NUM, ATTRIBUTES ATTR_STATUS, LISTTYPE LIST_STATUS
         WHERE CARD.OBJECT_TYPE_ID = 6 /* CARD */
           AND CARD.OBJECT_ID = ATTR_INVENTORY_NUM.OBJECT_ID
           AND CARD.OBJECT_ID = ATTR_STATUS.OBJECT_ID
           AND ATTR_INVENTORY_NUM.ATTR_ID = 13 /* INVENTORY_NUM */
           AND ATTR_STATUS.ATTR_ID = 16 /* STATUS */
           AND ATTR_STATUS.VALUE = LIST_STATUS.ID
           AND (p_object_id is null or CARD.OBJECT_ID = p_object_id)
           AND (p_inv_status_id is null or ATTR_STATUS.VALUE = p_inv_status_id)
      )
      where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last);
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
  procedure access_card_update(
        p_object_id     in number,
        p_inventory_num in varchar2,
        p_inv_status_id in varchar2) as
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
    end if;
    
    merge into attributes a
    USING(
      select p_object_id as object_id, 13 attr_id /* INVENTORY_NUM */, p_inventory_num as value from dual union all
      select p_object_id as object_id, 16 attr_id /* STATUS */, p_inv_status_id as value from dual 
    ) t on (a.object_id = t.object_id and a.attr_id = t.attr_id)
    when matched then update set a.value = t.value;
      
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Не найдена запись для редактирования.');
    end if;
  end;
  ----
  procedure access_card_delete(p_object_id in number) as
    l_OBJECT_TYPE_ID  number;
  begin
    select o.object_type_id
      into l_OBJECT_TYPE_ID
      from objects o
     where o.object_id = p_object_id;
     
    if l_OBJECT_TYPE_ID <> 6 then
      raise_application_error(-20001, 'Запись не соответствувет типу ACCESS_CARD.');
    end if;
  
    delete from attributes
     where object_id = p_object_id;
     
    delete from objects
     where object_id = p_object_id;
     
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Не найдена запись для удаления.');
    end if;
  end;
  
--==========================================================

begin
  -- Initialization
  null;
end dm_access_card;


/
