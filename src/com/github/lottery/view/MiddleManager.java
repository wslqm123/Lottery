package com.github.lottery.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import com.github.lottery.ConstantValue;
import com.github.lottery.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

/**
 * 中间容器管理工具
 * 
 * @author LQM
 *
 */
public class MiddleManager extends Observable{

	private static final String TAG = "MiddleManager";

	private static MiddleManager instance = new MiddleManager();

	private MiddleManager() {
	};

	private RelativeLayout middle;

	// K(String)唯一标识的BaseUI子类
	private Map<String, BaseUI> VIEWCACHE = new HashMap<String, BaseUI>();

	// 当前正在显示的界面
	private BaseUI currentUI;

	// 用户操作的历史记录
	private LinkedList<String> HISTORY = new LinkedList<String>();

	/**
	 * 切换界面 问题：“三个容器的联动”
	 * 
	 * @param ui
	 */

	public void changeUI(Class<? extends BaseUI> targetClazz) {
		// 判断当前正在展示的界面和目标界面是否相同
		if (currentUI != null && currentUI.getClass() == targetClazz) {
			return;
		}

		BaseUI targetUI = null;
		// 判断是否创建过，是则重用，否则创建
		String key = targetClazz.getSimpleName();
		if (VIEWCACHE.containsKey(key)) {
			targetUI = VIEWCACHE.get(key);
		} else {

			try {
				Constructor<? extends BaseUI> constructor = targetClazz
						.getConstructor(Context.class);

				targetUI = constructor.newInstance(getcontext());

				VIEWCACHE.put(key, targetUI);
			} catch (Exception e) {
				throw new RuntimeException("constructor new instance error");
			}

		}

		Log.i(TAG, targetUI.toString());
		// 切换界面的核心代码
		middle.removeAllViews();
		// FadeUtil.fadeOut(child1, 1000);
		View child = targetUI.getChild();
		middle.addView(child);
		// FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(getcontext(),
				R.anim.ia_view_change));
		currentUI = targetUI;
		// 将当前界面放到栈顶
		HISTORY.addFirst(key);

		// 当中间容器切换成功时，处理另外两个容器变化
		changeTitleAndBottom();

	}

	private void changeTitleAndBottom() {
		// 1.界面一对应未登录标题和通用导航
		// 2.界面二对应通用导航和玩法导航

		// 当前正在展示的如果是第一个界面
		// 方案一：存在问题，比对的依据：名称 或者 字节码
		// 在界面处理初期，将所有的界面名称确定
		// 如果是字节码，将所有的界面都的创建完成
		// if (currentUI.getClass().getSimpleName().equals("FirstUI")) {
		// TitleManager.getInstance().showUnLoginTitle();
		// BottomManager.getInstrance().showCommonBottom();
		// }
		//
		// if (currentUI.getClass().getSimpleName().equals("SecondUI")) {
		// TitleManager.getInstance().showCommonTitle();
		// BottomManager.getInstrance().showGameBottom();
		// }

		/*// 方案二：更换比对依据
		switch (currentUI.getID()) {
		case ConstantValue.VIEW_FIRST:
			TitleManager.getInstance().showUnLoginTitle();
			BottomManager.getInstrance().showCommonBottom();
			break;
		case ConstantValue.VIEW_SECOND:
			TitleManager.getInstance().showCommonTitle();
			BottomManager.getInstrance().showGameBottom();
			break;

		}*/

		// 降低三个容器的耦合度
		// 当中间容器变动的时候，中间容器“通知”其他的容器，你们该变动了，唯一的标示传递，其他容器依据唯一标示进行容器内容的切换
		// 通知：
		// 广播：多个应用
		// 为中间容器的变动增加了监听——观察者设计模式

		// ①将中间容器变成被观察的对象
		// ②标题和底部导航变成观察者
		// ③建立观察者和被观察者之间的关系（标题和底部导航添加到观察者的容器里面）
		// ④一旦中间容器变动，修改boolean，然后通知所有的观察者.updata()
		
		setChanged();
		notifyObservers(currentUI.getID());

	}

	/**
	 * 切换界面 问题：“中间容器中，每次切换没有判断当前正在展示和需要切换的目标是不是同一个”
	 * 
	 * @param ui
	 */
	public void changeUI3(Class<? extends BaseUI> targetClazz) {
		// 判断当前正在展示的界面和目标界面是否相同
		if (currentUI != null && currentUI.getClass() == targetClazz) {
			return;
		}

		BaseUI targetUI = null;
		// 判断是否创建过，是则重用，否则创建
		String key = targetClazz.getSimpleName();
		if (VIEWCACHE.containsKey(key)) {
			targetUI = VIEWCACHE.get(key);
		} else {

			try {
				Constructor<? extends BaseUI> constructor = targetClazz
						.getConstructor(Context.class);

				targetUI = constructor.newInstance(getcontext());

				VIEWCACHE.put(key, targetUI);
			} catch (Exception e) {
				throw new RuntimeException("constructor new instance error");
			}

		}

		Log.i(TAG, targetUI.toString());
		// 切换界面的核心代码
		middle.removeAllViews();
		// FadeUtil.fadeOut(child1, 1000);
		View child = targetUI.getChild();
		middle.addView(child);
		// FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(getcontext(),
				R.anim.ia_view_change));
		currentUI = targetUI;
		// 将当前界面放到栈顶
		HISTORY.addFirst(key);
	}

	/**
	 * 切换界面 问题：每次点击都创建新的界面
	 * 
	 * @param ui
	 */
	public void changeUI2(Class<? extends BaseUI> targetClazz) {
		BaseUI targetUI = null;
		// 判断是否创建过，是则重用，否则创建
		String key = targetClazz.getSimpleName();
		if (VIEWCACHE.containsKey(key)) {
			targetUI = VIEWCACHE.get(key);
		} else {

			try {
				Constructor<? extends BaseUI> constructor = targetClazz
						.getConstructor(Context.class);

				targetUI = constructor.newInstance(getcontext());

				VIEWCACHE.put(key, targetUI);
			} catch (Exception e) {
				throw new RuntimeException("constructor new instance error");
			}

		}

		Log.i(TAG, targetUI.toString());
		// 切换界面的核心代码
		middle.removeAllViews();
		// FadeUtil.fadeOut(child1, 1000);
		View child = targetUI.getChild();
		middle.addView(child);
		// FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(getcontext(),
				R.anim.ia_view_change));
		currentUI = targetUI;

	}

	/**
	 * 切换界面
	 * 
	 * @param ui
	 */
	public void changeUI1(BaseUI ui) {
		// 切换界面的核心代码
		middle.removeAllViews();
		// FadeUtil.fadeOut(child1, 1000);
		View child = ui.getChild();
		middle.addView(child);
		// FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(getcontext(),
				R.anim.ia_view_change));
	}

	public Context getcontext() {
		return middle.getContext();

	}

	public static MiddleManager getInstance() {
		return instance;
	}

	public void setMiddle(RelativeLayout middle) {
		this.middle = middle;
	}

	public boolean goBack() {
		// 记录用户操作历史
		// 频繁操作栈顶（添加）
		// 获取栈顶
		// 删除栈顶
		// 有序集合

		if (HISTORY.size() > 0) {
			// 当用户误操作到返回键，不退出应用
			if (HISTORY.size() == 1) {
				return false;
			}

			// NoSuchElementException - if this LinkedList is empty.
			HISTORY.removeFirst();
			if (HISTORY.size() > 0) {
				// NoSuchElementException - if this LinkedList is empty.
				String key = HISTORY.getFirst();

				BaseUI targetUI = VIEWCACHE.get(key);
				middle.removeAllViews();
				middle.addView(targetUI.getChild());
				currentUI = targetUI;

				changeTitleAndBottom();
				return true;
			}
		}

		return false;
	}
}
