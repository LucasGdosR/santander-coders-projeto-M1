import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Database {
    private List<Product> products;
    private String filePath;

    Database(List<Product> products, String filePath) {
        this.products = products;
        this.filePath = filePath;
    }

    public List<Product> getProducts() {
        return products;
    }

    private void persist() {
        try {
            FileWriter myWriter = new FileWriter(filePath, false);
            for (Product product : products) {
                myWriter.append(product.getName() + ";" + product.getQuantity() + ";" + product.getPrice() + "\n");
            }
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create(Product product) {
        products.add(product);
        persist();
    }

    public void edit(Product product, Integer index) throws IndexOutOfBoundsException{
        //Testar o erro
        products.set(index, product);
        persist();
    };

    public void delete(Integer index) throws IndexOutOfBoundsException{
        //Testar o erro
        products.remove((int)index);
        persist();
    };

    public void search(String substring) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().contains(substring)){
                System.out.println("ID: " + i + "; " + products.get(i).toString());
            }
        }
    };

    public void buy(Integer index, Integer orderQuantity) {
        Product product = products.get(index);
        product.reduceStock(orderQuantity);
        products.set(index, product);
        persist();
    };
}