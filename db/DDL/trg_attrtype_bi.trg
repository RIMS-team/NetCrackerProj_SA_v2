
  CREATE OR REPLACE TRIGGER "TRG_ATTRTYPE_BI" 
  before insert
  on ATTRTYPE
  for each row
declare
  --
begin
  if :new.attr_id is null then
    :new.attr_id := SEQ_ATTRTYPE.NEXTVAL;
  end if;
end TRG_ATTRTYPE_BI;

/
