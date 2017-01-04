CREATE OR REPLACE package body dm_ord_status is

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
  procedure ord_status_delete(p_id in number) as
  begin
    delete from listtype
     where id = p_id
       and attrtype_code = 'ORD_STATUS';
     
    if SQL%ROWCOUNT = 0 then
      raise_application_error(-20001, 'Не найдена запись для удаления.');
    end if;
  end;
  
--=========================================


begin
  -- Initialization
  null;
end dm_ord_status;


/
