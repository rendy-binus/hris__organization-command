package com.rendyezaputra.hris.organizationcommand.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/job-title")
public class JobTitleController {

    @PostMapping
    public ResponseEntity<String> createJobTitle() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("createJobTitle");
    }

    @PatchMapping
    public ResponseEntity<String> updateJobTitle() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("updateJobTitle");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteJobTitle() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("deleteJobTitle");
    }
    
}
