package com.github.lottery;

import com.github.lottery.util.FadeUtil;
import com.github.lottery.util.PromptManager;
import com.github.lottery.view.BaseUI;
import com.github.lottery.view.BottomManager;
import com.github.lottery.view.FirstUI;
import com.github.lottery.view.Hall;
import com.github.lottery.view.MiddleManager;
import com.github.lottery.view.SecondUI;
import com.github.lottery.view.TitleManager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 中间占位容器
	private RelativeLayout middle;
	// 第一个界面
	private View child1;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			//changeUI();
			changeUI(new SecondUI(MainActivity.this));
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);

		// //commons-codec.jar:加密用--MD5
		// DigestUtils.md5Hex("");
		// //commons-lang3-3.0-beta.jar:字符串操作
		// //字符串非空判断：null;"";"  "
		// StringUtils.isBlank("");//true
		// StringUtils.isNotBlank("");//false
		// //字符串替换
		// String info = "dfvNUM1ksdfgdfbhNUM2fnjlssfs";
		// info = StringUtils.replaceEach(info,new String[]{"NUM1","NUM2"},new
		// String[]{"num","num"});
		// //字符截取
		// info = "<body>.....</body>";
		// StringUtils.substringBetween(info, "<body>","</body>");

		init();

	}

	private void init() {
		TitleManager manager = TitleManager.getInstance();
		manager.init(this);
		manager.showUnLoginTitle();

		BottomManager.getInstrance().init(this);
		BottomManager.getInstrance().showCommonBottom();

		middle = (RelativeLayout) findViewById(R.id.ii_middle);
		MiddleManager.getInstance().setMiddle(middle);
		//loadaFirstUI();
		//MiddleManager.getInstance().changeUI(FirstUI.class);
		MiddleManager.getInstance().changeUI(Hall.class);
		
		// 建立观察者和被观察者之间的关系（标题和底部导航添加到观察者的容器里面）
		MiddleManager.getInstance().addObserver(TitleManager.getInstance());
		MiddleManager.getInstance().addObserver(BottomManager.getInstrance());

		// 当第一个界面加载完两秒以后，第二个界面显示
		//handler.sendEmptyMessageDelayed(0, 2000);
	}

	private void loadaFirstUI() {
		FirstUI firstUI = new FirstUI(this);
		child1 = firstUI.getChild();
		middle.addView(child1);
	}

	protected void loadSecondUI() {
		SecondUI secondUI = new SecondUI(this);
		View child = secondUI.getChild();
		middle.addView(child);
		FadeUtil.fadeIn(child, 1000, 1000);

		// 执行切换动画
		// child.startAnimation(AnimationUtils.loadAnimation(this,
		// R.anim.ia_view_change));
	}

	/**
	 * 切换界面
	 * 
	 * @param ui
	 */
	protected void changeUI(BaseUI ui) {
		//切换界面的核心代码
		middle.removeAllViews();
		//FadeUtil.fadeOut(child1, 1000);
		View child = ui.getChild();
		middle.addView(child);
		//FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(this,
				R.anim.ia_view_change));
	}

	protected void changeUI() {
		// 切换界面时清理上一个显示内容
		// middle.removeAllViews();
		// middle.removeView(child1);
		FadeUtil.fadeOut(child1, 1000);
		loadSecondUI();

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			boolean reuslt = MiddleManager.getInstance().goBack();
			//返回键操作失败
			if (!reuslt) {
			//	Toast.makeText(this, "确定要退出吗？", 0).show();
			PromptManager.showExitSystem(this);
			}
			return false;
		}
		
		return super.onKeyDown(keyCode, event);
	}
}
