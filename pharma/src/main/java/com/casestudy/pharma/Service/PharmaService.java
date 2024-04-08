package com.casestudy.pharma.Service;

import com.casestudy.pharma.entity.BatchInfo;
import com.casestudy.pharma.entity.BatchVo;
import com.casestudy.pharma.entity.ShippingMaster;
import com.casestudy.pharma.exception.PharmaBusinessException;
import com.casestudy.pharma.repository.BatchInfoRepository;
import com.casestudy.pharma.repository.ShippingMasterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;
import java.util.regex.Pattern;

@Service

public class PharmaService{

    @Autowired
    private ShippingMasterRepository shippingMasterRepository;
    @Autowired
    private BatchInfoRepository batchInfoRepository;
    private static final List<String> ALLOWED_MEDICINE_CODES = Arrays.asList(
            "MC_301", "MC_303", "MC-300", "MC_302", "MC_304",
            "MC_305", "MC_306", "MC_307", "MC_308", "MC_309", "MC_310"
    );
    public boolean shipmentCharge(BatchInfo batchInfo) throws PharmaBusinessException {
        if (!isValidBatchCode(batchInfo.getBatchCode())) {
            throw new PharmaBusinessException(513, "Batch format wrong. It should be in the format 'BTC-1234'");
        }
        if (batchInfoRepository.existsByMedicineCode(batchInfo.getMedicineCode())) {
            throw new PharmaBusinessException(511,"Medicine code already exists");
        }
        if (!ALLOWED_MEDICINE_CODES.contains(batchInfo.getMedicineCode())) {
            throw new PharmaBusinessException(510, "Invalid Medicine Code");
        }
        if (batchInfo.getWeight() < 100) {
            throw new PharmaBusinessException(512,"Batch Weight Should be greater than 100");
        }

            BatchVo batchVo=new BatchVo();
            batchVo.setBatchCode(batchInfo.getBatchCode());
            batchVo.setMedicineCode(batchInfo.getMedicineCode());

            batchVo.setPrice(batchInfo.getPrice());
            batchVo.setMedicineTypeCode(batchInfo.getMedicineTypeCode());
            String weight= calculateWeight(batchInfo.getWeight());
            batchVo.setWeight(batchVo.getWeight());

            ShippingMaster shippingMaster=shippingMasterRepository.findByMedicineTypeCodeAndWeightRange(batchInfo.getMedicineTypeCode(), weight);
            batchVo.setShippingCharge(shippingMaster.getShippingCharge());
            if ("C".equals(batchInfo.getMedicineTypeCode())) {
                batchVo.setCareLevel("Normal");
            } else if ("S".equals(batchInfo.getMedicineTypeCode())) {
                batchVo.setCareLevel("Extremely High");
            }else if("T".equals(batchInfo.getMedicineTypeCode())){
                batchVo.setCareLevel("medium");
            }
            batchInfoRepository.save(batchVo);
            System.out.println(batchVo);



        return true;
    }

    private String calculateWeight(int weight) {


        if (weight <= 500) {
            return "W1";
        } else if (weight <= 1000) {
            return "W2";
        } else {
            return "W3";
        }

    }
    private boolean isValidBatchCode(String batchCode) {
        // Regex pattern to match the batch code format "BTC-1234"
        String regex = "^BTC-\\d{4}$";
        return Pattern.matches(regex, batchCode);
    }

}
