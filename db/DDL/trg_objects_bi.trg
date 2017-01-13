
  CREATE OR REPLACE TRIGGER "TRG_OBJECTS_BI" 
  before insert
  on OBJECTS
  for each row
declare
  --
begin
  if :new.object_id is null then
    :new.object_id := SEQ_OBJECTS.NEXTVAL;
  end if;
end TRG_OBJECTS_BI;

/
