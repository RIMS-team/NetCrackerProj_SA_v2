package com.xvitcoder.springmvcangularjs.pdfbuilder;

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

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100.0f);
            table.setWidths(new float[] {0.3f, 1.2f, 1.0f, 0.6f, 1.0f, 1.0f, 0.7f, 1.2f});
            table.setSpacingBefore(10);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(BaseColor.WHITE);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.BLACK);
            cell.setPadding(5);


            cell.setPhrase(new Phrase("N", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Name", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Model", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Memory Type", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Serial Number", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Inventory Num", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Location", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Status", font));
            table.addCell(cell);

            int i = 1;
            for (Notebook notebook : listNotebook) {
                table.addCell(String.valueOf(i));
                table.addCell(notebook.getName());
                table.addCell(notebook.getModel());
                table.addCell(notebook.getMemoryType());
                table.addCell(notebook.getSerialNumber());
                table.addCell(String.valueOf(notebook.getInventoryNum()));
                table.addCell(notebook.getLocation());
                table.addCell(notebook.getStatusName());
                i++;
            }
            doc.add(table);
            doc.close();
        } else if (listCards != null) {

            doc.open();
            doc.newPage();

            doc.add(new Paragraph("Access Cards Stock Status:"));

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100.0f);
            table.setWidths(new float[] {0.2f, 0.4f, 0.4f});
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

            cell.setPhrase(new Phrase("Status", font));
            table.addCell(cell);


            Integer i = 1;
            for (AccessCard card : listCards) {
                table.addCell(i.toString());
                table.addCell(card.getInventoryNum());
                table.addCell(card.getStatusName());
                i++;
            }
            doc.add(table);
            doc.close();
        }

    }
}
