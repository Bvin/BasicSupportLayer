package cn.bvin.lib.request;

import java.util.concurrent.ConcurrentHashMap;

import android.text.TextUtils;
import cn.bvin.lib.utils.StringUtils;
/**
 * 
 * @ClassName: RequestParams 
 * @Description: 请求参数，有需求再慢慢加
 * @author: Bvin
 * @date: 2015年2月3日 下午2:54:55
 */
public class RequestParams {

	protected ConcurrentHashMap<String, Object> urlParams;
	
	public RequestParams() {
		init();
	}

	private void init(){
        urlParams = new ConcurrentHashMap<String, Object>();
    }
	
	/**
	 * 
	 * @Title: put 参数
	 * @Description: 添加参数键值对
	 * @param key 参数名
	 * @param value 参数值
	 * @return: void
	 */
	public void put(String key, Object value){
        if(!TextUtils.isEmpty(key)) {
            urlParams.put(key, value);
        }
    }
	
	/**
	 * 
	 * @Title: remove 
	 * @Description: 移除某参数
	 * @param key 移除的参数名
	 * @return: void
	 */
	public void remove(String key){
        urlParams.remove(key);
    }
	
	/**
	 * 
	 * @Title: isEmpty 
	 * @Description: 判断参数是否为空
	 * @return: boolean
	 */
	public boolean isEmpty(){
		return urlParams.isEmpty();
	}
	
	/**
	 * 
	 * @Title: contains 
	 * @Description: 是否包含某个参数，需要传参数名key
	 * @param key 参数名
	 * @return: boolean
	 */
	public boolean contains(String key) {
		return urlParams.containsKey(key);
	}
	
	/**
	 * 
	 * @Title: clear 
	 * @Description: 清空参数列表
	 * @return: void
	 */
	public void clear() {
		urlParams.clear();
	}

	/**
	 * 
	 * @Title: getURLParams 
	 * @Description: 获取拼接成URL参数的字符串
	 * @return: String
	 */
	public String getURLParams() {
		return toString();
	}
	
	@Override
	public String toString() {
		if (!urlParams.isEmpty()) {
			return StringUtils.getStringFromMap(urlParams);
		}else
		return super.toString();
	}
	
}
