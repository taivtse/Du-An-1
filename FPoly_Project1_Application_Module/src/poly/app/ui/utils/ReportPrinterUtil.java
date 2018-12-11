package poly.app.ui.utils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportPrinterUtil {

    public static final String VEBAN_REPORT_URL = "src/poly/app/ui/report/VeBanReportTemplate.jasper".replace("/", File.separator);
    public static final String HOADON_REPORT_URL = "src/poly/app/ui/report/HoaDonReportTemplate.jasper".replace("/", File.separator);

    public static void showPrintPreview(Map<String, Object> parameters, String reportUrl) {
        try {
            File file = new File(reportUrl);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportPrinterUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showMultiPrintPreview(List<? extends Map<String, Object>> listReportParameters, String reportUrl) {
        try {
            File file = new File(reportUrl);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, listReportParameters.get(0), new JREmptyDataSource());

            if (listReportParameters.size() > 1) {
                for (int i = 1; i < listReportParameters.size(); i++) {
                    JasperPrint jasperPrint_next = JasperFillManager.fillReport(jasperReport, listReportParameters.get(i), new JREmptyDataSource());
                    List pages = jasperPrint_next.getPages();
                    for (int j = 0; j < pages.size(); j++) {
                        JRPrintPage object = (JRPrintPage) pages.get(j);
                        jasperPrint.addPage(object);
                    }
                }
            }

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportPrinterUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printReport(Map<String, Object> parameters, String reportUrl, boolean withPrintDialog) {
        try {
            File file = new File(reportUrl);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperPrintManager.printReport(jasperPrint, withPrintDialog);
        } catch (JRException ex) {
            Logger.getLogger(ReportPrinterUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
