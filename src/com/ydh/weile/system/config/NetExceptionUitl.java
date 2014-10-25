package com.ydh.weile.system.config;

import android.content.Context;

import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.merchant.R;
import com.ydh.weile.uitl.ToastUitl;
import com.ydh.weile.view.LoadDataView;

/**
 * Created by liujianying on 14/10/23.
 */
public class NetExceptionUitl {

    public static NetExceptionUitl netExceptionUitl = null;

    public static NetExceptionUitl newNetExceptionUitl() {

        if(netExceptionUitl == null) {
            synchronized(NetExceptionUitl.class){
                if(netExceptionUitl == null)netExceptionUitl =new NetExceptionUitl();
            }
        }
        return netExceptionUitl;
    }

    private NetExceptionUitl() {

    }


    public void showExceptionToast(final Context mContext, final int failCode) {
        if (failCode == NetCode.NoNetworkConnection) {
            ToastUitl.showToast(mContext, R.string.NoNetworkConnection);
            return;
        }

        if (failCode == NetCode.NetworkAnomaly) {
            ToastUitl.showToast(mContext, R.string.NetworkAnomaly);
            return;
        }

        if (failCode == NetCode.OtrerException) {
            ToastUitl.showToast(mContext, R.string.OtrerException);
            return;
        }
    }

    public void showExceptionToast(final Context mContext, final int failCode, LoadDataView loadDataView) {
        if (failCode == NetCode.NoNetworkConnection) {
            ToastUitl.showToast(mContext, R.string.NoNetworkConnection);
            loadDataView.closed(LoadDataView.LoadResponse.NoNetWork);
            return;
        }

        if (failCode == NetCode.NetworkAnomaly) {
            ToastUitl.showToast(mContext, R.string.NetworkAnomaly);
            loadDataView.closed(LoadDataView.LoadResponse.Fail);
            return;
        }

        if (failCode == NetCode.OtrerException) {
            ToastUitl.showToast(mContext, R.string.OtrerException);
            loadDataView.closed(LoadDataView.LoadResponse.Fail);
            return;
        }
    }

//    loadDataView.closed(LoadDataView.LoadResponse.NoNetWork);

}
