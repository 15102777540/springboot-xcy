package com.springbootmall.service;

import com.springbootmall.pojo.dto.CovDto;

import java.util.List;
import java.util.Set;

public interface YiqinService {

    List<CovDto> findAll() throws Exception;

    int add(CovDto covDto);

    int update(CovDto covDto);

    int delete(Set<Long> ids);

    int insertList(List<CovDto> covDtoList) throws Exception;

}
