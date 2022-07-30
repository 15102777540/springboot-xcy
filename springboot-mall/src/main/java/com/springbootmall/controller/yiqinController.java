package com.springbootmall.controller;

import com.springbootmall.javaBeanMapper.CovMapConverter;
import com.springbootmall.pojo.dto.Cov;
import com.springbootmall.pojo.dto.CovDto;
import com.springbootmall.service.YiqinService;
import com.springbootmall.util.ExcelUtils;
import com.springbootmall.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/yiqin")
public class yiqinController {

    @Autowired
    YiqinService yiqinService;

    @RequestMapping("/upload")
    @ResponseBody
    public ResponseEntity<Object> upload(@RequestParam("name") String name,@RequestParam("file") MultipartFile file)throws Exception{
        Map<String,Object> map = new HashMap<>(2);
        List<Map> list = new ArrayList<>();
        list = ExcelUtils.readExcel(file);
        List<CovDto> covDtos = CovMapConverter.INSTANCE.toCovDtoList(list);
        for (CovDto item : covDtos) {
            item.setCreateBy("Admin");
        }
        int i = yiqinService.insertList(covDtos);
        map.put("state",i);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping("/getyiqin")
    @ResponseBody
    public ResponseEntity<Object> getyiqin()throws Exception{
        List<CovDto> all = yiqinService.findAll();
        return new ResponseEntity<>(PageUtil.toPage(all,all.size()),HttpStatus.OK);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public ResponseEntity<Object> edit(@RequestBody CovDto covDto)throws Exception{
        Map<String,Object> map = new HashMap<>(1);
        int i = yiqinService.update(covDto);
        map.put("state",i);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseEntity<Object> del(@RequestBody Set<Long> ids)throws Exception{
        Map<String,Object> map = new HashMap<>(1);
        int i = yiqinService.delete(ids);
        map.put("state",i);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
