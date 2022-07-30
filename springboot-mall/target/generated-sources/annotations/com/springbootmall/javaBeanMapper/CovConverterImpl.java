package com.springbootmall.javaBeanMapper;

import com.springbootmall.pojo.dto.Cov;
import com.springbootmall.pojo.dto.CovDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T15:05:13+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class CovConverterImpl implements CovConverter {

    @Override
    public CovDto toCovDto(Cov cov) {
        if ( cov == null ) {
            return null;
        }

        CovDto covDto = new CovDto();

        covDto.setCreateBy( cov.getCreateBy() );
        covDto.setUpdateBy( cov.getUpdateBy() );
        covDto.setCreateTime( cov.getCreateTime() );
        covDto.setUpdateTime( cov.getUpdateTime() );
        covDto.setId( cov.getId() );
        covDto.setProvince( cov.getProvince() );
        covDto.setDate( cov.getDate() );
        covDto.setConfirmAdd( cov.getConfirmAdd() );
        covDto.setConfirm( cov.getConfirm() );
        covDto.setHeal( cov.getHeal() );
        covDto.setDead( cov.getDead() );
        covDto.setNewdiagnose( cov.getNewdiagnose() );
        covDto.setAsymptomatic( cov.getAsymptomatic() );
        covDto.setAsymptomaticAdd( cov.getAsymptomaticAdd() );

        return covDto;
    }

    @Override
    public List<CovDto> toCovDtoList(List<Cov> covList) {
        if ( covList == null ) {
            return null;
        }

        List<CovDto> list = new ArrayList<CovDto>( covList.size() );
        for ( Cov cov : covList ) {
            list.add( toCovDto( cov ) );
        }

        return list;
    }

    @Override
    public Cov toCov(CovDto cov) {
        if ( cov == null ) {
            return null;
        }

        Cov cov1 = new Cov();

        cov1.setCreateBy( cov.getCreateBy() );
        cov1.setUpdateBy( cov.getUpdateBy() );
        cov1.setCreateTime( cov.getCreateTime() );
        cov1.setUpdateTime( cov.getUpdateTime() );
        cov1.setId( cov.getId() );
        cov1.setProvince( cov.getProvince() );
        cov1.setDate( cov.getDate() );
        cov1.setConfirmAdd( cov.getConfirmAdd() );
        cov1.setConfirm( cov.getConfirm() );
        cov1.setHeal( cov.getHeal() );
        cov1.setDead( cov.getDead() );
        cov1.setNewdiagnose( cov.getNewdiagnose() );
        cov1.setAsymptomatic( cov.getAsymptomatic() );
        cov1.setAsymptomaticAdd( cov.getAsymptomaticAdd() );

        return cov1;
    }

    @Override
    public List<Cov> toCovList(List<CovDto> covList) {
        if ( covList == null ) {
            return null;
        }

        List<Cov> list = new ArrayList<Cov>( covList.size() );
        for ( CovDto covDto : covList ) {
            list.add( toCov( covDto ) );
        }

        return list;
    }
}
