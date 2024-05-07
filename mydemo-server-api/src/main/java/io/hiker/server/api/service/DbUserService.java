package io.hiker.server.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.hiker.server.api.model.entity.DbUserEntity;
import org.springframework.security.core.userdetails.UserDetails;


public interface DbUserService extends IService<DbUserEntity> {

    boolean save(UserDetails user);
}
