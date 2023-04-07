CREATE TABLE endereco (
  id SERIAL PRIMARY KEY,
  bairro VARCHAR(50) NOT NULL,
  rua VARCHAR(100) NOT NULL,
  numero INTEGER NOT NULL,
  cep VARCHAR(8) NOT NULL
);


CREATE TABLE usuario (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  data_nascimento DATE NOT NULL,
  imagem VARCHAR(100),
  endereco_id INT NOT NULL,
  CONSTRAINT usuario_endereco_fk FOREIGN KEY (endereco_id)
    REFERENCES endereco(id)
);