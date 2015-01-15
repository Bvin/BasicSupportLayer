package cn.bvin.lib.app;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

public class PlaceViewHolder {
	public View loadingView;
	public View emptyView;
	public View errorView;
	private Context context;
	private LoadMode mLoadMode;

	public PlaceViewHolder(Activity activity) {
		this.context = activity;
	}

	public void setLoadMode(LoadMode mLoadMode) {
		this.mLoadMode = mLoadMode;
	}

	/**
	 * Inits the hold view. 所有视图都不显示
	 */
	public void initHoldView(View loadingView, View emptyView, View errorView) {
		this.loadingView = loadingView;
		this.emptyView = emptyView;
		this.errorView = errorView;
		setEmptyViewVisibility(false);
		setErrorViewVisibility(false);
		setLoadingViewVisibility(false);
	}

	public void setOnErrorViewClickListener(OnClickListener listener) {
		if (this.errorView == null) {
			return;
		}
		this.errorView.setOnClickListener(listener);
	}

	public void resetHoldView() {
		this.setEmptyViewVisibility(false);// 开始请求的时候设置不可见
		this.setErrorViewVisibility(false);

		if (mLoadMode == null) {
			mLoadMode = LoadMode.FirstLoad;
		}
		if (mLoadMode == LoadMode.FirstLoad
				|| mLoadMode == LoadMode.FilterRefreshLoad
				|| mLoadMode == LoadMode.ReLoad) {// 第一次加载和筛选的时出现
			this.setLoadingViewVisibility(true);
		} else {
			this.setLoadingViewVisibility(false);
		}
	}

	/**
	 * Sets the empty view visibility. 只有在请求后得到数据为空才显示，每一次请求设置不可见(复位)
	 * 
	 * @param visibable
	 *            the new empty view visibility
	 */
	public void setEmptyViewVisibility(boolean visibable) {
		if (this.emptyView != null) {
			this.emptyView.setVisibility(visibable ? View.VISIBLE : View.GONE);
		}
	}

	public void setEmptyViewVisibility(boolean visibable, String emptyTips) {
		if (this.emptyView != null) {
			this.emptyView.setVisibility(visibable ? View.VISIBLE : View.GONE);
			if (!TextUtils.isEmpty(emptyTips)) {
				/*TextView tvEmptyTips = (TextView) this.emptyView
						.findViewById(R.id.tvEmptyTips);
				tvEmptyTips.setText(emptyTips);*/
			}
		}
	}

	/**
	 * Sets the error view visibility. 1.请求成功后，数据有误显示
	 * 2.请求后触发VolleyErroR，根据情况更改字符 3.每一次请求复位(先设置为不可见，文字也复位)
	 * 
	 * @param visibable
	 *            the new error view visibility
	 */
	public void setErrorViewVisibility(boolean visibable) {
		if (this.errorView != null) {
			this.errorView.setVisibility(visibable ? View.VISIBLE : View.GONE);
		}
	}

	public void setErrorViewVisibility(boolean visibable, String errorTips,
			String solveTips) {

		this.errorView.setVisibility(visibable ? View.VISIBLE : View.GONE);
		/*String sceneFormat = ResourceFinder.findString(context,
				R.string.error_scene_tipss);
		String solveFormat = ResourceFinder.findString(context,
				R.string.error_scene_tipss);*/
		String solveFormat = "轻触重试！";
		StringBuilder sb = new StringBuilder();
		if (!TextUtils.isEmpty(errorTips)) {
			sb.append(errorTips);
		}
		if (!TextUtils.isEmpty(solveTips)) {
			sb.append("，");
			sb.append(solveTips);
		} else {
			sb.append("，");
			sb.append(solveFormat);
		}
		/*((TextView) this.errorView.findViewById(R.id.tvErrorSceneTips))
				.setText(visibable ? (TextUtils.isEmpty(sb.toString()) ? sceneFormat
						: sb.toString())
						: sceneFormat);*/
	}

	
	public void setEmptyTips(String emptyTips) {
		setEmptyViewVisibility(true, emptyTips);
	}

	public void setErrorTips(String sceneTips) {
		setErrorViewVisibility(true, sceneTips, null);
	}

	public void setErrorTips(String sceneTips, String solveTips) {
		setErrorViewVisibility(true, sceneTips, solveTips);

	}

	/**
	 * Sets the loading view visibility. 1.在第一次请求的时候出现
	 * 2.在筛选栏筛选的时候出现(筛选是从新加载，刷新是显示ListView头部) 3.任何网络响应后不可见
	 * 
	 * @param visibable
	 *            the new loading view visibility
	 */
	public void setLoadingViewVisibility(boolean visibable) {
		if (this.loadingView != null) {
			this.loadingView
					.setVisibility(visibable ? View.VISIBLE : View.GONE);
		}
	}
	
	public void showLoadingView(){
		setLoadingViewVisibility(true);
	}

	public void showLoadingView(String tips){
		setLoadingTips(tips);
		setLoadingViewVisibility(true);
	}
	
	public void hideLoadingView(){
		/*setLoadingTips(ResourceFinder.findString(context, R.attr.loading_tip));*/
		setLoadingViewVisibility(false);
	}
	
	public void setLoadingTips(String loadingTips){
		/*if (loadingView.findViewById(R.id.tvLoadTip)==null||TextUtils.isEmpty(loadingTips)) {
			return;
		}
		TextView tvLoadTip = (TextView) loadingView.findViewById(R.id.tvLoadTip);
		if(tvLoadTip!=null)
			tvLoadTip.setText(loadingTips);*/
	}
	
	public enum LoadMode {
		FirstLoad, // 第一次加载(pageIndex = 1&&shouldReLoad==false)
		RefreshLoad, PullRefreshLoad, FilterRefreshLoad, LoadMoreLoad, ReLoad
	}

	
}
