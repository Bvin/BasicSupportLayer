package cn.bvin.lib.request;

import cn.bvin.tools.secure.ParamsEncoder.Mode;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Volley adapter for JSON requests that will be parsed into Java objects by Gson.
 */
public class GsonRequest<T> extends SecureRequest<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Listener<T> listener;

    /**
     * Make a POST request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url,Map<String, Object> params, Class<T> clazz,Mode encodeMode, 
                       Listener<T> listener, ErrorListener errorListener) {
        super(url, params, encodeMode, errorListener);
        this.clazz = clazz;
        this.listener = listener;
    }

    
    /**
     * 
     * 默认是不加密的Gson请求
     * 
     * @param url URL of the request to make
     * @param params Map of request body
     * @param clazz Relevant class object, for Gson's reflection
     * @param listener
     * @param errorListener
     */
    public GsonRequest(String url,Map<String, Object> params, Class<T> clazz, 
            Listener<T> listener, ErrorListener errorListener) {
    	this(url, params, clazz, null, listener, errorListener);
    }
    
    /**
     * 
     * 不需要参数的话，加密模式也不需要，只需要提供一个Class<T> clazz来生成Json类
     * 
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param listener
     * @param errorListener
     */
    public GsonRequest(String url,Class<T> clazz,Listener<T> listener, ErrorListener errorListener) {
        super(url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
    }
	
    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}