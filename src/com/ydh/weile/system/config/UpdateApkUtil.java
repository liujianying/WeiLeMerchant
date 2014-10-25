package com.ydh.weile.system.config;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ydh.weile.android.WeiLeMerchantApp;
import com.ydh.weile.entity.UpdateVersionEntity;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.merchant.R;
import com.ydh.weile.net.mode.uitl.LoginMode;
import com.ydh.weile.uitl.SafetyUitl;
import com.ydh.weile.view.CustomDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * 版本更新工具类
 * @author xiezw
 * @version 1.1.5
 * @date 2014-1-7
 *
 */
public class UpdateApkUtil {

	private static UpdateApkUtil updateApkUtil;
	private Context mContext;
	private UpdateVersionEntity updateApkBean;

	private CustomDialog customDialog;

	// 进度条
	private ProgressBar mProgress;
	// 显示下载数值
	private TextView mProgressText;
	/**
	 * 下载的百分比
	 */
	private TextView tv_Percent;
	// 查询动画
	private ProgressDialog mProDialog;
	//终止标记
	private boolean interceptFlag;
	// 下载对话框
	private Dialog downloadDialog;
	
	//已经是最新' 或者 '无法获取最新版本' 的对话框
    private Dialog latestOrFailDialog;

	// 下载线程
	private Thread downLoadThread;
	// 下载包保存路径
	private String savePath = "";
	// apk保存完整路径
	private String apkFilePath = "";
	// 临时下载文件路径
	private String tmpFilePath = "";
	// 下载文件大小
	private String apkFileSize;
	// 已下载文件大小
	private String tmpFileSize;
	// 进度值
	private int progress;

	
	private static final int DIALOG_TYPE_LATEST = 0;
    private static final int DIALOG_TYPE_FAIL   = 1;

	private static final int DOWN_NOSDCARD = 0;
	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				mProgressText.setText(tmpFileSize + "/" + apkFileSize);
				tv_Percent.setText(progress + "%");
				break;
			case DOWN_OVER:
				downloadDialog.dismiss();
				installApk();
				break;
			case DOWN_NOSDCARD:
				downloadDialog.dismiss();
				Toast.makeText(WeiLeMerchantApp.wlmApp, "无法下载安装文件，请检查是否有SD卡",Toast.LENGTH_LONG).show();
				break;
			}
		};
	};

	/**
	 * 
	 * @Title: getUpdateApkUtile
	 * @Description: 获得UpdateApkUtil
	 * @param @return 设定文件
	 * @return UpdateApkUtil 返回类型
	 * @throws
	 */
	public static UpdateApkUtil getUpdateApkUtil() {
		if (updateApkUtil == null) {
			updateApkUtil = new UpdateApkUtil();
		}
		updateApkUtil.interceptFlag = false;
		return updateApkUtil;
	}

	/**
	 * 检查App更新
	 * @param context
	 * @param isShowMsg
	 *            是否显示提示消息
	 */
	public void checkAppUpdate(Context context, final boolean isShowMsg) {
		this.mContext = context;
//		curVersionCode = TelephoneUtil.getVersionCode(context);
		if (isShowMsg) {
			if (mProDialog == null){
				mProDialog = ProgressDialog.show(mContext, null, "正在检测,请稍后...",true, true);
			}else if(mProDialog.isShowing()|| (latestOrFailDialog != null && latestOrFailDialog.isShowing())){
				return;
			}
				
		}
		final Handler handler = new Handler() {
			
			public void handleMessage(Message msg) {

				// 进度条对话框不显示 - 检测结果也不显示
				if (mProDialog != null && !mProDialog.isShowing()) {
					return;
				}
				// 关闭并释放释放进度条对话框
				if (isShowMsg && mProDialog != null) {
					mProDialog.dismiss();
					mProDialog = null;
				}
				
				
				switch (msg.what) {
				case 0:
					showUpdateDialog(updateApkBean.getUpdateContent(), updateApkBean.getForceUpdate());
					break;
				case 1:
					
					if(isShowMsg){
						showLatestOrFailDialog(DIALOG_TYPE_LATEST);
					}
					
					break;

				default:
					break;
				}
				
			}
		};


        LoginMode.newLoginMode().appVersionUpdate(mContext, new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what)
                {
                    case NetCode.RequestSuccess:
                        updateApkBean = (UpdateVersionEntity) msg.obj;

                        double code = SafetyUitl.trydouble(updateApkBean.getVersionCode());

                        if (SystemVal.versionCode < code) {
                            updateApkBean.setVersionSize(Float.valueOf(updateApkBean.getVersionSize()) / 100 + "");
                            updateApkBean.setUpdateContent(updateApkBean.getUpdateContent().replaceAll("_", "\n\n"));
                            handler.sendEmptyMessage(0);
                        } else {
                            handler.sendEmptyMessage(1);

                        }
                        break;
                    case NetCode.RequestFailed:
                        break;
                    case NetCode.System_Error:
                        break;
                    default:break;
                }
            }
        }, 0);
//		requestVersionUpdate(handler);
		
	}
	
	
//	/**
//	 *
//	 * @category 请求版本更新
//	 * @Title: requestVersionUpdate
//	 * @Description:
//	 * @return void
//	 * @throws
//	 */
//	private void requestVersionUpdate(final Handler handler) {
//		try {
//
//			FetchDataHelper.post(HttpRequestUrl.getVersionUpdateUrl(), HttpRequestBody.requestVersionUpdate(), new IFetchCallback() {
//
//                @Override
//                public void errorMessage(int resultCode, String data) {
//                }
//
//                @Override
//                public void fetchSuccess(String content) {
//                    try {
//                        JSONObject object = new JSONObject(content);
//                        JSONObject data = object.getJSONObject("data");
//                        String versionCode = data.getString("versionCode");
//
//                        int code = Integer.valueOf(versionCode);
//
//                        if (SystemVal.versionCode < code) {
//
//                            updateApkBean = new UpdateVersionEntity();
//
//                            updateApkBean.setVersionCode(versionCode);
//                            updateApkBean.setVersionURL(data.getString("versionURL"));
//
//                            String versionSize = data.getString("versionSize");
//                            updateApkBean.setF_versionSize(Float.valueOf(versionSize) / 100);
//                            updateApkBean.setForceUpdate(data.getString("forceUpdate"));
//
//                            String updateContent = data.getString("updateContent");
//                            updateApkBean.setUpdateContent(updateContent.replaceAll("_", "\n\n"));
//
//
//                            handler.sendEmptyMessage(0);
//                        } else {
//                            handler.sendEmptyMessage(1);
//
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//            });
//
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//
//	}
//



//	private void requestVersionUpdate(Context context){
//		
//		
//		
//	}

	private void showUpdateDialog(String updateContent, String forceUpdate) {

		View.OnClickListener dialog_cancel = null;
		if ("1".equals(forceUpdate)) {
			dialog_cancel = new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					System.exit(0);
					
				}
			};
		} else {
			dialog_cancel = new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// mHandle.sendEmptyMessage(0);
					customDialog.dismiss();
				}
			};
		}
		customDialog = new CustomDialog(mContext,
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// new DownAndUpdateTask().execute(versionURL);

						showDownloadDialog();

						customDialog.dismiss();
					}
				}, dialog_cancel);

		customDialog.setMessage(updateContent);
		customDialog.setTitle("更新 ");
		customDialog.show();

	}
	
	/**
	 * 显示'已经是最新'或者'无法获取版本信息'对话框
	 */
	private void showLatestOrFailDialog(int dialogType) {
		if (latestOrFailDialog != null) {
			//关闭并释放之前的对话框
			latestOrFailDialog.dismiss();
			latestOrFailDialog = null;
		}
		Builder builder = new Builder(mContext);
		builder.setTitle("系统提示");
		if (dialogType == DIALOG_TYPE_LATEST) {
			builder.setMessage("您当前已经是最新版本");
		} else if (dialogType == DIALOG_TYPE_FAIL) {
			builder.setMessage("无法获取版本更新信息");
		}
		builder.setPositiveButton("确定", null);
		latestOrFailDialog = builder.create();
		latestOrFailDialog.show();
	}

	private void showDownloadDialog() {
		Builder builder = new Builder(mContext);
		builder.setTitle("正在下载新版本");

		final LayoutInflater inflater = LayoutInflater.from(mContext);
				
		View v = inflater.inflate(R.layout.update, null);
		mProgress = (ProgressBar) v.findViewById(R.id.proBar_Update);
		mProgressText = (TextView) v.findViewById(R.id.tv_M);
		tv_Percent = (TextView)v.findViewById(R.id.tv_Percent);

		builder.setView(v);

		builder.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				dialog.dismiss();
				interceptFlag = true;
			}
		});
		downloadDialog = builder.create();
		downloadDialog.setCanceledOnTouchOutside(false);
		downloadDialog.show();

		downloadApk();
	}

	/**
	 * 下载apk
	 * 
	 * @param
	 */
	private void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				String apkName = "weile_"+updateApkBean.getVersionCode()+".apk";
				String tmpApk = "weile_"+updateApkBean.getVersionCode()+".tmp";
				// 判断是否挂载了SD卡
				String storageState = Environment.getExternalStorageState();
				if (storageState.equals(Environment.MEDIA_MOUNTED)) {
					savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.ydh/Update/";
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					apkFilePath = savePath + apkName;
					tmpFilePath = savePath + tmpApk;
				}

				// 没有挂载SD卡，无法下载文件
				if (apkFilePath == null || apkFilePath == "") {
					mHandler.sendEmptyMessage(DOWN_NOSDCARD);
					return;
				}

				File ApkFile = new File(apkFilePath);

				// 是否已下载更新文件
				if (ApkFile.exists()) {
					downloadDialog.dismiss();
					installApk();
					return;
				}

				// 输出临时下载文件
				File tmpFile = new File(tmpFilePath);
				FileOutputStream fos = new FileOutputStream(tmpFile);

				URL url = new URL(updateApkBean.getVersionURL());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();

				// 显示文件大小格式：2个小数点显示
				DecimalFormat df = new DecimalFormat("0.00");
				// 进度条下面显示的总文件大小
				apkFileSize = df.format((float) length / 1024 / 1024) + "MB";

				int count = 0;
				byte buf[] = new byte[1024];

				do {
					int numread = is.read(buf);
					count += numread;
					// 进度条下面显示的当前下载文件大小
					tmpFileSize = df.format((float) count / 1024 / 1024) + "MB";
					// 当前进度值
					progress = (int) (((float) count / length) * 100);
					// 更新进度
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// 下载完成 - 将临时下载文件转成APK文件
						if (tmpFile.renameTo(ApkFile)) {
							// 通知安装
							mHandler.sendEmptyMessage(DOWN_OVER);
						}
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// 点击取消就停止下载

				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};

	/**
	 * 安装apk
	 * 
	 * @param
	 */
	private void installApk() {
		File apkfile = new File(apkFilePath);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),"application/vnd.android.package-archive");
				
		mContext.startActivity(i);
	}

	
	public void dismissDialog(){
		if(customDialog != null && customDialog.isShowing())
			customDialog.dismiss();
		if(latestOrFailDialog != null && latestOrFailDialog.isShowing())
			latestOrFailDialog.dismiss();
		if(downloadDialog != null && downloadDialog.isShowing())
			downloadDialog.dismiss();
	}


}
