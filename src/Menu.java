import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Menu {
    private Scanner scanner;
    private Database database;

    Menu(String filePath){
        List<Product> products = parseFile(filePath);
        this.scanner = new Scanner(System.in);
        this.database = new Database(products, filePath);
    }

    private List<Product> parseFile(String filePath){
        //Inicia uma lista com o estoque
        List<Product> stock = new ArrayList<>();
        try{
            //Abre o arquivo db.txt
            File db = new File(filePath);
            Scanner leitor = new Scanner(db);
            while (leitor.hasNextLine())
            {
                //Transforma a string data em um vetor
                String data = leitor.nextLine();
                String[] parts = data.split(";"); // Talvez ; seja melhor, pra não dar pau de usar vírgula no preço

                //Envia o vetor para o Product p
                Product p = new Product(parts[0],Integer.parseInt(parts[1]),new BigDecimal(parts[2]));

                //Adiciona ao arraylist
                stock.add(p);
            }
            leitor.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
        }
        System.out.println();

        return (stock);
    }

    public void interact() {
        System.out.println("Bem vindo à nossa lojinha!");
        boolean flag = true;
        while (flag) {
            System.out.println("Opções:\n1 - Criar produto;\n2 - Listar produtos\n3 - Editar produto\n4 - Deletar produto\n" +
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
        this.scanner.close();
        System.out.println("Obrigado pela sua preferência. Nossa lojinha está sempre de portas abertas :)");
    }

    private Product getInput() {
        // Faltando: tratar cada leitura do scanner para certificar que não está vazio / é número positivo
        System.out.println("Informe o nome do produto: ");
        scanner.nextLine();
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
            System.out.println("ID: " + i + " " + products.get(i));

        }
    }

    private void editProduct() {
        System.out.println("Informe o ID do produto que deseja editar: ");
        // Checar que é um inteiro, tratar erro de out of bounds index
        Integer id = scanner.nextInt();
        Product product = getInput();
        database.edit(product, id);
        System.out.println("Produto editado com sucesso!\nID: " + id + " " + product.toString());
    }

    private void deleteProduct(){
        System.out.println("Informe o ID do produto que deseja deletar: ");
        // Checar que é um inteiro, tratar erro de out of bounds index
        Integer id = scanner.nextInt();
        database.delete(id);
        System.out.println("Produto deletado com sucesso!");
    }

    private void searchProduct(){
        System.out.println("Informe o nome a buscar: ");
        scanner.nextLine();
        String substring = scanner.nextLine();
        database.search(substring);
        System.out.println("Busca concluída.");
    }
    private void buyProduct(){
        List<Product> shoppingCart = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        boolean buyMore = true;
        BigDecimal totalOrderPrice = new BigDecimal(0);
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
                BigDecimal orderPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
                totalOrderPrice = totalOrderPrice.add(orderPrice);
                shoppingCart.add(new Product(product.getName(), quantity, orderPrice));
                ids.add(id);
            }
            System.out.println("Deseja adicionar mais um produto ao carrinho? S/N: ");
            scanner.nextLine();
            Character confirm = scanner.nextLine().toLowerCase().charAt(0);
            if (confirm == 'n') buyMore = false;
        }
        System.out.println("Confirma sua compra? S/N: ");
        Character confirm = scanner.nextLine().toLowerCase().charAt(0);
        if (confirm == 's') {
            System.out.println("Você comprou: ");
            for (int i = 0; i < shoppingCart.size(); i++) {
                System.out.println(shoppingCart.get(i).toString());
                database.buy(ids.get(i), shoppingCart.get(i).getQuantity());
            }
            System.out.println("Total: R$" + totalOrderPrice);
        } else System.out.println("Compra cancelada. Trabalhão da porra à toa, hein? ");
    }
}