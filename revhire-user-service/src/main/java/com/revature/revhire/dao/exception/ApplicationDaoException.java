package com.revature.revhire.dao.exception;

public class ApplicationDaoException extends Exception{
	
	public ApplicationDaoException() {
		super();
	}
	public ApplicationDaoException(String message) {
		super(message);
	}
	public ApplicationDaoException(String message, Throwable cause) {
		super(message, cause);
	}
	public ApplicationDaoException(Throwable cause) {
		super(cause);
	}
	public ApplicationDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
