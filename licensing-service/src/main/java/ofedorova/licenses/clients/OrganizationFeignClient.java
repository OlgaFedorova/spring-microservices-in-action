package ofedorova.licenses.clients;

import ofedorova.licenses.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient("organization-service")
public interface OrganizationFeignClient {
  @RequestMapping(
      method= RequestMethod.GET,
      value="/v1/organizations/{organizationId}",
      consumes="application/json")
  Organization getOrganization(@PathVariable("organizationId") UUID organizationId);
}
