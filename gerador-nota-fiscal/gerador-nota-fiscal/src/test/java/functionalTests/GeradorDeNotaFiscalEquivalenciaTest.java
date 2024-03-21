package functionalTests;

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
import static org.junit.jupiter.api.Assertions.fail;

public class GeradorDeNotaFiscalEquivalenciaTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testGerarNotaFiscalConsultoria() {
        // Preparação dos dados de entrada
        Fatura fatura = new Fatura("Maria", "Av. Presidente Costa e Silva", TipoServico.CONSULTORIA, 100.0);

        // Execução do método a ser testado
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);

        // Verificação do resultado esperado
        assertEquals("Maria", notaFiscal.getCliente());
        assertEquals(100.0, notaFiscal.getValor());
        assertEquals(25.0, notaFiscal.getImposto());
    }

    @Test
    public void testGerarNotaFiscalTreinamento() {
        // Preparação dos dados de entrada
        Fatura fatura = new Fatura("Eduarda", "Rua. Pedras de Diamante", TipoServico.TREINAMENTO, 100.0);

        // Execução do método a ser testado
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);

        // Verificação do resultado esperado
        assertEquals("Eduarda", notaFiscal.getCliente());
        assertEquals(TipoServico.TREINAMENTO, fatura.getTipoServico());
        assertEquals(100.0, notaFiscal.getValor());
        assertEquals(15.0, notaFiscal.getImposto());
    }

    @Test
    public void testGerarNotaFiscalOutro() {
        // Preparação dos dados de entrada
        Fatura fatura = new Fatura("José", "Rua. Alfredo Dantas", TipoServico.OUTRO, 100.0);

        // Execução do método a ser testado
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);

        // Verificação do resultado esperado
        assertEquals("José", notaFiscal.getCliente());
        assertEquals(TipoServico.OUTRO, fatura.getTipoServico());
        assertEquals(100.0, notaFiscal.getValor());
        assertEquals(6.0, notaFiscal.getImposto());
    }

    @Test
    public void testGerarNotaFiscalNomeNull() {
        // Preparação dos dados de entrada com nome do cliente como null
        Fatura fatura = new Fatura(null, "Rua. Eduardo Costa", TipoServico.TREINAMENTO, 20.0);

        // Execução do método a ser testado e verificação de exceção
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        try {
            NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
            // Se não lançar exceção, o teste falhará
            fail("Deveria ter lançado IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Sucesso: esperamos que uma exceção seja lançada
            assertEquals("Nome do cliente não pode ser nulo.", e.getMessage());
        }
    }

    @Test
    public void testGerarNotaFiscalValorNull() {
        Fatura fatura = new Fatura("Felipe", "Rua João Tavares", TipoServico.OUTRO, null);
        GeradorNotaFiscal gerador = new GeradorNotaFiscal();
        try {
            NotaFiscal notaFiscal = gerador.gerarNotaFiscal(fatura);
            fail("Deveria ter lançado IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Sucesso: esperamos que uma exceção seja lançada
            assertEquals("Valor da fatura não pode ser nulo.", e.getMessage());
        }
    }
}