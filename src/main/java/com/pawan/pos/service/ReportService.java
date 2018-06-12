package com.pawan.pos.service;

import java.io.File;
import java.util.Date;

public interface ReportService {


	File excelGenerator(Date sdate, Date edate, int empId);

}
