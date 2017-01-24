-- db update 2016.01.20  notif_templ.sql

--========================


-- Create table
create table NOTIF_TEMPL
(
  notif_num NUMBER not null,
  user_id   NUMBER,
  template  VARCHAR2(4000)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index FK_NOTIF_TEMPL_R_OBJ_USER_ID on NOTIF_TEMPL (USER_ID)
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table NOTIF_TEMPL
  add constraint UK_NOTIF_TEMPL_NUM_USER unique (NOTIF_NUM, USER_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

alter table NOTIF_TEMPL
  add constraint FK_NOTIF_TEMPL_R_OBJ_USER_ID foreign key (USER_ID)
  references OBJECTS (OBJECT_ID);


--========================


create or replace package dm_notif_templ is

  function notif_templ_get(
        p_notif_num   in number default 1,
        p_user_id     in number) return varchar2;  --clob;
        
  procedure notif_templ_set(
        p_notif_num   in number,
        p_user_id     in number,
        p_template    in varchar2  --clob
        --,p_out_cursor out sys_refcursor   -- for debug
        );
        

        
  procedure notif_templ_delete(
        p_notif_num   in number,
        p_user_id     in number);

end dm_notif_templ;


/



--===========================================


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
        p_user_id     in number) return varchar2  --clob
  is
    l_template  clob;
  begin
    begin
      select nt.template
        into l_template
        from notif_templ nt
        left join objects o on o.object_id = nt.user_id
       where nt.notif_num = p_notif_num
         and (p_user_id is null and user_id is null or nt.user_id = p_user_id)
         and rownum = 1;
    exception
      when no_data_found then
        l_template := null;
    end;
    
    return l_template;
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





