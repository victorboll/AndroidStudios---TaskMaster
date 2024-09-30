package br.com.senac.taskmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TarefaDAO {

    private SQLiteDatabase db;
    private DBHandler dbHandler;

    public TarefaDAO(Context context){
        this.dbHandler = new DBHandler(context);
        db = dbHandler.getWritableDatabase();
    }

    public List<Tarefa> buscarTodasTarefas(){
        List<Tarefa> tarefas = new ArrayList<>();
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * FROM tarefas", null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
                String prazo = cursor.getString(cursor.getColumnIndex("prazo"));
                String prioridade = cursor.getString(cursor.getColumnIndex("prioridade"));
                String status = cursor.getString(cursor.getColumnIndex("status"));

                tarefas.add(new Tarefa(id, nome, descricao, prazo, prioridade, status));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tarefas;
    }

    public void inserirTarefa(String nome, String descricao, String prazo, String prioridade) {
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("descricao", descricao);
        valores.put("prazo", prazo);
        valores.put("prioridade", prioridade);
        valores.put("status", "Em Progresso");


        // Insere a tarefa no banco de dados
        db.insert("tarefas", null, valores);

        db.close();
    }

    public void inserirTarefasIniciais() {
        db.delete("tarefas", null, null);

        for (int i = 1; i <= 3; i++) {
            ContentValues valores = new ContentValues();
            valores.put("nome", "Tarefa " + i);
            valores.put("descricao", "Descrição da Tarefa " + i);
            valores.put("prazo", "2024-09-" + (i < 10 ? "0" + i : i));
            String prioridade;
            if (i % 3 == 0) {
                prioridade = "Alta";
            } else if (i % 3 == 1) {
                prioridade = "Baixa";
            } else {
                prioridade = "Normal";
            }
            valores.put("prioridade", prioridade);

            valores.put("status", "Em Progresso");

            db.insert("tarefas", null, valores);
        }

        db.close();
    }

    public List<Tarefa> ordenarTarefasPorData(List<Tarefa> tarefas) {
        Collections.sort(tarefas, new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa t1, Tarefa t2) {
                return t1.getPrazo().compareTo(t2.getPrazo());
            }
        });

        return tarefas;
    }

}
