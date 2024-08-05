package com.project.project_reserve_table.service;


import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project.project_reserve_table.model.ManageUserResponseModel;
import com.project.project_reserve_table.model.PaymentResponseModel;
import com.project.project_reserve_table.model.ReservationsResponseModel;
import com.project.project_reserve_table.model.TablesResponseModel;
import com.project.project_reserve_table.utils.Constants;
import com.project.project_reserve_table.utils.DateUtil;

@Service
public class GenPdfService {

	public File genPDF(List<?> data, String type) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		File tempFile = File.createTempFile("report", ".pdf");
		FileOutputStream out = new FileOutputStream(tempFile);
		PdfWriter.getInstance(document, out);

		document.open();
		Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
		String outputPathStr = path + File.separator + Constants.PATH_FOLDER_FRONT + File.separator + "THSarabun.ttf";
		BaseFont baseFont =
                BaseFont.createFont(outputPathStr, BaseFont.IDENTITY_H,
                true);
        Font font = new Font(baseFont);
//		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(24);
		font.setColor(Color.black);
		
		String projectName = "ระบบคลังสินค้า";

		Paragraph p = new Paragraph(projectName, font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		if(Constants.TYPE_CUSTOMER.equals(type)) {
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100f);
			table.setWidths(new float[] { 4.0f, 4f, 2.5f,4.0f, 7.2f });
			table.setSpacingBefore(10);
			
			@SuppressWarnings("unchecked")
			List<ManageUserResponseModel> listUser = (List<ManageUserResponseModel>) data;
			writeTableHeaderCustomer(table,baseFont);
			writeTableDataCustomer(table,baseFont,listUser);
			document.add(table);
		}

		document.close();

		return tempFile;
	}

	private void writeTableHeaderCustomer(PdfPTable table, BaseFont baseFont) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		
		cell.setBackgroundColor(Color.yellow);
		cell.setPadding(7);

//		Font font = FontFactory.getFont(FontFactory.HELVETICA);
//		font.setColor(Color.WHITE);
		
        Font font = new Font(baseFont);
        font.setSize(15);
		
		cell.setPhrase(new Phrase("ชื่อ-นามสกุล", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("เบอร์โทรศัทพ์", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("อายุ", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("สถานะ", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("วันที่ลงทะเบียน", font));
		table.addCell(cell);
	}

	private void writeTableDataCustomer(PdfPTable table, BaseFont baseFont,List<ManageUserResponseModel> listUser) throws DocumentException, IOException {

		PdfPCell cell = new PdfPCell();
		cell.setPadding(3);

        Font font = new Font(baseFont);
        font.setSize(13);
		for (ManageUserResponseModel user : listUser) {

			cell.setPhrase(new Phrase( user.getFristName() +"-"+ user.getLastName(), font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase( user.getPhone(),font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase( user.getAge(), font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase( "1".equals(user.getStatus()) ? "ปกติ" : "ไม่ปกติ" , font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase( DateUtil.dateToString(user.getCreateDate()), font));
			table.addCell(cell);
		}
	}
	

    public File genPDFTable(List<?> data, String type) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        File tempFile = File.createTempFile("report", ".pdf");
        FileOutputStream out = new FileOutputStream(tempFile);
        PdfWriter.getInstance(document, out);

        document.open();
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        String outputPathStr = path + File.separator + Constants.PATH_FOLDER_FRONT + File.separator + "THSarabun.ttf";
        BaseFont baseFont = BaseFont.createFont(outputPathStr, BaseFont.IDENTITY_H, true);
        Font font = new Font(baseFont);
        font.setSize(24);
        font.setColor(Color.black);

        String projectName = "Table Report";

        Paragraph p = new Paragraph(projectName, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        if (Constants.TYPE_TABLE.equals(type)) {
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{2.5f, 3.5f, 4.0f, 2.0f, 2.5f});
            table.setSpacingBefore(10);

            @SuppressWarnings("unchecked")
            List<TablesResponseModel> listTable = (List<TablesResponseModel>) data;
            writeTableHeader(table, baseFont);
            writeTableData(table, baseFont, listTable);
            document.add(table);
        }

        document.close();

        return tempFile;
    }

    private void writeTableHeader(PdfPTable table, BaseFont baseFont) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.blue);
        cell.setPadding(7);

        Font font = new Font(baseFont);
        font.setSize(15);

        cell.setPhrase(new Phrase("Table Name", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Table Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Capacity", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, BaseFont baseFont, List<TablesResponseModel> listTable) {
        Font font = new Font(baseFont);
        font.setSize(13);

        for (TablesResponseModel tableModel : listTable) {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(3);

            cell.setPhrase(new Phrase(tableModel.getTableName(), font));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(tableModel.getTableNumber(), font));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(tableModel.getTableDesc(), font));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(String.valueOf(tableModel.getCapacity()), font));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase("1".equals(tableModel.getStatus()) ? "Available" : "Unavailable", font));
            table.addCell(cell);
        }
    }
    
    public File genPDFreservations(List<?> data, String type) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        File tempFile = File.createTempFile("report", ".pdf");
        FileOutputStream out = new FileOutputStream(tempFile);
        PdfWriter.getInstance(document, out);

        document.open();
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        String outputPathStr = path + File.separator + Constants.PATH_FOLDER_FRONT + File.separator + "THSarabun.ttf";
        BaseFont baseFont = BaseFont.createFont(outputPathStr, BaseFont.IDENTITY_H, true);
        Font font = new Font(baseFont);
        font.setSize(24);
        font.setColor(Color.black);

        String projectName = "ระบบการจอง";
        Paragraph p = new Paragraph(projectName, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        if (Constants.TYPE_CUSTOMER.equals(type)) {
            // Existing customer table code...
        } else if (Constants.TYPE_TABLE.equals(type)) {
            // Existing table data code...
        } else if (Constants.TYPE_RESERVATIONS.equals(type)) {
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100f);
            table.setWidths(new float[] { 1.5f, 2f, 2f, 2f, 1.5f, 2f, 2f });
            table.setSpacingBefore(10);

            @SuppressWarnings("unchecked")
            List<ReservationsResponseModel> listReservations = (List<ReservationsResponseModel>) data;
            writeTableHeaderReservation(table, baseFont);
            writeTableDataReservation(table, baseFont, listReservations);
            document.add(table);
        }

        document.close();
        return tempFile;
    }

    private void writeTableHeaderReservation(PdfPTable table, BaseFont baseFont) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.green);
        cell.setPadding(7);
        Font font = new Font(baseFont);
        font.setSize(15);

        cell.setPhrase(new Phrase("Reservation ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("User ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Table ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Booking Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Start Time", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("End Time", font));
        table.addCell(cell);
    }

    private void writeTableDataReservation(PdfPTable table, BaseFont baseFont, List<ReservationsResponseModel> listReservations) throws DocumentException, IOException {
        Font font = new Font(baseFont);
        font.setSize(13);
        for (ReservationsResponseModel reservation : listReservations) {
            table.addCell(new Phrase(String.valueOf(reservation.getReservationsId()), font));
            table.addCell(new Phrase(String.valueOf(reservation.getUserId()), font));
            table.addCell(new Phrase(String.valueOf(reservation.getTableId()), font));
            table.addCell(new Phrase(String.valueOf(reservation.getBookingDate()), font));
            table.addCell(new Phrase(reservation.getStatus(), font));
            table.addCell(new Phrase(reservation.getStartTime().toString(), font));
            table.addCell(new Phrase(reservation.getEndTime().toString(), font));
        }
    }
    

    public File genPDFpayment(List<?> data, String type) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        File tempFile = File.createTempFile("report", ".pdf");
        FileOutputStream out = new FileOutputStream(tempFile);
        PdfWriter.getInstance(document, out);

        document.open();
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        String outputPathStr = path + File.separator + Constants.PATH_FOLDER_FRONT + File.separator + "THSarabun.ttf";
        BaseFont baseFont = BaseFont.createFont(outputPathStr, BaseFont.IDENTITY_H, true);
        Font font = new Font(baseFont);
        font.setSize(24);
        font.setColor(Color.black);

        String projectName = "ระบบการชำระเงิน";
        Paragraph p = new Paragraph(projectName, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        if (Constants.TYPE_CUSTOMER.equals(type)) {
            // Existing customer table code...
        } else if (Constants.TYPE_TABLE.equals(type)) {
            // Existing table data code...
        } else if (Constants.TYPE_RESERVATIONS.equals(type)) {
            // Existing reservation data code...
        } else if (Constants.TYPE_PAYMENT.equals(type)) {
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100f);
            table.setWidths(new float[] { 1.5f, 2f, 2f, 2f, 2f, 2f });
            table.setSpacingBefore(10);

            @SuppressWarnings("unchecked")
            List<PaymentResponseModel> listPayments = (List<PaymentResponseModel>) data;
            writeTableHeaderPayment(table, baseFont);
            writeTableDataPayment(table, baseFont, listPayments);
            document.add(table);
        }

        document.close();
        return tempFile;
    }

    private void writeTableHeaderPayment(PdfPTable table, BaseFont baseFont) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.magenta);
        cell.setPadding(7);
        Font font = new Font(baseFont);
        font.setSize(15);

        cell.setPhrase(new Phrase("Payment ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Reservation ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("User ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Status Payment", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price Payment", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Create Date", font));
        table.addCell(cell);
    }

    private void writeTableDataPayment(PdfPTable table, BaseFont baseFont, List<PaymentResponseModel> listPayments) throws DocumentException, IOException {
        Font font = new Font(baseFont);
        font.setSize(13);
        for (PaymentResponseModel payment : listPayments) {
            table.addCell(new Phrase(String.valueOf(payment.getPaymentId()), font));
            table.addCell(new Phrase(String.valueOf(payment.getReservationId()), font));
            table.addCell(new Phrase(String.valueOf(payment.getUserId()), font));
            table.addCell(new Phrase(payment.getStatusPayment(), font));
            table.addCell(new Phrase(String.valueOf(payment.getPricePayment()), font));
            table.addCell(new Phrase(payment.getCreateDate().toString(), font));
        }
    }
}
