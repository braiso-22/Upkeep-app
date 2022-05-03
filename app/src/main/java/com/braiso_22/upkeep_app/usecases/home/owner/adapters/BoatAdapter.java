package com.braiso_22.upkeep_app.usecases.home.owner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Boat;

import java.util.List;

public class BoatAdapter extends RecyclerView.Adapter<BoatAdapter.BoatViewHolder> {

    private List<Boat> values;
    private LayoutInflater inflater;
    private Context context;
    private OnBoatClickListener listener;

    public interface OnBoatClickListener {
        void onBoatClick(Boat boat);
    }

    public BoatAdapter(List<Boat> values, Context context, OnBoatClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.values = values;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @Override
    public BoatAdapter.BoatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.boat_item, parent, false);
        return new BoatAdapter.BoatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BoatAdapter.BoatViewHolder holder, final int position) {
        holder.bindData(values.get(position));
    }


    public class BoatViewHolder extends RecyclerView.ViewHolder {
        // Atributes declaration
        public TextView id;
        public TextView code;
        public TextView name;
        public TextView registration;

        // Constructor declaration
        public BoatViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.boatIdText);
            code = view.findViewById(R.id.boatCodeText);
            name = view.findViewById(R.id.boatNameText);
            registration = view.findViewById(R.id.boatRegistrationText);
        }

        // Methods declaration binding and toString
        public void bindData(Boat boat) {
            id.setText(String.valueOf(boat.getId()));
            code.setText(boat.getCode());
            name.setText(boat.getName());
            registration.setText(boat.getRegistration());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBoatClick(boat);
                }
            });

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'" + " '" + registration.getText() + "'";
        }

    }
}
