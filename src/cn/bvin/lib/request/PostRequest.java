package cn.bvin.lib.request;

import java.io.UnsupportedEncodingException;
import java.util.Map;



import cn.bvin.lib.utils.StringUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;

public abstract class PostRequest<T> extends Request<T>{

	protected Map<String, Object> params;
	
	public PostRequest(String url, Map<String, Object> params, ErrorListener listener) {
		super(Request.Method.POST, url, listener);
		this.params = params;
	}

	public PostRequest(String url,ErrorListener listener) {
		super(Request.Method.POST, url, listener);
	}
	
	@Override
	public byte[] getBody() throws AuthFailureError {
		if (params != null && params.size() > 0) {
            return encodeParameters(params, getParamsEncoding());
        } else {
        	return super.getBody();
		}
	}

	protected byte[] encodeParameters(Map<String, Object> params, String paramsEncoding) {
        try {
        	String encodedStr = StringUtils.getStringFromMap(params);
            return encodedStr.getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }
	
	/**
	 * getDebugUrl()
	 * 返回未加密同时带参数的Url(形式如同GET请求:www.xxx.com?p1=v1&p2=v2&p3=v3),便于调试。
	 * 相当于urlgetUrl()+params.toString()
	 * */
	public String getDebugUrl() {
		if (params!=null&&params.size()>0) {
			return getUrl()+"?"+StringUtils.getStringFromMap(params);
		} else {
			return getUrl();
		}
	}
}
