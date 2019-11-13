package ofedorova.licenses.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Organization {

  private UUID id;
  private String name;
  private String contactName;
  private String contactEmail;
  private String contactPhone;
}
