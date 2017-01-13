CREATE OR REPLACE package dm_access_card is

  --===========================================
  
  procedure access_card_select(
        p_object_id     in number,
        p_out_cursor    out sys_refcursor,
        p_inv_status_id in number default null,   -- filter by status
        p_rownum_first  in number default null,
        p_rownum_last   in number default null
        );
        
   procedure access_card_insert(
        p_object_id     in out  number,
        p_inventory_num in      varchar2,
        p_inv_status_id in      varchar2);
        
  procedure access_card_update(
        p_object_id     in number,
        p_inventory_num in varchar2,
        p_inv_status_id in varchar2);
  
  procedure access_card_delete(p_object_id in number);
  
  --===========================================

end dm_access_card;


/
