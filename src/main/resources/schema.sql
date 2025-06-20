create database erp_db;
use erp_db;
create table item_estoque(
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(200),
    quantidade_atual INT,
    quantidade_ideal INT,
    data_hora_ultima_atualizacao DATE
);
create table produto(
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(200),
    valor_unitario VARCHAR(200),
    data_hora_ultima_atualizacao DATE
);
create table pedido(
    id INT PRIMARY KEY AUTO_INCREMENT,
    forma_pagamento VARCHAR(100),
    pagamento_aprovado BOOLEAN,
    nome_cliente VARCHAR(200),
    comanda INT,
    mesa INT,
    status_pedido VARCHAR(100),
    data_hora_pedido DATE
);
create table item_pedido(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_produto INT references produto(id),
    id_pedido INT references pedido(id),
    quantidade INT,
    FOREIGN KEY(id_produto) references produto(id),
    FOREIGN KEY(id_pedido) references pedido(id)
);