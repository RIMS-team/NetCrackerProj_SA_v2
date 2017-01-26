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