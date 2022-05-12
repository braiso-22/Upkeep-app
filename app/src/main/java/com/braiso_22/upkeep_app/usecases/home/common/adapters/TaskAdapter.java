package com.braiso_22.upkeep_app.usecases.home.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> values;
    private LayoutInflater inflater;
    private TaskAdapter.OnTaskClickListener listener;
    private Context context;

    // Constructor
    public TaskAdapter(List<Task> values, Context context, TaskAdapter.OnTaskClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    // Method to get the size of the list
    @Override
    public int getItemCount() {
        return values.size();
    }

    // Method to create the view holder
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    // Method to bind the data to the view holder
    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.bindData(values.get(position));
    }

    //Inner interface for the click listener
    public interface OnTaskClickListener {
        void onTaskClick(Task task);
    }

    // Inner class TaskViewHolder
    public class TaskViewHolder extends RecyclerView.ViewHolder {

        public TextView taskId;
        public TextView taskName;
        public TextView taskLength;
        public TextView taskDescription;
        private Task task;

        // Constructor
        public TaskViewHolder(View itemView) {
            super(itemView);
            taskId = itemView.findViewById(R.id.taskIdText);
            taskName = itemView.findViewById(R.id.taskNameText);
            taskLength = itemView.findViewById(R.id.taskLengthText);
            taskDescription = itemView.findViewById(R.id.taskDescriptionText);
        }

        // BindData method
        public void bindData(Task task) {
            this.task = task;
            taskId.setText(String.valueOf(task.getId()));
            taskName.setText(task.getName());
            taskLength.setText(String.valueOf(task.getLength()));
            taskDescription.setText(task.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTaskClick(task);
                }
            });
        }

        @Override
        public String toString() {
            return "TaskViewHolder{" + "taskId=" + taskId + ", taskLength=" + taskLength + ", taskDescription=" + taskDescription + '}';
        }
    }
}
