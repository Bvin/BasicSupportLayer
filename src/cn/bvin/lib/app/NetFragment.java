package cn.bvin.lib.app;
/**
 * 
 * @ClassName: NetFragment 
 * @Description: 如果父Activity是NetActivity才会回调onNetXXX()方法
 * @author: Bvin
 * @date: 2015年2月3日 上午11:55:33
 */
public class NetFragment extends WiseFragment{

	/**网络是否可用，只有ParentActivity是NetActivity才可用，否则将会抛出ClassCastException*/
	protected boolean isNetworkAvailable() {
		if (getActivity() instanceof NetActivity) {
			return ((NetActivity)getActivity()).isNetworkAvailable();
		} else {
			throw new ClassCastException("ParentActivity is not NetActivity");
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
}
