package com.dengjian.gomars.common.exception;

import java.sql.SQLException;
import java.util.Locale;

import com.dengjian.gomars.common.util.ApplicationContextUtil;

/**
 * 异常类<br/>
 * 异常信息文件位于：<b>message/exception.properties</b><br/>
 * 异常信息命名规范为：{@value #EXCEPTION_PREFIX} + errorCode
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public static final String EXCEPTION_PREFIX="EXCEPTION.";
	/** 加载异常文件出错异常 */
	public static final int LOAD_EXCEPTION_CONF_FILE_EXCEPTION = 1;
	public static final String LOAD_EXCEPTION_CONF_FILE_EXCEPTION_PREFIX = "Load exception message error! Error code: \t";
	/** SQL 异常 */
	public static final int SQL_EXCEPTION = 100001;
	
	/**模板文件生成异常 */
	public static final int TEMPLATE_WRITE_EXCEPTION = 300001;
	
	/** 异常码 */
	private int errorCode = 0;
	/** 异常消息 */
	private String errorMsg = null;
	private BaseException(int errorCode, String errorMsg){
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	private BaseException(Throwable cause, int errorCode, String errorMsg){
		super(errorMsg, cause);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	
	/**
	 * 生成异常对象<br/>
	 * <ol>
	 * <li>依据errorCode，取得msgPattern</li>
	 * <li>格式化msgPattern，格式化方式为，根据 params 顺序，逐一替换Pattern中的{0}、{1}、{2}等</li>
	 * </ol>
	 * @param errorCode
	 * @param params 信息参数，注意cause置于最后，例如SQL异常生成方式为：<br/>
	 * <pre>
	 * 	catch ( {@link SQLException} e){
	 * 		throw {@link BaseException}.exception({@link BaseException}.{@link #SQL_EXCEPTION}, "插入数据错误", e)
	 * 	}
	 * </pre>
	 * @return
	 */
	public static BaseException exception(int errorCode, Object...params){
		
		String message;
		try {
			message = ApplicationContextUtil.context.getMessage(EXCEPTION_PREFIX + errorCode,params, Locale.CHINA);
		} catch (Throwable t) {
			message = LOAD_EXCEPTION_CONF_FILE_EXCEPTION_PREFIX + errorCode;
			errorCode = LOAD_EXCEPTION_CONF_FILE_EXCEPTION;
		}
		if(null != params && 0 != params.length && params[params.length - 1] instanceof Throwable){
			return new BaseException((Throwable)params[params.length - 1], errorCode, message);
		} else {
			return new BaseException(errorCode, message);
		}
		
	}
}
