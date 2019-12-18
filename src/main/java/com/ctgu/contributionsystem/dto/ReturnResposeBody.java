package com.ctgu.contributionsystem.dto;

import java.io.Serializable;
import lombok.ToString;
/**
 * @Description 后端统一返回前端格式
 * @Author wh_lan
 * @create 2019-12-18 11:21
 * @ClassName ReturnResposeBody
 * @Version 1.0.0
 */
@ToString
public class ReturnResposeBody implements Serializable {

    private String status;//响应吗
    private String msg;//信息
    private Object result;
//    private String jwtToken;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

//    public String getJwtToken() {
//        return jwtToken;
//    }
//
//    public void setJwtToken(String jwtToken) {
//        this.jwtToken = jwtToken;
//    }

}
