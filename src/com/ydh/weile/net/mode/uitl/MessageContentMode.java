package com.ydh.weile.net.mode.uitl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.ydh.weile.entity.CouponsInfoEntity;
import com.ydh.weile.entity.LoginInfo;
import com.ydh.weile.entity.MembershipCardInfoEntity;
import com.ydh.weile.entity.MessageEntity;
import com.ydh.weile.entity.StorageCardInfo;
import com.ydh.weile.entity.VoucherUserEntity;
import com.ydh.weile.entity.YDHData;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.net.DataFetcherRunnable;
import com.ydh.weile.net.FetchDataHelper;
import com.ydh.weile.net.HttpRequestBody;
import com.ydh.weile.net.HttpRequestUrl;
import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.uitl.ToastUitl;

import org.json.JSONException;

/**
 * Created by liujianying on 14/10/23.
 * @消息中心net mode
 */
public class MessageContentMode {

    public static MessageContentMode messageContentMode = null;

    public static MessageContentMode newMessageContentMode() {

        if(messageContentMode == null) {
            synchronized(MessageContentMode.class){
                if(messageContentMode == null)messageContentMode =new MessageContentMode();
            }
        }
        return messageContentMode;
    }

    private MessageContentMode() {

    }



    /**
     * @category 未处理消息中心
     * @param
     * @param
     */
    public final void appUntreatedMessage(final Context mContext,final int pageCount,final int crrentIndex, final Handler handler){

        final Message msg = handler.obtainMessage();
        try {
            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppUntreatedMessage(), HttpRequestBody.newBody().requestappUntreatedMessage(pageCount, crrentIndex), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if (ydhData.getResultCode() == 0) {
                        msg.what = NetCode.RequestSuccess;
                        msg.obj = JSON.parseObject(ydhData.getData(), MessageEntity.class);
                    } else {
                        msg.what = NetCode.System_Error;
                        ToastUitl.showToast(mContext, ydhData.getMsg());
                    }
                    handler.sendMessage(msg);
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    msg.obj = resultCode;
                    msg.what = NetCode.RequestFailed;
                    handler.sendMessage(msg);
                }
            });
        } catch (JSONException e) {
            msg.what = NetCode.RequestFailed;
            msg.obj = NetCode.OtrerException;
            handler.sendMessage(msg);
            e.printStackTrace();
        }







    }


    /**
     * @获取会员卡申请使用
     * @param mContext
     * @param mcardId
     * @param handler
     * @return
     */
    public final void appGetApplyMcardInfo(final Context mContext, final String mcardId, final Handler handler, final int requestFlag) {
        final Message message = handler.obtainMessage();
        message.arg2 = requestFlag;
        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppGetApplyMcardInfo(), HttpRequestBody.newBody().requestAppGetApplyMcardInfo(mcardId), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() == 0) {
                        message.what = NetCode.RequestSuccess;
                        message.obj = JSON.parseObject(ydhData.getData(), MembershipCardInfoEntity.class);
                    }else {
                        message.what = NetCode.System_Error;
                        ToastUitl.showToast(mContext, ydhData.getMsg());
                    }
                    handler.sendMessage(message);
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    message.what = NetCode.RequestFailed;
                    message.arg1 = resultCode;
                    handler.sendMessage(message);
                }
            });

        } catch (Exception e){
            message.what = NetCode.RequestFailed;
            message.arg1 = NetCode.OtrerException;
            handler.sendMessage(message);
            e.printStackTrace();
        } finally {

//            handler.sendMessage(message);
        }
    }


    /**
     * @获取代金券申请使用
     * @param mContext
     * @param mcardId
     * @param handler
     * @return
     */
    public final void appGetApplyCashcouponInfo(final Context mContext, final String mcardId, final Handler handler, final int requestFlag) {
        final Message message = handler.obtainMessage();
        message.arg2 =  requestFlag;
        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppGetApplyCashcouponInfo(), HttpRequestBody.newBody().requestAppGetApplyCashcouponInfo(mcardId), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() == 0) {
                        message.what = NetCode.RequestSuccess;
                        message.obj = JSON.parseObject(ydhData.getData(), VoucherUserEntity.class);
                    }else {
                        message.what = NetCode.System_Error;
                        ToastUitl.showToast(mContext, ydhData.getMsg());
                    }
                    handler.sendMessage(message);
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    message.what = NetCode.RequestFailed;
                    message.arg1 = resultCode;
                    handler.sendMessage(message);
                }
            });

        } catch (Exception e){
            message.what = NetCode.RequestFailed;
            message.arg1 = NetCode.OtrerException;
            handler.sendMessage(message);
            e.printStackTrace();
        } finally {

//            handler.sendMessage(message);
        }
    }

    /**
     * @获取优惠券申请使用
     * @param mContext
     * @param mcardId
     * @param handler
     * @return
     */
    public final void appGetApplyCouponInfo(final Context mContext, final String mcardId, final Handler handler, final int requestFlag) {
        final Message message = handler.obtainMessage();
        message.arg2 = requestFlag;
        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppGetApplyCouponInfo(), HttpRequestBody.newBody().requestAppGetApplyCouponInfo(mcardId), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() == 0) {
                        message.what = NetCode.RequestSuccess;
                        CouponsInfoEntity gg = JSON.parseObject(ydhData.getData(), CouponsInfoEntity.class);
                        message.obj = gg;
                    }else {
                        message.what = NetCode.System_Error;
                        ToastUitl.showToast(mContext, ydhData.getMsg());
                    }
                    handler.sendMessage(message);
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    message.what = NetCode.RequestFailed;
                    message.arg1 = resultCode;
                    handler.sendMessage(message);
                }
            });

        } catch (Exception e){
            message.what = NetCode.RequestFailed;
            message.arg1 = NetCode.OtrerException;
            handler.sendMessage(message);
            e.printStackTrace();
        } finally {

//            handler.sendMessage(message);
        }
    }


    /**
     * @获取储值卡申请支付act= appGetApplyVcardInfo
     * @param mContext
     * @param mcardId
     * @param handler
     * @return
     */
    public final void appGetApplyVcardInfo(final Context mContext, final String mcardId, final Handler handler, final int requestFlag) {
        final Message message = handler.obtainMessage();
        message.arg2 = requestFlag;
        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppGetApplyVcardInfo(), HttpRequestBody.newBody().requestAppGetApplyVcardInfo(mcardId), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() == 0) {
                        message.what = NetCode.RequestSuccess;
                        message.obj = JSON.parseObject(ydhData.getData(), StorageCardInfo.class);
                    }else {
                        message.what = NetCode.System_Error;
                        ToastUitl.showToast(mContext, ydhData.getMsg());
                    }
                    handler.sendMessage(message);
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    message.what = NetCode.RequestFailed;
                    message.arg1 = resultCode;
                    handler.sendMessage(message);
                }
            });

        } catch (Exception e){
            message.what = NetCode.RequestFailed;
            message.arg1 = NetCode.OtrerException;
            handler.sendMessage(message);
            e.printStackTrace();
        } finally {

//            handler.sendMessage(message);
        }
    }


    /**
     * @会员卡使用审核
     * @param mCotent
     * @param idType
     * @param no
     * @param id
     * @param integralBalance
     * @param consumeAmount
     * @param approvalResult
     * @param approvalDesc
     * @param handler
     */
    public final void appApprovalMcard(final Context mCotent, final String idType, final String no, final String id, final String integralBalance, final String consumeAmount, final String approvalResult,final String approvalDesc, final Handler handler) {
        final Message message = handler.obtainMessage();

        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppApprovalMcard(), HttpRequestBody.newBody().requestAppApprovalMcard(idType, no, id, integralBalance, consumeAmount, approvalResult, approvalDesc), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() == 0) {
                        message.what = NetCode.RequestSuccess;

                    }else {
                        message.what = NetCode.System_Error;
                        ToastUitl.showToast(mCotent, ydhData.getMsg());
                    }
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    message.what = NetCode.RequestFailed;
                    message.arg1 = resultCode;
                }
            });

        } catch (Exception e){
            message.what = NetCode.RequestFailed;
            message.arg1 = NetCode.OtrerException;
            e.printStackTrace();
            handler.sendMessage(message);
        } finally {

//            handler.sendMessage(message);
        }
    }

    /**
     * @储值卡使用审核
     * @param mCotent
     * @param idType
     * @param no
     * @param id
     * @param integralBalance
     * @param consumeAmount
     * @param approvalResult
     * @param approvalDesc
     * @param handler
     */
    public final void appApprovalVcard(final Context mCotent, final String idType, final String no, final String id, final String integralBalance, final String consumeAmount, final String approvalResult,final String approvalDesc, final Handler handler) {
        final Message message = handler.obtainMessage();

        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppApprovalVcard(), HttpRequestBody.newBody().requestAppApprovalVcard(idType, no, id, integralBalance, consumeAmount, approvalResult, approvalDesc), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() == 0) {
                        message.what = NetCode.RequestSuccess;

                    }else {
                        message.what = NetCode.System_Error;
                        ToastUitl.showToast(mCotent, ydhData.getMsg());
                    }
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    message.what = NetCode.RequestFailed;
                    message.arg1 = resultCode;
                }
            });

        } catch (Exception e){
            message.what = NetCode.RequestFailed;
            message.arg1 = NetCode.OtrerException;
            e.printStackTrace();
        } finally {

            handler.sendMessage(message);
        }
    }


    /**
     * @代金券使用审核
     * @param mCotent
     * @param idType
     * @param no
     * @param id
     * @param approvalResult
     * @param approvalDesc
     * @param handler
     */
    public final void appApprovalCashcoupon(final Context mCotent, final String idType, final String no, final String id, final String approvalResult,final String approvalDesc, final Handler handler) {
        final Message message = handler.obtainMessage();

        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppApprovalCashcoupon(), HttpRequestBody.newBody().requestAppApprovalCashcoupon(idType, no, id, approvalResult, approvalDesc), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() == 0) {
                        message.what = NetCode.RequestSuccess;

                    }else {
                        message.what = NetCode.System_Error;
                        ToastUitl.showToast(mCotent, ydhData.getMsg());
                    }
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    message.what = NetCode.RequestFailed;
                    message.arg1 = resultCode;
                }
            });

        } catch (Exception e){
            message.what = NetCode.RequestFailed;
            message.arg1 = NetCode.OtrerException;
            e.printStackTrace();
        } finally {

            handler.sendMessage(message);
        }
    }


    /**
     * @优惠券使用审核
     * @param mCotent
     * @param idType
     * @param no
     * @param id
     * @param approvalResult
     * @param approvalDesc
     * @param handler
     */
    public final void appApprovalCoupon(final Context mCotent, final String idType, final String no, final String id, final String approvalResult,final String approvalDesc, final Handler handler) {
        final Message message = handler.obtainMessage();

        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppApprovalCoupon(), HttpRequestBody.newBody().requestAppApprovalCoupon(idType, no, id, approvalResult, approvalDesc), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() == 0) {
                        message.what = NetCode.RequestSuccess;

                    }else {
                        message.what = NetCode.System_Error;
                        ToastUitl.showToast(mCotent, ydhData.getMsg());
                    }
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    message.what = NetCode.RequestFailed;
                    message.arg1 = resultCode;
                }
            });

        } catch (Exception e){
            message.what = NetCode.RequestFailed;
            message.arg1 = NetCode.OtrerException;
            e.printStackTrace();
        } finally {

            handler.sendMessage(message);
        }
    }


}
