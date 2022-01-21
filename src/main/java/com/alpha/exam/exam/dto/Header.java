package com.alpha.exam.exam.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Header {

    private double totalUnit;
    private double costPerUnit;
    private double totalCost;
}
