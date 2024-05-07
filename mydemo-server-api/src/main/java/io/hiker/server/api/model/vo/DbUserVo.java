package io.hiker.server.api.model.vo;

import io.hiker.server.api.model.bo.DbUserBo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.authority.AuthorityUtils;

@Data
@Accessors(chain = true)
public class DbUserVo {
    //用户名
    private String username;
    private String password;

}
