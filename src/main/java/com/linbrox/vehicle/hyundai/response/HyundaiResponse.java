package com.linbrox.vehicle.hyundai.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class HyundaiResponse {
    @Getter(onMethod_ = {@JsonProperty("VER_CODIGO")})
    @Setter(onMethod_ = {@JsonProperty("VER_CODIGO")})
    private Long code;
    @Getter(onMethod_ = {@JsonProperty("VER_NOMBRE")})
    @Setter(onMethod_ = {@JsonProperty("VER_NOMBRE")})
    private String name;
    @Getter(onMethod_ = {@JsonProperty("VEA_ANIO")})
    @Setter(onMethod_ = {@JsonProperty("VEA_ANIO")})
    private Long year;
    @Getter(onMethod_ = {@JsonProperty("VEA_PRECIO_PVP")})
    @Setter(onMethod_ = {@JsonProperty("VEA_PRECIO_PVP")})
    private Long salePrice;
    @Getter(onMethod_ = {@JsonProperty("VEA_BONO")})
    @Setter(onMethod_ = {@JsonProperty("VEA_BONO")})
    private Long bonus;
    @Getter(onMethod_ = {@JsonProperty("VEA_PRECIO_FINAL")})
    @Setter(onMethod_ = {@JsonProperty("VEA_PRECIO_FINAL")})
    private Long finalPrice;
    @Getter(onMethod_ = {@JsonProperty("VEA_DISCAPACIDAD_100")})
    @Setter(onMethod_ = {@JsonProperty("VEA_DISCAPACIDAD_100")})
    private Long disability100;
    @Getter(onMethod_ = {@JsonProperty("VER_COD_SGC")})
    @Setter(onMethod_ = {@JsonProperty("VER_COD_SGC")})
    private String sgcCode;

    @JsonCreator
    public HyundaiResponse(
            @JsonProperty("VER_CODIGO") Long code,
            @JsonProperty("VER_NOMBRE") String name,
            @JsonProperty("VEA_ANIO") Long year,
            @JsonProperty("VEA_PRECIO_PVP") Long salePrice,
            @JsonProperty("VEA_BONO") Long bonus,
            @JsonProperty("VEA_PRECIO_FINAL") Long finalPrice,
            @JsonProperty("VEA_DISCAPACIDAD_100") Long disability100,
            @JsonProperty("VER_COD_SGC") String sgcCode) {
        this.code = code;
        this.name = name;
        this.year = year;
        this.salePrice = salePrice;
        this.bonus = bonus;
        this.finalPrice = finalPrice;
        this.disability100 = disability100;
        this.sgcCode = sgcCode;
    }
}