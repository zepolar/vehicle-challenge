package com.linbrox.vehicle.infrastructure.entity;
import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.domain.model.Vehicle;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "vehicles")
@Data
@Builder
@NoArgsConstructor
public class VehicleEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "code", unique = true)
    private Long code;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "year")
    private Long year;
    @Column(name = "sale_price")
    private Double salePrice;
    @Column(name = "bonus")
    private Double bonus;
    @Column(name = "final_price")
    private Double finalPrice;
    @Column(name = "disability_100")
    private Long disability100;
    @Column(name = "sgc_code")
    private String sgcCode;
    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private HyundaiModelEnum hyundaiModel;

    public VehicleEntity(UUID id, Long code, String name, Long year, Double salePrice, Double bonus, Double finalPrice, Long disability100, String sgcCode, HyundaiModelEnum hyundaiModel) {
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

    public static VehicleEntity fromDomanModel(Vehicle vehicle){
        return VehicleEntity.builder()
                .id(vehicle.getId())
                .code(vehicle.getCode())
                .name(vehicle.getName())
                .year(vehicle.getYear())
                .salePrice(vehicle.getSalePrice())
                .bonus(vehicle.getBonus())
                .finalPrice(vehicle.getFinalPrice())
                .disability100(vehicle.getDisability100())
                .sgcCode(vehicle.getSgcCode())
                .hyundaiModel(vehicle.getHyundaiModel())
                .build();
    }

    public Vehicle toDomanModel(){
        return Vehicle.builder()
                .id(this.id)
                .code(this.code)
                .name(this.name)
                .year(this.year)
                .salePrice(this.salePrice)
                .bonus(this.bonus)
                .finalPrice(this.finalPrice)
                .disability100(this.disability100)
                .sgcCode(this.sgcCode)
                .hyundaiModel(this.hyundaiModel)
                .build();
    }

}
