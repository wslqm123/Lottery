package com.github.lottery.view;

import com.github.lottery.ConstantValue;
import com.github.lottery.R;

import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Hall extends BaseUI{

	
	private TextView ssqTssue;
	private ImageView ssqBet;

	public Hall(Context context) {
		super(context);
		
	}

	

	public void init() {

		showInMiddle = (LinearLayout) View.inflate(context, R.layout.il_hall,
				null);
		
		
		ssqTssue = (TextView) findViewById(R.id.ii_hall_ssq_summary);
		ssqBet = (ImageView) findViewById(R.id.ii_hall_ssq_bet);

	}


	@Override
	public int getID() {
		return ConstantValue.VIEW_HALL;
	}

	public void setListener() {
		ssqBet.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {

		super.onClick(v);
	}

}
