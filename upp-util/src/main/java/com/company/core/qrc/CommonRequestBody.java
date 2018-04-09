package com.company.core.qrc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: simons.xiao
 * Date: 2018/2/26
 * Time: 上午11:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonRequestBody implements Serializable{
    private String platformId;
    private String token;

    private String businessBody;
    private String businessBodySign;
}