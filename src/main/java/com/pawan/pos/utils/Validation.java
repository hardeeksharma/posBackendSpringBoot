package com.pawan.pos.utils;

import javax.servlet.http.HttpSession;

import com.pawan.pos.exception.CustomException;

public class Validation {
	public static boolean validateSession(HttpSession session) {
		return (session.getAttribute("employee") != null);

	}

	public static void validateField(String field) throws CustomException {
		if (field == null || field.isEmpty()) {
			throw new CustomException("EXCEP_FIELD_EMPTY");
		}
	}

}
