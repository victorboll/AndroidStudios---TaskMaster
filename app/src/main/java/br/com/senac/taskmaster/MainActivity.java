package br.com.senac.taskmaster;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private TarefaDAO tarefaDAO;
    private List<Tarefa> listaDeTarefas;
    private FloatingActionButton adicionarTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tarefaDAO = new TarefaDAO(this);

        tarefaDAO.inserirTarefasIniciais();

        listaDeTarefas = tarefaDAO.buscarTodasTarefas();
        listaDeTarefas = tarefaDAO.ordenarTarefasPorData(listaDeTarefas);

        tarefaAdapter = new TarefaAdapter(listaDeTarefas);
        recyclerView.setAdapter(tarefaAdapter);

        adicionarTarefa = findViewById(R.id.floatingActionButton);

        adicionarTarefa.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });
    }

    protected void onResume() {
        super.onResume();
        listaDeTarefas = tarefaDAO.buscarTodasTarefas();
        listaDeTarefas = tarefaDAO.ordenarTarefasPorData(listaDeTarefas);
        tarefaAdapter.updateTarefas(listaDeTarefas);
    }
}