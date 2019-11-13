package ofedorova.organizations.repository;

import ofedorova.organizations.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, UUID> {

  Optional<Organization> findById(UUID organizationId);
}
