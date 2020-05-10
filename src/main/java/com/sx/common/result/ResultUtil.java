package com.sx.common.result;

/**
 *
 */
public class ResultUtil {

    /**
     * 响应成功 直接传入data
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setData(data);
        return result;
    }

    /**
     * 响应成功 无需返回数据
     */
    public static Result success() {
        return success("");
    }

    /**
     * 响应成功 自定义响应码、内容和数据
     */
    public static Result success(Integer code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 响应成功 自定义响应码和数据
     */
    public static Result success(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData("");
        return result;
    }

    /**
     * 响应成功 自定义响应信息和数据
     */
    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 响应失败 传入数据
     */
    public static Result error(Object data) {
        Result result = new Result();
        result.setCode(500);
        result.setMsg("error");
        result.setData(data);
        return result;
    }

    /**
     * 响应失败 无需传入数据
     */
    public static Result error() {
        return error("");
    }

    /**
     * 响应失败 自定义响应码、内容和数据
     */
    public static Result error(Integer code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 响应失败 自定义响应码和内容
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData("");
        return result;
    }

    /**
     * 响应失败 自定义响应内容和数据
     */
    public static Result error(String msg, Object data) {
        Result result = new Result();
        result.setCode(500);
        result.setMsg(msg);
        result.setData("");
        return result;
    }
}
