import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Database database;

    Menu(String filePath){
        List<Product> products = parseFile(filePath); // parseFile a implementar
        this.scanner = new Scanner(System.in);
        this.database = new Database(products);
    }

    private List<Product> parseFile(String filePath){
        // Recebe o caminho para o arquivo, faz uma iteração de todas as linhas,
        // constrói um produto com cada linha, salva cada produto em um índice de um ArrayList,
        // retorna o ArrayList
    }

    public void interact() {
        System.out.println("Bem vindo à nossa lojinha!");
        boolean flag = true;
        while (flag) {
            System.out.println("Opções:\n1 - Criar produto;\n2 - Listar produtos\n 3 - Editar produto\n4 - Deletar produto\n" +
                    "5 - Buscar produto\n6 - Comprar produto\n0 - Sair\nQual operação deseja realizar?");
            // Tratar a entrada para ser um int
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> createProduct();
                case 2 -> showProducts();
                case 3 -> editProduct();
                case 4 -> deleteProduct();
                case 5 -> searchProduct();
                case 6 -> buyProduct();
                case 0 -> flag = false;
                default -> System.out.println("Opção Inválida.");
            }
        }
        // finally { scanner.close() }
        System.out.println("Obrigado pela sua preferência. Nossa lojinha está sempre portas abertas :)");
    }

    private Product getInput() {
        // Faltando: tratar cada leitura do scanner para certificar que não está vazio / é número positivo
        System.out.println("Informe o nome do produto: ");
        String name = scanner.nextLine();
        // if (name.equals(""))...
        System.out.println("Certo. Qual é o estoque dele? ");
        Integer quantity = scanner.nextInt();
        // if (quantity < 0)...
        System.out.println("Informe o preço unitário: ");
        BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());
        // if (price.compareTo(new BigDecimal(0)) == -1)
        return new Product(name, quantity, price);
    }

    private void createProduct() {
        Product product = getInput();
        database.create(product);
        System.out.println("Produto criado com sucesso!\n" + product.toString());
    }

    private void showProducts() {
        List<Product> products = database.getProducts();
        System.out.println("Estes são os itens: ");
        for (int i = 0; i < products.size(); i++) {
            // Imprime os produtos
        }
    }

    private void editProduct() {
        System.out.println("Informe o ID do produto que deseja editar: ");
        // Checar que é um inteiro, tratar erro de out of bounds index
        Integer id = scanner.nextInt();
        Product product = getInput();
        database.edit(product, id);
        System.out.println("Produto editado com sucesso!\nID: " + id + product.toString());
    }

    private void deleteProduct(){
        System.out.println("Informe o ID do produto que deseja deletar: ");
        // Checar que é um inteiro, tratar erro de out of bounds index
        Integer id = scanner.nextInt();
        database.delete((id));
        System.out.println("Produto deletado com sucesso!");
    }

    private void searchProduct(){
        System.out.println("Informe o nome a buscar: ");
        String substring = scanner.nextLine();
        database.search(substring);
        System.out.println("Busca concluída.");
    }
    private void buyProduct(){
        List<Product> shoppingCart = new ArrayList<>();
        boolean buyMore = true;
        while (buyMore){
            System.out.println("Informe o ID do produto: ");
            // Validar que é um int, tratar erro de out of bounds
            Integer id = scanner.nextInt();
            Product product = database.getProducts().get(id);
            System.out.println("Informe a quantidade do produto: ");
            // Validar que é um int positivo
            Integer quantity = scanner.nextInt();
            if (quantity > product.getQuantity())
                System.out.printf("Quantidade indisponível. Temos apenas %d unidades em estoque.\n", product.getQuantity());
            else {
                // Falta pensar melhor neste passo. Vai ter que adicionar o nome, a quantidade, e o preço.
                // Esta implementação passa o estoque inteiro, e não a quantidade.
                shoppingCart.add(product);
            }
        }
        System.out.println("Confirma sua compra? S/N: ");
        Character confirm = scanner.nextLine().toLowerCase().charAt(0);
        if (confirm == 's') {
            // Precisa pensar melhor como passar para o buy os parâmetros necessários
            shoppingCart.forEach(product -> database.buy());
            // mostre tudo que ele comprou, os preços e o total
        } else System.out.println("Compra cancelada. ");
    }
}
