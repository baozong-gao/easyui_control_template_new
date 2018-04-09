package com.company.core.qrc;

/**
 * Created with IntelliJ IDEA.
 * User: simons.xiao
 * Date: 2018/3/11
 * Time: 上午10:59
 */
public class ZSResponse {
    private String merchantId;
    private String orderDate;
    private String orderId;
    private String orderAmount;
    private String orderType;
    private String qrcCode;
    private String payStatus;
    private String systemOrderId;
    private String respCode;
    private String respMessage;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getQrcCode() {
        return qrcCode;
    }

    public void setQrcCode(String qrcCode) {
        this.qrcCode = qrcCode;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getSystemOrderId() {
        return systemOrderId;
    }

    public void setSystemOrderId(String systemOrderId) {
        this.systemOrderId = systemOrderId;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }
}
