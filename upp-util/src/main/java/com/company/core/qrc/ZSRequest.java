package com.company.core.qrc;

/**
 * Created with IntelliJ IDEA.
 * User: simons.xiao
 * Date: 2018/3/10
 * Time: 下午11:08
 */
public class ZSRequest {
    private String merchantId;
    private String orderId;
    private String orderDate;
    private String orderType;
    private String orderAmount;
    private String orderSense;
    private String goodsDesc;
    private String callBackUrl;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderSense() {
        return orderSense;
    }

    public void setOrderSense(String orderSense) {
        this.orderSense = orderSense;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }
}