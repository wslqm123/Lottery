package com.github.lottery.view;

import com.github.lottery.R;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ���Ƶײ���������
 * 
 * @author Administrator
 * 
 */
public class BottomManager1 {
	protected static final String TAG = "BottomManager";
	/******************* ��һ������������Ĵ���(����ģʽ) ***************************************************/
	// ����һ����̬ʵ��
	private static BottomManager1 instrance;

	// ����˽��
	private BottomManager1() {
	}

	// �ṩͳһ�Ķ����ȡʵ�������
	public static BottomManager1 getInstrance() {
		if (instrance == null) {
			instrance = new BottomManager1();
		}
		return instrance;
	}

	/*********************************************************************************************/
	/******************* �ڶ�������ʼ������������������ؿؼ����ü��� *********************************/

	/********** �ײ��˵����� **********/
	private RelativeLayout bottomMenuContainer;
	/************ �ײ����� ************/
	private LinearLayout commonBottom;// ����ͨ�õ���
	private LinearLayout playBottom;// ����

	/***************** ������ť ******************/

	/************ ���ʵ����ײ���ť����ʾ��Ϣ ************/
	private ImageButton cleanButton;
	private ImageButton addButton;

	private TextView playBottomNotice;

	/************ ͨ�õ����ײ���ť ************/
	private ImageButton homeButton;
	private ImageButton hallButton;
	private ImageButton rechargeButton;
	private ImageButton myselfButton;

	public void init(Activity activity) {
		bottomMenuContainer = (RelativeLayout) activity
				.findViewById(R.id.ii_bottom);
		commonBottom = (LinearLayout) activity
				.findViewById(R.id.ii_bottom_common);
		playBottom = (LinearLayout) activity.findViewById(R.id.ii_bottom_game);

		playBottomNotice = (TextView) activity
				.findViewById(R.id.ii_bottom_game_choose_notice);
		cleanButton = (ImageButton) activity
				.findViewById(R.id.ii_bottom_game_choose_clean);
		addButton = (ImageButton) activity
				.findViewById(R.id.ii_bottom_game_choose_ok);

		// ���ü���
		setListener();
	}

	/**
	 * ���ü���
	 */
	private void setListener() {
		// ��հ�ť
		cleanButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Log.i(TAG, "�����հ�ť");

			}
		});
		// ѡ�ð�ť
		addButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Log.i(TAG, "���ѡ�ð�ť");

			}
		});
	}

	/*********************************************************************************************/
	/****************** �����������Ƹ���������������ʾ������ *****************************************/
	/**
	 * ת����ͨ�õ���
	 */
	public void showCommonBottom() {
		if (bottomMenuContainer.getVisibility() == View.GONE
				|| bottomMenuContainer.getVisibility() == View.INVISIBLE) {
			bottomMenuContainer.setVisibility(View.VISIBLE);
		}
		commonBottom.setVisibility(View.VISIBLE);
		playBottom.setVisibility(View.INVISIBLE);
	}

	/**
	 * ת��������
	 */
	public void showGameBottom() {
		if (bottomMenuContainer.getVisibility() == View.GONE
				|| bottomMenuContainer.getVisibility() == View.INVISIBLE) {
			bottomMenuContainer.setVisibility(View.VISIBLE);
		}
		commonBottom.setVisibility(View.INVISIBLE);
		playBottom.setVisibility(View.VISIBLE);
	}

	/**
	 * �ı�ײ�����������ʾ���
	 */
	public void changeBottomVisiblity(int type) {
		if (bottomMenuContainer.getVisibility() != type)
			bottomMenuContainer.setVisibility(type);
	}

	/*********************************************************************************************/
	/*********************** ���Ĳ��������淨����������ʾ ********************************************/
	/**
	 * �����淨�ײ���ʾ��Ϣ
	 * 
	 * @param notice
	 */
	public void changeGameBottomNotice(String notice) {
		playBottomNotice.setText(notice);
	}
	/*********************************************************************************************/

}