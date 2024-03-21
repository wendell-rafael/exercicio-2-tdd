import org.example.Fatura;
import org.example.GeradorNotaFiscal;
import org.example.NotaFiscal;
import org.example.TipoServico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeradorNotaFiscalTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testGerarNotaFiscalServicoConsultoria() {
        Fatura fatura = new Fatura("Cliente Teste", "Endereço Teste", TipoServico.CONSULTORIA, 100.0);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
        assertEquals("Cliente Teste", notaFiscal.getCliente());
        assertEquals(100.0, notaFiscal.getValor());
        assertEquals(25.0, notaFiscal.getImposto());
    }
    @Test
    public void testGerarNotaFiscalParaServicoDeTreinamento(){
        Fatura fatura = new Fatura("Cliente Teste", "Endereço Teste", TipoServico.TREINAMENTO, 100.0);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal nf = gerador.gerarNotaFiscal(fatura);
        assertEquals("Cliente Teste", nf.getCliente());
        assertEquals(100.0, nf.getValor());
        assertEquals(15.0, nf.getImposto());
    }
    @Test
    public void testGerarNotaFiscalParaServicoOutro(){
        Fatura fatura = new Fatura("Cliente Teste", "Endereço Teste", TipoServico.OUTRO, 100.0);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal nf = gerador.gerarNotaFiscal(fatura);
        assertEquals("Cliente Teste", nf.getCliente());
        assertEquals(100.0, nf.getValor());
        assertEquals(6.0, nf.getImposto());
    }
    @Test
    public void testVerificarEnviosESalvamentoAposGerarNotaFiscal() {
        Fatura fatura = new Fatura("Cliente Teste", "Endereço Teste", TipoServico.OUTRO, 100.0);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        gerador.gerarNotaFiscal(fatura);
        String[] linhasDeSaida = outputStreamCaptor.toString().split(System.lineSeparator());
        assertEquals("enviando por email", linhasDeSaida[0].trim());
        assertEquals("enviando pro sap", linhasDeSaida[1].trim());
        assertEquals("salvando no banco", linhasDeSaida[2].trim());
    }
}