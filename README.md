<h1 align="center">Stock-Flex 📦</h1>

<p align="center">
  <strong>Sistema de Gerenciamento de Estoque</strong>
</p>

<p align="center">
  <a href="#-sobre">Sobre</a> •
  <a href="#-tecnologias">Tecnologias</a> •
  <a href="#-instruções-de-utilização">Instruções de Utilização</a> •
  <a href="#-swagger">Swagger</a> •
  <a href="#-como-contribuir">Como Contribuir</a> •
  <a href="#-licença">Licença</a>
</p>


---

## 💡 Sobre

O **Stock-Flex** é um sistema de gerenciamento de estoque desenvolvido como uma aplicação prática de programação orientada a objetos, REST, gerenciamento de banco de dados e segurança com Spring. Ele permite que os usuários controlem o estoque de produtos, adicionem categorias, atualizem e excluam informações.

Este projeto é uma oportunidade para aprender e aplicar conceitos fundamentais de desenvolvimento de software, incluindo:

- 🪄 **Orientação a Objetos**: O Stock-Flex utiliza princípios de programação orientada a objetos para criar uma estrutura organizada e modular do código.

- 🌐 **API RESTful**: A aplicação é baseada em uma API RESTful, que fornece uma interface fácil de usar para interagir com o sistema.

- 🗄️ **Banco de Dados**: O sistema armazena dados em um banco de dados Oracle e H2, permitindo o armazenamento e recuperação eficientes de informações de estoque.

- 🔐 **Segurança com Spring Security**: A segurança é uma prioridade, e o Spring Security é usado para autenticar e autorizar usuários, protegendo as funcionalidades críticas.

O Stock-Flex é uma excelente ferramenta para estudar e praticar o desenvolvimento de aplicativos web, com foco em gerenciamento de estoque. Além disso, ele pode ser estendido e personalizado para atender a diferentes requisitos de negócios relacionados ao gerenciamento de estoque.

---

## 🚀 Tecnologias

### Backend

- ☕ [Java 17](http://www.oracle.com/java/technologies/javase-downloads.html)
- 📦 [Gradle Wrapper (Gradlew)](https://gradle.org/install/)

### Database

- 🛢️ [Oracle Database](https://www.oracle.com/database/technologies/)
- 🗄️ [H2 Database](https://www.h2database.com/html/main.html)

### Ferramentas

- 🧠 [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- 📮 [Postman](http://www.postman.com/downloads/)
- 🧭 [SQL Developer](https://www.oracle.com/tools/downloads/sqldev-downloads.html)
- 🐙 [Git](https://git-scm.com/downloads/)
- 🐳 [Docker](https://docs.docker.com/desktop/install/windows-install/)

---

## ⤵ Instruções de Utilização

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas e dependências instaladas:

- ☕ Java 17
- 📦 Gradle Wrapper (Gradlew)
- 🛢️ Oracle Database
- 🗄️ H2 Database
- 🧠 IntelliJ IDEA
- 📮 Postman
- 🧭 SQL Developer
- 🐙 Git
- 🐳 Docker (Docker-Compose)



<br>

- Passo 1: Clonar o repositório:
  ```bash
  git clone https://github.com/JoelmaBittencourt/stock-flex.git

<br>

- Passo 2: subir o docker(se preferir pelo banco oracle, caso contrario pule este passo):
  ```bash
   docker-compose-up -d

<br>

- Passo 3: Iniciar a aplicação Spring Boot:
  ```bash
  $ ./gradlew bootrun
  ```

<br>


Você pode acessar a documentação da API do Stock-Flex utilizando o Swagger. Basta iniciar a aplicação e acessar o seguinte link em seu navegador:
[swagger](http://localhost:8080/swagger-ui/index.html#/)


🤝 Como Contribuir

Você pode contribuir para o Stock-Flex de várias maneiras:

   1- Reportando Problemas: Se encontrar algum problema ou erro, por favor, relate-o no GitHub Issues.

   2- Sugerindo Melhorias: Se tiver sugestões de melhorias ou novos recursos, fique à vontade para criar uma nova

## 📝 Licença

Este projeto está sob a **Licença MIT** - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.

A Licença MIT é uma licença de código aberto permissiva que permite:

- O uso do código para qualquer finalidade, incluindo fins comerciais.
- A modificação do código.
- A distribuição do código.
- A inclusão da sua própria licença em derivações do código.

A única obrigação é que você inclua o aviso de licença e atribuição ao autor original em seu projeto derivado.

[Leia a Licença MIT](LICENSE) para obter mais detalhes.

---

