package com.xvitcoder.springmvcangularjs.pdfbuilder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.xvitcoder.springmvcangularjs.model.AccessCard;
import com.xvitcoder.springmvcangularjs.model.Notebook;

/**
 * Created by trvler135 on 17.01.2017.
 */
public class PDFBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Notebook> listNotebook = (List<Notebook>) model.get("listNotebook");
        List<AccessCard> listCards = (List<AccessCard>) model.get("listCards");

        if (listNotebook != null) {
            doc.open();
            doc.setPageSize(PageSize.A4.rotate());
            doc.newPage();

            doc.add(new Paragraph("Notebook Stock Status:"));

            PdfPTable table = new PdfPTable(11);
            table.setWidthPercentage(100.0f);
            table.setWidths(new float[] {0.3f, 0.9f, 1.1f, 0.5f, 1.0f, 0.9f, 0.9f, 1.0f, 0.7f, 0.7f, 1.1f});
            table.setSpacingBefore(10);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(BaseColor.WHITE);
            font.setSize(10);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.BLACK);
            cell.setPadding(5);

            cell.setPhrase(new Phrase("N", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Name", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Model", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Mem. Type", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Serial Number", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Inventory Num", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Location", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Employee Name", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Open Date", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Due Date", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Status", font));
            table.addCell(cell);

            Font innerFont = FontFactory.getFont(FontFactory.HELVETICA);
            innerFont.setSize(9);
            innerFont.setColor(BaseColor.BLACK);
            cell.setBackgroundColor(BaseColor.WHITE);

            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

            int i = 1;
            for (Notebook notebook : listNotebook) {
                //1
                cell.setPhrase(new Phrase(String.valueOf(i),innerFont));
                table.addCell(cell);
                //2
                cell.setPhrase(new Phrase(notebook.getName(),innerFont));
                table.addCell(cell);
                //3
                cell.setPhrase(new Phrase(notebook.getModel(),innerFont));
                table.addCell(cell);
                //4
                cell.setPhrase(new Phrase(notebook.getMemoryType(),innerFont));
                table.addCell(cell);
                //5
                cell.setPhrase(new Phrase(notebook.getSerialNumber(),innerFont));
                table.addCell(cell);
                //6
                cell.setPhrase(new Phrase(notebook.getInventoryNum(),innerFont));
                table.addCell(cell);
                //7
                cell.setPhrase(new Phrase(notebook.getLocation(),innerFont));
                table.addCell(cell);
                //8
                cell.setPhrase(new Phrase(notebook.getEmployeeName(),innerFont));
                table.addCell(cell);
                //9
                cell.setPhrase(new Phrase(format.format(notebook.getOpenDate()),innerFont));
                table.addCell(cell);
                //10
                cell.setPhrase(new Phrase(format.format(notebook.getDueDate()),innerFont));
                table.addCell(cell);
                //11
                cell.setPhrase(new Phrase(notebook.getStatusName(),innerFont));
                table.addCell(cell);


//                table.addCell(String.valueOf(i));
//                table.addCell(notebook.getName());
//                table.addCell(notebook.getModel());
//                table.addCell(notebook.getMemoryType());
//                table.addCell(notebook.getSerialNumber());
//                table.addCell(String.valueOf(notebook.getInventoryNum()));
//                table.addCell(notebook.getLocation());
//                table.addCell(notebook.getStatusName());
//                table.addCell(notebook.getEmployeeName());
//                table.addCell(notebook.getDueDate().toString());
//                table.addCell(notebook.getOpenDate().toString());
                i++;
            }

            doc.add(table);
            doc.close();
        } else if (listCards != null) {

            doc.open();
            doc.setPageSize(PageSize.A4.rotate());
            doc.newPage();

            doc.add(new Paragraph("Access Cards Stock Status:"));

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100.0f);
            table.setWidths(new float[] {0.3f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f});
            table.setSpacingBefore(10);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(BaseColor.WHITE);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.BLACK);
            cell.setPadding(5);


            cell.setPhrase(new Phrase("N", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Inventory Number", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Employee Name", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Open Date", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Due Date", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Status", font));
            table.addCell(cell);

            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

            Integer i = 1;
            for (AccessCard card : listCards) {
                table.addCell(i.toString());
                table.addCell(card.getInventoryNum());
                table.addCell(card.getEmployeeName());
                table.addCell(format.format(card.getOpenDate()));
                table.addCell(format.format(card.getDueDate()));
                table.addCell(card.getStatusName());
                i++;
            }
            doc.add(table);
            doc.close();
        }

    }
}
