# Santander Coders: Projeto M1 (Lógica de Programação)
## Cadastro de Produtos (CRUD)

### Arquitetura da Aplicação
O método main cria um Menu, que é responsável por informar ao usuário suas opções, coletar dados dele, e retornar suas solicitações. De posse dos dados, o menu decide qual operação deve ser feita, valida as entradas (quantidade menor que o estoque, preço positivo, números não são strings, etc), e chama os métodos do Database, que efetua a manipulação dos dados. O database tem um ArrayList de Products, de forma que existe um índice para cada produto, e cada produto é um objeto com nome, estoque, e preço.

### Melhorias a implementar:
- O método buyProducts() não permite uma compra maior do que o estoque, mas permite se o usuário tentar comprar o mesmo item duas vezes. Isso pode ser corrigido.
- Os preços usam o BigDecimal, mas não há controle de número de casas decimais. Poderia ser implementada uma padronização de 2 casas decimais.
