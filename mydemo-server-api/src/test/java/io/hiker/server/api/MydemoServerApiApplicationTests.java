package io.hiker.server.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest
class MydemoServerApiApplicationTests {

    @Test
    void contextLoads() {
        String s = Base64.getEncoder().encodeToString("nacos@24nacos@24nacos@24nacos@24nacos@24".getBytes(StandardCharsets.UTF_8));
        System.out.println("woshi" + s);
    }

}
