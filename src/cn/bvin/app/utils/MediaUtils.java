package cn.bvin.app.utils;

import com.google.common.base.Splitter;

public class MediaUtils {

	public final static String SYS_RECORD_COMMAND = "/system/bin/screenrecord";
	private final static Splitter blankSplitter = Splitter.on(" ").trimResults().omitEmptyStrings();

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
