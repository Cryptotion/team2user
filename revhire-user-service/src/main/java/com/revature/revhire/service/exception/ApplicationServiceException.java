package com.revature.revhire.service.exception;

public class ApplicationServiceException  extends Exception{
	
	public ApplicationServiceException() {
		super();
	}
	public ApplicationServiceException(String message) {
		super(message);
	}
	public ApplicationServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	public ApplicationServiceException(Throwable cause) {
		super(cause);
	}
	public ApplicationServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
