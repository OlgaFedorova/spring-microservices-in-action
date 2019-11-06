DO $$
DECLARE
  lScriptName    VARCHAR := 'to create schema license';
  lScriptVersion VARCHAR := '1';
  lErrorStack    TEXT;
  lErrorState    TEXT;
  lErrorMsg      TEXT;
  lErrorDetail   TEXT;
  lErrorHint     TEXT;
BEGIN
    BEGIN
        RAISE NOTICE 'Start % ...', lScriptName;

        CREATE SCHEMA IF NOT EXISTS license;

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