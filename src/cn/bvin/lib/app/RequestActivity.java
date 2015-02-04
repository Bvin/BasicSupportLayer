package cn.bvin.lib.app;

import android.support.v4.app.Fragment;
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
	protected AbstractRPFragment mRequestHolderFragment;// 请求占位视图
	
	@Override
	public void onResponse(T arg0) {
		onRequestSuccess(arg0);
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		onRequestFailure(arg0);
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

	@SuppressWarnings("unchecked")
	@Override
	public void onRequestStart(Request<T> resquest) {
		if (resquest instanceof BvinRequest) {
			SimpleLogger.log_i("onRequestStart", ((BvinRequest<T>) resquest).getDebugUrl());
		} else {
			SimpleLogger.log_i("onRequestStart", resquest.getUrl());
		}
		//先让请求占位重置视图
		if (mRequestHolderFragment != null) {
			mRequestHolderFragment.resetHoldView();
			mRequestHolderFragment.showLoadingProgress();
		}
			
		//再找出并通知包含的RequestFragment回调onRequestStart
		if (!getSupportFragmentManager().getFragments().isEmpty()) {
			for (Fragment fragment : getSupportFragmentManager().getFragments()) {
				if (fragment instanceof RequestFragment) {
						((RequestFragment<T>)fragment).onRequestStart(request);
					}
				}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRequestSuccess(T result) {
		// 返回成功不一定就真的成功，这里移交到具体的类去处理
		if (mRequestHolderFragment != null) {}// 请求成功把LoadingView设置为GONE
			mRequestHolderFragment.hideLoadingProgress();
		//再找出并通知包含的RequestFragment回调onRequestSuccess
		if (!getSupportFragmentManager().getFragments().isEmpty()) {
			for (Fragment fragment : getSupportFragmentManager().getFragments()) {
				if (fragment instanceof RequestFragment) {
						((RequestFragment<T>)fragment).onRequestSuccess(result);
					}
				}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRequestFailure(VolleyError error) {
		if (mRequestHolderFragment != null) {
			mRequestHolderFragment.hideLoadingProgress();
			if (error instanceof NoConnectionError) {// 错误界面
				mRequestHolderFragment.setErrorTips("服务器连接失败");
			} else if (error instanceof TimeoutError) {
				mRequestHolderFragment.setErrorTips("网络超时");
			} else if (error instanceof ParseError) {
				mRequestHolderFragment.setErrorTips("解析异常");
			} else if (error instanceof ServerError) {
				mRequestHolderFragment.setErrorTips("服务器异常");
			} else {
				mRequestHolderFragment.setErrorTips("未知错误");
			}
		}
		//再找出并通知包含的RequestFragment回调onRequestSuccess
		if (!getSupportFragmentManager().getFragments().isEmpty()) {
			for (Fragment fragment : getSupportFragmentManager().getFragments()) {
				if (fragment instanceof RequestFragment) {
						((RequestFragment<T>)fragment).onRequestFailure(error);
					}
				}
		}
	}
	
}
