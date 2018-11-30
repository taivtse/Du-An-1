package poly.app.ui.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author javaQuery
 * @date 24nd November, 2015
 * @Github: https://github.com/javaquery/Examples
 */
public class JasperTableExample {

    public static void main(String[] args) {
        try {
            /* User home directory location */
//            String userHomeDirectory = System.getProperty("user.home");

            /* Output file location */
//            String outputFile = userHomeDirectory + File.separatorChar + "Desktop"
//                    + File.separatorChar + "JasperTableExample.pdf";

            /* List to hold Items */
            List<Item> listItems = new ArrayList<Item>();

            /* Create Items */
            Item iPhone = new Item();
            iPhone.setName("iPhone 6S");
            iPhone.setPrice(65000.00);

            Item iPad = new Item();
            iPad.setName("iPad Pro");
            iPad.setPrice(70000.00);
            
            Item iPod = new Item();
            iPod.setName("iPod Pro");
            iPod.setPrice(20000.00);

            /* Add Items to List */
            listItems.add(iPhone);
            listItems.add(iPad);
            listItems.add(iPod);
            for (int i = 0; i < 10; i++) {
                listItems.add(iPod);
                listItems.add(iPad);
                listItems.add(iPhone);
            }

            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("ItemDataSource", itemsJRBean);

            File f = new File("src/poly/app/ui/custom/HoaDonReportTemplate.jasper");
            if (!f.exists()) {
                JasperCompileManager.compileReportToFile("src/poly/app/ui/custom/HoaDonReportTemplate.jrxml", "src/poly/app/ui/custom/HoaDonReportTemplate.jasper");
                f = new File("src/poly/app/ui/custom/HoaDonReportTemplate.jasper");
            }
            JasperReport jr = (JasperReport) JRLoader.loadObject(f);


            /* Using compiled version(.jasper) of Jasper report to generate PDF */
//            JasperPrint jasperPrint = JasperFillManager.fillReport("src/poly/app/ui/custom/HoaDonReportTemplate.jasper", parameters, new JREmptyDataSource());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, parameters, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);
//            JasperViewer.viewReport(jasperPrint, false);
            /* outputStream to create PDF */
//            OutputStream outputStream = new FileOutputStream(new File(outputFile));
            /* Write content to PDF file */
//            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

//            System.out.println("File Generated");
        } catch (JRException ex) {
            ex.printStackTrace();
        } 
//        catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        }
    }
}
