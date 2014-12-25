package cn.bvin.lib.widget;

import cn.bvin.lib.basic_support.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @ClassName: CompatCheckBox 
 * @Description: 简易的兼容布局CheckBox
 * @author: Bvin
 * @date: 2014年10月13日 上午11:29:24
 */
public class CompatCheckBox extends LinearLayout implements Checkable{

	private CheckBox cbInComb;
	private TextView tvInComb;
	
	private String text = "";
	private boolean checked = false;
	
	
	public CompatCheckBox(Context context) {
		super(context);
		setClickable(true);
		if (!isInEditMode()) {
			View view = LayoutInflater.from(context).inflate(R.layout.comb_check_box_layout, this, true);  
		}
		
	}

	@SuppressLint("Recycle")
	public CompatCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		setClickable(true);
		
		View view = LayoutInflater.from(context).inflate(R.layout.comb_check_box_layout, this, true); 
		
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CompatCheckBox);
		if(a.hasValue(R.styleable.CompatCheckBox_text)) {
			text = a.getString(R.styleable.CompatCheckBox_text);
		}
		if(a.hasValue(R.styleable.CompatCheckBox_checked)) {
			checked = a.getBoolean(R.styleable.CompatCheckBox_checked, false);
		}
		a.recycle();
		
		
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		if (!isInEditMode()) {
			initViews(getContext());
		}
		
	}
	private void initViews(Context context) {
		cbInComb = (CheckBox)findViewById(R.id.cbInComb);
		if (!isInEditMode()) {
			cbInComb.setChecked(checked);
		}else {
			cbInComb.setChecked(false);
		}
			
		tvInComb = (TextView)findViewById(R.id.tvInComb);
		if (text!=null&&!TextUtils.isEmpty(text)) {
			tvInComb.setText(text);
		}
	}
	
	public void setChecked(boolean checked) {
		if (checked != isChecked()) {
			this.checked = checked;
			cbInComb.setChecked(checked);
		}
	}
	
	public boolean isChecked() {
		return cbInComb.isChecked();
	}
	
	public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
		cbInComb.setOnCheckedChangeListener(listener);
	}
	
	public void setText(CharSequence text) {
		tvInComb.setText(text);
	}
	
	public void setText(int resid) {
		tvInComb.setText(resid);
	}
	
	public CharSequence getText() {
		return tvInComb.getText();
	}

	@Override
	public void toggle() {
		setChecked(!checked);
	}

	@Override
	public boolean performClick() {
		toggle();
		return super.performClick();
	}
	
	
}
