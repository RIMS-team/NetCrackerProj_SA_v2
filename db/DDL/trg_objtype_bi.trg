
  CREATE OR REPLACE TRIGGER "TRG_OBJTYPE_BI" 
  before insert
  on OBJTYPE
  for each row
declare
  --
begin
  if :new.object_type_id is null then
    :new.object_type_id := SEQ_OBJTYPE.NEXTVAL;
  end if;
end TRG_OBJTYPE_BI;

/
