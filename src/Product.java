import java.math.BigDecimal;

public class Product {
    private String name;
    private Integer quantity;
    private BigDecimal price;

    Product(String name, Integer quantity, BigDecimal price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void reduceStock(Integer quantity) {
        this.quantity -= quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
