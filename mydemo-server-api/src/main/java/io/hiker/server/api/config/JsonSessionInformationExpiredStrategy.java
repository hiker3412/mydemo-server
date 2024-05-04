package io.hiker.server.api.config;

import com.alibaba.fastjson2.JSONObject;
import io.hiker.common.model.response.R;
import io.hiker.common.model.response.REnum;
import jakarta.servlet.ServletException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

@Configuration
public class JsonSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().getWriter().write(JSONObject.toJSONString(R.from(REnum.SESSION_EXPIRED)));
    }
}
