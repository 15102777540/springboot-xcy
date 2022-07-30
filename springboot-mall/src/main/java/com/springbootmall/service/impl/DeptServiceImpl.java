package com.springbootmall.service.impl;

import com.springbootmall.mapper.DeptMapper;
import com.springbootmall.pojo.dto.Dept;
import com.springbootmall.pojo.dto.DeptDto;
import com.springbootmall.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public DeptDto findById(Long id) {
        return null;
    }

    @Override
    public void create(Dept resources) {

    }

    @Override
    public void update(Dept resources) {

    }

    @Override
    public void delete(Set<DeptDto> deptDtos) {

    }

    @Override
    public List<Dept> findByPid(long pid) {
        return deptMapper.findByPid(pid);
    }

    @Override
    public Set<Dept> findByRoleId(Long id) {
        return (Set<Dept>) deptMapper.findByRoleId(id);
    }

    @Override
    public void download(List<DeptDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public Set<DeptDto> getDeleteDepts(List<Dept> deptList, Set<DeptDto> deptDtos) {
        return null;
    }

    @Override
    public List<DeptDto> getSuperior(DeptDto deptDto, List<Dept> depts) {
        return null;
    }

    @Override
    public Object buildTree(List<DeptDto> deptDtos) {
        return null;
    }

    @Override
    public List<Long> getDeptChildren(List<Dept> deptList) {
        List<Long> list = new ArrayList<>();
        deptList.forEach(dept -> {
                    if (dept != null && dept.getEnabled()) {
                        List<Dept> depts = deptMapper.findByPid(dept.getId());
                        if (depts.size() != 0) {
                            list.addAll(getDeptChildren(depts));
                        }
                        list.add(dept.getId());
                    }
                }
        );
        return list;
    }

    @Override
    public void verification(Set<DeptDto> deptDtos) {

    }
}
