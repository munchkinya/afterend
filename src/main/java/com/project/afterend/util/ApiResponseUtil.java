package com.project.afterend.util;


import com.project.afterend.model.ApiResponse;
import com.project.afterend.model.ApiResponseEnum;

/**
 * web层统一返回工具类
 */
public class ApiResponseUtil {

    /**
     * 获取请求成功响应的ApiResponse
     * @param data
     * @return
     */
    public static ApiResponse getApiResponse(Object data) {
        return getApiResponse(data, ApiResponseEnum.SUCCESS.getErrCode(), ApiResponseEnum.SUCCESS.getErrMsg());
    }

    /**
     * 获取其他请求响应的ApiResponse
     * @param code
     * @param msg
     * @return
     */
    public static ApiResponse getApiResponse(int code,String msg) {
        return getApiResponse(null, code, msg);
    }

    /**
     * 枚举信息转统一返回对象
     * @param apiResponseEnum
     * @return
     */
    public static ApiResponse getApiResponse(ApiResponseEnum apiResponseEnum){
        return  getApiResponse(apiResponseEnum.getErrCode(),apiResponseEnum.getErrMsg());
    }
    public static ApiResponse getApiResponse(Object data, int code, String msg) {
        ApiResponse apiResponse = new ApiResponse(data);
        apiResponse.setErrCode(code);
        apiResponse.setErrMsg(msg);
        return apiResponse;
    }
}
