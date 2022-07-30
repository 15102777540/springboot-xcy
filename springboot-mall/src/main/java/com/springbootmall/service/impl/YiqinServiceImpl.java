package com.springbootmall.service.impl;

import com.springbootmall.javaBeanMapper.CovConverter;
import com.springbootmall.mapper.CovMapper;
import com.springbootmall.pojo.dto.Cov;
import com.springbootmall.pojo.dto.CovDto;
import com.springbootmall.service.YiqinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class YiqinServiceImpl implements YiqinService {

    @Autowired
    CovMapper covMapper;

    @Override
    public List<CovDto> findAll() throws Exception{
        List<CovDto> list = new ArrayList<>();
        try {
            List<Cov> covs = covMapper.selectAll();
            return CovConverter.INSTANCE.toCovDtoList(covs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int add(CovDto covDto) {
        try {
            return covMapper.insert(CovConverter.INSTANCE.toCov(covDto));
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(CovDto covDto) {
        try {
            return covMapper.updateByPrimaryKeySelective(CovConverter.INSTANCE.toCov(covDto));
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Set<Long> ids) {
        return covMapper.deleteList(ids);
    }

    @Override
    public int insertList(List<CovDto> covDtoList) throws Exception {
        try {
            int i = covMapper.insertList(CovConverter.INSTANCE.toCovList(covDtoList));
            return i;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
