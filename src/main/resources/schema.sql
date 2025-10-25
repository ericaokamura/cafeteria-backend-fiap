CREATE TABLE item_estoque (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(200),
    quantidade_atual INT,
    quantidade_ideal INT,
    data_hora_ultima_atualizacao TIMESTAMP
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200),
    descricao VARCHAR(200),
    tags VARCHAR(200),
    valor_unitario REAL,
    data_hora_ultima_atualizacao TIMESTAMP
);

CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    forma_pagamento VARCHAR(100),
    pagamento_aprovado BOOLEAN,
    nome_cliente VARCHAR(200),
    comanda INT,
    mesa INT,
    status_pedido VARCHAR(100),
    data_hora_pedido TIMESTAMP,
    comentarios VARCHAR(500)
);

CREATE TABLE item_pedido (
    id SERIAL PRIMARY KEY,
    id_produto INT NOT NULL,
    id_pedido INT NOT NULL,
    quantidade INT,
    FOREIGN KEY (id_produto) REFERENCES produto(id),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome_usuario VARCHAR(200),
    senha VARCHAR(200),
    cpf VARCHAR(11),
    bloqueado BOOLEAN,
    email VARCHAR(200),
    tipo_funcionario VARCHAR(11),
    data_hora_cadastro TIMESTAMP
);

CREATE TABLE auditoria_log (
    id SERIAL PRIMARY KEY,
    mensagem_log VARCHAR(200),
    metodo_http VARCHAR(20),
    n_tentativas INT,
    data_hora_auditoria TIMESTAMP
);

CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS vector_store (
    id UUID PRIMARY KEY,
    content TEXT,
    embedding VECTOR(1536),
    metadata TEXT
);
