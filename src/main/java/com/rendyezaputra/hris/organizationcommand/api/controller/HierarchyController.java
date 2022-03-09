package com.rendyezaputra.hris.organizationcommand.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/hierarchy")
public class HierarchyController {

    @PostMapping
    public ResponseEntity<String> createHierarchy() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("createHierarchy");
    }

    @PatchMapping
    public ResponseEntity<String> updateHierarchy() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("updateHierarchy");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHierarchy() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("deleteHierarchy");
    }
    
}
