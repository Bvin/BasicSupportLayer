package cn.bvin.lib.app;

import cn.bvin.lib.app.TestLauncher.LaunchMeta;
import android.support.v4.app.FragmentActivity;

public class TestLaunchActivity extends FragmentActivity{

	private TestLauncher mTestLauncher;
	
	/**此方法不能轻易用，只能在manifest中设置Main和Launch的Activity中调用*/
	protected void launch(LaunchMeta launchMeta) {
		if (mTestLauncher==null) {
			mTestLauncher = new TestLauncher(this);
		}
		if (launchMeta!=null) {
			mTestLauncher.launch(launchMeta);
			finish();//启动后将结束自己
		}
		
	}

	/**实现此方法，便可快速测试，启动app直接跳转至你所给定的目标
	 * Activity或Service或广播
	 * */
	protected static LaunchMeta buildLaunchMeta() {
		return null;
	}
	
}
