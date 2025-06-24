package com.consumption.kaban.dao;


import com.consumption.kaban.model.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class TarefaDAO {
    private Connection conn;

    public TarefaDAO(Connection conn) {
        this.conn = conn;
    }

    // Salvar nova tarefa vinculada a um projeto
    public void salvar(Tarefa tarefa, int projetoId) throws SQLException {
        String sql = "INSERT INTO tarefa (titulo, descricao, estado, projetoId) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tarefa.getTitulo());
        stmt.setString(2, tarefa.getDescricao());
        stmt.setString(3, tarefa.getStatus().name()); // salva como "TODO", "IN_PROGRESS", etc
        stmt.setInt(4, tarefa.getProjetoId());
        stmt.executeUpdate();
    }

    // Listar tarefas de um projeto
    public List<Tarefa> buscarPorProjeto(int idProjeto) throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE id_projeto = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idProjeto);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Tarefa tarefa = new Tarefa(
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    valueOf(rs.getString("status")) // converte string → enum
            );
            tarefa.setId(rs.getInt("id"));
            tarefas.add(tarefa);
        }

        return tarefas;
    }

    // Atualizar estado e descrição da tarefa
    public void atualizar(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE tarefa SET titulo = ?, descricao = ?, estado = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tarefa.getTitulo());
        stmt.setString(2, tarefa.getDescricao());
        stmt.setString(3, tarefa.getStatus().name());
        stmt.setInt(4, tarefa.getId());
        stmt.executeUpdate();
    }

    // Excluir tarefa
    public void excluir(int idTarefa) throws SQLException {
        String sql = "DELETE FROM tarefa WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idTarefa);
        stmt.executeUpdate();
    }
}
