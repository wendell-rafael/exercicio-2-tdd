package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

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
        if (id >= 0 && id < listaTarefas.size()) {
            Tarefa tarefaAtualizada = getTarefaById(id);

            if (novoTitulo != null) {
                tarefaAtualizada.setTitulo(novoTitulo);
            }

            if (novaDescricao != null) {
                tarefaAtualizada.setDesc(novaDescricao);
            }

            if (novaDataVencimento != null) {
                tarefaAtualizada.setDataVencimento(novaDataVencimento);
            }

            if (novaPrioridade != null) {
                tarefaAtualizada.setPrioridade(novaPrioridade);
            }
        } else {
            throw new IllegalArgumentException("Índice inválido para atualização de tarefa.");
        }
    }

    public void excluirTarefa(int id) {
        if (id >= 0 && id < listaTarefas.size()) {
            listaTarefas.remove(id);
        } else {
            throw new IllegalArgumentException("Índice inválido para exclusão de tarefa.");
        }
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
        if (id >= 0 && id < listaTarefas.size()) {
            Tarefa tarefa = getTarefaById(id);
            tarefa.setPrioridade(prioridade);
        } else {
            throw new IllegalArgumentException("Índice inválido para marcar prioridade da tarefa.");
        }
    }
}


