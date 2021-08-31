package cn.mycookies.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 和前端约定的新的常用响应值枚举
 * 后端所有参数错误code统一设为2
 * 服务器内部错误code统一设为10
 */
public enum NewResponseStatusEnum {

    SUCCESS(0, "成功"),
    PARAM_ERROR(2, "参数错误"),
    INTERNAL_ERROR(10, "服务器内部错误");

    //构建内部静态映射
    private static Map<Integer, NewResponseStatusEnum> enumMap = new HashMap<>();

    static {
        Arrays.stream(NewResponseStatusEnum.values())
                .forEach(status -> enumMap.put(status.code, status));
    }

    private int code;   //状态码
    private String msg; //状态说明

    NewResponseStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code转换为枚举
     *
     * @param code
     * @return
     */
    public static NewResponseStatusEnum of(Integer code) {
        return enumMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
