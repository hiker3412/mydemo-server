package io.hiker.server.api.config.security;

import com.alibaba.fastjson2.JSONObject;
import io.hiker.common.model.response.R;
import io.hiker.common.model.response.REnum;
import jakarta.servlet.ServletException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().getWriter().write(JSONObject.toJSONString(new R<>(REnum.SESSION_EXPIRED)));
    }
}
