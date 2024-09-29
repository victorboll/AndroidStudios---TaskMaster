package br.com.senac.taskmaster;

public class Tarefa {
    private int id;
    private String nome;
    private String descricao;
    private String prazo;
    private String prioridade;

    public Tarefa(int id, String nome, String descricao, String prazo, String prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.prazo = prazo;
        this.prioridade = prioridade;
    }

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

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrazo() {
        return prazo;
    }
    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
