package io.hiker.server.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.hiker.server.api.model.bo.DbUserBo;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("user")
public class DbUserEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean enabled = true;

    public DbUserBo toBo() {
        return (DbUserBo) new DbUserBo()
                .setUsername(getUsername())
                .setPassword(getPassword())
                .setEnabled(isEnabled())
                .setId(getId());
    }
}
