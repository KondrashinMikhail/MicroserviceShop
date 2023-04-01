package OrganizationCreationService.dto;

import OrganizationCreationService.entities.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Long id;
    private String name;
    private String description;
    private String logoPath;
    private int state;
    private Long userId;

    public OrganizationDto(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.description = organization.getDescription();
        this.logoPath = organization.getLogoPath();
        this.state = organization.getState();
        this.userId = organization.getUserId();
    }
}
