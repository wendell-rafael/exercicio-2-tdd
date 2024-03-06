import org.example.GerenciadorTarefas;
import org.example.Prioridade;
import org.example.Tarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class GerenciadorTarefasTest {

    @Test
    public void testCriarTarefa() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String titulo = "Comprar mantimentos";
        String descricao = "Ir ao supermercado e comprar os itens necessários";
        String dataVencimento = "2024-03-15";
        Prioridade prioridade = Prioridade.MEDIA;

        gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);

        List<Tarefa> tarefas = gerenciador.getTarefas();
        Assertions.assertEquals(1, tarefas.size());

        Tarefa tarefaCriada = tarefas.getFirst();
        Assertions.assertEquals(titulo, tarefaCriada.getTitulo());
        Assertions.assertEquals(descricao, tarefaCriada.getDesc());
        Assertions.assertEquals(dataVencimento, tarefaCriada.getDataVencimento());
        Assertions.assertEquals(prioridade, tarefaCriada.getPrioridade());
    }

    @Test
    public void testAtualizarTarefa() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String titulo = "Estudar V&V";
        String descricao = "Estudar V&V hoje a tarde";
        String dataVencimento = "2024-03-06";
        Prioridade prioridade = Prioridade.ALTA;
        gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);

        String novoTitulo = "Estudar atal";
        String novaDescricao = "Como tem prova, estudar atal, na verdade";
        gerenciador.atualizarTarefa(novoTitulo, novaDescricao, null, null);
        Tarefa tarefaAtualizada = gerenciador.getTarefaById(1);

        Assertions.assertEquals(novoTitulo, tarefaAtualizada.getTitulo());
        Assertions.assertEquals(novaDescricao, tarefaAtualizada.getDesc());
        Assertions.assertEquals(dataVencimento, tarefaAtualizada.getDataVencimento());
        Assertions.assertEquals(prioridade, tarefaAtualizada.getPrioridade());
    }

    @Test
    public void testExcluirTarefa() {
    }

    @Test
    public void testOrdenarTarefas() {
    }

    @Test
    public void testMarcarPrioridade() {
    }
}