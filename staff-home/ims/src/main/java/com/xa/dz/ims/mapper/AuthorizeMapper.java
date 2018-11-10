package com.xa.dz.ims.mapper;

import com.xa.dz.ims.model.Authorize;
import com.xa.dz.ims.model.AuthorizeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorizeMapper {

    int countByExample(AuthorizeExample example);

    int deleteByExample(AuthorizeExample example);

    int insert(Authorize record);

    int insertSelective(Authorize record);

    List<Authorize> selectByExample(AuthorizeExample example);

    int updateByExampleSelective(@Param("record") Authorize record, @Param("example") AuthorizeExample example);

    int updateByExample(@Param("record") Authorize record, @Param("example") AuthorizeExample example);
}