package cn.bvin.lib.app;

import cn.bvin.lib.interf.RequestListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
/**
 * 
 * @ClassName: RequestFragment 
 * @Description: 具有Request请求功能的Fragment，通过NetActivity代理实现，
 * 只有ParentActivity是RequestActivity才具有请求功能，否则所有Request方法都失效
 * @author: Bvin
 * @date: 2015年2月3日 上午11:51:57 
 * @param <T>
 */
public class RequestFragment<T> extends NetFragment implements RequestListener<T>,Response.ErrorListener, Listener<T>{

	@SuppressWarnings("unchecked")
	@Override
	public void onResponse(T arg0) {
		if (getActivity() instanceof RequestActivity) {
			((RequestActivity<T>)getActivity()).onResponse(arg0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onErrorResponse(VolleyError arg0) {
		if (getActivity() instanceof RequestActivity) {
			((RequestActivity<T>)getActivity()).onErrorResponse(arg0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addRequest(Request<T> resquest) {
		
		if (getActivity() instanceof RequestActivity) {
			((RequestActivity<T>)getActivity()).addRequest(resquest);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void cancelRequest() {
		if (getActivity() instanceof RequestActivity) {
			((RequestActivity<T>)getActivity()).cancelRequest();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reloadRequest() {
		if (getActivity() instanceof RequestActivity) {
			((RequestActivity<T>)getActivity()).reloadRequest();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRequestStart(Request<T> resquest) {
		if (getActivity() instanceof RequestActivity) {
			((RequestActivity<T>)getActivity()).onRequestStart(resquest);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRequestSuccess(T result) {
		if (getActivity() instanceof RequestActivity) {
			((RequestActivity<T>)getActivity()).onRequestSuccess(result);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRequestFailure(VolleyError error) {
		if (getActivity() instanceof RequestActivity) {
			((RequestActivity<T>)getActivity()).onRequestFailure(error);
		}
	}
	
}
