package cn.bvin.lib.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

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
}
