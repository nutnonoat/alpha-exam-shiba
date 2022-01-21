package com.alpha.exam.exam.service;

import com.alpha.exam.exam.dto.response.ResponseDto;

public interface ElectricityBillService {

    public ResponseDto fetchCostOfElectricityBill(String name, String startDate, String endDate);
}
