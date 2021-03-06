package ofedorova.licenses.repository;

import ofedorova.licenses.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LicenseRepository extends CrudRepository<License, UUID> {

  List<License> findByOrganizationId(UUID organizationId);

  License findByOrganizationIdAndLicenseId(UUID organizationId, UUID licenseId);
}
