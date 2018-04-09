package com.company.core.entity;

import java.math.BigDecimal;

public class RLSysUsrRoleDAO {
    private BigDecimal sysUsrId;

    private BigDecimal sysRoleId;

    public BigDecimal getSysUsrId() {
        return sysUsrId;
    }

    public void setSysUsrId(BigDecimal sysUsrId) {
        this.sysUsrId = sysUsrId;
    }

    public BigDecimal getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(BigDecimal sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
}