package br.com.senac.taskmaster;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private DBHandler dbHandler;

    public TarefaDAO(Context context){
        this.dbHandler = new DBHandler(context);
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

                tarefas.add(new Tarefa(id, nome, descricao, prazo, prioridade));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tarefas;
    }

}
