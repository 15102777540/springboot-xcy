package com.springbootmall.mapper;

import com.springbootmall.pojo.dto.Cov;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface CovMapper {

    int deleteByPrimaryKey(Long id);

    int deleteList(@Param("ids") Set<Long> ids);

    int insert(Cov record);

    int insertList(@Param("list") List<Cov> covlist);

    int insertSelective(Cov record);

    Cov selectByPrimaryKey(Long id);

    List<Cov> selectAll();

    int updateByPrimaryKeySelective(Cov record);

    int updateByPrimaryKey(Cov record);
}
