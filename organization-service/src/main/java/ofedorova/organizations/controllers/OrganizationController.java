package ofedorova.organizations.controllers;

import ofedorova.organizations.model.Organization;
import ofedorova.organizations.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value="v1/organizations")
public class OrganizationController {

  @Autowired
  private OrganizationService organizationService;

  @RequestMapping(value="/{organizationId}",method = RequestMethod.GET)
  public Organization getOrganization(@PathVariable("organizationId") UUID organizationId) {
    return organizationService.findById(organizationId).orElse(null);
  }

  @RequestMapping(value="/{organizationId}",method = RequestMethod.PUT)
  public void updateOrganization( @PathVariable("organizationId") UUID orgId, @RequestBody Organization org) {
    organizationService.update(org);
  }

  @RequestMapping(value="/{organizationId}",method = RequestMethod.POST)
  public void saveOrganization(@RequestBody Organization org) {
    organizationService.save(org);
  }

  @RequestMapping(value="/{organizationId}",method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrganization( @PathVariable("orgId") UUID orgId,  @RequestBody Organization org) {
    organizationService.delete(org);
  }
}
