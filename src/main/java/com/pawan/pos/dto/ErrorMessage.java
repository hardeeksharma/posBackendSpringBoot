package com.pawan.pos.dto;

public class ErrorMessage {

	private String errorMessage;
	private boolean flag;

	public ErrorMessage(String eMsg) {
		this.errorMessage = eMsg;
		this.flag = false;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
