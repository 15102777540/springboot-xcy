package com.springbootCore.mapper;

import com.springbootCore.pojo.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionMapper {

    int insertList(@Param("list") List<Question> covlist);

    int updateList(@Param("list") List<Question> covlist);

    List<Question> selectByWhere(Map map);

    List<Question> selectByname(Map map);

    List<Question> selectByqueContent(@Param("queNo")String queNo);
}
