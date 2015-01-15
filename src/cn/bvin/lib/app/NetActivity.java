package cn.bvin.lib.app;

import android.os.Bundle;
import cn.bvin.lib.network.NetworkHelp;
import cn.bvin.lib.network.NetworkWatcher;

public class NetActivity extends WiseActivity implements NetworkWatcher{

	NetworkHelp helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		helper = NetworkHelp.getNetworkHelper();
		helper.registerWatcher(this);
	}

	@Override
	protected void onDestroy() {
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

	protected void onNetworkConnected(){
		
	}
	
	protected void onNetworkDisconnect(){
		
	}
	
	protected void onMobileNetworkSwitch(){
		onNetworkConnected();
	}
	
	protected void onWifiNetworkswitch(){
		onNetworkConnected();
	}
	
	protected boolean isNetworkAvailable() {
		return helper.isNetworkAvailable(getApplicationContext());
	}
}
