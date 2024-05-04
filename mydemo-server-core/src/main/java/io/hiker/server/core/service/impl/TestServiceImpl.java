package io.hiker.server.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.hiker.server.core.model.entity.Test;
import io.hiker.server.core.service.TestService;
import io.hiker.server.core.mapper.TestMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【test】的数据库操作Service实现
* @createDate 2024-05-02 15:51:06
*/
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService{

}




