package com.linbrox.vehicle.domain.model;

import com.linbrox.vehicle.common.HyundaiModelEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
public class Vehicle {
    private UUID id;
    private Long code;
    private String name;
    private Long year;
    private Double salePrice;
    private Double bonus;
    private Double finalPrice;
    private Long disability100;
    private String sgcCode;
    private HyundaiModelEnum hyundaiModel;

    public Vehicle(UUID id, Long code, String name, Long year, Double salePrice, Double bonus, Double finalPrice, Long disability100, String sgcCode, HyundaiModelEnum hyundaiModel) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.year = year;
        this.salePrice = salePrice;
        this.bonus = bonus;
        this.finalPrice = finalPrice;
        this.disability100 = disability100;
        this.sgcCode = sgcCode;
        this.hyundaiModel = hyundaiModel;
    }
}
