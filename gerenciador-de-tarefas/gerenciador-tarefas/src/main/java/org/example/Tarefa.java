package org.example;

public class Tarefa {
    private final int id;
    private String titulo;
    private String desc;
    private String dataVencimento;
    private Prioridade prioridade;

    public Tarefa(int id, String titulo, String desc, String dataVencimento, Prioridade prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.desc = desc;
        this.dataVencimento = dataVencimento;
        this.prioridade = prioridade;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDesc() {
        return desc;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", desc='" + desc + '\'' +
                ", dataVencimento='" + dataVencimento + '\'' +
                ", prioridade=" + prioridade +
                '}';
    }
}
