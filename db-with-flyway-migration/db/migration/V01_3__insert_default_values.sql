DO $$
DECLARE
  lScriptName    VARCHAR := 'to insert default values';
  lScriptVersion VARCHAR := '3';
  lErrorStack    TEXT;
  lErrorState    TEXT;
  lErrorMsg      TEXT;
  lErrorDetail   TEXT;
  lErrorHint     TEXT;
BEGIN
    BEGIN
        RAISE NOTICE 'Start % ...', lScriptName;

        INSERT INTO license.licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
        VALUES ('8acc73fe-3abe-48f4-982d-830790a2e62f', '0874d3a0-4a32-4a34-8ecf-3c4482e6be13', 'user','customer-crm-co', 100,5);
        INSERT INTO license.licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
        VALUES ('edb0857b-eaf2-4a99-bf95-e651f53885a1', '0874d3a0-4a32-4a34-8ecf-3c4482e6be13', 'user','suitability-plus', 200,189);
        INSERT INTO license.licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
        VALUES ('726f9d97-1d9d-4082-a539-2068d95a004a', 'a8883ce2-1d08-47b9-803f-3b787f49189b', 'user','HR-PowerSuite', 100,4);
        INSERT INTO license.licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
        VALUES ('4a2cf98c-0c9f-45b7-a3b2-785b08d2c049', 'a8883ce2-1d08-47b9-803f-3b787f49189b', 'core-prod','WildCat Application Gateway', 16,16);

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