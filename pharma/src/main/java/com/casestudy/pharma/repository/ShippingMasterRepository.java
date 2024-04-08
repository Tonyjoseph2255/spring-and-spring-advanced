package com.casestudy.pharma.repository;

import com.casestudy.pharma.entity.ShippingMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingMasterRepository extends JpaRepository<ShippingMaster,Long> {
    ShippingMaster findByMedicineTypeCodeAndWeightRange( String medicineTypeCode, String weightRange);
}
