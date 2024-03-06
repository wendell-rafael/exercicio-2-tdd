import org.example.Fatura;
import org.example.GeradorNotaFiscal;
import org.example.NotaFiscal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeradorNotaFiscalTest {

    @Test
    public void testGerarNotaFiscalServicoConsultoria() {

        Fatura fatura = new Fatura("Cliente Teste", "Endereço Teste", "CONSULTORIA", 100.0);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);


        assertEquals("Cliente Teste", notaFiscal.getCliente());
        assertEquals(100.0, notaFiscal.getValor());
        assertEquals(25.0, notaFiscal.getImposto());
    }
}
