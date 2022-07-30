package com.wljy.services;

import com.wljy.mapper.QuestionMapper;
import com.wljy.mapper.SubjectMapper;
import com.wljy.pojo.Question;
import com.wljy.pojo.Subject;
import com.wljy.util.JacksonUtil;
import com.wljy.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
class wljyTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    RedisUtils redisUtils;

    @Test
    void TestMain(){
        List<Subject> subjects = subjectMapper.selectByWhere(new HashMap());
        for (Subject subject : subjects) {
            Map map = new HashMap<>();
            map.put("subjectNo",subject.getSubjectNo());
            List<Question> questions = questionMapper.selectByWhere(map);
            redisUtils.set(subject.getSubjectNo(), JacksonUtil.obj2String(questions));
            for (Question question : questions) {
                redisUtils.set(question.getQuestionQueno(), JacksonUtil.obj2String(question));
            }
        }
    }

    @Test
    void Random(){
        Random random = new Random();
        random.nextInt(11);
    }
}
