package com.example.lr20190024.deals.controllers;

import com.example.lr20190024.deals.requests.DealStageUpdateRequest;
import com.example.lr20190024.deals.requests.DealStoreRequest;
import com.example.lr20190024.deals.requests.DealUpdateRequest;
import com.example.lr20190024.deals.responses.DealResponse;
import com.example.lr20190024.deals.services.IDealService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "crm")
@RequestMapping("/api/deals")
public class DealsController {

    private final IDealService dealService;

    @GetMapping(value = "/for-client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_DEAL', 'ROLE_USER_VIEW_DEAL')")
    public ResponseEntity<List<DealResponse>> getForClient(@PathVariable @Positive Long clientId) {
        return ResponseEntity.ok(this.dealService.getForClient(clientId));
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_DEAL', 'ROLE_USER_VIEW_DEAL')")
    public ResponseEntity<DealResponse> get(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(this.dealService.get(id));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CREATE_DEAL', 'ROLE_USER_CREATE_DEAL')")
    public ResponseEntity<DealResponse> store(@RequestBody @Validated DealStoreRequest dealStoreRequest) {
        return ResponseEntity.ok(this.dealService.store(dealStoreRequest));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_UPDATE_DEAL', 'ROLE_USER_UPDATE_DEAL')")
    public ResponseEntity<DealResponse> update(@PathVariable @Positive Long id, @RequestBody @Validated DealUpdateRequest dealUpdateRequest) {
        return ResponseEntity.ok(this.dealService.update(id, dealUpdateRequest));
    }

    @PutMapping(value = "/update-stage/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_UPDATE_DEAL', 'ROLE_USER_UPDATE_DEAL')")
    public ResponseEntity<DealResponse> updateStage(@PathVariable @Positive Long id, @RequestBody @Validated DealStageUpdateRequest dealStageUpdateRequest) {
        return ResponseEntity.ok(this.dealService.updateStage(id, dealStageUpdateRequest));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_DELETE_DEAL')")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        this.dealService.delete(id);
        return ResponseEntity.ok().build();
    }
}
