package cn.edu.nbpt.music.pojo;

import lombok.Getter;

@Getter
public enum ErrorCode {

    /**
     * 异常分类种类、粒度看项目需要设定，无定论
     */
    // 枚举值需放第一行
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    INSERT_ERROR(501, "插入失败"),
    INSERT_NOT_FIND_ERROR(50101, "收藏歌曲不存在"),
    INSERT_EXIST_ERROR(50102, "目标歌曲已收藏"),
    UPDATE_ERROR(502, "更新失败"),
    DELETE_ERROR(503, "删除失败"),
    SELECT_ERROR(504, "查询失败"),
    FILE_OPERATE_ERROR(505, "文件操作失败"),
    PARAMETER_VALIDATE_ERROR(509, "参数校验失败"),
    TOKEN_INVALIDATE(50008, "token失效");

    private final Integer code;
    private final String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
