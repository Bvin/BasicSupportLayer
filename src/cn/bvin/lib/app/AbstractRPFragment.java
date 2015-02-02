package cn.bvin.lib.app;

import cn.bvin.lib.utils.SystemUtils.ResourceFinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * 
 * @ClassName: AbstractRequestPlaceholderFragment 
 * @Description: 请求占位Fragment的抽象类，由子类去实现Placeholder的布局形式和控件绑定
 * @author: Bvin
 * @date: 2015年1月30日 下午5:25:02
 */
public abstract class AbstractRPFragment extends WiseFragment{

	protected View mLoadingFrame;
	protected View mEmptyFrame;
	protected View mErrorFrame;
	protected TextView tvLoadTip;
	protected TextView tvEmptyTips;
	protected TextView tvErrorSceneTips;
	protected String defaultLoadTips;//默认加载数据时的提示字符
	protected String defaultEmptyTips;//默认空数据提示字符
	protected String defaultSceneError;//默认出错提示字符
	protected String defaultErrorSolution;//默认出错解决方案的提示字符串
	
	/**必须在onCreateView()方法中主动给rootView，找出控件赋予默认值*/
	public abstract void initViews(View rootView);
	
	/**设置LoadingFrame的可见性*/
	private void setLoadingFrameVisibility(boolean visibable) {
		if (this.mLoadingFrame != null) {
			this.mLoadingFrame.setVisibility(visibable ? View.VISIBLE : View.GONE);
		}
	}
	
	/**设置LoadingFrame的提示字符*/
	private void setLoadingTips(String loadingTips){
		if (TextUtils.isEmpty(loadingTips)) {
			return;
		}
		if(tvLoadTip!=null)
			tvLoadTip.setText(loadingTips);
	}
	
	/**显示加载进度圈*/
	public void showLoadingProgress(){
		setLoadingFrameVisibility(true);
	}

	/**显示加载进度圈，并且显示进度圈提示*/
	public void showLoadingProgress(String tips){
		setLoadingTips(tips);
		showLoadingProgress();
	}
	
	/**隐藏加载进度圈，并且把进度圈提示还原到默认*/
	public void hideLoadingProgress(){
		setLoadingTips(defaultLoadTips);
		setLoadingFrameVisibility(false);
	}
	
	public void hideEmptyFrame() {
		setEmptyFrameVisibility(false);
	}
	
	private void setEmptyFrameVisibility(boolean visibable) {
		if (this.mEmptyFrame != null) {
			this.mEmptyFrame.setVisibility(visibable ? View.VISIBLE : View.GONE);
		}
	}
	
	private void setEmptyView(AdapterView adapterView,String tips,int emptyImage) {
		if (adapterView==null) {
			return;
		}
		setEmptyFrameVisibility(true);
		adapterView.setEmptyView(mEmptyFrame);
		if (!TextUtils.isEmpty(tips)) {
			tvEmptyTips.setText(tips);
		}
		if (emptyImage>0) {
			tvEmptyTips.setCompoundDrawables(null, ResourceFinder.findDrawable(getActivity(), emptyImage), null, null);
		}
		
	}
	
	public void setEmptyView(AdapterView adapterView,String tips) {
		setEmptyView(adapterView, tips, 0);
	}
	
	public void setEmptyView(AdapterView adapterView) {
		setEmptyView(adapterView, null);
	}
	
	public void setErrorFrameVisibility(boolean visibable) {
		if (this.mErrorFrame != null) {
			this.mErrorFrame.setVisibility(visibable ? View.VISIBLE : View.GONE);
		}
	}
	
	public void hideErrorFrame() {
		setErrorFrameVisibility(false);
	}
	
	private void setErrorFrameVisibility(boolean visibable, String errorTips,
			String solveTips) {
		setErrorFrameVisibility(visibable);
		StringBuilder sb = new StringBuilder();
		if (!TextUtils.isEmpty(errorTips)) {
			sb.append(errorTips);
		}
		if (!TextUtils.isEmpty(solveTips)) {
			sb.append("，");
			sb.append(solveTips);
		} else {
			sb.append("，");
			sb.append(defaultErrorSolution);
		}
		tvErrorSceneTips.setText(visibable ? (TextUtils.isEmpty(sb.toString()) ? defaultErrorSolution
						: sb.toString())
						: defaultErrorSolution);
	}
	
	public void setErrorTips(String sceneTips) {
		setErrorFrameVisibility(true, sceneTips, null);
	}

	public void setErrorTips(String sceneTips, String solveTips) {
		setErrorFrameVisibility(true, sceneTips, solveTips);

	}

	public void resetHoldView() {
		//TODO 如果有多次请求的话，在请求开始的时候需要重置HoldView
		
	}
}
