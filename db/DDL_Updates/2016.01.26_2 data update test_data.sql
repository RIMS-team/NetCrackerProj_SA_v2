-- clear db inventory data
-- and write new data

begin

  dm_test_data.drop_all_objects;

  dm_test_data.add_objects(p_count_employees => 100,
                           p_count_access_cards => 200,
                           p_count_notebooks => 50,
                           p_count_orders_access_cards => 400,
                           p_count_orders_notebooks => 80,
                           p_count_notifications => 0);
end;



begin
  -- Call the procedure
  dm_access_card.access_card_select_ext(p_object_id => :p_object_id,
                                        p_out_cursor => :p_out_cursor,
                                        p_inv_status_id => :p_inv_status_id,
                                        p_rownum_first => :p_rownum_first,
                                        p_rownum_last => :p_rownum_last);
end;
begin
  -- Call the procedure
  dm_notebook.notebook_select_ext(p_object_id => :p_object_id,
                                  p_out_cursor => :p_out_cursor,
                                  p_inv_status_id => :p_inv_status_id,
                                  p_name_mask => :p_name_mask,
                                  p_location_mask => :p_location_mask,
                                  p_rownum_first => :p_rownum_first,
                                  p_rownum_last => :p_rownum_last);
end;