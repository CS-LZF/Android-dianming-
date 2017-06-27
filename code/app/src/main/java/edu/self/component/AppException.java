package edu.self.component;

public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isTimeOut = false;

	public boolean isTimeOut() {
		return isTimeOut;
	}

	public void setTimeOut(boolean isTimeOut) {
		this.isTimeOut = isTimeOut;
	}

	private int errorCode;

	public AppException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public AppException() {

	}

	public int getErrorCode() {
		return errorCode;
	}
}
