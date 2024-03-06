package org.example;

import java.util.ArrayList;
import java.util.List;

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
}
