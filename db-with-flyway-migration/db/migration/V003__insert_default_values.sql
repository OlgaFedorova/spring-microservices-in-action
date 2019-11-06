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
        VALUES ('f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a', 'e254f8c-c442-4ebe-a82a-e2fc1d1ff78a', 'user','customer-crm-co', 100,5);
        INSERT INTO license.licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
        VALUES ('t9876f8c-c338-4abc-zf6a-ttt1', 'e254f8c-c442-4ebe-a82a-e2fc1d1ff78a', 'user','suitability-plus', 200,189);
        INSERT INTO license.licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
        VALUES ('38777179-7094-4200-9d61-edb101c6ea84', '442adb6e-fa58-47f3-9ca2-ed1fecdfe86c', 'user','HR-PowerSuite', 100,4);
        INSERT INTO license.licenses (license_id,  organization_id, license_type, product_name, license_max, license_allocated)
        VALUES ('08dbe05-606e-4dad-9d33-90ef10e334f9', '442adb6e-fa58-47f3-9ca2-ed1fecdfe86c', 'core-prod','WildCat Application Gateway', 16,16);

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