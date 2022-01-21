package com.alpha.exam.exam.dto.response;

import com.alpha.exam.exam.dto.ElectricityBillData;
import com.alpha.exam.exam.dto.Header;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDto {

    private Header header;
    private List<ElectricityBillData> electricityBillData;

}
