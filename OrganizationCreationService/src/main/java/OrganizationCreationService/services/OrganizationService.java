package OrganizationCreationService.services;

import OrganizationCreationService.entities.Organization;
import OrganizationCreationService.enums.OrganizationState;
import OrganizationCreationService.repositories.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public void addOrganization(String name, String description, String logoPath, Long userId) {
        Long id = (long) (organizationRepository.findAll().size() + 1);
        final Organization organization = new Organization(id, name, description, logoPath, OrganizationState.Request.ordinal(), userId);
        organizationRepository.save(organization);
    }

    @Transactional
    public void updateOrganization(Long id, String name, String description, String logoPath, int state, Long userId) {
        final Organization organization = findOrganization(id);
        organization.setName(name);
        organization.setDescription(description);
        organization.setLogoPath(logoPath);
        organization.setState(state);
        organization.setUserId(userId);
        organizationRepository.save(organization);
    }

    @Transactional
    public void updateOrganizationState(Long id, int state) {
        final Organization organization = findOrganization(id);
        organization.setState(state);
        organizationRepository.save(organization);
    }

    @Transactional(readOnly = true)
    public List<Organization> findAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Transactional
    public Organization findOrganization(Long id) {
        final Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElseThrow();
    }
}
