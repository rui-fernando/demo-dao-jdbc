# Demo DAO JDBC

Aplicação em Java que demonstra a implementação do padrão de projeto **DAO (Data Access Object)** combinado com **JDBC puro**, para acesso a um banco de dados relacional (MySQL) com as entidades `Seller` (vendedor) e `Department` (departamento).

## 📋 Sobre o projeto

O projeto evolui a abordagem "tudo no `main`" comum em exercícios iniciais de JDBC, introduzindo uma arquitetura organizada em camadas: entidades puras, interfaces de acesso a dados (contratos) e implementações concretas em JDBC, orquestradas por uma fábrica (`DAOFactory`).

## ✨ Funcionalidades

CRUD completo para as duas entidades do sistema:

**Seller (Vendedor)**
- Buscar por ID (`findById`)
- Buscar por departamento (`findByDepartment`)
- Listar todos (`findAll`)
- Inserir (`insert`)
- Atualizar (`update`)
- Remover por ID (`deletedById`)

**Department (Departamento)**
- Buscar por ID (`findById`)
- Listar todos (`findAll`)
- Inserir (`insert`)
- Atualizar (`update`)
- Remover por ID (`deletedById`)

## 🏗️ Arquitetura — Padrão DAO

O projeto separa claramente **o quê** pode ser feito de **como** é feito:

- **`model.entities`** — classes puras (`Seller`, `Department`), representando as tabelas do banco como objetos, sem nenhuma lógica de persistência.
- **`model.dao`** — interfaces (`SellerDAO`, `DepartmentDAO`) que definem o contrato de operações disponíveis para cada entidade, independente de tecnologia.
- **`model.dao.impl`** — implementações concretas (`SellerDAO_JDBC`, `DepartmentDAO_JDBC`) que usam JDBC (`PreparedStatement`) para executar as operações no banco.
- **`model.dao.DAOFactory`** — fábrica responsável por instanciar os DAOs, centralizando a escolha da implementação e desacoplando o restante da aplicação de detalhes de JDBC.
- **`db`** — infraestrutura de conexão (`DB`) e exceções customizadas (`DbException`, `DbIntegrityException`) para tratar erros de banco de forma mais clara que uma `SQLException` genérica.

## 🚀 Destaques técnicos

- **`INNER JOIN` otimizado**: as consultas de `Seller` já trazem o departamento correspondente junto, evitando uma segunda consulta ao banco.
- **Cache local de objetos**: em `findAll()`/`findByDepartment()`, um `Map<Integer, Department>` garante que vendedores do mesmo departamento compartilhem a mesma instância de `Department`, em vez de criar objetos duplicados.
- **Chaves geradas automaticamente**: uso de `Statement.RETURN_GENERATED_KEYS` para recuperar o ID gerado pelo banco logo após um `insert`.
- **Tratamento de integridade referencial**: exceção customizada (`DbIntegrityException`) para erros como remoção de um departamento que ainda possui vendedores vinculados, ou tentativa de duplicar um dado com restrição de unicidade no banco (ex: e-mail).

## 🛠️ Tecnologias utilizadas

- **Java**
- **JDBC** (`java.sql`)
- **MySQL** (ou outro banco relacional configurável via `db.properties`)
- Padrão de projeto **DAO**

## 📁 Estrutura do projeto

```
demo-dao-jdbc/
└── src/
    ├── application/
    │   ├── Program.java          # Testa o CRUD completo de Seller
    │   └── Program2.java         # Testa o CRUD completo de Department
    ├── db/
    │   ├── DB.java                # Conexão e utilitários JDBC
    │   ├── DbException.java       # Exceção genérica de banco
    │   └── DbIntegrityException.java  # Exceção de integridade referencial
    └── model/
        ├── entities/
        │   ├── Seller.java
        │   └── Department.java
        └── dao/
            ├── SellerDAO.java           # Interface (contrato)
            ├── DepartmentDAO.java       # Interface (contrato)
            ├── DAOFactory.java           # Fábrica de DAOs
            └── impl/
                ├── SellerDAO_JDBC.java       # Implementação JDBC
                └── DepartmentDAO_JDBC.java   # Implementação JDBC
```

## ▶️ Como executar

### Pré-requisitos
- JDK instalado
- MySQL (ou outro banco relacional) com as tabelas `department` e `seller` criadas
- Driver JDBC do banco utilizado no classpath

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/rui-fernando/demo-dao-jdbc.git
   cd demo-dao-jdbc
   ```
2. Crie um arquivo `db.properties` na raiz do projeto (não incluído no repositório, por segurança), com as credenciais do seu banco:
   ```properties
   user=seu_usuario
   password=sua_senha
   dburl=jdbc:mysql://localhost:3306/nome_do_banco
   useSSL=false
   ```
3. Compile e execute a demonstração desejada:
   ```bash
   javac -d bin -cp "src;lib/*" src/application/Program.java src/db/*.java src/model/entities/*.java src/model/dao/*.java src/model/dao/impl/*.java
   java -cp "bin;lib/*" application.Program
   ```
   *(ajuste o separador de classpath para `:` em Linux/Mac; troque `Program` por `Program2` para testar o CRUD de departamentos)*

## 📌 Nota

Este projeto tem fins exclusivamente educacionais, para prática do padrão DAO e de acesso a banco de dados relacional via JDBC puro, sem uso de frameworks de persistência (ORM).

## 👤 Autor

Desenvolvido por [Rui Fernando](https://github.com/rui-fernando), estudante de Ciência da Computação na Universidade Estadual da Paraíba (UEPB).
