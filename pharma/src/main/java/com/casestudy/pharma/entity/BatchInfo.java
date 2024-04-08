package com.casestudy.pharma.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@NoArgsConstructor

public class BatchInfo {
    private String batchCode;
    private String medicineCode;
    private int weight;
    private double price;
    private String medicineTypeCode;
    private boolean refrigerator;
}
