package functionalTests;

import org.example.Fatura;
import org.example.GeradorNotaFiscal;
import org.example.NotaFiscal;
import org.example.TipoServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GeradorDeNotaFiscalValoresLimitesTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testGerarNotaFiscalValorMinimo() {
        Fatura fatura = new Fatura("Maria", "Av. Presidente Costa e Silva", TipoServico.CONSULTORIA, 0.0);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
        assertEquals("Maria", notaFiscal.getCliente());
        assertEquals(0.0, notaFiscal.getValor(), 0.001);
        assertEquals(0.0, notaFiscal.getImposto(), 0.001);
    }
    @Test
    public void testGerarNotaFiscalValorMinimoMaisUmCentavo() {
        Fatura fatura = new Fatura("Eduarda", "Rua. Pedras de Diamante", TipoServico.OUTRO, 0.01);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
        assertEquals("Eduarda", notaFiscal.getCliente());
        assertEquals(0.01, notaFiscal.getValor(), 0.001);
        assertEquals(0.0006, notaFiscal.getImposto(), 0.0001);
    }

    @Test
    public void testGerarNotaFiscalValorQualquer() {

        Fatura fatura = new Fatura("José", "Rua. Alfredo Dantas", TipoServico.OUTRO, 10000.0);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
        assertEquals("José", notaFiscal.getCliente());
        assertEquals(10000.0, notaFiscal.getValor(), 0.001);
        assertEquals(600.0, notaFiscal.getImposto(), 0.001);
    }

    @Test
    public void testGerarNotaFiscalValorMaximo() {

        Fatura fatura = new Fatura("José", "Rua. Eduardo Costa", TipoServico.TREINAMENTO, Double.POSITIVE_INFINITY);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        try {
            NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
            fail("Deveria ter lançado IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Valor da fatura não pode ser infinito.", e.getMessage());
        }
    }

    @Test
    public void testGerarNotaFiscalValorMaximoMenosUmCentavo() {
        Fatura fatura = new Fatura("Monica", "Rua. Severino Costa", TipoServico.OUTRO, Double.MAX_VALUE - 0.01);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
        assertEquals("Monica", notaFiscal.getCliente());
        assertEquals(TipoServico.OUTRO, fatura.getTipoServico());
        assertEquals(Double.MAX_VALUE - 0.01, notaFiscal.getValor(), 0.001); // Usamos 0.001 como margem de erro para lidar com comparações de ponto flutuante
        assertEquals((Double.MAX_VALUE - 0.01) * 0.06, notaFiscal.getImposto(), 0.001); // Usamos 0.001 como margem de erro para lidar com comparações de ponto flutuante
    }
}

