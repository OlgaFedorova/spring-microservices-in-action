DO $$
DECLARE
  lScriptName    VARCHAR := 'to create table licenses';
  lScriptVersion VARCHAR := '2';
  lErrorStack    TEXT;
  lErrorState    TEXT;
  lErrorMsg      TEXT;
  lErrorDetail   TEXT;
  lErrorHint     TEXT;
BEGIN
    BEGIN
        RAISE NOTICE 'Start % ...', lScriptName;

        CREATE TABLE license.licenses (
                                  license_id        uuid PRIMARY KEY NOT NULL,
                                  organization_id   uuid NOT NULL,
                                  license_type      TEXT NOT NULL,
                                  product_name      TEXT NOT NULL,
                                  license_max       INT   NOT NULL,
                                  license_allocated INT,
                                  comment           VARCHAR(100));

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