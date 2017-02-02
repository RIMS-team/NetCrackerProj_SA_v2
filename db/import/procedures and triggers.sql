-- procedure
create or replace procedure sdb_data_processing(user_id in number, logs out varchar2) as
  v_notebook_id number(30);
  v_inv_status_id number(30);
  v_employee_id number(30);
  v_null varchar2(5) := null;
  v_splitter varchar2(5) := '|';
begin -- begin sdb_data_processing
  logs := '';
  FOR item IN (
    SELECT Name, In_Use_by,	Inv_Status,	Location,	Memory_Type,
                Model,	Employee,	Inventory_Num,	Serial_Number
        FROM sdb_orders WHERE Is_Valid = 1
  )
  LOOP
    SELECT ID INTO v_inv_status_id FROM LISTTYPE WHERE ATTRTYPE_CODE = 'INV_STATUS' AND CODE = item.inv_status;
    BEGIN -- insert or update notebook
      SELECT NOTE.OBJECT_ID INTO v_notebook_id
          FROM OBJECTS NOTE, ATTRIBUTES INVENTORY_NUM_ATTR
         WHERE NOTE.OBJECT_ID = INVENTORY_NUM_ATTR.OBJECT_ID
           AND INVENTORY_NUM_ATTR.ATTR_ID = 13/*INVENTORY_NUM*/
           AND INVENTORY_NUM_ATTR.VALUE = item.INVENTORY_NUM;
      DM_NOTEBOOK.NOTEBOOK_UPDATE(v_notebook_id, item.name, item.location, item.memory_type,
            item.model, item.inventory_num, item.serial_number, v_inv_status_id);
      logs := logs || 'Updated notebook with inventory number = ' || item.inventory_num || v_splitter;
    EXCEPTION -- if not found then insert
      WHEN no_data_found THEN
        v_notebook_id := null;
        DM_NOTEBOOK.notebook_insert(v_notebook_id, item.name, item.location,
        item.memory_type, item.model, item.inventory_num,
        item.serial_number, v_inv_status_id);
        logs := logs || 'Added notebook with inventory number = ' || item.inventory_num || v_splitter;
    END; -- insert or update notebook
    BEGIN -- if employee is not null
      IF item.inv_status = 'IN_USE' THEN
        SELECT EMP.OBJECT_ID INTO v_employee_id
            FROM OBJECTS EMP, ATTRIBUTES FNAME_ATTR
           WHERE EMP.OBJECT_ID = FNAME_ATTR.OBJECT_ID
             AND FNAME_ATTR.ATTR_ID = 1/*FULL_NAME*/
             AND FNAME_ATTR.VALUE = item.Employee;
      dm_order.order_insert(v_null, v_notebook_id, v_employee_id,
        user_id, v_null, TO_DATE(item.In_Use_by, 'dd/mm/yyyy'));
      END IF;
      EXCEPTION -- if not found
      WHEN no_data_found THEN
        logs := logs || 'Unable to create order for ' || item.Employee || ' - employee does not exists' || v_splitter;
    END; -- if employee is not null
  END LOOP;
  BEGIN
    execute immediate 'truncate table sdb_orders';
  END;
end; -- end sdb_data_processing
/

-- trigger
CREATE OR REPLACE TRIGGER sdb_orders_validation 
	BEFORE INSERT ON sdb_orders
FOR EACH ROW
DECLARE
BEGIN
  :NEW.inv_status := UPPER(REGEXP_REPLACE(:NEW.inv_status, '\s', '_'));
	IF :NEW.inv_status = 'IN_USE' AND (:NEW.employee is null OR :NEW.IN_USE_BY is null)THEN 
		:NEW.Is_Valid := 0;
	END IF;
  IF :NEW.name is null OR :NEW.location is null OR :NEW.MODEL is null OR :NEW.inventory_num is null THEN 
		:NEW.Is_Valid := 0;
	END IF;
END;
/

-- procedure
create or replace procedure sdb_emp_data_processing(user_id in number default 1, logs out varchar2) as
  v_employee_id number(30);
  v_null varchar(30) := null;
  v_splitter varchar2(5) := '|';
begin -- begin sdb_data_processing
  logs := '';
  FOR item IN (
    SELECT Name, Email
        FROM sdb_users WHERE Is_Valid = 1
  )
  LOOP
    BEGIN -- insert or update notebook
      SELECT EMP.OBJECT_ID INTO v_employee_id
          FROM OBJECTS EMP, ATTRIBUTES FNAME_ATTR
         WHERE EMP.OBJECT_TYPE_ID = 1 /* EMPLOYEE */
           AND EMP.OBJECT_ID = FNAME_ATTR.OBJECT_ID
           AND FNAME_ATTR.ATTR_ID = 1 /* FULL_NAME */
           AND FNAME_ATTR.VALUE = item.NAME;
      DM_EMPLOYEE.EMPLOYEE_UPDATE(v_employee_id, ITEM.NAME, v_null, item.email);
      logs := logs || 'Updated employee ' || item.name || v_splitter;
    EXCEPTION -- if not found then insert
      WHEN no_data_found THEN
        v_employee_id := null;
        DM_EMPLOYEE.EMPLOYEE_insert(v_employee_id, item.name, v_null, item.email);
        logs := logs || 'Added employee ' || item.name || v_splitter;
    END; -- insert or update notebook
  END LOOP;
  BEGIN
    execute immediate 'truncate table sdb_users';
  END;
end; -- end sdb_data_processing
/

-- trigger
CREATE OR REPLACE TRIGGER sdb_users_validation 
	BEFORE INSERT ON sdb_users
FOR EACH ROW
DECLARE
BEGIN
	IF :NEW.name IS NULL OR :NEW.email is null OR NOT regexp_like(:NEW.email, '^[A-Z0-9._%-]+@netcracker.com$') THEN 
		:NEW.Is_Valid := 0;
	END IF;
END;
/