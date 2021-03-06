package ofedorova.licenses.controllers;

import ofedorova.licenses.config.ServiceConfig;
import ofedorova.licenses.model.License;
import ofedorova.licenses.services.LicenseService;
import ofedorova.licenses.utill.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
  private static final Logger logger = LoggerFactory.getLogger(LicenseServiceController.class);

  @Autowired
  private LicenseService licenseService;

  @Autowired
  private ServiceConfig serviceConfig;

  @RequestMapping(method = RequestMethod.GET)
  public List<License> getLicenses(@PathVariable("organizationId") UUID organizationId) {
    logger.info("LicenseServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
    return licenseService.getLicensesByOrg(organizationId);
  }

  @RequestMapping(value="/{licenseId}",method = RequestMethod.GET)
  public License getLicenses( @PathVariable("organizationId") UUID organizationId,
                              @PathVariable("licenseId") UUID licenseId) {
    logger.info("LicenseServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
    return licenseService.getLicense(organizationId,licenseId);
  }

  @RequestMapping(value="{licenseId}",method = RequestMethod.PUT)
  public String updateLicenses( @PathVariable("licenseId") String licenseId) {
    return String.format("This is the put");
  }

  @RequestMapping(method = RequestMethod.POST)
  public void saveLicenses(@RequestBody License license) {
    licenseService.saveLicense(license);
  }

  @RequestMapping(value="{licenseId}",method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String deleteLicenses( @PathVariable("licenseId") UUID licenseId) {
    return String.format("This is the Delete");
  }
}
