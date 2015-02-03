package cn.bvin.lib.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import cn.bvin.lib.network.NetworkHelp;
import cn.bvin.lib.network.NetworkWatcher;
/**
 * 
 * @ClassName: NetActivity 
 * @Description: 通知子类和ChidFragment网络状态发生变化
 * @author: Bvin
 * @date: 2015年2月3日 上午11:56:53
 */
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
		switch (type) {//先通知NetActivity的网络状态发生变化
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
		//再通知NetFragment网络状态发生变化
		notifyNetFragmentNetChanged(type);
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
	
	/**通知NetFragment网络状态发生变化*/
	private void notifyNetFragmentNetChanged(int type) {
		if (!getSupportFragmentManager().getFragments().isEmpty()) {
			for (Fragment fragment : getSupportFragmentManager().getFragments()) {
				if (fragment instanceof NetFragment) {
					switch (type) {
						case NetworkWatcher.NETWORK_TYPE_DISCONN:
							((NetFragment)fragment).onNetworkConnected();
							break;

						case NetworkWatcher.NETWORK_TYPE_OTHERCONN:
							((NetFragment)fragment).onNetworkConnected();
							break;
									
						case NetworkWatcher.NETWORK_TYPE_MOBLECONN:
							((NetFragment)fragment).onMobileNetworkSwitch();
							break;
							
						case NetworkWatcher.NETWORK_TYPE_WIFICONN:
							((NetFragment)fragment).onWifiNetworkswitch();
							break;
							
						default:
							break;
					}
				}
			}
		}
	}
	
	/**是否连接上网络*/
	protected boolean isNetworkAvailable() {
		return helper.isNetworkAvailable(getApplicationContext());
	}
}
