package com.springbootmall.pojo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageUtils<T> implements Serializable {

    private Long total;

    private List<T> rows;

    private Long page;

    private Long size;
}
