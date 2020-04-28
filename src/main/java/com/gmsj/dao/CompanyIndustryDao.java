package com.gmsj.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.gmsj.model.po.CompanyIndustryPo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * @author baojieren
 * @date 2020/4/24 10:27
 */
public interface CompanyIndustryDao extends Mapper<CompanyIndustryPo> {

    int deleteByCompanyId(@Param("companyId")Integer companyId);

    List<CompanyIndustryPo> selectAllByCompanyId(@Param("companyId")Integer companyId);

}