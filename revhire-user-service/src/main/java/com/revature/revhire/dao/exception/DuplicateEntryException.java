package com.revature.revhire.dao.exception;

public class DuplicateEntryException extends Exception {
	public DuplicateEntryException() {
		super();
	}
	public DuplicateEntryException(String message) {
		super(message);
	}
	public DuplicateEntryException(String message, Throwable cause) {
		super(message, cause);
	}
	public DuplicateEntryException(Throwable cause) {
		super(cause);
	}
	public DuplicateEntryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	

}
