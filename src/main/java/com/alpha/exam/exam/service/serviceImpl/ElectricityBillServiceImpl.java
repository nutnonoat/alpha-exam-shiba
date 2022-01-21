package com.alpha.exam.exam.service.serviceImpl;

import com.alpha.exam.exam.dto.ElectricityBillData;
import com.alpha.exam.exam.dto.Header;
import com.alpha.exam.exam.dto.response.ResponseDto;
import com.alpha.exam.exam.model.CostPerUnit;
import com.alpha.exam.exam.model.ElectricityBill;
import com.alpha.exam.exam.repository.CostPerUnitRepository;
import com.alpha.exam.exam.repository.ElectricityBillRepository;
import com.alpha.exam.exam.service.ElectricityBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ElectricityBillServiceImpl implements ElectricityBillService {

    @Autowired
    ElectricityBillRepository electricityBillRepository;

    @Autowired
    CostPerUnitRepository costPerUnitRepository;

    @Override
    public ResponseDto fetchCostOfElectricityBill(String name, String startDate, String endDate) {

        List<ElectricityBill> electricityBillList =
                electricityBillRepository.findByCountryOrderByUnitDesc(name);

        double totalUnit=0;
        double costPerunit=0;

        if(!electricityBillList.isEmpty()){
            totalUnit = electricityBillList.stream().mapToDouble(x->Double.parseDouble(x.getUnit())).sum();

            CostPerUnit costPerUnit = costPerUnitRepository.findByCountry(name);
            costPerunit = Double.parseDouble(costPerUnit.getCostPerUnit());

        }
        double totalCost = totalUnit * costPerunit;

        List<ElectricityBillData> electricityBillDataList =
                electricityBillList.stream().map(
                        x-> ElectricityBillData.builder()
                                .id(x.getId())
                                .name(x.getName())
                                .country(x.getCountry())
                                .unit(Double.parseDouble(x.getUnit()))
                                .billedAt(x.getBilledAt())
                                .createdAt(x.getCreatedAt())
                                .build()

                ).collect(Collectors.toList());

        ResponseDto responseDto = ResponseDto.builder()
                .header(Header.builder()
                        .totalUnit(totalUnit).costPerUnit(costPerunit).totalCost(totalCost)
                        .build()
                )
                .electricityBillData(electricityBillDataList)
                .build();

        return responseDto;
    }

    private LocalDateTime getLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyymmdd");
        LocalDate localDateTime = formatter.parse(date, LocalDate::from);
       // LocalDate  localDateTime = LocalDate.parse(date, formatter);
        return  localDateTime.atTime(0,0,0,0);
    }

}
