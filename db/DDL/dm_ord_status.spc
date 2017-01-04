CREATE OR REPLACE package dm_ord_status is

  --=====================================
  procedure ord_status_select(
        p_id          in number,
        p_out_cursor out sys_refcursor);
        
  procedure ord_status_insert(
        p_id             in out  number,
        p_attrtype_code     out  varchar2,
        p_code           in      varchar2,
        p_name           in      varchar2,
        p_comments       in      varchar2);
        
  procedure ord_status_update(
        p_id             in     number,
        p_code           in out varchar2,
        p_name           in out varchar2,
        p_comments       in out varchar2);
        
  procedure ord_status_delete(p_id in number);
        
  --======================================

end dm_ord_status;


/
