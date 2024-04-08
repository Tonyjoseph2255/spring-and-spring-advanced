package com.casestudy.pharma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BATCH_INFO")
public class BatchVo {
    @Id
    @Column(name = "BATCH_CODE")
    private String batchCode;
    @Column(name = "MEDICINE_CODE")
    private String medicineCode;
    @Column(name = "WEIGHT")
    private String weight;
    @Column(name = "PRICE")
    private double price;

    @Column(name = "MEDICINE_TYPE_CODE")
    private String medicineTypeCode;
    @Column(name = "SHIPPING_CHARGE")
    private double shippingCharge;

    @Column(name = "CARE_LEVEL")
    private String careLevel;
}
