package io.hiker.server.api.config.security;

import com.google.gson.Gson;
import io.hiker.common.model.response.R;
import io.hiker.common.model.response.REnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;

@Component
public class JsonAuthenticationFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE+ ";charset=UTF-8");
        response.getWriter().write(new Gson().toJson(new R<>(REnum.NOT_LOGIN).setMsg("登录失败")));
    }
}
