package cn.bvin.lib.debug;

import java.lang.reflect.Array;
import java.util.List;

import cn.bvin.lib.basic_support.BuildConfig;
import cn.bvin.lib.utils.StringUtils;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @ClassName: SimpleLogger 
 * @Description: 不能受自己控制，完全有gen/BuildConfig.DEBUG属性决定
 * 				防止消息走漏，不用自己修改
 * @author: Bvin
 * @date: 2014年11月26日 下午4:34:47
 */
public class SimpleLogger {

	private static boolean debugeSwitch = BuildConfig.DEBUG;

	public static boolean getDebugeSwitch(){
		return debugeSwitch;
	}
	
	/*error组*/
	public static void Log_e(String msg){
		log_e("tag",msg);
	}
	
	public static void log_e(String tag,int msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void log_e(String tag,int[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_e(String tag,boolean msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void log_e(String tag,float msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_e(String tag,float[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_e(String tag,double msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_e(String tag,double[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_e(String tag,Object msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag, msg.toString());
			}
		}
	}
	
	public static void log_e(String tag,Array msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_e(String tag,List<Object> msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)&&msg!=null) {
				Log.e(tag, StringUtils.getStringFromList(msg));
			}
		}
	}
	
	public static void log_e(String tag,String msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag, msg);
			}
		}
	}
	
	public static void log_e(String tag,CharSequence[] arrayStr){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag, StringUtils.getStringFromArray(arrayStr));
			}
		}
	}
	
	/*verbose组*/
	public static void log_v(String msg){
		log_v("tag", msg);
	}
	
	public static void log_v(String tag,String msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)&&!TextUtils.isEmpty(msg)) {
				Log.v(tag, msg);
			}
		}
	}
	
	public static void log_v(String tag,int msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.e(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void log_v(String tag,int[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.v(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_v(String tag,boolean msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.v(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void log_v(String tag,float msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.v(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_v(String tag,float[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.v(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_v(String tag,double msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.v(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_v(String tag,double[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.v(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_v(String tag,Object msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.v(tag, msg.toString());
			}
		}
	}
	
	public static void log_v(String tag,Array msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.v(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	/*debug组*/
	public static void log_d(String msg){
		log_d("tag", msg);
	}
	
	public static void log_d(String tag,String msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)&&!TextUtils.isEmpty(msg)) {
				Log.d(tag, msg);
			}
		}
	}
	
	public static void log_d(String tag,int[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.d(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_d(String tag,int msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.d(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void log_d(String tag,boolean msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.d(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void log_d(String tag,float msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.d(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_d(String tag,float[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.d(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_d(String tag,double msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.d(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_d(String tag,double[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.d(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_d(String tag,Object msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.d(tag, msg.toString());
			}
		}
	}
	
	public static void log_d(String tag,Array msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.d(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	/*info组*/
	public static void log_i(String msg){
		log_i("tag", msg);
	}
	
	public static void log_i(String tag,String msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)&&!TextUtils.isEmpty(msg)) {
				Log.i(tag, msg);
			}
		}
	}
	
	public static void log_i(String tag,int msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.i(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void log_i(String tag,int[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.i(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_i(String tag,boolean msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.i(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void log_i(String tag,float msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.i(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_i(String tag,float[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.i(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_i(String tag,double msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.i(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_i(String tag,double[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.i(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_i(String tag,Object msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.i(tag, msg.toString());
			}
		}
	}
	
	public static void log_i(String tag,Array msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.i(tag, StringUtils.getStringFromArray(msg));			}
		}
	}
	
	/*warn组*/
	public static void log_w(String msg){
		log_w("tag", msg);
	}
	
	public static void log_w(String tag,String msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)&&!TextUtils.isEmpty(msg)) {
				Log.w(tag, msg);
			}
		}
	}
	
	public static void Log_w(String tag,int msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.w(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void Log_w(String tag,int[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.w(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_w(String tag,boolean msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.w(tag,String.valueOf(msg));
			}
		}
	}
	
	public static void log_w(String tag,float msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.w(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_w(String tag,float[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.w(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_w(String tag,double msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.w(tag, String.valueOf(msg));
			}
		}
	}
	
	public static void log_w(String tag,double[] msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.w(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	public static void log_w(String tag,Object msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.w(tag, msg.toString());
			}
		}
	}
	
	public static void log_w(String tag,Array msg){
		if (debugeSwitch) {
			if (!TextUtils.isEmpty(tag)) {
				Log.w(tag, StringUtils.getStringFromArray(msg));
			}
		}
	}
	
	/*toast组*/
	public static void showToast(Context ctx,String msg){
		showToast(ctx, msg, 3000);
	}
	
	public static void showToast(Context ctx,String msg,int length){
		if(debugeSwitch)
		if(!TextUtils.isEmpty(msg)){
			Toast.makeText(ctx, msg, length).show();
		}
		
	}
	
	/*print组*/
	public static void print(String msg){
		if(debugeSwitch)
		if(!TextUtils.isEmpty(msg)){
			System.out.println(msg);
		}
	}
	
}
