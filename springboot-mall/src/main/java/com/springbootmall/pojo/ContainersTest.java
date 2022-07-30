package com.springbootmall.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ContainersTest {
    private String id;

    private String key;

    private String remark;

    private String user;

    private String iycCntrno;

    private String iycInytm;

    private String iycType;

    private String iycCszCsizecd;

    private String iycCtypecd;

    private String iycCheightcd;

    private String iycIsocd;

    private String iycCweight;

    private String iycStsCstatuscd;

    private String iycCstCopercd;

    private String iycYlocation;

    private String iycPotLdport;

    private String iycPotUnldport;

    private String iycPotDstport;

    private String iycPotTransport;

    private String iycCstFrom;

    private String iycCstTo;

    private String iycOuttm;

    private String iycDoordirection;

    private String iycPassfg;

    private String iycStowagefg;

    private String iycGroupfg;

    private List<String> cols;
}
