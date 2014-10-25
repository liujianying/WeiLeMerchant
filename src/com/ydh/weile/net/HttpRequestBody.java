package com.ydh.weile.net;

import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.system.config.SystemVal;
import com.ydh.weile.uitl.encryption.AESCrypto;
import com.ydh.weile.uitl.encryption.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequestBody {



    public static HttpRequestBody httpRequestBody = null;

    public static HttpRequestBody newBody() {

        if(httpRequestBody == null) {
            synchronized(HttpRequestBody.class){
                if(httpRequestBody == null)httpRequestBody =new HttpRequestBody();
            }
        }
        return httpRequestBody;
    }

    private HttpRequestBody() {

    }

	/**
	 * 加密data数据
	 *
	 * @param data
	 * @return
	 */
	private static String encrypt(String data) {

		return AESCrypto.encrypt(SystemVal.getLoginInfo().getKey(), data.toString());

	}
//
//	/**
//	 * 加密data数据
//	 *
//	 * @param data
//	 * @return
//	 */
//	private static String encryptArray(String data) {
//
//		return AESCrypto.encrypt(SystemVal.getAuthKey(), data.toString());
//
//	}

    /**
     * 请求登录接口
     * @param userName
     * @param password
     * @return
     * @throws JSONException
     */
    public String requestManagerLoginBody(String userName, String password) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("userName", userName);
        data.put("password", MD5Util.getMD5(password) );
        JSONObject request = new JSONObject();
        request.put("data", data);
        request.put("encryptType", 2);
        return request.toString();
    }

    /**
     * 退出接口
     * @return
     * @throws JSONException
     */
    public String requestManagerLogout() throws JSONException {
        return requestData(0);
    }

    /**
     * 获取用户信息
     * @return
     * @throws JSONException
     */
    public String requestAppGetUserInfo() throws JSONException  {
        return requestData(1);
    }

    /**
     * 获取更新版本升级接口
     * @return
     * @throws JSONException
     * type 平台类型 1安卓2 ios”
     */
    public String requestAppVersionUpdate() throws JSONException  {
        JSONObject request = new JSONObject();
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("data", encrypt("{}"));
        request.put("encryptType", 1);
        request.put("type", 1);

        return request.toString();
    }


    /**
     * 今日收款金额,今日卡券消费数
     * @return
     * @throws JSONException
     */
    public String requestappNowDayConsumeInfo() throws JSONException {
        return requestData(1);
    }

    /**
     * 未处理消息中心
     * @param pageCount     每页显示数
     * @param crrentIndex   开始页数”
     * @return
     * @throws JSONException
     */
    public String requestappUntreatedMessage(int pageCount, int crrentIndex) throws JSONException  {
        JSONObject data = new JSONObject();
        data.put("currentIndex", crrentIndex);
        data.put("pageCount", pageCount);
        JSONObject request = new JSONObject();
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("data", encrypt(data.toString()));
        request.put("encryptType", 1);
        return request.toString();
    }

    /**
     * @获取会员卡详情
     * @param mcardId 会员卡ID
     * @return
     * @throws JSONException
     */
    public String requestAppGetMcardInfo(String mcardId) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("mcardId", mcardId);
        JSONObject request = new JSONObject();
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("data", encrypt(data.toString()));
        request.put("encryptType", 1);
        return request.toString();
    }

    /**
     * @获取代金券券详情
     * @param cashcouponId 代金券ID,
     * @return
     */
    public String requestAppGetCashcouponInfo(String cashcouponId) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("cashcouponId", cashcouponId);
        JSONObject request = new JSONObject();
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("data", encrypt(data.toString()));
        request.put("encryptType", 1);
        return request.toString();
    }

    /**
     * @获取优惠券详情
     * @param couponId 优惠券ID
     * @return
     */
    public String requestAppGetCouponInfo(String couponId) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("couponId", couponId);
        JSONObject request = new JSONObject();
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("data", encrypt(data.toString()));
        request.put("encryptType", 1);
        return request.toString();
    }

    /**
     * @获取储值卡详情
     * @param vcardId 储值卡ID
     * @return
     * @throws JSONException
     */
    public String requestAppGetVcardInfo(String vcardId) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("vcardId", vcardId);
        JSONObject request = new JSONObject();
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("data", encrypt(data.toString()));
        request.put("encryptType", 1);
        return request.toString();
    }

    /**
     * @获取会员卡申请使用
     * @param id ID(消息中心返回的ID)
     * @return
     * @throws JSONException
     */
    public String requestAppGetApplyMcardInfo(String id) throws JSONException {
        return getCartInfo(id);
    }

    /**
     * @获取代金券申请使用
     * @param id
     * @return
     * @throws JSONException
     */
    public String requestAppGetApplyCashcouponInfo(String id) throws JSONException {
        return getCartInfo(id);
    }

    /**
     * @获取优惠券申请使用
     * @param id
     * @return
     * @throws JSONException
     */
    public String requestAppGetApplyCouponInfo(String id) throws JSONException {
        return getCartInfo(id);
    }

    /**
     * @获取储值卡申请支付
     * @param id
     * @return
     * @throws JSONException
     */
    public String requestAppGetApplyVcardInfo(String id) throws JSONException {
        return getCartInfo(id);
    }


    /**
     * @会员卡使用审核
     * @param idType           1会员卡号,2流水ID,(2流水ID 的使用场景为用户发起的,消息中心进来的)
     * @param no               会员卡号,
     * @param id               流水ID,
     * @param integralBalance  赠送积分
     * @param consumeAmount    消费金额
     * @param approvalResult   审核结果（0：不通过，1：通过）”,
     * @param approvalDesc     审核说明
     * @return
     * @throws JSONException
     */
    public String requestAppApprovalMcard(String idType, String no, String id, String integralBalance,String consumeAmount, String approvalResult, String approvalDesc) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("idType", idType);
        data.put("no", no);
        data.put("id", id);
        data.put("integralBalance", integralBalance);
        data.put("consumeAmount", consumeAmount);
        data.put("approvalResult", approvalResult);
        data.put("approvalDesc", approvalDesc);
        JSONObject request = new JSONObject();
        request.put("data", encrypt(data.toString()));
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("encryptType", 1);
        return request.toString();
    }

    /**
     * 储值卡使用审核
     * @param idType
     * @param no
     * @param id
     * @param integralBalance
     * @param consumeAmount
     * @param approvalResult
     * @param approvalDesc
     * @return
     * @throws JSONException
     */
    public String requestAppApprovalVcard(String idType, String no, String id, String integralBalance,String consumeAmount, String approvalResult, String approvalDesc) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("idType", idType);
        data.put("no", no);
        data.put("id", id);
        data.put("integralBalance", integralBalance);
        data.put("consumeAmount", consumeAmount);
        data.put("approvalResult", approvalResult);
        data.put("approvalDesc", approvalDesc);
        JSONObject request = new JSONObject();
        request.put("data", encrypt(data.toString()));
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("encryptType", 1);
        return request.toString();
    }


    /**
     * @代金券使用审核
     * @param idType           1卡号,2流水ID,(2流水ID 的使用场景为用户发起的,消息中心进来的)
     * @param no               卡号,
     * @param id               流水ID,
     * @param approvalResult   审核结果（0：不通过，1：通过）”
     * @param approvalDesc     审核说明”
     * @return
     * @throws JSONException
     */
    public String requestAppApprovalCashcoupon(String idType, String no, String id, String approvalResult, String approvalDesc) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("idType", idType);
        data.put("no", no);
        data.put("id", id);
        data.put("approvalResult", approvalResult);
        data.put("approvalDesc", approvalDesc);
        JSONObject request = new JSONObject();
        request.put("data", encrypt(data.toString()));
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("encryptType", 1);
        return request.toString();
    }

    /**
     * @优惠券使用审核
     * @param idType           1卡号,2流水ID,(2流水ID 的使用场景为用户发起的,消息中心进来的)
     * @param no               卡号,
     * @param id               流水ID,
     * @param approvalResult   审核结果（0：不通过，1：通过）”
     * @param approvalDesc     审核说明”
     * @return
     * @throws JSONException
     */
    public String requestAppApprovalCoupon(String idType, String no, String id, String approvalResult, String approvalDesc) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("idType", idType);
        data.put("no", no);
        data.put("id", id);
        data.put("approvalResult", approvalResult);
        data.put("approvalDesc", approvalDesc);
        JSONObject request = new JSONObject();
        request.put("data", encrypt(data.toString()));
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("encryptType", 1);
        return request.toString();
    }

    /**
     * @交易记录
     * @param currentIndex 开始页数
     * @param pageCount    每页显示数
     * @param cardType     卡券类型 0全部,1储值卡2代金券3会员卡4优惠券”
     * @return
     * @throws JSONException
     */
    public String requestAppCardDealResultList(int currentIndex, int pageCount, int cardType) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("currentIndex", currentIndex);
        data.put("pageCount", pageCount);
        data.put("cardType", cardType);
        JSONObject request = new JSONObject();
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("data", encrypt(data.toString()));
        request.put("encryptType", 1);
        return request.toString();
    }






    private String getCartInfo(String id) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("id", id);
        JSONObject request = new JSONObject();
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        request.put("data", encrypt(data.toString()));
        request.put("encryptType", 1);
        request.put("id", id);
        return request.toString();
    }

    private String requestData(int encryptType) throws JSONException {
        JSONObject request = new JSONObject();
        request.put("session", SharePrefs.newSharePrefs().getLoginInfo().getSession());
        String data = encryptType == 1 ? encrypt("{}") :"{}";
        request.put("data", data);
        request.put("encryptType", encryptType);
        return request.toString();
    }




}
