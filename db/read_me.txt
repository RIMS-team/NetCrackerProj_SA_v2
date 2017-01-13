DDL - папка скриптов схемы
DATA - папка скриптов метаинформации к схеме

1. создать схему под SYS ( login = kurator2   password = 12345 )
2. войти в нее. 
3. Запустить скрипт \db\ddl\===PLSQL_Developer_Import_DDL.sql  для создания объектов схемы.
   ВНИМАНИЕ - можете либо руками корректировать пути к файлам команд *.SQL в файлах 
      ===PLSQL_Developer_Import_DDL.sql  и  __ALL_DDL.sql
      либо запустить SQL_DDL_PREPARE.bat, который сделает все автоматически
   Если в процессе отработки скриптов возникнут ошибки создания системных уникальных индексов - это нормально. 
4. Запустить скрипт \db\data\===PLSQL_Developer_Import_Data.sql  для внесения записей метаданных в 
   таблицы OBJTYPE, ATTRTYPE, LISTTYPE, а также одного предопределенного админа и 2-х юзеров.
   ВНИМАНИЕ - можете либо руками корректировать пути к файлам команд *.SQL в файле 
      ===PLSQL_Developer_Import_DDL.sql 
      либо запустить SQL_DATA_PREPARE.bat, который сделает все автоматически
5. Заполнить таблицы тестовыми объектами
   Выполнить из пакета DM_TEST_DATA хранимку
   
   begin
     -- Call the procedure
     dm_test_data.add_objects(100,  -- p_count_employees
                              200,  -- p_count_access_cards
                              55,   -- p_count_notebooks
                              500   -- p_count_orders
     );
     COMMIT;
   end;

   ПРИМЕЧАНИЕ: Для очистки схемы от тестовых объектов выполнить

   begin
     -- Call the procedure
     dm_test_data.drop_all_objects;      -- не удаляет юзеров и админов
   end;

