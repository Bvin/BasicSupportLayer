package cn.bvin.lib.app;

import cn.bvin.lib.network.NetworkHelp;
import cn.bvin.lib.network.NetworkWatcher;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NetService extends Service implements NetworkWatcher{

	NetworkHelp helper;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		helper = NetworkHelp.getNetworkHelper();
		helper.registerWatcher(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		helper.removeWatcher(this);
	}

	@Override
	public void change(int type) {
		switch (type) {
			case NetworkWatcher.NETWORK_TYPE_DISCONN:
				onNetworkDisconnect();
				break;

			case NetworkWatcher.NETWORK_TYPE_OTHERCONN:
				onNetworkConnected();
				break;
						
			case NetworkWatcher.NETWORK_TYPE_MOBLECONN:
				onMobileNetworkSwitch();
				break;
				
			case NetworkWatcher.NETWORK_TYPE_WIFICONN:
				onWifiNetworkswitch();
				break;
				
			default:
				break;
			}
	}

	/**网络已连接*/
	protected void onNetworkConnected(){
		
	}
	
	/**网络已断开*/
	protected void onNetworkDisconnect(){
		
	}
	
	/**已连接至手机网络*/
	protected void onMobileNetworkSwitch(){
		onNetworkConnected();
	}
	
	/**已连接至wifi网络*/
	protected void onWifiNetworkswitch(){
		onNetworkConnected();
	}
	
	/**是否连接上网络*/
	protected boolean isNetworkAvailable() {
		return helper.isNetworkAvailable(getApplicationContext());
	}
}
