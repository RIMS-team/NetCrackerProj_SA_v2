DDL - ����� �������� �����
DATA - ����� �������� �������������� � �����

1. ������� ����� ��� SYS ( login = kurator2   password = 12345 )
2. ����� � ���. 
3. ��������� ������ \db\ddl\===PLSQL_Developer_Import_DDL.sql  ��� �������� �������� �����.
   �������� - ������ ���� ������ �������������� ���� � ������ ������ *.SQL � ������ 
      ===PLSQL_Developer_Import_DDL.sql  �  __ALL_DDL.sql
      ���� ��������� SQL_DDL_PREPARE.bat, ������� ������� ��� �������������
   ���� � �������� ��������� �������� ��������� ������ �������� ��������� ���������� �������� - ��� ���������. 
4. ��������� ������ \db\data\===PLSQL_Developer_Import_Data.sql  ��� �������� ������� ���������� � 
   ������� OBJTYPE, ATTRTYPE, LISTTYPE, � ����� ������ ����������������� ������ � 2-� ������.
   �������� - ������ ���� ������ �������������� ���� � ������ ������ *.SQL � ����� 
      ===PLSQL_Developer_Import_DDL.sql 
      ���� ��������� SQL_DATA_PREPARE.bat, ������� ������� ��� �������������
5. ��������� ������� ��������� ���������
   ��������� �� ������ DM_TEST_DATA ��������
   
   begin
     -- Call the procedure
     dm_test_data.add_objects(100,  -- p_count_employees
                              200,  -- p_count_access_cards
                              55,   -- p_count_notebooks
                              500   -- p_count_orders
     );
     COMMIT;
   end;

   ����������: ��� ������� ����� �� �������� �������� ���������

   begin
     -- Call the procedure
     dm_test_data.drop_all_objects;      -- �� ������� ������ � �������
   end;

