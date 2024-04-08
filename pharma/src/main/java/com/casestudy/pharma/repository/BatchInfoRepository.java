package com.casestudy.pharma.repository;

import com.casestudy.pharma.entity.BatchVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BatchInfoRepository extends JpaRepository<BatchVo,String> {
    boolean existsByMedicineCode(String medicineCode);

    boolean existsByBatchCode(String batchCode);
}
