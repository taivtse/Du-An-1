package poly.app.ui.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class HoaDonReportParameter extends HashMap<String, Object>{

    public HoaDonReportParameter(String invoiceID, String employeeName, Date createdDate, String TotalPrice) {
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(new ArrayList<HoaDonReportBean>());
        this.put("ItemDataSource", itemsJRBean);
        this.put("InvoiceID", invoiceID);
        this.put("EmployeeName", employeeName);
        this.put("CreatedDate", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(createdDate));
        this.put("TotalPrice", TotalPrice);
    }
    
    public void addHoaDonReportBean(String name, String quantity, String price, String total){
        HoaDonReportBean bean = new HoaDonReportBean(name, quantity, price, total);
        JRBeanCollectionDataSource itemsJRBean = (JRBeanCollectionDataSource) this.get("ItemDataSource");
        List<HoaDonReportBean> beans = (List<HoaDonReportBean>) itemsJRBean.getData();
        beans.add(bean);
        itemsJRBean = new JRBeanCollectionDataSource(beans);
        this.put("ItemDataSource", itemsJRBean);
    }

    public class HoaDonReportBean {
        private String name;
        private String quantity;
        private String price;
        private String total;

        public HoaDonReportBean(String name, String quantity, String price, String total) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.total = total;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

    }
}
