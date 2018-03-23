package com.xsh.blog.dao;

import com.xsh.blog.model.Vo.LogVo;
import com.xsh.blog.model.Vo.LogVoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LogVoMapper {
    long countByExample(LogVoExample example);

    int deleteByExample(LogVoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LogVo record);

    int insertSelective(LogVo record);

    List<LogVo> selectByExample(LogVoExample example);

    LogVo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LogVo record, @Param("example") LogVoExample example);

    int updateByExample(@Param("record") LogVo record, @Param("example") LogVoExample example);

    int updateByPrimaryKeySelective(LogVo record);

    int updateByPrimaryKey(LogVo record);
}