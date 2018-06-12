package com.pawan.pos.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.pos.dto.ErrorMessageResponseDto;
import com.pawan.pos.model.Employee;
import com.pawan.pos.service.ReportService;
import com.pawan.pos.utils.Constants;
import com.pawan.pos.utils.DateParse;
import com.pawan.pos.utils.Validation;

/**
 * @author pawansaini
 *
 */
@RestController
@RequestMapping(value = "/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping
	public ResponseEntity<Object> generateReport(HttpSession session,
			@RequestParam(value = "startDate", required = true) String startDate,
			@RequestParam(value = "endDate", required = true) String endDate) throws IOException, ParseException {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		int empId = ((Employee) session.getAttribute(Constants.EMPLOYEE)).getId();

		Date sdate, edate;
		try {
			sdate = DateParse.dateParse(startDate);
			edate = DateParse.dateParse(endDate);
		} catch (Exception e) {
			return ErrorMessageResponseDto.errorMessage(e.getMessage());
		}

		File file = reportService.excelGenerator(sdate, edate, empId);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

		return responseEntity;

	}
}
