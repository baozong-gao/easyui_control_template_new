package com.company.core.bo;

import com.company.core.vo.BaseForm;
import lombok.Data;

/**
 * @Author: gaobaozong
 * @Description: 用于查询
 * @Date: Created in 2017/12/21 - 14:02
 * @Version: V1.0-SNAPSHOT
 */
@Data
public class UserBO extends BaseForm<UserBO> {

    private String id;

    private String instId;

    private String userType;

    private String name;

    private String pwd;

    private String remark;

    private String userStatus;

    private String email;

    private String userTypeId;

    private String agentId;

}
