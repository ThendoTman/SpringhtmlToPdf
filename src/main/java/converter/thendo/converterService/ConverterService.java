/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.thendo.converterService;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.TemplateException;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 *
 * @author thendo
 */
@Service
public class ConverterService {

    public void createPdf() {

        URL url;
        try {
            url = new URL("http://localhost:8080/users");
            URLConnection conn = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            String fileName = "/home/thendo/testMan_html.html";
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            while ((inputLine = br.readLine()) != null) {
                bw.write(inputLine);
                System.out.println(inputLine);
            }
            bw.close();
            br.close();
            System.out.println("Html Creation Done");

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/thendo/testPdf.pdf"));
            document.open();
            try {
                XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                        new FileInputStream("/home/thendo/testMan_html.html"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            convertHtmlToPdf();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ConverterService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ConverterService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void convertHtmlToPdf() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/thendo/testPdf.pdf"));
        document.open();
        try {
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                    new FileInputStream("/home/thendo/testMan_html.html"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("PDF Created Successfully");
        document.close();
    }

    public static void convertToPdf(Map map) throws IOException, TemplateException, DocumentException, com.itextpdf.text.DocumentException {
        String htmlFullPath = HtmlUtils.generateHtmlFromTemplate(map, "/home/thendo/", "testPdf.pdf");

        convertHtmlToPdf();

        File htmlFile = new File(htmlFullPath);
        if (htmlFile.exists()) {
            htmlFile.delete();
        }
    }
}
