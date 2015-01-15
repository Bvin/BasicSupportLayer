package cn.bvin.lib.interf;

public interface IActivityInit {

	/**解析、接收Intent传递的参数*/
	public void parserIntent();
	
	/**初始化数据*/
	public void initData();
	
	/**控件绑定，设置事件监听*/
	public void initViews();
	
}
