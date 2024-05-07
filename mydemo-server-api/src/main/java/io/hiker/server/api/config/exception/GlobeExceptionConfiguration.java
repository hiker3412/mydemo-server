package io.hiker.server.api.config.exception;

import io.hiker.common.model.response.R;
import io.hiker.common.model.response.RException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobeExceptionConfiguration {

    @ExceptionHandler({RException.class})
    @ResponseBody
    public R<Void> rExceptionHandler(RException e) {
        log.error("controller exception : ",e);
        return e.toR();
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public R<Void> exceptionHandler(Exception e) {
        log.error("controller exception : ",e);
        return R.fail();
    }

}
