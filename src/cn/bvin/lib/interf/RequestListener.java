package cn.bvin.lib.interf;

import com.android.volley.Request;
import com.android.volley.VolleyError;

public interface RequestListener<T> {

	public void addRequest(Request<T> resquest);
	
	public void cancelRequest();
	
	public void reloadRequest();
	
	public void onRequestStart(Request<T> resquest);
	
	public void onRequestSuccess(T result);
	
	public void onRequestFailure(VolleyError error);
	
}
