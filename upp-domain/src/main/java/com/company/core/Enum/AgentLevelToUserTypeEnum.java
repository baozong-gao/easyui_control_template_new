package com.company.core.Enum;

import org.apache.commons.lang.StringUtils;

/**
 * Created by lijinzhu on 2018/3/28.
 */
public enum AgentLevelToUserTypeEnum {

    ONE_AGENT("1","00","一级代理商"),
    TWO_AGENT("2","00","二级代理商"),
    THR_AGENT("3","00","三级代理商"),

    ;
    private String agentLevel;
    private String userType;
    private String levelName;

    AgentLevelToUserTypeEnum(String agentLevel,String userType,String levelName){
        this.agentLevel = agentLevel;
        this.userType = userType;
        this.levelName = levelName;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public static AgentLevelToUserTypeEnum getEnumByLevel(String agentLevel){
        for(AgentLevelToUserTypeEnum itemEnum : AgentLevelToUserTypeEnum.values()){
            if(StringUtils.equals(agentLevel, itemEnum.getAgentLevel())){
                return itemEnum;
            }
        }
        return null;
    }

}
