package com.github.lottery.view;

import android.R.integer;
import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 通用界面的基类
 * 
 * @author LQM
 *
 */
public abstract class BaseUI implements View.OnClickListener {

	protected Context context;

	// 显示到中间容器
	protected ViewGroup showInMiddle;

	public BaseUI(Context context) {
		this.context = context;

		init();

		setListener();
	}

	/**
	 * 初始化
	 * 
	 * @return
	 */
	public abstract void init();

	/**
	 * 设置监听
	 * 
	 * @return
	 */
	public abstract void setListener();

	/**
	 * 获取需要在中间容器加载的内容
	 * 
	 * @return
	 */
	public View getChild() {
		// 设置layout参数
		// root=null
		// showInMiddle.getLayoutParams() = null
		// root!=null
		// return root

		if (showInMiddle.getLayoutParams() == null) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			showInMiddle.setLayoutParams(params);
		}
		return showInMiddle;
	}

	/**
	 * 获取每个界面的标识--容器联动时比对的依据
	 * 
	 * @return
	 */
	public abstract int getID();

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
	
	public View findViewById(int id){
		return showInMiddle.findViewById(id);
		
	}
}
