package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.function.Consumer;

public class GerenciadorTarefas {
    private List<Tarefa> listaTarefas;

    public GerenciadorTarefas() {
        this.listaTarefas = new ArrayList<>();
    }

    public void criarTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        Tarefa tarefa = new Tarefa(listaTarefas.size(), titulo, descricao, dataVencimento, prioridade);
        listaTarefas.add(tarefa);
    }

    public List<Tarefa> getTarefas() {
        return listaTarefas;
    }

    public Tarefa getTarefaById(int id) {
        return listaTarefas.get(id);
    }

    public void atualizarTarefa(int id, String novoTitulo, String novaDescricao, String novaDataVencimento, Prioridade novaPrioridade) {
        isIndiceValido(id);

        Tarefa tarefaAtualizada = getTarefaById(id);

        atualizarAtributoSeNaoNulo(novoTitulo, tarefaAtualizada::setTitulo);
        atualizarAtributoSeNaoNulo(novaDescricao, tarefaAtualizada::setDesc);
        atualizarAtributoSeNaoNulo(novaDataVencimento, tarefaAtualizada::setDataVencimento);
        atualizarAtributoSeNaoNulo(novaPrioridade, tarefaAtualizada::setPrioridade);
    }

    public void excluirTarefa(int id) {
        isIndiceValido(id);
        listaTarefas.remove(id);
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
        isIndiceValido(id);
        Tarefa tarefa = getTarefaById(id);
        tarefa.setPrioridade(prioridade);

    }

    private void isIndiceValido(int id) {
        if (id < 0 || id > listaTarefas.size()) {
            throw new IllegalArgumentException("Índice inválido para marcar prioridade da tarefa.");
        }

    }

    private <T> void atualizarAtributoSeNaoNulo(T novoValor, Consumer<T> setter) {
        if (novoValor != null) {
            setter.accept(novoValor);
        }
    }
}


