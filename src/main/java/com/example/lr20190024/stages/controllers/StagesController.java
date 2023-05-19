package com.example.lr20190024.stages.controllers;

import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.requests.StageStoreRequest;
import com.example.lr20190024.stages.services.IStageService;
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
@RequestMapping("/api/stages")
public class StagesController {
    private final IStageService stageService;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_STAGE')")
    public ResponseEntity<List<Stage>> index() {
        return ResponseEntity.ok().body(this.stageService.getAll());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CREATE_STAGE')")
    public ResponseEntity<Stage> store(@RequestBody @Validated StageStoreRequest stageStoreRequest) {
        Stage stage = this.stageService.store(stageStoreRequest);
        return ResponseEntity.ok().body(stage);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_UPDATE_STAGE')")
    public ResponseEntity<Stage> update(@PathVariable @Positive Long id, @RequestBody @Validated StageStoreRequest stageStoreRequest) {
        try {
            Stage stage = this.stageService.update(id, stageStoreRequest);
            return ResponseEntity.ok().body(stage);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_DELETE_STAGE')")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        this.stageService.delete(id);
        return ResponseEntity.ok().build();
    }
}
