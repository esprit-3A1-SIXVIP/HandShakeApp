package handshake;

import Utils.DataBase;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Pdfcreat {
  private Connection con = DataBase.getInstance().getConnection();
  
  private Statement ste;
  
  public void add(String file, String N, String N1, String N2) throws FileNotFoundException, SQLException, DocumentException {
    Document my_pdf_report = new Document();
    PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
    my_pdf_report.open();
    PdfPTable my_report_table = new PdfPTable(2);
    new Phrase("titre");
    new Phrase(N);
    new Phrase("description");
    new Phrase(N1);
    new Phrase("auteur");
    new Phrase(N2);
    my_pdf_report.add((Element)my_report_table);
    my_pdf_report.close();
  }
}