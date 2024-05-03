package io.hiker.server.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.hiker")
@MapperScan(basePackages = {"io.hiker.server.core.mapper","io.hiker.server.api.mapper"})
public class MydemoServerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MydemoServerApiApplication.class, args);
    }

}
