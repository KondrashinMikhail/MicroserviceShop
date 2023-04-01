package OrganizationCreationService.controllers;

import OrganizationCreationService.enums.OrganizationState;
import OrganizationCreationService.services.OrganizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/OrganizationCreationService", method = {RequestMethod.GET, RequestMethod.POST})
public class OrganizationCreationController {

    private final OrganizationService organizationService;


    public OrganizationCreationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/sendRequest")
    public String sendRequest(@RequestParam("name") String name,
                            @RequestParam("description") String description,
                            @RequestParam("logoPath") String logoPath,
                            @RequestParam("userId") Long userId) {
        organizationService.addOrganization(name, description, logoPath, userId);
        return "REQUEST IN WORK";
    }

    @PostMapping("/organization/{id}/approveRequest")
    public String approveRequest(@PathVariable("id") Long id) {
        organizationService.updateOrganizationState(id, OrganizationState.Working.ordinal());
        return "ORGANIZATION APPROVED";
    }

    @PostMapping("/organization/{id}/freeze")
    public String freezeOrganization(@PathVariable("id") Long id) {
        organizationService.updateOrganizationState(id, OrganizationState.Frozen.ordinal());
        return "ORGANIZATION FROZEN";
    }

    @PostMapping("/organization/{id}/unfreeze")
    public String unfreezeOrganization(@PathVariable("id") Long id) {
        organizationService.updateOrganizationState(id, OrganizationState.Working.ordinal());
        return "ORGANIZATION UNFROZEN";
    }

    @PostMapping("/organization/{id}/delete")
    public String deleteOrganization(@PathVariable("id") Long id) {
        organizationService.updateOrganizationState(id, OrganizationState.Deleted.ordinal());
        return "ORGANIZATION DELETED";
    }

    @GetMapping("/findOrganizationFounder")
    public Long findOrganizationFounder(@RequestParam("organizationId") Long organizationId) {
        return organizationService.findOrganization(organizationId).getUserId();
    }
}
