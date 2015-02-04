package cn.bvin.lib.request;

import java.io.UnsupportedEncodingException;
import java.util.Map;




import cn.bvin.lib.utils.StringUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;

/***
 * 
 * @ClassName: BvinRequest 
 * @Description: 有参数Map集合就用Post，没有就会走Get
 * @author: Bvin
 * @date: 2015年2月2日 下午2:12:50 
 * @param <T>
 */
public abstract class BvinRequest<T> extends Request<T>{

	
	protected Map<String, Object> mapParams;
	protected RequestParams reqParams;
	
	/**
	 * 
	 * @Title:BvinRequest
	 * @Description:Get请求
	 * @param url 请求地址
	 * @param listener 请求出错监听器
	 */
	public BvinRequest(String url,ErrorListener listener) {
		super(Request.Method.GET, url, listener);
	}
	
	/**
	 * 
	 * @Title:BvinRequest
	 * @Description:Post请求
	 * @param url 请求地址
	 * @param params 参数集合
	 * @param listener 请求出错监听器
	 */
	public BvinRequest(String url, Map<String, Object> params, ErrorListener listener) {
		super(Request.Method.POST, url, listener);
		this.mapParams = params;
	}
	
	/**
	 * 
	 * @Title:BvinRequest
	 * @Description: RequestParams参数赋值的同时也会对MapParams赋值
	 * @param url
	 * @param params RequestParams参数集合
	 * @param listener
	 */
	public BvinRequest(String url, RequestParams params, ErrorListener listener) {
		super(Request.Method.POST, url, listener);
		this.reqParams = params;
		this.mapParams  = params.urlParams;
	}
	

	@Override
	public byte[] getBody() throws AuthFailureError {
		//优先发送RequestParams形式的参数
		if (reqParams!=null&&!reqParams.isEmpty()) {
            return encodeParameters(reqParams, getParamsEncoding());
        //如果 reqParams参数为空就传mapParams
        } else if (mapParams != null && mapParams.size() > 0) {
            return encodeParameters(mapParams, getParamsEncoding());
        } {//如果都为空就调用父类的getBody()
        	return super.getBody();
		}
	}

	/**对参数编码*/
	protected byte[] encodeParameters(RequestParams reqParams, String paramsEncoding) {
		return encodeParameters(reqParams.urlParams, paramsEncoding);
	}

	/**只拼接了参数，没有作任何加密，如需要需要，重写这个方法*/
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
		if (mapParams!=null&&mapParams.size()>0) {
			return getUrl()+"?"+StringUtils.getStringFromMap(mapParams);
		} else {
			return getUrl();
		}
	}
}
