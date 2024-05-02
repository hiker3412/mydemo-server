package io.hiker.server.api.controller;

import io.hiker.server.core.model.Test;
import io.hiker.server.core.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/{userId}")
    public List<Test> test(@PathVariable Integer userId) {
        return testService.list();
    }
}
