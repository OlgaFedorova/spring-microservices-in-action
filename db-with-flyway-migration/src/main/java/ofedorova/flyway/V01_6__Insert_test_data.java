package ofedorova.flyway;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V01_6__Insert_test_data extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        try (Statement update = context.getConnection().createStatement()) {
            update.execute("INSERT INTO organization.organizations (organization_id, name, contact_name, contact_email, contact_phone)\n" +
                    "        VALUES ('3be5b9aa-91ed-4266-b83f-f09772e77143', 'Test', 'Test','test@hr.com', '111-111-111');");
        }
    }
}
