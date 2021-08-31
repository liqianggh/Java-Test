package cn.mycookies.user;

public class ResultDTO<T> {
    private int code;   //响应状态码（在ReponseStatusEnum中已经预定义了一部分）

    private String msg; //响应状态说明

    private T data; //数据载荷

    private ResultDTO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 快速生成“成功”结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(
                NewResponseStatusEnum.SUCCESS.getCode(),
                NewResponseStatusEnum.SUCCESS.getMsg(),
                data
        );
    }
    /* ============ static tools ============= */

    /**
     * 快速生成“成功”结果
     */
    public static <T> ResultDTO<T> success() {
        return success(null);
    }

    /**
     * 快速生成“成功”结果
     * （自定义成功消息）
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> success(String msg, T data) {
        return new ResultDTO<>(
                NewResponseStatusEnum.SUCCESS.getCode(),
                msg, data
        );
    }

    /**
     * 快速生成“参数错误”结果
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> paramError() {
        return new ResultDTO<>(
                NewResponseStatusEnum.PARAM_ERROR.getCode(),
                NewResponseStatusEnum.PARAM_ERROR.getMsg(),
                null
        );
    }

    /**
     * 快速生成“参数错误”结果
     * （自定义错误消息）
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> paramError(String msg) {
        return new ResultDTO<>(
                NewResponseStatusEnum.PARAM_ERROR.getCode(),
                msg, null
        );
    }

    /**
     * 快速生成“服务器内部错误”结果
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> internalError() {
        return new ResultDTO<>(
                NewResponseStatusEnum.INTERNAL_ERROR.getCode(),
                NewResponseStatusEnum.INTERNAL_ERROR.getMsg(),
                null
        );
    }

    /**
     * 快速生成“服务器内部错误”结果
     * （自定义错误消息）
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> internalError(String msg) {
        return new ResultDTO<>(
                NewResponseStatusEnum.INTERNAL_ERROR.getCode(),
                msg, null
        );
    }

    /**
     * 快速生成自定义错误结果
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> customError(int code, String msg) {
        return new ResultDTO<>(code, msg, null);
    }

    public boolean isSuccess() {
        return code == NewResponseStatusEnum.SUCCESS.getCode();
    }

    /* ============ getters & setters ============= */

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}