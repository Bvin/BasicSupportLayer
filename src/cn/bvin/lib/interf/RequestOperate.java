package cn.bvin.lib.interf;

import com.android.volley.Request;

public interface RequestOperate<T> {

	public void addRequest(Request<T> resquest);
	
	public void cancelRequest();
	
	public void reloadRequest();
}
