
  CREATE OR REPLACE TRIGGER "TRG_LISTTYPE_BI" 
  before insert
  on listtype 
  for each row
declare
  
begin
  if :new.id is null then
    :new.id := seq_listtype.nextval;
  end if;
end TRG_LISTTYPE_BI;

/
