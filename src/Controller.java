import java.util.List;
import java.util.Scanner;

public class Controller {

    private Database database;

    Controller(List<Product> file){
        this.database = new Database(file);
    }

    public void chooseOperation (Integer operation) {

        switch (operation){
            case "1":

                database.create(name, quantity, price);
        }
    }
}
switch (opcao) {
        case "1" -> criarProduto();
        case "2" -> editarProduto();
        case "3" -> deletarProduto();
        case "4" -> procurarProduto();
        case "5" -> comprarProduto();
        case "0" -> continuar = false;
default -> System.out.println("Opção Inválida.");