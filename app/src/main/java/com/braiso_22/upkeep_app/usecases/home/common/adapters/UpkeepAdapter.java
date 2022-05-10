package com.braiso_22.upkeep_app.usecases.home.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Upkeep;

import java.util.List;

public class UpkeepAdapter extends RecyclerView.Adapter<UpkeepAdapter.UpkeepViewHolder> {

    private List<Upkeep> values;
    private LayoutInflater inflater;
    private final Context context;
    private OnUpkeepClickListener listener;

    public UpkeepAdapter(List<Upkeep> values, Context context, OnUpkeepClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    // getItemCount
    @Override
    public int getItemCount() {
        return values.size();
    }

    // onCreateViewHolder
    @Override
    public UpkeepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.upkeep_item, parent, false);
        return new UpkeepViewHolder(view);
    }

    // onBindViewHolder
    @Override
    public void onBindViewHolder(UpkeepViewHolder holder, int position) {
        holder.bindData(values.get(position));
    }

    public interface OnUpkeepClickListener {
        void onUpkeepClick(Upkeep upkeep);
    }

    public class UpkeepViewHolder extends RecyclerView.ViewHolder {
        // TextViews
        public final TextView id, date, hour;
        private Upkeep upkeep;

        public UpkeepViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idUpkeepText);
            date = itemView.findViewById(R.id.dateUpkeepText);
            hour = itemView.findViewById(R.id.hourUpkeepText);
        }

        public void bindData(Upkeep upkeep) {
            this.upkeep = upkeep;
            id.setText(String.valueOf(upkeep.getId()));
            date.setText(upkeep.getDate());
            hour.setText(upkeep.getHour());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUpkeepClick(upkeep);
                }
            });
        }

        @Override
        public String toString() {
            return "UpkeepViewHolder{" +
                    "id=" + id +
                    ", date=" + date +
                    ", hour=" + hour +
                    '}';
        }
    }
}
