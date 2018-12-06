package com.lonton.dsms.common.exception;

/**
 * 业务处理异常，为检查异常，建议在service层抛出，在web层捕获并处理
 *
 */
public class ServiceProcessException extends Exception{

	private static final long serialVersionUID = 7411031498144469311L;
	
	/** sql异常 */
	public static final String ERROR_CODE_SQL_EXCEPTION = "Exception.SQLException";
	/** 校验异常 */
	public static final String ERROR_CODE_VALIDATION_EXCEPTION = "Exception.ValidationException";
	/** 未知异常 */
	public static final String ERROR_CODE_UNKNOWN_EXCEPTION = "Exception.UnknownException";
	
	/** sql异常 */
	public static final String ERROR_MSG_SQL_EXCEPTION = "sql异常";
	/** 校验异常 */
	public static final String ERROR_MSG_VALIDATION_EXCEPTION = "校验异常";
	/** 未知异常 */
	public static final String ERROR_MSG_UNKNOWN_EXCEPTION = "未知异常";
	

	private String errorCode;
	private String errorMsg;
	
	public ServiceProcessException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
	public ServiceProcessException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public ServiceProcessException(String errorCode, String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	
	/**
	 * 实例化一个SQL类型的ServiceProcessException
	 * @param cause
	 * @return
	 */
	public static ServiceProcessException instanceWrapSqlException(Throwable cause) {
		return new ServiceProcessException(ERROR_CODE_SQL_EXCEPTION, ERROR_MSG_SQL_EXCEPTION, cause); 
	}
	
	/**
	 * 实例化一个Validation类型的ServiceProcessException
	 * @param message
	 * @return
	 */
	public static ServiceProcessException instanceWrapValidationException(String message) {
		return new ServiceProcessException(ERROR_CODE_VALIDATION_EXCEPTION, message); 
	}
	
	/**
	 * 实例化一个Unknown的ServiceProcessException
	 * @param message
	 * @return
	 */
	public static ServiceProcessException instanceWrapUnkownException(Throwable cause) {
		return new ServiceProcessException(ERROR_CODE_UNKNOWN_EXCEPTION, ERROR_MSG_UNKNOWN_EXCEPTION, cause); 
	}
	
}
