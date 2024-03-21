package functionalTests;

import org.example.GerenciadorTarefas;
import org.example.Prioridade;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GerenciadorTarefasTabelaDecisaoTest {

    @Test
    public void testCriarTarefaTituloDescricaoDataPrioridadeValidosTarefaCadastrada() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-06-25", Prioridade.ALTA);
        assertEquals("CRIADO COM SUCESSO", resultado);
    }

    @Test
    public void testCriarTarefaTituloInvalido() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("", "Estudar python hoje a tarde", "2024-06-25", Prioridade.ALTA);
        assertEquals("ERRO AO CRIAR TAREFA", resultado);
    }

    @Test
    public void testCriarTarefaDescricaoInvalida() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("Estudar python", "", "2024-06-25", Prioridade.ALTA);
        assertEquals("ERRO AO CRIAR TAREFA", resultado);
    }

    @Test
    public void testCriarTarefaDataInvalida() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2023-06-25", Prioridade.ALTA);
        assertEquals("ERRO AO CRIAR TAREFA", resultado);
    }

    @Test
    public void testCriarTarefaPrioridadeInvalida() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-06-25", null);
        assertEquals("ERRO AO CRIAR TAREFA", resultado);
    }

    @Test
    public void testAtualizarTarefaTodasAtributosValidosTarefaCadastrada() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-06-25", Prioridade.ALTA);
        String resultado = gerenciador.atualizarTarefa(0, "Estudar Java", "Estudar Java hoje de manhã", "2024-07-25", Prioridade.MEDIA);
        assertEquals("TAREFA ATUALIZADA", resultado);
        assertEquals("Estudar Java", gerenciador.getTarefaById(0).getTitulo());
        assertEquals("Estudar Java hoje de manhã", gerenciador.getTarefaById(0).getDesc());
        assertEquals("2024-07-25", gerenciador.getTarefaById(0).getDataVencimento());
        assertEquals(Prioridade.MEDIA, gerenciador.getTarefaById(0).getPrioridade());
    }

    @Test
    public void testAtualizarTarefaTituloValidoDescricaoValidaTarefaCadastrada() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-06-25", Prioridade.ALTA);
        String resultado = gerenciador.atualizarTarefa(0, "Estudar Java", "Estudar Java hoje de manhã", null, null);
        assertEquals("TAREFA ATUALIZADA", resultado);
        assertEquals("Estudar Java", gerenciador.getTarefaById(0).getTitulo());
        assertEquals("Estudar Java hoje de manhã", gerenciador.getTarefaById(0).getDesc());
        assertEquals("2024-06-25", gerenciador.getTarefaById(0).getDataVencimento());
        assertEquals(Prioridade.ALTA, gerenciador.getTarefaById(0).getPrioridade());
    }

    @Test
    public void testAtualizarTarefaTituloValidoTarefaCadastrada() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-06-25", Prioridade.ALTA);
        String resultado = gerenciador.atualizarTarefa(0, "Estudar Java", null, null, null);
        assertEquals("TAREFA ATUALIZADA", resultado);
        assertEquals("Estudar Java", gerenciador.getTarefaById(0).getTitulo());
        assertEquals("Estudar python hoje a tarde", gerenciador.getTarefaById(0).getDesc());
        assertEquals("2024-06-25", gerenciador.getTarefaById(0).getDataVencimento());
        assertEquals(Prioridade.ALTA, gerenciador.getTarefaById(0).getPrioridade());
    }

    @Test
    public void testAtualizarTarefaErroAtualizarTarefa() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.atualizarTarefa(0, "Estudar Java", null, null, null); // Tentativa de atualizar tarefa inexistente
        assertEquals("ERRO AO ATUALIZAR TAREFA", resultado);
    }

    @Test
    public void testExcluirTarefaTarefaCadastrada() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        gerenciador.criarTarefa("Estudar python", "Estudar python hoje a tarde", "2024-06-25", Prioridade.ALTA);
        String resultado = gerenciador.excluirTarefa(0);
        assertEquals("EXCLUIDA COM SUCESSO", resultado);
        assertTrue(gerenciador.getTarefas().isEmpty());
    }

    @Test
    public void testExcluirTarefaErroExcluirTarefa() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String resultado = gerenciador.excluirTarefa(0);
        assertEquals("ERRO AO EXCLUIR TAREFA", resultado);
    }
}

