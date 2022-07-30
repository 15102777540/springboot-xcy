package com.springbootCore.Email.Services;

import com.springbootCore.mapper.QuestionMapper;
import com.springbootCore.mapper.SubjectMapper;
import com.springbootCore.pojo.Question;
import com.springbootCore.pojo.Subject;
import com.springbootCore.util.JacksonUtil;
import com.springbootCore.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.*;

@Component
public class ScheduledService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;

    //@Scheduled(cron = "0 2/3 * * * ?")
    public void test() {
        System.out.println(new Date());
    }
    /**
     * 0 10 12 5 * ? 每月5日12点10分触发
     */
    //@Scheduled(cron = "0 10 12 5 * ?")
    @Scheduled(cron = "0 35 17 3/5 * ?")
    public void cron() {
        System.out.println("时间1"+new Date());
        MimeMessage message = null;
        try {

            //Date date = new Date();
            //SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar instance = Calendar.getInstance();
            int month = instance.get(Calendar.MONTH);

            message = jms.createMimeMessage();
            System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
            helper.setFrom(from);
            helper.setTo("xucy@softinfor.com"); // 接收地址
            helper.setSubject(month+"月份报销单"); // 标题
            helper.setText("详情参见附件内容！"); // 内容
            // 传入附件
            File f = new File("D:\\19732\\OneDrive\\桌面\\个人文件\\安装包、文件\\报销单");
            File fa[] = f.listFiles();

            FileSystemResource file = new FileSystemResource(fa[0]);
            helper.addAttachment(fa[0].getName(), file);
            jms.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 每3分钟触发
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void initwljyredis() {
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
}
