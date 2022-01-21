package com.alpha.exam.exam.controller;

import com.alpha.exam.exam.dto.response.ResponseDto;
import com.alpha.exam.exam.service.ElectricityBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElectricityBillController {

    @Autowired
    ElectricityBillService electricityBillService;

    @GetMapping("/v2/cost")
    public ResponseEntity<ResponseDto> fetchCostOfElectricityBill(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate
    ){
        ResponseDto responseDto =
                electricityBillService.fetchCostOfElectricityBill(name, startDate, endDate );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
