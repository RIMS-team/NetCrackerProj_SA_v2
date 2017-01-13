CREATE OR REPLACE package dm_notebook is

  --===========================================
  
  procedure notebook_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_inv_status_id in number default null,    -- filter by status
        p_name_mask     in varchar2 default null,  -- filter by name
        p_location_mask in varchar2 default null,   -- filter by location
        p_rownum_first  in number default null,
        p_rownum_last   in number default null
        );
  
  procedure notebook_insert(
        p_object_id     in out  number,
        p_name          in      varchar2,
        p_location      in      varchar2,
        p_memory_type   in      varchar2,
        p_model         in      varchar2,
        p_inventory_num in      varchar2,
        p_serial_number in      varchar2,
        p_inv_status_id in      varchar2);
        
  procedure notebook_update(
        p_object_id     in number,
        p_name          in varchar2,
        p_location      in  varchar2,
        p_memory_type   in varchar2,
        p_model         in varchar2,
        p_inventory_num in varchar2,
        p_serial_number in varchar2,
        p_inv_status_id in varchar2);
  
  procedure notebook_delete(p_object_id in number);
  
  --===========================================

end dm_notebook;


/
