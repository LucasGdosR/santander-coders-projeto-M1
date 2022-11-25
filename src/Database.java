import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private List<Product> products;
    String filePath = "./database/db.txt";

    Database() {}

    List<Product> getProducts() {
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
                String[] parts = data.split(",");

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
        // Recebe o caminho para o arquivo, faz uma iteração de todas as linhas,
        // constrói um produto com cada linha, salva cada produto em um índice de um ArrayList,
        // retorna o ArrayList

        return (stock);
    }

    public void create(Product product) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.append(product.getName() + "," + product.getQuantity() + "," + product.getPrice() + "\n");
            myWriter.close();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
        }

    }

    public void edit(Product product, Integer index) throws IndexOutOfBoundsException{
        try {
            File db = new File(filePath);
            List<String> lines = Files.readAllLines(db.toPath());
            lines.set(index, product.getName() + "," + product.getQuantity() + "," + product.getPrice());
            Files.write(db.toPath(), lines);
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
        }
    };

    public void delete(int index) throws IndexOutOfBoundsException{
        try {
            File db = new File(filePath);
            List<String> lines = Files.readAllLines(db.toPath());
            lines.remove(index);
            Files.write(db.toPath(), lines);
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
        }
    };

    public List<Product> search(String substring) {
        try {
            List<Product> products = new ArrayList<>();

            File db = new File(filePath);
            List<String> lines = Files.readAllLines(db.toPath());

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(substring)) {
                    String[] parts = lines.get(i).split(",");
                    Product p = new Product(parts[0],Integer.parseInt(parts[1]),new BigDecimal(parts[2]));
                    products.add(p);
                }
            }

            return products;
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
            return null;
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