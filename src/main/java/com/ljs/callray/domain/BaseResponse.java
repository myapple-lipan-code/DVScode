package com.ljs.callray.domain;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * API返回参数
 * @author Administrator
 *
 */
@ApiModel(value = "API返回参数")
public class BaseResponse {
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "响应消息", required = false)
    private String message;

    /**
     * 响应码：参考`ResultCode`
     */
    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    /**
     * 响应中的数据
     */
    @ApiModelProperty(value = "响应数据", required = false)
    private Object data;


    ////////////////定义成功和失败状态信息
    public static BaseResponse fail(String message) {
        return new BaseResponse(500, message, null);
    }
    public  static  BaseResponse success(String message){
        return  new BaseResponse(200,message,null);
    }
    public  static  BaseResponse success(String message, Object data){
        return  new BaseResponse(200,message,data);
    }
    public  static  BaseResponse success( Object data){
        return  new BaseResponse(200,"",data);
    }
    //////////////////

    /***
     * 自定义错误返回码
     *
     * @param code
     * @param message:
     * @return: BaseResponse
     */
    public static BaseResponse fail(Integer code, String message) {
        return new BaseResponse(code, message, null);
    }

    public static BaseResponse ok(String message) {
        return new BaseResponse(ResultCode.SUCCESS.getCode(), message, null);
    }

    public static BaseResponse ok() {
        return new BaseResponse(ResultCode.SUCCESS.getCode(), "OK", null);
    }

    public static BaseResponse build(Integer code, String msg, Object data) {
        return new BaseResponse(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static BaseResponse ok(String message, Object data) {
        return new BaseResponse(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 自定义返回码
     */
    public static BaseResponse ok(Integer code, String message) {
        return new BaseResponse(code, message);
    }



    /**
     * 自定义
     *
     * @param code：验证码
     * @param message：返回消息内容
     * @param data：返回数据
     * @return: BaseResponse
     */
    public static BaseResponse ok(Integer code, String message, Object data) {
        return new BaseResponse(code, message, data);
    }

    public BaseResponse() { }

    public static BaseResponse build(Integer code, String msg) {
        return new BaseResponse(code, msg, null);
    }

    public BaseResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public BaseResponse(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = "OK";
        this.data = data;
    }

    public BaseResponse(String message) {
        this(ResultCode.SUCCESS.getCode(), message, null);
    }

    public BaseResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum ResultCode {
        //成功
        SUCCESS( 200, "SUCCESS" ),
        //失败
        FAILURE( 500, "FAILURE" );




        private int code;
        private String desc;

        ResultCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}

