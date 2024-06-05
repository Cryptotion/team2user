package com.revature.revhire.dao.exception;

public class UserDaoException extends Exception{
	public UserDaoException() {
		super();
	}
	public UserDaoException(String message) {
		super(message);
	}
	public UserDaoException(String message, Throwable cause) {
		super(message, cause);
	}
	public UserDaoException(Throwable cause) {
		super(cause);
	}
	public UserDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
