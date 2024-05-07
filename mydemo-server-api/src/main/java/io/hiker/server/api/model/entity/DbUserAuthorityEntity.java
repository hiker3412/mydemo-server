package io.hiker.server.api.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.hiker.common.model.entity.EntityCommonField;
import io.hiker.server.api.model.bo.DbUserBo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("user_authority")
public class DbUserAuthorityEntity extends EntityCommonField {
    private String userid;
    private String authority;

}
