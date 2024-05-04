package io.hiker.server.api.controller;

import io.hiker.common.model.response.R;
import io.hiker.server.core.model.entity.Test;
import io.hiker.server.core.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/{userId}")
    public R<List<Test>> test(@PathVariable Integer userId) {
        return R.success(testService.list());
    }
}
