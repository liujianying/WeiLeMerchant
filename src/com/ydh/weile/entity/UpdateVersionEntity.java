package com.ydh.weile.entity;

/**
 * Created by liujianying on 14/10/21.
 * @版本升级实体类
 */
public class UpdateVersionEntity {

//            " versionCode ":"版本号",
//            " versionURL ":"版本下载URL",
//            " versionSize ":"版本大小",
//            " updateTime ":"版本更新",
//            " forceUpdate ":"是否强制更新",
//            " updateContent ":"更新内容",
//            " appStoreUrl ":"暂时不用",

    private String versionCode;
    private String versionURL;
    private String versionSize;
    private String updateTime;
    private String forceUpdate;
    private String updateContent;
    private String appStoreUrl;


    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionURL() {
        return versionURL;
    }

    public void setVersionURL(String versionURL) {
        this.versionURL = versionURL;
    }

    public String getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(String versionSize) {
        this.versionSize = versionSize;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(String forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getAppStoreUrl() {
        return appStoreUrl;
    }

    public void setAppStoreUrl(String appStoreUrl) {
        this.appStoreUrl = appStoreUrl;
    }
}
