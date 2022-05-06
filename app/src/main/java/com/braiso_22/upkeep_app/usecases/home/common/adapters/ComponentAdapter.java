package com.braiso_22.upkeep_app.usecases.home.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Component;

import java.util.List;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.ComponentViewHolder> {
    // List of Components
    private final List<Component> values;
    // LayoutInflater
    private LayoutInflater inflater;
    // Context
    private final Context context;
    // OnComponentClickListener
    private OnComponentClickListener listener;

    // Constructor with values, context and listener, and inflater from context
    public ComponentAdapter(List<Component> values, Context context, OnComponentClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.values = values;
        this.context = context;
        this.listener = listener;
    }

    // GetItemCount
    @Override
    public int getItemCount() {
        return values.size();
    }
    // OnCreateViewHolder with inflater and parent
    @Override
    public ComponentAdapter.ComponentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.component_item, parent, false);
        return new ComponentViewHolder(view);
    }

    // OnBindViewHolder with holder and position
    @Override
    public void onBindViewHolder(ComponentAdapter.ComponentViewHolder holder, int position) {
        holder.bindData(values.get(position));
    }

    // Inner Interface OnComponentClickListener
    public interface OnComponentClickListener {
        void onComponentClick(Component component);
    }

    // Inner Class ComponentViewHolder
    public class ComponentViewHolder extends RecyclerView.ViewHolder {
        // Component
        private Component component;


        // Constructor with view
        public ComponentViewHolder(View view) {
            super(view);
        }

        // BindData with component
        public void bindData(Component component) {
            this.component = component;

        }

        @Override
        public String toString() {
            return super.toString() + " '" +  ""+ "'";
        }
    }


}
