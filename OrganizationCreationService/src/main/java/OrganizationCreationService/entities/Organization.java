package OrganizationCreationService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organization", schema = "public")
public class Organization {
    @Id
    private Long id;
    private String name;
    private String description;
    private String logoPath;
    private int state;
    private Long userId;

    public Organization(String name, String description, String logoPath, int state, Long userId) {
        this.name = name;
        this.description = description;
        this.logoPath = logoPath;
        this.state = state;
        this.userId = userId;
    }
}
