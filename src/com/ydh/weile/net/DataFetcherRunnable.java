package com.ydh.weile.net;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.ydh.weile.android.WeiLeMerchantApp;
import com.ydh.weile.entity.LoginInfo;
import com.ydh.weile.entity.YDHData;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.net.mode.uitl.LoginMode;
import com.ydh.weile.system.config.NetExceptionUitl;
import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.system.config.SystemVal;
import com.ydh.weile.uitl.LogUitl;
import com.ydh.weile.uitl.encryption.AESCrypto;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONObject;

public class DataFetcherRunnable implements Runnable {

    private volatile boolean isAgainRequest = false;// 是否再次请求
    private String url;
    private String body;
    private String responseContent;
    private IFetchCallback callback;

	@Override
	public void run() {

		try {
			do {
			responseContent = post();
			
//			MyFileOperateDemo(responseContent);
			
			LogUitl.LogD("weile", "=DataFetcherRunnable===responseContent======" + responseContent);

			YDHData ydhData = JSON.parseObject(responseContent, YDHData.class);

			ydhData.GsonEnncryptToString();// 数据还原

			int resultCode = ydhData.getResultCode();

			LogUitl.LogD("weile", "=DataFetcherRunnable===resultCode======" + resultCode);

			LogUitl.LogD("weile","=DataFetcherRunnable===resultCode======" + ydhData.toString());

			if (0 == resultCode && callback != null) {
				callback.fetchSuccess(ydhData);
				isAgainRequest = false;
			} else if (0 != resultCode && callback != null) {
				managerResultCode(resultCode, ydhData);
			}

		}while (isAgainRequest);

		} catch (Exception e) {
            exceptonMethod(e);
            e.printStackTrace();
		}

	}

    /**
     * 异常统一处理类
     * @param e
     */
    private void exceptonMethod(Exception e) {
        if(e instanceof java.net.ConnectException) {
            LogUitl.SystemOut("ydh-----网络未连接");
            callback.errorMessage(NetCode.NoNetworkConnection, null);
        }else if (e instanceof ConnectTimeoutException
                || e instanceof HttpHostConnectException ||e instanceof java.net.SocketTimeoutException) {
            LogUitl.SystemOut("ydh-----连接超时错误");
            callback.errorMessage(NetCode.NetworkAnomaly, null);
        } else {

            if(e instanceof java.io.IOException) {
                LogUitl.SystemOut("ydh------404页面不存在");
                callback.errorMessage(NetCode.OtrerException, null);
            } else {
                LogUitl.SystemOut("ydh------其他异常");
                callback.errorMessage(NetCode.OtrerException, null);
            }

        }
    }

    public String post() throws Exception {

		if (TextUtils.isEmpty(this.url) || TextUtils.isEmpty(this.body)) {
			return "";
		}
		
		return OkHttpUtil.post(url, body);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public IFetchCallback getCallback() {
		return callback;
	}

	public void setCallback(IFetchCallback callback) {
		this.callback = callback;
	}

	public interface IFetchCallback {

		/**
		 * 
		 * @category Title: fetchSuccess
		 * @Description: 获取数据成功的回调
		 * @return void
		 * @throws
		 * @param
		 */
		void fetchSuccess(Object object);

		/**
		 * 
		 * @category Title: errorMessage
		 * @Description: 获取数据失败的回调
		 * @return void
		 * @throws
		 * @param
		 */
		void errorMessage(int resultCode, Object object);
	}

	private void managerResultCode(final int resultCode, final Object object) throws Exception {

		switch (resultCode) {

		case ErrorCode.INVALID_SESSION:// Session无效或者过期
		case ErrorCode.SESSION_NO_EXIST:// Session不存在
		case ErrorCode.KEY_ERROR:// 获取认证KEY失败
		case ErrorCode.CRYPT_ERROR:// 获取认证KEY失败

                LoginInfo logiInfo = SharePrefs.newSharePrefs().getLoginInfo();
                LoginMode.newLoginMode().startLogin(WeiLeMerchantApp.wlmApp, logiInfo.getUserName(), logiInfo.getPassword(), logiInfo.isAutomaticLogin(), new Handler()
                {
                    @Override
                    public void handleMessage(Message msg) {

                        switch (msg.what) {

                            case NetCode.RequestSuccess:
                                LoginInfo logiInfo = (LoginInfo) msg.obj;
                                try {

                                    final  JSONObject body_json =  new JSONObject(body);
                                    String data = null;
                                    if(body_json.getInt("encryptType")  == 1 && !body_json.isNull("data")){
                                        String data_Aes = body_json.getString("data");
                                        data = AESCrypto.decrypt(SystemVal.getLoginInfo().getKey(), data_Aes);
                                    }
                                    body_json.put("session", logiInfo.getSession());
                                    if(body_json.getInt("encryptType")  == 1 && !body_json.isNull("data")){
                                        body_json.put("data", AESCrypto.encrypt(logiInfo.getKey(), data));
                                    }
                                    body = body_json.toString();
                                /*    更新用户key   ****/
                                    SystemVal.setLoginInfo(null);
                                } catch (Exception e) {
                                    callback.fetchSuccess(object);
                                    isAgainRequest = false;
                                }
                                break;
                            case NetCode.RequestFailed:
                            case NetCode.System_Error:
                            default:
                                NetExceptionUitl.newNetExceptionUitl().showExceptionToast(WeiLeMerchantApp.wlmApp,(Integer)msg.obj);
                                callback.fetchSuccess(object);
                                isAgainRequest = false;
                                break;

                        }
                    }
                });

			break;
			
		default:
			isAgainRequest = false;
			callback.fetchSuccess(object);
			break;
		}

	}



	
	
//	protected static void MyFileOperateDemo(String str) {
//		String FILENAME = "mymldn" + System.currentTimeMillis() + ".txt"; // 设置文件名称
//		String DIR = "YDH";// 操作文件夹的名称
//		/**
//		 * 使用OutputStream完成输出文件到sdcard中的操作（操作SD卡加权限）
//		 */
//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {// 判断sdcard是否存在
//
//			File file = new File(Environment.getExternalStorageDirectory()
//					+ File.separator + DIR + File.separator + FILENAME);// 定义要操作的文件
//
//			if (!file.getParentFile().exists()) {
//				file.getParentFile().mkdirs();// 创建父文件夹路径
//			}
//			PrintStream out = null;
//			try {
//				out = new PrintStream(new FileOutputStream(file));
//				out.println(str);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} finally {// 一定要关闭流
//				if (out != null) {
//					out.close();
//				}
//			}
//		} else {
//			System.out.println("保存失败，SD卡不存在");
//		}
//	}

}
