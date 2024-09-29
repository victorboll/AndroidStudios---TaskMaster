package br.com.senac.taskmaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "listaDeTarefas.db";
    private static final int DB_VERSION = 1;

    private static final String CRIAR_TABELA =
            "CREATE TABLE IF NOT EXISTS tarefas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "descricao TEXT, " +
                    "prazo TEXT, " +
                    "prioridade TEXT " +
                    ")";

    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CRIAR_TABELA);
        inserirTarefasIniciais(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS tarefas");
        onCreate(db);
    }

    public void inserirTarefasIniciais(SQLiteDatabase db) {
        // Inserindo 20 tarefas iniciais
        for (int i = 1; i <= 20; i++) {
            String nome = "Tarefa " + i;
            String descricao = "Descrição da Tarefa " + i;
            String prazo = "2024-09-" + (i < 10 ? "0" + i : i);
            String prioridade;
            if (i % 3 == 0) {
                prioridade = "Alta";
            } else if (i % 3 == 1) {
                prioridade = "Baixa";
            } else {
                prioridade = "Média";
            }

            db.execSQL("INSERT INTO tarefas (nome, descricao, prazo, prioridade) " +
                    "VALUES ('" + nome + "', '" + descricao + "', '" + prazo + "', '" + prioridade + "')");
        }
    }

}
