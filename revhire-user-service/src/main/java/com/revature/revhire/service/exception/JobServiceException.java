package com.revature.revhire.service.exception;

public class JobServiceException extends Exception{
	public JobServiceException() {
		super();
	}
	public JobServiceException(String message) {
		super(message);
	}
	public JobServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	public JobServiceException(Throwable cause) {
		super(cause);
	}
	public JobServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
