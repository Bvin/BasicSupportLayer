package cn.bvin.app.manager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;




import cn.bvin.app.network.OkHttpStack;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.NoCache;
import com.squareup.okhttp.OkHttpClient;

public class RequestManager {

	private static RequestQueue mRequestQueue = newRequestQueue();

	private RequestManager() {
	}

	private static RequestQueue newRequestQueue() {
		RequestQueue requestQueue;
		Network network = new BasicNetwork(getStack());
		requestQueue = new RequestQueue(getCache(), network);
		requestQueue.start();
		return requestQueue;
	}

	public static void addRequest(Request request, Object tag) {
		if (tag != null) {
			request.setTag(tag);
		}
		mRequestQueue.add(request);
	}
	
	public static void clearRequests(Object tag) {
		mRequestQueue.cancelAll(tag);
	}

	/**
	 * Gets the stack.
	 * 
	 * @return the stack
	 */
	private static HttpStack getStack() {
		HttpStack stack = new OkHttpStack();
		return stack;
	}

	/**
	 * Gets the cache. 获取缓存，有SD卡就用DiskBasedCache缓存，没有不缓存
	 * 
	 * @param context
	 *            the context
	 * @return the cache
	 */
	private static Cache getCache() {
		Cache cache = new NoCache();
		return cache;
	}

	public static String getStringResponse(String url) {
		HttpURLConnection conn = null;
		OkHttpClient client = new OkHttpClient();
		StringBuilder jsonResults = new StringBuilder();
		try {
			URL uri = new URL(url);
			conn = client.open(uri);
			conn.setRequestMethod("GET");
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
		} catch (MalformedURLException e) {
			return e.getLocalizedMessage();
		} catch (IOException e) {
			return e.getLocalizedMessage();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return jsonResults.toString();
	}

}
