package br.com.senac.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<Tarefa> listaDeTarefas;

    public TarefaAdapter(List<Tarefa> listaDeTarefas){
        this.listaDeTarefas = listaDeTarefas;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarefa,parent,false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        Tarefa tarefa = listaDeTarefas.get(position);
        holder.nome.setText(tarefa.getNome());
        holder.prazo.setText(tarefa.getPrazo());
        holder.prioridade.setText(tarefa.getPrioridade());
    }

    @Override
    public int getItemCount(){
        return listaDeTarefas.size();
    }

    public void updateTarefas(List<Tarefa> novasTarefas) {
        this.listaDeTarefas = novasTarefas;
        notifyDataSetChanged();
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView nome, descricao, prazo, prioridade;

        public TarefaViewHolder(@NonNull View itemDaView){
            super(itemDaView);
            nome = itemDaView.findViewById(R.id.task_name);
            prazo = itemDaView.findViewById(R.id.task_deadline);
            prioridade = itemDaView.findViewById(R.id.task_priority);
        }
    }
}
