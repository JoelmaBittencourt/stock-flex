# Projeto de Gestão de Estoque

Este é um projeto de gestão de estoque desenvolvido em Java com o uso do Spring Framework. Ele oferece uma solução para gerenciar estoques, categorias e produtos.

## Como rodar o projeto

Para rodar o projeto, siga estas etapas:

### 1. Instale as ferramentas necessárias

Se você ainda não instalou as ferramentas necessárias, siga as instruções na seção "Instalação das Ferramentas" abaixo.

### 2. Obtenha o projeto

Clone o projeto do GitHub:

```bash
git clone https://github.com/seu-usuario/seu-projeto.git
```   
## Execute o Docker Compose

Para iniciar o projeto em um ambiente de contêiner, execute o seguinte comando:

```bash
docker-compose up -d
```

```bash
    ./gradlew clean build
    ./gradlew bootrun
```

Ferramentas Necessárias para Executar o Projeto

Certifique-se de ter as seguintes ferramentas e recursos instalados em seu ambiente de desenvolvimento:

    Docker: Utilizado para criar e gerenciar contêineres.
    Docker Compose: Utilizado para definir e executar serviços multi-contêiner em aplicativos.
    Java 17: A versão mais recente do Java é necessária para executar o aplicativo.
    SQL Developer ou DBeaver: Um cliente SQL para interagir com o banco de dados.
    Postman: Uma ferramenta para testar APIs.
    IntelliJ IDEA: Um ambiente de desenvolvimento integrado (IDE) para Java.

Instalação das Ferramentas

Siga estas etapas para configurar o ambiente e executar o projeto:

    Docker e Docker Compose:

    Certifique-se de ter o Docker e o Docker Compose instalados em seu sistema. Você pode obtê-los em Docker e Docker Compose.

    Java 17:

    Instale o Java 17 em seu sistema. Você pode encontrar a versão mais recente em Oracle ou OpenJDK.

    SQL Developer ou DBeaver:

    Escolha entre o SQL Developer e o DBeaver como seu cliente de banco de dados SQL e siga as instruções de instalação nos respectivos sites:
        SQL Developer
        DBeaver

    Postman:

    Baixe e instale o Postman em Postman.

    IntelliJ IDEA:

    Instale o IntelliJ IDEA em IntelliJ IDEA.

Uso
Autenticação

Para acessar os recursos protegidos, você precisará se autenticar. Isso pode ser feito por meio de uma solicitação HTTP com suas credenciais, geralmente um nome de usuário e senha.
Gerenciamento de Estoque
Listar Estoques

    Método HTTP: GET
    Descrição: Este método permite listar todos os estoques disponíveis na aplicação.

Criar Estoque

    Método HTTP: POST
    Descrição: Este método permite criar um novo estoque especificando seu nome e descrição.

Atualizar Estoque

    Método HTTP: PUT
    Descrição: Este método permite atualizar os detalhes de um estoque existente, como nome ou descrição.

Excluir Estoque

    Método HTTP: DELETE
    Descrição: Este método permite remover um estoque da aplicação.

Gerenciamento de Categorias
Listar Categorias

    Método HTTP: GET
    Descrição: Este método permite listar todas as categorias disponíveis em um estoque.

Criar Categoria

    Método HTTP: POST
    Descrição: Este método permite criar uma nova categoria em um estoque especificando seu nome e descrição.

Atualizar Categoria

    Método HTTP: PUT
    Descrição: Este método permite atualizar os detalhes de uma categoria existente em um estoque.

Excluir Categoria

    Método HTTP: DELETE
    Descrição: Este método permite remover uma categoria de um estoque.

Gerenciamento de Produtos
Listar Produtos

    Método HTTP: GET
    Descrição: Este método permite listar todos os produtos disponíveis em uma categoria.

Criar Produto

    Método HTTP: POST
    Descrição: Este método permite criar um novo produto em uma categoria especificando seu nome, descrição e preço.

Atualizar Produto

    Método HTTP: PUT
    Descrição: Este método permite atualizar os detalhes de um produto existente em uma categoria.

Excluir Produto

    Método HTTP: DELETE
    Descrição: Este método permite remover um produto de uma categoria.

Funcionalidades

O projeto permite:

    Gerenciar estoques.
    Criar, atualizar e excluir categorias em um estoque.
    Criar, atualizar e excluir produtos em uma categoria.
    Autenticar usuários para acessar recursos protegidos.

Acessando a Documentação com Swagger

[swagger](http://localhost:8080/swagger-ui/index.html#/)

### Script de Instalação de Ferramentas

Você pode usar o script ferramentas.sh para automatizar a instalação das ferramentas necessárias. Siga as etapas abaixo para usá-lo:

    Dê permissão de execução ao script

    Abra um terminal e execute o seguinte comando para dar permissão de execução ao script:

 ``` shell

chmod +x ferramentas.sh
```

Execute o script para instalar as ferramentas

Agora, execute o script usando o seguinte comando:

```shell
    ./ferramentas.sh
```

    Isso executará o script e instalará as ferramentas necessárias em seu sistema. Certifique-se de que o script está localizado no diretório correto e que você está no diretório correto ao executá-lo.

Contribuindo

Sinta-se à vontade para contribuir com melhorias ou correções para este projeto. Abra um problema ou envie uma solicitação de pull request para