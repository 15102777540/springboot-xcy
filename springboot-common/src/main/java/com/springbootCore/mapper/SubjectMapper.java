package com.springbootCore.mapper;

import com.springbootCore.pojo.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SubjectMapper {

    int insertList(@Param("list") List<Subject> covlist);

    List<Subject> selectByWhere(Map map);
}
