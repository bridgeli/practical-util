package cn.bridgeli.exception;

public class BussinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMsg;

	public BussinessException(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
}
