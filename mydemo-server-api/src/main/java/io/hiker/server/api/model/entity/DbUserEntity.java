package io.hiker.server.api.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.hiker.common.model.entity.EntityCommonField;
import io.hiker.server.api.model.bo.DbUserBo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("user")
public class DbUserEntity extends EntityCommonField {
    private String username;
    private String password;
    private boolean enabled = true;
}
