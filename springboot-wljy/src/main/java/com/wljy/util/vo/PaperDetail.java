package com.wljy.util.vo;

import lombok.Data;

import java.util.List;

@Data
public class PaperDetail {

    private String title;

    private List<QuestionList> questionList;

    private String titleName;

    private String pqtDesc;

    private String queType;
}
