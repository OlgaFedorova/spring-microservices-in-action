package ofedorova.licenses.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class License {

  private String id;
  private String organizationId;
  private String productName;
  private String licenseType;

}
