package com.wljy.pojo;

import lombok.Data;

@Data
public class Question {

    /**
     * ID
     */
    private Long questionId;

    private String seqNo;

    /**
     * 题目编号
     */
    private String questionQueno;

    /**
     * 题目内容
     */
    private String questionQuecontent;

    /**
     * 题目答案选择
     */
    private String questionCorrectanswer;

    /**
     * 题目答案内容
     */
    private String questionAnswercontent;

    /**
     * 科目编号
     */
    private String subjectNo;
}
