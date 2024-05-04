package io.hiker.server.api.controller;

import io.hiker.common.model.response.R;
import io.hiker.common.model.response.REnum;
import io.hiker.server.api.model.vo.DbUserVo;
import io.hiker.server.api.service.DbUserDetailsManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final DbUserDetailsManager dbUserDetailsManager;

    public UserController(DbUserDetailsManager dbUserDetailsManager) {
        this.dbUserDetailsManager = dbUserDetailsManager;
    }

    @PostMapping("/create")
    public R<Void> create(@RequestBody DbUserVo userVo) {
        try {
            if (StringUtils.isBlank(userVo.getUsername()) || StringUtils.isBlank(userVo.getPassword())) {
                return R.clientFail("用户名或密码不能为空");
            }
            dbUserDetailsManager.createUser(userVo.toBo());
            return R.success();
        } catch (Exception e) {
            return new R<Void>(REnum.FAIL).setMsg("创建用户失败");
        }
    }

}
