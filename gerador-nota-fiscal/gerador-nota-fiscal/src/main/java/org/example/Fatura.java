package org.example;

public class Fatura {
    private String cliente;
    private String endereco;
    private String tipoServico;
    private double valor;
//solucao
    public Fatura(String cliente, String endereco, String tipoServico, double valor) {
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

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}