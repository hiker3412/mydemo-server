package io.hiker.server.api.config;

import com.alibaba.fastjson2.JSON;
import io.hiker.common.model.response.R;
import io.hiker.common.model.response.REnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;

@Configuration
public class JsonAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE+ ";charset=UTF-8");
        String result = JSON.toJSONString(R.from(REnum.NOT_AUTHORITY));
        response.getWriter().write(result);
    }
}
