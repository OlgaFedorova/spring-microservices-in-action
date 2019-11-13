package ofedorova.organizations.services;

import ofedorova.organizations.model.Organization;
import ofedorova.organizations.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

  @Autowired
  private OrganizationRepository orgRepository;

  public Optional<Organization> findById(UUID organizationId) {
    return orgRepository.findById(organizationId);
  }

  public void save(Organization org){
    org.setId(UUID.randomUUID());
    orgRepository.save(org);

  }

  public void update(Organization org){
    orgRepository.save(org);
  }

  public void delete(Organization org){
    orgRepository.deleteById(org.getId());
  }
}
