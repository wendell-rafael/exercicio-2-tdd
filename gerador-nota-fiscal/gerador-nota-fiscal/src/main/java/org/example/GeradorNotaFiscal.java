package org.example;

public class GeradorNotaFiscal {
    private NotaFiscalDao notaFiscalDao;
    private Smtp smtp;
    private SAP sap;

    public GeradorNotaFiscal() {
        this.notaFiscalDao = new NotaFiscalDao() ;
        this.smtp = new Smtp();
        this.sap = new SAP();
    }
    public NotaFiscal gerarNotaFiscal(Fatura fatura) {
        if (fatura.getCliente() == null || fatura.getCliente().isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo.");
        }
        if (fatura.getValor() == null) {
            throw new IllegalArgumentException("Valor da fatura não pode ser nulo.");
        }
        if (Double.isInfinite(fatura.getValor())) {
            throw new IllegalArgumentException("Valor da fatura não pode ser infinito.");
        }

        double imposto;
        switch (fatura.getTipoServico()) {
            case CONSULTORIA:
                imposto = 0.25 * fatura.getValor();
                break;
            case TREINAMENTO:
                imposto = fatura.getValor() * 0.15;
                break;
            default:
                imposto = 0.06 * fatura.getValor();
                break;
        }

        NotaFiscal notaFiscal = new NotaFiscal(fatura.getCliente(), fatura.getValor(), imposto);
        this.smtp.envia(notaFiscal);
        this.sap.envia(notaFiscal);
        this.notaFiscalDao.salva(notaFiscal);
        return notaFiscal;
    }


}