package cn.edu.nbpt.music.exception;

import cn.edu.nbpt.music.pojo.ErrorCode;

public class BizException extends RuntimeException {
    private Integer code;

    private String msg;

    public BizException() {
        super(ErrorCode.ERROR.getMsg());
        this.code = ErrorCode.ERROR.getCode();
        this.msg = ErrorCode.ERROR.getMsg();
    }

    public BizException(String msg) {
        super(msg);
        this.code = ErrorCode.ERROR.getCode();
        this.msg = msg;
    }

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public BizException(ErrorCode errorCode, String msg) {
        super(msg);
        this.code = errorCode.getCode();
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
