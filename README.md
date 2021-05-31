# 🛒 Mercado Fácil

Um supermercado da cidade de Campina Grande precisa de um sistema que gerencie o estoque e venda de produtos na sua loja. Neste sistema, o administrador deve obter uma visão geral e o controle sobre o funcionamento do supermercado, por exemplo, ele deve poder adicionar novos produtos, acompanhar quantas unidades do produto estão disponíveis, alterar preços, ser notificado sobre eventos críticos, gerenciar as vendas e oferecer alguns serviços personalizados para o cliente.

## ⚙️ Estrutura básica
- Um projeto: MercadoFacil;
- Um Controller RestApiController que implementa os endpoints da API Rest.
- Dois repositórios são utilizados: ProdutoRepository e LoteRepository, que são responsáveis por manipular as entidades Produto e Lote em um banco de dados em memória;
- O modelo é composto pelas classes Produto.java e Lote.java que podem ser encontradas no pacote model;
- O pacote exceptions guarda as classes de exceções que podem ser levantadas dentro do sistema;
- Não há implementação de frontend, mas o projeto fornece uma interface de acesso à API via swagger.

## 💻 Tecnologias
- Código base gerado via start.sprint.io com as seguintes dependências:
- Spring Web
- Spring Actuator
- Spring Boot DevTools
- Spring Data JPA
- H2 Database

## 🌐 Endereços úteis
- http://localhost:8080/swagger-ui.html
- http://localhost:8080/h2
