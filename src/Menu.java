import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Database database;

    Menu(List<Product> file){
        this.scanner = new Scanner(System.in);
        this.database = new Database(file);
    }

    public void interact() {
        System.out.println("Bem vindo à nossa lojinha!");
        boolean flag = true;
        while (flag) {
            System.out.println("Opções:\n1 - Criar produto;\n2 - Editar produto\n3 - Listar produtos\n4 - Deletar produto\n" +
                    "5 - Comprar produto\n0 - Sair\nQual operação deseja realizar?");
            int option = scanner.nextInt();

            switch (option) {
                case "1" -> createProduct();
                case "2" -> editProduct();
                case "3" -> deleteProduct();
                case "4" -> searchProduct();
                case "5" -> buyproduct();
                case "0" -> flag = false;
                default -> System.out.println("Opção Inválida.");
            }
        }
    }

    private void createProduct() {
        System.out.println("Informe o nome do produto: ");
        String name = scanner.nextLine();
        System.out.println("Certo. Qual é o estoque dele? ");
        Integer quantity = scanner.nextInt();
        System.out.println("Informe o preço unitário: ");
        BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());
        database.create(name, quantity, price);
    }

    private void editProduct() {
        List<Product> products = database.getProducts();
        System.out.println("Estes são os itens: ");
        for (int i = 0; i < products.size(); i++) {
            // Imprime os produtos
        }
        System.out.println("Informe o ID do produto que deseja editar: ");
        Integer id = scanner.nextInt();
//        System.out.println();
//        String name = scanner.nextLine();
//        System.out.println("Certo. Qual é o estoque dele? ");
//        Integer quantity = scanner.nextInt();
//        System.out.println("Informe o preço unitário: ");
//        BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());
//        database.create(name, quantity, price);;
    }
    // Que quer fazer agora?
    // Quero fazer tal coisa
    // Beleza...
    // Toma
    // Fiz
    // E aí?
    // Falou valeu
    // Té mais
//    void static showOptions(){
//
//    }
//
//    void static chooseOption() {
//        boolean continuar = true;
//        while (continuar) {
//            String opcao = view.pegarOpcaoMenu();
//            switch (opcao) {
//
            }
        }
    }
}
