alter table ATTRIBUTES disable constraint CON_AATTR_ID;
alter table ATTRIBUTES disable constraint CON_AOBJECT_ID;
alter table ATTRTYPE disable constraint CON_ATTR_OBJECT_TYPE_ID;
alter table ATTRTYPE disable constraint CON_ATTR_OBJECT_TYPE_ID_REF;
alter table OBJECTS disable constraint CON_OBJ_TYPE_ID;
alter table OBJECTS disable constraint CON_PARENTS_ID;
alter table OBJTYPE disable constraint CON_PARENT_ID;
