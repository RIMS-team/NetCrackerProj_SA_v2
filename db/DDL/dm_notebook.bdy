CREATE OR REPLACE package body dm_notebook is

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
          FROM OBJECTS NOTE, ATTRIBUTES NAME_ATTR, ATTRIBUTES LOC_ATTR, ATTRIBUTES MEM_ATTR,
               ATTRIBUTES MODEL_ATTR, ATTRIBUTES INVENTORY_NUM_ATTR, ATTRIBUTES SER_ATTR, ATTRIBUTES STATUS_ATTR, LISTTYPE LIST_STATUS
         WHERE NOTE.OBJECT_TYPE_ID = 7
           AND NOTE.OBJECT_ID = NAME_ATTR.OBJECT_ID
           AND NOTE.OBJECT_ID = LOC_ATTR.OBJECT_ID
           AND NOTE.OBJECT_ID = MEM_ATTR.OBJECT_ID
           AND NOTE.OBJECT_ID = MODEL_ATTR.OBJECT_ID
           AND NOTE.OBJECT_ID = INVENTORY_NUM_ATTR.OBJECT_ID
           AND NOTE.OBJECT_ID = SER_ATTR.OBJECT_ID
           AND NOTE.OBJECT_ID = STATUS_ATTR.OBJECT_ID
           AND NAME_ATTR.ATTR_ID = 9/*NAME*/
           AND LOC_ATTR.ATTR_ID = 10/*LOCATION*/
           AND MEM_ATTR.ATTR_ID = 11/*MEMORY_TYPE*/
           AND MODEL_ATTR.ATTR_ID = 12/*MODEL*/
           AND INVENTORY_NUM_ATTR.ATTR_ID = 13/*INVENTORY_NUM*/
           AND SER_ATTR.ATTR_ID = 14/*SERIAL_NUMBER*/
           AND STATUS_ATTR.ATTR_ID = 16/*STATUS*/
           AND STATUS_ATTR.VALUE = LIST_STATUS.ID
           AND (p_object_id is null or NOTE.OBJECT_ID = p_object_id)
           AND (p_inv_status_id is null or STATUS_ATTR.VALUE = p_inv_status_id)
           AND (p_name_mask is null or (length(p_name_mask) > 2 and NAME_ATTR.VALUE like '%' || p_name_mask || '%'))
           AND (p_location_mask is null or (length(p_name_mask) > 2 and LOC_ATTR.VALUE like '%' || p_location_mask || '%'))
      )
      where  p_rownum_last is null or (rn between l_rownum_first AND p_rownum_last);
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
  procedure notebook_update(
        p_object_id     in number,
        p_name          in varchar2,
        p_location      in  varchar2,
        p_memory_type   in varchar2,
        p_model         in varchar2,
        p_inventory_num in varchar2,
        p_serial_number in varchar2,
        p_inv_status_id in varchar2) as
  begin
    if p_object_id is null then
      raise_application_error(-20001, 'Не указан id записи для редактирования.');
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
      
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Не найдена запись для редактирования.');
    end if;
  end;
  ----
  procedure notebook_delete(p_object_id in number) as
    l_OBJECT_TYPE_ID  number;
  begin
    select o.object_type_id
      into l_OBJECT_TYPE_ID
      from objects o
     where o.object_id = p_object_id;
     
    if l_OBJECT_TYPE_ID <> 7 then
      raise_application_error(-20001, 'Запись не соответствувет типу NOTEBOOK.');
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
end dm_notebook;


/
