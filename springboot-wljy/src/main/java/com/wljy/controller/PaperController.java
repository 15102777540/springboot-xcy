package com.wljy.controller;

import com.wljy.pojo.Question;
import com.wljy.pojo.Subject;
import com.wljy.services.PaperService;
import com.wljy.util.RedisUtils;
import com.wljy.util.vo.PaperVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("api/wljy")
public class PaperController {

    @Autowired
    PaperService paperService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取答案
     *
     * @return
     */
    @PostMapping("/getAnswer")
    @ResponseBody
    public List<Question> getAnswer(String data,HttpServletRequest request) {
        List<Question> list = new ArrayList<>();
        list = paperService.getSelectQuestion(data);
        return list;
    }

    /**
     * 新增答案
     * @param data
     * @return
     */
    @PostMapping("/addQues")
    @ResponseBody
    public Boolean addQues(String data) {
        int i = paperService.addQuestion(data);
        return i > 0;
    }

    /**
     * 新增答案接口
     * @param data
     * @return
     */
    @PostMapping("/addQuesapi")
    @ResponseBody
    public Boolean addQuesapi(@RequestBody PaperVo data) {
        int i = paperService.addQuestionapi(data);
        return i > 0;
    }

    /**
     * 获取课程
     *
     * @return
     */
    @GetMapping("/getsubject")
    @ResponseBody
    public List<Subject> getsubject() throws Exception {
        List<Subject> list = new ArrayList<>();
        list = paperService.findSubjectAll();
        return list;
    }

    /**
     * 搜题
     *
     * @return
     */
    @GetMapping("/getSearch")
    @ResponseBody
    public List<Question> getSearch(@Param("data") String data, @Param("sno") String sno) throws Exception {
        List<Question> list = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("subjectNo",sno);
        map.put("queContent",data);
        list = paperService.findQuestion(map);
        return list;
    }

    @PostMapping("/getInfo")
    @ResponseBody
    public List<Question> getInfo(@Param("sno") String sno) throws Exception {
        List<Question> list = new ArrayList<>();
        try {
            list = paperService.findQueContent(sno);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    @PostMapping("/setValueList")
    @ResponseBody
    public boolean setValueList(@RequestBody Map data) throws Exception {
        try {
            String k = data.get("key").toString();
            String v = data.get("value").toString();
            if(redisUtils.hasKey(k)){
                String o = (String)redisUtils.get(k);
                o += "\n" + v;
                redisUtils.set(k,o);
            }else{
                redisUtils.set(k,v);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("/getValueList")
    @ResponseBody
    public List<String> getValueList(@Param("keya") String keya) throws Exception {
        List<String> list = new ArrayList<>();
        if(redisUtils.hasKey(keya)){
            String o = (String)redisUtils.get(keya);
            list = Arrays.asList(o.split("\n"));
            return list;
        }
        return list;
    }

    @PostMapping("/getValuekey")
    @ResponseBody
    public List<String> getValuekey(String k) throws Exception {
        List<String> list = new ArrayList<>();
        Set<Object> findkeys = redisUtils.findkeys(k + "*");
        ArrayList<Object> objects = new ArrayList<>(findkeys);
        for (Object object : objects) {
            list.add((String)object);
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                /*String s1 = o1.split("_")[1];
                String s2 = o2.split("_")[1];*/
                return o2.compareTo(o1);
            }
        });
        return list;
    }
}
