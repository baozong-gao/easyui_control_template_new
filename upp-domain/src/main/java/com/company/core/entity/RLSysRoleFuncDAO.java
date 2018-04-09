package com.company.core.entity;

import java.math.BigDecimal;

public class RLSysRoleFuncDAO {
    private BigDecimal sysRoleId;

    private BigDecimal sysFuncId;

    public BigDecimal getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(BigDecimal sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public BigDecimal getSysFuncId() {
        return sysFuncId;
    }

    public void setSysFuncId(BigDecimal sysFuncId) {
        this.sysFuncId = sysFuncId;
    }
}