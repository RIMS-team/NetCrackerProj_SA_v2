--2016.01.28 db update param.sql

--param


-- Create table
create table PARAM
(
  param_group VARCHAR2(100) not null,
  param_code  VARCHAR2(100) not null,
  param_value VARCHAR2(1000) not null
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table PARAM
  add constraint UNIQ_PARAM_GROUP_CODE unique (PARAM_GROUP, PARAM_CODE)
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


--======================================================


insert into PARAM (PARAM_GROUP, PARAM_CODE, PARAM_VALUE)
values ('email', 'mail.smtp.host', 'smtp.yandex.ru');

insert into PARAM (PARAM_GROUP, PARAM_CODE, PARAM_VALUE)
values ('email', 'mail.smtp.socketFactory.port', '465');

insert into PARAM (PARAM_GROUP, PARAM_CODE, PARAM_VALUE)
values ('email', 'mail.smtp.socketFactory.class', 'javax.net.ssl.SSLSocketFactory');

insert into PARAM (PARAM_GROUP, PARAM_CODE, PARAM_VALUE)
values ('email', 'mail.smtp.auth', 'true');

insert into PARAM (PARAM_GROUP, PARAM_CODE, PARAM_VALUE)
values ('email', 'mail.smtp.port', '465');

insert into PARAM (PARAM_GROUP, PARAM_CODE, PARAM_VALUE)
values ('email', 'from', 'v.karpov2018@yandex.ru');

commit;


--==============================================

create or replace package dm_param is
----
procedure get_parameters(
      p_group  in varchar2,
      p_out_cursor out sys_refcursor);
----      
procedure get_parameter(
      p_group  in varchar2,
      p_code   in varchar2,
      p_value  out varchar2);
----      
procedure set_parameter(
      p_group  in varchar2,
      p_code   in varchar2,
      p_value  in varchar2);
----      
procedure delete_parameter(
      p_group  in varchar2,
      p_code   in varchar2);      
----
end dm_param;


/


create or replace package body dm_param is
----
procedure get_parameters(
      p_group  in varchar2,
      p_out_cursor out sys_refcursor)
as
begin
  open p_out_cursor for
    select p.param_group, p.param_code, p.param_value
      from param p
     where (p_group is null or p.param_group = p_group);
end;
----
procedure get_parameter(
      p_group  in varchar2,
      p_code   in varchar2,
      p_value  out varchar2)
as
begin
  begin
    select p.param_value
      into p_value
      from param p
     where p.param_group = p_group and p.param_code = p_code;
  exception
    when others then
      p_value := '';
  end;
end;
----
procedure set_parameter(
      p_group  in varchar2,
      p_code   in varchar2,
      p_value  in varchar2) is
begin
  merge into param p using dual 
      on (p.param_group = p_group and p.param_code = p_code)
      when not matched then insert (param_group, param_code, param_value) values (p_group, p_code, p_value)
      when matched then update set param_value = p_value;
end;
----
procedure delete_parameter(
      p_group  in varchar2,
      p_code   in varchar2) as
begin
  delete from param
   where param_group = p_group and param_code = p_code;
end;
----


begin
  -- Initialization
  null;
end dm_param;

/

