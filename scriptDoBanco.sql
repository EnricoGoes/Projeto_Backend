CREATE DATABASE IF NOT EXISTS Projeto_SCC;
USE Projeto_SCC;

CREATE TABLE IF NOT EXISTS Endereco(
	idEndereco INT AUTO_INCREMENT PRIMARY KEY,
    Rua_Endereco VARCHAR(45) NOT NULL,
    Bairro_Endereco VARCHAR(45) NOT NULL,
    Numero_Endereco VARCHAR(5) NOT NULL,
    Cep_Endereco VARCHAR(8) NOT NULL
);
CREATE TABLE IF NOT EXISTS Telefone(
	idTelefone INT AUTO_INCREMENT PRIMARY KEY,
    Tipo_Telefone VARCHAR(20) NOT NULL,
    Numero_Telefone VARCHAR(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS Usuario(
	idUsuario INT AUTO_INCREMENT PRIMARY KEY,
	Nome_Usuario VARCHAR(45) NOT NULL,
    CPF_Usuario VARCHAR(11) NOT NULL UNIQUE,
    Telefone_idTelefone INT UNIQUE,
    FOREIGN KEY (Telefone_idTelefone)
    REFERENCES Telefone(idTelefone) ON UPDATE CASCADE ON DELETE SET NULL,
    Endereco_idEndereco INT,
    FOREIGN KEY (Endereco_idEndereco)
    REFERENCES Endereco(idEndereco) ON UPDATE CASCADE ON DELETE SET NULL,
    Email_Usuario VARCHAR(40) NOT NULL UNIQUE,
    Login_Usuario VARCHAR(20) NOT NULL UNIQUE,
    Senha_Usuario VARCHAR(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS Categoria(
	idCategoria INT AUTO_INCREMENT PRIMARY KEY,
    Descricao_Categoria VARCHAR(45) NOT NULL,
	Tipo_Categoria VARCHAR(45) NOT NULL
);
CREATE TABLE IF NOT EXISTS Contas(
	idContas INT AUTO_INCREMENT PRIMARY KEY,
    Descricao_Conta VARCHAR(45) NOT NULL,
    Valor_Conta FLOAT NOT NULL,
    DataVencimento_Conta DATE NOT NULL,
    DataPagamento_Conta DATE,
    TipoConta_idCategoria INT NOT NULL,
    FOREIGN KEY (TipoConta_idCategoria)
    REFERENCES Categoria(idCategoria) ON UPDATE CASCADE ON DELETE RESTRICT,
    Status_Conta TINYINT NOT NULL,
    Usuario_idUsuario INT NOT NULL,
	FOREIGN KEY (Usuario_idUsuario)
    REFERENCES Usuario(idUsuario) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Parcelas(
	idParcelas INT AUTO_INCREMENT PRIMARY KEY,
    DataVencimento_Parcela DATE NOT NULL,
    Numero_Parcela INT NOT NULL,
    Valor_Parcela DOUBLE NOT NULL,
    Status_Parcela TINYINT NOT NULL,
    Conta_idContas INT NOT NULL,
    FOREIGN KEY (Conta_idContas)
    REFERENCES Contas(idContas) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO Endereco (Rua_Endereco, Bairro_Endereco, Numero_Endereco, Cep_Endereco)
VALUES ('Rua das Flores', 'Centro', '123', '12345678');

INSERT INTO Telefone (Tipo_Telefone, Numero_Telefone)
VALUES ('Celular', '11999998888');

INSERT INTO Usuario (Nome_Usuario, CPF_Usuario, Telefone_idTelefone, Endereco_idEndereco, Email_Usuario, Login_Usuario, Senha_Usuario)
VALUES ('João Silva', '12345678901', 1, 1, 'joao@email.com', 'joaosilva', 'senha123');

INSERT INTO Categoria (Descricao_Categoria, Tipo_Categoria)
VALUES ('Aluguel', 'Despesa'), ('Salário', 'Receita');

INSERT INTO Contas (Descricao_Conta, Valor_Conta, DataVencimento_Conta, DataPagamento_Conta, TipoConta_idCategoria, Status_Conta, Usuario_idUsuario)
VALUES ('Salário Mensal', 5000.00, '2024-04-01', '2024-04-01', 2, 1, 1);

INSERT INTO Contas (Descricao_Conta, Valor_Conta, DataVencimento_Conta, DataPagamento_Conta, TipoConta_idCategoria, Status_Conta, Usuario_idUsuario)
VALUES ('Aluguel do Apartamento', 1500.00, '2024-04-05', NULL, 1, 0, 1);

INSERT INTO Parcelas (DataVencimento_Parcela, Numero_Parcela, Valor_Parcela, Status_Parcela, Conta_idContas)
VALUES 
    ('2024-04-05', 1, 500.00, 0, 2),
    ('2024-05-05', 2, 500.00, 0, 2),
    ('2024-06-05', 3, 500.00, 0, 2);