package com.ydh.weile.net;

import com.ydh.weile.uitl.encryption.MD5Util;

import org.json.JSONException;

public class HttpRequestUrl {



    public static HttpRequestUrl httpRequestUrl = null;


	private static String BASEURL = ConfigurationUrl.COMMONURL+"WeiLeService?";


    public static HttpRequestUrl newUrl() {

        if(httpRequestUrl == null) {
            synchronized(HttpRequestUrl.class){
                if(httpRequestUrl == null)httpRequestUrl =new HttpRequestUrl();
            }
        }
        return httpRequestUrl;
    }

    private HttpRequestUrl() {

    }
	  

    /**
     * @登录接口
     * @param userName
     * @param password
     * @return
     */
    public final String getManagerLogin(String userName, String password)throws JSONException {
        return BASEURL + "act=managerLogin&sign=" + MD5Util.getMD5(HttpRequestBody.newBody().requestManagerLoginBody(userName, password));
    }

    /**
     * @退出接口requestManagerLogout
     * @return
     */
    public final String getRequestManagerLogout() {
        return BASEURL + "act=requestManagerLogout";
    }

    /**
     * @获取用户信息接口
     */
    public final String getAppGetUserInfo() {
        return BASEURL + "act=appGetUserInfo";
    }

    /**
     * @获取更新版本升级接口
     * @return
     */
    public final String getAppVersionUpdate() {
        return BASEURL + "act=appVersionUpdate";
    }

    /**
     * @今日收款金额,今日卡券消费数
     * @return
     */
    public final String getAppNowDayConsumeInfo() { return BASEURL + "act=appNowDayConsumeInfo";  }

    /**
     * @消息中心
     * @return
     */
    public final String getAppUntreatedMessage() {
        return BASEURL + "act=appUntreatedMessage";
    }

    /**
     * @获取会员卡详情
     * @return
     */
    public final String getAppGetMcardInfo() { return BASEURL + "act=appGetMcardInfo"; }


    /**
     * @获取代金券券详情
     * @return
     */
    public final String getAppGetCashcouponInfo() { return BASEURL + "act=appGetCashcouponInfo"; }


    /**
     * @获取优惠券详情
     * @return
     */
    public final String getAppGetCouponInfo() { return BASEURL + "act=appGetCouponInfo"; }


    /**
     * @获取储值卡详情
     * @return
     */
    public final String getAppGetVcardInfo() { return BASEURL + "act=appGetVcardInfo"; }

    /**
     * @获取会员卡申请使用
     * @return
     */
    public final String getAppGetApplyMcardInfo() { return BASEURL + "act=appGetApplyMcardInfo"; }

    /**
     * @获取代金券申请使用
     * @return
     */
    public final String getAppGetApplyCashcouponInfo() { return BASEURL + "act=appGetApplyCashcouponInfo"; }

    /**
     * @获取优惠券申请使用
     * @return
     */
    public final String getAppGetApplyCouponInfo() { return BASEURL + "act=appGetApplyCouponInfo"; }

    /**
     * @获取储值卡申请支付
     * @return
     */
    public final String getAppGetApplyVcardInfo() { return BASEURL + "act=appGetApplyVcardInfo"; }

    /**
     * 会员卡使用审核
     * @return
     */
    public final String getAppApprovalMcard() { return BASEURL + "act=appApprovalMcard"; }

    /**
     * @储值卡使用审核
     * @return
     */
    public final String getAppApprovalVcard() { return BASEURL + "act=appApprovalVcard"; }

    /**
     * @代金券使用审核act= appApprovalCashcoupon
     * @return
     */
    public final String getAppApprovalCashcoupon() { return BASEURL + "act=appApprovalCashcoupon"; }

    /**
     * 优惠券使用审核act= appApprovalCoupon
     * @return
     */
    public final String getAppApprovalCoupon() { return BASEURL + "act=appApprovalCoupon"; }



    /**
     * 交易记录
     * @return
     */
    public final String getAppCardDealResultList() { return BASEURL + "act=appCardDealResultList"; }


}
