package com.example.lr20190024.pipelines.controllers;

import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.pipelines.requests.PipelineStoreRequest;
import com.example.lr20190024.pipelines.services.IPipelineService;
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
@RequestMapping("/api/pipelines")
public class PipelinesController {
    private final IPipelineService pipelineService;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_PIPELINE')")
    public ResponseEntity<List<Pipeline>> index() {
        return ResponseEntity.ok().body(this.pipelineService.getAll());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CREATE_PIPELINE')")
    public ResponseEntity<Pipeline> store(@RequestBody @Validated PipelineStoreRequest pipelineStoreRequest) {
        Pipeline pipeline = this.pipelineService.store(pipelineStoreRequest);
        return ResponseEntity.ok().body(pipeline);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_UPDATE_PIPELINE')")
    public ResponseEntity<Pipeline> update(@PathVariable @Positive Long id, @RequestBody @Validated PipelineStoreRequest pipelineStoreRequest) {
        try {
            Pipeline pipeline = this.pipelineService.update(id, pipelineStoreRequest);
            return ResponseEntity.ok().body(pipeline);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_DELETE_PIPELINE')")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        this.pipelineService.delete(id);
        return ResponseEntity.ok().build();
    }
}
