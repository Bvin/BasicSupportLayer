package cn.bvin.lib.debug;

import java.lang.reflect.Array;
import java.util.Hashtable;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DebugeHelper {

	private static boolean debugeSwitch = true;
	private final static int logLevel = Log.DEBUG;
	private static Hashtable<String, DebugeHelper> sLoggerTable = new Hashtable<String, DebugeHelper>();
	private static String[] users = { "DevA", "DevB", "DevC", "DevD", "DevE", };
	private static DebugeHelper[] loggers = new DebugeHelper[users.length];
	private String user;
	
	public static boolean getDebugeSwitch(){
		return debugeSwitch;
	}
	
	private DebugeHelper(String name) {
		super();
		user = name;
		// TODO Auto-generated constructor stub
	}

	private static DebugeHelper getLogger(String name) {
		DebugeHelper logger = sLoggerTable.get(name);
		if (logger == null) {
			logger = new DebugeHelper(name);
			sLoggerTable.put(name, logger);
		}
		return logger;
	}

	public static DebugeHelper ALog() {
		int leve = 0;
		if (loggers[leve] == null) {
			loggers[leve] = new DebugeHelper(users[leve]);
		}
		return loggers[leve];
	}

	public static DebugeHelper BLog() {
		int leve = 1;
		if (loggers[leve] == null) {
			loggers[leve] = new DebugeHelper(users[leve]);
		}
		return loggers[leve];
	}

	public static DebugeHelper CLog() {
		int leve = 2;
		if (loggers[leve] == null) {
			loggers[leve] = new DebugeHelper(users[leve]);
		}
		return loggers[leve];
	}

	public static DebugeHelper DLog() {
		int leve = 3;
		if (loggers[leve] == null) {
			loggers[leve] = new DebugeHelper(users[leve]);
		}
		return loggers[leve];
	}

	public static DebugeHelper ELog() {
		int leve = 4;
		if (loggers[leve] == null) {
			loggers[leve] = new DebugeHelper(users[leve]);
		}
		return loggers[leve];
	}
	
	public static void openDebuger(){
		debugeSwitch = true;
	}
	
	public static void closeDebuger(){
		debugeSwitch = false;
	}

	/******************** d�?*************************/
	
	public void tag_d(String msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_d(boolean msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_d(int msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_d(int[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_d(float msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_d(float[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_d(double msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_d(double[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_d(Object msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_d(Array msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.DEBUG) {
				SimpleLogger.log_d("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}

	/******************** e�?*************************/
	
	public void tag_e(String msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_e(int msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_e(int[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_e(float msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_e(float[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_e(double msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_e(double[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_e(boolean msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_e(Object msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_e(Array msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	/******************** i�?*************************/

	public void tag_i(String msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}

	public void tag_i(int msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}

	public void tag_i(int[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_i(float msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_i(float[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_i(double msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_i(double[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_i(boolean msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_i(Object msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_i(Array msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.INFO) {
				SimpleLogger.log_i("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	/******************** w�?*************************/
	
	public void tag_w(String msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}

	public void tag_w(int msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_w(int[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_w(float msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_w(float[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_w(double msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_w(double[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_w(boolean msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_w(Object msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_w(Array msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.WARN) {
				SimpleLogger.log_w("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	/******************** v�?*************************/
	
	public void tag_v(String msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_v(int msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_v(int[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_v(float msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_v(float[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_v(double msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_v(double[] msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_v(boolean msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_v(Object msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public void tag_v(Array msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.VERBOSE) {
				SimpleLogger.log_v("@"+user+":"+StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	/******************** print�?*************************/
	
	public void print(String msg){
		if (debugeSwitch) {
			SimpleLogger.print("@"+user+":"+StackTracer.getStackTraceLines()+"<<"+ msg);
		}
	}
	
	/******************** toast�?*************************/
	
	public void showToast(Context ctx,String msg){
		if (debugeSwitch) {
			SimpleLogger.showToast(ctx,msg,Toast.LENGTH_LONG);
		}
	}
	
	/******************** 自定义*************************/
	
	public static void e_tag(String msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e(StackTracer.getStackTraceLines(), msg);
			}
		}
	}
	
	public static void e_tag(int msg) {
		if (debugeSwitch) {
			if (logLevel <= Log.ERROR) {
				SimpleLogger.log_e(StackTracer.getStackTraceLines(), msg);
			}
		}
	}
}
