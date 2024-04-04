package junit5Tests;

import org.example.Fatura;
import org.example.GeradorNotaFiscal;
import org.example.NotaFiscal;
import org.example.TipoServico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NotaFiscalJunit5Test {

    GeradorNotaFiscal gerador;
    @BeforeEach
    void setUp() {
        this.gerador = new GeradorNotaFiscal();
        System.out.println("Configuração inicial antes de cada teste");
    }
    @AfterEach
    void tearDown() {
        this.gerador = null;
        System.out.println("Limpeza após cada teste");
    }

    @Test
    @DisplayName("Gerar nota fiscal para serviço de consultoria")
    public void testGerarNotaFiscalServicoConsultoria() {
        Fatura fatura = new Fatura("Cliente Teste", "Endereço Teste", TipoServico.CONSULTORIA, 100.0);
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
        assertEquals("Cliente Teste", notaFiscal.getCliente());
        assertEquals(100.0, notaFiscal.getValor());
        assertEquals(25.0, notaFiscal.getImposto());
    }

    @Test
    @DisplayName("Gerar nota fiscal para serviço de treinamento")
    public void testGerarNotaFiscalParaServicoDeTreinamento(){
        Fatura fatura = new Fatura("Cliente Teste", "Endereço Teste", TipoServico.TREINAMENTO, 100.0);
        NotaFiscal nf = gerador.gerarNotaFiscal(fatura);
        assertEquals("Cliente Teste", nf.getCliente());
        assertEquals(100.0, nf.getValor());
        assertEquals(15.0, nf.getImposto());
    }

    @Test
    @DisplayName("Gerar nota fiscal para outro serviço")
    public void testGerarNotaFiscalParaServicoOutro(){
        Fatura fatura = new Fatura("Cliente Teste", "Endereço Teste", TipoServico.OUTRO, 100.0);
        NotaFiscal nf = gerador.gerarNotaFiscal(fatura);
        assertEquals("Cliente Teste", nf.getCliente());
        assertEquals(100.0, nf.getValor());
        assertEquals(6.0, nf.getImposto());
    }

    @ParameterizedTest
    @CsvSource({"Maria, Av. Presidente Costa e Silva, CONSULTORIA, 0.0, Maria, 0.0, 0.0",
            "Eduarda, Rua. Pedras de Diamante, OUTRO, 0.01, Eduarda, 0.01, 0.0006",
            "José, Rua. Alfredo Dantas, OUTRO, 10000.0, José, 10000.0, 600.0"})
    @DisplayName("Teste para diferentes valores de fatura")
    public void testGerarNotaFiscalValoresLimite(String cliente, String endereco, TipoServico tipoServico, double valor, String expectedCliente, double expectedValor, double expectedImposto) {
        Fatura fatura = new Fatura(cliente, endereco, tipoServico, valor);
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
        assertEquals(expectedCliente, notaFiscal.getCliente());
        assertEquals(expectedValor, notaFiscal.getValor(), 0.001);
        assertEquals(expectedImposto, notaFiscal.getImposto(), 0.001);
    }

    @Test
    @DisplayName("Teste para valor de fatura máximo")
    public void testGerarNotaFiscalValorMaximo() {
        Fatura fatura = new Fatura("José", "Rua. Eduardo Costa", TipoServico.TREINAMENTO, Double.POSITIVE_INFINITY);
        try {
            gerador.gerarNotaFiscal(fatura);
            fail("Deveria ter lançado IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Valor da fatura não pode ser infinito.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste para valor de fatura máximo menos um centavo")
    public void testGerarNotaFiscalValorMaximoMenosUmCentavo() {
        Fatura fatura = new Fatura("Monica", "Rua. Severino Costa", TipoServico.OUTRO, Double.MAX_VALUE - 0.01);
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
        assertEquals("Monica", notaFiscal.getCliente());
        assertEquals(TipoServico.OUTRO, fatura.getTipoServico());
        assertEquals(Double.MAX_VALUE - 0.01, notaFiscal.getValor(), 0.001);
        assertEquals((Double.MAX_VALUE - 0.01) * 0.06, notaFiscal.getImposto(), 0.001);
    }
}
