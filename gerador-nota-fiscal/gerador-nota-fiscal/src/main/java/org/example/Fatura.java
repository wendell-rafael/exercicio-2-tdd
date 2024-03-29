package org.example;

public class Fatura {
    private String cliente;
    private String endereco;
    private TipoServico tipoServico;
    private Double valor;

    public Fatura(String cliente, String endereco, TipoServico tipoServico, Double valor) {
        this.cliente = cliente;
        this.endereco = endereco;
        this.tipoServico = tipoServico;
        this.valor = valor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}