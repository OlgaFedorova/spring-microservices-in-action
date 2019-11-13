package ofedorova.licenses.services;

import ofedorova.licenses.clients.OrganizationFeignClient;
import ofedorova.licenses.config.ServiceConfig;
import ofedorova.licenses.model.License;
import ofedorova.licenses.model.Organization;
import ofedorova.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {

  @Autowired
  private LicenseRepository licenseRepository;

  @Autowired
  private ServiceConfig config;

  @Autowired
  private OrganizationFeignClient organizationFeignClient;

  public License getLicense(UUID organizationId, UUID licenseId) {
    License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

    Organization org = retrieveOrgInfo(organizationId);

    license.setOrganizationName(org.getName());
    license.setContactName( org.getContactName());
    license.setContactEmail( org.getContactEmail() );
    license.setContactPhone( org.getContactPhone() );
    license.setComment(config.getExampleProperty());

    return license;
  }

  public List<License> getLicensesByOrg(UUID organizationId){
    return licenseRepository.findByOrganizationId(organizationId );
  }

  public void saveLicense(License license){
    license.setLicenseId(UUID.randomUUID());
    licenseRepository.save(license);
  }

  public void updateLicense(License license){
    licenseRepository.save(license);
  }

  public void deleteLicense(License license){
    licenseRepository.deleteById(license.getLicenseId());
  }

  private Organization retrieveOrgInfo(UUID organizationId){
    return organizationFeignClient.getOrganization(organizationId);
  }
}
