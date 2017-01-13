CREATE OR REPLACE package dm_user is

  --===========================================
  
  procedure user_select(
        p_object_id   in number,
        p_out_cursor out sys_refcursor);
        
  procedure user_insert(
        p_object_id     in out  number,
        p_full_name     in      varchar2,
        p_phone_number  in      varchar2,
        p_email         in      varchar2,
        p_password      in      varchar2);
        
  procedure user_update(
        p_object_id     in number,
        p_full_name     in varchar2,
        p_phone_number  in varchar2,
        p_email         in varchar2,
        p_password      in varchar2);
        
  procedure user_delete(p_object_id in number);
  
  --===========================================

end dm_user;


/
