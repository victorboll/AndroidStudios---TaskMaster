package br.com.senac.taskmaster;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText etTaskName, etTaskDescription, etTaskDeadline;
    private Spinner spinnerPriority;
    private Button btnCancel, btnSubmit;
    private TarefaDAO tarefaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTaskName = findViewById(R.id.et_task_name);
        etTaskDescription = findViewById(R.id.et_description);
        etTaskDeadline = findViewById(R.id.et_task_deadline);
        spinnerPriority = findViewById(R.id.spinner_priority);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSubmit = findViewById(R.id.btn_submit);

        tarefaDAO = new TarefaDAO(this);

        configurarSpinnerPrioridade();

        btnSubmit.setOnClickListener(view -> adicionarNovaTarefa());
        btnCancel.setOnClickListener(view -> finish());
    }

    private void adicionarNovaTarefa() {
        String nome = etTaskName.getText().toString();
        String descricao = etTaskDescription.getText().toString();
        String prazo = etTaskDeadline.getText().toString();
        String prioridade = spinnerPriority.getSelectedItem().toString();

        tarefaDAO.inserirTarefa(nome, descricao, prazo, prioridade);

        finish();
    }

    private void configurarSpinnerPrioridade() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.priority_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPriority.setAdapter(adapter);
    }
}