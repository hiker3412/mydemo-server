package io.hiker.server.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.hiker.server.api.model.entity.DbUserAuthorityEntity;
import io.hiker.server.api.model.entity.DbUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbUserAuthorityMapper extends BaseMapper<DbUserAuthorityEntity> {
}
