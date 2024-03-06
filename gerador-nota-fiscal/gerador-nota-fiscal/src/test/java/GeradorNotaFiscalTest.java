import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeradorNotaFiscalTest {

    @Test
    public void testGerarNotaFiscalServicoConsultoria() {

        Fatura fatura = new Fatura("Cliente Teste", "Endereço Teste", "CONSULTORIA", 100.0);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);


        assertEquals("Cliente Teste", notaFiscal.getNomeCliente());
        assertEquals(100.0, notaFiscal.getValor(), 100.0);
        assertEquals(250.0, notaFiscal.getImposto(),25.0);
    }
}
