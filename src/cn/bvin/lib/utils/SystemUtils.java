package cn.bvin.lib.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;


import com.google.common.base.Splitter;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StatFs;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;

public class SystemUtils {

	public static class FileUtils {
		
		public static boolean copy(InputStream inputStream, OutputStream outputStream) {
			try {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = inputStream.read(buffer)) > 0) {
					outputStream.write(buffer, 0, length);
				}
				inputStream.close();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					if (inputStream != null)
						inputStream.close();
					if (outputStream != null)
						outputStream.close();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				return false;
			}
			return true;
		}

		public static boolean copy(InputStream inputStream,File outFile) {
			if (outFile!=null&&outFile.exists()&&outFile.isFile()) {
				try {
					OutputStream outputStream = new FileOutputStream(outFile);
					return copy(inputStream, outputStream);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
		
		public static boolean isPresent(File file) {
			return file != null&&file.exists()&&file.isFile();
		}
		
		/**
		 * Gets the dir available size.
		 * 获取指定目录剩余空间
		 * @param path the path
		 * @return the dir available size
		 */
		@SuppressWarnings("deprecation")
		public static long getDirAvailableSize(String path){
			StatFs stat = new StatFs(path);
			long totalBlocks = stat.getBlockCount();
			return totalBlocks;
		}
		
		private static File getAppDataDir(Context context) {
			final String appDir = "/Android/data/" + context.getPackageName();
			File file = new File(Environment.getExternalStorageDirectory()
					.getPath() + appDir);
			return file;
		}
		
		public static File getCacheDir(Context context) {
			File file = new File(getAppDataDir(context),"cache");
			return file;
		}
		
		public static String getCacheDirPath(Context context) {
			return getCacheDir(context).getAbsolutePath();
		}
		
		public static File getImageDir(Context context) {
			File file = new File(getAppDataDir(context),"images");
			return file;
		}
		
		public static String getImageDirPath(Context context) {
			return getImageDir(context).getAbsolutePath();
		}
		
		public static File getDownloadDir(Context context) {
			File file = new File(getAppDataDir(context),"download");
			return file;
		}
		
		public static String getDownloadDirPath(Context context) {
			return getDownloadDir(context).getAbsolutePath();
		}

		public static File getLogDir(Context context) {
			File file = new File(getAppDataDir(context),"log");
			return file;
		}
		
		public static String getLogDirPath(Context context) {
			return getLogDir(context).getAbsolutePath();
		}
		
		/**
		 * 转换文件大小
		 */
		public static String formetFileSize(long size) {
			DecimalFormat df = new DecimalFormat("#.00");
			String fileSizeString = "";
			if (size < 1024) {
				fileSizeString = df.format((double) size) + "B";
			} else if (size < 1048576) {
				fileSizeString = df.format((double) size / 1024) + "K";
			} else if (size < 1073741824) {
				fileSizeString = df.format((double) size / 1048576) + "M";
			} else {
				fileSizeString = df.format((double) size / 1073741824) + "G";
			}
			return fileSizeString;
		}
	
		public static File writeData(File file,byte[] data) {
			if (data==null||data.length<=0) {
				return null;
			}
			try {
				if (!file.exists()) {
		            file.createNewFile();
		        }
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.seek(file.length());
		        raf.write(data);
		        raf.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return file;
	        
		}
	}
	
	public static class ViewUtils {
		
		/**还没有对Invisible进行处理*/
		public static void setVisibility(View view,boolean visible) {
			view.setVisibility(visible?View.VISIBLE:View.GONE);
		}
		
		/**
		 * 
		 * @Title: setInputPolicy 
		 * @Description: 为EditText设置输入策略
		 * @param editText 目标输入框
		 * @param inputType 输入类型
		 * @param accepted 接收输入的字符，设置了这个就默认了一个KeyListener
		 * @param maxLength 最大输入字符数，默认设置Filters
		 * @return: void
		 */
		public static void setInputPolicy(EditText editText,int inputType,String accepted,int maxLength) {
			if (editText==null) return;
			editText.setInputType(inputType);
			editText.setKeyListener(DigitsKeyListener.getInstance(accepted));
			InputFilter[] filters = {new InputFilter.LengthFilter(maxLength)};  
			editText.setFilters(filters); 
		}
	}
	
	public static class TerminalUtils {

		public static final String COMMAND_SU       = "su";
	    public static final String COMMAND_SH       = "sh";
	    public static final String COMMAND_EXIT     = "exit\n";
	    public static final String COMMAND_LINE_END = "\n";
	    
		public static boolean rootCommand(String command) {
			Process process = null;
			boolean isSuc = false;
			try {
				process = exec(COMMAND_SU);
				isSuc = writeCommand(process.getOutputStream(), command);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
				isSuc = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (process != null) {
					process.destroy();
				}
			}
			return isSuc;
		}

		public static boolean shellCommand(String command) {
			Process process = null;
			boolean isSuc = false;
			try {
				process = exec(COMMAND_SH);
				isSuc = writeCommand(process.getOutputStream(), command);
			} catch (IOException e) {
				e.printStackTrace();
				isSuc = false;
			}finally {
				if (process != null) {
					process.destroy();
				}
			}
			return isSuc;
		}
		
		/** 判断手机是否root，不弹出root请求框<br/> */
	    public static boolean isDeviceRoot() {
	        String binPath = "/system/bin/su";
	        String xBinPath = "/system/xbin/su";
	        if (new File(binPath).exists() && isExecutable(binPath))
	            return true;
	        if (new File(xBinPath).exists() && isExecutable(xBinPath))
	            return true;
	        return false;
	    }

	    /**判断/system/bin/su和/system/xbin/su文件是否存在*/
	    private static boolean isExecutable(String filePath) {
	        Process p = null;
	        try {
	            p = Runtime.getRuntime().exec("ls -l " + filePath);
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    p.getInputStream()));
	            String str = in.readLine();
	            if (str != null && str.length() >= 4) {
	                char flag = str.charAt(3);
	                if (flag == 's' || flag == 'x')
	                    return true;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            if(p!=null){
	                p.destroy();
	            }
	        }
	        return false;
	    }
		
		private static Process exec(String prog) throws IOException {
			return Runtime.getRuntime().exec(prog);
		}
		
		private static boolean writeCommand(OutputStream out, String command) {
			boolean isSuc = false;
			DataOutputStream os = new DataOutputStream(out);
			try {
				os.writeBytes(command + COMMAND_LINE_END);
				os.writeBytes(COMMAND_EXIT);
				os.flush();
				isSuc = true;
			} catch (IOException e) {
				e.printStackTrace();
				isSuc = false;
			}finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (Exception e) {
				}
			}
			return isSuc;
			
		}

		public static boolean silentInstall(String apkPath) throws Exception {
			boolean isPresent = true;
			if (TextUtils.isEmpty(apkPath)) {
				isPresent = false;
				throw new Exception("apk path is empty.");
			}
			File file = new File(apkPath);
			if (file == null || file.length() <= 0) {
				isPresent = false;
				throw new Exception("apk file is null.");
			}
			if (!file.exists()) {
				isPresent = false;
				throw new Exception("apk file is't exists.");
			}
			if ( !file.isFile()) {
				isPresent = false;
				throw new Exception("maby apk path is dir not a file.");
			}
			if (isPresent) {//apk文件确实存在就执行root
				StringBuilder command = new StringBuilder();
				command.append("chmod 777 "+apkPath+COMMAND_LINE_END);//给权限777
				command.append("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install -r "+apkPath);//pm install -r
				return rootCommand(command.toString());
			}
			return isPresent;  
		}
		
		public static boolean silentUninstall(String apkPackage,boolean keepData) throws Exception {
			boolean isPresent = true;
			if (TextUtils.isEmpty(apkPackage)) {
				isPresent = false;
				throw new Exception ("apk package name is empty.");
			}
			if (isPresent) {//包名存在
				StringBuilder command = new StringBuilder();
				command.append("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall")
				.append(keepData ? " -k " : " ")
				.append(apkPackage.replace(" ", "\\ "));
				return rootCommand(command.toString());
			}
			return isPresent;  
		}
	}

	public static class MediaUtils {

		public final static String SYS_RECORD_COMMAND = "/system/bin/screenrecord";

		public static void startRecording(String fileName) {
			final String command = SYS_RECORD_COMMAND + " " + fileName + " ";
			new Thread() {
				@Override
				public void run() {
					TerminalUtils.rootCommand(command);
				};
			}.start();
		}

		public static void stopRecording() {
			/*CommandResult res = TerminalUtils.rootCommand("ps | grep " + SYS_RECORD_COMMAND, false);
			if (StringUtils.isNotEmpty(res.successMsg)) {
				List<String> list = blankSplitter.splitToList(res.successMsg);
				if (list != null && list.size() >= 2) {
					String pidStr = list.get(1);
					Log.d("DEBUG", "pidStr=" + pidStr);
					res = ShellUtils.execCommand("kill -2 " + pidStr, true);
					Log.d("DEBUG", "res:" + res.errorMsg + "|" + res.successMsg + "|" + res.result);
				}
			}*/
		}
	}
	
	/**
	 * The Class ResouceFinder.
	 * 
	 * @ClassName ResouceFinder
	 * @Description 资源查找
	 * @version version
	 * @author Bvin
	 * @update 2013-9-10 下午02:17:41
	 */
	public static class ResourceFinder {

		/**
		 * Find string.
		 * 
		 * @param ctx
		 *            the ctx
		 * @param res
		 *            the res
		 * @return the string
		 */
		public static String findString(Context ctx, int res) {
			String str = null;
			try {
				str = ctx.getResources().getString(res);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}

		/**
		 * Find color.
		 * 
		 * @param ctx
		 *            the ctx
		 * @param res
		 *            the res
		 * @return the int
		 */
		public static int findColor(Context ctx, int res) {
			int color = 0;
			try {
				color = ctx.getResources().getColor(res);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return color;
		}

		/**
		 * 
		 * @Title: findAttrColor 
		 * @Description: 寻找attr实际值
		 * @param ctx||不能用getApplicationContext()
		 * @param attr
		 * @return: int
		 */
		public static int findAttrColor(Context ctx,int attr){
			TypedArray array = ctx.getTheme().obtainStyledAttributes(new int[] {attr }); 
			int backgroundColor = array.getColor(0, 0x000000); 
			array.recycle();
			return backgroundColor;
		}
		
		public static CharSequence findAttrText(Context ctx,int attr){
			TypedArray array = ctx.getTheme().obtainStyledAttributes(new int[] {attr }); 
			CharSequence str = array.getText(attr); 
			array.recycle();
			return str;
		}
		
		
		/**
		 * Find dimens.
		 * 
		 * @param ctx
		 *            the ctx
		 * @param res
		 *            the res
		 * @return the float
		 */
		public static float findDimens(Context ctx, int res) {
			float dimens = 0;
			try {
				dimens = ctx.getResources().getDimension(res);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dimens;
		}

		public static Drawable findDrawable(Context ctx, int res) {
			Drawable drawable = null;
			try {
				drawable = ctx.getResources().getDrawable(res);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return drawable;
		}
	
		private static int reverseResourceId(Context ctx,String resourceName,String typeName) {
		  if (ctx == null) {
            // FIXME 这里请使用 {@link IllegalArgumentException}
            throw new IllegalArgumentException();
          }
		  return ctx.getResources().getIdentifier(resourceName, typeName, ctx.getApplicationContext().getPackageName());
		}
		
		public static int getAnimId(Context ctx,String animResourceName) {
          return reverseResourceId(ctx,animResourceName,"anim");
        }
		
		public static int getArrayId(Context ctx,String arrayResourceName) {
          return reverseResourceId(ctx,arrayResourceName,"array");
        }
		
		public static int getColorId(Context ctx,String colorResourceName) {
          return reverseResourceId(ctx,colorResourceName,"color");
        }

        public static int getDrawableId(Context ctx,String drawableResourceName) {
          return reverseResourceId(ctx,drawableResourceName,"drawable");
        }
		
		public static int getDimenId(Context ctx,String dimenResourceName) {
          return reverseResourceId(ctx,dimenResourceName,"dimen");
        }
		
		public static int getLayoutId(Context ctx,String layoutResourceName) {
          return reverseResourceId(ctx,layoutResourceName,"layout");
        }
		
		public static int getStringId(Context ctx,String stringResourceName) {
          return reverseResourceId(ctx,stringResourceName,"string");
        }
		
		public static int getStyleId(Context ctx,String styleResourceName) {
          return reverseResourceId(ctx,styleResourceName,"style");
        }
		
	}
}
