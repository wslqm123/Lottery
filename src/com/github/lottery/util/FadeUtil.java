package com.github.lottery.util;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * 淡入淡出的切换
 * 
 * @author LQM
 *
 */
public class FadeUtil {

	/**
	 * 当前正在展示的界面淡出，动画执行时间 在这个执行过程中，第二个界面处于等待状态 第二个界面淡入，动画执行时间
	 */

	private static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			View view = (View) msg.obj;
			ViewGroup parent = (ViewGroup) view.getParent();
			parent.removeView(view);
		}

	};

	public static void fadeOut(final View view, long duration) {

		AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
		alphaAnimation.setDuration(duration);

		// 动画执行完以后删除view
		// 增加d动画执行完成后的监听
		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				// ViewGroup parent = (ViewGroup) view.getParent();
				// parent.removeView(view);

				Message msg = Message.obtain();
				msg.obj = view;
				handler.sendMessage(msg);

			}
		});

		view.startAnimation(alphaAnimation);
	}

	public static void fadeIn(View view, long delay, long duration) {

		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setStartOffset(delay);
		alphaAnimation.setDuration(duration);
		view.startAnimation(alphaAnimation);
	}
}
