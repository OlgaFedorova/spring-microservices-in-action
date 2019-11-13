package ofedorova.licenses.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "license", name = "licenses")
public class License {
  @Id
  @Column(name = "license_id", nullable = false)
  private UUID licenseId;

  @Column(name = "organization_id", nullable = false)
  private UUID organizationId;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "license_type", nullable = false)
  private String licenseType;

  @Column(name = "license_max", nullable = false)
  private Integer licenseMax;

  @Column(name = "license_allocated", nullable = false)
  private Integer licenseAllocated;

  @Column(name = "comment")
  private String comment;

  @Transient
  private String organizationName = "";

  @Transient
  private String contactName = "";

  @Transient
  private String contactPhone = "";

  @Transient
  private String contactEmail = "";
}
