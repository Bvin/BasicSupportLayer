package cn.bvin.lib.request;

import java.util.Map;

import cn.bvin.tools.secure.ParamsEncoder;
import cn.bvin.tools.secure.ParamsEncoder.Mode;

import com.android.volley.Response.ErrorListener;

public abstract class SecureRequest<T> extends BvinRequest<T>{

	Mode mode = null;
	
	public SecureRequest(String url, Map<String, Object> params, ErrorListener listener) {
		super(url, params, listener);
	}
	
	public SecureRequest(String url, Map<String, Object> params,Mode encodeMode, ErrorListener listener) {
		super(url, params, listener);
		this.mode = encodeMode;
	}
	
	public SecureRequest(String url, ErrorListener listener) {
		super(url, listener);
	}

	@Override
	protected byte[] encodeParameters(Map<String, Object> params, String paramsEncoding) {
		
		if (params!=null&&params.size()>0&&mode!=null) {
			try {
				ParamsEncoder encoder = new ParamsEncoder(params);
				return encoder.encodeParameters(paramsEncoding, mode);
			} catch (Exception e) {
				e.printStackTrace();
				return super.encodeParameters(params, paramsEncoding);
			}
		} else {
			return super.encodeParameters(params, paramsEncoding);
		}
		
	}
	

}
