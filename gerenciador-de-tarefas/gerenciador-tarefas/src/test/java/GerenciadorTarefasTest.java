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

        //Nesse caso, modifiquei apenas titulo e descrição
        String novoTitulo = "Estudar atal";
        String novaDescricao = "Como tem prova, estudar atal, na verdade";
        gerenciador.atualizarTarefa(0, novoTitulo, novaDescricao, null, null);
        Tarefa tarefaAtualizada = gerenciador.getTarefaById(0);


        Assertions.assertEquals(novoTitulo, tarefaAtualizada.getTitulo());
        Assertions.assertEquals(novaDescricao, tarefaAtualizada.getDesc());
        Assertions.assertEquals(dataVencimento, tarefaAtualizada.getDataVencimento());
        Assertions.assertEquals(prioridade, tarefaAtualizada.getPrioridade());
    }

    @Test
    public void testExcluirTarefa() {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        String titulo = "Estudar V&V";
        String descricao = "Estudar V&V hoje a tarde";
        String dataVencimento = "2024-03-06";
        Prioridade prioridade = Prioridade.ALTA;
        gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);
        Assertions.assertEquals(1, gerenciador.getTarefas().size());
        gerenciador.excluirTarefa(0);
        Assertions.assertEquals(0, gerenciador.getTarefas().size());
    }

    @Test
    public void testExibirLista() {
        // Arrange
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();

        gerenciador.criarTarefa("Estudar ATAL", "Preparar slides", "2024-03-15", Prioridade.MEDIA);
        gerenciador.criarTarefa("Estudar V&V", "Revisar conceitos", "2024-03-10", Prioridade.ALTA);
        gerenciador.criarTarefa("Estudar Rec Info", "Ler capítulo 5", "2024-03-20", Prioridade.BAIXA);

        // Act
        List<Tarefa> listaOrdenada = gerenciador.exibirLista();

        // Assert
        Assertions.assertEquals(3, listaOrdenada.size());

        Assertions.assertEquals(0, listaOrdenada.get(0).getDataVencimento().compareTo("2024-03-10"));
        Assertions.assertEquals(0, listaOrdenada.get(1).getDataVencimento().compareTo("2024-03-15"));
        Assertions.assertEquals(0, listaOrdenada.get(2).getDataVencimento().compareTo("2024-03-20"));

        Assertions.assertEquals(0, listaOrdenada.get(0).getPrioridade().compareTo(Prioridade.ALTA));
        Assertions.assertEquals(0, listaOrdenada.get(1).getPrioridade().compareTo(Prioridade.MEDIA));
        Assertions.assertEquals(0, listaOrdenada.get(2).getPrioridade().compareTo(Prioridade.BAIXA));
    }

    @Test
    public void testMarcarPrioridade() {
    }
}