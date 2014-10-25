package com.ydh.weile.net.mode.uitl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.ydh.weile.entity.YDHData;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.net.DataFetcherRunnable;
import com.ydh.weile.net.FetchDataHelper;
import com.ydh.weile.net.HttpRequestBody;
import com.ydh.weile.net.HttpRequestUrl;
import com.ydh.weile.uitl.ToastUitl;

/**
 * Created by liujianying on 14/10/23.
 * @交易记录 net mode
 */
public class TransactionRecordsMode {

    public static TransactionRecordsMode transactionRecordsMode = null;

    public static TransactionRecordsMode newTransactionRecordsMode() {

        if(transactionRecordsMode == null) {
            synchronized(TransactionRecordsMode.class){
                if(transactionRecordsMode == null)transactionRecordsMode =new TransactionRecordsMode();
            }
        }
        return transactionRecordsMode;
    }

    private TransactionRecordsMode() {

    }

    /**
     * @获取会员卡详情
     * @param mCotent
     * @param mcardId
     * @param handler
     */
    public final void appGetMcardInfo(final Context mCotent, final String mcardId, final Handler handler) {
        final Message message = handler.obtainMessage();

        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppGetMcardInfo(), HttpRequestBody.newBody().requestAppGetMcardInfo(mcardId), new DataFetcherRunnable.IFetchCallback() {
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
     * @获取代金券券详情
     * @param mCotent
     * @param mcardId
     * @param handler
     */
    public final void appGetCashcouponInfo(final Context mCotent, final String mcardId, final Handler handler) {
        final Message message = handler.obtainMessage();

        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppGetCashcouponInfo(), HttpRequestBody.newBody().requestAppGetCouponInfo(mcardId), new DataFetcherRunnable.IFetchCallback() {
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
     * @获取优惠券详情
     * @param mCotent
     * @param mcardId
     * @param handler
     */
    public final void appGetCouponInfo(final Context mCotent, final String mcardId, final Handler handler) {
        final Message message = handler.obtainMessage();

        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppGetCouponInfo(), HttpRequestBody.newBody().requestAppGetCouponInfo(mcardId), new DataFetcherRunnable.IFetchCallback() {
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
     * @获取储值卡详情
     * @param mCotent
     * @param mcardId
     * @param handler
     */
    public final void appGetVcardInfo(final Context mCotent, final String mcardId, final Handler handler) {
        final Message message = handler.obtainMessage();

        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppGetVcardInfo(), HttpRequestBody.newBody().requestAppGetApplyVcardInfo(mcardId), new DataFetcherRunnable.IFetchCallback() {
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
