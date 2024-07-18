package cn.edu.nbpt.music.exception;

import cn.edu.nbpt.music.pojo.ErrorCode;
import cn.edu.nbpt.music.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 异常从上按下捕获，捕获后后续无法捕获
     *
     * @param e
     * @return
     */

    @ExceptionHandler(BizException.class)
    public Result BizExceptionHandler(BizException e) {
        e.printStackTrace();
        return Result.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public Result ExceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.error(ErrorCode.ERROR.getCode(), e.getMessage());
    }
}
