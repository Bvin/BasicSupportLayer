package cn.bvin.app.utils;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class DeviceUtils {

	
	/**
	 * 
	 * @Title: activeDeviceManager 
	 * @Description: 激活设备管理器
	 * @param context
	 * @param cls 必须继承DeviceAdminReceiver
	 * @param explanation 弹出设备管理器的时候显示的描述
	 */
	public static void activeDeviceManager(Context context,Class<? extends DeviceAdminReceiver> cls,String explanation) {
		ComponentName receiver = new ComponentName(context,cls);
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, receiver);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, explanation);  
		context.startActivity(intent);
	}
	
	/**
	 * 
	 * @Title: unactiveDeviceManager 
	 * @Description: 注销设备管理器
	 * @param context
	 * @param cls 
	 */
	private static void unactiveDeviceManager(Context context,Class<? extends DeviceAdminReceiver> cls) {
		DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
		ComponentName receiver = new ComponentName(context,cls);
		if (isDeviceManagerActive(context, cls)) {
			dpm.removeActiveAdmin(receiver);
		}
	}
	
	/**
	 * 
	 * @Title: isDeviceManagerActive 
	 * @Description: 检测是否激活设备管理器
	 * @param context
	 * @param cls
	 * @return: boolean
	 */
	public static boolean isDeviceManagerActive(Context context,Class<? extends DeviceAdminReceiver> cls) {
		DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
		ComponentName receiver = new ComponentName(context,cls);
		return dpm.isAdminActive(receiver);
	}
}
