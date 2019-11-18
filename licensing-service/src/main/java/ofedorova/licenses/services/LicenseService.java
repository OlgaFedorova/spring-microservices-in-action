package ofedorova.licenses.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import ofedorova.licenses.clients.OrganizationRestTemplateClient;
import ofedorova.licenses.config.ServiceConfig;
import ofedorova.licenses.model.License;
import ofedorova.licenses.model.Organization;
import ofedorova.licenses.repository.LicenseRepository;
import ofedorova.licenses.utill.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {
  private static final Logger logger = LoggerFactory.getLogger(LicenseService.class);

  @Autowired
  private LicenseRepository licenseRepository;

  @Autowired
  private ServiceConfig config;

  @Autowired
  private OrganizationRestTemplateClient organizationRestClient;

  public License getLicense(UUID organizationId, UUID licenseId) {
    License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

    Organization org = retrieveOrgInfo(organizationId);

    license.setOrganizationName(org.getName());
    license.setContactName(org.getContactName());
    license.setContactEmail(org.getContactEmail());
    license.setContactPhone(org.getContactPhone());
    license.setComment(config.getExampleProperty());

    return license;
  }

  @HystrixCommand(fallbackMethod = "buildFallbackLicenseList",
      threadPoolKey = "licenseByOrgThreadPool",
      threadPoolProperties = {
          @HystrixProperty(name = "coreSize", value = "30"),
          @HystrixProperty(name = "maxQueueSize", value = "10")
      },
      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"),
          @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
          @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
          @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
          @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
          @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
      })
  public List<License> getLicensesByOrg(UUID organizationId) {
    logger.info("LicenseService.getLicensesByOrg  Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
    return licenseRepository.findByOrganizationId(organizationId);
  }

  public void saveLicense(License license) {
    license.setLicenseId(UUID.randomUUID());
    licenseRepository.save(license);
  }

  public void updateLicense(License license) {
    licenseRepository.save(license);
  }

  public void deleteLicense(License license) {
    licenseRepository.deleteById(license.getLicenseId());
  }

  @HystrixCommand
  private Organization retrieveOrgInfo(UUID organizationId) {
    logger.info("LicenseService.retrieveOrgInfo Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
    return organizationRestClient.getOrganization(organizationId);
  }

  private List<License> buildFallbackLicenseList(UUID organizationId) {
    List<License> fallbackList = new ArrayList<>();
    License license = License.builder()
        .licenseId(UUID.fromString("0000000-00-00000"))
        .organizationId(organizationId)
        .productName("Sorry no licensing information currently available")
        .build();

    fallbackList.add(license);
    return fallbackList;
  }
}
