package io.hiker.server.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.hiker.server.api.mapper.DbUserAuthorityMapper;
import io.hiker.server.api.model.entity.DbUserAuthorityEntity;
import io.hiker.server.api.service.DbUserAuthorityService;
import org.springframework.stereotype.Service;

@Service
public class DbUserAuthorityServiceImpl
        extends ServiceImpl<DbUserAuthorityMapper, DbUserAuthorityEntity>
        implements DbUserAuthorityService {
}
