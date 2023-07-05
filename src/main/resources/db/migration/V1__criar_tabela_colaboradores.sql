CREATE TABLE colaboradores (
id bigint(20) NOT NULL AUTO_INCREMENT,
nome varchar(100) NOT NULL,
senha varchar(100) NOT NULL,
cpf varchar(11) NOT NULL,
telefone varchar(15) NOT NULL,
cargo varchar(255) NOT NULL,
score double NOT NULL,
forca varchar(255) NOT NULL,
PRIMARY KEY (id)
);