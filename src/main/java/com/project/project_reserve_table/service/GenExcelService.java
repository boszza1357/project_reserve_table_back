package com.project.project_reserve_table.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.project.project_reserve_table.model.ManageUserResponseModel;
import com.project.project_reserve_table.model.PaymentResponseModel;
import com.project.project_reserve_table.model.ReservationsResponseModel;
import com.project.project_reserve_table.model.TablesResponseModel;
import com.project.project_reserve_table.utils.Constants;
import com.project.project_reserve_table.utils.DateUtil;


@Service
public class GenExcelService {
	
	public File genExcel(List<?> data , String type) throws IOException {
		
		Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
		String template = "";
		if(Constants.TYPE_CUSTOMER.equals(type)) {
			template = "customer.xlsx";
		}
		String outputPathStr = path + File.separator + Constants.PATH_FOLDER_REPORT_EXCEL + File.separator + template;
		
	   InputStream in = new FileInputStream(new File(outputPathStr));
       XSSFWorkbook workbook = new XSSFWorkbook(in); 
       XSSFSheet spreadsheet = workbook.getSheetAt(0);
       
       if(Constants.TYPE_CUSTOMER.equals(type)) {
    	
		@SuppressWarnings("unchecked")
		List<ManageUserResponseModel> listUser = (List<ManageUserResponseModel>) data;
    	this.setCellForCustomer(listUser, spreadsheet);
       }
       

       try {
           File tempFile = File.createTempFile("report", ".xlsx");
           FileOutputStream out = new FileOutputStream(tempFile);
           workbook.write(out);

           return tempFile;
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }
	
	public void setCellForCustomer(List<ManageUserResponseModel> listUser,  XSSFSheet spreadsheet) {
		int i = 1;
		for(ManageUserResponseModel item : listUser) {
           XSSFRow row = spreadsheet.createRow(i);
           
           Cell cellV1 = row.createCell(0);
           cellV1.setCellValue(i++);

           Cell cellV2 = row.createCell(1);
           cellV2.setCellValue(item.getFristName() + "-" + item.getLastName());
           
           Cell cellV3 = row.createCell(2);
           cellV3.setCellValue(item.getPhone());
           
           Cell cellV4 = row.createCell(3);
           cellV4.setCellValue(item.getAge());
           
           Cell cellV5 = row.createCell(4);
           cellV5.setCellValue("1".equals(item.getStatus()) ? "ปกติ" : "ไม่ปกติ");
           
           Cell cellV6 = row.createCell(5);
           cellV6.setCellValue(DateUtil.dateToString(item.getCreateDate()));
	
	   }
	}
	public File genExcelForTables(List<TablesResponseModel> data) throws IOException {
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        String template = "tables.xlsx"; // ตั้งชื่อไฟล์เทมเพลตสำหรับตาราง
        String outputPathStr = path + File.separator + Constants.PATH_FOLDER_REPORT_EXCEL + File.separator + template;

        InputStream in = new FileInputStream(new File(outputPathStr));
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);

        setCellForTables(data, spreadsheet);

        try {
            File tempFile = File.createTempFile("tables_report", ".xlsx");
            FileOutputStream out = new FileOutputStream(tempFile);
            workbook.write(out);
            
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setCellForTables(List<TablesResponseModel> data, XSSFSheet spreadsheet) {
        int i = 1;
        for (TablesResponseModel item : data) {
            XSSFRow row = spreadsheet.createRow(i++);

            row.createCell(0).setCellValue(item.getTablesId());
            row.createCell(1).setCellValue(item.getTablesTypeId());
            row.createCell(2).setCellValue(item.getTableName());
            row.createCell(3).setCellValue(item.getTableNumber());
            row.createCell(4).setCellValue(item.getTableDesc());
            row.createCell(5).setCellValue(item.getCapacity());
            row.createCell(6).setCellValue(item.getStatus());
            row.createCell(7).setCellValue(item.getPrice().doubleValue()); 
            row.createCell(8).setCellValue(item.getNots());
        }
    }
    public File genExcelForReservations(List<ReservationsResponseModel> data) throws IOException {
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        String template = "reservations.xlsx"; // ชื่อไฟล์เทมเพลตสำหรับการจอง
        String outputPathStr = path + File.separator + Constants.PATH_FOLDER_REPORT_EXCEL + File.separator + template;

        InputStream in = new FileInputStream(new File(outputPathStr));
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);

        setCellForReservations(data, spreadsheet);

        try {
            File tempFile = File.createTempFile("reservations_report", ".xlsx");
            FileOutputStream out = new FileOutputStream(tempFile);
            workbook.write(out);
           
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public class DateUtil {

        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

        public static String dateToString(LocalDate date) {
            return date != null ? date.format(DATE_FORMATTER) : "";
        }

        public static String timeToString(LocalTime time) {
            return time != null ? time.format(TIME_FORMATTER) : "";
        }

        public static String dateToString(Date date) {
            if (date == null) {
                return "";
            }
            LocalDate localDate = convertToLocalDate(date);
            return dateToString(localDate);
        }

        public static String dateToString(java.sql.Date date) {
            if (date == null) {
                return "";
            }
            LocalDate localDate = convertToLocalDate(date);
            return dateToString(localDate);
        }

        public static String timeToString(Time time) {
            if (time == null) {
                return "";
            }
            LocalTime localTime = convertToLocalTime(time);
            return timeToString(localTime);
        }
        

        private static LocalDate convertToLocalDate(Date date) {
            return new java.sql.Date(date.getTime()).toLocalDate();
        }

        private static LocalDate convertToLocalDate(java.sql.Date date) {
            return date.toLocalDate();
        }

        private static LocalTime convertToLocalTime(Time time) {
            return time.toLocalTime();  // ใช้การแปลงที่เหมาะสม
        }
    }


    private void setCellForReservations(List<ReservationsResponseModel> data, XSSFSheet spreadsheet) {
        int i = 1;
        for (ReservationsResponseModel item : data) {
            XSSFRow row = spreadsheet.createRow(i++);

            row.createCell(0).setCellValue(item.getReservationsId());
            row.createCell(1).setCellValue(item.getUserId());
            row.createCell(2).setCellValue(item.getTableId());
            row.createCell(3).setCellValue(DateUtil.dateToString(item.getBookingDate())); // แปลงวันที่เป็น String
            row.createCell(4).setCellValue(item.getStatus());
            row.createCell(5).setCellValue(DateUtil.timeToString(item.getStartTime())); // แปลงเวลาเป็น String
            row.createCell(6).setCellValue(DateUtil.timeToString(item.getEndTime())); // แปลงเวลาเป็น String
            row.createCell(7).setCellValue(DateUtil.dateToString(item.getCreateDate())); // แปลงวันที่เป็น String
            row.createCell(8).setCellValue(DateUtil.dateToString(item.getUpdateDate())); // แปลงวันที่เป็น String
        }
    }
    
    public File genExcelpayment(List<?> data, String type) throws IOException {
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        String template = "";

        if (Constants.TYPE_PAYMENT.equals(type)) {
            template = "payment.xlsx"; // ชื่อตัวอย่างของ template สำหรับ payment
        }

        String outputPathStr = path + File.separator + Constants.PATH_FOLDER_REPORT_EXCEL + File.separator + template;
        InputStream in = new FileInputStream(new File(outputPathStr));
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);

        if (Constants.TYPE_PAYMENT.equals(type)) {
            @SuppressWarnings("unchecked")
            List<PaymentResponseModel> listPayments = (List<PaymentResponseModel>) data;
            this.setCellForPayments(listPayments, spreadsheet);
        }

        try {
            File tempFile = File.createTempFile("report", ".xlsx");
            FileOutputStream out = new FileOutputStream(tempFile);
            workbook.write(out);
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void setCellForPayments(List<PaymentResponseModel> data, XSSFSheet spreadsheet) {
        int i = 1;
        for (PaymentResponseModel item : data) {
            XSSFRow row = spreadsheet.createRow(i++);

            row.createCell(0).setCellValue(item.getPaymentId());
            row.createCell(1).setCellValue(item.getReservationId());
            row.createCell(2).setCellValue(item.getUserId());
            row.createCell(3).setCellValue(item.getStatusPayment());
            row.createCell(4).setCellValue(item.getPricePayment().toString()); // แปลง BigDecimal เป็น String
            row.createCell(5).setCellValue(DateUtil.dateToString(item.getCreateDate())); // แปลงวันที่เป็น String
        }
    }
}
  