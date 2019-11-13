DO $$
DECLARE
  lScriptName    VARCHAR := 'to create table organizations';
  lScriptVersion VARCHAR := '4';
  lErrorStack    TEXT;
  lErrorState    TEXT;
  lErrorMsg      TEXT;
  lErrorDetail   TEXT;
  lErrorHint     TEXT;
BEGIN
    BEGIN
        RAISE NOTICE 'Start % ...', lScriptName;

        CREATE SCHEMA IF NOT EXISTS organization;

        CREATE TABLE organization.organizations (
                                  organization_id        uuid PRIMARY KEY NOT NULL,
                                  name                   TEXT NOT NULL,
                                  contact_name           TEXT NOT NULL,
                                  contact_email          TEXT NOT NULL,
                                  contact_phone          TEXT   NOT NULL);

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