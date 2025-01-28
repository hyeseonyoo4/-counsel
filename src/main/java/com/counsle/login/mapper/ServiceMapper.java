package com.nuriapp.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ServiceMapper {

    List<com.nuriapp.login.domain.Service> getService(@Param("id") String id, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
