CREATE OR REPLACE package dm_inv_status is

  --======================================
  
  procedure inv_status_select(
        p_id          in number,
        p_out_cursor  out sys_refcursor);  
        
  procedure inv_status_insert(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2);
        
  procedure inv_status_update(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2);
        
  procedure inv_status_delete(p_id in number);
  
  --==========================================

end dm_inv_status;


/
