package com.springbootmall.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestNumber implements Serializable {
    private BigDecimal a;
    private BigDecimal b;

    private String name;
    private String subject;
    private BigDecimal result;
    private Double result2;
}
