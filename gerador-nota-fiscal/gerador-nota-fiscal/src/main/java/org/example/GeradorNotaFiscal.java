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
        double imposto;
        switch (fatura.getTipoServico()) {
            case "CONSULTORIA":
                imposto = 0.25 * fatura.getValor();
                break;
            default:
                imposto = 0;
                break;
        }

        NotaFiscal notaFiscal = new NotaFiscal(fatura.getCliente(), fatura.getValor(), imposto);

        this.smtp.envia(notaFiscal);
        this.sap.envia(notaFiscal);
        this.notaFiscalDao.salva(notaFiscal);

        return notaFiscal;
    }


}