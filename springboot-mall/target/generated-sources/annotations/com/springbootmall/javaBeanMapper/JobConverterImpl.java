package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Job;
import com.springbootmall.pojo.dto.JobSmallDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T15:05:13+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class JobConverterImpl implements JobConverter {

    @Override
    public JobSmallDto toMenuDto(Job menu) {
        if ( menu == null ) {
            return null;
        }

        JobSmallDto jobSmallDto = new JobSmallDto();

        jobSmallDto.setId( menu.getId() );
        jobSmallDto.setName( menu.getName() );

        return jobSmallDto;
    }

    @Override
    public List<JobSmallDto> toMenuDtoList(List<Job> menuList) {
        if ( menuList == null ) {
            return null;
        }

        List<JobSmallDto> list = new ArrayList<JobSmallDto>( menuList.size() );
        for ( Job job : menuList ) {
            list.add( toMenuDto( job ) );
        }

        return list;
    }
}
