package com.alpha.exam.exam.service;

import com.alpha.exam.exam.dto.response.ResponseDto;
import com.alpha.exam.exam.model.CostPerUnit;
import com.alpha.exam.exam.model.ElectricityBill;
import com.alpha.exam.exam.repository.CostPerUnitRepository;
import com.alpha.exam.exam.repository.ElectricityBillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ElectricityBillServiceTest {

    @MockBean
    ElectricityBillRepository electricityBillRepository;

    @MockBean
    CostPerUnitRepository costPerUnitRepository;

    @Autowired
    ElectricityBillService electricityBillService;

    @BeforeEach
    void setUp() {

        List<ElectricityBill> electricityBillList = new ArrayList<>();
        ElectricityBill electricityBill1 = ElectricityBill.builder()
                .id(UUID.randomUUID())
                .country("Thailand")
                .billedAt(getLocalDateTime("20220105"))
                .createdAt(LocalDateTime.now())
                .name("Rohan")
                .unit("20")
                .build(); //300+ 525 =825
        ElectricityBill electricityBill2 = ElectricityBill.builder()
                .id(UUID.randomUUID())
                .country("Thailand")
                .billedAt(getLocalDateTime("20220109"))
                .createdAt(LocalDateTime.now())
                .name("Rohan")
                .unit("35")
                .build();
        electricityBillList.add(electricityBill1);
        electricityBillList.add(electricityBill2);

        CostPerUnit costPerUnit =  CostPerUnit.builder()
                .id(UUID.randomUUID())
                .country("Thailand")
                .costPerUnit("15")
                .createdAt(LocalDateTime.now())
                .build();

        Mockito.when(electricityBillRepository.findByCountryAndBilledAtBetweenOrderByUnitDesc(
                        "Thailand", getLocalDateTime("20220101"), getLocalDateTime("20220127")))
                .thenReturn(electricityBillList);

        Mockito.when(costPerUnitRepository.findByCountry(Mockito.anyString())).thenReturn(costPerUnit);

    }

    @Test
    @DisplayName("Get electricityBillList data with total cost")
    public void whenInputParameterMatched_thenShouldReturnElectricityBillListWithTotalCost(){
        ResponseDto responseDto = electricityBillService.fetchCostOfElectricityBill("Thailand", "20220101", "20220127");
        assertEquals(responseDto.getElectricityBillData().size(), 2);
        assertEquals(responseDto.getHeader().getCostPerUnit(), Double.parseDouble("15") );
    }

    @Test
    @DisplayName("To test the total cost")
    public void testTheTotalCost(){
        ResponseDto responseDto = electricityBillService.fetchCostOfElectricityBill("Thailand", "20220101", "20220127");
        assertEquals(responseDto.getHeader().getTotalCost(), Double.parseDouble("825"));
    }

    private LocalDateTime getLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = formatter.parse(date, LocalDate::from);
        return  localDate.atTime(0,0,0,0);
    }
}