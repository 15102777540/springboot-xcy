package com.wljy.pojo;

import lombok.*;

@Data
public class Subject {

    /**
     * ID
     */
    private Long subjectId;

    /**
     * 科目编号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;
}
