package com.github.lottery.test;

import com.github.lottery.net.NetUtils;

import android.test.AndroidTestCase;

public class NetTest extends AndroidTestCase {

	public void testNetType(){
		
		NetUtils.checkNet(getContext());
	}
	
}
