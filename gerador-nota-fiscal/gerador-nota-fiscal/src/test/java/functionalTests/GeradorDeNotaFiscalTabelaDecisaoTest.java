package functionalTests;
import org.example.Fatura;
import org.example.GeradorNotaFiscal;
import org.example.NotaFiscal;
import org.example.TipoServico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class GeradorDeNotaFiscalTabelaDecisaoTest {

    @Test
    public void testCondicao1() {
        // Preparação dos dados de entrada com os critérios especificados
        Fatura fatura = new Fatura("Cliente", "Endereço", TipoServico.CONSULTORIA, 100.0);

        // Execução do método a ser testado
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);

        // Verificação do resultado esperado
        assertEquals("Cliente", notaFiscal.getCliente());
        assertEquals(TipoServico.CONSULTORIA, fatura.getTipoServico());
        assertEquals(100.0, notaFiscal.getValor(), 0.001);
        assertEquals(100.0 * 0.25, notaFiscal.getImposto(), 0.001);
    }
    @Test
    public void testCondicao2() {

        Fatura fatura = new Fatura("Cliente", "Endereço", TipoServico.TREINAMENTO, 100.0);


        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);


        assertEquals("Cliente", notaFiscal.getCliente());
        assertEquals(TipoServico.TREINAMENTO, fatura.getTipoServico());
        assertEquals(100.0, notaFiscal.getValor(), 0.001);
        assertEquals(100.0 * 0.15, notaFiscal.getImposto(), 0.001);
    }
    @Test
    public void testCondicao3() {
        Fatura fatura = new Fatura("Cliente", "Endereço", TipoServico.OUTRO, 100.0);

        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);

        assertEquals("Cliente", notaFiscal.getCliente());
        assertEquals(TipoServico.OUTRO, fatura.getTipoServico());
        assertEquals(100.0, notaFiscal.getValor(), 0.001);
        assertEquals(100.0 * 0.06, notaFiscal.getImposto(), 0.001);
    }

    @Test
    public void testCondicao4NomeClienteVazio() {
        try {
            Fatura fatura = new Fatura("", "Endereço", TipoServico.CONSULTORIA, 100.0);
            GeradorNotaFiscal gerador = new GeradorNotaFiscal();
            NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
            fail("Deveria ter lançado IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Esperado
        }
    }

    @Test
    public void testCondicao4NomeClienteNull() {
        try {
            Fatura fatura = new Fatura(null, "Endereço", TipoServico.CONSULTORIA, 100.0);
            GeradorNotaFiscal gerador = new GeradorNotaFiscal();
            NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
            fail("Deveria ter lançado IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Esperado
        }
    }

    @Test
    public void testCondicao5() {
        try {
            Fatura fatura = new Fatura("Cliente", "Endereço", null, 100.0);
            GeradorNotaFiscal gerador = new GeradorNotaFiscal();
            NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
            fail("Deveria ter lançado IllegalArgumentException");
        } catch (NullPointerException e) {
            // Esperado
        }
    }

    @Test
    public void testCondicao6ValorNull() {
        try {
            Fatura fatura = new Fatura("Cliente", "Endereço", TipoServico.OUTRO, null);
            GeradorNotaFiscal gerador = new GeradorNotaFiscal();
            NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
            fail("Deveria ter lançado IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Esperado
        }
    }


}
