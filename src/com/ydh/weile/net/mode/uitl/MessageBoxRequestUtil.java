package com.ydh.weile.net.mode.uitl;

import android.os.Handler;

/**
 * 消息盒子请求工具类
 * @author Administrator
 *
 */
public class MessageBoxRequestUtil {
	
	/**
	 *@category 获取用户消息列表
	 */
	public static void getUserMessageList(String createTime,int currentIndex,int pageCount,final Handler handler){
//		try {
//			FetchDataHelper.post(HttpRequestUrl.getUserMessageList(), HttpRequestBody.getUserMessageList(createTime, currentIndex, pageCount), new IFetchCallback() {
//
//				@Override
//				public void fetchSuccess(String content) {
//					try {
//						JSONObject jsonObject = new JSONObject(content);
//						String string_data =JSONReadUtils.JsonEnncryptToString(jsonObject);
//						if(null != string_data){
//							JSONObject json = new JSONObject(string_data);
//							JSONArray array = json.getJSONArray("messages");
//							ArrayList<MessageEntity> list = new ArrayList<MessageEntity>();
//							for (int i = 0; i < array.length(); i++) {
//								MessageEntity mMessageEntity =  MyGsonUitl.fromJson(array.getJSONObject(i).toString(), MessageEntity.class);
//								list.add(mMessageEntity);
//							}
//
//							if(list.size() > 0){
//								Message msg = new Message();
//								msg.what = MessageBoxListFragment.Load_Success;
//								msg.obj = list;
//								handler.sendMessage(msg);
//							}else{
//								handler.sendEmptyMessage(MessageBoxListFragment.Load_NoData);
//							}
//						}else{
//							handler.sendEmptyMessage(MessageBoxListFragment.Load_NoData);
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//						handler.sendEmptyMessage(MessageBoxListFragment.Load_Fail);
//					}
//				}
//
//				@Override
//				public void errorMessage(int resultCode, String data) {
//					System.out.println(data);
//					handler.sendEmptyMessage(MessageBoxListFragment.Load_Fail);
//				}
//			});
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
	}
	
	
	/**
	 *@category 获取系统消息列表
	 */
	public static void getSystemMessageList(String createTime,int currentIndex,int pageCount,final Handler handler){
//		try {
//			FetchDataHelper.post(HttpRequestUrl.getSystemMessageList(), HttpRequestBody.getSystemMessageList(createTime, currentIndex, pageCount), new IFetchCallback() {
//
//				@Override
//				public void fetchSuccess(String content) {
//					try {
//						JSONObject jsonObject = new JSONObject(content);
//						String string_data =JSONReadUtils.JsonEnncryptToString(jsonObject);
//						if(null != string_data){
//							JSONObject json = new JSONObject(string_data);
//							JSONArray array = json.getJSONArray("messages");
//							ArrayList<MessageEntity> list = new ArrayList<MessageEntity>();
//							for (int i = 0; i < array.length(); i++) {
//								MessageEntity mMessageEntity =  MyGsonUitl.fromJson(array.getJSONObject(i).toString(), MessageEntity.class);
//								list.add(mMessageEntity);
//							}
//							if(list.size() > 0){
//								Message msg = new Message();
//								msg.what = MessageBoxListFragment.Load_Success;
//								msg.obj = list;
//								handler.sendMessage(msg);
//							}else{
//								handler.sendEmptyMessage(MessageBoxListFragment.Load_NoData);
//							}
//						}else{
//							handler.sendEmptyMessage(MessageBoxListFragment.Load_NoData);
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//						handler.sendEmptyMessage(MessageBoxListFragment.Load_Fail);
//					}
//				}
//
//				@Override
//				public void errorMessage(int resultCode, String data) {
//					System.out.println(data);
//					handler.sendEmptyMessage(MessageBoxListFragment.Load_Fail);
//				}
//			});
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
	}
	
	
	/**
	 *@category 用户消息判断卡卷评价
	 * @param 
	 */
	public static void judgeMerchantCardReview(String msgId,final Handler handler){
//		try {
//			FetchDataHelper.post(HttpRequestUrl.judgeMerchantCardReview(), HttpRequestBody.judgeMerchantCardReview(msgId), new IFetchCallback() {
//
//				@Override
//				public void fetchSuccess(String content) {
//					try {
//						JSONObject jsonObject = new JSONObject(content);
//						String string_data =JSONReadUtils.JsonEnncryptToString(jsonObject);
//						if(null != string_data){
//							JSONObject json = new JSONObject(string_data);
//							String sourceId = json.getString("sourceId");
//							int cardType = json.getInt("cardType");
//							MessageBoxComment  comment = new MessageBoxComment();
//							comment.setSourceId(sourceId);
//							comment.setCardType(cardType);
//							Message msg = new Message();
//							msg.what = MessageBoxListFragment.Load_Comment_Success;
//							msg.obj = comment;
//							handler.sendMessage(msg);
//						}else{
//							handler.sendEmptyMessage(MessageBoxListFragment.Load_Comment_Fail);
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//						handler.sendEmptyMessage(MessageBoxListFragment.Load_Comment_Fail);
//					}
//				}
//
//				@Override
//				public void errorMessage(int resultCode, String data) {
//					System.out.println(data);
//					Message message=ErrorMessageUtil.getServeError(resultCode, data);
//					if(null !=  message){
//						handler.sendMessage(message);
//					}
//
//				}
//			});
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
	}


    public static void getSysMessage(String msgId,final Handler handler){
//        try {
//            FetchDataHelper.post(HttpRequestUrl.getSysMessage(), HttpRequestBody.getSysMessage(msgId), new IFetchCallback() {
//
//                @Override
//                public void fetchSuccess(String content) {
//                    try {
//                        JSONObject jsonObject = new JSONObject(content);
//                        String string_data =JSONReadUtils.JsonEnncryptToString(jsonObject);
//                        if(null != string_data){
//                            JSONObject json = new JSONObject(string_data);
//                            MessageEntity mMessageEntity =  MyGsonUitl.fromJson(json.toString(), MessageEntity.class);
//
//                            Message msg = new Message();
//                            msg.what = SystemMessageActivity.LoadSystemMsg_Success;
//                            msg.obj = mMessageEntity;
//                            handler.sendMessage(msg);
//                        }else{
//                            handler.sendEmptyMessage(SystemMessageActivity.LoadSystemMsg_Fail);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        handler.sendEmptyMessage(SystemMessageActivity.LoadSystemMsg_Fail);
//                    }
//                }
//
//                @Override
//                public void errorMessage(int resultCode, String data) {
//                    System.out.println(data);
//                    Message message=ErrorMessageUtil.getServeError(resultCode, data);
//                    if(null !=  message){
//                        handler.sendMessage(message);
//                    }
//
//                }
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }



}
