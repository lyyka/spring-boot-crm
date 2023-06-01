package com.example.lr20190024.stages.controllers;

import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.exceptions.InvalidOrderException;
import com.example.lr20190024.stages.requests.StageOrderUpdateRequest;
import com.example.lr20190024.stages.requests.StageStoreRequest;
import com.example.lr20190024.stages.requests.StageUpdateRequest;
import com.example.lr20190024.stages.responses.StageResponse;
import com.example.lr20190024.stages.services.IStageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "crm")
@RequestMapping("/api/stages")
public class StagesController {
    private final IStageService stageService;

    @GetMapping(value = "{pipeline_id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_STAGE')")
    public ResponseEntity<List<StageResponse>> index(@PathVariable @Positive Long pipeline_id) {
        return ResponseEntity.ok().body(this.stageService.getForPipeline(pipeline_id));
    }

    @GetMapping(value = "get/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_STAGE')")
    public ResponseEntity<StageResponse> get(@PathVariable @Positive Long id) {
        return ResponseEntity.ok().body(
                this.stageService.get(id)
        );
    }

    @GetMapping(value = "get-ids-per-pipeline")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_STAGE')")
    public ResponseEntity<Map<Long, List<StageResponse>>> getStagesPerPipeline() {
        return ResponseEntity.ok().body(
                this.stageService.getStagesPerPipeline()
        );
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CREATE_STAGE')")
    public ResponseEntity<StageResponse> store(@RequestBody @Validated StageStoreRequest stageStoreRequest) {
        Stage stage = this.stageService.store(stageStoreRequest);
        return ResponseEntity.ok().body(StageResponse.fromEntity(stage));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_UPDATE_STAGE')")
    public ResponseEntity<StageResponse> update(@PathVariable @Positive Long id, @RequestBody @Validated StageUpdateRequest stageUpdateRequest) {
        try {
            Stage stage = this.stageService.update(id, stageUpdateRequest);
            return ResponseEntity.ok().body(StageResponse.fromEntity(stage));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/order/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_UPDATE_STAGE')")
    public ResponseEntity<StageResponse> updateOrder(@PathVariable @Positive Long id, @RequestBody @Validated StageOrderUpdateRequest stageOrderUpdateRequest) {
        try {
            Stage stage = this.stageService.updateOrder(id, stageOrderUpdateRequest);
            return ResponseEntity.ok().body(StageResponse.fromEntity(stage));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidOrderException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_DELETE_STAGE')")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        this.stageService.delete(id);
        return ResponseEntity.ok().build();
    }
}
