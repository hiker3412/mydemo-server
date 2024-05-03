package io.hiker.server.api.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class DbUser {
    @TableId
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
}
