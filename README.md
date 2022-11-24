# Santander Coders: Projeto M1 (Lógica de Programação)
## Cadastro de Produtos (CRUD)

### Arquitetura da Aplicação
O método main cria um Menu, que é responsável por informar ao usuário suas opções, coletar dados dele, e retornar suas solicitações. De posse dos dados, o menu decide qual operação deve ser feita, valida as entradas (quantidade menor que o estoque, preço positivo, números não são strings, etc), e chama os métodos do Database, que efetua a manipulação dos dados. O database tem um ArrayList de Products, de forma que existe um índice para cada produto, e cada produto é um objeto com nome, estoque, e preço.

### Funcionalidades a implementar
#### Main:
- Caminho do arquivo "banco de dados".

#### Database:
- Inicialização do banco de dados a partir de um arquivo externo. O construtor recebe um List<Product> file. É necessário gerar esse file a partir de um arquivo externo. Isso é feito no construtor do Menu;
- Salvar as operações no arquivo externo (pode ser criado um arquivo do zero a cada operação, o que é mais fácil, mas menos performático, ou a edição de um arquivo pré-existente);
- Implementação do método close (talvez só faça sentido no menu, pra fechar o scanner).

#### Menu:
- Tratamento de erros de tentativa de acesso de índice além dos limites do banco de dados;
- Validação dos parâmetros de entrada para as opções do switch dentro do método interact(), do método getInput(nome, estoque, preço), do método buy(quantidade > 0), e das entradas de ID/índice (inteiros não-negativos);
- Finalização da implementação do método buyProduct().
