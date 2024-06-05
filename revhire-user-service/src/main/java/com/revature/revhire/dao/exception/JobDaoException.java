package com.revature.revhire.dao.exception;

public class JobDaoException extends Exception{
	public JobDaoException() {
		super();
	}
	public JobDaoException(String message) {
		super(message);
	}
	public JobDaoException(String message, Throwable cause) {
		super(message, cause);
	}
	public JobDaoException(Throwable cause) {
		super(cause);
	}
	public JobDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
