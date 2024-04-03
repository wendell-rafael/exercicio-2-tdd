package junit5Tests;

import org.example.GerenciadorTarefas;
import org.example.Prioridade;
import org.example.Tarefa;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GerenciadorTarefasTest5 {

    private GerenciadorTarefas gerenciador;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Configuração inicial antes de todos os testes");
    }

    @BeforeEach
    void setUp() {
        gerenciador = new GerenciadorTarefas();
        System.out.println("Configuração inicial antes de cada teste");
    }

    @AfterEach
    void tearDown() {
        gerenciador = null;
        System.out.println("Limpeza após cada teste");
    }

    @Nested
    @DisplayName("Testes para criar tarefa")
    class CriarTarefaTest {

        @Test
        @DisplayName("Deve criar uma nova tarefa")
        void deveCriarTarefa() {
            // Arrange
            String titulo = "Comprar mantimentos";
            String descricao = "Ir ao supermercado e comprar os itens necessários";
            String dataVencimento = "2024-06-15";
            Prioridade prioridade = Prioridade.MEDIA;

            // Act
            String resultado = gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);

            // Assert
            assertEquals("CRIADO COM SUCESSO", resultado);
            List<Tarefa> tarefas = gerenciador.getTarefas();
            assertEquals(1, tarefas.size());
            Tarefa tarefaCriada = tarefas.getFirst();
            assertEquals(titulo, tarefaCriada.getTitulo());
            assertEquals(descricao, tarefaCriada.getDesc());
            assertEquals(dataVencimento, tarefaCriada.getDataVencimento());
            assertEquals(prioridade, tarefaCriada.getPrioridade());
        }
    }

    @Nested
    @DisplayName("Testes para atualizar tarefa")
    class AtualizarTarefaTest {

        @Test
        @DisplayName("Deve atualizar uma tarefa existente")
        void deveAtualizarTarefa() {
            // Arrange
            String titulo = "Estudar V&V";
            String descricao = "Estudar V&V hoje a tarde";
            String dataVencimento = "2024-06-15";
            Prioridade prioridade = Prioridade.ALTA;
            gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);

            // Act
            String novoTitulo = "Estudar atal";
            String novaDescricao = "Como tem prova, estudar atal, na verdade";
            gerenciador.atualizarTarefa(0, novoTitulo, novaDescricao, null, null);

            // Assert
            Tarefa tarefaAtualizada = gerenciador.getTarefaById(0);
            assertEquals(novoTitulo, tarefaAtualizada.getTitulo());
            assertEquals(novaDescricao, tarefaAtualizada.getDesc());
            assertEquals(dataVencimento, tarefaAtualizada.getDataVencimento());
            assertEquals(prioridade, tarefaAtualizada.getPrioridade());
        }
    }

    @Nested
    @DisplayName("Testes para excluir tarefa")
    class ExcluirTarefaTest {

        @Test
        @DisplayName("Deve excluir uma tarefa existente")
        void deveExcluirTarefa() {
            // Arrange
            String titulo = "Estudar V&V";
            String descricao = "Estudar V&V hoje a tarde";
            String dataVencimento = "2024-06-15";
            Prioridade prioridade = Prioridade.ALTA;
            gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);

            // Act
            String resultado = gerenciador.excluirTarefa(0);

            // Assert
            assertEquals("EXCLUIDA COM SUCESSO", resultado);
            Assertions.assertTrue(gerenciador.getTarefas().isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes para exibir lista")
    class ExibirListaTest {

        @Test
        @DisplayName("Deve exibir a lista de tarefas ordenada")
        void deveExibirListaOrdenada() {
            // Arrange
            gerenciador.criarTarefa("Estudar ATAL", "Preparar slides", "2024-06-15", Prioridade.BAIXA);
            gerenciador.criarTarefa("Estudar CDP", "Revisar slides", "2024-06-15", Prioridade.ALTA);
            gerenciador.criarTarefa("Estudar V&V", "Revisar conceitos", "2024-06-10", Prioridade.ALTA);
            gerenciador.criarTarefa("Estudar Rec Info", "Ler capítulo 5", "2024-06-20", Prioridade.MEDIA);

            // Act
            List<Tarefa> listaOrdenada = gerenciador.exibirLista();

            // Assert
            assertEquals("Estudar V&V", listaOrdenada.get(0).getTitulo());
            assertEquals("Estudar CDP", listaOrdenada.get(1).getTitulo());
            assertEquals("Estudar ATAL", listaOrdenada.get(2).getTitulo());
            assertEquals("Estudar Rec Info", listaOrdenada.get(3).getTitulo());
        }
    }

    @Nested
    @DisplayName("Testes para marcar prioridade")
    class MarcarPrioridadeTest {

        @Test
        @DisplayName("Deve marcar a prioridade de uma tarefa")
        void deveMarcarPrioridade() {
            // Arrange
            gerenciador.criarTarefa("Estudar ATAL", "Preparar slides", "2024-06-15", Prioridade.MEDIA);
            gerenciador.criarTarefa("Estudar V&V", "Revisar conceitos", "2024-06-10", Prioridade.ALTA);
            gerenciador.criarTarefa("Estudar Rec Info", "Ler capítulo 5", "2024-06-20", Prioridade.BAIXA);

            // Act
            gerenciador.marcarPrioridade(0, Prioridade.ALTA);
            gerenciador.marcarPrioridade(1, Prioridade.BAIXA);
            gerenciador.marcarPrioridade(2, Prioridade.ALTA);

            // Assert
            assertEquals(Prioridade.ALTA, gerenciador.getTarefaById(0).getPrioridade());
            assertEquals(Prioridade.BAIXA, gerenciador.getTarefaById(1).getPrioridade());
            assertEquals(Prioridade.ALTA, gerenciador.getTarefaById(2).getPrioridade());
        }
    }


    @Nested
    @DisplayName("Testes para criar tarefa")
    class CriarTarefaTestUsingCsvAndParameter {

        @ParameterizedTest(name = "Testando criação de tarefa com os parâmetros: titulo={0}, descricao={1}, dataVencimento={2}, prioridade={3}")
        @CsvSource({
                "Comprar mantimentos, Ir ao supermercado e comprar os itens necessários, 2024-06-15, MEDIA",
                "Estudar programação, Resolver exercícios de Java, 2024-06-20, ALTA",
                "Passear com o cachorro, Dar uma volta no parque, 2024-06-10, BAIXA"
        })
        void deveCriarTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
            // Act
            String resultado = gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);

            // Assert
            assertEquals("CRIADO COM SUCESSO", resultado);
            List<Tarefa> tarefas = gerenciador.getTarefas();
            assertEquals(1, tarefas.size());
            Tarefa tarefaCriada = tarefas.getFirst();
            assertEquals(titulo, tarefaCriada.getTitulo());
            assertEquals(descricao, tarefaCriada.getDesc());
            assertEquals(dataVencimento, tarefaCriada.getDataVencimento());
            assertEquals(prioridade, tarefaCriada.getPrioridade());
        }
    }


    @Nested
    @DisplayName("Testes para excluir tarefa")
    class ExcluirTarefaTestUsingCsvAndParameter {

        @ParameterizedTest(name = "Testando exclusão de tarefa com o índice: {0}")
        @CsvSource({
                "0",
        })
        void deveExcluirTarefa(int indice) {
            // Arrange
            String titulo = "Estudar V&V";
            String descricao = "Estudar V&V hoje a tarde";
            String dataVencimento = "2024-06-06";
            Prioridade prioridade = Prioridade.ALTA;
            gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);

            String resultado = gerenciador.excluirTarefa(indice);

            assertEquals("EXCLUIDA COM SUCESSO", resultado);
            Assertions.assertTrue(gerenciador.getTarefas().isEmpty());
        }
    }


    @Nested
    @DisplayName("Testes para manipulação de datas")
    class ManipulacaoDatasTest {

        @Test
        @DisplayName("Deve criar tarefa com data de vencimento no passado")
        void deveCriarTarefaComDataNoPassado() {
            String titulo = "Tarefa com data passada";
            String descricao = "Descrição da tarefa com data passada";
            String dataVencimento = "2020-01-01";
            Prioridade prioridade = Prioridade.BAIXA;

            String resultado = gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);
            assertEquals("ERRO AO CRIAR TAREFA", resultado);
        }

        @Test
        @DisplayName("Deve criar tarefa com data de vencimento no futuro")
        void deveCriarTarefaComDataNoFuturo() {
            String titulo = "Tarefa com data futura";
            String descricao = "Descrição da tarefa com data futura";
            String dataVencimento = "2030-01-01";
            Prioridade prioridade = Prioridade.MEDIA;

            String resultado = gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);
            assertEquals("CRIADO COM SUCESSO", resultado);
        }
    }


}

