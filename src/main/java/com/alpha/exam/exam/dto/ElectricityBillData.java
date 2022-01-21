package com.alpha.exam.exam.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ElectricityBillData {

    private UUID id;
    private String name;
    private LocalDateTime billedAt;
    private String country;
    private double unit;
    private LocalDateTime createdAt;

}
