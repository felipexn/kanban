package com.consumption.kaban.model;

import com.consumption.kaban.enums.TarefaStatusEnum;

import java.util.Date;

public  class Tarefa {
    protected int id;
    protected String titulo;
    protected String descricao;
    protected int projetoId;

    protected Boolean comPrazo;

    public int getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    protected Date prazo;

    protected TarefaStatusEnum status;

    public Tarefa(String titulo, String descricao, String estado) {
        this.titulo = titulo;
        this.descricao = descricao;

    }
    public void exebirDetalhes(){

    }

    public TarefaStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TarefaStatusEnum status) {
        this.status = status;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }


}