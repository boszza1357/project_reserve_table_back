package com.project.project_reserve_table.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project_reserve_table.model.RegisterResponseModel;
import com.project.project_reserve_table.model.ReservationsResponseModel;
import com.project.project_reserve_table.utils.Constants;
import com.project.project_reserve_table.utils.SendEmailUtils;

import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;

//@Service
//public class SendMailService {
//
//	 @Autowired
//	 private SendEmailUtils sendEmailUtils;
//	 
//	 public void sendMail(Object object,String email, String subJect, String type) {
//		 
//		 try {
//			 Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
//				String output = path + File.separator + Constants.PATH_FOLDER_EMAIL + File.separator;
//			 if(Constants.TYPE_SENDMAIL_REGISTER.equals(type)) {
//				 RegisterResponseModel obj = (RegisterResponseModel) object;
//				 File file = new File(output + "registerEmailTemplate.txt");
//				 BufferedReader reader = new BufferedReader(new FileReader(file));
//				 String emailTo = email;
//				 StringBuilder stringBuilder = new StringBuilder();
//				 String line = null;
//				 String ls = System.getProperty("line.separator");
//				while ((line = reader.readLine()) != null) {
//				 	stringBuilder.append(line);
//				 	stringBuilder.append(ls);
//				 }
//				 stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//				 reader.close();
//
//				 String content = stringBuilder.toString();
//				 content = setParam(content, obj);
//				 
//				 sendEmailUtils.sendMail(emailTo, subJect, content);
//			 }
//			 
//			 
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	 }
//	 
//	 private String setParam(String content,RegisterResponseModel obj) {
//
//		 if(content.contains("{FULL_NAME}")) {
//			 content = content.replace("{FULL_NAME}",isNull(obj.getFristName() + "-" + obj.getLastName())); 
//		 }
//		 
//		 if(content.contains("{DETAIL}")) {
//			 StringBuilder detail = new StringBuilder();
//			 
//			 detail.append("เข้าสู่ระบบโดย :");
//			 detail.append(" Username = " + obj.getUserName());
//			 detail.append(" Password = " + obj.getPassword());
//			 content = content.replace("{DETAIL}",detail.toString()); 
//		 }
//		 return content;
//	 }
//	 
//	 private String isNull(String str) {
//		 if(StringUtils.isNotBlank(str)) {
//			 return str;
//		 }
//		 return "";
//	 }
//}

@Service
public class SendMailService {

    @Autowired
    private SendEmailUtils sendEmailUtils;

    public void sendMail(Object object, String email, String subject, String type) {
        try {
            Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
            String output = path + File.separator + Constants.PATH_FOLDER_EMAIL + File.separator;

            if (Constants.TYPE_SENDMAIL_REGISTER.equals(type)) {
                RegisterResponseModel obj = (RegisterResponseModel) object;
                File file = new File(output + "registerEmailTemplate.txt");
                String content = readEmailTemplate(file);
                content = setRegisterParam(content, obj);
                sendEmailUtils.sendMail(email, subject, content);
            } 
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
    
    
    public void sendMailpayment(Object object, String email, String subject, String type) {
        try {
            Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
            String output = path + File.separator + Constants.PATH_FOLDER_EMAIL + File.separator;
            if (Constants.TYPE_SENDMAIL_PAYMENT.equals(type)) {
                ReservationsResponseModel obj = (ReservationsResponseModel) object;
                File file = new File(output + "paymentEmailTemplate.txt");
                String content = readEmailTemplate(file);
                content = setPaymentParam(content, obj);
                sendEmailUtils.sendMail(email, subject, content);
            }
            
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
   

    private String readEmailTemplate(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();
        return stringBuilder.toString();
    }

    private String setRegisterParam(String content, RegisterResponseModel obj) {
        if (content.contains("{FULL_NAME}")) {
            content = content.replace("{FULL_NAME}", isNull(obj.getFristName() + " " + obj.getLastName()));
        }

        if (content.contains("{DETAIL}")) {
            StringBuilder detail = new StringBuilder();
            detail.append("เข้าสู่ระบบโดย : ");
            detail.append(" Username = " + obj.getUserName());
            detail.append(" Password = " + obj.getPassword());
            content = content.replace("{DETAIL}", detail.toString());
        }

        return content;
    }

    private String setPaymentParam(String content, ReservationsResponseModel obj) {
    	 if (content.contains("{DETAIL}")) {
    	        StringBuilder detail = new StringBuilder();
    	        detail.append("หมายเลขการจอง: ").append(obj.getReservationsId()).append("<br>");
    	        detail.append("วันที่จอง: ").append(obj.getBookingDate()).append("<br>");
    	        detail.append("เวลาเริ่ม: ").append(obj.getStartTime()).append("<br>");
    	        detail.append("เวลาสิ้นสุด: ").append(obj.getEndTime()).append("<br>");
    	        content = content.replace("{DETAIL}", detail.toString());
    	    }
        return content;
    }


    private String isNull(String value) {
        return value != null ? value : "";
    }
}

