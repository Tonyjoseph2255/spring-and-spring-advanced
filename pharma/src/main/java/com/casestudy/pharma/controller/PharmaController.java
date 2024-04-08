package com.casestudy.pharma.controller;

import com.casestudy.pharma.Service.PharmaService;
import com.casestudy.pharma.entity.BatchInfo;
import com.casestudy.pharma.exception.PharmaBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/pharma")
public class PharmaController {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PharmaService pharmaService;


    @GetMapping("/check-database")
    public String checkDatabaseConnection() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Database connection is successful";
        } catch (Exception e) {
            return "Failed to connect to the database: " + e.getMessage();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<String> createNewBatch(@RequestBody BatchInfo batchInfo) {
        try {
            if (pharmaService.shipmentCharge(batchInfo)) {
                return ResponseEntity.ok("Batch added successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add batch.");
            }
        } catch (PharmaBusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorCode() + " " + e.getMessage());
        }
    }
}

