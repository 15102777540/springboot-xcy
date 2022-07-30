package com.wljy.util.vo;

import lombok.Data;

import java.util.List;

@Data
public class QuestionList {

    private String result;

    private List<AnswerList> answerList;

    private int rn;

    private String queContent;

    private String subQueContent;

    private String standard;

    private String seqNo;

    private String subjectNo;

    private String queNotice;

    private String userAnswer;

    private String correctAnswer;

    private String subQueOption;

    private String queRequest;

    private int optionNum;

    private String queNo;
}
