package io.hiker.server.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
public class WebSecurityConfiguration {


    @Bean
    public SecurityFilterChain mydemoSecurityFilterChain(
            HttpSecurity security,
            AuthenticationFailureHandler authenticationFailureHandler,
            LogoutSuccessHandler logoutSuccessHandler,
            AuthenticationEntryPoint authenticationEntryPoint,
            AccessDeniedHandler accessDeniedHandler,
            SessionInformationExpiredStrategy sessionInformationExpiredStrategy
    ) throws Exception {
        security
                .authorizeHttpRequests(authorizationConfigure ->
                        authorizationConfigure
                                .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionConfigurer ->
                        exceptionConfigurer
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .formLogin(authenticationConfigure ->
                        authenticationConfigure
                                .failureHandler(authenticationFailureHandler)
                )
                .sessionManagement(sessionConfigure ->
                        sessionConfigure
                                .maximumSessions(2)
                                .expiredSessionStrategy(sessionInformationExpiredStrategy))
                .logout(logoutConfigure ->
                        logoutConfigure
                                .logoutSuccessHandler(logoutSuccessHandler)
                )
                .cors(Customizer.withDefaults())
                .csrf(csrfConfigure ->
                        csrfConfigure.disable()
                );
        DefaultSecurityFilterChain build = security.build();
        return build;
    }

//    @Bean
//    public UserDetailsService dbUserDetailsManager(DbUserMapper dbUserMapper) {
//        return new DbUserDetailsManager(dbUserMapper, bCryptPasswordEncoder());
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
