package org.o7planning.springmvcshoppingcart.views;


import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.o7planning.springmvcshoppingcart.model.CartInfo;
import org.o7planning.springmvcshoppingcart.model.CartLineInfo;
import org.o7planning.springmvcshoppingcart.util.Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ItextPdfView extends AbstractITextPdfView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
//        List<CartInfo> courses = (List<Course>) model.get("courses");
        CartInfo cartInfo = Utils.getCartInSession(request);
        PdfPTable table = new PdfPTable(4);
        table.setWidths(new int[]{10, 30, 30, 30});

        table.addCell("Code");
        table.addCell("Nombre");
        table.addCell("Nombre del producto");
        table.addCell("Precio");
        
      for(CartLineInfo cInfo : cartInfo.getCartLines()) {
        table.addCell(String.valueOf(cInfo.getProductInfo().getCode()));
        table.addCell(String.valueOf(cartInfo.getCustomerInfo().getName()));
        table.addCell(String.valueOf(cInfo.getProductInfo().getName()));
        table.addCell(String.valueOf(cInfo.getProductInfo().getPrice()));
      }

        document.add(table);
    }

}