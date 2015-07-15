package com.winit.commons;

/**
 * 状态码
 */
public class StatusCode {
	
	/**
	 * 抓取面单成功
	 */
	public static final int SUCCESS = 0;
	
	/**
	 * 参数校验失败
	 */
	public static final int PARAM_VALIDATE_FAILED = 100;
	
	/**
	 * 业务异常：如邮编不规范，地址超长等
	 */
	public static final int BUSINESS_EXCEPTION = 200;
	
	/**
	 * 系统异常：如网络异常等
	 */
	public static final int SYSTEM_EXCEPTION = 300;
	
	/**
	 * 其他异常：如NullPointerException等
	 */
	public static final int OTHER_EXCEPTION = 400;
	
}
