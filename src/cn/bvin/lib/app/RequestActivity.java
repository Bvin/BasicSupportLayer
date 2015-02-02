package cn.bvin.lib.app;

import android.text.TextUtils;
import cn.bvin.lib.debug.SimpleLogger;
import cn.bvin.lib.interf.RequestListener;
import cn.bvin.lib.manager.RequestManager;
import cn.bvin.lib.request.BvinRequest;

import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public class RequestActivity<T> extends NetActivity implements RequestListener<T>,Response.ErrorListener, Listener<T> {

	private Request<T> request;// 当前请求，保持唯一
	protected AbstractRPFragment mPlaceViewHolder;// 请求占位视图
	
	@Override
	public void onResponse(T arg0) {
		
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		
	}

	@Override
	public void addRequest(Request<T> resquest) {
		if (resquest==null) {
  	        SimpleLogger.log_w("addRequest", "resquest为Null");
  	        return;
        }
		if (TextUtils.isEmpty(resquest.getUrl())) {
			SimpleLogger.log_w("addRequest", "不存在URL的请求");
		} else {
			this.request = resquest;
			onRequestStart(resquest);
			SimpleLogger.log_i("addRequest", resquest.getUrl());
			RequestManager.addRequest(resquest, resquest.getUrl());
		}
	}

	@Override
	public void cancelRequest() {
		if (request != null)
			request.cancel();
	}

	@Override
	public void reloadRequest() {
		 SimpleLogger.log_i("reloadRequest", request.getUrl());
	     addRequest(request);
	}

	@Override
	public void onRequestStart(Request<T> resquest) {
		if (resquest instanceof BvinRequest) {
			SimpleLogger.log_i("onRequestStart", ((BvinRequest) resquest).getDebugUrl());
		} else {
			SimpleLogger.log_i("onRequestStart", resquest.getUrl());
		}
		if (mPlaceViewHolder != null) {}
			mPlaceViewHolder.resetHoldView();
		
	}

	@Override
	public void onRequestSuccess(T result) {
		// 返回成功不一定就真的成功，这里移交到具体的类去处理
		if (mPlaceViewHolder != null) {}// 请求成功把LoadingView设置为GONE
			mPlaceViewHolder.hideLoadingProgress();;
	}

	@Override
	public void onRequestFailure(VolleyError error) {
		if (mPlaceViewHolder != null) {
			mPlaceViewHolder.hideLoadingProgress();
			if (error instanceof NoConnectionError) {// 错误界面
				mPlaceViewHolder.setErrorTips("服务器连接失败");
			} else if (error instanceof TimeoutError) {
				mPlaceViewHolder.setErrorTips("网络超时");
			} else if (error instanceof ParseError) {
				mPlaceViewHolder.setErrorTips("解析异常");
			} else if (error instanceof ServerError) {
				mPlaceViewHolder.setErrorTips("服务器异常");
			} else {
				mPlaceViewHolder.setErrorTips("未知错误");
			}
		}
	}
	
}
