package functionalTests;

import org.example.GerenciadorTarefas;
import org.example.Prioridade;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GerenciadorTarefasEquivalenciaTest {

    @Test
    public void testCriarTarefaCamposValidos() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-06-25", Prioridade.ALTA);
        assertEquals("CRIADO COM SUCESSO", resultado);
    }

    @Test
    public void testCriarTarefaTituloVazio() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("", "Estudar python hoje a tarde", "2024-03-25", Prioridade.ALTA);
        assertEquals("ERRO AO CRIAR TAREFA", resultado);
    }

    @Test
    public void testCriarTarefaDescricaoVazia() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("Estudar python", "", "2024-03-25", Prioridade.ALTA);
        assertEquals("ERRO AO CRIAR TAREFA", resultado);
    }

    @Test
    public void testCriarTarefaDataInvalida() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-02-25", Prioridade.ALTA);
        assertEquals("ERRO AO CRIAR TAREFA", resultado);
    }

    @Test
    public void testCriarTarefaPrioridadeInvalida() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-03-25", null);
        assertEquals("ERRO AO CRIAR TAREFA", resultado);
    }

    @Test
    public void testAtualizarTarefaTituloValido() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-03-25", Prioridade.ALTA);
        gerenciador.atualizarTarefa(0, "Estudar Java", null, null, null);
        assertEquals("Estudar Java", gerenciador.getTarefaById(0).getTitulo());
    }

    @Test
    public void testAtualizarTarefaDescricaoValida() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-03-25", Prioridade.ALTA);
        gerenciador.atualizarTarefa(0, null, "Estudar Java hoje a tarde", null, null);
        assertEquals("Estudar Java hoje a tarde", gerenciador.getTarefaById(0).getDesc());
    }

    @Test
    public void testAtualizarTarefaDataValida() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-03-25", Prioridade.ALTA);
        gerenciador.atualizarTarefa(0, null, null, "2024-03-25", null);
        assertEquals("2024-03-25", gerenciador.getTarefaById(0).getDataVencimento());
    }

    @Test
    public void testAtualizarTarefaPrioridadeValida() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-03-25", Prioridade.ALTA);
        gerenciador.atualizarTarefa(0, null, null, null, Prioridade.MEDIA);
        assertEquals(Prioridade.MEDIA, gerenciador.getTarefaById(0).getPrioridade());
    }

    @Test
    public void testExcluirTarefaExistente() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-03-25", Prioridade.ALTA);
        String resultado = gerenciador.excluirTarefa(0);
        assertEquals(resultado, "EXCLUIDA COM SUCESSO");
    }

    @Test
    public void testExcluirTarefaInexistente() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.excluirTarefa(0);
        assertEquals(resultado, "ERRO AO EXCLUIR TAREFA");

    }

    @Test
    public void testExibirListaTarefasExistentes() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-03-25", Prioridade.ALTA);
        gerenciador.criarTarefa("Fazer compras", "Ir ao supermercado", "2024-03-25", Prioridade.BAIXA);
        assertEquals(2, gerenciador.exibirLista().size());
    }

    @Test
    public void testExibirListaTarefasVazia() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        assertTrue(gerenciador.exibirLista().isEmpty());
    }
}


