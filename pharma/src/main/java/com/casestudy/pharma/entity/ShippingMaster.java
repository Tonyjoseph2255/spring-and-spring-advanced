package com.casestudy.pharma.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for ShippingMaster
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHIPPING_MASTER")
public class ShippingMaster {
    @Id


    @Column(name = "MEDICINE_TYPE_CODE")
    private String medicineTypeCode;

    @Column(name = "WEIGHT_RANGE")
    private String weightRange;

    @Column(name = "SHIPPING_CHARGE")
    private Double shippingCharge;

}

