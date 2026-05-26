package com.template;

public class JogoDTO {
    // Atributos privados para garantir o encapsulamento
    private int id;
    private String titulo;
    private String genero;
    private String plataforma;
    private double preco;

    // Construtor padrão (necessário para inicialização em branco)
    public JogoDTO() {}

    // Construtor completo para facilitar criações rápidas
    public JogoDTO(int id, String titulo, String genero, String plataforma, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.preco = preco;
    }

    // Métodos Getters e Setters para acesso e modificação controlada
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Sobrescrita do toString para facilitar a exibição rápida do objeto.
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Titulo: %-30s | Genero: %-15s | Plataforma: %-15s | Preco: R$ %.2f",
                id, titulo, genero, plataforma, preco);
    }
}
