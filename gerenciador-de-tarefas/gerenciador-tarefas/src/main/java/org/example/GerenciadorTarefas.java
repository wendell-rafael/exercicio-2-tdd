package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GerenciadorTarefas {
    private List<Tarefa> listaTarefas;

    public GerenciadorTarefas() {
        this.listaTarefas = new ArrayList<>();
    }

    public String criarTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        if (titulo == null || titulo.trim().isEmpty() || titulo.trim().isBlank() ||
                descricao == null || descricao.trim().isEmpty() || descricao.trim().isBlank() ||
                dataVencimento == null ||
                prioridade == null) {
            return "ERRO AO CRIAR TAREFA";
        }

        LocalDate dataVencimentoFormatada = LocalDate.parse(dataVencimento, DateTimeFormatter.ISO_LOCAL_DATE);

        LocalDate hoje = LocalDate.now();

        if (dataVencimentoFormatada.isBefore(hoje)) {
            return "ERRO AO CRIAR TAREFA";
        }

        Tarefa tarefa = new Tarefa(listaTarefas.size(), titulo, descricao, dataVencimento, prioridade);
        listaTarefas.add(tarefa);
        return "CRIADO COM SUCESSO";
    }

    public List<Tarefa> getTarefas() {
        return listaTarefas;
    }

    public Tarefa getTarefaById(int id) {
        if (id >= 0 && id < listaTarefas.size()) {
            return listaTarefas.get(id);
        } else {
            return null;
        }    }

    public String atualizarTarefa(int id, String novoTitulo, String novaDescricao, String novaDataVencimento, Prioridade novaPrioridade) {
        Tarefa tarefaAtualizada = getTarefaById(id);
        if (tarefaAtualizada == null){
            return "ERRO AO ATUALIZAR TAREFA";
        }
        atualizarAtributoSeNaoNulo(novoTitulo, tarefaAtualizada::setTitulo);
        atualizarAtributoSeNaoNulo(novaDescricao, tarefaAtualizada::setDesc);
        atualizarAtributoSeNaoNulo(novaDataVencimento, tarefaAtualizada::setDataVencimento);
        atualizarAtributoSeNaoNulo(novaPrioridade, tarefaAtualizada::setPrioridade);
        return "TAREFA ATUALIZADA";
    }

    public String excluirTarefa(int id) {
        if (!listaTarefas.isEmpty()) {
            if (isIndiceValido(id)) {
                listaTarefas.remove(id);
                return "EXCLUIDA COM SUCESSO";
            }

        }

        return "ERRO AO EXCLUIR TAREFA";

    }

    public List<Tarefa> exibirLista() {
        List<Tarefa> listaOrdenada = new ArrayList<>(listaTarefas);

        listaOrdenada.sort(
                Comparator.comparing(Tarefa::getDataVencimento)
                        .thenComparing(Tarefa::getPrioridade, Comparator.reverseOrder())
        );
        return listaOrdenada;
    }

    public void marcarPrioridade(int id, Prioridade prioridade) {
        if (isIndiceValido(id)) {
            Tarefa tarefa = getTarefaById(id);
            tarefa.setPrioridade(prioridade);
        }

    }

    private boolean isIndiceValido(int id) {
        return !(id < 0 || id > listaTarefas.size());
    }

    private <T> void atualizarAtributoSeNaoNulo(T novoValor, Consumer<T> setter) {
        if (novoValor != null) {
            setter.accept(novoValor);
        }
    }
}


