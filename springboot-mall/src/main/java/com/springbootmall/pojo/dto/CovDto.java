package com.springbootmall.pojo.dto;

import com.springbootmall.pojo.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class CovDto extends BaseDTO {

    private Long id;

    /**
     * 省份
     */
    private String province;

    /**
     * 日期
     */
    private String date;

    /**
     * 新增
     */
    private BigDecimal confirmAdd;

    /**
     * 确诊
     */
    private BigDecimal confirm;

    /**
     * 治愈
     */
    private BigDecimal heal;

    /**
     * 死亡
     */
    private BigDecimal dead;

    /**
     * 现有确诊
     */
    private BigDecimal newdiagnose;

    /**
     * 无症状
     */
    private BigDecimal asymptomatic;

    /**
     * 无症状新增
     */
    private BigDecimal asymptomaticAdd;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CovDto covDto = (CovDto) o;
        return Objects.equals(id, covDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
