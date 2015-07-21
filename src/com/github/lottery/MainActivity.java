package com.github.lottery;


import com.github.lottery.view.BottomManager;
import com.github.lottery.view.TitleManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);
	
//		//commons-codec.jar:¼ÓÃÜÓÃ--MD5
//		DigestUtils.md5Hex("");
//		//commons-lang3-3.0-beta.jar:×Ö·û´®²Ù×÷
//		//×Ö·û´®·Ç¿ÕÅÐ¶Ï£ºnull;"";"  "
//		StringUtils.isBlank("");//true
//		StringUtils.isNotBlank("");//false
//		//×Ö·û´®Ìæ»»
//		String info = "dfvNUM1ksdfgdfbhNUM2fnjlssfs";
//		info = StringUtils.replaceEach(info,new String[]{"NUM1","NUM2"},new String[]{"num","num"});
//		//×Ö·û½ØÈ¡
//		info = "<body>.....</body>";
//		StringUtils.substringBetween(info, "<body>","</body>");
		
		init();
		
	}

	private void init() {
		TitleManager manager = TitleManager.getInstance();
		manager.init(this);
		manager.showUnLoginTitle();
		
		BottomManager.getInstrance().init(this);
		BottomManager.getInstrance().showCommonBottom();
	}
}
