package com.example.diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.RcViewHolder> {
    ArrayList<Task> mtasks;
    public static class RcViewHolder extends RecyclerView.ViewHolder{
        TextView mtextview;
        CheckBox mcheckBox;
        public RcViewHolder(@NonNull View itemView) {
            super(itemView);
            mtextview = itemView.findViewById(R.id.textView);
            mcheckBox = itemView.findViewById(R.id.checkBox2);

        }
    }

    public RcAdapter(ArrayList<Task> tasks)
    {
        mtasks = tasks;
    }



    @NonNull
    @Override
    public RcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View TaskView = inflater.inflate(R.layout.task_card,parent,false);
        return(new RcViewHolder(TaskView));
    }

    @Override
    public void onBindViewHolder(@NonNull RcViewHolder holder, int position) {
        Task task = mtasks.get(position);
        TextView textView = holder.mtextview;
        CheckBox checkBox = holder.mcheckBox;
        textView.setText(String.valueOf(position+1)+". "+task.getTaskName());
        checkBox.setChecked(task.getCompleted());

    }

    @Override
    public int getItemCount() {
        return mtasks.size();
    }
}
