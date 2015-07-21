package com.github.lottery.net;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class NetUtils {

	/**
	 * 检查用户网络：是否联网
	 */
	public static boolean checkNet(Context context) {
		// 判断：WIFI链接
		boolean isWIF = isWIFIConnection(context);

		// 判断：mobile链接
		boolean isMOBILE = isMOBILEConnection(context);

		// 如果是Mobile。是哪个APN
		if (isMOBILE) {
			// 判断是哪个APN被选中了
			readAPN(context);
		}

		if (!isMOBILE && !isWIF) {
			return false;
		}

		return true;
	}

	/**
	 * APN被选中，读取里面的代理信息，有的话则是wap连接
	 * 
	 * @param context
	 */
	private static void readAPN(Context context) {
		// 4.0模拟器屏蔽掉该权限
		Uri PREFERRED_APN_URI = Uri
				.parse("content://telephony/carriers/preferapn");

		// 与操作联系人类似
		ContentResolver resolver = context.getContentResolver();
		// 判断哪个APN被选中了
		Cursor cursor = resolver.query(PREFERRED_APN_URI, null, null, null, null);

		if (cursor.moveToFirst()) {
			String proxy = cursor.getString(cursor.getColumnIndex("Proxy"));
			int port = cursor.getInt(cursor.getColumnIndex("Port"));
			System.out.println(proxy);
		}
		
	}

	/**
	 * 判断是否移动网络连接
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isMOBILEConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}

		return false;
	}

	/**
	 * 判断是否wifi连接
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isWIFIConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}

		return false;
	}

}
