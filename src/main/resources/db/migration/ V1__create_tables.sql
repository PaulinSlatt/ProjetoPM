CREATE TABLE policial (
                          id BIGINT PRIMARY KEY,
                          matricula VARCHAR(30) NOT NULL UNIQUE,
                          FOREIGN KEY (id) REFERENCES usuario(id)
);

CREATE TABLE ocorrencia (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            tipo_crime VARCHAR(50) NOT NULL,
                            descricao TEXT NOT NULL,
                            data_hora DATETIME NOT NULL,
                            local VARCHAR(150) NOT NULL,
                            status ENUM('ABERTA', 'EM_INVESTIGACAO', 'ENCERRADA') DEFAULT 'ABERTA',
                            policial_responsavel_id BIGINT,
                            criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (policial_responsavel_id) REFERENCES policial(id)
);

CREATE TABLE envolvido (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           nome VARCHAR(100) NOT NULL,
                           tipo ENUM('VITIMA', 'SUSPEITO', 'TESTEMUNHA') NOT NULL
);

CREATE TABLE ocorrencia_envolvido (
                                      ocorrencia_id BIGINT NOT NULL,
                                      envolvido_id BIGINT NOT NULL,
                                      PRIMARY KEY (ocorrencia_id, envolvido_id),
                                      FOREIGN KEY (ocorrencia_id) REFERENCES ocorrencia(id) ON DELETE CASCADE,
                                      FOREIGN KEY (envolvido_id) REFERENCES envolvido(id) ON DELETE CASCADE
);

CREATE TABLE atualizacao_ocorrencia (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        ocorrencia_id BIGINT NOT NULL,
                                        comentario TEXT NOT NULL,
                                        data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (ocorrencia_id) REFERENCES ocorrencia(id) ON DELETE CASCADE
);