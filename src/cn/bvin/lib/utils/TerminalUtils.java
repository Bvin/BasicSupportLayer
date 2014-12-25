package cn.bvin.lib.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.text.TextUtils;


public class TerminalUtils {

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
