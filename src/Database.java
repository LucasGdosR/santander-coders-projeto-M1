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

    public Integer create(String name, Integer quantity, BigDecimal price) {
        // Tratar o retorno com int
        if (quantity < 0) return 1;
        if (price.compareTo(new BigDecimal(0)) == -1) return 2;
        if (name.equals("")) return 3;
        Product product = new Product(name, quantity, price);
        products.add(product);
        return 0;
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

    public void close(){};
}
/* Crie um programa que simula uma loja

Onde sera possivel: Criar, Editar, Excluir, Listar e Pesquisar o produto; Realizar a venda dos produtos;

Regras:

Deve ser manipulado um arquivo para salvar as informações dos produtos, para que caso saia do programa as informações persistam.

Criar produto, contendo as informações nome do produto, quantidade e preço.

Editar produto, que pegue as novas informações e atualize o produto escolhido pela identificador. (Para isso liste os produtos
com um identificador sendo a posição do produto).

Excluir produto, que pega a posição do produto e excluía ele. (Para isso liste os produtos com um identificador sendo a posição
do produto)

Pesquisa de produtos. Receba o nome ou parte dele, filtre todos os produtos que contém o nome e mostre a lista filtrada.

Venda de produtos, onde o usuário pode escolher produtos e quantidades conforme ele queira, assim que escolher finalizar,
mostre tudo que ele comprou, os preços e o total. Quando ele for escolher o produto e quantidade, faça uma verificação
se o produto tem aquela quantidade, caso não tenha, informa ao usuário que não contém a quantidade deste produto no estoque.
Assim que o usuário confirmar a compra, deduza as quantidades dos produtos selecionados.

Uma opção de saída que fecha o programa caso o usuário escolha.
 */