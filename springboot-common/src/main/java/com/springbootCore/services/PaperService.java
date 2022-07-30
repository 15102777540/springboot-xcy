package com.springbootCore.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.springbootCore.util.JacksonUtil;
import com.springbootCore.util.RedisUtils;
import com.springbootCore.mapper.QuestionMapper;
import com.springbootCore.mapper.SubjectMapper;
import com.springbootCore.pojo.Question;
import com.springbootCore.pojo.Subject;
import com.springbootCore.util.vo.AnswerList;
import com.springbootCore.util.vo.PaperDetail;
import com.springbootCore.util.vo.PaperVo;
import com.springbootCore.util.vo.QuestionList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PaperService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private RedisUtils redisUtils;

    public List<Question> getSelectQuestion(String data) {
        List<Question> list = new ArrayList<>();
        Map questionjMap = new HashMap();
        Map subjMap = new HashMap();
        PaperVo paperVo = JacksonUtil.string2Obj(data, new TypeReference<PaperVo>() {
        });
        List<Question> questions = new ArrayList<>();
        List<PaperDetail> paperDetail = paperVo.getPaperDetail();
        try {
            if (!CollectionUtils.isEmpty(paperDetail)) {
                for (PaperDetail detail : paperDetail) {
                    PaperDetail paperDetail1 = detail;
                    List<QuestionList> questionList = paperDetail1.getQuestionList();
                    for (int i = 0; i < questionList.size(); i++) {
                        QuestionList questionList1 = questionList.get(i);
                        if (i == 0) {
                            questionjMap.put("subjectNo", questionList1.getSubjectNo());
                            //redis处理
                            if (redisUtils.hasKey(questionList1.getSubjectNo())) {
                                String o = (String) redisUtils.get(questionList1.getSubjectNo());
                                questions = JacksonUtil.string2Obj(o, new TypeReference<List<Question>>() {
                                });
                            } else {
                                questions = questionMapper.selectByWhere(questionjMap);
                            }
                            if (questions.size() == 0) {
                                log.error("没有该科目数据");
                                return list;
                            }
                        }
                        Question question = new Question();
                        question.setSeqNo(questionList1.getSeqNo());
                        question.setSubjectNo(questionList1.getSubjectNo());
                        question.setQuestionQueno(questionList1.getQueNo());
                        question.setQuestionQuecontent(questionList1.getQueContent());
                        List<Question> collect = questions.stream().filter(r -> r.getQuestionQueno().equals(questionList1.getQueNo())).collect(Collectors.toList());
                        if (!CollectionUtils.isEmpty(collect)) {
                            question.setQuestionAnswercontent(collect.get(0).getQuestionAnswercontent());//答案内容
                            question.setQuestionCorrectanswer(collect.get(0).getQuestionCorrectanswer());//答案
                        }
                        list.add(question);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    public int addQuestion(String data) {
        int count = 0;
        List<Subject> subjects = new ArrayList<>(1);
        List<Question> questions = new ArrayList<>(1);
        List<Question> questions_u = new ArrayList<>(1);
        PaperVo paperVo = JacksonUtil.string2Obj(data, new TypeReference<PaperVo>() {
        });
        if (paperVo == null) {
            log.info("数据格式不对");
            return 0;
        }
        try {
            //新增答案
            List<PaperDetail> paperDetail = paperVo.getPaperDetail();
            String SubjectNo = "";
            Map questionjMap = new HashMap();
            if (paperDetail.size() >= 0) {
                for (PaperDetail detail : paperDetail) {
                    PaperDetail paperDetail1 = detail;
                    List<QuestionList> questionList = paperDetail1.getQuestionList();
                    for (int i = 0; i < questionList.size(); i++) {
                        QuestionList questionList1 = questionList.get(i);
                        if (i == 0) {
                            SubjectNo = questionList1.getSubjectNo();
                        }
                        Question question = new Question();
                        question.setSubjectNo(questionList1.getSubjectNo());
                        question.setQuestionQueno(questionList1.getQueNo());
                        question.setQuestionQuecontent(questionList1.getQueContent());
                        question.setQuestionCorrectanswer(questionList1.getCorrectAnswer());
                        List<AnswerList> answerList = questionList1.getAnswerList();
                        for (AnswerList anitem : answerList) {
                            if (anitem.getOptionNo().equals(questionList1.getCorrectAnswer()) || questionList1.getCorrectAnswer().contains(anitem.getOptionNo()))
                                question.setQuestionAnswercontent((question.getQuestionAnswercontent() == null ? "" : question.getQuestionAnswercontent()) + anitem.getOptionNo() + ":" + anitem.getOption());
                        }
                        questionjMap.put("subjectNo", questionList1.getSubjectNo());
                        questionjMap.put("questionQueno", questionList1.getQueNo());
                        //redis处理
                        List<Question> questions1 = new ArrayList<>();
                        if (redisUtils.hasKey(questionList1.getSubjectNo())) {
                            String o = (String) redisUtils.get(questionList1.getSubjectNo());
                            log.info(questionList1.getSubjectNo());
                            questions1 = JacksonUtil.string2Obj(o, new TypeReference<List<Question>>() {
                            });
                            questions1 = questions1.stream().filter(x -> x.getQuestionQueno().equals(questionList1.getQueNo())).collect(Collectors.toList());
                        } else {
                            questions1 = questionMapper.selectByWhere(questionjMap);
                        }
                        if (questionList1.getCorrectAnswer() != null && CollectionUtils.isEmpty(questions1))
                            questions.add(question);
                        if (questionList1.getCorrectAnswer() != null && !CollectionUtils.isEmpty(questions1) && StringUtils.isEmpty(questions1.get(0).getQuestionAnswercontent()))
                            questions_u.add(question);
                    }
                }
            }

            List<Question> new_questions = new ArrayList<>();
            if (redisUtils.hasKey(SubjectNo)) {
                String o = (String) redisUtils.get(SubjectNo);
                new_questions = JacksonUtil.string2Obj(o, new TypeReference<List<Question>>() {
                });
            }

            if (questions.size() > 0) {
                count = questionMapper.insertList(questions);
                if (count > 0) {
                    new_questions.addAll(questions);
                    redisUtils.set(SubjectNo,JacksonUtil.obj2String(new_questions));
                    for (Question question : questions) {
                        redisUtils.set(question.getQuestionQueno(),JacksonUtil.obj2String(question));
                    }
                }
            }

            if (questions_u.size() > 0) {
                count = questionMapper.updateList(questions_u);
                if (count > 0) {
                    for (Question question : questions_u) {
                        if (new_questions.stream().allMatch(x->x.getQuestionQueno().equals(question.getQuestionQueno()) && x.getSubjectNo().equals(question.getSubjectNo()))){
                            List<Question> collect = new_questions.stream().filter(x -> x.getSubjectNo().equals(question.getSubjectNo()) && x.getQuestionQueno().equals(question.getQuestionQueno())).collect(Collectors.toList());
                            Question question1 = collect.get(0);
                            question1.setQuestionAnswercontent(question.getQuestionAnswercontent());
                            redisUtils.set(question1.getQuestionQueno(),JacksonUtil.obj2String(question1));
                        }
                    }
                    redisUtils.set(SubjectNo,JacksonUtil.obj2String(new_questions));
                }
            }

            //是否新增科目
            Map subjMap = new HashMap();
            subjMap.put("subjectNo", SubjectNo);
            Subject subject = new Subject();
            if (subjectMapper.selectByWhere(subjMap).size() == 0) {
                subject.setSubjectName(paperVo.getPaperName());
                subject.setSubjectNo(SubjectNo);
                subjects.add(subject);
                subjectMapper.insertList(subjects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Transactional
    public int addQuestionapi(PaperVo paperVo) {
        int count = 0;
        List<Subject> subjects = new ArrayList<>(1);
        List<Question> questions = new ArrayList<>(1);
        List<Question> questions_u = new ArrayList<>(1);
        /*PaperVo paperVo = JacksonUtil.string2Obj(data, new TypeReference<PaperVo>() {
        });*/
        if (paperVo == null) {
            log.info("数据格式不对");
            return 0;
        }
        try {
            //新增答案
            List<PaperDetail> paperDetail = paperVo.getPaperDetail();
            String SubjectNo = "";
            Map questionjMap = new HashMap();
            if (paperDetail.size() >= 0) {
                for (PaperDetail detail : paperDetail) {
                    PaperDetail paperDetail1 = detail;
                    List<QuestionList> questionList = paperDetail1.getQuestionList();
                    for (int i = 0; i < questionList.size(); i++) {
                        QuestionList questionList1 = questionList.get(i);
                        if (i == 0) {
                            SubjectNo = questionList1.getSubjectNo();
                        }
                        Question question = new Question();
                        question.setSubjectNo(questionList1.getSubjectNo());
                        question.setQuestionQueno(questionList1.getQueNo());
                        question.setQuestionQuecontent(questionList1.getQueContent());
                        question.setQuestionCorrectanswer(questionList1.getCorrectAnswer());
                        List<AnswerList> answerList = questionList1.getAnswerList();
                        for (AnswerList anitem : answerList) {
                            if (anitem.getOptionNo().equals(questionList1.getCorrectAnswer()) || questionList1.getCorrectAnswer().contains(anitem.getOptionNo()))
                                question.setQuestionAnswercontent((question.getQuestionAnswercontent() == null ? "" : question.getQuestionAnswercontent()) + anitem.getOptionNo() + ":" + anitem.getOption());
                        }
                        questionjMap.put("subjectNo", questionList1.getSubjectNo());
                        questionjMap.put("questionQueno", questionList1.getQueNo());
                        //redis处理
                        List<Question> questions1 = new ArrayList<>();
                        if (redisUtils.hasKey(questionList1.getSubjectNo())) {
                            String o = (String) redisUtils.get(questionList1.getSubjectNo());
                            log.info(questionList1.getSubjectNo());
                            questions1 = JacksonUtil.string2Obj(o, new TypeReference<List<Question>>() {
                            });
                            questions1 = questions1.stream().filter(x -> x.getQuestionQueno().equals(questionList1.getQueNo())).collect(Collectors.toList());
                        } else {
                            questions1 = questionMapper.selectByWhere(questionjMap);
                        }
                        if (questionList1.getCorrectAnswer() != null && CollectionUtils.isEmpty(questions1))
                            questions.add(question);
                        if (questionList1.getCorrectAnswer() != null && !CollectionUtils.isEmpty(questions1) && StringUtils.isEmpty(questions1.get(0).getQuestionAnswercontent()))
                            questions_u.add(question);
                    }
                }
            }

            List<Question> new_questions = new ArrayList<>();
            if (redisUtils.hasKey(SubjectNo)) {
                String o = (String) redisUtils.get(SubjectNo);
                new_questions = JacksonUtil.string2Obj(o, new TypeReference<List<Question>>() {
                });
            }

            if (questions.size() > 0) {
                count = questionMapper.insertList(questions);
                if (count > 0) {
                    new_questions.addAll(questions);
                    redisUtils.set(SubjectNo,JacksonUtil.obj2String(new_questions));
                    for (Question question : questions) {
                        redisUtils.set(question.getQuestionQueno(),JacksonUtil.obj2String(question));
                    }
                }
            }

            if (questions_u.size() > 0) {
                count = questionMapper.updateList(questions_u);
                if (count > 0) {
                    for (Question question : questions_u) {
                        if (new_questions.stream().allMatch(x->x.getQuestionQueno().equals(question.getQuestionQueno()) && x.getSubjectNo().equals(question.getSubjectNo()))){
                            List<Question> collect = new_questions.stream().filter(x -> x.getSubjectNo().equals(question.getSubjectNo()) && x.getQuestionQueno().equals(question.getQuestionQueno())).collect(Collectors.toList());
                            Question question1 = collect.get(0);
                            question1.setQuestionAnswercontent(question.getQuestionAnswercontent());
                            redisUtils.set(question1.getQuestionQueno(),JacksonUtil.obj2String(question1));
                        }
                    }
                    redisUtils.set(SubjectNo,JacksonUtil.obj2String(new_questions));
                }
            }

            //是否新增科目
            Map subjMap = new HashMap();
            subjMap.put("subjectNo", SubjectNo);
            Subject subject = new Subject();
            if (subjectMapper.selectByWhere(subjMap).size() == 0) {
                subject.setSubjectName(paperVo.getPaperName());
                subject.setSubjectNo(SubjectNo);
                subjects.add(subject);
                subjectMapper.insertList(subjects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Subject> findSubjectAll() throws Exception {
        return subjectMapper.selectByWhere(new HashMap());
    }

    public List<Question> findQuestion(Map map) throws Exception {
        return questionMapper.selectByname(map);
    }

    public List<Question> findQueContent(String queNo) throws Exception {
        List<Question> list = new ArrayList<>();
        if (redisUtils.hasKey(queNo)) {
            String o = (String) redisUtils.get(queNo);
            Question new_questions = JacksonUtil.string2Obj(o, new TypeReference<Question>() {
            });
            list.add(new_questions);
        }else {
            list = questionMapper.selectByqueContent(queNo);
        }
        return list;
    }
}
