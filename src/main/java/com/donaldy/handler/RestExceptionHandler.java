package com.donaldy.handler;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.donaldy.common.Const;
import com.donaldy.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Iterator;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    /**
     * 自定义运行异常
     * @param exception
     * @return
     */
    @ExceptionHandler(RestfulException.class)
    @ResponseBody
    public ServerResponse restfulExceptionHandler(RestfulException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ServerResponse.createByErrorCodeMessage(exception.getCode() ,exception.getMsg());
    }

    /**
     * 参数不完整(400)
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServerResponse methodExceptionHandler(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ServerResponse.createByErrorCodeMessage(Const.HttpStatusCode.BAD_REQUEST.getCode(),
                "这貌似不送给我的吧");
    }

    /**
     * 404的拦截.
     * @param exception
     * @return
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ServerResponse notFoundHandler(NoHandlerFoundException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ServerResponse.createByErrorCodeMessage(Const.HttpStatusCode.NOT_FOUND.getCode(),
                "真没找到这个资源哇！");
    }

    /**
     * 参数格式错误(400)
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ServerResponse numberFormatHandler(MethodArgumentTypeMismatchException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ServerResponse.createByErrorCodeMessage(Const.HttpStatusCode.BAD_REQUEST.getCode(),
                "我要的是红色的，你怎么给我绿色。");
    }

    /**
     * 校验JSR-303
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ServerResponse validate(ConstraintViolationException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        Iterator iter = exception.getConstraintViolations().iterator();
        ConstraintViolation constraintViolation = (ConstraintViolation) iter.next();
        return ServerResponse.createByErrorCodeMessage(Const.HttpStatusCode.BAD_REQUEST.getCode(),
                constraintViolation.getConstraintDescriptor().getMessageTemplate());
    }

    /**
     * 400错误
     * @param exception
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ServerResponse requestMissingHandler(MissingServletRequestParameterException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ServerResponse.createByErrorCodeMessage(Const.HttpStatusCode.BAD_REQUEST.getCode() ,
                "这快递不是我的");
    }

    /**
     * IO异常(500)
     * @param exception
     * @return
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ServerResponse ioExceptionHandler(IOException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ServerResponse.createByErrorCodeMessage(Const.HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(),
                "快递送错了");
    }

    /**
     * OSS client
     * @param exception
     * @return
     */
    @ExceptionHandler({ClientException.class})
    @ResponseBody
    public ServerResponse ossCreateExceptionHandler(OSSException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ServerResponse.createByErrorCodeMessage(Const.HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(),
                "网貌似断开了");
    }

    /**
     * OSS 异常(500)
     * @param exception
     * @return
     */
    @ExceptionHandler({OSSException.class})
    @ResponseBody
    public ServerResponse ossExceptionHandler(OSSException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ServerResponse.createByErrorCodeMessage(Const.HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(),
                exception.getMessage());
    }

    /**
     * 默认异常处理(500)
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ServerResponse serverErrorHandler(Exception exception) {
        log.error(exception.getMessage());
        log.error(String.valueOf(exception.getStackTrace()));
        exception.printStackTrace();
        return ServerResponse.createByErrorCodeMessage(Const.HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(),
                "好像出错了哦");
    }
}
