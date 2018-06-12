package com.pawan.pos.dto;

import org.springframework.http.ResponseEntity;

public class ErrorMessageResponseDto {

	public static ResponseEntity<Object> errorMessage(String eMsg) {

		return ResponseEntity.badRequest().body(new ErrorMessage(eMsg));
	}
}
