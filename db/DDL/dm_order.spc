CREATE OR REPLACE package dm_order is

  --===========================================
  
  procedure order_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_ord_status_id in number default null,   -- filter by status
        p_rownum_first  in number default null,
        p_rownum_last   in number default null
        );
        
  procedure order_insert(
        p_object_id     in out  number,
        p_inventory_id  in      number,
        p_employee_id   in      number,
        p_user_id       in      number,
        p_ord_status_id in      number,
        p_date          in      date);
        
  procedure order_update(
        p_object_id     in number,
        p_inventory_id  in number,
        p_employee_id   in number,
        p_user_id       in number,
        p_ord_status_id in number,
        p_date          in date);
  
  procedure order_delete(p_object_id in number);
  
  --===========================================

end dm_order;


/
