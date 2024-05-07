package io.hiker.server.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.hiker.common.model.response.REnum;
import io.hiker.common.model.response.RException;
import io.hiker.server.api.mapper.DbUserMapper;
import io.hiker.server.api.model.entity.DbUserEntity;
import io.hiker.server.api.service.DbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class DbUserServiceImpl extends ServiceImpl<DbUserMapper, DbUserEntity> implements DbUserService {
    private final DbUserMapper dbUserMapper;
    private final PasswordEncoder passwordEncoder;

    public DbUserServiceImpl(DbUserMapper dbUserMapper,
                             PasswordEncoder passwordEncoder
    ) {
        this.dbUserMapper = dbUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserDetails user) {
        DbUserEntity dbUserEntity = new DbUserEntity()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setEnabled(user.isEnabled());
        return save(dbUserEntity);
    }

    public boolean save(DbUserEntity entity) {
        if (StringUtils.isBlank(entity.getUsername()) || StringUtils.isBlank(entity.getPassword())) {
            throw new RException(REnum.CLIENT_FAIL, "用户名或密码不能为空");
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return SqlHelper.retBool(dbUserMapper.insert(entity));
    }
}
