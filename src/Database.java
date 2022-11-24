import java.math.BigDecimal;
import java.util.List;

public class Database {
    private List<Product> products;

    Database(List<Product> file) {
        this.products = file;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void create(Product product) {
        products.add(product);
    }

    public void edit(Product product, Integer index) throws IndexOutOfBoundsException{
        //Testar o erro
        products.set(index, product);
    };

    public void delete(Integer index) throws IndexOutOfBoundsException{
        //Testar o erro
        products.remove(index);
    };

    public void search(String substring) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().contains(substring)){
                // Formatar bonitinho
                System.out.println("ID: " + i + "; " + products.get(i).toString());
            }
        }
    };

    public void buy(Integer index, Integer orderQuantity) {
        products.get(index).reduceStock(orderQuantity);
    };

    public void close(){
        // Desnecessário? Talvez só o close no scanner na classe menu
    };
}
/*
Venda de produtos, onde o usuário pode escolher produtos e quantidades conforme ele queira, assim que escolher finalizar,
mostre tudo que ele comprou, os preços e o total. Quando ele for escolher o produto e quantidade, faça uma verificação
se o produto tem aquela quantidade, caso não tenha, informa ao usuário que não contém a quantidade deste produto no estoque.
Assim que o usuário confirmar a compra, deduza as quantidades dos produtos selecionados.
 */