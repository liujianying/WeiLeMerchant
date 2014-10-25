package com.ydh.weile.interfaces;

/**
 * Created by liujianying on 14/10/21.
 */
public interface NetCode {

    /* 网络错误连接超时 */
    public final int NetworkAnomaly = -1000001;
    /* 网络未连接 */
    public final int NoNetworkConnection = -1000002;
    /* 其他异常 */
    public final int OtrerException = -1000003;
    /* 请求成功 */
    public final int RequestSuccess = -1000000;
    /* 请求失败 */
    public final int RequestFailed = -999999;
    /* 页面不存 404 */
    public final int Not_Found = -1000004;
    /* 系统错误 */
    public final int System_Error = -1000005;

//    服务端接口返回错误是 如果错误码是 9999时  使用服务端返回的错误信息


}
