package com.company.core.qrc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: simons.xiao
 * Date: 2018/2/24
 * Time: 上午1:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseBody implements Serializable{
    private String code;
    private String message;

    private String data;
    private String sign;

    public CommonResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponseBody(String data) {
        this.code = "200";
        this.message = "success";
        this.data = data;
    }
}