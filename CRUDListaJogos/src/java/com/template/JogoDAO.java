package com.template;

import com.template.Conexao;
import com.template.JogoDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Data Access Object (DAO) para a entidade Jogo.
 * Centraliza as operacoes de CRUD (Create, Read, Update, Delete) no banco de dados.
 */
public class JogoDAO {

    private final Conexao conexao;

    public JogoDAO() {
        this.conexao = new Conexao();
    }

    /**
     * Insere um novo jogo no banco de dados.
     * @param jogo Objeto JogoDTO contendo os dados para insercao.
     */
    public void cadastrarJogo(JogoDTO jogo) {
        String sql = "INSERT INTO jogo (titulo, genero, plataforma, preco) VALUES (?, ?, ?, ?)";

        // Try-with-resources garante o fechamento automatico da conexao e do statement
        try (Connection conn = conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jogo.getTitulo());
            ps.setString(2, jogo.getGenero());
            ps.setString(3, jogo.getPlataforma());
            ps.setDouble(4, jogo.getPreco());

            ps.execute();
            System.out.println("[SUCESSO] Jogo \"" + jogo.getTitulo() + "\" cadastrado com exito!");

        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao cadastrar o jogo: " + e.getMessage());
        }
    }

    /**
     * Recupera todos os jogos registrados no banco de dados.
     * @return List de JogoDTO contendo todos os registros encontrados.
     */
    public List<JogoDTO> listarJogos() {
        String sql = "SELECT * FROM jogo ORDER BY id ASC";
        List<JogoDTO> listaDeJogos = new ArrayList<>();

        try (Connection conn = conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                JogoDTO jogo = new JogoDTO();
                jogo.setId(rs.getInt("id"));
                jogo.setTitulo(rs.getString("titulo"));
                jogo.setGenero(rs.getString("genero"));
                jogo.setPlataforma(rs.getString("plataforma"));
                jogo.setPreco(rs.getDouble("preco"));
                listaDeJogos.add(jogo);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao listar os jogos: " + e.getMessage());
        }

        return listaDeJogos;
    }

    /**
     * Atualiza os dados de um jogo existente no banco.
     * @param jogo Objeto JogoDTO com os novos dados e o ID correspondente.
     */
    public void atualizarJogo(JogoDTO jogo) {
        String sql = "UPDATE jogo SET titulo = ?, genero = ?, plataforma = ?, preco = ? WHERE id = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jogo.getTitulo());
            ps.setString(2, jogo.getGenero());
            ps.setString(3, jogo.getPlataforma());
            ps.setDouble(4, jogo.getPreco());
            ps.setInt(5, jogo.getId());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("[SUCESSO] Jogo com ID " + jogo.getId() + " atualizado com exito!");
            } else {
                System.out.println("[AVISO] Nenhum jogo encontrado com o ID " + jogo.getId());
            }

        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao atualizar o jogo: " + e.getMessage());
        }
    }

    /**
     * Remove um jogo do banco de dados pelo seu ID.
     * @param id Identificador unico do jogo.
     */
    public void excluirJogo(int id) {
        String sql = "DELETE FROM jogo WHERE id = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("[SUCESSO] Jogo com ID " + id + " removido com exito!");
            } else {
                System.out.println("[AVISO] Nenhum jogo encontrado com o ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO] Falha ao excluir o jogo: " + e.getMessage());
        }
    }
}