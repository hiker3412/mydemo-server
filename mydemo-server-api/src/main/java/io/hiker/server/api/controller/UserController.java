package io.hiker.server.api.controller;

import io.hiker.common.model.response.R;
import io.hiker.server.api.model.entity.DbUserEntity;
import io.hiker.server.api.model.vo.DbUserVo;
import io.hiker.server.api.service.DbUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final DbUserService dbUserService;
    private final PasswordEncoder passwordEncoder;

    public UserController(DbUserService dbUserService,
                          PasswordEncoder passwordEncoder
    ) {
        this.dbUserService = dbUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create")
    public R<Void> create(@RequestBody DbUserVo vo) {
        DbUserEntity entity = new DbUserEntity()
                .setUsername(vo.getUsername())
                .setPassword(vo.getPassword());
        dbUserService.save(entity);
        return R.success();
    }

    @GetMapping("/list")
    public R<List<DbUserEntity>> list() {
        return R.success(dbUserService.list());
    }

}
