CREATE OR REPLACE package dm_test_data is

  procedure drop_all_objects_by_type(p_obj_type_id in number);
  
  procedure drop_all_objects;
  
  ----
  
  procedure add_employees(
        p_count in number
        --,p_out_cursor out sys_refcursor   -- for debug
        );
  ----
  procedure add_access_cards(
        p_count in number
        --,p_out_cursor out sys_refcursor   -- for debug
        );
  ----
  procedure add_notebooks(
        p_count in number
        --,p_out_cursor out sys_refcursor   -- for debug
        );
  ----
  procedure add_orders(
        p_count     in number,
        p_date_from in date,
        p_date_to   in date
        --,p_out_cursor out sys_refcursor   -- for debug
        );

end dm_test_data;


/
