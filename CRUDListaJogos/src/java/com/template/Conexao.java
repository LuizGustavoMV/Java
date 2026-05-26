package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados PostgreSQL.
 * Implementa o padrão de fábrica simples para retornar instâncias de Connection.
 */
public class Conexao {

    // Configurações de acesso ao banco de dados
    private static final String URL = "jdbc:postgresql://localhost:5432/ListaJogos";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    /**
     * Estabelece uma conexão com o banco de dados.
     * @return Connection objeto de conexão ativo.
     * @throws RuntimeException em caso de erro na conexão.
     */
    public Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            // Lança uma exceção de tempo de execução com mensagem clara
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }
}