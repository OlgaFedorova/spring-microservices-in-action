package ofedorova.licenses.services;

import ofedorova.licenses.config.ServiceConfig;
import ofedorova.licenses.model.License;
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

  public License getLicense(UUID organizationId, UUID licenseId) {
    License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
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
}
