package com.springbootCore.util.vo;

import lombok.Data;

import java.util.List;

@Data
public class PaperVo {
    private int flag;

    private String paperName;

    private List<PaperDetail> paperDetail;

    private String instState;

    private String msgInfo;
}
