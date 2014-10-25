package com.ydh.weile.system.config;

import com.ydh.weile.android.WeiLeMerchantApp;

/**
 * 
 * @ClassName: SystemConst
 * @Description: 定义系统的相关常量
 * 
 */

public class SystemConst {
    /** 地区数据库版本号 **/
    public static final int ADDRESS_DB_VERSION = 3;

    public static final String PACKAGE_NAME = "com.ydh.weile";


	/**
	 * 微乐应用在/data/data中的保存图片的路径
	 */
	public static final String storeImagePath = WeiLeMerchantApp.wlmApp.getFilesDir().getAbsolutePath();


	
	

}
