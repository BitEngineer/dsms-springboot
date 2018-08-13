package com.lonton.dsms.common.exception;

public class ValidationException extends Exception{

	private static final long serialVersionUID = 5951821365206897423L;

	private String errorMsg;
	
	public ValidationException() {
		super();
	}
	
	public ValidationException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
	public ValidationException(String errorMsg, Throwable e) {
		super(errorMsg, e);
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	
}
