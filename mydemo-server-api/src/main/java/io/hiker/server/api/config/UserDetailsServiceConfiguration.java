package io.hiker.server.api.config;

import io.hiker.server.api.mapper.DbUserMapper;
import io.hiker.server.api.security.DbUserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserDetailsServiceConfiguration {

    @Bean
    public DbUserDetailsManager dbUserDetailsManager(DbUserMapper dbUserMapper) {
        return new DbUserDetailsManager(dbUserMapper, bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
