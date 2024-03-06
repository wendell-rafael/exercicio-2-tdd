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