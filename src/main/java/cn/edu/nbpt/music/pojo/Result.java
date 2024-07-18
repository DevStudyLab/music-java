package cn.edu.nbpt.music.pojo;

public class Result<T> {
    private Integer code;

    private T data;

    private String msg;

    public Result(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static <K> Result<K> success(K data) {
        return new Result(ErrorCode.SUCCESS.getCode(), data, ErrorCode.SUCCESS.getMsg());
    }

    public static <K> Result<K> error(Integer code, String msg) {
        return new Result(code, null, msg);
    }

    public static <K> Result<K> error(ErrorCode errorCode) {
        return new Result(errorCode.getCode(), null, errorCode.getMsg());
    }
}
