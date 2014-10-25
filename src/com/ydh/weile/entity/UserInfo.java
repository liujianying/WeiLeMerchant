package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/21.
 * 获取当前用户信息
 */
public class UserInfo {

//            "userId":"用户ID",
//            "userName":"中文姓名",
//            "loginName":"登录名",
//            "merchantName":"商户名",
//            "areaName":"区域名称",
//            "address":"商家地址",
//            "isSon":"是否操作员 0不是1是",
    private String userId;
    private String userName;
    private String merchantName;
    private String loginName;
    private String areaName;
    private String address;
    private String isSon;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsSon() {
        return isSon;
    }

    public void setIsSon(String isSon) {
        this.isSon = isSon;
    }
}
