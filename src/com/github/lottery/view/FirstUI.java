package com.github.lottery.view;

import com.github.lottery.ConstantValue;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class FirstUI extends BaseUI {


	public FirstUI(Context context) {
		super(context);
	}
	
	/**
	 * 获取需要在中间容器加载的控件
	 * @return
	 */
	public View getChild(){
		//简单界面
		TextView textView = new TextView(context);
		
		LayoutParams layoutParams = textView.getLayoutParams();
		layoutParams = new LayoutParams(layoutParams.MATCH_PARENT, layoutParams.MATCH_PARENT);
		textView.setLayoutParams(layoutParams);
		
		textView.setBackgroundColor(Color.BLUE);
		textView.setText("这是第一个简单的界面");
		return textView;
		
	}

	@Override
	public int getID() {
		
		return ConstantValue.VIEW_FIRST;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}
	
}
