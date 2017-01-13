CREATE OR REPLACE package dm_test_data is

  procedure drop_all_objects_by_type(p_obj_type_id in number);
  
  procedure drop_all_objects;
  
  ----
  
  procedure add_objects(
        p_count_employees    in number default 100,
        p_count_access_cards in number default 200,
        p_count_notebooks    in number default 55,
        p_count_orders       in number default 500
        );
  
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
