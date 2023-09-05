# Projeto de Gestão de Estoque

Este é um projeto de gestão de estoque desenvolvido em Java com o uso do Spring Framework. Ele oferece uma solução para gerenciar estoques, categorias e produtos.

# Como rodar o projeto

Para rodar o projeto, siga estas etapas:

1. **Instale as ferramentas necessárias**

Se você ainda não instalou as ferramentas necessárias, siga as instruções na seção "Instalação das Ferramentas" do README.

2. **Obtenha o projeto**

Clone o projeto do GitHub:

**Execute o Docker Compose**

O comando `docker-compose up -d` iniciará o projeto em um ambiente de contêiner:
execute o comando ./gradlew clean build.
./gradlew bootrun.


## Ferramentas Necessárias para Executar o Projeto

#### baixe as ferramentas necessarias com arquivo sh seguindo os comandos:
 Dê permissão de execução ao script


``
chmod +x ferramentas.sh
``

Execute o script para instalar as ferramentas

``
bash
./ferramentas.sh
``

Certifique-se de ter as seguintes ferramentas e recursos instalados em seu ambiente de desenvolvimento:

- **Docker**: Utilizado para criar e gerenciar contêineres.
- **Docker Compose**: Utilizado para definir e executar serviços multi-contêiner em aplicativos.
- **Java 17**: A versão mais recente do Java é necessária para executar o aplicativo.
- **SQL Developer ou DBeaver**: Um cliente SQL para interagir com o banco de dados.
- **Postman**: Uma ferramenta para testar APIs.
- **IntelliJ IDEA**: Um ambiente de desenvolvimento integrado (IDE) para Java.

## Instalação das Ferramentas

Siga estas etapas para configurar o ambiente e executar o projeto:

1. **Docker e Docker Compose**:

   Certifique-se de ter o Docker e o Docker Compose instalados em seu sistema. Você pode obtê-los em [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/).

2. **Java 17**:

   Instale o Java 17 em seu sistema. Você pode encontrar a versão mais recente em [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) ou [OpenJDK](https://adoptopenjdk.net/).

3. **SQL Developer ou DBeaver**:

   Escolha entre o SQL Developer e o DBeaver como seu cliente de banco de dados SQL e siga as instruções de instalação nos respectivos sites:
    - [SQL Developer](https://www.oracle.com/database/technologies/appdev/sqldeveloper-landing.html)
    - [DBeaver](https://dbeaver.io/download/)

4. **Postman**:

   Baixe e instale o Postman em [Postman](https://www.postman.com/downloads/).

5. **IntelliJ IDEA**:

   Instale o IntelliJ IDEA em [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

## Uso

### Autenticação

Para acessar os recursos protegidos, você precisará se autenticar. Isso pode ser feito por meio de uma solicitação HTTP com suas credenciais, geralmente um nome de usuário e senha.

### Gerenciamento de Estoque

#### Listar Estoques

- **Método HTTP:** GET
- **Descrição:** Este método permite listar todos os estoques disponíveis na aplicação.

#### Criar Estoque

- **Método HTTP:** POST
- **Descrição:** Este método permite criar um novo estoque especificando seu nome e descrição.

#### Atualizar Estoque

- **Método HTTP:** PUT
- **Descrição:** Este método permite atualizar os detalhes de um estoque existente, como nome ou descrição.

#### Excluir Estoque

- **Método HTTP:** DELETE
- **Descrição:** Este método permite remover um estoque da aplicação.

### Gerenciamento de Categorias

#### Listar Categorias

- **Método HTTP:** GET
- **Descrição:** Este método permite listar todas as categorias disponíveis em um estoque.

#### Criar Categoria

- **Método HTTP:** POST
- **Descrição:** Este método permite criar uma nova categoria em um estoque especificando seu nome e descrição.

#### Atualizar Categoria

- **Método HTTP:** PUT
- **Descrição:** Este método permite atualizar os detalhes de uma categoria existente em um estoque.

#### Excluir Categoria

- **Método HTTP:** DELETE
- **Descrição:** Este método permite remover uma categoria de um estoque.

### Gerenciamento de Produtos

#### Listar Produtos

- **Método HTTP:** GET
- **Descrição:** Este método permite listar todos os produtos disponíveis em uma categoria.

#### Criar Produto

- **Método HTTP:** POST
- **Descrição:** Este método permite criar um novo produto em uma categoria especificando seu nome, descrição e preço.

#### Atualizar Produto

- **Método HTTP:** PUT
- **Descrição:** Este método permite atualizar os detalhes de um produto existente em uma categoria.

#### Excluir Produto

- **Método HTTP:** DELETE
- **Descrição:** Este método permite remover um produto de uma categoria.

## Funcionalidades

O projeto permite:

- Gerenciar estoques.
- Criar, atualizar e excluir categorias em um estoque.
- Criar, atualizar e excluir produtos em uma categoria.
- Autenticar usuários para acessar recursos protegidos.

### Acessando a Documentação com Swagger

[swagger](http://localhost:8080/swagger-ui/index.html#/)

## Contribuindo

Sinta-se à vontade para contribuir com melhorias ou correções para este projeto. Abra um problema ou envie uma solicitação de pull request para colaborar.
