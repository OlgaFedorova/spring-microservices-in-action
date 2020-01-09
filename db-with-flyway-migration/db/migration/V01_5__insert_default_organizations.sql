DO $$
DECLARE
  lScriptName    VARCHAR := 'to insert default values for organizations';
  lScriptVersion VARCHAR := '5';
  lErrorStack    TEXT;
  lErrorState    TEXT;
  lErrorMsg      TEXT;
  lErrorDetail   TEXT;
  lErrorHint     TEXT;
BEGIN
    BEGIN
        RAISE NOTICE 'Start % ...', lScriptName;

        INSERT INTO organization.organizations (organization_id, name, contact_name, contact_email, contact_phone)
        VALUES ('0874d3a0-4a32-4a34-8ecf-3c4482e6be13', 'customer-crm-co', 'Mark Balster', 'mark.balster@custcrmco.com', '823-555-1212');

        INSERT INTO organization.organizations (organization_id, name, contact_name, contact_email, contact_phone)
        VALUES ('a8883ce2-1d08-47b9-803f-3b787f49189b', 'HR-PowerSuite', 'Doug Drewry','doug.drewry@hr.com', '920-555-1212');

        RAISE NOTICE 'Process % is finished', lScriptName;

    EXCEPTION
        WHEN OTHERS
            THEN
                GET STACKED DIAGNOSTICS
                    lErrorState = RETURNED_SQLSTATE,
                    lErrorMsg = MESSAGE_TEXT,
                    lErrorDetail = PG_EXCEPTION_DETAIL,
                    lErrorHint = PG_EXCEPTION_HINT,
                    lErrorStack = PG_EXCEPTION_CONTEXT;
                RAISE EXCEPTION ' in script "%" when execute code.
  code       : %
  message : %
  description  : %
  hint : %
  context  : %', lScriptVersion, lErrorState, lErrorMsg, lErrorDetail, lErrorHint, lErrorStack;
    END;
END $$