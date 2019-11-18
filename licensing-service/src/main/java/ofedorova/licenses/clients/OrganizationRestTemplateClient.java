package ofedorova.licenses.clients;

import ofedorova.licenses.model.Organization;
import ofedorova.licenses.utill.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class OrganizationRestTemplateClient {

  @Autowired
  private RestTemplate restTemplate;

  private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

  public Organization getOrganization(UUID organizationId){
    logger.info(">>> In Licensing Service.getOrganization: {}. Thread Id: {}", UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
    ResponseEntity<Organization> restExchange =
        restTemplate.exchange(
            "http://zuul-server/api/organization/v1/organizations/{organizationId}",
            HttpMethod.GET,
            null, Organization.class, organizationId);
    return restExchange.getBody();
  }
}
