package br.com.senac.taskmaster;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private TarefaDAO tarefaDAO;
    private List<Tarefa> listaDeTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tarefaDAO = new TarefaDAO(this);

        listaDeTarefas = tarefaDAO.buscarTodasTarefas();

        tarefaAdapter = new TarefaAdapter(listaDeTarefas);
        recyclerView.setAdapter(tarefaAdapter);
    }
}