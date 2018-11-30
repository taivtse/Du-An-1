package poly.app.ui.custom;

public class HoaDonReport {
    private String name;
    private Integer quantity;
    private Integer price;
    private Integer total;

    public HoaDonReport(String name, Integer quantity, Integer price, Integer total) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    
    
}