package com.github.lottery.net.protocal;


/**
 * 封装服务器回复结果
 * @author LQM
 *
 */
public class Oelement {

	private String errorcode;
	private String errormsg;
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	
}
