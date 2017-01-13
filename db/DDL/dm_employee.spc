CREATE OR REPLACE package dm_employee is

  --==========================================
  
  procedure employee_select(
        p_object_id      in number,
        p_out_cursor     out sys_refcursor,
        p_full_name_mask in varchar2 default null,
        p_rownum_first   in number default null,
        p_rownum_last    in number default null);
        
  procedure employee_insert(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2);
        
  procedure employee_update(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2);
  
  procedure employee_delete(p_object_id in number);
  
  --===========================================


end dm_employee;


/
