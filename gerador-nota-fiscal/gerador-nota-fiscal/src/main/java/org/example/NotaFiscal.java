package org.example;

public class NotaFiscal {
    private String cliente;
    private double valor;
    private double imposto;

    public NotaFiscal(String cliente, double valor, double imposto) {
        this.cliente = cliente;
        this.valor = valor;
        this.imposto = imposto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }
}