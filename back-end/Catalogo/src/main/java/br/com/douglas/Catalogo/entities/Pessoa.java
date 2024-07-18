package br.com.douglas.Catalogo.entities;


import jakarta.persistence.Id;

public class Pessoa {

    @Id
    private Long id;
    private String nome;


    public Pessoa() {
    }

    public Pessoa(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
