package br.com.agenda_de_contatos.entidades;

public class Contatos {

    private int id;
    private  String nome;
    private String telefone;
    private String Correio_eletronico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCorreio_eletronico() {
        return Correio_eletronico;
    }

    public void setCorreio_eletronico(String correio_eletronico) {
        Correio_eletronico = correio_eletronico;
    }
}
