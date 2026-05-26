package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.awt.*;
import javafx.event.ActionEvent;

public class MainController {
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnCadrastar;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtPlataforma;
    @FXML
    private TextField txtPreco;
    @FXML
    private TableView<JogoDTO> tblUsuario;
    @FXML
    private TableColumn<JogoDTO, Integer> colId;
    @FXML
    private TableColumn<JogoDTO, String> colNome;
    @FXML
    private TableColumn<JogoDTO, String> colGenero;
    @FXML
    private TableColumn<JogoDTO, String> colPlataforma;
    @FXML
    private TableColumn<JogoDTO, Double> colPreco;

    @FXML
    private void initialize() {
        System.out.println("FXML loaded successfully!");
    }
    @FXML
    private void btnCadastrarAction(ActionEvent event) {
        String titulo = txtNome.getText();
        String genero = txtGenero.getText();
        String plataforma = txtPlataforma.getText();
        double preco = Double.parseDouble(txtPreco.getText().replace(",", "."));

        // Criando o objeto seguindo o padrão camelCase
        JogoDTO jogoDTO = new JogoDTO();
        jogoDTO.setTitulo(titulo);
        jogoDTO.setGenero(genero);
        jogoDTO.setPlataforma(plataforma);
        jogoDTO.setPreco(preco);

        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.cadastrarJogo(jogoDTO);

        JogoDTO.ListarJogos();
        limparCampos();
    }

    // 2. BOTÃO EDITAR (Puxar os dados da linha selecionada para os campos de texto)
    @FXML
    private void btnEditarAction(ActionEvent event) {
        JogoDTO jogoSelecionado = tabelaJogos.getSelectionModel().getSelectedItem();

        if (jogoSelecionado != null) {
            txtId.setText(String.valueOf(jogoSelecionado.getId()));
            txtNome.setText(jogoSelecionado.getTitulo());
            txtGenero.setText(jogoSelecionado.getGenero());
            txtPlataforma.setText(jogoSelecionado.getPlataforma());
            txtPreco.setText(String.valueOf(jogoSelecionado.getPreco()));
        }
    }

    // 3. BOTÃO SALVAR (Confirmar a alteração do jogo editado)
    @FXML
    private void btnSalvarAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String titulo = txtNome.getText();
        String genero = txtGenero.getText();
        String plataforma = txtPlataforma.getText();
        double preco = Double.parseDouble(txtPreco.getText().replace(",", "."));

        JogoDTO jogoDTO = new JogoDTO();
        jogoDTO.setId(id);
        jogoDTO.setTitulo(titulo);
        jogoDTO.setGenero(genero);
        jogoDTO.setPlataforma(plataforma);
        jogoDTO.setPreco(preco);

        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.atualizarJogo(jogoDTO);

        JogoDTO.ListarJogos();
        limparCampos();
    }

    // 4. BOTÃO DELETAR (Excluir o registro usando o ID do campo)
    @FXML
    private void btnDeletarAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());

        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.excluirJogo(id);

        JogoDTO.ListarJogos();
        limparCampos();
    }

    // Método utilitário para limpar o formulário
    private void limparCampos() {
        txtId.clear();
        txtNome.clear();
        txtGenero.clear();
        txtPlataforma.clear();
        txtPreco.clear();
    }
}
