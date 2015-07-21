package com.github.lottery.engine;

import com.github.lottery.bean.User;
import com.github.lottery.net.protocal.Message;

/**
 * 用户相关的业务操作接口
 * @author LQM
 *
 */
public interface UserEngine {

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	Message login(User user);
	
}
